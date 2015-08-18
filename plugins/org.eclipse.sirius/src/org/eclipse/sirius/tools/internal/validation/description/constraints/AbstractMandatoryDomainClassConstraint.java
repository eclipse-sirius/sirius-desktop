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
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;

/**
 * Validates mandatory DomainClass attributes.
 * 
 * @author ymortier
 */
public abstract class AbstractMandatoryDomainClassConstraint extends AbstractConstraint {

    /** The name of the DomainClass feature. */
    private static final String DOMAIN_CLASS_FEATURE = "domainClass"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    public IStatus validate(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        final EMFEventType eventType = ctx.getEventType();

        // In the case of batch mode.
        if (eventType == EMFEventType.NULL) {
            if (isElementContainedInAKnownMetamodel(eObj)) {
                final EStructuralFeature domainClassFeature = eObj.eClass().getEStructuralFeature(DOMAIN_CLASS_FEATURE);
                if (domainClassFeature != null) {
                    final Object[] result = checkError(domainClassFeature, eObj);
                    if (result != null) {
                        return ctx.createFailureStatus(result);
                    }
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    private Object[] checkError(final EStructuralFeature domainClassFeature, final EObject eObj) {
        final Object value = eObj.eGet(domainClassFeature);
        if ((value == null || (value instanceof String && StringUtil.isEmpty((String) value))) && !canHaveNullDomainClass(eObj)) {
            return new Object[] {};
        }
        return null;
    }

    /**
     * Return true if this EObject can have null domain class, false otherwise.
     * 
     * @param instance
     *            the instance.
     * 
     * @return true if this EObject can have null domain class, false otherwise.
     */
    protected boolean canHaveNullDomainClass(EObject instance) {
        return false;
    }
}
