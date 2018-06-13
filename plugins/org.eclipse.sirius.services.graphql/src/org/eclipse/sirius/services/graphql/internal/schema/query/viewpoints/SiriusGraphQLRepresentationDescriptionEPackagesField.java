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
package org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.services.graphql.internal.entities.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationDataFetcher;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the ePackages field of the representation description.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLRepresentationDescriptionEPackagesField {
    /**
     * The name of the ePackages field.
     */
    private static final String EPACKAGES_FIELD = "ePackages"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLRepresentationDescriptionEPackagesField() {
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
                .name(EPACKAGES_FIELD)
                .argument(SiriusGraphQLPaginationArguments.build())
                .type(new GraphQLTypeReference(SiriusGraphQLRepresentationDescriptionTypesBuilder.REPRESENTATION_DESCRIPTION_EPACKAGE_CONNECTION_TYPE))
                .dataFetcher(SiriusGraphQLRepresentationDescriptionEPackagesField.getEPackagesDataFetcher())
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
                        List<EPackage> ePackages = representationDescription.getMetamodel();
                        return ePackages;
                    })
                    .orElseGet(ArrayList::new);
        });
        // @formatter:on
    }
}
