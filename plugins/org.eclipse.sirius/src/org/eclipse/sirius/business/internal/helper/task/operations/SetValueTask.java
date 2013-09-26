/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.IModificationTask;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;

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

    private RuntimeLoggerInterpreter safeInterpreter;

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
     * @param session
     *            the {@link Session} to be used
     */
    public SetValueTask(final CommandContext context, final ModelAccessor exPackage, final SetValue op, final Session session) {
        super(context, exPackage, session.getInterpreter());
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
     * @param session
     *            the {@link Session} to be used
     */
    public SetValueTask(final CommandContext context, final ModelAccessor exPackage, final SetObject op, final Session session) {
        super(context, exPackage, session.getInterpreter());
        this.opObject = op;
        this.safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "Set a value";
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {

        Object value = null;
        final String featureExp = op == null ? opObject.getFeatureName() : op.getFeatureName();
        final String featureName = getFeatureName(context.getCurrentTarget(), op, featureExp);

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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getAffectedElements()
     */
    public Collection<EObject> getAffectedElements() {
        final Collection<EObject> result = new LinkedList<EObject>();
        /* affectedObject could be null if the execute operation failed */
        if (affectedObject != null) {
            result.add(affectedObject);
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getCreatedReferences()
     */
    public Collection<EObject> getCreatedReferences() {
        return Collections.emptySet();
    }

}
