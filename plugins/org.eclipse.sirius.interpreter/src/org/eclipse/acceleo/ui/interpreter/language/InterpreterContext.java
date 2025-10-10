/*******************************************************************************
 * Copyright (c) 2011, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.language;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.ui.interpreter.view.Variable;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

/**
 * This will be used to pass the current compilation context to the language interpreter.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class InterpreterContext {
    /** The full expression currently written in the interpreter's source viewer. */
    private final String expression;

    /**
     * Current selection in the source viewer.
     */
    private final ISelection selection;

    /**
     * This will be initialized with the list of EObjects that have been selected in the workbench.
     * 
     * @deprecated use {@link #targetNotifiers} instead.
     */
    @Deprecated
    private final List<EObject> targetEObjects;

    /**
     * This will be initialized with the list of Notifiers that have been selected in the workbench.
     */
    private final List<Notifier> targetNotifiers;

    /** List of the variables currently accessible by the interpreter. */
    private final List<Variable> variables;

    /**
     * Copy constructor.
     * 
     * @param context
     *            The context we are to copy.
     */
    public InterpreterContext(InterpreterContext context) {
        this.expression = context.expression;
        this.selection = context.selection;
        this.targetEObjects = new ArrayList<EObject>(context.targetEObjects);
        this.targetNotifiers = new ArrayList<Notifier>(context.targetNotifiers);
        this.variables = new ArrayList<Variable>(context.variables);
    }

    /**
     * Instantiates this context.
     * 
     * @param expression
     *            The full expression currently written in the interpreter's source viewer.
     * @param selection
     *            The selected part of the source viewer's expression.
     * @param targetNotifiers
     *            Currently selected Notifiers.
     * @param variables
     *            The variables currently accessible by the interpreter.
     */
    public InterpreterContext(String expression, ISelection selection, List<Notifier> targetNotifiers, List<Variable> variables) {
        this.expression = expression;
        this.selection = selection;
        this.targetNotifiers = targetNotifiers;
        this.targetEObjects = new ArrayList<EObject>(targetNotifiers.size());
        for (Notifier notifier : targetNotifiers) {
            if (notifier instanceof EObject) {
                targetEObjects.add((EObject) notifier);
            }
        }
        this.variables = variables;
    }

    /**
     * Returns the expression currently written in the interpreter's source viewer.
     * 
     * @return The expression currently written in the interpreter's source viewer.
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Returns the part of this expression that was selected in the source viewer.
     * 
     * @return The part of this expression that was selected in the source viewer.
     */
    public ISelection getSelection() {
        return selection;
    }

    /**
     * Returns the list of EObjects that have been selected in the workbench.
     * 
     * @return The list of EObjects that have been selected in the workbench.
     * @deprecated use {@link #getTargetNotifiers()} instead.
     */
    @Deprecated
    public List<EObject> getTargetEObjects() {
        return targetEObjects;
    }

    /**
     * Returns the list of Notifiers that have been selected in the workbench.
     * 
     * @return The list of Notifiers that have been selected in the workbench.
     */
    public List<Notifier> getTargetNotifiers() {
        return targetNotifiers;
    }

    /**
     * Returns the variables currently accessible by the interpreter.
     * 
     * @return The variables currently accessible by the interpreter.
     */
    public List<Variable> getVariables() {
        return variables;
    }
}
