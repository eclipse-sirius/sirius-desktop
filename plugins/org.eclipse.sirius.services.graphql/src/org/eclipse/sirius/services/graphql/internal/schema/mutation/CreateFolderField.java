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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the createFolder field.
 *
 * @author sbegaudeau
 */
public final class CreateFolderField {

    /**
     * The name of the createFolder mutation.
     */
    private static final String CREATE_FOLDER_FIELD = "createFolder"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private CreateFolderField() {
        // Prevent instantiation
    }

    /**
     * Returns the createFolder field.
     *
     * @return The createFolder field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(CREATE_FOLDER_FIELD)
                .argument(ProjectNameArgument.build())
                .argument(ContainerPathArgument.build())
                .argument(NameArgument.build())
                .type(new GraphQLTypeReference(WorkspaceSchemaConstants.FOLDER_TYPE))
                .dataFetcher(CreateFolderField.getCreateFolderDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the createFolder data fetcher.
     *
     * @return The createFolder data fetcher.
     */
    private static DataFetcher<IFolder> getCreateFolderDataFetcher() {
        // @formatter:off
        return environment -> {
            Optional<IProject> optionalProject = WorkspaceOptionalUtils.projectFromName(environment.getArgument(ProjectNameArgument.PROJECT_NAME_ARG));
            Optional<String> optionalContainerPath = Optional.of(environment.getArgument(ContainerPathArgument.CONTAINER_PATH_ARG));
            Optional<String> optionalName = Optional.of(environment.getArgument(NameArgument.NAME_ARG));

            if (optionalProject.isPresent() && optionalContainerPath.isPresent() && optionalName.isPresent()) {
                String containerPath = optionalContainerPath.get();
                Optional<IContainer> optionalContainer = optionalProject.map(iProject -> iProject.findMember(containerPath))
                        .filter(IContainer.class::isInstance)
                        .map(IContainer.class::cast);


                if (optionalContainer.isPresent()) {
                    IContainer iContainer = optionalContainer.get();
                    String name = optionalName.get();

                    IFolder iFolder = iContainer.getFolder(new Path(name));
                    if (!iFolder.exists()) {
                        try {
                            iFolder.create(false, true, new NullProgressMonitor());
                        } catch (CoreException exception) {
                            IStatus status = new Status(IStatus.ERROR, SiriusGraphQLPlugin.PLUGIN_ID, exception.getMessage(), exception);
                            SiriusGraphQLPlugin.getPlugin().log(status);
                        }
                        return iFolder;
                    }
                }
            }

            return null;
        };
        // @formatter:on
    }
}
