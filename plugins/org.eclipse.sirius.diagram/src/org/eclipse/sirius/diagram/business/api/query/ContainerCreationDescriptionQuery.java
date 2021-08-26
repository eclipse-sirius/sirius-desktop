/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.query.AbstractNodeMappingApplicabilityTester;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link ContainerCreationDescription} as a starting point.
 * 
 * @author cbrun
 * 
 */
public class ContainerCreationDescriptionQuery {

    private ContainerCreationDescription tool;

    /**
     * Create a new query.
     * 
     * @param tool
     *            the element to query.
     */
    public ContainerCreationDescriptionQuery(ContainerCreationDescription tool) {
        this.tool = tool;
    }

    /**
     * return true if the node could be created on the current container
     * checking the mapping consistency.
     * 
     * @param diagram
     *            any diagram
     * @return true if the node could be created on the current container
     *         checking the mapping consistency.
     */
    public boolean canCreateIn(DDiagram diagram) {
        return new AbstractNodeMappingApplicabilityTester(tool.getContainerMappings()).canCreateIn(diagram);
    }

    /**
     * return true if the node could be created on the current container
     * checking the mapping consistency.
     * 
     * @param container
     *            any container
     * @return true if the node could be created on the current container
     *         checking the mapping consistency.
     */
    public boolean canCreateIn(DDiagramElementContainer container) {
        if (checkExtraMappings(container)) {
            return true;
        } else {
            return new AbstractNodeMappingApplicabilityTester(tool.getContainerMappings()).canCreateIn(container);
        }
    }

    /**
     * return true if the node could be created on the current container
     * checking the mapping consistency.
     * 
     * @param node
     *            any container node
     * @return true if the node could be created on the current container
     *         checking the mapping consistency.
     */
    public boolean canCreateIn(DNode node) {
        if (checkExtraMappings(node)) {
            return true;
        } else {
            return new AbstractNodeMappingApplicabilityTester(tool.getContainerMappings()).canCreateIn(node);
        }

    }

    private boolean checkExtraMappings(AbstractDNode nodeOrContainer) {
        for (AbstractNodeMapping extrMapping : tool.getExtraMappings()) {
            DiagramElementMappingQuery extraMappingQuery = new DiagramElementMappingQuery(extrMapping);
            if (extraMappingQuery.isInstanceOf(nodeOrContainer)) {
                return true;
            }
        }
        return false;
    }

}
