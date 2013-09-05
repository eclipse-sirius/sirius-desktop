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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;

import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.description.IdentifiedElement;
import org.eclipse.sirius.description.RepresentationExtensionDescription;
import org.eclipse.sirius.description.style.StyleDescription;

/**
 * A abstract {@link AbstractModelConstraint} common to constraint on
 * EStructuralFeatureCustomization.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public abstract class AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint extends AbstractModelConstraint {

    /**
     * Tells if the specified {@link EObject} is a style description or a
     * element of style description.
     * 
     * @param eObject
     *            the specified {@link EObject}
     * @return true if the specified {@link EObject} is a style description or a
     *         element of style description, false else
     */
    protected boolean isStyleDescriptionElt(EObject eObject) {
        boolean isStyleDescriptionElt = eObject instanceof StyleDescription || eObject.eContainer() instanceof StyleDescription;
        return isStyleDescriptionElt;
    }

    /**
     * Get a readable text representation of a vsm element.
     * 
     * @param element
     *            the vsm element
     * @return a readable text representation of a vsm element
     */
    protected String getPath(EObject element) {
        String text = getLabel(element);
        EObject container = element.eContainer();
        while (container != null) {
            text = getLabel(container) + " > " + text;
            container = container.eContainer();
        }
        return "\"" + text + "\"";
    }

    private String getLabel(final EObject eObject) {
        String label = "Element whithout name";
        if (eObject instanceof IdentifiedElement) {
            label = new IdentifiedElementQuery((IdentifiedElement) eObject).getLabel();
        } else if (eObject instanceof RepresentationExtensionDescription) {
            label = ((RepresentationExtensionDescription) eObject).getName();
        } else {
            label = eObject.eClass().getName();
        }
        return label;
    }
}
