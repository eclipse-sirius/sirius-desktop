/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.AutoSizeAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Overrides the GMF AutoSizeAction to authorize auto-size for region container.
 * 
 * @author fbarbin
 *
 */
@SuppressWarnings("restriction")
public class SiriusAutoSizeAction extends AutoSizeAction {

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the {@link IWorkbenchPage}
     */
    public SiriusAutoSizeAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    /**
     * Duplicated from org.eclipse.gmf.runtime.diagram.ui.actions.internal.AutoSizeAction.getCommand(Request) to
     * authorized autoSize for container with regions.
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected Command getCommand(Request request) {
        boolean foundNonAutosizedPart = false;
        List operationSet = getOperationSet();
        Iterator editParts = operationSet.iterator();
        CompoundCommand command = new CompoundCommand(getCommandLabel());
        while (editParts.hasNext()) {
            EditPart editPart = (EditPart) editParts.next();
            // check if the editpart is autosized
            if (editPart instanceof GraphicalEditPart) {
                GraphicalEditPart graphicalEditPart = (GraphicalEditPart) editPart;
                foundNonAutosizedPart = !new EditPartQuery(graphicalEditPart).isAutoSized();
            }

            Command curCommand = editPart.getCommand(request);
            if (curCommand != null) {
                command.add(curCommand);
            }
        }
        return command.isEmpty() || command.size() != operationSet.size() || !foundNonAutosizedPart ? UnexecutableCommand.INSTANCE : (Command) command;
    }
}
