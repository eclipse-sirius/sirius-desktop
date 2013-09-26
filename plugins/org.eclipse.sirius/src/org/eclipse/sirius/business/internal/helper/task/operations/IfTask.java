/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.If;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * An If task.
 * 
 * @author ymortier
 */
public class IfTask extends AbstractOperationTask {

    /** The if operation. */
    private If ifOperation;

    /**
     * Create a new If task with the specified context and model accessor.
     * 
     * @param context
     *            the current context.
     * @param extPackage
     *            the model accessor.
     * @param ifOperation
     *            the if operation.
     * @param session
     *            the {@link Session} to be used by this task
     */
    public IfTask(final CommandContext context, final ModelAccessor extPackage, final If ifOperation, final Session session) {
        super(context, extPackage, session.getInterpreter());
        this.ifOperation = ifOperation;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        final EObject context = this.getContext().getCurrentTarget();
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(this.getContext().getCurrentTarget());
        final boolean conditionAccepted = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(context, ifOperation, ToolPackage.eINSTANCE.getIf_ConditionExpression(), true);

        if (!conditionAccepted) {
            this.getChildrenTasks().clear();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "Evaluate : " + ifOperation.getConditionExpression();
    }

}
