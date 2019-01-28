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
package org.eclipse.sirius.services.graphql.internal.schema.query;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.eclipse.sirius.services.graphql.common.api.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationDataFetcher;
import org.eclipse.sirius.services.graphql.internal.schema.SchemaConstants;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the activatedViewpoints field of the project.
 * 
 * @author sbegaudeau
 */
public final class ActivatedViewpointsField {

    /**
     * The name of the activatedViewpoints field.
     */
    private static final String ACTIVATED_VIEWPOINTS = "activatedViewpoints"; //$NON-NLS-1$

    /**
     * The complexity of the retrieval of an activated viewpoint.
     */
    private static final int ACTIVATED_VIEWPOINTS_COMPLEXITY = 1;

    /**
     * The constructor.
     */
    private ActivatedViewpointsField() {
        // Prevent instantiation
    }

    /**
     * Returns the activated viewpoints field.
     * 
     * @return The activated viewpoints field
     */
    public static GraphQLFieldDefinition build() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(ACTIVATED_VIEWPOINTS)
                .type(new GraphQLTypeReference(SchemaConstants.PROJECT_VIEWPOINT_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .withDirective(new SiriusGraphQLCostDirective(ACTIVATED_VIEWPOINTS_COMPLEXITY, multipliers).build())
                .dataFetcher(ActivatedViewpointsField.getActivatedViewpointsDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the activated viewpoints data fetcher.
     *
     * @return The activated viewpoints data fetcher.
     */
    private static DataFetcher<SiriusGraphQLConnection> getActivatedViewpointsDataFetcher() {
        // @formatter:off
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            Optional<IProject> optionalProject = Optional.of(environment.getSource())
                    .filter(IProject.class::isInstance)
                    .map(IProject.class::cast);
            
            return optionalProject.flatMap(SiriusServicesCommonOptionalUtils::toSession)
                    .map(session -> {
                        List<Viewpoint> viewpoints = new ArrayList<>(session.getSelectedViewpoints(true));
                        return viewpoints;
                    })
                    .orElseGet(ArrayList::new);
        }, ActivatedViewpointsField::computeViewpointCursor);
        // @formatter:on
    }

    /**
     * Returns the cursor of the given EPackage.
     * 
     * @param ePackage
     *            The EPackage
     * @return The cursor of the given EPackage
     */
    private static String computeViewpointCursor(Viewpoint viewpoint) {
        String unEncodedCursor = EcoreUtil.getURI(viewpoint).toString();
        return Base64.getEncoder().encodeToString(unEncodedCursor.getBytes());
    }
}
