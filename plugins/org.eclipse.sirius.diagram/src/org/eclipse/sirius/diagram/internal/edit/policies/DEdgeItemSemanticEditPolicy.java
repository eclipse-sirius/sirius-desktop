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
package org.eclipse.sirius.diagram.internal.edit.policies;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import org.eclipse.sirius.diagram.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.internal.edit.commands.DEdgeReorientCommand;
import org.eclipse.sirius.diagram.internal.edit.parts.DEdgeEditPart;

/**
 * @was-generated
 */
public class DEdgeItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

    /**
     * @was-generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return getGEFWrapper(new DestroyElementCommand(req));
    }

    /**
     * Returns command to reorient EClass based link. New link target or source
     * should be the domain model element associated with this node.
     * 
     * @was-generated NOT
     */
    protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
        switch (getVisualID(req)) {
        case DEdgeEditPart.VISUAL_ID:
        case BracketEdgeEditPart.VISUAL_ID:
            return getGEFWrapper(new DEdgeReorientCommand(req));
        }
        return super.getReorientRelationshipCommand(req);
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
