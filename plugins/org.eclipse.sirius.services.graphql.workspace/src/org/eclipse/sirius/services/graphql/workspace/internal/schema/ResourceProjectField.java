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

import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the project field of the resource.
 *
 * @author sbegaudeau
 */
public final class ResourceProjectField {
    /**
     * The name of the project field.
     */
    private static final String PROJECT_FIELD = "project"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private ResourceProjectField() {
        // Prevent instantiation
    }

    /**
     * Returns the project field.
     *
     * @return The project field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(PROJECT_FIELD)
                .type(new GraphQLNonNull(new GraphQLTypeReference(WorkspaceSchemaConstants.PROJECT_TYPE)))
                .build();
        // @formatter:on
    }
}
