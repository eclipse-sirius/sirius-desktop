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
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * A abstract {@link AbstractModelConstraint} common to constraint on
 * EStructuralFeatureCustomization.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public abstract class AbstractEStructuralFeatureCustomizationFeatureNameCommonToAppliedOnConstraint extends AbstractCommonToolToAppliedOnConstraint {

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
}
