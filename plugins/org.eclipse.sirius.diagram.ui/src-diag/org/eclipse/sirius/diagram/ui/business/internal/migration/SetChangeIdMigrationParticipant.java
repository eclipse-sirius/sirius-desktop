/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.helper.RepresentationHelper;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * This migration participant updates DRpresentationDescriptor.changeId.<br/>
 * Its purpose is to replace the old value that was an uid by the new one that is a time stamp representing the absolute
 * time.
 * 
 * @author Glenn Plouhinec
 * @author lfasani
 *
 */
public class SetChangeIdMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Migration version.
     */
    public static final Version MIGRATION_VERSION = new Version("15.1.0.202211301600"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            EList<DView> ownedViews = dAnalysis.getOwnedViews();
            boolean migrationOccured = false;
            for (DView view : ownedViews) {
                List<DRepresentationDescriptor> ownedRepresentationsDescriptors = view.getOwnedRepresentationDescriptors();
                for (DRepresentationDescriptor descriptor : ownedRepresentationsDescriptors) {
                    migrationOccured = true;
                    RepresentationHelper.updateChangeId(descriptor);
                }
            }
            if (migrationOccured) {
                logMigrationInfo(Messages.SetChangeIdMigrationParticipant_title);
                migrationOccured = false;
            }
        }
    }

}
