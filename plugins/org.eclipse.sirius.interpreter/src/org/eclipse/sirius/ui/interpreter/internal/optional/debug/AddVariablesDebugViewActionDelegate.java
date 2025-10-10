/*******************************************************************************
 * Copyright (c) 2008, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.optional.debug;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterPlugin;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * This action will allow the user to add the selected element in the variable view to the interpreter view.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AddVariablesDebugViewActionDelegate implements IViewActionDelegate {

    /**
     * The EObject selected in the variables view.
     */
    private List<Object> currentVariablesValues = new ArrayList<Object>();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
     */
    public void init(IViewPart view) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @SuppressWarnings("unchecked")
    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            List<Object> variables = structuredSelection.toList();

            currentVariablesValues.clear();

            for (Object variable : variables) {
                EObject variableEObject = null;
                if (variable instanceof EObject) {
                    variableEObject = (EObject) variable;
                } else if (variable instanceof IAdaptable) {
                    variableEObject = (EObject) ((IAdaptable) variable).getAdapter(EObject.class);
                } else {
                    variableEObject = (EObject) Platform.getAdapterManager().getAdapter(variable, EObject.class);
                }

                if (variableEObject != null) {
                    currentVariablesValues.add(variableEObject);
                } else {
                    currentVariablesValues.add(variable);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        if (this.currentVariablesValues.size() > 0) {
            List<Object> variables = new ArrayList<Object>(currentVariablesValues);
            IViewReference interpreterViewReference = null;
            IViewReference[] viewReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
            for (IViewReference iViewReference : viewReferences) {
                if ("org.eclipse.sirius.ui.interpreter.view".equals(iViewReference.getId())) { //$NON-NLS-1$
                    interpreterViewReference = iViewReference;
                }
            }

            IViewPart interpreterViewPart = null;
            if (interpreterViewReference == null) {
                // The interpreter view is not open, let's activate it!

                try {
                    interpreterViewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.sirius.ui.interpreter.view"); //$NON-NLS-1$
                } catch (PartInitException e) {
                    InterpreterPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, InterpreterPlugin.PLUGIN_ID, e.getMessage()));
                }
            } else {
                // Activate it if its not
                IWorkbenchPart part = interpreterViewReference.getPart(true);
                if (part instanceof IViewPart) {
                    interpreterViewPart = (IViewPart) part;
                }
            }

            if (interpreterViewPart != null) {
                interpreterViewPart.setFocus();
            } else {
                InterpreterPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, InterpreterPlugin.PLUGIN_ID, InterpreterMessages.getString("AddVariablesDebug.InterpreterViewNotFound"))); //$NON-NLS-1$
            }

            // Let's add the variables
            if (interpreterViewPart instanceof InterpreterView) {
                InterpreterView interpreterView = (InterpreterView) interpreterViewPart;
                if (!interpreterView.isVariableVisible()) {
                    interpreterView.toggleVariableVisibility();
                }
                for (Object variableEObject : variables) {
                    interpreterView.addVariables(variableEObject);
                }
            }
        }
    }
}
