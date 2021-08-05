/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;

/**
 * Helpers to navigate the Sirius Diagrams models.
 * 
 * @author cbrun
 */
public final class ContentLayerHelper {

    /**
     * Avoid Instantiation.
     */
    private ContentLayerHelper() {

    }

    /**
     * Get all edge mappings (including wrap EdgeMappingImport) used in a designer diagram description on one particular
     * layer.
     * 
     * @param layer
     *            the layer of a diagram description of a designer diagram
     * @return all the edge mappings used
     */
    public static EList<EdgeMapping> getAllEdgeMappings(final Layer layer) {
        final Collection<EdgeMapping> result = new ArrayList<EdgeMapping>();

        result.addAll(layer.getEdgeMappings());
        // Add the wrapper of EdgeMappingImport
        final Iterator<EdgeMappingImport> iterMappingImport = layer.getEdgeMappingImports().iterator();
        while (iterMappingImport.hasNext()) {
            result.add(EdgeMappingImportWrapper.getWrapper(iterMappingImport.next()));
        }
        // Add the EdgeMapping of the reused mapping
        final Iterator<DiagramElementMapping> it = ContentLayerHelper.getReuseMappings(layer).iterator();
        while (it.hasNext()) {
            final DiagramElementMapping eObj = it.next();
            if (eObj instanceof EdgeMapping) {
                result.add((EdgeMapping) eObj);
            }
        }
        return new BasicEList<>(result);
    }

    /**
     * Return the list of reuse mappings for the layer removing any import of the layers mappings itself (which might be
     * considered as a "useless import").
     * 
     * @param layer
     *            the layer.
     * @return the collection of reused mappings.
     */
    public static Collection<DiagramElementMapping> getReuseMappings(final Layer layer) {
        final Set<DiagramElementMapping> mappings = new LinkedHashSet<DiagramElementMapping>(layer.getReusedMappings().size());
        mappings.addAll(layer.getReusedMappings());
        mappings.removeAll(layer.getContainerMappings());
        mappings.removeAll(layer.getNodeMappings());
        mappings.removeAll(layer.getEdgeMappings());
        // Remove the wrapper of EdgeMappingImport
        final Iterator<EdgeMappingImport> iterMappingImport = layer.getEdgeMappingImports().iterator();
        while (iterMappingImport.hasNext()) {
            mappings.remove(EdgeMappingImportWrapper.getWrapper(iterMappingImport.next()));
        }
        return mappings;
    }

}
