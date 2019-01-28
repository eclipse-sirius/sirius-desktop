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
package org.eclipse.sirius.services.graphql.common.api.directives;

import java.util.List;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLDirective;
import graphql.schema.GraphQLList;

/**
 * The cost directive.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLCostDirective {

    /**
     * The name of the directive.
     */
    public static final String COST = "cost"; //$NON-NLS-1$

    /**
     * The name of the complexity argument.
     */
    public static final String COMPLEXITY_ARG = "complexity"; //$NON-NLS-1$

    /**
     * The name of the multipliers argument.
     */
    public static final String MULTIPLIERS_ARG = "multipliers"; //$NON-NLS-1$

    /**
     * The complexity.
     */
    private int complexity;

    /**
     * The multipliers.
     */
    private List<String> multipliers;

    /**
     * The constructor.
     *
     * @param complexity
     *            The complexity
     * @param multipliers
     *            The multipliers
     */
    public SiriusGraphQLCostDirective(int complexity, List<String> multipliers) {
        this.complexity = complexity;
        this.multipliers = multipliers;
    }

    /**
     * Returns the cost directive.
     *
     * @return The cost directive
     */
    public GraphQLDirective build() {
        // @formatter:off
        return GraphQLDirective.newDirective()
                .name(COST)
                .argument(this.getComplexityArgument())
                .argument(this.getMultipliersArgument())
                .build();
        // @formatter:on
    }

    /**
     * Returns the complexity argument.
     *
     * @return The complexity argument
     */
    private GraphQLArgument getComplexityArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(COMPLEXITY_ARG)
                .type(Scalars.GraphQLInt)
                .value(Integer.valueOf(this.complexity))
                .defaultValue(Integer.valueOf(this.complexity))
                .build();
        // @formatter:on
    }

    /**
     * Returns the multipliers argument.
     *
     * @return The multipliers argument
     */
    private GraphQLArgument getMultipliersArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(MULTIPLIERS_ARG)
                .type(new GraphQLList(Scalars.GraphQLString))
                .value(this.multipliers)
                .defaultValue(this.multipliers)
                .build();
        // @formatter:on
    }
}
