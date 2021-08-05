/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;

/**
 * A {@link AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint} to check the constraint that
 * {@link EAttributeCustomization#getAttributeName()} is the name of a common {@link EAttribute} to all referenced style
 * description or a description element owned by a style description through
 * {@link EAttributeCustomization#getAppliedOn()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint extends AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus status = null;
        EObject target = ctx.getTarget();
        if (target instanceof EAttributeCustomization) {
            EAttributeCustomization eAttributeCustomization = (EAttributeCustomization) target;
            status = validateEAttributeCustomization(eAttributeCustomization, ctx);
        }
        if (status == null) {
            status = ctx.createSuccessStatus();
        }
        return status;
    }

    private IStatus validateEAttributeCustomization(EAttributeCustomization eAttributeCustomization, IValidationContext ctx) {
        IStatus status = null;
        String attributeName = eAttributeCustomization.getAttributeName();
        Iterator<EObject> appliedOnIterator = eAttributeCustomization.getAppliedOn().iterator();
        if (appliedOnIterator.hasNext()) {
            EObject firstStyleDescriptionElt = appliedOnIterator.next();
            if (!isStyleDescriptionElt(firstStyleDescriptionElt)) {
                status = ctx.createFailureStatus(
                        MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_validationStyleDescriptionErrorMsg, getPath(firstStyleDescriptionElt)));
            } else {
                EStructuralFeature eStructuralFeature = firstStyleDescriptionElt.eClass().getEStructuralFeature(attributeName);
                if (eStructuralFeature instanceof EAttribute) {
                    EClassifier firstEType = eStructuralFeature.getEType();
                    status = validateFollowingStyleDescriptionElts(appliedOnIterator, firstStyleDescriptionElt, firstEType, attributeName, ctx);
                } else if (eStructuralFeature == null) {
                    status = ctx.createFailureStatus(MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_validationNotExistErrorMsg, attributeName,
                            getPath(firstStyleDescriptionElt)));
                } else {
                    status = ctx.createFailureStatus(MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_notEAttributeErrorMsg, attributeName,
                            getPath(firstStyleDescriptionElt), eStructuralFeature));
                }
            }
        }
        return status;
    }

    private IStatus validateFollowingStyleDescriptionElts(Iterator<EObject> appliedOnIterator, EObject firstStyleDescriptionElt, EClassifier firstEType, String attributeName, IValidationContext ctx) {
        IStatus status = null;
        while (appliedOnIterator.hasNext()) {
            EObject next = appliedOnIterator.next();
            if (!isStyleDescriptionElt(next)) {
                status = ctx.createFailureStatus(MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_validationStyleDescriptionErrorMsg, getPath(next)));
                break;
            } else {
                EStructuralFeature eStructuralFeature = next.eClass().getEStructuralFeature(attributeName);
                if (eStructuralFeature instanceof EAttribute) {
                    if (firstEType != eStructuralFeature.getEType()) {
                        status = ctx.createFailureStatus(MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_EAttributeDiffernentTypesErrorMsg,
                                getPath(firstStyleDescriptionElt), getPath(next), attributeName));
                        break;
                    }
                } else if (eStructuralFeature == null) {
                    status = ctx.createFailureStatus(
                            MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_validationNotExistErrorMsg, attributeName, getPath(next)));
                } else {
                    status = ctx.createFailureStatus(
                            MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_notEAttributeErrorMsg, attributeName, getPath(next), eStructuralFeature));
                    break;
                }
            }
        }
        return status;
    }

}
