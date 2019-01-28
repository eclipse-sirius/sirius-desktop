/*******************************************************************************
 * Copyright (c) 2019 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.services.graphql.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.services.graphql.api.ISiriusGraphQLQueryResult;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;
import org.eclipse.sirius.services.graphql.emf.internal.schema.EMFGraphQLSchemaCustomizer;
import org.eclipse.sirius.services.graphql.emf.internal.schema.EObjectTypeProvider;
import org.eclipse.sirius.services.graphql.emf.internal.schema.EPackageTypesProvider;
import org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage;
import org.junit.Test;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLSchema.Builder;
import graphql.schema.GraphQLType;

/**
 * Tests of the EPackage GraphQL schema.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLEMFEPackagesTests {

    /**
     * The name of the EObject type.
     */
    private static final String EOBJECT = "EObject"; //$NON-NLS-1$

    /**
     * The name of the Library type.
     */
    private static final String LIBRARY = "Library"; //$NON-NLS-1$

    /**
     * The name of the Book type.
     */
    private static final String BOOK = "Book"; //$NON-NLS-1$

    /**
     * Test the default schema generated.
     */
    @Test
    public void testDefaultSchema() {
        List<EPackage> ePackages = new ArrayList<>();
        ePackages.add(EcorePackage.eINSTANCE);

        GraphQLObjectType queryType = GraphQLObjectType.newObject().name("Query").build(); //$NON-NLS-1$
        Builder graphQLSchemaBuilder = GraphQLSchema.newSchema().query(queryType);
        Function<GraphQLType, SiriusGraphQLFilterStatus> typeFilter = graphQLType -> SiriusGraphQLFilterStatus.KEEP;
        ISiriusGraphQLTypeCustomizer graphQLTypeCustomizer = new ISiriusGraphQLTypeCustomizer() {
            // Do not customize anything
        };
        Builder customizedBuilder = new EMFGraphQLSchemaCustomizer().customize(graphQLSchemaBuilder, typeFilter, graphQLTypeCustomizer);

        GraphQLSchema graphQLSchema = customizedBuilder.build();
        ISiriusGraphQLQueryResult result = new SiriusGraphQLTestsHelper().getSchema(graphQLSchema);
        new SiriusGraphQLTestsHelper().testType(result, SiriusGraphQLTestsMessages.eObject, EOBJECT);
    }

    /**
     * Test the schema generated for library.
     */
    @Test
    public void testLibrarySchema() {
        ISiriusGraphQLTypeCustomizer graphQLTypeCustomizer = new ISiriusGraphQLTypeCustomizer() {
            // Do not customize anything
        };
        GraphQLType eObjectType = new EObjectTypeProvider().getType(graphQLTypeCustomizer);
        Set<GraphQLType> types = new EPackageTypesProvider(ExtlibraryPackage.eINSTANCE).getTypes(graphQLTypeCustomizer);

        GraphQLObjectType queryType = GraphQLObjectType.newObject().name("Query").build(); //$NON-NLS-1$
        GraphQLSchema graphQLSchema = GraphQLSchema.newSchema().query(queryType).additionalType(eObjectType).additionalTypes(types).build();
        ISiriusGraphQLQueryResult result = new SiriusGraphQLTestsHelper().getSchema(graphQLSchema);
        new SiriusGraphQLTestsHelper().testType(result, SiriusGraphQLTestsMessages.library, LIBRARY);
        new SiriusGraphQLTestsHelper().testType(result, SiriusGraphQLTestsMessages.book, BOOK);
    }
}
