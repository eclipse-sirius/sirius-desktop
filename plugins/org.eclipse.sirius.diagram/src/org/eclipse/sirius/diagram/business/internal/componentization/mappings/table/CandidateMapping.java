/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.componentization.mappings.table;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;

/**
 * A candidate mapping.
 * 
 * @author mchauvin
 */
public class CandidateMapping {

    private DiagramElementMapping mapping;

    private Layer parentLayer;

    private Collection<Layer> parentLayers;

    /**
     * Construct a new candidate mapping.
     * 
     * @param mapping
     *            the mapping to wrap
     */
    public CandidateMapping(final DiagramElementMapping mapping) {
        this.mapping = mapping;
        this.parentLayer = computeParentLayer();
        this.parentLayers = computeParentLayers();
    }

    /**
     * compute all Layers which use this mapping if available.
     * 
     * @return a containing Layer if available.
     */
    private Collection<Layer> computeParentLayers() {
        return LayerHelper.getParentLayers(mapping);
    }

    private Layer computeParentLayer() {
        EObject current = mapping;
        while (current != null) {
            current = current.eContainer();
            if (current instanceof Layer) {
                return (Layer) current;
            }
        }
        return null;
    }

    /**
     * Get the direct parent layer.
     * 
     * @return the parentLayer
     */
    public Layer getParentLayer() {
        return parentLayer;
    }

    /**
     * Get the indirect parent layers.
     * 
     * @return the parentLayers
     */
    public Collection<Layer> getParentLayers() {
        return parentLayers;
    }

    /**
     * Get the wrapped mapping.
     * 
     * @return the wrapped mapping
     */
    public DiagramElementMapping getMapping() {
        return mapping;
    }

}
