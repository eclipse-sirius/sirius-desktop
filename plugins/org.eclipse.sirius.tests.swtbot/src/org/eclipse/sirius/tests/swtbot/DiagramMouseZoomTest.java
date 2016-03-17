/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import static org.junit.Assert.assertNotEquals;

import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramRootEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckDiagramSelected;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Tests that the zoom started by CTRL+Mouse wheel on a diagram is correct
 * regarding its specification.
 * 
 * The diagram point behind the mouse location must stay at the same position in
 * the viewport after zoom when no diagram boundaries are inside the viewport
 * after zoom.
 * 
 * If boundaries are visible after zoom, then the diagram point behind mouse
 * location will be shifted regarding the zoom level and viewport location from
 * some pixel on x or y axis or both because diagram cannot be extend during
 * zoom.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DiagramMouseZoomTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String FILE_DIR = "/";

    private static final String DIAGRAM_INSTANCE_NAME = "zoomMouseDiagram";

    private static final String DIAGRAM_DESCRIPTION = "Diagram";

    private static final String MODEL_FILE = "mouseZoomTest.ecore";

    private static final String SESSION_FILE = "mouseZoomTest.aird";

    private static final String VSM_FILE = "VSMForMouseZoomTest.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/mouseZoom/";

    private SWTBotSiriusDiagramEditor editor;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Restore the default zoom level
        editor.click(0, 0); // Set the focus on the diagram
        editor.zoom(ZoomLevel.ZOOM_100);
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    private void openDiagram(String descriptionName, String instanceName, ZoomLevel zoomLevel) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), descriptionName, instanceName, DDiagram.class);
        editor.zoom(zoomLevel);
        SWTBotUtils.waitAllUiEvents();
    }

    private enum BoundariesVisible {
        NONE, ZOOMOUT, ZOOMIN
    }

    /**
     * Assert that the viewport is shifted to the right location when doing a
     * zoom with CTRL+MouseScroll when the mouse is over the origin point of the
     * given part.
     * 
     * @param editPart
     *            the edit part from which we will apply a mouse zoom on its
     *            origin.
     * @param targetZoomLevel
     *            The final zoom to apply.
     * @param originalZoomLevel
     *            the zoom level before zooming.
     * @param zoomIncrement
     *            the zoom increment leading to the wanted zoom.
     * @param scrollX
     *            the x coordinate to scroll to the diagram.
     * @param scrollY
     *            the y coordinate to scroll to the diagram.
     * @param boundariesVisible
     *            If {@link BoundariesVisible#NONE}, then checks the viewport is
     *            right positioned with the zoom point at the same place after
     *            zoom. If {@link BoundariesVisible#ZOOMOUT}, then checks the
     *            viewport is at (0,0) position. And the zoomed point has been
     *            shifted regarding the zoom on the lower and right direction.
     *            If {@link BoundariesVisible#ZOOMIN}, then checks the viewport
     *            is at the lowest and rightmost position. And the zoomed point
     *            has been shifted regarding the zoom on a lower and rightmost
     *            direction.
     */
    private void assertViewportShiftedRightForMouseZoom(SWTBotGefEditPart editPart, ZoomLevel originalZoomLevel, ZoomLevel targetZoomLevel, int zoomIncrement, int scrollX, int scrollY,
            BoundariesVisible boundariesVisible) {
        ICondition condition = new CheckDiagramSelected(editor);
        editor.click(new Point(0, 0));
        bot.waitUntil(condition);
        DiagramEditPart diagramPart = (DiagramEditPart) ((IStructuredSelection) editor.getSelection()).getFirstElement();
        DDiagramRootEditPart parentDiagram = (DDiagramRootEditPart) diagramPart.getParent();
        editor.scrollTo(scrollX, scrollY);
        SWTBotUtils.waitAllUiEvents();

        IGraphicalEditPart zoomTargetPart = (IGraphicalEditPart) editPart.part();
        Point mouseZoomAbsoluteLocationBeforeZoom = zoomTargetPart.getFigure().getBounds().getCopy().getLocation();
        zoomTargetPart.getFigure().translateToAbsolute(mouseZoomAbsoluteLocationBeforeZoom);

        assertNotEquals(new Rectangle(0, 0, 0, 0), zoomTargetPart);

        // We do the zoom algorithm part that computes the expected viewport
        // location after zoom to have the expected viewport location that we
        // know is working because of manual test.
        ScalableFigure scalableFigure = parentDiagram.getZoomManager().getScalableFigure();
        Point expectedMouseRelativeLocation = new Point(mouseZoomAbsoluteLocationBeforeZoom);
        scalableFigure.translateToRelative(expectedMouseRelativeLocation);
        Point originalViewLocation = diagramPart.getViewport().getViewLocation();
        Dimension difference = originalViewLocation.getDifference(new Point(expectedMouseRelativeLocation.x, expectedMouseRelativeLocation.y));
        Point scaledPoint = new Point(expectedMouseRelativeLocation.x, expectedMouseRelativeLocation.y).scale(targetZoomLevel.getAmount() / originalZoomLevel.getAmount());
        Point expectedViewportOriginPoint = scaledPoint.getTranslated(difference);

        editor.mouseScrollWithKey(mouseZoomAbsoluteLocationBeforeZoom.x, mouseZoomAbsoluteLocationBeforeZoom.y, SWT.CTRL, zoomIncrement);
        SWTBotUtils.waitAllUiEvents();

        Point newViewLocation = diagramPart.getViewport().getViewLocation();
        if (BoundariesVisible.NONE == boundariesVisible) {
            assertEquals("viewport after zoom has not been placed at the right position on the x axis.", expectedViewportOriginPoint.x, newViewLocation.x, 1);
            assertEquals("viewport after zoom has not been placed at the right position on the y axis.", expectedViewportOriginPoint.y, newViewLocation.y, 1);
        } else if (BoundariesVisible.ZOOMOUT == boundariesVisible) {
            assertEquals("viewport after zoom has not been placed at the right position on the x axis.", 0, newViewLocation.x, 1);
            assertEquals("viewport after zoom has not been placed at the right position on the y axis.", 0, newViewLocation.y, 1);
        } else if (BoundariesVisible.ZOOMIN == boundariesVisible) {
            assertEquals("viewport after zoom has not been placed at the rightest and lowest position on the x axis.", diagramPart.getViewport().getHorizontalRangeModel().getMaximum(),
                    newViewLocation.x + diagramPart.getViewport().getHorizontalRangeModel().getExtent(), 1);
            assertEquals("viewport after zoom has not been placed at the rightest and lowest position on the y axis.", diagramPart.getViewport().getVerticalRangeModel().getMaximum(),
                    newViewLocation.y + diagramPart.getViewport().getVerticalRangeModel().getExtent(), 1);
        } else {
            throw new UnsupportedOperationException("nothing is tested.");
        }
        Rectangle zoomTargetAbsoluteBoundsAfterZoom = zoomTargetPart.getFigure().getBounds().getCopy();
        zoomTargetPart.getFigure().translateToAbsolute(zoomTargetAbsoluteBoundsAfterZoom);
        if (BoundariesVisible.NONE == boundariesVisible) {
            assertEquals("The point behind the mouse is not the same one after zoom than before. x axis does not match.", mouseZoomAbsoluteLocationBeforeZoom.x, zoomTargetAbsoluteBoundsAfterZoom.x,
                    1);
            assertEquals("The point behind the mouse is not the same one after zoom than before. y axis does not match.", mouseZoomAbsoluteLocationBeforeZoom.y, zoomTargetAbsoluteBoundsAfterZoom.y,
                    1);
        } else if (BoundariesVisible.ZOOMIN == boundariesVisible) {
            assertEquals("The point behind the mouse is the same one after zoom than before. Whereas it should be shifted regarding the zoom.",
                    mouseZoomAbsoluteLocationBeforeZoom.x - difference.width, zoomTargetAbsoluteBoundsAfterZoom.x, 1);
            assertEquals("The point behind the mouse is the same one after zoom than before. Whereas it should not.", mouseZoomAbsoluteLocationBeforeZoom.y - difference.height,
                    zoomTargetAbsoluteBoundsAfterZoom.y, 1);
        } else if (BoundariesVisible.ZOOMOUT == boundariesVisible) {
            assertEquals("The point behind the mouse is the same one after zoom than before. Whereas it should be shifted regarding the zoom.",
                    mouseZoomAbsoluteLocationBeforeZoom.x
                            + (expectedViewportOriginPoint.x + (diagramPart.getViewport().getHorizontalRangeModel().getMaximum() - diagramPart.getViewport().getHorizontalRangeModel().getExtent())),
                    zoomTargetAbsoluteBoundsAfterZoom.x, 1);
            assertEquals("The point behind the mouse is the same one after zoom than before. Whereas it should not.",
                    mouseZoomAbsoluteLocationBeforeZoom.y
                            + (expectedViewportOriginPoint.y - (diagramPart.getViewport().getVerticalRangeModel().getMaximum() - diagramPart.getViewport().getVerticalRangeModel().getExtent())),
                    zoomTargetAbsoluteBoundsAfterZoom.y, 1);
        } else {
            throw new UnsupportedOperationException("nothing is tested.");
        }
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom when
     * a zoom in from 100% to 125% is done.
     * 
     * The zoomed point is in the left upper corner.
     * 
     * No diagram boundaries are visible after zoom so the viewport must keep
     * the point behind the mouse to the same location on it after zoom on x and
     * y axis.
     * 
     * Original scroll is (0,0).
     */
    public void testZoomInWithMouseWithoutBoundaryVisibleAfterZoomOnViewport() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_100);
        SWTBotGefEditPart editPart = editor.getEditPart("A");

        assertViewportShiftedRightForMouseZoom(editPart, ZoomLevel.ZOOM_100, ZoomLevel.ZOOM_125, 2, 0, 0, BoundariesVisible.NONE);

    }

    /**
     * Tests that the viewport is shifted to the right position after zoom when
     * a zoom in from 50% to 75% is done.
     * 
     * The zoomed point is in the left upper corner.
     * 
     * Upper and left diagram boundaries are visible after zoom so the viewport
     * will not keep the point behind the mouse to the same location on it after
     * zoom on x and y axis. And Viewport will not move its origin because it is
     * blocked by boundaries.
     * 
     * Original scroll is (0,0).
     */
    public void testZoomInWithMouseWithLeftAndUpperBoundaryVisibleOnViewport() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_25);
        SWTBotGefEditPart editPart = editor.getEditPart("A");

        assertViewportShiftedRightForMouseZoom(editPart, ZoomLevel.ZOOM_25, ZoomLevel.ZOOM_50, 2, 0, 0, BoundariesVisible.ZOOMIN);
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom when
     * a zoom in from 75% to 100% is done.
     * 
     * The zoomed point is in the right lower corner.
     * 
     * Lower and right diagram boundaries are visible after zoom so the viewport
     * has been blocked on this sides so the zoomed point will not be at the
     * same position in the viewport. It will be shifted to the lower and right
     * position.
     * 
     * Original scroll is (0,0).
     */
    public void testZoomInWithMouseWithLowerAndRightBoundaryVisibleOnViewport() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_25);
        SWTBotGefEditPart editPart = editor.getEditPart("F");

        assertViewportShiftedRightForMouseZoom(editPart, ZoomLevel.ZOOM_25, ZoomLevel.ZOOM_50, 2, 0, 0, BoundariesVisible.ZOOMIN);
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom when
     * a zoom out from 175% to 150% is done.
     * 
     * The zoomed point is in the left upper corner.
     * 
     * No diagram boundaries are visible after zoom out so the viewport must
     * keep the point behind the mouse to the same location on it after zoom on
     * x and y axis.
     * 
     * Original scroll is (60,60).
     */
    public void testZoomOutWithMouseWithoutBoundaryVisibleAfterZoomOnViewport() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_175);
        SWTBotGefEditPart editPart = editor.getEditPart("A");

        assertViewportShiftedRightForMouseZoom(editPart, ZoomLevel.ZOOM_175, ZoomLevel.ZOOM_150, -2, 60, 60, BoundariesVisible.NONE);
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom when
     * a zoom out from 175% to 150% is done.
     * 
     * The zoomed point is in the left upper corner.
     * 
     * Upper and left diagram boundaries are visible after zoom out so the
     * viewport will not keep the point behind the mouse to the same location on
     * it after zoom on x and y axis. And Viewport will not move its origin
     * because it is blocked by boundaries.
     * 
     * Original scroll is (0,0).
     */
    public void testZoomOutWithMouseWithLeftAndUpperBoundaryVisibleOnViewport() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart editPart = editor.getEditPart("A");

        assertViewportShiftedRightForMouseZoom(editPart, ZoomLevel.ZOOM_75, ZoomLevel.ZOOM_50, -2, 0, 0, BoundariesVisible.ZOOMOUT);
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom when
     * a zoom out from 125% to 100% is done.
     * 
     * The zoomed point is in the right lower corner.
     * 
     * Lower and right diagram boundaries are visible after zoom so the viewport
     * has been blocked on this sides so the zoomed point will not be at the
     * same position in the viewport. It will be shifted to the lower and right
     * position.
     * 
     * Original scroll is (0,0).
     */
    public void testZoomOutWithMouseWithLowerAndRightBoundaryVisibleOnViewport() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_50);
        SWTBotGefEditPart editPart = editor.getEditPart("F");

        assertViewportShiftedRightForMouseZoom(editPart, ZoomLevel.ZOOM_50, ZoomLevel.ZOOM_25, -2, 0, 0, BoundariesVisible.ZOOMOUT);
    }
}
