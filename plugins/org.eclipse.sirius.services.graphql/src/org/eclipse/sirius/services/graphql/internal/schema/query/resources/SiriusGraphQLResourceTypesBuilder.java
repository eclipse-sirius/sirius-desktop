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

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLType;

/**
 * Used to create the Resource interface of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLResourceTypesBuilder implements ISiriusGraphQLTypesBuilder {
    /**
     * The name of the Resource type.
     */
    public static final String RESOURCE_TYPE = "Resource"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLInterfaceType resource = GraphQLInterfaceType.newInterface()
                .name(RESOURCE_TYPE)
                .field(SiriusGraphQLResourceNameField.build())
                .field(SiriusGraphQLResourcePathField.build())
                .field(SiriusGraphQLResourceContainerField.build())
                .field(SiriusGraphQLResourceProjectField.build())
                .typeResolver(SiriusGraphQLResourceTypeResolver.build())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(resource);
        return types;
    }

}
