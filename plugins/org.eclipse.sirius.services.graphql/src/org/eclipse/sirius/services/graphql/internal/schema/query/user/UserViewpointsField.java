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

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.services.graphql.common.api.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationDataFetcher;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * The viewpoints field of the user.
 *
 * @author sbegaudeau
 */
public final class UserViewpointsField {

    /**
     * The name of the field viewpoints.
     */
    private static final String VIEWPOINTS_FIELD = "viewpoints"; //$NON-NLS-1$

    /**
     * The complexity of the retrieval of a viewpoint.
     */
    private static final int COMPLEXITY = 1;

    /**
     * The constructor.
     */
    private UserViewpointsField() {
        // Prevent instantiation
    }

    /**
     * Returns the viewpoints field.
     *
     * @return The viewpoints field
     */
    public static GraphQLFieldDefinition build() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(VIEWPOINTS_FIELD)
                .type(new GraphQLTypeReference(UserTypesProvider.USER_VIEWPOINT_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .withDirective(new SiriusGraphQLCostDirective(COMPLEXITY, multipliers).build())
                .dataFetcher(UserViewpointsField.getViewpointsDataFetcher())
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
        }, UserViewpointsField::computeViewpointCursor);
    }

    /**
     * Returns the cursor of the given project.
     * 
     * @param project
     *            The project
     * @return The cursor of the given project
     */
    private static String computeViewpointCursor(Viewpoint viewpoint) {
        String unEncodedCursor = EcoreUtil.getURI(viewpoint).toString();
        return Base64.getEncoder().encodeToString(unEncodedCursor.getBytes());
    }
}
