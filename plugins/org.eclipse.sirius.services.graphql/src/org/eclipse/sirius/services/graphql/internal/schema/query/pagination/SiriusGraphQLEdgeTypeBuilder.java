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
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create Edge types for the GraphQL schema. See https://facebook.github.io/relay/graphql/connections.htm for
 * the specification.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLEdgeTypeBuilder {
    /**
     * The suffix of all edge types.
     */
    public static final String EDGE_SUFFIX = "Edge"; //$NON-NLS-1$

    /**
     * The name of the node field.
     */
    private static final String NODE_FIELD = "node"; //$NON-NLS-1$

    /**
     * The name of the cursor field.
     */
    private static final String CURSOR_FIELD = "cursor"; //$NON-NLS-1$

    /**
     * The name of the type of the edge.
     */
    private String typeName;

    /**
     * The name of the target of the edge.
     */
    private String targetName;

    /**
     * The constructor.
     *
     * @param typeName
     *            The name of the type of the edge
     *
     * @param targetName
     *            The name of the target
     */
    public SiriusGraphQLEdgeTypeBuilder(String typeName, String targetName) {
        this.typeName = typeName;
        this.targetName = targetName;
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
                .field(this.getNodeField())
                .field(this.getCursorField())
                .build();
        // @formatter:on
    }

    /**
     * Returns the node field.
     *
     * @return The node field
     */
    private GraphQLFieldDefinition getNodeField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(NODE_FIELD)
                .type(new GraphQLTypeReference(this.targetName))
                .build();
        // @formatter:on
    }

    /**
     * Returns the cursor field.
     *
     * @return The cursor field
     */
    private GraphQLFieldDefinition getCursorField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(CURSOR_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }
}
