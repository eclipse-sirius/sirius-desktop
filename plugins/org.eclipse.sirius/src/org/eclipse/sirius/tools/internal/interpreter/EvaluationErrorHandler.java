/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo.
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
package org.eclipse.sirius.tools.internal.interpreter;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Allows client code to decide how errors occuring during expression evaluation should be treated.
 * 
 * @author pcdavid
 */
public abstract class EvaluationErrorHandler {
    /**
     * Standard handler that ignores any exception.
     */
    public static final EvaluationErrorHandler IGNORE = new EvaluationErrorHandler() {
        @Override
        public void handleException(Exception ex) {
            // Ignore.
        }
    };

    /**
     * Standard handler that triggers a rollback of the current transaction.
     */
    public static final EvaluationErrorHandler ROLLBACK = new EvaluationErrorHandler() {
        @Override
        public void handleException(Exception ex) {
            requestTransactionRollback(ex);
        }
    };

    /**
     * Invoked when an expression's evaluation throws an exception.
     * 
     * @param ex
     *            the unexpected exception which occurred during an operation.
     * @throws RuntimeException
     *             if the handler decides that the correct behavior is to throw a real exception (the original one or
     *             another).
     */
    public abstract void handleException(Exception ex);

    /**
     * Can be used to log an error in the system log.
     * 
     * @param message
     *            the message to log.
     * @param ex
     *            the error.
     */
    protected void logError(String message, Exception ex) {
        SiriusPlugin.getDefault().error(message, ex);
    }

    /**
     * Can be used to log a warning in the system log.
     * 
     * @param message
     *            the message to log.
     * @param ex
     *            the error.
     */
    protected void logWarning(String message, Exception ex) {
        SiriusPlugin.getDefault().warning(message, ex);
    }

    /**
     * Can be used when the exception occured inside an EMF Transaction to request all the changes previously made in
     * the transaction to be rolled back. This throws an exception and thus should be the last method called from
     * {@link #handleException(EObject, String, Exception)}.
     * 
     * @param cause
     *            the root cause.
     */
    protected void requestTransactionRollback(Exception cause) {
        RuntimeException cancel = new OperationCanceledException();
        cancel.initCause(cause);
        throw cancel;
    }

}
