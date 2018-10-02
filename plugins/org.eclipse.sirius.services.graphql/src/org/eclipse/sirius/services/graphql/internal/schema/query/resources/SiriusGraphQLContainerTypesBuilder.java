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
package org.eclipse.sirius.services.graphql.internal.schema.query.resources;

import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder.EDGE_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLResourceTypesBuilder.RESOURCE_TYPE;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;

/**
 * Used to create the Container interface of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLContainerTypesBuilder implements ISiriusGraphQLTypesBuilder {
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
    public Set<GraphQLType> getTypes() {
        GraphQLObjectType resourceEdge = new SiriusGraphQLEdgeTypeBuilder(CONTAINER_RESOURCE_EDGE_TYPE, RESOURCE_TYPE).build();
        GraphQLObjectType resourceConnection = new SiriusGraphQLConnectionTypeBuilder(CONTAINER_RESOURCE_CONNECTION_TYPE, CONTAINER_RESOURCE_EDGE_TYPE).build();

        // @formatter:off
        GraphQLInterfaceType container = GraphQLInterfaceType.newInterface()
                .name(CONTAINER_TYPE)
                .field(SiriusGraphQLContainerResourcesField.build())
                .typeResolver(SiriusGraphQLResourceTypeResolver.build())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(container);
        types.add(resourceEdge);
        types.add(resourceConnection);
        return types;
    }
}
