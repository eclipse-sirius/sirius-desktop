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
package org.eclipse.sirius.services.graphql.common.api;

import graphql.schema.GraphQLEnumType;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLUnionType;

/**
 * Interface used to customize any GraphQL type builder.
 * 
 * @author sbegaudeau
 */
public interface ISiriusGraphQLTypeCustomizer {

    /**
     * Allows the customization of the given type builder.
     * 
     * @param name
     *            The name of the type
     * @param objectTypeBuilder
     *            The object type builder
     * @return The given object type builder
     */
    default GraphQLObjectType.Builder customize(String name, GraphQLObjectType.Builder objectTypeBuilder) {
        return objectTypeBuilder;
    }

    /**
     * Allows the customization of the given type builder.
     * 
     * @param name
     *            The name of the type
     * @param interfaceTypeBuilder
     *            The interface type builder
     * @return The given interface type builder
     */
    default GraphQLInterfaceType.Builder customize(String name, GraphQLInterfaceType.Builder interfaceTypeBuilder) {
        return interfaceTypeBuilder;
    }

    /**
     * Allows the customization of the given type builder.
     * 
     * @param name
     *            The name of the type
     * @param unionTypeBuilder
     *            The union type builder
     * @return The given union type builder
     */
    default GraphQLUnionType.Builder customize(String name, GraphQLUnionType.Builder unionTypeBuilder) {
        return unionTypeBuilder;
    }

    /**
     * Allows the customization of the given type builder.
     * 
     * @param name
     *            The name of the type
     * @param enumTypeBuilder
     *            The enum type builder
     * @return The given enum type builder
     */
    default GraphQLEnumType.Builder customize(String name, GraphQLEnumType.Builder enumTypeBuilder) {
        return enumTypeBuilder;
    }

    /**
     * Allows the customization of the given type builder.
     * 
     * @param name
     *            The name of the type
     * @param inputObjectTypeBuilder
     *            The input object type builder
     * @return The given input object type builder
     */
    default GraphQLInputObjectType.Builder customize(String name, GraphQLInputObjectType.Builder inputObjectTypeBuilder) {
        return inputObjectTypeBuilder;
    }
}
