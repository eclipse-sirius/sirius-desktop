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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Iterables;

/**
 * This operation updates edges anchors to make sure their connection points
 * keep the same location when the shape is resized.
 * 
 * @author Florian Barbin
 * 
 */
public class ShiftEdgeIdentityAnchorOperation extends AbstractModelChangeOperation<Void> {

    private ChangeBoundsRequest request;

    public ShiftEdgeIdentityAnchorOperation(ChangeBoundsRequest request) {
        this.request = request;
    }

    @Override
    public Void execute() {
        List<?> editParts = request.getEditParts();
        for (EditPart editPart : Iterables.filter(editParts, EditPart.class)) {
            Option<View> viewOption = getView(editPart);
            if (viewOption.some()) {
                handleTargetEdges(viewOption.get(), editPart);
                handleSourceEdges(viewOption.get(), editPart);

            }
        }
        return null;
    }

    private void handleSourceEdges(View view, EditPart editPart) {
        List<?> sourceEdges = view.getSourceEdges();
        for (Edge edge : Iterables.filter(sourceEdges, Edge.class)) {
            Anchor sourceAnchor = edge.getSourceAnchor();
            if (sourceAnchor instanceof IdentityAnchor) {
                PrecisionPoint anchorPoint = BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) sourceAnchor).getId());
                PrecisionPoint newPoint = ComputeNewAnchor(anchorPoint, editPart);
                ((IdentityAnchor) sourceAnchor).setId(new SlidableAnchor(null, newPoint).getTerminal());
            }
        }
    }

    private void handleTargetEdges(View view, EditPart editPart) {
        List<?> targetEdges = view.getTargetEdges();
        for (Edge edge : Iterables.filter(targetEdges, Edge.class)) {
            Anchor targetAnchor = edge.getTargetAnchor();
            if (targetAnchor instanceof IdentityAnchor) {
                PrecisionPoint anchorPoint = BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) targetAnchor).getId());
                PrecisionPoint newPoint = ComputeNewAnchor(anchorPoint, editPart);
                ((IdentityAnchor) targetAnchor).setId(new SlidableAnchor(null, newPoint).getTerminal());
            }
        }

    }

    private PrecisionPoint ComputeNewAnchor(PrecisionPoint currentAnchorPoint, EditPart editPart) {

        double scale = GraphicalHelper.getZoom(editPart);
        IFigure figure = ((IGraphicalEditPart) editPart).getFigure();
        Dimension currentSize = figure.getBounds().getSize();
        float widthFactor = (float) (currentSize.width + (request.getSizeDelta().width / scale)) / currentSize.width;
        float heightFactor = (float) (currentSize.height + (request.getSizeDelta().height / scale)) / currentSize.height;

        int direction = request.getResizeDirection();
        double newAnchorX = computeNewXAnchor(direction, currentAnchorPoint, widthFactor);
        double newAnchorY = computeNewYAnchor(direction, currentAnchorPoint, heightFactor);

        PrecisionPoint newPoint = new PrecisionPoint(newAnchorX, newAnchorY);
        return newPoint;
    }

    private double computeNewXAnchor(int direction, PrecisionPoint currentAnchorPoint, float widthFactor) {
        if (direction == PositionConstants.NORTH_WEST || direction == PositionConstants.WEST || direction == PositionConstants.SOUTH_WEST) {
            return 1 - ((1 - currentAnchorPoint.preciseX()) / widthFactor);
        } else {

            return currentAnchorPoint.preciseX() / widthFactor;
        }
    }

    private double computeNewYAnchor(int direction, PrecisionPoint currentAnchorPoint, float heightFactor) {

        if (direction == PositionConstants.NORTH_WEST || direction == PositionConstants.NORTH || direction == PositionConstants.NORTH_EAST) {
            return 1 - ((1 - currentAnchorPoint.preciseY()) / heightFactor);
        } else {
            return currentAnchorPoint.preciseY() / heightFactor;
        }
    }

    private Option<View> getView(EditPart editPart) {
        Object model = editPart.getModel();
        if (model instanceof View) {
            return Options.newSome((View) model);
        }

        return Options.newNone();
    }
}
