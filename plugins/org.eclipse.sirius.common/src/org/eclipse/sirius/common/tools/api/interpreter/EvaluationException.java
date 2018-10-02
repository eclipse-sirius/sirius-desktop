/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.interpreter;

/**
 * This exception is thrown when the model request language interpreted
 * encounter a problem while evaluating an expression.
 * 
 * @author ymortier
 */
public class EvaluationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create the Exception.
     */
    public EvaluationException() {
    }

    /**
     * Create the Exception with a message.
     * 
     * @param message
     *            error message.
     */
    public EvaluationException(final String message) {
        super(message);
    }

    /**
     * Create the Exception from a cause.
     * 
     * @param cause
     *            the cause.
     */
    public EvaluationException(final Throwable cause) {
        super(cause);
    }

    /**
     * Create a new Exception with a cause and a message.
     * 
     * @param message
     *            the message.
     * @param cause
     *            the cause.
     */
    public EvaluationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
