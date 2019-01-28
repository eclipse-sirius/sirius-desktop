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

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the DiagramDescription type.
 *
 * @author sbegaudeau
 */
public class DiagramDescriptionTypeProvider implements ISiriusGraphQLTypeProvider {

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLObjectType.newObject()
                .name(CoreSchemaConstants.DIAGRAM_DESCRIPTION_TYPE)
                .field(RepresentationDescriptionIdentifierField.build())
                .field(RepresentationDescriptionNameField.build())
                .field(RepresentationDescriptionViewpointField.build())
                .withInterface(new GraphQLTypeReference(CoreSchemaConstants.REPRESENTATION_DESCRIPTION_TYPE));
        // @formatter:on

        Builder customizedBuilder = customizer.customize(CoreSchemaConstants.DIAGRAM_DESCRIPTION_TYPE, builder);
        return customizedBuilder.build();
    }
}
