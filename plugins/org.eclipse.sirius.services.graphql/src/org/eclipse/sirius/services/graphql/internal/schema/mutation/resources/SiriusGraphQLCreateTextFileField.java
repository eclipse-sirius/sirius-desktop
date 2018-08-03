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
package org.eclipse.sirius.services.graphql.internal.schema.mutation.resources;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLFileTypesBuilder;

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
public final class SiriusGraphQLCreateTextFileField {
    /**
     * The name of the createTextFile field.
     */
    private static final String CREATE_TEXT_FILE_FIELD = "createTextFile"; //$NON-NLS-1$

    /**
     * The name of the description argument.
     */
    private static final String DESCRIPTION_ARG = "description"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLCreateTextFileField() {
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
                .name(CREATE_TEXT_FILE_FIELD)
                .argument(SiriusGraphQLProjectNameArgument.build())
                .argument(SiriusGraphQLContainerPathArgument.build())
                .argument(SiriusGraphQLCreateTextFileField.getDescriptionArgument())
                .type(new GraphQLTypeReference(SiriusGraphQLFileTypesBuilder.FILE_TYPE))
                .dataFetcher(SiriusGraphQLCreateTextFileField.getCreateTextFileDataFetcher())
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
                .type(new GraphQLNonNull(new GraphQLTypeReference(SiriusGraphQLTextFileCreationDescriptionTypesBuilder.TEXT_FILE_CREATION_DESCRIPTION_TYPE)))
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
            Optional<IFile> optionalFile = SiriusGraphQLFileCreationHelper.getFile(environment);
            if (optionalFile.isPresent()) {
                IFile iFile = optionalFile.get();
                if (!iFile.exists()) {
                    Map<String, String> description = environment.<Map<String, String>> getArgument(DESCRIPTION_ARG);
                    String content = description.get(SiriusGraphQLTextFileCreationDescriptionTypesBuilder.CONTENT_FIELD);
                    try {
                        iFile.create(new ByteArrayInputStream(content.getBytes()), false, new NullProgressMonitor());
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
