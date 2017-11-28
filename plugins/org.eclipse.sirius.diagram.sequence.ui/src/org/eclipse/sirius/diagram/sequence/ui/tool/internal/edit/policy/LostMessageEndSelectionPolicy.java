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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.internal.requests.ChangeBoundsDeferredRequest;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LostMessageEndEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirResizableEditPolicy;

import com.google.common.base.Preconditions;

/**
 * This policy controls the moves of {@link LostMessageEndEditPart}s.
 * 
 * @author mporhel
 */
public class LostMessageEndSelectionPolicy extends AirResizableEditPolicy {

    private static List<Integer> handledAlignments = new ArrayList<>();
    {
        handledAlignments.add(PositionConstants.LEFT);
        handledAlignments.add(PositionConstants.CENTER);
        handledAlignments.add(PositionConstants.RIGHT);
    }

    /**
     * Overridden to validate the host type.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void setHost(EditPart host) {
        Preconditions.checkArgument(host instanceof LostMessageEndEditPart);
        super.setHost(host);
    }

    /**
     * Convenience method to return the host part with the correct type.
     * 
     * @return returns the host of this policy, with the appropriate type.
     */
    protected LostMessageEndEditPart getLostMessageEnd() {
        return (LostMessageEndEditPart) getHost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        Command result = UnexecutableCommand.INSTANCE;

        if (handledAlignments.contains(request.getAlignment())) {
            result = super.getAlignCommand(request);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        cancelVerticalMoveDelta(request);
        return super.getMoveCommand(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveDeferredCommand(ChangeBoundsDeferredRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        cancelVerticalMoveDelta(request);
        super.showChangeBoundsFeedback(request);
    }

    /**
     * Cancel vertical changes of the given request.
     * 
     * @param request
     *            a request.
     */
    protected void cancelVerticalMoveDelta(ChangeBoundsRequest request) {
        if (request == null) {
            return;
        }

        RequestQuery query = new RequestQuery(request);
        if (query.isMove()) {
            Point moveDelta = request.getMoveDelta().getCopy();
            if (moveDelta != null) {
                request.setMoveDelta(new Point(moveDelta.x, 0));
            }
        }
    }
}
