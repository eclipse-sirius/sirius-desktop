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
package org.eclipse.sirius.ui.interpreter.internal.view.wizards;

import java.util.List;

import org.eclipse.sirius.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.sirius.ui.interpreter.internal.view.Variable;
import org.eclipse.jface.wizard.Wizard;

/**
 * This wizard will be used in order to create new variables.
 * <p>
 * Similar to the "new class" wizard, it will allow for the create new values for existing variables, new empty
 * variables, or new variables with initial values.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class NewVariableWizard extends Wizard {
    /** This will contain the list of pre-existing variables, if any. */
    private final List<Variable> existingVariables;

    /** The one and only page of this wizard. */
    private NewVariableWizardPage page;

    /** If there was a variable selected in the viewer, this will hold a reference to it. */
    private final Variable selectedVariable;

    /**
     * Instantiates the new variable wizard given the initially selected variable.
     * 
     * @param selectedVariable
     *            The variable that was selected in the viewer when this wizard has been created.
     * @param existingVariables
     *            This will contain the list of pre-existing variables, if any.
     */
    public NewVariableWizard(Variable selectedVariable, List<Variable> existingVariables) {
        super();
        setWindowTitle(InterpreterMessages.getString("interpreter.wizard.newvariable.wizardtitle")); //$NON-NLS-1$
        this.selectedVariable = selectedVariable;
        this.existingVariables = existingVariables;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        final String initialVariableName;
        if (selectedVariable != null) {
            initialVariableName = selectedVariable.getName();
        } else {
            initialVariableName = null;
        }

        page = new NewVariableWizardPage(initialVariableName, existingVariables);
        addPage(page);
    }

    /**
     * Returns the variable name.
     * 
     * @return The variable name.
     */
    public String getVariableName() {
        if (page != null) {
            return page.getVariableName();
        }
        return null;
    }

    /**
     * Returns the value of the new variable.
     * 
     * @return The value of the new variable.
     */
    public Object getVariableValue() {
        if (page != null) {
            return page.getVariableValue();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        page.setVariableName();
        page.setVariableValue();
        return true;
    }
}
