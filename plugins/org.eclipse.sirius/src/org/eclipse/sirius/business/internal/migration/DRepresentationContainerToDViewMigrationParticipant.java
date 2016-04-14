/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;

/**
 * Migration to handle the merge of DRepresentationContainer into DView.
 * 
 * @author mporhel
 */
public class DRepresentationContainerToDViewMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {
    /**
     * The VP version for which this migration is added.
     */
    /**
     * The version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("11.0.0.201604141600"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        EClassifier eClass = null;
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (ViewpointPackage.eINSTANCE.equals(ePackage) && "DRepresentationContainer".equals(name)) { //$NON-NLS-1$
                eClass = ViewpointPackage.eINSTANCE.getDView();
            }
        }
        return eClass;
    }
}
