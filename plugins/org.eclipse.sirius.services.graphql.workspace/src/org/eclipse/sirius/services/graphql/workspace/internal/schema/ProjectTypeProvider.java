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
package org.eclipse.sirius.services.graphql.workspace.internal.schema;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;
import org.eclipse.sirius.services.graphql.workspace.internal.SiriusGraphQLWorkspacePlugin;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the Project type of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class ProjectTypeProvider implements ISiriusGraphQLTypeProvider {

    /**
     * The name of the description field.
     */
    private static final String DESCRIPTION_FIELD = "description"; //$NON-NLS-1$

    /**
     * The name of the resourceByPath field.
     */
    private static final String RESOURCE_BY_PATH_FIELD = "resourceByPath"; //$NON-NLS-1$

    /**
     * The name of the path argument.
     */
    private static final String PATH_ARG = "path"; //$NON-NLS-1$

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(WorkspaceSchemaConstants.PROJECT_TYPE)
                .field(ResourceNameField.build())
                .field(ResourcePathField.build())
                .field(ResourceContainerField.build())
                .field(ResourceProjectField.build())
                .field(ContainerResourcesField.build())
                .field(this.getDescriptionField())
                .field(this.getResourceByPathField())
                .withInterface(new GraphQLTypeReference(ResourceTypeProvider.RESOURCE_TYPE))
                .withInterface(new GraphQLTypeReference(ContainerTypesProvider.CONTAINER_TYPE));
        // @formatter:on

        Builder customizedBuilder = customizer.customize(WorkspaceSchemaConstants.PROJECT_TYPE, builder);
        return customizedBuilder.build();
    }

    /**
     * Returns the description field.
     *
     * @return The description field
     */
    private GraphQLFieldDefinition getDescriptionField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(DESCRIPTION_FIELD)
                .type(Scalars.GraphQLString)
                .dataFetcher(this.getDescriptionDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the data fetcher for the description.
     *
     * @return The data fetcher for the description
     */
    private DataFetcher<String> getDescriptionDataFetcher() {
        return environment -> {
            String description = null;

            Object source = environment.getSource();
            if (source instanceof IProject) {
                IProject iProject = (IProject) source;
                try {
                    IProjectDescription projectDescription = iProject.getDescription();
                    description = projectDescription.getComment();
                } catch (CoreException exception) {
                    IStatus status = new Status(IStatus.ERROR, SiriusGraphQLWorkspacePlugin.PLUGIN_ID, exception.getMessage(), exception);
                    SiriusGraphQLWorkspacePlugin.getPlugin().log(status);
                }
            }
            return description;
        };
    }

    /**
     * Returns the resource by path field.
     *
     * @return The resource by path field
     */
    private GraphQLFieldDefinition getResourceByPathField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(RESOURCE_BY_PATH_FIELD)
                .argument(this.getResourceByPathPathArg())
                .type(new GraphQLTypeReference(ResourceTypeProvider.RESOURCE_TYPE))
                .dataFetcher(this.getResourcebyPathDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the resource by path path argument.
     *
     * @return The resource by path path argument
     */
    private GraphQLArgument getResourceByPathPathArg() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(PATH_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the resource by path data fetcher.
     *
     * @return The resource by path data fetcher.
     */
    private DataFetcher<IResource> getResourcebyPathDataFetcher() {
        return environment -> {
            Object source = environment.getSource();
            Object pathArg = environment.getArgument(PATH_ARG);
            if (source instanceof IProject && pathArg instanceof String) {
                IProject iProject = (IProject) source;
                String path = (String) pathArg;

                return iProject.findMember(path);
            }
            return null;
        };
    }

}
