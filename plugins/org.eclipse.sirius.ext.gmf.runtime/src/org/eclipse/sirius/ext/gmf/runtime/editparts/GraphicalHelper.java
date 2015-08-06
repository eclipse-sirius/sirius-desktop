/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.gmf.runtime.editparts;

import java.util.List;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

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
    public static double getZoom(EditPart part) {
        Preconditions.checkNotNull(part);
        double scale = 1.0;
        if (part.getRoot() instanceof DiagramRootEditPart) {
            DiagramRootEditPart rootEditPart = (DiagramRootEditPart) part.getRoot();
            scale = rootEditPart.getZoomManager().getZoom();
        }
        return scale;
    }

    /**
     * Applied zoom on relative point.<BR>
     * For example:
     * <UL>
     * <LI>For a zoom of 200%, the result of this method for the point (100,
     * 100) is (200, 200)</LI>
     * <LI>For a zoom of 50%, the result of this method for the point (100, 100)
     * is (50, 50)</LI>
     * </UL>
     * 
     * @param part
     *            the current part
     * @param relativePoint
     *            relative point
     * @deprecated Use
     *             {@link #applyInverseZoomOnPoint(IGraphicalEditPart, Point)}
     *             instead
     */
    @Deprecated
    public static void appliedZoomOnRelativePoint(IGraphicalEditPart part, Point relativePoint) {
        double zoom = getZoom(part);
        if (relativePoint instanceof PrecisionPoint) {
            ((PrecisionPoint) relativePoint).setPreciseLocation(relativePoint.preciseX() / zoom, relativePoint.preciseY() / zoom);
        } else {
            relativePoint.setLocation((int) (relativePoint.x * zoom), (int) (relativePoint.y * zoom));
        }
    }

    /**
     * Applies zoom on a point and returns this point for convenience.<BR>
     * For example:
     * <UL>
     * <LI>For a zoom of 200%, the result of this method for the point (100,
     * 100) is (200, 200)</LI>
     * <LI>For a zoom of 50%, the result of this method for the point (100, 100)
     * is (50, 50)</LI>
     * </UL>
     * 
     * @param part
     *            the current part
     * @param point
     *            a point
     * @return <code>point</code> for convenience
     */
    public static Point applyZoomOnPoint(IGraphicalEditPart part, Point point) {
        double zoom = getZoom(part);
        if (point instanceof PrecisionPoint) {
            ((PrecisionPoint) point).setPreciseLocation(point.preciseX() * zoom, point.preciseY() * zoom);
        } else {
            point.setLocation((int) (point.x * zoom), (int) (point.y * zoom));
        }
        return point;
    }

    /**
     * Applies inverse zoom on a point and returns this point for convenience.
     * <BR>
     * For example:
     * <UL>
     * <LI>For a zoom of 200%, the result of this method for the point (100,
     * 100) is (50, 50)</LI>
     * <LI>For a zoom of 50%, the result of this method for the point (100, 100)
     * is (200, 200)</LI>
     * </UL>
     * 
     * @param part
     *            the current part
     * @param point
     *            a point
     * @return <code>point</code> for convenience
     */
    public static Point applyInverseZoomOnPoint(IGraphicalEditPart part, Point point) {
        double zoom = getZoom(part);
        if (point instanceof PrecisionPoint) {
            ((PrecisionPoint) point).setPreciseLocation(point.preciseX() / zoom, point.preciseY() / zoom);
        } else {
            point.setLocation((int) (point.x / zoom), (int) (point.y / zoom));
        }
        return point;
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
    public static Point getScrollSize(GraphicalEditPart part) {
        Preconditions.checkNotNull(part);
        FreeformViewport viewport = FigureUtilities.getRootFreeformViewport(part.getFigure());
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
    public static void screen2logical(Rectangle rect, GraphicalEditPart part) {
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

    /**
     * Return the Point (absolute draw2d coordinates) corresponding to this
     * Anchor. If anchor is not an IdentityAnchor, the center of
     * <code>parent</code> is returned.
     * 
     * @param parent
     *            The parent node
     * @param anchor
     *            The anchor
     * @return The corresponding point to this anchor
     */
    public static Point getAnchorPoint(IGraphicalEditPart parent, Anchor anchor) {
        if (anchor instanceof IdentityAnchor) {
            return getAnchorPoint(parent, (IdentityAnchor) anchor);
        } else {
            return getAnchorPoint(parent, null);
        }
    }

    /**
     * Return the Point (absolute draw2d coordinates) corresponding to this
     * Anchor. If anchor is null, the center of <code>parent</code> is returned.
     * 
     * @param parent
     *            The parent node
     * @param anchor
     *            The anchor
     * @return The corresponding point to this anchor
     */
    public static Point getAnchorPoint(IGraphicalEditPart parent, IdentityAnchor anchor) {
        PrecisionRectangle bounds;
        if (parent.getFigure() instanceof HandleBounds) {
            bounds = new PrecisionRectangle(((HandleBounds) parent.getFigure()).getHandleBounds());
        } else {
            bounds = new PrecisionRectangle(parent.getFigure().getBounds());
        }
        parent.getFigure().translateToAbsolute(bounds);

        PrecisionPoint rel;
        if (anchor != null) {
            rel = BaseSlidableAnchor.parseTerminalString(anchor.getId());
        } else {
            // If anchor is null, the default value is (0.5, 0.5)
            rel = new PrecisionPoint(0.5, 0.5);
        }
        Point location = new PrecisionPoint(bounds.preciseX() + bounds.preciseWidth() * rel.preciseX(), bounds.preciseY() + bounds.preciseHeight() * rel.preciseY());
        return location;
    }

    /**
     * Get intersection between a line between lineOrigin and lineTerminus, and
     * the rectangle bounds of the part. If there are several intersections, the
     * shortest is returned.
     * 
     * @param lineOrigin
     *            Origin of the line
     * @param lineTerminus
     *            Terminus of the line
     * @param part
     *            Part to detect intersection.
     * @param minimalDistancefromLineOrigin
     *            true if the shortest distance is between the line origin and
     *            the part, false otherwise.
     * @return Intersection between a line and a rectangle.
     */
    public static Option<Point> getIntersection(Point lineOrigin, Point lineTerminus, IGraphicalEditPart part, boolean minimalDistancefromLineOrigin) {
        return getIntersection(lineOrigin, lineTerminus, part, minimalDistancefromLineOrigin, false);
    }

    /**
     * Get intersection between a line between lineOrigin and lineTerminus, and
     * the rectangle bounds of the part. If there are several intersections, the
     * shortest is returned.
     * 
     * @param lineOrigin
     *            Origin of the line
     * @param lineTerminus
     *            Terminus of the line
     * @param part
     *            Part to detect intersection.
     * @param minimalDistancefromLineOrigin
     *            true if the shortest distance is between the line origin and
     *            the part, false otherwise.
     * @param useNearestPoint
     *            If true, if there is no intersection, the nearest point on the
     *            rectangle is returned.
     * @return Intersection between a line and a rectangle.
     */
    public static Option<Point> getIntersection(Point lineOrigin, Point lineTerminus, IGraphicalEditPart part, boolean minimalDistancefromLineOrigin, boolean useNearestPoint) {
        // Get the bounds of the part
        Rectangle bounds = getAbsoluteBoundsIn100Percent(part);
        return getIntersection(lineOrigin, lineTerminus, bounds, minimalDistancefromLineOrigin, useNearestPoint);
    }

    /**
     * Get intersection between a line between lineOrigin and lineTerminus, and
     * a rectangle. If there are several intersections, the shortest is
     * returned.
     * 
     * @param lineOrigin
     *            Origin of the line
     * @param lineTerminus
     *            Terminus of the line
     * @param rectangle
     *            rectangle to detect intersection.
     * @param minimalDistancefromLineOrigin
     *            true if the shortest distance is between the line origin and
     *            the part, false otherwise.
     * @return Intersection between a line and a rectangle.
     */
    public static Option<Point> getIntersection(Point lineOrigin, Point lineTerminus, Rectangle rectangle, boolean minimalDistancefromLineOrigin) {
        return getIntersection(lineOrigin, lineTerminus, rectangle, minimalDistancefromLineOrigin, false);
    }

    /**
     * Get intersection between a line between lineOrigin and lineTerminus, and
     * a rectangle. If there are several intersections, the shortest is
     * returned.
     * 
     * @param lineOrigin
     *            Origin of the line
     * @param lineTerminus
     *            Terminus of the line
     * @param rectangle
     *            rectangle to detect intersection.
     * @param minimalDistancefromLineOrigin
     *            true if the shortest distance is between the line origin and
     *            the part, false otherwise.
     * @param useNearestPoint
     *            If true, if there is no intersection, the nearest point on the
     *            rectangle is returned.
     * @return Intersection between a line and a rectangle.
     */
    public static Option<Point> getIntersection(Point lineOrigin, Point lineTerminus, Rectangle rectangle, boolean minimalDistancefromLineOrigin, boolean useNearestPoint) {
        Option<Point> result = Options.newNone();
        // Create the line segment
        PointList line = new PointList();
        line.addPoint(lineOrigin);
        line.addPoint(lineTerminus);
        // Get the intersection
        PointList partBoundsPointList = PointListUtilities.createPointsFromRect(rectangle);
        PointList distances = new PointList();
        PointList intersections = new PointList();
        PointListUtilities.findIntersections(line, partBoundsPointList, intersections, distances);

        Point oppositePoint;
        if (minimalDistancefromLineOrigin) {
            oppositePoint = lineOrigin;
        } else {
            oppositePoint = lineTerminus;
        }
        if (intersections.size() > 0) {
            Point shortestPoint = intersections.getFirstPoint();
            double minimalDistance = shortestPoint.getDistance(oppositePoint);
            for (int i = 1; i < intersections.size(); i++) {
                Point intersectionPoint = intersections.getPoint(i);
                double currentDistance = intersectionPoint.getDistance(oppositePoint);
                if (currentDistance < minimalDistance) {
                    minimalDistance = currentDistance;
                    shortestPoint = intersectionPoint;
                }
            }
            result = Options.newSome(shortestPoint);
        } else if (!lineOrigin.equals(lineTerminus) && useNearestPoint) {
            // If no intersection is found and the origin is not the terminus,
            // the origin (or the terminus) is outside the rectangle, probably
            // because of the snap, so we search the nearest point on the figure
            // respecting one of the x or y coordinate.
            Point linePointToConsider;
            if (minimalDistancefromLineOrigin) {
                linePointToConsider = lineTerminus;
            } else {
                linePointToConsider = lineOrigin;
            }

            List<Point> nearestPoints = Lists.newArrayList();
            List rectangleBorders = PointListUtilities.getLineSegments(partBoundsPointList);
            for (Object rectangleBorder : rectangleBorders) {
                if (rectangleBorder instanceof LineSeg) {
                    LineSeg lineSeg = (LineSeg) rectangleBorder;
                    Point potentialNearestPoint;
                    if (lineSeg.getOrigin().x == lineSeg.getTerminus().x) {
                        potentialNearestPoint = new Point(lineSeg.getOrigin().x, linePointToConsider.y);
                    } else {
                        potentialNearestPoint = new Point(linePointToConsider.x, lineSeg.getOrigin().y);
                    }
                    if (lineSeg.containsPoint(potentialNearestPoint, 0)) {
                        nearestPoints.add(potentialNearestPoint);
                    }
                }
            }
            if (nearestPoints.size() > 0) {
                double minimalDistance = -1;
                Point resultPoint = null;
                // Search the closest intersection
                for (Point nearestPoint : nearestPoints) {
                    double distance = nearestPoint.getDistance(oppositePoint);
                    if (minimalDistance == -1 || distance < minimalDistance) {
                        minimalDistance = distance;
                        resultPoint = nearestPoint;
                    }
                }
                result = Options.newSome(resultPoint);
            }

        }
        return result;
    }

    /**
     * Get the intersection point list between the segment formed by the start
     * and end points and the given figure bounds.
     * 
     * @param start
     *            start point
     * @param end
     *            end point
     * @param figureBounds
     *            the figure bounds.
     * @return the intersection point list.
     */
    public static PointList getIntersectionPoints(Point start, Point end, Rectangle figureBounds) {
        PointList intersection = new PointList();
        final PointList polygon = PointListUtilities.createPointsFromRect(figureBounds);
        LineSeg lineSeg = new LineSeg(start, end);
        PointList intersectionTemp = lineSeg.getLineIntersectionsWithLineSegs(polygon);
        for (int i = 0; i < intersectionTemp.size(); i++) {
            Point currentPoint = intersectionTemp.getPoint(i);
            if (lineSeg.containsPoint(currentPoint, 1)) {
                intersection.addPoint(currentPoint);
            }
        }
        return intersection;
    }

    /**
     * Get the absolute bounds of this <code>part</code>.<BR>
     * Detail: If the zoom is set to 200%, the location and the size are
     * multiplied by two with respect to the real location and size.
     * 
     * @param part
     *            The part to consider.
     * @return The absolute bounds.
     */
    public static Rectangle getAbsoluteBounds(IGraphicalEditPart part) {
        PrecisionRectangle bounds;
        if (part.getFigure() instanceof HandleBounds) {
            bounds = new PrecisionRectangle(((HandleBounds) part.getFigure()).getHandleBounds());
        } else {
            bounds = new PrecisionRectangle(part.getFigure().getBounds());
        }
        part.getFigure().translateToAbsolute(bounds);
        return bounds;
    }

    /**
     * Get the absolute bounds of this <code>part</code>. In case of zoom or/and
     * a scrollbar, the bounds are converted from screen to logical.<BR>
     * 
     * @param part
     *            The part to consider.
     * @return The absolute bounds.
     */
    public static Rectangle getAbsoluteBoundsIn100Percent(GraphicalEditPart part) {
        PrecisionRectangle bounds;
        if (part.getFigure() instanceof HandleBounds) {
            bounds = new PrecisionRectangle(((HandleBounds) part.getFigure()).getHandleBounds());
        } else {
            bounds = new PrecisionRectangle(part.getFigure().getBounds());
        }
        part.getFigure().translateToAbsolute(bounds);
        screen2logical(bounds, part);
        return bounds;
    }

    /**
     * Return true if the snapToGrid is enabled for the diagram containing this
     * edit part, false otherwise.
     * 
     * @param editPart
     *            The edit part to use.
     * @return true if the snapToGrid is enabled for the diagram containing this
     *         edit part, false otherwise.
     */
    public static boolean isSnapToGridEnabled(EditPart editPart) {
        return (Boolean) editPart.getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
    }
}
