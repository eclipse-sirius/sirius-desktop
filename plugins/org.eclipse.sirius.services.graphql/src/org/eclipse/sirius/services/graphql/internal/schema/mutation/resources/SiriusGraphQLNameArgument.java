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
package org.eclipse.sirius.services.graphql.internal.schema.mutation.resources;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLNonNull;

/**
 * Used to create the name argument.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLNameArgument {
    /**
     * The name of the name argument.
     */
    public static final String NAME_ARG = "name"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLNameArgument() {
        // Prevent instantiation
    }

    /**
     * Returns the name field.
     *
     * @return The name field
     */
    public static GraphQLArgument build() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(NAME_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

}
