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
package org.eclipse.sirius.services.graphql.internal.schema.query.user;

import static org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnectionTypeProvider.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLEdgeTypeProvider.EDGE_SUFFIX;
import static org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants.VIEWPOINT_TYPE;
import static org.eclipse.sirius.services.graphql.emf.api.EMFSchemaConstants.EPACKAGE_TYPE;
import static org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants.PROJECT_TYPE;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypesProvider;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnectionTypeProvider;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLEdgeTypeProvider;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLType;

/**
 * Used to create the User type of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class UserTypesProvider implements ISiriusGraphQLTypesProvider {
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
    public Set<GraphQLType> getTypes(ISiriusGraphQLTypeCustomizer customizer) {
        GraphQLObjectType projectEdge = new SiriusGraphQLEdgeTypeProvider(USER_PROJECT_EDGE_TYPE, PROJECT_TYPE).getType(customizer);
        GraphQLObjectType projectConnection = new SiriusGraphQLConnectionTypeProvider(USER_PROJECT_CONNECTION_TYPE, USER_PROJECT_EDGE_TYPE).getType(customizer);

        GraphQLObjectType viewpointEdge = new SiriusGraphQLEdgeTypeProvider(USER_VIEWPOINT_EDGE_TYPE, VIEWPOINT_TYPE).getType(customizer);
        GraphQLObjectType viewpointConnection = new SiriusGraphQLConnectionTypeProvider(USER_VIEWPOINT_CONNECTION_TYPE, USER_VIEWPOINT_EDGE_TYPE).getType(customizer);

        GraphQLObjectType ePackageEdge = new SiriusGraphQLEdgeTypeProvider(USER_EPACKAGE_EDGE_TYPE, EPACKAGE_TYPE).getType(customizer);
        GraphQLObjectType ePackageConnection = new SiriusGraphQLConnectionTypeProvider(USER_EPACKAGE_CONNECTION_TYPE, USER_EPACKAGE_EDGE_TYPE).getType(customizer);

        // @formatter:off
        Builder userBuilder = GraphQLObjectType.newObject()
                .name(USER_TYPE)
                .field(UserProjectField.build())
                .field(UserProjectsField.build())
                .field(UserEPackageField.build())
                .field(UserEPackagesField.build())
                .field(UserViewpointField.build())
                .field(UserViewpointsField.build());
        // @formatter:on

        Builder customizedUserBuilder = customizer.customize(USER_TYPE, userBuilder);

        Set<GraphQLType> types = new LinkedHashSet<>();

        types.add(customizedUserBuilder.build());
        types.add(projectEdge);
        types.add(projectConnection);
        types.add(viewpointEdge);
        types.add(viewpointConnection);
        types.add(ePackageEdge);
        types.add(ePackageConnection);

        return types;
    }
}
