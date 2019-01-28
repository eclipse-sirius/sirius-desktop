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

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;
import org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the Diagram type.
 *
 * @author sbegaudeau
 */
public class DiagramTypeProvider implements ISiriusGraphQLTypeProvider {

    /**
     * The name of the name field.
     */
    private static final String NAME_FIELD = "name"; //$NON-NLS-1$

    /**
     * The name of the description field.
     */
    private static final String DESCRIPTION_FIELD = "description"; //$NON-NLS-1$

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(CoreSchemaConstants.DIAGRAM_TYPE)
                .field(this.getNameField())
                .field(this.getDescriptionField())
                .withInterface(new GraphQLTypeReference(CoreSchemaConstants.REPRESENTATION_TYPE));
        // @formatter:on

        Builder customizedBuilder = customizer.customize(CoreSchemaConstants.DIAGRAM_TYPE, builder);
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
     * Returns the description field.
     *
     * @return The description field
     */
    private GraphQLFieldDefinition getDescriptionField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(DESCRIPTION_FIELD)
                .type(new GraphQLNonNull(new GraphQLTypeReference(CoreSchemaConstants.DIAGRAM_DESCRIPTION_TYPE)))
                .build();
        // @formatter:on
    }
}
