/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Tools tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramDocumentationToolsTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    public void testDefaultLayerClassCreation() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        assertEquals("The class was not created or more elements were created", 1, ePackage.getEClassifiers().size());
        assertTrue("The class has not the right instance type", ePackage.getEClassifiers().get(0) instanceof EClass);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        assertEquals("The class has not the right name", "NewEClass1", eClass.getName());
    }

    public void testDefaultLayerAttributeCreation() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        assertTrue(applyNodeCreationTool("Attribute", diagram, getFirstDiagramElement(diagram, eClass)));
        assertEquals("The attribute was not created or more elements were created", 1, eClass.getEAttributes().size());
        final EAttribute attribute = eClass.getEAttributes().get(0);
        assertEquals("The attribute has not the right name", "newAttribute", attribute.getName());
    }

    public void testDefaultLayerOperationCreation() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        assertTrue(applyNodeCreationTool("Operation", diagram, getFirstDiagramElement(diagram, eClass)));
        assertEquals("The operation was not created or more elements were created", 1, eClass.getEOperations().size());
        final EOperation eOperation = eClass.getEOperations().get(0);
        assertEquals("The operation has not the right name", "newOperation1", eOperation.getName());
    }

    public void testDefaultLayerReferenceCreation() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass source = (EClass) ePackage.getEClassifiers().get(0);

        applyNodeCreationTool("Class", diagram, diagram);

        final EClass target = (EClass) ePackage.getEClassifiers().get(1);

        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));
        assertEquals("The operation was not created or more elements were created", 1, source.getEReferences().size());
        final EReference eReference = source.getEReferences().get(0);
        assertEquals("The reference has not the right name", "newEReference1", eReference.getName());
        assertEquals("The reference has not the right type", target, eReference.getEType());
    }

    public void testDefaultLayerCompositionCreation() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass source = (EClass) ePackage.getEClassifiers().get(0);

        applyNodeCreationTool("Class", diagram, diagram);

        final EClass target = (EClass) ePackage.getEClassifiers().get(1);

        applyEdgeCreationTool("Composition", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));
        assertEquals("The operation was not created or more elements were created", 1, source.getEReferences().size());
        final EReference eReference = source.getEReferences().get(0);
        assertEquals("The reference has not the right name", "newEReference", eReference.getName());
        assertEquals("The reference has not the right type", target, eReference.getEType());
        assertEquals("The reference is not a contained reference", true, eReference.isContainment());
    }

    public void testDefaultLayerSupertypeCreation() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass source = (EClass) ePackage.getEClassifiers().get(0);

        applyNodeCreationTool("Class", diagram, diagram);

        final EClass target = (EClass) ePackage.getEClassifiers().get(1);

        assertEquals("The element source has already a super type", 0, source.getESuperTypes().size());
        applyEdgeCreationTool("SuperType", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));
        assertEquals("The super type was not set", 1, source.getESuperTypes().size());
        final EClass eSuperType = source.getESuperTypes().get(0);
        assertTrue("The super type is not the correct one", eSuperType == target);
    }

    public void testDefaultLayerNameEdition() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);

        assertTrue(applyDirectEditTool("Edit Name", diagram, getFirstDiagramElement(diagram, eClass), "test"));
        assertEquals("The class has not the right name", "test", eClass.getName());

        assertTrue(applyDirectEditTool("Edit Name", diagram, getFirstDiagramElement(diagram, eClass), "hop"));
        assertEquals("The class has not the right name", "hop", eClass.getName());
    }

    public void testPackageLayerPackageCreation() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getESubpackages().isEmpty());
        assertTrue(applyNodeCreationTool("Package", diagram, diagram));
        assertEquals("The package was not created or more elements were created", 1, ePackage.getESubpackages().size());

        final EPackage eSubPackage = ePackage.getESubpackages().get(0);
        assertEquals("The sub package has not the right name", "newPackage1", eSubPackage.getName());
    }

    @Override
    protected void tearDown() throws Exception {
        
        diagram = null;
        
        super.tearDown();
    }
}
