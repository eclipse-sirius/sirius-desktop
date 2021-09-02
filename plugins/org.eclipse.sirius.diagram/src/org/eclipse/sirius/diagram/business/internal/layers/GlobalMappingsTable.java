/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.layers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.table.CandidateMappingManager;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.tools.api.Messages;

/**
 * The global mappings table to retrieve mappings information.
 * 
 * @author mchauvin
 */
public class GlobalMappingsTable {

    private final DiagramDescriptionMappingsManager descriptionMappings;

    private MappingsTable<NodeMapping> nodeMappingsTable;

    private MappingsTable<ContainerMapping> containerMappingsTable;

    private MappingsTable<EdgeMapping> edgeMappingsTable;

    private CandidateMappingManager candidateMappingsManager;

    /**
     * Construct a new instance.
     * 
     * @param descriptionMappings
     *            the description mappings from which to get the available
     *            mappings depending on the activated viewpoints
     */
    public GlobalMappingsTable(final DiagramDescriptionMappingsManager descriptionMappings) {
        this.descriptionMappings = descriptionMappings;

        nodeMappingsTable = new MappingsTable<NodeMapping>();
        containerMappingsTable = new MappingsTable<ContainerMapping>();
        edgeMappingsTable = new MappingsTable<EdgeMapping>();

        candidateMappingsManager = new CandidateMappingManager(descriptionMappings);
    }

    /**
     * Build the global table of available mappings depending on the activated
     * layers.
     * 
     * @param activatedLayers
     *            the activated layers
     */
    public void build(final Collection<Layer> activatedLayers) {

        nodeMappingsTable.clear();
        containerMappingsTable.clear();
        edgeMappingsTable.clear();

        candidateMappingsManager.build(activatedLayers);

        containerMappingsTable.build(candidateMappingsManager.getAvailableCandidateContainerMappings());
        nodeMappingsTable.build(candidateMappingsManager.getAvailableCandidateNodeMappings());
        edgeMappingsTable.build(candidateMappingsManager.getAvailableCandidateEdgeMappings());
    }

    public List<NodeMapping> getNodeMappings() {
        return nodeMappingsTable.availableSortedMappingsList(descriptionMappings.getNodeMappings());
    }

    public List<ContainerMapping> getContainerMappings() {
        return containerMappingsTable.availableSortedMappingsList(descriptionMappings.getContainerMappings());
    }

    public List<EdgeMapping> getEdgeMappings() {
        return edgeMappingsTable.availableSortedMappingsList(descriptionMappings.getEdgeMappings());
    }

    /**
     * Get other importers mappings (those which were not used because there was
     * at least another importer).
     * 
     * @return the other importers
     */
    public List<DiagramElementMapping> getOtherImportersMappings() {
        final List<DiagramElementMapping> otherImporterMappings = new ArrayList<DiagramElementMapping>();
        otherImporterMappings.addAll(nodeMappingsTable.otherImportersMappingsList());
        otherImporterMappings.addAll(containerMappingsTable.otherImportersMappingsList());
        return otherImporterMappings;
    }

    /**
     * Get the available container mappings children of the container mapping
     * given as parameter.
     * 
     * @param initialContainerMappings
     *            .
     * @return .
     */
    public List<ContainerMapping> getContainerMappings(final List<ContainerMapping> initialContainerMappings) {
        return containerMappingsTable.availableSortedMappingsList(initialContainerMappings);
    }

    /**
     * Get the node mappings children of the container mapping given as
     * parameter.
     * 
     * @param initialNodeMappings
     *            .
     * @return .
     */
    public List<NodeMapping> getNodeMappings(final List<NodeMapping> initialNodeMappings) {
        return nodeMappingsTable.availableSortedMappingsList(initialNodeMappings);
    }

    /**
     * Get the sorted bordered node mappings.
     * 
     * @param borderNodeMappings
     *            .
     * @return .
     */
    public List<NodeMapping> getBorderedNodeMappings(final List<NodeMapping> borderNodeMappings) {
        return nodeMappingsTable.availableSortedMappingsList(borderNodeMappings);
    }

    /**
     * get the indirect parent layers of a mapping.
     * 
     * @param mapping
     *            the diagram element mapping
     * @return the indirect parent layers
     */
    public Collection<Layer> getIndirectParentLayers(final DiagramElementMapping mapping) {

        Collection<Layer> parents = Collections.emptySet();
        final MappingTableEntry mappingNode = searchMappingEntry(mapping);

        if (mappingNode != null) {
            parents = mappingNode.getIndirectParentLayers();
        }

        return parents;
    }

    /**
     * Search a mapping entry in the global mapping table.
     * 
     * @param mapping
     *            the mapping to search
     * @return the mapping entry found
     */
    private MappingTableEntry searchMappingEntry(final DiagramElementMapping mapping) {
        MappingTableEntry entry;
        if (mapping instanceof NodeMapping) {
            entry = nodeMappingsTable.searchMappingEntry((NodeMapping) mapping);
        } else if (mapping instanceof ContainerMapping) {
            entry = containerMappingsTable.searchMappingEntry((ContainerMapping) mapping);
        } else if (mapping instanceof EdgeMapping) {
            entry = edgeMappingsTable.searchMappingEntry((EdgeMapping) mapping);
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.GlobalMappingsTable_mappingErrorMsg, mapping.getClass()));
        }
        return entry;
    }

}
