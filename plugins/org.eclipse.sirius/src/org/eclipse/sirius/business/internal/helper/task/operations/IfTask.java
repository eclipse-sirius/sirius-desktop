/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task.operations;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.If;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * An If task.
 * 
 * @author ymortier
 */
public class IfTask extends AbstractOperationTask {

    /** The if operation. */
    private final If ifOperation;

    /**
     * Create a new If task with the specified context and model accessor.
     * 
     * @param context
     *            the current context.
     * @param extPackage
     *            the model accessor.
     * @param ifOperation
     *            the if operation.
     * @param interpreter
     *            the interpreter to use.
     */
    public IfTask(CommandContext context, ModelAccessor extPackage, If ifOperation, IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        this.ifOperation = ifOperation;
    }

    @Override
    public void execute() {
        final EObject context = this.getContext().getCurrentTarget();
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(this.getContext().getCurrentTarget());
        final boolean conditionAccepted = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(context, ifOperation, ToolPackage.eINSTANCE.getIf_ConditionExpression(), true);
        if (!conditionAccepted) {
            this.getChildrenTasks().clear();
        }
    }

    @Override
    public String getLabel() {
        return MessageFormat.format(Messages.IfTask_label, ifOperation.getConditionExpression());
    }
}
