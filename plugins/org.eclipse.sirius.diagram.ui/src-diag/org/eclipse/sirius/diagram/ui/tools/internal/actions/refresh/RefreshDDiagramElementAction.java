/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Refresh a {@link org.eclipse.sirius.diagram.DDiagramElement}.
 * 
 * @author cbrun
 */
public class RefreshDDiagramElementAction extends Action implements IObjectActionDelegate {

    /**
     * Construct a new refresh element action.
     */
    public RefreshDDiagramElementAction() {
        super();
    }

    /**
     * Construct a new refresh element action.
     * 
     * @param label
     *            the action label
     * @param descriptor
     *            the action image descriptor
     */
    public RefreshDDiagramElementAction(String label, ImageDescriptor descriptor) {
        super(label, descriptor);
    }

    /**
     * Empty. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        // empty.
    }

    /**
     * Empty. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(final IAction action, final ISelection s) {
        // empty.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        RefreshDiagramAction.refresh(selection);
    }

    /**
     * Execute the action. Refresh all selected view point element.
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(final IAction action) {
        run();
    }
}
