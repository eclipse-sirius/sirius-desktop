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
package org.eclipse.sirius.tests.unit.diagram.query;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;

import junit.framework.TestCase;

/**
 * Class to test the {@link DDiagramElementQuery} class.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 * 
 */
public class DDiagramElementQueryTest extends TestCase {

    private DNode node;

    private DNode nodeBorderNode;

    private DNode containerBorderNode;

    private DNode containedNode;

    private DNodeContainer container;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        node = createNode();
        nodeBorderNode = createNode();
        containerBorderNode = createNode();
        containedNode = createNode();
        container = createContainer();

        node.getOwnedBorderedNodes().add(nodeBorderNode);
        container.getOwnedBorderedNodes().add(containerBorderNode);
        container.getOwnedDiagramElements().add(containedNode);
    }

    /**
     * Test.
     */
    public void testHidden() {
        addGraphicalFilter(DiagramPackage.eINSTANCE.getHideFilter(), node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsHidden(true, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyHidden(true, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsFiltered(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyFiltered(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsCollapsed(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyCollapsed(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

    }

    /**
     * Test.
     */
    public void testIndirectlyHidden() {
        addGraphicalFilter(DiagramPackage.eINSTANCE.getHideFilter(), node, container, containerBorderNode);

        checkIsHidden(true, node, container, containerBorderNode);
        checkIsHidden(false, nodeBorderNode, containedNode);
        checkIsIndirectlyHidden(true, nodeBorderNode, containedNode, containerBorderNode);

        checkIsFiltered(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyFiltered(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsCollapsed(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyCollapsed(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

    }

    /**
     * Test.
     */
    public void testFiltered() {
        addGraphicalFilter(DiagramPackage.eINSTANCE.getAppliedCompositeFilters(), node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsHidden(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyHidden(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsFiltered(true, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyFiltered(true, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsCollapsed(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyCollapsed(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
    }

    /**
     * Test.
     */
    public void testIndirectlyFiltered() {
        addGraphicalFilter(DiagramPackage.eINSTANCE.getAppliedCompositeFilters(), node, container, containerBorderNode);

        checkIsHidden(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyHidden(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsFiltered(true, node, container, containerBorderNode);
        checkIsFiltered(false, nodeBorderNode, containedNode);
        checkIsIndirectlyFiltered(true, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsCollapsed(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyCollapsed(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
    }

    /**
     * Test.
     */
    public void testCollapsed() {
        addGraphicalFilter(DiagramPackage.eINSTANCE.getCollapseFilter(), node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsHidden(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyHidden(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsFiltered(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyFiltered(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsCollapsed(true, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyCollapsed(true, node, nodeBorderNode, container, containedNode, containerBorderNode);
    }

    /**
     * Test.
     */
    public void testIndirectlyCollapsed() {
        addGraphicalFilter(DiagramPackage.eINSTANCE.getCollapseFilter(), node, container, containerBorderNode);

        checkIsHidden(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyHidden(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsFiltered(false, node, nodeBorderNode, container, containedNode, containerBorderNode);
        checkIsIndirectlyFiltered(false, node, nodeBorderNode, container, containedNode, containerBorderNode);

        checkIsCollapsed(true, node, container, containerBorderNode);
        checkIsCollapsed(false, nodeBorderNode, containedNode);
        checkIsIndirectlyCollapsed(true, node, container, containerBorderNode);
    }

    private void addGraphicalFilter(EClass type, DDiagramElement... elements) {
        for (DDiagramElement element : elements) {
            EObject filter = DiagramFactory.eINSTANCE.create(type);
            assertTrue(filter instanceof GraphicalFilter);
            element.getGraphicalFilters().add((GraphicalFilter) filter);
        }
    }

    private void checkIsHidden(boolean hidden, DDiagramElement... elements) {
        for (DDiagramElement element : elements) {
            DDiagramElementQuery query = new DDiagramElementQuery(element);
            assertEquals(hidden, query.isHidden());
        }
    }

    private void checkIsIndirectlyHidden(boolean hidden, DDiagramElement... elements) {
        for (DDiagramElement element : elements) {
            DDiagramElementQuery query = new DDiagramElementQuery(element);
            assertEquals(hidden, query.isIndirectlyHidden());
        }
    }

    private void checkIsFiltered(boolean filtered, DDiagramElement... elements) {
        for (DDiagramElement element : elements) {
            DDiagramElementQuery query = new DDiagramElementQuery(element);
            assertEquals(filtered, query.isFiltered());
        }
    }

    private void checkIsIndirectlyFiltered(boolean filtered, DDiagramElement... elements) {
        for (DDiagramElement element : elements) {
            DDiagramElementQuery query = new DDiagramElementQuery(element);
            assertEquals(filtered, query.isIndirectlyFiltered());
        }
    }

    private void checkIsCollapsed(boolean collapsed, DDiagramElement... elements) {
        for (DDiagramElement element : elements) {
            DDiagramElementQuery query = new DDiagramElementQuery(element);
            assertEquals(collapsed, query.isCollapsed());
        }
    }

    private void checkIsIndirectlyCollapsed(boolean collapsed, DDiagramElement... elements) {
        for (DDiagramElement element : elements) {
            DDiagramElementQuery query = new DDiagramElementQuery(element);
            assertEquals(collapsed, query.isIndirectlyCollapsed());
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        node = null;
        nodeBorderNode = null;
        containerBorderNode = null;
        containedNode = null;
        container = null;
    }

    private DNode createNode() {
        return DiagramFactory.eINSTANCE.createDNode();
    }

    private DNodeContainer createContainer() {
        return DiagramFactory.eINSTANCE.createDNodeContainer();
    }
}
