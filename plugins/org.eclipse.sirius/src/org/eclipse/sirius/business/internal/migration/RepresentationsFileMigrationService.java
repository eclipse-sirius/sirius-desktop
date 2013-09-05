/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
