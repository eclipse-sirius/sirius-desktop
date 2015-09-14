/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IEditableEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * In case the diagram is frozen we allow the refresh (enable editMode and
 * launch refresh). For diagramEditPart, it stay in enable editMode. For other
 * editPart, we restore the old editMode value if the refreshRequest returns a
 * null or unexecutable command.
 * 
 * @author lredor
 * 
 */
public class RefreshRunnableWithProgress implements IRunnableWithProgress {
    Collection<EditPart> editPartsToRefresh;

    /**
     * Default constructor.
     * 
     * @param editPartsToRefresh
     *            The edit parts to refresh
     */
    public RefreshRunnableWithProgress(Collection<EditPart> editPartsToRefresh) {
        this.editPartsToRefresh = editPartsToRefresh;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
            monitor.beginTask(Messages.RefreshRunnableWithProgress_taskName, editPartsToRefresh.size());
            Collection<Command> commandsToExecute = new ArrayList<Command>();
            for (final EditPart editPartToRefresh : editPartsToRefresh) {
                if (editPartToRefresh instanceof IDDiagramEditPart) {
                    refreshFromDiagramEditPart((IDDiagramEditPart) editPartToRefresh, monitor);
                } else {
                    Option<Command> refreshEditPartCommand = refreshFromEditPart(editPartToRefresh, monitor);
                    if (refreshEditPartCommand.some()) {
                        commandsToExecute.add(refreshEditPartCommand.get());
                    }
                }
            }
            // to avoid to execute one command per edit part, we execute them
            // inside only one compound command
            executeInCompoundCommand(commandsToExecute, monitor);
        } finally {
            monitor.done();
        }
    }

    private IDiagramEditDomain getFirstEditingDomain() {
        for (EditPart editPart : editPartsToRefresh) {
            if (editPart instanceof IDiagramElementEditPart) {
                return ((IDiagramElementEditPart) editPart).getDiagramEditDomain();
            }
        }
        return null;
    }

    private void executeInCompoundCommand(Collection<Command> commandsToExecute, IProgressMonitor monitor) {
        if (!commandsToExecute.isEmpty()) {
            IDiagramEditDomain domain = getFirstEditingDomain();
            CompoundCommand compoundCommand = new CompoundCommand(Messages.RefreshRunnableWithProgress_commandLabel);
            for (Command command : commandsToExecute) {
                compoundCommand.add(command);
            }
            domain.getDiagramCommandStack().execute(compoundCommand);
            monitor.worked(commandsToExecute.size());
        }
    }

    /**
     * Refresh the diagram (enable editMode and launch refresh).
     * 
     * @param dDiagramEditPart
     *            The diagram edit part to refresh.
     * @param progressMonitor
     *            Progress monitor to use.
     */
    private void refreshFromDiagramEditPart(IDDiagramEditPart dDiagramEditPart, IProgressMonitor progressMonitor) {
        // Enable edit mode for the diagram.
        dDiagramEditPart.enableEditMode();
        Option<DDiagram> dDiagramOption = dDiagramEditPart.resolveDDiagram();
        if (dDiagramOption.some()) {
            DDiagram dDiagram = dDiagramOption.get();
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
            domain.getCommandStack().execute(new RefreshRepresentationsCommand(domain, new SubProgressMonitor(progressMonitor, 1), dDiagram));
        }
    }

    /**
     * Refresh this editPart: enable editMode, returns the refresh command if
     * the refresh command is executable. Otherwise, restore the old editMode
     * state if it is disabled before.
     * 
     * @param editPart
     *            The edit part to refresh.
     * @param progressMonitor
     *            Progress monitor to use.
     * @return the optional command.
     */
    private Option<Command> refreshFromEditPart(EditPart editPart, IProgressMonitor progressMonitor) {
        Option<Boolean> oldEditModeState = Options.newNone();
        if (editPart instanceof IEditableEditPart) {
            // Keep the old editMode state for editPart and activate editMode.
            IEditableEditPart editableEditPart = (IEditableEditPart) editPart;
            oldEditModeState = Options.newSome(editableEditPart.isEditModeEnabled());
            editableEditPart.enableEditMode();
        }
        // Get the command from refresh request on the "enabledMode" editPart.
        final Request refreshRequest = new GroupRequest(RequestConstants.REQ_REFRESH_VIEWPOINT);
        Command refreshCmd = editPart.getCommand(refreshRequest);
        if (refreshCmd != null && refreshCmd.canExecute() && editPart instanceof IGraphicalEditPart) {
            return Options.newSome(refreshCmd);
        } else if (editPart instanceof IEditableEditPart && oldEditModeState.get() && !oldEditModeState.some()) {
            // Restore the old editMode state (if disable)
            progressMonitor.worked(1);
            ((IEditableEditPart) editPart).disableEditMode();
        }
        return Options.newNone();
    }
}
