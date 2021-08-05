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

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ChangeContextTask;
import org.eclipse.sirius.business.internal.helper.task.operations.CreateInstanceTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ExternalJavaActionTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ForTask;
import org.eclipse.sirius.business.internal.helper.task.operations.IfTask;
import org.eclipse.sirius.business.internal.helper.task.operations.LetTask;
import org.eclipse.sirius.business.internal.helper.task.operations.MoveElementTask;
import org.eclipse.sirius.business.internal.helper.task.operations.RemoveElementTask;
import org.eclipse.sirius.business.internal.helper.task.operations.SetValueTask;
import org.eclipse.sirius.business.internal.helper.task.operations.SwitchTask;
import org.eclipse.sirius.business.internal.helper.task.operations.UnexecutableOperationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.UnsetTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.DeleteView;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.If;
import org.eclipse.sirius.viewpoint.description.tool.Let;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.Unset;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * Transform operation object to task.
 * 
 * @author jdupont
 * 
 */
public class ModelOperationToTask implements Function<ModelOperation, ICommandTask> {

    /**
     * Ext package.
     */
    private final ModelAccessor extPackage;

    /**
     * UI call back.
     */
    private final UICallBack uiCallback;

    /** The Session on which this task will be executed. */
    private final Session session;

    /**
     * The interpreter to use for tasks that need to evaluate expressions.
     */
    private final IInterpreter interpreter;

    /**
     * The context.
     */
    private final CommandContext context;

    /**
     * Transform model operations to tasks instances.
     * 
     * @param extPackage
     *            access to semantic model.
     * @param uiCallback
     *            user interface interactions.
     * @param session
     *            the {@link Session} to be used.
     * @param context
     *            current context.
     */
    public ModelOperationToTask(ModelAccessor extPackage, UICallBack uiCallback, Session session, CommandContext context) {
        this.extPackage = extPackage;
        this.uiCallback = uiCallback;
        this.session = session;
        this.interpreter = session.getInterpreter();
        this.context = context;
    }

    /**
     * Create a new task.
     * 
     * @param op
     *            the operation
     * @return the created task
     */
    public AbstractOperationTask createTask(final ModelOperation op) {
        AbstractOperationTask task = null;
        Option<? extends AbstractCommandTask> optionalDialectTask = DialectManager.INSTANCE.createTask(context, extPackage, op, session, uiCallback);
        if (optionalDialectTask.some() && optionalDialectTask.get() instanceof AbstractOperationTask) {
            task = (AbstractOperationTask) optionalDialectTask.get();
        } else if (op instanceof CreateInstance) {
            task = new CreateInstanceTask(context, extPackage, (CreateInstance) op, interpreter);
        } else if (op instanceof SetValue) {
            task = new SetValueTask(context, extPackage, (SetValue) op, interpreter);
        } else if (op instanceof SetObject) {
            task = new SetValueTask(context, extPackage, (SetObject) op, interpreter);
        } else if (op instanceof ChangeContext) {
            task = new ChangeContextTask(context, extPackage, (ChangeContext) op, interpreter);
        } else if (op instanceof MoveElement) {
            task = new MoveElementTask(context, extPackage, (MoveElement) op, interpreter);
        } else if (op instanceof RemoveElement) {
            task = new RemoveElementTask(context, extPackage, (RemoveElement) op, interpreter);
        } else if (op instanceof For) {
            task = new ForTask(context, extPackage, (For) op, interpreter, uiCallback);
        } else if (op instanceof Unset) {
            task = new UnsetTask(context, extPackage, (Unset) op, interpreter);
        } else if (op instanceof If) {
            task = new IfTask(context, extPackage, (If) op, interpreter);
        } else if (op instanceof DeleteView) {
            task = new RemoveElementTask(extPackage, context, (DeleteView) op, interpreter);
        } else if (op instanceof ExternalJavaAction) {
            task = new ExternalJavaActionTask(context, extPackage, (ExternalJavaAction) op, interpreter, uiCallback);
        } else if (op instanceof ExternalJavaActionCall) {
            task = new ExternalJavaActionTask(context, extPackage, ((ExternalJavaActionCall) op).getAction(), interpreter, uiCallback);
        } else if (op instanceof Switch) {
            task = new SwitchTask(context, extPackage, (Switch) op, session, uiCallback);
        } else if (op instanceof Let) {
            task = new LetTask(context, extPackage, (Let) op, interpreter);
        }

        if (task == null) {
            Optional<ICommandTask> optionalTask = Optional.empty();

            Iterator<ModelOperationManagerDescriptor> iterator = ModelOperationManagerRegistry.getRegisteredExtensions().iterator();
            while (iterator.hasNext() && !optionalTask.isPresent()) {
                ModelOperationManagerDescriptor descriptor = iterator.next();
                optionalTask = descriptor.getModelOperationManager().createTask(op, extPackage, uiCallback, session, interpreter, context);
            }
            if (!optionalTask.isPresent()) {
                task = UnexecutableOperationTask.getInstance();
                SiriusPlugin.getDefault().getLog()
                        .log(new Status(IStatus.WARNING, SiriusPlugin.ID, MessageFormat.format(Messages.ModelOperationToTask_cannotCreateTaskWarningMsg, op.eClass().getName())));
            } else if (optionalTask.isPresent() && optionalTask.get() instanceof AbstractOperationTask) {
                task = (AbstractOperationTask) optionalTask.get();
            }
        }
        Option<EObject> opt = new EObjectQuery(op).getFirstAncestorOfType(ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION);
        if (opt.some()) {
            task.setSourceTool((AbstractToolDescription) opt.get());
        }
        return task;
    }

    @Override
    public ICommandTask apply(ModelOperation from) {
        ICommandTask result = createTask(from);
        if (from instanceof ContainerModelOperation) {
            result.getChildrenTasks().addAll(Collections2.transform(((ContainerModelOperation) from).getSubModelOperations(), this));
        }
        return result;
    }
}
