/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;

/**
 * Delete from diagram edit policy.
 * 
 * @author ymortier
 */
public class DeleteFromDiagramEditPolicy extends AbstractEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return req.getType() != null && req.getType().equals(RequestConstants.REQ_DELETE_FROM_DIAGRAM);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        if (request.getType() != null && request.getType().equals(RequestConstants.REQ_DELETE_FROM_DIAGRAM)) {
            if (this.getHost() instanceof IGraphicalEditPart) {
                final IGraphicalEditPart gmfEditPart = (IGraphicalEditPart) this.getHost();
                final EObject element = gmfEditPart.resolveSemanticElement();
                if (element instanceof DDiagramElement) {
                    final DestroyElementRequest destroyRequest = new AirDestroyElementRequest(gmfEditPart.getEditingDomain(), false, false);
                    final Command destroyView = gmfEditPart.getCommand(new EditCommandRequestWrapper(destroyRequest, Collections.EMPTY_MAP));
                    return destroyView;

                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (request.getType() != null && request.getType().equals(RequestConstants.REQ_DELETE_FROM_DIAGRAM)) {
            return this.getHost();
        }
        return super.getTargetEditPart(request);
    }
}
