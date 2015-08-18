/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.action;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.common.ui.tools.api.find.AbstractFindLabelDialog;
import org.eclipse.sirius.diagram.ui.tools.internal.find.BasicFindLabelEngine;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A find element Eclipse action.
 * 
 * @author glefur
 */
public class FindElementAction implements IObjectActionDelegate {
    /**
     * The action ID.
     */
    public static final String ID = "org.eclipse.sirius.transversal.find.ui.binding.FindElementAction"; //$NON-NLS-1$

    private DiagramEditor currentPart;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        if (targetPart instanceof DiagramEditor) {
            currentPart = (DiagramEditor) targetPart;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(final IAction action) {
        if (currentPart != null) {
            final AbstractFindLabelDialog dialog = new RevealFindLabelDialog(currentPart.getSite().getShell(), new BasicFindLabelEngine(currentPart), currentPart);
            dialog.open();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(final IAction action, final ISelection selection) {
        final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            final IWorkbenchPage page = window.getActivePage();
            if (page != null) {
                final IWorkbenchPart targetPart = page.getActivePart();
                if (targetPart instanceof DiagramEditor) {
                    currentPart = (DiagramEditor) targetPart;
                }
            }
        }
    }

}
