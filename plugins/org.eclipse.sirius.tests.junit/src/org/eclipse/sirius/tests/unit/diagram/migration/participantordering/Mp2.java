/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.migration.participantordering;

import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.osgi.framework.Version;

public class Mp2 extends AbstractRepresentationsFileMigrationParticipant {
    public Mp2() {
    }

    @Override
    public Version getMigrationVersion() {
        return RepresentationsFileMigrationService.REPRESENTATION_FILE_MIGRATION_PARTICIPANT_SORT_THRESHOLD;
    }
};
