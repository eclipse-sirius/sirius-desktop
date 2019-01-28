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
package org.eclipse.sirius.services.graphql.internal.schema.query.user;

import java.util.Optional;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * The viewpoint field of the user.
 *
 * @author sbegaudeau
 */
public final class UserViewpointField {

    /**
     * The name of the viewpoint field.
     */
    private static final String VIEWPOINT_FIELD = "viewpoint"; //$NON-NLS-1$

    /**
     * The name of the argument identifier of the field viewpoint.
     */
    private static final String VIEWPOINT_IDENTIFIER_ARG = "identifier"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private UserViewpointField() {
        // Prevent instantiation
    }

    /**
     * Returns the project field.
     *
     * @return The project field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(VIEWPOINT_FIELD)
                .type(new GraphQLTypeReference(CoreSchemaConstants.VIEWPOINT_TYPE))
                .argument(UserViewpointField.getViewpointFieldIdentifierArg())
                .dataFetcher(UserViewpointField.getViewpointDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the identifier argument of the viewpoint field.
     *
     * @return The identifier argument of the viewpoint field
     */
    private static GraphQLArgument getViewpointFieldIdentifierArg() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(VIEWPOINT_IDENTIFIER_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the viewpoint data fetcher.
     *
     * @return The viewpoint data fetcher
     */
    private static DataFetcher<Viewpoint> getViewpointDataFetcher() {
        // @formatter:off
        return environment -> {
            Optional<String> optionalViewpointIdentifier = Optional.of(environment.getArgument(VIEWPOINT_IDENTIFIER_ARG))
                    .filter(String.class::isInstance)
                    .map(String.class::cast);
            
            Optional<Viewpoint> optionalViewpoint = optionalViewpointIdentifier.flatMap(viewpointIdentifier -> {
                return ViewpointRegistry.getInstance().getViewpoints().stream()
                        .filter(viewpoint -> viewpointIdentifier.equals(viewpoint.getName()))
                        .findFirst();
            });
            
            return optionalViewpoint.orElse(null);
        }; 
        // @formatter:on
    }
}
