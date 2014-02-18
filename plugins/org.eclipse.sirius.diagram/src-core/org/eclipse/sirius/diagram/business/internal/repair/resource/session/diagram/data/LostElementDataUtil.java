/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;

/**
 * Utils for lost data elements.
 * 
 * @author dlecan
 */
public final class LostElementDataUtil {

    private LostElementDataUtil() {
        // Nothing
    }

    /**
     * Find designer diagram element.
     * <p>
     * This method delegates massively to
     * {@link ISimilarLostElementData#isSimilarTo(DDiagramElement)} to check if
     * elements are similar. Previous method will check recursively if
     * necessary.
     * </p>
     * 
     * @param designerDiagram
     *            Diagram where to find.
     * @param data
     *            Data to use to find diagram element.
     * @return Diagram element found.
     */
    public static DDiagramElement findDesignerDiagramElement(final DDiagram designerDiagram, final ISimilarLostElementData data) {
        DDiagramElement result = null;
        for (final DDiagramElement diagramElement : new DDiagramQuery(designerDiagram).getAllDiagramElements()) {
            if (data.isSimilarTo(diagramElement)) {
                result = diagramElement;
                break;
            }
        }
        return result;
    }
}
