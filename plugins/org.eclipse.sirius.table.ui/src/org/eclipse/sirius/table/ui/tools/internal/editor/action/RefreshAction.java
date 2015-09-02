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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * This action refresh the content of the table.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RefreshAction extends Action implements IObjectActionDelegate {

    private AbstractDTableEditor tableEditor;

    /**
     * Default constructor.
     *
     * @param tableEditor
     *            the table editor
     */
    public RefreshAction(final AbstractDTableEditor tableEditor) {
        super(Messages.RefreshAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.REFRESH_IMG));
        this.tableEditor = tableEditor;
    }

    @Override
    public void run() {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (activePage != null) {
            IEditorPart activeEditor = activePage.getActiveEditor();
            if (activeEditor instanceof AbstractDTableEditor) {
                tableEditor = (AbstractDTableEditor) activeEditor;
                final IRunnableWithProgress op = new IRunnableWithProgress() {
                    @Override
                    public void run(final IProgressMonitor monitor) {
                        TransactionalEditingDomain domain = tableEditor.getEditingDomain();
                        domain.getCommandStack().execute(new RefreshRepresentationsCommand(domain, monitor, tableEditor.getTableModel()));
                    }
                };
                final Shell activeShell = tableEditor.getSite().getShell();
                final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
                try {
                    tableEditor.enablePropertiesUpdate(false);
                    RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(tableEditor.getTableModel());
                    monitorDialog.run(true, false, op);
                } catch (final InvocationTargetException e) {
                    MessageDialog.openError(activeShell, Messages.Action_error, e.getTargetException().getMessage());
                    SiriusPlugin.getDefault().error(Messages.RefreshAction_errorDuringRefresh, e);
                } catch (final InterruptedException e) {
                    MessageDialog.openInformation(activeShell, Messages.Action_cancelled, e.getMessage());
                } finally {
                    tableEditor.enablePropertiesUpdate(true);
                }
            }
        }
    }

    @Override
    public void run(IAction action) {
        run();
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {

    }

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {

    }
}
