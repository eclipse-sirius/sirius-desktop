/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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

package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;
import java.util.Optional;
import java.util.OptionalInt;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.internal.interpreter.DefaultConverter;

/**
 * Default implementation of {@link IEvaluationResult} that should be suitable for most cases.
 * 
 * @author pcdavid
 */
public class EvaluationResult implements IEvaluationResult {

    /**
     * When an evaluation failed, no value is available so all conversion methods return and empty optional.
     * 
     * @author pcdavid
     *
     */
    private static final class FailedEvaluationConverter implements IConverter {
        @Override
        public Optional<String> toString(Object rawValue) {
            return Optional.empty();
        }

        @Override
        public OptionalInt toInt(Object rawValue) {
            return OptionalInt.empty();
        }

        @Override
        public Optional<Collection<EObject>> toEObjectCollection(Object rawValue) {
            return Optional.empty();
        }

        @Override
        public Optional<EObject> toEObject(Object rawValue) {
            return Optional.empty();
        }

        @Override
        public Optional<Boolean> toBoolean(Object rawValue) {
            return Optional.empty();
        }
    }

    /**
     * The raw, uninterpreted and unconverted value that resulted from the evaluation of the expression. May be empty if
     * the expression did not succeed or simply returned <code>null</code>.
     */
    private final Optional<Object> rawValue;

    /**
     * The converter to use for interpreting the raw value into the various types that are supported by Sirius.
     */
    private final IConverter converter;

    /**
     * Indicates if the evaluation succeeded or not.
     */
    private final Diagnostic diagnostic;

    /**
     * Constructor.
     * 
     * @param rawValue
     *            the raw, uninterpreted and unconverted value that resulted from the evaluation of the expression.
     * @param converter
     *            the converter to use for interpreting the raw value into the various types that are supported by
     *            Sirius.
     * @param diagnostic
     *            indicates if the evaluation succeeded or not.
     */
    protected EvaluationResult(Optional<Object> rawValue, IConverter converter, Diagnostic diagnostic) {
        this.rawValue = rawValue;
        this.converter = converter;
        this.diagnostic = diagnostic;
    }

    /**
     * Static factory method to create a plain successufl result from a raw value.
     * 
     * @param rawValue
     *            the raw value of the evaluation.
     * @return the {@link IEvaluationResult} representing the successful result.
     */
    public static IEvaluationResult ofValue(Object rawValue) {
        return ofValue(rawValue, new DefaultConverter(), Diagnostic.OK_INSTANCE);
    }

    /**
     * Static factory method to create a plain successufl result from a raw value.
     * 
     * @param rawValue
     *            the raw value of the evaluation.
     * @param converter
     *            a specific converter to use to coerce the raw value instead of the default.
     * @return the {@link IEvaluationResult} representing the successful result.
     */
    public static IEvaluationResult ofValue(Object rawValue, IConverter converter) {
        return ofValue(rawValue, converter, Diagnostic.OK_INSTANCE);
    }

    /**
     * Static factory method to create a successufl result with additional (non-ERROR) diagnotics.
     * 
     * @param rawValue
     *            the raw value of the evaluation.
     * @param converter
     *            a specific converter to use to coerce the raw value instead of the default.
     * @param diagnostic
     *            additional diagnostics about the evaluation, which can be INFO or WARNING, but not ERROR.
     * 
     * @return the {@link IEvaluationResult} representing the successful result.
     */
    public static IEvaluationResult ofValue(Object rawValue, IConverter converter, Diagnostic diagnostic) {
        if (diagnostic.getSeverity() >= Diagnostic.ERROR) {
            throw new IllegalArgumentException("An evalution can not produce a meaningful value if it was in error"); //$NON-NLS-1$
        }
        return new EvaluationResult(Optional.ofNullable(rawValue), converter, diagnostic);
    }

    /**
     * Static factory method to create a result indicate that the evaluation failed with an error.
     * 
     * @param error
     *            the diagnostic representing the evaluation error(s).
     * @return the {@link IEvaluationResult} representing the failed evaluation.
     */
    public static IEvaluationResult ofError(Diagnostic error) {
        return new EvaluationResult(Optional.empty(), new FailedEvaluationConverter(), error);
    }

    /**
     * Static factory method to create a result indicate that the evaluation failed with an exception.
     * 
     * @param th
     *            the exception which caused the evaluation to fail.
     * @return the {@link IEvaluationResult} representing the failed evaluation.
     */
    public static IEvaluationResult ofError(Throwable th) {
        BasicDiagnostic diag = new BasicDiagnostic(Diagnostic.ERROR, DslCommonPlugin.PLUGIN_ID, 0, org.eclipse.sirius.common.tools.Messages.Interpreter_evaluationError, new Object[] { th });
        return ofError(diag);
    }

    @Override
    public Object getValue() {
        return rawValue.orElse(null);
    }

    @Override
    public Optional<Collection<EObject>> asEObjects() {
        return rawValue.flatMap(converter::toEObjectCollection);
    }

    @Override
    public Optional<String> asString() {
        return rawValue.flatMap(converter::toString);
    }

    @Override
    public OptionalInt asInt() {
        if (rawValue.isPresent()) {
            return converter.toInt(rawValue.get());
        } else {
            return OptionalInt.empty();
        }
    }

    @Override
    public Optional<Boolean> asBoolean() {
        return rawValue.flatMap(converter::toBoolean);
    }

    @Override
    public Optional<EObject> asEObject() {
        return rawValue.flatMap(converter::toEObject);
    }

    @Override
    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    @Override
    public boolean success() {
        return diagnostic.getSeverity() < Diagnostic.ERROR;
    }

    @Override
    public String toString() {
        if (success()) {
            return String.valueOf(getValue());
        } else {
            return diagnostic.toString();
        }
    }
}
