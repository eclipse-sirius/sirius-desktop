/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.actions.repair;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.migration.AirdResourceVersionMismatchException;
import org.eclipse.sirius.business.api.repair.SiriusRepairProcess;
import org.eclipse.sirius.business.internal.migration.resource.MigrationUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;

/**
 * This action will be called when the user needs to repair a representations file.
 *
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @author mchauvin
 */
public class RepresentationFilesRepairAction extends ActionDelegate {

    /**
     * The repair action label.
     */
    public static final String REPAIR_ACTION_LABEL = Messages.RepresentationFilesRepairValidator_repairActionLabel;

    /** The file selected in the workspace. */
    protected IFile file;

    /**
     * Constructor.
     */
    public RepresentationFilesRepairAction() {
        super();
    }

    @Override
    public void run(final IAction action) {
        if (file != null) {
            try {
                IStatus validationStatus = new RepresentationFilesNeedCloseSessionValidator(REPAIR_ACTION_LABEL).validate(file);
                if (validationStatus.isOK()) {
                    PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            try {
                                repair(monitor);
                            } catch (AirdResourceVersionMismatchException e) {
                                UICallBack uiCallback = SiriusEditPlugin.getPlugin().getUiCallback();
                                if (uiCallback != null && uiCallback.askSessionReopeningWithResourceVersionMismatch(e)) {
                                    try {
                                        MigrationUtil.ignoreVersionMismatch = true;
                                        repair(monitor);
                                    } finally {
                                        MigrationUtil.ignoreVersionMismatch = false;
                                    }
                                }
                            }
                        }

                        private void repair(IProgressMonitor monitor) throws InterruptedException {
                            SiriusRepairProcess process = new SiriusRepairProcess(file, true);
                            process.run(monitor);
                            process.dispose();
                        }
                    });
                }
            } catch (final InvocationTargetException e) {
                dispose();
                final Status status = new Status(IStatus.ERROR, SiriusPlugin.ID, Messages.RepresentationFilesRepairAction_repairFailed, e.getCause());
                ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.RepresentationFilesRepairAction_repairError,
                        Messages.RepresentationFilesRepairAction_repairError, status);
                SiriusPlugin.getDefault().getLog().log(status);
            } catch (final InterruptedException e) {
                dispose();
                final Status status = new Status(IStatus.WARNING, SiriusPlugin.ID, e.getMessage(), e);
                ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.RepresentationFilesRepairAction_repairInterrupted,
                        Messages.RepresentationFilesRepairAction_repairInterrupted, status);
                SiriusPlugin.getDefault().getLog().log(status);
            }
        }

    }

    @Override
    public void dispose() {
        super.dispose();
        file = null;
    }

    @Override
    public void selectionChanged(final IAction action, final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            final Object object = ((IStructuredSelection) selection).getFirstElement();
            if (object instanceof IFile) {
                file = (IFile) object;
            }
        }
    }
}
