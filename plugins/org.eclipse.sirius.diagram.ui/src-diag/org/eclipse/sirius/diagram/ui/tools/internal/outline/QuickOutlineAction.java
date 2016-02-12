/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.outline;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * This action will display a quick outline view within a text popup menu.
 * 
 * @author nlepine
 * 
 */
public class QuickOutlineAction extends Action implements IWorkbenchWindowActionDelegate, IObjectActionDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
     */
    @Override
    public void dispose() {
        // No disposal needed here
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
     */
    @Override
    public void init(IWorkbenchWindow window) {
        // no initialization required
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        super.run();
        DDiagramEditorImpl currentEditor = getCurrentEditor();
        if (currentEditor != null) {
            SiriusInformationPresenter presenter = currentEditor.getQuickOutlinePresenter();
            presenter.showInformation();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // We don't need to react to selection changes
    }

    /**
     * Returns the currently active editor if it's an SiriusDiagramEditor.
     * 
     * @return The currently active editor if it's an SiriusDiagramEditor,
     *         <code>null</code> otherwise.
     */
    protected DDiagramEditorImpl getCurrentEditor() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null && window.getActivePage() != null && window.getActivePage().getActiveEditor() instanceof SiriusDiagramEditor) {
            return (DDiagramEditorImpl) window.getActivePage().getActiveEditor();
        }
        return null;
    }

    /**
     * Empty. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        // empty.
    }
}
