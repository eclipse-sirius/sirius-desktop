/*******************************************************************************
 * Copyright (c) 2017-2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot;

import java.util.Optional;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Class test for the new feature that Removes edge bendpoints. see bug #443108
 * 
 * @author Florian Barbin
 */
public class RemoveEdgeBendpointsTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "useCase.migrationmodeler";

    private static final String SESSION_FILE = "useCase.aird";

    private static final String VSM_FILE = "useCase.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/shapeResizing/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "useCase";

    /*
     * (non-Javadoc)
     * 
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
     * 
     * @see
     * org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase
     * #onSetUpAfterOpeningDesignerPerspective()
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        bot.viewById("org.eclipse.ui.views.ContentOutline").close();
        SWTBotUtils.waitAllUiEvents();
        openDiagram("new useCase");

    }

    /**
     * Test that removes bendpoints on an edge between two containers (zoom
     * 100%)
     */
    public void testRemoveBendpointsBetweenContainers() {
        removeBendpointsBetweenContainers(ZoomLevel.ZOOM_100);
    }

    /**
     * Test that removes bendpoints on an edge between a border node and a
     * container (zoom 100%)
     */
    public void testRemoveBendpointsBetweenBorderNodeContainer() {
        removeBendpointsBetweenBorderNodeContainer(ZoomLevel.ZOOM_100);
    }

    /**
     * Test that removes bendpoints on an edge between two nodes (zoom 100%)
     */
    public void testRemoveBendpointsBetweenNode() {
        removeBendpointsBetweenNode(ZoomLevel.ZOOM_100);
    }

    /**
     * Test that removes bendpoints on a rectilinear edge between a border node and an
     * other one (zoom 100%). Result will be an edge with three segments.
     */
    public void testRemoveRectilinearBendpointsBetweenBorderNodesThreeSeg() {
        removeRectilinearBendpointsBetweenBorderNodesThreeSeg(ZoomLevel.ZOOM_100);
    }

    /**
     * Test that removes bendpoints on a rectilinear edge between a border node and an
     * other one (zoom 100%). Result will be an edge with two segments.
     */
    public void testRemoveRectilinearBendpointsBetweenBorderNodesTwoSeg() {
        removeRectilinearBendpointsBetweenBorderNodesTwoSeg(ZoomLevel.ZOOM_100);
    }

    /**
     * Test that removes bendpoints on a rectilinear edge between two nodes (zoom 100%)
     * Result will be an edge with three segments.
     */
    public void testRemoveRectilinearBendpointsBetweenNodeThreeSeg() {
        removeRectilinearBendpointsBetweenNodeThreeSeg(ZoomLevel.ZOOM_100);
    }

    /**
     * Test that removes bendpoints on a rectilinear edge between two nodes (zoom 100%)
     * Result will be an edge with two segments.
     */
    public void testRemoveRectilinearBendpointsBetweenNodeTwoSeg() {
        removeRectilinearBendpointsBetweenNodeTwoSeg(ZoomLevel.ZOOM_100);
    }

    /**
     * Test that removes bendpoints on an edge between two containers (zoom 50%)
     */
    public void testRemoveBendpointsBetweenContainers50() {
        removeBendpointsBetweenContainers(ZoomLevel.ZOOM_50);
    }

    /**
     * Test that removes bendpoints on an edge between a border node and a
     * container (zoom 50%)
     */
    public void testRemoveBendpointsBetweenBorderNodeContainer50() {
        removeBendpointsBetweenBorderNodeContainer(ZoomLevel.ZOOM_50);
    }

    /**
     * Test that removes bendpoints on an edge between two nodes (zoom 50%)
     */
    public void testRemoveBendpointsBetweenNode50() {
        removeBendpointsBetweenNode(ZoomLevel.ZOOM_50);
    }

    /**
     * Test that removes bendpoints on a rectilinear edge between a border node and an
     * other one (zoom 50%). Result will be an edge with three segments.
     */
    public void testRemoveRectilinearBendpointsBetweenBorderNodesThreeSeg50() {
        removeRectilinearBendpointsBetweenBorderNodesThreeSeg(ZoomLevel.ZOOM_50);
    }

    /**
     * Test that removes bendpoints on a rectilinear edge between a border node and an
     * other one (zoom 50%). Result will be an edge with two segments.
     */
    public void testRemoveRectilinearBendpointsBetweenBorderNodesTwoSeg50() {
        removeRectilinearBendpointsBetweenBorderNodesTwoSeg(ZoomLevel.ZOOM_50);
    }

    /**
     * Test that removes bendpoints on a rectilinear edge between two nodes (zoom 50%)
     * Result will be an edge with three segments.
     */
    public void testRemoveRectilinearBendpointsBetweenNodeThreeSeg50() {
        removeRectilinearBendpointsBetweenNodeThreeSeg(ZoomLevel.ZOOM_50);
    }

    /**
     * Test that removes bendpoints on a rectilinear edge between two nodes (zoom 50%)
     * Result will be an edge with two segments.
     */
    public void testRemoveRectilinearBendpointsBetweenNodeTwoSeg50() {
        removeRectilinearBendpointsBetweenNodeTwoSeg(ZoomLevel.ZOOM_50);
    }

    /**
     * Test that removes bendpoints on an edge between two containers (zoom
     * 125%)
     */
    public void testRemoveBendpointsBetweenContainers125() {
        removeBendpointsBetweenContainers(ZoomLevel.ZOOM_125);
    }

    /**
     * Test that removes bendpoints on an edge between a border node and a
     * container (zoom 125%)
     */
    public void testRemoveBendpointsBetweenBorderNodeContainer125() {
        removeBendpointsBetweenBorderNodeContainer(ZoomLevel.ZOOM_125);
    }

    /**
     * Test that removes bendpoints on an edge between two nodes (zoom 125%)
     */
    public void testRemoveBendpointsBetweenNode125() {
        removeBendpointsBetweenNode(ZoomLevel.ZOOM_125);
    }

    private void removeBendpointsBetweenContainers(ZoomLevel zoomLevel) {

        removeBendpointsOfEdge(zoomLevel, "edge2");
    }

    private void removeBendpointsBetweenBorderNodeContainer(ZoomLevel zoomLevel) {

        removeBendpointsOfEdge(zoomLevel, "edge3");
    }

    private void removeBendpointsBetweenNode(ZoomLevel zoomLevel) {
        removeBendpointsOfEdge(zoomLevel, "edge15");
    }

    private void removeRectilinearBendpointsBetweenBorderNodesThreeSeg(ZoomLevel zoomLevel) {

        removeBendpointsOfRectilinearEdge(zoomLevel, "edge6", 3);
    }

    private void removeRectilinearBendpointsBetweenBorderNodesTwoSeg(ZoomLevel zoomLevel) {

        removeBendpointsOfRectilinearEdge(zoomLevel, "edge16", 2);
    }

    private void removeRectilinearBendpointsBetweenNodeThreeSeg(ZoomLevel zoomLevel) {
        removeBendpointsOfRectilinearEdge(zoomLevel, "edge11", 3);
    }

    private void removeRectilinearBendpointsBetweenNodeTwoSeg(ZoomLevel zoomLevel) {
        removeBendpointsOfRectilinearEdge(zoomLevel, "edge17", 2);
    }

    private void removeBendpointsOfEdge(ZoomLevel zoomLevel, String edgename) {
        editor.zoom(zoomLevel);
        SWTBotUtils.waitAllUiEvents();

        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart(edgename,
                DEdgeEditPart.class);
        swtBotGefEditPart.select();
        removeBendpoints();

        editor.zoom(ZoomLevel.ZOOM_100);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeHasExpectedBendpoints(swtBotGefEditPart);
    }

    private void removeBendpointsOfRectilinearEdge(ZoomLevel zoomLevel, String edgename, int nbSegemnt) {
        editor.zoom(zoomLevel);
        SWTBotUtils.waitAllUiEvents();

        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart(edgename,
                DEdgeEditPart.class);
        swtBotGefEditPart.select();
        removeBendpoints();

        editor.zoom(ZoomLevel.ZOOM_100);
        SWTBotUtils.waitAllUiEvents();

        assertRectilinearEdgeHasExpectedBendpoints(swtBotGefEditPart, nbSegemnt);
    }

    private void removeBendpoints() {
        editor.clickContextMenu("Remove Bend-points");
        SWTBotUtils.waitAllUiEvents();
    }

    private void openDiagram(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);
    }

    private void assertEdgeHasExpectedBendpoints(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart) {

        SWTBotGefEditPart sourceSwtBotGefEditPart = swtBotGefConnectionEditPart.source();
        SWTBotGefEditPart targetSwtBotGefEditPart = swtBotGefConnectionEditPart.target();
        ConnectionAnchor srcAnchor = ((Connection) swtBotGefConnectionEditPart.part().getFigure()).getSourceAnchor();
        ConnectionAnchor tgtAnchor = ((Connection) swtBotGefConnectionEditPart.part().getFigure()).getTargetAnchor();

        Connection connection = (Connection) swtBotGefConnectionEditPart.part().getFigure();
        PointList pointList = connection.getPoints();
        assertEquals("The egdge should have only two bendpoints corresponding to the edge ends.", 2, pointList.size());
        Point realTargetConnection = pointList.getPoint(1);
        Point realSourceConnection = pointList.getPoint(0);

        Point lineOrigin = srcAnchor.getReferencePoint();
        Point lineTerminus = tgtAnchor.getReferencePoint();

        GraphicalHelper.screen2logical(lineOrigin, (IGraphicalEditPart) swtBotGefConnectionEditPart.part());
        GraphicalHelper.screen2logical(lineTerminus, (IGraphicalEditPart) swtBotGefConnectionEditPart.part());

        Optional<Point> expectedFirstBendpoint = GraphicalHelper.getIntersection(lineOrigin, lineTerminus,
                (IGraphicalEditPart) sourceSwtBotGefEditPart.part(), true);
        Optional<Point> expectedSecondBendpoint = GraphicalHelper.getIntersection(lineOrigin, lineTerminus,
                (IGraphicalEditPart) targetSwtBotGefEditPart.part(), false);

        assertConnectionEndPointEquals("Wrong edge source connection", expectedFirstBendpoint.get(),
                realSourceConnection);
        assertConnectionEndPointEquals("Wrong edge target connection", expectedSecondBendpoint.get(),
                realTargetConnection);

    }

    private void assertRectilinearEdgeHasExpectedBendpoints(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart,
            int nbSegment) {

        SWTBotGefEditPart sourceSwtBotGefEditPart = swtBotGefConnectionEditPart.source();
        SWTBotGefEditPart targetSwtBotGefEditPart = swtBotGefConnectionEditPart.target();
        ConnectionAnchor srcAnchor = ((Connection) swtBotGefConnectionEditPart.part().getFigure()).getSourceAnchor();
        ConnectionAnchor tgtAnchor = ((Connection) swtBotGefConnectionEditPart.part().getFigure()).getTargetAnchor();

        IFigure srcFigure = ((IGraphicalEditPart) sourceSwtBotGefEditPart.part()).getFigure();
        IFigure tgtFigure = ((IGraphicalEditPart) targetSwtBotGefEditPart.part()).getFigure();

        Point srcAnchorPoint = srcAnchor.getReferencePoint();
        Point tgtAnchorPoint = tgtAnchor.getReferencePoint();

        GraphicalHelper.screen2logical(srcAnchorPoint, (IGraphicalEditPart) swtBotGefConnectionEditPart.part());
        GraphicalHelper.screen2logical(tgtAnchorPoint, (IGraphicalEditPart) swtBotGefConnectionEditPart.part());

        // check anchor in Figure center
        assertConnectionEndPointEquals("Wrong source anchor point.", srcAnchorPoint, getFigureCenter(srcFigure));
        assertConnectionEndPointEquals("Wrong target anchor point.", tgtAnchorPoint, getFigureCenter(tgtFigure));

        Connection connection = (Connection) swtBotGefConnectionEditPart.part().getFigure();
        PointList pointList = connection.getPoints();

        // check number of Bendpoints
        int nbPoint = nbSegment + 1;
        assertEquals("The egdge should have only " + nbPoint + " bendpoints corresponding to the edge ends.", nbPoint,
                pointList.size());
        Point realTargetConnection = pointList.getPoint(nbPoint - 1);
        Point realSourceConnection = pointList.getPoint(0);

        Optional<Point> intersectionAnchorsSrcFigure = GraphicalHelper.getIntersection(srcAnchorPoint, tgtAnchorPoint,
                (IGraphicalEditPart) sourceSwtBotGefEditPart.part(), true);
        Optional<Point> intersectionAnchorsTgtFigure = GraphicalHelper.getIntersection(srcAnchorPoint, tgtAnchorPoint,
                (IGraphicalEditPart) targetSwtBotGefEditPart.part(), false);

        // check if source and target are correct
        isPointOnSideAndAlignWithAnchor(srcAnchorPoint, intersectionAnchorsSrcFigure.get(), realSourceConnection);
        isPointOnSideAndAlignWithAnchor(tgtAnchorPoint, intersectionAnchorsTgtFigure.get(), realTargetConnection);

        if (nbSegment == 2) {
            // +/- 1 tolerance
            if (realSourceConnection.x() <= (srcAnchorPoint.x() + 1)
                && realSourceConnection.x() >= srcAnchorPoint.x() - 1) {
                // case source side is horizontal
                assertConnectionEndPointEquals("Wrong middle Bendpoints.", pointList.getPoint(1),
                        new Point(srcAnchorPoint.x, tgtAnchorPoint.y));
            } else {
                // case source side is vertical
                assertConnectionEndPointEquals("Wrong middle Bendpoints.", pointList.getPoint(1),
                        new Point(srcAnchorPoint.y, tgtAnchorPoint.x));
            }
        } else if (nbSegment == 3) {
            // +/- 1 tolerance
            if (realSourceConnection.x() <= (srcAnchorPoint.x() + 1)
                && realSourceConnection.x() >= srcAnchorPoint.x() - 1) {
                int middleY = (intersectionAnchorsSrcFigure.get().y + intersectionAnchorsTgtFigure.get().y) / 2;
                // case source side is horizontal
                assertConnectionEndPointEquals("Wrong middle Bendpoints.", pointList.getPoint(1),
                        new Point(srcAnchorPoint.x, middleY));
                assertConnectionEndPointEquals("Wrong middle Bendpoints.", pointList.getPoint(2),
                        new Point(tgtAnchorPoint.x, middleY));
            } else {
                int middleX = (intersectionAnchorsSrcFigure.get().x + intersectionAnchorsTgtFigure.get().x) / 2;
                // case source side is vertical
                assertConnectionEndPointEquals("Wrong middle Bendpoints.", pointList.getPoint(1),
                        new Point(middleX, srcAnchorPoint.y));
                assertConnectionEndPointEquals("Wrong middle Bendpoints.", pointList.getPoint(2),
                        new Point(middleX, tgtAnchorPoint.y));
            }
        }

    }

    /**
     * Check if a real point is align with the anchor of a figure and if it is on a side of this figure.
     * 
     * @param anchor
     *            the anchor of the figure (center of figure for rectilinear study)
     * @param intersection
     *            point on the intersection side on which the point has to belong
     * @param realPoint
     *            the real point to check
     */
    private void isPointOnSideAndAlignWithAnchor(Point anchor, Point interection, Point realPoint) {
        if (interection.y == realPoint.y) {
            assertConnectionEndPointEquals(
                    "Bendpoint (" + realPoint.x() + "," + realPoint.y() + ") is not aligned with anchor.",
                    new Point(anchor.x(), interection.y), realPoint);
        } else if (interection.x == realPoint.x) {
            assertConnectionEndPointEquals(
                    "Bendpoint (" + realPoint.x() + "," + realPoint.y() + ") is not aligned with anchor.",
                    new Point(interection.x(), anchor.y), realPoint);
        } else {
            fail("Bendpoint (" + realPoint.x() + "," + realPoint.y() + ") is not on the figure bounds.");
        }
    }

    /**
     * Return the center of a quadrangle figure.
     * 
     * @param figure
     *            the quadrangle figure with the center to find
     * @return the center of the quadrangle figure
     */
    private Point getFigureCenter(IFigure figure) {
        Rectangle bounds = figure.getBounds();
        return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
    }

    /**
     * Assert that the actual point is equal to the expected one with +/- 1
     * tolerance.
     * 
     * @param msg
     * @param expected
     * @param actual
     */
    private void assertConnectionEndPointEquals(String msg, Point expected, Point actual) {
        assertTrue(msg, actual.x() <= (expected.x() + 1) && actual.x() >= expected.x() - 1);
        assertTrue(msg, actual.y() <= (expected.y() + 1) && actual.y() >= expected.y() - 1);
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        editor = null;
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

}
