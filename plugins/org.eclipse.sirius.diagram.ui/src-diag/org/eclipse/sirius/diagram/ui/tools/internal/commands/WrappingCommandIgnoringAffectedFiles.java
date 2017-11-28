/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;

/**
 * ICommand forwarding calls to a wrapped instance, except for the
 * "affected file" which are always returned null.
 * 
 * @author cbrun
 * 
 */
public class WrappingCommandIgnoringAffectedFiles implements ICommand {

    private ICommand original;

    /**
     * Create a new wrapper.
     * 
     * @param wrapped
     *            the wrapped command.
     */
    public WrappingCommandIgnoringAffectedFiles(final ICommand wrapped) {
        this.original = wrapped;
    }

    /**
     * Return the original command wrapped by this command.
     * 
     * @return the original command wrapped by this command.
     */
    public ICommand getOriginalCommand() {
        return original;
    }

    /**
     * {@inheritDoc}
     */
    public IStatus undo(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        IStatus status = null;
        if (original != null) {
            status = original.undo(monitor, info);
        }
        return status;
    }

    /**
     * {@inheritDoc}
     */
    public void removeContext(final IUndoContext context) {
        if (original != null) {
            original.removeContext(context);
        }
    }

    /**
     * {@inheritDoc}
     */
    public IStatus redo(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        IStatus status = null;
        if (original != null) {
            status = original.redo(monitor, info);
        }
        return status;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasContext(final IUndoContext context) {
        boolean hasContext = false;
        if (original != null) {
            hasContext = original.hasContext(context);
        }
        return hasContext;
    }

    /**
     * {@inheritDoc}
     */
    public String getLabel() {
        String label = null;
        if (original != null) {
            label = original.getLabel();
        }
        return label;
    }

    /**
     * {@inheritDoc}
     */
    public IUndoContext[] getContexts() {
        IUndoContext[] contexts = new IUndoContext[0];
        if (original != null) {
            contexts = original.getContexts();
        }
        return contexts;
    }

    /**
     * {@inheritDoc}
     */
    public IStatus execute(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        return original.execute(monitor, info);
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        if (original != null) {
            original.dispose();
            original = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean canUndo() {
        boolean canUndo = false;
        if (original != null) {
            canUndo = original.canUndo();
        }
        return canUndo;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canRedo() {
        boolean canRedo = false;
        if (original != null) {
            canRedo = original.canRedo();
        }
        return canRedo;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canExecute() {
        boolean canExecute = false;
        if (original != null) {
            canExecute = original.canExecute();
        }
        return canExecute;
    }

    /**
     * {@inheritDoc}
     */
    public void addContext(final IUndoContext context) {
        if (original != null) {
            original.addContext(context);
        }

    }

    /**
     * {@inheritDoc}
     */
    public void setLabel(final String label) {
        if (original != null) {
            original.setLabel(label);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ICommand reduce() {
        ICommand command = null;
        if (original != null) {
            command = original.reduce();
        }
        return command;
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult getCommandResult() {
        CommandResult commandResult = null;
        if (original != null) {
            commandResult = original.getCommandResult();
        }
        return commandResult;
    }

    /**
     * {@inheritDoc}
     */
    public List getAffectedFiles() {
        return new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    public ICommand compose(final IUndoableOperation operation) {
        ICommand command = null;
        if (original != null) {
            command = original.compose(operation);
        }
        return command;
    }
}
