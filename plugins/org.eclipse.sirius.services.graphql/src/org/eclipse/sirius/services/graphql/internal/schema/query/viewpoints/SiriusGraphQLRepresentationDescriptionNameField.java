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

import java.util.Optional;

import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;

/**
 * Used to create the name field of the representation description.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLRepresentationDescriptionNameField {
    /**
     * The name of the name field.
     */
    private static final String NAME_FIELD = "name"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLRepresentationDescriptionNameField() {
        // Prevent instantiation
    }

    /**
     * Returns the name field.
     *
     * @return The name field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(NAME_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .dataFetcher(SiriusGraphQLRepresentationDescriptionNameField.getNameDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the name data fetcher.
     *
     * @return The name data fetcher
     */
    private static DataFetcher<?> getNameDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.getSource())
                .filter(RepresentationDescription.class::isInstance)
                .map(RepresentationDescription.class::cast)
                .map(RepresentationDescription::getLabel)
                .orElse(null);
        // @formatter:on
    }
}
