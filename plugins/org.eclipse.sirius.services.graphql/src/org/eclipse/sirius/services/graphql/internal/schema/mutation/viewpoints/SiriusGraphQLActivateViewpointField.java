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
package org.eclipse.sirius.services.graphql.internal.schema.mutation.viewpoints;

import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.services.common.api.ProjectServices;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.eclipse.sirius.services.common.api.ViewpointServices;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLProjectTypesBuilder;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the activateViewpoint field.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLActivateViewpointField {

    /**
     * The name of the activateViewpoint field.
     */
    private static final String ACTIVATE_VIEWPOINT_FIELD = "activateViewpoint"; //$NON-NLS-1$

    /**
     * The name of the projectName argument.
     */
    private static final String PROJECT_NAME_ARG = "projectName"; //$NON-NLS-1$

    /**
     * The name of the viewpoint identifier argument.
     */
    private static final String VIEWPOINT_IDENTIFIER_ARG = "viewpointIdentifier"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLActivateViewpointField() {
        // Prevent instantiation
    }

    /**
     * Returns the activateViewpoint field.
     * 
     * @return The activateViewpoint field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(ACTIVATE_VIEWPOINT_FIELD)
                .argument(SiriusGraphQLActivateViewpointField.getProjectNameArgument())
                .argument(SiriusGraphQLActivateViewpointField.getViewpointIdentifierArgument())
                .type(new GraphQLTypeReference(SiriusGraphQLProjectTypesBuilder.PROJECT_TYPE))
                .dataFetcher(SiriusGraphQLActivateViewpointField.getActivateViewpointDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the projectName argument.
     * 
     * @return The projectName argument
     */
    private static GraphQLArgument getProjectNameArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(PROJECT_NAME_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the viewpointIdentifier argument.
     * 
     * @return The viewpointIdentifier argument
     */
    private static GraphQLArgument getViewpointIdentifierArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(VIEWPOINT_IDENTIFIER_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the activateViewpoint data fetcher.
     * 
     * @return The activateViewpoint data fetcher
     */
    private static DataFetcher<IProject> getActivateViewpointDataFetcher() {
        // @formatter:off
        return environment -> {
            Optional<IProject> optionalProject = ProjectServices.projectFromName(environment.getArgument(PROJECT_NAME_ARG));
            Optional<Session> optionalSession = optionalProject.flatMap(SiriusServicesCommonOptionalUtils::toSession);
            
            Optional<Viewpoint> optionalViewpoint = ViewpointServices.viewpointFromIdentifier(environment.getArgument(VIEWPOINT_IDENTIFIER_ARG));
            
            optionalSession.ifPresent(session -> {
                optionalViewpoint.ifPresent(viewpoint -> {
                    ViewpointServices.activateViewpoint(session, viewpoint);
                });
            });
            
            return optionalProject.orElse(null);
        };
        // @formatter:on
    }
}
