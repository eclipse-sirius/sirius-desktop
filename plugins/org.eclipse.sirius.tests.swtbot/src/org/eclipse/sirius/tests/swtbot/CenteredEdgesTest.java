/*******************************************************************************
 * Copyright (c) 2008, 2014 THALES GLOBAL SERVICES and others.
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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Class test for the new feature "centered edges". see bug #437528
 * 
 * @author Florian Barbin
 * 
 */
public class CenteredEdgesTest extends AbstractSiriusSwtBotGefTestCase {

    private static final PrecisionPoint TOP_LEFT_CORNER = new PrecisionPoint(0.1, 0.1);

    private static final PrecisionPoint BOTTOM_RIGHT_CORNER = new PrecisionPoint(0.9, 0.9);

    private static final String PATH = "/data/unit/centeredEdge/";

    private static final String SEMANTIC_MODEL = "useCase.migrationmodeler";

    private static final String REPRESENTATION_MODEL = "useCase.aird";

    private static final String MODELER = "useCase.odesign";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "useCase";

    private static final String REPRESENTATION_NAME = "new " + REPRESENTATION_DESCRIPTION_NAME;

    private static final String REPRESENTATION_NAME_RECONNECT = "reconnect";

    private static final String REPRESENTATION_NAME_ROUTING = "routingStyle";

    private static final String REPRESENTATION_NAME_MOVING = "moving";

    private static final String VIEWPOINT_NAME = "centered";

    private static final String RECTILINEAR_STYLE_ROUTING = "Rectilinear Style Routing";

    private UIDiagramRepresentation diagram;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL, REPRESENTATION_MODEL, MODELER);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        sessionAirdResource = new UIResource(designerProject, "/", REPRESENTATION_MODEL);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test that the edge is correctly centered on the source border node when
     * using an edge creation tool.
     */
    public void testEdgeCreationToolOnCenteredSrcBorderNode() {
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefEditPart srcEditPart = editor.getEditPart("border1", DNode4EditPart.class);
        SWTBotGefEditPart tgtEditPart = editor.getEditPart("border3", DNode4EditPart.class);
        createEdge((IGraphicalEditPart) srcEditPart.part(), TOP_LEFT_CORNER, (IGraphicalEditPart) tgtEditPart.part(), BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("newEdge", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));

    }

    /**
     * Test that the edge is correctly centered on the target border node when
     * using an edge creation tool.
     */
    public void testEdgeCreationToolOnCenteredTgtBorderNode() {
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefEditPart srcEditPart = editor.getEditPart("border2", DNode4EditPart.class);
        SWTBotGefEditPart tgtEditPart = editor.getEditPart("border4", DNode4EditPart.class);
        createEdge((IGraphicalEditPart) srcEditPart.part(), TOP_LEFT_CORNER, (IGraphicalEditPart) tgtEditPart.part(), BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("newEdge", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));

    }

    /**
     * Test that the edge is correctly centered on the source node when using an
     * edge creation tool.
     */
    public void testEdgeCreationToolOnCenteredSrcNode() {
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefEditPart srcEditPart = editor.getEditPart("node2", DNodeEditPart.class);
        SWTBotGefEditPart tgtEditPart = editor.getEditPart("node4", DNodeEditPart.class);
        createEdge((IGraphicalEditPart) srcEditPart.part(), TOP_LEFT_CORNER, (IGraphicalEditPart) tgtEditPart.part(), BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("newEdge", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the edge is correctly centered on the target node when using an
     * edge creation tool.
     */
    public void testEdgeCreationToolOnCenteredTgtNode() {
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefEditPart srcEditPart = editor.getEditPart("node3", DNodeEditPart.class);
        SWTBotGefEditPart tgtEditPart = editor.getEditPart("node1", DNodeEditPart.class);
        createEdge((IGraphicalEditPart) srcEditPart.part(), TOP_LEFT_CORNER, (IGraphicalEditPart) tgtEditPart.part(), BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("newEdge", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the edge is correctly centered on the source container when
     * using an edge creation tool.
     */
    public void testEdgeCreationToolOnCenteredSrcContainer() {
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefEditPart srcEditPart = editor.getEditPart("container1", DNodeContainerEditPart.class);
        SWTBotGefEditPart tgtEditPart = editor.getEditPart("container3", DNodeContainerEditPart.class);
        createEdge((IGraphicalEditPart) srcEditPart.part(), TOP_LEFT_CORNER, (IGraphicalEditPart) tgtEditPart.part(), BOTTOM_RIGHT_CORNER);
        DEdgeEditPart editPart = getSingleDEdgeFrom((NodeEditPart) srcEditPart.part());

        assertEdgeHasExpectedSrcAnchor(editPart, (NodeEditPart) srcEditPart.part(), new PrecisionPoint(0.5, 0.5));
        // assertEdgeHasExpectedSrcAnchor(editPart, srcEditPart, new
        // PrecisionPoint(0.5, 0.5), new PrecisionPoint(0.5, 1));
    }

    /**
     * Test that the edge is correctly centered on the target container when
     * using an edge creation tool.
     */
    public void testEdgeCreationToolOnCenteredTgtContainer() {
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefEditPart srcEditPart = editor.getEditPart("container4", DNodeContainerEditPart.class);
        SWTBotGefEditPart tgtEditPart = editor.getEditPart("container2", DNodeContainerEditPart.class);
        createEdge((IGraphicalEditPart) srcEditPart.part(), TOP_LEFT_CORNER, (IGraphicalEditPart) tgtEditPart.part(), BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("newEdge", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the edge is correctly centered on the target node when using
     * the reconnect tool.
     */
    public void testEdgeReconnectionOnCenteredNode() {
        openDiagram(REPRESENTATION_NAME_RECONNECT);

        SWTBotGefEditPart toBotGefEditPart = editor.getEditPart("node2", DNodeEditPart.class);
        reconnectEdge("edge3", toBotGefEditPart, false);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the edge is correctly centered on the target border node when
     * using the reconnect tool.
     */
    public void testEdgeReconnectionOnCenteredBorderNode() {
        openDiagram(REPRESENTATION_NAME_RECONNECT);
        SWTBotGefEditPart toBotGefEditPart = editor.getEditPart("border1", DNode4EditPart.class);
        reconnectEdge("edge1", toBotGefEditPart, false);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the edge is correctly centered on the target container when
     * using the reconnect tool.
     */
    public void testEdgeReconnectionOnCenteredContainer() {
        openDiagram(REPRESENTATION_NAME_RECONNECT);
        SWTBotGefEditPart toBotGefEditPart = editor.getEditPart("container1", DNodeContainerEditPart.class);
        reconnectEdge("edge2", toBotGefEditPart, false);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that when changing the edge routing style to rectilinear, the edge
     * is still centered toward its source.
     */
    public void testSrcChangingRoutingStyle() {
        openDiagram(REPRESENTATION_NAME_ROUTING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("edge2", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        assertEdgeHasExpectedSrcAnchor((SWTBotGefConnectionEditPart) botGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that when changing the edge routing style to rectilinear, the edge
     * is still centered toward its target.
     */
    public void testTgtChangingRoutingStyle() {
        openDiagram(REPRESENTATION_NAME_ROUTING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("edge1", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        assertEdgeHasExpectedTgtAnchor((SWTBotGefConnectionEditPart) botGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge source that should be
     * centered. Border node case.
     */
    public void testUserMoveCenteredSrcOnBorderNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("border4", DNode4EditPart.class);
        moveEdgeConnection("edge1", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge source that should be
     * centered. Container node case.
     */
    public void testUserMoveCenteredSrcOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container2", DNodeContainerEditPart.class);
        moveEdgeConnection("edge3", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge source that should be
     * centered. Node case.
     */
    public void testUserMoveCenteredSrcOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node1", DNodeEditPart.class);
        moveEdgeConnection("edge5", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge target that should be
     * centered. Border node case.
     */
    public void testUserMoveCenteredTgtOnBorderNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("border1", DNode4EditPart.class);
        moveEdgeConnection("edge2", botGefEditPart, false, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge target that should be
     * centered. Container case.
     */
    public void testUserMoveCenteredTgtOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container1", DNodeContainerEditPart.class);
        moveEdgeConnection("edge4", botGefEditPart, false, BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge target that should be
     * centered. Node case.
     */
    public void testUserMoveCenteredTgtOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node2", DNodeEditPart.class);
        moveEdgeConnection("edge6", botGefEditPart, false, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge6", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user can move the edge source which is not centered.
     * Border node case. Deactivated since draw2D try to keep the edge straight,
     * so the move is not perceptible.
     */
    public void _testUserMoveFreeSrcOnBorderNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("border3", DNode4EditPart.class);
        moveEdgeConnection("edge2", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, TOP_LEFT_CORNER);
    }

    /**
     * Test that the end user can move the edge source which is not centered.
     * Container case.
     */
    public void testUserMoveFreeSrcOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container4", DNodeContainerEditPart.class);
        moveEdgeConnection("edge4", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, TOP_LEFT_CORNER);
    }

    /**
     * Test that the end user can move the edge source which is not centered.
     * Node case.
     */
    public void testUserMoveFreeSrcOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node3", DNodeEditPart.class);
        moveEdgeConnection("edge6", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge6", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, TOP_LEFT_CORNER);
    }

    /**
     * Test that the end user can move the edge target which is not centered.
     * Border node case.
     */
    public void testUserMoveFreeTgtOnBorderNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("border2", DNode4EditPart.class);
        moveEdgeConnection("edge1", botGefEditPart, false, BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, BOTTOM_RIGHT_CORNER);
    }

    /**
     * Test that the end user can move the edge target which is not centered.
     * Container case.
     */
    public void testUserMoveFreeTgtOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container3", DNodeContainerEditPart.class);
        moveEdgeConnection("edge3", botGefEditPart, false, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, TOP_LEFT_CORNER);
    }

    /**
     * Test that the end user can move the edge target which is not centered.
     * Node case.
     */
    public void testUserMoveFreeTgtOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node4", DNodeEditPart.class);
        moveEdgeConnection("edge5", botGefEditPart, false, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, TOP_LEFT_CORNER);
    }

    /**
     * 
     * @param gefConnectionEditPart
     * @param routingStyle
     */
    private void changeRoutingStyle(SWTBotGefConnectionEditPart gefConnectionEditPart, String routingStyle) {
        gefConnectionEditPart.select();
        SWTBotUtils.waitAllUiEvents();
        // select the routing style with the diagram menu
        editor.clickContextMenu(routingStyle);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * 
     * @param from
     * @param to
     */
    private void reconnectEdge(String edgeName, SWTBotGefEditPart toBotGefEditPart, boolean source) {
        SWTBotGefConnectionEditPart edgeConnectionEditPartBot = (SWTBotGefConnectionEditPart) editor.getEditPart(edgeName, DEdgeEditPart.class);
        ViewEdgeFigure viewEdgeFigure = (ViewEdgeFigure) edgeConnectionEditPartBot.part().getFigure();
        int id = 0;
        if (!source) {
            id = viewEdgeFigure.getPoints().size() - 1;
        }
        Point fromPoint = viewEdgeFigure.getPoints().getPoint(id);
        Point toPoint = editor.getBounds(toBotGefEditPart).getTopRight();
        toPoint.translate(-1, 1);
        edgeConnectionEditPartBot.select();
        editor.drag(fromPoint, toPoint);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * @param string
     * @param botGefEditPart
     * @param b
     * @param dimension
     */
    private void moveEdgeConnection(String edgeName, SWTBotGefEditPart botGefEditPart, boolean source, PrecisionPoint anchor) {
        SWTBotGefConnectionEditPart edgeConnectionEditPartBot = (SWTBotGefConnectionEditPart) editor.getEditPart(edgeName, DEdgeEditPart.class);
        ViewEdgeFigure viewEdgeFigure = (ViewEdgeFigure) edgeConnectionEditPartBot.part().getFigure();
        int id = 0;
        if (!source) {
            id = viewEdgeFigure.getPoints().size() - 1;
        }
        Point fromPoint = viewEdgeFigure.getPoints().getPoint(id);
        Rectangle figureBounds = editor.getBounds(botGefEditPart);
        Point toPoint = figureBounds.getTopLeft().getCopy();
        toPoint.setX((int) (toPoint.x + Math.round(figureBounds.width * anchor.preciseX())));
        toPoint.setY((int) (toPoint.y + Math.round(figureBounds.height * anchor.preciseY())));
        edgeConnectionEditPartBot.select();
        editor.drag(fromPoint, toPoint);
        SWTBotUtils.waitAllUiEvents();

    }

    /**
     * 
     */
    private void openDiagram(String representationName) {
        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_DESCRIPTION_NAME)
                .selectRepresentationInstance(representationName, UIDiagramRepresentation.class).open();

        editor = diagram.getEditor();

    }

    private void assertEdgeHasExpectedTgtAnchor(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart, PrecisionPoint expectedAnchor) {
        SWTBotGefEditPart targetSwtBotGefEditPart = swtBotGefConnectionEditPart.target();

        ConnectionAnchor connectionAnchor = ((NodeEditPart) targetSwtBotGefEditPart.part()).getTargetConnectionAnchor(swtBotGefConnectionEditPart.part());

        assertEquals("Wrong edge target anchor", "(" + expectedAnchor.preciseX() + "," + expectedAnchor.preciseY() + ")", ((SlidableAnchor) connectionAnchor).getTerminal());

        Connection connection = (Connection) swtBotGefConnectionEditPart.part().getFigure();
        PointList pointList = connection.getPoints();
        Point lineOrigin = pointList.getPoint(pointList.size() - 2);
        Point realTargetConnection = pointList.getPoint(pointList.size() - 1);

        Point expectedLineTerminus = getProportionalPoint(getAbsoluteBounds((IGraphicalEditPart) targetSwtBotGefEditPart.part()), expectedAnchor);

        Option<Point> option = GraphicalHelper.getIntersection(lineOrigin, expectedLineTerminus, (IGraphicalEditPart) targetSwtBotGefEditPart.part(), false);
        if (option.some()) {
            assertConnectionEndPointEquals("Wrong edge target connection", option.get(), realTargetConnection);
        }

    }

    private void assertEdgeHasExpectedSrcAnchor(ConnectionEditPart connectionPart, NodeEditPart sourceEditPart, PrecisionPoint expectedAnchor) {

        ConnectionAnchor connectionAnchor = sourceEditPart.getSourceConnectionAnchor(connectionPart);

        assertEquals("Wrong edge source anchor", "(" + expectedAnchor.preciseX() + "," + expectedAnchor.preciseY() + ")", ((SlidableAnchor) connectionAnchor).getTerminal());

        Connection connection = (Connection) connectionPart.getFigure();
        PointList pointList = connection.getPoints();
        Point lineOrigin = pointList.getPoint(1);
        Point realSourceConnection = pointList.getPoint(0);

        Point expectedLineTerminus = getProportionalPoint(getAbsoluteBounds((IGraphicalEditPart) sourceEditPart), expectedAnchor);

        Option<Point> option = GraphicalHelper.getIntersection(lineOrigin, expectedLineTerminus, (IGraphicalEditPart) sourceEditPart, false);
        if (option.some()) {
            assertConnectionEndPointEquals("Wrong edge source connection", option.get(), realSourceConnection);
        }

    }

    private void assertEdgeHasExpectedSrcAnchor(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart, PrecisionPoint expectedAnchor) {

        assertEdgeHasExpectedSrcAnchor((ConnectionEditPart) swtBotGefConnectionEditPart.part(), (NodeEditPart) swtBotGefConnectionEditPart.source().part(), expectedAnchor);

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

    /**
     * Get expected relative coordinates between the anchor and the first
     * bendpoint (the connection).
     * 
     * @param expectedAnchor
     *            the expected anchor.
     * @param expectedBendpoint
     *            the expected bendpoint.
     * @param nodeEditPart
     *            the node edit part.
     * @return the relative location.
     */
    private Dimension getExpectedBendpointRelativeCoordinates(PrecisionPoint expectedAnchor, PrecisionPoint expectedBendpoint, NodeEditPart nodeEditPart) {
        Dimension size = nodeEditPart.getFigure().getBounds().getSize().getCopy();
        Dimension anchor = nodeEditPart.getFigure().getBounds().getSize().getCopy();
        anchor.scale(expectedAnchor.preciseX(), expectedAnchor.preciseY());
        size.scale(expectedBendpoint.preciseX(), expectedBendpoint.preciseY());
        return size.getShrinked(anchor);
    }

    private DEdgeEditPart getSingleDEdgeFrom(NodeEditPart sourcePart) {

        bot.waitUntil(new WaitEdgeCreationCondition(sourcePart));

        assertEquals(1, ((NodeEditPart) sourcePart).getSourceConnections().size());
        ConnectionEditPart edge = (ConnectionEditPart) ((NodeEditPart) sourcePart).getSourceConnections().get(0);
        assertTrue(edge instanceof DEdgeEditPart);
        return (DEdgeEditPart) edge;
    }

    private void createEdge(IGraphicalEditPart source, PrecisionPoint sourcePosition, IGraphicalEditPart target, PrecisionPoint targetPosition) {
        Point sourcePoint = getProportionalPoint(getAbsoluteBounds(source), sourcePosition);
        Point targetPoint = getProportionalPoint(getAbsoluteBounds(target), targetPosition);

        editor.activateTool("Create Edge");
        editor.click(sourcePoint);
        editor.click(targetPoint);
    }

    private Rectangle getAbsoluteBounds(IGraphicalEditPart part) {
        IFigure figure = part.getFigure();
        Rectangle r = figure.getBounds().getCopy();
        figure.getParent().translateToAbsolute(r);
        return r;
    }

    private Point getProportionalPoint(Rectangle bounds, PrecisionPoint proportions) {
        Point result = bounds.getTopLeft().getCopy();
        long xOffest = Math.round(bounds.width * proportions.preciseX());
        long yOffset = Math.round(bounds.height * proportions.preciseY());
        result.translate(new Dimension((int) xOffest, (int) yOffset));
        return result;
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        diagram = null;
        super.tearDown();
    }

    private class WaitEdgeCreationCondition extends DefaultCondition {
        private NodeEditPart part;

        public WaitEdgeCreationCondition(NodeEditPart part) {
            this.part = part;
        }

        @Override
        public boolean test() throws Exception {
            return part.getSourceConnections().size() == 1;
        }

        @Override
        public String getFailureMessage() {
            return "";
        }
    }

}
