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

import org.eclipse.core.runtime.IStatus;

/**
 * This should be used as the result of a language interpreter's compilation task. Later on, it will be passed to the
 * evaluation task of the language interpreter.
 * <p>
 * {@link #status} can be populated with either a simple {@link org.eclipse.core.runtime.Status} or a
 * {@link org.eclipse.core.runtime.MultiStatus}. If this is not <code>null</code>, the issue(s) will be reported on the
 * interpreter UI.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class CompilationResult {
    /**
     * This will hold the actual result of the compilation. Note that this can legally be <code>null</code> if the
     * compilation encountered critical errors.
     */
    private Object compiledExpression;

    /**
     * This can hold any info, warning or error that has been encountered by the compilation. This can legally be
     * <code>null</code> if no problem was encountered.
     */
    private IStatus status;

    /**
     * Creates a compilation result given the compilation issues. This assumes that critical errors were encountered
     * during the compilation, and that the expression could not be compiled.
     * 
     * @param status
     *            The status of the compilation. Can be a {@link org.eclipse.core.runtime.MultiStatus}.
     */
    public CompilationResult(IStatus status) {
        this.status = status;
    }

    /**
     * Creates a compilation result given the compiled expression. This assumes that no issues were encountered during
     * the compilation.
     * 
     * @param compiledExpression
     *            Result of the compilation.
     */
    public CompilationResult(Object compiledExpression) {
        this.compiledExpression = compiledExpression;
    }

    /**
     * Creates a compilation result given the compiled expression and the status of the compilation.
     * 
     * @param compiledExpression
     *            Result of the compilation.
     * @param status
     *            The status of the compilation. Can be a {@link org.eclipse.core.runtime.MultiStatus}.
     */
    public CompilationResult(Object compiledExpression, IStatus status) {
        this.compiledExpression = compiledExpression;
        this.status = status;
    }

    /**
     * Returns the compiled expression.
     * 
     * @return The compiled expression.
     */
    public Object getCompiledExpression() {
        return compiledExpression;
    }

    /**
     * Returns the status of the compilation.
     * 
     * @return The status of the compilation
     */
    public IStatus getStatus() {
        return status;
    }
}
