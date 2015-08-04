/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts.locator;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Vector;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.LabelHelper;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;

/**
 * Utility class used to compute the position of a label according to its edge
 * move (old points and new points list).
 *
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EdgeLabelsComputationUtil {

    private final static double DISTANCE_TOLERANCE = 0.001;

    /** BendPoint list before the edge modification. */
    private PointList oldBendPointList;

    /** BendPoint list corresponding to the edge modification. */
    private PointList newBendPointList;

    /** The routing status of edge on which the label is. */
    private boolean isEdgeWithObliqueRoutingStyle;

    /** The old offset of the label */
    private Point oldLabelOffset;

    /**
     * The keyPoint of the label (
     * {@link org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart#getKeyPoint()}
     * ).
     */
    private Integer keyPoint;

    private List<LineSeg> oldEdgeSegments;

    private List<LineSeg> newEdgeSegments;

    /**
     * Default constructor.
     *
     * @param oldBendPointList
     *            Bendpoint list before the edge modification
     * @param newBendPointList
     *            Bendpoint list after the edge modification
     * @param isEdgeWithObliqueRoutingStyle
     *            status of the edge from which to get the previous position of
     *            the bendpoints and from which to get the three labels
     * @param oldLabelOffset
     *            The old offset.
     * @param keyPoint
     *            The keyPoint of the label (
     *            {@link org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart#getKeyPoint()}
     *            )
     */
    @SuppressWarnings("unchecked")
    public EdgeLabelsComputationUtil(PointList oldBendPointList, PointList newBendPointList, boolean isEdgeWithObliqueRoutingStyle, Point oldLabelOffset, Integer keyPoint) {
        this.isEdgeWithObliqueRoutingStyle = isEdgeWithObliqueRoutingStyle;

        this.oldBendPointList = oldBendPointList;
        Preconditions.checkState(newBendPointList.size() > 0);
        this.newBendPointList = newBendPointList;
        this.oldLabelOffset = oldLabelOffset;
        this.keyPoint = keyPoint;

        // compute lineSegments from bendPoints
        oldEdgeSegments = PointListUtilities.getLineSegments(oldBendPointList);
        newEdgeSegments = PointListUtilities.getLineSegments(newBendPointList);
    }

    /**
     * Calculate the new GMF label offset: the label offset defines the position
     * of the label compared to labelAnchor point. <br>
     * The new Label offset is computed taking into account:<br>
     * <ul>
     * <li>the label anchor point move (start, center or end)</li>
     * <li>the orientation of the segment owning the label anchor. Indeed, the
     * label offset is displayed by draw2D relatively to the direction of the
     * edge segment including the anchor of the label</li>
     * <li>the expected move of the label according to the functional
     * specification</li>
     * </ul>
     * .
     *
     * @return the new offset of the label
     */
    public Point calculateGMFLabelOffset() {
        if (areBendpointsIdentical() && areSegmentsValid()) {
            return oldLabelOffset;
        } else {
            int anchorPointRatio = LabelHelper2.getLocation(keyPoint);
            Point oldAnchorPoint = PointListUtilities.calculatePointRelativeToLine(oldBendPointList, 0, anchorPointRatio, true);
            Point oldLabelCenter = LabelHelper2.relativeCenterCoordinateFromOffset(oldBendPointList, oldAnchorPoint, oldLabelOffset);

            Point newAnchorPoint = PointListUtilities.calculatePointRelativeToLine(newBendPointList, 0, anchorPointRatio, true);

            Point newLabelCenter = calculateNewCenterLocation(oldLabelCenter, LabelHelper2.getStandardLabelCenterLocation(newBendPointList, keyPoint));
            return LabelHelper2.offsetFromRelativeCoordinate(newLabelCenter, newBendPointList, newAnchorPoint);
        }
    }

    /**
     * Check if all segments of new and old points are valid (no segment with
     * same origin and terminus).
     *
     * @return true if segments are valid, false otherwise.
     */
    private boolean areSegmentsValid() {
        boolean areSegmentsValid = true;
        for (LineSeg lineSeg : newEdgeSegments) {
            if (lineSeg.getOrigin().equals(lineSeg.getTerminus())) {
                areSegmentsValid = false;
                break;
            }
        }

        if (areSegmentsValid) {
            for (LineSeg lineSeg : oldEdgeSegments) {
                if (lineSeg.getOrigin().equals(lineSeg.getTerminus())) {
                    areSegmentsValid = false;
                    break;
                }
            }
        }
        return areSegmentsValid;
    }

    /**
     * Check if the old points and the new one are the same.
     *
     * @return true if the old points are the same as the new, false otherwise.
     */
    private boolean areBendpointsIdentical() {
        boolean areBendpointsIdentical = true;
        if (newBendPointList.size() == oldBendPointList.size()) {
            for (int i = 0; i < newBendPointList.size(); i++) {
                Point newPoint = newBendPointList.getPoint(i);
                Point oldPoint = oldBendPointList.getPoint(i);
                if (!newPoint.equals(oldPoint)) {
                    areBendpointsIdentical = false;
                    break;
                }
            }
        } else {
            areBendpointsIdentical = false;
        }

        return areBendpointsIdentical;
    }

    /**
     * Calculate the new center location of the label according to functional
     * specification.
     *
     * @param oldCenterLabel
     *            The old center location of the label.
     * @param newDefaultLocation
     *            The standard center location according to the label keyPoint (
     *            {@link org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart#getKeyPoint()}
     *            and the default snap back position (
     *            {@link LabelEditPart#getSnapBackPosition(String)}.
     * @return
     */
    private Point calculateNewCenterLocation(Point oldCenterLabel, Point newDefaultLocation) {
        Vector fromOldToNewCenterVector = null;

        // Step 1 : Calculate old reference point (the nearest point on the edge
        // from the center of the label).
        LineSeg oldNearestSeg = PointListUtilities.getNearestSegment(oldEdgeSegments, oldCenterLabel.x, oldCenterLabel.y);
        Point oldNearestPoint = oldNearestSeg.perpIntersect(oldCenterLabel.x, oldCenterLabel.y);

        // Step 2 : Is there a new segment and an old segment on the same line?
        // Case of segment increased or decreased (and eventually inverted)
        Option<Vector> fromOldToNewRefPoint = getVectorFromOldToNewForSegmentsOnSameLine(oldNearestSeg, oldNearestPoint, oldCenterLabel);
        if (fromOldToNewRefPoint.some()) {
            // In this case the vector for the reference point is the same than
            // for the label center.
            fromOldToNewCenterVector = fromOldToNewRefPoint.get();
        } else { // No identical segment line has been found
            // RECTILINEAR and TREE routing
            if (!isEdgeWithObliqueRoutingStyle) {
                // Get projection of oldNearestPoint on newSegments along
                // oldRefVector
                LineSeg oldRefVectorIntoSegment = null;
                if (oldCenterLabel.equals(oldNearestPoint)) {
                    // Get a segment perpendicular to oldRefSegment going
                    // through oldNearestPoint
                    oldRefVectorIntoSegment = new LineSeg(oldNearestPoint, new PrecisionPoint(oldNearestPoint.x + (oldNearestSeg.getOrigin().y - oldNearestSeg.getTerminus().y),
                            oldNearestPoint.y - (oldNearestSeg.getOrigin().x - oldNearestSeg.getTerminus().x)));
                } else {
                    oldRefVectorIntoSegment = new LineSeg(oldCenterLabel, oldNearestPoint);
                }

                // Is there a new segment at the same index as old segment and
                // with same axis? Case of rectilinear segment move.
                fromOldToNewCenterVector = getVectorForSegmentMoveCase(oldNearestSeg, oldNearestPoint, oldCenterLabel);
                if (fromOldToNewCenterVector == null) {
                    for (LineSeg lineSeg : newEdgeSegments) {
                        PointList linesIntersections = oldRefVectorIntoSegment.getLinesIntersections(lineSeg);
                        // intersection should be, at more, one point
                        if (linesIntersections.size() == 1 && lineSeg.distanceToPoint(linesIntersections.getPoint(0).x, linesIntersections.getPoint(0).y) <= Math.sqrt(2)) {
                            Vector tempLabelMove = new Vector(linesIntersections.getPoint(0).x - oldNearestPoint.x, linesIntersections.getPoint(0).y - oldNearestPoint.y);
                            if (fromOldToNewCenterVector == null || tempLabelMove.getLength() < fromOldToNewCenterVector.getLength()) {
                                fromOldToNewCenterVector = tempLabelMove;
                            }
                        }
                    }
                    // Compare the minimalLabelMove with the default location.
                    // If the default location is nearer reset the labelMove.
                    Vector fromOldNearestPointToStandardLocation = new Vector(newDefaultLocation.x - oldNearestPoint.x, newDefaultLocation.y - oldNearestPoint.y);
                    if (fromOldToNewCenterVector == null || fromOldNearestPointToStandardLocation.getLength() < fromOldToNewCenterVector.getLength()) {
                        fromOldToNewCenterVector = null;
                    }
                }
            } else if (newEdgeSegments.size() == oldEdgeSegments.size()) {
                // The newNearestSegment as the same index in
                // newEdgeSegments than oldNearestSegment in oldEdgeSegments
                LineSeg newRefSeg = newEdgeSegments.get(oldEdgeSegments.indexOf(oldNearestSeg));
                // Keep ratio on segment for newRefPoint
                double oldRatio = oldNearestSeg.projection(oldCenterLabel.x, oldCenterLabel.y);
                Point newRefPoint = new PrecisionPoint(newRefSeg.getOrigin().x + oldRatio * (newRefSeg.getTerminus().x - newRefSeg.getOrigin().x),
                        newRefSeg.getOrigin().y + oldRatio * (newRefSeg.getTerminus().y - newRefSeg.getOrigin().y));
                fromOldToNewCenterVector = new Vector(newRefPoint.x - oldNearestPoint.x, newRefPoint.y - oldNearestPoint.y);

            }
        }
        if (fromOldToNewCenterVector == null) {
            return newDefaultLocation;
        } else {
            return oldCenterLabel.getTranslated(fromOldToNewCenterVector.x, fromOldToNewCenterVector.y);
        }
    }

    /**
     * Check if we are in case of a rectilinear segment move: there is a new
     * segment at the same index as old nearest segment and with the same axis.
     * Return the corresponding vector in this case, null otherwise.
     *
     * @param oldNearestSeg
     *            The segment that is the nearest from the center of the label
     *            in the old points list.
     * @return the corresponding vector in case of rectilinear segment move,
     *         null otherwise.
     */
    private Vector getVectorForSegmentMoveCase(LineSeg oldNearestSeg, Point oldNearestPoint, Point oldCenterLabel) {
        Vector fromOldToNewCenterVector = null;
        if (newEdgeSegments.size() == oldEdgeSegments.size()) {
            int index = oldEdgeSegments.indexOf(oldNearestSeg);
            LineSeg newNearestSegment = newEdgeSegments.get(index);
            if (oldNearestSeg.isHorizontal() == newNearestSegment.isHorizontal()) {
                Vector oldVector = new Vector(oldNearestSeg.getTerminus().x - oldNearestSeg.getOrigin().x, oldNearestSeg.getTerminus().y - oldNearestSeg.getOrigin().y);
                Vector newVector = new Vector(newNearestSegment.getTerminus().x - newNearestSegment.getOrigin().x, newNearestSegment.getTerminus().y - newNearestSegment.getOrigin().y);
                fromOldToNewCenterVector = applyOldRatioOnNewSegment(oldNearestSeg, oldNearestPoint, oldCenterLabel, newNearestSegment, oldVector.getAngle(newVector) == 180, false);
            }
        }
        return fromOldToNewCenterVector;
    }

    private Option<Vector> getVectorFromOldToNewForSegmentsOnSameLine(LineSeg oldRefSeg, Point oldRefPoint, Point oldCenterLabel) {
        Option<Vector> result = Options.newNone();
        LineSeg newSegmentOnSameLineWithSameDirection = null;
        LineSeg newSegmentOnSameLineWithOppositeDirection = null;
        Vector oldRefVector = new Vector(oldRefSeg.getTerminus().x - oldRefSeg.getOrigin().x, oldRefSeg.getTerminus().y - oldRefSeg.getOrigin().y);
        for (LineSeg newSeg : newEdgeSegments) {
            if (newSeg.length() != 0) {
                Vector newSegVector = new Vector(newSeg.getTerminus().x - newSeg.getOrigin().x, newSeg.getTerminus().y - newSeg.getOrigin().y);
                double angle = oldRefVector.getAngle(newSegVector);
                if (angle == 0 || angle == 180) {
                    double distToInfiniteLine = java.awt.geom.Line2D.ptLineDist(newSeg.getOrigin().x, newSeg.getOrigin().y, newSeg.getTerminus().x, newSeg.getTerminus().y, oldRefSeg.getOrigin().x,
                            oldRefSeg.getOrigin().y);
                    if (distToInfiniteLine < DISTANCE_TOLERANCE) {
                        if (angle == 180) {
                            newSegmentOnSameLineWithOppositeDirection = newSeg;
                            // Continue to search a potential segment in the
                            // same
                            // direction.
                        } else {
                            newSegmentOnSameLineWithSameDirection = newSeg;
                            break;
                        }
                    }
                }
            }
        }

        LineSeg newRefSeg = newSegmentOnSameLineWithSameDirection;
        if (newRefSeg == null) {
            newRefSeg = newSegmentOnSameLineWithOppositeDirection;
        }

        if (newRefSeg != null) {
            result = Options.newSome(applyOldRatioOnNewSegment(oldRefSeg, oldRefPoint, oldCenterLabel, newRefSeg, newSegmentOnSameLineWithOppositeDirection != null, true));
        }
        return result;
    }

    /**
     * @param oldRefSeg
     * @param oldRefPoint
     * @param oldCenterLabel
     * @param newSegmentOnSameLineWithOppositeDirection
     * @param newRefSeg
     * @return
     */
    private Vector applyOldRatioOnNewSegment(LineSeg oldRefSeg, Point oldRefPoint, Point oldCenterLabel, LineSeg newRefSeg, boolean oppositeDirection, boolean sameLine) {
        Vector result;
        double newRatio = newRefSeg.projection(oldCenterLabel.x, oldCenterLabel.y);
        if (sameLine && newRatio >= 0 && newRatio <= 1) {
            // If the orthogonal projection is inside segment (between 0 and
            // 1), the reference point does not move.
            result = new Vector(0, 0);
        } else {
            Point newRefPoint;
            double oldRatio = oldRefSeg.projection(oldCenterLabel.x, oldCenterLabel.y);
            if (!oppositeDirection) {
                newRefPoint = new PrecisionPoint(newRefSeg.getOrigin().x + oldRatio * (newRefSeg.getTerminus().x - newRefSeg.getOrigin().x),
                        newRefSeg.getOrigin().y + oldRatio * (newRefSeg.getTerminus().y - newRefSeg.getOrigin().y));
            } else {
                newRefPoint = new PrecisionPoint(newRefSeg.getOrigin().x - oldRatio * (newRefSeg.getOrigin().x - newRefSeg.getTerminus().x),
                        newRefSeg.getOrigin().y - oldRatio * (newRefSeg.getOrigin().y - newRefSeg.getTerminus().y));
            }
            if (!sameLine && newRatio >= 0 && newRatio <= 1) {
                // If the orthogonal projection is inside segment (between 0 and
                // 1), we keep the oldRefPoint one axis
                if (newRefSeg.isHorizontal()) {
                    newRefPoint.setX(oldRefPoint.x);
                } else {
                    newRefPoint.setY(oldRefPoint.y);
                }
            }
            Vector vectorFromOldToNewRefPoint = new Vector(newRefPoint.x - oldRefPoint.x, newRefPoint.y - oldRefPoint.y);
            if (oldRatio >= 0 && oldRatio <= 1) {
                // Keep ratio on segment for newRefPoint (if it was
                // previously inside segment)
                result = vectorFromOldToNewRefPoint;
            } else {
                // If the label is previously outside of the segment, we
                // keep the shortest point (new or old one).
                Point potentialNewCenter = oldCenterLabel.getTranslated(vectorFromOldToNewRefPoint.x, vectorFromOldToNewRefPoint.y);
                if ((newRatio > 1 && newRatio < newRefSeg.projection(potentialNewCenter.x, potentialNewCenter.y))
                        || (newRatio < 0 && newRatio > newRefSeg.projection(potentialNewCenter.x, potentialNewCenter.y))) {
                    result = new Vector(0, 0);
                } else {
                    result = vectorFromOldToNewRefPoint;
                }
            }
        }
        return result;
    }

    /**
     * Logic extracted from
     * {@link LabelHelper#calculatePointRelativeToPointOnLine}. Returns a point
     * located relative to the line by the given offset.
     *
     * @param ptLst
     *            the point
     * @param ptOnLine
     * @param offset
     * @return the relative point given the line angle
     */
    private Point getVectorAccordingToSegmentOrientation(LineSeg segment, Point offset) {
        Point relativeOffset = null;
        if (segment != null) {
            if (segment.isHorizontal()) {
                if (segment.getOrigin().x > segment.getTerminus().x) {
                    relativeOffset = offset.getNegated();
                    return relativeOffset;
                } else {
                    relativeOffset = offset;
                    return relativeOffset;
                }
            } else if (segment.isVertical()) {
                if (segment.getOrigin().y > segment.getTerminus().y) {
                    relativeOffset = offset.getCopy().scale(-1, 1).transpose();
                    return relativeOffset;
                } else {
                    relativeOffset = offset.getCopy().scale(1, -1).transpose();
                    return relativeOffset;
                }
            } else {
                double slope = segment.slope();
                double theta = Math.atan(slope);
                Point normalizedOffset = new Point(offset);
                Point calculatedOffset = new Point();
                if (segment.getOrigin().x > segment.getTerminus().x) {
                    normalizedOffset = offset.getCopy().scale(-1, -1);
                }

                calculatedOffset = new Point(normalizedOffset.x * Math.cos(theta) - normalizedOffset.y * Math.sin(theta), normalizedOffset.x * Math.sin(theta) + normalizedOffset.y * Math.cos(theta));
                relativeOffset = calculatedOffset;
                return relativeOffset;
            }
        }
        return null;
    }

    /**
     * Logic extracted from
     * {@link LabelHelper#normalizeRelativePointToPointOnLine} Calculates the
     * normalized offset from a point on a <code>Connection</code>'s point list
     * to an point.
     *
     * @param ptLst
     * @param ptOnLine
     * @param offset
     * @return the normalized offset
     */
    private static Point normalizeRelativePointToPointOnLine(LineSeg segment, Point offset, Point ptOnLine) {
        Point normalOffset = null;
        if (segment != null) {
            if (segment.isHorizontal()) {
                if (segment.getOrigin().x > segment.getTerminus().x) {
                    normalOffset = offset.getNegated();
                    return normalOffset;
                } else {
                    normalOffset = offset;
                    return normalOffset;
                }
            } else if (segment.isVertical()) {
                if (segment.getOrigin().y < segment.getTerminus().y) {
                    normalOffset = offset.scale(-1, 1).transpose();
                    return normalOffset;
                } else {
                    normalOffset = offset.scale(1, -1).transpose();
                    return normalOffset;
                }
            } else {
                Point p = ptOnLine.getTranslated(offset);
                normalOffset = getOrthogonalDistances(segment, ptOnLine, p);
                return normalOffset;
            }
        }
        return null;

    }

    /**
     * Calculates distances from a <code>Point</code> on a <code>LineSeg</code>
     * to another <code>Point</code>. The sign of the distances indicate
     * direction.
     *
     * @param lineSeg
     * @param ptOnLine
     * @param refPoint
     * @return the distance from <code>Point</code> on a <code>LineSeg</code> to
     *         another <code>Point</code>
     */
    private static Point getOrthogonalDistances(LineSeg lineSeg, Point ptOnLine, Point refPoint) {
        LineSeg parallelSeg = lineSeg.getParallelLineSegThroughPoint(refPoint);
        Point p1 = parallelSeg.perpIntersect(ptOnLine.x, ptOnLine.y);
        double dx = p1.getDistance(refPoint) * ((p1.x > refPoint.x) ? -1 : 1);
        double dy = p1.getDistance(ptOnLine) * ((p1.y < ptOnLine.y) ? -1 : 1);
        Point orth = new Point(dx, dy);
        // Reflection in the y axis
        if (lineSeg.getOrigin().x > lineSeg.getTerminus().x) {
            orth = orth.scale(-1, -1);
        }
        return orth;
    }

    /**
     * Get the signed angle between two segments.
     *
     * @param line1
     * @param line2
     * @return the signed angle in radian
     */
    private double angleBetween2Lines(LineSeg line1, LineSeg line2) {
        if (line1 == null || line2 == null) {
            return 0;
        }
        double angle1 = Math.atan2(line1.getOrigin().y - line1.getTerminus().y, line1.getOrigin().x - line1.getTerminus().x);
        double angle2 = Math.atan2(line2.getOrigin().y - line2.getTerminus().y, line2.getOrigin().x - line2.getTerminus().x);
        return angle1 - angle2;
    }
}
