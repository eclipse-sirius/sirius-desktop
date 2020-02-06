/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
 * This migration participant does nothing in the model but changes the version because of a possible incompatibility
 * with previous Sirius version if the new "Tunnel" jump link type is used.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 *
 */
public class JumpLinkNewTypeMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * This version corresponds to the first Sirius version using GMF 1.13.0 with the new Jump Link Type.
     */
    public static final Version MIGRATION_VERSION_NEW_JUMP_LINK_TYPE = new Version("14.3.1.202003101500"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION_NEW_JUMP_LINK_TYPE;
    }
}
