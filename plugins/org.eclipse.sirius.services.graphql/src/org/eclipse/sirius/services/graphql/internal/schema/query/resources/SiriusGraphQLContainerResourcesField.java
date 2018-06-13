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
package org.eclipse.sirius.services.graphql.internal.schema.query.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.internal.entities.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationDataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the resources field of the container.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLContainerResourcesField {

    /**
     * The name of the resources field.
     */
    private static final String RESOURCES_FIELD = "resources"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLContainerResourcesField() {
        // Prevent instantiation
    }

    /**
     * Returns the resources field.
     *
     * @return The resources field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(RESOURCES_FIELD)
                .argument(SiriusGraphQLPaginationArguments.build())
                .type(new GraphQLTypeReference(SiriusGraphQLContainerTypesBuilder.CONTAINER_RESOURCE_CONNECTION_TYPE))
                .dataFetcher(SiriusGraphQLContainerResourcesField.getResourcesDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the resources data fetcher.
     *
     * @return The resources data fetcher
     */
    private static DataFetcher<SiriusGraphQLConnection> getResourcesDataFetcher() {
        // @formatter:off
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            return Optional.of(environment.getSource())
                    .filter(IContainer.class::isInstance)
                    .map(IContainer.class::cast)
                    .map(SiriusGraphQLContainerResourcesField::getResources)
                    .map(Arrays::asList)
                    .orElseGet(ArrayList::new);
        });
        // @formatter:on
    }

    /**
     * Returns the resources of the given container.
     *
     * @param iContainer
     *            The container
     * @return The resources of the given container
     */
    private static IResource[] getResources(IContainer iContainer) {
        try {
            return iContainer.members();
        } catch (CoreException exception) {
            IStatus status = new Status(IStatus.ERROR, SiriusGraphQLPlugin.PLUGIN_ID, exception.getMessage(), exception);
            SiriusGraphQLPlugin.getPlugin().log(status);
        }
        return new IResource[0];
    }
}
