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
package org.eclipse.sirius.business.internal.migration.description;

import org.osgi.framework.Version;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.viewpoint.description.Group;

/**
 * The default participant for VSM migration rules. Updates the version
 * attribute to the last migration version.
 * 
 * @author fbarbin
 * 
 */
public class DefaultVSMMigrationParticipant extends AbstractVSMMigrationParticipant {
    /**
     * The VP version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("6.5.0.201208161001");

    /**
     * {@inheritDoc}
     */
    public void postLoad(Group group, Version loadedVersion) {
        super.postLoad(group, loadedVersion);
        group.setVersion(VSMMigrationService.getInstance().getLastMigrationVersion().toString());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.migration.IMigrationParticipant#getMigrationVersion()
     */
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }
}
