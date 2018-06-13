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

/**
 * Used to create the containerPath argument.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLContainerPathArgument {
    /**
     * The name of the containerPath argument.
     */
    public static final String CONTAINER_PATH_ARG = "containerPath"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLContainerPathArgument() {
        // Prevent instantiation
    }

    /**
     * Returns the containerPath field.
     *
     * @return The containerPath field
     */
    public static GraphQLArgument build() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(CONTAINER_PATH_ARG)
                .type(Scalars.GraphQLString)
                .build();
        // @formatter:on
    }
}
