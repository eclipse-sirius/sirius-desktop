/*******************************************************************************
 * Copyright (c) 2010, 2026 THALES GLOBAL SERVICES.
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
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;

/**
 * Same tests as {@link CollapsedBorderedNodeCreationNearCollapsedTest} but with
 * snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class CollapsedBorderedNodeCreationNearCollapsedWithSnapToGridTest extends CollapsedBorderedNodeCreationNearCollapsedTest {

    /**
     * The grid spacing in pixels.
     */
    private static final int GRID_SPACING = 100;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        editor.setSnapToGrid(true, GRID_SPACING, 2);
    }

    @Override
    protected Point adaptExpectedLocation(IFigure parentFigure, Point parentAbsoluteLocation, Point absoluteExpectedLocation) {
        // Do not adapt expected location this is done later in
        // #assertSameLocation(String, Point, Point, Point, Point,
        // IGraphicalEditPart) method.
        // This allows to have in this method the expected location for expanded
        // state of border node.
        return absoluteExpectedLocation.getCopy();

    }

    /**
     * Only one of the coordinates is snap to grid. The other is constrained by
     * the parent border.
     */
    @Override
    protected void assertSameLocation(String errorMessage, Node borderNode, Point nodeLocation, IGraphicalEditPart borderNodePart, Point absoluteExpectedLocation, Point parentAbsoluteLocation,
            Point creationLocation, IGraphicalEditPart parentPart) {
        // Adapt the expected location to the grid
        Point snapToLocation = editor.adaptLocationToSnap(absoluteExpectedLocation);
        // Adapt the expected location to the scrollbar of the parents
        Point absoluteSnapToLocation = new PrecisionPoint(snapToLocation);
        if (parentPart != null) {
            addParentScrollbar(parentPart.getFigure(), absoluteSnapToLocation);
        }
        if (createCollapsedBorderedNode) {
            // Adapt the expected location to collapsed one.
            absoluteExpectedLocation = PortLayoutHelper.getCollapseCandidateLocation(new Dimension(BorderedNodeCreationTest.COLLAPSED_SIZE, BorderedNodeCreationTest.COLLAPSED_SIZE),
                    new Rectangle(absoluteExpectedLocation.x, absoluteExpectedLocation.y, BorderedNodeCreationTest.EXPANDED_SIZE, BorderedNodeCreationTest.EXPANDED_SIZE),
                    new Rectangle(parentAbsoluteLocation, parentPart.getFigure().getSize())).getTopLeft();
            // Adapt the snap location to collapsed one.
            absoluteSnapToLocation = PortLayoutHelper.getCollapseCandidateLocation(new Dimension(BorderedNodeCreationTest.COLLAPSED_SIZE, BorderedNodeCreationTest.COLLAPSED_SIZE),
                    new Rectangle(absoluteSnapToLocation.x, absoluteSnapToLocation.y, BorderedNodeCreationTest.EXPANDED_SIZE, BorderedNodeCreationTest.EXPANDED_SIZE),
                    new Rectangle(parentAbsoluteLocation, parentPart.getFigure().getSize())).getTopLeft();
        }
        assertBorderNodeCenteredOnGrid(errorMessage, borderNode, nodeLocation, borderNodePart, parentAbsoluteLocation, parentPart, absoluteSnapToLocation, GRID_SPACING, new Point(0, 0));
    }

    /**
     * Add scrolls of parent (except the diagram scroll)
     * 
     * @param figure
     *            the actual figure level
     * @param location
     *            a location in absolute coordinates
     */
    private static void addParentScrollbar(final IFigure figure, final Point location) {
        if (figure instanceof Viewport && figure.getParent() != null && figure.getParent().getParent() != null) {
            location.performTranslate(((Viewport) figure).getHorizontalRangeModel().getValue(), ((Viewport) figure).getVerticalRangeModel().getValue());
        }
        if (figure.getParent() != null) {
            addParentScrollbar(figure.getParent(), location);
        }
    }
}
