/*******************************************************************************
 * Copyright (c) 2008, 2023 THALES GLOBAL SERVICES and others.
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

import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.ConnectorStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

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

    private static final String REPRESENTATION_NAME_ROUTING_BOTH_CENTERED = "routingStyleBothCentered";

    private static final String REPRESENTATION_NAME_CHANGING_ROUTING = "changeRoutingStyle";

    private static final String REPRESENTATION_NAME_MOVING = "moving";

    private static final String REPRESENTATION_NAME_RESIZE = "resizeTest";

    private static final String REPRESENTATION_NAME_RESIZE_2 = "resizeTest2";

    private static final String REPRESENTATION_NAME_RESIZE_BORDER_NODE = "resizeBorderNode";

    private static final String REPRESENTATION_NAME_AUTO_SIZE = "auto-size";

    private static final String REPRESENTATION_NAME_RECTILINEAR_CASES = "rectilinearCases";

    private static final String RECTILINEAR_STYLE_ROUTING = "Rectilinear Style Routing";

    private static final String OBLIQUE_STYLE_ROUTING = "Oblique Style Routing";

    private static final String PROPERTIES = "Properties";

    private static final String STYLE = "Style";

    private static final String APPEARANCE = "Appearance";

    private static final String STYLES = "Styles:";

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
     * Test that the edge is correctly centered on the source border node when using an edge creation tool.
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
     * Test that the edge is correctly centered on the target border node when using an edge creation tool.
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
     * Test that the edge is correctly centered on the source node when using an edge creation tool.
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
     * Test that the edge is correctly centered on the target node when using an edge creation tool.
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
     * Test that the edge is correctly centered on the source container when using an edge creation tool.
     */
    public void testEdgeCreationToolOnCenteredSrcContainer() {
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefEditPart srcEditPart = editor.getEditPart("container1", DNodeContainerEditPart.class);
        SWTBotGefEditPart tgtEditPart = editor.getEditPart("container3", DNodeContainerEditPart.class);
        createEdge((IGraphicalEditPart) srcEditPart.part(), TOP_LEFT_CORNER, (IGraphicalEditPart) tgtEditPart.part(), BOTTOM_RIGHT_CORNER);
        DEdgeEditPart editPart = getSingleDEdgeFrom((NodeEditPart) srcEditPart.part());

        assertEdgeHasExpectedSrcAnchor(editPart, (NodeEditPart) srcEditPart.part(), new PrecisionPoint(0.5, 0.5), false);
    }

    /**
     * Test that the edge is correctly centered on the target container when using an edge creation tool.
     */
    public void testEdgeCreationToolOnCenteredTgtContainer() {
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefEditPart srcEditPart = editor.getEditPart("container4", DNodeContainerEditPart.class);
        SWTBotGefEditPart tgtEditPart = editor.getEditPart("container2", DNodeContainerEditPart.class);
        createEdge((IGraphicalEditPart) srcEditPart.part(), TOP_LEFT_CORNER, (IGraphicalEditPart) tgtEditPart.part(), BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("newEdge", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5), false);
    }

    /**
     * Test that the edge is correctly centered on the target node when using the reconnect tool.
     */
    public void testEdgeReconnectionOnCenteredNode() {
        openDiagram(REPRESENTATION_NAME_RECONNECT);

        SWTBotGefEditPart toBotGefEditPart = editor.getEditPart("node2", DNodeEditPart.class);
        reconnectEdge("edge3", toBotGefEditPart, false);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the edge is correctly centered on the target border node when using the reconnect tool.
     */
    public void testEdgeReconnectionOnCenteredBorderNode() {
        openDiagram(REPRESENTATION_NAME_RECONNECT);
        SWTBotGefEditPart toBotGefEditPart = editor.getEditPart("border1", DNode4EditPart.class);
        reconnectEdge("edge1", toBotGefEditPart, false);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the edge is correctly centered on the target container when using the reconnect tool.
     */
    public void testEdgeReconnectionOnCenteredContainer() {
        openDiagram(REPRESENTATION_NAME_RECONNECT);
        SWTBotGefEditPart toBotGefEditPart = editor.getEditPart("container1", DNodeContainerEditPart.class);
        reconnectEdge("edge2", toBotGefEditPart, false);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5), false);
    }

    /**
     * Test that the Rectilinear edge is correctly centered on the target border node when using the reconnect tool.
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
     * Test that the Rectilinear edge is correctly centered on the target container when using the reconnect tool.
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
     * Test that when changing the edge routing style to rectilinear, the edge is still centered toward its source.
     */
    public void testSrcChangingRoutingStyle() {
        openDiagram(REPRESENTATION_NAME_ROUTING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("edge2", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        assertEdgeHasExpectedSrcAnchor((SWTBotGefConnectionEditPart) botGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the oblique edge transformed from rectilinear edge is correctly centered toward its source and is
     * oblique.
     */
    public void testSrcChangingRoutingStyleFromRectToOblique() {
        openDiagram(REPRESENTATION_NAME_ROUTING);
        SWTBotGefConnectionEditPart botGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        changeRoutingStyle(botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        Connection connection = (Connection) botGefEditPart.part().getFigure();
        assertEquals("The rectilinear edge should contain only 4 points.", 4, connection.getPoints().size());
        changeRoutingStyle(botGefEditPart, OBLIQUE_STYLE_ROUTING);
        assertEdgeHasExpectedSrcAnchor(botGefEditPart, new PrecisionPoint(0.5, 0.5));
        assertEquals("The oblique edge should contain only 2 points.", 2, connection.getPoints().size());
    }

    /**
     * Test that when changing the edge routing style to rectilinear, the edge is still centered toward its target.
     */
    public void testTgtChangingRoutingStyle() {
        openDiagram(REPRESENTATION_NAME_ROUTING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("edge1", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        assertEdgeHasExpectedTgtAnchor((SWTBotGefConnectionEditPart) botGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the oblique edge transformed from rectilinear edge is correctly centered toward its target and is
     * oblique.
     */
    public void testTgtChangingRoutingStyleFromRectToOblique() {
        openDiagram(REPRESENTATION_NAME_ROUTING);
        SWTBotGefConnectionEditPart botGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        changeRoutingStyle(botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        Connection connection = (Connection) botGefEditPart.part().getFigure();
        assertEquals("The rectilinear edge should contain only 4 points.", 4, connection.getPoints().size());
        changeRoutingStyle(botGefEditPart, OBLIQUE_STYLE_ROUTING);
        assertEdgeHasExpectedTgtAnchor(botGefEditPart, new PrecisionPoint(0.5, 0.5));
        assertEquals("The oblique edge should contain only 2 points.", 2, connection.getPoints().size());
    }

    /**
     * Test that when changing the edge routing style to rectilinear, the edge is still centered toward its target and
     * its source.
     */
    public void testTgtAndSrcChangingRoutingStyle() {
        openDiagram(REPRESENTATION_NAME_ROUTING_BOTH_CENTERED);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("edge1", DEdgeEditPart.class);
        changeRoutingStyle((SWTBotGefConnectionEditPart) botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        assertEdgeHasExpectedTgtAnchor((SWTBotGefConnectionEditPart) botGefEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedSrcAnchor((SWTBotGefConnectionEditPart) botGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the oblique edge transformed from rectilinear edge is correctly centered toward its target and is
     * oblique.
     */
    public void testTgtAndSrcChangingRoutingStyleFromRectToOblique() {
        openDiagram(REPRESENTATION_NAME_ROUTING_BOTH_CENTERED);
        SWTBotGefConnectionEditPart botGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        changeRoutingStyle(botGefEditPart, RECTILINEAR_STYLE_ROUTING);
        Connection connection = (Connection) botGefEditPart.part().getFigure();
        assertEquals("The rectilinear edge should contain only 4 points.", 4, connection.getPoints().size());
        changeRoutingStyle(botGefEditPart, OBLIQUE_STYLE_ROUTING);
        assertEdgeHasExpectedTgtAnchor(botGefEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedSrcAnchor(botGefEditPart, new PrecisionPoint(0.5, 0.5));
        assertEquals("The oblique edge should contain only 2 points.", 2, connection.getPoints().size());
    }

    /**
     * Test that when changing the edge routing style to rectilinear (from the menu == only one notification filter by
     * RefreshEdgeLayoutNotificationFilter), the edge is still centered toward its target. Check that the GMF point is
     * the same as the draw2d point. It should be the case because in current scenario we can use the figure as
     * reference. This problem of GMF point != draw2d point should exist only when the GMF data is used.
     */
    public void testTgtChangingRoutingStyleWithGMFAutoSize() {
        openDiagram(REPRESENTATION_NAME_CHANGING_ROUTING);
        SWTBotGefConnectionEditPart botEdgeEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        changeRoutingStyle(botEdgeEditPart, RECTILINEAR_STYLE_ROUTING);
        assertEdgeHasExpectedTgtAnchor(botEdgeEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that when changing the edge routing style to rectilinear (from style tab of properties view == 2
     * notifications used by RefreshEdgeLayoutNotificationFilter), the edge is still centered toward its target. Check
     * that the GMF point is the same as the draw2d point. It should be the case because in current scenario we can use
     * the figure as reference. This problem of GMF point != draw2d point should exist only when the GMF data is used.
     * 
     */
    public void testTgtChangingRoutingStyleFromStyleTabWithGMFAutoSize() {
        openDiagram(REPRESENTATION_NAME_CHANGING_ROUTING);
        SWTBotGefConnectionEditPart botEdgeEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        changeRoutingStyleFromTabStyleOfPropertiesView(botEdgeEditPart, EdgeRouting.MANHATTAN_LITERAL);
        assertEdgeHasExpectedTgtAnchor(botEdgeEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge source that should be centered. Border node case.
     */
    public void testUserMoveCenteredSrcOnBorderNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("border4", DNode4EditPart.class);
        moveEdgeConnection("edge1", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge source that should be centered. Container node case.
     */
    public void testUserMoveCenteredSrcOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container2", DNodeContainerEditPart.class);
        moveEdgeConnection("edge3", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge source that should be centered. Node case.
     */
    public void testUserMoveCenteredSrcOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node1", DNodeEditPart.class);
        moveEdgeConnection("edge5", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge target that should be centered. Border node case.
     */
    public void testUserMoveCenteredTgtOnBorderNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("border1", DNode4EditPart.class);
        moveEdgeConnection("edge2", botGefEditPart, false, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge target that should be centered. Container case.
     */
    public void testUserMoveCenteredTgtOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container1", DNodeContainerEditPart.class);
        moveEdgeConnection("edge4", botGefEditPart, false, BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user cannot move the edge target that should be centered. Node case.
     */
    public void testUserMoveCenteredTgtOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node2", DNodeEditPart.class);
        moveEdgeConnection("edge6", botGefEditPart, false, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge6", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user can move the edge source which is not centered. Border node case. Deactivated since draw2D
     * try to keep the edge straight, so the move is not perceptible.
     */
    public void _testUserMoveFreeSrcOnBorderNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("border3", DNode4EditPart.class);
        moveEdgeConnection("edge2", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, TOP_LEFT_CORNER);
    }

    /**
     * Test that the end user can move the edge source which is not centered. Container case.
     */
    public void testUserMoveFreeSrcOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container4", DNodeContainerEditPart.class);
        moveEdgeConnection("edge4", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        // In this case, the GMF point is not the same as the draw2d point. This
        // "bug" does not concern the centered edge as the source is not
        // centered.
        PrecisionPoint expectedAnchor = getExpectedAnchor(botGefEditPart, TOP_LEFT_CORNER);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, expectedAnchor, false);
        // The target, other side of the move, must be always centered. But
        // there is the same problem about GMF point (TODO compare with node
        // case).
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5), false);
    }

    /**
     * Test that the end user can move the edge source which is not centered. Node case.
     */
    public void testUserMoveFreeSrcOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node3", DNodeEditPart.class);
        moveEdgeConnection("edge6", botGefEditPart, true, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge6", DEdgeEditPart.class);
        // In this case, the GMF point is not the same as the draw2d point. This
        // "bug" does not concern the centered edge as the source is not
        // centered.
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, TOP_LEFT_CORNER, false);
        // The target, other side of the move, must be always centered.
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that the end user can move the edge target which is not centered. Border node case.
     */
    public void testUserMoveFreeTgtOnBorderNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("border2", DNode4EditPart.class);
        moveEdgeConnection("edge1", botGefEditPart, false, BOTTOM_RIGHT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, BOTTOM_RIGHT_CORNER, false);
        // TODO: In this case the source is no longer centered as the router
        // flats the edge.
    }

    /**
     * Test that the end user can move the edge target which is not centered. Container case.
     */
    public void testUserMoveFreeTgtOnContainer() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("container3", DNodeContainerEditPart.class);
        moveEdgeConnection("edge3", botGefEditPart, false, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        // In this case, the GMF point is not the same as the draw2d point. This
        // "bug" does not concern the centered edge as the target is not
        // centered.
        PrecisionPoint expectedAnchor = getExpectedAnchor(botGefEditPart, TOP_LEFT_CORNER);
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, expectedAnchor, false);
        // The source, other side of the move, must be always centered. But
        // there is the same problem about GMF point (TODO compare with node
        // case).
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5), false);
    }

    /**
     * Test that the end user can move the edge target which is not centered. Node case.
     */
    public void testUserMoveFreeTgtOnNode() {
        openDiagram(REPRESENTATION_NAME_MOVING);
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("node4", DNodeEditPart.class);
        moveEdgeConnection("edge5", botGefEditPart, false, TOP_LEFT_CORNER);
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        // In this case, the GMF point is not the same as the draw2d point. This
        // "bug" does not concern the centered edge as the target is not
        // centered.
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart, TOP_LEFT_CORNER, false);
        // The source, other side of the move, must be always centered.
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart, new PrecisionPoint(0.5, 0.5), true);
    }

    /**
     * Test that the end user cannot move a rectilinear edge end with two bendpoints and only one centered. See #448739
     * Comment1. Container case.
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
     * Test that the end user cannot move a rectilinear edge end with two bendpoints and only one centered. See #448739
     * Comment1. Node case.
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
     * Test that when resizing the edge source with a source and a target 'auto-size', the edge is still centered. See
     * <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c5">Bug 448739#c5</a>.
     */
    public void testResizingAutoSizeContainer() {
        openDiagram(REPRESENTATION_NAME_RESIZE);
        SWTBotGefEditPart containerBotGefEditPart = editor.getEditPart("container2", DNodeContainerEditPart.class);
        containerBotGefEditPart.select();

        IFigure figure = ((GraphicalEditPart) containerBotGefEditPart.part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();
        containerBotGefEditPart.resize(PositionConstants.SOUTH_EAST, 300, 80);

        // we make sure the figure has been resized
        bot.waitUntil(new WaitFigureBoundsChangedCondition(boundsBefore, figure));

        SWTBotGefConnectionEditPart edgeSwtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edgeSwtBotGefEditPart, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Test that when resizing a border node, the bendpoints of the not centered edge, with a null anchor, are not
     * moved. See <a https://bugs.eclipse.org/bugs/show_bug.cgi?id=466384">Bug 466384</a>.
     */
    public void testResizingNullAnchorSourceBorderNode() {
        openDiagram(REPRESENTATION_NAME_RESIZE_BORDER_NODE);
        SWTBotGefEditPart borderNodeBotGefEditPart = editor.getEditPart("border3", AbstractDiagramBorderNodeEditPart.class);
        borderNodeBotGefEditPart.select();

        SWTBotGefConnectionEditPart edgeSwtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        PointList edge2PointListBefore = getEdgePointList(edgeSwtBotGefEditPart);

        IFigure figure = ((GraphicalEditPart) borderNodeBotGefEditPart.part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();
        borderNodeBotGefEditPart.resize(PositionConstants.SOUTH, 0, 200);

        // we make sure the figure has been resized
        bot.waitUntil(new WaitFigureBoundsChangedCondition(boundsBefore, figure));
        // The source is no longer centered, and the bendpoints of edges must no
        // be changed (bug 441424)
        checkPointsListAfterResizing(edgeSwtBotGefEditPart, edge2PointListBefore, false);
    }

    /**
     * Test that when resizing a border node, the bendpoints of the centered edge are fix and the edge is still
     * centered. See <a https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c18">Bug 448739#c18</a>.
     */
    public void testResizingCenteredTargetBorderNodeWithZoom200() {
        resizeCenteredTargetBorderNodeWithZoom(ZoomLevel.ZOOM_200);
    }

    /**
     * Test that when resizing a border node, the bendpoints of the centered edge are fix and the edge is still
     * centered. See <a https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c25">Bug 448739#c25</a>.
     */
    public void testResizingCenteredTargetBorderNodeWithZoom125() {
        resizeCenteredTargetBorderNodeWithZoom(ZoomLevel.ZOOM_125);
    }

    /**
     * Test that when resizing a border node, the bendpoints of the centered edge are fix and the edge is still
     * centered. See <a https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c18">Bug 448739#c18</a>.
     * 
     * @param zoomLevel
     *            The zoom to apply on the editor
     */
    protected void resizeCenteredTargetBorderNodeWithZoom(ZoomLevel zoomLevel) {
        openDiagram(REPRESENTATION_NAME_RESIZE_BORDER_NODE);
        editor.zoom(zoomLevel);
        try {
            SWTBotGefEditPart borderNodeBotGefEditPart = editor.getEditPart("border1", AbstractDiagramBorderNodeEditPart.class);
            borderNodeBotGefEditPart.select();
            editor.reveal(borderNodeBotGefEditPart.part());

            SWTBotGefConnectionEditPart edgeSwtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
            PointList edge2PointListBefore = getEdgePointList(edgeSwtBotGefEditPart);

            IFigure figure = ((GraphicalEditPart) borderNodeBotGefEditPart.part()).getFigure();
            Rectangle boundsBefore = figure.getBounds().getCopy();
            borderNodeBotGefEditPart.resize(PositionConstants.SOUTH, 0, (int) (200 * zoomLevel.getAmount()));

            // we make sure the figure has been resized
            bot.waitUntil(new WaitFigureBoundsChangedCondition(boundsBefore, figure));

            assertEdgeHasExpectedTgtAnchor(edgeSwtBotGefEditPart, new PrecisionPoint(0.5, 0.5));

            // Bendpoints of edges must no be changed (bug 441424), except the
            // last point as it is centered.
            checkPointsListAfterResizing(edgeSwtBotGefEditPart, edge2PointListBefore, true);
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test that when resizing a border node over another border node, the bendpoints of the centered edge are fix and
     * the edge is still centered. See <a https://bugs.eclipse.org/bugs/show_bug.cgi?id=466422">Bug 466422</a>.
     */
    public void testResizingCenteredTargetBorderNodeOverAnotherNode() {
        openDiagram(REPRESENTATION_NAME_RESIZE_BORDER_NODE);
        SWTBotGefEditPart border1NodeBotGefEditPart = editor.getEditPart("border1", AbstractDiagramBorderNodeEditPart.class);
        border1NodeBotGefEditPart.select();

        SWTBotGefConnectionEditPart edgeSwtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        PointList edge2PointListBefore = getEdgePointList(edgeSwtBotGefEditPart);

        IFigure figure = ((GraphicalEditPart) border1NodeBotGefEditPart.part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();
        // Resize border1 over border2
        border1NodeBotGefEditPart.resize(PositionConstants.NORTH, 0, 160);

        // we make sure the figure has been resized (and moved)
        bot.waitUntil(new WaitFigureBoundsChangedCondition(boundsBefore, figure));

        assertEdgeHasExpectedTgtAnchor(edgeSwtBotGefEditPart, new PrecisionPoint(0.5, 0.5));

        // Bendpoints of edges must no be changed (bug 441424), except the
        // last point as it is centered.
        checkPointsListAfterResizing(edgeSwtBotGefEditPart, edge2PointListBefore, true);
    }

    /**
     * Test that when resizing a border node over another border node, the overlapped border node is not moved. See <a
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=466456">Bug 466456</a>.
     */
    public void testResizingTargetBorderNodeOverAnotherNode() {
        openDiagram(REPRESENTATION_NAME_RESIZE_BORDER_NODE);
        SWTBotGefEditPart border1NodeBotGefEditPart = editor.getEditPart("border1", AbstractDiagramBorderNodeEditPart.class);
        Rectangle border1BoundsBefore = ((GraphicalEditPart) border1NodeBotGefEditPart.part()).getFigure().getBounds().getCopy();

        SWTBotGefEditPart border2NodeBotGefEditPart = editor.getEditPart("border2", AbstractDiagramBorderNodeEditPart.class);
        border2NodeBotGefEditPart.select();

        IFigure figure = ((GraphicalEditPart) border2NodeBotGefEditPart.part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();
        // Resize border2 over border1
        border2NodeBotGefEditPart.resize(PositionConstants.SOUTH, 0, 125);

        // we make sure the figure has been resized (and moved)
        bot.waitUntil(new WaitFigureBoundsChangedCondition(boundsBefore, figure));

        assertEquals("The overlapped border node should not be moved.", border1BoundsBefore, ((GraphicalEditPart) border1NodeBotGefEditPart.part()).getFigure().getBounds().getCopy());
    }

    /**
     * Test that when resizing a shape over edge bendpoints, the edge is still centered. See
     * <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c8">Bug 448739#c8</a> and
     * <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=448739#c10">Bug 448739#c10</a>.
     */
    public void testResizingContainerWithInViewBendpoints() {
        openDiagram(REPRESENTATION_NAME_RESIZE_2);
        SWTBotGefEditPart containerBotGefEditPart = editor.getEditPart("container1container1container1container1container1container1", DNodeContainerEditPart.class);
        containerBotGefEditPart.select();

        IFigure figure = ((GraphicalEditPart) containerBotGefEditPart.part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();
        containerBotGefEditPart.resize(PositionConstants.NORTH_EAST, 677, 255);

        // we make sure the figure has been resized
        bot.waitUntil(new WaitFigureBoundsChangedCondition(boundsBefore, figure));

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
        arrangeAll();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(checkEditPartMoved);
        edge3BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(edge3BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5), false);

        SWTBotGefConnectionEditPart edge1BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(edge1BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge2BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edge2BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge4BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edge4BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5), false);

        SWTBotGefConnectionEditPart edge5BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(edge5BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

        SWTBotGefConnectionEditPart edge6BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge6", DEdgeEditPart.class);
        assertEdgeHasExpectedTgtAnchor(edge6BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

    }

    /**
     * Test that rectilinear edges with two or tree bend-points are centered for both ends when changing the style from
     * NONE to BOTH.
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

        arrangeAll();
        SWTBotUtils.waitAllUiEvents();

        SWTBotGefConnectionEditPart edge3BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge3", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge1BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge2BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge4BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge4", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge5BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge5", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart edge6BotGefConnectionEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge6", DEdgeEditPart.class);

        assertEdgeHasExpectedSrcAnchor(edge3BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5), false);
        assertEdgeHasExpectedSrcAnchor(edge1BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(edge2BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5), false);
        assertEdgeHasExpectedTgtAnchor(edge4BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5), false);
        assertEdgeHasExpectedSrcAnchor(edge5BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(edge6BotGefConnectionEditPart, new PrecisionPoint(0.5, 0.5));

    }

    /**
     * This test launches a specific tool that arranges some nodes and edges:
     * <UL>
     * <LI>Source node of edge A is moved</LI>
     * <LI>Target node of edge A is moved</LI>
     * <LI>Bendpoints of edge A are recomputed</LI>
     * <LI>Routing style of edge A is changed (both in Sirius style and GMF style)</LI>
     * <LI>Routing style of edge B is changed (both in Sirius style and GMF style)</LI>
     * </UL>
     * . This kind of layout can be done when someone make specific layout action. The goal is to check that in this
     * condition, the edge A is correctly layouted.
     */
    public void testSpecificLayout() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "borderNodesDiagram", "new borderNodesDiagram", DDiagram.class);
        IFigure figure = ((GraphicalEditPart) editor.getEditPart("Class1", DNodeEditPart.class).part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();

        editor.activateTool("Move elements");
        editor.click(10, 10);
        // we make sure the at least one figure has been moved
        bot.waitUntil(new WaitFigureBoundsChangedCondition(boundsBefore, figure));

        // Check that the edge from Class1 to Class11 is correctly layouted
        // (only two bendpoints)
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("toClass11", DEdgeEditPart.class);
        ConnectionEditPart connectionEditPart = (ConnectionEditPart) swtBotGefEditPart.part();
        Connection connection = (Connection) connectionEditPart.getFigure();
        assertEquals("Wrong nomber of points for edge figure.", 2, connection.getPoints().size());
        assertEquals("Wrong nomber of GMF bendpoints.", 2, ((RelativeBendpoints) ((Edge) connectionEditPart.getNotationView()).getBendpoints()).getPoints().size());
    }

    /**
     * This test launches a specific tool that arranges some nodes and edge:
     * <UL>
     * <LI>Auto-sized target node of an edge is moved</LI>
     * <LI>Bendpoints of edge are recomputed</LI>
     * <LI>Routing style of edge is changed (both in Sirius style and GMF style)</LI>
     * </UL>
     * . This kind of layout can be done when someone make specific layout action. The goal is to check that in this
     * condition, the edge is correctly layouted.
     */
    public void testSpecificVerticalLayout() {
        openDiagram("specificVerticalLayout");
        IFigure figure = ((GraphicalEditPart) editor.getEditPart("container1container1container1container1container1container1", DNodeContainerEditPart.class).part()).getFigure();
        Rectangle boundsBefore = figure.getBounds().getCopy();

        editor.activateTool("Move elements");
        editor.click(10, 10);
        // we make sure the at least one figure has been moved
        bot.waitUntil(new WaitFigureBoundsChangedCondition(boundsBefore, figure));

        // Check that the edge from container2 to
        // container1container1container1container1container1container1 is
        // correctly layouted (only two bendpoints)
        SWTBotGefConnectionEditPart swtBotGefEditPart = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        ConnectionEditPart connectionEditPart = (ConnectionEditPart) swtBotGefEditPart.part();
        Connection connection = (Connection) connectionEditPart.getFigure();
        assertEquals("Wrong nomber of points for edge figure.", 2, connection.getPoints().size());
        assertEquals("Wrong nomber of GMF bendpoints.", 2, ((RelativeBendpoints) ((Edge) connectionEditPart.getNotationView()).getBendpoints()).getPoints().size());
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
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class, true, true);
    }

    private void assertEdgeHasExpectedTgtAnchor(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart, PrecisionPoint expectedAnchor) {
        assertEdgeHasExpectedTgtAnchor(swtBotGefConnectionEditPart, expectedAnchor, true);
    }

    private void assertEdgeHasExpectedTgtAnchor(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart, PrecisionPoint expectedAnchor, boolean checkGMFPoint) {
        SWTBotGefEditPart targetSwtBotGefEditPart = swtBotGefConnectionEditPart.target();

        ConnectionAnchor connectionAnchor = ((NodeEditPart) targetSwtBotGefEditPart.part()).getTargetConnectionAnchor(swtBotGefConnectionEditPart.part());

        assertEquals("Wrong edge target anchor", "(" + expectedAnchor.preciseX() + "," + expectedAnchor.preciseY() + ")", ((SlidableAnchor) connectionAnchor).getTerminal());

        Connection connection = (Connection) swtBotGefConnectionEditPart.part().getFigure();
        PointList pointList = connection.getPoints();
        Point lineOrigin = pointList.getPoint(pointList.size() - 2);
        Point realTargetConnection = pointList.getPoint(pointList.size() - 1);

        // Get figure bounds
        PrecisionRectangle figureBounds = null;
        if (targetSwtBotGefEditPart.part() instanceof DNodeContainerEditPart) {
            IFigure figure = ((DNodeContainerEditPart) targetSwtBotGefEditPart.part()).getFigure();
            if (figure instanceof NodeFigure) {
                Rectangle bounds = ((NodeFigure) figure).getHandleBounds().getCopy();
                figureBounds = new PrecisionRectangle(bounds);
            }
        }
        if (figureBounds == null) {
            figureBounds = getAbsoluteBounds((IGraphicalEditPart) targetSwtBotGefEditPart.part());
        }

        PrecisionPoint expectedLineTerminus = getProportionalPoint(figureBounds, expectedAnchor);
        connection.translateToRelative(expectedLineTerminus);

        Optional<Point> option = GraphicalHelper.getIntersection(lineOrigin, expectedLineTerminus, (IGraphicalEditPart) targetSwtBotGefEditPart.part(), false);
        if (option.isPresent()) {
            assertConnectionEndPointEquals("Wrong edge target connection", option.get(), realTargetConnection);
        }
        if (checkGMFPoint) {
            // Check the last GMF points compared to last draw2D point
            Edge edge = (Edge) swtBotGefConnectionEditPart.part().getModel();
            List<Point> pointsFromSource = GMFHelper.getPointsFromSource(swtBotGefConnectionEditPart.part());
            Point lastGMFPoint = pointsFromSource.get(pointsFromSource.size() - 1);
            assertConnectionEndPointEquals("The last GMF point should be the same as last draw2d point.", realTargetConnection, lastGMFPoint);
        }

    }

    private void assertEdgeHasExpectedSrcAnchor(ConnectionEditPart connectionPart, NodeEditPart sourceEditPart, PrecisionPoint expectedAnchor) {
        assertEdgeHasExpectedSrcAnchor(connectionPart, sourceEditPart, expectedAnchor, true);
    }

    private void assertEdgeHasExpectedSrcAnchor(ConnectionEditPart connectionPart, NodeEditPart sourceEditPart, PrecisionPoint expectedAnchor, boolean checkGMFPoint) {

        ConnectionAnchor connectionAnchor = sourceEditPart.getSourceConnectionAnchor(connectionPart);

        assertEquals("Wrong edge source anchor", "(" + expectedAnchor.preciseX() + "," + expectedAnchor.preciseY() + ")", ((SlidableAnchor) connectionAnchor).getTerminal());

        Connection connection = (Connection) connectionPart.getFigure();
        PointList pointList = connection.getPoints();
        Point lineOrigin = pointList.getPoint(1);
        Point realSourceConnection = pointList.getPoint(0);

        // Get figure bounds
        PrecisionRectangle figureBounds = null;
        IFigure figure = sourceEditPart.getFigure();
        if (figure instanceof NodeFigure) {
            Rectangle bounds = ((NodeFigure) figure).getHandleBounds().getCopy();
            figureBounds = new PrecisionRectangle(bounds);
        } else {
            figureBounds = getAbsoluteBounds((IGraphicalEditPart) sourceEditPart);
        }

        PrecisionPoint expectedLineTerminus = getProportionalPoint(figureBounds, expectedAnchor);

        Optional<Point> option = GraphicalHelper.getIntersection(lineOrigin, expectedLineTerminus, (IGraphicalEditPart) sourceEditPart, false);
        if (option.isPresent()) {
            assertConnectionEndPointEquals("Wrong edge source connection", option.get(), realSourceConnection);
        }
        if (checkGMFPoint) {
            // Check the first GMF points compared to first draw2D point
            Edge edge = (Edge) connectionPart.getModel();
            List<Point> pointsFromTarget = GMFHelper.getPointsFromTarget(connectionPart);
            Point firstGMFPoint = pointsFromTarget.get(0);
            assertConnectionEndPointEquals("The first GMF point should be the same as first draw2d point.", realSourceConnection, firstGMFPoint);
        }
    }

    private void assertEdgeHasExpectedSrcAnchor(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart, PrecisionPoint expectedAnchor) {

        assertEdgeHasExpectedSrcAnchor((ConnectionEditPart) swtBotGefConnectionEditPart.part(), (NodeEditPart) swtBotGefConnectionEditPart.source().part(), expectedAnchor);

    }

    private void assertEdgeHasExpectedSrcAnchor(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart, PrecisionPoint expectedAnchor, boolean checkGMFPoint) {

        assertEdgeHasExpectedSrcAnchor((ConnectionEditPart) swtBotGefConnectionEditPart.part(), (NodeEditPart) swtBotGefConnectionEditPart.source().part(), expectedAnchor, checkGMFPoint);

    }

    /**
     * Assert that the actual point is equal to the expected one with +/- 1 tolerance.
     * 
     * @param msg
     * @param expected
     * @param actual
     */
    private void assertConnectionEndPointEquals(String msg, Point expected, Point actual) {
        assertTrue(msg + ": expected " + expected + ", but was " + actual, actual.x() <= (expected.x() + 1) && actual.x() >= expected.x() - 1);
        assertTrue(msg + ": expected " + expected + ", but was " + actual, actual.y() <= (expected.y() + 1) && actual.y() >= expected.y() - 1);
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

    private PrecisionRectangle getAbsoluteBounds(IGraphicalEditPart part) {
        IFigure figure = part.getFigure();
        PrecisionRectangle r = new PrecisionRectangle(figure.getBounds());
        figure.getParent().translateToAbsolute(r);
        return r;
    }

    private PrecisionPoint getProportionalPoint(PrecisionRectangle bounds, PrecisionPoint proportions) {
        PrecisionPoint result = new PrecisionPoint(bounds.getTopLeft());
        double xOffest = bounds.preciseWidth() * proportions.preciseX();
        double yOffset = bounds.preciseHeight() * proportions.preciseY();
        result.translate(xOffest, yOffset);
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
    private class WaitFigureBoundsChangedCondition extends DefaultCondition {

        private Rectangle before;

        private IFigure figure;

        /**
         * Constructor.
         * 
         * @param before
         *            the bounds before the resize. We will wait until the new given figure bounds are different.
         * @param figure
         *            the figure.
         */
        public WaitFigureBoundsChangedCondition(Rectangle before, IFigure figure) {
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
         * @see org.eclipse.swtbot.swt.finder.waits.ICondition#getFailureMessage()
         */
        @Override
        public String getFailureMessage() {
            return "The bounds of the figure should be changed (resized or moved)";
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
         * @see org.eclipse.swtbot.swt.finder.waits.ICondition#getFailureMessage()
         */
        @Override
        public String getFailureMessage() {
            return "the edge should be centered for both source and target";
        }

    }

    private PointList getEdgePointList(SWTBotGefConnectionEditPart edgeEditPart) {
        return ((Connection) edgeEditPart.part().getFigure()).getPoints().getCopy();
    }

    // /**
    // * Check that the given edge didn't move during the shape resizing.
    // *
    // * @param string
    // * the edge label id.
    // * @param edge1PointListBefore
    // * the edge point list before resizing.
    // */
    // private void checkPointsListAfterResizing(String edgeId, PointList
    // edgePointListBefore, boolean ignoreLast) {
    // checkPointsListAfterResizing(edgeId, edgePointListBefore, ignoreLast);
    // }

    /**
     * Check that the given edge didn't move during the shape resizing.
     * 
     * @param string
     *            the edge label id.
     * @param edge1PointListBefore
     *            the edge point list before resizing.
     * @param delta
     *            In some conditions, zoom for example, the points list can be slightly different. This parameter allows
     *            to use a delta when comparing point.
     */
    private void checkPointsListAfterResizing(SWTBotGefConnectionEditPart edgeEditPart, PointList edgePointListBefore, boolean ignoreLast) {
        PointList afterPointList = getEdgePointList(edgeEditPart);
        assertEquals("The edge point list size is different", edgePointListBefore.size(), afterPointList.size());
        for (int i = 0; i < edgePointListBefore.size(); i++) {
            if (!ignoreLast || (ignoreLast && i != (edgePointListBefore.size() - 1))) {
                Point pointBefore = edgePointListBefore.getPoint(i);
                Point pointAfter = afterPointList.getPoint(i);
                assertEquals("The x coordinate of point #" + i + " is different after resizing: ", pointBefore.x, pointAfter.x);
                assertEquals("The y coordinate of point #" + i + " is different after resizing: ", pointBefore.y, pointAfter.y);
            }
        }
    }

    private void changeRoutingStyleFromTabStyleOfPropertiesView(SWTBotGefConnectionEditPart botEdgeEditPart, EdgeRouting routingStyle) {
        botEdgeEditPart.select();
        SWTBotUtils.waitAllUiEvents();
        // accesses to property view
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        SWTBotUtils.waitAllUiEvents();
        propertiesBot.setFocus();
        // accesses to tab Style
        SWTBotSiriusHelper.selectPropertyTabItem(STYLE, propertiesBot.bot());
        SWTBotTree tree = propertiesBot.bot().tree();
        // select routing syle <routingStyle> in combo
        tree.expandNode("Misc").select().getNode("Routing Style").doubleClick();
        SWTBotCCombo comboBox = propertiesBot.bot().ccomboBox();
        String routingStyleLitteral = routingStyle.getLiteral().substring(0, 1).toUpperCase().concat(routingStyle.getLiteral().substring(1, routingStyle.getLiteral().length()));
        comboBox.setSelection(routingStyleLitteral);
        // In Photon we need to press enter to leave the combo.
        if (TestsUtil.isPhotonPlatformOrLater()) {
            comboBox.pressShortcut(Keystrokes.CR);
        }
        // applied change with change focus
        SWTBotSiriusHelper.selectPropertyTabItem(APPEARANCE, propertiesBot.bot());

        checkRoutingStyleInAppearance(routingStyle);
        checkRoutingStyle(botEdgeEditPart, routingStyle);
    }

    private void checkRoutingStyleInAppearance(EdgeRouting routingStyle) {
        String appearanceLabel = "Oblique";
        if (routingStyle.equals(EdgeRouting.MANHATTAN_LITERAL)) {
            appearanceLabel = "Rectilinear";
        } else if (routingStyle.equals(EdgeRouting.TREE_LITERAL)) {
            appearanceLabel = "Tree";
        }
        assertEquals("The radio button oblique should be selected", true, bot.viewByTitle(PROPERTIES).bot().radioInGroup(appearanceLabel, STYLES).isSelected());
    }

    private void checkRoutingStyle(SWTBotGefConnectionEditPart botEdgeEditPart, EdgeRouting routingStyle) {
        Edge edgeGMF = (Edge) botEdgeEditPart.part().getModel();
        DEdge dedge = (DEdge) edgeGMF.getElement();
        assertEquals("The rooting style is not : " + routingStyle.getLiteral(), routingStyle, ((EdgeStyle) dedge.getStyle()).getRoutingStyle());
        Routing currentRouting = ((ConnectorStyle) edgeGMF.getStyles().get(0)).getRouting();
        String gmfRoutingStyleName;
        if (currentRouting.equals(Routing.MANUAL_LITERAL)) {
            gmfRoutingStyleName = "straight";
        } else if (currentRouting.equals(Routing.RECTILINEAR_LITERAL)) {
            gmfRoutingStyleName = "manhattan";
        } else {
            gmfRoutingStyleName = currentRouting.getLiteral().toLowerCase();
        }
        assertEquals("The GMF routing style is not the right", routingStyle.getLiteral(), gmfRoutingStyleName);
    }

    private PrecisionPoint getExpectedAnchor(SWTBotGefEditPart botGefEditPart, PrecisionPoint relativeToBounds) {
        IFigure figure = ((DNodeContainerEditPart) botGefEditPart.part()).getFigure();
        Rectangle figureBounds = null;
        PrecisionPoint relativePoint = new PrecisionPoint();
        Point anchorPoint = new PrecisionPoint();
        if (figure instanceof NodeFigure) {
            figureBounds = ((NodeFigure) figure).getHandleBounds().getCopy();
            // Compute Anchor position
            anchorPoint = figureBounds.getTopLeft().getCopy();
            anchorPoint.setX((int) (anchorPoint.x + Math.round(figureBounds.width * relativeToBounds.preciseX())));
            anchorPoint.setY((int) (anchorPoint.y + Math.round(figureBounds.height * relativeToBounds.preciseY())));
            // compute anchor relative position
            relativePoint.setPreciseX((anchorPoint.preciseX() - figureBounds.x) / figureBounds.width);
            relativePoint.setPreciseY((anchorPoint.preciseY() - figureBounds.y) / figureBounds.height);
        }
        return relativePoint;
    }

}
