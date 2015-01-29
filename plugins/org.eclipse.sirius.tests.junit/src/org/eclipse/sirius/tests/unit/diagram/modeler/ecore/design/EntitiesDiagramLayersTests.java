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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Layers tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramLayersTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    public void testDefaultLayerVisibility() {

        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        applyNodeCreationTool("Class", diagram, diagram);
        final EClass source = (EClass) ePackage.getEClassifiers().get(0);
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass target = (EClass) ePackage.getEClassifiers().get(1);
        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, source)));
        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, target)));
    }

    public void testPackageLayerVisibility() {

        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        assertTrue(applyNodeCreationTool("Package", diagram, diagram));

        final EPackage eSubPackage = ePackage.getESubpackages().get(0);

        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, eSubPackage)));
        assertTrue(deactivateLayer(diagram, LAYER_PACKAGE_NAME));
        assertFalse(isVisible(diagram, getFirstDiagramElement(diagram, eSubPackage)));
        assertTrue(activateLayer(diagram, LAYER_PACKAGE_NAME));
        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, eSubPackage)));

        assertTrue(applyNodeCreationTool("Class", diagram, getFirstDiagramElement(diagram, eSubPackage)));
        final EClass eSubClass = (EClass) eSubPackage.getEClassifiers().get(0);

        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, eSubClass)));
        assertTrue(deactivateLayer(diagram, LAYER_PACKAGE_NAME));
        assertFalse(isVisible(diagram, getFirstDiagramElement(diagram, eSubClass)));
    }

    @Override
    protected void tearDown() throws Exception {
        
        diagram = null;
        
        super.tearDown();
    }

}
