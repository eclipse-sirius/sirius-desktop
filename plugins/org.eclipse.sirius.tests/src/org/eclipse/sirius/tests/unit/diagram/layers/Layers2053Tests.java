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
package org.eclipse.sirius.tests.unit.diagram.layers;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.helper.SiriusDiagramHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;

public class Layers2053Tests extends AbtsractLayerTests {

    private static final String TRAC_2053_DIAGRAM = "tc2053";

    private static final String TRAC_2053_SEMANTIC_MODEL_PATH = "/data/unit/layers/trac2053.ecore";

    private static final String TRAC_2053_MODELER_PATH = "/data/unit/layers/trac2053.odesign";

    @Override
    protected void init() throws Exception {
        genericSetUp(PLUGIN + TRAC_2053_SEMANTIC_MODEL_PATH, PLUGIN + TRAC_2053_MODELER_PATH);
        initViewpoint("tc2053");
    }

    /**
     * Tests the overrides of node mapping with "creates element" to false and
     * without semantic candidates expression.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testNodeMappingImportsCreateElementToFalseWithoutSemanticCandidatesExpression() throws Exception {

        // loads the diagram.
        final DiagramDescription classDiag = findDiagramDescription(TRAC_2053_DIAGRAM);

        initSynchronizer(classDiag, TRAC_2053_DIAGRAM);

        refreshDiagram();
        assertEquals("Wrong number of elements", 0, getNumberOfElementsInDiagram());

        final Layer lLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(0);
        final Layer l2Layer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);

        assertEquals("Wrong layer", "L", lLayer.getName());
        assertEquals("Wrong layer", "L2", l2Layer.getName());

        final NodeMapping eClassNodeMapping = lLayer.getNodeMappings().get(0);
        NodeMapping extendedCLassNodeMapping = l2Layer.getNodeMappings().get(0);

        EClass eClassEC1 = (EClass) semanticModel.eContents().get(0);

        DDiagramElement createdElement = SiriusDiagramHelper.createElement(eClassNodeMapping, (DSemanticDiagram) diagram, eClassEC1);
        diagram.getOwnedDiagramElements().add(createdElement);
        assertTrue(EqualityHelper.areEquals(eClassNodeMapping, createdElement.getDiagramElementMapping()));

        refreshDiagram();

        assertEquals("Wrong number of elements", 1, getNumberOfElementsInDiagram());

        // Enable l2 layer, where another mapping overrides
        // ePackageContainerMapping
        setLayerVisibility(diagram, l2Layer, true);
        refreshDiagram();
        refreshVisibility(diagram);

        assertFalse("Mapping should have changed", EqualityHelper.areEquals(eClassNodeMapping, createdElement.getDiagramElementMapping()));
        assertTrue("Extended mapping was not set", EqualityHelper.areEquals(extendedCLassNodeMapping, createdElement.getDiagramElementMapping()));

        setLayerVisibility(diagram, l2Layer, false);
        refreshDiagram();
        refreshVisibility(diagram);

        assertFalse("Mapping should have changed", EqualityHelper.areEquals(extendedCLassNodeMapping, createdElement.getDiagramElementMapping()));
        assertTrue("Base mapping was not set", EqualityHelper.areEquals(eClassNodeMapping, createdElement.getDiagramElementMapping()));
    }

    /**
     * Tests the overrides of container mapping with "creates element" to false
     * and without semantic candidates expression.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testContainerMappingImportsCreateElementToFalseWithoutSemanticCandidatesExpression() throws Exception {
        // reload trac models.
        genericSetUp(PLUGIN + TRAC_2053_SEMANTIC_MODEL_PATH, PLUGIN + TRAC_2053_MODELER_PATH);
        initViewpoint("tc2053");
        //
        // loads the diagram.
        final DiagramDescription classDiag = findDiagramDescription(TRAC_2053_DIAGRAM);

        initSynchronizer(classDiag, TRAC_2053_DIAGRAM);

        refreshDiagram();
        assertEquals("Wrong number of elements", 0, getNumberOfElementsInDiagram());

        final Layer lLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(0);
        final Layer l2Layer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);

        assertEquals("Wrong layer", "L", lLayer.getName());
        assertEquals("Wrong layer", "L2", l2Layer.getName());

        final ContainerMapping ePackageContainerMapping = lLayer.getContainerMappings().get(0);
        ContainerMapping extendedPackageContainerMapping = l2Layer.getContainerMappings().get(0);

        EPackage p1 = (EPackage) semanticModel.eContents().get(1);

        DDiagramElement createdElement = SiriusDiagramHelper.createElement(ePackageContainerMapping, (DSemanticDiagram) diagram, p1);
        diagram.getOwnedDiagramElements().add(createdElement);
        assertTrue(EqualityHelper.areEquals(ePackageContainerMapping, createdElement.getDiagramElementMapping()));

        refreshDiagram();

        assertEquals("Wrong number of elements", 1, getNumberOfElementsInDiagram());

        // Enable l2 layer, where another mapping overrides
        // ePackageContainerMapping
        setLayerVisibility(diagram, l2Layer, true);
        refreshDiagram();
        refreshVisibility(diagram);

        assertFalse("Mapping should have changed", EqualityHelper.areEquals(ePackageContainerMapping, createdElement.getDiagramElementMapping()));
        assertTrue("Extended mapping was not set", EqualityHelper.areEquals(extendedPackageContainerMapping, createdElement.getDiagramElementMapping()));

        setLayerVisibility(diagram, l2Layer, false);
        refreshDiagram();
        refreshVisibility(diagram);

        assertFalse("Mapping should have changed", EqualityHelper.areEquals(extendedPackageContainerMapping, createdElement.getDiagramElementMapping()));
        assertTrue("Base mapping was not set", EqualityHelper.areEquals(ePackageContainerMapping, createdElement.getDiagramElementMapping()));

    }
}
