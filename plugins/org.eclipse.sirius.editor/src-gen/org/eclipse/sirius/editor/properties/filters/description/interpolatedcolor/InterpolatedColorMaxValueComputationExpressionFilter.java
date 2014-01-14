/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.filters.description.interpolatedcolor;

// Start of user code specific imports

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;

// End of user code specific imports

/**
 * A filter for the maxValueComputationExpression property section.
 */
public class InterpolatedColorMaxValueComputationExpressionFilter extends ViewpointPropertyFilter {

    /**
     * {@inheritDoc}
     */
    protected EStructuralFeature getFeature() {
        return DescriptionPackage.eINSTANCE.getInterpolatedColor_MaxValueComputationExpression();
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.viewpoint.description.InterpolatedColor;
    }

    // Start of user code user methods

    private InterpolatedColor InterpolatedColor;

    @Override
    public boolean select(Object arg0) {
        if (arg0 instanceof InterpolatedColor) {
            InterpolatedColor = (InterpolatedColor) arg0;
        }
        return super.select(arg0);
    }

    @Override
    protected boolean isVisible(EStructuralFeature feature) {
        if (InterpolatedColor != null && !InterpolatedColor.getColorSteps().isEmpty()) {
            // If the InterpolatedColor has children, the color will be
            // defined by the children. Therefore, this property will have no
            // use and is hidden.
            return false;
        }
        return super.isVisible(feature);
    }

    // End of user code user methods

}
