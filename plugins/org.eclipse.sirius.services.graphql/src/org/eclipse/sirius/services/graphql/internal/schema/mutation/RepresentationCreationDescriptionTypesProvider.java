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
package org.eclipse.sirius.services.graphql.internal.schema.mutation;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;

import graphql.Scalars;
import graphql.schema.GraphQLInputObjectField;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLInputObjectType.Builder;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLType;

/**
 * Used to create the RepresentationCreationDescription input type.
 * 
 * @author sbegaudeau
 */
public class RepresentationCreationDescriptionTypesProvider implements ISiriusGraphQLTypeProvider {

    /**
     * The name of the RepresentationCreationDescription type.
     */
    public static final String REPRESENTATION_CREATION_DESCRIPTION_TYPE = "RepresentationCreationDescription"; //$NON-NLS-1$

    /**
     * The name of the viewpointIdentifier field.
     */
    public static final String VIEWPOINT_IDENTIFIER_FIELD = "viewpointIdentifier"; //$NON-NLS-1$

    /**
     * The name of the representationIdentifier field.
     */
    public static final String REPRESENTATION_IDENTIFIER_FIELD = "representationIdentifier"; //$NON-NLS-1$

    /**
     * The name of the eObjectFragment field.
     */
    public static final String EOBJECT_FRAGMENT_FIELD = "eObjectFragment"; //$NON-NLS-1$

    /**
     * The name of the name field.
     */
    public static final String NAME_FIELD = "name"; //$NON-NLS-1$

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLInputObjectType.newInputObject()
                .name(REPRESENTATION_CREATION_DESCRIPTION_TYPE)
                .field(this.getViewpointIdentifierField())
                .field(this.getRepresentationIdentifierField())
                .field(this.getEObjectFragmentField())
                .field(this.getNameField());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(REPRESENTATION_CREATION_DESCRIPTION_TYPE, builder);
        return customizedBuilder.build();
    }

    /**
     * Returns the viewpointIdentifier field.
     * 
     * @return The viewpointIdentifier field
     */
    private GraphQLInputObjectField getViewpointIdentifierField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(VIEWPOINT_IDENTIFIER_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the representationIdentifier field.
     * 
     * @return The representationIdentifier field
     */
    private GraphQLInputObjectField getRepresentationIdentifierField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(REPRESENTATION_IDENTIFIER_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the eObjectFragment field.
     * 
     * @return The eObjectFragment field
     */
    private GraphQLInputObjectField getEObjectFragmentField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(EOBJECT_FRAGMENT_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the name field.
     * 
     * @return The name field
     */
    private GraphQLInputObjectField getNameField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(NAME_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

}
