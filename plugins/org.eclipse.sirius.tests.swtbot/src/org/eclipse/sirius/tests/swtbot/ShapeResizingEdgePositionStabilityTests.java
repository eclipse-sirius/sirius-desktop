/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class that make sure edges do not move during a shape resize. See
 * bugzilla #441424 for more details. Also tests that border nodes have correct
 * GMF location after resizing toward the container center. (see bugzilla
 * #450067).
 * 
 * @author Florian Barbin
 * 
 */
public class ShapeResizingEdgePositionStabilityTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "useCase.migrationmodeler";

    private static final String SESSION_FILE = "useCase.aird";

    private static final String VSM_FILE = "useCase.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/shapeResizing/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "useCase";

    private static final Dimension SOUTH_WEST = new Dimension(-20, 20);

    private static final Dimension SOUTH = new Dimension(0, 20);

    private static final Dimension SOUTH_EAST = new Dimension(20, 20);

    private static final Dimension EAST = new Dimension(20, 0);

    private static final Dimension NORTH = new Dimension(0, -20);

    private static final Dimension NORTH_EAST = new Dimension(20, -20);

    private static final Dimension NORTH_WEST = new Dimension(-20, -20);

    private static final Dimension WEST = new Dimension(-20, 0);

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase
     * #onSetUpBeforeClosingWelcomePage()
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase
     * #onSetUpAfterOpeningDesignerPerspective()
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        openDiagram("new useCase");

    }

    /**
     * Resize Container1 toward South.
     */
    public void testResizingC1ToS() {
        PointList edge1PointListBefore = getEdgePointList("edge1");
        PointList edge2PointListBefore = getEdgePointList("edge2");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container1");
        Rectangle bounds = editor.clickCentered("container1");
        bot.waitUntil(cS);
        resizeToSouth(bounds);
        checkPointsListAfterResizing("edge1", edge1PointListBefore);
        checkPointsListAfterResizing("edge2", edge2PointListBefore);
    }

    /**
     * Resize Container1 toward South-West.
     */
    public void testResizingC1ToSW() {
        PointList edge1PointListBefore = getEdgePointList("edge1");
        PointList edge2PointListBefore = getEdgePointList("edge2");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container1");
        Rectangle bounds = editor.clickCentered("container1");
        bot.waitUntil(cS);
        resizeToSouthWest(bounds);
        checkPointsListAfterResizing("edge1", edge1PointListBefore);
        checkPointsListAfterResizing("edge2", edge2PointListBefore);
    }

    /**
     * Resize Container2 toward South.
     */
    public void testResizingC2ToS() {
        PointList edge1PointListBefore = getEdgePointList("edge1");
        PointList edge2PointListBefore = getEdgePointList("edge2");
        PointList edge3PointListBefore = getEdgePointList("edge3");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container2");
        Rectangle bounds = editor.clickCentered("container2");
        bot.waitUntil(cS);
        resizeToSouth(bounds);
        checkPointsListAfterResizing("edge1", edge1PointListBefore);
        checkPointsListAfterResizing("edge2", edge2PointListBefore);
        checkPointsListAfterResizing("edge3", edge3PointListBefore);
    }

    /**
     * Resize Container2 toward South-West.
     */
    public void testResizingC2ToSW() {
        testResizingC2ToSW(ZoomLevel.ZOOM_100, 0);
    }

    /**
     * Resize Container2 toward South-West with a given zoom.
     * 
     * @param zoomLevel
     *            the zoom level.
     * @param delta
     *            In some conditions, zoom for example, the points list can be
     *            slightly different. This parameter allows to use a delta when
     *            comparing point.
     */
    protected void testResizingC2ToSW(ZoomLevel zoomLevel, int delta) {
        editor.zoom(zoomLevel);
        try {
            PointList edge1PointListBefore = getEdgePointList("edge1");
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container2");
            Rectangle bounds = editor.clickCentered("container2");
            bot.waitUntil(cS);
            resizeToSouthWest(bounds);
            checkPointsListAfterResizing("edge1", edge1PointListBefore, delta);
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
        }
    }

    /**
     * Resize Container2 toward South-West with 50% Zoom.
     */
    public void testResizingC2ToSWWith50() {
        testResizingC2ToSW(ZoomLevel.ZOOM_50, 1);
    }

    /**
     * Resize Container2 toward South-West with 125% Zoom.
     */
    public void testResizingC2ToSWWith125() {
        testResizingC2ToSW(ZoomLevel.ZOOM_125, 0);
    }

    /**
     * Resize Container2 toward South-East with a given zoom.
     * 
     * @param zoomLevel
     *            the zoom level.
     */
    protected void testResizingC2ToSE(ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        try {
            PointList edge1PointListBefore = getEdgePointList("edge1");
            PointList edge2PointListBefore = getEdgePointList("edge2");
            PointList edge3PointListBefore = getEdgePointList("edge3");
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container2");
            Rectangle bounds = editor.clickCentered("container2");
            bot.waitUntil(cS);
            resizeToSouthEast(bounds);
            checkPointsListAfterResizing("edge1", edge1PointListBefore);
            checkPointsListAfterResizing("edge2", edge2PointListBefore);
            checkPointsListAfterResizing("edge3", edge3PointListBefore);

        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
        }
    }

    /**
     * Resize Container2 toward South-East with 50% Zoom.
     */
    public void testResizingC2ToSEWith50() {
        testResizingC2ToSE(ZoomLevel.ZOOM_50);
    }

    /**
     * Resize Container2 toward South-East with 125% Zoom.
     */
    public void testResizingC2ToSEWith125() {
        testResizingC2ToSE(ZoomLevel.ZOOM_125);
    }

    /**
     * Resize Container2 toward South-East.
     */
    public void testResizingC2ToSE() {
        testResizingC2ToSE(ZoomLevel.ZOOM_100);
    }

    /**
     * Resize Container3 toward North-East.
     */
    public void testResizingC3ToNE() {
        PointList edge9PointListBefore = getEdgePointList("edge9");
        PointList edge10PointListBefore = getEdgePointList("edge10");
        PointList edge11PointListBefore = getEdgePointList("edge11");
        PointList edge12PointListBefore = getEdgePointList("edge12");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container3");
        Rectangle bounds = editor.clickCentered("container3");
        bot.waitUntil(cS);
        resizeToNorthEast(bounds);
        checkPointsListAfterResizing("edge9", edge9PointListBefore);
        checkPointsListAfterResizing("edge10", edge10PointListBefore);
        checkPointsListAfterResizing("edge11", edge11PointListBefore);
        checkPointsListAfterResizing("edge12", edge12PointListBefore);
    }

    /**
     * Resize Container3 toward South-East.
     */
    public void testResizingC3ToSE() {
        PointList edge9PointListBefore = getEdgePointList("edge9");
        PointList edge10PointListBefore = getEdgePointList("edge10");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container3");
        Rectangle bounds = editor.clickCentered("container3");
        bot.waitUntil(cS);
        resizeToSouthEast(bounds);
        checkPointsListAfterResizing("edge9", edge9PointListBefore);
        checkPointsListAfterResizing("edge10", edge10PointListBefore);
    }

    /**
     * Resize Container3 toward North-West.
     */
    public void testResizingC3ToNW() {
        PointList edge11PointListBefore = getEdgePointList("edge11");
        PointList edge12PointListBefore = getEdgePointList("edge12");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container3");
        Rectangle bounds = editor.clickCentered("container3");
        bot.waitUntil(cS);
        resizeToNorthWest(bounds);
        checkPointsListAfterResizing("edge11", edge11PointListBefore);
        checkPointsListAfterResizing("edge12", edge12PointListBefore);
    }

    /**
     * Resize Container4 toward South.
     */
    public void testResizingC4ToS() {
        PointList edge5PointListBefore = getEdgePointList("edge5");
        PointList edge7PointListBefore = getEdgePointList("edge7");
        PointList edge8PointListBefore = getEdgePointList("edge8");
        PointList edge11PointListBefore = getEdgePointList("edge11");
        PointList edge13PointListBefore = getEdgePointList("edge13");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container4");
        Rectangle bounds = editor.clickCentered("container4");
        bot.waitUntil(cS);
        resizeToSouth(bounds);
        checkPointsListAfterResizing("edge5", edge5PointListBefore);
        checkPointsListAfterResizing("edge7", edge7PointListBefore);
        checkPointsListAfterResizing("edge8", edge8PointListBefore);
        checkPointsListAfterResizing("edge11", edge11PointListBefore);
        checkPointsListAfterResizing("edge13", edge13PointListBefore);
    }

    /**
     * Resize Container4 toward South-West.
     */
    public void testResizingC4ToSW() {
        PointList edge7PointListBefore = getEdgePointList("edge7");
        PointList edge8PointListBefore = getEdgePointList("edge8");
        PointList edge11PointListBefore = getEdgePointList("edge11");
        PointList edge13PointListBefore = getEdgePointList("edge13");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container4");
        Rectangle bounds = editor.clickCentered("container4");
        bot.waitUntil(cS);
        resizeToSouthWest(bounds);
        checkPointsListAfterResizing("edge7", edge7PointListBefore);
        checkPointsListAfterResizing("edge8", edge8PointListBefore);
        checkPointsListAfterResizing("edge11", edge11PointListBefore);
        checkPointsListAfterResizing("edge13", edge13PointListBefore);
    }

    /**
     * Resize Container4 toward South East.
     */
    public void testResizingC4ToSE() {
        PointList edge5PointListBefore = getEdgePointList("edge5");
        PointList edge7PointListBefore = getEdgePointList("edge7");
        PointList edge8PointListBefore = getEdgePointList("edge8");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container4");
        Rectangle bounds = editor.clickCentered("container4");
        bot.waitUntil(cS);
        resizeToSouthEast(bounds);
        checkPointsListAfterResizing("edge5", edge5PointListBefore);
        checkPointsListAfterResizing("edge7", edge7PointListBefore);
        checkPointsListAfterResizing("edge8", edge8PointListBefore);
    }

    /**
     * Resize Container4 toward North East.
     */
    public void testResizingC4ToNE() {
        PointList edge5PointListBefore = getEdgePointList("edge5");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container4");
        Rectangle bounds = editor.clickCentered("container4");
        bot.waitUntil(cS);
        resizeToNorthEast(bounds);
        checkPointsListAfterResizing("edge5", edge5PointListBefore);
    }

    /**
     * Resize Container4 toward North West.
     */
    public void testResizingC4ToNW() {
        PointList edge11PointListBefore = getEdgePointList("edge11");
        PointList edge13PointListBefore = getEdgePointList("edge13");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "container4");
        Rectangle bounds = editor.clickCentered("container4");
        bot.waitUntil(cS);
        resizeToNorthWest(bounds);
        checkPointsListAfterResizing("edge11", edge11PointListBefore);
        checkPointsListAfterResizing("edge13", edge13PointListBefore);
    }

    /**
     * Resize Node1 toward South West.
     */
    public void testResizingN1ToSW() {
        PointList edge4PointListBefore = getEdgePointList("edge4");
        PointList edge5PointListBefore = getEdgePointList("edge5");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "node1");
        Rectangle bounds = editor.clickCentered("node1");
        bot.waitUntil(cS);
        resizeToSouthWest(bounds);
        checkPointsListAfterResizing("edge4", edge4PointListBefore);
        checkPointsListAfterResizing("edge5", edge5PointListBefore);
    }

    /**
     * Resize Node2 toward South West.
     */
    public void testResizingN2ToSW() {
        PointList edge12PointListBefore = getEdgePointList("edge12");
        PointList edge15PointListBefore = getEdgePointList("edge15");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "node2");
        Rectangle bounds = editor.clickCentered("node2");
        bot.waitUntil(cS);
        resizeToSouthWest(bounds);
        checkPointsListAfterResizing("edge12", edge12PointListBefore);
        checkPointsListAfterResizing("edge15", edge15PointListBefore);
    }

    /**
     * Resize Node2 toward South East.
     */
    public void testResizingN2ToSE() {
        PointList edge12PointListBefore = getEdgePointList("edge12");
        PointList edge13PointListBefore = getEdgePointList("edge13");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "node2");
        Rectangle bounds = editor.clickCentered("node2");
        bot.waitUntil(cS);
        resizeToSouthEast(bounds);
        checkPointsListAfterResizing("edge12", edge12PointListBefore);
        checkPointsListAfterResizing("edge13", edge13PointListBefore);
    }

    /**
     * Resize Node2 toward North West.
     */
    public void testResizingN2ToNW() {
        PointList edge14PointListBefore = getEdgePointList("edge14");
        PointList edge15PointListBefore = getEdgePointList("edge15");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "node2");
        Rectangle bounds = editor.clickCentered("node2");
        bot.waitUntil(cS);
        resizeToNorthWest(bounds);
        checkPointsListAfterResizing("edge14", edge14PointListBefore);
        checkPointsListAfterResizing("edge15", edge15PointListBefore);
    }

    /**
     * Resize Node2 toward North East.
     */
    public void testResizingN2ToNE() {
        PointList edge13PointListBefore = getEdgePointList("edge13");
        PointList edge14PointListBefore = getEdgePointList("edge14");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "node2");
        Rectangle bounds = editor.clickCentered("node2");
        bot.waitUntil(cS);
        resizeToNorthEast(bounds);
        checkPointsListAfterResizing("edge13", edge13PointListBefore);
        checkPointsListAfterResizing("edge14", edge14PointListBefore);
    }

    /**
     * Resize Node3 toward South West.
     */
    public void testResizingN3ToSW() {
        PointList edge15PointListBefore = getEdgePointList("edge15");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "node3");
        Rectangle bounds = editor.clickCentered("node3");
        bot.waitUntil(cS);
        resizeToSouthWest(bounds);
        checkPointsListAfterResizing("edge15", edge15PointListBefore);
    }

    /**
     * Resize Node3 toward South East.
     */
    public void testResizingN3ToSE() {
        PointList edge14PointListBefore = getEdgePointList("edge14");
        PointList edge15PointListBefore = getEdgePointList("edge15");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "node3");
        Rectangle bounds = editor.clickCentered("node3");
        bot.waitUntil(cS);
        resizeToSouthEast(bounds);
        checkPointsListAfterResizing("edge14", edge14PointListBefore);
        checkPointsListAfterResizing("edge15", edge15PointListBefore);
    }

    /**
     * Resize Node3 toward North East.
     */
    public void testResizingN3ToNE() {
        PointList edge14PointListBefore = getEdgePointList("edge14");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "node3");
        Rectangle bounds = editor.clickCentered("node3");
        bot.waitUntil(cS);
        resizeToNorthEast(bounds);
        checkPointsListAfterResizing("edge14", edge14PointListBefore);
    }

    /**
     * Resize BorderNode2 toward East.
     */
    public void testResizingB2ToE() {
        PointList edge6PointListBefore = getEdgePointList("edge6");
        PointList edge9PointListBefore = getEdgePointList("edge9");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "border2");
        Rectangle bounds = editor.clickCentered("border2");
        bot.waitUntil(cS);
        resizeToEast(bounds);
        checkPointsListAfterResizing("edge6", edge6PointListBefore);
        checkPointsListAfterResizing("edge9", edge9PointListBefore);
    }

    /**
     * Resize BorderNode2 toward North.
     */
    public void testResizingB2ToN() {
        PointList edge6PointListBefore = getEdgePointList("edge6");
        PointList edge10PointListBefore = getEdgePointList("edge10");
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "border2");
        Rectangle bounds = editor.clickCentered("border2");
        bot.waitUntil(cS);
        resizeToNorth(bounds);
        checkPointsListAfterResizing("edge6", edge6PointListBefore);
        checkPointsListAfterResizing("edge10", edge10PointListBefore);
    }

    /**
     * Resize BorderNode1 toward North.
     */
    public void testResizingB1ToN() {
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "border1");
        Rectangle bounds = editor.clickCentered("border1");
        bot.waitUntil(cS);
        resizeToNorth(bounds);
        checkGMFDraw2DConsistency("border1");
    }

    /**
     * Resize BorderNode3 toward South.
     */
    public void testResizingB3ToS() {
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "border3");
        Rectangle bounds = editor.clickCentered("border3");
        bot.waitUntil(cS);
        resizeToSouth(bounds);
        checkGMFDraw2DConsistency("border3");
    }

    /**
     * Resize BorderNode3 toward West.
     */
    public void testResizingB2ToW() {
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "border2");
        Rectangle bounds = editor.clickCentered("border2");
        bot.waitUntil(cS);
        resizeToWest(bounds);
        checkGMFDraw2DConsistency("border2");
    }

    /**
     * Check whether the GMF and draw2D coordinates are consistent.
     * 
     * @param nodeName
     *            the node label.
     */
    private void checkGMFDraw2DConsistency(String nodeName) {
        SWTBotGefEditPart botGefEditPart = editor.getEditPart(nodeName, AbstractDiagramBorderNodeEditPart.class);
        GraphicalEditPart graphicalEditPart = (GraphicalEditPart) botGefEditPart.part();
        Rectangle figureBounds = graphicalEditPart.getFigure().getBounds().getCopy();
        Rectangle parentBounds = graphicalEditPart.getFigure().getParent().getBounds();
        Bounds bounds = (Bounds) ((Node) graphicalEditPart.getModel()).getLayoutConstraint();
        figureBounds.translate(-parentBounds.getTopLeft().x, -parentBounds.getTopLeft().y);

        assertEquals("The GMF location is different from the Figure one", figureBounds.getTopLeft(), new Point(bounds.getX(), bounds.getY()));
        assertEquals("The GMF dimension is different from the Figure one", figureBounds.getSize(), new Dimension(bounds.getWidth(), bounds.getHeight()));

    }

    /**
     * Check that the given edge didn't move during the shape resizing.
     * 
     * @param string
     *            the edge label id.
     * @param edge1PointListBefore
     *            the edge point list before resizing.
     */
    private void checkPointsListAfterResizing(String edgeId, PointList edgePointListBefore) {
        checkPointsListAfterResizing(edgeId, edgePointListBefore, 0);
    }

    /**
     * Check that the given edge didn't move during the shape resizing.
     * 
     * @param string
     *            the edge label id.
     * @param edge1PointListBefore
     *            the edge point list before resizing.
     * @param delta
     *            In some conditions, zoom for example, the points list can be
     *            slightly different. This parameter allows to use a delta when
     *            comparing point.
     */
    private void checkPointsListAfterResizing(String edgeId, PointList edgePointListBefore, int delta) {
        PointList afterPointList = getEdgePointList(edgeId);
        assertEquals("The edge point list size is different", edgePointListBefore.size(), afterPointList.size());
        for (int i = 0; i < edgePointListBefore.size(); i++) {
            Point pointBefore = edgePointListBefore.getPoint(i);
            Point pointAfter = afterPointList.getPoint(i);
            assertEquals("The x coordinate of point #" + i + " is different after resizing: ", pointBefore.x, pointAfter.x, delta);
            assertEquals("The y coordinate of point #" + i + " is different after resizing: ", pointBefore.y, pointAfter.y, delta);
        }

    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase
     * #tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        editor.restore();
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        editor = null;
        super.tearDown();
    }

    private PointList getEdgePointList(String egdeId) {
        SWTBotGefConnectionEditPart editPart = (SWTBotGefConnectionEditPart) editor.getEditPart(egdeId, ConnectionEditPart.class);
        return ((Connection) editPart.part().getFigure()).getPoints().getCopy();
    }

    private void openDiagram(String representationName) {

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);

        editor.maximize();
        SWTBotUtils.waitAllUiEvents();

    }

    private void resizeToEast(Rectangle bounds) {
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(EAST));
    }

    private void resizeToWest(Rectangle bounds) {
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(WEST));
    }

    private void resizeToNorth(Rectangle bounds) {
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(NORTH));
    }

    private void resizeToNorthWest(Rectangle bounds) {
        editor.drag(bounds.getTopLeft(), bounds.getTopLeft().getTranslated(NORTH_WEST));
    }

    private void resizeToNorthEast(Rectangle bounds) {
        editor.drag(bounds.getTopRight(), bounds.getTopRight().getTranslated(NORTH_EAST));
    }

    private void resizeToSouth(Rectangle bounds) {
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(SOUTH));
    }

    private void resizeToSouthWest(Rectangle bounds) {
        editor.drag(bounds.getBottomLeft(), bounds.getBottomLeft().getTranslated(SOUTH_WEST));
    }

    private void resizeToSouthEast(Rectangle bounds) {
        editor.drag(bounds.getBottomRight(), bounds.getBottomRight().getTranslated(SOUTH_EAST));
    }

}
