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
package org.eclipse.sirius.services.graphql.common.api.pagination;

import java.util.ArrayList;
import java.util.List;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;

/**
 * Used to create the pagination arguments.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLPaginationArguments {
    /**
     * The name of the argument first used for the pagination.
     */
    public static final String FIRST_ARG = "first"; //$NON-NLS-1$

    /**
     * The name of the argument last used for the pagination.
     */
    public static final String LAST_ARG = "last"; //$NON-NLS-1$

    /**
     * The name of the argument after used for the pagination.
     */
    public static final String AFTER_ARG = "after"; //$NON-NLS-1$

    /**
     * The name of the argument before used for the pagination.
     */
    public static final String BEFORE_ARG = "before"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLPaginationArguments() {
        // Prevent instantiation
    }

    /**
     * Returns the list of pagination arguments.
     *
     * @return The list of pagination arguments.
     */
    public static List<GraphQLArgument> build() {
        List<GraphQLArgument> arguments = new ArrayList<>();
        arguments.add(SiriusGraphQLPaginationArguments.getFieldFirstArg());
        arguments.add(SiriusGraphQLPaginationArguments.getFieldAfterArg());
        arguments.add(SiriusGraphQLPaginationArguments.getFieldLastArg());
        arguments.add(SiriusGraphQLPaginationArguments.getFieldBeforeArg());
        return arguments;
    }

    /**
     * Returns the argument first of the field.
     *
     * @return The argument first of the field.
     */
    private static GraphQLArgument getFieldFirstArg() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(FIRST_ARG)
                .type(Scalars.GraphQLInt)
                .build();
        // @formatter:on
    }

    /**
     * Returns the argument after of the field.
     *
     * @return The argument after of the field.
     */
    private static GraphQLArgument getFieldAfterArg() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(AFTER_ARG)
                .type(Scalars.GraphQLString)
                .build();
        // @formatter:on
    }

    /**
     * Returns the argument last of the field.
     *
     * @return The argument last of the field.
     */
    private static GraphQLArgument getFieldLastArg() {
       // @formatter:off
       return GraphQLArgument.newArgument()
               .name(LAST_ARG)
               .type(Scalars.GraphQLInt)
               .build();
       // @formatter:on
    }

    /**
     * Returns the argument before of the field.
     *
     * @return The argument before of the field.
     */
    private static GraphQLArgument getFieldBeforeArg() {
       // @formatter:off
       return GraphQLArgument.newArgument()
               .name(BEFORE_ARG)
               .type(Scalars.GraphQLString)
               .build();
       // @formatter:on
    }
}
