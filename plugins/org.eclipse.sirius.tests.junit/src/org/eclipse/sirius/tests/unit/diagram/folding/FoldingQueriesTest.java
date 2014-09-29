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
package org.eclipse.sirius.tests.unit.diagram.folding;

import org.eclipse.sirius.diagram.ui.business.internal.query.EdgeTargetQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.EdgeTargetQuery.FoldingState;

import com.google.common.collect.Iterables;

/**
 * Test cases for queries relative to edge folding.
 * 
 * @author pcdavid
 */
public class FoldingQueriesTest extends AbstractFoldingTest {
    public void test_container_with_no_edges_has_no_foldable_edges() {
        openDiagram("P1A1 With Containers Source All Expanded");
        EdgeTargetQuery q = new EdgeTargetQuery(getNodeContainer("P1A1A"));
        assertTrue(Iterables.isEmpty(q.getAllFoldableEdges()));
    }

    public void test_container_with_no_edges_is_considered_unfolded() {
        openDiagram("P1A1 With Containers Source All Expanded");
        EdgeTargetQuery q = new EdgeTargetQuery(getNodeContainer("P1A1A"));
        assertEquals(FoldingState.UNFOLDED, q.getFoldingState());
    }

    public void test_node_with_no_edges_has_no_foldable_edges() {
        openDiagram("P1A1 With Nodes Source All Expanded");
        EdgeTargetQuery q = new EdgeTargetQuery(getNode("P1A1A"));
        assertTrue(Iterables.isEmpty(q.getAllFoldableEdges()));
    }

    public void test_node_with_no_edges_is_considered_unfolded() {
        openDiagram("P1A1 With Nodes Source All Expanded");
        EdgeTargetQuery q = new EdgeTargetQuery(getNode("P1A1A"));
        assertEquals(FoldingState.UNFOLDED, q.getFoldingState());
    }

    public void test_container_source_of_source_folding_style_edge_has_foldable_edges() {
        openDiagram("P1 With Containers Source All Expanded");
        EdgeTargetQuery q = new EdgeTargetQuery(getNodeContainer("P1A1"));
        assertEquals(2, Iterables.size(q.getAllFoldableEdges()));

        q = new EdgeTargetQuery(getNodeContainer("P1A"));
        assertEquals(3, Iterables.size(q.getAllFoldableEdges()));
    }

    public void test_container_target_of_source_folding_style_edge_has_no_foldable_edges() {
        openDiagram("P1 With Containers Source All Expanded");
        EdgeTargetQuery q = new EdgeTargetQuery(getNodeContainer("P1A1A"));
        assertEquals(0, Iterables.size(q.getAllFoldableEdges()));
    }

    public void test_container_target_of_target_folding_style_edge_has_foldable_edges() {
        openDiagram("P1 With Containers Target All Expanded");
        EdgeTargetQuery q = new EdgeTargetQuery(getNodeContainer("P1A1"));
        assertEquals(2, Iterables.size(q.getAllFoldableEdges()));

        q = new EdgeTargetQuery(getNodeContainer("P1A"));
        assertEquals(3, Iterables.size(q.getAllFoldableEdges()));
    }

    public void test_container_source_of_target_folding_style_edge_has_no_foldable_edges() {
        openDiagram("P1 With Containers Target All Expanded");
        EdgeTargetQuery q = new EdgeTargetQuery(getNodeContainer("P1A1A"));
        assertEquals(0, Iterables.size(q.getAllFoldableEdges()));
    }
}
