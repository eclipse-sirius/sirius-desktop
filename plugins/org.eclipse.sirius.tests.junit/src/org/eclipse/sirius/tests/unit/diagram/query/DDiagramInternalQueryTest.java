/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.query;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.internal.query.model.DDiagramInternalQuery;

import com.google.common.collect.Iterables;

import junit.framework.TestCase;

/**
 * Class to test the {@link DDiagramInternalQuery} class.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 * 
 */
public class DDiagramInternalQueryTest extends TestCase {

    private DDiagram dDiagram;

    private DDiagramInternalQuery query;

    private int nodesNb = 0;

    private int edgesNb = 0;

    private int containersNb = 0;

    private int listsNb = 0;

    private int listEltsNb = 0;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // initialize dDiagram
        dDiagram = DiagramFactory.eINSTANCE.createDDiagram();
        query = new DDiagramInternalQuery(dDiagram);

        DNode node0 = createNode();
        DNode node0B = createNode();
        node0.getOwnedBorderedNodes().add(node0B);

        DNode node0BB = createNode();
        node0B.getOwnedBorderedNodes().add(node0BB);

        DNode node0BBB = createNode();
        node0BB.getOwnedBorderedNodes().add(node0BBB);
        dDiagram.getOwnedDiagramElements().add(node0);

        DNodeContainer container0 = createContainer();
        DNode container0B = createNode();
        container0.getOwnedBorderedNodes().add(container0B);

        DNode node1 = createNode();
        DNode node1B = createNode();
        node1.getOwnedBorderedNodes().add(node1B);
        container0.getOwnedDiagramElements().add(node1);

        DNodeContainer container1 = createContainer();
        DNode container1B = createNode();
        container1.getOwnedBorderedNodes().add(container1B);

        DNode node2 = createNode();
        DNode node2B = createNode();
        node2.getOwnedBorderedNodes().add(node2B);
        container1.getOwnedDiagramElements().add(node2);
        container0.getOwnedDiagramElements().add(container1);

        DNodeList list1 = createList();
        DNode list1B = createNode();
        DNodeListElement listElt1 = createListElement();
        list1.getOwnedElements().add(listElt1);
        list1.getOwnedBorderedNodes().add(list1B);
        container0.getOwnedDiagramElements().add(list1);
        dDiagram.getOwnedDiagramElements().add(container0);

        DNodeList list0 = createList();
        DNode list0B = createNode();
        DNodeListElement listElt0 = createListElement();
        list0.getOwnedBorderedNodes().add(list0B);
        list0.getOwnedElements().add(listElt0);
        dDiagram.getOwnedDiagramElements().add(list0);

        DEdge edge = createEdge();
        edge.setSourceNode(node0);
        edge.setTargetNode(node1);
        dDiagram.getOwnedDiagramElements().add(edge);
    }

    /**
     * Test.
     */
    public void testDiagramElementsNumber() {
        int nbElts = nodesNb + containersNb + edgesNb + listsNb + listEltsNb;
        assertEquals(nbElts, query.getDiagramElements().size());
    }

    /**
     * Test.
     */
    public void testNodesNumber() {
        assertEquals(nodesNb, query.getNodes().size());
    }

    /**
     * Test.
     */
    public void testEdgesNumber() {
        assertEquals(edgesNb, query.getEdges().size());
    }

    /**
     * Test.
     */
    public void testListElementsNumber() {
        assertEquals(listEltsNb, query.getNodeListElements().size());
    }

    /**
     * Test.
     */
    public void testDiagramElementContainersNumber() {
        assertEquals(listsNb + containersNb, query.getContainers().size());
    }

    /**
     * Test.
     */
    public void testContainersNumber() {
        assertEquals(containersNb, Iterables.size(Iterables.filter(query.getContainers(), DNodeContainer.class)));
    }

    /**
     * Test.
     */
    public void testListsNumber() {
        assertEquals(listsNb, Iterables.size(Iterables.filter(query.getContainers(), DNodeList.class)));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        dDiagram = null;
        edgesNb = 0;
        nodesNb = 0;
        containersNb = 0;
        listEltsNb = 0;
        listsNb = 0;
    }

    private DEdge createEdge() {
        edgesNb++;
        return DiagramFactory.eINSTANCE.createDEdge();
    }

    private DNode createNode() {
        nodesNb++;
        return DiagramFactory.eINSTANCE.createDNode();
    }

    private DNodeContainer createContainer() {
        containersNb++;
        return DiagramFactory.eINSTANCE.createDNodeContainer();
    }

    private DNodeListElement createListElement() {
        listEltsNb++;
        return DiagramFactory.eINSTANCE.createDNodeListElement();
    }

    private DNodeList createList() {
        listsNb++;
        return DiagramFactory.eINSTANCE.createDNodeList();
    }
}
