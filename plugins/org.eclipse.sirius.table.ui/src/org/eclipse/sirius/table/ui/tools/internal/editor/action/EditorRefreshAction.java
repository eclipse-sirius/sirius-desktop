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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.table.ui.business.internal.refresh.TableRefresherHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
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
     * This records the editor or view with which the action is currently associated.
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
            TableRefresherHelper.refreshEditor((AbstractDTableEditor) workbenchPart, new NullProgressMonitor());
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
