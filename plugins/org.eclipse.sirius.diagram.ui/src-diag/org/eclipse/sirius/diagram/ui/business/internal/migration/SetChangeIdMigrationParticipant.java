/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * This migration participant set a changeId for each {@link DRepresentationDescriptor} that does not have a changeId set.
 * 
 * @author Glenn Plouhinec
 *
 */
public class SetChangeIdMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Migration version.
     */
    public static final Version MIGRATION_VERSION = new Version("14.6.0.202109231100"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            StringBuilder sb = new StringBuilder(Messages.SetChangeIdMigrationParticipant_title);
            EList<DView> ownedViews = dAnalysis.getOwnedViews();
            boolean migrationOccured = false;
            for (DView view : ownedViews) {
                List<DRepresentationDescriptor> ownedRepresentationsDescriptors = view.getOwnedRepresentationDescriptors();
                for (DRepresentationDescriptor descriptor : ownedRepresentationsDescriptors) {
                    if (descriptor.getChangeId() == null) {
                        migrationOccured = true;
                        sb.append(MessageFormat.format(Messages.SetChangeIdMigrationParticipant_changeIDModified, descriptor.getName()));
                        descriptor.setChangeId(UUID.randomUUID().toString());
                    }
                }
            }
            if (migrationOccured) {
                DiagramPlugin.getDefault().logInfo(sb.toString());
                migrationOccured = false;
            }
        }
    }

}
