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
package org.eclipse.sirius.services.graphql.internal.schema.query.user;

import static org.eclipse.sirius.services.graphql.internal.schema.query.emf.SiriusGraphQLEPackageTypesBuilder.EPACKAGE_TYPE;
import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder.EDGE_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLProjectTypesBuilder.PROJECT_TYPE;
import static org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLViewpointTypesBuilder.VIEWPOINT_TYPE;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;

/**
 * Used to create the User type of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLUserTypesBuilder implements ISiriusGraphQLTypesBuilder {
    /**
     * The name of the User type.
     */
    public static final String USER_TYPE = "User"; //$NON-NLS-1$

    /**
     * The name of the User to Project connection type.
     */
    public static final String USER_PROJECT_CONNECTION_TYPE = USER_TYPE + PROJECT_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the User to Project edge type.
     */
    public static final String USER_PROJECT_EDGE_TYPE = USER_TYPE + PROJECT_TYPE + EDGE_SUFFIX;

    /**
     * The name of the User to Viewpoint connection type.
     */
    public static final String USER_VIEWPOINT_CONNECTION_TYPE = USER_TYPE + VIEWPOINT_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the User to Viewpoint edge type.
     */
    public static final String USER_VIEWPOINT_EDGE_TYPE = USER_TYPE + VIEWPOINT_TYPE + EDGE_SUFFIX;

    /**
     * The name of the User to EPackage connection type.
     */
    public static final String USER_EPACKAGE_CONNECTION_TYPE = USER_TYPE + EPACKAGE_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the User to EPackage edge type.
     */
    public static final String USER_EPACKAGE_EDGE_TYPE = USER_TYPE + EPACKAGE_TYPE + EDGE_SUFFIX;

    @Override
    public Set<GraphQLType> getTypes() {
        GraphQLObjectType projectEdge = new SiriusGraphQLEdgeTypeBuilder(USER_PROJECT_EDGE_TYPE, PROJECT_TYPE).build();
        GraphQLObjectType projectConnection = new SiriusGraphQLConnectionTypeBuilder(USER_PROJECT_CONNECTION_TYPE, USER_PROJECT_EDGE_TYPE).build();

        GraphQLObjectType viewpointEdge = new SiriusGraphQLEdgeTypeBuilder(USER_VIEWPOINT_EDGE_TYPE, VIEWPOINT_TYPE).build();
        GraphQLObjectType viewpointConnection = new SiriusGraphQLConnectionTypeBuilder(USER_VIEWPOINT_CONNECTION_TYPE, USER_VIEWPOINT_EDGE_TYPE).build();

        GraphQLObjectType ePackageEdge = new SiriusGraphQLEdgeTypeBuilder(USER_EPACKAGE_EDGE_TYPE, EPACKAGE_TYPE).build();
        GraphQLObjectType ePackageConnection = new SiriusGraphQLConnectionTypeBuilder(USER_EPACKAGE_CONNECTION_TYPE, USER_EPACKAGE_EDGE_TYPE).build();

        // @formatter:off
        GraphQLObjectType user = GraphQLObjectType.newObject()
                .name(USER_TYPE)
                .field(SiriusGraphQLUserProjectField.build())
                .field(SiriusGraphQLUserProjectsField.build())
                .field(SiriusGraphQLUserEPackageField.build())
                .field(SiriusGraphQLUserEPackagesField.build())
                .field(SiriusGraphQLUserViewpointField.build())
                .field(SiriusGraphQLUserViewpointsField.build())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();

        types.add(user);
        types.add(projectEdge);
        types.add(projectConnection);
        types.add(viewpointEdge);
        types.add(viewpointConnection);
        types.add(ePackageEdge);
        types.add(ePackageConnection);

        return types;
    }
}
