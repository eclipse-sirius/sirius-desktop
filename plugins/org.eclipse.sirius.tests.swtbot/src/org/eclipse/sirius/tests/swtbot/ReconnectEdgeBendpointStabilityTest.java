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

import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
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

    /**
     * This test validates that using a specific line style will not cause NPE
     * after several reconnections.
     */
    public void testReconnectWithUserSpecificLineStyle() {
        final IEclipsePreferences diagramCoreDefaultPreferences = DefaultScope.INSTANCE.getNode(DiagramPlugin.ID);
        boolean enableOverride = diagramCoreDefaultPreferences.getBoolean(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, false);
        int specificLineStyleSirius = diagramCoreDefaultPreferences.getInt(SiriusDiagramCorePreferences.PREF_LINE_STYLE, EdgeRouting.STRAIGHT);
        final int specificLineStyleGMF = DiagramUIPlugin.getPlugin().getPreferenceStore().getInt(IPreferenceConstants.PREF_LINE_STYLE);
        try {
            // Initialize preference to enable the user specific line style to
            // manhattan
            diagramCoreDefaultPreferences.putBoolean(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, true);
            diagramCoreDefaultPreferences.putInt(SiriusDiagramCorePreferences.PREF_LINE_STYLE, EdgeRouting.MANHATTAN);
            DiagramUIPlugin.getPlugin().getPreferenceStore().setValue(IPreferenceConstants.PREF_LINE_STYLE, Routing.RECTILINEAR);

            // Open a diagram with straight edge
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_STRAIGHT_EDGE_NAME, REPRESENTATION_INSTANCE_STRAIGHT_EDGE_NAME, DDiagram.class);
            // 1 - D&d of the target
            reconnectEdge(false);
            // 2 - D&d of the source
            reconnectEdge(true, "eClass3", "eClass2", "eClass1");
            // 3 - D&d of the target again (it was causing a NPE)
            reconnectEdge(false, "eClass1", "eClass2", "eClass3");
        } finally {
            diagramCoreDefaultPreferences.putBoolean(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, enableOverride);
            diagramCoreDefaultPreferences.putInt(SiriusDiagramCorePreferences.PREF_LINE_STYLE, specificLineStyleSirius);
            UIThreadRunnable.syncExec(new VoidResult() {

                @Override
                public void run() {
                    // Unlike the previous call, this time we need to run it in
                    // a UIThreadRunnable or there is no active workbench
                    // (causing an NPE)
                    DiagramUIPlugin.getPlugin().getPreferenceStore().setValue(IPreferenceConstants.PREF_LINE_STYLE, specificLineStyleGMF);
                }
            });
        }
    }

    private void reconnectAndValidate(String representationName, boolean reconnectSource) {
        if (REPRESENTATION_STRAIGHT_EDGE_NAME.equals(representationName)) {
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_STRAIGHT_EDGE_NAME, REPRESENTATION_INSTANCE_STRAIGHT_EDGE_NAME, DDiagram.class);
        } else if (REPRESENTATION_MANHATTAN_EDGE_NAME.equals(representationName)) {
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_MANHATTAN_EDGE_NAME, REPRESENTATION_INSTANCE_MANHATTAN_EDGE_NAME, DDiagram.class);
        } else {
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_TREE_EDGE_NAME, REPRESENTATION_INSTANCE_TREE_EDGE_NAME, DDiagram.class);
        }

        PointList pointList = reconnectEdge(reconnectSource);

        // Verify that the bendpoints after reconnection are at the expected
        // locations
        if (reconnectSource) {
            checkConnectionPoints("eClass2", "eClass1", pointList, reconnectSource);
        } else {
            checkConnectionPoints("eClass3", "eClass2", pointList, false);
        }
    }

    private PointList reconnectEdge(boolean reconnectSource) {
        return reconnectEdge(reconnectSource, "eClass3", "eClass1", "eClass2");
    }

    private PointList reconnectEdge(boolean reconnectSource, String source, String target, String reconnectionTarget) {
        // Retrieve location for container list named EClass 3
        Point location = editor.getLocation(reconnectionTarget, AbstractDiagramBorderNodeEditPart.class);
        Dimension dimension = editor.getDimension(reconnectionTarget, AbstractDiagramBorderNodeEditPart.class);

        // Retrieve edge "ref" target point location
        PointList pointList = getEdgePointList(source, target);
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
        return pointList;
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
            if(!reconnectedSource) {
            	// There is an issue on linux where the reconnection, with SWTBot (not manually), has not the same result on bendpoint coordinates
                expectedMovedBendpoint = 3;
            }
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
