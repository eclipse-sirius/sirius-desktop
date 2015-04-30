/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.ResetOriginChangeModelOperation;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

/**
 * An EditPolicy that returns a ResetOrigin Command.
 * 
 * @author Florian Barbin
 * 
 */
public class ResetOriginEditPolicy extends AbstractEditPolicy {

    @Override
    public boolean understandsRequest(Request req) {
        return RequestConstants.REQ_RESET_ORIGIN.equals(req.getType());
    }

    @Override
    public Command getCommand(Request request) {
        if (understandsRequest(request)) {
            EditPart editPart = getHost();
            ResetOriginChangeModelOperation operation = null;
            if (editPart instanceof DiagramEditPart) {
                operation = new ResetOriginChangeModelOperation((DiagramEditPart) editPart);
            } else if (editPart instanceof AbstractDiagramContainerEditPart) {

                AbstractDiagramContainerEditPart adcp = (AbstractDiagramContainerEditPart) editPart;
                if (!adcp.isRegionContainer()) {
                    operation = new ResetOriginChangeModelOperation((AbstractDiagramContainerEditPart) editPart);
                }
            }
            if (operation != null) {
                ICommand command = CommandFactory.createICommand(((IGraphicalEditPart) editPart).getEditingDomain(), operation);
                return new ICommandProxy(command);
            }
        }
        return super.getCommand(request);
    }
}
