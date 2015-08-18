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
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.handles.SiriusConnectionEndPointHandle;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.draw2d.ui.geometry.LineSegQuery;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Lists;

/**
 * Override ConnectionEndPointEditPolicy to call our connection end point handle
 * ({@link SiriusConnectionEndPointHandle}).
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class SiriusConnectionEndPointEditPolicy extends ConnectionEndpointEditPolicy {

    private ConnectionAnchor originalAnchor;

    private Object originalConstraint;

    private List<Point> originalPoints;

    /**
     * Default constructor.
     */
    public SiriusConnectionEndPointEditPolicy() {
        super();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy#createSelectionHandles()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected List createSelectionHandles() {
        List list = new ArrayList();
        list.add(new SiriusConnectionEndPointHandle((AbstractDiagramEdgeEditPart) getHost(), ConnectionLocator.SOURCE));
        list.add(new SiriusConnectionEndPointHandle((AbstractDiagramEdgeEditPart) getHost(), ConnectionLocator.TARGET));
        return list;
    }

    /**
     * {@inheritDoc} <BR>
     * Override this method to fix the points of the edge:
     * <UL>
     * <LI>if it is on orthogonal tree branch</LI>
     * <LI>if connection has oblique or rectilinear router, to move only the
     * points corresponding to the last segment.</LI>
     * </UL>
     * Without this fix, a move of the source or the target has incidence on all
     * the points.
     * 
     * @see org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy#showConnectionMoveFeedback(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected void showConnectionMoveFeedback(ReconnectRequest request) {
        ConnectionQuery connectionQuery = new ConnectionQuery(getConnection());
        ConnectionEditPartQuery cepq = new ConnectionEditPartQuery((ConnectionEditPart) getHost());
        boolean isOrthogonalTreeBranch = connectionQuery.isOrthogonalTreeBranch(getConnection().getPoints());
        boolean isEdgeWithObliqueRoutingStyle = cepq.isEdgeWithObliqueRoutingStyle();
        boolean isEdgeWithRectilinearRoutingStyle = cepq.isEdgeWithRectilinearRoutingStyle();
        if (isOrthogonalTreeBranch || isEdgeWithObliqueRoutingStyle || isEdgeWithRectilinearRoutingStyle) {
            preShowConnectionMoveFeedback(request);
        } else if (originalAnchor != null) {
            // We probably go out of the previous target node so erase the
            // previous custom feedback
            getConnection().setRoutingConstraint(originalConstraint);
        }
        super.showConnectionMoveFeedback(request);
        if (isOrthogonalTreeBranch) {
            postShowConnectionMoveFeedbackForOrthogonalTreeBranch(request);
        } else if (isEdgeWithObliqueRoutingStyle || isEdgeWithRectilinearRoutingStyle) {
            postShowConnectionMoveFeedbackForObliqueOrRectilinearConnection(request);
        }
    }

    /**
     * Store the original anchor (source or target), routing constraint and
     * points.
     * 
     * @param request
     *            the reconnect request
     */
    private void preShowConnectionMoveFeedback(ReconnectRequest request) {
        if (originalAnchor == null) {
            if (request.isMovingStartAnchor()) {
                originalAnchor = getConnection().getSourceAnchor();
            } else {
                originalAnchor = getConnection().getTargetAnchor();
            }
            originalPoints = Lists.newLinkedList();
            saveOriginalConstraint();

            ConnectionQuery connectionQuery = new ConnectionQuery(getConnection());
            Option<List<RelativeBendpoint>> optionalRelativeBendpointsContraint = connectionQuery.getTreeRelativeBendpointsConstraint();
            if (optionalRelativeBendpointsContraint.some()) {
                for (RelativeBendpoint relativeBendpoint : optionalRelativeBendpointsContraint.get()) {
                    originalPoints.add(relativeBendpoint.getLocation());
                }
            } else {
                Option<List<AbsoluteBendpoint>> optionalAsboluteBendpointsContraint = connectionQuery.getTreeAbsoluteBendpointsConstraint();
                if (optionalAsboluteBendpointsContraint.some()) {
                    for (AbsoluteBendpoint absoluteBendpoint : optionalAsboluteBendpointsContraint.get()) {
                        originalPoints.add(absoluteBendpoint.getLocation().getCopy());
                    }
                }
            }
            if (originalPoints.isEmpty()) {
                // The constraint probably contains only 2 points (it's
                // possible just after an edge creation for example). So in
                // this case, we use the points of the edge
                for (int i = 0; i < getConnection().getPoints().size(); i++) {
                    originalPoints.add(getConnection().getPoints().getPoint(i).getCopy());
                }
            }
        }
    }

    /**
     * This method copies each {@link RelativeBendpoint} to be able to restore
     * it later as they will be updated for the feedback
     */
    @SuppressWarnings("unchecked")
    private void saveOriginalConstraint() {
        if (getConnection().getRoutingConstraint() instanceof List) {
            List<RelativeBendpoint> listBendpoint = Lists.newArrayList();
            ArrayList<Object> originListBendpoint = new ArrayList<Object>((List<Object>) getConnection().getRoutingConstraint());
            try {
                // Unfortunately, the vectors (stored as dimensions) and weight
                // of Draw2D RelativeBendpoint is not accessible
                Field dimension1Field = RelativeBendpoint.class.getDeclaredField("d1"); //$NON-NLS-1$
                boolean dim1Accessibility = dimension1Field.isAccessible();
                dimension1Field.setAccessible(true);
                Field dimension2Field = RelativeBendpoint.class.getDeclaredField("d2"); //$NON-NLS-1$
                boolean dim2Accessibility = dimension1Field.isAccessible();
                dimension2Field.setAccessible(true);
                Field weightField = RelativeBendpoint.class.getDeclaredField("weight"); //$NON-NLS-1$
                boolean weightAccessibility = dimension1Field.isAccessible();
                weightField.setAccessible(true);
                for (int i = 0; i < originListBendpoint.size(); i++) {
                    if (originListBendpoint.get(i) instanceof RelativeBendpoint) {
                        // Copy of each RelativeBendpoint dimensions and weight
                        // in a new RelativeBendpoint
                        RelativeBendpoint originBendpoint = (RelativeBendpoint) originListBendpoint.get(i);
                        Dimension dimension1 = (Dimension) dimension1Field.get(originBendpoint);
                        Dimension dimension2 = (Dimension) dimension2Field.get(originBendpoint);
                        Float weight = (Float) weightField.get(originBendpoint);
                        RelativeBendpoint copyBendpoint = new RelativeBendpoint(getConnection());
                        copyBendpoint.setRelativeDimensions(dimension1, dimension2);
                        copyBendpoint.setWeight(weight);
                        listBendpoint.add(copyBendpoint);
                    }
                }
                // Restore accessibility status of the dimensions and weight
                // fields
                dimension1Field.setAccessible(dim1Accessibility);
                dimension2Field.setAccessible(dim2Accessibility);
                weightField.setAccessible(weightAccessibility);
            } catch (SecurityException e) {
                DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, DiagramUIPlugin.ID, e.getMessage()));
            } catch (NoSuchFieldException e) {
                DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, DiagramUIPlugin.ID, e.getMessage()));
            } catch (IllegalArgumentException e) {
                DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, DiagramUIPlugin.ID, e.getMessage()));
            } catch (IllegalAccessException e) {
                DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, DiagramUIPlugin.ID, e.getMessage()));
            }
            originalConstraint = listBendpoint;
        } else {
            originalConstraint = getConnection().getRoutingConstraint();
        }
    }

    /**
     * Change the routing constraint of the Connection according to a change of
     * the first vertical segment or of the last vertical segment.
     * 
     * @param request
     *            the reconnect request
     */
    private void postShowConnectionMoveFeedbackForOrthogonalTreeBranch(ReconnectRequest request) {
        // Change the constraints of the connection according to the new anchor
        // (move the vertical first segment or the vertical last segment).
        List<Point> points = Lists.newArrayList();
        for (Point point : originalPoints) {
            points.add(point.getCopy());
        }
        Point newSourceRefPoint = getConnection().getSourceAnchor().getReferencePoint();
        getConnection().translateToRelative(newSourceRefPoint);
        Point newTargetRefPoint = getConnection().getTargetAnchor().getReferencePoint();
        getConnection().translateToRelative(newTargetRefPoint);
        if (request.isMovingStartAnchor()) {
            // Compute the x delta
            int deltaX = points.get(0).x - newSourceRefPoint.x;
            // The first and the second points must be shift on the x
            // coordinate
            points.get(0).translate(deltaX, 0);
            points.get(1).translate(deltaX, 0);
        } else {
            // Compute the x delta
            int deltaX = newTargetRefPoint.x - points.get(3).x;
            // The third and the last points must be shift on the x
            // coordinate
            points.get(2).translate(deltaX, 0);
            points.get(3).translate(deltaX, 0);
        }
        // Change the routing constraint (bendpoints constraints) of the
        // Connection according to this new points.
        changeRoutingConstraint(points, newSourceRefPoint, newTargetRefPoint);
    }

    /**
     * Change the routing constraint (bendpoints constraints) of the
     * {@link Connection} according to this new points.
     * 
     * @param points
     *            List of points to follow
     * @param sourceRefPoint
     *            Source reference point
     * @param targetRefPoint
     *            Target reference point
     */
    private void changeRoutingConstraint(List<Point> points, Point sourceRefPoint, Point targetRefPoint) {
        ConnectionQuery connectionQuery = new ConnectionQuery(getConnection());
        Option<List<RelativeBendpoint>> optionalRelativeBendpointsContraint = connectionQuery.getTreeRelativeBendpointsConstraint();
        Option<List<AbsoluteBendpoint>> optionalAsboluteBendpointsContraint = connectionQuery.getTreeAbsoluteBendpointsConstraint();
        if (optionalRelativeBendpointsContraint.some() || optionalAsboluteBendpointsContraint.some()) {
            if (optionalRelativeBendpointsContraint.some()) {
                for (int i = 0; i < points.size(); i++) {
                    Dimension s = points.get(i).getDifference(sourceRefPoint);
                    Dimension t = points.get(i).getDifference(targetRefPoint);
                    optionalRelativeBendpointsContraint.get().get(i).setRelativeDimensions(s, t);
                }
                getConnection().setRoutingConstraint(optionalRelativeBendpointsContraint.get());
            } else {
                for (int i = 0; i < points.size(); i++) {
                    optionalAsboluteBendpointsContraint.get().get(i).setLocation(points.get(i));
                }
                getConnection().setRoutingConstraint(optionalAsboluteBendpointsContraint.get());
            }
        } else {
            List<RelativeBendpoint> newConstraint = Lists.newLinkedList();
            for (int i = 0; i < points.size(); i++) {
                Dimension s = points.get(i).getDifference(sourceRefPoint);
                Dimension t = points.get(i).getDifference(targetRefPoint);
                RelativeBendpoint rb = new RelativeBendpoint(getConnection());
                rb.setRelativeDimensions(s, t);
                rb.setWeight(i / ((float) points.size() - 1));
                newConstraint.add(rb);
            }
            getConnection().setRoutingConstraint(newConstraint);
        }
    }

    /**
     * Change the routing constraint of the Connection according to a change of
     * the first or of the last segment.
     * 
     * @param request
     *            the reconnect request
     */
    private void postShowConnectionMoveFeedbackForObliqueOrRectilinearConnection(ReconnectRequest request) {
        // Compute the move delta
        PrecisionPoint moveDelta;
        List<Point> points = Lists.newArrayList();
        for (Point point : originalPoints) {
            points.add(point.getCopy());
        }
        Connection connection = getConnection();
        ConnectionEditPart connectionEditPart = (ConnectionEditPart) getHost();

        Point newSourceRefPoint = connection.getSourceAnchor().getReferencePoint();
        connection.translateToRelative(newSourceRefPoint);
        Point newTargetRefPoint = connection.getTargetAnchor().getReferencePoint();
        connection.translateToRelative(newTargetRefPoint);

        PrecisionPoint scrollSize = new PrecisionPoint(GraphicalHelper.getScrollSize(connectionEditPart));
        GraphicalHelper.applyInverseZoomOnPoint(connectionEditPart, scrollSize);

        // If the source or target is disconnected, the anchor is changed from a
        // SlidableAnchor to a XYAnchor. The location computation is different
        // and we need to introduce scroll and zoom. Otherwise the dragged
        // extremity will not be under the mouse.
        if (!request.isMovingStartAnchor() && !connectionEditPart.getTarget().equals(request.getTarget()) && connection.getTargetAnchor() instanceof XYAnchor) {
            ((XYAnchor) connection.getTargetAnchor()).setLocation(GraphicalHelper.applyZoomOnPoint(connectionEditPart, newTargetRefPoint.getTranslated(-scrollSize.x, -scrollSize.y)));
            newTargetRefPoint = connection.getTargetAnchor().getReferencePoint().getCopy();
            connection.translateToRelative(newTargetRefPoint);
        } else if (request.isMovingStartAnchor() && !connectionEditPart.getSource().equals(request.getTarget()) && connection.getSourceAnchor() instanceof XYAnchor) {
            ((XYAnchor) connection.getSourceAnchor()).setLocation(GraphicalHelper.applyZoomOnPoint(connectionEditPart, newSourceRefPoint.getTranslated(-scrollSize.x, -scrollSize.y)));
            newSourceRefPoint = connection.getSourceAnchor().getReferencePoint().getCopy();
            connection.translateToRelative(newSourceRefPoint);
        }

        if (request.isMovingStartAnchor()) {
            moveDelta = new PrecisionPoint(points.get(0).x - newSourceRefPoint.x, points.get(0).y - newSourceRefPoint.y);
        } else {
            moveDelta = new PrecisionPoint(newTargetRefPoint.x - points.get(points.size() - 1).x, newTargetRefPoint.y - points.get(points.size() - 1).y);
        }
        // Change the constraints of the connection according to the new anchor
        // (move the first or the last segment).
        if (request.isMovingStartAnchor()) {
            moveDelta.negate();
            LineSeg firstSegment = new LineSeg(points.get(0), points.get(1));
            LineSegQuery lineSegQuery = new LineSegQuery(firstSegment);
            // Change the first point according to the move
            points.set(0, points.get(0).getTranslated(moveDelta));
            if (new ConnectionEditPartQuery(connectionEditPart).isEdgeWithRectilinearRoutingStyle()) {
                // and also change the second point only if the edge has a
                // rectilinear routing constraint.
                if (lineSegQuery.isHorizontal()) {
                    points.set(1, points.get(1).getTranslated(0, moveDelta.preciseY()));
                } else {
                    points.set(1, points.get(1).getTranslated(moveDelta.preciseX(), 0));
                }
            }
        } else {
            LineSeg lastSegment = new LineSeg(points.get(points.size() - 2), points.get(points.size() - 1));
            LineSegQuery lineSegQuery = new LineSegQuery(lastSegment);
            // Change the last point according to the move
            points.set(points.size() - 1, points.get(points.size() - 1).getTranslated(moveDelta));
            // and also change the penultimate point only if the edge has a
            // rectilinear routing constraint.
            if (new ConnectionEditPartQuery(connectionEditPart).isEdgeWithRectilinearRoutingStyle()) {
                if (lineSegQuery.isHorizontal()) {
                    points.set(points.size() - 2, points.get(points.size() - 2).getTranslated(0, moveDelta.preciseY()));
                } else {
                    points.set(points.size() - 2, points.get(points.size() - 2).getTranslated(moveDelta.preciseX(), 0));
                }
            }
        }

        // Change the routing constraint (bendpoints constraints) of the
        // Connection according to this new points.
        changeRoutingConstraint(points, newSourceRefPoint, newTargetRefPoint);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy#eraseConnectionMoveFeedback(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected void eraseConnectionMoveFeedback(ReconnectRequest request) {
        super.eraseConnectionMoveFeedback(request);
        if (originalAnchor != null) {
            getConnection().setRoutingConstraint(originalConstraint);
            originalAnchor = null;
        }
        originalConstraint = null;
    }
}
