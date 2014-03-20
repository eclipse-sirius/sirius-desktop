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
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.Iterator;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ChangeSynchronizedDagramStatusCommand;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Toggle between synchronized and unsynchronized diagram modes.
 * 
 * @author smonnier
 * 
 */
public class SynchronizedDiagramAction implements IObjectActionDelegate {

    /** The selection. */
    private ISelection selection;

    /**
     * Empty. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        // Empty
    }

    /**
     * Execute the action. Toggle synchronization mode. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(final IAction action) {
        if (selection instanceof StructuredSelection) {
            final StructuredSelection structuredSelection = (StructuredSelection) selection;
            final Iterator<?> iterator = structuredSelection.iterator();
            while (iterator.hasNext()) {
                final Object object = iterator.next();
                if (object instanceof DDiagramEditPart) {
                    final DDiagramEditPart ddiagramEditPart = (DDiagramEditPart) object;
                    final DDiagramEditor ddiagramEditor = (DDiagramEditor) ddiagramEditPart.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                    if (ddiagramEditor.getRepresentation() instanceof DDiagram) {
                        final DDiagram diagram = (DDiagram) ddiagramEditor.getRepresentation();
                        TransactionalEditingDomain editingDomain = ddiagramEditPart.getEditingDomain();
                        editingDomain.getCommandStack().execute(new ChangeSynchronizedDagramStatusCommand(editingDomain, diagram));
                    }
                }
            }
        }
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
