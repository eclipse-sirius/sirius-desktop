/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.internal.operation.RemoveBendpointsOperation;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Handle the remove bendpoints command.
 *
 * @author Florian Barbin
 *
 */
public class RemoveBendpointsHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {

            List<RecordingCommand> commands = new ArrayList<RecordingCommand>();
            TransactionalEditingDomain ted = computeCommandsToExecute(selection, commands);
            if (commands.size() > 0 && ted != null) {
                CompoundCommand cc = new CompoundCommand(Messages.RemoveBendpointsHandler_cmdLabel);
                for (RecordingCommand command : commands) {
                    cc.append(command);
                }

                ted.getCommandStack().execute(cc);
            }
        }
        return null;
    }

    private TransactionalEditingDomain computeCommandsToExecute(ISelection selection, List<RecordingCommand> commands) {
        TransactionalEditingDomain ted = null;
        Iterator<?> iterator = ((IStructuredSelection) selection).iterator();
        while (iterator.hasNext()) {
            Object currentSelectedObject = iterator.next();
            if (currentSelectedObject instanceof ConnectionNodeEditPart) {
                if (ted == null) {
                    ted = ((ConnectionNodeEditPart) currentSelectedObject).getEditingDomain();
                }
                RecordingCommand command = CommandFactory.createRecordingCommand(ted, new RemoveBendpointsOperation((ConnectionNodeEditPart) currentSelectedObject));
                commands.add(command);
            }
        }
        return ted;
    }

}
