/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Same tests as {@link BorderedNodeCreationTest} but with snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class BorderedNodeCreationWithSnapToGridTest extends BorderedNodeCreationTest {
    /**
     * The name of the tool that create a bordered node (that named
     * "new Attribute2" on a class.
     */
    private static final String BORDERED_NODE_CREATION_ON_CLASS_2_TOOL_NAME = "Attribute2";

    /**
     * The name of a new bordered node created on class with the above tool.
     */
    private static final String NEW_BORDERED_NODE_ON_CLASS_2_NAME = "new Attribute2";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        editor.setSnapToGrid(true, 100, 2);
    }

    /**
     * Ensures that 2 border nodes created at same location (forced with
     * snapToGrid) on a Node (zoom level : 100%) are not at same location.
     */
    public void testCreateTwoBorderNodesAtSameLocation() {
        testBNC_OnNode(ZoomLevel.ZOOM_100, C1_NAME);
        // Create another border node just beside the new border node but at the
        // same location with snapToGrid and check that the create
        // location is not the same (in draw2d and GMF coordinates).
        // Get the location of the class (relative the part visible on the
        // screen)
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        SWTBotGefEditPart firstNewNode = editor.getEditPart(getNewBorderedNodeOnClassName(), AbstractDiagramBorderNodeEditPart.class);
        Rectangle firstNewNodeAbsoluteBounds = editor.getAbsoluteBounds(firstNewNode);

        // We compute the location according the the first border node location
        // (just beside).
        Point creationLocation = firstNewNodeAbsoluteBounds.getTopLeft().getTranslated(11, 3);

        createBorderedNode(BORDERED_NODE_CREATION_ON_CLASS_2_TOOL_NAME, creationLocation.x, creationLocation.y);

        SWTBotGefEditPart secondNewNode = editor.getEditPart(NEW_BORDERED_NODE_ON_CLASS_2_NAME, AbstractDiagramBorderNodeEditPart.class);
        Rectangle secondNewNodeAbsoluteLocation = editor.getAbsoluteBounds(secondNewNode);

        assertFalse("Second border node should not overlap the first one (draw2d).", firstNewNodeAbsoluteBounds.intersects(secondNewNodeAbsoluteLocation));

        Rectangle firstNewNodeGMFBounds = GMFHelper.getAbsoluteBounds((Node) firstNewNode.part().getModel());
        Rectangle secondNewNodeGMFBounds = GMFHelper.getAbsoluteBounds((Node) secondNewNode.part().getModel());

        assertFalse("Second border node should not overlap the first one (GMF).", firstNewNodeGMFBounds.intersects(secondNewNodeGMFBounds));
    }

    /**
     * Only one of the coordinates is snap to grid. The other is constrained by
     * the parent border.
     * 
     * @see org.eclipse.sirius.tests.swtbot.BorderedNodeCreationTest#assertSameLocation(String,
     *      Point, Point, Point, Point, IGraphicalEditPart)
     */
    @Override
    protected void assertSameLocation(String errorMessage, Point expectedLocation, Point nodeLocation, Point parentLocation, Point creationLocation, IGraphicalEditPart parentPart) {
        // Adapt the expected location to the grid
        Point snapToLocation = editor.adaptLocationToSnap(expectedLocation);
        // Adapt the expected location to the scrollbar of the parents
        PrecisionPoint absoluteSnapToLocation = new PrecisionPoint(snapToLocation);
        if (parentPart != null) {
            addParentScrollbar(parentPart.getFigure(), absoluteSnapToLocation);
        }

        if (nodeLocation.x == parentLocation.x + 8 || nodeLocation.x == parentLocation.x) {
            // Case where the grid is outside the parent
            errorMessage += " expected <Point(" + nodeLocation.x + ", " + absoluteSnapToLocation.y + ")> or <Point(" + nodeLocation.x + ", " + expectedLocation.y + ")> but was:" + nodeLocation;
            assertTrue(errorMessage, nodeLocation.x == nodeLocation.x && nodeLocation.y == absoluteSnapToLocation.y || nodeLocation.x == nodeLocation.x && nodeLocation.y == expectedLocation.y);
        } else if (nodeLocation.y == parentLocation.y) {
            // The y axis is the same as parent. Check that this axis also
            // corresponds to the grid. The x axis is constrained by the border
            // (east or west).
            errorMessage += " expected <Point(" + nodeLocation.x + ", " + absoluteSnapToLocation.y + ")> or <Point(" + nodeLocation.x + ", " + expectedLocation.y + ")> but was:" + nodeLocation;
            assertTrue(errorMessage, nodeLocation.y == absoluteSnapToLocation.y);
        } else {
            errorMessage += " expected <Point(" + expectedLocation.x + ", " + absoluteSnapToLocation.y + ")> or <Point(" + absoluteSnapToLocation.x + ", " + expectedLocation.y + ")> but was:"
                    + nodeLocation;
            assertTrue(errorMessage, nodeLocation.x == expectedLocation.x && nodeLocation.y == absoluteSnapToLocation.y || nodeLocation.x == absoluteSnapToLocation.x
                    && nodeLocation.y == expectedLocation.y);
        }
    }

    /**
     * Add scrolls of parent (except the diagram scroll)
     * 
     * @param figure
     *            the actual figure level
     * @param location
     *            a location in absolute coordinates
     */
    private static void addParentScrollbar(final IFigure figure, final PrecisionPoint location) {
        if (figure instanceof Viewport && figure.getParent() != null && figure.getParent().getParent() != null) {
            location.performTranslate(((Viewport) figure).getHorizontalRangeModel().getValue(), ((Viewport) figure).getVerticalRangeModel().getValue());
        }
        if (figure.getParent() != null) {
            addParentScrollbar(figure.getParent(), location);
        }
    }
}
