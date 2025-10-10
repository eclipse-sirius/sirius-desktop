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

/**
 * This will be used to pass the full interpreter context to the evaluation task. This includes the
 * {@link CompilationResult} of the interpreter's compilation task.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class EvaluationContext extends InterpreterContext {
    /** The result of the expression's compilation. */
    private final CompilationResult compilationResult;

    /**
     * Instantiates this evaluation context given the corresponding compilation context and result.
     * 
     * @param compilationContext
     *            Context that had been passed to the compilation task.
     * @param compilationResult
     *            Result of the compilation task.
     */
    public EvaluationContext(InterpreterContext compilationContext, CompilationResult compilationResult) {
        super(compilationContext);
        this.compilationResult = compilationResult;
    }

    /**
     * Returns the compilation result for this expression.
     * 
     * @return The compilation result for this expression.
     */
    public CompilationResult getCompilationResult() {
        return compilationResult;
    }
}
