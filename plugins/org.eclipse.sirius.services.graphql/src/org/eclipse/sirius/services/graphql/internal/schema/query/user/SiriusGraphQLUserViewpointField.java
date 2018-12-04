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
package org.eclipse.sirius.services.graphql.internal.schema.query.user;

import org.eclipse.sirius.services.common.api.ViewpointServices;
import org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLViewpointTypesBuilder;
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
public final class SiriusGraphQLUserViewpointField {

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
    private SiriusGraphQLUserViewpointField() {
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
                .type(new GraphQLTypeReference(SiriusGraphQLViewpointTypesBuilder.VIEWPOINT_TYPE))
                .argument(SiriusGraphQLUserViewpointField.getViewpointFieldIdentifierArg())
                .dataFetcher(SiriusGraphQLUserViewpointField.getViewpointDataFetcher())
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
        return environment -> ViewpointServices.viewpointFromIdentifier(environment.getArgument(VIEWPOINT_IDENTIFIER_ARG))
                .orElse(null);
        // @formatter:on
    }
}
