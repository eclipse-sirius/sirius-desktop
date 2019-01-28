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

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLNonNull;

/**
 * Used to create the projectName argument.
 *
 * @author sbegaudeau
 */
public final class ProjectNameArgument {
    /**
     * The name of the projectName argument.
     */
    public static final String PROJECT_NAME_ARG = "projectName"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private ProjectNameArgument() {
        // Prevent instantiation
    }

    /**
     * Returns the projectName field.
     *
     * @return The projectName field
     */
    public static GraphQLArgument build() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(PROJECT_NAME_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }
}
