/*******************************************************************************
 * Copyright (c) 2009, 2013 THALES GLOBAL SERVICES.
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.table.CandidateMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;

/**
 * A table to store mappings inheritance paths.
 * 
 * @param <T>
 *            a class which extends {@link DiagramElementMapping}
 * 
 * @author mchauvin
 */
public class MappingsTable<T extends DiagramElementMapping> {

    private final List<MappingTableEntry> mappingsTable;

    /**
     * Construct a new mappings table.
     */
    public MappingsTable() {
        mappingsTable = new ArrayList<MappingTableEntry>();
    }

    /**
     * Clear the mappings table.
     */
    public void clear() {
        mappingsTable.clear();
    }

    /**
     * Build a mapping table.
     * 
     * @param candidateMappings
     *            the available candidate
     */
    public void build(final Collection<CandidateMapping> candidateMappings) {
        /*
         * add the mappings which are not imports mappings
         */
        addNotImportMappings(candidateMappings);

        /*
         * Add the imports mappings
         */
        addImportMappings(candidateMappings);
    }

    private void addNotImportMappings(final Collection<CandidateMapping> candidateMappings) {
        for (final CandidateMapping candidate : candidateMappings) {
            final DiagramElementMapping mapping = candidate.getMapping();
            if (!(mapping instanceof NodeMappingImport) && !(mapping instanceof ContainerMappingImport) && !(mapping instanceof EdgeMappingImport)) {
                addNotImportMappingInTable(candidate);
            }
        }
    }

    /**
     * @param availableMappings
     */
    private void addImportMappings(final Collection<CandidateMapping> candidateMappings) {
        for (final CandidateMapping candidate : candidateMappings) {
            final DiagramElementMapping mapping = candidate.getMapping();
            if (mapping instanceof AbstractMappingImport || mapping instanceof EdgeMappingImport) {
                addImportMappingInTable(candidate, candidateMappings);
            }
        }
    }

    /**
     * get the available mappings list from an original mappings list. For each
     * mapping if there is more specialized mapping than the original it will be
     * added before the original.
     * 
     * @param originalMappings
     *            the list of original mappings
     * @return a list of mappings.
     */
    public List<T> availableSortedMappingsList(final List<T> originalMappings) {

        final List<T> result = new ArrayList<T>();
        for (final T mapping : originalMappings) {
            final List<T> sortedMappings = availableSortedMappingsList(mapping);
            for (final T sortedMapping : sortedMappings) {
                if (!result.contains(sortedMapping)) {
                    result.add(sortedMapping);
                }
            }
        }
        return result;
    }

    private List<T> availableSortedMappingsList(final T mapping) {
        final MappingTableEntry entry = searchEntry(mapping);
        if (entry == null) {
            return Collections.emptyList();
        }
        final List<T> list = convertMappingNodeToList(entry);
        return list;
    }

    /**
     * Browse a mappings list from its more specialized mapping.
     * 
     * @return a list of mappings.
     */
    public List<T> otherImportersMappingsList() {
        final List<T> result = new ArrayList<T>();
        final Iterator<MappingTableEntry> it = getSafeIterator();
        while (it.hasNext()) {
            final MappingTableEntry mNode = it.next();
            final List<T> list = convertMappingNodeToImportersList(mNode);
            result.addAll(list);
        }
        return result;
    }

    private Iterator<MappingTableEntry> getSafeIterator() {
        return new ArrayList<MappingTableEntry>(mappingsTable).iterator();
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
    public MappingTableEntry searchMappingEntry(final T searchedMapping) {
        return searchEntry(searchedMapping);
    }

    /**
     * Convert a mapping Node to an ordered list.
     * 
     * @param mNode
     *            the mapping node to convert
     * @param <T>
     *            .
     * @return a {@link List} instance
     */
    @SuppressWarnings("unchecked")
    private List<T> convertMappingNodeToList(final MappingTableEntry mNode) {
        final List<T> list = new LinkedList<T>();

        MappingTableEntry current = mNode;
        list.add((T) current.getMapping());

        for (MappingTableEntry mappingTableEntry : current.getImporters()) {
            list.add(0, (T) mappingTableEntry.getMapping());
            convertMappingNodeToList(list, mappingTableEntry);
        }
        return list;
    }

    /**
     * Convert a mapping Node to an ordered list.
     * 
     * @param list
     * @param current
     */
    private void convertMappingNodeToList(final List<T> list, MappingTableEntry current) {
        for (MappingTableEntry mappingTableEntry : current.getImporters()) {
            list.add(0, (T) mappingTableEntry.getMapping());
            convertMappingNodeToList(list, mappingTableEntry);
        }
    }

    /**
     * Convert a mapping Node to an ordered list of not taken importers.
     * 
     * @param mNode
     *            the mapping node to convert
     * @param <T>
     *            .
     * @return a {@link List} instance
     */
    @SuppressWarnings("unchecked")
    private List<T> convertMappingNodeToImportersList(final MappingTableEntry mNode) {
        final List<T> list = new LinkedList<T>();

        MappingTableEntry current = mNode;
        Collection<MappingTableEntry> others = current.getOtherImporters();
        for (final MappingTableEntry mappingNode : others) {
            list.add(0, (T) mappingNode.getMapping());
        }

        while (current.getImporter().some()) {
            current = current.getImporter().get();
            others = current.getOtherImporters();
            for (final MappingTableEntry mappingNode : others) {
                list.add(0, (T) mappingNode.getMapping());
            }
        }
        return list;
    }

    /**
     * Search the {@link MappingTableEntry} which owns the mapping given as
     * parameter in the provided list.
     * 
     * @param searchedMapping
     *            the mapping to search
     * @return a {@link MappingTableEntry} which owns the searchedMapping, or
     *         <code>null</code> if not found
     */
    private MappingTableEntry searchEntry(final DiagramElementMapping searchedMapping) {

        /* safe iterator */
        final Iterator<MappingTableEntry> itListEntry = getSafeIterator();

        MappingTableEntry node = null;

        while (itListEntry.hasNext() && node == null) {
            final MappingTableEntry currentTreeEntry = itListEntry.next();
            node = currentTreeEntry.searchMappingEntry(searchedMapping);
        }

        return node;
    }

    private void addNotImportMappingInTable(final CandidateMapping candidate) {
        final MappingTableEntry entry = new MappingTableEntry(candidate.getMapping(), candidate.getParentLayer(), candidate.getParentLayers());
        final DiagramElementMapping mapping = entry.getMapping();
        final MappingTableEntry existingEntry = searchEntry(mapping);
        if (existingEntry == null) {
            mappingsTable.add(entry);
        }
    }

    private Option<CandidateMapping> getImportedCandidateMapping(Collection<CandidateMapping> candidateMappings, DiagramElementMapping importedMapping) {
        for (CandidateMapping candidateMapping : candidateMappings) {
            if (candidateMapping.getMapping().equals(importedMapping)) {
                return Options.newSome(candidateMapping);
            }
        }
        return Options.newNone();
    }

    private void addImportMappingInTable(final CandidateMapping candidate, Collection<CandidateMapping> candidateMappings) {
        MappingTableEntry entry = new MappingTableEntry(candidate.getMapping(), candidate.getParentLayer(), candidate.getParentLayers());
        final DiagramElementMapping mapping = entry.getMapping();
        final DiagramElementMapping importedMapping = getImportedMapping(mapping);

        MappingTableEntry importedMappingExistingEntry = searchEntry(importedMapping);

        MappingTableEntry mappingEntry = searchEntry(entry.getMapping());
        if (mappingEntry != null) {
            entry = mappingEntry;
        }

        if (importedMappingExistingEntry == null) {
            /*
             * imported mapping entry was not found => we add the mapping import
             * entry as root in the table
             */
            mappingsTable.add(entry);
            Option<CandidateMapping> importedCandidate = getImportedCandidateMapping(candidateMappings, importedMapping);
            if (importedCandidate.some()) {
                importedMappingExistingEntry = new MappingTableEntry(importedCandidate.get().getMapping(), importedCandidate.get().getParentLayer(), importedCandidate.get().getParentLayers());
                mappingsTable.add(importedMappingExistingEntry);
            }
        }

        if (importedMappingExistingEntry != null) {
            /*
             * imported mapping entry was found => We take the last importer and
             * add the previous one as others
             */
            final Option<MappingTableEntry> previousImporter = importedMappingExistingEntry.getImporter();
            if (previousImporter.some()) {
                importedMappingExistingEntry.addOtherImporters(previousImporter.get());
            }
            importedMappingExistingEntry.setImporter(entry);
        }
    }

    private DiagramElementMapping getImportedMapping(final DiagramElementMapping mapping) {
        DiagramElementMapping importedMapping = null;

        if (mapping instanceof NodeMappingImport) {
            importedMapping = ((NodeMappingImport) mapping).getImportedMapping();
        } else if (mapping instanceof ContainerMappingImport) {
            importedMapping = ((ContainerMappingImport) mapping).getImportedMapping();
        } else if (mapping instanceof EdgeMappingImport) {
            IEdgeMappingQuery query = new IEdgeMappingQuery(((EdgeMappingImport) mapping).getImportedMapping());
            importedMapping = query.getEdgeMapping().get();
        }
        return importedMapping;
    }

}
