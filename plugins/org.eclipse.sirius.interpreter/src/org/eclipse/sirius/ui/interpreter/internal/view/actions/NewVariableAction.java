/*******************************************************************************
 * Copyright (c) 2010, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.sirius.ui.interpreter.internal.view.Variable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

/**
 * This action can be used in order to add a new variable to the evaluation context.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class NewVariableAction extends Action {
    /** Keeps a reference to the variable viewer. */
    protected final TreeViewer variableViewer;

    /** Value of this new variable. */
    private Object value;

    /**
     * Instantiates the "new variable" action given the variable viewer.
     * 
     * @param viewer
     *            The variable viewer.
     */
    public NewVariableAction(TreeViewer viewer) {
        super(InterpreterMessages.getString("interpreter.action.createvariable.name")); //$NON-NLS-1$
        this.variableViewer = viewer;
    }

    /**
     * Instantiates the "new variable" action given the variable viewer and the new variable's value.
     * 
     * @param viewer
     *            The variable viewer.
     * @param value
     *            Value of the new variable;
     */
    public NewVariableAction(TreeViewer viewer, Object value) {
        this(viewer);
        this.value = value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        InputDialog dialog = new InputDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), InterpreterMessages.getString("interpreter.action.createvariable.popup.title"), //$NON-NLS-1$
                InterpreterMessages.getString("interpreter.action.createvariable.popup.message") + ':', "", //$NON-NLS-1$ //$NON-NLS-2$
                new VariableNameValidator());
        int result = dialog.open();
        if (result == Window.OK) {
            Variable newVar = new Variable(dialog.getValue());
            if (value != null) {
                newVar.setValue(value);
            }
            Object input = variableViewer.getInput();
            if (!(input instanceof List<?>)) {
                input = new ArrayList<Variable>();
                variableViewer.setInput(input);
            }
            ((List<Variable>) variableViewer.getInput()).add(newVar);
            variableViewer.refresh();
        }
    }

    /**
     * This basic validator only checks that the variable name is a valid Java identifier.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected class VariableNameValidator implements IInputValidator {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.dialogs.IInputValidator#isValid(java.lang.String)
         */
        public String isValid(String newText) {
            String errorMessage = null;
            if (newText == null || "".equals(newText)) { //$NON-NLS-1$
                errorMessage = InterpreterMessages.getString("interpreter.action.createvariable.popup.error.noname"); //$NON-NLS-1$
            } else if (!isJavaIdentifier(newText)) {
                errorMessage = InterpreterMessages.getString("interpreter.action.createvariable.popup.error.invalid", newText); //$NON-NLS-1$
            } else {
                Object input = variableViewer.getInput();
                if (input instanceof List<?>) {
                    for (Object var : (List<?>) input) {
                        if (var instanceof Variable && newText.equals(((Variable) var).getName())) {
                            errorMessage = InterpreterMessages.getString("interpreter.action.createvariable.popup.error.duplicate"); //$NON-NLS-1$
                        }
                    }
                }
            }
            return errorMessage;
        }

        /**
         * Returns <code>true</code> if each of the given String's character is a valid Java identifier part.
         * 
         * @param name
         *            Name of which we need to check the validity.
         * @return <code>true</code> if the given <code>name</code> can be considered a valid Java identifier,
         *         <code>false</code> otherwise.
         */
        private boolean isJavaIdentifier(String name) {
            for (char character : name.toCharArray()) {
                if (!Character.isJavaIdentifierPart(character)) {
                    return false;
                }
            }
            return true;
        }
    }
}
