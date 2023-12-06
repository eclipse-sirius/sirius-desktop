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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;

/**
 * @author smonnier
 */
@SuppressWarnings("nls")
public class ArrangeAllLinkedBorderedNodesLayoutStabilityTest extends AbstractArrangeAllTest
        implements PositionConstants {

    /**
     * A safety margin because of the method used to compute the port location
     * (sometimes, near the corner for example or if the edge is flat, the
     * location can be better).
     */
    private static final int SAFETY_MARGIN = 5;

    private static final String REPRESENTATION_NAME_UC1 = "TC1957";

    private static final String REPRESENTATION_NAME_UC2 = "TC1957_withoutLabel";

    private static final String REPRESENTATION_NAME_UC3 = "TC1957_Container";

    private static final String REPRESENTATION_NAME_UC4 = "TC1957_Container_LeftRight";

    private static final String REPRESENTATION_NAME_UC5 = "testReconnect";

    private static final String REPRESENTATION_INSTANCE_NAME_UC1 = "UseCase1";

    private static final String REPRESENTATION_INSTANCE_NAME_UC2 = "UseCase2";

    private static final String REPRESENTATION_INSTANCE_NAME_UC3 = "UseCase3";

    private static final String REPRESENTATION_INSTANCE_NAME_UC4 = "UseCase4";

    private static final String REPRESENTATION_INSTANCE_NAME_UC5_1 = "UseCase5-1";

    private static final String REPRESENTATION_INSTANCE_NAME_UC5_2 = "UseCase5-2";

    private static final String REPRESENTATION_INSTANCE_NAME_UC5_3 = "UseCase5-3";

    private static final String REPRESENTATION_INSTANCE_NAME_UC5_4 = "UseCase5-4";

    private static final String REPRESENTATION_INSTANCE_NAME_UC5_5 = "UseCase5-5";

    private static final String REPRESENTATION_INSTANCE_NAME_UC5_6 = "UseCase5-6";

    private static final String REPRESENTATION_INSTANCE_NAME_UC5_7 = "UseCase5-7";

    private static final String REPRESENTATION_INSTANCE_NAME_UC5_8 = "UseCase5-8";

    private static final String REPRESENTATION_INSTANCE_NAME_UC6 = "UseCase6";

    private static final String REPRESENTATION_INSTANCE_NAME_UC7 = "new testReconnect";

    private static final String REPRESENTATION_INSTANCE_NAME_UC8 = "simpleCase";

    private static final String REPRESENTATION_INSTANCE_NAME_UC9 = "simpleCase2";

    private static final String REPRESENTATION_INSTANCE_NAME_UC10 = "complexeCase";

    private static final String MODEL = "tc1957.ecore";

    private static final String MODEL2 = "testReconnect.ecore";

    private static final String SESSION_FILE = "tc1957.aird";

    private static final String VSM_FILE = "tc1957.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/borderedNodes/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * True if SnapToGrid is activated on editor, false otherwise.
     */
    protected boolean snapToGrid;

    /**
     * Step used for the grid spacing.
     */
    protected static final int GRID_STEP = 20;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // Disable refresh on opening
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, MODEL2, SESSION_FILE, VSM_FILE);
    }

    private void arrangeLinkedBorderedNodes() {
        if (snapToGrid) {
            editor.setSnapToGrid(true, GRID_STEP, 2);
        }
        editor.clickContextMenu("All Linked Border Nodes");
    }

    private void arrangeLinkedBorderedNodesOfSelection() {
        if (snapToGrid) {
            editor.setSnapToGrid(true, GRID_STEP, 2);
        }
        editor.clickContextMenu("Linked Border Nodes");
    }

    private void arrangeAllMenu() {
        if (snapToGrid) {
            editor.setSnapToGrid(true, GRID_STEP, 2);
        }
        arrangeAll();
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * The layout with label (usecase1) is problematic because the label can
     * take the place of the port.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase1() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC1, REPRESENTATION_INSTANCE_NAME_UC1, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeAllMenu();
        // Validate the positions of the bordered nodes.
        // We don't check the edges crossing because the label on the border
        // nodes can cause crossing (indeed a label can take the place of a
        // border node).
        validatePositions(false, false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase2() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC2, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeAllMenu();
        // Validate the positions of the border nodes.
        validatePositions(false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase3() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC3, REPRESENTATION_INSTANCE_NAME_UC3, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeAllMenu();
        // Validate the positions of the border nodes.
        validatePositions(true);
    }

    /**
     * Test method. This test check a specific use case an vertical container
     * became an horizontal container so the size after arrange is needed (and
     * it's not often the case). <BR>
     * <B>Currently, this test failed</B> : In this case, the calculation is OK
     * (or almost) but during the creation of the command the DBorderItemLocator
     * compute the real location with the parent figure (which has not been
     * resized). It is resized later with a refreshVisuals after the execution
     * of all commands.
     * TODO : Correct the problem (if possible) and reactivate this test.
     * 
     * @throws Exception
     *             Test error.
     */
    public void failedTestArrangeLinkedBorderNodesCase4() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC4, REPRESENTATION_INSTANCE_NAME_UC4, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeAllMenu();
        // Validate the positions of the border nodes.
        validatePositions(true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase5_1() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC5_1, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionsOfUseCase5();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase5_2() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC5_2, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionsOfUseCase5();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase5_3() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC5_3, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionsOfUseCase5();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase5_4() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC5_4, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionsOfUseCase5();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase5_5() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC5_5, DDiagram.class, true, true);
        arrangeLinkedBorderedNodes();
        // Validate the positions of the bordered nodes.
        validatePositionsOfUseCase5();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase5_6() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC5_6, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionsOfUseCase5();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase5_7() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC5_7, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionsOfUseCase5();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase5_8() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC2, REPRESENTATION_INSTANCE_NAME_UC5_8, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionsOfUseCase5();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase6() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC3, REPRESENTATION_INSTANCE_NAME_UC6, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositions(true);
    }

    private void pinAll() {
        bot.toolbarButtonWithTooltip("Pin/Unpin").click();
        SWTBot pinDialogBot = bot.shell("Diagram elements pinning").bot();
        pinDialogBot.buttonWithTooltip("Check All").click();
        pinDialogBot.button("OK").click();
    }

    /**
     * Test "Arrange Linked Border Nodes" with the "Move Pinned Elements" option enabled and with all elements pinned
     * (equivalent to an "Arrange Linked Border Nodes" without pinned elements).
     */
    public void testArrangeLinkedBorderNodesIgnorePin() throws Exception {
        final String prefKey = SiriusDiagramPreferencesKeys.PREF_MOVE_PINNED_ELEMENTS.name();

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_UC3, REPRESENTATION_INSTANCE_NAME_UC6, DDiagram.class, true, true);

        pinAll();

        // enable "Move Pinned Elements" option
        assertFalse("Wrong initial state for Move Pinned Elements", InstanceScope.INSTANCE.getNode(DiagramPlugin.ID).getBoolean(prefKey, false));
        movePinnedElements();
        assertTrue("Wrong state for Move Pinned Elements after enabled", InstanceScope.INSTANCE.getNode(DiagramPlugin.ID).getBoolean(prefKey, false));

        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositions(true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase7() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC5, REPRESENTATION_INSTANCE_NAME_UC7, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionOfPortOnContainer("package2", "eClass4");
        validatePositionOfPortOnContainer("package1", "eClass2");
        validatePositionOfPortOnContainer("Package1", "eClass2");
        validatePositionOfPortOnContainer("Package1", "eClass4");
        validatePositionOfPortOnContainer("Package2", "eClass4");
        validatePositionOfPortOnContainer("Package2", "eClass1");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase7WithZoom() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC5, REPRESENTATION_INSTANCE_NAME_UC7, DDiagram.class, true, true);
        editor.zoom(ZoomLevel.ZOOM_200);
        try {
            // activate the "Arrange Linked Border Nodes" action
            arrangeLinkedBorderedNodes();
            // Validate the positions of the border nodes.
            validatePositionOfPortOnContainer("package2", "eClass4");
            validatePositionOfPortOnContainer("package1", "eClass2");
            validatePositionOfPortOnContainer("Package1", "eClass2");
            validatePositionOfPortOnContainer("Package1", "eClass4");
            validatePositionOfPortOnContainer("Package2", "eClass4");
            validatePositionOfPortOnContainer("Package2", "eClass1");
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase8() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC5, REPRESENTATION_INSTANCE_NAME_UC8, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionOfPortOnContainer("package1", "eClass2");
        validatePositionOfPortOnContainer("package2", "eClass4");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase8WithZoom() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC5, REPRESENTATION_INSTANCE_NAME_UC8, DDiagram.class, true, true);
        editor.zoom(ZoomLevel.ZOOM_200);
        try {
            // activate the "Arrange Linked Border Nodes" action
            arrangeLinkedBorderedNodes();
            // Validate the positions of the border nodes.
            validatePositionOfPortOnContainer("package1", "eClass2");
            validatePositionOfPortOnContainer("package2", "eClass4");
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase9() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC5, REPRESENTATION_INSTANCE_NAME_UC9, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionOfPortOnContainer("package1", "eClass2");
        validatePositionOfPortOnContainer("package2", "eClass4");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase9WithZoom() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC5, REPRESENTATION_INSTANCE_NAME_UC9, DDiagram.class, true, true);
        editor.zoom(ZoomLevel.ZOOM_200);
        try {
            // activate the "Arrange Linked Border Nodes" action
            arrangeLinkedBorderedNodes();
            // Validate the positions of the border nodes.
            validatePositionOfPortOnContainer("package1", "eClass2");
            validatePositionOfPortOnContainer("package2", "eClass4");
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase10() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC5, REPRESENTATION_INSTANCE_NAME_UC10, DDiagram.class, true, true);
        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodes();
        // Validate the positions of the border nodes.
        validatePositionOfPortOnContainer("Package1", "eClass2");
        validatePositionOfPortOnContainer("Package1", "eClass4");
        validatePositionOfPortOnContainer("Package2", "eClass1");
        validatePositionOfPortOnContainer("Package2", "eClass4");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderNodesCase10WithZoom() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
                REPRESENTATION_NAME_UC5, REPRESENTATION_INSTANCE_NAME_UC10, DDiagram.class, true, true);
        editor.zoom(ZoomLevel.ZOOM_200);
        try {
            // activate the "Arrange Linked Border Nodes" action
            arrangeLinkedBorderedNodes();
            // Validate the positions of the border nodes.
            validatePositionOfPortOnContainer("Package1", "eClass2");
            validatePositionOfPortOnContainer("Package1", "eClass4");
            validatePositionOfPortOnContainer("Package2", "eClass1");
            validatePositionOfPortOnContainer("Package2", "eClass4");
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test the <code>Arrange Linked Border Node</code> for selection, with a model that contains no child
     * nodes/containers (except for border nodes and the diagram itself).
     */
    public void testArrangeLinkedBorderNodesSelection() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_UC1, REPRESENTATION_INSTANCE_NAME_UC1, DDiagram.class, true, true);

        // select P1, p3 and p4
        editor.select(editor.getEditPart("P1").parent(), editor.getEditPart("p3").parent(), editor.getEditPart("p4").parent());

        // save initial position of the unselected border node
        HashMap<String, Rectangle> initialPosition = new HashMap<String, Rectangle>();
        initialPosition.put("C21", getInitialPosition("p2", "C21"));
        initialPosition.put("C22", getInitialPosition("p2", "C22"));

        initialPosition.put("C51", getInitialPosition("p5", "C51"));
        initialPosition.put("C52", getInitialPosition("p5", "C52"));
        initialPosition.put("C53", getInitialPosition("p5", "C53"));
        initialPosition.put("C54", getInitialPosition("p5", "C54"));

        initialPosition.put("C61", getInitialPosition("p6", "C61"));
        initialPosition.put("C62", getInitialPosition("p6", "C62"));
        initialPosition.put("C63", getInitialPosition("p6", "C63"));
        initialPosition.put("C64", getInitialPosition("p6", "C64"));

        initialPosition.put("C71", getInitialPosition("p7", "C71"));
        initialPosition.put("C72", getInitialPosition("p7", "C72"));
        initialPosition.put("C73", getInitialPosition("p7", "C73"));

        initialPosition.put("C101", getInitialPosition("p10", "C101"));

        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodesOfSelection();

        // Validate the positions of all bordered nodes
        validatePositionOfPortOnContainer("P1", "C11");
        validatePositionOfPortOnContainer("P1", "C12");

        validateInitialPosition("p2", "C21", initialPosition.get("C21"));
        validateInitialPosition("p2", "C22", initialPosition.get("C22"));

        // Due to a label, grid alignment is not possible for this node.
        // So we force the internal method call to bypass the call to
        // a child method that overrides the behavior by adding the grid check.
        internalValidatePositionOfPortOnContainer("p3", "C31", false);
        validatePositionOfPortOnContainer("p3", "C32");
        validatePositionOfPortOnContainer("p3", "C33");

        validatePositionOfPortOnContainer("p4", "C41");
        validatePositionOfPortOnContainer("p4", "C42");
        validatePositionOfPortOnContainer("p4", "C43");

        validateInitialPosition("p5", "C51", initialPosition.get("C51"));
        validateInitialPosition("p5", "C52", initialPosition.get("C52"));
        validateInitialPosition("p5", "C53", initialPosition.get("C53"));
        validateInitialPosition("p5", "C54", initialPosition.get("C54"));

        validateInitialPosition("p6", "C61", initialPosition.get("C61"));
        validateInitialPosition("p6", "C62", initialPosition.get("C62"));
        validateInitialPosition("p6", "C63", initialPosition.get("C63"));
        validateInitialPosition("p6", "C64", initialPosition.get("C64"));

        validateInitialPosition("p7", "C71", initialPosition.get("C71"));
        validateInitialPosition("p7", "C72", initialPosition.get("C72"));
        validateInitialPosition("p7", "C73", initialPosition.get("C73"));

        validateInitialPosition("p10", "C101", initialPosition.get("C101"));
    }

    /**
     * Test the <code>Arrange Linked Border Node</code> for selection, with a model that contains children/sub-children.
     */
    public void testArrangeLinkedBorderNodesSelectionWithChildren() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_UC3, REPRESENTATION_INSTANCE_NAME_UC6, DDiagram.class, true, true);

        // select P1, p3, p4, p8 and p17
        editor.select(//
                editor.getEditPart("P1").parent(), //
                editor.getEditPart("p3").parent(), //
                editor.getEditPart("p4").parent(), //
                editor.getEditPart("p8").parent(), //
                editor.getEditPart("p17").parent() //
        );

        // save initial position of the unselected border node
        HashMap<String, Rectangle> initialPosition = new HashMap<String, Rectangle>();
        initialPosition.put("C21", getInitialPosition("p2", "C21"));
        initialPosition.put("C22", getInitialPosition("p2", "C22"));

        initialPosition.put("C51", getInitialPosition("p5", "C51"));
        initialPosition.put("C52", getInitialPosition("p5", "C52"));
        initialPosition.put("C53", getInitialPosition("p5", "C53"));
        initialPosition.put("C54", getInitialPosition("p5", "C54"));

        initialPosition.put("C61", getInitialPosition("p6", "C61"));
        initialPosition.put("C62", getInitialPosition("p6", "C62"));
        initialPosition.put("C63", getInitialPosition("p6", "C63"));
        initialPosition.put("C64", getInitialPosition("p6", "C64"));

        initialPosition.put("C71", getInitialPosition("p7", "C71"));
        initialPosition.put("C72", getInitialPosition("p7", "C72"));
        initialPosition.put("C73", getInitialPosition("p7", "C73"));

        initialPosition.put("C91", getInitialPosition("p9", "C91"));
        initialPosition.put("C92", getInitialPosition("p9", "C92"));
        initialPosition.put("C93", getInitialPosition("p9", "C93"));

        initialPosition.put("C101", getInitialPosition("p10", "C101"));
        initialPosition.put("C111", getInitialPosition("p11", "C111"));
        initialPosition.put("C121", getInitialPosition("p12", "C121"));

        // activate the "Arrange Linked Border Nodes" action
        arrangeLinkedBorderedNodesOfSelection();

        // Validate the positions of all bordered nodes
        validatePositionOfPortOnContainer("P1", "C11");
        validatePositionOfPortOnContainer("P1", "C12");

        validateInitialPosition("p2", "C21", initialPosition.get("C21"));
        validateInitialPosition("p2", "C22", initialPosition.get("C22"));

        validatePositionOfPortOnContainer("p3", "C31");
        validatePositionOfPortOnContainer("p3", "C32");
        validatePositionOfPortOnContainer("p3", "C33");

        validatePositionOfPortOnContainer("p4", "C41");
        validatePositionOfPortOnContainer("p4", "C42");
        validatePositionOfPortOnContainer("p4", "C43");

        validateInitialPosition("p5", "C51", initialPosition.get("C51"));
        validateInitialPosition("p5", "C52", initialPosition.get("C52"));
        validateInitialPosition("p5", "C53", initialPosition.get("C53"));
        validateInitialPosition("p5", "C54", initialPosition.get("C54"));

        validateInitialPosition("p6", "C61", initialPosition.get("C61"));
        validateInitialPosition("p6", "C62", initialPosition.get("C62"));
        validateInitialPosition("p6", "C63", initialPosition.get("C63"));
        validateInitialPosition("p6", "C64", initialPosition.get("C64"));

        validateInitialPosition("p7", "C71", initialPosition.get("C71"));
        validateInitialPosition("p7", "C72", initialPosition.get("C72"));
        validateInitialPosition("p7", "C73", initialPosition.get("C73"));

        validatePositionOfPortOnContainer("p8", "C81");

        validateInitialPosition("p9", "C91", initialPosition.get("C91"));
        validateInitialPosition("p9", "C92", initialPosition.get("C92"));
        validateInitialPosition("p9", "C93", initialPosition.get("C93"));

        validateInitialPosition("p10", "C101", initialPosition.get("C101"));

        validateInitialPosition("p11", "C111", initialPosition.get("C111"));

        validateInitialPosition("p12", "C121", initialPosition.get("C121"));
    }

    /**
     * Validate the positions of all bordered nodes.
     * 
     * @param isDiagramWithRecursivePackage
     *            true if the diagram to validate is with recursive package
     * @param validateEdgeCrossing
     *            true if this method must check the edge crossing, false otherwise
     */
    private void validatePositions(boolean isDiagramWithRecursivePackage, boolean validateEdgeCrossing) {
        validatePositionOfPortOnContainer("P1", "C11");
        validatePositionOfPortOnContainer("P1", "C12");

        validatePositionOfPortOnContainer("p2", "C21");
        validatePositionOfPortOnContainer("p2", "C22");

        validatePositionOfPortOnContainer("p3", "C31");
        validatePositionOfPortOnContainer("p3", "C32");
        validatePositionOfPortOnContainer("p3", "C33");

        validatePositionOfPortOnContainer("p4", "C41");
        validatePositionOfPortOnContainer("p4", "C42");
        validatePositionOfPortOnContainer("p4", "C43");

        validatePositionOfPortOnContainer("p5", "C51");
        validatePositionOfPortOnContainer("p5", "C52");
        validatePositionOfPortOnContainer("p5", "C53");
        validatePositionOfPortOnContainer("p5", "C54");

        validatePositionOfPortOnContainer("p6", "C61");
        validatePositionOfPortOnContainer("p6", "C62");
        validatePositionOfPortOnContainer("p6", "C63");
        validatePositionOfPortOnContainer("p6", "C64");

        validatePositionOfPortOnContainer("p7", "C71");
        validatePositionOfPortOnContainer("p7", "C72");
        validatePositionOfPortOnContainer("p7", "C73");

        validatePositionOfPortOnContainer("p10", "C101");

        if (isDiagramWithRecursivePackage) {
            validatePositionOfPortOnContainer("p8", "C81");

            validatePositionOfPortOnContainer("p9", "C91");
            validatePositionOfPortOnContainer("p9", "C92");
            validatePositionOfPortOnContainer("p9", "C93");

            validatePositionOfPortOnContainer("p12", "C121");

            validatePositionOfPortOnContainer("p11", "C111");
        }

        if (validateEdgeCrossing) {
            validateCrossEdges(isDiagramWithRecursivePackage);
        }
    }

    /**
     * Validate the positions of all bordered nodes.
     * 
     * @param isDiagramWithrecursivePackage
     *            true if the diagram to validate is with recursive package
     */
    private void validatePositions(final boolean isDiagramWithRecursivePackage) {
        validatePositions(isDiagramWithRecursivePackage, true);
    }

    /**
     * @param isDiagramWithRecursivePackage
     */
    private void validateCrossEdges(boolean isDiagramWithRecursivePackage) {
        String message = "Some intersections exist after layout";
        assertNull(message, getLineSeg("P1", "C11", "p2", "C21").intersect(getLineSeg("P1", "C12", "p2", "C22"), 0));

        assertNull(message, getLineSeg("p3", "C31", "p4", "C41").intersect(getLineSeg("p3", "C32", "p4", "C42"), 0));
        assertNull(message, getLineSeg("p3", "C31", "p4", "C41").intersect(getLineSeg("p3", "C33", "p4", "C43"), 0));

        assertNull(message, getLineSeg("p3", "C32", "p4", "C42").intersect(getLineSeg("p3", "C33", "p4", "C43"), 0));

        assertNull(message, getLineSeg("p5", "C51", "p6", "C61").intersect(getLineSeg("p5", "C52", "p6", "C62"), 0));
        assertNull(message, getLineSeg("p5", "C51", "p6", "C61").intersect(getLineSeg("p5", "C53", "p6", "C64"), 0));
        assertNull(message, getLineSeg("p5", "C51", "p6", "C61").intersect(getLineSeg("p5", "C54", "p6", "C63"), 0));

        assertNull(message, getLineSeg("p5", "C52", "p6", "C62").intersect(getLineSeg("p5", "C53", "p6", "C64"), 0));
        assertNull(message, getLineSeg("p5", "C52", "p6", "C62").intersect(getLineSeg("p5", "C54", "p6", "C63"), 0));

        assertNull(message, getLineSeg("p5", "C53", "p6", "C64").intersect(getLineSeg("p5", "C54", "p6", "C63"), 0));

        if (isDiagramWithRecursivePackage) {
            assertNull(message,
                    getLineSeg("p7", "C71", "p9", "C92").intersect(getLineSeg("p7", "C72", "p9", "C91"), 0));
            assertNull(message,
                    getLineSeg("p7", "C71", "p9", "C92").intersect(getLineSeg("p7", "C73", "p10", "C101"), 0));

            assertNull(message,
                    getLineSeg("p7", "C72", "p9", "C91").intersect(getLineSeg("p7", "C73", "p10", "C101"), 0));
        }
    }

    /**
     * Validate the positions of all bordered nodes.
     */
    private void validatePositionsOfUseCase5() {
        validatePositionOfPortOnContainer("p5", "C51");
        validatePositionOfPortOnContainer("p5", "C52");
        validatePositionOfPortOnContainer("p5", "C53");
        validatePositionOfPortOnContainer("p5", "C54");

        validatePositionOfPortOnContainer("p6", "C61");
        validatePositionOfPortOnContainer("p6", "C62");
        validatePositionOfPortOnContainer("p6", "C63");
        validatePositionOfPortOnContainer("p6", "C64");

        String message = "Some intersections exist after layout";
        assertNull(message, getLineSeg("p5", "C51", "p6", "C61").intersect(getLineSeg("p5", "C52", "p6", "C62"), 0));
        assertNull(message, getLineSeg("p5", "C51", "p6", "C61").intersect(getLineSeg("p5", "C53", "p6", "C64"), 0));
        assertNull(message, getLineSeg("p5", "C51", "p6", "C61").intersect(getLineSeg("p5", "C54", "p6", "C63"), 0));

        assertNull(message, getLineSeg("p5", "C52", "p6", "C62").intersect(getLineSeg("p5", "C53", "p6", "C64"), 0));
        assertNull(message, getLineSeg("p5", "C52", "p6", "C62").intersect(getLineSeg("p5", "C54", "p6", "C63"), 0));

        assertNull(message, getLineSeg("p5", "C53", "p6", "C64").intersect(getLineSeg("p5", "C54", "p6", "C63"), 0));
    }

    /**
     * @param sourceContainerName
     * @param sourcePortName
     * @param targetContainerName
     * @param targetPortName
     * @return
     */
    private LineSeg getLineSeg(String sourceContainerName, String sourcePortName, String targetContainerName,
            String targetPortName) {
        SWTBotGefEditPart swtbotSourceContainerEditPart = editor.getEditPart(sourceContainerName);
        assertNotNull("No container edit part found with this name", swtbotSourceContainerEditPart);
        EditPart sourceContainerSquareEP = swtbotSourceContainerEditPart.part();
        assertTrue("The parent edit part of the container label is not a AbstractBorderedShapeEditPart",
                sourceContainerSquareEP.getParent() instanceof AbstractBorderedShapeEditPart);
        final AbstractBorderedShapeEditPart sourceContainerEP = (AbstractBorderedShapeEditPart) sourceContainerSquareEP
                .getParent();
        final AbstractDiagramBorderNodeEditPart sourcePortEP = findPortInContainer(sourceContainerEP, sourcePortName);
        assertNotNull("No port edit part found with this name", sourcePortEP);

        SWTBotGefEditPart swtbotTargetContainerEditPart = editor.getEditPart(targetContainerName);
        assertNotNull("No container edit part found with this name", swtbotTargetContainerEditPart);
        EditPart targetContainerSquareEP = swtbotTargetContainerEditPart.part();
        assertTrue("The parent edit part of the container label is not a AbstractBorderedShapeEditPart",
                targetContainerSquareEP.getParent() instanceof AbstractBorderedShapeEditPart);
        final AbstractBorderedShapeEditPart targetContainerEP = (AbstractBorderedShapeEditPart) targetContainerSquareEP
                .getParent();
        final AbstractDiagramBorderNodeEditPart targetPortEP = findPortInContainer(targetContainerEP, targetPortName);
        assertNotNull("No port edit part found with this name", targetPortEP);

        return new LineSeg(editor.getAbsoluteCenter(sourcePortEP), editor.getAbsoluteCenter(targetPortEP));
    }

    /**
     * Check that the port is arranged correctly.
     * 
     * @param containerName
     *            The container name
     * @param portName
     *            The port name
     */
    private void validatePositionOfPortOnContainer(String containerName, String portName) {
        validatePositionOfPortOnContainer(containerName, portName, false);
    }

    /**
     * If the absolute rectangle (absolute position and size) of the port <code>portName</code> contained in container
     * <code>containerName</code> is same as <code>initialRectangle</code> (same x, y, width and height), does nothing,
     * otherwise, fail with message.
     */
    private void validateInitialPosition(String containerName, String portName, Rectangle initialRectangle) {
        SWTBotGefEditPart swtbotContainerEditPart = editor.getEditPart(containerName);

        assertNotNull("No container edit part found with this name", swtbotContainerEditPart);

        EditPart containerSquareEP = swtbotContainerEditPart.part();

        assertTrue("The parent edit part of the container label is not a AbstractBorderedShapeEditPart", containerSquareEP.getParent() instanceof AbstractBorderedShapeEditPart);
        final AbstractBorderedShapeEditPart containerEP = (AbstractBorderedShapeEditPart) containerSquareEP.getParent();

        AbstractDiagramBorderNodeEditPart part = findPortInContainer(containerEP, portName);
        Rectangle after = part.getFigure().getBounds();
        assertEquals("The port " + portName + " (container " + containerName + ") has been moved even though it is not in the selection", initialRectangle, after);
    }

    /**
     * Return the absolute rectangle (absolute position and size) of the port <code>portName</code> contained in
     * container <code>containerName</code>.
     */
    private Rectangle getInitialPosition(String containerName, String portName) {
        SWTBotGefEditPart swtbotContainerEditPart = editor.getEditPart(containerName);

        assertNotNull("No container edit part found with this name", swtbotContainerEditPart);

        EditPart containerSquareEP = swtbotContainerEditPart.part();

        assertTrue("The parent edit part of the container label is not a AbstractBorderedShapeEditPart", containerSquareEP.getParent() instanceof AbstractBorderedShapeEditPart);
        final AbstractBorderedShapeEditPart containerEP = (AbstractBorderedShapeEditPart) containerSquareEP.getParent();

        AbstractDiagramBorderNodeEditPart part = findPortInContainer(containerEP, portName);
        return part.getFigure().getBounds();
    }

    /**
     * Check that the port is arranged correctly.
     * 
     * @param containerName
     *            The container name
     * @param portName
     *            The port name
     * @param pinBorderedNodes
     *            true if the port must be pin during the validation of it
     */
    protected void validatePositionOfPortOnContainer(String containerName, String portName, boolean pinBorderedNodes) {
        internalValidatePositionOfPortOnContainer(containerName, portName, pinBorderedNodes);
    }

    /**
     * See {@link #validatePositionOfPortOnContainer(String, String, boolean)}
     */
    private void internalValidatePositionOfPortOnContainer(String containerName, String portName, boolean pinBorderedNodes) {
        SWTBotGefEditPart swtbotContainerEditPart = editor.getEditPart(containerName);

        assertNotNull("No container edit part found with this name", swtbotContainerEditPart);

        EditPart containerSquareEP = swtbotContainerEditPart.part();

        assertTrue("The parent edit part of the container label is not a AbstractBorderedShapeEditPart",
                containerSquareEP.getParent() instanceof AbstractBorderedShapeEditPart);
        final AbstractBorderedShapeEditPart containerEP = (AbstractBorderedShapeEditPart) containerSquareEP.getParent();
        final AbstractDiagramBorderNodeEditPart portEP = findPortInContainer(containerEP, portName);

        assertNotNull("No port edit part found with this name", portEP);

        if (pinBorderedNodes) {
            pinDiagramElement((DDiagramElement) ((Node) portEP.getModel()).getElement());
        }

        if (!snapToGrid) {
            // This test is not done when snapToGrid is enabled as the snap can
            // break the "arrange all linked border nodes" rules.
            boolean edgeFromPortCrossParentContainer = validateEdgeFromPortCrossParentContainer(containerEP, portEP);
            assertFalse("The port " + portName + " has an edge that cross the parent container " + containerName,
                edgeFromPortCrossParentContainer);
        }
        boolean validateEdgeFromPortHaveBendpointsReset = validateEdgeFromPortHaveBendpointsReset(containerEP, portEP);
        assertTrue("The port " + portName + " has an edge that don't have its bendpoints reset " + containerName,
                validateEdgeFromPortHaveBendpointsReset);
    }

    private boolean validateEdgeFromPortCrossParentContainer(final AbstractBorderedShapeEditPart containerEP,
            final AbstractDiagramBorderNodeEditPart portEP) {
        Rectangle containerBounds = containerEP.getFigure().getBounds().getCopy();
        containerEP.getFigure().translateToAbsolute(containerBounds);
        List<AbstractDiagramEdgeEditPart> edgesEP = listEdgesConnectedToPort(portEP);
        if (edgesEP.size() == 1) {
            for (AbstractDiagramEdgeEditPart edgeEP : edgesEP) {
                int north = findNorth(edgeEP);
                int south = findSouth(edgeEP);
                int west = findWest(edgeEP);
                int east = findEast(edgeEP);
                Rectangle edgeBounds = new Rectangle(west + SAFETY_MARGIN, north + SAFETY_MARGIN,
                        east - west - (SAFETY_MARGIN * 2), south - north - (SAFETY_MARGIN * 2));
                return edgeBounds.intersects(containerBounds);
            }
        }
        return false;
    }

    private boolean validateEdgeFromPortHaveBendpointsReset(final AbstractBorderedShapeEditPart containerEP,
            final AbstractDiagramBorderNodeEditPart portEP) {
        List<AbstractDiagramEdgeEditPart> edgesEP = listEdgesConnectedToPort(portEP);
        if (!edgesEP.isEmpty()) {
            for (AbstractDiagramEdgeEditPart edgeEP : edgesEP) {
                Bendpoints bendpoints = ((Edge) edgeEP.getNotationView()).getBendpoints();
                if (bendpoints instanceof RelativeBendpoints
                    && ((RelativeBendpoints) bendpoints).getPoints().size() != 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private int findNorth(final AbstractDiagramEdgeEditPart edgeEP) {
        int north = Integer.MAX_VALUE;
        PointList points = edgeEP.getPolylineConnectionFigure().getPoints().getCopy();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.getPoint(i);
            edgeEP.getPolylineConnectionFigure().translateToAbsolute(point);
            if (point.y < north)
                north = point.y;
        }
        return north;
    }

    private int findSouth(final AbstractDiagramEdgeEditPart edgeEP) {
        int south = Integer.MIN_VALUE;
        PointList points = edgeEP.getPolylineConnectionFigure().getPoints().getCopy();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.getPoint(i);
            edgeEP.getPolylineConnectionFigure().translateToAbsolute(point);
            if (point.y > south)
                south = point.y;
        }
        return south;
    }

    private int findWest(final AbstractDiagramEdgeEditPart edgeEP) {
        int west = Integer.MAX_VALUE;
        PointList points = edgeEP.getPolylineConnectionFigure().getPoints().getCopy();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.getPoint(i);
            edgeEP.getPolylineConnectionFigure().translateToAbsolute(point);
            if (point.x < west)
                west = point.x;
        }
        return west;
    }

    private int findEast(final AbstractDiagramEdgeEditPart edgeEP) {
        int west = Integer.MIN_VALUE;
        PointList points = edgeEP.getPolylineConnectionFigure().getPoints().getCopy();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.getPoint(i);
            edgeEP.getPolylineConnectionFigure().translateToAbsolute(point);
            if (point.x > west)
                west = point.x;
        }
        return west;
    }

    protected List<AbstractDiagramEdgeEditPart> listEdgesConnectedToPort(final AbstractDiagramBorderNodeEditPart portEP) {
        ArrayList<AbstractDiagramEdgeEditPart> linkedPorts = new ArrayList<AbstractDiagramEdgeEditPart>();
        Iterator<?> targetIterator = portEP.getTargetConnections().iterator();
        while (targetIterator.hasNext()) {
            Object object = targetIterator.next();
            if (object instanceof AbstractDiagramEdgeEditPart) {
                AbstractDiagramEdgeEditPart edgeEP = (AbstractDiagramEdgeEditPart) object;
                linkedPorts.add(edgeEP);
            }
        }

        Iterator<?> sourceIterator = portEP.getSourceConnections().iterator();
        while (sourceIterator.hasNext()) {
            Object object = sourceIterator.next();
            if (object instanceof AbstractDiagramEdgeEditPart) {
                AbstractDiagramEdgeEditPart edgeEP = (AbstractDiagramEdgeEditPart) object;
                linkedPorts.add(edgeEP);
            }
        }

        return linkedPorts;
    }

    /**
     * Extract port from a container with a given name.
     * 
     * @param containerEP
     *            container which contains the port
     * @param portName
     *            name of the port to find
     * @return port from a container with a given name.
     */
    protected AbstractDiagramBorderNodeEditPart findPortInContainer(AbstractBorderedShapeEditPart containerEP,
            String portName) {
        Iterator<?> iterator = containerEP.getChildren().iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof AbstractDiagramBorderNodeEditPart) {
                AbstractDiagramBorderNodeEditPart portEP = (AbstractDiagramBorderNodeEditPart) object;
                Object portModel = portEP.getModel();
                if (portModel instanceof Node) {
                    Node portNode = (Node) portModel;
                    EObject portElement = portNode.getElement();
                    if (portElement instanceof DDiagramElement) {
                        DDiagramElement portDDiagramElement = (DDiagramElement) portElement;
                        Iterator<EObject> semanticElementsIterator = portDDiagramElement.getSemanticElements()
                                .iterator();
                        while (semanticElementsIterator.hasNext()) {
                            EObject eObject = semanticElementsIterator.next();
                            if (eObject instanceof ENamedElement) {
                                ENamedElement ref = (ENamedElement) eObject;
                                if (ref.getName().equals(portName)) {
                                    return portEP;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

}
