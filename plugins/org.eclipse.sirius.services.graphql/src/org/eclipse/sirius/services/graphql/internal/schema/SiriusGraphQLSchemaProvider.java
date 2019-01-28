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
package org.eclipse.sirius.services.graphql.internal.schema;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLSchemaCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLCommonPlugin;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;
import org.eclipse.sirius.services.graphql.common.api.pagination.PageInfoTypeProvider;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnectionTypeProvider;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLEdgeTypeProvider;
import org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants;
import org.eclipse.sirius.services.graphql.emf.api.EMFSchemaConstants;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.FileCreationDescriptionTypeProvider;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.MutationTypeProvider;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.ProjectCreationDescriptionTypeProvider;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.RepresentationCreationDescriptionTypesProvider;
import org.eclipse.sirius.services.graphql.internal.schema.query.QueryTypeProvider;
import org.eclipse.sirius.services.graphql.internal.schema.query.user.UserTypesProvider;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLSchema.Builder;
import graphql.schema.GraphQLType;

/**
 * Used to create the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLSchemaProvider {

    /**
     * Creates the GraphQL schema.
     *
     * @return The GraphQL schema
     */
    public GraphQLSchema getSchema() {
        ISiriusGraphQLTypeCustomizer graphQLTypeCustomizer = new SiriusGraphQLTypeCustomizer();
        Builder builder = this.initializeSchema(graphQLTypeCustomizer);

        // @formatter:off
        Function<GraphQLType, SiriusGraphQLFilterStatus> pageInfoTypeFilter = graphQLType -> {
            if (PageInfoTypeProvider.PAGE_INFO_TYPE.equals(graphQLType.getName())) {
                return SiriusGraphQLFilterStatus.REJECT;
            }
            return SiriusGraphQLFilterStatus.KEEP;
        };
        // @formatter:on

        Collection<ISiriusGraphQLSchemaCustomizer> customizers = SiriusGraphQLCommonPlugin.getPlugin().getGraphQLSchemaCustomizers();
        customizers.forEach(customizer -> customizer.customize(builder, pageInfoTypeFilter, graphQLTypeCustomizer));

        return builder.build();
    }

    /**
     * Initializes the schema.
     * 
     * @param graphQLTypeCustomizer
     *            The GraphQL type customizer
     * @return The builder of the initialized schema
     */
    private Builder initializeSchema(ISiriusGraphQLTypeCustomizer graphQLTypeCustomizer) {
        GraphQLObjectType query = new QueryTypeProvider().getType(graphQLTypeCustomizer);
        GraphQLObjectType mutation = new MutationTypeProvider().getType(graphQLTypeCustomizer);

        Set<GraphQLType> userTypes = new UserTypesProvider().getTypes(graphQLTypeCustomizer);
        GraphQLType pageInfoType = new PageInfoTypeProvider().getType(graphQLTypeCustomizer);
        GraphQLType projectCreationDescriptionType = new ProjectCreationDescriptionTypeProvider().getType(graphQLTypeCustomizer);
        GraphQLType fileCreationDescriptionType = new FileCreationDescriptionTypeProvider().getType(graphQLTypeCustomizer);
        GraphQLType representationCreationDescriptionType = new RepresentationCreationDescriptionTypesProvider().getType(graphQLTypeCustomizer);

        GraphQLObjectType representationDescriptionEPackageEdge = new SiriusGraphQLEdgeTypeProvider(SchemaConstants.REPRESENTATION_DESCRIPTION_EPACKAGE_EDGE_TYPE, EMFSchemaConstants.EPACKAGE_TYPE)
                .getType(graphQLTypeCustomizer);
        GraphQLObjectType representationDescriptionEPackageConnection = new SiriusGraphQLConnectionTypeProvider(SchemaConstants.REPRESENTATION_DESCRIPTION_EPACKAGE_CONNECTION_TYPE,
                SchemaConstants.REPRESENTATION_DESCRIPTION_EPACKAGE_EDGE_TYPE).getType(graphQLTypeCustomizer);

        GraphQLObjectType projectActivatedViewpointsEdge = new SiriusGraphQLEdgeTypeProvider(SchemaConstants.PROJECT_VIEWPOINT_EDGE_TYPE, CoreSchemaConstants.VIEWPOINT_TYPE)
                .getType(graphQLTypeCustomizer);
        GraphQLObjectType projectActivatedViewpointsConnection = new SiriusGraphQLConnectionTypeProvider(SchemaConstants.PROJECT_VIEWPOINT_CONNECTION_TYPE, SchemaConstants.PROJECT_VIEWPOINT_EDGE_TYPE)
                .getType(graphQLTypeCustomizer);

        GraphQLObjectType fileEObjectsEdge = new SiriusGraphQLEdgeTypeProvider(SchemaConstants.FILE_EOBJECT_EDGE_TYPE, EMFSchemaConstants.EOBJECT_TYPE).getType(graphQLTypeCustomizer);
        GraphQLObjectType fileEObjectsConnection = new SiriusGraphQLConnectionTypeProvider(SchemaConstants.FILE_EOBJECT_CONNECTION_TYPE, SchemaConstants.FILE_EOBJECT_EDGE_TYPE)
                .getType(graphQLTypeCustomizer);

        GraphQLObjectType fileRepresentationsEdge = new SiriusGraphQLEdgeTypeProvider(SchemaConstants.FILE_REPRESENTATION_EDGE_TYPE, CoreSchemaConstants.REPRESENTATION_TYPE)
                .getType(graphQLTypeCustomizer);
        GraphQLObjectType fileRepresentationsConnection = new SiriusGraphQLConnectionTypeProvider(SchemaConstants.FILE_REPRESENTATION_CONNECTION_TYPE, SchemaConstants.FILE_REPRESENTATION_EDGE_TYPE)
                .getType(graphQLTypeCustomizer);

        // @formatter:off
        Builder builder = GraphQLSchema.newSchema()
                .query(query)
                .mutation(mutation)
                .additionalTypes(userTypes)
                .additionalType(pageInfoType)
                .additionalType(projectCreationDescriptionType)
                .additionalType(fileCreationDescriptionType)
                .additionalType(representationCreationDescriptionType)
                .additionalType(representationDescriptionEPackageEdge)
                .additionalType(representationDescriptionEPackageConnection)
                .additionalType(projectActivatedViewpointsEdge)
                .additionalType(projectActivatedViewpointsConnection)
                .additionalType(fileEObjectsEdge)
                .additionalType(fileEObjectsConnection)
                .additionalType(fileRepresentationsEdge)
                .additionalType(fileRepresentationsConnection);
        // @formatter:on

        return builder;
    }
}
