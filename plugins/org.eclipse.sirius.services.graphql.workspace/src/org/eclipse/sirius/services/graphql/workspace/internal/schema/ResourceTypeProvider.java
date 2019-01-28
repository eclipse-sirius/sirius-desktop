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

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLInterfaceType.Builder;
import graphql.schema.GraphQLType;

/**
 * Used to create the Resource interface of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class ResourceTypeProvider implements ISiriusGraphQLTypeProvider {
    /**
     * The name of the Resource type.
     */
    public static final String RESOURCE_TYPE = "Resource"; //$NON-NLS-1$

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLInterfaceType.newInterface()
                .name(RESOURCE_TYPE)
                .field(ResourceNameField.build())
                .field(ResourcePathField.build())
                .field(ResourceContainerField.build())
                .field(ResourceProjectField.build())
                .typeResolver(ResourceTypeResolver.build());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(RESOURCE_TYPE, builder);
        return customizedBuilder.build();
    }

}
