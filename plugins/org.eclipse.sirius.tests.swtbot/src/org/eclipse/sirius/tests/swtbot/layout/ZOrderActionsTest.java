/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * Check effects of Z-Order actions on edges.
 * 
 * @author lredor
 */
public class ZOrderActionsTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/tools/zorder/";

    private static final String SEMANTIC_RESOURCE_NAME = "My.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "representations.aird";

    private static final String MODELER_RESOURCE_NAME = "My.odesign";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }
    
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        sessionAirdResource = new UIResource(designerProject, "/", REPRESENTATIONS_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test a z-order action using the associated shortcut.
     * 
     * @param actionName
     *            Name of the action to launch
     * @param shift
     *            true if SHIFT modifier key must also be pressed, false otherwise
     * @param c
     *            the character to use (associated with CTRL) for the shortcut
     * @param edgeToCompareName
     *            Name of the edge to compare with (to compare order)
     * @param edgeToMoveBelowEdgeToCompare
     *            Initial state, true if the edgeToMove is initially below the edge(s) to compare and should be over it
     *            after the action, false if the edgeToMove is initially over the edge(s) to compare and should be below
     *            it after the action.
     * @param checkFirstLocationAfterAction
     *            true if the first edge to move is expected to be at the first location in GMF list after the action,
     *            false otherwise
     * @param checkLastLocationAfterAction
     *            true if the last edge to move is expected to be at the last location in GMF list after the action,
     *            false otherwise
     * @param edgesToMoveName
     *            Name of the edges to move (to launch the action on)
     */
    protected void testZOrderActionWithShortcut(String actionName, boolean shift, char c, String edgeToCompareName, boolean edgeToMoveBelowEdgeToCompare, boolean checkFirstLocationAfterAction,
            boolean checkLastLocationAfterAction, String... edgesToMoveName) {
        testZOrderAction(true, actionName, shift, c, edgeToCompareName, edgeToMoveBelowEdgeToCompare, checkFirstLocationAfterAction, checkLastLocationAfterAction, edgesToMoveName);
    }

    /**
     * @param actionName
     *            Name of the action to launch
     * @param edgeToCompareName
     *            Name of the edge to compare with (to compare order)
     * @param edgeToMoveBelowEdgeToCompare
     *            Initial state, true if the edgeToMove is initially below the edge(s) to compare and should be over it
     *            after the action, false if the edgeToMove is initially over the edge(s) to compare and should be below
     *            it after the action.
     * @param checkFirstLocationAfterAction
     *            true if the first edge to move is expected to be at the first location in GMF list after the action,
     *            false otherwise
     * @param checkLastLocationAfterAction
     *            true if the last edge to move is expected to be at the last location in GMF list after the action,
     *            false otherwise
     * @param edgesToMoveName
     *            Name of the edges to move (to launch the action on)
     */
    protected void testZOrderAction(String actionName, String edgeToCompareName, boolean edgeToMoveBelowEdgeToCompare, boolean checkFirstLocationAfterAction, boolean checkLastLocationAfterAction,
            String... edgesToMoveName) {
        testZOrderAction(false, actionName, false, '.', edgeToCompareName, edgeToMoveBelowEdgeToCompare, checkFirstLocationAfterAction, checkLastLocationAfterAction, edgesToMoveName);
    }


    /**
     * 
     * @param useShortcut
     *            true if the shortcut must be used, false if the contextual menu must be used.
     * @param actionName
     *            Name of the action to launch
     * @param shift
     *            true if SHIFT modifier key must also be pressed, false otherwise
     * @param c
     *            the character to use (associated with CTRL) for the shortcut
     * @param edgeToCompareName
     *            Name of the edge to compare with (to compare order)
     * @param edgeToMoveBelowEdgeToCompare
     *            Initial state, true if the edgeToMove is initially below the edge(s) to compare and should be over it
     *            after the action, false if the edgeToMove is initially over the edge(s) to compare and should be below
     *            it after the action.
     * @param checkFirstLocationAfterAction
     *            true if the first edge to move is expected to be at the first location in GMF list after the action,
     *            false otherwise
     * @param checkLastLocationAfterAction
     *            true if the last edge to move is expected to be at the last location in GMF list after the action,
     *            false otherwise
     * @param edgesToMoveName
     *            Name of the edges to move (to launch the action on)
     */
    protected void testZOrderAction(boolean useShortcut, String actionName, boolean shift, char c, String edgeToCompareName, boolean edgeToMoveBelowEdgeToCompare,
            boolean checkFirstLocationAfterAction, boolean checkLastLocationAfterAction, String... edgesToMoveName) {
        // Open diagram with edges (the order of edges is : edge1, edge4, edge6, edge2, edge3, edge5, edge7
        openDiagram("diagramWithNodesAndEdges");

        SWTBotGefEditPart swtBotEditPartEdgeToCompare = editor.getEditPart(edgeToCompareName, AbstractDiagramEdgeEditPart.class);
        // Check initial conditions
        checkInitialConditions(edgeToCompareName, swtBotEditPartEdgeToCompare, edgeToMoveBelowEdgeToCompare, edgesToMoveName);

        // Select the edges
        ArrayList<SWTBotGefEditPart> editPartToSelect = new ArrayList<>();
        for (String edgeToMoveName : edgesToMoveName) {
            editPartToSelect.add(editor.getEditPart(edgeToMoveName, AbstractDiagramEdgeEditPart.class));
        }
        editor.select(editPartToSelect);
        SWTBotUtils.waitAllUiEvents();

        // Launch action
        if (useShortcut) {
            pressShortcut(shift, c);
        } else {
            editor.clickContextMenu(actionName);
            SWTBotUtils.waitAllUiEvents();
        }

        // Ensure that the GMF order and figure order have been changed
        checkState("Wrong application of action" + actionName, edgeToCompareName, swtBotEditPartEdgeToCompare, !edgeToMoveBelowEdgeToCompare, checkFirstLocationAfterAction, checkLastLocationAfterAction,
                edgesToMoveName);

        // Undo the action
        undo();
        // Check that the edges have their initial state
        checkUndoneState(edgeToCompareName, swtBotEditPartEdgeToCompare, edgeToMoveBelowEdgeToCompare, edgesToMoveName);
    }

    private void checkInitialConditions(String edgeToCompareName, SWTBotGefEditPart swtBotEditPartEdgeToCompare, boolean edgeToMoveBelowEdgeToCompare, String... edgesToMoveName) {
        checkState("Wrong initial condition", edgeToCompareName, swtBotEditPartEdgeToCompare, edgeToMoveBelowEdgeToCompare, edgesToMoveName);
    }

    private void checkUndoneState(String edgeToCompareName, SWTBotGefEditPart swtBotEditPartEdgeToCompare, boolean edgeToMoveBelowEdgeToCompare, String... edgesToMoveName) {
        checkState("Wrong undone state", edgeToCompareName, swtBotEditPartEdgeToCompare, edgeToMoveBelowEdgeToCompare, edgesToMoveName);
    }

    private void checkState(String messagePrefix, String edgeToCompareName, SWTBotGefEditPart swtBotEditPartEdgeToCompare, boolean edgeToMoveBelowEdgeToCompare, String... edgesToMoveName) {
        checkState(messagePrefix, edgeToCompareName, swtBotEditPartEdgeToCompare, edgeToMoveBelowEdgeToCompare, false, false, edgesToMoveName);
    }

    private void checkState(String messagePrefix, String edgeToCompareName, SWTBotGefEditPart swtBotEditPartEdgeToCompare, boolean edgeToMoveBelowEdgeToCompare, boolean checkFirstLocationAfterAction,
            boolean checkLastLocationAfterAction, String... edgesToMoveName) {
        Diagram gmfDiagram = (Diagram) editor.getDiagramEditPart().getModel();
        Edge gmfEdgeToCompare = (Edge) swtBotEditPartEdgeToCompare.part().getModel();
        int gmfIndexToCompare = gmfDiagram.getEdges().indexOf(gmfEdgeToCompare);

        IFigure figureEdgeToCompare = ((DEdgeEditPart) swtBotEditPartEdgeToCompare.part()).getFigure();
        List<? extends IFigure> edgeFigures = editor.getDiagramEditPart().getLayer(LayerConstants.CONNECTION_LAYER).getChildren();
        int figureIndexToCompare = edgeFigures.indexOf(figureEdgeToCompare);

        int previousGmfIndexOfMovedEdge = -1;
        int previousFigureIndexOfMovedEdge = -1;
        for (int i = 0; i < edgesToMoveName.length; i++) {
            String edgeToMoveName = edgesToMoveName[i];
            SWTBotGefEditPart swtBotEditPartEdgeToMove = editor.getEditPart(edgeToMoveName, AbstractDiagramEdgeEditPart.class);
            Edge gmfEdgeToMove = (Edge) swtBotEditPartEdgeToMove.part().getModel();
            int gmfIndexOfMovedEdge = gmfDiagram.getEdges().indexOf(gmfEdgeToMove);
            // Check that edge to move is as the expected location compared to "edgeToCompare"
            if (edgeToMoveBelowEdgeToCompare) {
                assertTrue(messagePrefix + ": " + edgeToMoveName + " should be before " + edgeToCompareName + " in the GMF list.",
                        gmfIndexOfMovedEdge < gmfIndexToCompare);
            } else {
                assertTrue(messagePrefix + ": " + edgeToMoveName + " should be after " + edgeToCompareName + " in the GMF list.",
                        gmfIndexOfMovedEdge > gmfIndexToCompare);
            }
            if (checkFirstLocationAfterAction && i == 0) {
                assertTrue(messagePrefix + ": " + edgeToMoveName + " should be at first location in the GMF list, expected 0 but was "
                        + gmfDiagram.getEdges().indexOf(gmfEdgeToMove) + ".", gmfDiagram.getEdges().indexOf(gmfEdgeToMove) == 0);
            }
            if (checkLastLocationAfterAction && i == edgesToMoveName.length) {
                assertTrue(messagePrefix + ": " + edgeToMoveName + " should be at last location in the GMF list, expected " + (gmfDiagram.getEdges().size() - 1)
                        + " but was " + gmfDiagram.getEdges().indexOf(gmfEdgeToMove) + ".", gmfDiagram.getEdges().indexOf(gmfEdgeToMove) == gmfDiagram.getEdges().size() - 1);
            }
            // Check the order of edges to move
            assertTrue(messagePrefix + ": The order of edges to move is not coherent, problem detected with " + edgeToMoveName + ".", previousGmfIndexOfMovedEdge < gmfIndexOfMovedEdge);
            previousGmfIndexOfMovedEdge = gmfIndexOfMovedEdge;

            // Check that the figure corresponding to the edge to move is as the expected location compared to the
            // figure of the "edgeToCompare"
            IFigure figureEdgeToMove = ((DEdgeEditPart) swtBotEditPartEdgeToMove.part()).getFigure();
            int figureIndexOfMovedEdge = edgeFigures.indexOf(figureEdgeToMove);
            if (edgeToMoveBelowEdgeToCompare) {
                assertTrue(messagePrefix + ": Graphically, " + edgeToMoveName + " should be below " + edgeToCompareName + ".",
                        figureIndexOfMovedEdge < figureIndexToCompare);
            } else {
                assertTrue(messagePrefix + ": Graphically, " + edgeToMoveName + " should be over " + edgeToCompareName + ".",
                        figureIndexOfMovedEdge > figureIndexToCompare);
            }
            // Check the order of the figures corresponding to edges to move
            assertTrue("The order of figures is not coherent, problem detected with " + edgeToMoveName + ".", previousFigureIndexOfMovedEdge < figureIndexOfMovedEdge);
            previousFigureIndexOfMovedEdge = figureIndexOfMovedEdge;
        }
    }

    /**
     * Send to back one edge with contextual menu.
     */
    public void testOneEdgeSendToBack() {
        testZOrderAction("Send to Back", "edge1", false, true, false, "edge2");
    }

    /**
     * Bring to front one edge with contextual menu.
     */
    public void testOneEdgeBringToFront() {
        testZOrderAction("Bring to Front", "edge2", true, false, true, "edge1");
    }

    /**
     * Send backward one edge with contextual menu.
     */
    public void testOneEdgeSendBackward() {
        testZOrderAction("Send Backward", "edge6", false, false, false, "edge7");
    }

    /**
     * Bring forward one edge with contextual menu.
     */
    public void testOneEdgeBringForward() {
        testZOrderAction("Bring Forward", "edge3", true, false, false, "edge6");
    }

    /**
     * Send to back one edge with shortcut.
     */
    public void testOneEdgeSendToBackWithShortcut() {
        testZOrderActionWithShortcut("Send to Back", true, 'b', "edge1", false, true, false, "edge2");
    }

    /**
     * Bring to front one edge with shortcut.
     */
    public void testOneEdgeBringToFrontWithShortcut() {
        testZOrderActionWithShortcut("Bring to Front", true, 'f', "edge2", true, false, true, "edge1");
    }

    /**
     * Send backward one edge with shortcut.
     */
    public void testOneEdgeSendBackwardWithShortcut() {
        testZOrderActionWithShortcut("Send Backward", false, 'b', "edge6", false, false, false, "edge7");
    }

    /**
     * Bring forward one edge with shortcut.
     */
    public void testOneEdgeBringForwardWithShortcut() {
        testZOrderActionWithShortcut("Bring Forward", false, 'f', "edge3", true, false, false, "edge6");
    }

    /**
     * Send to back several edges with contextual menu.
     */
    public void testSeveralEdgesSendToBack() {
        testZOrderAction("Send to Back", "edge1", false, true, false, "edge4", "edge3");
    }

    /**
     * Bring to front several edgse with contextual menu.
     */
    public void testSeveralEdgesBringToFront() {
        testZOrderAction("Bring to Front", "edge7", true, false, true, "edge1", "edge6");
    }

    /**
     * Send backward several edges with contextual menu.
     */
    public void testSeveralEdgesSendBackward() {
        testZOrderAction("Send Backward", "edge1", false, false, false, "edge4", "edge2");
    }

    /**
     * Bring forward several edges with contextual menu.
     */
    public void testSeveralEdgesBringForward() {
        testZOrderAction("Bring Forward", "edge5", true, false, false, "edge1", "edge2");
    }

    private void openDiagram(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Diag", representationName, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();
    }
    
    /**
     * Press shortcut CTRL+char or CTRL+SHIFT+char.
     * 
     * @param shift
     *            true if SHIFT modifier key must also be pressed, false otherwise
     * @param c
     *            the character
     */
    private void pressShortcut(boolean shift, char c) {
        String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        if (shift) {
            editor.getCanvas().pressShortcut(SWT.CTRL | SWT.SHIFT, c);
        } else {
            editor.getCanvas().pressShortcut(SWT.CTRL, c);
        }
        SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;
        SWTBotUtils.waitAllUiEvents();
    }
}
