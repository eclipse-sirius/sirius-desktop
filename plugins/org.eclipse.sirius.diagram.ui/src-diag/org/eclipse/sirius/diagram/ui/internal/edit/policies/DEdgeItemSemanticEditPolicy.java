/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;

/**
 * @was-generated
 */
public class DEdgeItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

    /**
     * As now canonical refresh is done in precommit, we always return true.
     */
    @Override
    protected boolean shouldProceed(DestroyRequest destroyRequest) {
        return true;
    }

    /**
     * @was-generated
     */
    @Override
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return getGEFWrapper(new DestroyElementCommand(req));
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to avoid NPE during reconnection.
     * 
     * @was-generated NOT
     */
    @Override
    protected Command getReorientRelationshipTargetCommand(ReconnectRequest request) {
        if (request.getTarget() instanceof ConnectionEditPart && ((ConnectionEditPart) request.getTarget()).getTarget() != null) {
            return super.getReorientRelationshipTargetCommand(request);
        }
        return null;
    }
}
