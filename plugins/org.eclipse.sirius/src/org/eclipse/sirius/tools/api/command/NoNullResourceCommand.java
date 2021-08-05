/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.api.command;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.tools.api.Messages;

/**
 * A command wrapper to avoid execution of command if a resource is null.
 * 
 * @author mchauvin
 */
public class NoNullResourceCommand extends AbstractCommand implements IAdaptable {

    private final Command wrappedCommand;

    private final EObject element;

    /**
     * Default constructor.
     * 
     * @param cmd
     *            the command to wrap
     * @param element
     *            the element to test
     * 
     *            throw an exception if command is an instance of {@link RecordingCommand} and not an instance of
     *            {@link SiriusCommand}
     */
    public NoNullResourceCommand(Command cmd, EObject element) {
        if (cmd instanceof RecordingCommand && !(cmd instanceof DCommand)) {
            throw new UnsupportedOperationException(Messages.NoNullResourceCommand_instanceErrorMsg);
        }
        this.wrappedCommand = cmd;
        this.element = element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute() {
        if (wrappedCommand != null) {
            return this.wrappedCommand.canExecute();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        if (element != null && element.eResource() != null) {
            this.wrappedCommand.execute();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {
        this.wrappedCommand.undo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canUndo() {
        return this.wrappedCommand.canUndo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redo() {
        this.wrappedCommand.redo();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
        Object object = null;
        if (adapter != null) {
            if (adapter.isInstance(this.wrappedCommand)) {
                object = this.wrappedCommand;
            } else if (this.wrappedCommand instanceof IAdaptable) {
                object = ((IAdaptable) this.wrappedCommand).getAdapter(adapter);
            }
        }
        return object;
    }
}
