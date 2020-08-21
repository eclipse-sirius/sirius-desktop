/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.api.query;

import org.eclipse.core.runtime.Assert;

/**
 * Queries on IllegalStateException.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 *
 */
public class IllegalStateExceptionQuery {

    /**
     * The name of a specific net4j Exception, sub class of IllegalStateException but necessary to detect in the Sirius
     * core code.
     */
    private static final String LIFECYCLE_EXCEPTION_NAME = "org.eclipse.net4j.util.lifecycle.LifecycleException"; //$NON-NLS-1$

    /** The message of a LifeCycleExpcetion when the server is not reachable. */
    private static final String NOT_ACTIVE_CDOTRANSACTION_MESSAGE = "Not active: CDOTransactionImpl"; //$NON-NLS-1$

    private final IllegalStateException exception;

    /**
     * Constructor.
     * 
     * @param exception
     *            the exception to query.
     */
    public IllegalStateExceptionQuery(IllegalStateException exception) {
        Assert.isNotNull(exception);
        this.exception = exception;
    }
    
    /**
     * In Sirius, used in a team environment, the resource, and all "contained" EObject, can be unreachable, most of the
     * time because the connection with the server has been lost. In this case a
     * "org.eclipse.net4j.util.lifecycle.LifecycleException" is thrown with the specific message "Not active:
     * CDOTransactionImpl". This method allows to verify that we are in this specific situation.
     *
     * @return true if the exception corresponds to the Exception thrown when the server is not reachable, false
     *         otherwise.
     */
    public boolean isAConnectionLostException() {
        return exception.getClass().getName().equals(LIFECYCLE_EXCEPTION_NAME) && NOT_ACTIVE_CDOTRANSACTION_MESSAGE.equals(exception.getMessage());
    }
}
