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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.osgi.framework.Version;

/**
 * Migration contribution to handle the listContainer attribute deletion.
 * ContainerMapping which had a listAttribute = true now have a List
 * childrenPresentation = List.
 * 
 * @author mporhel
 * 
 */
public class ListContainerAttributeMigrationParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("6.7.0.201302071200"); //$NON-NLS-1$

    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleFeature(EObject owner, EStructuralFeature unkownFeature, Object valueOfUnknownFeature) {
        super.handleFeature(owner, unkownFeature, valueOfUnknownFeature);
        if (owner instanceof ContainerMapping && "listContainer".equals(unkownFeature.getName())) { //$NON-NLS-1$
            ContainerMapping mapping = (ContainerMapping) owner;
            if (valueOfUnknownFeature instanceof String && Boolean.parseBoolean((String) valueOfUnknownFeature)) {
                mapping.setChildrenPresentation(ContainerLayout.LIST);
            }
        }
    }
}
