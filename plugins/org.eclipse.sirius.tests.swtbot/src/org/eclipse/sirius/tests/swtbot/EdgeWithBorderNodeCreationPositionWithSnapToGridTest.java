/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.NoSuchElementException;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.collect.Iterables;

/**
 * Same tests as {@link EdgeCreationPositionTest} but with snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EdgeWithBorderNodeCreationPositionWithSnapToGridTest extends EdgeCreationPositionTest {

    private static final double gridStep = 100;

    @Override
    protected void openDiagram(String name) {
        super.openDiagram(name);
        editor.setSnapToGrid(true, gridStep, 2);
    }

    @Override
    protected String getCreateEdgeToolName() {
        return "SuperWithBorderNode";
    }

    @Override
    protected DEdgeEditPart getSingleDEdgeFrom(NodeEditPart sourcePart) {
        // Get the new source border node
        AbstractDiagramBorderNodeEditPart sourceBorderNode = getBorderNode(sourcePart);
        assertEquals(1, sourceBorderNode.getSourceConnections().size());
        ConnectionEditPart edge = (ConnectionEditPart) sourceBorderNode.getSourceConnections().get(0);
        assertTrue(edge instanceof DEdgeEditPart);
        return (DEdgeEditPart) edge;
    }

    private AbstractDiagramBorderNodeEditPart getBorderNode(EditPart parent) {
        AbstractDiagramBorderNodeEditPart result = null;
        try {
            result = Iterables.getOnlyElement(Iterables.filter(parent.getChildren(), AbstractDiagramBorderNodeEditPart.class));
        } catch (NoSuchElementException e) {
            fail("There is no border node created on source.");
        } catch (IllegalArgumentException e) {
            fail("There should be only one border node created on source.");
        }
        return result;
    }

    @Override
    protected void assertAreValidAnchors(IGraphicalEditPart source, IGraphicalEditPart target, DEdgeEditPart edge) {
        // Get the new source border node
        IGraphicalEditPart sourceBorderNode = getBorderNode(source);
        // Get the new target border node
        IGraphicalEditPart targetBorderNode = getBorderNode(target);

        super.assertAreValidAnchors(sourceBorderNode, targetBorderNode, edge);
        // Check that at least
        // * the x coordinate of the border node is on the grid
        // * or the y coordinate of the border node is on the grid
        // * or one of them is the same as parent (case when the grid is outside
        // the
        // node).
        assertTrue("For starting point, the x coordinate should be on the grid or the y coordinate should be on the grid or at least one should be the same as parent.",
                checkLocation(GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceBorderNode).getTopLeft(), source));
        assertTrue("For ending point, the x coordinate should be on the grid or the y coordinate should be on the grid or at least one should be the same as parent.",
                checkLocation(GraphicalHelper.getAbsoluteBoundsIn100Percent(targetBorderNode).getTopLeft(), target));
    }

    private boolean checkLocation(Point location, IGraphicalEditPart parentPart) {
        boolean result = (location.x % gridStep) == 0 || (location.y % gridStep) == 0;
        if (!result) {
            Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart);
            result = (location.x == parentBounds.x || location.x == (parentBounds.x + parentBounds.width)) || (location.y == parentBounds.y || location.y == (parentBounds.y + parentBounds.height));
        }
        return result;
    }

    /**
     * For closed source and target points on an axis, the feedback shows a
     * straighten edge, the result must be also 2 aligned border nodes.
     */
    public void testBorderNodesAreAligned() {
        testBorderNodesAreAligned("TC2185 Node", "NodeForStraightCase", "A", AbstractDiagramNodeEditPart.class, 100, "B", AbstractDiagramNodeEditPart.class, 109, true);
    }

    /**
     * For closed source and target points on an axis, the feedback shows a
     * straighten edge, the result must be also 2 aligned border nodes.<BR>
     * For this test, the feedback behavior is slightly different as
     * {@link #testBorderNodesAreAligned()}.
     */
    public void testBorderNodesAreAlignedAnotherCase() {
        testBorderNodesAreAligned("TC2185 Node", "NodeForStraightCase", "A", AbstractDiagramNodeEditPart.class, 100, "B", AbstractDiagramNodeEditPart.class, 110, true);
    }

    /**
     * Same as {@link #testBorderNodesAreAligned()} but with scroll bar on
     * diagram.
     */
    public void testBorderNodesAreAlignedWithScrollOnDiagram() {
        testBorderNodesAreAligned("TC2185 Node", "NodeForStraightCaseWithScroll", "A", AbstractDiagramNodeEditPart.class, 100, "B", AbstractDiagramNodeEditPart.class, 109, true);
    }

    /**
     * Same as {@link #testBorderNodesAreAlignedAnotherCase()} but with scroll
     * bar on diagram and zoom at 50%.
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
        testBorderNodesAreAligned("TC2185 Container in Container", "Container in Container With Scroll", "C", AbstractDiagramContainerEditPart.class, 100, "D", AbstractDiagramContainerEditPart.class,
                109, false, ZoomLevel.ZOOM_100, Options.newSome("OtherClass"));
    }

    /**
     * Case of edge created between 2 border nodes of container with scroll and
     * zoom.
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
     * Case of edge created between 2 containers in container with scroll and
     * zoom.
     */
    public void testBorderNodesOnContainerInContainerAreAlignedWithScrollOnDiagramAndContainerAndZoom() {
        testBorderNodesAreAligned("TC2185 Container in Container", "Container in Container With Scroll", "C", AbstractDiagramContainerEditPart.class, 100, "D", AbstractDiagramContainerEditPart.class,
                109, false, ZoomLevel.ZOOM_50, Options.newSome("OtherClass"));
    }

    /**
     * Create an edge with its associated border nodes between two elements
     * (zoom 100%).
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
     *            The delta from left if <code>verticalEdge</code> is true, or
     *            from top otherwise, of the source node to compute the source
     *            point of the edge.
     * @param targetName
     *            The name of the target node
     * @param targetEditPartType
     *            The type of the target EditPart
     * @param targetDelta
     *            The delta from left if <code>verticalEdge</code> is true, or
     *            from top otherwise, of the target node to compute the target
     *            point of the edge.
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
     *            The delta from left if <code>verticalEdge</code> is true, or
     *            from top otherwise, of the source node to compute the source
     *            point of the edge.
     * @param targetName
     *            The name of the target node
     * @param targetEditPartType
     *            The type of the target EditPart
     * @param targetDelta
     *            The delta from left if <code>verticalEdge</code> is true, or
     *            from top otherwise, of the target node to compute the target
     *            point of the edge.
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
     *            The delta from left if <code>verticalEdge</code> is true, or
     *            from top otherwise, of the source node to compute the source
     *            point of the edge.
     * @param targetName
     *            The name of the target node
     * @param targetEditPartType
     *            The type of the target EditPart
     * @param targetDelta
     *            The delta from left if <code>verticalEdge</code> is true, or
     *            from top otherwise, of the target node to compute the target
     *            point of the edge.
     * @param verticalEdge
     *            true if the edge is a vertical edge, false otherwise
     * @param zoomLevel
     *            The zoom to apply to the diagram
     * @param nameOfElementToRevealInFirst
     *            The optional name of element to reveal in first if necessary
     *            (to fully see node in a container node for example).
     */
    private void testBorderNodesAreAligned(String diagramDescriptionName, String diagramName, String sourceName, Class<? extends EditPart> sourceEditPartType, int sourceDelta, String targetName,
            Class<? extends EditPart> targetEditPartType, int targetDelta, final boolean verticalEdge, ZoomLevel zoomLevel, Option<String> nameOfElementToRevealInFirst) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), diagramDescriptionName, diagramName, DDiagram.class, false, true);
        editor.setSnapToGrid(true, 2, 2);
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
                absoluteSource = absoluteSourceBounds.getLocation().getTranslated(sourceDelta, absoluteSourceBounds.height - 2);
                absoluteTarget = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourcePart).getLocation().getTranslated(targetDelta, 2);
            } else {
                Rectangle absoluteSourceBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourcePart);
                sourceTranslation = new PrecisionPoint((absoluteSourceBounds.width - 2) * zoomLevel.getAmount(), sourceDelta * zoomLevel.getAmount());
                targetTranslation = new PrecisionPoint(2 * zoomLevel.getAmount(), targetDelta * zoomLevel.getAmount());
                absoluteSource = absoluteSourceBounds.getLocation().getTranslated(absoluteSourceBounds.width - 2, sourceDelta);
                absoluteTarget = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourcePart).getLocation().getTranslated(2, targetDelta);
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
            // Get the new target border node
            IGraphicalEditPart targetBorderNode = getBorderNode(targetPart);
            if (verticalEdge) {
                int sourceXLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceBorderNode).getLocation().x;
                assertEquals("The source and the target border nodes should be aligned.", sourceXLocation, GraphicalHelper.getAbsoluteBoundsIn100Percent(targetBorderNode).getLocation().x);
                // The x coordinate must be between source clicked point and
                // target clicked point.
                assertTrue("The x coordinate must be between source clicked point and target clicked point: Expected " + absoluteSource.x + "<= x <=" + absoluteTarget.x + " but what "
                        + sourceXLocation + ".", absoluteSource.x <= sourceXLocation && sourceXLocation <= absoluteTarget.x);
            } else {
                int sourceYLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceBorderNode).getLocation().y;
                assertEquals("The source and the target border nodes should be aligned.", sourceYLocation, GraphicalHelper.getAbsoluteBoundsIn100Percent(targetBorderNode).getLocation().y);
                // The y coordinate must be between source clicked point and
                // target clicked point.
                assertTrue("The y coordinate must be between source clicked point and target clicked point: Expected " + absoluteSource.y + "<= y <=" + absoluteTarget.y + " but what "
                        + sourceYLocation + ".", absoluteSource.y <= sourceYLocation && sourceYLocation <= absoluteTarget.y);
            }
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }
}
