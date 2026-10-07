/*******************************************************************************
 * Copyright (c) 2026 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.migration;

import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.osgi.framework.Version;

/**
 * This migration participant does not modify the model; it only updates its version to address a potential
 * incompatibility with earlier Sirius versions that included the experimental ELK feature.
 *
 * That feature introduced a migration participant registered last. As a result, a model migrated with that participant
 * cannot be opened in a Sirius version where the experimental feature is not installed, even if that Sirius version is
 * more recent.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class PostExperimentalFeatureMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * This version corresponds to the first migration participant added after
     * EmptyJunctionPointsStringValueStyleMigrationParticipant.
     */
    public static final Version MIGRATION_VERSION_POST_EXPERIMENTAL_FEATURE = new Version("15.4.16.202610070916"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION_POST_EXPERIMENTAL_FEATURE;
    }
}
