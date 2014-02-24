/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.componentization.mappings;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManagerListener;
import org.eclipse.sirius.diagram.business.api.helper.layers.LayerService;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Mappings available for a given diagram description.
 * 
 * @author mchauvin
 */
public class DiagramDescriptionMappingsManagerImpl implements DiagramDescriptionMappingsManager {

    private final DiagramDescription description;

    private EList<NodeMapping> nodeMappings;

    private EList<ContainerMapping> containerMappings;

    private EList<EdgeMapping> edgeMappings;

    private final List<DiagramDescriptionMappingsManagerListener> listeners;

    /**
     * Create a new instance.
     * 
     * @param description
     *            the diagram description
     */
    public DiagramDescriptionMappingsManagerImpl(final DiagramDescription description) {
        this.description = description;
        this.listeners = new ArrayList<DiagramDescriptionMappingsManagerListener>(2);
    }

    /**
     * {@inheritDoc}
     */
    public void computeMappings(Collection<Viewpoint> enabledViewpoints) {
        nodeMappings = new DiagramComponentizationManager().getAllNodeMappings(enabledViewpoints, this.description);
        edgeMappings = new DiagramComponentizationManager().getAllEdgeMappings(enabledViewpoints, this.description);
        containerMappings = new DiagramComponentizationManager().getAllContainerMappings(enabledViewpoints, this.description);

        for (final DiagramDescriptionMappingsManagerListener listener : listeners) {
            listener.mappingsComputed(enabledViewpoints);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#getNodeMappings()
     */
    public List<NodeMapping> getNodeMappings() {
        return nodeMappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#getContainerMappings()
     */
    public List<ContainerMapping> getContainerMappings() {
        return containerMappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#getEdgeMappings()
     */
    public List<EdgeMapping> getEdgeMappings() {
        return edgeMappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#getContainerMappings(org.eclipse.sirius.viewpoint.description.ContainerMapping)
     */
    public List<ContainerMapping> getContainerMappings(final ContainerMapping containerMapping) {
        List<ContainerMapping> result = Collections.emptyList();
        if (containerMapping != null) {
            result = containerMapping.getAllContainerMappings();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#getNodeMappings(org.eclipse.sirius.viewpoint.description.ContainerMapping)
     */
    public List<NodeMapping> getNodeMappings(final ContainerMapping containerMapping) {
        List<NodeMapping> result = Collections.emptyList();
        if (containerMapping != null) {
            result = containerMapping.getAllNodeMappings();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#getBorderedNodeMappings(org.eclipse.sirius.viewpoint.description.AbstractNodeMapping)
     */
    public List<NodeMapping> getBorderedNodeMappings(final AbstractNodeMapping mapping) {
        List<NodeMapping> result = Collections.emptyList();
        if (mapping != null) {
            result = mapping.getAllBorderedNodeMappings();
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#addListener(org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManagerListener)
     */
    public void addListener(final DiagramDescriptionMappingsManagerListener listener) {
        this.listeners.add(listener);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#removeListener(org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManagerListener)
     */
    public void removeListener(final DiagramDescriptionMappingsManagerListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#isLayerMode()
     */
    public boolean isLayerMode() {
        return !LayerService.withoutLayersMode(description);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager#dispose()
     */
    public void dispose() {
        for (final DiagramDescriptionMappingsManagerListener listener : listeners) {
            listener.dispose();
        }
        listeners.clear();
    }

}
