/*******************************************************************************
 * Copyright (c) 2014, 2023 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation - removeRedundantPoints(PointList) method
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.routers;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Utility class for rectilinear edges. Handles edge centering.
 * 
 * @author Florian Barbin
 *
 */
public final class RectilinearEdgeUtil {

    /**
     * The minimal distance between an edge connection and the first bendpoint to add (if we need to add bendpoints).
     */
    private static final int MINIMAL_SEGMENT_SIZE = 20;

    private RectilinearEdgeUtil() {
        // private constructor.
    }

    /**
     * Centers the edge ends of the given rectilinear point list.
     * 
     * @param line
     *            the point list (in absolute coordinates). We assume this list is rectilinear.
     * @param srcRefPoint
     *            the source anchor absolute location. Must be centered if the centering style is BOTH or SOURCE.
     * @param tgtRefPoint
     *            the target anchor absolute location. Must be centered if the centering style is BOTH or TARGET.
     * @param centeringStyle
     *            the centering style.
     */
    public static void centerEdgeEnds(PointList line, Point srcRefPoint, Point tgtRefPoint, CenteringStyle centeringStyle) {
        if (CenteringStyle.BOTH == centeringStyle || CenteringStyle.SOURCE == centeringStyle) {

            addPointsIfNeeded(line, srcRefPoint, true);
            alignSegmentTowardAnchor(line, srcRefPoint, true);
        }
        if (CenteringStyle.BOTH == centeringStyle || CenteringStyle.TARGET == centeringStyle) {

            addPointsIfNeeded(line, tgtRefPoint, false);
            alignSegmentTowardAnchor(line, tgtRefPoint, false);
        }
        removeRedundantPoints(line);
    }

    /**
     * Adds transitional bendpoints in the cases where centering one segment changes the other segment orientation.
     * 
     * @param line
     *            the rectilinear edge point list.
     * @param absoluteAnchorCoordinates
     *            the anchor absolute coordinates where the segment should be aligned
     * @param isFirst
     *            true if it concerns the source or false for the target.
     */
    private static void addPointsIfNeeded(PointList line, Point absoluteAnchorCoordinates, boolean isFirst) {
        if (line.size() == 2) {
            addPointForStraightEdge(line);
        } else if (line.size() == 3 && isOppositeEndWillBeAffected(line, absoluteAnchorCoordinates, isFirst)) {
            addPointForLEdge(line, isFirst);
        }
    }

    /**
     * For straight edges, we need to had 2 bendpoints at the middle of the line to center only one segment at time.
     * 
     * @param line
     *            the current 2 points list. This point list will be modified by adding two points in the middle.
     */
    private static void addPointForStraightEdge(PointList line) {
        Point pointToInsert = null;
        int segmentOrientation = line.getFirstPoint().x == line.getLastPoint().x ? PositionConstants.VERTICAL : PositionConstants.HORIZONTAL;
        if (segmentOrientation == PositionConstants.VERTICAL) {
            double xValue = line.getFirstPoint().preciseX();
            double delta = (line.getFirstPoint().preciseY() - line.getLastPoint().preciseY()) / 2;
            pointToInsert = new PrecisionPoint(xValue, line.getFirstPoint().preciseY() - delta);
        } else {
            double yValue = line.getFirstPoint().preciseY();
            double delta = (line.getFirstPoint().preciseX() - line.getLastPoint().preciseX()) / 2;
            pointToInsert = new PrecisionPoint(line.getFirstPoint().preciseX() - delta, yValue);
        }
        line.insertPoint(pointToInsert, 1);
        line.insertPoint(pointToInsert.getCopy(), 1);

    }

    /**
     * In the case of an edge with 3 bendpoints (the edge forms a "L"), shifting one segment could change the other
     * segment connection point.
     * 
     * @param newLine
     *            the edge point list.
     * @param absoluteAnchorCoordinates
     *            the coordinate of the anchor we want to align the segment.
     * @param isFirst
     *            true if want to move the source segment. Otherwise false.
     * @return True if the perpendicular segment will change of shape face.
     */
    private static boolean isOppositeEndWillBeAffected(PointList line, Point absoluteAnchorCoordinates, boolean isFirst) {
        LineSeg oppositeSegment = getSegment(line, !isFirst);
        int origTermDelta = 0;
        int origNewLocDelta = 0;

        if (oppositeSegment.isVertical()) {
            origTermDelta = oppositeSegment.getOrigin().y - oppositeSegment.getTerminus().y;
            origNewLocDelta = oppositeSegment.getOrigin().y - absoluteAnchorCoordinates.y;
        } else {
            origTermDelta = oppositeSegment.getOrigin().x - oppositeSegment.getTerminus().x;
            origNewLocDelta = oppositeSegment.getOrigin().x - absoluteAnchorCoordinates.x;

        }

        return (origTermDelta > 0) != (origNewLocDelta > 0);

    }

    /**
     * Adds two bendpoints on the segment we want to align with the anchor. That avoids to break the other segment when
     * centering this one. (in case of edge with 3 bendpoints that means two perpendicular segments).
     * 
     * @param line
     *            the point list to add new bendpoints.
     * @param isFirst
     *            true if the first segment is concerned (from the source), false for the second one (the last from the
     *            target).
     */
    private static void addPointForLEdge(PointList line, boolean isFirst) {
        LineSeg segment = getSegment(line, isFirst);
        Point origin = segment.getOrigin();
        Point terminus = segment.getTerminus();
        Point newPoint = origin.getCopy();
        if (segment.isHorizontal()) {
            int newPointX;
            if (origin.x > terminus.x) {
                newPointX = origin.x - MINIMAL_SEGMENT_SIZE;
            } else {
                newPointX = origin.x + MINIMAL_SEGMENT_SIZE;
            }
            newPoint.setX(newPointX);

        } else {

            int newPointY;
            if (origin.y > terminus.y) {
                newPointY = origin.y - MINIMAL_SEGMENT_SIZE;
            } else {
                newPointY = origin.y + MINIMAL_SEGMENT_SIZE;
            }
            newPoint.setY(newPointY);
        }
        if (isFirst) {
            line.insertPoint(newPoint, 1);
            line.insertPoint(newPoint.getCopy(), 1);
        } else {

            line.insertPoint(newPoint, line.size() - 1);
            line.insertPoint(newPoint.getCopy(), line.size() - 1);
        }

    }

    /**
     * Aligns the given segment toward anchor.
     * 
     * @param line
     *            the edge point list.
     * @param absoluteAnchorCoordinates
     *            the anchor coordinates.
     * @param isFirst
     *            true to align the first segment (from the source), false to align the last one (from the target).
     */
    private static void alignSegmentTowardAnchor(PointList line, Point absoluteAnchorCoordinates, boolean isFirst) {
        LineSeg segment = getSegment(line, isFirst);
        Point origin = segment.getOrigin();
        Point terminus = segment.getTerminus();
        if (segment.isVertical()) {
            origin.setX(absoluteAnchorCoordinates.x());
            terminus.setX(absoluteAnchorCoordinates.x());
        } else {
            origin.setY(absoluteAnchorCoordinates.y());
            terminus.setY(absoluteAnchorCoordinates.y());
        }
        if (isFirst) {
            line.setPoint(origin, 0);
            line.setPoint(terminus, 1);
        } else {
            line.setPoint(origin, line.size() - 1);
            line.setPoint(terminus, line.size() - 2);
        }
    }

    /**
     * Gives the first or the last edge segment from the line.
     * 
     * @param line
     *            the edge point list.
     * @param isFirst
     *            true to get the first segment, false to get the last one.
     * @return the segment. The origin point is always the one connected to the shape. For the first segment, the origin
     *         is the first point, for the last segment, the origin is the last point.
     */
    private static LineSeg getSegment(PointList line, boolean isFirst) {
        if (isFirst) {
            return new LineSeg(line.getFirstPoint(), line.getPoint(1));
        } else {
            return new LineSeg(line.getLastPoint(), line.getPoint(line.size() - 2));
        }
    }

    /**
     * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter. removeRedundantPoints(PointList)
     * Iterates through points of a polyline and does the following: if 3 points lie on the same line the middle point
     * is removed
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

    /**
     * Compute the point list (in absolute coordinates) composing rectilinear edge. Points are aligned with center of
     * source or target node side from which the edge is connected.
     * 
     * @param srcAbsoluteBounds
     *            figure bounds of the edge source
     * @param tgtAbsoluteBounds
     *            figure bounds of the edge target
     * @param srcPoint
     *            source point of the rectilinear edge
     * @param tgtPoint
     *            target point of the rectilinear edge
     * @return the point list (in absolute coordinates) composing rectilinear edge.
     */
    public static PointList computeRectilinearBendpoints(Rectangle srcAbsoluteBounds, Rectangle tgtAbsoluteBounds, Point srcPoint, Point tgtPoint) {
        PointList pointList = new PointList();
        pointList.addPoint(srcPoint);
        // check if source and target side of connection is horizontal
        LineSeg srcSeg = getBoundSideIntersection(srcPoint, srcAbsoluteBounds);
        LineSeg tgtSeg = getBoundSideIntersection(tgtPoint, tgtAbsoluteBounds);
        if (srcSeg != null && tgtSeg != null) {
            if (srcSeg.isHorizontal()) {
                if (srcPoint.x == tgtPoint.x) {
                    // no intermediate point to add
                } else if (tgtSeg.isHorizontal()) {
                    // edge is composed of 3 segments (4 points)
                    int middleY = (srcPoint.y + tgtPoint.y) / 2;
                    pointList.addPoint(new Point(srcPoint.x, middleY));
                    pointList.addPoint(new Point(tgtPoint.x, middleY));
                } else {
                    // edge is composed of 2 segments (3 points)
                    pointList.addPoint(new Point(srcPoint.x, tgtPoint.y));
                }
            } else {
                if (srcPoint.y == tgtPoint.y) {
                    // no intermediate point to add
                } else if (tgtSeg.isVertical()) {
                    // edge is composed of 3 segments (4 points)
                    int middleX = (srcPoint.x + tgtPoint.x) / 2;
                    pointList.addPoint(new Point(middleX, srcPoint.y));
                    pointList.addPoint(new Point(middleX, tgtPoint.y));
                } else {
                    // edge is composed of 2 segments (3 points)
                    pointList.addPoint(new Point(tgtPoint.x, srcPoint.y));
                }
            }
        }
        pointList.addPoint(tgtPoint);
        return pointList;
    }

    /**
     * Compute the point list (in absolute coordinates) composing rectilinear edge with the same source and target. This
     * method will compute a default route from the side where the connection source was connected and the target will
     * be set on the next side in a clockwise order.
     * 
     * @param srcAndTgtAbsoluteBounds
     *            figure bounds of the edge source and target (as they are the same)
     * @param editPart
     *            {@link ConnectionNodeEditPart} corresponding to the rectilinear edge to recompute
     * @return the point list (in absolute coordinates) composing rectilinear edge.
     */
    public static PointList computeRectilinearBendpointsSameSourceAndTarget(Rectangle srcAndTgtAbsoluteBounds, ConnectionNodeEditPart editPart) {
        // Look for the first bendpoint location
        Bendpoints bendpoints = ((Edge) editPart.getModel()).getBendpoints();
        RelativeBendpoints relativeBendpoints = (RelativeBendpoints) bendpoints;
        ConnectionEditPartQuery connectionEditPartQuery = new ConnectionEditPartQuery(editPart);
        PrecisionPoint sourceAnchorsAbsoluteLocation = connectionEditPartQuery.getCenteredAnchorsAbsoluteLocation(srcAndTgtAbsoluteBounds);
        PrecisionPoint targetAnchorsAbsoluteLocation = connectionEditPartQuery.getCenteredAnchorsAbsoluteLocation(srcAndTgtAbsoluteBounds);
        PointList initialPointList = new ConnectionQuery((Connection) editPart.getFigure()).getAbsolutePointList(relativeBendpoints, sourceAnchorsAbsoluteLocation, targetAnchorsAbsoluteLocation);
        // Check on which side this first bendpoint is located
        EditPartQuery editPartQuery = new EditPartQuery((IGraphicalEditPart) editPart.getSource());
        int sourceSide = editPartQuery.getSideOfLocation(initialPointList.getFirstPoint());
        // If the coordinates of the edge bendpoints are broken, we choose the source Side as east as a default value
        if (sourceSide == PositionConstants.NONE) {
            sourceSide = PositionConstants.EAST;
        }
        // Compute the location of the center of this side to use it as the new location of the first bendpoint
        Option<Point> srcConnectionBendpoint = Options.newSome(editPartQuery.getCenterOfSide(sourceSide));
        Point srcPoint = srcConnectionBendpoint.get();
        return computeRectilinearBendpointsSameSourceAndTarget(srcAndTgtAbsoluteBounds, srcPoint, sourceSide);
    }

    /**
     * Compute the point list (in absolute coordinates) composing rectilinear edge with the same source and target. This
     * method will compute a default route from the side where the connection source was connected and the target will
     * be set on the next side in a clockwise order.
     * 
     * @param srcAndTgtAbsoluteBounds
     *            figure bounds of the edge source and target (as they are the same)
     * @param srcPoint
     *            location of the first bendpoint (source)
     * @param sourceSide
     *            side on the parent where the first bendpoint is located
     * @return the point list (in absolute coordinates) composing rectilinear edge.
     */
    public static PointList computeRectilinearBendpointsSameSourceAndTarget(Rectangle srcAndTgtAbsoluteBounds, Point srcPoint, int sourceSide) {
        PointList pointList = new PointList();
        pointList.addPoint(srcPoint);
        int offset = LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.height;
        if (PositionConstants.NORTH == sourceSide) {
            Point newSecondPoint = new Point(srcPoint.x, srcAndTgtAbsoluteBounds.getTop().y - offset);
            pointList.addPoint(newSecondPoint);
            Point newThirdPoint = new Point(srcAndTgtAbsoluteBounds.getRight().x + offset, srcAndTgtAbsoluteBounds.getTop().y - offset);
            pointList.addPoint(newThirdPoint);
            Point newFourthPoint = new Point(srcAndTgtAbsoluteBounds.getRight().x + offset, srcAndTgtAbsoluteBounds.getRight().y);
            pointList.addPoint(newFourthPoint);
            pointList.addPoint(srcAndTgtAbsoluteBounds.getRight());
        } else if (PositionConstants.EAST == sourceSide) {
            Point newSecondPoint = new Point(srcAndTgtAbsoluteBounds.getRight().x + offset, srcPoint.y);
            pointList.addPoint(newSecondPoint);
            Point newThirdPoint = new Point(srcAndTgtAbsoluteBounds.getRight().x + offset, srcAndTgtAbsoluteBounds.getBottom().y + offset);
            pointList.addPoint(newThirdPoint);
            Point newFourthPoint = new Point(srcAndTgtAbsoluteBounds.getBottom().x, srcAndTgtAbsoluteBounds.getBottom().y + offset);
            pointList.addPoint(newFourthPoint);
            pointList.addPoint(srcAndTgtAbsoluteBounds.getBottom());
        } else if (PositionConstants.SOUTH == sourceSide) {
            Point newSecondPoint = new Point(srcPoint.x, srcAndTgtAbsoluteBounds.getBottom().y + offset);
            pointList.addPoint(newSecondPoint);
            Point newThirdPoint = new Point(srcAndTgtAbsoluteBounds.getLeft().x - offset, srcAndTgtAbsoluteBounds.getBottom().y + offset);
            pointList.addPoint(newThirdPoint);
            Point newFourthPoint = new Point(srcAndTgtAbsoluteBounds.getLeft().x - offset, srcAndTgtAbsoluteBounds.getLeft().y);
            pointList.addPoint(newFourthPoint);
            pointList.addPoint(srcAndTgtAbsoluteBounds.getLeft());
        } else if (PositionConstants.WEST == sourceSide) {
            Point newSecondPoint = new Point(srcAndTgtAbsoluteBounds.getLeft().x - offset, srcPoint.y);
            pointList.addPoint(newSecondPoint);
            Point newThirdPoint = new Point(srcAndTgtAbsoluteBounds.getLeft().x - offset, srcAndTgtAbsoluteBounds.getTop().y - offset);
            pointList.addPoint(newThirdPoint);
            Point newFourthPoint = new Point(srcAndTgtAbsoluteBounds.getTop().x, srcAndTgtAbsoluteBounds.getTop().y - offset);
            pointList.addPoint(newFourthPoint);
            pointList.addPoint(srcAndTgtAbsoluteBounds.getTop());
        }
        return pointList;
    }

    /**
     * Align point of figure's bound toward anchor.
     * 
     * @param figureBounds
     *            bounds of figure which contains point to align
     * @param point
     *            the point to align with anchor
     * @param absoluteAnchorCoordinates
     *            the anchor coordinates.
     */
    public static void alignBoundPointTowardAnchor(Rectangle figureBounds, Point point, Point absoluteAnchorCoordinates) {
        LineSeg segment = getBoundSideIntersection(point, figureBounds);
        if (segment != null) {
            if (segment.isHorizontal()) {
                point.setX(absoluteAnchorCoordinates.x);
            } else {
                point.setY(absoluteAnchorCoordinates.y);
            }
        }
    }

    /**
     * Get the segment of bounds figure which include a given point.
     * 
     * @param boundPoint
     *            point of the figure bounds
     * @param bounds
     *            bounds of the figure
     * @return the segment of bounds figure which include a given point.
     */
    private static LineSeg getBoundSideIntersection(Point boundPoint, Rectangle bounds) {
        LineSeg seg = null;
        if (boundPoint != null && bounds != null) {
            Point topLeftCorner = new Point(bounds.x, bounds.y);
            Point topRightCorner = new Point(bounds.x + bounds.width, bounds.y);
            Point btmLeftCorner = new Point(bounds.x, bounds.y + bounds.height);
            Point btmRightCorner = new Point(bounds.x + bounds.width, bounds.y + bounds.height);
            if (bounds.x == boundPoint.x) {
                seg = new LineSeg(topLeftCorner, btmLeftCorner);
            } else if (bounds.x + bounds.width == boundPoint.x) {
                seg = new LineSeg(topRightCorner, btmRightCorner);
            } else if (bounds.y == boundPoint.y) {
                seg = new LineSeg(topLeftCorner, topRightCorner);
            } else if (bounds.y + bounds.height == boundPoint.y) {
                seg = new LineSeg(btmLeftCorner, btmRightCorner);
            }
        }
        return seg;
    }

    /**
     * Goes through line segments of a polyline and makes strict straight segments from nearly straight segments. Then
     * remove also unnecessary points.
     * 
     * @param line
     *            polyline to straight
     * @param tolerance
     *            tolerance value specifying nearly straight lines.
     * @return the line made straight with only necessary points
     */
    public static PointList normalizeToStraightLineTolerance(PointList line, int tolerance) {
        if (line == null || line.size() < 3) {
            // line is too short to be straight
            return line;
        }
        // straight edge
        for (int i = 0; i < line.size() - 1; i++) {
            Point pt1 = line.getPoint(i);
            Point pt2 = line.getPoint(i + 1);
            if (Math.abs(pt1.y - pt2.y) < tolerance) {
                line.setPoint(new Point(pt2.x, pt1.y), i + 1);
            } else if (Math.abs(pt1.x - pt2.x) < tolerance) {
                line.setPoint(new Point(pt1.x, pt2.y), i + 1);
            }
        }
        // delete unnecessary points
        PointList newLine = new PointList();
        newLine.addPoint(line.getPoint(0));
        for (int i = 1; i < line.size() - 1; i++) {
            Point pt1 = line.getPoint(i - 1);
            Point pt2 = line.getPoint(i);
            Point pt3 = line.getPoint(i + 1);
            if ((pt1.x == pt2.x && pt3.x == pt2.x) || (pt1.y == pt2.y && pt3.y == pt2.y)) {
                // three point are aligned horizontally or vertically
                // point is not necessary
            } else {
                newLine.addPoint(pt2);
            }
        }
        newLine.addPoint(line.getPoint(line.size() - 1));
        return newLine;
    }
}
