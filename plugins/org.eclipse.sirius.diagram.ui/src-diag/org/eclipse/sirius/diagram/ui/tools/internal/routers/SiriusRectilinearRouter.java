/*******************************************************************************
 * Copyright (c) 2014, 2021 THALES GLOBAL SERVICES.
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

package org.eclipse.sirius.diagram.ui.tools.internal.routers;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.draw2d.ui.figures.OrthogonalConnectionAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.OrthogonalRouterUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;

/**
 * A Sirius specific Rectilinear Router to keep edges centered.
 * 
 * @author Florian Barbin
 *
 */
@SuppressWarnings("restriction")
public class SiriusRectilinearRouter extends RectilinearRouter {

    @Override
    public void routeLine(Connection conn, int nestedRoutingDepth, PointList newLine) {
        repairInvalidPointList(conn, newLine);
        super.routeLine(conn, nestedRoutingDepth, newLine);

        // if the edge is currently reconnected for instance, we do not perform
        // the centering.
        if (!isReorienting(conn)) {
            handleEdgeCentering(conn, newLine);
        }
    }

    /**
     * Repairs the PointList if it is invalid and contains less than 2 points, in which case it is set to two points by
     * default.
     * 
     * @param conn
     *            the connection figure.
     * @param newLine
     *            the current point list.
     */
    private void repairInvalidPointList(Connection conn, PointList newLine) {
        if (newLine.size() < 2) {
            Point anchorPoint = new Point(0, 0);
            Point targetPoint = new Point(0, 0);
            if (conn.getSourceAnchor() != null) {
                anchorPoint = conn.getSourceAnchor().getReferencePoint();
            }
            if (conn.getTargetAnchor() != null) {
                targetPoint = conn.getTargetAnchor().getReferencePoint();
            }
            newLine.removeAllPoints();
            newLine.addPoint(anchorPoint);
            newLine.addPoint(targetPoint);
        }
    }

    /**
     * Handle the edge centering in the case where at least one end is centered.
     * 
     * @param conn
     *            the connection figure.
     * @param newLine
     *            the current routed point list.
     */
    private void handleEdgeCentering(Connection conn, PointList newLine) {
        if (conn instanceof ViewEdgeFigure) {
            if (((ViewEdgeFigure) conn).isSourceCentered() || ((ViewEdgeFigure) conn).isTargetCentered()) {
                Point srcRefPoint = null;
                Point tgtRefPoint = null;

                // if the source is centered, we compute the source figure
                // center since the source anchor could not be centered.
                if (((ViewEdgeFigure) conn).isSourceCentered()) {
                    srcRefPoint = getAnchorOwnerCenter(conn.getSourceAnchor());
                }

                // if the target is centered, we compute the target figure
                // center since the target anchor could not be centered.
                if (((ViewEdgeFigure) conn).isTargetCentered()) {
                    tgtRefPoint = getAnchorOwnerCenter(conn.getTargetAnchor());
                }

                if (srcRefPoint == null) {
                    ConnectionAnchor srcAnchor = conn.getSourceAnchor();
                    srcRefPoint = srcAnchor.getReferencePoint();
                }

                if (tgtRefPoint == null) {
                    ConnectionAnchor tgtAnchor = conn.getTargetAnchor();
                    tgtRefPoint = tgtAnchor.getReferencePoint();
                }
                // We translate the source and target anchor into the connection
                // coordinate system which is absolute.

                conn.translateToRelative(srcRefPoint);
                conn.translateToRelative(tgtRefPoint);

                RectilinearEdgeUtil.centerEdgeEnds(newLine, srcRefPoint, tgtRefPoint, ((ViewEdgeFigure) conn).getCenteringStyle());

            }
        }
    }

    private Point getAnchorOwnerCenter(ConnectionAnchor anchor) {
        Rectangle rBox = anchor.getOwner() instanceof HandleBounds ? new PrecisionRectangle(((HandleBounds) anchor.getOwner()).getHandleBounds())
                : new PrecisionRectangle(anchor.getOwner().getBounds());
        anchor.getOwner().translateToAbsolute(rBox);
        return rBox.getCenter();
    }

    @Override
    protected void resetEndPointsToEdge(Connection conn, PointList line) {
        if (isReorienting(conn)) {
            /*
             * If the connection doesn't have a shape as a source or target we'll let the oblique router to do the work.
             * The connection doesn't need to be rectilinear at this point. There is no support for making a rectilinear
             * connection for which one of the ends is not connected to anything.
             */
            // super.resetEndPointsToEdge(conn, line);
            resetEndPointsToEdgeFromRouterHelper(conn, line);
            return;
        }
        PrecisionRectangle source = sourceBoundsRelativeToConnection(conn);
        PrecisionRectangle target = targetBoundsRelativeToConnection(conn);
        int offSourceDirection = PositionConstants.NONE;
        int offTargetDirection = PositionConstants.NONE;
        int sourceAnchorRelativeLocation = PositionConstants.NONE;
        int targetAnchorRelativeLocation = PositionConstants.NONE;
        if (line.size() == 0) {
            /*
             * If there are no valid bend points, we'll use the oblique connection anchor points and just convert the
             * polyline from oblique to rectilinear.
             */
            // Need to add 2 dumb points to ensure that RouterHelper#resetEndPointsToEdge works
            line.addPoint(new PrecisionPoint(0, 0));
            line.addPoint(new PrecisionPoint(0, 0));
            resetEndPointsToEdgeFromRouterHelper(conn, line);
            sourceAnchorRelativeLocation = getAnchorOffRectangleDirection(line.getFirstPoint(), source);
            targetAnchorRelativeLocation = getAnchorOffRectangleDirection(line.getLastPoint(), target);
            /*
             * We need to find two points offset from the source and target anchors outside the shapes such that when
             * the polyline is converted to rectilinear from oblique we won't have rectilinear line segments alligned
             * with source or target shapes edges.
             */
            Point offStart = line.getFirstPoint();
            Point offEnd = line.getLastPoint();
            Dimension offsetDim = offStart.getDifference(offEnd).scale(0.5);
            offStart.translate(getTranslationValue(sourceAnchorRelativeLocation, Math.abs(offsetDim.width), Math.abs(offsetDim.height)));
            offEnd.translate(getTranslationValue(targetAnchorRelativeLocation, Math.abs(offsetDim.width), Math.abs(offsetDim.height)));
            line.insertPoint(new Point((int) Math.round(offStart.preciseX()), (int) Math.round(offStart.preciseY())), 1);
            line.insertPoint(new Point((int) Math.round(offEnd.preciseX()), (int) Math.round(offEnd.preciseY())), 2);

            offSourceDirection = getOffShapeDirection(sourceAnchorRelativeLocation);
            offTargetDirection = getOffShapeDirection(targetAnchorRelativeLocation);
        } else {
            Point start = line.getFirstPoint();
            Point end = line.getLastPoint();
            if (conn.getSourceAnchor() instanceof OrthogonalConnectionAnchor) {
                line.insertPoint(OrthogonalRouterUtilities.getOrthogonalLineSegToAnchorLoc(conn, conn.getSourceAnchor(), start).getOrigin(), 0);
            } else {
                /*
                 * If anchor is not supporting orthogonal connections we'll use the oblique connection anchors and then
                 * convert it to rectilinear.
                 */
                PrecisionPoint reference = new PrecisionPoint(start);
                conn.getSourceAnchor().getOwner().translateToAbsolute(reference);
                PrecisionPoint anchorLocation = new PrecisionPoint(conn.getSourceAnchor().getLocation(reference));
                conn.translateToRelative(anchorLocation);
                line.insertPoint(anchorLocation, 0);
            }
            if (conn.getTargetAnchor() instanceof OrthogonalConnectionAnchor) {
                line.addPoint(OrthogonalRouterUtilities.getOrthogonalLineSegToAnchorLoc(conn, conn.getTargetAnchor(), end).getOrigin());
            } else {
                /*
                 * If anchor is not supporting orthogonal connections we'll use the oblique connection anchors and then
                 * convert it to rectilinear.
                 */
                PrecisionPoint reference = new PrecisionPoint(end);
                conn.getSourceAnchor().getOwner().translateToAbsolute(reference);
                PrecisionPoint anchorLocation = new PrecisionPoint(conn.getTargetAnchor().getLocation(reference));
                conn.translateToRelative(anchorLocation);
                line.addPoint(anchorLocation);
            }
            sourceAnchorRelativeLocation = getAnchorOffRectangleDirection(line.getFirstPoint(), source);
            offSourceDirection = getOffShapeDirection(sourceAnchorRelativeLocation);
            targetAnchorRelativeLocation = getAnchorOffRectangleDirection(line.getLastPoint(), target);
            offTargetDirection = getOffShapeDirection(targetAnchorRelativeLocation);
        }

        /*
         * Convert the polyline to rectilinear. If the connection is rectilinear already then the connection will remain
         * as it is.
         */
        OrthogonalRouterUtilities.transformToOrthogonalPointList(line, offSourceDirection, offTargetDirection);
        removeRedundantPoints(line);
    }

    private PrecisionRectangle sourceBoundsRelativeToConnection(Connection conn) {
        PrecisionRectangle source = new PrecisionRectangle(conn.getSourceAnchor().getOwner().getBounds());
        conn.getSourceAnchor().getOwner().translateToAbsolute(source);
        conn.translateToRelative(source);
        return source;
    }

    private PrecisionRectangle targetBoundsRelativeToConnection(Connection conn) {
        PrecisionRectangle target = new PrecisionRectangle(conn.getTargetAnchor().getOwner().getBounds());
        conn.getTargetAnchor().getOwner().translateToAbsolute(target);
        conn.translateToRelative(target);
        return target;
    }

    private Dimension getTranslationValue(int position, int xFactorValue, int yFactorValue) {
        Dimension translationDimension = new Dimension();
        if (position == PositionConstants.EAST) {
            translationDimension.width = xFactorValue;
        } else if (position == PositionConstants.SOUTH) {
            translationDimension.height = yFactorValue;
        } else if (position == PositionConstants.WEST) {
            translationDimension.width = -xFactorValue;
        } else if (position == PositionConstants.NORTH) {
            translationDimension.height = -yFactorValue;
        }
        return translationDimension;
    }

    private int getAnchorOffRectangleDirection(Point anchorPoint, Rectangle rect) {
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

    // CHECKSTYLE:OFF
    private int getOffShapeDirection(int anchorRelativeLocation) {
        if (anchorRelativeLocation == PositionConstants.EAST || anchorRelativeLocation == PositionConstants.WEST) {
            return PositionConstants.HORIZONTAL;
        } else if (anchorRelativeLocation == PositionConstants.NORTH || anchorRelativeLocation == PositionConstants.SOUTH) {
            return PositionConstants.VERTICAL;
        }
        return PositionConstants.NONE;
    }

    private boolean removeRedundantPoints(PointList line) {
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

    private void resetEndPointsToEdgeFromRouterHelper(Connection conn, PointList newLine) {
        if (newLine.size() < 2) {
            /*
             * Connection must have at least 2 points in the list: the source and target anchor points. Otherwise it's
             * invalid connection. Invalid connection case: add a dumb point at the start of the list and at the end of
             * the list. The first and the last point in the list are replaced by the new source and target anchor
             * points in this method
             */
            newLine.addPoint(0, 0);
            newLine.insertPoint(new Point(), 0);
        }

        PrecisionPoint sourceAnchorPoint, targetAnchorPoint;
        if (newLine.size() > 2) {
            /*
             * First bend point is the outside reference point for the source anchor. Last bend point is the outside
             * reference point for the target anchor.
             */
            PrecisionPoint sourceReference = new PrecisionPoint(newLine.getPoint(1));
            PrecisionPoint targetReference = new PrecisionPoint(newLine.getPoint(newLine.size() - 2));
            conn.translateToAbsolute(sourceReference);
            conn.translateToAbsolute(targetReference);
            sourceAnchorPoint = getAnchorLocation(conn.getSourceAnchor(), sourceReference);
            targetAnchorPoint = getAnchorLocation(conn.getTargetAnchor(), targetReference);
        } else {
            /*
             * We need to take target anchor reference point as an outside reference point for the source anchor
             * location. The outside reference point for the target anchor would the source anchor location.
             */
            PrecisionPoint sourceReference = getAnchorReference(conn.getTargetAnchor());
            sourceAnchorPoint = getAnchorLocation(conn.getSourceAnchor(), sourceReference);
            targetAnchorPoint = getAnchorLocation(conn.getTargetAnchor(), sourceAnchorPoint);
        }

        conn.translateToRelative(sourceAnchorPoint);
        conn.translateToRelative(targetAnchorPoint);

        newLine.setPoint(new Point((int) Math.round(sourceAnchorPoint.preciseX()), (int) Math.round(sourceAnchorPoint.preciseY())), 0);
        newLine.setPoint(new Point((int) Math.round(targetAnchorPoint.preciseX()), (int) Math.round(targetAnchorPoint.preciseY())), newLine.size() - 1);
    }

    private PrecisionPoint getAnchorLocation(ConnectionAnchor anchor, Point reference) {
        return new PrecisionPoint(anchor.getLocation(reference));
    }

    private PrecisionPoint getAnchorReference(ConnectionAnchor anchor) {
        return new PrecisionPoint(anchor.getReferencePoint());
    }
}
