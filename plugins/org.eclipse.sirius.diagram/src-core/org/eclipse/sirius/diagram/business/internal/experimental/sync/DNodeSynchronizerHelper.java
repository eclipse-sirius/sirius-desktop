/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.experimental.sync;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.query.AbstractNodeMappingQuery;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * An helper for node.
 * 
 * @author mchauvin
 */
public class DNodeSynchronizerHelper extends AbstractSynchronizerHelper {

    /**
     * Constructor.
     * 
     * @param sync
     *            the main synchronizer
     * @param accessor
     *            the model accessor
     * @param diagram
     *            the semantic diagram
     */
    public DNodeSynchronizerHelper(final DDiagramSynchronizer sync, final DSemanticDiagram diagram, final ModelAccessor accessor) {
        super(sync, diagram, accessor);
    }

    /**
     * Compute node candidates to be added to the given container for the given
     * mapping.
     * 
     * @param container
     *            the container in which candidates will be added
     * @param mapping
     *            the mapping
     * @param candidateFilter
     *            the filter which contains candidates to not add in the
     *            returned collection
     * @param ids
     *            the refresh ids holder.
     * @return all node candidates
     */
    public Collection<AbstractDNodeCandidate> computeNodeCandidates(final DragAndDropTarget container, final AbstractNodeMapping mapping, final Collection<AbstractDNodeCandidate> candidateFilter,
            RefreshIdsHolder ids) {

        final Collection<AbstractDNodeCandidate> nowCandidates = new ArrayList<>();
        final Iterable<EObject> semantics = getSemanticCandidates(container, mapping);

        /*
         * produce the candidates states if the precondition is valid.
         */
        for (final EObject semantic : semantics) {
            final AbstractDNodeCandidate candidate = new AbstractDNodeCandidate(mapping, semantic, container, ids);
            if (candidateFilter == null || !candidateFilter.contains(candidate)) {
                AbstractNodeMappingQuery abstractNodeMappingQuery = new AbstractNodeMappingQuery(mapping);
                if (abstractNodeMappingQuery.evaluatePrecondition(diagram, container, interpreter, semantic)) {
                    nowCandidates.add(candidate);
                    if (candidateFilter != null) {
                        candidateFilter.add(candidate);
                    }
                }
            }
        }
        return nowCandidates;
    }

}
