/*******************************************************************************
 * Copyright (c) 2014, 2019 THALES GLOBAL SERVICES.
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

package org.eclipse.sirius.diagram.ui.internal.operation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.RectilinearEdgeUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Operation that removes all edge bendpoints and recreates only the figure
 * connection bendpoints. We obtain the new edge ends by computing the
 * intersection points with the source and target figure on the straight line
 * formed by the source and target anchors.
 *
 * @author Florian Barbin
 *
 */
public class RemoveBendpointsOperation extends AbstractModelChangeOperation<Void> {

    /**
     * Relative position for center anchor.
     */
    private static final String CENTERED_RELATIVE_POINT = "(0.5,0.5)"; //$NON-NLS-1$

    private ConnectionNodeEditPart editPart;

    /**
     * The Operation constructor.
     *
     * @param editPart
     *            The Edge Edit Part to remove bendpoints.
     */
    public RemoveBendpointsOperation(ConnectionNodeEditPart editPart) {
        this.editPart = editPart;
    }

    @Override
    public Void execute() {
        Object model = editPart.getModel();
        if (model instanceof Edge) {
            Bendpoints bendpoints = ((Edge) model).getBendpoints();
            if (bendpoints instanceof RelativeBendpoints) {
                computeNewBendpoints();
            }
        }
        return null;
    }

    private void computeNewBendpoints() {
        IFigure figure = editPart.getFigure();
        Edge edge = (Edge) editPart.getModel();
        Bendpoints bendpoints = ((Edge) editPart.getModel()).getBendpoints();
        RelativeBendpoints relativeBendpoints = (RelativeBendpoints) bendpoints;
        // Number of bend-points composing the edge before remove action.
        int originalNbPoint = relativeBendpoints.getPoints().size();

        if (figure instanceof Connection) {
            Point absoluteSrcAnchorCoordinates = null;
            Point absoluteTgtAnchorCoordinates = null;

            Rectangle srcAbsoluteBounds = getFigureBounds(editPart.getSource());
            Rectangle tgtAbsoluteBounds = getFigureBounds(editPart.getTarget());

            EdgeQuery edgeQuery = new EdgeQuery(edge);
            Routing routingStyle = edgeQuery.getRoutingStyle();
            // Compute anchor logical coordinates
            if (Routing.MANUAL_LITERAL.equals(routingStyle)) {
                absoluteSrcAnchorCoordinates = ((Connection) figure).getSourceAnchor().getReferencePoint();
                absoluteTgtAnchorCoordinates = ((Connection) figure).getTargetAnchor().getReferencePoint();

                // convert coordinates into logical coordinates
                GraphicalHelper.screen2logical(absoluteSrcAnchorCoordinates, editPart);
                GraphicalHelper.screen2logical(absoluteTgtAnchorCoordinates, editPart);
            } else if (Routing.RECTILINEAR_LITERAL.equals(routingStyle)) {
                int newX = srcAbsoluteBounds.x + srcAbsoluteBounds.width / 2;
                int newY = srcAbsoluteBounds.y + srcAbsoluteBounds.height / 2;
                absoluteSrcAnchorCoordinates = new Point(newX, newY);

                newX = tgtAbsoluteBounds.x + tgtAbsoluteBounds.width / 2;
                newY = tgtAbsoluteBounds.y + tgtAbsoluteBounds.height / 2;
                absoluteTgtAnchorCoordinates = new Point(newX, newY);
            }

            // we compute the new bendpoints by computing the intersection
            // points between the source and the target anchors.
            if (srcAbsoluteBounds != null && tgtAbsoluteBounds != null) {
                Option<Point> srcConnectionBendpoint;
                Option<Point> tgtConnectionBendpoint;

                PointList pointList = null;
                if (Routing.RECTILINEAR_LITERAL.equals(routingStyle) && srcAbsoluteBounds != null && srcAbsoluteBounds.equals(tgtAbsoluteBounds)) {
                    // If the edge as the same source and target, there will be no intersection to compute
                    pointList = RectilinearEdgeUtil.computeRectilinearBendpointsSameSourceAndTarget(srcAbsoluteBounds, editPart);
                    srcConnectionBendpoint = Options.newSome(pointList.getFirstPoint());
                    tgtConnectionBendpoint = Options.newSome(pointList.getLastPoint());
                } else {
                    srcConnectionBendpoint = GraphicalHelper.getIntersection(absoluteSrcAnchorCoordinates, absoluteTgtAnchorCoordinates, srcAbsoluteBounds, true);
                    tgtConnectionBendpoint = GraphicalHelper.getIntersection(absoluteSrcAnchorCoordinates, absoluteTgtAnchorCoordinates, tgtAbsoluteBounds, false);
                    Point srcPoint = srcConnectionBendpoint.get();
                    Point tgtPoint = tgtConnectionBendpoint.get();
                    if (Routing.RECTILINEAR_LITERAL.equals(routingStyle)) {
                        RectilinearEdgeUtil.alignBoundPointTowardAnchor(srcAbsoluteBounds, srcPoint, absoluteSrcAnchorCoordinates);
                        RectilinearEdgeUtil.alignBoundPointTowardAnchor(tgtAbsoluteBounds, tgtPoint, absoluteTgtAnchorCoordinates);
                        pointList = RectilinearEdgeUtil.computeRectilinearBendpoints(srcAbsoluteBounds, tgtAbsoluteBounds, srcPoint, tgtPoint);
                    } else {
                        pointList = new PointList();
                        pointList.addPoint(srcPoint);
                        pointList.addPoint(tgtPoint);
                    }
                }

                if (srcConnectionBendpoint.some() && tgtConnectionBendpoint.some() && originalNbPoint > pointList.size()) {
                    if (Routing.RECTILINEAR_LITERAL.equals(routingStyle)) {
                        // Set GMF Anchor on figure center
                        IdentityAnchor srcAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                        IdentityAnchor tgtAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                        srcAnchor.setId(CENTERED_RELATIVE_POINT);
                        tgtAnchor.setId(CENTERED_RELATIVE_POINT);
                        edge.setSourceAnchor(srcAnchor);
                        edge.setTargetAnchor(tgtAnchor);
                    }
                    // Remove bend-points
                    relativeBendpoints.setPoints(new ArrayList<>());
                    // Add new bend-points
                    setNewBendpoints(pointList, absoluteSrcAnchorCoordinates, absoluteTgtAnchorCoordinates);
                }

            }
        }

    }

    private void setNewBendpoints(PointList pointList, Point absoluteSrcAnchorCoordinates, Point absoluteTgtAnchorCoordinates) {
        Object model = editPart.getModel();
        if (model instanceof Edge) {
            GMFNotationUtilities.setGMFBendpoints((Edge) model, pointList, absoluteSrcAnchorCoordinates, absoluteTgtAnchorCoordinates);
            // For each label, reset the offset to default
            List<?> children = editPart.getChildren();
            for (Object child : children) {
                if (child instanceof AbstractDEdgeNameEditPart) {
                    AbstractDEdgeNameEditPart labelEditPartToUpdate = (AbstractDEdgeNameEditPart) child;
                    Node labelNodeToUpdate = (Node) labelEditPartToUpdate.getModel();
                    LayoutConstraint layoutConstraint = labelNodeToUpdate.getLayoutConstraint();
                    if (layoutConstraint instanceof Bounds) {
                        Bounds bounds = (Bounds) layoutConstraint;
                        Point snapBackPosition = EdgeLabelQuery.getSnapBackPosition(labelEditPartToUpdate.getKeyPoint());
                        Bounds labelBounds = NotationFactory.eINSTANCE.createBounds();
                        labelBounds.setX(snapBackPosition.x);
                        labelBounds.setY(snapBackPosition.y);
                        labelBounds.setWidth(bounds.getWidth());
                        labelBounds.setHeight(bounds.getHeight());
                        labelNodeToUpdate.setLayoutConstraint(labelBounds);
                    }
                }
            }
        }

    }

    private Rectangle getFigureBounds(EditPart editPart) {
        if (editPart instanceof GraphicalEditPart) {
            return GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) editPart);
        }
        return null;
    }

}
