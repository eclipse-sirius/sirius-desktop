/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task.operations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * This task allows to change the context of model request language expressions
 * that are in a "tool".
 * 
 * @author cbrun
 */
public class ChangeContextTask extends AbstractOperationTask {

    /** The operation that describes how to change the context. */
    private ChangeContext op;

    /** The interpreter to evaluate expression with. */
    private RuntimeLoggerInterpreter safeInterpreter;

    /**
     * Create a new {@link ChangeContextTask}.
     *
     * @param context
     *            the stack of contexts.
     * @param extPackage
     *            the {@link ModelAccessor} that is useful for the extension
     *            management.
     * @param op
     *            The operation that describes how to change the context
     * @param interpreter
     *            the {@link IInterpreter} to be used
     */
    public ChangeContextTask(final CommandContext context, final ModelAccessor extPackage, final ChangeContext op, final IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        this.op = op;
        this.safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
    }

    @Override
    public void execute() {
        final EObject browseResult = safeInterpreter.evaluateEObject(context.getCurrentTarget(), op, ToolPackage.eINSTANCE.getChangeContext_BrowseExpression());
        if (browseResult != null) {
            final EObject newTarget = browseResult;
            context.setNextPushEObject(newTarget);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "change the context";
    }
}
