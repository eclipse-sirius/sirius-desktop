/*******************************************************************************
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.export;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.ui.tools.api.actions.export.ExportAction;
import org.eclipse.sirius.ui.tools.api.dialogs.ExportSeveralRepresentationsAsImagesDialog;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * An eclipse action to export all representations of representations file to
 * images.
 *
 * @author mchauvin
 */
public class ExportRepresentationsFromFileAction implements IObjectActionDelegate {

    private IFile sessionResourceFile;

    @Override
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
    }

    @Override
    public void run(final IAction action) {

        final Shell shell = Display.getCurrent().getActiveShell();
        final IPath targetPath = this.sessionResourceFile.getParent().getLocation();
        final URI sessionResourceURI = URI.createPlatformResourceURI(sessionResourceFile.getFullPath().toOSString(), true);
        Session session = SessionManager.INSTANCE.getSession(sessionResourceURI, new SubProgressMonitor(new NullProgressMonitor(), 1));
        if (session != null) {
            final Collection<DRepresentation> dRepresentationsToExportAsImage = DialectManager.INSTANCE.getAllRepresentations(session);
            if (!dRepresentationsToExportAsImage.isEmpty()) {
                final ExportSeveralRepresentationsAsImagesDialog dialog = new ExportSeveralRepresentationsAsImagesDialog(shell, targetPath);
                if (dialog.open() == Window.CANCEL) {
                    dialog.close();
                    return;
                }

                final IPath outputPath = dialog.getOutputPath();
                final ImageFileFormat imageFormat = dialog.getImageFormat();
                final boolean exportToHtml = dialog.isExportToHtml();
                final boolean exportDecorations = dialog.isExportDecorations();

                IRunnableWithProgress exportAllRepresentationsRunnable = new WorkspaceModifyOperation() {

                    @Override
                    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                        Session session = null;
                        boolean isOpen = false;
                        try {
                            monitor.beginTask(Messages.ExportRepresentationsFromFileAction_exportTask, 10);
                            session = SessionManager.INSTANCE.openSession(sessionResourceURI, new SubProgressMonitor(monitor, 2), SiriusEditPlugin.getPlugin().getUiCallback());

                            if (session != null) {
                                ExportAction exportAction = new ExportAction(session, dRepresentationsToExportAsImage, outputPath, imageFormat, exportToHtml, exportDecorations);
                                exportAction.run(new SubProgressMonitor(monitor, 7));
                            }
                        } finally {
                            if (!isOpen && session != null) {
                                session.close(new SubProgressMonitor(monitor, 1));
                            }
                            monitor.done();
                        }
                    }
                };

                final ProgressMonitorDialog pmd = new ProgressMonitorDialog(shell);
                try {
                    pmd.run(false, false, exportAllRepresentationsRunnable);
                } catch (final InvocationTargetException e) {
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
                    MessageDialog.openError(shell, Messages.ExportRepresentationsFromFileAction_errorDialog_title, e.getTargetException().getMessage());
                } catch (final InterruptedException e) {
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
                    MessageDialog.openInformation(shell, Messages.ExportRepresentationsFromFileAction_interruptedDialog_title, e.getMessage());
                } finally {
                    pmd.close();

                }
            } else {
                MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.ExportRepresentationsFromFileAction_noRepresentationsDialog_title,
                        Messages.ExportRepresentationsFromFileAction_noRepresentationsDialog_message);
            }
        }
    }

    @Override
    public void selectionChanged(final IAction action, final ISelection selection) {
        action.setEnabled(false);
        if (selection instanceof IStructuredSelection && !selection.isEmpty() && ((IStructuredSelection) selection).getFirstElement() instanceof IFile) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof IFile) {
                sessionResourceFile = (IFile) structuredSelection.getFirstElement();
                action.setEnabled(new FileQuery(sessionResourceFile.getFileExtension()).isSessionResourceFile());
            }
        }
    }

}
