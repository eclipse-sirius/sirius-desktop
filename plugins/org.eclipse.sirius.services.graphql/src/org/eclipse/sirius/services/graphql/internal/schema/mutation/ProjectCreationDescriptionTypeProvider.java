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
 * Used to create the ProjectCreationDescription input type.
 * 
 * @author sbegaudeau
 */
public class ProjectCreationDescriptionTypeProvider implements ISiriusGraphQLTypeProvider {

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
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLInputObjectType.newInputObject()
                .name(PROJECT_CREATION_DESCRIPTION_TYPE)
                .field(this.getKindField())
                .field(this.getNameField());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(PROJECT_CREATION_DESCRIPTION_TYPE, builder);
        return customizedBuilder.build();
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
