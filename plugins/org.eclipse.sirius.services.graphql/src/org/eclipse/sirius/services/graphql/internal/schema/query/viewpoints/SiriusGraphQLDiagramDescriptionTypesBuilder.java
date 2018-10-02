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
package org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the DiagramDescription type.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLDiagramDescriptionTypesBuilder implements ISiriusGraphQLTypesBuilder {
    /**
     * The name of the DiagramDescription type.
     */
    public static final String DIAGRAM_DESCRIPTION_TYPE = "DiagramDescription"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLObjectType diagramDescription = GraphQLObjectType.newObject()
                .name(DIAGRAM_DESCRIPTION_TYPE)
                .field(SiriusGraphQLRepresentationDescriptionIdentifierField.build())
                .field(SiriusGraphQLRepresentationDescriptionNameField.build())
                .field(SiriusGraphQLRepresentationDescriptionViewpointField.build())
                .field(SiriusGraphQLRepresentationDescriptionEPackagesField.build())
                .withInterface(new GraphQLTypeReference(SiriusGraphQLRepresentationDescriptionTypesBuilder.REPRESENTATION_DESCRIPTION_TYPE))
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(diagramDescription);
        return types;
    }
}
