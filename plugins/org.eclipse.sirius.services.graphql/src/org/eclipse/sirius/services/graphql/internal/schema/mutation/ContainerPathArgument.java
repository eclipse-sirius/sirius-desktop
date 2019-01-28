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

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLNonNull;

/**
 * Used to create the containerPath argument.
 *
 * @author sbegaudeau
 */
public final class ContainerPathArgument {
    /**
     * The name of the containerPath argument.
     */
    public static final String CONTAINER_PATH_ARG = "containerPath"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private ContainerPathArgument() {
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
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }
}
