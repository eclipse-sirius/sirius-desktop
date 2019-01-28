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
package org.eclipse.sirius.tests.services.graphql.internal;

import org.eclipse.sirius.services.graphql.api.ISiriusGraphQLQueryResult;
import org.eclipse.sirius.services.graphql.internal.schema.SiriusGraphQLSchemaProvider;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for the GraphQL schema.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLSchemaTests {

    /**
     * The name of the Query type.
     */
    private static final String QUERY = "Query"; //$NON-NLS-1$

    /**
     * The name of the PageInfo type.
     */
    private static final String PAGEINFO = "PageInfo"; //$NON-NLS-1$

    /**
     * The name of the Mutation type.
     */
    private static final String MUTATION = "Mutation"; //$NON-NLS-1$

    /**
     * The name of the User type.
     */
    private static final String USER = "User"; //$NON-NLS-1$

    /**
     * The name of the Resource type.
     */
    private static final String RESOURCE = "Resource"; //$NON-NLS-1$

    /**
     * The name of the Container type.
     */
    private static final String CONTAINER = "Container"; //$NON-NLS-1$

    /**
     * The name of the Project type.
     */
    private static final String PROJECT = "Project"; //$NON-NLS-1$

    /**
     * The name of the Folder type.
     */
    private static final String FOLDER = "Folder"; //$NON-NLS-1$

    /**
     * The name of the File type.
     */
    private static final String FILE = "File"; //$NON-NLS-1$

    /**
     * The name of the FileCreationDescription type.
     */
    private static final String FILE_CREATION_DESCRIPTION = "FileCreationDescription"; //$NON-NLS-1$

    /**
     * The name of the RepresentationCreationDescription type.
     */
    private static final String REPRESENTATION_CREATION_DESCRIPTION = "RepresentationCreationDescription"; //$NON-NLS-1$

    /**
     * The name of the Viewpoint type.
     */
    private static final String VIEWPOINT = "Viewpoint"; //$NON-NLS-1$

    /**
     * The name of the RepresentationDescription type.
     */
    private static final String REPRESENTATION_DESCRIPTION = "RepresentationDescription"; //$NON-NLS-1$

    /**
     * The name of the DiagramDescription type.
     */
    private static final String DIAGRAM_DESCRIPTION = "DiagramDescription"; //$NON-NLS-1$

    /**
     * The name of the Representation type.
     */
    private static final String REPRESENTATION = "Representation"; //$NON-NLS-1$

    /**
     * The name of the Diagram type.
     */
    private static final String DIAGRAM = "Diagram"; //$NON-NLS-1$

    /**
     * The name of the EObject type.
     */
    private static final String EOBJECT = "EObject"; //$NON-NLS-1$

    /**
     * The name of the EModelElement type.
     */
    private static final String EMODELELEMENT = "EModelElement"; //$NON-NLS-1$

    /**
     * The name of the ENamedElement type.
     */
    private static final String ENAMEDELEMENT = "ENamedElement"; //$NON-NLS-1$

    /**
     * The name of the EClassifier type.
     */
    private static final String ECLASSIFIER = "EClassifier"; //$NON-NLS-1$

    /**
     * The name of the EClass type.
     */
    private static final String ECLASS = "EClass"; //$NON-NLS-1$

    /**
     * The name of the ETypedElement type.
     */
    private static final String ETYPEDELEMENT = "ETypedElement"; //$NON-NLS-1$

    /**
     * The name of the EStructuralFeature type.
     */
    private static final String ESTRUCTURALFEATURE = "EStructuralFeature"; //$NON-NLS-1$

    /**
     * The name of the EAttribute type.
     */
    private static final String EATTRIBUTE = "EAttribute"; //$NON-NLS-1$

    /**
     * The name of the EReference type.
     */
    private static final String EREFERENCE = "EReference"; //$NON-NLS-1$

    /**
     * The result of the execution of the query.
     */
    private ISiriusGraphQLQueryResult result;

    /**
     * Execute the query.
     */
    @Before
    public void setup() {
        this.result = new SiriusGraphQLTestsHelper().getSchema(new SiriusGraphQLSchemaProvider().getSchema());
    }

    /**
     * Test the Query type of the schema.
     */
    @Test
    public void testQuerySchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.query, QUERY);
    }

    /**
     * Test the PageInfo type of the schema.
     */
    @Test
    public void testPageInfoSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.pageInfo, PAGEINFO);
    }

    /**
     * Test the User type of the schema.
     */
    @Test
    public void testUserSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.user, USER);
    }

    /**
     * Test the Mutation type of the schema.
     */
    @Test
    public void testMutationSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.mutation, MUTATION);
    }

    /**
     * Test the TextFileCreationDescription type of the schema.
     */
    @Test
    public void testFileCreationDescriptionSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.fileCreationDescription, FILE_CREATION_DESCRIPTION);
    }

    /**
     * Test the RepresentationCreationDescription type of the schema.
     */
    @Test
    public void testRepresentationCreationDescriptionSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.representationCreationDescription, REPRESENTATION_CREATION_DESCRIPTION);
    }

    /**
     * Test the Resource type of the schema.
     */
    @Test
    public void testResourceSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.resource, RESOURCE);
    }

    /**
     * Test the Container type of the schema.
     */
    @Test
    public void testContainerSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.container, CONTAINER);
    }

    /**
     * Test the Project type of the schema.
     */
    @Test
    public void testProjectSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.project, PROJECT);
    }

    /**
     * Test the Folder type of the schema.
     */
    @Test
    public void testFolderSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.folder, FOLDER);
    }

    /**
     * Test the File type of the schema.
     */
    @Test
    public void testFileSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.file, FILE);
    }

    /**
     * Test the Viewpoint type of the schema.
     */
    @Test
    public void testViewpointSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.viewpoint, VIEWPOINT);
    }

    /**
     * Test the RepresentationDescription type of the schema.
     */
    @Test
    public void testRepresentationDescriptionSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.representationDescription, REPRESENTATION_DESCRIPTION);
    }

    /**
     * Test the DiagramDescription type of the schema.
     */
    @Test
    public void testDiagramDescriptionSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.diagramDescription, DIAGRAM_DESCRIPTION);
    }

    /**
     * Test the Representation type of the schema.
     */
    @Test
    public void testRepresentationSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.representation, REPRESENTATION);
    }

    /**
     * Test the Diagram type of the schema.
     */
    @Test
    public void testDiagramSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.diagram, DIAGRAM);
    }

    /**
     * Test the EObject type of the schema.
     */
    @Test
    public void testEObjectSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eObject, EOBJECT);
    }

    /**
     * Test the EModelElement type of the schema.
     */
    @Test
    public void testEModelElementSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eModelElement, EMODELELEMENT);
    }

    /**
     * Test the ENamedElement type of the schema.
     */
    @Test
    public void testENamedElementSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eNamedElement, ENAMEDELEMENT);
    }

    /**
     * Test the EClassifier type of the schema.
     */
    @Test
    public void testEClassifierSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eClassifier, ECLASSIFIER);
    }

    /**
     * Test the EClass type of the schema.
     */
    @Test
    public void testEClassSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eClass, ECLASS);
    }

    /**
     * Test the ETypedElement type of the schema.
     */
    @Test
    public void testETypedElementSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eTypedElement, ETYPEDELEMENT);
    }

    /**
     * Test the EStructuralFeature type of the schema.
     */
    @Test
    public void testEStructuralFeatureSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eStructuralFeature, ESTRUCTURALFEATURE);
    }

    /**
     * Test the EAttribute type of the schema.
     */
    @Test
    public void testEAttributeSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eAttribute, EATTRIBUTE);
    }

    /**
     * Test the EReference type of the schema.
     */
    @Test
    public void testEReferenceSchema() {
        new SiriusGraphQLTestsHelper().testType(this.result, SiriusGraphQLTestsMessages.eReference, EREFERENCE);
    }
}
