/*******************************************************************************
 * Copyright (c) 2014, 2020 Obeo.
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
package org.eclipse.sirius.ext.emf.tx;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.util.ValidateEditSupport;

/**
 * A {@link ValidateEditSupport} which delegates to another one.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DelegatingValidateEditSupport implements ValidateEditSupport {

    private ValidateEditSupport delegate;

    /**
     * Default constructor.
     * 
     * @param delegate
     *            the {@link ValidateEditSupport} to which delegate
     */
    public DelegatingValidateEditSupport(ValidateEditSupport delegate) {
        this.delegate = delegate;
    }

    public ValidateEditSupport getDelegate() {
        return delegate;
    }

    /**
     * Set the {@link ValidateEditSupport} to which delegate to null.
     */
    public void dispose() {
        this.delegate = null;
    }

    @Override
    public IStatus validateEdit(Transaction transaction, Object context) {
        IStatus status = Status.OK_STATUS;
        if (delegate != null) {
            status = delegate.validateEdit(transaction, context);
        }
        return status;
    }

    @Override
    public void handleResourceChange(Resource resource, Notification notification) {
        if (delegate != null) {
            delegate.handleResourceChange(resource, notification);
        }
    }

    @Override
    public void finalizeForRollback() {
        if (delegate != null) {
            delegate.finalizeForRollback();
        }
    }

    @Override
    public void finalizeForCommit() {
        if (delegate != null) {
            delegate.finalizeForCommit();
        }
    }
}
