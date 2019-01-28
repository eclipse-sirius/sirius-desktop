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
package org.eclipse.sirius.services.graphql.workspace.internal.schema;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;

import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

/**
 * Used to resolve the type of the resources.
 *
 * @author sbegaudeau
 */
public final class ResourceTypeResolver {
    /**
     * The constructor.
     */
    private ResourceTypeResolver() {
        // Prevent instantiation
    }

    /**
     * Returns the type resolver.
     *
     * @return The type resolver
     */
    public static TypeResolver build() {
        return environment -> {
            GraphQLObjectType type = null;
            Object object = environment.getObject();
            if (object instanceof IProject) {
                type = environment.getSchema().getObjectType(WorkspaceSchemaConstants.PROJECT_TYPE);
            } else if (object instanceof IFolder) {
                type = environment.getSchema().getObjectType(WorkspaceSchemaConstants.FOLDER_TYPE);
            } else if (object instanceof IFile) {
                type = environment.getSchema().getObjectType(WorkspaceSchemaConstants.FILE_TYPE);
            }
            return type;
        };
    }
}
