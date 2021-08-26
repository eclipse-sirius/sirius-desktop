/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.compartment;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * Test compartments detection.
 * 
 * @author mporhel
 *
 */
public class CompartmentsTest extends SiriusDiagramTestCase implements ICompartmentTests {

    private DDiagram diagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, SESSION_PATH);
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        super.tearDown();
    }

    /**
     * Ensure that the different queries and helper methods correctly detect horizontal RegionContainers and Regions.
     */
    public void testHorizontalStackDetection() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestStackDetection(ContainerLayout.HORIZONTAL_STACK);
    }

    /**
     * Ensure that the different queries and helper methods correctly detect vertical RegionContainers and Regions.
     */
    public void testVerticalStackDetection() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestStackDetection(ContainerLayout.VERTICAL_STACK);
    }

    private void doTestStackDetection(ContainerLayout stackDirection) {
        checkQueries(FIRST_REGION_CONTAINER_NAME, true, false, stackDirection, false);
        checkQueries(LEFT_CLASS_NAME, false, true, stackDirection, true);
        checkQueries(CENTER_CLASS_NAME, false, true, stackDirection, true);
        checkQueries(RIGHT_CLASS_NAME, false, true, stackDirection, true);
        checkQueries(LEFT_PKG_NAME, false, true, stackDirection, false);
        checkQueries(CENTER_PKG_NAME, false, true, stackDirection, false);
        checkQueries(RIGHT_PKG_NAME, false, true, stackDirection, false);
    }

    private void checkQueries(String label, boolean isRegionContainer, boolean isRegion, ContainerLayout stackDirection, boolean isList) {
        DDiagramElementContainer ddec = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(ddec);

        assertFalse("An element cannot be a region container and a list.", isRegionContainer && isList);
        assertTrue(isList && ddec instanceof DNodeList || ddec instanceof DNodeContainer);
        assertTrue(isList && editPart instanceof IDiagramListEditPart || editPart instanceof IDiagramContainerEditPart);

        // Check RegionContainer queries
        ContainerMappingQuery cmQuery = new ContainerMappingQuery(ddec.getActualMapping());
        assertEquals(isRegionContainer, cmQuery.isRegionContainer());
        assertEquals(isRegionContainer, !isList && new DNodeContainerExperimentalQuery((DNodeContainer) ddec).isRegionContainer());
        assertEquals(isRegionContainer, !isList && ((AbstractDiagramContainerEditPart) editPart).isRegionContainer());
        if (isRegionContainer) {
            assertFalse(cmQuery.isFreeFormContainer());
            assertFalse(cmQuery.isListContainer());
            assertEquals(ddec.getActualMapping().getChildrenPresentation() == ContainerLayout.VERTICAL_STACK, cmQuery.isVerticalStackContainer());
            assertEquals(ddec.getActualMapping().getChildrenPresentation() == ContainerLayout.HORIZONTAL_STACK, cmQuery.isHorizontalStackContainer());
        } else {
            assertTrue(!isList && cmQuery.isFreeFormContainer() || isList && cmQuery.isListContainer());
            assertFalse(cmQuery.isVerticalStackContainer() || cmQuery.isHorizontalStackContainer());
        }

        // Check Region queries
        assertEquals(isRegion, cmQuery.isRegion());
        assertEquals(isRegion, new DDiagramElementContainerExperimentalQuery(ddec).isRegion());
        assertEquals(isRegion, editPart.isRegion());
        assertEquals(getExpectedParentStackDirection(isRegion, stackDirection), editPart.getParentStackDirection());
    }

    private int getExpectedParentStackDirection(boolean isRegion, ContainerLayout stackDirection) {
        int expectedParentStackDirection = PositionConstants.NONE;
        if (isRegion) {
            if (ContainerLayout.VERTICAL_STACK == stackDirection) {
                expectedParentStackDirection = PositionConstants.NORTH_SOUTH;
            } else if (ContainerLayout.HORIZONTAL_STACK == stackDirection) {
                expectedParentStackDirection = PositionConstants.EAST_WEST;
            }
        }
        return expectedParentStackDirection;
    }
}
