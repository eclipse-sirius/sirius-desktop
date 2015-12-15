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
package org.eclipse.sirius.common.interpreter.api;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;

/**
 * Default implementaiton of {@link IEvaluationResult}, suitable for most cases.
 * 
 * @author pcdavid
 */
public class EvaluationResult implements IEvaluationResult {
    /**
     * The raw value resulting fromt the expression's evaluation, without any
     * coercion applied. May be <code>null</code> in case of evaluation error.
     */
    private final Object rawValue;

    /**
     * The status of the evaluation.
     */
    private final Diagnostic status;

    public static EvaluationResult of(Object o) {
        return of(o, Diagnostic.OK_INSTANCE);
    }

    public static EvaluationResult of(Object o, Diagnostic diag) {
        return new EvaluationResult(o, diag);
    }

    public static EvaluationResult noEvaluation() {
        return new EvaluationResult(null, Diagnostic.CANCEL_INSTANCE);
    }

    public static EvaluationResult withError(Diagnostic diag) {
        return new EvaluationResult(null, diag);
    }

    public static EvaluationResult withError(String message) {
        return new EvaluationResult(null, new BasicDiagnostic(Diagnostic.ERROR, IInterpreter.class.getName(), 0, message, null));
    }

    private EvaluationResult(Object rawValue, Diagnostic status) {
        this.rawValue = rawValue;
        this.status = status;
    }

    @Override
    public Object getValue() {
        return rawValue;
    }

    @Override
    public Diagnostic getDiagnostic() {
        return status;
    }
}
