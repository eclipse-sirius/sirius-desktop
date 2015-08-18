/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration.description;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.osgi.framework.Version;

/**
 * Default values of ArcHeight and ArcWidth of rounded corners have been
 * modified to 10 in Sirius metamodels. This migration goal is to replace each
 * default value of ArcHeight and ArcWidth by 1, the previous default value.
 * 
 * @author bgrouhan
 * 
 */
public class RoundedCornerDefaultValuesMigrationParticipant extends AbstractVSMMigrationParticipant {

    private static final int OLD_DEFAULT_VALUE = 1;

    /**
     * The Sirius version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("10.0.0.201504091800"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EObject updateCreatedObject(EObject newObject, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (newObject instanceof ContainerStyleDescription) {
                ((ContainerStyleDescription) newObject).setArcHeight(OLD_DEFAULT_VALUE);
                ((ContainerStyleDescription) newObject).setArcWidth(OLD_DEFAULT_VALUE);
            }
        }
        return newObject;
    }
}
