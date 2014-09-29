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

public class ContainerFoldingTest extends AbstractFoldingTest {

    public void test_folding_on_intermediate_container_source_of_source_folding_style_edges() {
        openDiagram("Root With Containers Source All Expanded");
        assertContainerIsVisible("P1A1");

        assertContainerIsVisible("P1A1A");
        assertContainerIsVisible("P1A1B");
        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");

        foldContainer("P1A1");

        assertContainerIsVisible("P1A1");
        assertContainerIsNotVisible("P1A1A");
        assertContainerIsNotVisible("P1A1B");
        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");
    }

    public void test_folding_unfolding_on_intermediate_container_source_of_source_folding_style_edges() {
        test_folding_on_intermediate_container_source_of_source_folding_style_edges();

        unfoldContainer("P1A1");

        assertContainerIsVisible("P1A1");
        assertContainerIsVisible("P1A1A");
        assertContainerIsVisible("P1A1B");
        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");
    }

    public void test_folding_unfolding_multiple_levels_container_source_of_source_folding_style_edges() {
        test_folding_on_intermediate_container_source_of_source_folding_style_edges();

        foldContainer("P1A");

        assertContainerIsNotVisible("P1A1A");
        assertContainerIsNotVisible("P1A1B");
        assertContainerIsNotVisible("P1A1");
        assertContainerIsNotVisible("P1A2");
        assertContainerIsNotVisible("P1A3");
        assertContainerIsVisible("P1A");

        unfoldContainer("P1A");

        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A1");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");
        assertContainerIsNotVisible("P1A1A");
        assertContainerIsNotVisible("P1A1B");

        unfoldContainer("P1A1");

        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A1");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");
        assertContainerIsVisible("P1A1A");
        assertContainerIsVisible("P1A1B");
    }

    public void test_folding_on_intermediate_container_target_of_target_folding_style_edges() {
        openDiagram("Root With Containers Target All Expanded");
        assertContainerIsVisible("P1A1");

        assertContainerIsVisible("P1A1A");
        assertContainerIsVisible("P1A1B");
        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");

        foldContainer("P1A1");

        assertContainerIsVisible("P1A1");
        assertContainerIsNotVisible("P1A1A");
        assertContainerIsNotVisible("P1A1B");
        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");
    }

    public void test_folding_unfolding_on_intermediate_container_target_of_target_folding_style_edges() {
        test_folding_on_intermediate_container_target_of_target_folding_style_edges();

        unfoldContainer("P1A1");

        assertContainerIsVisible("P1A1");
        assertContainerIsVisible("P1A1A");
        assertContainerIsVisible("P1A1B");
        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");
    }

    public void test_folding_unfolding_multiple_levels_container_target_of_target_folding_style_edges() {
        test_folding_on_intermediate_container_target_of_target_folding_style_edges();

        foldContainer("P1A");

        assertContainerIsNotVisible("P1A1A");
        assertContainerIsNotVisible("P1A1B");
        assertContainerIsNotVisible("P1A1");
        assertContainerIsNotVisible("P1A2");
        assertContainerIsNotVisible("P1A3");
        assertContainerIsVisible("P1A");

        unfoldContainer("P1A");

        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A1");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");
        assertContainerIsNotVisible("P1A1A");
        assertContainerIsNotVisible("P1A1B");

        unfoldContainer("P1A1");

        assertContainerIsVisible("P1A");
        assertContainerIsVisible("P1A1");
        assertContainerIsVisible("P1A2");
        assertContainerIsVisible("P1A3");
        assertContainerIsVisible("P1A1A");
        assertContainerIsVisible("P1A1B");
    }

    public void test_unfolding_explicitly_hidden_container_with_source_folding_style_edges() {
        openDiagram("Root With Containers Source All Expanded");
        TestsUtil.synchronizationWithUIThread();
        HideDDiagramElementAction hideAction = new HideDDiagramElementAction();

        // Hide P1A1A explicitly
        assertContainerIsVisible("P1A1A");
        diagramEditor.getDiagramGraphicalViewer().select(getEditPart(getNodeContainer("P1A1A")));
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();
        assertContainerIsNotVisible("P1A1A");

        // Fold its "parent"
        foldContainer("P1A1");

        // Make sure it is still not visible
        assertContainerIsNotVisible("P1A1A");

        // Unfold the parent
        unfoldContainer("P1A1");

        // Make sure it is still not visible. It is unfolded but still
        // explicitly hidden.
        assertContainerIsNotVisible("P1A1A");
    }

    public void test_unfolding_explicitly_hidden_container_with_target_folding_style_edges() {
        openDiagram("Root With Containers Target All Expanded");
        TestsUtil.synchronizationWithUIThread();
        HideDDiagramElementAction hideAction = new HideDDiagramElementAction();

        // Hide P1A1A explicitly
        assertContainerIsVisible("P1A1A");
        diagramEditor.getDiagramGraphicalViewer().select(getEditPart(getNodeContainer("P1A1A")));
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();
        assertContainerIsNotVisible("P1A1A");

        // Fold its "parent"
        foldContainer("P1A1");

        // Make sure it is still not visible
        assertContainerIsNotVisible("P1A1A");

        // Unfold the parent
        unfoldContainer("P1A1");

        // Make sure it is still not visible. It is unfolded but still
        // explicitly hidden.
        assertContainerIsNotVisible("P1A1A");
    }
}
