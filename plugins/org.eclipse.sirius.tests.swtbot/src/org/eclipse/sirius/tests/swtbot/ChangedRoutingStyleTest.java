/*******************************************************************************
 * Copyright (c) 2020, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.Connection;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;

/**
 * Test that an edge routing style change, from Rectilinear or Tree into Oblique, generates an oblique line.
 * 
 * @author Jessy MALLET
 */
public class ChangedRoutingStyleTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_FROM_TREE_NAME = "changeEdgeStyleFromTree";

    private static final String REPRESENTATION_FROM_RECTILINEAR_NAME = "changeEdgeStyleFromRectilinear";

    private static final String REPRESENTATION_FROM_RECTILINEAR_WITHOUT_CHANGE_NAME = "changeEdgeStyleFromRectilinearButWithoutVisualChange";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String SEMANTIC_MODEL = "My.ecore";

    private static final String REPRESENTATION_MODEL = "My.aird";

    private static final String MODELER = "My.odesign";

    private static final String PATH = "data/unit/routing/change_routing_style/";

    private static final String OBLIQUE_STYLE_ROUTING = "Oblique Style Routing";

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
     * Test that an edge routing style change, from rectilinear style to oblique style, generates an oblique line
     * without intermediate points.
     */
    public void testEdgeChangedFromRectilinearToOblique() {
        openDiagram(REPRESENTATION_FROM_RECTILINEAR_NAME);
        SWTBotGefConnectionEditPart botEdgeEditPart = getConnectionEditPartList("EClass 1", "EClass 2").get(0);
        changeRoutingStyle(botEdgeEditPart, OBLIQUE_STYLE_ROUTING);

        // check oblique edge
        Object element = botEdgeEditPart.part().getModel();
        assertTrue(element instanceof Edge);
        EdgeQuery edgeQuery = new EdgeQuery((Edge) element);
        assertTrue("The routing style of GMF edge should be Oblique.", edgeQuery.isEdgeWithObliqueRoutingStyle());
        Connection connection = (Connection) botEdgeEditPart.part().getFigure();
        assertEquals("The oblique edge should contain only 2 points.", 2, connection.getPoints().size());

    }

    /**
     * Test that an edge routing style change, from rectilinear style to oblique style, generates an oblique line
     * without intermediate points.
     */
    public void testEdgeChangedFromRectilinearToObliqueButWithoutVisualChange() {
        openDiagram(REPRESENTATION_FROM_RECTILINEAR_WITHOUT_CHANGE_NAME);
        SWTBotGefConnectionEditPart botEdgeEditPart = getConnectionEditPartList("EClass 1", "EClass 2").get(0);
        changeRoutingStyle(botEdgeEditPart, OBLIQUE_STYLE_ROUTING);

        // check oblique edge
        Object element = botEdgeEditPart.part().getModel();
        assertTrue(element instanceof Edge);
        EdgeQuery edgeQuery = new EdgeQuery((Edge) element);
        assertTrue("The routing style of GMF edge should be Oblique.", edgeQuery.isEdgeWithObliqueRoutingStyle());
        Connection connection = (Connection) botEdgeEditPart.part().getFigure();
        assertEquals("The oblique edge should always contain 5 points (source and target are overlapped).", 5, connection.getPoints().size());

    }

    /**
     * Test that an edge routing style change, from tree style into oblique style, generates an oblique line without
     * intermediate points.
     */
    public void testEdgeChangedFromTreeToOblique() {
        openDiagram(REPRESENTATION_FROM_TREE_NAME);
        SWTBotGefConnectionEditPart botEdgeEditPart = getConnectionEditPartList("EClass 1", "EClass 2").get(0);
        changeRoutingStyle(botEdgeEditPart, OBLIQUE_STYLE_ROUTING);

        // check oblique edge
        Object element = botEdgeEditPart.part().getModel();
        assertTrue(element instanceof Edge);
        EdgeQuery edgeQuery = new EdgeQuery((Edge) element);
        assertTrue("The routing style of GMF edge should be Oblique.", edgeQuery.isEdgeWithObliqueRoutingStyle());
        Connection connection = (Connection) botEdgeEditPart.part().getFigure();
        assertEquals("The oblique edge should contain only 2 points.", 2, connection.getPoints().size());
    }

    /**
     * Open representation with a given name.
     * 
     * @param representationName
     *            name of the representation to open.
     */
    private void openDiagram(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class, true, true);
    }

    /**
     * Change the routing style of a given edge.
     * 
     * @param gefConnectionEditPart
     *            editPart of the edge to change,
     * @param routingStyle
     *            new routing style to apply on the edge.
     */
    private void changeRoutingStyle(SWTBotGefConnectionEditPart gefConnectionEditPart, String routingStyle) {
        gefConnectionEditPart.select();
        SWTBotUtils.waitAllUiEvents();
        // select the routing style with the diagram menu
        editor.clickContextMenu(routingStyle);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Get connection editPart between a source and a target.
     * 
     * @param sourceEditPart
     *            name of the source edit part of the connection,
     * @param targetEditPart
     *            name of the target edit part of the connection,
     * @return connection editPart between a source and a target.
     */
    private List<SWTBotGefConnectionEditPart> getConnectionEditPartList(String sourceEditPart, String targetEditPart) {
        return editor.getConnectionEditPart(editor.getEditPart(sourceEditPart, AbstractDiagramListEditPart.class), editor.getEditPart(targetEditPart, AbstractDiagramListEditPart.class));
    }

}
