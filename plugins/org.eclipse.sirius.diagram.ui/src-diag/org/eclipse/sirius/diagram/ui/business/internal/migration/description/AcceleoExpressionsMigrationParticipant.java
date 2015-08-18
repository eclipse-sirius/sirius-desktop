/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.osgi.framework.Version;

/**
 * All Acceleo 3 references have been removed in Sirius Metamodels to avoid a
 * dependency to Acceleo 3. This migration contribution goal is to replace each
 * default value in VSMs by the old default value expression to avoid a change
 * of behavior.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class AcceleoExpressionsMigrationParticipant extends AbstractVSMMigrationParticipant {

    private static final String OLD_EXPRESSION_DEFAULT_VALUE = "[eContents()->size()/]"; //$NON-NLS-1$

    /**
     * The Sirius version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("10.0.0.201411061000"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EObject updateCreatedObject(EObject newObject, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (newObject instanceof EdgeStyleDescription) {
                ((EdgeStyleDescription) newObject).setSizeComputationExpression(OLD_EXPRESSION_DEFAULT_VALUE);
            } else if (newObject instanceof InterpolatedColor) {
                ((InterpolatedColor) newObject).setColorValueComputationExpression(OLD_EXPRESSION_DEFAULT_VALUE);
            }
        }
        return newObject;
    }
}
