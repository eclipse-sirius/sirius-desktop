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
 */
public class LockedInstanceException extends RuntimeException {

    /** The default permission issue message. */
    public static final String PERMISSION_ISSUE_MESSAGE = "An instance is locked and should not be modified among : ";

    private static final long serialVersionUID = 1L;

    /** The locked element that the user tried to modify. */
    private final EObject[] lockedElements;

    /**
     * Build the exception.
     * 
     * @param lockedElements
     *            the elements that user tried to modify
     */
    public LockedInstanceException(final EObject... lockedElements) {
        super(PERMISSION_ISSUE_MESSAGE + lockedElements);
        this.lockedElements = lockedElements;
    }

    /**
     * Builds the exception.
     * 
     * @param message
     *            a message explaining the cause of the error
     */
    public LockedInstanceException(final String message) {
        super(message);
        lockedElements = null;
    }

    /**
     * Returns the first locked element that the user tried to modify.
     * 
     * @return the first locked element that the user tried to modify
     */
    public EObject getLockedElement() {
        return lockedElements != null && lockedElements.length > 0 ? lockedElements[0] : null;
    }

    /**
     * Returns the locked elements that the user tried to modify.
     * 
     * @return the locked elements that the user tried to modify
     */
    public EObject[] getLockedElements() {
        return lockedElements;
    }
}
