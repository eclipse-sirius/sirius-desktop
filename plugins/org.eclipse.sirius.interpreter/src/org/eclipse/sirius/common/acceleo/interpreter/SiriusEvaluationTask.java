/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.interpreter;

import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

import org.eclipse.acceleo.ui.interpreter.language.EvaluationContext;
import org.eclipse.acceleo.ui.interpreter.language.EvaluationResult;
import org.eclipse.acceleo.ui.interpreter.view.Variable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

/**
 * This task delegates to the viewpoint's CompoundInterpreter for expression
 * evaluations.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class SiriusEvaluationTask implements Callable<EvaluationResult> {
    /** Context of this evaluation as passed from the interpreter. */
    private EvaluationContext context;

    /**
     * Instantiates our evaluation task given the current evaluation context.
     * 
     * @param context
     *            The current interpreter evaluation context.
     */
    public SiriusEvaluationTask(EvaluationContext context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.concurrent.Callable#call()
     */
    public EvaluationResult call() throws Exception {
        checkCancelled();

        final String expression = context.getExpression();
        if (context.getTargetEObjects().isEmpty()) {
            IStatus errorStatus = new Status(IStatus.ERROR, InterpreterViewPlugin.PLUGIN_ID, "No target for evaluation of " + expression);
            return new EvaluationResult(errorStatus);
        }

        // Only consider the first of the selected EObjects' list
        final EObject target = context.getTargetEObjects().get(0);
        EObject semanticTarget = target;
        if (target instanceof DSemanticDecorator) {
            semanticTarget = ((DSemanticDecorator) target).getTarget();
        }
        Session curSession = SessionManager.INSTANCE.getSession(semanticTarget);

        final IInterpreter vpInterpreter;
        if (curSession != null) {
            vpInterpreter = curSession.getInterpreter();
        } else {
            vpInterpreter = CompoundInterpreter.INSTANCE.getInterpreterForExpression(expression);
        }

        assert vpInterpreter != null;
        checkCancelled();

        for (Variable var : context.getVariables()) {
            vpInterpreter.setVariable(var.getName(), var.getValue());
        }

        EvaluationResult evaluationResult = null;
        try {
            final Object result = vpInterpreter.evaluate(target, expression);
            final IStatus status = createResultStatus(result);
            evaluationResult = new EvaluationResult(result, status);
        } catch (EvaluationException e) {
            final IStatus status = new Status(IStatus.ERROR, InterpreterViewPlugin.PLUGIN_ID, e.getMessage(), e);
            evaluationResult = new EvaluationResult(status);
        }

        assert evaluationResult != null;

        return evaluationResult;
    }

    /**
     * This will create the status that allows the interpreter to display the
     * type and size of the result object for successful evaluations.
     * 
     * @param result
     *            The result of the evaluation.
     * @return Status that can be displayed by the interpreter for this
     *         evaluation.
     */
    private IStatus createResultStatus(Object result) {
        if (result == null) {
            return new Status(IStatus.OK, InterpreterViewPlugin.PLUGIN_ID, ""); //$NON-NLS-1$
        }

        final String type = result.getClass().getSimpleName();

        String size = null;
        if (result instanceof String) {
            size = String.valueOf(((String) result).length());
        } else if (result instanceof Collection<?>) {
            size = String.valueOf(((Collection<?>) result).size());
        }

        String message = "Result of type " + type;
        if (size != null) {
            message += " and size " + size;
        }

        return new Status(IStatus.OK, InterpreterViewPlugin.PLUGIN_ID, message);
    }

    /**
     * Throws a new {@link CancellationException} if the current thread has been
     * cancelled.
     */
    private void checkCancelled() {
        if (Thread.currentThread().isInterrupted()) {
            throw new CancellationException();
        }
    }
}
