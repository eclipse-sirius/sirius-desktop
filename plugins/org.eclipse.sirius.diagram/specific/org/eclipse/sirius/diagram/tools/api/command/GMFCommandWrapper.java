/*******************************************************************************
 * Copyright (c) 2009, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import org.eclipse.sirius.tools.api.command.IUndoableCommand;

/**
 * Wrap an EMF command into a GMF command.
 * 
 * @author mchauvin
 * @since 2.1
 */
public class GMFCommandWrapper extends AbstractTransactionalCommand {

    /**
     * This constant says whether the undo support should be disabled or not.
     */
    public static final boolean DISABLE_UNDO = false;

    /** The EMF command. */
    private final org.eclipse.emf.common.command.Command emfCommand;

    /**
     * Creates a new <code>GMFCommandWrapper</code>.
     * 
     * @param domain
     *            current editing domain.
     * 
     * @param emfCommand
     *            the EMF command.
     */
    public GMFCommandWrapper(final TransactionalEditingDomain domain, final Command emfCommand) {
        super(domain, "GMF Command Wrapper", null);
        if (emfCommand == null) {
            throw new IllegalArgumentException("the command is null");
        }
        if (domain == null) {
            throw new IllegalArgumentException("the domain is null");
        }

        /* Set the right label */
        final String label = emfCommand.getLabel();
        if (label != null) {
            this.setLabel(label);
        }

        this.emfCommand = emfCommand;
    }

    /**
     * Return the emf command.
     * 
     * @return the emf command.
     */
    public org.eclipse.emf.common.command.Command getEmfCommand() {
        return emfCommand;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.operations.AbstractOperation#canExecute()
     */
    @Override
    public boolean canExecute() {
        return super.canExecute() && this.emfCommand.canExecute();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.workspace.AbstractEMFOperation#canUndo()
     */
    @Override
    public boolean canUndo() {
        return super.canUndo() && this.emfCommand.canUndo();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        this.emfCommand.execute();
        return CommandResult.newOKCommandResult();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doUndo(org.eclipse.core.runtime.IProgressMonitor,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected IStatus doUndo(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        final IStatus status = super.doUndo(monitor, info);
        if (this.emfCommand instanceof IAdaptable) {
            final Object adapter = ((IAdaptable) this.emfCommand).getAdapter(IUndoableCommand.class);
            if (adapter != null) {
                ((IUndoableCommand) adapter).doUndo();
            }
        }
        return status;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doRedo(org.eclipse.core.runtime.IProgressMonitor,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected IStatus doRedo(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        final IStatus status = super.doRedo(monitor, info);
        if (this.emfCommand instanceof IAdaptable) {
            final Object adapter = ((IAdaptable) this.emfCommand).getAdapter(IUndoableCommand.class);
            if (adapter != null) {
                ((IUndoableCommand) adapter).doRedo();
            }
        }
        return status;
    }

}
