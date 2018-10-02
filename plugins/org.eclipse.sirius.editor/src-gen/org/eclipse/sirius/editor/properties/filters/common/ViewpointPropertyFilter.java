/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.properties.filters.common;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IFilter;

// Start of user code imports

// End of user code imports

public abstract class ViewpointPropertyFilter implements IFilter {

    @Override
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
