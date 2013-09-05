/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;

import org.eclipse.sirius.description.EReferenceCustomization;

/**
 * A
 * {@link AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint}
 * to check the constraint that
 * {@link EReferenceCustomization#getReferenceName()} is the name of a common
 * {@link EReference} to all referenced style description or a description
 * element owned by a style description through
 * {@link EReferenceCustomization#getAppliedOn()} or different
 * {@link EReference} with same name and having a common type. Check also that
 * the {@link EReferenceCustomization#getValue()} type is conforming to the type
 * of the common {@link EReference} or the common type of different
 * {@link EReference} available.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EReferenceCustomizationReferenceNameCommonToAppliedOnConstraint extends AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint {

    private static final String EREFERENCE_NAME_ON = " EReference name on ";

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
                status = ctx.createFailureStatus(getPath(firstStyleDescriptionElt) + " doesn't concerns a style description or a style description element");
            } else {
                EStructuralFeature eStructuralFeature = firstStyleDescriptionElt.eClass().getEStructuralFeature(referenceName);
                if (eStructuralFeature instanceof EReference) {
                    EClassifier firstEType = eStructuralFeature.getEType();
                    status = validateFollowingStyleDescriptionElts(appliedOnIterator, firstStyleDescriptionElt, firstEType, referenceName, ctx);
                } else if (eStructuralFeature == null) {
                    status = ctx.createFailureStatus(referenceName + EREFERENCE_NAME_ON + getPath(firstStyleDescriptionElt) + " doesn't exists");
                } else {
                    status = ctx.createFailureStatus(referenceName + EREFERENCE_NAME_ON + getPath(firstStyleDescriptionElt) + " concerns " + eStructuralFeature + " which is not a EReference");
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
                status = ctx.createFailureStatus(getPath(next) + " doesn't concerns a style description or a style description element");
                break;
            } else {
                EStructuralFeature eStructuralFeature = next.eClass().getEStructuralFeature(referenceName);
                if (eStructuralFeature instanceof EReference) {
                    if (firstEType != eStructuralFeature.getEType()) {
                        status = ctx.createFailureStatus(getPath(firstStyleDescriptionElt) + " and " + getPath(next) + "have each a EAttribute named " + referenceName + " but with differents types");
                        break;
                    }
                } else if (eStructuralFeature == null) {
                    status = ctx.createFailureStatus(referenceName + EREFERENCE_NAME_ON + getPath(next) + " doesn't exists");
                } else {
                    status = ctx.createFailureStatus(referenceName + EREFERENCE_NAME_ON + getPath(next) + " concerns " + eStructuralFeature + " which is not a EAttribute");
                    break;
                }
            }
        }
        return status;
    }
}
