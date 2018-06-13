/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.graphql.internal.schema.query.resources;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

/**
 * Used to resolve the type of the resources.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLResourceTypeResolver {
    /**
     * The constructor.
     */
    private SiriusGraphQLResourceTypeResolver() {
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
                type = environment.getSchema().getObjectType(SiriusGraphQLProjectTypesBuilder.PROJECT_TYPE);
            } else if (object instanceof IFolder) {
                type = environment.getSchema().getObjectType(SiriusGraphQLFolderTypesBuilder.FOLDER_TYPE);
            } else if (object instanceof IFile) {
                type = environment.getSchema().getObjectType(SiriusGraphQLFileTypesBuilder.FILE_TYPE);
            }
            return type;
        };
    }
}
