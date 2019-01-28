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
package org.eclipse.sirius.services.graphql.core.internal.schema;

import java.util.Optional;

import org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the viewpoint field of the representation description.
 *
 * @author sbegaudeau
 */
public final class RepresentationDescriptionViewpointField {
    /**
     * The name of the viewpoint field.
     */
    private static final String VIEWPOINT_FIELD = "viewpoint"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private RepresentationDescriptionViewpointField() {
        // Prevent instantiation
    }

    /**
     * Returns the viewpoint field.
     *
     * @return The viewpoint field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(VIEWPOINT_FIELD)
                .type(new GraphQLNonNull(new GraphQLTypeReference(CoreSchemaConstants.VIEWPOINT_TYPE)))
                .dataFetcher(RepresentationDescriptionViewpointField.getViewpointDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the viewpoint data fetcher.
     *
     * @return The viewpoint data fetcher
     */
    private static DataFetcher<Viewpoint> getViewpointDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.getSource())
                .filter(RepresentationDescription.class::isInstance)
                .map(RepresentationDescription.class::cast)
                .map(RepresentationDescription::eContainer)
                .filter(Viewpoint.class::isInstance)
                .map(Viewpoint.class::cast)
                .orElse(null);
        // @formatter:on
    }
}
