/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo.
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
package org.eclipse.sirius.business.internal.helper.task.operations;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.description.tool.Let;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A task operation to create a new variable.
 * 
 * @author sbegaudeau
 */
public class LetTask extends AbstractOperationTask {

    /**
     * The operation.
     */
    private Let let;

    /**
     * Default constructor.
     * 
     * @param context
     *            The current context
     * @param extPackage
     *            The extended package
     * @param let
     *            The operation
     * @param interpreter
     *            The {@link IInterpreter} to be used
     */
    public LetTask(CommandContext context, ModelAccessor extPackage, Let let, IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        this.let = let;
    }

    @Override
    public String getLabel() {
        return Messages.LetTask_label;
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        final EObject currentTarget = this.getContext().getCurrentTarget();
        Object value = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluate(currentTarget, this.let, ToolPackage.eINSTANCE.getLet_ValueExpression());

        String variableName = Optional.ofNullable(this.let.getVariableName()).orElse(""); //$NON-NLS-1$
        if (!variableName.isEmpty()) {
            if ("self".equals(variableName) && value instanceof EObject) { //$NON-NLS-1$
                context.setNextPushEObject((EObject) value);
            } else {
                ICommandTask childTask = new InterpretedExpressionVariableTask(context, extPackage, InterpretedExpressionVariableTask.KIND_SET, variableName, value, interpreter);
                childTask.execute();
            }
        }
    }

}
