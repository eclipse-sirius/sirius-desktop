/*******************************************************************************
 * Copyright (c) 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.editor.properties.filters.template.tbasicmessagemapping;

//Start of user code specific imports

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;

//End of user code specific imports

/**
 * A filter for the oblique property section.
 */
public class TBasicMessageMappingObliqueFilter extends ViewpointPropertyFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected EStructuralFeature getFeature() {
        return TemplatePackage.eINSTANCE.getTBasicMessageMapping_Oblique();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping;
    }

    // Start of user code user methods

    // End of user code user methods

}
