/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.repair;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Validator to validate the environment before launching the repair action and eventually ask questions to the user to
 * passed in valid environment.
 * 
 * A valid environment is an environment with no session open.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RepresentationFilesNeedCloseSessionValidator {

    private static final int OPERATION_CANCELED = 0;

    private String actionName;

    /**
     * Default constructor.
     * 
     * @param actionName
     *            the name of the action that needs to close opened sessions.
     */
    public RepresentationFilesNeedCloseSessionValidator(String actionName) {
        this.actionName = actionName;
    }

    /**
     * Get the name of the dialog which ask question.
     * 
     * @param actionName
     *            the name of the action.
     * 
     * @return the title.
     */
    public static String getMessageTitle(String actionName) {
        return MessageFormat.format(Messages.RepresentationFilesRepairValidator_confirmationDialogTitle, actionName);
    }

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
     * @return the status of validation. The {@link IStatus#getSeverity severity} of the result indicates whether
     *         validation passed or (how badly it) failed. Normally, the result is a {@link IStatus#isMultiStatus
     *         multi-status} whose children are the results of individual constraint evaluations
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
        StringBuffer message = new StringBuffer(MessageFormat.format(Messages.RepresentationFilesRepairValidator_launchImpossibleRepresentationsOpened, actionName));
        if (dirtySessionsName.size() > 0) {
            if (dirtySessionsName.size() == 1) {
                message.append(" ").append(Messages.RepresentationFilesRepairValidator_representationFileModified); //$NON-NLS-1$
            } else {
                message.append(" ").append(Messages.RepresentationFilesRepairValidator_representationFilesModified); //$NON-NLS-1$
            }
            for (String dirtySessionName : dirtySessionsName) {
                message.append(MessageFormat.format("\n\t- ''{0}'', ", dirtySessionName)); //$NON-NLS-1$
            }
            message.delete(message.length() - 2, message.length() - 1);
            message.append("\n\n").append(Messages.RepresentationFilesRepairValidator_askSave); //$NON-NLS-1$
            String[] buttons = new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL };
            final MessageDialog dialog = new MessageDialog(shell, getMessageTitle(actionName), null, message.toString(), MessageDialog.QUESTION, buttons, 0);
            int result = dialog.open();
            if (result == SWT.DEFAULT || buttons[result].equals(IDialogConstants.CANCEL_LABEL)) {
                throw new CoreException(new Status(IStatus.CANCEL, SiriusEditPlugin.ID, RepresentationFilesNeedCloseSessionValidator.OPERATION_CANCELED,
                        Messages.RepresentationFilesRepairValidator_migrationCanceled, null));
            }
            if (buttons[result].equals(IDialogConstants.YES_LABEL)) {
                saveSessions = true;
            }
        } else {
            message.append("\n").append(Messages.RepresentationFilesRepairValidator_askContinue); //$NON-NLS-1$
            if (!MessageDialog.openConfirm(shell, getMessageTitle(actionName), message.toString())) {
                throw new CoreException(new Status(IStatus.CANCEL, SiriusEditPlugin.ID, OPERATION_CANCELED, Messages.RepresentationFilesRepairValidator_migrationCanceled, null));
            }
        }
        return saveSessions;
    }

    /**
     * Close all the opened sessions.
     * 
     * @param saveSessions
     *            true to save the session before closing, false to close without saving
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
                    session.save(new NullProgressMonitor());
                }
                session.close(new NullProgressMonitor());
            }
        }
    }
}
