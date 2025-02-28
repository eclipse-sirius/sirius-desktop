/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Same tests as {@link BorderedNodeCreationTest} but with snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class BorderedNodeCreationWithSnapToGridTest extends BorderedNodeCreationTest {
    /**
     * The name of the tool that create a bordered node (that named "new Attribute2" on a class.
     */
    private static final String BORDERED_NODE_CREATION_ON_CLASS_2_TOOL_NAME = "Attribute2";

    /**
     * The name of the tool that create a bordered node (that named "new Enum"with number of Enum as suffix on a
     * container.
     */
    private static final String BORDERED_NODE_CREATION_ON_PACKAGE_TOOL_NAME_2 = "Enum2";

    /**
     * The name of a new bordered node created on class with the above tool.
     */
    private static final String NEW_BORDERED_NODE_ON_CLASS_2_NAME = "new Attribute2";

    /**
     * The grid spacing in pixels.
     */
    private static final int GRID_SPACING = 100;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        editor.setSnapToGrid(true, GRID_SPACING, 2);
    }

    /**
     * Ensures that 2 border nodes created at same location (forced with snapToGrid) on a Node (zoom level : 100%) are
     * not at same location.
     */
    public void testCreateTwoBorderNodesAtSameLocation() {
        testBNC_OnNode(ZoomLevel.ZOOM_100, C1_NAME, false);
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

        Rectangle firstNewNodeGMFBounds = GMFHelper.getAbsoluteBounds((Node) firstNewNode.part().getModel(), false, false, false, true);
        Rectangle secondNewNodeGMFBounds = GMFHelper.getAbsoluteBounds((Node) secondNewNode.part().getModel(), false, false, false, true);

        assertFalse("Second border node should not overlap the first one (GMF).", firstNewNodeGMFBounds.intersects(secondNewNodeGMFBounds));

        SWTBotGefEditPart classEditPart = editor.getEditPart(C1_NAME, AbstractDiagramNodeEditPart.class);
        Point classAbsoluteLocation = editor.getAbsoluteLocation((GraphicalEditPart) classEditPart.part());
        assertBorderedNodeAtLocation(NEW_BORDERED_NODE_ON_CLASS_2_NAME, creationLocation, classAbsoluteLocation, creationLocation, ((GraphicalEditPart) classEditPart.part()),
                nearCollapsedBorderedNode);
    }

    /**
     * Ensures that 2 border nodes created at same location (forced with snapToGrid) on a package (zoom level : 100%)
     * are aligned on the grid. Indeed, Grid spacing is sufficient to have two border node aligned on the grid.
     */
    public void testCreateTwoBorderNodesAtSameLocationWithThinGridSpacing() {
        int gridStep = 50;
        editor.setSnapToGrid(true, gridStep, 2);

        SWTBotGefEditPart packageP4 = editor.getEditPart(PACKAGE_4_NAME, AbstractDiagramContainerEditPart.class);
        Rectangle packageAbsoluteBounds = editor.getAbsoluteBounds(packageP4);
        editor.reveal(packageP4.part());

        // We convert to screen coordinates
        ((GraphicalEditPart) packageP4.part()).getFigure().translateToAbsolute(packageAbsoluteBounds);
        // We compute the location according the top left package location
        Point creationLocation = packageAbsoluteBounds.getTopLeft().translate(20, 20);

        createBorderedNode(BORDERED_NODE_CREATION_ON_PACKAGE_TOOL_NAME_2, creationLocation.x, creationLocation.y);
        createBorderedNode(BORDERED_NODE_CREATION_ON_PACKAGE_TOOL_NAME_2, creationLocation.x, creationLocation.y);

        IGraphicalEditPart firstNewNode = (IGraphicalEditPart) editor.getEditPart("new Enum2", AbstractDiagramBorderNodeEditPart.class).part();
        checkLocationAlignOnGrid(firstNewNode, "BorderNode 'new Enum2'", gridStep);

        IGraphicalEditPart secondNewNode = (IGraphicalEditPart) editor.getEditPart("new Enum3", AbstractDiagramBorderNodeEditPart.class).part();
        checkLocationAlignOnGrid(secondNewNode, "BorderNode 'new Enum3'", gridStep);
    }

    /**
     * Only one of the coordinates is snap to grid. The other is constrained by the parent border.
     * 
     * @see org.eclipse.sirius.tests.swtbot.BorderedNodeCreationTest#assertSameLocation(String, Point, Point, Point,
     *      Point, IGraphicalEditPart)
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
            errorMessage += " expected <Point(" + nodeLocation.x + ", " + absoluteSnapToLocation.y + ")> or <Point(" + nodeLocation.x + ", " + expectedLocation.y + ")> but was: " + nodeLocation;
            assertTrue(errorMessage, nodeLocation.x == nodeLocation.x && nodeLocation.y == absoluteSnapToLocation.y || nodeLocation.x == nodeLocation.x && nodeLocation.y == expectedLocation.y);
        } else if (nodeLocation.y == parentLocation.y) {
            // The y axis is the same as parent. Check that this axis also
            // corresponds to the grid. The x axis is constrained by the border
            // (east or west).
            errorMessage += " expected <Point(" + nodeLocation.x + ", " + absoluteSnapToLocation.y + ")> or <Point(" + nodeLocation.x + ", " + expectedLocation.y + ")> but was: " + nodeLocation;
            assertTrue(errorMessage, nodeLocation.y == absoluteSnapToLocation.y);
        } else {
            // Get the absolute bounds of parent
            Rectangle parentBounds = new Rectangle(editor.getAbsoluteLocation(parentPart), parentPart.getFigure().getSize());

            errorMessage += " At least x or y must be on the grid (grid spacing = " + GRID_SPACING + "), but was: " + nodeLocation + " for parent: " + parentBounds;

            assertTrue(errorMessage,
                    (nodeLocation.x % GRID_SPACING == 0 && (nodeLocation.y == expectedLocation.y || nodeLocation.y == parentLocation.y - 2 || nodeLocation.y == parentBounds.bottom() - 8))
                            || ((nodeLocation.x == expectedLocation.x || nodeLocation.x == parentLocation.x - 2 || nodeLocation.x == parentBounds.right() - 8) && nodeLocation.y % GRID_SPACING == 0));
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

    /**
     * Check that a diagram element is aligned on the grid.
     * 
     * @param editPart
     *            edit part of the diagram element to check
     * @param elementNameToDisplay
     *            The name of the element displayed in case of error
     * @param gridSpacing
     *            The current grid spacing
     */
    private void checkLocationAlignOnGrid(IGraphicalEditPart editPart, String elementNameToDisplay, int gridSpacing) {
        Point location = editPart.getFigure().getBounds().getTopLeft();
        boolean locationIsOK = (location.x % gridSpacing) == 0 || (location.y % gridSpacing) == 0;
        if (!locationIsOK) {
            IGraphicalEditPart parentPart = (IGraphicalEditPart) editPart.getParent();
            Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart);
            locationIsOK = (location.x == parentBounds.x || location.x == (parentBounds.x + parentBounds.width))
                    || (location.y == parentBounds.y || location.y == (parentBounds.y + parentBounds.height));
        }
        if (!locationIsOK) {
            fail("For " + elementNameToDisplay + ", the x or y coordinate of the top left corner should be on the grid (grid spacing = " + gridSpacing + "), but was: " + location + ".");
        }

    }
}
