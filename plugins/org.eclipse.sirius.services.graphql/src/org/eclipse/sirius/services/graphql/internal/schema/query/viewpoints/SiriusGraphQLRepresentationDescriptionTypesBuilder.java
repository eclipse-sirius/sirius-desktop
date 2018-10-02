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

import static org.eclipse.sirius.services.graphql.internal.schema.query.emf.SiriusGraphQLEPackageTypesBuilder.EPACKAGE_TYPE;
import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder.EDGE_SUFFIX;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.TypeResolver;

/**
 * Used to create the representation description type.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLRepresentationDescriptionTypesBuilder implements ISiriusGraphQLTypesBuilder {
    /**
     * The name of the RepresentationDescription type.
     */
    public static final String REPRESENTATION_DESCRIPTION_TYPE = "RepresentationDescription"; //$NON-NLS-1$

    /**
     * The name of the RepresentationDescription to EPackage connection type.
     */
    public static final String REPRESENTATION_DESCRIPTION_EPACKAGE_CONNECTION_TYPE = REPRESENTATION_DESCRIPTION_TYPE + EPACKAGE_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the RepresentationDescription to EPackage edge type.
     */
    public static final String REPRESENTATION_DESCRIPTION_EPACKAGE_EDGE_TYPE = REPRESENTATION_DESCRIPTION_TYPE + EPACKAGE_TYPE + EDGE_SUFFIX;

    @Override
    public Set<GraphQLType> getTypes() {

        GraphQLObjectType ePackageEdge = new SiriusGraphQLEdgeTypeBuilder(REPRESENTATION_DESCRIPTION_EPACKAGE_EDGE_TYPE, EPACKAGE_TYPE).build();
        GraphQLObjectType ePackageConnection = new SiriusGraphQLConnectionTypeBuilder(REPRESENTATION_DESCRIPTION_EPACKAGE_CONNECTION_TYPE, REPRESENTATION_DESCRIPTION_EPACKAGE_EDGE_TYPE).build();

        // @formatter:off
        GraphQLInterfaceType representationDescription = GraphQLInterfaceType.newInterface()
                .name(REPRESENTATION_DESCRIPTION_TYPE)
                .field(SiriusGraphQLRepresentationDescriptionIdentifierField.build())
                .field(SiriusGraphQLRepresentationDescriptionNameField.build())
                .field(SiriusGraphQLRepresentationDescriptionViewpointField.build())
                .field(SiriusGraphQLRepresentationDescriptionEPackagesField.build())
                .typeResolver(this.getTypeResolver())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(representationDescription);
        types.add(ePackageEdge);
        types.add(ePackageConnection);
        return types;
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
                return environment.getSchema().getObjectType(SiriusGraphQLDiagramDescriptionTypesBuilder.DIAGRAM_DESCRIPTION_TYPE);
            } else {
                // TODO Support other types of representations
            }
            return null;
        };
        // @formatter:on
    }

}
