/*******************************************************************************
 * Copyright (c) 2012, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.api.query;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IOvalAnchorableFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IPolygonAnchorableFigure;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link ConnectionEditPart} as a starting point.
 * 
 * @author lredor
 */
public class ConnectionEditPartQuery {

    private ConnectionEditPart connectionEditPart;

    /**
     * Create a new query.
     * 
     * @param connectionEditPart
     *            the starting point.
     */
    public ConnectionEditPartQuery(ConnectionEditPart connectionEditPart) {
        this.connectionEditPart = connectionEditPart;
    }

    /**
     * Check if this ConnectionEditPart is in diagram that is described with an
     * OrderedTreeLayout or a ComponentLayout.
     * 
     * @return true if ConnectionEditPart is in diagram that is described with
     *         an OrderedTreeLayout or a ComponentLayout, false otherwise.
     */
    public boolean isLayoutComponent() {
        boolean isLayoutComponent = false;
        if (isEdgeWithTreeRoutingStyle()) {
            Diagram diagram = getDiagram();
            if (diagram != null && diagram.getElement() instanceof DSemanticDiagram) {
                DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) diagram.getElement();
                Layout layout = dSemanticDiagram.getDescription().getLayout();
                isLayoutComponent = isOrderedTreeLayoutOrComponentLayout(layout);
            }
        }
        return isLayoutComponent;
    }

    /**
     * Check if this ConnectionEditPart has a tree routing style.
     * 
     * @return true if ConnectionEditPart has a tree routing style, false
     *         otherwise.
     */
    public boolean isEdgeWithTreeRoutingStyle() {
        boolean isEdgeTreeRoutingStyle = false;
        if (connectionEditPart.getModel() instanceof Edge) {
            isEdgeTreeRoutingStyle = new EdgeQuery((Edge) connectionEditPart.getModel()).isEdgeWithTreeRoutingStyle();
        }
        return isEdgeTreeRoutingStyle;
    }

    /**
     * Check if this ConnectionEditPart has a rectilinear routing style.
     * 
     * @return true if ConnectionEditPart has a rectilinear routing style, false
     *         otherwise.
     */
    public boolean isEdgeWithRectilinearRoutingStyle() {
        boolean isEdgeWithRectilinearRoutingStyle = false;
        if (connectionEditPart.getModel() instanceof Edge) {
            isEdgeWithRectilinearRoutingStyle = new EdgeQuery((Edge) connectionEditPart.getModel()).isEdgeWithRectilinearRoutingStyle();
        }
        return isEdgeWithRectilinearRoutingStyle;
    }

    /**
     * Check if this ConnectionEditPart has an oblique routing style.
     * 
     * @return true if ConnectionEditPart has an oblique routing style, false
     *         otherwise.
     */
    public boolean isEdgeWithObliqueRoutingStyle() {
        boolean isEdgeWithObliqueRoutingStyle = false;
        if (connectionEditPart.getModel() instanceof Edge) {
            isEdgeWithObliqueRoutingStyle = new EdgeQuery((Edge) connectionEditPart.getModel()).isEdgeWithObliqueRoutingStyle();
        }
        return isEdgeWithObliqueRoutingStyle;
    }

    private Diagram getDiagram() {
        Diagram diagram = null;
        if (connectionEditPart.getParent() instanceof DiagramRootEditPart) {
            DiagramRootEditPart diagramRootEditPart = (DiagramRootEditPart) connectionEditPart.getParent();
            if (diagramRootEditPart.getChildren().get(0) instanceof DiagramEditPart) {
                DiagramEditPart diagramEditPart = (DiagramEditPart) diagramRootEditPart.getChildren().get(0);
                if (diagramEditPart.getModel() instanceof Diagram) {
                    diagram = (Diagram) diagramEditPart.getModel();
                }
            }
        }
        return diagram;
    }

    private boolean isOrderedTreeLayoutOrComponentLayout(Layout layout) {
        boolean isLayout = false;
        if (layout instanceof OrderedTreeLayout || layout instanceof CompositeLayout) {
            if (layout instanceof CompositeLayout) {
                // This code is commented because left to right run not
                // correctly see ticket.
                // CompositeLayout compositeLayout = (CompositeLayout) layout;
                // if
                // (!LayoutDirection.LEFT_TO_RIGHT.getLiteral().equals(compositeLayout.getDirection().getName()))
                // {
                isLayout = true;
                // }
            } else {
                isLayout = true;
            }
        }
        return isLayout;
    }

    /**
     * Checks if source shape and target shape of the connection intersect (only intersect - not one contained in another).
     * Method inspired from what is done in {@link org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ObliqueRouter#checkShapesIntersect(Connection, PointList)}.
     * 
     * @return true if the source and target intersect, false otherwise. 
     */
    @SuppressWarnings("restriction")
    public boolean checkShapesIntersect() {
        boolean result = false;
        if (connectionEditPart instanceof AbstractConnectionEditPart) {
            Connection conn = ((AbstractConnectionEditPart) connectionEditPart).getConnectionFigure();
            if (!(conn.getSourceAnchor().getOwner() == null || conn.getSourceAnchor().getOwner() instanceof Connection || conn.getTargetAnchor().getOwner() == null
                    || conn.getTargetAnchor().getOwner() instanceof Connection)) {
                PrecisionRectangle sourceBounds = getShapeBounds(conn.getSourceAnchor().getOwner());
                PrecisionRectangle targetBounds = getShapeBounds(conn.getTargetAnchor().getOwner());
                conn.getSourceAnchor().getOwner().translateToAbsolute(sourceBounds);
                conn.getTargetAnchor().getOwner().translateToAbsolute(targetBounds);
                if (sourceBounds.intersects(targetBounds) && !sourceBounds.contains(targetBounds) && !targetBounds.contains(sourceBounds) || sourceBounds.equals(targetBounds)) {
                    result = true;
                }
            }
        }
        return result;
    }

    private PrecisionRectangle getShapeBounds(IFigure figure) {
        PrecisionRectangle result = new PrecisionRectangle(figure.getBounds());
        if (figure instanceof IOvalAnchorableFigure) {
            result = new PrecisionRectangle(((IOvalAnchorableFigure) figure).getOvalBounds());
        } else if (figure instanceof IPolygonAnchorableFigure) {
            result = new PrecisionRectangle(((IPolygonAnchorableFigure) figure).getPolygonPoints().getBounds());
        }
        return result;

    }

    /**
     * Computes an anchor at the center of a {@link Rectangle}.
     * 
     * @param bounds
     *            the {@link Rectangle} used for computing an anchor.
     * @return the resulting anchor as a {@link PrecisionPoint}
     */
    public PrecisionPoint getCenteredAnchorsAbsoluteLocation(Rectangle bounds) {
        Edge edge = (Edge) connectionEditPart.getModel();
        return getAbsoluteAnchorCoordinates(bounds, getCenteredAnchorId(edge.getSourceAnchor()));
    }

    /**
     * Compute the anchor absolute coordinates following the figure bounds and the anchor location.
     * 
     * @param absoluteBounds
     *            the figure absolute bounds.
     * @param precisionPoint
     *            the anchor location.
     * @return the anchor absolute location.
     */
    private PrecisionPoint getAbsoluteAnchorCoordinates(Rectangle absoluteBounds, PrecisionPoint precisionPoint) {
        return new PrecisionPoint(absoluteBounds.x() + (absoluteBounds.width() * precisionPoint.preciseX()), absoluteBounds.y() + (absoluteBounds.height() * precisionPoint.preciseY()));

    }

    private PrecisionPoint getCenteredAnchorId(Anchor anchor) {
        PrecisionPoint anchorId = new PrecisionPoint(0.5, 0.5);
        if (anchor instanceof IdentityAnchor) {
            anchorId = BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) anchor).getId());
        }
        return anchorId;
    }
}
