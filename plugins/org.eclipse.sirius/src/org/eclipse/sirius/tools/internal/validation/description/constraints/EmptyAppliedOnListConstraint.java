/*****************************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES
 * All rights reserved.
 *
 * Contributors:
 *      Obeo - Initial API and implementation
 *****************************************************************************************/
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
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

    /**
     * Error message for this constraint.
     */
    private static final String ERROR_MESSAGE = "This customization has no style to customize.";

    @Override
    public IStatus validate(IValidationContext ctx) {

        IStatus status = null;
        EObject target = ctx.getTarget();
        if (target instanceof EStructuralFeatureCustomization) {
            EStructuralFeatureCustomization eStructuralFeatureCustomization = (EStructuralFeatureCustomization) target;

            if (!eStructuralFeatureCustomization.isApplyOnAll()) {
                if (eStructuralFeatureCustomization.getAppliedOn().isEmpty()) {
                    status = ctx.createFailureStatus(ERROR_MESSAGE);
                }
            }
        }
        if (status == null) {
            status = ctx.createSuccessStatus();
        }
        return status;
    }
}