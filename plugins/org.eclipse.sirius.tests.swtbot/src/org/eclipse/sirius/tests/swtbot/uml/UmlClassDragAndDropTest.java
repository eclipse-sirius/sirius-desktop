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
package org.eclipse.sirius.tests.swtbot.uml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.geometry.PointAround;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper.EdgeData;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;

/**
 * Class drag an drop tests.
 * 
 * @author dlecan
 */
public class UmlClassDragAndDropTest extends AbstractUmlDragAndDropTest {

    private static final Dimension IDENTITY_TRANSLATION = new Dimension(0, 0);

    private static final String DIAGRAM_NAME = "DnD Class Diagram";

    private static final String REPRESENTATION_NAME = "Class Diagram";

    private static final String IN_CLASS2 = "InClass2";

    private static final String IN_CLASS3 = "InClass3";

    private static final String IN_AND_OUT_CLASS_4 = "InAndOutClass4";

    private static final Rectangle RECT_DRAG_AROUND = new Rectangle(1, 150, 255, 150);

    private static final Rectangle ANOTHER_RECT_DRAG_AROUND = new Rectangle(545, 90, 265, 90);

    private static final Point PT_IN_CLASS_TO_DRAG = new Point(55, 230);

    private static final Point PT_IN_CANVAS_TO_DROP = new Point(605, 150);

    private static final Point PT_IN_CANVAS_TO_DRAG = PT_IN_CANVAS_TO_DROP;

    private static final Point PT_IN_CONTAINER_TO_DROP = new Point(455, 260);

    private static final Point ANOTHER_PT_IN_CONTAINER_WHERE_TO_DROP = new Point(55, 200);

    private static final Point PT_IN_AACLASS_TO_DRAG = new Point(300, 275);

    private class TestData {
        Rectangle bounds1;

        Rectangle bounds2;

        EdgeData bendpointsLocation;

        Dimension translation;
    }

    private TestData testDataInitialState;

    private TestData testDataAfterDnD;

    private TestData testDataAfterUndoDnD;

    private TestData testDataAfterRedoDnD;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        testDataInitialState = new TestData();
        testDataAfterDnD = new TestData();
        testDataAfterUndoDnD = new TestData();
        testDataAfterRedoDnD = new TestData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return REPRESENTATION_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return DIAGRAM_NAME;
    }

    /**
     * Test classes with edge drag and drop from container to canvas.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDragAndDropClassesWithEdgeFromContainerToCanvas() throws Exception {
        dragAndDropClassesWithEdge(PT_IN_CANVAS_TO_DROP);
    }

    /**
     * Validates that drag and dropping a node, from container to canvas, with
     * snap to grid as false and scroll, will drop the node at the proper
     * location.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDragAndDropClassesWithEdgeFromContainerToCanvasWithScroll() throws Exception {
        // Save full screen status.
        final boolean fullScreenStatus = UIThreadRunnable.syncExec(new Result<Boolean>() {
            public Boolean run() {
                return PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell().getFullScreen();
            }
        });
        try {
            // Deactivate full screen to have scroll bars on diagram
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

                public void run() {
                    PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell().setFullScreen(false);
                }
            });

            editor.setSnapToGrid(false);

            // Reveal element to have some scroll
            editor.reveal("CanvasPackageToDrop2");
            editor.reveal("Interface1");

            Rectangle interface1Bounds = getEditPartBounds("Interface1");
            Rectangle canvasPackageToDrop1Bounds = getEditPartBounds("CanvasPackageToDrop1");

            // Drop Interface 1 on the right of its container
            Point dropLocation = canvasPackageToDrop1Bounds.getRight().getTranslated(10, 0).getCopy();
            editor.drag(interface1Bounds.getTopLeft(), dropLocation);

            // Validate drop location
            Assert.assertEquals("The dropped element is not at the expected position", dropLocation, getEditPartBounds("Interface1").getTopLeft());

        } finally {
            // Restore full screen as it was before this test case.
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

                public void run() {
                    PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell().setFullScreen(fullScreenStatus);
                }
            });
        }
    }

    /**
     * Test classes with edge drag and drop from container to another container.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDragAndDropClassesWithEdgeFromContainerToContainer() throws Exception {
        boolean oldWarningCatchActiveValue = isWarningCatchActive();
        setWarningCatchActive(true);
        try {
            dragAndDropClassesWithEdge(PT_IN_CONTAINER_TO_DROP);
            if (doesAWarningOccurs()) {
                fail("This drag'n'drop should be done without warning in Error Log view.");
            }
        } finally {
            setWarningCatchActive(oldWarningCatchActiveValue);
        }
    }

    /**
     * Test classes with edge drag and drop from container to canvas, then undo
     * operation.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDragAndDropClassesWithEdgeFromContainerToCanvasThenUndoThenRedoOperation() throws Exception {
        dragAndDropClassesWithEdge(PT_IN_CANVAS_TO_DROP);
        undoDragAndDrop();
        redoUndoDragAndDrop();
    }

    /**
     * Test classes with edge drag and drop from container to another container,
     * then undo operation.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDragAndDropClassesWithEdgeFromContainerToContainerThenUndoThenRedoOperation() throws Exception {
        dragAndDropClassesWithEdge(PT_IN_CONTAINER_TO_DROP);
        undoDragAndDrop();
        redoUndoDragAndDrop();
    }

    /**
     * Test classes with edge drag and drop from container to canvas, then go
     * back to original position
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDragAndDropClassesWithEdgeFromContainerToCanvasThenGoBack() throws Exception {
        // First D&D
        dragAndDropClassesWithEdge(PT_IN_CANVAS_TO_DROP);

        // Then go back to original position
        dragAndDropClassesWithEdge(ANOTHER_RECT_DRAG_AROUND, PT_IN_CANVAS_TO_DRAG, ANOTHER_PT_IN_CONTAINER_WHERE_TO_DROP);
    }

    /**
     * Check that a specific drap'n'drop is done without error. VP-4078: NPE is
     * thrown when doing a drag'n'drop of an element changes the mapping of its
     * old container.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDragAndDropClassesThatImpliesContainerMappingChanges() throws Exception {
        // Drag the class AAClass outside the package AAPackage. The old
        // container (AAPackage) has now a new mapping and is a new
        // DNodeContainer.
        editor.drag(PT_IN_AACLASS_TO_DRAG, PT_IN_CANVAS_TO_DROP);
        SWTBotUtils.waitAllUiEvents();
        // Check if there is error in errorLog
        if (doesAnErrorOccurs()) {
            fail("This drag'n'drop should be done without error in errorlog.");
        }
    }

    /**
     * Move a class to check the stability of the edge.
     * 
     */
    public void testMoveClass() {
        // Get original bendpoints
        PointList originalDraw2DPoints = getBendpoints(IN_CLASS2, AbstractDiagramListEditPart.class);
        List<Point> originalGmfPointsFromSource = getGMFBendpointsFromSource(IN_CLASS2, AbstractDiagramListEditPart.class);

        // Get the bottom center coordinates of the port
        final Rectangle originalClassBounds = getEditPartBounds(IN_CLASS2);

        // clicked point, drag start
        final Point pointToDrag = originalClassBounds.getTop().getTranslated(0, 3);

        // Select the Class
        editor.select(editor.getEditPart(IN_CLASS2, AbstractDiagramListEditPart.class));
        // Compute the drop destination
        final Point endpoint = pointToDrag.getTranslated(0, 20);
        // Drag'and'drop with the mouse
        editor.drag(pointToDrag.x, pointToDrag.y, endpoint.x, endpoint.y);
        // Get the new bounds and compare with the expected
        final Rectangle newClassBounds = getEditPartBounds(IN_CLASS2);
        assertThat("Class is not at expected position (probably not moved but resized)", newClassBounds.getTopRight(), PointAround.around(originalClassBounds.getTopRight().getTranslated(0, 20), 5));

        if (originalDraw2DPoints != null && originalGmfPointsFromSource != null) {
            // check the stability of the existing edge when moving the
            // border node
            checkEdgeStability(IN_CLASS2, AbstractDiagramListEditPart.class, originalDraw2DPoints, originalGmfPointsFromSource);
        }
    }

    private void dragAndDropClassesWithEdge(final Point dropPoint) {
        dragAndDropClassesWithEdge(RECT_DRAG_AROUND, PT_IN_CLASS_TO_DRAG, dropPoint);
    }

    private void dragAndDropClassesWithEdge(final Rectangle dragAround, final Point pointInSelectedElementsToDrag, final Point dropPoint) {
        // Get positions and sizes before doing drag and drop
        testDataInitialState.bounds1 = getEditPartBounds(IN_CLASS3);
        testDataInitialState.bounds2 = getEditPartBounds(IN_AND_OUT_CLASS_4);
        testDataInitialState.bendpointsLocation = getConnectionBendpointsLocation(IN_AND_OUT_CLASS_4, IN_CLASS3);

        // Select the 2 classes together and the edge between.
        editor.drag(dragAround.getTopLeft().x, dragAround.getTopLeft().y, dragAround.getBottomRight().x, dragAround.getBottomRight().y);

        editor.drag(pointInSelectedElementsToDrag.x, pointInSelectedElementsToDrag.y, dropPoint.x, dropPoint.y);
        testDataInitialState.translation = dropPoint.getDifference(pointInSelectedElementsToDrag);

        // Get positions and sizes after doing drag and drop
        testDataAfterDnD.bounds1 = getEditPartBounds(IN_CLASS3);
        testDataAfterDnD.bounds2 = getEditPartBounds(IN_AND_OUT_CLASS_4);
        testDataAfterDnD.bendpointsLocation = getConnectionBendpointsLocation(IN_AND_OUT_CLASS_4, IN_CLASS3);

        checkNewCoordinates(IN_CLASS3, testDataInitialState.bounds1, testDataAfterDnD.bounds1, testDataInitialState.translation);

        checkNewCoordinates(IN_AND_OUT_CLASS_4, testDataInitialState.bounds2, testDataAfterDnD.bounds2, testDataInitialState.translation);

        checkConstantGapBetween(IN_CLASS3, testDataInitialState.bounds1.getTopLeft(), testDataAfterDnD.bounds1.getTopLeft(), IN_AND_OUT_CLASS_4, testDataInitialState.bounds2.getTopLeft(),
                testDataAfterDnD.bounds2.getTopLeft());

        checkEdgeNewStyle(testDataInitialState.bendpointsLocation, testDataAfterDnD.bendpointsLocation, testDataInitialState.translation);
    }

    private void undoDragAndDrop() {
        final SWTBotMenu editMenu = new SWTWorkbenchBot().menu("Edit");
        editMenu.menu("Undo Drop Elements").click();

        // Get positions and sizes after undoing drag and drop
        testDataAfterUndoDnD.bounds1 = getEditPartBounds(IN_CLASS3);
        testDataAfterUndoDnD.bounds2 = getEditPartBounds(IN_AND_OUT_CLASS_4);
        testDataAfterUndoDnD.bendpointsLocation = getConnectionBendpointsLocation(IN_AND_OUT_CLASS_4, IN_CLASS3);

        checkConstantGapBetween(IN_CLASS3, testDataInitialState.bounds1.getTopLeft(), testDataAfterUndoDnD.bounds1.getTopLeft(), IN_AND_OUT_CLASS_4, testDataInitialState.bounds2.getTopLeft(),
                testDataAfterUndoDnD.bounds2.getTopLeft());

        assertThat("Initial position of " + IN_CLASS3 + " has changed", testDataAfterUndoDnD.bounds1, equalTo(testDataInitialState.bounds1));
        assertThat("Initial position of " + IN_AND_OUT_CLASS_4 + " has changed", testDataAfterUndoDnD.bounds2, equalTo(testDataInitialState.bounds2));
        checkEdgeNewStyle(testDataAfterUndoDnD.bendpointsLocation, testDataInitialState.bendpointsLocation, IDENTITY_TRANSLATION);
    }

    private void redoUndoDragAndDrop() {
        new SWTWorkbenchBot().menu("Edit").menu("Redo Drop Elements").click();

        // Get positions and sizes after undoing drag and drop
        testDataAfterRedoDnD.bounds1 = getEditPartBounds(IN_CLASS3);
        testDataAfterRedoDnD.bounds2 = getEditPartBounds(IN_AND_OUT_CLASS_4);
        testDataAfterRedoDnD.bendpointsLocation = getConnectionBendpointsLocation(IN_AND_OUT_CLASS_4, IN_CLASS3);

        checkEdgeNewStyle(testDataAfterUndoDnD.bendpointsLocation, testDataAfterRedoDnD.bendpointsLocation, testDataInitialState.translation);

        checkNewCoordinates(IN_CLASS3, testDataAfterUndoDnD.bounds1, testDataAfterRedoDnD.bounds1, testDataInitialState.translation);

        checkNewCoordinates(IN_AND_OUT_CLASS_4, testDataAfterUndoDnD.bounds2, testDataAfterRedoDnD.bounds2, testDataInitialState.translation);

        checkConstantGapBetween(IN_CLASS3, testDataAfterUndoDnD.bounds1.getTopLeft(), testDataAfterRedoDnD.bounds1.getTopLeft(), IN_AND_OUT_CLASS_4, testDataAfterUndoDnD.bounds2.getTopLeft(),
                testDataAfterRedoDnD.bounds2.getTopLeft());
    }

    private EdgeData getConnectionBendpointsLocation(final String sourceLabel, final String targetLabel) {
        final List<EdgeData> connectionEditParts = SWTBotCommonHelper.getEdgeData(getUmlEditPart(sourceLabel), getUmlEditPart(targetLabel), editor);
        assertThat(String.format("Wrong number of connections between %s and %s", sourceLabel, targetLabel), connectionEditParts.size(), equalTo(1));

        return connectionEditParts.get(0);
    }

    private void checkEdgeNewStyle(final EdgeData originalBendpoints, final EdgeData newBendpoints, final Dimension translation) {
        assertThat("Number of edge routing constrainsts has changed", newBendpoints.getPoints().size(), equalTo(originalBendpoints.getPoints().size()));

        GraphicTestsSupportHelp.assertEquals("Relative source location has changed (delta: " + TEMPORARY_DELTA + ")", originalBendpoints.getSource(),
                newBendpoints.getSource().translate(translation.getNegated()), TEMPORARY_DELTA);
        GraphicTestsSupportHelp.assertEquals("Relative target location has changed (delta: " + TEMPORARY_DELTA + ")", originalBendpoints.getTarget(),
                newBendpoints.getTarget().translate(translation.getNegated()), TEMPORARY_DELTA);

        for (int i = 0; i < newBendpoints.getPoints().size(); i++) {
            final Point originalBendpoint = originalBendpoints.getPoints().getPoint(i);
            final Point newBendpoint = newBendpoints.getPoints().getPoint(i);

            GraphicTestsSupportHelp.assertEquals("Relative bendpoint location has changed", originalBendpoint, newBendpoint.translate(translation.getNegated()), TEMPORARY_DELTA);
        }
    }
}
