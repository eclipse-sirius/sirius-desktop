/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.commands;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

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
        this.session = session;
    }

    @Override
    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.CloseUISessionCommand_closeRepresentationFileTask, IProgressMonitor.UNKNOWN);
        if (session != null && session.isOpen()) {
            boolean saveSession = false;
            // TODO remove this try/catch once the offline mode will
            // be supported
            try {
                int choice = ISaveablePart2.YES;
                if (session.getStatus() == SessionStatus.DIRTY) {
                    /* Show a dialog. */
                    choice = SWTUtil.showSaveDialog(session, Messages.CloseUISessionCommand_saveDialogTitle, false);
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
                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, Messages.CloseUISessionCommand_closingError, e));
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
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, Messages.CloseUISessionCommand_closingError, e));
                }
                monitor.worked(1);
            }
        }
        monitor.done();
    }

}
