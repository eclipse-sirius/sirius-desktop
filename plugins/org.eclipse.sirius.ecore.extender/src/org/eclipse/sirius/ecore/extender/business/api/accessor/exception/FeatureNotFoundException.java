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
package org.eclipse.sirius.ecore.extender.business.api.accessor.exception;

import java.text.MessageFormat;

import org.eclipse.sirius.ecore.extender.business.internal.Messages;

/**
 * Exception launched when a feature is not found on a given
 * {@link org.eclipse.emf.ecore.EObject} instance.
 * 
 * @author cbrun
 * 
 */
public class FeatureNotFoundException extends Exception {
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
    public FeatureNotFoundException(final Exception e) {
        super(e);
    }

    /**
     * Build the {@link Exception}.
     */
    public FeatureNotFoundException() {
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
    public FeatureNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param message
     *            Error message.
     */
    public FeatureNotFoundException(final String message) {
        super(message);
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param cause
     *            source error.
     */
    public FeatureNotFoundException(final Throwable cause) {
        super(cause);
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param featureName
     *            not found feature name.
     * @param typeName
     *            type name.
     */
    public FeatureNotFoundException(final String featureName, final String typeName) {
        this(MessageFormat.format(Messages.FeatureNotFoundException_message, featureName, typeName));
    }

    /**
     * Build the {@link Exception}.
     * 
     * @param featureName
     *            not found feature name.
     * @param typeName
     *            type name.
     * @param cause
     *            source error.
     */
    public FeatureNotFoundException(final String featureName, final String typeName, final Throwable cause) {
        this(MessageFormat.format(Messages.FeatureNotFoundException_message, featureName, typeName), cause);
    }

}
