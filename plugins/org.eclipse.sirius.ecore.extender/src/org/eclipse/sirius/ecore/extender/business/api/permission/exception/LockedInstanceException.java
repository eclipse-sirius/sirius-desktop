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
package org.eclipse.sirius.ecore.extender.business.api.permission.exception;

import org.eclipse.emf.ecore.EObject;

/**
 * {@link Exception} thrown when an instance nobody should be able to change
 * suffered a tentative change.
 * 
 * @author cbrun
 * 
 */
public class LockedInstanceException extends RuntimeException {

    /**
     * The default permission issue message.
     */
    public static final String PERMISSION_ISSUE_MESSAGE = "An instance is locked and should not be modified : ";

    private static final long serialVersionUID = 1L;

    /**
     * The locked element that the user tried to modify.
     */
    private final EObject lockedElement;

    /**
     * Build the exception.
     * 
     * @param lockedElement
     *            the element that user tried to modify
     */
    public LockedInstanceException(final EObject lockedElement) {
        super(PERMISSION_ISSUE_MESSAGE + lockedElement);
        this.lockedElement = lockedElement;
    }

    /**
     * Builds the exception.
     * 
     * @param message
     *            a message explaining the cause of the error
     */
    public LockedInstanceException(final String message) {
        super(message);
        this.lockedElement = null;
    }

    /**
     * Returns the locked element that the user tried to modify.
     * 
     * @return the locked element that the user tried to modify
     */
    public EObject getLockedElement() {
        return lockedElement;
    }
}
