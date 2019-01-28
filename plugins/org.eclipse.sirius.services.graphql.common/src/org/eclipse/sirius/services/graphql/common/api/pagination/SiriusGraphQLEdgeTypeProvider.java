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
package org.eclipse.sirius.services.graphql.common.api.pagination;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create Edge types for the GraphQL schema. See https://facebook.github.io/relay/graphql/connections.htm for
 * the specification.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLEdgeTypeProvider implements ISiriusGraphQLTypeProvider {
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
    public SiriusGraphQLEdgeTypeProvider(String typeName, String targetName) {
        this.typeName = typeName;
        this.targetName = targetName;
    }

    @Override
    public GraphQLObjectType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(this.typeName)
                .field(this.getNodeField())
                .field(this.getCursorField());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(this.typeName, builder);
        return customizedBuilder.build();
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
