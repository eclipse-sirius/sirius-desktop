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

import static org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnectionTypeProvider.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLEdgeTypeProvider.EDGE_SUFFIX;
import static org.eclipse.sirius.services.graphql.workspace.internal.schema.ResourceTypeProvider.RESOURCE_TYPE;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypesProvider;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnectionTypeProvider;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLEdgeTypeProvider;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLInterfaceType.Builder;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;

/**
 * Used to create the Container interface of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class ContainerTypesProvider implements ISiriusGraphQLTypesProvider {
    /**
     * The name of the Container type.
     */
    public static final String CONTAINER_TYPE = "Container"; //$NON-NLS-1$

    /**
     * The name of the Container to Resource connection type.
     */
    public static final String CONTAINER_RESOURCE_CONNECTION_TYPE = CONTAINER_TYPE + RESOURCE_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the Container to Resource edge type.
     */
    public static final String CONTAINER_RESOURCE_EDGE_TYPE = CONTAINER_TYPE + RESOURCE_TYPE + EDGE_SUFFIX;

    @Override
    public Set<GraphQLType> getTypes(ISiriusGraphQLTypeCustomizer customizer) {
        GraphQLObjectType resourceEdge = new SiriusGraphQLEdgeTypeProvider(CONTAINER_RESOURCE_EDGE_TYPE, RESOURCE_TYPE).getType(customizer);
        GraphQLObjectType resourceConnection = new SiriusGraphQLConnectionTypeProvider(CONTAINER_RESOURCE_CONNECTION_TYPE, CONTAINER_RESOURCE_EDGE_TYPE).getType(customizer);

        // @formatter:off
        Builder containerBuilder = GraphQLInterfaceType.newInterface()
                .name(CONTAINER_TYPE)
                .field(ContainerResourcesField.build())
                .typeResolver(ResourceTypeResolver.build());
        // @formatter:on

        Builder customizedContainerBuilder = customizer.customize(CONTAINER_TYPE, containerBuilder);

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(customizedContainerBuilder.build());
        types.add(resourceEdge);
        types.add(resourceConnection);
        return types;
    }
}
