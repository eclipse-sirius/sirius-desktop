/*******************************************************************************
 * Copyright (c) 2007 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.command;

import org.eclipse.gef.commands.Command;

/**
 * A command useful when you want to avoid a deletion, because if a deletion
 * command can not execute, GMF builds a new default deletion command.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public final class DoNothingCommand extends Command {

    /**
     * The singleton instance.
     */
    public static final DoNothingCommand INSTANCE = new DoNothingCommand();

    private DoNothingCommand() {
    }

    /**
     * {@inheritDoc}
     * 
     * @return <code>true</code>
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @return <code>true</code>
     * @see org.eclipse.gef.commands.Command#canUndo()
     */
    @Override
    public boolean canUndo() {
        return true;
    }

}
