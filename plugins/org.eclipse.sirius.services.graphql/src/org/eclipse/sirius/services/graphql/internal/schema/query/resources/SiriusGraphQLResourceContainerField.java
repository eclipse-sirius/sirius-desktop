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

import java.util.Optional;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * The container field of the resources.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLResourceContainerField {
    /**
     * The name of the container field.
     */
    private static final String CONTAINER_FIELD = "container"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLResourceContainerField() {
        // Prevent instantiation
    }

    /**
     * Returns the container field.
     *
     * @return The container field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(CONTAINER_FIELD)
                .type(new GraphQLTypeReference(SiriusGraphQLContainerTypesBuilder.CONTAINER_TYPE))
                .dataFetcher(SiriusGraphQLResourceContainerField.getContainerDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the container data fetcher.
     *
     * @return The container data fetcher
     */
    private static DataFetcher<IContainer> getContainerDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.getSource())
                .filter(IResource.class::isInstance)
                .map(IResource.class::cast)
                .map(iResource -> {
                    if (iResource instanceof IProject) {
                        return null; // Do not expose the WorkspaceRoot to the API
                    }
                    return iResource.getParent();
                })
                .orElse(null);
        // @formatter:on
    }

}
