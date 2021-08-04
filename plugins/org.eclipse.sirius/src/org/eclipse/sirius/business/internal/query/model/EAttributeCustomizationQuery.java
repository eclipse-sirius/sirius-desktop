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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;

/**
 * A query for {@link EAttributeCustomization}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EAttributeCustomizationQuery {

    /** The concern {@link EAttributeCustomization}. */
    protected EAttributeCustomization eAttributeCustomization;

    /**
     * Default constructor.
     * 
     * @param eAttributeCustomization
     *            the {@link EAttributeCustomization} to query
     */
    public EAttributeCustomizationQuery(EAttributeCustomization eAttributeCustomization) {
        this.eAttributeCustomization = eAttributeCustomization;
    }

    /**
     * Tells if the current {@link EAttributeCustomization} is conforms to the specified style description element.
     * 
     * @param eObject
     *            the specified style description element
     * @return true if the current {@link EAttributeCustomization} is conforms, false else
     */
    public boolean isStyleDescriptionEltConformToEAttributeCustomization(EObject eObject) {
        boolean isStyleDescriptionEltConformToEAttributeCustomization = false;
        String attributeName = eAttributeCustomization.getAttributeName();
        if (attributeName != null && attributeName.length() > 0) {
            isStyleDescriptionEltConformToEAttributeCustomization = eObject.eClass().getEStructuralFeature(attributeName) instanceof EAttribute;
        } else {
            isStyleDescriptionEltConformToEAttributeCustomization = true;
        }
        return isStyleDescriptionEltConformToEAttributeCustomization;
    }
}
