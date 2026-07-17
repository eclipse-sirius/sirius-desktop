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
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * Same tests as {@link BorderedNodeCreationNearCollapsedTest} but with
 * snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class BorderedNodeCreationNearCollapsedWithSnapToGridTest extends BorderedNodeCreationNearCollapsedTest {

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
     * Only one of the coordinates is snap to grid. The other is constrained by the parent border.
     */
    @Override
    protected void assertSameLocation(String errorMessage, Node borderNode, Point nodeLocation, IGraphicalEditPart borderNodePart, Point expectedLocation, Point parentLocation,
            Point creationLocation, IGraphicalEditPart parentPart) {
        PrecisionPoint snapToLocation = new PrecisionPoint();
        if (creationLocation == null) {
            snapToLocation = editor.adaptLocationToSnap(expectedLocation);
        } else {
            snapToLocation = new PrecisionPoint(creationLocation);
            addDiagramScrollbar(parentPart.getFigure(), snapToLocation);
            considerZoom(parentPart.getFigure(), snapToLocation);
            snapToLocation = editor.adaptLocationToSnap(snapToLocation);
        }
        // Adapt the expected location to the scrollbar of the parents
        PrecisionPoint absoluteSnapToLocation = new PrecisionPoint(snapToLocation);
        if (parentPart != null) {
            addParentScrollbar(parentPart.getFigure(), absoluteSnapToLocation);
        }
        assertBorderNodeCenteredOnGrid(errorMessage, borderNode, nodeLocation, borderNodePart, parentLocation, parentPart, absoluteSnapToLocation, GRID_SPACING, new Point(0, 0));
    }

    /**
     * Add scrolls of diagram
     * 
     * @param figure
     *            the actual figure level
     * @param location
     *            a location
     */
    private static void addDiagramScrollbar(final IFigure figure, final PrecisionPoint location) {
        if (figure instanceof Viewport && figure.getParent() != null && figure.getParent().getParent() == null) {
            location.performTranslate(((Viewport) figure).getHorizontalRangeModel().getValue(), ((Viewport) figure).getVerticalRangeModel().getValue());
        }
        if (figure.getParent() != null) {
            addDiagramScrollbar(figure.getParent(), location);
        }
    }

    /**
     * Add scrolls of diagram
     * 
     * @param figure
     *            the actual figure level
     * @param location
     *            a location
     */
    private static void considerZoom(final IFigure figure, final PrecisionPoint location) {
        if (figure instanceof ScalableFigure) {
            location.performScale(1 / ((ScalableFigure) figure).getScale());
        }
        if (figure.getParent() != null) {
            considerZoom(figure.getParent(), location);
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
