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

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.junit.Assert;

/**
 * This test is dedicated to validate bendpoint positions after reconnection.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class ReconnectEdgeBendpointStabilityTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_STRAIGHT_EDGE_NAME = "new straightEdgeReconnection";

    private static final String REPRESENTATION_STRAIGHT_EDGE_NAME = "straightEdgeReconnection";

    private static final String REPRESENTATION_INSTANCE_MANHATTAN_EDGE_NAME = "new manhattanEdgeReconnection";

    private static final String REPRESENTATION_MANHATTAN_EDGE_NAME = "manhattanEdgeReconnection";

    private static final String REPRESENTATION_INSTANCE_TREE_EDGE_NAME = "new treeEdgeReconnection";

    private static final String REPRESENTATION_TREE_EDGE_NAME = "treeEdgeReconnection";

    private static final String VIEWPOINT_NAME = "edgeReconnection";

    private static final String MODEL = "edgeReconnection.ecore";

    private static final String SESSION_FILE = "edgeReconnection.aird";

    private static final String VSM_FILE = "edgeReconnection.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/reconnect/bendpointStability/";

    private static final String FILE_DIR = "/";

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
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test that reconnects straight edge target and validates bendpoints
     * positions.
     */
    public void testReconnectStraightEdgeTarget() {
        reconnectAndValidate(REPRESENTATION_STRAIGHT_EDGE_NAME, false);
    }

    /**
     * Test that reconnects straight edge source and validates bendpoints
     * positions.
     */
    public void testReconnectStraightEdgeSource() {
        reconnectAndValidate(REPRESENTATION_STRAIGHT_EDGE_NAME, true);
    }

    /**
     * Test that reconnects Manhattan edge target and validates bendpoints
     * positions.
     */
    public void testReconnectManhattanEdgeTarget() {
        reconnectAndValidate(REPRESENTATION_MANHATTAN_EDGE_NAME, false);
    }

    /**
     * Test that reconnects Manhattan edge source and validates bendpoints
     * positions.
     */
    public void testReconnectManhattanEdgeSource() {
        reconnectAndValidate(REPRESENTATION_MANHATTAN_EDGE_NAME, true);
    }

    /**
     * Test that reconnects tree edge target and validates bendpoints positions.
     */
    public void testReconnectTreeEdgeTarget() {
        reconnectAndValidate(REPRESENTATION_TREE_EDGE_NAME, false);
    }

    /**
     * Test that reconnects tree edge source and validates bendpoints positions.
     */
    public void testReconnectTreeEdgeSource() {
        reconnectAndValidate(REPRESENTATION_TREE_EDGE_NAME, true);
    }

    protected void reconnectAndValidate(String representationName, boolean reconnectSource) {
        if (REPRESENTATION_STRAIGHT_EDGE_NAME.equals(representationName)) {
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_STRAIGHT_EDGE_NAME, REPRESENTATION_INSTANCE_STRAIGHT_EDGE_NAME, DDiagram.class);
        } else if (REPRESENTATION_MANHATTAN_EDGE_NAME.equals(representationName)) {
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_MANHATTAN_EDGE_NAME, REPRESENTATION_INSTANCE_MANHATTAN_EDGE_NAME, DDiagram.class);
        } else {
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_TREE_EDGE_NAME, REPRESENTATION_INSTANCE_TREE_EDGE_NAME, DDiagram.class);
        }

        // Retrieve location for container list named EClass 3
        Point location = editor.getLocation("eClass2", AbstractDiagramBorderNodeEditPart.class);
        Dimension dimension = editor.getDimension("eClass2", AbstractDiagramBorderNodeEditPart.class);

        // Retrieve edge "ref" target point location
        PointList pointList = getEdgePointList("eClass3", "eClass1");
        Point endToReconnect;
        if (reconnectSource) {
            endToReconnect = pointList.getPoint(0);
        } else {
            endToReconnect = pointList.getPoint(pointList.size() - 1);
        }

        // Select the edge
        editor.select(editor.getEditPart("ref", ConnectionEditPart.class));

        // Drag the source benpoint to the new source node
        editor.drag(endToReconnect, location.x + dimension.width / 2, location.y + dimension.height / 2);

        // Verify that the bendpoints after reconnection are at the expected
        // locations
        if (reconnectSource) {
            checkConnectionPoints("eClass2", "eClass1", pointList, reconnectSource);
        } else {
            checkConnectionPoints("eClass3", "eClass2", pointList, false);
        }
    }

    /**
     * Validates the connection bendpoints are in the expected locations
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     * @param originalPointList
     *            locations of bendpoints before reconnection
     * @param reconnectedSource
     *            true if reconnection of the source, false if reconnection of
     *            the target
     */
    private void checkConnectionPoints(String sourceEditPartName, String targetEditPartName, PointList originalPointList, boolean reconnectedSource) {

        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        DEdge dedge = (DEdge) element;
        // Depending on the router, 1 or more bendpoint(s) are expected to move
        int expectedMovedBendpoint = 0;
        if (EdgeRouting.STRAIGHT_LITERAL.equals(((EdgeStyle) dedge.getStyle()).getRoutingStyle())) {
            expectedMovedBendpoint = 1;
        } else if (EdgeRouting.MANHATTAN_LITERAL.equals(((EdgeStyle) dedge.getStyle()).getRoutingStyle())) {
            expectedMovedBendpoint = 2;
        } else if (EdgeRouting.TREE_LITERAL.equals(((EdgeStyle) dedge.getStyle()).getRoutingStyle())) {
            // It is a default value because there can be more moved bendpoint
            // with the tree router
            expectedMovedBendpoint = 2;
        }
        checkConnectionPoints(sourceEditPartName, targetEditPartName, originalPointList, reconnectedSource, expectedMovedBendpoint);
    }

    /**
     * Validates the connection bendpoints are in the expected locations
     * 
     * @param sourceEditPartName
     *            source of the connection
     * @param targetEditPartName
     *            target of the connection
     * @param originalPointList
     *            locations of bendpoints before reconnection
     * @param reconnectedSource
     *            true if reconnection of the source, false if reconnection of
     *            the target
     */
    private void checkConnectionPoints(String sourceEditPartName, String targetEditPartName, PointList originalPointList, boolean reconnectedSource, int expectedMovedBendpoint) {
        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();

        if (reconnectedSource) {
            for (int i = expectedMovedBendpoint; i < pointList.size(); i++) {
                // Reconnecting from source will move the first bendpoints
                Assert.assertEquals("Bendpoint " + pointList.getPoint(i) + " expected to be at " + originalPointList.getPoint(i), originalPointList.getPoint(i), pointList.getPoint(i));
            }
        } else {
            for (int i = 0; i < pointList.size() - expectedMovedBendpoint; i++) {
                // Reconnecting from target will move the last bendpoints
                Assert.assertEquals("Bendpoint " + pointList.getPoint(i) + " expected to be at " + originalPointList.getPoint(i), originalPointList.getPoint(i), pointList.getPoint(i));
            }
        }
    }

    private ConnectionEditPart getConnectionEditPart(String sourceEditPartName, String targetEditPartName) {
        List<SWTBotGefConnectionEditPart> connectionEditPartList = editor.getConnectionEditPart(editor.getEditPart(sourceEditPartName, AbstractDiagramBorderNodeEditPart.class),
                editor.getEditPart(targetEditPartName, AbstractDiagramBorderNodeEditPart.class));
        assertNotNull("There is no connection between " + sourceEditPartName + " and " + targetEditPartName, connectionEditPartList);
        assertEquals("There are more or less than 1 connection between " + sourceEditPartName + " and " + targetEditPartName, 1, connectionEditPartList.size());
        return connectionEditPartList.get(0).part();
    }

    private PointList getEdgePointList(String sourceEditPartName, String targetEditPartName) {

        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();

        return pointList;
    }
}
