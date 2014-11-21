/*******************************************************************************
 * Copyright (c) 2014, Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

    private final ValidateEditSupport delegate;

    /**
     * Default constructor.
     * 
     * @param delegate
     *            the {@link ValidateEditSupport} to which delegage
     */
    public DelegatingValidateEditSupport(ValidateEditSupport delegate) {
        this.delegate = delegate;
    }

    public ValidateEditSupport getDelegate() {
        return delegate;
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
