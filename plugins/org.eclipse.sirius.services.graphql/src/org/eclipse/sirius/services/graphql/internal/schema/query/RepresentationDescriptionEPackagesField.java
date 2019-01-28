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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.services.graphql.common.api.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationDataFetcher;
import org.eclipse.sirius.services.graphql.internal.schema.SchemaConstants;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the ePackages field of the representation description.
 *
 * @author sbegaudeau
 */
public final class RepresentationDescriptionEPackagesField {
    /**
     * The name of the ePackages field.
     */
    private static final String EPACKAGES_FIELD = "ePackages"; //$NON-NLS-1$

    /**
     * The complexity of the retrieval of an EPackage.
     */
    private static final int COMPLEXITY = 1;

    /**
     * The constructor.
     */
    private RepresentationDescriptionEPackagesField() {
        // Prevent instantiation
    }

    /**
     * Returns the resources field.
     *
     * @return The resources field
     */
    public static GraphQLFieldDefinition build() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(EPACKAGES_FIELD)
                .argument(SiriusGraphQLPaginationArguments.build())
                .type(new GraphQLTypeReference(SchemaConstants.REPRESENTATION_DESCRIPTION_EPACKAGE_CONNECTION_TYPE))
                .withDirective(new SiriusGraphQLCostDirective(COMPLEXITY, multipliers).build())
                .dataFetcher(RepresentationDescriptionEPackagesField.getEPackagesDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the resources data fetcher.
     *
     * @return The resources data fetcher
     */
    private static DataFetcher<SiriusGraphQLConnection> getEPackagesDataFetcher() {
        // @formatter:off
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            return Optional.of(environment.getSource())
                    .filter(RepresentationDescription.class::isInstance)
                    .map(RepresentationDescription.class::cast)
                    .map(representationDescription -> {
                        List<EPackage> p = representationDescription.getMetamodel();
                        return p;
                    })
                    .orElseGet(ArrayList::new);
        }, RepresentationDescriptionEPackagesField::computeEPackageCursor);
        // @formatter:on
    }

    /**
     * Returns the cursor of the given EPackage.
     * 
     * @param ePackage
     *            The EPackage
     * @return The cursor of the given EPackage
     */
    private static String computeEPackageCursor(EPackage ePackage) {
        String unEncodedCursor = ePackage.getNsURI();
        return Base64.getEncoder().encodeToString(unEncodedCursor.getBytes());
    }
}
