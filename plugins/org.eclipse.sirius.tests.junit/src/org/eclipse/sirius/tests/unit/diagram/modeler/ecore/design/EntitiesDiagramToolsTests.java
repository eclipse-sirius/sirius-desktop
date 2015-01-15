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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.FontFormat;

/**
 * Tools tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramToolsTests extends SiriusDiagramTestCase implements EcoreModeler {

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

        DDiagramElement elt = getFirstDiagramElement(diagram, attribute);
        assertFalse("The label of the attribute should not start with /", elt.getName().startsWith("/"));
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                attribute.setDerived(true);
            }

        };

        executeCommand(cmd);

        refresh(diagram);

        assertTrue("The label of the attribute should start with /", elt.getName().startsWith("/"));

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

        DDiagramElement elt = getFirstDiagramElement(diagram, eReference);
        assertTrue(elt.getStyle() instanceof EdgeStyle);
        assertFalse("The label of the reference should not start with /", elt.getName().startsWith("/"));
        assertEquals("The label of the reference should have a normal font format.", Collections.emptyList(), ((EdgeStyle) elt.getStyle()).getCenterLabelStyle().getLabelFormat());

        Command cmd2 = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                eReference.setDerived(true);
            }

        };

        executeCommand(cmd2);

        refresh(diagram);

        assertTrue("The label of the reference should contain /", elt.getName().contains("/"));
        List<FontFormat> fontFormat = new ArrayList<FontFormat>();
        fontFormat.add(FontFormat.ITALIC_LITERAL);
        assertEquals("The label of the reference should be italicised.", fontFormat, ((EdgeStyle) elt.getStyle()).getCenterLabelStyle().getLabelFormat());

        Command cmd3 = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                eReference.setDerived(false);
            }

        };

        executeCommand(cmd3);

        refresh(diagram);

        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) getFirstDiagramElement(diagram, target), (EdgeTarget) getFirstDiagramElement(diagram, source));

        final EReference eReference2 = target.getEReferences().get(0);

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                eReference.setEOpposite(eReference2);
                eReference2.setEOpposite(eReference);
            }

        };

        executeCommand(cmd);

        refresh(diagram);

        checkBiReferenceCreation("[MODIFY SIMPLE TO DOUBLE]", source, target, eReference.getName(), eReference2.getName());

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

    public void testDefaultLayerBiReferenceCreation() {

        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("[CREATION] The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass source = (EClass) ePackage.getEClassifiers().get(0);

        applyNodeCreationTool("Class", diagram, diagram);

        final EClass target = (EClass) ePackage.getEClassifiers().get(1);

        applyEdgeCreationTool("Bi-directional Reference", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        String sourceExpectedReferenceName = source.getName().toLowerCase();
        String targetExpectedReferenceName = target.getName().toLowerCase();

        checkBiReferenceCreation("[CREATION]", source, target, sourceExpectedReferenceName, targetExpectedReferenceName);

        refresh(diagram);

        checkBiReferenceCreation("[REFRESH 1]", source, target, sourceExpectedReferenceName, targetExpectedReferenceName);

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                final EReference eReferenceSource = source.getEReferences().get(0);
                eReferenceSource.setUpperBound(-1);
            }

        };

        executeCommand(cmd);

        refresh(diagram);
        EList<DEdge> edges = diagram.getEdges();
        DEdge edge = edges.get(0);
        String edgeName = edge.getName();
        String reference1 = "[0..1] " + sourceExpectedReferenceName + " - [0..*] " + targetExpectedReferenceName;
        String reference2 = "[0..*] " + targetExpectedReferenceName + " - [0..1] " + sourceExpectedReferenceName;
        assertTrue("[REFRESH 2] The edge has not the right name", edgeName.equals(reference1) || edgeName.equals(reference2));
    }

    protected void checkBiReferenceCreation(String discriminant, EClass source, EClass target, String sourceExpectedReferenceName, String targetExpectedReferenceName) {

        assertEquals(discriminant + " The operation was not created or more elements were created on source element", 1, source.getEReferences().size());
        assertEquals(discriminant + " The operation was not created or more elements were created on target element", 1, target.getEReferences().size());
        final EReference eReferenceSource = source.getEReferences().get(0);
        assertEquals(discriminant + " The reference from source has not the right name", targetExpectedReferenceName, eReferenceSource.getName());
        assertEquals(discriminant + " The reference from source has not the right type", target, eReferenceSource.getEType());
        final EReference eReferenceTarget = target.getEReferences().get(0);
        assertEquals(discriminant + " The reference from target has not the right name", sourceExpectedReferenceName, eReferenceTarget.getName());
        assertEquals(discriminant + " The reference from target has not the right type", source, eReferenceTarget.getEType());

        EList<DEdge> edges = diagram.getEdges();

        assertEquals(discriminant + " The edge was not created or too edges were created", 1, edges.size());
        DEdge edge = edges.get(0);
        if (edge.getStyle() instanceof EdgeStyle) {
            EdgeStyle style = (EdgeStyle) edge.getStyle();
            assertTrue(discriminant + " The edge has not the right decoration",
                    style.getSourceArrow().equals(EdgeArrows.NO_DECORATION_LITERAL) && style.getTargetArrow().equals(EdgeArrows.NO_DECORATION_LITERAL));
        }

        String edgeName = edge.getName();
        String reference1 = "[0..1] " + sourceExpectedReferenceName + " - [0..1] " + targetExpectedReferenceName;
        String reference2 = "[0..1] " + targetExpectedReferenceName + " - [0..1] " + sourceExpectedReferenceName;
        assertTrue(discriminant + " The edge has not the right name", edgeName.equals(reference1) || edgeName.equals(reference2));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
