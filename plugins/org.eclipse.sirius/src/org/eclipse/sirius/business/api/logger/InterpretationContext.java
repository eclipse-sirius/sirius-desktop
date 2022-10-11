/*******************************************************************************
 * Copyright (c) 2023  Obeo and others.
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
package org.eclipse.sirius.business.api.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.logger.RuntimeLoggerInterpreterImpl;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;


/**
 * Builder for invocation ensuring an always clean interpreter when done.
 * <p>
 * Such instance is meant to be used in tool evaluation and representation refresh actions.
 * </p>
 * 
 * @author nperansin
 */
public final class InterpretationContext {

    private List<String> variables;
    private final RuntimeLoggerInterpreterImpl implementation;
    
    
    /**
     * Default constructor.
     * 
     * @param interpreter to use
     */
    private InterpretationContext(RuntimeLoggerInterpreter interpreter) {
        if (!(interpreter instanceof RuntimeLoggerInterpreterImpl)) {
            throw new UnsupportedOperationException("This context can only operate on specific implementation.");  //$NON-NLS-1$            
        }
        implementation = (RuntimeLoggerInterpreterImpl) interpreter;
    }

    /**
     * Returns the interpreter of this context.
     * 
     * @return interpreter
     */
    public RuntimeLoggerInterpreter getInterpreter() {
        return implementation;
    }
    
    /**
     * Sets a variable.
     * 
     * @param name
     *            the name of the variable.
     * @param value
     *            the value of the variable.
     */
    public void setVariable(String name, Object value) {
        if (variables == null) {
            variables = new ArrayList<>();
        }
        variables.add(name);
        implementation.getDecorated().setVariable(name, value);
    }
    
    /**
     * Enables if errors are logged.
     * <p>
     * When this context is closed, error logging is always on (as initial state).
     * </p>
     * 
     * @param log flag to log error
     */
    public void setLogError(boolean log) {
        implementation.setLogError(log);
    }
    
    private void close() {
        if (variables != null) {
            variables.forEach(it -> implementation.getDecorated().unSetVariable(it));
        }
        setLogError(true);
    }
    
    /**
     * Runs a task in a clean-closable context.
     * 
     * @param interpreter to use
     * @param task to perform
     */
    public static void with(RuntimeLoggerInterpreter interpreter, Consumer<InterpretationContext> task) {
        InterpretationContext ctx = new InterpretationContext(interpreter);
        try { // Cannot use closable unless by exposing IOException
            task.accept(ctx);
        } finally {
            ctx.close();
        }
    }
    
    /**
     * Evaluates a function in a clean-closable context.
     * 
     * @param <T> type of task
     * @param interpreter to use
     * @param task to perform
     * @return task result
     */
    public static <T> T with(RuntimeLoggerInterpreter interpreter, Function<InterpretationContext, T> task) {
        InterpretationContext ctx = new InterpretationContext(interpreter);
        try { // Cannot use closable unless by exposing IOException
            return task.apply(ctx);
        } finally {
            ctx.close();
        }
    }
    
    /**
     * Runs a task in a clean-closable context.
     * 
     * @param interpreter to use
     * @param task to perform
     */
    public static void with(IInterpreter interpreter, Consumer<InterpretationContext> task) {
        with(RuntimeLoggerManager.INSTANCE.decorate(interpreter), task);
    }
    
    /**
     * Evaluates a function in a clean-closable context.
     * 
     * @param <T> type of task
     * @param interpreter to use
     * @param task to perform
     * @return task result
     */
    public static <T> T with(IInterpreter interpreter, Function<InterpretationContext, T> task) {
        return with(RuntimeLoggerManager.INSTANCE.decorate(interpreter), task);
    }

    /**
     * Runs a task in a clean-closable context.
     * 
     * @param context to use
     * @param task to perform
     */
    public static void with(EObject context, Consumer<InterpretationContext> task) {
        with(InterpreterUtil.getInterpreter(context), task);
    }
    
    /**
     * Evaluates a function in a clean-closable context.
     * 
     * @param <T> type of task
     * @param context to use
     * @param task to perform
     * @return task result
     */
    public static <T> T with(EObject context, Function<InterpretationContext, T> task) {
        return with(InterpreterUtil.getInterpreter(context), task);
    }


}
