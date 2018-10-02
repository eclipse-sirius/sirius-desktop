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
package org.eclipse.sirius.services.graphql.internal.schema.mutation;

import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLCreateFolderField;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLCreateProjectField;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLCreateSemanticFileField;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLCreateTextFileField;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.viewpoints.SiriusGraphQLActivateViewpointField;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.viewpoints.SiriusGraphQLCreateRepresentationField;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.viewpoints.SiriusGraphQLDeactivateViewpointField;

import graphql.schema.GraphQLObjectType;

/**
 * Used to create the mutation type.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLMutationTypeBuilder {

    /**
     * The name of the mutation type.
     */
    public static final String MUTATION_TYPE = "Mutation"; //$NON-NLS-1$

    /**
     * Returns the mutation type.
     *
     * @return The mutation type
     */
    public GraphQLObjectType build() {
        // @formatter:off
        return GraphQLObjectType.newObject()
                .name(MUTATION_TYPE)
                .field(SiriusGraphQLCreateProjectField.build())
                .field(SiriusGraphQLCreateFolderField.build())
                .field(SiriusGraphQLCreateTextFileField.build())
                .field(SiriusGraphQLCreateSemanticFileField.build())
                .field(SiriusGraphQLActivateViewpointField.build())
                .field(SiriusGraphQLDeactivateViewpointField.build())
                .field(SiriusGraphQLCreateRepresentationField.build())
                .build();
        // @formatter:on
    }

}
