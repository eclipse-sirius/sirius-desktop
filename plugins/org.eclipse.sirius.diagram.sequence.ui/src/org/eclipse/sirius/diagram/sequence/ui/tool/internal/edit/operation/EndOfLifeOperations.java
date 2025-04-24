/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Helper class to factor common code for EndOfLife management.
 * 
 * @author mporhel
 */
public final class EndOfLifeOperations {
    private EndOfLifeOperations() {
        // Prevent instantiations.
    }

    /**
     * Shows or updates the lifeline source feedback for the given request.
     * 
     * @param request
     *            request describing the type of feedback.
     * @param endOfLifeEditPart
     *            current {@link EndOfLifeEditPart}.
     * @param moveSource
     *            editpart source fo the current move.
     */
    public static void showEndOfLifeFeedback(Request request, EndOfLifeEditPart endOfLifeEditPart, IGraphicalEditPart moveSource) {
        LifelineEditPart lifelineEditPart = (LifelineEditPart) endOfLifeEditPart.getParent();
        Point location = EndOfLifeOperations.getLocation(request);

        if (location == null) {
            return;
        }

        lifelineEditPart.showSourceFeedback(EndOfLifeOperations.getLifelineResizeRequest(endOfLifeEditPart, lifelineEditPart, location, moveSource));
    }

    /**
     * Erases for the specified {@link Request}. {@link #showSourceFeedback(Request)}.
     * 
     * @param request
     *            the type of feedback that is being erased
     * @param lep
     *            the current {@link LifelineEditPart}.
     */
    public static void eraseEndOfLifeFeedback(LifelineEditPart lep, Request request) {
        Point location = null;
        location = EndOfLifeOperations.getLocation(request);

        if (location == null) {
            return;
        }

        ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_RESIZE);
        cbr.getMoveDelta().setY(location.y - (lep.getFigure().getBounds().y + lep.getFigure().getBounds().height));
        cbr.setEditParts(lep);
        cbr.setLocation(location.getCopy());
        lep.eraseSourceFeedback(cbr);
    }

    /**
     * Compute a request to resize the lifeline, from a request on its end of life.
     * 
     * @param request
     *            the type of feedback that is wanted.
     * @param endOfLifeEditPart
     *            the current {@link EndOfLifeEditPart}.
     * @param moveSource
     *            editpart source fo the current move.
     * @return the computed request.
     */
    public static ChangeBoundsRequest getLifelineResizeRequest(Request request, EndOfLifeEditPart endOfLifeEditPart, IGraphicalEditPart moveSource) {
        Point location = EndOfLifeOperations.getLocation(request);
        if (endOfLifeEditPart.getParent() instanceof LifelineEditPart && location != null) {
            LifelineEditPart lifelineEditPart = (LifelineEditPart) endOfLifeEditPart.getParent();
            return EndOfLifeOperations.getLifelineResizeRequest(endOfLifeEditPart, lifelineEditPart, location, moveSource);
        }
        return null;
    }

    private static ChangeBoundsRequest getLifelineResizeRequest(EndOfLifeEditPart endOfLifeEditPart, LifelineEditPart lifelineEditPart, Point location, IGraphicalEditPart moveSource) {
        GraphicalHelper.screen2logical(location, lifelineEditPart);
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_RESIZE);
        cbr.setResizeDirection(PositionConstants.SOUTH);
        cbr.getSizeDelta().setHeight(EndOfLifeOperations.computeResizeDelta(endOfLifeEditPart, lifelineEditPart, location.y, moveSource));
        cbr.setEditParts(lifelineEditPart);
        cbr.setLocation(location.getCopy());
        return cbr;
    }

    private static int computeResizeDelta(EndOfLifeEditPart endOfLifeEditPart, LifelineEditPart lifelineEditPart, int location, IGraphicalEditPart moveSource) {
        double zoom = GraphicalHelper.getZoom(lifelineEditPart);
        int lifelineLocation = lifelineEditPart.getFigure().getBounds().y + lifelineEditPart.getFigure().getBounds().height;
        int eolMidSize = endOfLifeEditPart.getFigure().getBounds().height / 2;
        int feedbackRangeLimit = EndOfLifeOperations.getFeedBackRangeLimit(location, moveSource, eolMidSize, EndOfLifeOperations.getLastEventPosition(lifelineEditPart));
        return (int) (EndOfLifeOperations.getResizeDelta(lifelineLocation, eolMidSize, feedbackRangeLimit) * zoom);
    }

    private static int getFeedBackRangeLimit(int location, IGraphicalEditPart moveSource, int eolMidSize, int lastEventInTargetInstanceRole) {
        Rectangle sourceBbounds = moveSource.getFigure().getBounds();
        int feedbackRangeLimit = location;
        if (moveSource instanceof SequenceMessageEditPart) {
            if (feedbackRangeLimit < sourceBbounds.y) {
                feedbackRangeLimit = sourceBbounds.y;
            } else if (lastEventInTargetInstanceRole + eolMidSize > location) {
                feedbackRangeLimit = lastEventInTargetInstanceRole + eolMidSize;
            } else if (location > sourceBbounds.y + sourceBbounds.height) {
                feedbackRangeLimit = sourceBbounds.y + sourceBbounds.height;
            }
        }
        return feedbackRangeLimit;
    }

    // limit the vertical move to the first sequence event of the targeted
    // instance role
    private static int getLastEventPosition(LifelineEditPart lifelineEditPart) {
        int lastMessageInTargetInstanceRole = Integer.MIN_VALUE;
        Range occupiedRange = lifelineEditPart.getISequenceEvent().getOccupiedRange();
        if (!occupiedRange.isEmpty()) {
            // limit the move to the first sequence event of the target
            // lifeline
            lastMessageInTargetInstanceRole = occupiedRange.getUpperBound() + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        }
        return lastMessageInTargetInstanceRole;
    }

    private static int getResizeDelta(int lifelineLocation, int eolMidSize, int feedbackRangeLimit) {
        int delta = feedbackRangeLimit;
        delta -= lifelineLocation;
        delta -= eolMidSize;
        return delta;
    }

    private static Point getLocation(Request request) {
        Point location = null;
        if (request instanceof BendpointRequest) {
            BendpointRequest bendpointRequest = (BendpointRequest) request;
            if (bendpointRequest.getLocation() != null) {
                location = bendpointRequest.getLocation().getCopy();
            }
        } else if (request instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            if (changeBoundsRequest.getLocation() != null) {
                location = changeBoundsRequest.getLocation().getCopy();
            }
        }
        return location;
    }
}
