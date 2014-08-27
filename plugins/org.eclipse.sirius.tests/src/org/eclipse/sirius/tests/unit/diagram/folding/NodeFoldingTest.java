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

import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.tests.support.api.TestsUtil;

public class NodeFoldingTest extends AbstractFoldingTest {
    public void test_folding_on_intermediate_node_source_of_source_folding_style_edges() {
        openDiagram("Root With Nodes Source All Expanded");
        assertNodeIsVisible("P1A1");

        assertNodeIsVisible("P1A1A");
        assertNodeIsVisible("P1A1B");
        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");

        foldNode("P1A1");

        assertNodeIsVisible("P1A1");
        assertNodeIsNotVisible("P1A1A");
        assertNodeIsNotVisible("P1A1B");
        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");
    }

    public void test_folding_unfolding_on_intermediate_node_source_of_source_folding_style_edges() {
        test_folding_on_intermediate_node_source_of_source_folding_style_edges();

        unfoldNode("P1A1");

        assertNodeIsVisible("P1A1");
        assertNodeIsVisible("P1A1A");
        assertNodeIsVisible("P1A1B");
        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");
    }

    public void test_folding_unfolding_multiple_levels_node_source_of_source_folding_style_edges() {
        test_folding_on_intermediate_node_source_of_source_folding_style_edges();

        foldNode("P1A");

        assertNodeIsNotVisible("P1A1A");
        assertNodeIsNotVisible("P1A1B");
        assertNodeIsNotVisible("P1A1");
        assertNodeIsNotVisible("P1A2");
        assertNodeIsNotVisible("P1A3");
        assertNodeIsVisible("P1A");

        unfoldNode("P1A");

        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A1");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");
        assertNodeIsNotVisible("P1A1A");
        assertNodeIsNotVisible("P1A1B");

        unfoldNode("P1A1");

        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A1");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");
        assertNodeIsVisible("P1A1A");
        assertNodeIsVisible("P1A1B");
    }

    public void test_folding_on_intermediate_node_target_of_target_folding_style_edges() {
        openDiagram("Root With Nodes Target All Expanded");
        assertNodeIsVisible("P1A1");

        assertNodeIsVisible("P1A1A");
        assertNodeIsVisible("P1A1B");
        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");

        foldNode("P1A1");

        assertNodeIsVisible("P1A1");
        assertNodeIsNotVisible("P1A1A");
        assertNodeIsNotVisible("P1A1B");
        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");
    }

    public void test_folding_unfolding_on_intermediate_container_target_of_target_folding_style_edges() {
        test_folding_on_intermediate_node_target_of_target_folding_style_edges();

        unfoldNode("P1A1");

        assertNodeIsVisible("P1A1");
        assertNodeIsVisible("P1A1A");
        assertNodeIsVisible("P1A1B");
        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");
    }

    public void test_folding_unfolding_multiple_levels_node_target_of_target_folding_style_edges() {
        test_folding_on_intermediate_node_target_of_target_folding_style_edges();

        foldNode("P1A");

        assertNodeIsNotVisible("P1A1A");
        assertNodeIsNotVisible("P1A1B");
        assertNodeIsNotVisible("P1A1");
        assertNodeIsNotVisible("P1A2");
        assertNodeIsNotVisible("P1A3");
        assertNodeIsVisible("P1A");

        unfoldNode("P1A");

        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A1");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");
        assertNodeIsNotVisible("P1A1A");
        assertNodeIsNotVisible("P1A1B");

        unfoldNode("P1A1");

        assertNodeIsVisible("P1A");
        assertNodeIsVisible("P1A1");
        assertNodeIsVisible("P1A2");
        assertNodeIsVisible("P1A3");
        assertNodeIsVisible("P1A1A");
        assertNodeIsVisible("P1A1B");
    }

    public void test_unfolding_explicitly_hidden_node_with_source_folding_style_edges() {
        openDiagram("Root With Nodes Source All Expanded");
        TestsUtil.synchronizationWithUIThread();
        HideDDiagramElementAction hideAction = new HideDDiagramElementAction();

        // Hide P1A1A explicitly
        assertNodeIsVisible("P1A1A");
        diagramEditor.getDiagramGraphicalViewer().select(getEditPart(getNode("P1A1A")));
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();
        
        assertNodeIsNotVisible("P1A1A");

        // Fold its "parent"
        foldNode("P1A1");

        // Make sure it is still not visible
        assertNodeIsNotVisible("P1A1A");

        // Unfold the parent
        unfoldNode("P1A1");

        // Make sure it is still not visible. It is unfolded but still
        // explicitly hidden.
        assertNodeIsNotVisible("P1A1A");
    }

    public void test_unfolding_explicitly_hidden_node_with_target_folding_style_edges() {
        openDiagram("Root With Nodes Target All Expanded");
        TestsUtil.synchronizationWithUIThread();
        HideDDiagramElementAction hideAction = new HideDDiagramElementAction();

        // Hide P1A1A explicitly
        assertNodeIsVisible("P1A1A");
        diagramEditor.getDiagramGraphicalViewer().select(getEditPart(getNode("P1A1A")));
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();
        assertNodeIsNotVisible("P1A1A");

        // Fold its "parent"
        foldNode("P1A1");

        // Make sure it is still not visible
        assertNodeIsNotVisible("P1A1A");

        // Unfold the parent
        unfoldNode("P1A1");

        // Make sure it is still not visible. It is unfolded but still
        // explicitly hidden.
        assertNodeIsNotVisible("P1A1A");
    }
}
