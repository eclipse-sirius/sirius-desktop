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
package org.eclipse.sirius.services.graphql.internal.schema.query.pagination;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;

/**
 * Used to create PageInfo types for the GraphQL schema. See
 * https://facebook.github.io/relay/graphql/connections.htm for the
 * specification.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLPageInfoTypeBuilder {
    /**
     * The name of the PageInfo type.
     */
    public static final String PAGE_INFO_TYPE = "PageInfo"; //$NON-NLS-1$

    /**
     * The name of the hasPreviousPage field.
     */
    private static final String HAS_PREVIOUS_PAGE_FIELD = "hasPreviousPage"; //$NON-NLS-1$

    /**
     * The name of the hasNextPage field.
     */
    private static final String HAS_NEXT_PAGE_FIELD = "hasNextPage"; //$NON-NLS-1$

    /**
     * Creates the GraphQL type.
     * 
     * @return The GraphQL type
     */
    public GraphQLType build() {
        // @formatter:off
        return GraphQLObjectType.newObject()
                .name(PAGE_INFO_TYPE)
                .field(this.getHasPreviousPageField())
                .field(this.getHasNextPageField())
                .build();
        // @formatter:on
    }

    /**
     * Returns the hasPreviousPage field.
     * 
     * @return The hasPreviousPage field
     */
    private GraphQLFieldDefinition getHasPreviousPageField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(HAS_PREVIOUS_PAGE_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLBoolean))
                .build();
        // @formatter:on
    }

    /**
     * Returns the hasNextPage field.
     * 
     * @return The hasNextPage field
     */
    private GraphQLFieldDefinition getHasNextPageField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(HAS_NEXT_PAGE_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLBoolean))
                .build();
        // @formatter:on
    }
}
