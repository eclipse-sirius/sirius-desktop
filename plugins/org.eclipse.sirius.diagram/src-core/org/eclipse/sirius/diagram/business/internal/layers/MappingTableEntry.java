/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A mapping node is wrapper to a mapping with references to its importer and
 * children.
 * 
 * @author mchauvin
 */
public class MappingTableEntry {

    /** the layer which owns this mapping */
    private final Layer parentLayer;

    /** all layers which have a reference to this mapping */
    private final Collection<Layer> indirectParentLayers;

    private final DiagramElementMapping mapping;

    private List<MappingTableEntry> mappingImporters;

    /* keep for previous compatibility */
    private Collection<MappingTableEntry> otherImporters;

    /**
     * Constructor.
     * 
     * @param mapping
     *            the mapping.
     * @param parentLayer
     *            the parent layer
     * @param indirectParentLayers
     *            all the indirect parent layers
     */
    public MappingTableEntry(final DiagramElementMapping mapping, final Layer parentLayer, final Collection<Layer> indirectParentLayers) {
        this.mapping = mapping;
        this.parentLayer = parentLayer;
        this.indirectParentLayers = indirectParentLayers;
        this.mappingImporters = new ArrayList<>();
    }

    /**
     * Search the {@link MappingTableEntry} which owns the mapping given as
     * parameter.
     * 
     * @param searchedMapping
     *            the mapping to search
     * @return a {@link MappingTableEntry} which owns the searchedMapping, or
     *         <code>null</code> if not found
     */
    public MappingTableEntry searchMappingEntry(final DiagramElementMapping searchedMapping) {
        if (EqualityHelper.areEquals(this.mapping, searchedMapping)) {
            return this;
        }

        MappingTableEntry result = find(this.getImporters(), searchedMapping);

        if (result == null) {
            result = searchInOtherImporters(searchedMapping, this);
        }
        return result;
    }

    private MappingTableEntry find(final Collection<MappingTableEntry> entries, final DiagramElementMapping searchedMapping) {

        MappingTableEntry result = null;

        for (final MappingTableEntry entry : entries) {
            if (EqualityHelper.areEquals(entry.mapping, searchedMapping)) {
                result = entry;
                break;
            }
            MappingTableEntry findEntry = find(entry.getImporters(), searchedMapping);
            if (findEntry != null) {
                result = findEntry;
                break;
            }
        }
        return result;
    }

    private MappingTableEntry searchInOtherImporters(final DiagramElementMapping searchedMapping, final MappingTableEntry entry) {
        if (EqualityHelper.areEquals(entry.mapping, searchedMapping)) {
            return entry;
        }
        return searchInOtherImporters(searchedMapping, entry.getOtherImporters());
    }

    private MappingTableEntry searchInOtherImporters(final DiagramElementMapping searchedMapping, final Collection<MappingTableEntry> entries) {
        for (final MappingTableEntry entry : entries) {
            return searchInOtherImporters(searchedMapping, entry);
        }
        return null;
    }

    /**
     * Get the children entries.
     * 
     * @return never null
     */
    public Collection<? extends MappingTableEntry> getChildren() {
        return Collections.emptySet();
    }

    /**
     * Add an {@link MappingTableEntry} instance as import of the current
     * {@link MappingTableEntry}.
     * 
     * @param mNode
     *            the mapping node to add as importer
     */
    public void setImporter(final MappingTableEntry mNode) {
        if (mappingImporters == null) {
            mappingImporters = new ArrayList<>();
        }
        if (!mappingImporters.isEmpty()) {
            mappingImporters.remove(0);
        }
        mappingImporters.add(0, mNode);
    }

    /**
     * Check if a mapping is imported by others mappings.
     * 
     * @return <code>true</code> if the mapping is imported, <code>false</code>
     *         otherwise.
     */
    public boolean isImported() {
        return mappingImporters != null && !mappingImporters.isEmpty();
    }

    /**
     * Get the wrapped {@link DiagramElementMapping} instance.
     * 
     * @return the wrapped mapping.
     */
    public DiagramElementMapping getMapping() {
        return mapping;
    }

    /**
     * Get the first mappings node which imports this mapping, None
     * {@link Option} if any.
     * 
     * @return an optional {@link MappingTableEntry} instance which import this
     *         mapping
     */
    public Option<MappingTableEntry> getImporter() {
        if (mappingImporters == null || mappingImporters.isEmpty()) {
            return Options.newNone();
        }
        return Options.newSome(this.mappingImporters.get(0));
    }

    /**
     * Get all the mappings node which imports this mapping.
     * 
     * @return the {@link MappingTableEntry} instance which import this mapping
     */
    public Collection<MappingTableEntry> getImporters() {
        return this.mappingImporters;
    }

    /**
     * Add an importers to the not taken importers list.
     * 
     * @param importer
     *            the MappingNode to add.
     */
    public void addOtherImporters(final MappingTableEntry importer) {
        this.mappingImporters.add(importer);
        if (otherImporters == null) {
            otherImporters = new ArrayList<>();
        }
        otherImporters.add(importer);
    }

    /**
     * Get the mappings nodes which imports this mapping and are not taken in
     * account for hierarchy.
     * 
     * @return a collection of {@link MappingTableEntry} instance which imports
     *         this mapping
     */
    public Collection<MappingTableEntry> getOtherImporters() {
        if (otherImporters == null) {
            return Collections.<MappingTableEntry> emptyList();
        }
        return otherImporters;
    }

    /**
     * Get the layer which owns this mapping.
     * 
     * @return the parent layer
     */
    public Layer getParentLayer() {
        return this.parentLayer;
    }

    /**
     * Returns the indirectParentLayers.
     * 
     * @return The indirectParentLayers.
     */
    public Collection<Layer> getIndirectParentLayers() {
        return indirectParentLayers;
    }

}
