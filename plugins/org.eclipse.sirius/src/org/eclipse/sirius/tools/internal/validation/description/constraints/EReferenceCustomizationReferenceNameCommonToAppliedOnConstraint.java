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
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;

/**
 * A {@link AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint} to check the constraint that
 * {@link EReferenceCustomization#getReferenceName()} is the name of a common {@link EReference} to all referenced style
 * description or a description element owned by a style description through
 * {@link EReferenceCustomization#getAppliedOn()} or different {@link EReference} with same name and having a common
 * type. Check also that the {@link EReferenceCustomization#getValue()} type is conforming to the type of the common
 * {@link EReference} or the common type of different {@link EReference} available.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EReferenceCustomizationReferenceNameCommonToAppliedOnConstraint extends AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus status = null;
        EObject target = ctx.getTarget();
        if (target instanceof EReferenceCustomization) {
            EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) target;
            status = validateEReferenceCustomization(eReferenceCustomization, ctx);
        }
        if (status == null) {
            status = ctx.createSuccessStatus();
        }
        return status;
    }

    private IStatus validateEReferenceCustomization(EReferenceCustomization eReferenceCustomization, IValidationContext ctx) {
        IStatus status = null;
        String referenceName = eReferenceCustomization.getReferenceName();
        Iterator<EObject> appliedOnIterator = eReferenceCustomization.getAppliedOn().iterator();
        if (appliedOnIterator.hasNext()) {
            EObject firstStyleDescriptionElt = appliedOnIterator.next();
            if (!isStyleDescriptionElt(firstStyleDescriptionElt)) {
                status = ctx.createFailureStatus(
                        MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_validationStyleDescriptionErrorMsg, getPath(firstStyleDescriptionElt)));
            } else {
                EStructuralFeature eStructuralFeature = firstStyleDescriptionElt.eClass().getEStructuralFeature(referenceName);
                if (eStructuralFeature instanceof EReference) {
                    EClassifier firstEType = eStructuralFeature.getEType();
                    status = validateFollowingStyleDescriptionElts(appliedOnIterator, firstStyleDescriptionElt, firstEType, referenceName, ctx);
                } else if (eStructuralFeature == null) {
                    status = ctx.createFailureStatus(MessageFormat.format(Messages.EReferenceCustomizationReferenceNameCommonToAppliedOnConstraint_validationNotExistErrorMsg, referenceName,
                            getPath(firstStyleDescriptionElt)));
                } else {
                    status = ctx.createFailureStatus(MessageFormat.format(Messages.EReferenceCustomizationReferenceNameCommonToAppliedOnConstraint_notAReferenceErrorMsg, referenceName,
                            getPath(firstStyleDescriptionElt), eStructuralFeature));
                }
            }
        }
        return status;
    }

    private IStatus validateFollowingStyleDescriptionElts(Iterator<EObject> appliedOnIterator, EObject firstStyleDescriptionElt, EClassifier firstEType, String referenceName, IValidationContext ctx) {
        IStatus status = null;
        while (appliedOnIterator.hasNext()) {
            EObject next = appliedOnIterator.next();
            if (!isStyleDescriptionElt(next)) {
                status = ctx.createFailureStatus(MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_validationStyleDescriptionErrorMsg, getPath(next)));
                break;
            } else {
                EStructuralFeature eStructuralFeature = next.eClass().getEStructuralFeature(referenceName);
                if (eStructuralFeature instanceof EReference) {
                    if (firstEType != eStructuralFeature.getEType()) {
                        status = ctx.createFailureStatus(MessageFormat.format(Messages.EAttributeCustomizationAttributeNameCommonToAppliedOnConstraint_EAttributeDiffernentTypesErrorMsg,
                                getPath(firstStyleDescriptionElt), getPath(next), referenceName));
                        break;
                    }
                } else if (eStructuralFeature == null) {
                    status = ctx.createFailureStatus(
                            MessageFormat.format(Messages.EReferenceCustomizationReferenceNameCommonToAppliedOnConstraint_validationNotExistErrorMsg, referenceName, getPath(next)));
                } else {
                    status = ctx.createFailureStatus(
                            MessageFormat.format(Messages.EReferenceCustomizationReferenceNameCommonToAppliedOnConstraint_notAReferenceErrorMsg, referenceName, getPath(next), eStructuralFeature));
                    break;
                }
            }
        }
        return status;
    }
}
