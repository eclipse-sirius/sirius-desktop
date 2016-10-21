/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.osgi.framework.Version;

/**
 * This migration participant is used to artificially update the migration
 * version so that it is newer than the last migration participants of property
 * view. This is useful if the VSM has been created in "property view" context
 * and then is opened in a none "property view" context because in that case,
 * without this participant, the VSM would be considered with a too recent
 * version compared to the Sirius last migration participant version.
 * 
 * @author lfasani
 */
public class EmptyMigrationForPropertyViewMigrationParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The version of the migration.
     */
    private static final Version MIGRATION_VERSION = new Version("11.1.1.201610211630"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }
}
