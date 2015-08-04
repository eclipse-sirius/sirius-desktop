/******************************************************************************
 * Copyright (c) 2005, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts.locator;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.LabelHelper;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * Helper class to convert the label coordinates from an offset value from a
 * keypoint to real draw2d coordinate. Extends (and adapts) {@link LabelHelper}
 * to give access to private or protected methods.
 *
 * @author sshaw
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class LabelHelper2 extends LabelHelper {
    /**
     * Extracted from {@link LabelHelper#relativeCoordinateFromOffset}<BR>
     * Calculates the relative coordinate that is equivalent to the offset from
     * the reference point, that can be used to set the label center location.
     *
     * @param label
     *            the <code>IFigure</code> to calculate the relative coordinate
     *            for
     * @param points
     *            the <code>PointList</code> of the edge that contains the
     *            label. The label offset is relative to this
     *            <code>PointList</code>.
     * @param ref
     *            a <code>Point</code> located on the parent which the offset
     *            value is relative to.
     * @param offset
     *            a <code>Point</code> which represents a value offset from the
     *            <code>ref</code> point oriented based on the nearest line
     *            segment.
     * @return a <code>Point</code> that is the relative coordinate of the label
     *         that can be used to set it's center location.
     */
    static public Point relativeCenterCoordinateFromOffset(PointList points, Point ref, Point offset) {
        return calculatePointRelativeToPointOnLine(points, ref, offset);
    }

    /**
     * Calculates the label offset from the reference point given the label
     * center and a points list.
     *
     * @param labelCenter
     *            the label center for the <code>IFigure</code> to calculate the
     *            offset for
     * @param points
     *            the <code>PointList</code> of the edge that contains the
     *            label. The label offset is relative to this
     *            <code>PointList</code>.
     * @param ref
     *            the <code>Point</code> that is the reference point that the
     *            offset is based on.
     * @return a <code>Point</code> which represents a value offset from the
     *         <code>ref</code> point oriented based on the nearest line
     *         segment.
     */
    static public Point offsetFromRelativeCoordinate(Point labelCenter, PointList points, Point ref) {
        return normalizeRelativePointToPointOnLine(points, ref, new Point(labelCenter.x - ref.x, labelCenter.y - ref.y));
    }

    /**
     * Get the standard center location according to the label keyPoint (
     * {@link org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart#getKeyPoint()}
     * ) and the default snap back position (
     * {@link LabelEditPart#getSnapBackPosition(String)}
     *
     * @param pointsList
     *            The points of the edge of the label.
     * @param keyPoint
     *            The keyPoint of the label (
     *            {@link org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart#getKeyPoint()}
     *            ).
     * @return The center of the label {@link Bounds} if this label is located
     *         by default.
     */
    public static Point getStandardLabelCenterLocation(PointList pointsList, Integer keyPoint) {
        int percentage = getLocation(keyPoint);
        Point newAnchorPoint = PointListUtilities.calculatePointRelativeToLine(pointsList, 0, percentage, true);
        Point snapBackPosition = getSnapBackPosition(keyPoint);
        Point standardLabelCenter = newAnchorPoint.getTranslated(snapBackPosition);
        return standardLabelCenter;
    }

    public static Point getSnapBackPosition(Integer keyPoint) {
        int percentage = getLocation(keyPoint);
        Point snapBackPosition;
        if (LabelViewConstants.SOURCE_LOCATION == percentage) {
            snapBackPosition = LabelEditPart.getSnapBackPosition(SiriusVisualIDRegistry.getType(DEdgeBeginNameEditPart.VISUAL_ID));
        } else if (LabelViewConstants.TARGET_LOCATION == percentage) {
            snapBackPosition = LabelEditPart.getSnapBackPosition(SiriusVisualIDRegistry.getType(DEdgeEndNameEditPart.VISUAL_ID));
        } else {
            snapBackPosition = LabelEditPart.getSnapBackPosition(SiriusVisualIDRegistry.getType(DEdgeNameEditPart.VISUAL_ID));
        }
        return snapBackPosition;
    }

    /**
     * Get the location among {@link LabelViewConstants} constants where to
     * relocate the label figure.
     *
     * @return the location among {@link LabelViewConstants} constants
     */
    public static int getLocation(Integer keyPoint) {
        int location = LabelViewConstants.MIDDLE_LOCATION;
        switch (keyPoint) {
        case ConnectionLocator.SOURCE:
            location = LabelViewConstants.TARGET_LOCATION;
            break;
        case ConnectionLocator.TARGET:
            location = LabelViewConstants.SOURCE_LOCATION;
            break;
        case ConnectionLocator.MIDDLE:
            location = LabelViewConstants.MIDDLE_LOCATION;
            break;
        default:
            location = LabelViewConstants.MIDDLE_LOCATION;
            break;
        }
        return location;
    }

    /**
     * Copied from {@link LabelHelper}. <BR>
     * Calculates the normalized offset from a point on a
     * <code>Connection</code>'s point list to an point.
     *
     * @param ptLst
     * @param ptOnLine
     * @param offset
     * @return the normalized offset
     */
    private static Point normalizeRelativePointToPointOnLine(PointList ptLst, Point ptOnLine, Point offset) {
        // Calculate slope of line
        if (ptLst.size() == 1) {
            // This is a node...
            return offset;
        } else if (ptLst.size() >= 2) {
            // This is a edge...
            int index = PointListUtilities.findNearestLineSegIndexOfPoint(ptLst, ptOnLine);
            LineSeg segment = (LineSeg) PointListUtilities.getLineSegments(ptLst).get(index - 1);
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
        }
        return null;
    }

    /**
     * Copied from {@link LabelHelper}. <BR>
     *
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
}
