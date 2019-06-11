/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.junit.Assert;

/**
 * Test bendpoints position and edge style after doing a drag and drop.
 * 
 * @author smonnier
 */
public class EdgeStabilityOnDragAndDropTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new 2123Diag";

    private static final String REPRESENTATION_NAME = "2123Diag";

    private static final String MODEL = "2123.ecore";

    private static final String SESSION_FILE = "2123.aird";

    private static final String VSM_FILE = "2123.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeStabilityOnDragAndDrop/";

    private static final String FILE_DIR = "/";

    private static final String[][] EDGES = { { "C2", "C1" }, { "C3", "C1" }, { "C3", "C2" }, { "C5", "C6" } };

    /**
     * Drop direction on the target element
     */
    private enum Direction {
        NORTH, SOUTH, WEST, EAST
    }

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        editor.setSnapToGrid(false);
    }

    /**
     * Get bendpoints of the first connection found between <code>sourceEditPartName</code> and
     * <code>targetEditPartName</code>.
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     * 
     * @return copy of bendpoints position
     */
    private PointList getBendpoints(String sourceEditPartName, String targetEditPartName) {
        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        return ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
    }

    /**
     * Check the stability of the first connection found between <code>sourceEditPartName</code> and
     * <code>targetEditPartName</code>. The edge is only moved from one side.
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     * @param originalPoints
     *            original points
     * @param expectedRoutingStyle
     *            expected routing style
     */
    private void checkEdgeMovedFromOneSide(String sourceEditPartName, String targetEditPartName, PointList originalPoints, EdgeRouting expectedRoutingStyle) {
        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList newPoints = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
        Assert.assertEquals("The number of bendpoints should be the same", originalPoints.size(), newPoints.size());

        // get routing style
        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        DEdge dedge = (DEdge) element;
        EdgeRouting edgeRouting = ((EdgeStyle) dedge.getStyle()).getRoutingStyle();
        assertEquals("The routing style is not " + expectedRoutingStyle.getLiteral(), expectedRoutingStyle, edgeRouting);

        // if edgeRouting == EdgeRouting.STRAIGHT then the first or the last
        // point has moved

        // if edgeRouting == EdgeRouting.MANHATTAN then the two first or
        // the two last points have moved

        Point originalFirstPoint = originalPoints.getFirstPoint();
        Point newFirstPoint = newPoints.getFirstPoint();
        if (originalFirstPoint.equals(newFirstPoint)) {
            // EdgeRouting.STRAIGHT: the last point has moved
            // EdgeRouting.MANHATTAN: the two last points have moved
            int end = edgeRouting == EdgeRouting.STRAIGHT_LITERAL ? originalPoints.size() - 1 : originalPoints.size() - 2;

            // unmoved points
            for (int i = 1; i < end; i++) {
                Point originalPoint = originalPoints.getPoint(i);
                Point newPoint = newPoints.getPoint(i);
                Assert.assertEquals("The two points at index " + i + " should be equal", originalPoint, newPoint);
            }

            // moved points
            for (int i = end; i < originalPoints.size(); i++) {
                Point originalPoint = originalPoints.getPoint(i);
                Point newPoint = newPoints.getPoint(i);
                Assert.assertNotEquals("The two points at index " + i + " should be different", originalPoint, newPoint);
            }
        } else {
            // EdgeRouting.STRAIGHT: the first point has moved
            // EdgeRouting.MANHATTAN: the two first points have moved
            int begin = edgeRouting == EdgeRouting.STRAIGHT_LITERAL ? 1 : 2;

            // moved points
            for (int i = 1; i < begin; i++) {
                Point originalPoint = originalPoints.getPoint(i);
                Point newPoint = newPoints.getPoint(i);
                Assert.assertNotEquals("The two points at index " + i + " should be different", originalPoint, newPoint);
            }

            // unmoved points
            for (int i = begin; i < originalPoints.size(); i++) {
                Point originalPoint = originalPoints.getPoint(i);
                Point newPoint = newPoints.getPoint(i);
                Assert.assertEquals("The two points at index " + i + " should be equal", originalPoint, newPoint);
            }
        }

        EdgeLayoutData data = SiriusLayoutDataManager.INSTANCE.getData(dedge, false);
        assertNull("SiriusLayoutDataManager should not store data of DEdge between " + sourceEditPartName + " and " + targetEditPartName + " anymore", data);
    }

    /**
     * Check the stability of the first connection found between <code>sourceEditPartName</code> and
     * <code>targetEditPartName</code>. The edge is shifted by a constant vector.
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     * @param originalPoints
     *            original points
     * @param expectedRoutingStyle
     *            expected routing style
     */
    private void checkEdgeShiftedByVector(String sourceEditPartName, String targetEditPartName, PointList originalPoints, EdgeRouting expectedRoutingStyle) {
        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList newPoints = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
        Assert.assertEquals("The number of bendpoints should be the same", originalPoints.size(), newPoints.size());

        // get routing style
        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        DEdge dedge = (DEdge) element;
        EdgeRouting edgeRouting = ((EdgeStyle) dedge.getStyle()).getRoutingStyle();
        assertEquals("The routing style is not " + expectedRoutingStyle.getLiteral(), expectedRoutingStyle, edgeRouting);

        Point originalFirstPoint = originalPoints.getFirstPoint();
        Point newFirstPoint = newPoints.getFirstPoint();

        int vectorX = newFirstPoint.x - originalFirstPoint.x;
        int vectorY = newFirstPoint.y - originalFirstPoint.y;

        for (int i = 1; i < originalPoints.size(); i++) {
            Point newPoint = newPoints.getPoint(i);
            Point originalPoint = originalPoints.getPoint(i);;
            assertEquals("X position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", newPoint.x, originalPoint.x + vectorX, 1);
            assertEquals("Y position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", newPoint.y, originalPoint.y + vectorY, 1);
        }

        EdgeLayoutData data = SiriusLayoutDataManager.INSTANCE.getData(dedge, false);
        assertNull("SiriusLayoutDataManager should not store data of DEdge between " + sourceEditPartName + " and " + targetEditPartName + " anymore", data);
    }

    /**
     * Check that the first connection found between <code>sourceEditPartName</code> and <code>targetEditPartName</code>
     * has not moved.
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     * @param originalPoints
     *            original points
     * @param expectedRoutingStyle
     *            expected routing style
     */
    private void checkUnmovedEdge(String sourceEditPartName, String targetEditPartName, PointList originalPoints, EdgeRouting expectedRoutingStyle) {
        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList newPoints = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
        assertEquals("The number of bendpoints should be the same", originalPoints.size(), newPoints.size());

        for (int i = 0; i < originalPoints.size(); i++) {
            Point point = newPoints.getPoint(i);
            Point expectedPoint = originalPoints.getPoint(i);;
            assertEquals("X position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", expectedPoint.x, point.x, 1);
            assertEquals("Y position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", expectedPoint.y, point.y, 1);
        }

        // get routing style
        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        DEdge dedge = (DEdge) element;
        EdgeRouting edgeRouting = ((EdgeStyle) dedge.getStyle()).getRoutingStyle();
        assertEquals("The routing style is not " + expectedRoutingStyle.getLiteral(), expectedRoutingStyle, edgeRouting);

        EdgeLayoutData data = SiriusLayoutDataManager.INSTANCE.getData(dedge, false);
        assertNull("SiriusLayoutDataManager should not store data of DEdge between " + sourceEditPartName + " and " + targetEditPartName + " anymore", data);
    }

    /**
     * Get the first connection found between <code>sourceEditPartName</code> and <code>targetEditPartName</code>.
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     * 
     * @return the connection found
     */
    private ConnectionEditPart getConnectionEditPart(String sourceEditPartName, String targetEditPartName) {
        List<SWTBotGefConnectionEditPart> connectionEditPartList = editor.getConnectionEditPart(editor.getEditPart(sourceEditPartName, AbstractDiagramBorderNodeEditPart.class),
                editor.getEditPart(targetEditPartName, AbstractDiagramBorderNodeEditPart.class));
        assertNotNull("There is no connection between " + sourceEditPartName + " and " + targetEditPartName, connectionEditPartList);
        assertEquals("There are more or less than 1 connection between " + sourceEditPartName + " and " + targetEditPartName, 1, connectionEditPartList.size());
        return connectionEditPartList.get(0).part();
    }

    /**
     * Drag and drop a border node to the north of another bordered node.
     */
    public void testEdgeStabilityOnDragAndDropOnNorth() {
        doTestEdgeStabilityOnDragAndDrop("P3", "C1", Direction.NORTH, false);
    }

    /**
     * Drag and drop a border node to the south of another bordered node.
     */
    public void testEdgeStabilityOnDragAndDropOnSouth() {
        doTestEdgeStabilityOnDragAndDrop("P3", "C1", Direction.SOUTH, false);
    }

    /**
     * Drag and drop a border node to the west of another bordered node.
     */
    public void testEdgeStabilityOnDragAndDropOnWest() {
        doTestEdgeStabilityOnDragAndDrop("P3", "C1", Direction.WEST, false);
    }

    /**
     * Drag and drop a border node to the east of another bordered node.
     */
    public void testEdgeStabilityOnDragAndDropOnEast() {
        doTestEdgeStabilityOnDragAndDrop("P3", "C1", Direction.EAST, false);
    }

    /**
     * Change style of edges and drag and and drop a border node to the north of another bordered node.
     */
    public void testEdgeStabilityOnDragAndDropOnNorthAfterStyleChange() {
        doTestEdgeStabilityOnDragAndDrop("P2", "C2", Direction.NORTH, true);
    }

    /**
     * Change style of edges and drag and and drop a border node to the south of another bordered node.
     */
    public void testEdgeStabilityOnDragAndDropOnSouthAfterStyleChange() {
        doTestEdgeStabilityOnDragAndDrop("P2", "C2", Direction.SOUTH, true);
    }

    /**
     * Change style of edges and drag and and drop a border node to the west of another bordered node.
     */
    public void testEdgeStabilityOnDragAndDropOnWestAfterStyleChange() {
        doTestEdgeStabilityOnDragAndDrop("P2", "C2", Direction.WEST, true);
    }

    /**
     * Change style of edges and drag and and drop a border node to the east of another bordered node.
     */
    public void testEdgeStabilityOnDragAndDropOnEastAfterStyleChange() {
        doTestEdgeStabilityOnDragAndDrop("P2", "C2", Direction.EAST, true);
    }

    /**
     * Drag and drop a border node to node contained into another with horizontal scrollbar.
     */
    public void testEdgeStabilityOnDragAndDropToContainedNode() {
        doTestEdgeStabilityOnDragAndDrop("P4", "C2", Direction.WEST, false);
    }

    /**
     * Drag and drop a source and target edge border node to another node.
     */
    public void testEdgeStabilityOnDragAndDropWithSourceAndTarget() {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            // Get bendpoints before drag and drop
            PointList originalPointsC5C6 = getBendpoints("C5", "C6");

            // Drag and drop C5 and C6 to package P1
            dragNorth("P1", "C5", "C6");

            // Check the connections bendpoints stability
            checkEdgeShiftedByVector("C5", "C6", originalPointsC5C6, EdgeRouting.MANHATTAN_LITERAL); // rectilinear

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Drag and drop a border node to another bordered node.
     *
     * @param targetElement
     *            target element to drop on it
     * @param elementToDrag
     *            element to drag on the target element
     * @param direction
     *            direction on the target element
     * @param changeStyle
     *            true to change edge routing style to 'Oblique'
     */
    private void doTestEdgeStabilityOnDragAndDrop(String targetElement, String elementToDrag, Direction direction, boolean changeStyle) {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            // Get bendpoints before drag and drop
            List<PointList> originalPointsList = new ArrayList<PointList>();
            for (String[] edge : EDGES) {
                originalPointsList.add(getBendpoints(edge[0], edge[1]));
            }

            // Change style before drag and drop
            if (changeStyle) {
                for (String[] edge : EDGES) {
                    changeEdgeStyle(edge[0], edge[1], "Oblique");
                }
            }

            // Drag and drop
            drag(direction, targetElement, elementToDrag);

            // Check the connections bendpoints stability
            for (int i = 0; i < EDGES.length; i++) {
                String[] edge = EDGES[i];
                String source = edge[0];
                String target = edge[1];
                PointList originalPoints = originalPointsList.get(i);

                if (source.equals(elementToDrag) || target.equals(elementToDrag)) {
                    // move from one side
                    checkEdgeMovedFromOneSide(source, target, originalPoints, changeStyle ? EdgeRouting.STRAIGHT_LITERAL : EdgeRouting.MANHATTAN_LITERAL);
                } else {
                    // not moved
                    checkUnmovedEdge(source, target, originalPoints, changeStyle ? EdgeRouting.STRAIGHT_LITERAL : EdgeRouting.MANHATTAN_LITERAL);
                }
            }
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Change the edge routing style.
     * 
     * @param source
     *            source of the edge
     * @param target
     *            target of the edge
     * @param style
     *            routing style to set
     */
    private void changeEdgeStyle(String source, String target, String style) {
        SWTBotGefEditPart partSource = editor.getEditPart(source, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart partTarget = editor.getEditPart(target, AbstractDiagramBorderNodeEditPart.class);
        List<SWTBotGefConnectionEditPart> edges = editor.getConnectionEditPart(partSource, partTarget);
        select(edges.get(0));
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        SWTBotRadio radioToSelect = propertiesView.bot().radioInGroup(style, "Styles:");
        radioToSelect = new WrappedSWTBotRadio(radioToSelect);
        radioToSelect.click();
    }

    /**
     * Select several elements
     * 
     * @param elements
     *            element to select
     */
    private void select(String... elements) {
        List<SWTBotGefEditPart> selection = new ArrayList<SWTBotGefEditPart>(elements.length);

        for (String element : elements) {
            selection.add(editor.getSelectableEditPart(element));
        }

        editor.click(selection.get(0)); // click on the first element
        editor.select(selection);
    }

    /**
     * Select an edit part
     * 
     * @param element
     *            edit part to select
     */
    private void select(SWTBotGefEditPart element) {
        editor.click(element);
        editor.select(element);
    }

    /**
     * Get center point of the selection
     * 
     * @param selection
     *            edit part selection
     */
    private Point getCenterPoint(String... elements) {
        Rectangle selection = null;

        for (String element : elements) {
            Rectangle bounds = editor.getBounds(editor.getSelectableEditPart(element));
            selection = bounds.getUnion(selection);
        }

        return selection.getCenter();
    }

    /**
     * Drag source elements to the target element
     * 
     * @param direction
     *            target direction
     * @param targetElement
     *            target element
     * @param sourceElements
     *            source elements to drag
     */
    private void drag(Direction direction, String targetElement, String... sourceElements) {
        switch (direction) {
        case EAST:
            dragEast(targetElement, sourceElements);
            break;
        case NORTH:
            dragNorth(targetElement, sourceElements);
            break;
        case SOUTH:
            dragSouth(targetElement, sourceElements);
            break;
        case WEST:
            dragWest(targetElement, sourceElements);
            break;
        }
    }

    /**
     * Drag source elements to the north of the target element
     * 
     * @param targetElement
     *            target element
     * @param sourceElements
     *            source elements to drag
     */
    private void dragNorth(String targetElement, String... sourceElements) {
        select(sourceElements);

        Rectangle bounds = getPrimaryShapeBounds(targetElement);

        // compute an offset from the first element and the center of the
        // selection in order to be in the middle of the north
        Point sourceCenter = getCenterPoint(sourceElements);
        Point firstSourceCenter = getCenterPoint(sourceElements[0]);
        int offset = sourceCenter.x - firstSourceCenter.x;

        // put the selection on the middle
        int x = bounds.x + Math.max(bounds.width / 2 - offset, 1);

        // compute the minimal distance to be on the north
        int y = bounds.y + 1;

        editor.dragCentered(sourceElements[0], x, y);
    }

    /**
     * Drag source elements to the south of the target element
     * 
     * @param targetElement
     *            target element
     * @param sourceElements
     *            source elements to drag
     */
    private void dragSouth(String targetElement, String... sourceElements) {
        select(sourceElements);

        Rectangle bounds = getPrimaryShapeBounds(targetElement);

        // compute an offset from the first element and the center of the
        // selection in order to be in the middle of the south
        Point sourceCenter = getCenterPoint(sourceElements);
        Point firstSourceCenter = getCenterPoint(sourceElements[0]);
        int offset = sourceCenter.x - firstSourceCenter.x;

        // put the selection on the middle
        int x = bounds.x + Math.max(bounds.width / 2 - offset, 1);

        // compute the minimal distance to be on the south
        int y = bounds.y + bounds.height - 1;

        editor.dragCentered(sourceElements[0], x, y);
    }

    /**
     * Drag source elements to the west of the target element
     * 
     * @param targetElement
     *            target element
     * @param sourceElements
     *            source elements to drag
     */
    private void dragWest(String targetElement, String... sourceElements) {
        select(sourceElements);

        Rectangle bounds = getPrimaryShapeBounds(targetElement);

        // compute an offset from the first element and the center of the
        // selection in order to be in the middle of the west
        Point sourceCenter = getCenterPoint(sourceElements);
        Point firstSourceCenter = getCenterPoint(sourceElements[0]);
        int offset = sourceCenter.y - firstSourceCenter.y;

        // put the selection on the middle
        int y = bounds.y + Math.max(bounds.height / 2 - offset, 1);

        // compute the minimal distance to be on the west
        int x = bounds.x + 1;

        editor.dragCentered(sourceElements[0], x, y);
    }

    /**
     * Drag source elements to the east of the target element
     * 
     * @param targetElement
     *            target element
     * @param sourceElements
     *            source elements to drag
     */
    private void dragEast(String targetElement, String... sourceElements) {
        select(sourceElements);

        Rectangle bounds = getPrimaryShapeBounds(targetElement);

        // compute an offset from the first element and the center of the
        // selection in order to be in the middle of the east
        Point sourceCenter = getCenterPoint(sourceElements);
        Point firstSourceCenter = getCenterPoint(sourceElements[0]);
        int offset = sourceCenter.y - firstSourceCenter.y;

        // put the selection on the middle
        int y = bounds.y + Math.max(bounds.height / 2 - offset, 1);

        // compute the minimal distance to be on the east
        int x = bounds.x + bounds.width - 1;

        editor.dragCentered(sourceElements[0], x, y);
    }

    /**
     * Get the bounds of the primary shape
     * 
     * @param element
     *            edit part name
     * @return bounds of the primary shape
     */
    private Rectangle getPrimaryShapeBounds(String element) {
        AbstractDiagramContainerEditPart editPart = (AbstractDiagramContainerEditPart) editor.getEditPart(element, AbstractDiagramContainerEditPart.class).part();
        ViewNodeContainerFigureDesc shape = editPart.getPrimaryShape();
        Rectangle bounds = shape.getBounds().getCopy();
        shape.translateToAbsolute(bounds);
        return bounds;
    }
}
