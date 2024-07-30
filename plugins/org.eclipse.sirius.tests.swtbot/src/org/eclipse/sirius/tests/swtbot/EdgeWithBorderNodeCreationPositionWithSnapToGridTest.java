/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Same tests as {@link EdgeCreationPositionTest} but with snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EdgeWithBorderNodeCreationPositionWithSnapToGridTest extends EdgeWithBorderNodeCreationPositionTest {

    private static final double gridStep = 100;

    @Override
    protected void openDiagram(String name) {
        super.openDiagram(name);
        editor.setSnapToGrid(true, gridStep, 2);
    }

    @Override
    protected void openDiagram(String name, ZoomLevel zoomLevel) {
        super.openDiagram(name);
        editor.setSnapToGrid(true, gridStep, 2);
        editor.zoom(zoomLevel);
    }

    @Override
    protected void assertAreValidAnchors(IGraphicalEditPart source, PrecisionPoint sourcePosition, IGraphicalEditPart target, PrecisionPoint targetPosition, DEdgeEditPart edge) {
        super.assertAreValidAnchors(source, sourcePosition, target, targetPosition, edge);
        // Check that at least
        // * the x coordinate of the border node is on the grid
        // * or the y coordinate of the border node is on the grid
        // * or one of them is the same as parent (case when the grid is outside
        // the node).
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return !GraphicalHelper.getAbsoluteBoundsIn100Percent(source, true).getTopLeft().equals(0, 0);
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "The border node location is not yet correctly set.";
            }
        });
        Rectangle parentSourceBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(source, true);
        Point draw2DSourceBNLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(source, true).getTopLeft();
        assertTrue("For starting point (" + draw2DSourceBNLocation.preciseX() + ", " + draw2DSourceBNLocation.preciseY() + "), the x or y coordinate should be on the grid (step=" + gridStep
                + ") or at least one should be the same as parent: " + parentSourceBounds + ".", checkLocation(draw2DSourceBNLocation, parentSourceBounds));
        Point gmfSourceBNLocation = GMFHelper.getAbsoluteLocation((Node) source.getModel(), true, true);
        assertEquals("The computing starting point from GMF data should be the same than draw2D", draw2DSourceBNLocation, gmfSourceBNLocation);

        Rectangle parentTargetBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(target, true);
        Point draw2DTargetBNLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(target, true).getTopLeft();
        assertTrue("For ending point (" + draw2DTargetBNLocation.preciseX() + ", " + draw2DTargetBNLocation.preciseY() + "), the x or y coordinate should be on the grid (step=" + gridStep
                + ") or at least one should be the same as parent: " + parentTargetBounds + ".", checkLocation(draw2DTargetBNLocation, parentTargetBounds));
        Point gmfTargetBNLocation = GMFHelper.getAbsoluteLocation((Node) target.getModel(), true, true);
        assertEquals("The computing starting point from GMF data should be the same than draw2D", draw2DTargetBNLocation, gmfTargetBNLocation);
        // Contrary to super class, the checkSide are not done because
        // snapToGrid is enabled and has effect on the side.
    }

    private boolean checkLocation(Point location, Rectangle parentBounds) {
        boolean result = (location.x % gridStep) == 0 || (location.y % gridStep) == 0;
        if (!result) {
            result = (location.x == parentBounds.x || location.x == (parentBounds.x + parentBounds.width)) || (location.y == parentBounds.y || location.y == (parentBounds.y + parentBounds.height));
        }
        return result;
    }

    @Override
    public void test_Node() {
        // Force the expected source side as the snapToGrid change the "computed
        // one" in the test code.
        sourceSide = PositionConstants.WEST;
        try {
            super.test_Node();
        } finally {
            sourceSide = PositionConstants.NONE;
        }
    }

    @Override
    public void test_Node_WithZoom() {
        // Force the expected source side as the snapToGrid change the "computed
        // one" in the test code.
        sourceSide = PositionConstants.WEST;
        try {
            super.test_Node_WithZoom();
        } finally {
            sourceSide = PositionConstants.NONE;
        }
    }

    @Override
    public void test_List() {
        // Force the expected source side and target side as the snapToGrid
        // change the "computed ones" in the test code.
        sourceSide = PositionConstants.NORTH;
        targetSide = PositionConstants.EAST;
        try {
            super.test_List();
        } finally {
            sourceSide = PositionConstants.NONE;
            targetSide = PositionConstants.NONE;
        }
    }

    @Override
    public void test_Node_WithRectilinearEdge() {
        // Force the expected source side and target side as the snapToGrid
        // change the "computed ones" in the test code.
        sourceSide = PositionConstants.NORTH;
        targetSide = PositionConstants.WEST;
        try {
            super.test_Node_WithRectilinearEdge();
        } finally {
            sourceSide = PositionConstants.NONE;
            targetSide = PositionConstants.NONE;
        }
    }

    @Override
    public void test_Bordered_Node_on_Node() {
        // Force the expected source side and target side as the snapToGrid
        // change the "computed ones" in the test code.
        sourceSide = PositionConstants.WEST;
        targetSide = PositionConstants.WEST;
        try {
            super.test_Bordered_Node_on_Node();
        } finally {
            sourceSide = PositionConstants.NONE;
            targetSide = PositionConstants.NONE;
        }
    }

    @Override
    public void test_Container_in_Container() {
        // Force the expected source side and target side as the snapToGrid
        // change the "computed ones" in the test code.
        sourceSide = PositionConstants.NORTH;
        targetSide = PositionConstants.SOUTH;
        try {
            super.test_Container_in_Container();
        } finally {
            sourceSide = PositionConstants.NONE;
            targetSide = PositionConstants.NONE;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.sirius.tests.swtbot.EdgeCreationPositionTest# test_Bordered_Node_on_Container()
     */
    @Override
    public void test_Bordered_Node_on_Container() {
        // Force the expected source side as the snapToGrid change the "computed
        // ones" in the test code.
        sourceSide = PositionConstants.EAST;
        try {
            super.test_Bordered_Node_on_Container();
        } finally {
            sourceSide = PositionConstants.NONE;
        }
    }

    /**
     * For closed source and target points on an axis, the feedback shows a straighten edge, the result must be also 2
     * aligned border nodes.
     */
    public void testBorderNodesAreAligned() {
        testBorderNodesAreAligned("TC2185 Node", "NodeForStraightCase", "A", AbstractDiagramNodeEditPart.class, 100, "B", AbstractDiagramNodeEditPart.class, 109, true);
    }

    /**
     * For closed source and target points on an axis, the feedback shows a straighten edge, the result must be also 2
     * aligned border nodes.<BR>
     * For this test, the feedback behavior is slightly different as {@link #testBorderNodesAreAligned()}.
     */
    public void testBorderNodesAreAlignedAnotherCase() {
        testBorderNodesAreAligned("TC2185 Node", "NodeForStraightCase", "A", AbstractDiagramNodeEditPart.class, 100, "B", AbstractDiagramNodeEditPart.class, 110, true);
    }

    /**
     * Same as {@link #testBorderNodesAreAligned()} but with scroll bar on diagram.
     */
    public void testBorderNodesAreAlignedWithScrollOnDiagram() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        testBorderNodesAreAligned("TC2185 Node", "NodeForStraightCaseWithScroll", "A", AbstractDiagramNodeEditPart.class, 100, "B", AbstractDiagramNodeEditPart.class, 109, true);
    }

    /**
     * Same as {@link #testBorderNodesAreAlignedAnotherCase()} but with scroll bar on diagram and zoom at 50%.
     */
    public void testBorderNodesAreAlignedWithScrollOnDiagramAndWithZoom() {
        testBorderNodesAreAligned("TC2185 Node", "NodeForStraightCaseWithScroll", "A", AbstractDiagramNodeEditPart.class, 100, "B", AbstractDiagramNodeEditPart.class, 110, true, ZoomLevel.ZOOM_50);
    }

    /**
     * Case of edge created between 2 border nodes of container with scroll.
     */
    public void testBorderNodesOnBorderNodeOnContainerAreAlignedWithScrollOnDiagram() {
        testBorderNodesAreAligned("TC2185 Bordered Node on Container", "Bordered Node on Container With Scroll", "C", AbstractDiagramBorderNodeEditPart.class, 100, "D",
                AbstractDiagramBorderNodeEditPart.class, 108, false);
    }

    /**
     * Case of edge created between 2 nodes in container with scroll.
     */
    public void testBorderNodesOnNodeInContainerAreAlignedWithScrollOnDiagramAndContainer() {
        testBorderNodesAreAligned("TC2185 Node in Container", "Node in Container With Scroll", "C", AbstractDiagramNodeEditPart.class, 100, "D", AbstractDiagramNodeEditPart.class, 109, false,
                ZoomLevel.ZOOM_100, Options.newSome("OtherClass"));
    }

    /**
     * Case of edge created between 2 containers in container with scroll.
     */
    public void testBorderNodesOnContainerInContainerAreAlignedWithScrollOnDiagramAndContainer() {
        testBorderNodesAreAligned("TC2185 Container in Container", "Container in Container With Scroll", "C", AbstractDiagramContainerEditPart.class, 99, "D", AbstractDiagramContainerEditPart.class,
                108, false, ZoomLevel.ZOOM_100, Options.newSome("OtherClass"));
    }

    /**
     * Case of edge created between 2 border nodes of container with scroll and zoom.
     */
    public void testBorderNodesOnBorderNodeOnContainerAreAlignedWithScrollOnDiagramAndZoom() {
        testBorderNodesAreAligned("TC2185 Bordered Node on Container", "Bordered Node on Container With Scroll", "C", AbstractDiagramBorderNodeEditPart.class, 100, "D",
                AbstractDiagramBorderNodeEditPart.class, 108, false, ZoomLevel.ZOOM_50);
    }

    /**
     * Case of edge created between 2 nodes in container with scroll and zoom.
     */
    public void testBorderNodesOnNodeInContainerAreAlignedWithScrollOnDiagramAndContainerAndZoom() {
        testBorderNodesAreAligned("TC2185 Node in Container", "Node in Container With Scroll", "C", AbstractDiagramNodeEditPart.class, 100, "D", AbstractDiagramNodeEditPart.class, 109, false,
                ZoomLevel.ZOOM_50, Options.newSome("OtherClass"));
    }

    /**
     * Case of edge created between 2 containers in container with scroll and zoom.
     */
    public void testBorderNodesOnContainerInContainerAreAlignedWithScrollOnDiagramAndContainerAndZoom() {
        testBorderNodesAreAligned("TC2185 Container in Container", "Container in Container With Scroll", "C", AbstractDiagramContainerEditPart.class, 100, "D", AbstractDiagramContainerEditPart.class,
                109, false, ZoomLevel.ZOOM_50, Options.newSome("OtherClass"));
    }

    /**
     * Create an edge with its associated border nodes between two elements (zoom 100%).
     * 
     * @param diagramDescriptionName
     *            The diagram description name
     * @param diagramName
     *            The diagram name
     * @param sourceName
     *            The name of the source node
     * @param sourceEditPartType
     *            The type of the source EditPart
     * @param sourceDelta
     *            The delta from left if <code>verticalEdge</code> is true, or from top otherwise, of the source node to
     *            compute the source point of the edge.
     * @param targetName
     *            The name of the target node
     * @param targetEditPartType
     *            The type of the target EditPart
     * @param targetDelta
     *            The delta from left if <code>verticalEdge</code> is true, or from top otherwise, of the target node to
     *            compute the target point of the edge.
     * @param verticalEdge
     *            true if the edge is a vertical edge, false otherwise
     */
    private void testBorderNodesAreAligned(String diagramDescriptionName, String diagramName, String sourceName, Class<? extends EditPart> sourceEditPartType, int sourceDelta, String targetName,
            Class<? extends EditPart> targetEditPartType, int targetDelta, boolean verticalEdge) {
        testBorderNodesAreAligned(diagramDescriptionName, diagramName, sourceName, sourceEditPartType, sourceDelta, targetName, targetEditPartType, targetDelta, verticalEdge, ZoomLevel.ZOOM_100);
    }

    /**
     * Create an edge with its associated border nodes between two elements.
     * 
     * @param diagramDescriptionName
     *            The diagram description name
     * @param diagramName
     *            The diagram name
     * @param sourceName
     *            The name of the source node
     * @param sourceEditPartType
     *            The type of the source EditPart
     * @param sourceDelta
     *            The delta from left if <code>verticalEdge</code> is true, or from top otherwise, of the source node to
     *            compute the source point of the edge.
     * @param targetName
     *            The name of the target node
     * @param targetEditPartType
     *            The type of the target EditPart
     * @param targetDelta
     *            The delta from left if <code>verticalEdge</code> is true, or from top otherwise, of the target node to
     *            compute the target point of the edge.
     * @param verticalEdge
     *            true if the edge is a vertical edge, false otherwise
     * @param zoomLevel
     *            The zoom to apply to the diagram
     */
    private void testBorderNodesAreAligned(String diagramDescriptionName, String diagramName, String sourceName, Class<? extends EditPart> sourceEditPartType, int sourceDelta, String targetName,
            Class<? extends EditPart> targetEditPartType, int targetDelta, boolean xAxis, ZoomLevel zoomLevel) {
        Option<String> nothinfToRevealFirst = Options.newNone();
        testBorderNodesAreAligned(diagramDescriptionName, diagramName, sourceName, sourceEditPartType, sourceDelta, targetName, targetEditPartType, targetDelta, xAxis, zoomLevel,
                nothinfToRevealFirst);
    }

    /**
     * Create an edge with its associated border nodes between two elements.
     * 
     * @param diagramDescriptionName
     *            The diagram description name
     * @param diagramName
     *            The diagram name
     * @param sourceName
     *            The name of the source node
     * @param sourceEditPartType
     *            The type of the source EditPart
     * @param sourceDelta
     *            The delta from left if <code>verticalEdge</code> is true, or from top otherwise, of the source node to
     *            compute the source point of the edge.
     * @param targetName
     *            The name of the target node
     * @param targetEditPartType
     *            The type of the target EditPart
     * @param targetDelta
     *            The delta from left if <code>verticalEdge</code> is true, or from top otherwise, of the target node to
     *            compute the target point of the edge.
     * @param verticalEdge
     *            true if the edge is a vertical edge, false otherwise
     * @param zoomLevel
     *            The zoom to apply to the diagram
     * @param nameOfElementToRevealInFirst
     *            The optional name of element to reveal in first if necessary (to fully see node in a container node
     *            for example).
     */
    private void testBorderNodesAreAligned(String diagramDescriptionName, String diagramName, String sourceName, Class<? extends EditPart> sourceEditPartType, int sourceDelta, String targetName,
            Class<? extends EditPart> targetEditPartType, int targetDelta, final boolean verticalEdge, ZoomLevel zoomLevel, Option<String> nameOfElementToRevealInFirst) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), diagramDescriptionName, diagramName, DDiagram.class, false, true);
        int gridSpacing = 2;
        editor.setSnapToGrid(true, gridSpacing, 2);
        editor.zoom(zoomLevel);
        try {
            SWTBotGefEditPart sourceSwtbotPart = editor.getEditPart(sourceName, sourceEditPartType);
            final IGraphicalEditPart sourcePart = (IGraphicalEditPart) sourceSwtbotPart.part();
            SWTBotGefEditPart targetSwtbotPart = editor.getEditPart(targetName, targetEditPartType);
            IGraphicalEditPart targetPart = (IGraphicalEditPart) targetSwtbotPart.part();
            if (nameOfElementToRevealInFirst.some()) {
                editor.reveal(nameOfElementToRevealInFirst.get());
            }
            editor.reveal(sourcePart);
            editor.reveal(targetPart);

            ICondition done = new OperationDoneCondition();
            editor.activateTool(getCreateEdgeToolName());
            // Click just above the bottom of the source node (if the edge is
            // vertical), otherwise just before the right side.
            Rectangle sourceBounds = editor.getBounds(sourceSwtbotPart);
            Point sourceTranslation;
            Point targetTranslation;
            Point absoluteSource;
            Point absoluteTarget;
            if (verticalEdge) {
                Rectangle absoluteSourceBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourcePart);
                sourceTranslation = new PrecisionPoint(sourceDelta * zoomLevel.getAmount(), (absoluteSourceBounds.height - 2) * zoomLevel.getAmount());
                targetTranslation = new PrecisionPoint(targetDelta * zoomLevel.getAmount(), 2 * zoomLevel.getAmount());
                absoluteSource = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourcePart, true).getLocation().getTranslated(sourceDelta, absoluteSourceBounds.height - 2);
                absoluteTarget = GraphicalHelper.getAbsoluteBoundsIn100Percent(targetPart, true).getLocation().getTranslated(targetDelta, 2);
            } else {
                Rectangle absoluteSourceBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourcePart);
                sourceTranslation = new PrecisionPoint((absoluteSourceBounds.width - 2) * zoomLevel.getAmount(), sourceDelta * zoomLevel.getAmount());
                targetTranslation = new PrecisionPoint(2 * zoomLevel.getAmount(), targetDelta * zoomLevel.getAmount());
                absoluteSource = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourcePart, true).getLocation().getTranslated(absoluteSourceBounds.width - 2, sourceDelta);
                absoluteTarget = GraphicalHelper.getAbsoluteBoundsIn100Percent(targetPart, true).getLocation().getTranslated(2, targetDelta);
            }
            Point source = sourceBounds.getLocation().getTranslated(sourceTranslation);
            editor.click(source);
            // Click just below the top of the target node (if the edge is
            // vertical), otherwise just after the left side.
            Point target = editor.getLocation(targetSwtbotPart).getTranslated(targetTranslation);
            editor.click(target, true);
            SWTBotUtils.waitAllUiEvents();
            bot.waitUntil(done);

            // Get the new source border node (and wait that its figure is
            // correctly located, ie not at {0, 0})
            final IGraphicalEditPart sourceBorderNode = getBorderNode(sourcePart);
            bot.waitUntil(new ICondition() {

                @Override
                public boolean test() throws Exception {
                    if (verticalEdge) {
                        return sourceBorderNode.getFigure().getBounds().x != 0;
                    } else {
                        return sourceBorderNode.getFigure().getBounds().y != 0;
                    }
                }

                @Override
                public void init(SWTBot bot) {
                }

                @Override
                public String getFailureMessage() {
                    return "The border node coordinates are {0, 0}.";
                }
            });
            // Get the new target border node (and wait that its figure is
            // correctly located, ie not at {0, 0})
            IGraphicalEditPart targetBorderNode = getBorderNode(targetPart);
            bot.waitUntil(new ICondition() {

                @Override
                public boolean test() throws Exception {
                    if (verticalEdge) {
                        return targetBorderNode.getFigure().getBounds().x != 0;
                    } else {
                        return targetBorderNode.getFigure().getBounds().y != 0;
                    }
                }

                @Override
                public void init(SWTBot bot) {
                }

                @Override
                public String getFailureMessage() {
                    return "The border node coordinates are {0, 0}.";
                }
            });
            if (verticalEdge) {
                int sourceXLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceBorderNode, true).getLocation().x;
                assertEquals("The source and the target border nodes should be aligned.", sourceXLocation, GraphicalHelper.getAbsoluteBoundsIn100Percent(targetBorderNode, true).getLocation().x);
                // The x coordinate must be between source clicked point and
                // target clicked point.
                assertTrue("The x coordinate must be between source clicked point and target clicked point: Expected " + (absoluteSource.x - gridSpacing) + "<= x <=" + (absoluteTarget.x + gridSpacing)
                        + " but what " + sourceXLocation + ".", (absoluteSource.x - gridSpacing) <= sourceXLocation && sourceXLocation <= (absoluteTarget.x + gridSpacing));
            } else {
                int sourceYLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceBorderNode, true).getLocation().y;
                assertEquals("The source and the target border nodes should be aligned.", sourceYLocation, GraphicalHelper.getAbsoluteBoundsIn100Percent(targetBorderNode, true).getLocation().y);
                // The y coordinate must be between source clicked point and
                // target clicked point.
                assertTrue(
                        "The y coordinate must be aligned on the grid and between source clicked point and target clicked point (or at least no further than a grid step) : Expected "
                                + (absoluteSource.y - gridSpacing) + "<= y <=" + (absoluteTarget.y + gridSpacing) + " but what " + sourceYLocation + ".",
                        (absoluteSource.y - gridSpacing) <= sourceYLocation && sourceYLocation <= (absoluteTarget.y + gridSpacing));
            }
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }
}
