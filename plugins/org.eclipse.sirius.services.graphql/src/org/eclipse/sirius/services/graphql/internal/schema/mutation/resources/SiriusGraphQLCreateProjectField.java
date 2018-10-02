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
package org.eclipse.sirius.services.graphql.internal.schema.mutation.resources;

import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLProjectTypesBuilder;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the createProject field.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLCreateProjectField {

    /**
     * The name of the createProject field.
     */
    private static final String CREATE_PROJECT_FIELD = "createProject"; //$NON-NLS-1$

    /**
     * The name of the description argument.
     */
    private static final String DESCRIPTION_ARG = "description"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLCreateProjectField() {
        // Prevent instantiation
    }

    /**
     * Returns the createProject field.
     *
     * @return The createProject field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(CREATE_PROJECT_FIELD)
                .type(new GraphQLTypeReference(SiriusGraphQLProjectTypesBuilder.PROJECT_TYPE))
                .argument(SiriusGraphQLCreateProjectField.getProjectDescriptionArgument())
                .dataFetcher(SiriusGraphQLCreateProjectField.getCreateProjectDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the description argument.
     * 
     * @return The description argument
     */
    private static GraphQLArgument getProjectDescriptionArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(DESCRIPTION_ARG)
                .type(new GraphQLNonNull(new GraphQLTypeReference(SiriusGraphQLProjectCreationDescriptionTypesBuilder.PROJECT_CREATION_DESCRIPTION_TYPE)))
                .build();
        // @formatter:on
    }

    /**
     * Returns the createProject data fetcher.
     *
     * @return The createProject data fetcher
     */
    private static DataFetcher<IProject> getCreateProjectDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.<Map<String, String>>getArgument(DESCRIPTION_ARG))
                .map(description -> {
                    String name = description.get(SiriusGraphQLProjectCreationDescriptionTypesBuilder.NAME_FIELD);
                    IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
                    if (!iProject.exists()) {
                        try {
                            IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(name);
                            projectDescription.setNatureIds(new String[] { ModelingProject.NATURE_ID });
                            iProject.create(projectDescription, new NullProgressMonitor());
                            iProject.open(new NullProgressMonitor());

                            URI representationsURI = URI.createPlatformResourceURI(iProject.getFullPath().append(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME).toString(), true);
                            SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(representationsURI, new NullProgressMonitor());
                            sessionCreationOperation.execute();
                            
                            iProject.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
                        } catch (CoreException exception) {
                            IStatus status = new Status(IStatus.ERROR, SiriusGraphQLPlugin.PLUGIN_ID, exception.getMessage(), exception);
                            SiriusGraphQLPlugin.getPlugin().log(status);
                        }
                        return iProject;
                    }
                    return null;
                })
                .orElse(null);
        // @formatter:on
    }
}
