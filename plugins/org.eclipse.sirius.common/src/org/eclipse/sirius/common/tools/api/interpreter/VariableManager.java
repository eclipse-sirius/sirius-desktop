/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.ext.base.collect.StackEx;

/**
 * This class is able to manage variables.
 * 
 * @author ymortier
 */
public class VariableManager {

    /** the variables. */
    private Map<String, StackEx<Object>> variables;

    /**
     * Default constructor.
     */
    public VariableManager() {
        this.variables = new HashMap<String, StackEx<Object>>();
    }

    /**
     * Sets a variable.
     * 
     * @param name
     *            the name of the variable.
     * @param value
     *            the value of the variable.
     */
    public void setVariable(final String name, final Object value) {
        StackEx<Object> stack = this.variables.get(name);
        if (stack == null) {
            stack = new StackEx<Object>();
            this.variables.put(name, stack);
        }
        stack.push(value);
    }

    /**
     * Unsets the given variable.
     * 
     * @param name
     *            the variable to unset.
     */
    public void unSetVariable(final String name) {
        final StackEx<Object> stack = this.variables.get(name);
        if (stack != null && !stack.isEmpty()) {
            stack.pop();
        }
    }

    /**
     * Clears all variables.
     */
    public void clearVariables() {
        this.variables.clear();
    }

    /**
     * Returns a copy of the current declared variables.
     * 
     * @return Copy of the current declared variables.
     */
    public Map<String, Object> getVariables() {
        final Map<String, Object> result = new HashMap<String, Object>();
        for (Map.Entry<String, StackEx<Object>> variable : this.variables.entrySet()) {
            if (!variable.getValue().isEmpty()) {
                result.put(variable.getKey(), variable.getValue().peek());
            }
        }
        return result;
    }

    /**
     * Returns the current value of the given variable.
     * 
     * @param name
     *            the name of the variable.
     * @return the current value of the specified variable.
     */
    public Object getVariable(final String name) {
        final StackEx<Object> stack = this.variables.get(name);
        if (stack != null && !stack.isEmpty()) {
            return stack.peek();
        }
        return null;
    }

    /**
     * Returns a copy of this variable manager.
     * 
     * @return a copy of this variable manager.
     */
    public VariableManager getCopy() {
        final VariableManager variableManager = new VariableManager();
        for (Map.Entry<String, StackEx<Object>> variable : this.variables.entrySet()) {
            final StackEx<Object> copiedStackEx = new StackEx<Object>();
            for (Object object : variable.getValue().toList()) {
                copiedStackEx.push(object);
            }
            variableManager.variables.put(variable.getKey(), copiedStackEx);
        }
        return variableManager;
    }

    /**
     * Sets the variables on the given interpreter.
     * 
     * @param interpreter
     *            the interpreter.
     */
    public void setVariables(final IInterpreter interpreter) {
        for (Map.Entry<String, StackEx<Object>> variable : this.variables.entrySet()) {
            for (Object object : variable.getValue().toList()) {
                interpreter.setVariable(variable.getKey(), object);
            }
        }
    }

}
