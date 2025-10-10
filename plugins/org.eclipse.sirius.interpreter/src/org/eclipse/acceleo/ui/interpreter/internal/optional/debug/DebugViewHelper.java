/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal.optional.debug;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.ui.interpreter.view.Variable;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.IDebugView;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * All "debug" related tasks have been extracted here in this class. This code is optional and will never be called if
 * the <i>org.eclipse.debug.ui</i> bundle is not accessible in the classpath.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class DebugViewHelper {
    /** Does not need to be instantiated. */
    private DebugViewHelper() {
        // Hides default constructor
    }

    /**
     * <i>If</i> the debug view is accessible <i>and</i> a thread is selected in it </i>and</i> that thread has
     * variables, this will return them. We'll return an empty list in any other event.
     * 
     * @return The list
     */
    public static List<Variable> getCurrentDebugThreadVariables() {
        final IDebugView debugView = getDebugView();
        if (debugView != null) {
            ISelection threadSelection = debugView.getViewer().getSelection();
            if (!threadSelection.isEmpty() && threadSelection instanceof IStructuredSelection && ((IStructuredSelection) threadSelection).getFirstElement() instanceof IStackFrame) {
                IStackFrame frame = (IStackFrame) ((IStructuredSelection) threadSelection).getFirstElement();
                try {
                    return convertToInterpreterVariables(frame.getVariables());
                } catch (DebugException e) {
                    // Ignore the exception and return an empty list
                }
            }
        }
        return new ArrayList<Variable>();
    }

    /**
     * Converts the given array of debug variables to a list of Interpreter variables.
     * 
     * @param debugVariables
     *            The array we are to convert. Can be empty or <code>null</code>.
     * @return The list of converted variables. An empty list if there were no debug variables.
     */
    private static List<Variable> convertToInterpreterVariables(IVariable[] debugVariables) {
        final List<Variable> interpreterVariables = new ArrayList<Variable>();
        if (debugVariables != null && debugVariables.length != 0) {
            for (int i = 0; i < debugVariables.length; i++) {
                Variable variable = (Variable) debugVariables[i].getAdapter(Variable.class);
                if (variable != null) {
                    interpreterVariables.add(variable);
                }
            }
        }
        return interpreterVariables;
    }

    /**
     * Returns the debug view if it is accessible.
     * 
     * @return The debug view if accessible, <code>null</code> otherwise.
     */
    private static IDebugView getDebugView() {
        final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (workbenchWindow != null && workbenchWindow.getActivePage() != null) {
            for (IViewReference viewReference : workbenchWindow.getActivePage().getViewReferences()) {
                final IViewPart viewPart = viewReference.getView(false);
                if (viewPart instanceof IDebugView && isAvailable((IDebugView) viewPart)) {
                    return (IDebugView) viewPart;
                }
            }
        }
        return null;
    }

    /**
     * Checks whether the given debug view is available.
     * 
     * @param debugView
     *            The debug view we are to check.
     * @return <code>true</code> if the given debug view is available, <code>false</code> otherwise.
     */
    private static boolean isAvailable(IDebugView debugView) {
        final Viewer viewer = debugView.getViewer();
        return viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed();
    }
}
