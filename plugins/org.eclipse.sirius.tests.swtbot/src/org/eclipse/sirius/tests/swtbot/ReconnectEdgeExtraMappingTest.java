/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;

/**
 * Test a reconnect edge with extra mapping on ecore. The edge was mapping with
 * EReference. The container list was mapping with EClass. The bordered node was
 * mapped with EReference. Test VP-2460
 * 
 * @author jdupont
 */
public class ReconnectEdgeExtraMappingTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new package";

    private static final String REPRESENTATION_NAME = "package";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/dragAndDrop/extra_mapping/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

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

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test reconnect edge with extra mapping. Bordered node is an extra
     * mapping. Test reconnect edge "edge ref1-1" from "EClass 2" to "EClass 3".
     */
    public void testReconnectEdgeFromBorderedToBorderedNode() {
        // Retrieve location for container list named EClass 3
        Point location = editor.getLocation("EClass 3", AbstractDiagramListEditPart.class);
        Dimension dimension = editor.getDimension("EClass 3", AbstractDiagramListEditPart.class);

        // Retrieve edge "edge ref1-1" point location.
        Point target = edgeTarget("ref1-1", "ref1-1target");

        select("edge ref1-1");

        editor.drag(target, location.x + dimension.width / 2, location.y + dimension.height / 2);

        // Verify that the reconnection is ok.
        checkConnectionPoints("ref1-1", "ref1-1target");

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
        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        DEdge dedge = (DEdge) element;

        assertEquals("The rooting style is not Straight", ((EdgeStyle) dedge.getStyle()).getRoutingStyle(), EdgeRouting.STRAIGHT_LITERAL);

        assertEquals("The source for edge ref1-1 must be EClass 1", "EClass 1", ((EClass) ((DNode) dedge.getSourceNode()).getTarget().eContainer()).getName());

        assertEquals("The source for edge ref1-1 must be EClass 3", "EClass 3", ((DNodeList) dedge.getTargetNode().eContainer()).getName());

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

    private Point edgeTarget(String sourceEditPartName, String targetEditPartName) {

        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();

        return pointList.getPoint(1);
    }

    private void select(String element) {
        editor.click(editor.getEditPart(element));
        editor.select(editor.getEditPart(element));
    }
}
