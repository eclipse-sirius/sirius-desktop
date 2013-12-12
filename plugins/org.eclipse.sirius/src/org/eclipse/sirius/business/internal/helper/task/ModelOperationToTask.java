/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task;

import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ChangeContextTask;
import org.eclipse.sirius.business.internal.helper.task.operations.CreateInstanceTask;
import org.eclipse.sirius.business.internal.helper.task.operations.CreateViewTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ExternalJavaActionTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ForTask;
import org.eclipse.sirius.business.internal.helper.task.operations.IfTask;
import org.eclipse.sirius.business.internal.helper.task.operations.MoveElementTask;
import org.eclipse.sirius.business.internal.helper.task.operations.NavigationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.RemoveElementTask;
import org.eclipse.sirius.business.internal.helper.task.operations.SetValueTask;
import org.eclipse.sirius.business.internal.helper.task.operations.SwitchTask;
import org.eclipse.sirius.business.internal.helper.task.operations.UnsetTask;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.CreateView;
import org.eclipse.sirius.viewpoint.description.tool.DeleteView;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.If;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.Navigation;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
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
    private ModelAccessor extPackage;

    /**
     * UI call back.
     */
    private UICallBack uiCallback;

    /** The Session on which this task will be executed. */
    private Session session;

    /**
     * The context.
     */
    private CommandContext context;

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
        super();
        this.extPackage = extPackage;
        this.uiCallback = uiCallback;
        this.session = session;
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
        if (op instanceof CreateInstance) {
            final CreateInstance createOp = (CreateInstance) op;
            task = new CreateInstanceTask(context, extPackage, createOp, session.getInterpreter());
        } else if (op instanceof SetValue) {
            final SetValue setOp = (SetValue) op;
            task = new SetValueTask(context, extPackage, setOp, session);
        } else if (op instanceof SetObject) {
            final SetObject setOp = (SetObject) op;
            task = new SetValueTask(context, extPackage, setOp, session);
        } else if (op instanceof ChangeContext) {
            final ChangeContext changeOp = (ChangeContext) op;
            task = new ChangeContextTask(context, extPackage, changeOp, session.getInterpreter());
        } else if (op instanceof MoveElement) {
            final MoveElement moveOp = (MoveElement) op;
            task = new MoveElementTask(context, extPackage, moveOp, session);
        } else if (op instanceof RemoveElement) {
            final RemoveElement removeOp = (RemoveElement) op;
            task = new RemoveElementTask(context, extPackage, removeOp, session);
        } else if (op instanceof For) {
            final For forOp = (For) op;
            task = new ForTask(context, extPackage, forOp, session, uiCallback);
        } else if (op instanceof Unset) {
            final Unset unset = (Unset) op;
            task = new UnsetTask(context, extPackage, unset, session);
        } else if (op instanceof CreateView) {
            final CreateView createView = (CreateView) op;
            task = new CreateViewTask(context, extPackage, createView, session.getInterpreter());
        } else if (op instanceof If) {
            final If ifOp = (If) op;
            task = new IfTask(context, extPackage, ifOp, session);
        } else if (op instanceof DeleteView) {
            final DeleteView deleteView = (DeleteView) op;
            task = new RemoveElementTask(extPackage, context, deleteView, session);
        } else if (op instanceof ExternalJavaAction) {
            final ExternalJavaAction externalJavaAction = (ExternalJavaAction) op;
            task = new ExternalJavaActionTask(context, extPackage, externalJavaAction, session, uiCallback);
        } else if (op instanceof ExternalJavaActionCall) {
            final ExternalJavaActionCall call = (ExternalJavaActionCall) op;
            task = new ExternalJavaActionTask(context, extPackage, call.getAction(), session, uiCallback);
        } else if (op instanceof Navigation) {
            final Navigation doubleClickNavigation = (Navigation) op;
            task = new NavigationTask(context, extPackage, doubleClickNavigation, session, uiCallback);
        } else if (op instanceof Switch) {
            final Switch switchOp = (Switch) op;
            task = new SwitchTask(context, extPackage, switchOp, session, uiCallback);
        }
        return task;
    }

    /**
     * {@inheritDoc}
     */
    public ICommandTask apply(ModelOperation from) {
        ICommandTask result = createTask(from);
        if (from instanceof ContainerModelOperation) {
            result.getChildrenTasks().addAll(Collections2.transform(((ContainerModelOperation) from).getSubModelOperations(), this));
        }
        return result;
    }
}
