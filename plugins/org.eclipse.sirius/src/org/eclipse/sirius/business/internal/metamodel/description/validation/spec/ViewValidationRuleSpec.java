/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.description.validation.spec;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.metamodel.description.validation.operations.ValidationRuleSpecOperations;
import org.eclipse.sirius.viewpoint.description.validation.impl.ViewValidationRuleImpl;

/**
 * Implementation of ViewValidationRule.
 * 
 * @author cbrun
 */
public class ViewValidationRuleSpec extends ViewValidationRuleImpl {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl#checkRule(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean checkRule(final EObject obj) {
        return ValidationRuleSpecOperations.checkRule(this, obj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl#getMessage(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public String getMessage(final EObject obj) {
        return ValidationRuleSpecOperations.getMessage(this, obj);
    }

}
