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
package org.eclipse.sirius.services.graphql.internal.schema;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;

/**
 * Used to create arguments.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLArguments {
    /**
     * The constructor.
     */
    private SiriusGraphQLArguments() {
        // Prevent instantiation
    }

    /**
     * Creates a new string-based argument.
     * 
     * @param name
     *            The name of the argument
     * @return A new string-based argument
     */
    public static GraphQLArgument newString(String name) {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(name)
                .type(Scalars.GraphQLString)
                .build();
        // @formatter:on
    }
}
