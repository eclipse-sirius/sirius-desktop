/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.internal.helper.task.IModificationTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A task operation to move element.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class MoveElementTask extends AbstractOperationTask implements IModificationTask {

    /** The operation. */
    private final MoveElement op;

    private final RuntimeLoggerInterpreter safeInterpreter;

    /** The container of the ERef. */
    private EObject container;

    /** The element to move. */
    private EObject element;

    /**
     * Create a new {@link MoveElementTask}.
     * 
     * @param context
     *            the current context.
     * @param extPackage
     *            the extended package.
     * @param op
     *            the operation.
     * @param interpreter
     *            the interpreter to use.
     */
    public MoveElementTask(final CommandContext context, final ModelAccessor extPackage, final MoveElement op, IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        this.op = op;
        this.safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
    }

    @Override
    public void execute() {
        element = getElement();
        container = getContainer();
        final String featureName = getFeatureName(element, op, op.getFeatureName());
        try {
            if (!extPackage.eIsMany(container, featureName)) {
                // The reference upper bound is 1. try to see if a value is
                // already specified.
                final Object value = extPackage.eGet(container, featureName);
                if (value != null) {
                    SiriusPlugin.getDefault().error(MessageFormat.format(Messages.MoveElementTask_ImpossibleToAddValueErrorMsg, featureName, container), new RuntimeException());
                    return;
                }
            }
            extPackage.eAdd(container, featureName, element);
        } catch (final FeatureNotFoundException e) {
            RuntimeLoggerManager.INSTANCE.error(op, ToolPackage.eINSTANCE.getMoveElement_FeatureName(), e);
        }
    }

    @Override
    public String getLabel() {
        return Messages.MoveElementTask_label;
    }

    /**
     * Return the element to move.
     * 
     * @return the element to move.
     */
    private EObject getElement() {
        return context.getCurrentTarget();
    }

    /**
     * Return the container of the ERef.
     * 
     * @return the container of the ERef.
     */
    private EObject getContainer() {
        return safeInterpreter.evaluateEObject(context.getCurrentTarget(), op, ToolPackage.eINSTANCE.getMoveElement_NewContainerExpression());

    }

    @Override
    public Collection<EObject> getAffectedElements() {
        return Collections.singleton(container);
    }

    @Override
    public Collection<EObject> getCreatedReferences() {
        return Collections.emptySet();
    }
}
