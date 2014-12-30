/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.filters.description.diagramdescription;

// Start of user code specific imports

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;

// End of user code specific imports

/**
 * A filter for the reusedMappings property section.
 */
public class DiagramDescriptionReusedMappingsFilter extends ViewpointPropertyFilter {

    /**
     * {@inheritDoc}
     */
    protected EStructuralFeature getFeature() {
        return DescriptionPackage.eINSTANCE.getDiagramDescription_ReusedMappings();
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.diagram.description.DiagramDescription;
    }

    // Start of user code user methods

    @Override
    public boolean select(Object arg0) {
        boolean select = super.select(arg0);

        // Display reused mappings property section only if there are some
        // reused
        // mappings, this is done for compatibility reasons with old VSMs.
        // The possibility to add mappings as direct children of a
        // DiagramDescription has been disabled
        // Specifier can reuse mappings on layers.
        // This section will be completely hidden in a future version.
        if (select && arg0 instanceof org.eclipse.sirius.diagram.description.DiagramDescription) {
            DiagramDescription desc = (DiagramDescription) arg0;
            select = !desc.getReusedMappings().isEmpty();
        }

        return select;
    }

    // End of user code user methods

}
