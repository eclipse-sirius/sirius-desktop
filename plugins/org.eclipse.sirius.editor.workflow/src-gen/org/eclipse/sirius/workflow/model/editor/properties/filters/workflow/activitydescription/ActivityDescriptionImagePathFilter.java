/*******************************************************************************
 *  Copyright (c) 2018 Obeo.
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *  Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.workflow.model.editor.properties.filters.workflow.activitydescription;

// Start of user code specific imports

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;
import org.eclipse.sirius.workflow.WorkflowPackage;

// End of user code specific imports

/**
 * A filter for the imagePath property section.
 */
public class ActivityDescriptionImagePathFilter extends ViewpointPropertyFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected EStructuralFeature getFeature() {
        return WorkflowPackage.eINSTANCE.getActivityDescription_ImagePath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.workflow.ActivityDescription;
    }

    // Start of user code user methods

    // End of user code user methods

}
