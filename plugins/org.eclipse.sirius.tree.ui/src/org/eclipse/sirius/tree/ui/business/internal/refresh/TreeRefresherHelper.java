/*******************************************************************************
 * Copyright (c) 2017, 2024 Obeo
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
package org.eclipse.sirius.tree.ui.business.internal.refresh;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.DTreeItemLocalRefreshCommand;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.TreeRefreshContext;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.swt.widgets.Shell;

/**
 * This class contains utility methods used to refresh table editors.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class TreeRefresherHelper {

    /**
     * Private constructor for utility class.
     */
    private TreeRefresherHelper() {
        super();
    }

    /**
     * Refreshes the tree item of the given selection if such element exists. If not refreshes the all editor. Should be
     * call in an EMF {@link Command} because some semantics modifications can happen in this context.
     * 
     * @param treeEditor
     *            the tree editor to refresh.
     * @param structuredSelection
     *            the current tree editor selection.
     * @param theMonitor
     *            the monitor to use for monitoring the task.
     */
    public static void refreshEditor(DTreeEditor treeEditor, IStructuredSelection structuredSelection, IProgressMonitor theMonitor) {
        SubMonitor subMonitor = SubMonitor.convert(theMonitor, 1);
        IRunnableWithProgress op = getRunnable(treeEditor, structuredSelection);
        if (op != null) {
            run(op, treeEditor);
        }
        subMonitor.split(1);
    }

    /**
     * Execute the given refresh runnable refreshing the given editor.
     * 
     * @param op
     *            the refresh runnable to execute.
     * @param treeEditor
     *            the tree editor to refresh.
     */
    private static void run(final IRunnableWithProgress op, DTreeEditor treeEditor) {
        final Shell activeShell = treeEditor.getSite().getShell();
        final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
        try {
            treeEditor.enablePropertiesUpdate(false);
            monitorDialog.run(true, false, op);
        } catch (final InvocationTargetException e) {
            MessageDialog.openError(activeShell, Messages.EditorRefresh_error, e.getTargetException().getMessage());
            SiriusPlugin.getDefault().error(Messages.EditorRefresh_treeRefreshError, e);
        } catch (final InterruptedException e) {
            MessageDialog.openInformation(activeShell, Messages.EditorRefresh_refreshCancelled, e.getMessage());
        } finally {
            treeEditor.enablePropertiesUpdate(true);
        }
    }

    /**
     * Constructs the runnable refreshing the given tree editor or the tree items in the given selection if such element
     * exists.
     * 
     * @param treeEditor
     *            the tree editor to refresh.
     * @param structuredSelection
     *            the selection containing tree item to refresh.
     * @return the runnable refreshing the given tree editor or the tree items in the given selection if such element
     *         exists.
     */
    private static IRunnableWithProgress getRunnable(DTreeEditor treeEditor, IStructuredSelection structuredSelection) {
        IRunnableWithProgress op = null;
        LinkedList<Object> minimizedSelection = new LinkedList<>(Arrays.asList(structuredSelection.toArray()));
        if (minimizedSelection.isEmpty()) {
            op = monitor -> {
                TransactionalEditingDomain domain = treeEditor.getEditingDomain();
                domain.getCommandStack().execute(new RefreshRepresentationsCommand(domain, monitor, treeEditor.getTreeModel()));
            };
            RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(treeEditor.getTreeModel());
        } else {
            // @formatter:off
            Collection<DTreeItem> dTreeItems = minimizedSelection.stream()
                                                                 .filter(DTreeItem.class::isInstance)
                                                                 .map(DTreeItem.class::cast)
                                                                 .collect(Collectors.toList());
            // @formatter:on
            if (!dTreeItems.isEmpty()) {
                op = monitor -> {
                    Session session = new EObjectQuery(treeEditor.getRepresentation()).getSession();
                    if (session != null) {
                        GlobalContext globalContext = new TreeRefreshContext(session.getModelAccessor(), session.getInterpreter(), session.getSemanticResources(),
                                session.getTransactionalEditingDomain());
                        TransactionalEditingDomain domain = treeEditor.getEditingDomain();
                        Command localRefreshCmd = new DTreeItemLocalRefreshCommand(domain, globalContext, dTreeItems, false);
                        domain.getCommandStack().execute(localRefreshCmd);
                    }
                };
            }
        }
        return op;
    }
}
