/*******************************************************************************
 * Copyright (c) 2012, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.migration.description;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.sirius.business.api.migration.IMigrationParticipant;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;

/**
 * VSM Implementation of {@link AbstractSiriusMigrationService}.
 * 
 * @author fbarbin
 * 
 */
public final class VSMMigrationService extends AbstractSiriusMigrationService {

    private static VSMMigrationService instance = new VSMMigrationService();

    private static AtomicBoolean loaded = new AtomicBoolean();

    /**
     * Returns the single instance, after initializing it if required.
     * 
     * @return the singleton instance.
     */
    public static VSMMigrationService getInstance() {
        if (!loaded.get()) {
            /* Mark as loaded *before*, as loadContributions can trigger re-entrant calls 
             * and we do not want to actually call loadContributions multiple times.
             */
            loaded.set(true); 
            instance.loadContributions();
        }
        return instance;
    }

    @Override
    protected String getKind() {
        return IMigrationParticipant.VSM_KIND;
    }

}
