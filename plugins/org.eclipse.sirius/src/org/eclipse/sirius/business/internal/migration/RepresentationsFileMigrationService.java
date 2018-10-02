/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.business.api.migration.IMigrationParticipant;

/**
 * Aird migration service implementation.
 * 
 * @author fbarbin
 * 
 */
public final class RepresentationsFileMigrationService extends AbstractSiriusMigrationService {

    private static RepresentationsFileMigrationService instance = new RepresentationsFileMigrationService();

    private RepresentationsFileMigrationService() {
        loadContributions();
    }

    public static RepresentationsFileMigrationService getInstance() {
        return instance;
    }

    @Override
    protected String getKind() {
        return IMigrationParticipant.REPRESENTATIONSFILE_KIND;
    }

}
