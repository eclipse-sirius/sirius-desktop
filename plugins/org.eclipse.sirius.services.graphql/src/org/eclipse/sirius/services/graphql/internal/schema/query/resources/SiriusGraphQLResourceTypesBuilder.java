/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
