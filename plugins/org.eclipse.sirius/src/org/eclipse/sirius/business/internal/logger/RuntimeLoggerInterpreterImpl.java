/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * .
 * 
 * @author mchauvin
 */
public class RuntimeLoggerInterpreterImpl implements RuntimeLoggerInterpreter {

    private IInterpreter interpreter;

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
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter#evaluate(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public Object evaluate(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final Object result = interpreter.evaluate(context, expression);
            return result;
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, descriptionFeature, e);
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter#evaluateBoolean(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public boolean evaluateBoolean(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        return evaluateBoolean(context, descriptionObject, descriptionFeature, false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter#evaluateBoolean(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, boolean)
     */
    @Override
    public boolean evaluateBoolean(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature, final boolean flagCondition) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final boolean result = interpreter.evaluateBoolean(context, expression);
            return result;
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, descriptionFeature, e);
            if (flagCondition) {
                SiriusPlugin.getDefault().error(Messages.RuntimeLoggerInterpreterImpl_evaluationConditionErrorMsg, e);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter#evaluateInteger(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public Integer evaluateInteger(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final Integer result = interpreter.evaluateInteger(context, expression);
            return result;
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, descriptionFeature, e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter#evaluateString(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public String evaluateString(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final String result = interpreter.evaluateString(context, expression);
            return result;
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, descriptionFeature, e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter#evaluateEObject(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public EObject evaluateEObject(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final EObject result = interpreter.evaluateEObject(context, expression);
            return result;
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, descriptionFeature, e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter#evaluateCollection(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public Collection<EObject> evaluateCollection(final EObject context, final EObject descriptionObject, final EStructuralFeature descriptionFeature) {
        final String expression = (String) descriptionObject.eGet(descriptionFeature);
        try {
            final Collection<EObject> result = interpreter.evaluateCollection(context, expression);
            return result;
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, descriptionFeature, e);
        }
        return Collections.emptySet();
    }
}
