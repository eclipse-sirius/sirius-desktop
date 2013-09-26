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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.IModificationTask;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;

/**
 * A task operation to move element.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class MoveElementTask extends AbstractOperationTask implements IModificationTask {

    /** The operation. */
    private MoveElement op;

    private RuntimeLoggerInterpreter safeInterpreter;

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
     * @param session
     *            the {@link Session} to be used
     */
    public MoveElementTask(final CommandContext context, final ModelAccessor extPackage, final MoveElement op, final Session session) {
        super(context, extPackage, session.getInterpreter());
        this.op = op;
        this.safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
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
                    SiriusPlugin.getDefault().error("Impossible to add a value to the reference " + featureName + " of the object " + container, new RuntimeException());
                    return;
                }
            }
            extPackage.eAdd(container, featureName, element);
        } catch (final FeatureNotFoundException e) {
            RuntimeLoggerManager.INSTANCE.error(op, ToolPackage.eINSTANCE.getMoveElement_FeatureName(), e);
        }
    }

    public String getLabel() {
        return "Move an element";
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

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getAffectedElements()
     */
    public Collection<EObject> getAffectedElements() {
        return Collections.singleton(container);
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
