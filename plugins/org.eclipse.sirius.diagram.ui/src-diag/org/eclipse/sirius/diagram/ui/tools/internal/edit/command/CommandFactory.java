/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.edit.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Helper class to convert an {@link AbstractModelChangeOperation}s into either
 * a plain EMF Transaction {@link RecordingCommand} or a GMF-compatible
 * {@link ICommand}.
 *
 * @author pcdavid
 */
public final class CommandFactory {
    private CommandFactory() {
        // Prevent instantiation.
    }

    /**
     * Converts these operations into an EMF Transaction recording command.
     *
     * @param ted
     *            the editing domain in which to command will execute.
     * @param operations
     *            the array of operation to convert.
     *
     * @param <T>
     *            the return type of the operation.
     * @return a recording command which will execute this operation.
     */
    public static <T> RecordingCommand createRecordingCommand(TransactionalEditingDomain ted, final Collection<AbstractModelChangeOperation<T>> operations) {
        String name = operations.isEmpty() ? Messages.CommandFactory_doNothingLabel : operations.iterator().next().getName();
        return new RecordingCommandsExecutor<T>(ted, name, operations);
    }

    /**
     * Converts this operation into an EMF Transaction recording command.
     *
     * @param ted
     *            the editing domain in which to command will execute.
     * @param operation
     *            the operation to convert.
     *
     * @param <T>
     *            the return type of the operation.
     * @return a recording command which will execute this operation.
     */
    public static <T> RecordingCommand createRecordingCommand(TransactionalEditingDomain ted, final AbstractModelChangeOperation<T> operation) {
        return CommandFactory.createRecordingCommand(ted, Collections.singleton(operation));
    }

    /**
     * Converts these operations into an EMF Transaction recording command.
     *
     * @param ted
     *            the editing domain in which to command will execute.
     * @param operations
     *            the array of operation to convert.
     *
     * @param <T>
     *            the return type of the operation.
     * @return a recording command which will execute this operation.
     */
    public static <T> RecordingCommand createRecordingCommand(TransactionalEditingDomain ted, final AbstractModelChangeOperation<T>... operations) {
        return CommandFactory.createRecordingCommand(ted, Arrays.asList(operations));
    }

    /**
     * Converts these operations into a GMF-compatible ICommand.
     *
     * @param ted
     *            the editing domain in which to command will execute.
     * @param operations
     *            the operations to convert.
     *
     * @param <T>
     *            the return types of the operations.
     * @return a GMF-compatible command which will execute this operation.
     */
    public static <T> ICommand createICommand(TransactionalEditingDomain ted, final Collection<AbstractModelChangeOperation<T>> operations) {
        String name = operations.isEmpty() ? Messages.CommandFactory_doNothingLabel : operations.iterator().next().getName();
        return new AbstractTransactionalCommand(ted, name, null) {
            @Override
            protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                List<T> results = new ArrayList<T>();
                for (AbstractModelChangeOperation<T> operation : operations) {
                    T value = operation.execute();
                    if (value != null) {
                        results.add(value);
                    }
                }
                return CommandResult.newOKCommandResult(results.size() == 1 ? results.get(0) : results);
            }
        };
    }

    /**
     * Converts this operation into a GMF-compatible ICommand.
     *
     * @param ted
     *            the editing domain in which to command will execute.
     * @param operation
     *            the operation to convert.
     *
     * @param <T>
     *            the return type of the operation.
     * @return a GMF-compatible command which will execute this operation.
     */
    public static <T> ICommand createICommand(TransactionalEditingDomain ted, final AbstractModelChangeOperation<T> operation) {
        return CommandFactory.createICommand(ted, Collections.singleton(operation));
    }

    /**
     * Converts these operations into a GMF-compatible ICommand.
     *
     * @param ted
     *            the editing domain in which to command will execute.
     * @param operations
     *            the operations to convert.
     *
     * @param <T>
     *            the return types of the operations.
     * @return a GMF-compatible command which will execute this operation.
     */
    public static <T> ICommand createICommand(TransactionalEditingDomain ted, final AbstractModelChangeOperation<T>... operations) {
        return CommandFactory.createICommand(ted, Arrays.asList(operations));
    }
}
