/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.InterpreterStatusFactory;

import com.google.common.collect.Maps;

/**
 * A specialized interpreter which can only directly access variables (and
 * pseudo-variable).
 * 
 * @author pcdavid
 */
public class VariableInterpreter extends AbstractInterpreter implements org.eclipse.sirius.common.tools.api.interpreter.IInterpreter, IInterpreterProvider {

    /** The Variable interpreter prefix. */
    public static final String PREFIX = "var:";

    /** The self variable name. */
    public static final String SELF_VARIABLE_NAME = "self";

    private final Map<String, Object> variables = Maps.newHashMap();

    public String getPrefix() {
        return VariableInterpreter.PREFIX;
    }

    @Override
    public void setVariable(String name, Object value) {
        variables.put(name, value);
    }

    @Override
    public Object getVariable(String name) {
        return variables.get(name);
    }

    @Override
    public void clearVariables() {
        variables.clear();
    }

    public Map<String, Object> getVariables() {
        return Maps.newHashMap(variables);
    }

    /**
     * {@inheritDoc}
     */
    public Object evaluate(EObject target, String expression) throws EvaluationException {
        Object result = null;
        if (target != null && expression != null && expression.startsWith(PREFIX)) {
            String variableName = expression.trim().substring(PREFIX.length());
            result = evaluateVariable(target, variableName);
        }
        return result;
    }

    /**
     * Method to evaluate a variable.
     * 
     * @param target
     *            the EObject instance to evaluate on.
     * @param variableName
     *            the name of the variable to evaluate.
     * @return an object with the evaluation result.
     * @throws EvaluationException
     *             if the evaluation was not successful.
     */
    protected Object evaluateVariable(EObject target, String variableName) throws EvaluationException {
        Object result = null;
        if (target != null && variableName != null) {
            if (SELF_VARIABLE_NAME.equals(variableName)) {
                result = target;
            } else if (variables.containsKey(variableName)) {
                result = variables.get(variableName);
            } else {
                throw new EvaluationException("Unknown variable " + variableName + ".");
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public IInterpreter createInterpreter() {
        return new VariableInterpreter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsValidation() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        Collection<IInterpreterStatus> interpreterStatus = new ArrayList<IInterpreterStatus>();
        if (expression != null && context != null && expression.startsWith(PREFIX)) {
            String variableName = expression.substring(PREFIX.length());
            if (!context.getVariables().containsKey(variableName) && !SELF_VARIABLE_NAME.equals(variableName)) {
                interpreterStatus.add(InterpreterStatusFactory.createInterpreterStatus(context, IInterpreterStatus.ERROR, "The current context does not contains variable named : " + variableName));
            }
        }
        return interpreterStatus;
    }
}
