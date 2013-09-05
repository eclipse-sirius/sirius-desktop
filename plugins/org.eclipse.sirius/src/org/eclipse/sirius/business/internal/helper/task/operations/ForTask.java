/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.DRepresentationElement;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.ICreationTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.ExecuteToolOperationTask;
import org.eclipse.sirius.business.internal.helper.task.IDeletionTask;
import org.eclipse.sirius.business.internal.helper.task.IModificationTask;
import org.eclipse.sirius.description.tool.For;
import org.eclipse.sirius.description.tool.ModelOperation;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;

/**
 * A task wich dynamic create subtask and execute them.
 * 
 * @author mchauvin
 */
public class ForTask extends AbstractOperationTask implements ICreationTask, IDeletionTask {

    /** the user interface callback. */
    private UICallBack uiCallback;

    private Session session;

    private For forOp;

    /**
     * Defualt constructor.
     * 
     * @param extPackage
     *            the extended package
     * @param context
     *            the command context
     * @param forOp
     *            the operation
     * @param session
     *            the {@link Session} to be used to this task
     * @param uiCallback
     *            the {@link UICallBack}
     */
    public ForTask(final CommandContext context, final ModelAccessor extPackage, final For forOp, final Session session, final UICallBack uiCallback) {
        super(context, extPackage, session.getInterpreter());
        this.forOp = forOp;
        this.session = session;
        this.uiCallback = uiCallback;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {

        // create at the runtime the children tasks.
        final List<?> contextTargets = CommandContext.getContextTargets(forOp, context);
        final Iterator<?> iterTarget = contextTargets.iterator();
        EObject childOperationsContext = context.getCurrentTarget();
        while (iterTarget.hasNext()) {
            final Object currentTarget = iterTarget.next();
            final String iteratorName = forOp.getIteratorName();
            ICommandTask childTask;

            if (currentTarget instanceof EObject) {
                childOperationsContext = (EObject) currentTarget;
            }

            childTask = new InterpretedExpressionVariableTask(context, extPackage, InterpretedExpressionVariableTask.KIND_SET, iteratorName, currentTarget, session.getInterpreter());
            this.getChildrenTasks().add(childTask);
            final Iterator<ModelOperation> iterModelOperations = forOp.getSubModelOperations().iterator();
            while (iterModelOperations.hasNext()) {
                final ModelOperation currentOperation = iterModelOperations.next();
                // recursive call
                childTask = new ExecuteToolOperationTask(extPackage, childOperationsContext, context.getRepresentation(), currentOperation, uiCallback);
                this.getChildrenTasks().add(childTask);
            }
            childTask = new InterpretedExpressionVariableTask(context, extPackage, InterpretedExpressionVariableTask.KIND_UNSET, iteratorName, currentTarget, session.getInterpreter());
            this.getChildrenTasks().add(childTask);
        }

        // execute the created children
        final Iterator<ICommandTask> it = this.getChildrenTasks().iterator();
        while (it.hasNext()) {
            final ICommandTask childTask = it.next();
            childTask.execute();
        }
    }

    /**
     * This task execute itself the children tacks. {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.AbstractCommandTask#executeMyselfChildrenTasks()
     */
    @Override
    public boolean executeMyselfChildrenTasks() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "a for task";
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getCreatedReferences()
     */
    public Collection<EObject> getCreatedReferences() {
        final Collection<EObject> result = new HashSet<EObject>();
        final Iterator<ICommandTask> it = this.getChildrenTasks().iterator();
        while (it.hasNext()) {
            final ICommandTask task = it.next();
            if (task instanceof IModificationTask) {
                result.addAll(((IModificationTask) task).getCreatedReferences());
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getAffectedElements()
     */
    public Collection<EObject> getAffectedElements() {
        final Collection<EObject> result = new HashSet<EObject>();
        final Iterator<ICommandTask> it = this.getChildrenTasks().iterator();
        while (it.hasNext()) {
            final ICommandTask task = it.next();
            if (task instanceof IModificationTask) {
                result.addAll(((IModificationTask) task).getAffectedElements());
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICreationTask#getCreatedElements()
     */
    public Collection<EObject> getCreatedElements() {
        final Collection<EObject> result = new HashSet<EObject>();
        final Iterator<ICommandTask> it = this.getChildrenTasks().iterator();
        while (it.hasNext()) {
            final ICommandTask task = it.next();
            if (task instanceof ICreationTask) {
                result.addAll(((ICreationTask) task).getCreatedElements());
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IDeletionTask#getDeletedElements()
     */
    public Collection<EObject> getDeletedElements() {
        final Collection<EObject> result = new HashSet<EObject>();
        final Iterator<ICommandTask> it = this.getChildrenTasks().iterator();
        while (it.hasNext()) {
            final ICommandTask task = it.next();
            if (task instanceof IDeletionTask) {
                result.addAll(((IDeletionTask) task).getDeletedElements());
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICreationTask#getCreatedRepresentationElements()
     */
    public Collection<DRepresentationElement> getCreatedRepresentationElements() {
        return Collections.emptySet();
    }
}
