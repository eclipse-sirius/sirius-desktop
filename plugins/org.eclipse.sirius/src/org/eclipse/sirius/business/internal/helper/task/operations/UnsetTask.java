/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.Unset;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;

/**
 * Unset a reference. TODOYMO comment.
 * 
 * @author Yann Mortier (ymortier)
 */
public class UnsetTask extends AbstractOperationTask {

    /** The operation. */
    private Unset unsetOp;

    /**
     * Create a new {@link UnsetTask}.
     * 
     * @param context
     *            the current context.
     * @param extPackage
     *            the extended package.
     * @param unsetOp
     *            the operation.
     * @param session
     *            the {@link Session} to be used by this task
     */
    public UnsetTask(final CommandContext context, final ModelAccessor extPackage, final Unset unsetOp, final Session session) {
        super(context, extPackage, session.getInterpreter());
        if (unsetOp == null) {
            throw new IllegalArgumentException("unsetOp is null");
        }
        this.unsetOp = unsetOp;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "unset the value";
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        final String element = unsetOp.getElementExpression();
        String featureName = unsetOp.getFeatureName();
        final EObject context = this.context.getCurrentTarget();

        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(this.context.getCurrentTarget());
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
