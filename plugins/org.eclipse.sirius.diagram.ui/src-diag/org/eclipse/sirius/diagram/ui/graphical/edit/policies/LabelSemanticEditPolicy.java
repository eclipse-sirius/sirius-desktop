/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;

/**
 * This {@link org.eclipse.gef.EditPolicy} is used to handle the deletion of a
 * label element. It redirect the request to the parent edit part.
 * 
 * @author mporhel
 */
public class LabelSemanticEditPolicy extends SemanticEditPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(Request request) {
        Command command = null;
        if (RequestConstants.REQ_SEMANTIC_WRAPPER.equals(request.getType())) {
            IEditCommandRequest editCommandRequest = ((EditCommandRequestWrapper) request).getEditCommandRequest();
            if (editCommandRequest instanceof DestroyRequest && getHost() instanceof IDiagramNameEditPart) {
                EditPart parent = getHost().getParent();
                if (parent instanceof IDiagramElementEditPart) {
                    command = parent.getCommand(request);
                }
            } else {
                command = getSemanticCommand(editCommandRequest);
            }
        } else {
            command = super.getCommand(request);
        }
        return command;
    }
}
