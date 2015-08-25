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

package org.eclipse.sirius.diagram.ui.internal.operation;

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
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Lists;

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
                ((RelativeBendpoints) bendpoints).setPoints(Lists.newArrayList());
                computeNewBendpoints();
            }
        }
        return null;
    }

    private void computeNewBendpoints() {
        IFigure figure = editPart.getFigure();
        if (figure instanceof Connection) {
            Point absoluteSrcAnchorCoordinates = ((Connection) figure).getSourceAnchor().getReferencePoint();
            Point absoluteTgtAnchorCoordinates = ((Connection) figure).getTargetAnchor().getReferencePoint();

            // convert coordinates into logical coordinates
            GraphicalHelper.screen2logical(absoluteSrcAnchorCoordinates, editPart);
            GraphicalHelper.screen2logical(absoluteTgtAnchorCoordinates, editPart);

            Rectangle srcAbsoluteBounds = getFigureBounds(editPart.getSource());
            Rectangle tgtAbsoluteBounds = getFigureBounds(editPart.getTarget());

            // we compute the new bendpoints by computing the intersection
            // points between the source and the target anchors.
            if (srcAbsoluteBounds != null && tgtAbsoluteBounds != null) {
                Option<Point> srcConnectionBendpoint = GraphicalHelper.getIntersection(absoluteSrcAnchorCoordinates, absoluteTgtAnchorCoordinates, srcAbsoluteBounds, true);
                Option<Point> tgtConnectionBendpoint = GraphicalHelper.getIntersection(absoluteSrcAnchorCoordinates, absoluteTgtAnchorCoordinates, tgtAbsoluteBounds, false);

                if (srcConnectionBendpoint.some() && tgtConnectionBendpoint.some()) {
                    setNewBendpoints(srcConnectionBendpoint.get(), tgtConnectionBendpoint.get(), absoluteSrcAnchorCoordinates, absoluteTgtAnchorCoordinates);
                }

            }
        }

    }

    private void setNewBendpoints(Point sourceConnection, Point targetConnection, Point absoluteSrcAnchorCoordinates, Point absoluteTgtAnchorCoordinates) {
        Object model = editPart.getModel();
        if (model instanceof Edge) {
            PointList pointList = new PointList();
            pointList.addPoint(sourceConnection);
            pointList.addPoint(targetConnection);
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
