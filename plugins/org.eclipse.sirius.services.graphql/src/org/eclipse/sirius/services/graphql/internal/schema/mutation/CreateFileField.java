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

import java.io.ByteArrayInputStream;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the createTextFile field.
 * 
 * @author sbegaudeau
 */
public final class CreateFileField {
    /**
     * The name of the createFile field.
     */
    private static final String CREATE_FILE_FIELD = "createFile"; //$NON-NLS-1$

    /**
     * The name of the description argument.
     */
    private static final String DESCRIPTION_ARG = "description"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private CreateFileField() {
        // Prevent instantiation
    }

    /**
     * Returns the createTextFile field.
     *
     * @return The createTextFile field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(CREATE_FILE_FIELD)
                .argument(ProjectNameArgument.build())
                .argument(ContainerPathArgument.build())
                .argument(CreateFileField.getDescriptionArgument())
                .type(new GraphQLTypeReference(WorkspaceSchemaConstants.FILE_TYPE))
                .dataFetcher(CreateFileField.getCreateTextFileDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the description argument.
     * 
     * @return The description argument
     */
    private static GraphQLArgument getDescriptionArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(DESCRIPTION_ARG)
                .type(new GraphQLNonNull(new GraphQLTypeReference(FileCreationDescriptionTypeProvider.FILE_CREATION_DESCRIPTION_TYPE)))
                .build();
        // @formatter:on
    }

    /**
     * Returns the createTextFile data fetcher.
     *
     * @return The createTextFile data fetcher
     */
    private static DataFetcher<IFile> getCreateTextFileDataFetcher() {
        return environment -> {
            Optional<IFile> optionalFile = FileCreationHelper.getFile(environment);
            if (optionalFile.isPresent()) {
                IFile iFile = optionalFile.get();
                if (!iFile.exists()) {
                    try {
                        iFile.create(new ByteArrayInputStream("".getBytes()), false, new NullProgressMonitor()); //$NON-NLS-1$
                    } catch (CoreException exception) {
                        IStatus status = new Status(IStatus.ERROR, SiriusGraphQLPlugin.PLUGIN_ID, exception.getMessage(), exception);
                        SiriusGraphQLPlugin.getPlugin().log(status);
                    }
                    return iFile;
                }
            }

            return null;
        };
    }

}
