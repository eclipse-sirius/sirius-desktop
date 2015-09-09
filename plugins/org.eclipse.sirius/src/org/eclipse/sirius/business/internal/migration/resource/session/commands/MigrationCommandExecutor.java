/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Executes migration {@link Command} to take a minimum of memory space and
 * execution time.
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
     * Execute the specified {@link Command} to take a minimum of memory to
     * avoid OutOfMemory on Session.save().
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
