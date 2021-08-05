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
package org.eclipse.sirius.business.internal.helper.task.operations;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.ICreationTask;
import org.eclipse.sirius.business.internal.helper.task.ExecuteToolOperationTask;
import org.eclipse.sirius.business.internal.helper.task.IDeletionTask;
import org.eclipse.sirius.business.internal.helper.task.IModificationTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * A task wich dynamic create subtask and execute them.
 * 
 * @author mchauvin
 */
public class ForTask extends AbstractOperationTask implements ICreationTask, IDeletionTask {

    /** the user interface callback. */
    private final UICallBack uiCallback;

    private final For forOp;

    /**
     * Defualt constructor.
     * 
     * @param extPackage
     *            the extended package
     * @param context
     *            the command context
     * @param forOp
     *            the operation
     * @param interpreter
     *            the interpreter to use.
     * @param uiCallback
     *            the {@link UICallBack}
     */
    public ForTask(CommandContext context, ModelAccessor extPackage, For forOp, IInterpreter interpreter, UICallBack uiCallback) {
        super(context, extPackage, interpreter);
        this.forOp = forOp;
        this.uiCallback = uiCallback;
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        // create at the runtime the children tasks.
        final List<?> contextTargets = CommandContext.getContextTargets(forOp, context);

        final String iteratorName = forOp.getIteratorName();
        EObject childOperationsContext = context.getCurrentTarget();

        for (final Object currentTarget : contextTargets) {
            ICommandTask childTask;
            if (currentTarget instanceof EObject) {
                childOperationsContext = (EObject) currentTarget;
            }

            childTask = new InterpretedExpressionVariableTask(context, extPackage, InterpretedExpressionVariableTask.KIND_SET, iteratorName, currentTarget, interpreter);
            this.getChildrenTasks().add(childTask);
            final Iterator<ModelOperation> iterModelOperations = forOp.getSubModelOperations().iterator();
            while (iterModelOperations.hasNext()) {
                final ModelOperation currentOperation = iterModelOperations.next();
                // recursive call
                childTask = new ExecuteToolOperationTask(extPackage, childOperationsContext, context.getRepresentation(), currentOperation, uiCallback);
                this.getChildrenTasks().add(childTask);
            }
            childTask = new InterpretedExpressionVariableTask(context, extPackage, InterpretedExpressionVariableTask.KIND_UNSET, iteratorName, currentTarget, interpreter);
            this.getChildrenTasks().add(childTask);
        }

        // execute the created children
        for (ICommandTask childTask : this.getChildrenTasks()) {
            childTask.execute();
        }
    }

    @Override
    public boolean executeMyselfChildrenTasks() {
        return true;
    }

    @Override
    public String getLabel() {
        return Messages.ForTask_label;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Collection<DRepresentationElement> getCreatedRepresentationElements() {
        return Collections.emptySet();
    }
}
