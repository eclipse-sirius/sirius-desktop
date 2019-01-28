/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.workspace.api;

/**
 * Constants from the workspace schema.
 * 
 * @author sbegaudeau
 */
public final class WorkspaceSchemaConstants {

    /**
     * The identifier of the schema.
     */
    public static final String IDENTIFIER = "org.eclipse.sirius.services.graphql.workspace"; //$NON-NLS-1$

    /**
     * The name of the Project type.
     */
    public static final String PROJECT_TYPE = "Project"; //$NON-NLS-1$

    /**
     * The name of the Folder type.
     */
    public static final String FOLDER_TYPE = "Folder"; //$NON-NLS-1$

    /**
     * The name of the File type.
     */
    public static final String FILE_TYPE = "File"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private WorkspaceSchemaConstants() {
        // Prevent instantiation
    }
}
