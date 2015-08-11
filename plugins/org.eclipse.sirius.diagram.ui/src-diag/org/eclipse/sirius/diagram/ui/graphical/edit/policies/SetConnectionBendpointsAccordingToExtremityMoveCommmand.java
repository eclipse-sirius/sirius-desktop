/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Set connection bendpoints according to extremity move (source or target) to
 * keep, as much as possible, the edges aspects when a node (container or not)
 * is moved. A move of a node should move only the closest segment of the linked
 * edges.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SetConnectionBendpointsAccordingToExtremityMoveCommmand extends SetConnectionBendpointsCommand {

    private boolean sourceMove;

    private PrecisionPoint moveDelta;

    /**
     * Default constructor.
     * 
     * @param editingDomain
     *            the editing domain through which model changes are made
     */
    public SetConnectionBendpointsAccordingToExtremityMoveCommmand(TransactionalEditingDomain editingDomain) {
        super(editingDomain);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecute(org.eclipse.core.runtime.IProgressMonitor,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

        if (getEdgeAdaptor() instanceof ConnectionEditPart) {
            ConnectionEditPart connectionEditPart = (ConnectionEditPart) getEdgeAdaptor();
            // Applied zoom on moveDelta, because moveDelta is only element in
            // relative value
            GraphicalHelper.appliedZoomOnRelativePoint(connectionEditPart, moveDelta);
            Connection connection = connectionEditPart.getConnectionFigure();

            Point tempSourceRefPoint = connection.getSourceAnchor().getReferencePoint();
            connection.translateToRelative(tempSourceRefPoint);

            Point tempTargetRefPoint = connection.getTargetAnchor().getReferencePoint();
            connection.translateToRelative(tempTargetRefPoint);

            PointList connectionPointList = connection.getPoints().getCopy();
            adaptPointListAndRefPoints(sourceMove, moveDelta, connectionEditPart, tempSourceRefPoint, tempTargetRefPoint, connectionPointList);

            setNewPointList(connectionPointList, tempSourceRefPoint, tempTargetRefPoint);
            return super.doExecute(monitor, info);
        }
        return Status.OK_STATUS;
    }

    /**
     * Adapt the point list and the source reference point (or target reference
     * point) according to a move of the source (or of the target).
     * 
     * @param sourceMove
     *            true if the source node of edge has been moved, false
     *            otherwise
     * @param moveDelta
     *            the delta of the move of the extremity (source or target)
     * @param connectionEditPart
     *            The connection editPart corresponding to the edge to adapt
     * @param sourceRefPoint
     *            The source reference point to adapt (only adapted for
     *            sourceMove true)
     * @param targetRefPoint
     *            The target reference point to adapt (only adapted for
     *            sourceMove false)
     * @param connectionPointList
     *            The point list to adapt
     */
    public static void adaptPointListAndRefPoints(boolean sourceMove, Point moveDelta, org.eclipse.gef.ConnectionEditPart connectionEditPart, Point sourceRefPoint, Point targetRefPoint,
            PointList connectionPointList) {
        // Get the bounds of the source and target nodes
        PrecisionRectangle sourceBounds = new PrecisionRectangle(GraphicalHelper.getAbsoluteBoundsIn100Percent((IGraphicalEditPart) connectionEditPart.getSource()));
        PrecisionRectangle targetBounds = new PrecisionRectangle(GraphicalHelper.getAbsoluteBoundsIn100Percent((IGraphicalEditPart) connectionEditPart.getTarget()));
        boolean isEdgeWithRectilinearRoutingStyle = new ConnectionEditPartQuery(connectionEditPart).isEdgeWithRectilinearRoutingStyle();
        if (sourceMove) {
            moveFirstSegmentAndRefPointAccordingToSourcetMove(moveDelta, isEdgeWithRectilinearRoutingStyle, sourceBounds, targetBounds, sourceRefPoint, targetRefPoint, connectionPointList);
        } else {
            moveLastSegmentAndRefPointAccordingToTargetMove(moveDelta, isEdgeWithRectilinearRoutingStyle, sourceBounds, targetBounds, sourceRefPoint, targetRefPoint, connectionPointList);
        }
    }

    /**
     * Adapt the point list and the source reference point according to a move
     * of the source.
     * 
     * @param moveDelta
     *            the delta of the move of the extremity (source or target)
     * @param isEdgeWithRectilinearRoutingStyle
     *            True if the edge is rectilinear (one more point is adapted in
     *            this case), false otherwise
     * @param sourceBounds
     *            The bounds of the source node
     * @param targetBounds
     *            The bounds of the target node
     * @param sourceRefPoint
     *            The source reference point to adapt
     * @param targetRefPoint
     *            The target reference point
     * @param connectionPointList
     *            The point list to adapt
     */
    private static void moveFirstSegmentAndRefPointAccordingToSourcetMove(Point moveDelta, boolean isEdgeWithRectilinearRoutingStyle, PrecisionRectangle sourceBounds, PrecisionRectangle targetBounds,
            Point sourceRefPoint, Point targetRefPoint, PointList connectionPointList) {
        // Move reference point
        sourceRefPoint.performTranslate(moveDelta.x, moveDelta.y);
        // Move second point in case of rectilinear router
        if (isEdgeWithRectilinearRoutingStyle) {
            LineSeg firstSegment = new LineSeg(connectionPointList.getPoint(0), connectionPointList.getPoint(1));
            if (firstSegment.isHorizontal()) {
                connectionPointList.setPoint(connectionPointList.getPoint(0).translate(moveDelta.x, moveDelta.y), 0);
                connectionPointList.setPoint(connectionPointList.getPoint(1).translate(0, moveDelta.y), 1);
            } else {
                connectionPointList.setPoint(connectionPointList.getPoint(0).translate(moveDelta.x, moveDelta.y), 0);
                connectionPointList.setPoint(connectionPointList.getPoint(1).translate(moveDelta.x, 0), 1);
            }
            normalizeAndStraight(connectionPointList);
            removePointsInViews(connectionPointList, (PrecisionRectangle) sourceBounds.getTranslated(moveDelta), sourceRefPoint, targetBounds, targetRefPoint);
        } else {
            // Compute intersection between the line
            // (tempSourceRefPoint<-->second point) and the source node
            // 2-Compute intersection
            Option<Point> intersectionPoint = GraphicalHelper.getIntersection(sourceRefPoint, connectionPointList.getPoint(1), sourceBounds.getTranslated(moveDelta), false);
            if (intersectionPoint.some()) {
                connectionPointList.setPoint(intersectionPoint.get(), 0);
            } else {
                connectionPointList.setPoint(connectionPointList.getPoint(0).translate(moveDelta), 0);
            }
        }
    }

    /**
     * Adapt the point list and the target reference point according to a move
     * of the target.
     * 
     * @param moveDelta
     *            the delta of the move of the extremity (source or target)
     * @param isEdgeWithRectilinearRoutingStyle
     *            True if the edge is rectilinear (one more point is adapted in
     *            this case), false otherwise
     * @param sourceBounds
     *            The bounds of the source node
     * @param targetBounds
     *            The bounds of the target node
     * @param sourceRefPoint
     *            The source reference point
     * @param targetRefPoint
     *            The target reference point to adapt
     * @param connectionPointList
     *            The point list to adapt
     */
    private static void moveLastSegmentAndRefPointAccordingToTargetMove(Point moveDelta, boolean isEdgeWithRectilinearRoutingStyle, PrecisionRectangle sourceBounds, PrecisionRectangle targetBounds,
            Point sourceRefPoint, Point targetRefPoint, PointList connectionPointList) {
        // Move reference point
        targetRefPoint.performTranslate(moveDelta.x, moveDelta.y);
        // Move second last point in case of rectilinear router
        if (isEdgeWithRectilinearRoutingStyle) {
            LineSeg lastSegment = new LineSeg(connectionPointList.getPoint(connectionPointList.size() - 2), connectionPointList.getPoint(connectionPointList.size() - 1));
            if (lastSegment.isHorizontal()) {
                connectionPointList.setPoint(connectionPointList.getPoint(connectionPointList.size() - 2).translate(0, moveDelta.y), connectionPointList.size() - 2);
                connectionPointList.setPoint(connectionPointList.getPoint(connectionPointList.size() - 1).translate(moveDelta.x, moveDelta.y), connectionPointList.size() - 1);
            } else {
                connectionPointList.setPoint(connectionPointList.getPoint(connectionPointList.size() - 2).translate(moveDelta.x, 0), connectionPointList.size() - 2);
                connectionPointList.setPoint(connectionPointList.getPoint(connectionPointList.size() - 1).translate(moveDelta.x, moveDelta.y), connectionPointList.size() - 1);
            }
            normalizeAndStraight(connectionPointList);
            removePointsInViews(connectionPointList, sourceBounds, sourceRefPoint, (PrecisionRectangle) targetBounds.getTranslated(moveDelta), targetRefPoint);
        } else {
            // Compute intersection between the line
            // (tempTargetRefPoint<-->second to last point) and the target node
            // 2-Compute intersection
            Option<Point> intersectionPoint = GraphicalHelper.getIntersection(targetRefPoint, connectionPointList.getPoint(connectionPointList.size() - 2), targetBounds.getTranslated(moveDelta),
                    false);
            if (intersectionPoint.some()) {
                connectionPointList.setPoint(intersectionPoint.get(), connectionPointList.size() - 1);
            } else {
                connectionPointList.setPoint(connectionPointList.getPoint(connectionPointList.size() - 1).translate(moveDelta), connectionPointList.size() - 1);
            }

        }
    }

    /**
     * Check if the segment will be merged with next or previous segment with
     * draw2d rectilinear router. In this case, we remove the useless points.
     * <BR>
     * Logic extracted from
     * {@link org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter.routeLine(Connection,
     * int, PointList)}.
     * 
     * @param connectionPointList
     *            <code>PointList</code> to normalize
     */
    private static void normalizeAndStraight(PointList connectionPointList) {
        if (PointListUtilities.normalizeSegments(connectionPointList, 3)) {
            /*
             * Normalization can make our polyline not rectilinear. Hence, we
             * need to normalize segments of polyline to straight line
             * tolerance.
             */
            normalizeToStraightLineTolerance(connectionPointList, 3);
        }
    }

    /**
     * Goes through line segments of a polyline and makes strict straight
     * segments from nearly straight segments.<BR>
     * Copied from
     * {@link org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter}
     * 
     * @param line
     *            polyline
     * @param tolerance
     *            tolerance value specifying nearly straight lines.
     */
    private static void normalizeToStraightLineTolerance(PointList line, int tolerance) {
        for (int i = 0; i < line.size() - 1; i++) {
            Point pt1 = line.getPoint(i);
            Point pt2 = line.getPoint(i + 1);
            if (Math.abs(pt1.x - pt2.x) < tolerance) {
                line.setPoint(new Point(pt1.x, pt2.y), i + 1);
            } else if (Math.abs(pt1.y - pt2.y) < tolerance) {
                line.setPoint(new Point(pt2.x, pt1.y), i + 1);
            }
        }
    }

    /**
     * Removes consecutive points contained within the source shape and removes
     * consecutive points contained within the target shape. If all points have
     * been removed an extra point outside source and target shapes will be
     * added. Inspired from
     * {@link org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter#removePointsInViews(Connection, PointList, Point, Point)}
     * 
     * @param newLine
     *            polyline of the connection
     * @param source
     *            The bounds of the source node
     * @param start
     *            old start anchor point
     * @param source
     *            The bounds of the target node
     * @param end
     *            old end anchor point
     */
    private static void removePointsInViews(PointList newLine, PrecisionRectangle source, Point start, PrecisionRectangle target, Point end) {
        Point lastRemovedFromSource = null;
        Point lastRemovedFromTarget = null;

        /*
         * Starting from the first point of polyline remove points that are
         * contained within the source shape until the first point outside is
         * found. Remember the point that was removed from the source shape last
         * for a possible case of all points removed from polyline.
         */
        PointList sourcePointList = PointListUtilities.createPointsFromRect(source);
        if (newLine.size() != 0) {
            int nbIncludedPoints = 0;
            for (int i = 0; i < newLine.size() && sourcePointList.polygonContainsPoint(newLine.getPoint(i).x, newLine.getPoint(i).y); i++) {
                nbIncludedPoints++;
            }
            // Do nothing if there is only one point inside. This is the first
            // point that is on the border.
            if (nbIncludedPoints > 1) {
                for (int i = 0; i < nbIncludedPoints; i++) {
                    lastRemovedFromSource = newLine.removePoint(0);
                }
            }
        }

        /*
         * Starting from the end point of polyline remove points that are
         * contained within the target shape until the first point outside is
         * found. Remember the point that was removed from the target shape last
         * for a possible case of all points removed from polyline.
         */
        PointList targetPointList = PointListUtilities.createPointsFromRect(target);
        if (newLine.size() != 0) {
            int nbIncludedPoints = 0;
            for (int i = newLine.size() - 1; i > 0 && targetPointList.polygonContainsPoint(newLine.getPoint(i).x, newLine.getPoint(i).y); i--) {
                nbIncludedPoints++;
            }
            // Do nothing if there is only one point inside. This is the last
            // point that is on the border.
            if (nbIncludedPoints > 1) {
                for (int i = 0; i < nbIncludedPoints; i++) {
                    lastRemovedFromTarget = newLine.removePoint(newLine.size() - 1);
                }
            }
        }

        /*
         * Handle the special case of all points removed from polyline.
         */
        if (newLine.size() == 0) {
            Dimension tolerance = new Dimension(1, 0);
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
                if (source.preciseY() < target.preciseY()) {
                    newLine.addPoint(lastRemovedFromSource.x, (source.getBottom().y + target.getTop().y) / 2);
                } else {
                    newLine.addPoint(lastRemovedFromSource.x, (source.getTop().y + target.getBottom().y) / 2);
                }
            } else if (Math.abs(lastRemovedFromSource.y - lastRemovedFromTarget.y) < toleranceValue) {
                // Horizontal
                if (source.preciseX() < target.preciseX()) {
                    newLine.addPoint((source.getRight().x + target.getLeft().x) / 2, lastRemovedFromSource.y);
                } else {
                    newLine.addPoint((source.getLeft().x + target.getRight().x) / 2, lastRemovedFromSource.y);
                }
            }
        } else {
            // Add necessary point to complete the first (or last) segment if
            // points have been removed from source (or target).
            if (lastRemovedFromSource != null) {
                Option<Point> optionalIntersection = GraphicalHelper.getIntersection(lastRemovedFromSource, newLine.getFirstPoint(), source, true);
                if (optionalIntersection.some()) {
                    newLine.insertPoint(optionalIntersection.get(), 0);
                }
            } else if (lastRemovedFromTarget != null) {
                Option<Point> optionalIntersection = GraphicalHelper.getIntersection(lastRemovedFromTarget, newLine.getLastPoint(), target, false);
                if (optionalIntersection.some()) {
                    newLine.addPoint(optionalIntersection.get());
                }
            }
        }
    }

    /**
     * Set if the source is moved, or if the target is moved.
     * 
     * @param sourceMove
     *            true if the source of the edge is moved, false otherwise.
     */
    public void setSourceMove(boolean sourceMove) {
        this.sourceMove = sourceMove;
        if (sourceMove) {
            setLabel("Move first segment (on source side)");
        } else {
            setLabel("Move last segment (on target side)");
        }
    }

    /**
     * Set the move delta.
     * 
     * @param moveDelta
     *            Point representing the distance the EditPart has moved.
     */
    public void setMoveDelta(PrecisionPoint moveDelta) {
        this.moveDelta = new PrecisionPoint(moveDelta);

    }
}
