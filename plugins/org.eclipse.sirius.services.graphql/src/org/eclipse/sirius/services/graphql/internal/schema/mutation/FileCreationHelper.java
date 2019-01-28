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

import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;

import graphql.schema.DataFetchingEnvironment;

/**
 * Utility methods to help the creation of files.
 * 
 * @author sbegaudeau
 */
public final class FileCreationHelper {
    /**
     * The name of the description argument.
     */
    private static final String DESCRIPTION_ARG = "description"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private FileCreationHelper() {
        // Prevent instantiation
    }

    /**
     * Returns the file to create from the given environment.
     *
     * @param environment
     *            The environment
     * @return The file to create from the given environment
     */
    public static Optional<IFile> getFile(DataFetchingEnvironment environment) {
        Map<String, String> description = environment.<Map<String, String>> getArgument(DESCRIPTION_ARG);
        String name = description.get(FileCreationDescriptionTypeProvider.NAME_FIELD);

        Optional<IContainer> optionalContainer = FileCreationHelper.getContainer(environment);
        return optionalContainer.map(iContainer -> iContainer.getFile(new Path(name)));
    }

    /**
     * Returns the container in which the file should be created.
     *
     * @param environment
     *            The environment
     * @return The container in which the file should be created
     */
    private static Optional<IContainer> getContainer(DataFetchingEnvironment environment) {
        Optional<IProject> optionalProject = WorkspaceOptionalUtils.projectFromName(environment.getArgument(ProjectNameArgument.PROJECT_NAME_ARG));
        Optional<String> optionalContainerPath = Optional.of(environment.getArgument(ContainerPathArgument.CONTAINER_PATH_ARG));

       // @formatter:off
       return optionalContainerPath.flatMap(containerPath -> {
           return optionalProject.map(iProject -> iProject.findMember(containerPath))
                   .filter(IContainer.class::isInstance)
                   .map(IContainer.class::cast);
       });
       // @formatter:on
    }
}
