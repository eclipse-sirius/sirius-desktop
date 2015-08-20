/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.api.accessor.exception;

import java.text.MessageFormat;

import org.eclipse.sirius.ecore.extender.business.internal.Messages;

/**
 * Exception launched when a type is illegal as a value for a feature on a given
 * {@link org.eclipse.emf.ecore.EObject} instance.
 * 
 * @author cbrun
 * 
 */
public class IllegalTypeForValueException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Build the Exception from another Error.
     * 
     * @param e
     *            a factory exception.
     */
    public IllegalTypeForValueException(final Exception e) {
        super(e);
    }

    /**
     * Build the {@link Exception}.
     */
    public IllegalTypeForValueException() {
        super();
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param message
     *            error message.
     * @param cause
     *            source error.
     */
    public IllegalTypeForValueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param message
     *            Error message.
     */
    public IllegalTypeForValueException(final String message) {
        super(message);
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param cause
     *            source error.
     */
    public IllegalTypeForValueException(final Throwable cause) {
        super(cause);
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param featureName
     *            not found feature name.
     * @param actualTypeName
     *            actual type name
     * @param exceptedTypeName
     *            type name.
     */
    public IllegalTypeForValueException(final String featureName, final String actualTypeName, final String exceptedTypeName) {
        this(MessageFormat.format(Messages.IllegalTypeForValueException_message, featureName, actualTypeName));
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param featureName
     *            not found feature name.
     * @param actualTypeName
     *            actual type name
     * @param exceptedTypeName
     *            type name
     * @param cause
     *            source error.
     */
    public IllegalTypeForValueException(final String featureName, final String actualTypeName, final String exceptedTypeName, final Throwable cause) {
        this(MessageFormat.format(Messages.IllegalTypeForValueException_message, featureName, actualTypeName), cause);
    }

}
