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
package org.eclipse.sirius.services.graphql.core.internal.schema;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLSchemaCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;
import org.eclipse.sirius.services.graphql.common.api.pagination.PageInfoTypeProvider;
import org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants;

import graphql.schema.GraphQLSchema.Builder;
import graphql.schema.GraphQLType;

/**
 * Contributes the Sirius GraphQL Core schema into the GraphQL schema registry.
 * 
 * @author sbegaudeau
 */
public class CoreSchemaCustomizer implements ISiriusGraphQLSchemaCustomizer {

    @Override
    public String getIdentifier() {
        return CoreSchemaConstants.IDENTIFIER;
    }

    @Override
    public Builder customize(Builder graphQLSchemaBuilder, Function<GraphQLType, SiriusGraphQLFilterStatus> typeFilter, ISiriusGraphQLTypeCustomizer graphQLTypeCustomizer) {
        Set<GraphQLType> types = new LinkedHashSet<>();

        types.addAll(new ViewpointTypesBuilder().getTypes(graphQLTypeCustomizer));
        types.add(new RepresentationDescriptionTypeProvider().getType(graphQLTypeCustomizer));
        types.add(new DiagramDescriptionTypeProvider().getType(graphQLTypeCustomizer));
        types.add(new RepresentationTypeProvider().getType(graphQLTypeCustomizer));
        types.add(new DiagramTypeProvider().getType(graphQLTypeCustomizer));
        types.add(new PageInfoTypeProvider().getType(graphQLTypeCustomizer));

        types.removeIf(graphQLType -> SiriusGraphQLFilterStatus.REJECT.equals(typeFilter.apply(graphQLType)));

        return graphQLSchemaBuilder.additionalTypes(types);
    }

}
