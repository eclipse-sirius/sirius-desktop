/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.helper.task;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ForTask;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

import com.google.common.base.Preconditions;

/**
 * The main task for operations of a tool.
 * 
 * @author mchauvin
 */
public class ExecuteToolOperationTask extends AbstractCommandTask {

    /** The root operation task. */
    private AbstractOperationTask rootOperationTask;

    /** */
    private ModelAccessor extPackage;

    /** the user interface callback. */
    private UICallBack uiCallback;

    private Session session;

    /**
     * Default constructor.
     * 
     * @param extPackage
     *            the extended package
     * @param target
     *            the target
     * @param op
     *            the operation
     * 
     * @param uiCallback
     *            the {@link UICallBack}
     * @since 4.0.0
     */
    public ExecuteToolOperationTask(final ModelAccessor extPackage, final EObject target, final ModelOperation op, final UICallBack uiCallback) {
        this(extPackage, target, null, op, uiCallback);
    }

    /**
     * Default constructor.
     * 
     * @param extPackage
     *            the extended package
     * @param target
     *            the target
     * @param representation
     *            current representation
     * @param op
     *            the operation
     * 
     * @param uiCallback
     *            the {@link UICallBack}
     */
    public ExecuteToolOperationTask(final ModelAccessor extPackage, final EObject target, final DRepresentation representation, final ModelOperation op, final UICallBack uiCallback) {
        this.extPackage = extPackage;
        this.uiCallback = uiCallback;
        this.session = new EObjectQuery(target).getSession();
        if (session == null && representation != null) {
            this.session = new EObjectQuery(representation).getSession();
        }
        Preconditions.checkArgument(session != null, Messages.ExecuteToolOperationTask_sessionNotFound);
        final CommandContext context = new CommandContext(target, representation);
        this.rootOperationTask = createTask(op, context);
        // for task creates children tasks at runtime
        if (!(rootOperationTask instanceof ForTask) && op instanceof ContainerModelOperation) {
            createChildrenTasks(rootOperationTask, (ContainerModelOperation) op, context);
        }
        this.getChildrenTasks().add(rootOperationTask);
    }

    @Override
    public void execute() {
        CommandContext context = this.rootOperationTask.getContext();
        executeTask(this.rootOperationTask, context);
    }

    @Override
    public String getLabel() {
        return Messages.ExecuteToolOperationTask_label;
    }

    private void executeTask(final ICommandTask task, final CommandContext context) {
        CommandContext.pushContext(context);
        try {
            task.execute();
        } catch (MetaClassNotFoundException | FeatureNotFoundException e) {
            SiriusPlugin.getDefault().error(Messages.TaskExecutor_errorModifyingModelMsg, e);
        }
        if (!(task instanceof ForTask)) {
            CommandContext.pushContext(context);
            for (ICommandTask childTask : task.getChildrenTasks()) {
                executeTask(childTask, context);
            }
            CommandContext.popContext(context);
        }
        CommandContext.popContext(context);
    }

    private void createChildrenTasks(final ICommandTask parent, final ContainerModelOperation op, final CommandContext context) {
        final Iterator<ModelOperation> it = op.getSubModelOperations().iterator();
        while (it.hasNext()) {
            final ModelOperation childOp = it.next();
            final ICommandTask childTask = createTask(childOp, context);
            parent.getChildrenTasks().add(childTask);
            // for task creates children tasks at runtime
            if (!(childTask instanceof ForTask) && childOp instanceof ContainerModelOperation) {
                createChildrenTasks(childTask, (ContainerModelOperation) childOp, context);
            }
        }
    }

    /**
     * Create a new task.
     * 
     * @param op
     *            the operation
     * @param context
     *            the context
     * @return teh created task
     */
    public AbstractOperationTask createTask(final ModelOperation op, final CommandContext context) {
        return new ModelOperationToTask(extPackage, uiCallback, session, context).createTask(op);
    }

    @Override
    public boolean executeMyselfChildrenTasks() {
        return true;
    }

}
