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
package org.eclipse.sirius.services.graphql.emf.internal.schema;

import org.eclipse.emf.ecore.EEnum;

import graphql.schema.GraphQLEnumType;
import graphql.schema.GraphQLEnumType.Builder;

/**
 * Used to transform the definition of an EEnum into a GraphQL enum.
 * 
 * @author sbegaudeau
 */
public class EEnumTypeBuilder {

    /**
     * The EEnum.
     */
    private EEnum eEnum;

    /**
     * The constructor.
     * 
     * @param eEnum
     *            The EEnum
     */
    public EEnumTypeBuilder(EEnum eEnum) {
        this.eEnum = eEnum;
    }

    /**
     * Returns the GraphQL enum type.
     * 
     * @return The GraphQL enum type
     */
    public GraphQLEnumType getType() {
        // @formatter:off
        Builder builder = GraphQLEnumType.newEnum()
                .name(this.eEnum.getName());
        
        this.eEnum.getELiterals().forEach(eEnumLiteral -> {
            builder.value(eEnumLiteral.getName(), eEnumLiteral.getValue());
        });
        
        return builder.build();
        // @formatter:on
    }

}
