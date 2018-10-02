/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
 * Used to create the SemanticFileCreationDescription input type.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLSemanticFileCreationDescriptionTypesBuilder implements ISiriusGraphQLTypesBuilder {

    /**
     * The name of the SemanticFileCreationDescription type.
     */
    public static final String SEMANTIC_FILE_CREATION_DESCRIPTION_TYPE = "SemanticFileCreationDescription"; //$NON-NLS-1$

    /**
     * The name of the name field.
     */
    public static final String NAME_FIELD = "name"; //$NON-NLS-1$

    /**
     * The name of the ePackageNsURI field.
     */
    public static final String EPACKAGE_NS_URI_FIELD = "ePackageNsURI"; //$NON-NLS-1$

    /**
     * The name of the eClassName field.
     */
    public static final String ECLASS_NAME_FIELD = "eClassName"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLType semanticFileCreationDescription = GraphQLInputObjectType.newInputObject()
                .name(SEMANTIC_FILE_CREATION_DESCRIPTION_TYPE)
                .field(this.getNameField())
                .field(this.getEPackageNsURIField())
                .field(this.getEClassNameField())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(semanticFileCreationDescription);
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
     * Returns the field ePackageNsURI.
     * 
     * @return The field ePackageNsURI
     */
    private GraphQLInputObjectField getEPackageNsURIField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(EPACKAGE_NS_URI_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the field eClassName.
     * 
     * @return The field eClassName
     */
    private GraphQLInputObjectField getEClassNameField() {
        // @formatter:off
        return GraphQLInputObjectField.newInputObjectField()
                .name(ECLASS_NAME_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

}
