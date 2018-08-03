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
 * Used to create the identifier field of the representation description.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLRepresentationDescriptionIdentifierField {
    /**
     * The name of the identifier field.
     */
    private static final String IDENTIFIER_FIELD = "identifier"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLRepresentationDescriptionIdentifierField() {
        // Prevent instantiation
    }

    /**
     * Returns the identifier field.
     *
     * @return The identifier field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(IDENTIFIER_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .dataFetcher(SiriusGraphQLRepresentationDescriptionIdentifierField.getIdentifierDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the identifier of the viewpoint.
     *
     * @return The identifier of the viewpoint
     */
    private static DataFetcher<String> getIdentifierDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.getSource())
                .filter(RepresentationDescription.class::isInstance)
                .map(RepresentationDescription.class::cast)
                .map(RepresentationDescription::getName)
                .orElse(null);
        // @formatter:on
    }
}
