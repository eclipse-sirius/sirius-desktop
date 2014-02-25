/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.filters.style.nodestyledescription;

// Start of user code specific imports

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;

// End of user code specific imports

/**
 * A filter for the labelPosition property section.
 */
public class NodeStyleDescriptionLabelPositionFilter extends ViewpointPropertyFilter {

    /**
     * {@inheritDoc}
     */
    protected EStructuralFeature getFeature() {
        return StylePackage.eINSTANCE.getNodeStyleDescription_LabelPosition();
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
    }

    // Start of user code user methods

    /**
     * {@inheritDoc}
     */
    public boolean select(Object arg0) {
        if (isRightInputType(arg0) && isStyleInNodeMapping((org.eclipse.sirius.diagram.description.style.NodeStyleDescription) arg0)) {
            EStructuralFeature feature = getFeature();
            if (feature != null && isVisible(feature)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a style is contained in a node mapping.
     * 
     * @param styleDescription
     *            Node Style description
     * @return <code>true</code> if the style is contained in a node mapping,
     *         <code>false</code> otherwise
     */
    private boolean isStyleInNodeMapping(org.eclipse.sirius.diagram.description.style.NodeStyleDescription styleDescription) {
        EObject container = styleDescription.eContainer();
        while (container instanceof ConditionalStyleDescription) {
            container = container.eContainer();
        }
        return container instanceof NodeMapping;
    }

    // End of user code user methods

}
