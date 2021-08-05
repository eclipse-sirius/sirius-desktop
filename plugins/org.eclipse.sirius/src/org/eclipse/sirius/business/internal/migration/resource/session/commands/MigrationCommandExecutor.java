/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.migration.resource.session.commands;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Executes migration {@link Command} to take a minimum of memory space and execution time.
 * 
 * @author esteban
 */
public class MigrationCommandExecutor {

    private static final Map<Object, Object> OPTIONS = new HashMap<Object, Object>();

    static {
        // OPTIONS.put(Transaction.OPTION_NO_TRIGGERS, Boolean.TRUE);
        OPTIONS.put(Transaction.OPTION_NO_VALIDATION, Boolean.TRUE);
        OPTIONS.put(Transaction.OPTION_NO_UNDO, Boolean.TRUE);
    }

    /**
     * Execute the specified {@link Command} to take a minimum of memory to avoid OutOfMemory on Session.save().
     * 
     * @param domain
     *            {@link TransactionalCommandStack}
     * @param command
     *            {@link Command}
     */
    public void execute(TransactionalEditingDomain domain, Command command) {

        TransactionalCommandStack commandStack = (TransactionalCommandStack) domain.getCommandStack();
        try {
            commandStack.execute(command, OPTIONS);
        } catch (InterruptedException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, MessageFormat.format(Messages.MigrationCommandExecutor_migrationErrorMsg, e.getLocalizedMessage())));
        } catch (RollbackException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, MessageFormat.format(Messages.MigrationCommandExecutor_migrationErrorMsg, e.getLocalizedMessage())));
        }
    }
}
