/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.filters.style.edgestyledescription;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.ext.base.Option;

/**
 * An helper dedicated to the ends centering property sections.
 * 
 * @author Florian Barbin
 *
 */
public class EdgeStyleEndsCenteringHelper {

    /**
     * Used to filter "egde ends centering" property sections. This feature is
     * only available for EdgeStyleDescription under a strict EdgeMapping type
     * or an EdgeMappingImport with a strict EdgeMapping as original mapping.
     * (this feature is not available in sequence diagram for instance).
     * 
     * @param obj
     *            the current object from the filter (an EdgeStyleDescription
     *            instance).
     * @return true if the property section should be displayed, otherwise
     *         false.
     */
    public static boolean shouldDisplayPropertySection(Object obj) {
        boolean value = false;
        IEdgeMapping container = getEdgeStyleDescMappingContainer(obj);
        if (container != null) {
            IEdgeMappingQuery query = new IEdgeMappingQuery(container);
            Option<EdgeMapping> option = query.getOriginalEdgeMapping();
            if (option.some()) {
                value = (option.get().eClass() == DescriptionPackage.Literals.EDGE_MAPPING);
            }
        }
        return value;
    }

    private static IEdgeMapping getEdgeStyleDescMappingContainer(Object obj) {
        if (obj instanceof EObject) {
            EObject container = ((EObject) obj).eContainer();
            while (container != null) {
                if (container instanceof IEdgeMapping) {
                    return (IEdgeMapping) container;
                }
                container = container.eContainer();
            }
        }
        return null;
    }
}
