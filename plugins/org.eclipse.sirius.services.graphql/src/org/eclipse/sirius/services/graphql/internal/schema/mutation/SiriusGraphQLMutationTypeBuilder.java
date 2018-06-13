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
package org.eclipse.sirius.services.graphql.internal.schema.mutation;

import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLCreateFileField;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLCreateFolderField;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLCreateProjectField;

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
                .field(SiriusGraphQLCreateFileField.build())
                .build();
        // @formatter:on
    }

}
