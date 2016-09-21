/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.filters.style.nodestyledescription;

import org.eclipse.emf.ecore.EObject;

// Start of user code specific imports

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;

// End of user code specific imports

/**
 * A filter for the forbiddenSides property section.
 */
public class NodeStyleDescriptionForbiddenSidesFilter extends ViewpointPropertyFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected EStructuralFeature getFeature() {
        return StylePackage.eINSTANCE.getNodeStyleDescription_ForbiddenSides();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
    }

    // Start of user code user methods

    @Override
    public boolean select(Object object) {
        if (super.select(object)) {
            EObject nodeMapping = ((NodeStyleDescription) object).eContainer();
            if (nodeMapping instanceof ConditionalNodeStyleDescription) {
                nodeMapping = nodeMapping.eContainer();
            }
            // We display this property section only for nodeStyleDescription
            // contained in a borderNodeMapping (a NodeMapping referenced by the
            // parent borderNodeMapping feature)
            return DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings().equals(nodeMapping.eContainingFeature());
        }
        return false;
    }
    // End of user code user methods

}
