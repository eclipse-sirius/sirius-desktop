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
package org.eclipse.sirius.business.internal.query.model;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;

/**
 * A query on a {@link EReferenceCustomization}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EReferenceCustomizationQuery {

    private EReferenceCustomization eReferenceCustomization;

    /**
     * Default constructor.
     * 
     * @param eReferenceCustomization
     *            the {@link EReferenceCustomization} to query
     */
    public EReferenceCustomizationQuery(EReferenceCustomization eReferenceCustomization) {
        this.eReferenceCustomization = eReferenceCustomization;
    }

    /**
     * Tells if the current {@link EReferenceCustomization} is conforms to the specified style description element.
     * 
     * @param eObject
     *            the specified style description element
     * @return true if the current {@link EReferenceCustomization} is conforms, false else
     */
    public boolean isStyleDescriptionEltConformToEReferenceCustomization(EObject eObject) {
        boolean isStyleDescriptionEltConformToEReferenceCustomization = false;
        String referenceName = eReferenceCustomization.getReferenceName();
        EObject value = eReferenceCustomization.getValue();
        if (referenceName != null && referenceName.length() > 0) {
            EStructuralFeature eStructuralFeature = eObject.eClass().getEStructuralFeature(referenceName);
            isStyleDescriptionEltConformToEReferenceCustomization = eStructuralFeature instanceof EReference;
            if (isStyleDescriptionEltConformToEReferenceCustomization && value != null) {
                EClassifier eType = eStructuralFeature.getEType();
                if (eType instanceof EClass) {
                    EClass type = (EClass) eType;
                    isStyleDescriptionEltConformToEReferenceCustomization = type.isSuperTypeOf(value.eClass());
                }
            }
        } else {
            isStyleDescriptionEltConformToEReferenceCustomization = true;
        }
        return isStyleDescriptionEltConformToEReferenceCustomization;
    }
}
