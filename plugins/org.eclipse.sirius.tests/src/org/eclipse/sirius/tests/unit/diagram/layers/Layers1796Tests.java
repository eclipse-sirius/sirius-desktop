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

import java.util.Collection;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;

public class Layers1796Tests extends AbtsractLayerTests {

    private static final String TRAC_1796_MODELER_PATH = "/data/unit/layers/trac1796.odesign";

    private static final String TRAC_1796_DIAGRAM = "trac1796";

    private static final String TRAC_1796_SEMANTIC_MODEL_PATH = "/data/unit/layers/trac1796.uml";
    
    @Override
    protected void init() throws Exception {
        genericSetUp(PLUGIN + TRAC_1796_SEMANTIC_MODEL_PATH, PLUGIN + TRAC_1796_MODELER_PATH);
        initViewpoint("trac1796");
    }
    
    
    /**
     * Test successive refresh after activating of a layer containing an edge
     * mapping import. mapping import.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testRefreshWithEdgeMappingImport() throws Exception {
        // loads the diagram.
        final DiagramDescription classDiag = findDiagramDescription(TRAC_1796_DIAGRAM);

        initSynchronizer(classDiag, TRAC_1796_DIAGRAM);

        refreshDiagram();

        assertEquals("Wrong number of nodes", 4, getNumberOfNodes(diagram));
        assertEquals("Wrong number of edges", 2, getNumberOfEdges(diagram));

        DEdge class2Edge = (DEdge) find(getEdges(diagram), "class2");
        assertEquals("Reference Mapping", class2Edge.getMapping().getName());

        DEdge class4Edge = (DEdge) find(getEdges(diagram), "class4");
        assertEquals("Reference Mapping", class4Edge.getMapping().getName());

        // Activates the other layer.
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        setLayerVisibility(diagram, firstLayer, true);

        refreshDiagram();
        refreshVisibility(diagram);
        assertEquals("[REFRESH1]Wrong number of edges", 2, getNumberOfEdges(diagram));

        class2Edge = (DEdge) find(getEdges(diagram), "class2");
        assertEquals("Extension Reference Mapping", class2Edge.getMapping().getName());

        class4Edge = (DEdge) find(getEdges(diagram), "class4");
        assertEquals("Extension Reference Mapping", class4Edge.getMapping().getName());
        refreshDiagram();
        assertEquals("[REFRESH2]Wrong number of edges", 2, getNumberOfEdges(diagram));

        setLayerVisibility(diagram, firstLayer, false);
        refreshDiagram();
        refreshVisibility(diagram);
        assertEquals("[REFRESH2]Wrong number of edges", 2, getNumberOfEdges(diagram));

        class2Edge = (DEdge) find(getEdges(diagram), "class2");
        assertEquals("Reference Mapping", class2Edge.getMapping().getName());

        class4Edge = (DEdge) find(getEdges(diagram), "class4");
        assertEquals("Reference Mapping", class4Edge.getMapping().getName());

    }
    
    private <T extends DDiagramElement> DDiagramElement find(final Collection<T> elements, final String name) {
        for (final T element : elements) {
            if (element.getName().equals(name))
                return element;
        }
        throw new IllegalArgumentException("invalid name");
    }
}
