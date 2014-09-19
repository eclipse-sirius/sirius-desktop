/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
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
        // Get the bounds of the moved node
        Rectangle bounds;
        if (sourceMove) {
            bounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((IGraphicalEditPart) connectionEditPart.getSource());
        } else {
            bounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((IGraphicalEditPart) connectionEditPart.getTarget());
        }
        adaptPointListAndRefPoints(sourceMove, moveDelta, new ConnectionEditPartQuery(connectionEditPart).isEdgeWithRectilinearRoutingStyle(), bounds, sourceRefPoint, targetRefPoint,
                connectionPointList);
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
     * @param isEdgeWithRectilinearRoutingStyle
     *            True if the edge is rectilinear (one more point is adapted in
     *            this case), false otherwise
     * @param bounds
     *            The bounds of the moved node
     * @param sourceRefPoint
     *            The source reference point to adapt (only adapted for
     *            sourceMove true)
     * @param targetRefPoint
     *            The target reference point to adapt (only adapted for
     *            sourceMove false)
     * @param connectionPointList
     *            The point list to adapt
     */
    public static void adaptPointListAndRefPoints(boolean sourceMove, Point moveDelta, boolean isEdgeWithRectilinearRoutingStyle, Rectangle bounds, Point sourceRefPoint, Point targetRefPoint,
            PointList connectionPointList) {
        if (sourceMove) {
            // Move reference point
            sourceRefPoint.performTranslate(moveDelta.x, moveDelta.y);
            // Move second point in case of rectilinear router
            if (isEdgeWithRectilinearRoutingStyle) {
                LineSeg firstSegment = new LineSeg(connectionPointList.getPoint(0), connectionPointList.getPoint(1));
                if (firstSegment.isHorizontal()) {
                    connectionPointList.setPoint(connectionPointList.getPoint(1).translate(0, moveDelta.y), 1);
                } else {
                    connectionPointList.setPoint(connectionPointList.getPoint(1).translate(moveDelta.x, 0), 1);
                }
            }
            // Compute intersection between the line
            // (tempSourceRefPoint<-->second point) and the source node
            // 2-Compute intersection
            Option<Point> intersectionPoint = GraphicalHelper.getIntersection(sourceRefPoint, connectionPointList.getPoint(1), bounds.getTranslated(moveDelta), false);
            if (intersectionPoint.some()) {
                connectionPointList.setPoint(intersectionPoint.get(), 0);
            } else {
                connectionPointList.setPoint(connectionPointList.getPoint(0).translate(moveDelta), 0);
            }

        } else {
            // Move reference point
            targetRefPoint.performTranslate(moveDelta.x, moveDelta.y);
            // Move second last point in case of rectilinear router
            if (isEdgeWithRectilinearRoutingStyle) {
                LineSeg lastSegment = new LineSeg(connectionPointList.getPoint(connectionPointList.size() - 2), connectionPointList.getPoint(connectionPointList.size() - 1));
                if (lastSegment.isHorizontal()) {
                    connectionPointList.setPoint(connectionPointList.getPoint(connectionPointList.size() - 2).translate(0, moveDelta.y), connectionPointList.size() - 2);
                } else {
                    connectionPointList.setPoint(connectionPointList.getPoint(connectionPointList.size() - 2).translate(moveDelta.x, 0), connectionPointList.size() - 2);
                }
            }
            // Compute intersection between the line
            // (tempTargetRefPoint<-->second to last point) and the target
            // node
            // 2-Compute intersection
            Option<Point> intersectionPoint = GraphicalHelper.getIntersection(targetRefPoint, connectionPointList.getPoint(connectionPointList.size() - 1), bounds.getTranslated(moveDelta), false);
            if (intersectionPoint.some()) {
                connectionPointList.setPoint(intersectionPoint.get(), connectionPointList.size() - 1);
            } else {
                connectionPointList.setPoint(connectionPointList.getPoint(connectionPointList.size() - 1).translate(moveDelta), connectionPointList.size() - 1);
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
