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

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;
import org.eclipse.sirius.services.graphql.emf.api.EMFSchemaConstants;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the DynamicEObject type.
 * 
 * @author sbegaudeau
 */
public class DynamicEObjectTypeProvider implements ISiriusGraphQLTypeProvider {

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(EMFSchemaConstants.DYNAMICEOBJECT_TYPE)
                .withInterface(new GraphQLTypeReference(EMFSchemaConstants.EOBJECT_TYPE));
        // @formatter:on

        customizer.customize(EMFSchemaConstants.DYNAMICEOBJECT_TYPE, builder);
        return builder.build();
    }

}
