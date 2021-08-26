/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * /** A class aggregating all the queries (read-only!) having a Diagram
 * {@link MappingBasedToolDescription} as a starting point.
 * 
 * @since 0.9.0
 * @author alagarde
 * 
 */
public class MappingBasedToolDescriptionQuery {

    private MappingBasedToolDescription toolDescription;

    /**
     * Default constructor.
     * 
     * @param toolDescription
     *            the {@link MappingBasedToolDescription} to query
     */
    public MappingBasedToolDescriptionQuery(MappingBasedToolDescription toolDescription) {
        this.toolDescription = toolDescription;
    }

    /**
     * Returns all {@link RepresentationElementMapping}s associated to this
     * {@link MappingBasedToolDescription}.
     * 
     * @return all {@link RepresentationElementMapping}s associated to this
     *         {@link MappingBasedToolDescription}
     */
    public Collection<RepresentationElementMapping> getMappings() {
        Collection<RepresentationElementMapping> mappings = new LinkedHashSet<>();
        if (toolDescription instanceof ContainerCreationDescription) {
            ContainerCreationDescription ccd = (ContainerCreationDescription) toolDescription;
            mappings.addAll(ccd.getContainerMappings());
            mappings.addAll(ccd.getExtraMappings());
        }
        if (toolDescription instanceof ContainerDropDescription) {
            mappings.addAll(((ContainerDropDescription) toolDescription).getMappings());
        }
        if (toolDescription instanceof DeleteElementDescription) {
            mappings.addAll(((DeleteElementDescription) toolDescription).getMappings());
        }
        if (toolDescription instanceof DirectEditLabel) {
            mappings.addAll(((DirectEditLabel) toolDescription).getMapping());
        }
        if (toolDescription instanceof DoubleClickDescription) {
            mappings.addAll(((DoubleClickDescription) toolDescription).getMappings());
        }
        if (toolDescription instanceof EdgeCreationDescription) {
            EdgeCreationDescription ecd = (EdgeCreationDescription) toolDescription;
            mappings.addAll(ecd.getEdgeMappings());
            mappings.addAll(ecd.getExtraSourceMappings());
        }
        if (toolDescription instanceof NodeCreationDescription) {
            NodeCreationDescription ncd = (NodeCreationDescription) toolDescription;
            mappings.addAll(ncd.getNodeMappings());
            mappings.addAll(ncd.getExtraMappings());
        }
        if (toolDescription instanceof ReconnectEdgeDescription) {
            mappings.addAll(((ReconnectEdgeDescription) toolDescription).getMappings());
        }
        return mappings;
    }
}
