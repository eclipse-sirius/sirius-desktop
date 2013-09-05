/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;

/**
 * This action refresh the content of the table.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RefreshAction extends Action {
    private static final String DEFAULT_NAME = "Refresh table";

    AbstractDTableEditor tableEditor;

    /**
     * Default constructor.
     * 
     * @param tableEditor
     *            the table editor
     */
    public RefreshAction(final AbstractDTableEditor tableEditor) {
        super(DEFAULT_NAME, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.REFRESH_IMG));
        this.tableEditor = tableEditor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        final IRunnableWithProgress op = new IRunnableWithProgress() {
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
            MessageDialog.openError(activeShell, "Error", e.getTargetException().getMessage());
            SiriusPlugin.getDefault().error("Error while refreshing table", e);
        } catch (final InterruptedException e) {
            MessageDialog.openInformation(activeShell, "Cancelled", e.getMessage());
        } finally {
            tableEditor.enablePropertiesUpdate(true);
        }

    }
}
