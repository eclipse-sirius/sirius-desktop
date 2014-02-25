/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.gmf.runtime.editparts;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;

import com.google.common.base.Preconditions;

/**
 * Utility class to collect helper methods which deal with GraphicalOrdering but
 * which are not part of its API.
 * 
 * @author nlepine
 */
public final class GraphicalHelper {
    private GraphicalHelper() {
        // Prevent instantiation.
    }

    /**
     * Get the zoom factor.
     * 
     * @param part
     *            the current part
     * @return the zoom factor
     */
    public static double getZoom(IGraphicalEditPart part) {
        Preconditions.checkNotNull(part);
        double scale = 1.0;
        if (part.getRoot() instanceof DiagramRootEditPart) {
            DiagramRootEditPart rootEditPart = (DiagramRootEditPart) part.getRoot();
            scale = rootEditPart.getZoomManager().getZoom();
        }
        return scale;
    }

    /**
     * Applied zoom on relative point.
     * 
     * @param part
     *            the current part
     * @param relativePoint
     *            relative point
     */
    public static void appliedZoomOnRelativePoint(IGraphicalEditPart part, Point relativePoint) {
        double zoom = getZoom(part);
        relativePoint.setLocation((int) (relativePoint.x / zoom), (int) (relativePoint.y / zoom));
    }

    /**
     * Set the zoom factor.
     * 
     * @param part
     *            the current part
     * @param scale
     *            the zoom factor
     */
    public static void setZoom(IGraphicalEditPart part, double scale) {
        Preconditions.checkNotNull(part);
        if (part.getRoot() instanceof DiagramRootEditPart) {
            DiagramRootEditPart rootEditPart = (DiagramRootEditPart) part.getRoot();
            rootEditPart.getZoomManager().setZoom(scale);
        }
    }

    /**
     * Returns the difference between the logical origin (0, 0) and the top-left
     * point actually visible. This corresponds to how much the scrollbars
     * "shift" the diagram.
     * 
     * @param part
     *            an edit part on the view
     * @return the scroll size
     */
    public static Point getScrollSize(IGraphicalEditPart part) {
        Preconditions.checkNotNull(part);
        FreeformViewport viewport = FigureUtilities.getFreeformViewport(part.getFigure());
        if (viewport != null) {
            return viewport.getViewLocation();
        } else {
            return new Point(0, 0);
        }
    }

    /**
     * .
     * 
     * @param part
     *            an edit part on the view
     * @param scrollPosition
     *            the scroll size
     */
    public static void setScrollSize(IGraphicalEditPart part, Point scrollPosition) {
        Preconditions.checkNotNull(part);
        // FreeformViewport viewport =
        // FigureUtilities.getFreeformViewport(part.getFigure());
        // if (viewport != null) {
        // viewport.setLocation(scrollPosition);
        // }
        if (part.getViewer().getControl() instanceof FigureCanvas) {
            // UIThreadRunnable.syncExec(new VoidResult() {
            // public void run() {
            ((FigureCanvas) part.getViewer().getControl()).scrollTo(scrollPosition.x, scrollPosition.y);
            // }
            // });
        }
    }

    /**
     * Converts a point from screen coordinates to logical coordinates.
     * 
     * @param point
     *            the point to convert.
     * @param part
     *            a part from the diagram.
     */
    public static void screen2logical(Point point, IGraphicalEditPart part) {
        point.translate(GraphicalHelper.getScrollSize(part));
        point.performScale(1.0d / GraphicalHelper.getZoom(part));
    }

    /**
     * Converts a rectangle from screen coordinates to logical coordinates.
     * 
     * @param rect
     *            the rectangle to convert.
     * @param part
     *            a part from the diagram.
     */
    public static void screen2logical(Rectangle rect, IGraphicalEditPart part) {
        rect.translate(GraphicalHelper.getScrollSize(part));
        rect.performScale(1.0d / GraphicalHelper.getZoom(part));
    }

    /**
     * Converts a dimension from screen coordinates to logical coordinates.
     * Dimensions have no defined position, so only the current zoom level is
     * take into account, not the scroll state.
     * 
     * @param dim
     *            the dimension to convert.
     * @param part
     *            a part from the diagram.
     */
    public static void screen2logical(Dimension dim, IGraphicalEditPart part) {
        dim.performScale(1.0d / GraphicalHelper.getZoom(part));
    }

    /**
     * Converts a point from logical coordinates to screen coordinates.
     * 
     * @param point
     *            the point to convert.
     * @param part
     *            a part from the diagram.
     */
    public static void logical2screen(Point point, IGraphicalEditPart part) {
        point.performScale(GraphicalHelper.getZoom(part));
        point.translate(GraphicalHelper.getScrollSize(part).negate());
    }

    /**
     * Converts a rectangle from logical coordinates to screen coordinates.
     * 
     * @param rect
     *            the rectangle to convert.
     * @param part
     *            a part from the diagram.
     */
    public static void logical2screen(Rectangle rect, IGraphicalEditPart part) {
        rect.performScale(GraphicalHelper.getZoom(part));
        rect.translate(GraphicalHelper.getScrollSize(part).negate());
    }

    /**
     * Converts a dimension from logical coordinates to screen coordinates.
     * Dimensions have no defined position, so only the current zoom level is
     * take into account, not the scroll state.
     * 
     * @param dim
     *            the dimension to convert.
     * @param part
     *            a part from the diagram.
     */
    public static void logical2Screen(Dimension dim, IGraphicalEditPart part) {
        dim.performScale(GraphicalHelper.getZoom(part));
    }

}
