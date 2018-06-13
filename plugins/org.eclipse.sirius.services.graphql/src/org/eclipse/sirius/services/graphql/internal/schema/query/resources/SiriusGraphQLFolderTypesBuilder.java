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

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the Folder object of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLFolderTypesBuilder implements ISiriusGraphQLTypesBuilder {
    /**
     * The name of the Folder type.
     */
    public static final String FOLDER_TYPE = "Folder"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLObjectType folder = GraphQLObjectType.newObject()
                .name(FOLDER_TYPE)
                .field(SiriusGraphQLResourceNameField.build())
                .field(SiriusGraphQLResourcePathField.build())
                .field(SiriusGraphQLResourceContainerField.build())
                .field(SiriusGraphQLResourceProjectField.build())
                .field(SiriusGraphQLContainerResourcesField.build())
                .withInterface(new GraphQLTypeReference(SiriusGraphQLResourceTypesBuilder.RESOURCE_TYPE))
                .withInterface(new GraphQLTypeReference(SiriusGraphQLContainerTypesBuilder.CONTAINER_TYPE))
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(folder);
        return types;
    }
}
