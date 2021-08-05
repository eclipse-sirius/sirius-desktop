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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.Unset;

/**
 * Unset a reference.
 * 
 * @author Yann Mortier (ymortier)
 */
public class UnsetTask extends AbstractOperationTask {

    /** The operation. */
    private final Unset unsetOp;

    /**
     * Create a new {@link UnsetTask}.
     * 
     * @param context
     *            the current context.
     * @param extPackage
     *            the extended package.
     * @param unsetOp
     *            the operation.
     * @param interpreter
     *            the interpreter to use.
     */
    public UnsetTask(CommandContext context, ModelAccessor extPackage, Unset unsetOp, IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        if (unsetOp == null) {
            throw new IllegalArgumentException(Messages.UnsetTask_nullOperationErrorMsg);
        }
        this.unsetOp = unsetOp;
    }

    @Override
    public String getLabel() {
        return Messages.UnsetTask_label;
    }

    @Override
    public void execute() {
        final String element = unsetOp.getElementExpression();
        String featureName = unsetOp.getFeatureName();
        final EObject context = this.context.getCurrentTarget();

        try {
            featureName = interpreter.evaluateString(context, featureName);
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(unsetOp, ToolPackage.eINSTANCE.getUnset_FeatureName(), e);
        }

        if (element != null && !StringUtil.isEmpty(element)) {
            Collection<EObject> elements;

            final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
            elements = safeInterpreter.evaluateCollection(context, unsetOp, ToolPackage.eINSTANCE.getUnset_ElementExpression());

            final List<?> elementsToUnset = new ArrayList<Object>(elements);
            final Iterator<?> iterElementsToUnset = elementsToUnset.iterator();
            while (iterElementsToUnset.hasNext()) {
                final Object current = iterElementsToUnset.next();
                this.extPackage.eRemove(context, featureName, current);
            }

        } else {
            try {
                this.extPackage.eSet(context, featureName, null);
            } catch (final FeatureNotFoundException e) {
                RuntimeLoggerManager.INSTANCE.error(unsetOp, ToolPackage.eINSTANCE.getUnset_FeatureName(), e);
            }
        }
    }

}
