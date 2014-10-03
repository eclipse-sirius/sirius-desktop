/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;

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

    /**
     * Final bendpoints position used by testEdgeStabilityOnDragAndDrop()
     */
    private static final Point[] TEST_1_C3_C1 = { new Point(121, 100), new Point(619, 100), new Point(619, 276) };

    private static final Point[] TEST_1_C3_C2 = { new Point(121, 110), new Point(322, 110), new Point(322, 334), new Point(524, 334) };

    private static final Point[] TEST_1_C2_C1 = { new Point(534, 328), new Point(534, 238), new Point(613, 238), new Point(613, 276) };

    /**
     * Final bendpoints position used by
     * testEdgeStabilityOnDragAndDropAfterStyleChange()
     */
    private static final Point[] TEST_2_C3_C1 = { new Point(121, 100), new Point(635, 100), new Point(636, 120) };

    private static final Point[] TEST_2_C3_C2 = { new Point(121, 110), new Point(322, 110), new Point(322, 334), new Point(199, 180) };

    private static final Point[] TEST_2_C2_C1 = { new Point(203, 168), new Point(534, 238), new Point(630, 238), new Point(630, 150) };

    /**
     * Final bendpoints position used by
     * testEdgeStabilityOnDragAndDropAfterStyleChange2()
     */
    private static final Point[] TEST_3_C3_C1 = { new Point(121, 100), new Point(635, 100), new Point(283, 123) };

    private static final Point[] TEST_3_C3_C2 = { new Point(121, 110), new Point(322, 110), new Point(322, 334), new Point(524, 334) };

    private static final Point[] TEST_3_C2_C1 = { new Point(535, 328), new Point(534, 238), new Point(630, 238), new Point(283, 129) };

    /**
     * Final bendpoints position used by
     * testEdgeStabilityOnDragAndDropToContainedNode()
     */
    private static final Point[] TEST_4_C3_C1 = { new Point(121, 100), new Point(635, 100), new Point(636, 120) };

    private static final Point[] TEST_4_C3_C2 = { new Point(121, 110), new Point(322, 110), new Point(322, 184), new Point(660, 184) };

    private static final Point[] TEST_4_C2_C1 = { new Point(671, 208), new Point(671, 238), new Point(630, 238), new Point(630, 150) };

    /**
     * Final bendpoints position used by
     * testEdgeStabilityOnDragAndDropWithSourceAndTarget()
     */
    private static final Point[] TEST_5_C5_C6 = { new Point(694, 66), new Point(694, 34), new Point(754, 34), new Point(754, 66) };

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

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
     * Validates the connection bendpoints are in the expected locations
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     * @param expectedPositions
     *            expected bendpoints position
     * @param expectedRoutingStyle
     *            expected routing style
     * 
     */
    private void checkBendpoints(String sourceEditPartName, String targetEditPartName, Point[] expectedPositions, EdgeRouting expectedRoutingStyle) {

        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();

        for (int i = 0; i < expectedPositions.length; i++) {
            Point point = pointList.getPoint(i);
            Point expectedPoint = expectedPositions[i];
            assertEquals("X position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", expectedPoint.x, point.x, 1);
            assertEquals("Y position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", expectedPoint.y, point.y, 1);
        }

        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        DEdge dedge = (DEdge) element;

        assertEquals("The routing style is not " + expectedRoutingStyle.getLiteral(), expectedRoutingStyle, ((EdgeStyle) dedge.getStyle()).getRoutingStyle());

        EdgeLayoutData data = SiriusLayoutDataManager.INSTANCE.getData(dedge, false);
        assertNull("SiriusLayoutDataManager should not store data of DEge between " + sourceEditPartName + " and " + targetEditPartName + " anymore", data);
    }

    private ConnectionEditPart getConnectionEditPart(String sourceEditPartName, String targetEditPartName) {
        List<SWTBotGefConnectionEditPart> connectionEditPartList = editor.getConnectionEditPart(editor.getEditPart(sourceEditPartName, AbstractDiagramBorderNodeEditPart.class),
                editor.getEditPart(targetEditPartName, AbstractDiagramBorderNodeEditPart.class));
        assertNotNull("There is no connection between " + sourceEditPartName + " and " + targetEditPartName, connectionEditPartList);
        assertEquals("There are more or less than 1 connection between " + sourceEditPartName + " and " + targetEditPartName, 1, connectionEditPartList.size());
        return connectionEditPartList.get(0).part();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeStabilityOnDragAndDrop() throws Exception {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            // Drag and drop C1 to package P3
            dragNorth("P3", "C1");

            // Check the connections bendpoints stability
            checkBendpoints("C3", "C1", TEST_1_C3_C1, EdgeRouting.MANHATTAN_LITERAL); // rectilinear
            checkBendpoints("C3", "C2", TEST_1_C3_C2, EdgeRouting.MANHATTAN_LITERAL); // rectilinear
            checkBendpoints("C2", "C1", TEST_1_C2_C1, EdgeRouting.MANHATTAN_LITERAL); // rectilinear

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeStabilityOnDragAndDropAfterStyleChange() throws Exception {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            // Change the style of the edge between C3 and C2 and between C2 and
            // C1
            changeEdgeStyle("C3", "C2", "Oblique");
            changeEdgeStyle("C2", "C1", "Oblique");

            // Drag and drop C2 to package P2
            dragSouth("P2", "C2");

            // Check the connections bendpoints stability
            checkBendpoints("C3", "C2", TEST_2_C3_C2, EdgeRouting.STRAIGHT_LITERAL); // oblique
            checkBendpoints("C2", "C1", TEST_2_C2_C1, EdgeRouting.STRAIGHT_LITERAL); // oblique
            checkBendpoints("C3", "C1", TEST_2_C3_C1, EdgeRouting.MANHATTAN_LITERAL); // rectilinear

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeStabilityOnDragAndDropAfterStyleChange2() throws Exception {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            // Change the style of the edge between C3 and C1 and between C2 and
            // C1
            changeEdgeStyle("C3", "C1", "Oblique");
            changeEdgeStyle("C2", "C1", "Oblique");

            // Drag and drop C1 to package P2
            dragEast("P2", "C1");

            // Check the connections bendpoints stability
            checkBendpoints("C3", "C1", TEST_3_C3_C1, EdgeRouting.STRAIGHT_LITERAL); // oblique
            checkBendpoints("C2", "C1", TEST_3_C2_C1, EdgeRouting.STRAIGHT_LITERAL); // oblique
            checkBendpoints("C3", "C2", TEST_3_C3_C2, EdgeRouting.MANHATTAN_LITERAL); // rectilinear

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Drag and drop a border node to node contained into another with
     * horizontal scrollbar.
     */
    public void testEdgeStabilityOnDragAndDropToContainedNode() {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            // Drag and drop C2 to package P4
            dragWest("P4", "C2");

            // Check the connections bendpoints stability
            checkBendpoints("C3", "C1", TEST_4_C3_C1, EdgeRouting.MANHATTAN_LITERAL); // rectilinear
            checkBendpoints("C3", "C2", TEST_4_C3_C2, EdgeRouting.MANHATTAN_LITERAL); // rectilinear
            checkBendpoints("C2", "C1", TEST_4_C2_C1, EdgeRouting.MANHATTAN_LITERAL); // rectilinear

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Drag and drop a source and target edge border node to another node.
     */
    public void testEdgeStabilityOnDragAndDropWithSourceAndTarget() {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            // Drag and drop C5 and C6 to package P1
            dragNorth("P1", "C5", "C6");

            // Check the connections bendpoints stability
            checkBendpoints("C5", "C6", TEST_5_C5_C6, EdgeRouting.MANHATTAN_LITERAL); // rectilinear

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    private void changeEdgeStyle(String source, String target, String style) {
        SWTBotGefEditPart partSource = editor.getEditPart(source, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart partTarget = editor.getEditPart(target, AbstractDiagramBorderNodeEditPart.class);
        List<SWTBotGefConnectionEditPart> edges = editor.getConnectionEditPart(partSource, partTarget);
        select(edges.get(0));
        bot.viewByTitle("Properties").setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance");
        SWTBotRadio radioToSelect = bot.viewByTitle("Properties").bot().radioInGroup(style, "Styles:");
        radioToSelect = new WrappedSWTBotRadio(radioToSelect);
        radioToSelect.click();
    }

    private void select(String... elements) {
        List<SWTBotGefEditPart> selection = new ArrayList<SWTBotGefEditPart>(elements.length);

        for (String element : elements) {
            selection.add(editor.getSelectableEditPart(element));
        }

        editor.click(selection.get(0)); // click on the first element
        editor.select(selection);
    }

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
