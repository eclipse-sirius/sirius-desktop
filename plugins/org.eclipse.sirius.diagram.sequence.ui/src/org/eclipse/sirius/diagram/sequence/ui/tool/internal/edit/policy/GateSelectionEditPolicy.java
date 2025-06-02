/*******************************************************************************
 * Copyright (c) 2025 CEA.
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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.GateEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceDragEditPartsTrackerEx.SequenceCacheDragTrackerHelper;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SpecificBorderItemSelectionEditPolicy;
import org.eclipse.swt.graphics.Color;

/**
 * Specialization of the default policy for executions, in order to validate and execute the specific resize and move
 * behaviors needed for sequence diagrams.
 * 
 * @author smonnier
 */
public class GateSelectionEditPolicy extends SpecificBorderItemSelectionEditPolicy {

    /**
     * The color to use for the horizontal feedback rules shown when moving/resizing an execution.
     */
    protected static final Color EXECUTION_FEEDBACK_COLOR = SequenceInteractionFeedBackBuilder.ISE_FEEDBACK_COLOR;

    private static final Color CONFLICT_FEEDBACK_COLOR = SequenceInteractionFeedBackBuilder.CONFLICT_FEEDBACK_COLOR;

    private static final Color EXPANSION_FEEDBACK_COLOR = SequenceInteractionFeedBackBuilder.EXPANSION_FEEDBACK_COLOR;

    /**
     * Additional figures for feedback.
     */
    protected Collection<Figure> guides = new ArrayList<>();

    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        if (getHost() instanceof GateEditPart gep && gep.getGate().getMessage().some()) {
            cancelVerticalDelta(request);
        }
        return super.getMoveCommand(request);
    }

    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        if (getHost() instanceof GateEditPart gep && gep.getGate().getMessage().some()) {
            cancelVerticalDelta(request);
        }
        super.showChangeBoundsFeedback(request);
    }

    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        Command result = UnexecutableCommand.INSTANCE;

        return result;
    }

    /**
     * Cancel vertical changes of the given request.
     * 
     * @param request
     *            a request.
     */
    protected void cancelVerticalDelta(ChangeBoundsRequest request) {
        if (request == null) {
            return;
        }

        Point moveDelta = request.getMoveDelta();
        if (moveDelta != null) {
            request.setMoveDelta(new Point(moveDelta.x, 0));
        }

        Dimension sizeDelta = request.getSizeDelta();
        if (sizeDelta != null) {
            request.setSizeDelta(new Dimension(sizeDelta.width, 0));
        }
    }

    @Override
    protected ResizeTracker getResizeTracker(int direction) {
        return new ResizeTracker((GraphicalEditPart) getHost(), direction) {

            @Override
            protected boolean handleButtonUp(int button) {
                SequenceCacheDragTrackerHelper.handleButtonUp((IGraphicalEditPart) getOwner());
                return super.handleButtonUp(button);
            }

            @Override
            protected boolean handleButtonDown(int button) {
                boolean handleButtonDown = super.handleButtonDown(button);
                SequenceCacheDragTrackerHelper.handleButtonDown((IGraphicalEditPart) getOwner());
                return handleButtonDown;
            }
        };
    }
}
