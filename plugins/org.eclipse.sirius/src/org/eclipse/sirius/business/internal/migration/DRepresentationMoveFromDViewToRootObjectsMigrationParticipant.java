/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * Migration to handle the move of {@link DRepresentation} from
 * DView.ownedRepresentations to the root objects of the aird resource.
 * 
 * @author lfasani
 */
public class DRepresentationMoveFromDViewToRootObjectsMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {
    /**
     * The version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("11.1.0.201608251200"); //$NON-NLS-1$

    /**
     * The name of the feature DView.ownedRepresentations which has been
     * deleted.
     */
    public static final String DVIEW_OWNED_REPRESENTATIONS_UNKNOWN_FEATURE = "ownedRepresentations"; //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void handleFeature(EObject owner, EStructuralFeature unkownFeature, Object valueOfUnknownFeature) {
        if (DVIEW_OWNED_REPRESENTATIONS_UNKNOWN_FEATURE.equals(unkownFeature.getName())) {
            if (valueOfUnknownFeature instanceof DRepresentation && owner instanceof DView) {
                owner.eResource().getContents().add((EObject) valueOfUnknownFeature);
            }
        }
    }
}
