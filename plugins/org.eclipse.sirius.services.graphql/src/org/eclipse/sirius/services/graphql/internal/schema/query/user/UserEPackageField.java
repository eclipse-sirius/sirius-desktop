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

import org.eclipse.emf.ecore.EPackage;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * The ePackage field of the user.
 *
 * @author sbegaudeau
 */
public final class UserEPackageField {

    /**
     * The name of the type of the field.
     */
    private static final String EPACKAGE_TYPE = "EPackage"; //$NON-NLS-1$

    /**
     * The name of the ePackage field.
     */
    private static final String EPACKAGE_FIELD = "ePackage"; //$NON-NLS-1$

    /**
     * The name of the argument nsURI of the ePackage field.
     */
    private static final String EPACKAGE_NSURI_ARG = "nsURI"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private UserEPackageField() {
        // Prevent instantiation
    }

    /**
     * Returns the ePackage field.
     *
     * @return The ePackage field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(EPACKAGE_FIELD)
                .type(new GraphQLTypeReference(EPACKAGE_TYPE))
                .argument(UserEPackageField.getEPackageFieldNsURIArg())
                .dataFetcher(UserEPackageField.getEPackageDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the nsURI argument of the ePackage field.
     *
     * @return The nsURI argument of the ePackage field.
     */
    private static GraphQLArgument getEPackageFieldNsURIArg() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(EPACKAGE_NSURI_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the ePackage data fetcher.
     *
     * @return The ePackage data fetcher.
     */
    private static DataFetcher<EPackage> getEPackageDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.getArgument(EPACKAGE_NSURI_ARG))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(EPackage.Registry.INSTANCE::getEPackage)
                .orElse(null);
        // @formatter:on
    }

}
