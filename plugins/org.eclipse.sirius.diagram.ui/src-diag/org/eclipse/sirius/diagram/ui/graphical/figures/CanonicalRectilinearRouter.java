/******************************************************************************
 * Copyright (c) 2002, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Obeo - adaptation for Sirius
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.graphical.figures;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.OrthogonalRouterUtilities;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * A Rectilinear router able to route edges at the GMF level. This class is
 * mainly duplicated from
 * org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter.
 * Warning: This Router doesn't handle all cases managed by the draw2D router,
 * this router is currently used by the edge centering operation. Some cases are
 * commented.
 */
// CHECKSTYLE:OFF
@SuppressWarnings({ "deprecation", "restriction" })
public class CanonicalRectilinearRouter {

    private static int maxNestedRoutingDepth = 1;

    /**
     * Overridden method from ObliqueRouter that will perform the conversion of
     * the polyline to a rectilinear version.
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ObliqueRouter#routeLine(org.eclipse.draw2d.Connection,
     *      int, org.eclipse.draw2d.geometry.PointList)
     */
    public void routeLine(Edge edge, int nestedRoutingDepth, PointList newLine, Rectangle srcAbsoluteBounds, Rectangle tgtAbsoluteBounds) {
        // The line should have at least 2 points.
        if (newLine.size() < 2) {
            return;
        }
        boolean skipNormalization = false;

        // Handle special routing: self connections and intersecting shapes
        // connections
        // if (checkSelfRelConnection(edge, newLine) ||
        // checkShapesIntersect(conn, newLine)) {
        // resetEndPointsToEdge(conn, newLine);
        // OrthogonalRouterUtilities.transformToOrthogonalPointList(newLine,
        // getOffShapeDirection(getAnchorOffRectangleDirection(newLine.getFirstPoint(),
        // sourceBoundsRelativeToConnection(conn))),
        // getOffShapeDirection(getAnchorOffRectangleDirection(newLine.getLastPoint(),
        // targetBoundsRelativeToConnection(conn))));
        // removeRedundantPoints(newLine);
        // return;
        // }

        if (edge.getSource() == edge.getTarget()) {
            nestedRoutingDepth = maxNestedRoutingDepth;
        }

        /*
         * Remove and store former anchor points. Anchor points will be
         * re-calculated anyway. However, the old anchor points may be useful if
         * connection didn't have any bend points except the anchor points.
         */
        Point lastStartAnchor = newLine.removePoint(0);
        Point lastEndAnchor = newLine.removePoint(newLine.size() - 1);

        /*
         * Check if connection is rectilinear and if not make it rectilinear
         */
        if (!OrthogonalRouterUtilities.isRectilinear(newLine)) {
            OrthogonalRouterUtilities.transformToOrthogonalPointList(newLine, PositionConstants.NONE, PositionConstants.NONE);
        }

        removeRedundantPoints(newLine);

        /*
         * Remove unnecessary points that are contained within source and/or
         * target shapes as well as insert extra points if all points are within
         * source and/or target shapes
         */
        removePointsInViews(edge, newLine, lastStartAnchor, lastEndAnchor, srcAbsoluteBounds, tgtAbsoluteBounds);

        Dimension tolerance = new Dimension(3, 0);
        // if (!RouterHelper.getInstance().isFeedback(conn))
        // tolerance =
        // (Dimension)MapModeUtil.getMapMode(conn).DPtoLP(tolerance);

        /*
         * Normalize polyline to eliminate extra segments. (This makes 3
         * segments collapsing into one, while line segments are moved)
         */
        if (!skipNormalization) {
            if (PointListUtilities.normalizeSegments(newLine, tolerance.width)) {
                /*
                 * Normalization can make our polyline not rectilinear. Hence,
                 * we need to normalize segments of polyline to straight line
                 * tolerance.
                 */
                // normalizeToStraightLineTolerance(newLine, tolerance.width);
            }
        }

        /*
         * Normalization is not touching the end points, hence we'd like to
         * handle this here. If distance between start and end (which are the
         * only points in a polyline) points is too short we'll remove one of
         * the points
         */
        if (newLine.size() == 2) {
            Ray middleSeg = new Ray(newLine.getFirstPoint(), newLine.getLastPoint());
            if (middleSeg.length() <= tolerance.width) {
                newLine.removePoint(0);
            }
        }

        /*
         * Calculate connection anchor points and possibly some extra routing
         * work to keep the connection rectilinear if anchor points make it not
         * rectilinear.
         */
        resetEndPointsToEdge(edge, newLine, srcAbsoluteBounds, tgtAbsoluteBounds);

        // if (nestedRoutingDepth < maxNestedRoutingDepth &&
        // !isValidRectilinearLine(edge, newLine)) {
        // routeLine(edge, ++nestedRoutingDepth, newLine, srcAbsoluteBounds,
        // tgtAbsoluteBounds, sourceAnchor, targetAnchor);
        // }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ObliqueRouter#
     * resetEndPointsToEdge(org.eclipse.draw2d.Connection,
     * org.eclipse.draw2d.geometry.PointList)
     */
    protected void resetEndPointsToEdge(Edge edge, PointList line, Rectangle source, Rectangle target) {

        PrecisionPoint sourceAnchor = getAnchorId(edge.getSourceAnchor());
        PrecisionPoint targetAnchor = getAnchorId(edge.getTargetAnchor());
        Point srcAnchorPoint = getAbsoluteAnchorCoordinates(source, sourceAnchor);
        Point targetAnchorPoint = getAbsoluteAnchorCoordinates(target, targetAnchor);

        int offSourceDirection = PositionConstants.NONE;
        int offTargetDirection = PositionConstants.NONE;
        int sourceAnchorRelativeLocation = PositionConstants.NONE;
        int targetAnchorRelativeLocation = PositionConstants.NONE;
        if (line.size() == 0) {
            /*
             * If there are no valid bend points, we'll use the oblique
             * connection anchor points and just convert the polyline from
             * oblique to rectilinear.
             */

            computePointListByIntersections(line, source, target, srcAnchorPoint, targetAnchorPoint);

            sourceAnchorRelativeLocation = getAnchorOffRectangleDirection(line.getFirstPoint(), source);
            targetAnchorRelativeLocation = getAnchorOffRectangleDirection(line.getLastPoint(), target);
            /*
             * We need to find two points offset from the source and target
             * anchors outside the shapes such that when the polyline is
             * converted to rectilinear from oblique we won't have rectilinear
             * line segments alligned with source or target shapes edges.
             */
            Point offStart = line.getFirstPoint();
            Point offEnd = line.getLastPoint();
            Dimension offsetDim = offStart.getDifference(offEnd).scale(0.5);
            offStart.translate(getTranslationValue(sourceAnchorRelativeLocation, Math.abs(offsetDim.width), Math.abs(offsetDim.height)));
            offEnd.translate(getTranslationValue(targetAnchorRelativeLocation, Math.abs(offsetDim.width), Math.abs(offsetDim.height)));
            line.insertPoint(offStart, 1);
            line.insertPoint(offEnd, 2);
            offSourceDirection = getOffShapeDirection(sourceAnchorRelativeLocation);
            offTargetDirection = getOffShapeDirection(targetAnchorRelativeLocation);
        } else {
            Point start = line.getFirstPoint();
            Point end = line.getLastPoint();
            /*
             * If anchor is not supporting orthogonal connections we'll use the
             * oblique connection anchors and then convert it to rectilinear.
             */

            // PrecisionPoint reference = new PrecisionPoint(start);
            // conn.getSourceAnchor().getOwner().translateToAbsolute(reference);
            // PrecisionPoint anchorLocation = new
            // PrecisionPoint(conn.getSourceAnchor().getLocation(reference));
            // conn.translateToRelative(anchorLocation);
            line.insertPoint(getOrthogonalInterserction(start, source, sourceAnchor), 0);

            /*
             * If anchor is not supporting orthogonal connections we'll use the
             * oblique connection anchors and then convert it to rectilinear.
             */
            // reference = new PrecisionPoint(end);
            // conn.getSourceAnchor().getOwner().translateToAbsolute(reference);
            // PrecisionPoint anchorLocation = new
            // PrecisionPoint(conn.getTargetAnchor().getLocation(reference));
            // conn.translateToRelative(anchorLocation);
            line.addPoint(getOrthogonalInterserction(end, target, targetAnchor));

            sourceAnchorRelativeLocation = getAnchorOffRectangleDirection(line.getFirstPoint(), source);
            offSourceDirection = getOffShapeDirection(sourceAnchorRelativeLocation);
            targetAnchorRelativeLocation = getAnchorOffRectangleDirection(line.getLastPoint(), target);
            offTargetDirection = getOffShapeDirection(targetAnchorRelativeLocation);
        }

        /*
         * Convert the polyline to rectilinear. If the connection is rectilinear
         * already then the connection will remain as it is.
         */
        OrthogonalRouterUtilities.transformToOrthogonalPointList(line, offSourceDirection, offTargetDirection);
        removeRedundantPoints(line);
    }

    /**
     * This method replaces the call of
     * <code>OrthogonalRouterUtilities.getOrthogonalLineSegToAnchorLoc</code> to
     * compute the rectilinear line from the start point (outside the shape)
     * toward the shape intersection.
     * 
     * @param start
     *            the start point (the point outside the shape).
     * @param bounds
     *            the shape bounds.
     * @param anchor
     *            the shape anchor.
     * @return The orthogonal intersection point between the start point and the
     *         anchor.
     */
    private Point getOrthogonalInterserction(Point start, Rectangle bounds, PrecisionPoint anchor) {
        Point anchorAbsCoordinate = getAbsoluteAnchorCoordinates(bounds, anchor);
        PointList intersections = GraphicalHelper.getIntersectionPoints(start, anchorAbsCoordinate, bounds);
        if (intersections.size() > 0) {
            Point intersection = intersections.getFirstPoint();

            int direction = PositionConstants.NONE;
            if (start.x() >= bounds.x() && start.x() <= bounds.x() + bounds.width()) {
                direction = PositionConstants.VERTICAL;
            } else if (start.y() >= bounds.y() && start.y() <= bounds.y() + bounds.height()) {
                direction = PositionConstants.HORIZONTAL;
            }

            // if the direction is still NONE, that means the orthogonal segment
            // doesn't intersect the shape bounds. In that case we return the
            // intersection point. see
            // org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor.getOrthogonalLocation(Point)
            if (direction == PositionConstants.VERTICAL) {
                intersection.setX(start.x());
            } else if (direction == PositionConstants.HORIZONTAL) {
                intersection.setY(start.y());
            }
            return intersection;

        }
        return anchorAbsCoordinate;

    }

    private PrecisionPoint getAnchorId(Anchor anchor) {
        PrecisionPoint anchorId = new PrecisionPoint(0.5, 0.5);
        if (anchor instanceof IdentityAnchor) {
            anchorId = BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) anchor).getId());
        }
        return anchorId;
    }

    private void computePointListByIntersections(PointList list, Rectangle sourceBounds, Rectangle targetBounds, Point sourceAnchor, Point targetAnchor) {
        PointList srcIntersections = GraphicalHelper.getIntersectionPoints(targetAnchor, sourceAnchor, sourceBounds);
        PointList tgtIntersections = GraphicalHelper.getIntersectionPoints(sourceAnchor, targetAnchor, targetBounds);
        if (srcIntersections.size() > 0 && tgtIntersections.size() > 0) {
            list.removeAllPoints();
            list.addPoint(srcIntersections.getFirstPoint());
            list.addPoint(tgtIntersections.getFirstPoint());
        }
    }

    private Point getAbsoluteAnchorCoordinates(Rectangle absoluteBounds, PrecisionPoint precisionPoint) {
        return new Point((int) (absoluteBounds.x() + Math.round(absoluteBounds.width() * precisionPoint.preciseX())),
                (int) (absoluteBounds.y() + Math.round(absoluteBounds.height() * precisionPoint.preciseY())));

    }

    /**
     * Removes consecutive points contained within the source shape and removes
     * consecutive points contained within the target shape. If all points have
     * been removed an extra point outside source and target shapes will be
     * added.
     * 
     * @param conn
     *            connection
     * @param newLine
     *            polyline of the connection (routed connection)
     * @param start
     *            old start anchor point
     * @param end
     *            old end anchor point
     * @param target
     * @param source
     */
    private void removePointsInViews(Edge edge, PointList newLine, Point start, Point end, Rectangle source, Rectangle target) {

        if (edge.getSource() == edge.getTarget()) {
            return;
        }

        // /*
        // * Get the bounds of anchorable figure of the source and target and
        // * translate it to connection relative coordinates.
        // */
        // PrecisionRectangle source = conn.getSourceAnchor().getOwner() != null
        // ? new
        // PrecisionRectangle(FigureUtilities.getAnchorableFigureBounds(conn.getSourceAnchor().getOwner()))
        // : null;
        // PrecisionRectangle target = conn.getTargetAnchor().getOwner() != null
        // ? new
        // PrecisionRectangle(FigureUtilities.getAnchorableFigureBounds(conn.getTargetAnchor().getOwner()))
        // : null;
        // if (source != null) {
        // conn.getSourceAnchor().getOwner().translateToAbsolute(source);
        // conn.translateToRelative(source);
        // }
        // if (target != null) {
        // conn.getTargetAnchor().getOwner().translateToAbsolute(target);
        // conn.translateToRelative(target);
        // }
        //
        Point lastRemovedFromSource = null;
        Point lastRemovedFromTarget = null;

        /*
         * Starting from the first point of polyline remove points that are
         * contained within the source shape until the first point outside is
         * found. Remember the point that was removed from the source shape last
         * for a possible case of all points removed from polyline.
         */
        if (newLine.size() != 0 && source.contains(new PrecisionPoint(newLine.getFirstPoint()))) {
            lastRemovedFromSource = newLine.removePoint(0);
            for (int i = 0; i < newLine.size() && source.contains(new PrecisionPoint(newLine.getPoint(i))); i++) {
                lastRemovedFromSource = newLine.removePoint(i--);
            }
        }

        /*
         * Starting from the end point of polyline remove points that are
         * contained within the target shape until the first point outside is
         * found. Remember the point that was removed from the target shape last
         * for a possible case of all points removed from polyline.
         */
        if (newLine.size() != 0 && target.contains(new PrecisionPoint(newLine.getLastPoint()))) {
            lastRemovedFromTarget = newLine.removePoint(newLine.size() - 1);
            for (int i = newLine.size(); i > 0 && target.contains(new PrecisionPoint(newLine.getPoint(i - 1))); i--) {
                lastRemovedFromTarget = newLine.removePoint(i - 1);
            }
        }

        /*
         * Handle the special case of all points removed from polyline.
         */
        if (newLine.size() == 0) {
            Dimension tolerance = new Dimension(1, 0);
            // if (!RouterHelper.getInstance().isFeedback(conn))
            // tolerance =
            // (Dimension)MapModeUtil.getMapMode(conn).DPtoLP(tolerance);
            int toleranceValue = tolerance.width;
            if (lastRemovedFromSource == null) {
                lastRemovedFromSource = start;
            }
            if (lastRemovedFromTarget == null) {
                lastRemovedFromTarget = end;
            }
            /*
             * If last point removed from source and the points removed from
             * target form a vertical or horizontal line we'll find a point
             * located on this line and is outside of source and target shape
             * and insert it in the polyline. The check for vertical and
             * horizontal segment is using tolerance value, because bend point
             * location extracted from RelativeBendpoint can have precision
             * errors due to non-integer weight factors.
             */
            if (Math.abs(lastRemovedFromSource.x - lastRemovedFromTarget.x) < toleranceValue) {
                // Vertical
                if (source.y < target.y) {
                    newLine.addPoint(lastRemovedFromSource.x, (source.getBottom().y + target.getTop().y) / 2);
                } else {
                    newLine.addPoint(lastRemovedFromSource.x, (source.getTop().y + target.getBottom().y) / 2);
                }
            } else if (Math.abs(lastRemovedFromSource.y - lastRemovedFromTarget.y) < toleranceValue) {
                // Horizontal
                if (source.x < target.x) {
                    newLine.addPoint((source.getRight().x + target.getLeft().x) / 2, lastRemovedFromSource.y);
                } else {
                    newLine.addPoint((source.getLeft().x + target.getRight().x) / 2, lastRemovedFromSource.y);
                }
            } else if ((edge.getSourceAnchor() instanceof IdentityAnchor && StringStatics.BLANK.equals(((IdentityAnchor) edge.getSourceAnchor()).getId())
                    && (edge.getTargetAnchor() instanceof IdentityAnchor && StringStatics.BLANK.equals(((IdentityAnchor) edge.getTargetAnchor()).getId())))) {
                /*
                 * This a special case for old diagrams with rectilinear
                 * connections routed by the old router to look good with the
                 * new router
                 */
                if (lastRemovedFromSource != null && lastRemovedFromTarget != null) {
                    newLine.addPoint((lastRemovedFromSource.x + lastRemovedFromTarget.x) / 2, (lastRemovedFromSource.y + lastRemovedFromTarget.y) / 2);
                } else {
                    double startX = Math.max(source.x, target.x);
                    double endX = Math.min(source.x + source.width, target.x + target.width);
                    double startY = Math.max(source.y, target.y);
                    double endY = Math.min(source.y + source.height, target.y + target.height);
                    if (startX < endX) {
                        if (source.y < target.y) {
                            newLine.addPoint((int) Math.round((startX + endX) / 2.0), (source.getBottom().y + target.getTop().y) / 2);
                        } else {
                            newLine.addPoint((int) Math.round((startX + endX) / 2.0), (source.getTop().y + target.getBottom().y) / 2);
                        }
                    } else if (startY < endY) {
                        if (source.x < target.x) {
                            newLine.addPoint((source.getRight().x + target.getLeft().x) / 2, (int) Math.round((startY + endY) / 2.0));
                        } else {
                            newLine.addPoint((source.getLeft().x + target.getRight().x) / 2, (int) Math.round((startY + endY) / 2.0));
                        }
                    }
                }
            }
        }
    }

    /**
     * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
     * RectilinearRouter. Returns a translation dimension for the anchor point.
     * Translation dimension translates the anchor point off the shape. The off
     * shape direction is specified by the relative to the shape geographic
     * position of the anchor
     * 
     * @param position
     *            relative to the shape geographic position of the anchor
     * @param xFactorValue
     *            translation value along x-axis
     * @param yFactorValue
     *            translation value along y-axis
     * @return
     */
    private static Dimension getTranslationValue(int position, int xFactorValue, int yFactorValue) {
        Dimension translationDimension = new Dimension();
        if (position == PositionConstants.EAST) {
            translationDimension.setWidth(xFactorValue);
        } else if (position == PositionConstants.SOUTH) {
            translationDimension.setHeight(yFactorValue);
        } else if (position == PositionConstants.WEST) {
            translationDimension.setWidth(-xFactorValue);
        } else if (position == PositionConstants.NORTH) {
            translationDimension.setHeight(-yFactorValue);
        }
        return translationDimension;
    }

    /**
     * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
     * RectilinearRouter. Determines the relative to rectangle geographic
     * location of a point. Example: If shape is closer to the the top edge of
     * the rectangle location would be north. Method used to determine which
     * side of shape's bounding rectangle is closer to connection's anchor
     * point. All geometric quantities must be in the same coordinate system.
     * 
     * @param anchorPoint
     *            location of the anchor point
     * @param rect
     *            bounding rectangle of the shape
     * @return
     */
    private static int getAnchorOffRectangleDirection(Point anchorPoint, Rectangle rect) {
        int position = PositionConstants.NORTH;
        int criteriaValue = Math.abs(anchorPoint.y - rect.y);
        int tempCriteria = Math.abs(anchorPoint.y - rect.y - rect.height);
        if (tempCriteria < criteriaValue) {
            criteriaValue = tempCriteria;
            position = PositionConstants.SOUTH;
        }

        tempCriteria = Math.abs(anchorPoint.x - rect.x);
        if (tempCriteria < criteriaValue) {
            criteriaValue = tempCriteria;
            position = PositionConstants.WEST;
        }

        tempCriteria = Math.abs(anchorPoint.x - rect.x - rect.width);
        if (tempCriteria < criteriaValue) {
            criteriaValue = tempCriteria;
            position = PositionConstants.EAST;
        }

        return position;
    }

    /**
     * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
     * RectilinearRouter. Determines whether the rectilinear line segment coming
     * out of the shape should be horizontal or vertical based on the anchor
     * geographic position relative to the shape
     * 
     * @param anchorRelativeLocation
     * @return
     */
    private static int getOffShapeDirection(int anchorRelativeLocation) {
        if (anchorRelativeLocation == PositionConstants.EAST || anchorRelativeLocation == PositionConstants.WEST) {
            return PositionConstants.HORIZONTAL;
        } else if (anchorRelativeLocation == PositionConstants.NORTH || anchorRelativeLocation == PositionConstants.SOUTH) {
            return PositionConstants.VERTICAL;
        }
        return PositionConstants.NONE;
    }

    /**
     * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
     * RectilinearRouter. Iterates through points of a polyline and does the
     * following: if 3 points lie on the same line the middle point is removed
     * 
     * @param line
     *            polyline's points
     */
    private static boolean removeRedundantPoints(PointList line) {
        int initialNumberOfPoints = line.size();
        if (line.size() > 2) {
            PointList newLine = new PointList(line.size());
            newLine.addPoint(line.removePoint(0));
            while (line.size() >= 2) {
                Point p0 = newLine.getLastPoint();
                Point p1 = line.getPoint(0);
                Point p2 = line.getPoint(1);
                if (p0.x == p1.x && p0.x == p2.x) {
                    // Have two vertical segments in a row
                    // get rid of the point between
                    line.removePoint(0);
                } else if (p0.y == p1.y && p0.y == p2.y) {
                    // Have two horizontal segments in a row
                    // get rid of the point between
                    line.removePoint(0);
                } else {
                    newLine.addPoint(line.removePoint(0));
                }
            }
            while (line.size() > 0) {
                newLine.addPoint(line.removePoint(0));
            }
            line.removeAllPoints();
            line.addAll(newLine);
        }
        return line.size() != initialNumberOfPoints;
    }
}
