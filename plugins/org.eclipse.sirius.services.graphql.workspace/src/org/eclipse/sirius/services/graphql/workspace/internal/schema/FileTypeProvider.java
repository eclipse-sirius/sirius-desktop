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
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the File object of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class FileTypeProvider implements ISiriusGraphQLTypeProvider {

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(WorkspaceSchemaConstants.FILE_TYPE)
                .field(ResourceNameField.build())
                .field(ResourcePathField.build())
                .field(ResourceContainerField.build())
                .field(ResourceProjectField.build())
                .withInterface(new GraphQLTypeReference(ResourceTypeProvider.RESOURCE_TYPE));
        // @formatter:on

        Builder customizedBuilder = customizer.customize(WorkspaceSchemaConstants.FILE_TYPE, builder);
        return customizedBuilder.build();
    }
}
