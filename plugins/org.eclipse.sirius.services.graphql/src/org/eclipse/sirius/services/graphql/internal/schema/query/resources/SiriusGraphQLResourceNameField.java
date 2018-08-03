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
package org.eclipse.sirius.services.graphql.internal.schema.query.resources;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;

/**
 * The name field of the resources.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLResourceNameField {

    /**
     * The name of the name field.
     */
    private static final String NAME_FIELD = "name"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLResourceNameField() {
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
                .build();
        // @formatter:on
    }
}
