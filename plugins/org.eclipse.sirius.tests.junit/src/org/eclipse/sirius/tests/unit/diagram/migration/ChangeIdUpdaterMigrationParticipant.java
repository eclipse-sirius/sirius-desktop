/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.stream.Collectors;

import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * This test migration participant update DRpresentationDescriptor change id related to all loaded {@link DDiagram}. Its
 * purpose is to test the API of {@link AbstractRepresentationsFileMigrationParticipant#updateChangeId()}.
 * 
 * Its version will never been serialized as new participants have been provided for 6.3.0 with an higher version.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class ChangeIdUpdaterMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * This version corresponds to a changeId update test migration. Its purpose is to test the migration API which can
     * be used by migration participants to update the change id when they modify the content of a representation.
     */
    public static final Version MIGRATION_VERSION = new Version("14.3.0.201906171400"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : new DViewQuery(dView).getLoadedRepresentations().stream().filter(DDiagram.class::isInstance).map(DDiagram.class::cast).collect(Collectors.toList())) {
                    // As this participant is only used for test purpose, we count the modified diagrams and nothing
                    // will be logged to indicate the migration effect.
                    updateChangeId(dAnalysis, dDiagram);
                }
            }

        }
    }
}
