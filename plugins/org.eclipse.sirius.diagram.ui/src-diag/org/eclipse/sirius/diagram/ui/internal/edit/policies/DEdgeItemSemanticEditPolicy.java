/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

/**
 * @was-generated
 */
public class DEdgeItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

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
