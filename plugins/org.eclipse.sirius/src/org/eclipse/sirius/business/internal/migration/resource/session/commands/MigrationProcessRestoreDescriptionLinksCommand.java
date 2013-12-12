/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration.resource.session.commands;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.internal.migration.resource.MigrationUtil;

/**
 * A Recording Command to restore DescriptionLinks.
 * 
 * @author smonnier
 */
public class MigrationProcessRestoreDescriptionLinksCommand extends IdentityCommand {

    /**
     * This will hold a reference to all description files providing viewpoints
     * according to
     * {@link org.eclipse.sirius.business.api.componentization.ViewpointRegistry#getViewpoints()}
     * .
     */
    private Set<Resource> descriptionFiles;

    private URI proxyURI;

    private Entry<EObject, Collection<Setting>> entry;

    /**
     * Default constructor.
     * 
     * @param proxyURI
     *            {@link URI}
     * @param entry
     *            {@link Entry}
     * @param descriptionFiles
     *            {@link Collection}
     */
    public MigrationProcessRestoreDescriptionLinksCommand(URI proxyURI, Entry<EObject, Collection<Setting>> entry, Set<Resource> descriptionFiles) {
        super();
        this.proxyURI = proxyURI;
        this.entry = entry;
        this.descriptionFiles = descriptionFiles;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void execute() {
        final Resource description = findDescriptionResource(proxyURI);
        if (description != null) {
            for (final Setting setting : entry.getValue()) {
                final EStructuralFeature feature = setting.getEObject().eClass().getEStructuralFeature(setting.getEStructuralFeature().getName());
                final EObject target = description.getEObject(proxyURI.fragment());
                if (feature.isMany()) {
                    ((Collection) setting.getEObject().eGet(feature)).add(target);
                } else {
                    /*
                     * feature should be changeable
                     */
                    if (feature.isChangeable()) {
                        setting.getEObject().eSet(feature, target);
                    }
                }
            }
        }
        descriptionFiles = null;
        proxyURI = null;
        entry = null;

    }

    private Resource findDescriptionResource(final URI uri) {
        String expectedDescription = uri.lastSegment();
        if (expectedDescription.endsWith('.' + MigrationUtil.MODELER_DESCRIPTION_FILE_EXTENSION_V3)) {
            expectedDescription = expectedDescription.replace('.' + MigrationUtil.MODELER_DESCRIPTION_FILE_EXTENSION_V3, '.' + SiriusUtil.DESCRIPTION_MODEL_EXTENSION);
        }
        for (final Resource candidate : descriptionFiles) {
            final URI candidateURI = candidate.getURI();
            // The second test of this if allows us to handle a use case where
            // two odesign of the same name are in the registry
            if (candidateURI.lastSegment().equals(expectedDescription) && candidate.getEObject(uri.fragment()) != null) {
                return candidate;
            }
        }
        // Should probably ask the user to select odesign file.
        return null;
    }

    /**
     * Overridden to avoid the CommandStack to keep a reference to this command.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canUndo() {
        return false;
    }

}
