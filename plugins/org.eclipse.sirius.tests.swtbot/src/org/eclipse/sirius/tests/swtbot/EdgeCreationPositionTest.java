/*******************************************************************************
 * Copyright (c) 2010-2019 THALES GLOBAL SERVICES and others..
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

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Tests for tickets #1423, #1523 and #2185: when creating an edge between two elements, the edge's position should
 * match the start/end points the user specified with the mouse instead of always pointing to the centers of the
 * sourc/target elements.
 * 
 * @author pcdavid
 */
public class EdgeCreationPositionTest extends AbstractSiriusSwtBotGefTestCase {
    /** The viewpoint name. */
    protected static final String VIEWPOINT_NAME = "TC2185";

    private static final PrecisionPoint TOP_LEFT_CORNER = new PrecisionPoint(0.1, 0.1);

    private static final PrecisionPoint BOTTOM_RIGHT_CORNER = new PrecisionPoint(0.9, 0.9);

    private static final PrecisionPoint BOTTOM_LEFT_CORNER = new PrecisionPoint(0.1, 0.9);

    private static final String MODEL = "tc-2185.ecore";

    private static final String SESSION_FILE = "tc-2185.aird";

    private static final String DATA_UNIT_DIR = "data/unit/tc-2185_edge_creation_position/";

    private static final String FILE_DIR = "models/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, "models/" + MODEL, "models/" + SESSION_FILE, "description/tc-2185.odesign");
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

    }

    /**
     * Open the diagram with the given <code>name</code>
     * 
     * @param name
     *            The name of the diagram to open.
     */
    protected void openDiagram(String name) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), VIEWPOINT_NAME + " " + name, name, DDiagram.class);
        editor.setSnapToGrid(false);
        editor.zoom(ZoomLevel.ZOOM_100);
    }

    /**
     * Open the diagram with the given <code>name</code>
     * 
     * @param name
     *            The name of the diagram to open.
     * @param zoomLevel
     *            the zoom to use
     */
    protected void openDiagram(String name, ZoomLevel zoomLevel) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), VIEWPOINT_NAME + " " + name, name, DDiagram.class);
        editor.setSnapToGrid(false);
        editor.zoom(zoomLevel);
    }

    /** */
    public void test_Node() {
        createEdgeAndValidateAnchors("Node", "A", AbstractDiagramNodeEditPart.class, "B", AbstractDiagramNodeEditPart.class);
    }

    /**
     * Same as test_Container_Aligned() but with a specific zoom.
     */
    public void test_Node_WithZoom() {
        createEdgeAndValidateAnchors("Node", "A", AbstractDiagramNodeEditPart.class, TOP_LEFT_CORNER, "B", AbstractDiagramNodeEditPart.class, BOTTOM_RIGHT_CORNER, ZoomLevel.ZOOM_125);
    }

    /** */
    public void test_Node_WithRectilinearEdge() {
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, true);
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_LINE_STYLE, EdgeRouting.MANHATTAN);
        createEdgeAndValidateAnchors("Node", "A", AbstractDiagramNodeEditPart.class, new PrecisionPoint(0.5, 0.5), "B", AbstractDiagramNodeEditPart.class, TOP_LEFT_CORNER);
    }

    /** */
    public void test_Node_Aligned() {
        createEdgeAndValidateAnchors("Node", "A", AbstractDiagramNodeEditPart.class, new PrecisionPoint(0.98, 0.2737), "B", AbstractDiagramNodeEditPart.class, new PrecisionPoint(0.02, 0.35));
    }

    /** */
    public void test_Container() {
        createEdgeAndValidateAnchors("Container", "A", AbstractDiagramContainerEditPart.class, "B", AbstractDiagramContainerEditPart.class);
    }

    /**
     * Test Bendpoints of an edge created with border Node as source and target.
     */
    public void test_BorderedEdgeAndContainer() {
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, true);
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_LINE_STYLE, EdgeRouting.MANHATTAN);
        createBorderedEdgeAndValidateAnchors("Container", "A", AbstractDiagramContainerEditPart.class, "B", AbstractDiagramContainerEditPart.class);
    }

    /**
     * Same as test_Container() but with specific location that reveals a bug for snapToGrid (see bugzilla 519305).
     */
    public void test_Container_Aligned() {
        createEdgeAndValidateAnchors("Container", "A", AbstractDiagramContainerEditPart.class, new PrecisionPoint(0.98, 0.2737), "B", AbstractDiagramContainerEditPart.class,
                new PrecisionPoint(0.02, 0.2737));
    }

    /**
     * Same as test_Container_Aligned() but with a specific zoom.
     */
    public void test_Container_Aligned_WithZoom() {
        createEdgeAndValidateAnchors("Container", "A", AbstractDiagramContainerEditPart.class, new PrecisionPoint(0.98, 0.2737), "B", AbstractDiagramContainerEditPart.class,
                new PrecisionPoint(0.02, 0.2737), ZoomLevel.ZOOM_175);
    }

    /** */
    public void test_List() {
        createEdgeAndValidateAnchors("List", "A", AbstractDiagramListEditPart.class, "B", AbstractDiagramListEditPart.class);
    }

    /** */
    public void test_List_Aligned() {
        createEdgeAndValidateAnchors("List", "A", AbstractDiagramListEditPart.class, new PrecisionPoint(1.0, 0.638095238095238), "B", AbstractDiagramListEditPart.class,
                new PrecisionPoint(0.0, 0.4448669201520912));
    }

    /** */
    public void test_Node_in_Container() {
        createEdgeAndValidateAnchors("Node in Container", "C", AbstractDiagramNodeEditPart.class, "D", AbstractDiagramNodeEditPart.class);
    }

    /** */
    public void test_Container_in_Container() {
        createEdgeAndValidateAnchors("Container in Container", "C", AbstractDiagramContainerEditPart.class, "D", AbstractDiagramContainerEditPart.class);
    }

    /** */
    public void test_Container_in_Container_Aligned() {
        createEdgeAndValidateAnchors("Container in Container", "C", AbstractDiagramContainerEditPart.class, new PrecisionPoint(1.0, 0.18672199170124482), "D", AbstractDiagramContainerEditPart.class,
                new PrecisionPoint(0.0, 0.1262135922330097));
    }

    /** */
    public void test_Bordered_Node_on_Container() {
        createEdgeAndValidateAnchors("Bordered Node on Container", "C", AbstractDiagramBorderNodeEditPart.class, "D", AbstractDiagramBorderNodeEditPart.class);
    }

    /** */
    public void test_Bordered_Node_on_Node() {
        createEdgeAndValidateAnchors("Bordered Node on Node", "C", AbstractDiagramBorderNodeEditPart.class, "D", AbstractDiagramBorderNodeEditPart.class);
    }

    /** */
    public void test_Bordered_Node_on_Node_Aligned() {
        createEdgeAndValidateAnchors("Bordered Node on Node", "C", AbstractDiagramBorderNodeEditPart.class, new PrecisionPoint(0.98, 0.7038834951456311), "D", AbstractDiagramBorderNodeEditPart.class,
                new PrecisionPoint(0.02, 0.4975124378109453));
    }

    /**
     * Open the diagram <code>diagramName</code>, create an edge between <code>sourceName</code> and
     * <code>targetName</code> and validate the source and target anchors.
     * 
     * @param diagramName
     *            The name of the diagram to open
     * @param sourceName
     *            The name of the source
     * @param expectedSourceType
     *            The type of the expected source edit part
     * @param targetName
     *            The name of the target
     * @param expectedTargetType
     *            The type of the expected target edit part
     */
    private void createEdgeAndValidateAnchors(String diagramName, String sourceName, Class<? extends EditPart> expectedSourceType, String targetName, Class<? extends EditPart> expectedTargetType) {
        createEdgeAndValidateAnchors(diagramName, sourceName, expectedSourceType, TOP_LEFT_CORNER, targetName, expectedTargetType, BOTTOM_RIGHT_CORNER);
    }

    /**
     * Open the diagram <code>diagramName</code>, create an edge between <code>sourceName</code> and
     * <code>targetName</code> and validate the source and target anchors.
     * 
     * @param diagramName
     *            The name of the diagram to open
     * @param sourceName
     *            The name of the source
     * @param expectedSourceType
     *            The type of the expected source edit part
     * @param sourcePosition
     *            The position for the first point of the edge (source)
     * @param targetName
     *            The name of the target
     * @param expectedTargetType
     *            The type of the expected target edit part
     * @param targetPosition
     *            The position for the last point of the edge (target)
     */
    protected void createEdgeAndValidateAnchors(String diagramName, String sourceName, Class<? extends EditPart> expectedSourceType, PrecisionPoint sourcePosition, String targetName,
            Class<? extends EditPart> expectedTargetType, PrecisionPoint targetPosition) {
        createEdgeAndValidateAnchors(diagramName, sourceName, expectedSourceType, sourcePosition, targetName, expectedTargetType, targetPosition, ZoomLevel.ZOOM_100);
    }

    /**
     * Open the diagram <code>diagramName</code>, create an edge between <code>sourceName</code> and
     * <code>targetName</code> and validate the source and target anchors.
     * 
     * @param diagramName
     *            The name of the diagram to open
     * @param sourceName
     *            The name of the source
     * @param expectedSourceType
     *            The type of the expected source edit part
     * @param sourcePosition
     *            The position for the first point of the edge (source)
     * @param targetName
     *            The name of the target
     * @param expectedTargetType
     *            The type of the expected target edit part
     * @param targetPosition
     *            The position for the last point of the edge (target)
     * @param zoomLevel
     *            the zoom to use
     */
    protected void createEdgeAndValidateAnchors(String diagramName, String sourceName, Class<? extends EditPart> expectedSourceType, PrecisionPoint sourcePosition, String targetName,
            Class<? extends EditPart> expectedTargetType, PrecisionPoint targetPosition, ZoomLevel zoomLevel) {
        openDiagram(diagramName, zoomLevel);
        IGraphicalEditPart sourcePart = (IGraphicalEditPart) editor.getEditPart(sourceName, expectedSourceType).part();
        IGraphicalEditPart targetPart = (IGraphicalEditPart) editor.getEditPart(targetName, expectedTargetType).part();
        createEdge(sourcePart, sourcePosition, targetPart, targetPosition, getCreateEdgeToolName());
        DEdgeEditPart edge = getSingleDEdgeFrom((NodeEditPart) sourcePart);
        assertAreValidAnchors(sourcePart, sourcePosition, targetPart, targetPosition, edge);
        if (!ZoomLevel.ZOOM_100.equals(zoomLevel)) {
            // Reset to original zoom to avoid problem in further tests
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Open the diagram <code>diagramName</code>, create an edge between <code>sourceName</code> and
     * <code>targetName</code> including border Node creation and validate the source and target anchors.
     * 
     * @param diagramName
     *            The name of the diagram to open
     * @param sourceName
     *            The name of the source
     * @param expectedSourceType
     *            The type of the expected source edit part
     * @param targetName
     *            The name of the target
     * @param expectedTargetType
     *            The type of the expected target edit part
     */
    private void createBorderedEdgeAndValidateAnchors(String diagramName, String sourceName, Class<? extends EditPart> expectedSourceType, String targetName,
            Class<? extends EditPart> expectedTargetType) {
        openDiagram(diagramName);
        IGraphicalEditPart sourcePart = (IGraphicalEditPart) editor.getEditPart(sourceName, expectedSourceType).part();
        IGraphicalEditPart targetPart = (IGraphicalEditPart) editor.getEditPart(targetName, expectedTargetType).part();
        createEdge(sourcePart, BOTTOM_RIGHT_CORNER, targetPart, BOTTOM_LEFT_CORNER, getCreateBorderEdgeToolName());

        List<SWTBotGefConnectionEditPart> connectionsEditPart = editor.getConnectionsEditPart();
        assertEquals(1, connectionsEditPart.size());
        SWTBotGefConnectionEditPart conection = connectionsEditPart.get(0);
        assertTrue(conection.part() instanceof DEdgeEditPart);
        DEdgeEditPart edge = (DEdgeEditPart) conection.part();

        SWTBotGefEditPart borderSource = conection.source();
        SWTBotGefEditPart borderTarget = conection.target();

        assertAreValidAnchorsAndBendpoints((IGraphicalEditPart) borderSource.part(), (IGraphicalEditPart) borderTarget.part(), edge);
    }

    /** */
    public void test_Node_then_Container() {
        test_Node();
        bot.menu("Edit").menu("Undo " + getCreateEdgeToolName()).click();
        test_Container();
    }

    /**
     * @param source
     *            The source of the edge
     * @param sourcePosition
     *            The position for the first point of the edge (source)
     * @param target
     *            The target of the edge
     * @param targetPosition
     *            The position for the last point of the edge (target)
     * @param edge
     *            Edge to consider
     */
    protected void assertAreValidAnchors(IGraphicalEditPart source, PrecisionPoint sourcePosition, IGraphicalEditPart target, PrecisionPoint targetPosition, DEdgeEditPart edge) {
        assertAreValidAnchorsAndBendpoints(source, target, edge);
    }

    /**
     * Check that anchor of edge are valid and bend-points of edge are consistent with draw2D points.
     * 
     * @param source
     *            The source of the edge
     * @param target
     *            The target of the edge
     * @param edge
     *            Edge to consider
     */
    protected void assertAreValidAnchorsAndBendpoints(IGraphicalEditPart source, IGraphicalEditPart target, DEdgeEditPart edge) {
        assertIsValidAnchor((NodeEditPart) source, edge, true);
        assertIsValidAnchor((NodeEditPart) target, edge, false);

        final Connection connectionFigure = edge.getConnectionFigure();
        // Here sometimes the connection is not already initialized and the
        // coordinates are (0, 0); (100, 100)
        bot.waitUntil(new ICondition() {
            @Override
            public boolean test() throws Exception {
                return connectionFigure.getPoints().getFirstPoint().x != 0;
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Connection points are not initialized.";
            }
        });

        Point sourcePoint = GraphicalHelper.getAnchorPoint(source, ((Edge) edge.getModel()).getSourceAnchor());
        Point targetPoint = GraphicalHelper.getAnchorPoint(target, ((Edge) edge.getModel()).getTargetAnchor());
        PointList anchorsLine = new PointList();
        anchorsLine.addPoint(sourcePoint);
        anchorsLine.addPoint(targetPoint);

        Optional<Point> sourceIntersection = GraphicalHelper.getIntersection(sourcePoint, targetPoint, source, false);
        assertTrue("Intersection should exist between source and edge : " + GraphicalHelper.getAbsoluteBoundsIn100Percent(source) + " and " + sourcePoint + "-->" + targetPoint,
                sourceIntersection.isPresent());

        Optional<Point> targetIntersection = GraphicalHelper.getIntersection(sourcePoint, targetPoint, target, true);
        assertTrue("Intersection should exist between target and edge : " + GraphicalHelper.getAbsoluteBoundsIn100Percent(target) + " and " + sourcePoint + "-->" + targetPoint,
                targetIntersection.isPresent());

        // GMF bendpoints
        @SuppressWarnings("unchecked")
        List<Bendpoint> routingConstraint = (List<Bendpoint>) connectionFigure.getRoutingConstraint();
        assertNotNull(routingConstraint);

        // Draw 2D bendpoints
        PointList figurePoints = connectionFigure.getPoints();

        // check draw2D points coordinates
        assertEquals("Wrong x coordinate for source.", sourceIntersection.get().x, figurePoints.getFirstPoint().x, 1);
        assertEquals("Wrong y coordinate for source.", sourceIntersection.get().y, figurePoints.getFirstPoint().y, 1);
        assertEquals("Wrong x coordinate for target.", targetIntersection.get().x, figurePoints.getLastPoint().x, 1);
        assertEquals("Wrong y coordinate for target.", targetIntersection.get().y, figurePoints.getLastPoint().y, 1);

        // check GMF bendpoints coordinates
        assertEquals("Bad number of bendpoints after edge creation", routingConstraint.size(), figurePoints.size());

        for (int i = 0; i < routingConstraint.size(); i++) {
            Point gmfPoint = routingConstraint.get(i).getLocation();
            assertEquals("Wrong x gmf coordinate for point number " + i + ".", gmfPoint.x, figurePoints.getPoint(i).x, 1);
            assertEquals("Wrong y gmf coordinate for point number " + i + ".", gmfPoint.y, figurePoints.getPoint(i).y, 1);
        }
    }

    /**
     * @param part
     *            An extremity of the edge (source or target)
     * @param edge
     *            Edge to consider
     * @param isSource
     *            true if the part is the source of the edge
     */
    protected void assertIsValidAnchor(NodeEditPart part, DEdgeEditPart edge, boolean isSource) {
        if (isSource) {
            assertIsValidAnchor(part.getSourceConnectionAnchor(edge));
        } else {
            assertIsValidAnchor(part.getTargetConnectionAnchor(edge));
        }
    }

    private void assertIsValidAnchor(ConnectionAnchor anchor) {
        assertTrue(anchor instanceof SlidableAnchor);
        assertTrue(((SlidableAnchor) anchor).getTerminal().length() > 0);
    }

    /**
     * Get the outgoing edge from this edit part.
     * 
     * @param sourcePart
     *            The source edit part.
     * @return the outgoing edge.
     */
    protected DEdgeEditPart getSingleDEdgeFrom(NodeEditPart sourcePart) {
        assertEquals(1, sourcePart.getSourceConnections().size());
        ConnectionEditPart edge = (ConnectionEditPart) sourcePart.getSourceConnections().get(0);
        assertTrue(edge instanceof DEdgeEditPart);
        return (DEdgeEditPart) edge;
    }

    private void createEdge(IGraphicalEditPart source, PrecisionPoint sourcePosition, IGraphicalEditPart target, PrecisionPoint targetPosition, String toolName) {
        Point sourcePoint = getProportionalPoint(GraphicalHelper.getAbsoluteBounds(source), sourcePosition);
        Point targetPoint = getProportionalPoint(GraphicalHelper.getAbsoluteBounds(target), targetPosition);

        ICondition done = new OperationDoneCondition();
        editor.activateTool(toolName);
        editor.click(sourcePoint, true);
        editor.click(targetPoint, true);
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(done);
    }

    /**
     * Return the name of the create edge tool to use.
     * 
     * @return the name of the create edge tool to use.
     */
    protected String getCreateEdgeToolName() {
        return "Super";
    }

    /**
     * Return the name of the create border edge tool to use.
     * 
     * @return the name of the create edge tool to use.
     */
    protected String getCreateBorderEdgeToolName() {
        return "SuperWithBorderNode";
    }

    private Point getProportionalPoint(Rectangle bounds, PrecisionPoint proportions) {
        Point result = bounds.getTopLeft().getCopy();
        long xOffest = Math.round(bounds.width * proportions.preciseX());
        long yOffset = Math.round(bounds.height * proportions.preciseY());
        result.translate(new Dimension((int) xOffest, (int) yOffset));
        return result;
    }
}
