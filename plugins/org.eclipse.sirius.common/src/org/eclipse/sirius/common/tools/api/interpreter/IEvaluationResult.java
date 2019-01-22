/*******************************************************************************
 * Copyright (c) 2015-2019 Obeo.
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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;

/**
 * This interface represents the result of the evaluation of an expression with its value and a diagnostic.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @author <a href="mailto:pierre-charles.david@obeo.fr">Pierre-Charles David</a>
 */
public interface IEvaluationResult {
    /**
     * Returns the raw value computed from the evaluation of the expression.
     * 
     * @return The value
     */
    Object getValue();

    /**
     * Coerces the value as a collection of EObject if possible.
     * 
     * @return a collection of {@link EObject}, which will be empty if the raw value could not be coerced.
     */
    Optional<Collection<EObject>> asEObjects();

    /**
     * Coerces the value as a string.
     * 
     * @return a string representation of the value, which will be empty if the raw value could not be coerced
     *         meaningfully.
     */
    Optional<String> asString();

    /**
     * Coerces the value as an int if possible.
     * 
     * @return an int representing the result of the evaluation, which will be empty if the raw value can not be coerced
     *         meaningfully.
     */
    OptionalInt asInt();

    /**
     * Coerces the value as a boolean if possible.
     * 
     * @return a boolean representing the result of the evaluation, which will be empty if the raw value can not be
     *         coerced meaningfully.
     */
    Optional<Boolean> asBoolean();

    /**
     * Coerces the value as an {@link EObject} if possible.
     * 
     * @return an {@link EObject} representing the result of the evaluation, which will be empty if the raw value can
     *         not be coerced meaningfully.
     */
    Optional<EObject> asEObject();

    /**
     * The diagnostic computed during the evaluation of the expression.
     * 
     * @return The diagnostic
     */
    Diagnostic getDiagnostic();

    /**
     * Indicates if the evaluation was successful, i.e. produced a meaningful value and no error. It will be considered
     * a success even if there were {@link Diagnostic#INFO INFO} or {@link Diagnostic#WARNING WARNING} produced (which
     * will be accessible via {@link #getDiagnostic()}.
     * 
     * @return <code>true</code> if the evaluation was successful.
     */
    boolean success();
}
