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
import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
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
 * 
 * @author smonnier
 */
public class EdgeStabilityOnDragAndDropTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new 2123Diag";

    private static final String REPRESENTATION_NAME = "2123Diag";

    private static final String VIEWPOINT_NAME = "2123Viewpoint";

    private static final String MODEL = "2123.ecore";

    private static final String SESSION_FILE = "2123.aird";

    private static final String VSM_FILE = "2123.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeStabilityOnDragAndDrop/";

    private static final String FILE_DIR = "/";

    private static final HashMap<String, List<Point>> CONNECTION_EDITPART_POINTS = new HashMap<String, List<Point>>();

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    static {
        ArrayList<Point> pointsC3_C1 = new ArrayList<Point>();
        pointsC3_C1.add(new Point(121, 100));
        pointsC3_C1.add(new Point(635, 100));
        pointsC3_C1.add(new Point(635, 358));
        // pointsC3_C1.add(new Point(634, 358));
        CONNECTION_EDITPART_POINTS.put("C3_C1", pointsC3_C1);
        ArrayList<Point> pointsC3_C2 = new ArrayList<Point>();
        pointsC3_C2.add(new Point(121, 110));
        pointsC3_C2.add(new Point(322, 110));
        pointsC3_C2.add(new Point(322, 334));
        pointsC3_C2.add(new Point(524, 334));
        CONNECTION_EDITPART_POINTS.put("C3_C2", pointsC3_C2);
        ArrayList<Point> pointsC2_C1 = new ArrayList<Point>();
        pointsC2_C1.add(new Point(534, 328));
        pointsC2_C1.add(new Point(534, 238));
        pointsC2_C1.add(new Point(628, 238));
        pointsC2_C1.add(new Point(628, 358));
        CONNECTION_EDITPART_POINTS.put("C2_C1", pointsC2_C1);
    }

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

        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME, UIDiagramRepresentation.class).open();

        editor = diagram.getEditor();
        editor.setSnapToGrid(false);
    }

    /**
     * Validates the connection bendpoints are in the expected locations
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     */
    private void checkConnectionPoints(String sourceEditPartName, String targetEditPartName) {

        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();

        List<Point> referencePoints = CONNECTION_EDITPART_POINTS.get(sourceEditPartName + "_" + targetEditPartName);
        for (int i = 0; i < referencePoints.size(); i++) {
            Point point = pointList.getPoint(i);
            Point referencePoint = referencePoints.get(i);
            assertEquals("X position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", referencePoint.x, point.x, 1);
            assertEquals("Y position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", referencePoint.y, point.y, 1);
        }

        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        DEdge dedge = (DEdge) element;

        assertEquals("The rooting style is not Manhattan", ((EdgeStyle) dedge.getStyle()).getRoutingStyle(), EdgeRouting.MANHATTAN_LITERAL);

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
            Point location = editor.getLocation("P3", AbstractDiagramContainerEditPart.class);
            Dimension dimension = editor.getDimension("P3", AbstractDiagramContainerEditPart.class);

            // Drag and drop C1 to package P3
            SWTBotGefEditPart editPart = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
            editor.drag(editPart, location.x + dimension.width / 2, location.y + dimension.height / 2);

            // Check the connections bendpoints stability in order to validate
            // the router is still rectilinear
            checkConnectionPoints("C3", "C1");
            checkConnectionPoints("C3", "C2");
            checkConnectionPoints("C2", "C1");

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
            changeEdgeStyle("C3", "C2", AbstractDiagramBorderNodeEditPart.class, AbstractDiagramBorderNodeEditPart.class, "Oblique");
            changeEdgeStyle("C2", "C1", AbstractDiagramBorderNodeEditPart.class, AbstractDiagramBorderNodeEditPart.class, "Oblique");

            // Drag and drop C2 to package P2
            drag("C2", "P2", AbstractDiagramContainerEditPart.class);

            // Check the connections bendpoints stability in order to validate
            // the router is still straight
            checkRoutingStyle("C3", "C2", EdgeRouting.STRAIGHT_LITERAL);
            checkRoutingStyle("C2", "C1", EdgeRouting.STRAIGHT_LITERAL);

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
            changeEdgeStyle("C3", "C1", AbstractDiagramBorderNodeEditPart.class, AbstractDiagramBorderNodeEditPart.class, "Oblique");
            changeEdgeStyle("C2", "C1", AbstractDiagramBorderNodeEditPart.class, AbstractDiagramBorderNodeEditPart.class, "Oblique");

            // Drag and drop C1 to package P2
            drag("C1", "P2", AbstractDiagramContainerEditPart.class);

            // Check the connections bendpoints stability in order to validate
            // the router is still straight
            checkRoutingStyle("C3", "C1", EdgeRouting.STRAIGHT_LITERAL);
            checkRoutingStyle("C2", "C1", EdgeRouting.STRAIGHT_LITERAL);

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    private void changeEdgeStyle(String source, String target, Class sourceType, Class targetStyle, String style) {
        SWTBotGefEditPart partSource = editor.getEditPart(source, sourceType);
        SWTBotGefEditPart partTarget = editor.getEditPart(target, targetStyle);
        List<SWTBotGefConnectionEditPart> edges = editor.getConnectionEditPart(partSource, partTarget);
        select(edges.get(0));
        bot.viewByTitle("Properties").setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance");
        SWTBotRadio radioToSelect = bot.viewByTitle("Properties").bot().radioInGroup(style, "Styles:");
        radioToSelect = new WrappedSWTBotRadio(radioToSelect);
        radioToSelect.click();
    }

    private void select(String element) {
        editor.click(element);
        select(editor.getSelectableEditPart(element));
    }

    private void select(SWTBotGefEditPart element) {
        editor.click(element);
        editor.select(element);
    }

    private void drag(String element, String toElement, Class toElementType) {
        Point location = editor.getLocation(toElement, toElementType);
        Dimension dimension = editor.getDimension(toElement, toElementType);
        select(element);
        editor.dragCentered(element, location.x + dimension.width / 2, location.y + dimension.height / 2);
    }

    private void checkRoutingStyle(String sourceEditPartName, String targetEditPartName, EdgeRouting expectedRoutingStyle) {
        DEdge dedge = getEdge(sourceEditPartName, targetEditPartName);
        assertEquals("The routing style is not " + expectedRoutingStyle.getLiteral(), expectedRoutingStyle, ((EdgeStyle) dedge.getStyle()).getRoutingStyle());
    }

    private DEdge getEdge(String sourceEditPartName, String targetEditPartName) {
        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        return (DEdge) element;
    }
}
