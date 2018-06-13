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
package org.eclipse.sirius.services.graphql.internal.schema.query.user;

import java.util.Arrays;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.sirius.services.graphql.internal.entities.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationDataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * The projects field of the user.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLUserProjectsField {
    /**
     * The name of the field projects.
     */
    private static final String PROJECTS_FIELD = "projects"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLUserProjectsField() {
        // Prevent instantiation
    }

    /**
     * Returns the projects field.
     *
     * @return The projects field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(PROJECTS_FIELD)
                .type(new GraphQLTypeReference(SiriusGraphQLUserTypeBuilder.USER_PROJECT_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .dataFetcher(SiriusGraphQLUserProjectsField.getProjectsDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the projects data fetcher.
     *
     * @return The projects data fetcher.
     */
    private static DataFetcher<SiriusGraphQLConnection> getProjectsDataFetcher() {
        return SiriusGraphQLPaginationDataFetcher.build(environment -> Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects()));
    }
}
