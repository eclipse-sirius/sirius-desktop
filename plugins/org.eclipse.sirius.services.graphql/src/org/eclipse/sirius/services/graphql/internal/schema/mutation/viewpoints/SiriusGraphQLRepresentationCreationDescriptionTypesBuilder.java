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
package org.eclipse.sirius.services.graphql.internal.schema.mutation.viewpoints;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.Scalars;
import graphql.schema.GraphQLInputObjectField;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLType;

/**
 * Used to create the RepresentationCreationDescription input type.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLRepresentationCreationDescriptionTypesBuilder implements ISiriusGraphQLTypesBuilder {

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
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLType representationCreationDescription = GraphQLInputObjectType.newInputObject()
                .name(REPRESENTATION_CREATION_DESCRIPTION_TYPE)
                .field(this.getViewpointIdentifierField())
                .field(this.getRepresentationIdentifierField())
                .field(this.getEObjectFragmentField())
                .field(this.getNameField())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(representationCreationDescription);
        return types;
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
