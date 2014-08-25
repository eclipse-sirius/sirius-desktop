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

public class FoldingPointsIdentificationTest extends AbstractFoldingTest {

    public void test_Root_With_Containers_Source_All_Expanded() {
        openDiagram("Root With Containers Source All Expanded");
        assertContainerIsFoldingPoint("P1");
        assertContainerIsNotFoldingPoint("P2");
        assertContainerIsNotFoldingPoint("P3");

        assertContainerIsFoldingPoint("P1A");
        assertContainerIsFoldingPoint("P1A1");
        assertContainerIsNotFoldingPoint("P1A2");
        assertContainerIsNotFoldingPoint("P1A3");
        assertContainerIsNotFoldingPoint("P1A1A");
        assertContainerIsNotFoldingPoint("P1A1B");

        assertContainerIsFoldingPoint("P1B");
        assertContainerIsNotFoldingPoint("P1B1");
        assertContainerIsNotFoldingPoint("P1B2");
    }

    public void test_Root_With_Containers_Target_All_Expanded() {
        openDiagram("Root With Containers Target All Expanded");
        assertContainerIsFoldingPoint("P1");
        assertContainerIsNotFoldingPoint("P2");
        assertContainerIsNotFoldingPoint("P3");

        assertContainerIsFoldingPoint("P1A");
        assertContainerIsFoldingPoint("P1A1");
        assertContainerIsNotFoldingPoint("P1A2");
        assertContainerIsNotFoldingPoint("P1A3");
        assertContainerIsNotFoldingPoint("P1A1A");
        assertContainerIsNotFoldingPoint("P1A1B");

        assertContainerIsFoldingPoint("P1B");
        assertContainerIsNotFoldingPoint("P1B1");
        assertContainerIsNotFoldingPoint("P1B2");
    }

    public void test_Root_With_Nodes_Source_All_Expanded() {
        openDiagram("Root With Nodes Source All Expanded");
        assertNodeIsFoldingPoint("P1");
        assertNodeIsNotFoldingPoint("P2");
        assertNodeIsNotFoldingPoint("P3");

        assertNodeIsFoldingPoint("P1A");
        assertNodeIsFoldingPoint("P1A1");
        assertNodeIsNotFoldingPoint("P1A2");
        assertNodeIsNotFoldingPoint("P1A3");
        assertNodeIsNotFoldingPoint("P1A1A");
        assertNodeIsNotFoldingPoint("P1A1B");

        assertNodeIsFoldingPoint("P1B");
        assertNodeIsNotFoldingPoint("P1B1");
        assertNodeIsNotFoldingPoint("P1B2");
    }

    public void test_Root_With_Nodes_Target_All_Expanded() {
        openDiagram("Root With Nodes Target All Expanded");
        assertNodeIsFoldingPoint("P1");
        assertNodeIsNotFoldingPoint("P2");
        assertNodeIsNotFoldingPoint("P3");

        assertNodeIsFoldingPoint("P1A");
        assertNodeIsFoldingPoint("P1A1");
        assertNodeIsNotFoldingPoint("P1A2");
        assertNodeIsNotFoldingPoint("P1A3");
        assertNodeIsNotFoldingPoint("P1A1A");
        assertNodeIsNotFoldingPoint("P1A1B");

        assertNodeIsFoldingPoint("P1B");
        assertNodeIsNotFoldingPoint("P1B1");
        assertNodeIsNotFoldingPoint("P1B2");
    }
}
