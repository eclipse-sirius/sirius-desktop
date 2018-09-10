/*******************************************************************************
 * Copyright (c) 2008, 2018 THALES GLOBAL SERVICES.
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

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;

/**
 * Helper to create/update diagram elements.
 * 
 * @author ymortier
 */
public final class MappingHelper {

    private MappingHelper() {
        super();
    }

    /**
     * Returns all mappings the given mapping contains.
     * 
     * @param mapping
     *            the mapping from which contained mappings must be retrieved.
     * @return all mappings the given mapping contains.
     */
    public static EList<DiagramElementMapping> getAllMappings(final DiagramElementMapping mapping) {
        EList<DiagramElementMapping> diagramElementMappings = new BasicEList<>();
        if (mapping instanceof ContainerMapping) {
            diagramElementMappings = ContainerMappingHelper.getAllMappings((ContainerMapping) mapping);
        } else if (mapping instanceof EdgeMappingImport) {
            final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping((EdgeMappingImport) mapping);
            if (edgeMapping != null) {
                diagramElementMappings = getAllMappings(edgeMapping);
            }
        } else if (mapping instanceof NodeMapping) {
            final BasicEList<DiagramElementMapping> allMappings = new BasicEList<DiagramElementMapping>();
            allMappings.addAll(getAllBorderedNodeMappings((NodeMapping) mapping));
            diagramElementMappings = new UnmodifiableEList<DiagramElementMapping>(allMappings.size(), allMappings.toArray());
        } else {
            diagramElementMappings = new BasicEList.UnmodifiableEList<DiagramElementMapping>(0, new Object[0]);
        }
        return diagramElementMappings;
    }

    /**
     * Implementation of {@link AbstractNodeMapping#getAllBorderedNodeMappings()}.
     * 
     * @param nodeMapping
     *            the mapping.
     * @return all bordered node mappings.
     */
    public static EList<NodeMapping> getAllBorderedNodeMappings(final AbstractNodeMapping nodeMapping) {
        final EList<NodeMapping> result = new BasicEList<NodeMapping>();
        result.addAll(nodeMapping.getBorderedNodeMappings());
        result.addAll(nodeMapping.getReusedBorderedNodeMappings());
        return result;
    }

    /**
     * Return an unmodifiable EList of all node mapping contained by the given container mapping.
     * 
     * @param containerMapping
     *            the mapping from which we want to retrieve contained node mappings
     * @return an unmodifiable EList of all node mapping contained by the given container mapping.
     */
    public static EList<NodeMapping> getAllNodeMappings(ContainerMapping containerMapping) {
        final Collection<NodeMapping> result = ContainerMappingHelper.getAllNodeMappings(containerMapping);
        return new UnmodifiableEList<>(result.size(), result.toArray());
    }

    /**
     * Return an unmodifiable EList of all container mapping contained by the given container mapping.
     * 
     * @param containerMapping
     *            the mapping from which we want to retrieve contained container mappings
     * @return an unmodifiable EList of all container mapping contained by the given container mapping.
     */
    public static EList<ContainerMapping> getAllContainerMappings(ContainerMapping containerMapping) {
        final Collection<ContainerMapping> result = ContainerMappingHelper.getAllContainerMappings(containerMapping);
        return new UnmodifiableEList<>(result.size(), result.toArray());
    }

    /**
     * Return the edgeMapping imported by this edgeMappingImport.
     * 
     * @param edgeMappingImport
     *            The edgeMappingImport
     * @return the edgeMapping imported by this edgeMappingImport
     */
    public static EdgeMapping getEdgeMapping(final EdgeMappingImport edgeMappingImport) {
        EdgeMapping result = null;
        final IEdgeMapping iEdgeMapping = edgeMappingImport.getImportedMapping();
        if (iEdgeMapping instanceof EdgeMapping) {
            result = (EdgeMapping) iEdgeMapping;
        } else if (iEdgeMapping instanceof EdgeMappingImport) {
            result = MappingHelper.getEdgeMapping((EdgeMappingImport) iEdgeMapping);
        }
        return result;
    }
}
