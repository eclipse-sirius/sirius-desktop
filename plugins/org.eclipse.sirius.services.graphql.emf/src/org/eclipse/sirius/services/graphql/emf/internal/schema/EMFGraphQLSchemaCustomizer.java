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
package org.eclipse.sirius.services.graphql.emf.internal.schema;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLSchemaCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;

import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema.Builder;
import graphql.schema.GraphQLType;

/**
 * Contributes the GraphQL EMF schema into the GraphQL schema registry.
 * 
 * @author sbegaudeau
 */
public class EMFGraphQLSchemaCustomizer implements ISiriusGraphQLSchemaCustomizer {

    @Override
    public String getIdentifier() {
        return EcorePackage.eNS_URI;
    }

    @Override
    public Builder customize(Builder graphQLSchemaBuilder, Function<GraphQLType, SiriusGraphQLFilterStatus> typeFilter, ISiriusGraphQLTypeCustomizer graphQLTypeCustomizer) {
        Set<GraphQLType> types = new LinkedHashSet<>();

        types.add(new EObjectTypeProvider().getType(graphQLTypeCustomizer));
        types.add(new DynamicEObjectTypeProvider().getType(graphQLTypeCustomizer));
        HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache = new HashMap<>();

        // @formatter:off
        types.addAll(new EPackageTypesProvider(EcorePackage.eINSTANCE)
                .cache(eDataTypeToOutputTypeCache)
                .getTypes(graphQLTypeCustomizer));
        // @formatter:on

        types.removeIf(graphQLType -> SiriusGraphQLFilterStatus.REJECT.equals(typeFilter.apply(graphQLType)));

        return graphQLSchemaBuilder.additionalTypes(types);
    }
}
