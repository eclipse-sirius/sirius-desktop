/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.sync;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.query.DSemanticDecoratorQuery;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.DragAndDropTargetQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingsListVisitor;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Update mappings based on activated layers and mappings extension.
 * 
 * @author mchauvin
 */
public class MappingsUpdater {

    private DDiagram diagram;

    private DiagramMappingsManager mappingsManager;

    private DDiagramSynchronizer synchronizer;

    private RefreshIdsHolder ids;

    /**
     * .
     * 
     * @param diagram
     *            .
     * @param mappingsManager
     *            .
     * @param synchronizer
     *            the synchronizer.
     * @param ids
     *            the refresh ids holder.
     */
    public MappingsUpdater(final DDiagram diagram, final DiagramMappingsManager mappingsManager, final DDiagramSynchronizer synchronizer, RefreshIdsHolder ids) {
        this.diagram = diagram;
        this.mappingsManager = mappingsManager;
        this.synchronizer = synchronizer;
        this.ids = ids;
    }

    /**
     * Update the mappings.
     */
    public void updateMappings() {
        updateMappings(diagram);
    }

    /**
     * Update the mappings.
     * 
     * @param container
     *            the container to browse.
     */
    private void updateMappings(final DragAndDropTarget container) {
        if (container instanceof DSemanticDecorator) {
            DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) container;
            if (!new DSemanticDecoratorQuery(dSemanticDecorator).hasDetachedTarget()) {
                safeUpdateMappings(container);
            }
        } else {
            safeUpdateMappings(container);
        }
    }

    private void safeUpdateMappings(DragAndDropTarget container) {
        mappingsManager.iterate(new MappingUpdateVisitor(container, ids), container);
        for (final DDiagramElement child : DragAndDropTargetQuery.on(container).getLogicalChildren()) {
            if (child instanceof DragAndDropTarget) {
                updateMappings((DragAndDropTarget) child);
            }
        }
    }

    /**
     * Visitor class to browse mapping stored in a specific order.
     * 
     * @author mchauvin
     */
    private class MappingUpdateVisitor implements MappingsListVisitor {

        private DragAndDropTarget container;

        private RefreshIdsHolder factory;

        /**
         * Create a new instance
         * 
         * @param container
         *            the container
         */
        MappingUpdateVisitor(final DragAndDropTarget container, RefreshIdsHolder factory) {
            this.container = container;
            this.factory = factory;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingsListVisitor#visit(org.eclipse.sirius.viewpoint.description.DiagramElementMapping,
         *      java.util.Set)
         */
        public Set<DNodeCandidate> visit(final DiagramElementMapping pMapping, final Set<DNodeCandidate> candidateFilter) {
            Set<DNodeCandidate> result;

            if (pMapping instanceof AbstractNodeMapping) {
                final AbstractNodeMapping mapping = (AbstractNodeMapping) pMapping;
                final Collection<DNodeCandidate> validCandidates = getNodeCandidates(mapping, candidateFilter);

                final Set<EObject> semanticElementsDone = new HashSet<EObject>();
                if (!validCandidates.isEmpty()) {
                    final Set<EObject> validSemantics = Sets.newHashSet(Iterables.transform(validCandidates, new Function<DNodeCandidate, EObject>() {
                        public EObject apply(final DNodeCandidate from) {
                            return from.getSemantic();
                        }
                    }));
                    for (final DDiagramElement element : DragAndDropTargetQuery.on(container).getLogicalChildren()) {
                        final DiagramElementMapping elementMapping = element.getDiagramElementMapping();
                        final EObject semanticElement = element.getTarget();
                        if (elementMapping instanceof AbstractNodeMapping && semanticElement != null && validSemantics.contains(semanticElement)) {
                            if (new DiagramElementMappingQuery(elementMapping).areInSameHiearchy(mapping)) {
                                /* update with the new mapping */
                                LayerHelper.updateActualMapping(element, mapping);
                                semanticElementsDone.add(semanticElement);
                            }
                        }
                    }
                }

                result = Sets.newHashSet(Iterables.transform(semanticElementsDone, new Function<EObject, DNodeCandidate>() {
                    public DNodeCandidate apply(final EObject from) {
                        return new DNodeCandidate(mapping, from, container, factory);
                    }
                }));
            } else {
                result = Collections.emptySet();
            }
            return result;
        }

        /*
         * Transform the already known candidate filters
         */
        private Set<DNodeCandidate> getHierarchyCandidateFilter(final AbstractNodeMapping mapping, final Set<DNodeCandidate> semanticFilter) {
            final DiagramElementMappingQuery diagramElementMappingQuery = new DiagramElementMappingQuery(mapping);
            final Map<AbstractNodeMapping, Boolean> knownMappingHierarchy = new HashMap<>();

            return Sets.newLinkedHashSet(Iterables.transform(semanticFilter, new Function<DNodeCandidate, DNodeCandidate>() {
                public DNodeCandidate apply(final DNodeCandidate from) {
                    DNodeCandidate result = from;
                    AbstractNodeMapping fromMapping = from.getMapping();

                    boolean areInSameHiearchy;
                    if (!knownMappingHierarchy.containsKey(fromMapping)) {
                        // Do the hierarchy look up for each mapping only once
                        // per mapping.
                        areInSameHiearchy = diagramElementMappingQuery.areInSameHiearchy(fromMapping);
                        knownMappingHierarchy.put(fromMapping, areInSameHiearchy);
                    } else {
                        areInSameHiearchy = knownMappingHierarchy.get(fromMapping);
                    }

                    if (areInSameHiearchy) {
                        result = new DNodeCandidate(mapping, from.getSemantic(), container, factory);
                    }

                    return result;
                }
            }));
        }

        private Collection<DNodeCandidate> getNodeCandidates(final AbstractNodeMapping mapping, final Set<DNodeCandidate> candidateFilter) {
            Collection<DNodeCandidate> result;
            final Set<DNodeCandidate> hierarchyCandidateFilter = getHierarchyCandidateFilter(mapping, candidateFilter);

            if (!new DiagramElementMappingQuery(mapping).isSynchronizedAndCreateElement(diagram)) {
                /*
                 * in this case we retrieve candidates from previous diagram
                 * elements, so we should take in consideration diagram elements
                 * with mappings which could now be overriden by mapping imports
                 */
                MappingsUpdater.this.synchronizer.forceRetrieve();
                Collection<DNodeCandidate> candidates = MappingsUpdater.this.synchronizer.computeNodeCandidates(container, mapping, hierarchyCandidateFilter);
                MappingsUpdater.this.synchronizer.resetforceRetrieve();
                result = candidates;
            } else {
                result = MappingsUpdater.this.synchronizer.computeNodeCandidates(container, mapping, hierarchyCandidateFilter);
            }
            return result;
        }
    }

}
