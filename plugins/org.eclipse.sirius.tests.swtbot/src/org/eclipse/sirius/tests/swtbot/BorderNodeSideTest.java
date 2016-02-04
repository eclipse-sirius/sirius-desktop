/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * Test Class to check the border nodes authorized sides feature.
 * 
 * @author Florian Barbin
 *
 */
public class BorderNodeSideTest extends AbstractSiriusSwtBotGefTestCase {

    private static final int BOTTOM_RIGHT = 1;

    private static final int BOTTOM_LEFT = 2;

    private static final int TOP_LEFT = 3;

    private static final int TOP_RIGHT = 4;

    private static final String DATA_UNIT_DIR = "data/unit/borderNodeSide/";

    private static final String MODEL = "borderNodeSide.ecore";

    private static final String SESSION_FILE = "borderNodeSide.aird";

    private static final String ODESIGN_FILE = "borderNodeSide.odesign";

    private static final String REPRESENTATION_NAME = "borderNodeSide";

    private Session session;

    private DDiagram dDiagram;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ODESIGN_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        bot.viewById("org.eclipse.ui.views.ContentOutline").close();
        SWTBotUtils.waitAllUiEvents();
        session = localSession.getOpenedSession();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(session, REPRESENTATION_NAME, REPRESENTATION_NAME, DDiagram.class, true);
        dDiagram = (DDiagram) editor.getDRepresentation();
    }

    /**
     * Tests that border nodes are properly placed after having perform a
     * refresh.
     */
    public void testBorderNodeSideOnRefresh() {
        synchronizeDiagram();
        checkBorderNodesSides();

    }

    /**
     * Tests that border nodes are properly placed after having created them
     * using a tool.
     */
    public void testBorderNodeSideOnCreation() {
        createBorderNodesUsingTools(1);
        checkBorderNodesSides();
    }

    /**
     * Tests that border nodes are properly placed after having created them
     * using a tool until the side is full.
     */
    public void testBorderNodeSideOnCreationWithFullSide() {
        createBorderNodesUsingTools(10);
        checkBorderNodesSides();
    }

    /**
     * Tests that border nodes are properly placed after having performed an
     * arrangeAll.
     */
    public void testBorderNodeSideAfterArrangeAll() {
        createBorderNodesUsingTools(3);
        checkBorderNodesSides();
        arrangeAll();
        checkBorderNodesSides();
    }

    /**
     * Tests that we can't move a border node on an unauthorized side.
     */
    public void testMoveBorderNodeSide() {
        createBorderNodesUsingTools(3);
        checkBorderNodesSides();
        moveBorderNode("WestClass1", BOTTOM_RIGHT);
        moveBorderNode("EastClass1", TOP_LEFT);
        moveBorderNode("NorthClass1", BOTTOM_LEFT);
        moveBorderNode("SouthClass1", TOP_RIGHT);
        checkBorderNodesSides();

    }

    /**
     * Tests that border nodes keep their side after a container resizing.
     */
    public void testResizeContainer() {
        createBorderNodesUsingTools(3);
        checkBorderNodesSides();
        SWTBotGefEditPart containerGefEditPart = editor.getEditPart("subPackage1", AbstractDiagramContainerEditPart.class);
        containerGefEditPart.select();
        final Rectangle currentBounds = getContainerBounds().getCopy();
        editor.drag(currentBounds.getBottomRight(), currentBounds.getBottomRight().translate(-currentBounds.width / 2, -currentBounds.height / 2));
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return !currentBounds.equals(getContainerBounds());
            }

            @Override
            public void init(SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "The container resizing failed";
            }
        });
        checkBorderNodesSides();
    }

    private void moveBorderNode(String borderNodeName, int position) {
        SWTBotGefEditPart nodeGefEditPart = editor.getEditPart(borderNodeName, AbstractDiagramBorderNodeEditPart.class);
        Rectangle bounds = getContainerBounds();
        AbstractDiagramBorderNodeEditPart nodeEditPart = (AbstractDiagramBorderNodeEditPart) nodeGefEditPart.part();
        Point startPoint = nodeEditPart.getMainFigure().getBounds().getBottomRight().translate(new Point(-5, -5));
        Point targetPoint = null;
        switch (position) {
        case BOTTOM_RIGHT:
            targetPoint = bounds.getBottomRight();
            break;
        case TOP_RIGHT:
            targetPoint = bounds.getTopRight();
            break;
        case TOP_LEFT:
            targetPoint = bounds.getTopLeft();
            break;
        case BOTTOM_LEFT:
            targetPoint = bounds.getBottomLeft();
            break;
        }
        editor.drag(startPoint, targetPoint);
    }

    private Rectangle getContainerBounds() {
        AbstractDiagramContainerEditPart diagramContainerEditPart = getContainerEditPart();
        return diagramContainerEditPart.getMainFigure().getBounds();
    }

    private AbstractDiagramContainerEditPart getContainerEditPart() {
        SWTBotGefEditPart containerGefEditPart = editor.getEditPart("subPackage1", AbstractDiagramContainerEditPart.class);
        return (AbstractDiagramContainerEditPart) containerGefEditPart.part();
    }

    private void createBorderNodesUsingTools(int copies) {
        // test west
        activateAndUseTool("createWestBorderNode", "subPackage1", copies);

        // test east
        activateAndUseTool("createEastBorderNode", "subPackage1", copies);

        // test south
        activateAndUseTool("createSouthBorderNode", "subPackage1", copies);

        // test north
        activateAndUseTool("createNorthBorderNode", "subPackage1", copies);

        // test south and north
        activateAndUseTool("createSouthNorthBorderNode", "subPackage1", copies);
    }

    private void activateAndUseTool(String toolName, String targetEditPartName, int iterations) {
        for (int i = 0; i < iterations; i++) {
            editor.activateTool(toolName);
            editor.click(targetEditPartName);
        }
    }

    private void checkBorderNodesSides() {

        // We retrieve the border nodes edit parts...
        List<SWTBotGefEditPart> botGefEditParts = editor.editParts(new BaseMatcher<EditPart>() {

            @Override
            public boolean matches(Object item) {
                return item instanceof AbstractDiagramBorderNodeEditPart;
            }

            @Override
            public void describeTo(Description description) {
            }
        });

        // ...and check if they are on the expected container side following
        // their labels.
        for (SWTBotGefEditPart botGefEditPart : botGefEditParts) {
            AbstractDiagramBorderNodeEditPart editPart = (AbstractDiagramBorderNodeEditPart) botGefEditPart.part();
            DNode node = (DNode) editPart.resolveSemanticElement();
            String name = node.getName();
            int side = computeSide(editPart);
            String message = "The border node " + name + " is not at the expected side";
            if (name.contains("West")) {
                assertEquals(message, PositionConstants.WEST, side);
            } else if (name.contains("East")) {
                assertEquals(message, PositionConstants.EAST, side);
            } else if (name.contains("North")) {
                assertEquals(message, PositionConstants.NORTH, side);
            } else if (name.contains("South")) {
                assertEquals(message, PositionConstants.SOUTH, side);
            } else if (name.contains("SN")) {
                assertTrue(message, PositionConstants.SOUTH == side || PositionConstants.NORTH == side);
            }
        }
    }

    /**
     * Computes on which side is the border node.
     * 
     * @param editPart
     *            the border node edit part.
     * @return the real {@link PositionConstants}
     */
    private int computeSide(AbstractDiagramBorderNodeEditPart editPart) {
        int side = PositionConstants.NONE;
        IFigure borderNodeFigure = editPart.getFigure();
        Rectangle borderNodeBounds = borderNodeFigure.getBounds();
        AbstractGraphicalEditPart parentEditPart = (AbstractGraphicalEditPart) editPart.getParent();
        IFigure parentFigure = parentEditPart.getFigure();
        Rectangle parentBounds = parentFigure.getBounds();
        int expectedNorthYLocation = parentBounds.y - (borderNodeBounds.height - 8);
        // 8 is the border node inset for north and west location.
        int expectedSouthYLocation = parentBounds.y + (parentBounds.height - 10);
        // 10 is the border node inset for south and east (because of the
        // container shadow)
        int expectedWestXLocation = parentBounds.x - (borderNodeBounds.width - 8);
        int expectedEastXLocation = parentBounds.x + (parentBounds.width - 10);

        if (borderNodeBounds.x == expectedWestXLocation) {
            side = PositionConstants.WEST;
        } else if (borderNodeBounds.x == expectedEastXLocation) {
            side = PositionConstants.EAST;
        } else if (borderNodeBounds.y == expectedNorthYLocation) {
            side = PositionConstants.NORTH;
        } else if (borderNodeBounds.y == expectedSouthYLocation) {
            side = PositionConstants.SOUTH;
        }
        return side;
    }

    private void synchronizeDiagram() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                dDiagram.setSynchronized(true);
                dDiagram.refresh();
            }
        });
    }

    @Override
    protected void tearDown() throws Exception {
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        // Restore the default zoom level
        editor.click(1, 1); // Set the focus on the diagram
        editor.zoom(ZoomLevel.ZOOM_100);
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }
}
