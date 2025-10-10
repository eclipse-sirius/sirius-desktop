/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.language;

import java.util.concurrent.Callable;

/**
 * This will be used when no language interpreter has been provided to the interpreter view. It simply relies on the
 * default behavior, and copy/pastes the input expression as the evaluation result.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class DefaultLanguageInterpreter extends AbstractLanguageInterpreter {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.language.AbstractLanguageInterpreter#getEvaluationTask(org.eclipse.sirius.ui.interpreter.internal.language.EvaluationContext)
     */
    @Override
    public Callable<EvaluationResult> getEvaluationTask(final EvaluationContext context) {
        return new Callable<EvaluationResult>() {
            /**
             * {@inheritDoc}
             * 
             * @see java.util.concurrent.Callable#call()
             */
            public EvaluationResult call() throws Exception {
                return new EvaluationResult(context.getExpression());
            }
        };
    }
}
