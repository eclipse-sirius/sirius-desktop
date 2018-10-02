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
package org.eclipse.sirius.services.graphql.internal.schema.query.emf;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;

/**
 * Used to create the fragment field.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLEObjectFragmentField {
    /**
     * The name of the fragment field.
     */
    private static final String FRAGMENT_FIELD = "fragment"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLEObjectFragmentField() {
        // Prevent instantiation
    }

    /**
     * Returns the fragment field.
     * 
     * @return The fragment field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(FRAGMENT_FIELD)
                .type(Scalars.GraphQLString)
                .dataFetcher(SiriusGraphQLEObjectFragmentField.getFragmentDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the fragment data fetcher.
     * 
     * @return The fragment data fetcher
     */
    private static DataFetcher<String> getFragmentDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.getSource())
                .filter(EObject.class::isInstance)
                .map(EObject.class::cast)
                .flatMap(eObject -> {
                    return Optional.ofNullable(eObject.eResource())
                            .map(resource -> resource.getURIFragment(eObject));
                })
                .orElse(null);
        // @formatter:on
    }
}
