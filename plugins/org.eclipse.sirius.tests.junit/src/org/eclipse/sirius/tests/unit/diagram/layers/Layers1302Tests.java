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
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class Layers1302Tests extends AbtsractLayerTests {

    private static final String TRAC_1302_MODELER_PATH = "/data/unit/layers/trac1302.odesign";

    private static final String TRAC_1302_DIAGRAM = "trac1302_1";

    private static final String TRAC_1302_SEMANTIC_MODEL_PATH = "/data/unit/layers/trac1302.uml";

    @Override
    protected void init() throws Exception {
        genericSetUp(PLUGIN + TRAC_1302_SEMANTIC_MODEL_PATH, PLUGIN + TRAC_1302_MODELER_PATH);
        final Viewpoint vp = getViewpointFromName("trac1302", session);
        activateViewpoint(vp.getName());
    }

    /**
     * Tests a conditional style with a node mapping import that is on another
     * mapping import.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testConditionalStyleWithMappingImport() throws Exception {

        final String containerMappingImportName = "CMI_1";

        //
        // loads the diagram.
        final DiagramDescription classDiag = findDiagramDescription(TRAC_1302_DIAGRAM);

        initSynchronizer(classDiag, TRAC_1302_DIAGRAM);

        refreshDiagram();
        assertEquals("Wrong number of elements", 1, getNumberOfElementsInDiagram());

        final DDiagramElement element = getFirstElement();
        assertTrue("The diagram element is not a container", element instanceof DNodeContainer);

        final DNodeContainer container = (DNodeContainer) element;

        final List<DNode> borderedNodes = new ArrayList<DNode>(container.getOwnedBorderedNodes());
        assertEquals("Wrong number of bordered nodes", 1, borderedNodes.size());

        //
        // Activates the other layer.
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        setLayerVisibility(diagram, firstLayer, true);
        refreshDiagram();
        refreshVisibility(diagram);

        assertEquals("Wrong number of elements", 1, getNumberOfElementsInDiagram());
        final DDiagramElement elementWithlayer = getFirstElement();
        assertTrue("The diagram element is not a container", elementWithlayer instanceof DNodeContainer);
        final DNodeContainer containerWithLayer = (DNodeContainer) elementWithlayer;
        assertTrue("The diagram element has a wrong mapping", containerWithLayer.getActualMapping().getName().equals(containerMappingImportName));
        final List<DNode> borderedNodesWithLayer = new ArrayList<DNode>(containerWithLayer.getOwnedBorderedNodes());
        assertEquals("Wrong number of bordered nodes", 1, borderedNodesWithLayer.size());

        assertTrue("The conditional style of the container is not taken into account.", elementWithlayer.getStyle() instanceof ShapeContainerStyle);
        assertTrue("The conditional style of the bordered node is not taken into account.", borderedNodesWithLayer.get(0).getStyle() instanceof Lozenge);

    }

}
