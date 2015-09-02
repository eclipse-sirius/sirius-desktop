/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This action refresh the content of the table.
 *
 * @author lredor
 */
public class EditorRefreshAction implements IEditorActionDelegate {
    /**
     * This records the editor or view with which the action is currently
     * associated.
     */
    protected IWorkbenchPart workbenchPart;

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IEditorPart)
     */
    @Override
    public void setActiveEditor(final IAction action, final IEditorPart targetEditor) {
        setActiveWorkbenchPart(targetEditor);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(final IAction action) {
        if (workbenchPart instanceof AbstractDTableEditor) {
            final IRunnableWithProgress op = new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor monitor) {
                    final AbstractDTableEditor tableEditor = (AbstractDTableEditor) workbenchPart;
                    tableEditor.enablePropertiesUpdate(false);
                    RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(tableEditor.getTableModel());
                    tableEditor.getEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(tableEditor.getEditingDomain(), monitor, tableEditor.getTableModel()));
                    tableEditor.enablePropertiesUpdate(true);
                }
            };
            final Shell activeShell = workbenchPart.getSite().getShell();
            final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
            try {
                monitorDialog.run(false, false, op);
            } catch (final InvocationTargetException e) {
                MessageDialog.openError(activeShell, Messages.Action_error, e.getTargetException().getMessage());
                SiriusPlugin.getDefault().error(Messages.RefreshAction_errorDuringRefresh, e);
            } catch (final InterruptedException e) {
                MessageDialog.openInformation(activeShell, Messages.Action_cancelled, e.getMessage());
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(final IAction action, final ISelection selection) {
    }

    /**
     * Set the workbench part.
     *
     * @param aWorkbenchPart
     *            the new workbench part
     */
    public void setActiveWorkbenchPart(final IWorkbenchPart aWorkbenchPart) {
        this.workbenchPart = aWorkbenchPart;
    }
}
