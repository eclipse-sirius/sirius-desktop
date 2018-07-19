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
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.services.graphql.internal.entities.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.internal.schema.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationDataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * The ePackages field of the user.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLUserEPackagesField {
    /**
     * The name of the field ePackages.
     */
    private static final String EPACKAGES_FIELD = "ePackages"; //$NON-NLS-1$

    /**
     * The complexity of the retrieval of an EPackage.
     */
    private static final int COMPLEXITY = 1;

    /**
     * The constructor.
     */
    private SiriusGraphQLUserEPackagesField() {
        // Prevent instantiation
    }

    /**
     * Returns the ePackages field.
     *
     * @return The ePackages field
     */
    public static GraphQLFieldDefinition build() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(EPACKAGES_FIELD)
                .type(new GraphQLTypeReference(SiriusGraphQLUserTypesBuilder.USER_EPACKAGE_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .withDirective(new SiriusGraphQLCostDirective(COMPLEXITY, multipliers).build())
                .dataFetcher(SiriusGraphQLUserEPackagesField.getEPackagesDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the ePackages data fetcher.
     *
     * @return The ePackages data fetcher.
     */
    private static DataFetcher<SiriusGraphQLConnection> getEPackagesDataFetcher() {
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            // @formatter:off
            return EPackage.Registry.INSTANCE.values().stream()
                    .map(object -> {
                        EPackage ePackage = null;
                        if (object instanceof EPackage) {
                            ePackage = (EPackage) object;
                        } else if (object instanceof EPackage.Descriptor) {
                            ePackage = ((EPackage.Descriptor) object).getEPackage();
                        }
                        return ePackage;
                    })
                    .collect(Collectors.toList());
            // @formatter:on
        });
    }
}
