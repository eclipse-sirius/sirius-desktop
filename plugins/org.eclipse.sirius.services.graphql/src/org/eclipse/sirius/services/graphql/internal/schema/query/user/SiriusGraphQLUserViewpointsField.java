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
package org.eclipse.sirius.services.graphql.internal.schema.query.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.services.graphql.internal.entities.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationDataFetcher;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * The viewpoints field of the user.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLUserViewpointsField {

    /**
     * The name of the field viewpoints.
     */
    private static final String VIEWPOINTS_FIELD = "viewpoints"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLUserViewpointsField() {
        // Prevent instantiation
    }

    /**
     * Returns the viewpoints field.
     *
     * @return The viewpoints field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(VIEWPOINTS_FIELD)
                .type(new GraphQLTypeReference(SiriusGraphQLUserTypeBuilder.USER_EPACKAGE_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .dataFetcher(SiriusGraphQLUserViewpointsField.getViewpointsDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the viewpoints data fetcher.
     *
     * @return The viewpoints data fetcher
     */
    private static DataFetcher<SiriusGraphQLConnection> getViewpointsDataFetcher() {
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            Set<Viewpoint> viewpointSet = ViewpointRegistry.getInstance().getViewpoints();
            List<Viewpoint> viewpoints = new ArrayList<>(viewpointSet);
            viewpoints.sort((v1, v2) -> v1.getName().compareTo(v2.getName()));
            return viewpoints;
        });
    }
}
