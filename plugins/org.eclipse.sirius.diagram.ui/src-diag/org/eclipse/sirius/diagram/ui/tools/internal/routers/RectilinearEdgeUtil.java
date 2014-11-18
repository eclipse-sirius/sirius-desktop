/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation - removeRedundantPoints(PointList) method
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.routers;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.sirius.diagram.description.CenteringStyle;

/**
 * Utility class for rectilinear edges. Handles edge centering.
 * 
 * @author Florian Barbin
 *
 */
public final class RectilinearEdgeUtil {

    /**
     * The minimal distance between an edge connection and the first bendpoint
     * to add (if we need to add bendpoints).
     */
    private static final int MINIMAL_SEGMENT_SIZE = 20;

    private RectilinearEdgeUtil() {
        // private constructor.
    }

    /**
     * Centers the edge ends of the given rectilinear point list.
     * 
     * @param line
     *            the point list (in absolute coordinates). We assume this list
     *            is rectilinear.
     * @param srcRefPoint
     *            the source anchor absolute location. Must be centered if the
     *            centering style is BOTH or SOURCE.
     * @param tgtRefPoint
     *            the target anchor absolute location. Must be centered if the
     *            centering style is BOTH or TARGET.
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
     * Adds transitional bendpoints in the cases where centering one segment
     * changes the other segment orientation.
     * 
     * @param line
     *            the rectilinear edge point list.
     * @param absoluteAnchorCoordinates
     *            the anchor absolute coordinates where the segment should be
     *            aligned
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
     * For straight edges, we need to had 2 bendpoints at the middle of the line
     * to center only one segment at time.
     * 
     * @param line
     *            the current 2 points list. This point list will be modified by
     *            adding two points in the middle.
     */
    private static void addPointForStraightEdge(PointList line) {
        Point pointToInsert = null;
        int segmentOrientation = line.getFirstPoint().x == line.getLastPoint().x ? PositionConstants.VERTICAL : PositionConstants.HORIZONTAL;
        if (segmentOrientation == PositionConstants.VERTICAL) {
            int xValue = line.getFirstPoint().x;
            int delta = (line.getFirstPoint().y - line.getLastPoint().y) / 2;
            pointToInsert = new Point(xValue, line.getFirstPoint().y - delta);
        } else {
            int yValue = line.getFirstPoint().y;
            int delta = (line.getFirstPoint().x - line.getLastPoint().x) / 2;
            pointToInsert = new Point(line.getFirstPoint().x - delta, yValue);
        }
        line.insertPoint(pointToInsert, 1);
        line.insertPoint(pointToInsert.getCopy(), 1);

    }

    /**
     * In the case of an edge with 3 bendpoints (the edge forms a "L"), shifting
     * one segment could change the other segment connection point.
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
     * Adds two bendpoints on the segment we want to align with the anchor. That
     * avoids to break the other segment when centering this one. (in case of
     * edge with 3 bendpoints that means two perpendicular segments).
     * 
     * @param line
     *            the point list to add new bendpoints.
     * @param isFirst
     *            true if the first segment is concerned (from the source),
     *            false for the second one (the last from the target).
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
     *            true to align the first segment (from the source), false to
     *            align the last one (from the target).
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
     * @return the segment. The origin point is always the one connected to the
     *         shape. For the first segment, the origin is the first point, for
     *         the last segment, the origin is the last point.
     */
    private static LineSeg getSegment(PointList line, boolean isFirst) {
        if (isFirst) {
            return new LineSeg(line.getFirstPoint(), line.getPoint(1));
        } else {
            return new LineSeg(line.getLastPoint(), line.getPoint(line.size() - 2));
        }
    }

    /**
     * From
     * org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter.
     * removeRedundantPoints(PointList) Iterates through points of a polyline
     * and does the following: if 3 points lie on the same line the middle point
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
}
