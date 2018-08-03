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
package org.eclipse.sirius.services.graphql.internal.schema.mutation.resources;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.Scalars;
import graphql.schema.GraphQLInputObjectField;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLType;

/**
 * Used to create the TextFileCreationDescription input type.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLTextFileCreationDescriptionTypesBuilder implements ISiriusGraphQLTypesBuilder {

    /**
     * The name of the TextFileCreationDescription type.
     */
    public static final String TEXT_FILE_CREATION_DESCRIPTION_TYPE = "TextFileCreationDescription"; //$NON-NLS-1$

    /**
     * The name of the name field.
     */
    public static final String NAME_FIELD = "name"; //$NON-NLS-1$

    /**
     * The name of the content field.
     */
    public static final String CONTENT_FIELD = "content"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLType textFileCreationDescription = GraphQLInputObjectType.newInputObject()
                .name(TEXT_FILE_CREATION_DESCRIPTION_TYPE)
                .field(this.getNameField())
                .field(this.getContentField())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(textFileCreationDescription);
        return types;
    }

    /**
     * Returns the field name.
     * 
     * @return The field name
     */
    private GraphQLInputObjectField getNameField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(NAME_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the field content.
     * 
     * @return The field content
     */
    private GraphQLInputObjectField getContentField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(CONTENT_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

}
