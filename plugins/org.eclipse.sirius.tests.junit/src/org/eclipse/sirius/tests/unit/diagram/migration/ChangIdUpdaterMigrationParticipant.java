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
 * This migration participant update DRpresentationDescriptor change id related to all loaded {@link DDiagram}. Its
 * purpose is to test the API of {@link AbstractRepresentationsFileMigrationParticipant#updateChangeId()}.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class ChangIdUpdaterMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    private static final Version MIGRATION_VERSION = new Version("15.0.0.201412231738"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : new DViewQuery(dView).getLoadedRepresentations().stream().filter(DDiagram.class::isInstance).map(DDiagram.class::cast).collect(Collectors.toList())) {
                    updateChangeId(dAnalysis, dDiagram);
                }
            }

        }
    }
}
