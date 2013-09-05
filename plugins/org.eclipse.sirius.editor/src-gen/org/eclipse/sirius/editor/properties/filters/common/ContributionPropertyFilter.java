/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.filters.common;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IFilter;

// Start of user code imports

// End of user code imports

public abstract class ContributionPropertyFilter implements IFilter {

    public boolean select(Object arg0) {
        if (isRightInputType(arg0)) {
            EStructuralFeature feature = getFeature();
            if (feature != null && isVisible(feature)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isVisible(EStructuralFeature feature) {
        // Start of user code common filter isVisible
        return true;
        // End of user code common filter isVisible
    }

    abstract protected EStructuralFeature getFeature();

    abstract protected boolean isRightInputType(Object arg0);

    // Start of user code user methods

    // End of user code user methods

}
