/*******************************************************************************
 * Copyright (c) 2008, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.business.internal.refresh.TableRefresherHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
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
                TableRefresherHelper.refreshEditor(tableEditor, new NullProgressMonitor());
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
