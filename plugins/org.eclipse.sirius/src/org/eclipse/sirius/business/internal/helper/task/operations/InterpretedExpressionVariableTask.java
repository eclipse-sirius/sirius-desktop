/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.command.CommandContext;

/**
 * A task to use an interpreted expression variable.
 * 
 * @author mchauvin
 */
public final class InterpretedExpressionVariableTask extends AbstractOperationTask {

    /**
     * The kind for a set task.
     */
    public static final int KIND_SET = 0;

    /**
     * The kind for a unset task.
     */
    public static final int KIND_UNSET = 1;

    private int kind;

    private String name;

    private Object value;

    /**
     * Create a set or unset task.
     * 
     * @param context
     *            the current context
     * @param extPackage
     *            the extended package
     * @param kind
     *            {@link InterpretedExpressionVariableTask#KIND_SET} or
     *            {@link InterpretedExpressionVariableTask#KIND_UNSET}
     * @param name
     *            the name
     * @param value
     *            the value
     * @param interpreter
     *            the {@link IInterpreter} to be used
     */
    public InterpretedExpressionVariableTask(final CommandContext context, final ModelAccessor extPackage, final int kind, final String name, final Object value, final IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        this.kind = kind;
        this.name = name;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {

        switch (kind) {

        case KIND_SET:
            interpreter.setVariable(this.name, this.value);
            break;

        case KIND_UNSET:
            interpreter.unSetVariable(this.name);
            break;
        default:
            break;
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.InterpretedExpressionVariableTask_label;
    }

}
