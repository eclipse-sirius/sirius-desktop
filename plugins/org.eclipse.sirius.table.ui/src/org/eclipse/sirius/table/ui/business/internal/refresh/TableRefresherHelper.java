/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo.
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
package org.eclipse.sirius.table.ui.business.internal.refresh;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.swt.widgets.Shell;

/**
 * This class contains utility methods used to refresh table editors.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class TableRefresherHelper {

    /**
     * Private constructor for utility class.
     */
    private TableRefresherHelper() {
        super();
    }

    /**
     * Refreshes the given table editor and uses the given monitor for task monitoring. Should be call in an EMF Command
     * because some semantics modifications can happen in this context.
     * 
     * @param tableEditor
     *            the table editor to refresh.
     * @param theMonitor
     *            the monitor to use for monitoring the task.
     */
    public static void refreshEditor(AbstractDTableEditor tableEditor, IProgressMonitor theMonitor) {
        SubMonitor subMonitor = SubMonitor.convert(theMonitor, 1);
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
            subMonitor.split(1);
        } catch (final InvocationTargetException e) {
            MessageDialog.openError(activeShell, Messages.Refresh_error, e.getTargetException().getMessage());
            SiriusPlugin.getDefault().error(Messages.Refresh_errorDuringRefresh, e);
        } catch (final InterruptedException e) {
            MessageDialog.openInformation(activeShell, Messages.Refresh_cancelled, e.getMessage());
        } finally {
            tableEditor.enablePropertiesUpdate(true);
        }
    }
}
