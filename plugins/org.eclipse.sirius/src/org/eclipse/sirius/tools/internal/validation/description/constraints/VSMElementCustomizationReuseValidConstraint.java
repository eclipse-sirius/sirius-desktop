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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.model.business.internal.query.EAttributeCustomizationQuery;
import org.eclipse.sirius.model.business.internal.query.EReferenceCustomizationQuery;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;

/**
 * A {@link AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint} to check validity of
 * {@link VSMElementCustomizationReuse}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class VSMElementCustomizationReuseValidConstraint extends AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus status = null;
        EObject target = ctx.getTarget();
        if (target instanceof VSMElementCustomizationReuse) {
            VSMElementCustomizationReuse vsmElementCustomizationReuse = (VSMElementCustomizationReuse) target;
            for (EObject eObject : vsmElementCustomizationReuse.getAppliedOn()) {
                if (!isStyleDescriptionElt(eObject)) {
                    status = ctx.createFailureStatus(MessageFormat.format(Messages.VSMElementCustomizationReuseValidConstraint_doesntConcernsStyleDescErrorMsg, getPath(eObject)));
                } else {
                    for (EStructuralFeatureCustomization featureCustomization : vsmElementCustomizationReuse.getReuse()) {
                        if (featureCustomization instanceof EAttributeCustomization) {
                            EAttributeCustomization eAttributeCustomization = (EAttributeCustomization) featureCustomization;
                            EAttributeCustomizationQuery eAttributeCustomizationQuery = new EAttributeCustomizationQuery(eAttributeCustomization);
                            if (!eAttributeCustomizationQuery.isStyleDescriptionEltConformToEAttributeCustomization(eObject)) {
                                status = ctx.createFailureStatus(
                                        MessageFormat.format(Messages.VSMElementCustomizationReuseValidConstraint_noEAttributeErrorMsg, getPath(eObject), eAttributeCustomization.getAttributeName()));
                            }
                        } else if (featureCustomization instanceof EReferenceCustomization) {
                            EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) featureCustomization;
                            EReferenceCustomizationQuery eReferenceCustomizationQuery = new EReferenceCustomizationQuery(eReferenceCustomization);
                            if (!eReferenceCustomizationQuery.isStyleDescriptionEltConformToEReferenceCustomization(eObject)) {
                                status = ctx.createFailureStatus(
                                        MessageFormat.format(Messages.VSMElementCustomizationReuseValidConstraint_noEReferenceErrorMsg, getPath(eObject), eReferenceCustomization.getReferenceName()));
                            }
                        }
                    }
                }
            }
        }
        if (status == null) {
            status = ctx.createSuccessStatus();
        }
        return status;
    }

}
