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

import java.util.Iterator;

import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.osgi.framework.Version;

/**
 * Remove the old migration tags used before the refactoring of the migration
 * process.
 * 
 * @author lredor
 */
public class RemoveOldMigrationAnnotations extends AbstractRepresentationsFileMigrationParticipant {
    /**
     * The VP version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("6.5.0.201210011230"); //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    public void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        super.postLoad(dAnalysis, loadedVersion);
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            deleteOldMigrationAnnotations(dAnalysis);
        }
    }

    private void deleteOldMigrationAnnotations(DAnalysis dAnalysis) {
        Iterator<DAnnotationEntry> iterator = dAnalysis.getEAnnotations().iterator();
        while (iterator.hasNext()) {
            DAnnotationEntry entry = iterator.next();
            if (entry.getSource().equals("Migration")) { //$NON-NLS-1$
                iterator.remove();
            }
        }

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
