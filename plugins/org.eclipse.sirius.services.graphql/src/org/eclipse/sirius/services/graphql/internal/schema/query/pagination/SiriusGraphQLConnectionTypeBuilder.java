/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.services.graphql.internal.schema.query.pagination;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create connection types for the GraphQL schema. See https://facebook.github.io/relay/graphql/connections.htm
 * for the specification.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLConnectionTypeBuilder {
    /**
     * The suffix of all connection types.
     */
    public static final String CONNECTION_SUFFIX = "Connection"; //$NON-NLS-1$

    /**
     * The name of the total count field.
     */
    private static final String TOTAL_COUNT_FIELD = "totalCount"; //$NON-NLS-1$

    /**
     * The name of the edges field.
     */
    private static final String EDGES_FIELD = "edges"; //$NON-NLS-1$

    /**
     * The name of the pageInfo field.
     */
    private static final String PAGE_INFO_FIELD = "pageInfo"; //$NON-NLS-1$

    /**
     * The name of the type of the connection.
     */
    private String typeName;

    /**
     * The name of the type of the edge.
     */
    private String edgeTypeName;

    /**
     * The constructor.
     *
     * @param typeName
     *            The name of the type of the connection
     * @param edgeTypeName
     *            The name of the type of the edge
     */
    public SiriusGraphQLConnectionTypeBuilder(String typeName, String edgeTypeName) {
        this.typeName = typeName;
        this.edgeTypeName = edgeTypeName;
    }

    /**
     * Creates the GraphQL type.
     *
     * @return The GraphQL type
     */
    public GraphQLObjectType build() {
        // @formatter:off
        return GraphQLObjectType.newObject()
                .name(this.typeName)
                .field(this.getTotalCountField())
                .field(this.getEdgesField())
                .field(this.getPageInfoField())
                .build();
        // @formatter:on
    }

    /**
     * Returns the total count field.
     *
     * @return The total count field
     */
    private GraphQLFieldDefinition getTotalCountField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(TOTAL_COUNT_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLInt))
                .build();
        // @formatter:on
    }

    /**
     * Returns the edges field.
     *
     * @return The edges field
     */
    private GraphQLFieldDefinition getEdgesField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(EDGES_FIELD)
                .type(new GraphQLList(new GraphQLTypeReference(this.edgeTypeName)))
                .build();
        // @formatter:on
    }

    /**
     * Returns the pageInfo field.
     *
     * @return The pageInfo field
     */
    private GraphQLFieldDefinition getPageInfoField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(PAGE_INFO_FIELD)
                .type(new GraphQLNonNull(new GraphQLTypeReference(SiriusGraphQLPageInfoTypeBuilder.PAGE_INFO_TYPE)))
                .build();
        // @formatter:on
    }
}
