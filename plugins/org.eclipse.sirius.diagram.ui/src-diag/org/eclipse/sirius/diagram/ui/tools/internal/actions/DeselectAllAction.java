/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Select the {@link org.eclipse.sirius.diagram.DDiagram diagram} (equivalent to
 * unselect all selected {@link org.eclipse.sirius.diagram.DDiagramElement
 * diagram elements}).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DeselectAllAction extends Action implements IObjectActionDelegate {
    /** The selection. */
    private ISelection selection;

    /**
     * Create a {@link DeselectAllAction}.
     */
    public DeselectAllAction() {
        super();
        setId(ActionIds.DESELECT_ALL);
        setActionDefinitionId("org.eclipse.sirius.diagram.ui.command.deselectAll"); //$NON-NLS-1$
        setAccelerator(SWT.ESC);
        setText("Deselect All");
        setToolTipText("Deselect all selected diagram elements.");
        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DESELECT_ALL_ICON));
    }

    /**
     * Deselect all from the selection.
     * 
     * @param selection
     *            ISelection
     */
    public static void deselectAll(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof EditPart) {
                EditPartViewer viewer = ((EditPart) structuredSelection.getFirstElement()).getViewer();
                if (viewer != null) {
                    viewer.deselectAll();
                }
            }
        }
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
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        this.selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        DeselectAllAction.deselectAll(selection);
    }

    /**
     * Execute the action. Refresh all selected view point element.
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(final IAction action) {
        DeselectAllAction.deselectAll(selection);
    }

    /**
     * Set the selection. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(final IAction action, final ISelection s) {
        this.selection = s;
    }
}
