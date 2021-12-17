/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.AbstractNodeEventResizeSelectionValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;

/**
 * Specialization of the default policy for states, in order to customize resize
 * command and feedback.
 * 
 * @author mporhel
 */
public class StateSelectionEditPolicy extends ExecutionSelectionEditPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        updateHorizontalResize(request);
        return super.getResizeCommand(request);
    }

    /*
     * Feedback
     */
    /**
     * Show/update the horizontal feedback lines aligned on the top and bottom
     * of the execution.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        eraseChangeBoundsFeedback(request);

        StateEditPart hostPart = (StateEditPart) getHost();
        AbstractNodeEvent host = (AbstractNodeEvent) hostPart.getISequenceEvent();
        RequestQuery requestQuery = new RequestQuery(request);

        if (requestQuery.isResize()) {
            updateHorizontalResize(request);
            customiseFeedbackFigure(request);

            AbstractNodeEventResizeSelectionValidator validator = AbstractNodeEventResizeSelectionValidator.getOrCreateValidator(request, host);
           validator.validate();
            showResizeFeedBack(request);
            feedBack(validator);
        } else {
            super.showChangeBoundsFeedback(request);
        }
    }

    /**
     * Customize feedback : State figure is centered regarding the parent
     * figure. Don't use BorderItemLocator to locate the feedback.
     * 
     * @param request
     *            current resuqte to feedback.
     */
    private void customiseFeedbackFigure(ChangeBoundsRequest request) {
        final IFigure feedback = getDragSourceFeedbackFigure();
        final Rectangle rect = getInitialFeedbackBounds().getCopy();
        getHostFigure().translateToAbsolute(rect);
        rect.translate(request.getMoveDelta());
        rect.resize(request.getSizeDelta());
        getHostFigure().translateToRelative(rect);

        getHostFigure().translateToAbsolute(rect);
        feedback.translateToRelative(rect);
        feedback.setBounds(rect);
    }

    /**
     * Update the request, to center the horizontal resize.
     * 
     * @param request
     *            the current request.
     */
    private void updateHorizontalResize(ChangeBoundsRequest request) {
        Point moveDelta = new Point(request.getMoveDelta());
        Dimension sizeDelta = new Dimension(request.getSizeDelta());
        RequestQuery requestQuery = new RequestQuery(request);

        if (requestQuery.isResize()) {
            if (moveDelta.x == 0 && sizeDelta.width != 0) {
                moveDelta.setX(-sizeDelta.width);
            }

            if (sizeDelta.width == 0 && moveDelta.x != 0) {
                sizeDelta.setWidth(-moveDelta.x);
            }

            if (moveDelta.x == -sizeDelta.width) {
                sizeDelta.setWidth(sizeDelta.width - moveDelta.x);
            }

            request.setMoveDelta(moveDelta);
            request.setSizeDelta(sizeDelta);
        }
    }
}
