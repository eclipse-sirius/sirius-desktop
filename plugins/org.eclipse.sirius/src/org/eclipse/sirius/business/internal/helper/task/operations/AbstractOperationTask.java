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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.CommandContext;

/**
 * An operation specific implementation of ICommandTask.
 * 
 * @author Mariot Chauvin (mchauvin
 */
public abstract class AbstractOperationTask extends AbstractCommandTask {

    /** The context. */
    protected CommandContext context;

    /** The extension package. */
    protected ModelAccessor extPackage;

    /** The interpreter. */
    protected IInterpreter interpreter;

    /**
     * Default Constructor.
     * 
     * @param context
     *            the context
     * @param extPackage
     *            the extended package
     * @param interpreter
     *            the interpreter to be used by task
     */
    public AbstractOperationTask(final CommandContext context, final ModelAccessor extPackage, IInterpreter interpreter) {
        this.context = context;
        this.extPackage = extPackage;
        this.interpreter = interpreter;
    }

    /**
     * Get the current context.
     * 
     * @return the current context
     */
    public CommandContext getContext() {
        return this.context;
    }

    /**
     * Get the feature name.
     * 
     * @param eObj
     *            the object
     * @param featureOwner
     *            the feature owner
     * @param originalFeatureName
     *            the name
     * @return the feature name
     */
    public String getFeatureName(final EObject eObj, final EObject featureOwner, final String originalFeatureName) {
        try {
            final String result = interpreter.evaluateString(eObj, originalFeatureName);
            return result;
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(featureOwner, null, e);
        }
        return originalFeatureName;
    }
}
