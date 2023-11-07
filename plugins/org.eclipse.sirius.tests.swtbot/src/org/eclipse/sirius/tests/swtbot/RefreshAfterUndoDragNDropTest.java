/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusContainerDropPolicy;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class which checks correct undo after drag'n'drop of node with edges.
 * 
 * @author lredor
 */
public class RefreshAfterUndoDragNDropTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new tc2303";

    private static final String REPRESENTATION_NAME = "tc2303";

    private static final String MODEL = "tc2303.ecore";

    private static final String SESSION_FILE = "tc2303.aird";

    private static final String VSM_FILE = "tc2303.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/refresh/edge/2303/";

    private static final String FILE_DIR = "/";

    private static final Point POINT_IN_NODE_TO_DROP = new Point(475, 95);

    private static final Point POINT_IN_NEW_CONTAINER = new Point(625, 215);

    private static final String DROP_TARGET_NAME = "EClass3";

    private static final String REFRESH_CONTEXTUAL_MENU_NAME = "Refresh";

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
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Called test2xxxx to force to execute this test in first (more probability
     * to reproduce the problem)
     * 
     * @throws Exception
     *             Test error.
     */
    public void test2DragNDropAndUndoWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        SWTBotGefEditPart dropTargetSwtBotPart = editor.getEditPart(DROP_TARGET_NAME, AbstractDiagramContainerEditPart.class);

        assertTrue(dropTargetSwtBotPart.part().getRoot().getChildren().size() == 1);
        assertTrue(dropTargetSwtBotPart.part().getRoot().getChildren().get(0) instanceof AbstractDDiagramEditPart);
        AbstractDDiagramEditPart diagramEditPart = (AbstractDDiagramEditPart) dropTargetSwtBotPart.part().getRoot().getChildren().get(0);

        // Check the number of edges in GMF and DDiagram
        checkNumberOfEdges(diagramEditPart, 7);
        // Move node ref2 to EClasse3
        editor.drag(POINT_IN_NODE_TO_DROP, POINT_IN_NEW_CONTAINER);
        // Check the number of edges in GMF and DDiagram
        checkNumberOfEdges(diagramEditPart, 4);
        // Undo the drop
        bot.menu("Edit").menu("Undo " + SiriusContainerDropPolicy.DROP_ELEMENTS_CMD_NAME).click();
        // Check the number of edges in GMF and DDiagram
        checkNumberOfEdges(diagramEditPart, 7);
    }

    /**
     * Called test1xxxx to force to execute this test in first (more probability
     * to reproduce the problem)
     * 
     * @throws Exception
     *             Test error.
     */
    public void test1DragNDropAndUndoWithoutAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        SWTBotGefEditPart dropTargetSwtBotPart = editor.getEditPart(DROP_TARGET_NAME, AbstractDiagramContainerEditPart.class);
        assertTrue(dropTargetSwtBotPart.part().getRoot().getChildren().size() == 1);
        assertTrue(dropTargetSwtBotPart.part().getRoot().getChildren().get(0) instanceof AbstractDDiagramEditPart);
        AbstractDDiagramEditPart diagramEditPart = (AbstractDDiagramEditPart) dropTargetSwtBotPart.part().getRoot().getChildren().get(0);

        // Check the number of edges in GMF and DDiagram
        checkNumberOfEdges(diagramEditPart, 7);
        // Move node ref2 to EClasse3
        // editor.drag(elementToDragBounds.getCenter(),
        // dropTargetBounds.getCenter());
        editor.drag(POINT_IN_NODE_TO_DROP, POINT_IN_NEW_CONTAINER);
        // Check the number of edges in GMF and DDiagram
        checkNumberOfEdges(diagramEditPart, 7);
        // Undo the drop
        bot.menu("Edit").menu("Undo " + SiriusContainerDropPolicy.DROP_ELEMENTS_CMD_NAME).click();
        // Check that there are 7 edges in the DDiagramElement and 7 GMF edges
        checkNumberOfEdges(diagramEditPart, 7);
        // Move node ref2 to EClasse3
        editor.drag(POINT_IN_NODE_TO_DROP, POINT_IN_NEW_CONTAINER);
        // editor.drag(elementToDragBounds.getCenter(),
        // dropTargetBounds.getCenter());
        // Check the number of edges in GMF and DDiagram
        checkNumberOfEdges(diagramEditPart, 7);
        // Refresh the diagram
        editor.clickContextMenu(REFRESH_CONTEXTUAL_MENU_NAME);
        // Check the number of edges in GMF and DDiagram
        checkNumberOfEdges(diagramEditPart, 4);
        // Undo the refresh
        bot.menu("Edit").menu("Undo " + "Do Command").click();
        // Undo the drop
        bot.menu("Edit").menu("Undo " + SiriusContainerDropPolicy.DROP_ELEMENTS_CMD_NAME).click();
        // Check that there are 7 edges in the DDiagramElement and 7 GMF edges
        checkNumberOfEdges(diagramEditPart, 7);
    }

    /**
     * @param diagramEditPart
     * @param expectedEdges
     */
    private void checkNumberOfEdges(AbstractDDiagramEditPart diagramEditPart, int expectedEdges) {
        assertTrue(diagramEditPart.getModel() instanceof Diagram);
        Diagram diagram = (Diagram) diagramEditPart.getModel();
        assertEquals("Bad number of GMF edges", expectedEdges, diagram.getEdges().size());
        assertTrue(diagram.getElement() instanceof DDiagram);
        DDiagram dDiagram = (DDiagram) diagram.getElement();
        assertEquals("Bad number of edges in DDiagramElement", expectedEdges, dDiagram.getEdges().size());
    }
}
