/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.delete;

import java.util.Collection;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.sirius.diagram.business.api.helper.delete.DeleteHookHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A delete ICommand wrapper to execute deleteHooks.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DeleteWrapperHookExecutorCommand extends AbstractTransactionalCommand {

    private ICommand deleteCommand;

    private Collection<DSemanticDecorator> dSemanticDecoratorsToDelete;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain}
     * @param dSemanticDecoratorsToDelete
     *            the {@link DSemanticDecorator} to delete
     * @param deleteCommand
     *            the original command to execute if deleteHook approves
     */
    public DeleteWrapperHookExecutorCommand(TransactionalEditingDomain domain, Collection<DSemanticDecorator> dSemanticDecoratorsToDelete, ICommand deleteCommand) {
        super(domain, deleteCommand.getLabel(), deleteCommand.getAffectedFiles());
        this.deleteCommand = deleteCommand;
        this.dSemanticDecoratorsToDelete = dSemanticDecoratorsToDelete;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        CommandResult commandResult = null;
        final DeleteHookHelper deleteHookHelper = new DeleteHookHelper(dSemanticDecoratorsToDelete);
        if (deleteHookHelper.checkDeleteHook()) {
            IStatus status = deleteCommand.execute(monitor, info);
            if (status.isOK()) {
                commandResult = CommandResult.newOKCommandResult();
            } else if (status.getSeverity() == IStatus.CANCEL) {
                commandResult = CommandResult.newCancelledCommandResult();
            } else if (status.getSeverity() == IStatus.ERROR) {
                commandResult = CommandResult.newErrorCommandResult(status.getException());
            }
        } else {
            throw new OperationCanceledException();
        }
        return commandResult;
    }
}
