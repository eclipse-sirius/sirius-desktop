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
package org.eclipse.sirius.tools.api.validation.constraint;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * 
 * ConstraintStatus wrapping another ConstraintStatus but being able to provide
 * the source {@link ValidationRule} for the status. Every call will gets
 * delegated to the wrapped status.
 * 
 * @author cbrun
 * 
 */
public class RuleWrappingStatus extends ConstraintStatus {

    private IStatus wrapped;

    private ValidationRule rule;

    /**
     * Build a new Wrapper.
     * 
     * @param emfStatus
     *            wrapped status.
     * @param failedRule
     *            validation rule to provide.
     */
    public RuleWrappingStatus(final ConstraintStatus emfStatus, final ValidationRule failedRule) {
        super(emfStatus.getConstraint(), emfStatus.getTarget());
        this.wrapped = emfStatus;
        this.rule = failedRule;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus[] getChildren() {
        return wrapped.getChildren();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCode() {
        return wrapped.getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Throwable getException() {
        return wrapped.getException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return wrapped.getMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlugin() {
        return wrapped.getPlugin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSeverity() {
        return wrapped.getSeverity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMultiStatus() {
        return wrapped.isMultiStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOK() {
        return wrapped.isOK();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(final int arg0) {
        return wrapped.matches(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public ValidationRule getOriginRule() {
        return rule;
    }

}
