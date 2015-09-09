/*****************************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *****************************************************************************************/
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;

/**
 * Validation constraint to check that the
 * {@link EStructuralFeatureCustomization#getAppliedOn()} is not empty when the
 * {@link EStructuralFeatureCustomization#isApplyOnAll()} is false.
 * 
 * @author lredor
 * 
 */
public class EmptyAppliedOnListConstraint extends AbstractModelConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {

        IStatus status = null;
        EObject target = ctx.getTarget();
        if (target instanceof EStructuralFeatureCustomization) {
            EStructuralFeatureCustomization eStructuralFeatureCustomization = (EStructuralFeatureCustomization) target;

            if (!eStructuralFeatureCustomization.isApplyOnAll()) {
                if (eStructuralFeatureCustomization.getAppliedOn().isEmpty()) {
                    status = ctx.createFailureStatus(Messages.EmptyAppliedOnListConstraint_errorMsg);
                }
            }
        }
        if (status == null) {
            status = ctx.createSuccessStatus();
        }
        return status;
    }
}
