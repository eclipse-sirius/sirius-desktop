/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;

/**
 * An interface for implementations of {@link IInterpreter} used to provide
 * additional information after the evaluation of an expression.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public interface IInterpreterWithDiagnostic {

    /**
     * This interface represents the result of the evaluation of an expression
     * with its value and a diagnostic.
     * 
     * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
     */
    public interface IEvaluationResult {
        /**
         * Returns the value computed from the evaluation of the expression.
         * 
         * @return The value
         */
        Object getValue();

        /**
         * The diagnostic computed during the evaluation of the expression.
         * 
         * @return The diagnostic
         */
        Diagnostic getDiagnostic();
    }

    /**
     * Wrapper method to evaluate an expression and return the result and its
     * diagnostic. This method should have the same behavior as the method
     * {@link IInterpreter#evaluate(EObject, String)} but with a result
     * containing a diagnostic too.
     * 
     * @param target
     *            the EObject instance to evaluate on.
     * @param expression
     *            the expression to evaluate.
     * @return an object with the evaluation result.
     * @throws EvaluationException
     *             if the evaluation was not successful.
     */
    IEvaluationResult evaluateExpression(EObject target, String expression) throws EvaluationException;
}
