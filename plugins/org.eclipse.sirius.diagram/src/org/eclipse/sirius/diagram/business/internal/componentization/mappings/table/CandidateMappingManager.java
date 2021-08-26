/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES and others.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionMappingManagerQuery;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * Manage the candidate mappings availability based on activated layers.
 * 
 * @author mchauvin
 */
public class CandidateMappingManager {

    private Set<DiagramElementMapping> allMappings;

    private Set<CandidateMapping> availableCandidates;

    private Predicate<CandidateMapping> nodeMappingPredicate;

    private Predicate<CandidateMapping> containerMappingPredicate;

    private Predicate<CandidateMapping> edgeMappingPredicate;

    private DiagramDescriptionMappingsManager mappingsManager;

    /**
     * Construct a new instance.
     * 
     * @param mappingsManager
     *            .
     */
    public CandidateMappingManager(final DiagramDescriptionMappingsManager mappingsManager) {

        nodeMappingPredicate = new Predicate<CandidateMapping>() {
            @Override
            public boolean apply(final CandidateMapping input) {
                return input.getMapping() instanceof NodeMapping;
            }
        };

        containerMappingPredicate = new Predicate<CandidateMapping>() {
            @Override
            public boolean apply(final CandidateMapping input) {
                return input.getMapping() instanceof ContainerMapping;
            }
        };

        edgeMappingPredicate = new Predicate<CandidateMapping>() {
            @Override
            public boolean apply(final CandidateMapping input) {
                return input.getMapping() instanceof EdgeMapping;
            }
        };

        this.mappingsManager = mappingsManager;

    }

    /**
     * Build the available candidate mappings based on activated layers.
     * 
     * @param activatedLayers
     *            the activated layers
     */
    public void build(final Collection<Layer> activatedLayers) {
        computeAllMappings();
        sortMappings();
        Set<CandidateMapping> candidates = convertMappingsToCandidate();

        final Predicate<CandidateMapping> availablePredicate = new Predicate<CandidateMapping>() {
            @Override
            public boolean apply(final CandidateMapping input) {
                final Collection<Layer> layers = input.getParentLayers();
                for (final Layer layer : layers) {
                    if (EqualityHelper.contains(activatedLayers, layer)) {
                        return true;
                    }
                }
                return false;
            }
        };
        availableCandidates = Sets.filter(candidates, availablePredicate);
    }

    private void computeAllMappings() {
        allMappings = new DiagramDescriptionMappingManagerQuery(mappingsManager).computeAllMappings();
    }

    private void sortMappings() {

        final List<DiagramElementMapping> sortedMappings = new ArrayList<DiagramElementMapping>(allMappings);

        // CHECKSTYLE:OFF
        Collections.sort(sortedMappings, new Comparator<DiagramElementMapping>() {

            /**
             * {@inheritDoc}
             * 
             * @see java.util.Comparator#compare(java.lang.Object,
             *      java.lang.Object)
             */
            @Override
            public int compare(final DiagramElementMapping mapping1, final DiagramElementMapping mapping2) {
                if (mapping1 instanceof AbstractNodeMapping && mapping2 instanceof AbstractNodeMapping) {
                    return compareAM((AbstractNodeMapping) mapping1, (AbstractNodeMapping) mapping2);
                } else if (mapping1 instanceof EdgeMapping && mapping2 instanceof EdgeMapping) {
                    return compareEM((EdgeMapping) mapping1, (EdgeMapping) mapping2);
                }
                return 0;
            }

            private int compareAM(final AbstractNodeMapping nodeMapping1, final AbstractNodeMapping nodeMapping2) {

                if (nodeMapping1 instanceof AbstractMappingImport) {
                    if (CandidateMappingManager.getImportedMapping((AbstractMappingImport) nodeMapping1) == nodeMapping2) {
                        return +1;
                    }
                }
                if (nodeMapping2 instanceof AbstractMappingImport) {
                    if (CandidateMappingManager.getImportedMapping((AbstractMappingImport) nodeMapping2) == nodeMapping1) {
                        return -1;
                    }
                }
                return 0;
            }

            private int compareEM(final EdgeMapping edgeMapping1, final EdgeMapping edgeMapping2) {
                if (edgeMapping1 instanceof EdgeMappingImport) {
                    IEdgeMappingQuery query = new IEdgeMappingQuery(((EdgeMappingImport) edgeMapping1).getImportedMapping());
                    Option<EdgeMapping> importedMapping = query.getEdgeMapping();
                    if (importedMapping.some() && importedMapping.get() == edgeMapping2) {
                        return +1;
                    }
                }
                if (edgeMapping2 instanceof EdgeMappingImport) {
                    IEdgeMappingQuery query = new IEdgeMappingQuery(((EdgeMappingImport) edgeMapping2).getImportedMapping());
                    Option<EdgeMapping> importedMapping = query.getEdgeMapping();
                    if (importedMapping.some() && importedMapping.get() == edgeMapping1) {
                        return -1;
                    }
                }
                return 0;
            }

        });
        // CHECKSTYLE:ON
        allMappings = new LinkedHashSet<DiagramElementMapping>(sortedMappings);

    }

    /**
     * Get the imported mapping from a mapping import.
     * 
     * @param mappingImport
     *            the mapping import
     * @return the imported mapping
     */
    public static AbstractNodeMapping getImportedMapping(final AbstractMappingImport mappingImport) {
        AbstractNodeMapping importedMapping = null;
        if (mappingImport instanceof NodeMappingImport) {
            importedMapping = ((NodeMappingImport) mappingImport).getImportedMapping();
        } else if (mappingImport instanceof ContainerMappingImport) {
            importedMapping = ((ContainerMappingImport) mappingImport).getImportedMapping();
        }
        return importedMapping;
    }

    private Set<CandidateMapping> convertMappingsToCandidate() {
        Set<CandidateMapping> candidates = new LinkedHashSet<CandidateMapping>();
        /* convert mappings to candidate */
        for (final DiagramElementMapping mapping : allMappings) {
            final CandidateMapping candidate = new CandidateMapping(mapping);
            candidates.add(candidate);
        }
        return candidates;
    }

    /**
     * Get the available candidate node mappings.
     * 
     * @return the available candidate which wrap a {@link NodeMapping}
     */
    public Collection<CandidateMapping> getAvailableCandidateNodeMappings() {
        return ImmutableSet.copyOf(Collections2.filter(availableCandidates, nodeMappingPredicate));
    }

    /**
     * Get the available candidate node mappings.
     * 
     * @return the available candidate which wrap a {@link ContainerMapping}
     */
    public Collection<CandidateMapping> getAvailableCandidateContainerMappings() {
        return ImmutableSet.copyOf(Collections2.filter(availableCandidates, containerMappingPredicate));
    }

    /**
     * Get the available candidate node mappings.
     * 
     * @return the available candidate which wrap a {@link EdgeMapping}
     */
    public Collection<CandidateMapping> getAvailableCandidateEdgeMappings() {
        return ImmutableSet.copyOf(Collections2.filter(availableCandidates, edgeMappingPredicate));
    }

}
