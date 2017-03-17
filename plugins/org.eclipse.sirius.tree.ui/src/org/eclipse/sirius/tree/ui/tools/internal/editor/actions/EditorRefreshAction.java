/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.tree.ui.business.internal.refresh.TreeRefresherHelper;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This action refresh the content of the tree.
 * 
 * @author nlepine
 */
public class EditorRefreshAction implements IEditorActionDelegate {
    /**
     * This records the editor or view with which the action is currently associated.
     */
    protected IWorkbenchPart workbenchPart;

    @Override
    public void setActiveEditor(final IAction action, final IEditorPart targetEditor) {
        setActiveWorkbenchPart(targetEditor);
    }

    @Override
    public void run(final IAction action) {
        if (workbenchPart instanceof DTreeEditor) {
            TreeRefresherHelper.refreshEditor((DTreeEditor) workbenchPart, new StructuredSelection(), new NullProgressMonitor());
        }
    }

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
