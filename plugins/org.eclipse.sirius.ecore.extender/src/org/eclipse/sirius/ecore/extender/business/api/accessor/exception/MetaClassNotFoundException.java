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
package org.eclipse.sirius.ecore.extender.business.api.accessor.exception;

/**
 * {@link Exception} thrown when a type is not found.
 * 
 * @author cbrun
 * 
 */
public class MetaClassNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Build the exception from a {@link FactoryException}.
     * 
     * @param e
     *            a previous error.
     */
    public MetaClassNotFoundException(final Exception e) {
        super(e);
    }

    /**
     * Build the exception.
     */
    public MetaClassNotFoundException() {
        super();
    }

    /**
     * Build the exception.
     * 
     * @param name
     *            the type name not found.
     */
    public MetaClassNotFoundException(final String name) {
        super(name);
    }

}
