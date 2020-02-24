/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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

package org.eclipse.sirius.business.internal.session;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * An global {@link IOperationHistoryListener} for Sirius connected during the first {@link Session} added with
 * {@link org.eclipse.sirius.business.internal.session.SessionManagerImpl#add(Session)} and disconnected with the last
 * session removed with
 * {@link org.eclipse.sirius.business.internal.session.SessionManagerImpl#remove(org.eclipse.sirius.business.api.session.Session)}.<BR>
 * It currently only handles the case where an operation is not OK and with a {@link LockedInstanceException} as cause,
 * to log it.
 * 
 * @author Laurent Redor
 *
 */
public class SiriusOperationHistoryListener implements IOperationHistoryListener {

    @Override
    public void historyNotification(OperationHistoryEvent event) {
        if (event.getEventType() == OperationHistoryEvent.OPERATION_NOT_OK && event.getStatus() != null) {

            if (event.getStatus().isMultiStatus()) {
                for (IStatus status : event.getStatus().getChildren()) {
                    handleLockedInstanceException(status);
                }
            } else {
                handleLockedInstanceException(event.getStatus());
            }
        }
    }

    /**
     * Handle {@link LockedInstanceException} of error status.
     * 
     * @param status
     *            The Status to handle.
     */
    protected void handleLockedInstanceException(IStatus status) {
        Throwable exception = status.getException();
        if (IStatus.ERROR == status.getSeverity() && exception instanceof LockedInstanceException) {
            SiriusPlugin.getDefault().warning(exception.getMessage(), (LockedInstanceException) exception);
        }
    }
}
