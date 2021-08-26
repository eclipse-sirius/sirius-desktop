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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.visitor.MappingsHierarchyVisitor;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DiagramDescriptionMappingsManager} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class DiagramDescriptionMappingManagerQuery {

    private DiagramDescriptionMappingsManager manager;

    /**
     * Create a new query.
     * 
     * @param manager
     *            the element to query.
     */
    public DiagramDescriptionMappingManagerQuery(DiagramDescriptionMappingsManager manager) {
        this.manager = manager;
    }

    /**
     * Compute all available mappings from a mappings manager.
     * 
     * @return the available mappings from a diagram description
     */
    public Set<DiagramElementMapping> computeAllMappings() {
        final Set<DiagramElementMapping> allMappings = new LinkedHashSet<DiagramElementMapping>();
        computeAllMappings(allMappings, manager.getContainerMappings());
        computeAllMappings(allMappings, manager.getNodeMappings());
        computeAllMappings(allMappings, manager.getEdgeMappings());
        return allMappings;
    }

    private void computeAllMappings(Collection<DiagramElementMapping> allMappings, Collection<? extends DiagramElementMapping> mappings) {
        final MappingsHierarchyVisitor visitor = new MappingsHierarchyVisitor();
        for (final DiagramElementMapping mapping : mappings) {
            if (!allMappings.contains(mapping)) {
                allMappings.add(mapping);
                computeAllMappings(allMappings, visitor.getChildren(mapping));
            }
        }
    }
}
