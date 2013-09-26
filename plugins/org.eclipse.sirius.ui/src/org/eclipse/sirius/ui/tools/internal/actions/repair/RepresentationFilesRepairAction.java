/*******************************************************************************
 * Copyright (c) 2007-2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.sirius.business.api.repair.SiriusRepairProcess;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * This action will be called when the user needs to repair a representations
 * file.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @author mchauvin
 */
public class RepresentationFilesRepairAction extends ActionDelegate {

    private static final String MSG_REPAIR_ERROR = "Repair error";

    private static final String MSG_REPAIR_INTERRUPTED = "Repair interrupted";

    /** The file selected in the workspace. */
    protected IFile file;

    /**
     * Constructor.
     */
    public RepresentationFilesRepairAction() {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.ActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(final IAction action) {
        if (file != null) {
            try {
                IStatus validationStatus = new RepresentationFilesRepairValidator().validate(file);
                if (validationStatus.isOK()) {
                    PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            SiriusRepairProcess process = new SiriusRepairProcess(file, true);
                            process.run(monitor);
                            process.dispose();
                        }
                    });
                }
            } catch (final InvocationTargetException e) {
                dispose();
                final Status status = new Status(IStatus.ERROR, SiriusPlugin.ID, "Could not run repair process.", e.getCause());
                ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), MSG_REPAIR_ERROR, MSG_REPAIR_ERROR, status);
                SiriusPlugin.getDefault().getLog().log(status);
            } catch (final InterruptedException e) {
                dispose();
                final Status status = new Status(IStatus.WARNING, SiriusPlugin.ID, e.getMessage(), e);
                ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), MSG_REPAIR_INTERRUPTED, MSG_REPAIR_INTERRUPTED, status);
                SiriusPlugin.getDefault().getLog().log(status);
            }
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.ActionDelegate#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        file = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.ActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
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
