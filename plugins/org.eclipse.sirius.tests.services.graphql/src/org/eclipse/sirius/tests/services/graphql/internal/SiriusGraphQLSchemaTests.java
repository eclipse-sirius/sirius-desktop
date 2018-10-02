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
package org.eclipse.sirius.tests.services.graphql.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.sirius.services.graphql.api.ISiriusGraphQLQueryResult;
import org.eclipse.sirius.services.graphql.api.SiriusGraphQLInterpreter;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for the GraphQL schema.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLSchemaTests {

    /**
     * The data field of the result.
     */
    private static final String DATA = "data"; //$NON-NLS-1$

    /**
     * The schema field of the result.
     */
    private static final String SCHEMA = "__schema"; //$NON-NLS-1$

    /**
     * The types field of the result.
     */
    private static final String TYPES = "types"; //$NON-NLS-1$

    /**
     * The name field of the result.
     */
    private static final String NAME = "name"; //$NON-NLS-1$

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
     * The name of the TextFileCreationDescription type.
     */
    private static final String TEXT_FILE_CREATION_DESCRIPTION = "TextFileCreationDescription"; //$NON-NLS-1$

    /**
     * The name of the SemanticFileCreationDescription type.
     */
    private static final String SEMANTIC_FILE_CREATION_DESCRIPTION = "SemanticFileCreationDescription"; //$NON-NLS-1$

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
        String query = SiriusGraphQLTestsMessages.introspectionQuery;
        Map<String, Object> variables = new HashMap<>();
        String operationName = ""; //$NON-NLS-1$
        Object context = null;
        this.result = new SiriusGraphQLInterpreter().execute(query, variables, operationName, context);
    }

    @SuppressWarnings("unchecked")
    private void testType(String expected, String typeName) {
        Map<String, Object> rawData = result.getData();
        Map<String, Object> data = (Map<String, Object>) rawData.get(DATA);
        Map<String, Object> schema = (Map<String, Object>) data.get(SCHEMA);
        List<Map<String, Object>> types = (List<Map<String, Object>>) schema.get(TYPES);

        Optional<Map<String, Object>> optionalType = types.stream().filter(type -> typeName.equals(type.get(NAME))).findFirst();
        if (optionalType.isPresent()) {
            Map<String, Object> type = optionalType.get();
            String typeString = new SiriusGraphQLTypeSerializer().typeToString(type);
            assertEquals(expected, typeString);
        } else {
            fail("The " + typeName + " type has not been found in the schema"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Test the Query type of the schema.
     */
    @Test
    public void testQuerySchema() {
        this.testType(SiriusGraphQLTestsMessages.query, QUERY);
    }

    /**
     * Test the PageInfo type of the schema.
     */
    @Test
    public void testPageInfoSchema() {
        this.testType(SiriusGraphQLTestsMessages.pageInfo, PAGEINFO);
    }

    /**
     * Test the User type of the schema.
     */
    @Test
    public void testUserSchema() {
        this.testType(SiriusGraphQLTestsMessages.user, USER);
    }

    /**
     * Test the Mutation type of the schema.
     */
    @Test
    public void testMutationSchema() {
        this.testType(SiriusGraphQLTestsMessages.mutation, MUTATION);
    }

    /**
     * Test the TextFileCreationDescription type of the schema.
     */
    @Test
    public void testTextFileCreationDescriptionSchema() {
        this.testType(SiriusGraphQLTestsMessages.textFileCreationDescription, TEXT_FILE_CREATION_DESCRIPTION);
    }

    /**
     * Test the SemanticFileCreationDescription type of the schema.
     */
    @Test
    public void testSemanticFileCreationDescriptionSchema() {
        this.testType(SiriusGraphQLTestsMessages.semanticFileCreationDescription, SEMANTIC_FILE_CREATION_DESCRIPTION);
    }

    /**
     * Test the RepresentationCreationDescription type of the schema.
     */
    @Test
    public void testRepresentationCreationDescriptionSchema() {
        this.testType(SiriusGraphQLTestsMessages.representationCreationDescription, REPRESENTATION_CREATION_DESCRIPTION);
    }

    /**
     * Test the Resource type of the schema.
     */
    @Test
    public void testResourceSchema() {
        this.testType(SiriusGraphQLTestsMessages.resource, RESOURCE);
    }

    /**
     * Test the Container type of the schema.
     */
    @Test
    public void testContainerSchema() {
        this.testType(SiriusGraphQLTestsMessages.container, CONTAINER);
    }

    /**
     * Test the Project type of the schema.
     */
    @Test
    public void testProjectSchema() {
        this.testType(SiriusGraphQLTestsMessages.project, PROJECT);
    }

    /**
     * Test the Folder type of the schema.
     */
    @Test
    public void testFolderSchema() {
        this.testType(SiriusGraphQLTestsMessages.folder, FOLDER);
    }

    /**
     * Test the File type of the schema.
     */
    @Test
    public void testFileSchema() {
        this.testType(SiriusGraphQLTestsMessages.file, FILE);
    }

    /**
     * Test the Viewpoint type of the schema.
     */
    @Test
    public void testViewpointSchema() {
        this.testType(SiriusGraphQLTestsMessages.viewpoint, VIEWPOINT);
    }

    /**
     * Test the RepresentationDescription type of the schema.
     */
    @Test
    public void testRepresentationDescriptionSchema() {
        this.testType(SiriusGraphQLTestsMessages.representationDescription, REPRESENTATION_DESCRIPTION);
    }

    /**
     * Test the DiagramDescription type of the schema.
     */
    @Test
    public void testDiagramDescriptionSchema() {
        this.testType(SiriusGraphQLTestsMessages.diagramDescription, DIAGRAM_DESCRIPTION);
    }

    /**
     * Test the Representation type of the schema.
     */
    @Test
    public void testRepresentationSchema() {
        this.testType(SiriusGraphQLTestsMessages.representation, REPRESENTATION);
    }

    /**
     * Test the Diagram type of the schema.
     */
    @Test
    public void testDiagramSchema() {
        this.testType(SiriusGraphQLTestsMessages.diagram, DIAGRAM);
    }

    /**
     * Test the EObject type of the schema.
     */
    @Test
    public void testEObjectSchema() {
        this.testType(SiriusGraphQLTestsMessages.eObject, EOBJECT);
    }

    /**
     * Test the EModelElement type of the schema.
     */
    @Test
    public void testEModelElementSchema() {
        this.testType(SiriusGraphQLTestsMessages.eModelElement, EMODELELEMENT);
    }

    /**
     * Test the ENamedElement type of the schema.
     */
    @Test
    public void testENamedElementSchema() {
        this.testType(SiriusGraphQLTestsMessages.eNamedElement, ENAMEDELEMENT);
    }

    /**
     * Test the EClassifier type of the schema.
     */
    @Test
    public void testEClassifierSchema() {
        this.testType(SiriusGraphQLTestsMessages.eClassifier, ECLASSIFIER);
    }

    /**
     * Test the EClass type of the schema.
     */
    @Test
    public void testEClassSchema() {
        this.testType(SiriusGraphQLTestsMessages.eClass, ECLASS);
    }

    /**
     * Test the ETypedElement type of the schema.
     */
    @Test
    public void testETypedElementSchema() {
        this.testType(SiriusGraphQLTestsMessages.eTypedElement, ETYPEDELEMENT);
    }

    /**
     * Test the EStructuralFeature type of the schema.
     */
    @Test
    public void testEStructuralFeatureSchema() {
        this.testType(SiriusGraphQLTestsMessages.eStructuralFeature, ESTRUCTURALFEATURE);
    }

    /**
     * Test the EAttribute type of the schema.
     */
    @Test
    public void testEAttributeSchema() {
        this.testType(SiriusGraphQLTestsMessages.eAttribute, EATTRIBUTE);
    }

    /**
     * Test the EReference type of the schema.
     */
    @Test
    public void testEReferenceSchema() {
        this.testType(SiriusGraphQLTestsMessages.eReference, EREFERENCE);
    }
}
