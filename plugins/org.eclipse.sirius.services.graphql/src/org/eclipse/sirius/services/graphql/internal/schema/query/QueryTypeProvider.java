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
package org.eclipse.sirius.services.graphql.internal.schema.query;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;
import org.eclipse.sirius.services.graphql.internal.entities.SiriusGraphQLUser;
import org.eclipse.sirius.services.graphql.internal.schema.query.user.UserTypesProvider;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the Query type of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class QueryTypeProvider implements ISiriusGraphQLTypeProvider {

    /**
     * The name of the Query type.
     */
    public static final String QUERY_TYPE = "Query"; //$NON-NLS-1$

    /**
     * The name of the viewer field.
     */
    private static final String VIEWER_FIELD = "viewer"; //$NON-NLS-1$

    @Override
    public GraphQLObjectType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(QUERY_TYPE)
                .field(this.getViewerField());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(QUERY_TYPE, builder);
        return customizedBuilder.build();
    }

    /**
     * Returns the viewer field.
     *
     * @return The viewer field
     */
    private GraphQLFieldDefinition getViewerField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(VIEWER_FIELD)
                .type(new GraphQLTypeReference(UserTypesProvider.USER_TYPE))
                .dataFetcher(this.getViewerDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the viewer data fetcher.
     *
     * @return The viewer data fetcher
     */
    private DataFetcher<SiriusGraphQLUser> getViewerDataFetcher() {
        return environment -> new SiriusGraphQLUser();
    }

}
