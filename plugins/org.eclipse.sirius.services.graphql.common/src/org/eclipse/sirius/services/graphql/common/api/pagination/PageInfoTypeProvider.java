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
import graphql.schema.GraphQLType;

/**
 * Used to create PageInfo types for the GraphQL schema. See https://facebook.github.io/relay/graphql/connections.htm
 * for the specification.
 *
 * @author sbegaudeau
 */
public class PageInfoTypeProvider implements ISiriusGraphQLTypeProvider {
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

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(PAGE_INFO_TYPE)
                .field(this.getHasPreviousPageField())
                .field(this.getHasNextPageField());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(PAGE_INFO_TYPE, builder);
        return customizedBuilder.build();
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
