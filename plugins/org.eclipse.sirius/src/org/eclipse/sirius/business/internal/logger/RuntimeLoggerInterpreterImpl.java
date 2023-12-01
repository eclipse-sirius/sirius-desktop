/*******************************************************************************
 * Copyright (c) 2009, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.logger;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * .
 * 
 * @author mchauvin
 */
public class RuntimeLoggerInterpreterImpl implements RuntimeLoggerInterpreter {

    private IInterpreter interpreter;
    private boolean logError = true;

    /**
     * Only {@link RuntimeLoggerManagerImpl} should call this constructor.
     * 
     * @param interpreter
     *            the interpreter
     */
    protected RuntimeLoggerInterpreterImpl(final IInterpreter interpreter) {
        this.interpreter = interpreter;
    }
    
    /**
     * Returns the decorated interpreter.
     * 
     * @return interpreter
     */
    public IInterpreter getDecorated() {
        return interpreter;
    }
    
    @Override
    public Object evaluate(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final Object result = interpreter.evaluate(context, expression);
            return result;
        } catch (final EvaluationException e) {
            handleError(e, descriptionObject, descriptionFeature);
        }
        return null;
    }

    @Override
    public boolean evaluateBoolean(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        return evaluateBoolean(context, descriptionObject, descriptionFeature, false);
    }

    @Override
    public boolean evaluateBoolean(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature, final boolean flagCondition) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final boolean result = interpreter.evaluateBoolean(context, expression);
            return result;
        } catch (final EvaluationException e) {
            handleError(e, descriptionObject, descriptionFeature);
            if (flagCondition) {
                SiriusPlugin.getDefault().error(Messages.RuntimeLoggerInterpreterImpl_evaluationConditionErrorMsg, e);
            }
        }
        return false;
    }

    @Override
    public Integer evaluateInteger(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final Integer result = interpreter.evaluateInteger(context, expression);
            return result;
        } catch (final EvaluationException e) {
            handleError(e, descriptionObject, descriptionFeature);
        }
        return null;
    }

    @Override
    public String evaluateString(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final String result = interpreter.evaluateString(context, expression);
            return result;
        } catch (final EvaluationException e) {
            handleError(e, descriptionObject, descriptionFeature);
        }
        return null;
    }

    @Override
    public EObject evaluateEObject(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final EObject result = interpreter.evaluateEObject(context, expression);
            return result;
        } catch (final EvaluationException e) {
            handleError(e, descriptionObject, descriptionFeature);
        }
        return null;
    }

    @Override
    public Collection<EObject> evaluateCollection(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final Collection<EObject> result = interpreter.evaluateCollection(context, expression);
            return result;
        } catch (final EvaluationException e) {
            handleError(e, descriptionObject, descriptionFeature);
        }
        return Collections.emptySet();
    }

    /**
     * Enables if errors are logged.
     * 
     * @param log error
     */
    public void setLogError(boolean log) {
        logError = log;
    }
    
    private void handleError(EvaluationException failure, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        if (logError) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, descriptionFeature, failure);
        }
    }
    
}
