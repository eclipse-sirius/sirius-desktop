/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility;

import org.eclipse.gef.Disposable;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Extends the {@link RevealElementsAction} to make it compatible with the tabbar by making it disposable and by
 * handling the selection changes.
 * 
 * @author fbarbin
 */
public class TabbarRevealElementsAction extends RevealElementsAction implements Disposable {
    private IDiagramWorkbenchPart representationPart;
    
    /**
     * Constructor.
     * 
     * @param label the label
     */
    public TabbarRevealElementsAction(final String label) {
        super(label);
    }

    public void setActionPart(IDiagramWorkbenchPart part) {
        this.representationPart = part;
    }
    
    @Override
    public void selectionChanged(IAction action, ISelection s) {
        IWorkbenchPart selectedPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
        if (representationPart != null && !representationPart.equals(selectedPart)) {
            return;
        }
        super.selectionChanged(action, s);
        setEnabled(isEnabled());
    }

    @Override
    public boolean isEnabled() {
        if (selection instanceof IStructuredSelection) {
            return isActive((IStructuredSelection) selection);
        }
        return false;
    }

    @Override
    public void dispose() {
        this.representationPart = null;
        this.selection = null;
    }
    
}
