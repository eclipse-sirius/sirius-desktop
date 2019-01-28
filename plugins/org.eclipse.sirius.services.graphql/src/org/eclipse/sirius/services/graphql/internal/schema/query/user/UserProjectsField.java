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
package org.eclipse.sirius.services.graphql.internal.schema.query.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.sirius.services.graphql.common.api.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationDataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * The projects field of the user.
 *
 * @author sbegaudeau
 */
public final class UserProjectsField {
    /**
     * The name of the field projects.
     */
    private static final String PROJECTS_FIELD = "projects"; //$NON-NLS-1$

    /**
     * The complexity of the retrieval of a project.
     */
    private static final int COMPLEXITY = 1;

    /**
     * The constructor.
     */
    private UserProjectsField() {
        // Prevent instantiation
    }

    /**
     * Returns the projects field.
     *
     * @return The projects field
     */
    public static GraphQLFieldDefinition build() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(PROJECTS_FIELD)
                .type(new GraphQLTypeReference(UserTypesProvider.USER_PROJECT_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .withDirective(new SiriusGraphQLCostDirective(COMPLEXITY, multipliers).build())
                .dataFetcher(UserProjectsField.getProjectsDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the projects data fetcher.
     *
     * @return The projects data fetcher.
     */
    private static DataFetcher<SiriusGraphQLConnection> getProjectsDataFetcher() {
        // @formatter:off
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            return Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects());
        }, UserProjectsField::computeProjectCursor);
        // @formatter:on
    }

    /**
     * Returns the cursor of the given project.
     * 
     * @param project
     *            The project
     * @return The cursor of the given project
     */
    private static String computeProjectCursor(IProject project) {
        String unEncodedCursor = project.getName();
        return Base64.getEncoder().encodeToString(unEncodedCursor.getBytes());
    }
}
