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
package org.eclipse.sirius.diagram.tools.internal.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.diagram.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.tools.internal.graphical.edit.handles.SiriusConnectionEndPointHandle;

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
     * Override this method to fix the points of the edge if it is on orthogonal
     * tree branch. Without this fix, a move of the source or the target has
     * incidence on all the points.
     * 
     * @see org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy#showConnectionMoveFeedback(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected void showConnectionMoveFeedback(ReconnectRequest request) {
        boolean isOrthogonalTreeBranch = new ConnectionQuery(getConnection()).isOrthogonalTreeBranch(getConnection().getPoints());
        ConnectionQuery connectionQuery = new ConnectionQuery(getConnection());
        if (isOrthogonalTreeBranch) {
            if (originalAnchor == null) {
                if (request.isMovingStartAnchor()) {
                    originalAnchor = getConnection().getSourceAnchor();
                } else {
                    originalAnchor = getConnection().getTargetAnchor();
                }
                originalPoints = Lists.newLinkedList();
                originalConstraint = getConnection().getRoutingConstraint();
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
        } else if (originalAnchor != null) {
            // We probably go out of the previous target node so erase the
            // previous custom feedback
            getConnection().setRoutingConstraint(originalConstraint);
        }
        super.showConnectionMoveFeedback(request);
        if (isOrthogonalTreeBranch) {
            // Change the constraints of the connection according to the new
            // anchor
            Option<List<RelativeBendpoint>> optionalRelativeBendpointsContraint = connectionQuery.getTreeRelativeBendpointsConstraint();
            Option<List<AbsoluteBendpoint>> optionalAsboluteBendpointsContraint = connectionQuery.getTreeAbsoluteBendpointsConstraint();
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
            if (optionalRelativeBendpointsContraint.some() || optionalAsboluteBendpointsContraint.some()) {
                if (optionalRelativeBendpointsContraint.some()) {
                    for (int i = 0; i < points.size(); i++) {
                        Dimension s = points.get(i).getDifference(newSourceRefPoint);
                        Dimension t = points.get(i).getDifference(newTargetRefPoint);
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
                    Dimension s = points.get(i).getDifference(newSourceRefPoint);
                    Dimension t = points.get(i).getDifference(newTargetRefPoint);
                    RelativeBendpoint rb = new RelativeBendpoint(getConnection());
                    rb.setRelativeDimensions(s, t);
                    rb.setWeight(i / ((float) points.size() - 1));
                    newConstraint.add(rb);
                }
                getConnection().setRoutingConstraint(newConstraint);
            }
        }

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
