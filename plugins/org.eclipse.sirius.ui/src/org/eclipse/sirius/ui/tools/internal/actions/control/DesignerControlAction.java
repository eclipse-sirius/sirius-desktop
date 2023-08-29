/*******************************************************************************
 * Copyright (c) 2009, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.actions.control;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ui.tools.api.control.SiriusControlHandler;
import org.eclipse.sirius.ui.tools.api.control.SiriusUncontrolHandler;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * A specific control action handling representations.
 *
 * @author cbrun
 */
public class DesignerControlAction extends ControlAction {

    /**
     * Create a new action to control the models.
     *
     */
    public DesignerControlAction() {
        super();
    }

    @Override
    public void run() {
        final boolean controlling = this.command == null;
        int choice = ISaveablePart2.YES;
        final Session session = SessionManager.INSTANCE.getSession(this.eObject);
        if (session != null) {
            if (session.getStatus() == SessionStatus.DIRTY) {
                /* Show a dialog. */
                choice = SWTUtil.showSaveDialog(session, Messages.DesignerControlAction_saveDialogTitle, true);
            }

            if (choice == ISaveablePart2.YES) {
                try {
                    new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, new WorkspaceModifyOperation() {

                        @Override
                        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                            try {
                                monitor.beginTask(controlling ? Messages.DesignerControlAction_controlTask : Messages.DesignerControlAction_uncontrolTask, 2);

                                if (session.isOpen()) {
                                    monitor.subTask(Messages.DesignerControlAction_savingTask);
                                    session.save(new SubProgressMonitor(monitor, 1));
                                }
                                Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                                if (controlling) {
                                    new SiriusControlHandler().performControl(activeShell, eObject, new SubProgressMonitor(monitor, 1));
                                } else {
                                    new SiriusUncontrolHandler().performUncontrol(activeShell, eObject, new SubProgressMonitor(monitor, 1));
                                }
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
        }
        updateSelection(getStructuredSelection());
    }

    @Override
    public boolean updateSelection(final IStructuredSelection selection) {
        // At each selection changed, we must reset the eObject to avoid a
        // potential memory leak
        eObject = null;
        this.selection = null;
        setEditingDomain(null);

        final Iterator<?> it = selection.iterator();
        while (it.hasNext() && getEditingDomain() == null) {
            final Object next = it.next();
            if (next instanceof EObject) {
                this.eObject = (EObject) next;
                setEditingDomain(TransactionUtil.getEditingDomain(eObject));
                break;
            }
        }
        return getEditingDomain() != null && super.updateSelection(selection);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && SessionManager.INSTANCE.getSession(this.eObject) != null;
    }
}
