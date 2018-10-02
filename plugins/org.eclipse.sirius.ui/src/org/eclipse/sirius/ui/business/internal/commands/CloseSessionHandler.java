/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.commands;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.tools.internal.commands.CloseUISessionCommand;
import org.eclipse.sirius.ui.tools.internal.views.common.ContextMenuFiller;
import org.eclipse.sirius.ui.tools.internal.views.common.FileSessionFinder;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

/**
 * Handler allowing to close a session. Used only for items coming from a viewer without CNF capability. For viewer with
 * CNF capabilities, {@link ContextMenuFiller} is used.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class CloseSessionHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        if (selection instanceof StructuredSelection && ((StructuredSelection) selection).getFirstElement() instanceof IFile) {
            IFile selectedFile = (IFile) ((StructuredSelection) selection).getFirstElement();
            URI fileURI = URI.createPlatformResourceURI(selectedFile.getFullPath().toString(), true);
            List<Session> sessionToClose = new ArrayList<>();

            for (Session session : SessionManager.INSTANCE.getSessions()) {
                if (session.isOpen()) {
                    sessionToClose.add(session);
                }
            }
            for (Session session : sessionToClose) {
                for (Resource res : session.getAllSessionResources()) {
                    if (fileURI.equals(res.getURI())) {
                        CloseUISessionCommand operation = new CloseUISessionCommand(session);
                        try {
                            new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(true, false, operation);
                        } catch (InvocationTargetException | InterruptedException e) {
                            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, MessageFormat.format(Messages.CloseSessionsAction_error, e), e));
                        }
                        if (session.isOpen()) {
                            try {
                                new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(true, false, new IRunnableWithProgress() {
                                    @Override
                                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                                        session.close(SubMonitor.convert(monitor));
                                    }
                                });
                            } catch (InvocationTargetException | InterruptedException e) {
                                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, MessageFormat.format(Messages.CloseSessionsAction_error, e), e));
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEnabled() {
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            Object firstElement = structuredSelection.getFirstElement();
            if (firstElement instanceof IFile) {
                List<Session> relatedSessions = FileSessionFinder.getRelatedSessions(Lists.newArrayList((IFile) firstElement), false, false);
                for (Session session : relatedSessions) {
                    if (session.isOpen()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
