/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.internal.requests.ChangeBoundsDeferredRequest;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ObservationPointEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirResizableEditPolicy;

import com.google.common.base.Preconditions;

/**
 * This policy controls the moves of {@link ObservationPointEditPart}s.
 * 
 * @author mporhel
 */
public class ObservationPointSelectionPolicy extends AirResizableEditPolicy {

    /**
     * Overridden to validate the host type.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void setHost(EditPart host) {
        Preconditions.checkArgument(host instanceof ObservationPointEditPart);
        super.setHost(host);
    }

    /**
     * Convenience method to return the host part with the correct type.
     * 
     * @return returns the host of this policy, with the appropriate type.
     */
    protected ObservationPointEditPart getObservationPoint() {
        return (ObservationPointEditPart) getHost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveDeferredCommand(ChangeBoundsDeferredRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

}
