/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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

import java.util.HashMap;
import java.util.Set;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.SiriusGraphQLMutationTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLProjectCreationDescriptionTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLSemanticFileCreationDescriptionTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLTextFileCreationDescriptionTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.viewpoints.SiriusGraphQLRepresentationCreationDescriptionTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.SiriusGraphQLQueryTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.emf.SiriusGraphQLEPackageTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPageInfoTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLContainerTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLFileTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLFolderTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLProjectTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLResourceTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.user.SiriusGraphQLUserTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLDiagramDescriptionTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLDiagramTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLRepresentationDescriptionTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLRepresentationTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLViewpointTypesBuilder;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;

/**
 * Used to create the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLSchemaBuilder {

    /**
     * Creates the GraphQL schema.
     *
     * @return The GraphQL schema
     */
    public GraphQLSchema build() {
        GraphQLObjectType query = new SiriusGraphQLQueryTypeBuilder().build();

        Set<GraphQLType> userTypes = new SiriusGraphQLUserTypesBuilder().getTypes();

        Set<GraphQLType> resourceTypes = new SiriusGraphQLResourceTypesBuilder().getTypes();
        Set<GraphQLType> fileTypes = new SiriusGraphQLFileTypesBuilder().getTypes();
        Set<GraphQLType> containerTypes = new SiriusGraphQLContainerTypesBuilder().getTypes();
        Set<GraphQLType> projectTypes = new SiriusGraphQLProjectTypesBuilder().getTypes();
        Set<GraphQLType> folderTypes = new SiriusGraphQLFolderTypesBuilder().getTypes();

        Set<GraphQLType> viewpointTypes = new SiriusGraphQLViewpointTypesBuilder().getTypes();
        Set<GraphQLType> representationDescriptionTypes = new SiriusGraphQLRepresentationDescriptionTypesBuilder().getTypes();
        Set<GraphQLType> diagramDescriptionTypes = new SiriusGraphQLDiagramDescriptionTypesBuilder().getTypes();
        Set<GraphQLType> representationTypes = new SiriusGraphQLRepresentationTypesBuilder().getTypes();
        Set<GraphQLType> diagramTypes = new SiriusGraphQLDiagramTypesBuilder().getTypes();

        GraphQLType pageInfo = new SiriusGraphQLPageInfoTypeBuilder().build();

        Set<GraphQLType> ecoreTypes = new SiriusGraphQLEPackageTypesBuilder(EcorePackage.eINSTANCE, new HashMap<>()).getTypes();

        GraphQLObjectType mutation = new SiriusGraphQLMutationTypeBuilder().build();
        Set<GraphQLType> projectCreationDescriptionTypes = new SiriusGraphQLProjectCreationDescriptionTypesBuilder().getTypes();
        Set<GraphQLType> textFileCreationDescriptionTypes = new SiriusGraphQLTextFileCreationDescriptionTypesBuilder().getTypes();
        Set<GraphQLType> semanticFileCreationDescriptionTypes = new SiriusGraphQLSemanticFileCreationDescriptionTypesBuilder().getTypes();
        Set<GraphQLType> representationCreationDescriptionTypes = new SiriusGraphQLRepresentationCreationDescriptionTypesBuilder().getTypes();

        // @formatter:off
        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(query)
                .mutation(mutation)
                .additionalTypes(userTypes)
                .additionalTypes(resourceTypes)
                .additionalTypes(fileTypes)
                .additionalTypes(containerTypes)
                .additionalTypes(projectTypes)
                .additionalTypes(folderTypes)
                .additionalTypes(viewpointTypes)
                .additionalTypes(representationDescriptionTypes)
                .additionalTypes(diagramDescriptionTypes)
                .additionalTypes(representationTypes)
                .additionalTypes(diagramTypes)
                .additionalType(pageInfo)
                .additionalTypes(ecoreTypes)
                .additionalTypes(projectCreationDescriptionTypes)
                .additionalTypes(textFileCreationDescriptionTypes)
                .additionalTypes(semanticFileCreationDescriptionTypes)
                .additionalTypes(representationCreationDescriptionTypes)
                .build();
        // @formatter:on

        return schema;
    }
}
