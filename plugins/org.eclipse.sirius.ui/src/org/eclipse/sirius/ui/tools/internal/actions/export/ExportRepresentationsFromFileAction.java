/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * An eclipse action to export all representations of representations file to images.
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
        Session session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        if (session != null) {
            // Get only rep desc to avoid loading representation here if we are in lazy.
            final Collection<DRepresentationDescriptor> dRepresentationsDescToExportAsImage = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
            if (!dRepresentationsDescToExportAsImage.isEmpty()) {
                final ExportSeveralRepresentationsAsImagesDialog dialog = new ExportSeveralRepresentationsAsImagesDialog(shell, targetPath);
                if (dialog.open() == Window.CANCEL) {
                    dialog.close();
                    return;
                }

                final ProgressMonitorDialog pmd = new ProgressMonitorDialog(shell);
                try {
                    pmd.run(false, false, new ExportRepresentationsWorkspaceModifyOperation(dialog, sessionResourceURI, session));
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

    /**
     * A {@link WorkspaceModifyOperation} that will execute the Export as images action.
     * 
     * @author fbarbin
     *
     */
    private class ExportRepresentationsWorkspaceModifyOperation extends WorkspaceModifyOperation {

        private IPath outputPath;

        private ImageFileFormat imageFormat;

        private boolean exportToHtml;

        private boolean exportDecorations;

        private Integer scaleLevel;

        private URI sessionResourceURI;

        private Session session;

        ExportRepresentationsWorkspaceModifyOperation(ExportSeveralRepresentationsAsImagesDialog dialog, URI sessionResourceURI, Session session) {
            this.outputPath = dialog.getOutputPath();
            this.imageFormat = dialog.getImageFormat();
            this.exportToHtml = dialog.isExportToHtml();
            this.exportDecorations = dialog.isExportDecorations();
            this.scaleLevel = dialog.getDiagramScaleLevelInPercent();
            this.sessionResourceURI = sessionResourceURI;
            this.session = session;
        }

        @Override
        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
            boolean isOpen = false;
            SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.ExportRepresentationsFromFileAction_exportTask, 10);
            try {
                isOpen = session.isOpen();
                if (!isOpen) {
                    session = SessionManager.INSTANCE.openSession(sessionResourceURI, subMonitor.newChild(2), SiriusEditPlugin.getPlugin().getUiCallback());
                }
                if (session != null) {
                    // Get explicitly all representations (with loading them)
                    final Collection<DRepresentation> dRepresentationsToExportAsImage = DialectManager.INSTANCE.getAllRepresentations(session);
                    ExportAction exportAction = new ExportAction(session, dRepresentationsToExportAsImage, outputPath, imageFormat, exportToHtml, exportDecorations);
                    exportAction.setDiagramScaleLevel(scaleLevel);
                    exportAction.run(subMonitor.newChild(7));
                }
            } finally {
                if (!isOpen && session != null) {
                    session.close(subMonitor.newChild(1));
                }
                monitor.done();
            }
        }
    };
}
