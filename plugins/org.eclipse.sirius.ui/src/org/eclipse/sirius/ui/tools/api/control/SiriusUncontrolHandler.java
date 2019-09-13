/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.api.control;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.control.SiriusUncontrolCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Implements the UI part of the "Uncontrol" operation. This class gathers the required parameters from the user and the
 * invokes the properly configured {@link org.eclipse.sirius.business.internal.command.control.UncontrolCommand} .
 *
 * @since 0.9.0
 *
 * @author pcdavid
 */
public class SiriusUncontrolHandler extends AbstractHandler {
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final EObject semanticRoot = getSelectedEObject(event);
        if (semanticRoot != null) {
            try {
                new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, new WorkspaceModifyOperation() {

                    @Override
                    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                        try {
                            monitor.beginTask(Messages.SiriusUncontrolHandler_uncontrolTask, 1);
                            performUncontrol(HandlerUtil.getActiveShell(event), semanticRoot, new SubProgressMonitor(monitor, 1));
                        } finally {
                            monitor.done();
                        }
                    }
                });
            } catch (InvocationTargetException e) {
                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
            } catch (InterruptedException e) {
                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
            }

        }
        return null;
    }

    /**
     * Performs the uncontrol operation.
     *
     * @param shell
     *            the shell to use to interact with users.
     * @param semanticRoot
     *            the semantic root element to uncontrol.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of uncontrol
     */
    public void performUncontrol(final Shell shell, final EObject semanticRoot, IProgressMonitor monitor) {
        Session session = SessionManager.INSTANCE.getSession(semanticRoot);
        if (session != null) {
            boolean uncontrolRepresentations = shouldUncontrolRepresentations(shell);
            Command vuc = new SiriusUncontrolCommand(semanticRoot, uncontrolRepresentations, false, new SubProgressMonitor(monitor, 1));
            TransactionUtil.getEditingDomain(semanticRoot).getCommandStack().execute(vuc);
            session.save(new SubProgressMonitor(monitor, 1));

            // Opened editors of uncontrolled representations need an update of
            // the editor input with the new DRepresentation location
            if (uncontrolRepresentations) {
                IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
                for (DialectEditor editor : uiSession.getEditors()) {
                    if (editor instanceof IReusableEditor) {
                        IReusableEditor iReusableEditor = (IReusableEditor) editor;
                        DRepresentation representation = editor.getRepresentation();
                        URI repDescURI = Optional.ofNullable(editor.getEditorInput()).filter(SessionEditorInput.class::isInstance).map(SessionEditorInput.class::cast)
                                .map(SessionEditorInput::getRepDescUri).orElse(null);
                        SessionEditorInput updatedEditorInput = new SessionEditorInput(EcoreUtil.getURI(representation), repDescURI, representation.getName(), session);
                        iReusableEditor.setInput(updatedEditorInput);
                    }
                }
            }
        }
    }

    /**
     * Show dialog to uncontrol or not the representations.
     *
     * @param shell
     *            the shell to use to interact with users.
     * @return if the representations are uncontrolled
     */
    protected boolean shouldUncontrolRepresentations(final Shell shell) {
        return MessageDialog.openQuestion(shell, Messages.SiriusUncontrolHandler_uncontrolRepresentationsTitle, Messages.SiriusUncontrolHandler_uncontrolRepresentationsMessage);
    }

    private EObject getSelectedEObject(final ExecutionEvent event) {
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            final IStructuredSelection iss = (IStructuredSelection) selection;
            final Object obj = iss.getFirstElement();
            if (obj instanceof EObject) {
                return (EObject) obj;
            }
        }
        return null;
    }
}
