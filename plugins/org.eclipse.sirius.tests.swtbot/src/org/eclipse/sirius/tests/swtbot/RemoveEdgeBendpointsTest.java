/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES and others.
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
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
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
 * 
 */
public class RemoveEdgeBendpointsTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "useCase.migrationmodeler";

    private static final String SESSION_FILE = "useCase.aird";

    private static final String VSM_FILE = "useCase.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/shapeResizing/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "useCase";

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

    private void removeBendpointsOfEdge(ZoomLevel zoomLevel, String edgename) {
        editor.zoom(zoomLevel);
        SWTBotUtils.waitAllUiEvents();

        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart(edgename, DEdgeEditPart.class);
        swtBotGefEditPart.select();
        removeBendpoints();

        editor.zoom(ZoomLevel.ZOOM_100);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeHasExpectedBendpoints(swtBotGefEditPart);
    }

    private void removeBendpoints() {
        editor.clickContextMenu("Remove Bend-points");
        SWTBotUtils.waitAllUiEvents();
    }

    private void openDiagram(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);
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

        Option<Point> expectedFirstBendpoint = GraphicalHelper.getIntersection(lineOrigin, lineTerminus, (IGraphicalEditPart) sourceSwtBotGefEditPart.part(), true);
        Option<Point> expectedSecondBendpoint = GraphicalHelper.getIntersection(lineOrigin, lineTerminus, (IGraphicalEditPart) targetSwtBotGefEditPart.part(), false);

        assertConnectionEndPointEquals("Wrong edge source connection", expectedFirstBendpoint.get(), realSourceConnection);
        assertConnectionEndPointEquals("Wrong edge target connection", expectedSecondBendpoint.get(), realTargetConnection);

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
