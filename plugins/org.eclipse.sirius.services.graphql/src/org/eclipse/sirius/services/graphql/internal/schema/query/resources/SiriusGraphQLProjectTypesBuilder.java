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
package org.eclipse.sirius.services.graphql.internal.schema.query.resources;

import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder.EDGE_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLViewpointTypesBuilder.VIEWPOINT_TYPE;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.internal.entities.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationDataFetcher;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the Project type of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLProjectTypesBuilder implements ISiriusGraphQLTypesBuilder {
    /**
     * The name of the Project type.
     */
    public static final String PROJECT_TYPE = "Project"; //$NON-NLS-1$

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

    /**
     * The name of the activatedViewpoints field.
     */
    private static final String ACTIVATED_VIEWPOINTS = "activatedViewpoints"; //$NON-NLS-1$

    /**
     * The name of the Project to Viewpoint connection type.
     */
    public static final String PROJECT_VIEWPOINT_CONNECTION_TYPE = PROJECT_TYPE + VIEWPOINT_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the Project to Viewpoint edge type.
     */
    public static final String PROJECT_VIEWPOINT_EDGE_TYPE = PROJECT_TYPE + VIEWPOINT_TYPE + EDGE_SUFFIX;

    /**
     * The complexity of the retrieval of an activated viewpoint.
     */
    private static final int ACTIVATED_VIEWPOINTS_COMPLEXITY = 1;

    @Override
    public Set<GraphQLType> getTypes() {
        GraphQLObjectType activatedViewpointsEdge = new SiriusGraphQLEdgeTypeBuilder(PROJECT_VIEWPOINT_EDGE_TYPE, VIEWPOINT_TYPE).build();
        GraphQLObjectType activatedViewpointsConnection = new SiriusGraphQLConnectionTypeBuilder(PROJECT_VIEWPOINT_CONNECTION_TYPE, PROJECT_VIEWPOINT_EDGE_TYPE).build();

        // @formatter:off
        GraphQLObjectType project = GraphQLObjectType.newObject()
                .name(PROJECT_TYPE)
                .field(SiriusGraphQLResourceNameField.build())
                .field(SiriusGraphQLResourcePathField.build())
                .field(SiriusGraphQLResourceContainerField.build())
                .field(SiriusGraphQLResourceProjectField.build())
                .field(SiriusGraphQLContainerResourcesField.build())
                .field(this.getDescriptionField())
                .field(this.getResourceByPathField())
                .field(this.getActivatedViewpointsField())
                .withInterface(new GraphQLTypeReference(SiriusGraphQLResourceTypesBuilder.RESOURCE_TYPE))
                .withInterface(new GraphQLTypeReference(SiriusGraphQLContainerTypesBuilder.CONTAINER_TYPE))
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(project);
        types.add(activatedViewpointsEdge);
        types.add(activatedViewpointsConnection);
        return types;
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
                    IStatus status = new Status(IStatus.ERROR, SiriusGraphQLPlugin.PLUGIN_ID, exception.getMessage(), exception);
                    SiriusGraphQLPlugin.getPlugin().log(status);
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
                .type(new GraphQLTypeReference(SiriusGraphQLResourceTypesBuilder.RESOURCE_TYPE))
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

    /**
     * Returns the activated viewpoints field.
     * 
     * @return The activated viewpoints field
     */
    private GraphQLFieldDefinition getActivatedViewpointsField() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(ACTIVATED_VIEWPOINTS)
                .type(new GraphQLTypeReference(PROJECT_VIEWPOINT_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .withDirective(new SiriusGraphQLCostDirective(ACTIVATED_VIEWPOINTS_COMPLEXITY, multipliers).build())
                .dataFetcher(this.getActivatedViewpointsDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the activated viewpoints data fetcher.
     *
     * @return The activated viewpoints data fetcher.
     */
    private DataFetcher<SiriusGraphQLConnection> getActivatedViewpointsDataFetcher() {
        // @formatter:off
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            Optional<IProject> optionalProject = Optional.of(environment.getSource())
                    .filter(IProject.class::isInstance)
                    .map(IProject.class::cast);
            
            return optionalProject.flatMap(SiriusServicesCommonOptionalUtils::toSession)
                    .map(session -> new ArrayList<>(session.getSelectedViewpoints(true)))
                    .orElseGet(ArrayList::new);
        });
        // @formatter:on
    }
}
