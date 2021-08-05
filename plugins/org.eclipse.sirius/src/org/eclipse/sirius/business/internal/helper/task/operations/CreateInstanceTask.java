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

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.ICreationTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;

/**
 * A task operation to create a new instance.
 * 
 * @author mchauvin
 */
public class CreateInstanceTask extends AbstractOperationTask implements ICreationTask {

    /** The operation. */
    private CreateInstance createOp;

    /** The target. */
    private EObject target;

    private String referenceName;

    /** The created instance. */
    private EObject instance;

    /**
     * Default constructor.
     * 
     * @param context
     *            the current context
     * @param extPackage
     *            the extended package
     * @param createInstance
     *            the instance
     * @param interpreter
     *            the {@link IInterpreter} to be used
     */
    public CreateInstanceTask(final CommandContext context, final ModelAccessor extPackage, final CreateInstance createInstance, final IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        this.createOp = createInstance;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        target = context.getCurrentTarget();
        final String typeName = getFeatureName(target, createOp, createOp.getTypeName());
        instance = extPackage.createInstance(typeName);
        if (instance == null) {
            // the creation failed
            SiriusPlugin.getDefault().error(MessageFormat.format(Messages.CreateInstanceTask_creationErrorMsg, typeName), new RuntimeException());
        } else {
            if (!StringUtil.isEmpty(createOp.getVariableName())) {
                ICommandTask childTask;
                childTask = new InterpretedExpressionVariableTask(context, extPackage, InterpretedExpressionVariableTask.KIND_SET, createOp.getVariableName(), instance, interpreter);
                childTask.execute();
            }
            referenceName = getFeatureName(target, createOp, createOp.getReferenceName());
            if (!extPackage.eIsMany(target, referenceName)) {
                // The reference upper bound is 1. try to see if a value is
                // already specified.
                final Object value = extPackage.eGet(target, referenceName);
                if (value != null) {
                    SiriusPlugin.getDefault().error(MessageFormat.format(Messages.CreateInstanceTask_addToRefErrorMsg, referenceName, target), new RuntimeException());
                    return;
                }
            }
            extPackage.eAdd(target, referenceName, instance);
            context.setNextPushEObject(instance);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.CreateInstanceTask_label;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICreationTask#getCreatedElements()
     */
    @Override
    public Collection<EObject> getCreatedElements() {
        final Collection<EObject> result = new HashSet<EObject>(1);
        if (instance != null) {
            result.add(instance);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getAffectedElements()
     */
    @Override
    public Collection<EObject> getAffectedElements() {
        final Collection<EObject> result = new HashSet<EObject>(1);
        if (target != null) {
            result.add(target);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getCreatedReferences()
     */
    @Override
    public Collection<EObject> getCreatedReferences() {
        // not applicable
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICreationTask#getCreatedRepresentationElements()
     */
    @Override
    public Collection<DRepresentationElement> getCreatedRepresentationElements() {
        // nothing
        return Collections.emptySet();
    }

}
