/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration.description;

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.osgi.framework.Version;

import com.google.common.collect.Sets;

/**
 * The VSM migration participant for the migration from OptionalLayer to
 * AdditionalLayer.
 * 
 * @author fbarbin
 * 
 */
public class OptionalLayersVSMMigrationParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("6.9.0.201308011200"); //$NON-NLS-1$

    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            Set<String> descriptionsNsUri = Sets.newHashSet(DescriptionPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getNsURI());
            if (ePackage != null && ePackage.getNsURI() != null && descriptionsNsUri.contains(ePackage.getNsURI()) && name.equals("OptionalLayer")) { //$NON-NLS-1$
                return DescriptionPackage.eINSTANCE.getAdditionalLayer();
            }
        }
        return super.getType(ePackage, name, loadedVersion);
    }

    @Override
    public EStructuralFeature getLocalElement(EClass eClass, String name, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (DescriptionPackage.eINSTANCE.getDiagramDescription().isSuperTypeOf(eClass) && name.equals("optionalLayers")) { //$NON-NLS-1$
                return DescriptionPackage.eINSTANCE.getDiagramDescription_AdditionalLayers();
            }
        }
        return super.getLocalElement(eClass, name, loadedVersion);
    }

    @Override
    public Option<String> getNewFragment(String uriFragment) {
        if (uriFragment.contains("@optionalLayers")) { //$NON-NLS-1$
            String newUriFragment = uriFragment.replaceAll("@optionalLayers", "@additionalLayers"); //$NON-NLS-1$ //$NON-NLS-2$
            return Options.newSome(newUriFragment);
        } else {
            return super.getNewFragment(uriFragment);
        }
    }
}
