/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
import java.util.LinkedList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.business.internal.helper.RefreshTreeElementTask;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * This action refresh the entire tree or the tree items elements selected.
 * 
 * @author nlepine
 */
public class RefreshAction extends Action implements IObjectActionDelegate {
    private static final String DEFAULT_NAME = "Refresh Tree Element";

    DTreeEditor treeEditor;

    private ISelection selection;

    private LinkedList<Object> minimizedSelection;

    /**
     * Default constructor.
     * 
     * @param treeEditor
     *            the tree editor
     */
    public RefreshAction(final DTreeEditor treeEditor) {
        super(DEFAULT_NAME, DTreeViewerManager.getImageRegistry().getDescriptor(DTreeViewerManager.REFRESH_IMG));
        this.treeEditor = treeEditor;
        minimizedSelection = new LinkedList<Object>();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        this.selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        if (this.selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
            minimizedSelection = new LinkedList<Object>(Arrays.asList(structuredSelection.toArray()));

        }
        final IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor) {
                final SiriusCommand result = new SiriusCommand(treeEditor.getEditingDomain());
                Iterable<DTreeElement> elements = Iterables.filter(minimizedSelection, DTreeElement.class);
                result.getTasks().add(new RefreshTreeElementTask(Lists.newArrayList(elements), treeEditor.getEditingDomain(), monitor));
                treeEditor.getEditingDomain().getCommandStack().execute(result);
            }
        };
        run(op);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor) {
                TransactionalEditingDomain domain = treeEditor.getEditingDomain();
                domain.getCommandStack().execute(new RefreshRepresentationsCommand(domain, monitor, treeEditor.getTreeModel()));
            }
        };
        RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(treeEditor.getTreeModel());
        run(op);
    }

    private void run(final IRunnableWithProgress op) {
        final Shell activeShell = treeEditor.getSite().getShell();
        final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
        try {
            treeEditor.enablePropertiesUpdate(false);
            monitorDialog.run(true, false, op);
        } catch (final InvocationTargetException e) {
            MessageDialog.openError(activeShell, "Error", e.getTargetException().getMessage());
            SiriusPlugin.getDefault().error("Error while refreshing tree", e);
        } catch (final InterruptedException e) {
            MessageDialog.openInformation(activeShell, "Cancelled", e.getMessage());
        } finally {
            treeEditor.enablePropertiesUpdate(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(IAction action, ISelection sel) {
        this.selection = sel;
    }
}
