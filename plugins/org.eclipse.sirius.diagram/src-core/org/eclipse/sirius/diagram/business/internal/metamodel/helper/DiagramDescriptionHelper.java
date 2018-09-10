/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Helper class to factor specialized behavior for diagram descriptions.
 * 
 * @author pcdavid
 */
public final class DiagramDescriptionHelper {
    private DiagramDescriptionHelper() {
        // Prevent instantiation.
    }

    /**
     * Implements the {@link DiagramDescription#getAllActivatedTools()} method.
     * 
     * @param self
     *            the diagram description.
     * @return all the tools defined in this
     */
    public static Set<AbstractToolDescription> getAllTools(final DiagramDescription self) {
        /*
         * TODOCBR change that, we need to get: All the defined tool in this viewpoint + all the direct edit tools from
         * the mappings + all the delete tools from the mappings
         */
        final Set<AbstractToolDescription> result = new LinkedHashSet<AbstractToolDescription>();

        // Owned tools
        if (self.getToolSection() != null) {
            final Iterator<EObject> it = self.getToolSection().eAllContents();
            while (it.hasNext()) {
                final EObject eObj = it.next();
                if (eObj instanceof AbstractToolDescription) {
                    result.add((AbstractToolDescription) eObj);
                }
            }
        }

        // Layers tools
        final Iterator<Layer> it = LayerHelper.getAllLayers(self).iterator();
        while (it.hasNext()) {
            final Layer layer = it.next();
            if (layer != null) {
                result.addAll(layer.getAllTools());
            }
        }

        result.addAll(self.getReusedTools());
        return result;
    }
}
