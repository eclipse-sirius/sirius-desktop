/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.filters.contribution.directeobjectreference;

// Start of user code specific imports

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.editor.properties.filters.common.ContributionPropertyFilter;

// End of user code specific imports

/**
 * A filter for the value property section.
 */
public class DirectEObjectReferenceValueFilter extends ContributionPropertyFilter {

    /**
     * {@inheritDoc}
     */
    protected EStructuralFeature getFeature() {
        return ContributionPackage.eINSTANCE.getDirectEObjectReference_Value();
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.description.contribution.DirectEObjectReference;
    }

    // Start of user code user methods

    // End of user code user methods

}
