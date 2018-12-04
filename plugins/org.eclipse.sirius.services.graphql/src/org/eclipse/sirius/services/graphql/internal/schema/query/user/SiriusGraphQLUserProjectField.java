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
package org.eclipse.sirius.services.graphql.internal.schema.query.user;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.services.common.api.ProjectServices;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLProjectTypesBuilder;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * The project field of the user.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLUserProjectField {
    /**
     * The name of the project field.
     */
    private static final String PROJECT_FIELD = "project"; //$NON-NLS-1$

    /**
     * The name of the argument name of the field project.
     */
    private static final String PROJECT_NAME_ARG = "name"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLUserProjectField() {
        // Prevent instantiation
    }

    /**
     * Returns the project field.
     *
     * @return The project field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(PROJECT_FIELD)
                .type(new GraphQLTypeReference(SiriusGraphQLProjectTypesBuilder.PROJECT_TYPE))
                .argument(SiriusGraphQLUserProjectField.getProjectFieldNameArg())
                .dataFetcher(SiriusGraphQLUserProjectField.getProjectDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the name argument of the project field.
     *
     * @return The name argument of the project field
     */
    private static GraphQLArgument getProjectFieldNameArg() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(PROJECT_NAME_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the project data fetcher.
     *
     * @return The project data fetcher
     */
    private static DataFetcher<IProject> getProjectDataFetcher() {
        // @formatter:off
        return environment -> ProjectServices.projectFromName(environment.getArgument(PROJECT_NAME_ARG))
                .orElse(null);
        // @formatter:on
    }
}
