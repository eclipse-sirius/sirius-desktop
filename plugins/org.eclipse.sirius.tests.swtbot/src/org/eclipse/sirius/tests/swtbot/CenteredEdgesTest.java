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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
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

    private static final String REPRESENTATION_NAME_RESIZE = "resizeTest";

    private static final String REPRESENTATION_NAME_RESIZE_2 = "resizeTest2";

    private static final String REPRESENTATION_NAME_AUTO_SIZE = "auto-size";

    private static final String REPRESENTATION_NAME_RECTILINEAR_CASES = "rectilinearCases";

    private static final String RECTILINEAR_STYLE_ROUTING = "Rectilinear Style Routing";

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
     * Test that the Rectilinear edge is correctly centered on the target border
     * node when using the reconnect tool.
     */
    public void testRectilinearEdgeReconnectionOnCenteredBorderNode() {
        openDiagram(REPRESENTATION_NAME_RECONNECT);
        SWTBotGefEditPart toBotGefEditPart = editor.getEditPart("border1", DNode4EditPart.class);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("edge1", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        reconnectEdge("edge1", toBotGefEditPart, false);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the Rectilinear edge is correctly centered on the target
     * container when using the reconnect tool.
     */
    public void testRectilinearEdgeReconnectionOnCenteredContainer() {
        openDiagram(REPRESENTATION_NAME_RECONNECT);
        SWTBotGefEditPart toBotGefEditPart = editor.getEditPart("container1", DNodeContainerEditPart.class);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("edge2", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) botGefEditPart, RECTILINEAR_STYLE_ROUTING);
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
     * Test that the end user cannot move a rectilinear edge end with two
     * bendpoints and only one centered. See #448739 Comment1. Container case.
     */
    public void testUserCannotMoveRectilinearFreeTgtOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container3", DNodeContainerEditPart.class);

        SWTBotGefEditPart edgeBotGefEditPart = editor.getEditPart("edge3", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) edgeBotGefEditPart, RECTILINEAR_STYLE_ROUTING);

        moveEdgeConnection("edge3", botGefEditPart, false, TOP_LEFT_CORNER);

        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move a rectilinear edge end with two
     * bendpoints and only one centered. See #448739 Comment1. Node case.
     */
    public void testUserCannotMoveRectilinearFreeTgtOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node4", DNodeEditPart.class);

        SWTBotGefEditPart edgeBotGefEditPart = editor.getEditPart("edge5", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) edgeBotGefEditPart, RECTILINEAR_STYLE_ROUTING);

        moveEdgeConnection("edge5", botGefEditPart, false, TOP_LEFT_CORNER);

        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that when resizing the edge source with a source and a target
     * 'auto-size', the edge is still centered. See <a
     * href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c5">Bug
     * 448739#c5</a>.
     */
    public void testResizingAutoSizeContainer() {
        openDiagram(REPRESENTATION_NAME_RESIZE);
        SWTBotGefEditPart containerBotGefEditPart = editor.getEditPart("container2", DNodeContainerEditPart.class);
        containerBotGefEditPart.select();

        IFigure figure = ((GraphicalEditPart) containerBotGefEditPart.part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();
        containerBotGefEditPart.resize(PositionConstants.SOUTH_EAST, 300, 80);

        // we make sure the figure has been resized
        bot.waitUntil(new WaitFigureResizedCondition(boundsBefore, figure));

        SWTBotGefConnectionEditPart edgeSwtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edgeSwtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that when resizing a shape over edge bendpoints, the edge is still
     * centered. See <a
     * href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c8">Bug
     * 448739#c8</a> and <a
     * href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c10">Bug
     * 448739#c10</a>.
     */
    public void testResizingContainerWithInViewBendpoints() {
        openDiagram(REPRESENTATION_NAME_RESIZE_2);
        SWTBotGefEditPart containerBotGefEditPart = editor.getEditPart("container1container1container1container1container1container1", DNodeContainerEditPart.class);
        containerBotGefEditPart.select();

        IFigure figure = ((GraphicalEditPart) containerBotGefEditPart.part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();
        containerBotGefEditPart.resize(PositionConstants.NORTH_EAST, 677, 255);

        // we make sure the figure has been resized
        bot.waitUntil(new WaitFigureResizedCondition(boundsBefore, figure));

        SWTBotGefConnectionEditPart edgeSwtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edgeSwtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedSrcAnchor(edgeSwtBotGefEditPart, new PrecisionPoint(0.5, 0.5));

        // we also check that we have only 3 bendpoints left.

        assertEquals("The edge should have 3 bendpoints", 3, ((Connection) edgeSwtBotGefEditPart.part().getFigure()).getPoints().size());
    }

    /**
     * Test that the arrange all keep the edge centered.
     */
    public void testArrangeAllOnStraightEdges() {
        openDiagram(REPRESENTATION_NAME_MOVING);

        SWTBotGefConnectionEditPart edge3BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(edge3BotGefConnectionEditPart);
        editor.clickContextMenu("Arrange All");
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(checkEditPartMoved);
        edge3BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(edge3BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge1BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(edge1BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge2BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edge2BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge4BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edge4BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge5BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(edge5BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge6BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge6", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edge6BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

    }

    /**
     * Test that rectilinear edges with two or tree bend-points are centered for
     * both ends when changing the style from NONE to BOTH.
     */
    public void testRectilinearSpecificCases() {
        openDiagram(REPRESENTATION_NAME_RECTILINEAR_CASES);
        SWTBotUtils.waitAllUiEvents();

        SWTBotGefConnectionEditPart edge3BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        centerEdgeEnds(edge3BotGefConnectionEditPart);
        assertEdgeHasExpectedSrcAnchor(edge3BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(edge3BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge1BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        centerEdgeEnds(edge1BotGefConnectionEditPart);
        assertEdgeHasExpectedSrcAnchor(edge1BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(edge1BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge2BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        centerEdgeEnds(edge2BotGefConnectionEditPart);
        assertEdgeHasExpectedTgtAnchor(edge2BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedSrcAnchor(edge2BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge4BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        centerEdgeEnds(edge4BotGefConnectionEditPart);
        assertEdgeHasExpectedTgtAnchor(edge4BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedSrcAnchor(edge4BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

    }

    private void centerEdgeEnds(SWTBotGefConnectionEditPart connectionEditPart) {

        final DEdgeEditPart editPart = (DEdgeEditPart) connectionEditPart.part();
        TransactionalEditingDomain transactionalEditingDomain = editPart.getEditingDomain();
        transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
            @Override
            protected void doExecute() {
                EdgeStyle edgeStyle = ((DEdge) ((Edge) editPart.getModel()).getElement()).getOwnedStyle();
                edgeStyle.setCentered(CenteringStyle.BOTH);
            }
        });

        bot.waitUntil(new WaitEdgeCenteringCondition((ViewEdgeFigure) connectionEditPart.part().getFigure()));
    }

    /**
     * Test that the arrange all keep the edge centered.
     */
    public void testArrangeAllOnRectilinearEdges() {
        openDiagram(REPRESENTATION_NAME_AUTO_SIZE);

        editor.clickContextMenu("Arrange All");
        SWTBotUtils.waitAllUiEvents();

        SWTBotGefConnectionEditPart edge3BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge1BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge2BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge4BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge5BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge6BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge6", DEdgeEditPart.class);

        assertEdgeHasExpectedSrcAnchor(edge3BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedSrcAnchor(edge1BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(edge2BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(edge4BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedSrcAnchor(edge5BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(edge6BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

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
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);

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

    private DEdgeEditPart getSingleDEdgeFrom(NodeEditPart sourcePart) {

        bot.waitUntil(new WaitEdgeCreationCondition(sourcePart));

        assertEquals(1, sourcePart.getSourceConnections().size());
        ConnectionEditPart edge = (ConnectionEditPart) sourcePart.getSourceConnections().get(0);
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

    /**
     * Condition to wait until a figure bounds are changed.
     * 
     * @author fbarbin
     *
     */
    private class WaitFigureResizedCondition extends DefaultCondition {

        private Rectangle before;

        private IFigure figure;

        /**
         * Constructor.
         * 
         * @param before
         *            the bounds before the resize. We will wait until the new
         *            given figure bounds are different.
         * @param figure
         *            the figure.
         */
        public WaitFigureResizedCondition(Rectangle before, IFigure figure) {
            this.before = before;
            this.figure = figure;
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.swtbot.swt.finder.waits.ICondition#test()
         */
        @Override
        public boolean test() throws Exception {
            return !figure.getBounds().equals(before);
        }

        /*
         * (non-Javadoc)
         * @see
         * org.eclipse.swtbot.swt.finder.waits.ICondition#getFailureMessage()
         */
        @Override
        public String getFailureMessage() {
            return "the figure should be resized";
        }

    }

    /**
     * Condition to wait until an edge is centered on both ends.
     * 
     * @author fbarbin
     *
     */
    private class WaitEdgeCenteringCondition extends DefaultCondition {

        private ViewEdgeFigure figure;

        /**
         * Constructor.
         * 
         * @param figure
         *            the edge figure.
         */
        public WaitEdgeCenteringCondition(ViewEdgeFigure figure) {
            this.figure = figure;
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.swtbot.swt.finder.waits.ICondition#test()
         */
        @Override
        public boolean test() throws Exception {
            return figure.isSourceCentered() && figure.isTargetCentered();
        }

        /*
         * (non-Javadoc)
         * @see
         * org.eclipse.swtbot.swt.finder.waits.ICondition#getFailureMessage()
         */
        @Override
        public String getFailureMessage() {
            return "the edge should be centered for both source and target";
        }

    }

}
