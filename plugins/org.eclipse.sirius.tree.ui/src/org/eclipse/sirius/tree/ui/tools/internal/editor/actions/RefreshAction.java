/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.DTreeItemLocalRefreshCommand;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.TreeRefreshContext;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This action refresh the entire tree or the tree items elements selected.
 * 
 * @author nlepine
 */
public class RefreshAction extends Action implements IObjectActionDelegate {

    private DTreeEditor treeEditor;

    private ISelection selection;

    private LinkedList<Object> minimizedSelection;

    /**
     * Default constructor.
     * 
     * @param treeEditor
     *            the tree editor
     */
    public RefreshAction(final DTreeEditor treeEditor) {
        super(Messages.RefreshAction_refreshTreeElement, DTreeViewerManager.getImageRegistry().getDescriptor(DTreeViewerManager.REFRESH_IMG));
        this.treeEditor = treeEditor;
        minimizedSelection = new LinkedList<Object>();
    }

    @Override
    public void run() {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (activePage != null) {
            IEditorPart activeEditor = activePage.getActiveEditor();
            if (activeEditor instanceof DTreeEditor) {
                treeEditor = (DTreeEditor) activeEditor;
                this.selection = activePage.getSelection();
                if (this.selection instanceof IStructuredSelection) {
                    final IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
                    minimizedSelection = new LinkedList<Object>(Arrays.asList(structuredSelection.toArray()));
                }
                IRunnableWithProgress op = getRunnable();
                if (op != null) {
                    run(op);
                }
            }
        }
    }

    private IRunnableWithProgress getRunnable() {
        IRunnableWithProgress op = null;
        if (minimizedSelection.isEmpty()) {
            op = new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor monitor) {
                    TransactionalEditingDomain domain = treeEditor.getEditingDomain();
                    domain.getCommandStack().execute(new RefreshRepresentationsCommand(domain, monitor, treeEditor.getTreeModel()));
                }
            };
            RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(treeEditor.getTreeModel());
        } else {
            Iterable<DTreeItem> elements = Iterables.filter(minimizedSelection, DTreeItem.class);
            final Collection<DTreeItem> dTreeItems = Lists.newArrayList(elements);
            if (!dTreeItems.isEmpty()) {
                op = new IRunnableWithProgress() {
                    @Override
                    public void run(final IProgressMonitor monitor) {
                        Session session = new EObjectQuery(treeEditor.getRepresentation()).getSession();
                        if (session != null) {
                            GlobalContext globalContext = new TreeRefreshContext(session.getModelAccessor(), session.getInterpreter(), session.getSemanticResources(), session.getTransactionalEditingDomain());
                            TransactionalEditingDomain domain = treeEditor.getEditingDomain();
                            Command localRefreshCmd = new DTreeItemLocalRefreshCommand(domain, globalContext, dTreeItems, false);
                            domain.getCommandStack().execute(localRefreshCmd);
                        }
                    }
                };
            }
        }
        return op;
    }

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    @Override
    public void run(IAction action) {
        run();
    }

    private void run(final IRunnableWithProgress op) {
        final Shell activeShell = treeEditor.getSite().getShell();
        final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
        try {
            treeEditor.enablePropertiesUpdate(false);
            monitorDialog.run(true, false, op);
        } catch (final InvocationTargetException e) {
            MessageDialog.openError(activeShell, Messages.EditorRefreshAction_error, e.getTargetException().getMessage());
            SiriusPlugin.getDefault().error(Messages.EditorRefreshAction_treeRefreshError, e);
        } catch (final InterruptedException e) {
            MessageDialog.openInformation(activeShell, Messages.EditorRefreshAction_refreshCancelled, e.getMessage());
        } finally {
            treeEditor.enablePropertiesUpdate(true);
        }
    }

    @Override
    public void selectionChanged(IAction action, ISelection sel) {
        this.selection = sel;
    }
}
