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
 * Used to create the ProjectCreationDescription input type.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLProjectCreationDescriptionTypesBuilder implements ISiriusGraphQLTypesBuilder {

    /**
     * The name of the ProjectCreationDescription type.
     */
    public static final String PROJECT_CREATION_DESCRIPTION_TYPE = "ProjectCreationDescription"; //$NON-NLS-1$

    /**
     * The name of the kind field.
     */
    public static final String KIND_FIELD = "kind"; //$NON-NLS-1$

    /**
     * The name of the name field.
     */
    public static final String NAME_FIELD = "name"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLType projectCreationDescription = GraphQLInputObjectType.newInputObject()
                .name(PROJECT_CREATION_DESCRIPTION_TYPE)
                .field(this.getKindField())
                .field(this.getNameField())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(projectCreationDescription);
        return types;
    }

    /**
     * Returns the field kind.
     * 
     * @return The field kind
     */
    private GraphQLInputObjectField getKindField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(KIND_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
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

}
