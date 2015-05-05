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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
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

    /** The request to compute the new anchor. */
    private ChangeBoundsRequest request;

    /** The future size of the border node. */
    private Dimension futureSize;

    /** The real delta whose the border node will be shift. */
    private PrecisionPoint delta;

    /**
     * Default constructor.
     * 
     * @param request
     *            Request to compute the new anchor location.
     */
    public ShiftEdgeIdentityAnchorOperation(ChangeBoundsRequest request) {
        this.request = request;
    }

    /**
     * Constructor to use for border node. The request is not enough in case of
     * border node because its real location, depends on other border nodes and
     * not only on the request data.
     * 
     * @param request
     *            Request to compute the new anchor location.
     * @param futureSize
     *            The future size of the border node.
     * @param delta
     *            The delta whose the border node will be shift
     */
    public ShiftEdgeIdentityAnchorOperation(ChangeBoundsRequest request, Dimension futureSize, PrecisionPoint delta) {
        this(request);
        this.futureSize = futureSize;
        this.delta = delta;
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
            EObject eObj = edge.getElement();
            if (eObj instanceof DEdge) {
                handleEdge(edge, editPart, true);
            }
        }
    }

    private void handleTargetEdges(View view, EditPart editPart) {
        List<?> targetEdges = view.getTargetEdges();
        for (Edge edge : Iterables.filter(targetEdges, Edge.class)) {
            EObject eObj = edge.getElement();
            if (eObj instanceof DEdge) {
                handleEdge(edge, editPart, false);
            }
        }
    }

    private void handleEdge(Edge edge, EditPart editPart, boolean sourceAnchor) {
        Anchor anchorToModify;
        if (sourceAnchor) {
            anchorToModify = edge.getSourceAnchor();
        } else {
            anchorToModify = edge.getTargetAnchor();
        }
        String terminalString = GMFNotationUtilities.getTerminalString(0.5d, 0.5d);
        if (anchorToModify instanceof IdentityAnchor) {
            terminalString = ((IdentityAnchor) anchorToModify).getId();
        }
        PrecisionPoint anchorPoint = BaseSlidableAnchor.parseTerminalString(terminalString);
        PrecisionPoint newPoint = computeNewAnchor(anchorPoint, editPart);
        String newTerminalString = new SlidableAnchor(null, newPoint).getTerminal();
        if (anchorToModify instanceof IdentityAnchor) {
            ((IdentityAnchor) anchorToModify).setId(newTerminalString);
        } else if (anchorToModify == null) {
            // Create a new one
            IdentityAnchor newAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
            newAnchor.setId(newTerminalString);
            if (sourceAnchor) {
                edge.setSourceAnchor(newAnchor);
            } else {
                edge.setTargetAnchor(newAnchor);
            }
        }
    }

    private PrecisionPoint computeNewAnchor(PrecisionPoint currentAnchorPoint, EditPart editPart) {

        double scale = GraphicalHelper.getZoom(editPart);
        IFigure figure = ((IGraphicalEditPart) editPart).getFigure();
        Rectangle bounds = figure.getBounds();
        if (figure instanceof HandleBounds) {
            bounds = ((HandleBounds) figure).getHandleBounds();
        }

        Point currentRelativePoint = getAnchorRelativePoint(currentAnchorPoint, bounds);

        if (futureSize != null && delta != null) {
            // In case of border node, the real location is computed earlier
            // (according to BorderItemLocator). The corresponding futureSize
            // and delta are used instead of the request data.
            return new PrecisionPoint(((double) (currentRelativePoint.x - delta.x)) / futureSize.width, ((double) (currentRelativePoint.y - delta.y)) / futureSize.height);
        } else {

            double logicalWidthDelta = request.getSizeDelta().width / scale;
            double logicalHeightDelta = request.getSizeDelta().height / scale;

            int direction = request.getResizeDirection();

            double newRelativeX = computeNewXRelativeLocation(direction, currentRelativePoint, logicalWidthDelta);
            double newRelativeY = computeNewYRelativeLocation(direction, currentRelativePoint, logicalHeightDelta);

            return new PrecisionPoint(newRelativeX / (bounds.width() + logicalWidthDelta), newRelativeY / (bounds.height() + logicalHeightDelta));
        }
    }

    private Point getAnchorRelativePoint(PrecisionPoint currentAnchorPoint, Rectangle bounds) {
        return new PrecisionPoint(bounds.width() * currentAnchorPoint.preciseX(), bounds.height() * currentAnchorPoint.preciseY());
    }

    private double computeNewXRelativeLocation(int direction, Point currentRelativePoint, double logicalWidthDelta) {

        if (direction == PositionConstants.NORTH_WEST || direction == PositionConstants.WEST || direction == PositionConstants.SOUTH_WEST) {
            return currentRelativePoint.preciseX() + logicalWidthDelta;
        } else {

            return currentRelativePoint.preciseX();
        }
    }

    private double computeNewYRelativeLocation(int direction, Point currentRelativePoint, double logicalHeightDelta) {

        if (direction == PositionConstants.NORTH_WEST || direction == PositionConstants.NORTH || direction == PositionConstants.NORTH_EAST) {
            return currentRelativePoint.preciseY() + logicalHeightDelta;
        } else {
            return currentRelativePoint.preciseY();
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
