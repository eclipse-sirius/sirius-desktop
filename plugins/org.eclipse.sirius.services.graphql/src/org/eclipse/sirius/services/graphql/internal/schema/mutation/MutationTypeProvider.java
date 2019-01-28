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
package org.eclipse.sirius.services.graphql.internal.schema.mutation;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;

/**
 * Used to create the mutation type.
 *
 * @author sbegaudeau
 */
public class MutationTypeProvider implements ISiriusGraphQLTypeProvider {

    /**
     * The name of the mutation type.
     */
    public static final String MUTATION_TYPE = "Mutation"; //$NON-NLS-1$

    @Override
    public GraphQLObjectType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(MUTATION_TYPE)
                .field(CreateProjectField.build())
                .field(CreateFolderField.build())
                .field(CreateFileField.build())
                .field(ActivateViewpointField.build())
                .field(DeactivateViewpointField.build())
                .field(CreateRepresentationField.build());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(MUTATION_TYPE, builder);
        return customizedBuilder.build();
    }

}
