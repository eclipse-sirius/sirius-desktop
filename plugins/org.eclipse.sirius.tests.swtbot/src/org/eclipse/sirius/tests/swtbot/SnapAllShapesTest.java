/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.BendpointLocationCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TopCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Check the snap to all feature (move and resize).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SnapAllShapesTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String SEMANTIC_RESOURCE_NAME = "My.ecore";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    private static final String MODELER_RESOURCE_NAME = "snapToAll.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/snap/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        // Close the outline view
        bot.viewById("org.eclipse.ui.views.ContentOutline").close();
        SWTBotUtils.waitAllUiEvents();

        // Open the session
        UIResource sessionAirdResource = new UIResource(designerProject, SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        // Open the editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "snapToAllDiagram", "snapToAllDiagram", DDiagram.class, true, false);
        editor.maximize();
    }

    @Override
    protected void tearDown() throws Exception {
        if (editor != null) {
            editor.zoom(ZoomLevel.ZOOM_100);
            editor.restore();
            editor.scrollTo(0, 0);
        }
        // Close the editor before opening the outline
        if (editor != null) {
            editor.close();
            SWTBotUtils.waitAllUiEvents();
        }

        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Move a container a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Move a container a second time with F4 and check the location is the
     * expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram.
     */
    public void testMoveContainer() {
        moveTopOfElementNearBottomOfAnother("Container_p1", AbstractDiagramContainerEditPart.class, "BNBNN_att1", AbstractDiagramBorderNodeEditPart.class);
    }

    /**
     * Move a node a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Move a node a second time with F4 and check the location is the expected
     * one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram.
     */
    public void testMoveNode() {
        moveTopOfElementNearBottomOfAnother("Node_p1", AbstractDiagramNodeEditPart.class, "BNNC_att1", AbstractDiagramBorderNodeEditPart.class);
    }

    /**
     * Move a node in container a first time without F4 and check the location
     * is the expected one (ie the mouse location).<BR>
     * Move a node in container a second time with F4 and check the location is
     * the expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram and in
     * container.
     */

    public void testMoveNodeInContainer() {
        moveTopOfElementNearBottomOfAnother("NC_C1", AbstractDiagramNodeEditPart.class, "Node_p1", AbstractDiagramNodeEditPart.class);
    }

    /**
     * Move a node in container a first time without F4 and check the location
     * is the expected one (ie the mouse location).<BR>
     * Move a node in container a second time with F4 and check the location is
     * the expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram and in container
     * and with zoom different from 100%.
     */
    public void testMoveNodeInContainerWithZoom125() {
        moveTopOfElementNearBottomOfAnother("NC_C1", AbstractDiagramNodeEditPart.class, "Node_p1", AbstractDiagramNodeEditPart.class, ZoomLevel.ZOOM_125);
    }

    /**
     * Move a Note a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Move a Note a second time with F4 and check the location is the expected
     * one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram.
     */
    public void testMoveNote() {
        moveTopOfElementNearBottomOfAnother("Note", NoteEditPart.class, "BNNC_att1", AbstractDiagramBorderNodeEditPart.class);
    }

    /**
     * Resize a container a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Resize a container a second time with F4 and check the location is the
     * expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram.
     */
    public void testResizeContainer() {
        resizeTopOfElementNearBottomOfAnother("Container_p1", AbstractDiagramContainerEditPart.class, "BNBNN_att1", AbstractDiagramBorderNodeEditPart.class);
    }

    /**
     * Resize a node a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Resize a node a second time with F4 and check the location is the
     * expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram.
     */
    public void testResizeNode() {
        resizeTopOfElementNearBottomOfAnother("Node_p1", AbstractDiagramNodeEditPart.class, "BNNC_att1", AbstractDiagramBorderNodeEditPart.class);
    }

    /**
     * Resize a node in container a first time without F4 and check the location
     * is the expected one (ie the mouse location).<BR>
     * Resize a node in container a second time with F4 and check the location
     * is the expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram and in
     * container.
     */

    public void testResizeNodeInContainer() {
        resizeTopOfElementNearBottomOfAnother("NC_C1", AbstractDiagramNodeEditPart.class, "Node_p1", AbstractDiagramNodeEditPart.class);
    }

    /**
     * Resize a node in container a first time without F4 and check the location
     * is the expected one (ie the mouse location).<BR>
     * Resize a node in container a second time with F4 and check the location
     * is the expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram and in container
     * and with zoom different from 100%.
     */
    public void testResizeNodeInContainerWithZoom125() {
        resizeTopOfElementNearBottomOfAnother("NC_C1", AbstractDiagramNodeEditPart.class, "Node_p1", AbstractDiagramNodeEditPart.class, ZoomLevel.ZOOM_125);
    }

    /**
     * Resize a Note a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Resize a Note a second time with F4 and check the location is the
     * expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram.
     */
    public void testResizeNote() {
        resizeTopOfElementNearBottomOfAnother("Note", NoteEditPart.class, "BNNC_att1", AbstractDiagramBorderNodeEditPart.class);
    }

    /**
     * Resize a Text a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Resize a Text a second time with F4 and check the location is the
     * expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram.
     */
    public void testResizeText() {
        resizeTopOfElementNearBottomOfAnother("Text", TextEditPart.class, "BNNC_att1", AbstractDiagramBorderNodeEditPart.class);
    }

    /**
     * Move a bendpoint of an edge a first time without F4 and check the
     * location is the expected one (ie the mouse location).<BR>
     * Move a bendpoint of an edge a second time with F4 and check the location
     * is the expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram.
     */
    public void testMoveBendpoint() {
        moveBendpoint(ZoomLevel.ZOOM_100);
    }

    /**
     * Move a bendpoint of an edge a first time without F4 and check the
     * location is the expected one (ie the mouse location).<BR>
     * Move a bendpoint of an edge a second time with F4 and check the location
     * is the expected one (snap to another figure).<BR>
     * This test also handles the case of scroll bar in diagram and zoom
     * different from 100%.
     */
    public void testMoveBendpointWithZoom50() {
        moveBendpoint(ZoomLevel.ZOOM_50);
    }

    private void moveBendpoint(ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        editor.scrollTo(0, 0);
        SWTBotGefEditPart elementToMove = editor.getEditPart("toC2", AbstractDiagramEdgeEditPart.class);

        // Select the element to move
        editor.select(elementToMove);

        // Get the bendpoint to move
        assertTrue(elementToMove.part() instanceof ConnectionEditPart);
        ConnectionEditPart connectionEditPart = (ConnectionEditPart) elementToMove.part();
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
        Point pointToMove = pointList.getPoint(1);

        // Compute the drop destination (at 2 pixels of the bottom of another
        // figure)
        final Rectangle targetNodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) editor.getEditPart("BNBNC_att1", AbstractDiagramBorderNodeEditPart.class).part());
        final Point endpoint = new Point(pointToMove.x, targetNodeBounds.getBottom().y - 4);

        pointToMove.scale(zoomLevel.getAmount());
        endpoint.scale(zoomLevel.getAmount());
        // First move without F4 key pressed
        editor.dragWithKey(pointToMove.x, pointToMove.y, endpoint.x, endpoint.y, SWT.None);
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new BendpointLocationCondition((PolylineConnection) connectionEditPart.getFigure(), 1, false, targetNodeBounds.getBottom().y - 4,
                "Second bendpoint of edge is not at expected y location after resize without F4 key pressed", !ZoomLevel.ZOOM_100.equals(zoomLevel)));

        // Move to initial location
        undo(localSession.getOpenedSession());
        // Scroll to 0, 0 is needed because the first move can cause a scroll of
        // the diagram not reverted by the Undo.
        editor.scrollTo(0, 0);

        // Second move with F4 key pressed
        editor.dragWithKey(pointToMove.x, pointToMove.y, endpoint.x, endpoint.y, SWT.F4);
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new BendpointLocationCondition((PolylineConnection) connectionEditPart.getFigure(), 1, false, targetNodeBounds.getBottom().y - 1,
                "Second bendpoint of edge is not at expected y location after resize with F4 key pressed", false));
    }

    private void moveTopOfElementNearBottomOfAnother(String elementNameToMove, Class<? extends EditPart> expectedEditPartTypeOfMovedElement, String referenceElementName,
            Class<? extends EditPart> expectedEditPartTypeOfReferenceElement) {
        moveTopOfElementNearBottomOfAnother(elementNameToMove, expectedEditPartTypeOfMovedElement, referenceElementName, expectedEditPartTypeOfReferenceElement, ZoomLevel.ZOOM_100);
    }

    /**
     * Move element a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Move element a second time with F4 and check the location is the expected
     * one (snap to another figure).<BR>
     */
    private void moveTopOfElementNearBottomOfAnother(String elementNameToMove, Class<? extends EditPart> expectedEditPartTypeOfMovedElement, String referenceElementName,
            Class<? extends EditPart> expectedEditPartTypeOfReferenceElement, ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        editor.scrollTo(0, 0);

        SWTBotGefEditPart elementToMove = editor.getEditPart(elementNameToMove, expectedEditPartTypeOfMovedElement);
        // Select the element to move
        editor.select(elementToMove);

        // Get the top center coordinates, just a little below, of the element
        // to move
        final Rectangle originalBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) elementToMove.part());
        Point pointToDrag = originalBounds.getTop().getTranslated(0, 3);
        if (TextEditPart.class.equals(expectedEditPartTypeOfMovedElement)) {
            pointToDrag = originalBounds.getTop().getTranslated(0, 5);
        }

        Point scaledPointToDrag = new PrecisionPoint(pointToDrag);
        GraphicalHelper.logical2screen(scaledPointToDrag, (IGraphicalEditPart) elementToMove.part());
        // Compute the drop destination (at 4 pixels of the bottom of another
        // part)
        final Rectangle targetNodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) editor.getEditPart(referenceElementName, expectedEditPartTypeOfReferenceElement).part());
        final Point endpoint = new Point(pointToDrag.x, targetNodeBounds.getBottom().y - 1);
        Point scaledEndpoint = new PrecisionPoint(endpoint);
        GraphicalHelper.logical2screen(scaledEndpoint, (IGraphicalEditPart) elementToMove.part());

        // First move without F4 key pressed
        editor.drag(scaledPointToDrag.x, scaledPointToDrag.y, scaledEndpoint.x, scaledEndpoint.y);
        SWTBotUtils.waitAllUiEvents();
        // Get the new bounds and compare with the expected. It should be
        // precisely where the drag has been done: at 4 pixels of the bottom of
        // the other figure
        Rectangle newBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) elementToMove.part());
        assertEquals("Element \"" + elementNameToMove + "\" is not at expected y location after move without F4 key pressed", targetNodeBounds.getBottom().y - 4, newBounds.getTop().y);

        // Move to initial location
        undo(localSession.getOpenedSession());
        // Scroll to 0, 0 is needed because the first move can cause a scroll of
        // the diagram not reverted by the Undo.
        editor.scrollTo(0, 0);

        // Second move with F4 key pressed
        editor.dragWithKey(scaledPointToDrag.x, scaledPointToDrag.y, scaledEndpoint.x, scaledEndpoint.y, SWT.F4);
        SWTBotUtils.waitAllUiEvents();
        // Get the new bounds and compare with the expected. It should be
        // aligned to the bottom of the other figure: at 1 pixel of the bottom
        // as computed guide in SiriusSnapToGeometry.populateRowsAndCols(List)
        newBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) elementToMove.part());
        assertEquals("Element \"" + elementNameToMove + "\" is not at expected location after move with F4 key pressed", targetNodeBounds.getBottom().y - 1, newBounds.getTop().y);
    }

    private void resizeTopOfElementNearBottomOfAnother(String elementNameToMove, Class<? extends EditPart> expectedEditPartTypeOfMovedElement, String referenceElementName,
            Class<? extends EditPart> expectedEditPartTypeOfReferenceElement) {
        resizeTopOfElementNearBottomOfAnother(elementNameToMove, expectedEditPartTypeOfMovedElement, referenceElementName, expectedEditPartTypeOfReferenceElement, ZoomLevel.ZOOM_100);
    }

    /**
     * Resize element a first time without F4 and check the location is the
     * expected one (ie the mouse location).<BR>
     * Resize element a second time with F4 and check the location is the
     * expected one (snap to another figure).<BR>
     */
    private void resizeTopOfElementNearBottomOfAnother(String elementNameToMove, Class<? extends EditPart> expectedEditPartTypeOfMovedElement, String referenceElementName,
            Class<? extends EditPart> expectedEditPartTypeOfReferenceElement, final ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        editor.scrollTo(0, 0);

        final SWTBotGefEditPart elementToMove = editor.getEditPart(elementNameToMove, expectedEditPartTypeOfMovedElement);
        // Select the element to move
        editor.select(elementToMove);

        // Get the top center coordinates of the element to move
        final Rectangle originalBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) elementToMove.part());
        final Point pointToDrag = originalBounds.getTop();
        Point scaledPointToDrag = new PrecisionPoint(pointToDrag);
        GraphicalHelper.logical2screen(scaledPointToDrag, (IGraphicalEditPart) elementToMove.part());
        // Compute the drop destination (at 4 pixels of the bottom of another
        // part)
        final Rectangle targetNodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) editor.getEditPart(referenceElementName, expectedEditPartTypeOfReferenceElement).part());
        final Point endpoint = new Point(pointToDrag.x, targetNodeBounds.getBottom().y - 4);
        Point scaledEndpoint = new PrecisionPoint(endpoint);
        GraphicalHelper.logical2screen(scaledEndpoint, (IGraphicalEditPart) elementToMove.part());

        // First move without F4 key pressed
        editor.drag(scaledPointToDrag.x, scaledPointToDrag.y, scaledEndpoint.x, scaledEndpoint.y);
        SWTBotUtils.waitAllUiEvents();
        // Get the new bounds and compare with the expected. It should be
        // precisely where the drag has been done: at 4 pixels of the bottom of
        // the other figure
        bot.waitUntil(new TopCondition((GraphicalEditPart) elementToMove.part(), targetNodeBounds.getBottom().y - 4,
                "Element \"" + elementNameToMove + "\" is not at expected y location after resize without F4 key pressed", !ZoomLevel.ZOOM_100.equals(zoomLevel)));

        // Move to initial location
        undo(localSession.getOpenedSession());
        // Scroll to 0, 0 is needed because the first move can cause a scroll of
        // the diagram not reverted by the Undo.
        editor.scrollTo(0, 0);

        // Second move with F4 key pressed
        editor.dragWithKey(scaledPointToDrag.x, scaledPointToDrag.y, scaledEndpoint.x, scaledEndpoint.y, SWT.F4);
        SWTBotUtils.waitAllUiEvents();
        // Get the new bounds and compare with the expected. It should be
        // aligned to the bottom of the other figure: at 1 pixel of the bottom
        // as computed guide in SiriusSnapToGeometry.populateRowsAndCols(List)
        bot.waitUntil(new TopCondition((GraphicalEditPart) elementToMove.part(), targetNodeBounds.getBottom().y - 1,
                "Element \"" + elementNameToMove + "\" is not at expected y location after resize with F4 key pressed", !ZoomLevel.ZOOM_100.equals(zoomLevel)));
    }
}
