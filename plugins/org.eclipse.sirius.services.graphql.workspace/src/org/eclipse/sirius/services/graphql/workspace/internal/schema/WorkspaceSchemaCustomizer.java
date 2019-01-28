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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLSchemaCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;
import org.eclipse.sirius.services.graphql.common.api.pagination.PageInfoTypeProvider;
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;

import graphql.schema.GraphQLSchema.Builder;
import graphql.schema.GraphQLType;

/**
 * Contributes the GraphQL Workspace schema into the GraphQL schema registry.
 * 
 * @author sbegaudeau
 */
public class WorkspaceSchemaCustomizer implements ISiriusGraphQLSchemaCustomizer {

    @Override
    public String getIdentifier() {
        return WorkspaceSchemaConstants.IDENTIFIER;
    }

    @Override
    public Builder customize(Builder graphQLSchemaBuilder, Function<GraphQLType, SiriusGraphQLFilterStatus> typeFilter, ISiriusGraphQLTypeCustomizer graphQLTypeCustomizer) {
        Set<GraphQLType> types = new LinkedHashSet<>();

        types.add(new ResourceTypeProvider().getType(graphQLTypeCustomizer));
        types.addAll(new ContainerTypesProvider().getTypes(graphQLTypeCustomizer));
        types.add(new ProjectTypeProvider().getType(graphQLTypeCustomizer));
        types.add(new FolderTypeProvider().getType(graphQLTypeCustomizer));
        types.add(new FileTypeProvider().getType(graphQLTypeCustomizer));
        types.add(new PageInfoTypeProvider().getType(graphQLTypeCustomizer));

        types.removeIf(graphQLType -> SiriusGraphQLFilterStatus.REJECT.equals(typeFilter.apply(graphQLType)));

        return graphQLSchemaBuilder.additionalTypes(types);
    }

}
