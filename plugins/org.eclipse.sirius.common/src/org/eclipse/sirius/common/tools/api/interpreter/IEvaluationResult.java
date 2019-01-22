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

import org.eclipse.emf.common.util.Diagnostic;

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