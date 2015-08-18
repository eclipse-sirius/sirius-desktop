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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.ModelOperationToTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * An Switch task.
 * 
 * @author jdupont
 */
public class SwitchTask extends AbstractOperationTask {

    /** The Switch operation. */
    private Switch switchOperation;

    /**
     * The UI Call back.
     */
    private UICallBack uiCallback;

    /** The Session on which is executed this task. */
    private Session session;

    /**
     * Create a new Switch task with the specified context and model accessor.
     * 
     * @param context
     *            the current context.
     * @param extPackage
     *            the model accessor.
     * @param switchOperation
     *            the switch operation.
     * @param session
     *            the {@link Session}.
     * @param uiCallback
     *            the user interface.
     */
    public SwitchTask(final CommandContext context, final ModelAccessor extPackage, final Switch switchOperation, Session session, UICallBack uiCallback) {
        super(context, extPackage, session.getInterpreter());
        this.switchOperation = switchOperation;
        this.uiCallback = uiCallback;
        this.session = session;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        final EObject context = this.getContext().getCurrentTarget();
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(this.getContext().getCurrentTarget());
        boolean conditionAccepted = false;
        Collection<ModelOperation> operations = Lists.newArrayList();

        /*
         * retrieve the good model operations to execute
         */

        Iterator<Case> it = switchOperation.getCases().iterator();
        while (!conditionAccepted && it.hasNext()) {
            Case caseOperation = it.next();
            conditionAccepted = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(context, caseOperation, ToolPackage.eINSTANCE.getCase_ConditionExpression(), true);
            if (conditionAccepted) {
                operations = caseOperation.getSubModelOperations();
            }
        }
        if (!conditionAccepted && switchOperation.getDefault() != null) {
            operations = switchOperation.getDefault().getSubModelOperations();
        }

        // Transforms all model operations to tasks. Case operation and default
        // operation just serve to evaluate the condition. They have no
        // corresponding task because they are the son that interest us
        Collection<ICommandTask> childTasks = Collections2.transform(operations, new ModelOperationToTask(extPackage, uiCallback, session, this.context));
        getChildrenTasks().addAll(childTasks);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return ""; //$NON-NLS-1$
    }

}
