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

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;
import org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLInterfaceType.Builder;
import graphql.schema.GraphQLType;
import graphql.schema.TypeResolver;

/**
 * Used to create the representation description type.
 *
 * @author sbegaudeau
 */
public class RepresentationDescriptionTypeProvider implements ISiriusGraphQLTypeProvider {

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLInterfaceType.newInterface()
                .name(CoreSchemaConstants.REPRESENTATION_DESCRIPTION_TYPE)
                .field(RepresentationDescriptionIdentifierField.build())
                .field(RepresentationDescriptionNameField.build())
                .field(RepresentationDescriptionViewpointField.build())
                .typeResolver(this.getTypeResolver());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(CoreSchemaConstants.REPRESENTATION_DESCRIPTION_TYPE, builder);
        return customizedBuilder.build();
    }

    /**
     * Returns the type resolver.
     *
     * @return The type resolver
     */
    private TypeResolver getTypeResolver() {
        // @formatter:off
        return environment -> {
            Object object = environment.getObject();
            if (object instanceof DiagramDescription) {
                return environment.getSchema().getObjectType(CoreSchemaConstants.DIAGRAM_DESCRIPTION_TYPE);
            } else {
                // TODO Support other types of representations
            }
            return null;
        };
        // @formatter:on
    }

}
