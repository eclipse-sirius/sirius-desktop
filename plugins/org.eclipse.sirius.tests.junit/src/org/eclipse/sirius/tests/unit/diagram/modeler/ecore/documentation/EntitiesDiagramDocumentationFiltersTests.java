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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * Layers tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramDocumentationFiltersTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
    }

    public void testHideClassContentFilter() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        applyNodeCreationTool("Class", diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        assertTrue(applyNodeCreationTool("Attribute", diagram, getFirstDiagramElement(diagram, eClass)));
        assertTrue(applyNodeCreationTool("Operation", diagram, getFirstDiagramElement(diagram, eClass)));

        final EAttribute eAttribute = eClass.getEAttributes().get(0);
        final EOperation eOperation = eClass.getEOperations().get(0);

        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, eClass)));
        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, eAttribute)));
        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, eOperation)));

        activateFilter(diagram, "Hide class content");

        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, eClass)));
        assertFalse(isVisible(diagram, getFirstDiagramElement(diagram, eAttribute)));
        assertFalse(isVisible(diagram, getFirstDiagramElement(diagram, eOperation)));
    }

    public void testHideGeneralizations() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        applyNodeCreationTool("Class", diagram, diagram);
        final EClass source = (EClass) ePackage.getEClassifiers().get(0);
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass target = (EClass) ePackage.getEClassifiers().get(1);

        applyEdgeCreationTool("SuperType", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        assertTrue(isVisible(diagram, getFirstEdgeElement(diagram, source)));
        activateFilter(diagram, "Hide generalizations");
        assertFalse(isVisible(diagram, getFirstEdgeElement(diagram, source)));
    }

    public void testHideReferences() {
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        applyNodeCreationTool("Class", diagram, diagram);
        final EClass source = (EClass) ePackage.getEClassifiers().get(0);
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass target = (EClass) ePackage.getEClassifiers().get(1);

        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        final EReference eReference = source.getEReferences().get(0);

        assertTrue(isVisible(diagram, getFirstEdgeElement(diagram, eReference)));
        activateFilter(diagram, "Hide references");
        assertFalse(isVisible(diagram, getFirstEdgeElement(diagram, eReference)));
    }

    @Override
    protected void tearDown() throws Exception {

        diagram = null;

        editor = null;

        super.tearDown();
    }
}
