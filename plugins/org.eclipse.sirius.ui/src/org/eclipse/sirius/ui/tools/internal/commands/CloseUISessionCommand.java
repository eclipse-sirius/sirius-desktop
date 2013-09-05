/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.commands;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.provider.SiriusEditPlugin;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;

/**
 * Specific command to close the given session.
 * 
 * @author mporhel
 * 
 */
public class CloseUISessionCommand extends WorkspaceModifyOperation {

    private Session session;

    /**
     * * Specific command to close the given session.
     * 
     * @param session
     *            the session to close.
     */
    public CloseUISessionCommand(Session session) {
        super();
        this.session = session;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        monitor.beginTask("Close Representations file", IProgressMonitor.UNKNOWN);
        if (session == null) {
            return;
        }

        if (session.isOpen()) {
            boolean saveSession = false;
            // TODO remove this try/catch once the offline mode will
            // be supported
            try {
                int choice = ISaveablePart2.YES;
                if (session.getStatus() == SessionStatus.DIRTY) {
                    /* Show a dialog. */
                    choice = SWTUtil.showSaveDialog(session, "Representations file", false);
                    saveSession = choice == ISaveablePart2.YES;
                }
                if (choice == ISaveablePart2.CANCEL) {
                    return;
                }
            } catch (IllegalStateException e) {
                // In case the session is a remote session (CDO Session for
                // example)
                // We may encounter an issue to connect to remote CDO server
                // during close process
                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, "Error while closing the session", e));
            }

            final IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);

            if (ui != null) {
                if (saveSession) {
                    if (session.isOpen()) {
                        monitor.worked(1);
                        session.save(new NullProgressMonitor());
                        monitor.worked(1);
                    }
                }

                // TODO remove this try/catch once the offline mode
                // will be supported
                try {
                    ui.close(saveSession);
                } catch (IllegalStateException e) {
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, "Error while closing the session", e));
                }
                monitor.worked(1);
                ui.close();
            }
        }
        monitor.done();
    }

}
