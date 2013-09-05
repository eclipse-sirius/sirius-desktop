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
package org.eclipse.sirius.ui.tools.internal.actions.repair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.provider.SiriusEditPlugin;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;

/**
 * Validator to validate the environment before launching the repair action and
 * eventually ask questions to the user to passed in valid environment.
 * 
 * A valid environment is an environment with no session open.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RepresentationFilesRepairValidator {

    /**
     * The name of the dialog which ask question.
     */
    public static final String MESSAGE_TITLE = "Repair confirmation";

    private static final int OPERATION_CANCELED = 0;

    /**
     * Validates the environment for launching a repair action.
     * 
     * Close all the opened sessions :
     * <UL>
     * <LI>Inform the users of the closing of the sessions</LI>
     * <LI>Ask them to save if needed</LI>
     * <LI>Possibility of cancel the repair</LI>
     * </UL>
     * 
     * @param currentFileToMigrate
     *            File to repair.
     * 
     * @return the status of validation. The {@link IStatus#getSeverity
     *         severity} of the result indicates whether validation passed or
     *         (how badly it) failed. Normally, the result is a
     *         {@link IStatus#isMultiStatus multi-status} whose children are the
     *         results of individual constraint evaluations
     */
    public IStatus validate(IFile currentFileToMigrate) {
        IStatus resultStatus = null;

        Collection<Session> openedSessions = SessionManager.INSTANCE.getSessions();
        Collection<String> dirtySessionsName = new ArrayList<String>();
        if (openedSessions.size() > 0) {
            for (Session session : openedSessions) {
                if (SessionStatus.DIRTY == session.getStatus()) {
                    dirtySessionsName.add(session.toString());
                }
            }

            boolean saveSessions;
            try {
                saveSessions = userValidation(dirtySessionsName);
                closeSessions(saveSessions);
            } catch (CoreException e) {
                resultStatus = e.getStatus();
            }
        }
        if (resultStatus == null) {
            resultStatus = Status.OK_STATUS;
        }
        return resultStatus;
    }

    /**
     * @param dirtySessionsName
     *            The dirty sessions names
     * @return true if the dirty sessions must be saved, false otherwise
     * @throws CoreException
     *             If the user cancel the process
     */
    private boolean userValidation(Collection<String> dirtySessionsName) throws CoreException {
        // Get the active shell
        Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        if (shell == null) {
            shell = new Shell();
        }

        boolean saveSessions = false;
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        StringBuffer message = new StringBuffer("It's impossible to launch the \"" + repairActionLabel
                + "\" action with opened representations file. So they will be closed before the repair process.");
        if (dirtySessionsName.size() > 0) {
            message.append(" The following representations file");
            if (dirtySessionsName.size() == 1) {
                message.append(" has");
            } else {
                message.append("s have");
            }
            message.append(" been modified : ");
            for (String dirtySessionName : dirtySessionsName) {
                message.append("\n\t- '");
                message.append(dirtySessionName);
                message.append("'");
                message.append(", ");
            }
            message.delete(message.length() - 2, message.length() - 1);
            message.append("\n\nSave changes?");
            String[] buttons = new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL };
            final MessageDialog dialog = new MessageDialog(shell, MESSAGE_TITLE, null, message.toString(), MessageDialog.QUESTION, buttons, 0);
            int result = dialog.open();
            if (result == SWT.DEFAULT || buttons[result].equals(IDialogConstants.CANCEL_LABEL)) {
                throw new CoreException(new Status(IStatus.CANCEL, SiriusEditPlugin.ID, RepresentationFilesRepairValidator.OPERATION_CANCELED, "Migration canceled by user.", null));
            }
            if (buttons[result].equals(IDialogConstants.YES_LABEL)) {
                saveSessions = true;
            }
        } else {
            message.append("\nDo you want to continue?");
            if (!MessageDialog.openConfirm(shell, MESSAGE_TITLE, message.toString())) {
                throw new CoreException(new Status(IStatus.CANCEL, SiriusEditPlugin.ID, RepresentationFilesRepairValidator.OPERATION_CANCELED, "Migration canceled by user.", null));
            }
        }
        return saveSessions;
    }

    /**
     * Close all the opened sessions.
     * 
     * @param saveSessions
     *            true to save the session before closing, false to close
     *            without saving
     */
    private void closeSessions(boolean saveSessions) {
        Collection<IEditingSession> editingSessions = SessionUIManager.INSTANCE.getUISessions();
        for (IEditingSession editingSession : editingSessions) {
            editingSession.close(saveSessions);
        }
        List<Session> openedSessions = new ArrayList<Session>(SessionManager.INSTANCE.getSessions());
        for (Session session : openedSessions) {
            final IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);
            if (ui != null) {
                ui.close(saveSessions);
                SessionUIManager.INSTANCE.remove(ui);
            }
            if (session.isOpen()) {
                if (saveSessions) {
                    session.save();
                }
                session.close();
            }
        }
    }
}
