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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;

public class Layers1203Tests extends AbtsractLayerTests {

    private static final String TRAC_1203_MODELER_PATH = "/data/unit/layers/trac1203.odesign";

    private static final String TRAC_1203_DIAGRAM = "trac1203";

    private static final String TRAC_1203_SEMANTIC_MODEL_PATH = "/data/unit/layers/trac1203.uml";
    
    
    
    @Override
    protected void init() throws Exception {
        genericSetUp(PLUGIN + TRAC_1203_SEMANTIC_MODEL_PATH, PLUGIN + TRAC_1203_MODELER_PATH);
        initViewpoint("trac1203");
    }



    /**
     * Tests the feature : Bordered Node Mapping import on Container Mapping
     * import. See trac #1203.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testBorderedMappingImportOnContainerMappingImport() throws Exception {

        final String containerMappingImportName = "MyContainerImport";
        final String borderedMappingImportName = "ImportNode";

        //
        // loads the diagram.
        final DiagramDescription classDiag = findDiagramDescription(TRAC_1203_DIAGRAM);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        initSynchronizer(classDiag, TRAC_1203_DIAGRAM);

        refreshDiagram();
        assertEquals("Wrong number of elements", 1, getNumberOfElementsInDiagram());

        final DDiagramElement element = getFirstElement();
        assertTrue("The diagram element is not a container", element instanceof DNodeContainer);

        final DNodeContainer container = (DNodeContainer) element;

        final List<DNode> borderedNodes = new ArrayList<DNode>(container.getOwnedBorderedNodes());
        assertEquals("Wrong number of bordered nodes", 2, borderedNodes.size());

        //
        // Activates the other layer.
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        setLayerVisibility(diagram, firstLayer, true);
        refreshDiagram();
        refreshVisibility(diagram);

        assertEquals("Wrong number of elements", 1, getNumberOfElementsInDiagram());
        DDiagramElement elementWithlayer = getFirstElement();
        assertTrue("The diagram element is not a container", elementWithlayer instanceof DNodeContainer);
        DNodeContainer containerWithLayer = (DNodeContainer) elementWithlayer;
        assertTrue("The diagram element has a wrong mapping", containerWithLayer.getActualMapping().getName().equals(containerMappingImportName));
        List<DNode> borderedNodesWithLayer = new ArrayList<DNode>(containerWithLayer.getOwnedBorderedNodes());
        assertEquals("Wrong number of bordered nodes", 2, borderedNodesWithLayer.size());
        for (final DNode node : borderedNodesWithLayer) {
            assertTrue("The diagram element has a wrong mapping", node.getActualMapping().getName().equals(borderedMappingImportName));
        }

        compareElements(borderedNodes, borderedNodesWithLayer);

        //
        // Extension : Bug Refresh.
        refreshDiagram();

        assertEquals("Wrong number of elements", 1, getNumberOfElementsInDiagram());
        elementWithlayer = getFirstElement();
        assertTrue("The diagram element is not a container", elementWithlayer instanceof DNodeContainer);
        containerWithLayer = (DNodeContainer) elementWithlayer;
        assertTrue("The diagram element has a wrong mapping", containerWithLayer.getActualMapping().getName().equals(containerMappingImportName));
        borderedNodesWithLayer = new ArrayList<DNode>(containerWithLayer.getOwnedBorderedNodes());
        assertEquals("Wrong number of bordered nodes", 2, borderedNodesWithLayer.size());
        for (final DNode node : borderedNodesWithLayer) {
            assertTrue("The diagram element has a wrong mapping", node.getActualMapping().getName().equals(borderedMappingImportName));
        }

    }
    
}
