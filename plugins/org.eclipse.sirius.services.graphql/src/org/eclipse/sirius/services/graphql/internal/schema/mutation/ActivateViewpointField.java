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

import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.ViewpointSelector;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;
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
public final class ActivateViewpointField {

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
    private ActivateViewpointField() {
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
                .argument(ActivateViewpointField.getProjectNameArgument())
                .argument(ActivateViewpointField.getViewpointIdentifierArgument())
                .type(new GraphQLTypeReference(WorkspaceSchemaConstants.PROJECT_TYPE))
                .dataFetcher(ActivateViewpointField.getActivateViewpointDataFetcher())
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
            Optional<String> optionalProjectName = Optional.of(environment.getArgument(PROJECT_NAME_ARG))
                    .filter(String.class::isInstance)
                    .map(String.class::cast);
            
            Optional<IProject> optionalProject = optionalProjectName.map(ResourcesPlugin.getWorkspace().getRoot()::getProject);
            Optional<Session> optionalSession = optionalProject.flatMap(SiriusServicesCommonOptionalUtils::toSession);
            
            Optional<String> optionalViewpointIdentifier = Optional.of(environment.getArgument(VIEWPOINT_IDENTIFIER_ARG))
                    .filter(String.class::isInstance)
                    .map(String.class::cast);
            
            Optional<Viewpoint> optionalViewpoint = optionalViewpointIdentifier.flatMap(viewpointIdentifier -> {
                return ViewpointRegistry.getInstance().getViewpoints().stream()
                        .filter(viewpoint -> viewpointIdentifier.equals(viewpoint.getName()))
                        .findFirst();
            });
            
            optionalSession.ifPresent(session -> {
                optionalViewpoint.ifPresent(viewpoint -> {
                    RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
                        @Override
                        protected void doExecute() {
                            new ViewpointSelector(session).selectViewpoint(SiriusResourceHelper.getCorrespondingViewpoint(session, viewpoint), false, new NullProgressMonitor());
                        }
                    };
                    session.getTransactionalEditingDomain().getCommandStack().execute(command);
                });
            });
            
            return optionalProject.orElse(null);
        };
        // @formatter:on
    }
}
