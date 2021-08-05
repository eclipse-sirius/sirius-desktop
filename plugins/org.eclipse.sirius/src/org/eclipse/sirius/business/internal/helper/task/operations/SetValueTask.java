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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.internal.helper.task.IModificationTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A task operation to set a value.
 * 
 * @author mchauvin
 */
public class SetValueTask extends AbstractOperationTask implements IModificationTask {

    /** The operation. */
    private SetValue op;

    /** The operation object. */
    private SetObject opObject;

    private final RuntimeLoggerInterpreter safeInterpreter;

    /**
     * The object affected by this SetValue Task.
     */
    private EObject affectedObject;

    /**
     * Create a new {@link SetValueTask}.
     * 
     * @param context
     *            the context.
     * @param exPackage
     *            the extended package.
     * @param op
     *            the 'set/add' operation.
     * @param interpreter
     *            the interpreter to use.
     */
    public SetValueTask(CommandContext context, ModelAccessor exPackage, SetValue op, IInterpreter interpreter) {
        super(context, exPackage, interpreter);
        this.op = op;
        this.safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
    }

    /**
     * Create a new {@link SetValueCommandEx}.
     * 
     * @param exPackage
     *            the extended package.
     * @param context
     *            the context.
     * @param op
     *            the 'set/add' operation.
     * @param interpreter
     *            the interpreter to use.
     */
    public SetValueTask(CommandContext context, ModelAccessor exPackage, SetObject op, IInterpreter interpreter) {
        super(context, exPackage, interpreter);
        this.opObject = op;
        this.safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
    }

    @Override
    public String getLabel() {
        return Messages.SetValueTask_label;
    }

    @Override
    public void execute() {
        final String featureExp = op == null ? opObject.getFeatureName() : op.getFeatureName();
        final String featureName = getFeatureName(context.getCurrentTarget(), op, featureExp);

        final Object value;
        if (op != null) {
            value = safeInterpreter.evaluate(context.getCurrentTarget(), op, ToolPackage.eINSTANCE.getSetValue_ValueExpression());
        } else {
            value = opObject.getObject();
        }

        try {
            affectedObject = context.getCurrentTarget();
            extPackage.eSet(context.getCurrentTarget(), featureName, value);
        } catch (final FeatureNotFoundException e) {
            RuntimeLoggerManager.INSTANCE.error(op, null, e);
        }
    }

    @Override
    public Collection<EObject> getAffectedElements() {
        if (affectedObject != null) {
            return Collections.singletonList(affectedObject);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Collection<EObject> getCreatedReferences() {
        return Collections.emptySet();
    }
}
