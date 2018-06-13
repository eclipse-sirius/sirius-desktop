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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLType;
import graphql.schema.TypeResolver;

/**
 * Used to create the representation type.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLRepresentationTypesBuilder implements ISiriusGraphQLTypesBuilder {

    /**
     * The name of the Representation type.
     */
    public static final String REPRESENTATION_TYPE = "Representation"; //$NON-NLS-1$

    /**
     * The name of the name field.
     */
    private static final String NAME_FIELD = "name"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLInterfaceType representation = GraphQLInterfaceType.newInterface()
                .name(REPRESENTATION_TYPE)
                .field(this.getNameField())
                .typeResolver(this.getTypeResolver())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(representation);
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
                .type(Scalars.GraphQLString)
                .build();
        // @formatter:on
    }

    /**
     * Returns the type resolver.
     *
     * @return The type resolver
     */
    private TypeResolver getTypeResolver() {
        // @formatter:off
        return environment -> {
            Object object = environment.getObject();
            if (object instanceof DDiagram) {
                return environment.getSchema().getObjectType(SiriusGraphQLDiagramTypesBuilder.DIAGRAM_TYPE);
            }
            return null;
        };
        // @formatter:on
    }

}
