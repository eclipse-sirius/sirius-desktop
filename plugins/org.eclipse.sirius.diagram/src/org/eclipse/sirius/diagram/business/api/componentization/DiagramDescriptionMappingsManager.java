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
package org.eclipse.sirius.diagram.business.api.componentization;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Build the table of mappings for one diagram description. The build is launch
 * on all change on ViewpointRegistry. It is independent of the current diagram.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface DiagramDescriptionMappingsManager {

    /**
     * Add a listener.
     * 
     * @param listener
     *            the listener to add
     */
    void addListener(DiagramDescriptionMappingsManagerListener listener);

    /**
     * Remove a listener.
     * 
     * @param listener
     *            the listener to remove
     */
    void removeListener(DiagramDescriptionMappingsManagerListener listener);

    /**
     * Compute available mappings for this diagram description based.
     * 
     * @param viewpoints
     *            collection of Viewpoints
     */
    void computeMappings(Collection<Viewpoint> viewpoints);

    /**
     * Get available node mappings for the diagram description.
     * 
     * @return node mappings
     */
    List<NodeMapping> getNodeMappings();

    /**
     * Get available container mappings for the diagram description.
     * 
     * @return edge mappings
     */
    List<ContainerMapping> getContainerMappings();

    /**
     * Get available container mappings children for the container mapping.
     * 
     * @param containerMapping
     *            the container mapping
     * @return container mappings
     */
    List<ContainerMapping> getContainerMappings(ContainerMapping containerMapping);

    /**
     * Get available node mappings children for the container mapping.
     * 
     * @param containerMapping
     *            the container mapping
     * @return node mappings
     */
    List<NodeMapping> getNodeMappings(ContainerMapping containerMapping);

    /**
     * Get available bordered of the mapping for the mapping.
     * 
     * @param mapping
     *            the node or container mapping
     * @return bordered node mappings
     */
    List<NodeMapping> getBorderedNodeMappings(AbstractNodeMapping mapping);

    /**
     * Get available edge mappings for the diagram description.
     * 
     * @return edge mappings
     */
    List<EdgeMapping> getEdgeMappings();

    /**
     * Check if we are in layer mode or in compatibility mode.
     * 
     * @return <code>true</code> if we are in layer mode, <code>false</code>
     *         otherwise
     */
    boolean isLayerMode();

    /** Dispose this manager. */
    void dispose();
}
