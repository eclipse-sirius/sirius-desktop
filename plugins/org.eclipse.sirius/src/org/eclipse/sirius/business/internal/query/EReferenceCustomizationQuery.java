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
package org.eclipse.sirius.business.internal.query;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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
     * Tells if the current {@link EReferenceCustomization} is conforms to the
     * specified style description element.
     * 
     * @param eObject
     *            the specified style description element
     * @return true if the current {@link EReferenceCustomization} is conforms,
     *         false else
     */
    public boolean isStyleDescriptionEltConformToEReferenceCustomization(EObject eObject) {
        boolean isStyleDescriptionEltConformToEReferenceCustomization = false;
        String referenceName = eReferenceCustomization.getReferenceName();
        EObject value = eReferenceCustomization.getValue();
        if (referenceName != null && referenceName.length() > 0) {
            isStyleDescriptionEltConformToEReferenceCustomization = eObject.eClass().getEStructuralFeature(referenceName) instanceof EReference;
            if (isStyleDescriptionEltConformToEReferenceCustomization && value != null) {
                EClassifier eType = eObject.eClass().getEStructuralFeature(referenceName).getEType();
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
