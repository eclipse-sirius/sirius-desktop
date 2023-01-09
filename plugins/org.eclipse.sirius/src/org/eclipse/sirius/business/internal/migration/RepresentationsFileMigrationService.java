/*******************************************************************************
 * Copyright (c) 2012, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.migration;

import java.util.stream.Collectors;

import org.eclipse.sirius.business.api.migration.IMigrationParticipant;
import org.osgi.framework.Version;

/**
 * Aird migration service implementation.
 * 
 * @author fbarbin
 * 
 */
public final class RepresentationsFileMigrationService extends AbstractSiriusMigrationService {
    /**
     * After this version the migration participant are ordered.
     */
    public static final Version REPRESENTATION_FILE_MIGRATION_PARTICIPANT_SORT_THRESHOLD = new Version("15.1.0.000000000000"); //$NON-NLS-1$

    private static RepresentationsFileMigrationService instance = new RepresentationsFileMigrationService();

    private RepresentationsFileMigrationService() {
        loadContributions();
    }

    @Override
    protected void loadContributions() {
        super.loadContributions();

        // sort the participant which version is greater than MIGRATION_VERSION_FROM_WHICH_ORDER_PARTICIPANT
        delegatesParticipants = delegatesParticipants.stream()//
                .sorted((participant1, participant2) -> {
                    Version migrationVersion1 = participant1.getMigrationVersion();
                    Version migrationVersion2 = participant2.getMigrationVersion();
                    if (migrationVersion1.compareTo(REPRESENTATION_FILE_MIGRATION_PARTICIPANT_SORT_THRESHOLD) > 0 || migrationVersion2.compareTo(REPRESENTATION_FILE_MIGRATION_PARTICIPANT_SORT_THRESHOLD) > 0) {
                        return migrationVersion1.compareTo(migrationVersion2);
                    }
                    return 0;
                })//
                .collect(Collectors.toList());
    }

    public static RepresentationsFileMigrationService getInstance() {
        return instance;
    }

    @Override
    protected String getKind() {
        return IMigrationParticipant.REPRESENTATIONSFILE_KIND;
    }

}
