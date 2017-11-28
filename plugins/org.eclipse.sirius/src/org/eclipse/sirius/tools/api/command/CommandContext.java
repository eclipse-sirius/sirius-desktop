/*******************************************************************************
 * Copyright (c) 2008, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Lists;

/**
 * This class keeps the trace of all contexts.
 * 
 * @author cbrun
 */
public class CommandContext {

    /** The stack of all context. */
    private Stack<EObject> targetStack = new Stack<EObject>();

    /** The initial push. */
    private EObject nextPushEObject;

    private DRepresentation representation;

    /**
     * Create a new {@link CommandContext}.
     * 
     * @param target
     *            the first context.
     * @param representation
     *            the current representation
     * @since 0.9.0
     */
    public CommandContext(final EObject target, final DRepresentation representation) {
        this.nextPushEObject = target;
        this.representation = representation;
    }

    /**
     * Get the current representation.
     * 
     * @return the representation currently edited
     * @since 0.9.0
     */
    public DRepresentation getRepresentation() {
        return this.representation;
    }

    /**
     * Return the current context.
     * 
     * @return the current context.
     */
    public EObject getCurrentTarget() {
        if (this.targetStack.isEmpty()) {
            return nextPushEObject;
        }
        return this.targetStack.peek();
    }

    /**
     * Return the next element to push.
     * 
     * @return the next element to push.
     */
    public EObject getNextPush() {
        return this.nextPushEObject != null ? this.nextPushEObject : this.getCurrentTarget();
    }

    /**
     * Clear the next push.
     */
    public void clearNextPush() {
        this.nextPushEObject = null;
    }

    /**
     * Define the next push.
     * 
     * @param nextPushEObject
     *            the next push.
     */
    public void setNextPushEObject(final EObject nextPushEObject) {
        this.nextPushEObject = nextPushEObject;
    }

    /**
     * Push the specified object.
     * 
     * @param newTarget
     *            the object to push.
     */
    public void pushTarget(final EObject newTarget) {
        targetStack.push(newTarget);
    }

    /**
     * Pop and return the popped context. Return <code>null</code> if the is no
     * available context.
     * 
     * @return the popped context.
     */
    public EObject popTarget() {
        EObject result = null;

        if (!targetStack.isEmpty()) {
            result = targetStack.pop();
        }

        return result;
    }

    /**
     * Push the context of the specified command context.
     * 
     * @param context
     *            the command context.
     */
    public static void pushContext(final CommandContext context) {
        context.pushTarget(context.getNextPush());
        context.clearNextPush();
    }

    /**
     * Pop the context of the specified command context.
     * 
     * @param context
     *            the command context.
     */
    public static void popContext(final CommandContext context) {
        context.popTarget();
    }

    /**
     * Load the list of contexts.
     * 
     * @param forOp
     *            the expression that gets the list of contexts.
     * @param context
     *            the current context.
     * @return the list of contexts.
     */
    public static List<Object> getContextTargets(final For forOp, final CommandContext context) {

        List<Object> contextTargets = null;
        final IInterpreter inter = InterpreterUtil.getInterpreter(context.getCurrentTarget());
        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(inter);

        final Object result = safeInterpreter.evaluate(context.getCurrentTarget(), forOp, ToolPackage.eINSTANCE.getFor_Expression());
        if (result == null) {
            contextTargets = new ArrayList<>();
        } else if (result instanceof Collection) {
            contextTargets = Lists.newArrayList((Collection<?>) result);
        } else if (result.getClass().isArray()) {
            contextTargets = Lists.newArrayList((Object[]) result);
        } else {
            contextTargets = Lists.newArrayList(result);
        }
        return contextTargets;
    }
}
