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
package org.eclipse.sirius.ui.interpreter.internal.view.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.sirius.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.sirius.ui.interpreter.internal.view.Variable;
import org.eclipse.sirius.ui.interpreter.internal.view.wizards.NewVariableWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

/**
 * This action can be used in order to call the new variable wizard.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class NewVariableWizardAction extends Action {
    /** Keeps a reference to the variable viewer. */
    protected final TreeViewer variableViewer;

    /** Currently selected variable. */
    private final Variable variable;

    /**
     * Instantiates the "new variable" action given the variable viewer and the currently selected variable.
     * 
     * @param variableViewer
     *            The variable viewer.
     * @param variable
     *            The currently selected variable.
     */
    public NewVariableWizardAction(TreeViewer variableViewer, Variable variable) {
        super(InterpreterMessages.getString("interpreter.action.wizard.newvariable.name")); //$NON-NLS-1$
        this.variableViewer = variableViewer;
        this.variable = variable;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        final NewVariableWizard wizard = new NewVariableWizard(variable, getExistingVariables());
        final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
        int result = dialog.open();
        if (result == Window.OK) {
            final String variableName = wizard.getVariableName();
            final Object variableValue = wizard.getVariableValue();

            Variable target;
            if (variable != null && variableName.equals(variable.getName())) {
                target = variable;
            } else {
                target = getExistingVariable(variableName);
            }

            if (target == null) {
                target = createVariable(variableName);
            }

            Object currentValue = target.getValue();
            if (currentValue != null && !(currentValue instanceof Collection<?>)) {
                List<Object> newValue = new ArrayList<Object>(2);
                newValue.add(currentValue);
                newValue.add(variableValue);
                target.setValue(newValue);
            } else if (currentValue instanceof Collection<?>) {
                ((Collection<Object>) currentValue).add(variableValue);
            } else {
                target.setValue(variableValue);
            }

            variableViewer.expandToLevel(new TreePath(new Object[] { target, variableValue, }), 0);
            variableViewer.refresh();
        }
    }

    /**
     * Create a new, empty variable with the given name.
     * 
     * @param name
     *            Name of the new variable.
     * @return The newly created variable.
     */
    @SuppressWarnings("unchecked")
    private Variable createVariable(String name) {
        Variable result = new Variable(name);

        Object input = variableViewer.getInput();
        if (input == null) {
            input = new ArrayList<Variable>();
            variableViewer.setInput(input);
        } else if (input instanceof Variable) {
            List<Variable> newInput = new ArrayList<Variable>();
            newInput.add((Variable) input);
            variableViewer.setInput(newInput);
        }

        if (input instanceof Collection<?>) {
            ((Collection<Variable>) input).add(result);
        }

        return result;
    }

    /**
     * Searches for an existing variable with the given name.
     * 
     * @param name
     *            Name of the variable we seek.
     * @return The variable with the given name if it exists, <code>null</code> otherwise.
     */
    private Variable getExistingVariable(String name) {
        Object input = variableViewer.getInput();
        if (input instanceof Iterable<?>) {
            @SuppressWarnings("unchecked")
            Iterator<Variable> existingVariablesIterator = ((Iterable<Variable>) input).iterator();
            while (existingVariablesIterator.hasNext()) {
                Variable candidate = existingVariablesIterator.next();
                if (name.equals(candidate.getName())) {
                    return candidate;
                }
            }
        }

        Variable result = null;
        if (input instanceof Variable && name.equals(((Variable) input).getName())) {
            result = (Variable) input;
        }
        return result;
    }

    /**
     * Returns the list of pre-existing variables.
     * 
     * @return The list of pre-existing variables.
     */
    private List<Variable> getExistingVariables() {
        final List<Variable> result = new ArrayList<Variable>();

        Object input = variableViewer.getInput();
        if (input instanceof Iterable<?>) {
            @SuppressWarnings("unchecked")
            Iterator<Variable> existingVariablesIterator = ((Iterable<Variable>) input).iterator();
            while (existingVariablesIterator.hasNext()) {
                Variable candidate = existingVariablesIterator.next();
                result.add(candidate);
            }
        } else if (input instanceof Variable) {
            result.add((Variable) input);
        }

        return result;
    }
}
