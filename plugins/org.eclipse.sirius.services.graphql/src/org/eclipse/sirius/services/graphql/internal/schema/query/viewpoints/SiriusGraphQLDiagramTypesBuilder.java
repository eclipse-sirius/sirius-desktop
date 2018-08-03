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

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the Diagram type.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLDiagramTypesBuilder implements ISiriusGraphQLTypesBuilder {
    /**
     * The name of the diagram type.
     */
    public static final String DIAGRAM_TYPE = "Diagram"; //$NON-NLS-1$

    /**
     * The name of the name field.
     */
    private static final String NAME_FIELD = "name"; //$NON-NLS-1$

    /**
     * The name of the description field.
     */
    private static final String DESCRIPTION_FIELD = "description"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLObjectType diagram = GraphQLObjectType.newObject()
                .name(DIAGRAM_TYPE)
                .field(this.getNameField())
                .field(this.getDescriptionField())
                .withInterface(new GraphQLTypeReference(SiriusGraphQLRepresentationTypesBuilder.REPRESENTATION_TYPE))
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(diagram);
        return types;
    }

    /**
     * Returns the name field.
     *
     * @return The name field
     */
    private GraphQLFieldDefinition getNameField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(NAME_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the description field.
     *
     * @return The description field
     */
    private GraphQLFieldDefinition getDescriptionField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(DESCRIPTION_FIELD)
                .type(new GraphQLNonNull(new GraphQLTypeReference(SiriusGraphQLDiagramDescriptionTypesBuilder.DIAGRAM_DESCRIPTION_TYPE)))
                .build();
        // @formatter:on
    }
}
