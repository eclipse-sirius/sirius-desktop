/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.tree.ui.business.internal.refresh.TreeRefresherHelper;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * This action refresh the entire tree or the tree items elements selected.
 * 
 * @author nlepine
 */
public class RefreshAction extends Action implements IObjectActionDelegate {

    private DTreeEditor treeEditor;

    private ISelection selection;

    /**
     * Default constructor.
     * 
     * @param treeEditor
     *            the tree editor
     */
    public RefreshAction(final DTreeEditor treeEditor) {
        super(Messages.RefreshAction_refreshTreeElement, DTreeViewerManager.getImageRegistry().getDescriptor(DTreeViewerManager.REFRESH_IMG));
        this.treeEditor = treeEditor;
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
                    TreeRefresherHelper.refreshEditor(treeEditor, structuredSelection, new NullProgressMonitor());
                }
            }
        }
    }

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    @Override
    public void run(IAction action) {
        run();
    }

    @Override
    public void selectionChanged(IAction action, ISelection sel) {
        this.selection = sel;
    }
}
