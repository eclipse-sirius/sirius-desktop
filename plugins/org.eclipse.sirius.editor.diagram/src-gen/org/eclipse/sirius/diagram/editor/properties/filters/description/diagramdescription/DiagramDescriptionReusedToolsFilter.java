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
 * A filter for the reusedTools property section.
 */
public class DiagramDescriptionReusedToolsFilter extends ViewpointPropertyFilter {

    /**
     * {@inheritDoc}
     */
    protected EStructuralFeature getFeature() {
        return DescriptionPackage.eINSTANCE.getDiagramDescription_ReusedTools();
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

        // Display reused tools property section only if there are some reused
        // tools, this is done for compatibility reasons with old VSMs.
        // The possibility to add ToolSection as direct children of a
        // DiagramDescription has been disabled.
        // Specifier can reuse tools on layers.
        // This section will be completely hidden in a future version.
        if (select && arg0 instanceof org.eclipse.sirius.diagram.description.DiagramDescription) {
            DiagramDescription desc = (DiagramDescription) arg0;
            select = !desc.getReusedTools().isEmpty();
        }

        return select;
    }

    // End of user code user methods

}
