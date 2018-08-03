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

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the project field of the resource.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLResourceProjectField {
    /**
     * The name of the project field.
     */
    private static final String PROJECT_FIELD = "project"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLResourceProjectField() {
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
                .type(new GraphQLNonNull(new GraphQLTypeReference(SiriusGraphQLProjectTypesBuilder.PROJECT_TYPE)))
                .build();
        // @formatter:on
    }
}
