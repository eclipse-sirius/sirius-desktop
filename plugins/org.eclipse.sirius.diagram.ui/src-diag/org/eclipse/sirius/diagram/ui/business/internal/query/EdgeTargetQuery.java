/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.query;

import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.FoldingStyle;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Queries relative to a {@link EdgeTarget}.
 * 
 * @author pcdavid
 */
public class EdgeTargetQuery {
    /**
     * Represents the possible states of a folding point, i.e. an element from
     * which the user can fold/unfold incident edges.
     * 
     * @author pcdavid
     */
    public enum FoldingState {
        /**
         * Indicates the element is currently fully folded, i.e. all the edges
         * foldable from this point are folded
         */
        FOLDED,
        /**
         * Indicates that some of the edges foldable from this point are folded
         * and some are unfolded.
         */
        MIXED,
        /**
         * Indicates the element is currently fully unfolded, i.e. all the edges
         * foldable from this point are unfolded, or that the element is not a
         * folding point (so there is nothing to fold).
         */
        UNFOLDED;
    }

    private final EdgeTarget target;

    /**
     * Constructor.
     * 
     * @param target
     *            the edge target to query.
     */
    public EdgeTargetQuery(EdgeTarget target) {
        this.target = target;
    }

    /**
     * Test whether the element is the folding point of any edge which supports
     * folding and is visible.
     * 
     * @return <code>true</code> if the element is the folding point of any edge
     *         which supports folding and is visible.
     */
    public boolean isFoldingPoint() {
        Iterable<DEdge> foldables = getAllFoldableEdges();
        return !Iterables.isEmpty(foldables);
    }

    /**
     * Returns all the edges to follow from the edge target when walking the
     * graph on folding/unfolding of edges.
     * 
     * @return all the edges to follow on folding/unfolding of edges.
     */
    public Iterable<DEdge> getFoldableEdgesToFollow() {
        return Iterables.filter(getAllFoldableEdges(), new Predicate<DEdge>() {
            public boolean apply(DEdge input) {
                return !new DDiagramElementQuery(input).isExplicitlyFolded();
            }
        });
    }

    /**
     * Returns all the edges for which the edge target is the folding point.
     * 
     * @return all the edges for which the edge target is the folding point.
     */
    public Iterable<DEdge> getAllFoldableEdges() {
        Iterable<DEdge> incomingFoldables = Iterables.filter(Iterables.filter(target.getIncomingEdges(), DEdge.class), DEdgeQuery.hasFoldingStyle(FoldingStyle.TARGET_LITERAL));
        Iterable<DEdge> outgoingFoldables = Iterables.filter(Iterables.filter(target.getOutgoingEdges(), DEdge.class), DEdgeQuery.hasFoldingStyle(FoldingStyle.SOURCE_LITERAL));
        Iterable<DEdge> allFoldables = Iterables.concat(incomingFoldables, outgoingFoldables);
        return Iterables.filter(allFoldables, new Predicate<DEdge>() {
            public boolean apply(DEdge input) {
                return input != null && input.eContainer() != null;
            }
        });
    }

    /**
     * Returns the current folding state of the part, or more precisely of the
     * edges which support folding from this point.
     * 
     * @return a {@link FoldingState} indicating the current folding state of
     *         the part.
     */
    public FoldingState getFoldingState() {
        FoldingState result = FoldingState.UNFOLDED;
        boolean hasFoldedFoldables = false;
        boolean hasUnfoldedFoldables = false;
        for (DEdge edge : getAllFoldableEdges()) {
            if (new DDiagramElementQuery(edge).isExplicitlyFolded()) {
                hasFoldedFoldables = true;
            } else {
                hasUnfoldedFoldables = true;
            }
        }
        if (hasFoldedFoldables && hasUnfoldedFoldables) {
            result = FoldingState.MIXED;
        } else if (hasFoldedFoldables) {
            result = FoldingState.FOLDED;
        }
        return result;
    }
}
