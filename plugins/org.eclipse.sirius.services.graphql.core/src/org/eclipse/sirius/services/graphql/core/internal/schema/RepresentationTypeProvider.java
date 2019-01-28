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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;
import org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLInterfaceType.Builder;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLType;
import graphql.schema.TypeResolver;

/**
 * Used to create the representation type.
 *
 * @author sbegaudeau
 */
public class RepresentationTypeProvider implements ISiriusGraphQLTypeProvider {

    /**
     * The name of the name field.
     */
    private static final String NAME_FIELD = "name"; //$NON-NLS-1$

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLInterfaceType.newInterface()
                .name(CoreSchemaConstants.REPRESENTATION_TYPE)
                .field(this.getNameField())
                .typeResolver(this.getTypeResolver());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(CoreSchemaConstants.REPRESENTATION_TYPE, builder);
        return customizedBuilder.build();
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
     * Returns the type resolver.
     *
     * @return The type resolver
     */
    private TypeResolver getTypeResolver() {
        // @formatter:off
        return environment -> {
            Object object = environment.getObject();
            if (object instanceof DDiagram) {
                return environment.getSchema().getObjectType(CoreSchemaConstants.DIAGRAM_TYPE);
            }
            return null;
        };
        // @formatter:on
    }

}
