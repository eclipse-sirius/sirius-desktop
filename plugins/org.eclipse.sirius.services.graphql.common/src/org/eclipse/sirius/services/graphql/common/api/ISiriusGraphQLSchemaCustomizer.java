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
package org.eclipse.sirius.services.graphql.common.api;

import java.util.function.Function;

import graphql.schema.GraphQLSchema.Builder;
import graphql.schema.GraphQLType;

/**
 * The GraphQL schema customizer is used to customize a GraphQL schema.
 * 
 * @author sbegaudeau
 */
public interface ISiriusGraphQLSchemaCustomizer {
    /**
     * Returns the identifier of the schema customizer.
     * 
     * @return The identifier of the schema customizer
     */
    String getIdentifier();

    /**
     * Customize the given GraphQL schema builder.
     * 
     * @param graphQLSchemaBuilder
     *            The GraphQL schema builder
     * @param typeFilter
     *            A function used to filter the types to be contributed to the schema builder
     * @param graphQLTypeCustomizer
     *            Used to customize the type to be added to the schema builder
     * @return The given GraphQL schema builder
     */
    Builder customize(Builder graphQLSchemaBuilder, Function<GraphQLType, SiriusGraphQLFilterStatus> typeFilter, ISiriusGraphQLTypeCustomizer graphQLTypeCustomizer);
}
