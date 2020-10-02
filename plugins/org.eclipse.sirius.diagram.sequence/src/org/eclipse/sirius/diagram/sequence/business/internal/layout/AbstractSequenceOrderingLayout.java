/*******************************************************************************
 * Copyright (c) 2012, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

/**
 * Computes the appropriate graphical locations of sequence events and lifelines on a sequence diagram to reflect the
 * semantic order.
 * 
 * @param <S>
 *            the layouted element type.
 * 
 * @param <T>
 *            the layout data type.
 * 
 * @param <U>
 *            the ordering type.
 * 
 * @author mporhel
 */
public abstract class AbstractSequenceOrderingLayout<S, T, U> extends AbstractSequenceLayout<S, T> {

    /**
     * The semantic ordering.
     */
    protected final List<U> semanticOrdering;

    /**
     * The graphical ordering.
     */
    protected final List<U> graphicalOrdering;

    /**
     * Semantic flagged ordering elements.
     */
    protected final List<U> flaggedEnds;

    /**
     * Old flagged absolute bounds.
     */
    protected final Map<S, Rectangle> oldFlaggedLayoutData;

    /**
     * Constructor.
     * 
     * @param sequenceDiagram
     *            the sequence diagram to layout.
     */
    public AbstractSequenceOrderingLayout(SequenceDiagram sequenceDiagram) {
        super(sequenceDiagram);

        this.semanticOrdering = new ArrayList<>();
        this.graphicalOrdering = new ArrayList<>();
        this.flaggedEnds = new ArrayList<>();
        this.oldFlaggedLayoutData = new HashMap<>();
    }

    /**
     * Dispose the layout context after layout application.
     */
    @Override
    protected void dispose() {
        semanticOrdering.clear();
        graphicalOrdering.clear();
        flaggedEnds.clear();
        oldFlaggedLayoutData.clear();
        super.dispose();
    }

    /**
     * 
     * Look in the semantic, graphical and flaggedEnds orderings to retrieve the safest predecessor and try to keep a
     * stable delta regarding it.
     * 
     * @param currentPos
     *            the current position (x or y)
     * @param element
     *            the current ordering element
     * @param alreadyComputedLocations
     *            the already computed locations
     * @return the delta stable position.
     */
    protected int getDeltaStablePosition(final int currentPos, final U element, Map<? extends U, Integer> alreadyComputedLocations) {
        int deltaStablePos = currentPos;
        int semanticIndex = semanticOrdering.indexOf(element);
        int graphicalIndex = graphicalOrdering.indexOf(element);
        int flaggedIndex = flaggedEnds.indexOf(element);

        if (flaggedIndex != -1 && semanticIndex != 0 && graphicalIndex != -1) {
            Set<U> semanticPredecessors = new LinkedHashSet<>(semanticOrdering.subList(0, semanticIndex));
            Set<U> graphicalPredecessors = new LinkedHashSet<>(graphicalOrdering.subList(0, graphicalIndex));
            Set<U> flaggedPredecessors = new LinkedHashSet<>(flaggedEnds.subList(0, flaggedIndex));

            // Intersection
            HashSet<U> flaggedEndsSet = new LinkedHashSet<>(flaggedEnds);
            semanticPredecessors = Sets.intersection(semanticPredecessors, flaggedEndsSet);
            graphicalPredecessors = Sets.intersection(graphicalPredecessors, flaggedEndsSet);
            flaggedPredecessors = Sets.intersection(flaggedPredecessors, semanticPredecessors);

            // Which is the safer position ?
            Function<U, Integer> oldPosition = getOldPosition();
            U flaggedPred = null;

            Optional<U> lastGraphPredecessor = getLastGraphPredecessorIfEquals(semanticPredecessors, graphicalPredecessors);

            if (lastGraphPredecessor.isPresent()) {
                flaggedPred = lastGraphPredecessor.get();
            } else {
                // Desynchronisation -> flagged position
                oldPosition = getOldFlaggedPosition();

                // Look for the last semantic predecessor with same index in
                // semantic and flagged lists.
                U potentialSafePred = null;
                Iterator<U> semanticPredecessorsIterator = semanticPredecessors.iterator();
                Iterator<U> flaggedPredecessorsIterator = flaggedPredecessors.iterator();
                int i = 0;

                while (flaggedPredecessorsIterator.hasNext() && semanticPredecessorsIterator.hasNext() && i < flaggedPredecessors.size()) {
                    U flaggedPot = flaggedPredecessorsIterator.next();
                    U semPot = semanticPredecessorsIterator.next();
                    if (semPot != null && semPot.equals(flaggedPot)) {
                        potentialSafePred = semPot;
                    }
                    i++;
                }

                if (potentialSafePred != null) {
                    flaggedPred = potentialSafePred;
                }
            }
            if (flaggedPred != null) {
                Integer predY = oldPosition.apply(flaggedPred);
                Integer flaggedY = oldPosition.apply(element);
                int delta = flaggedY - predY;
                if (delta >= 0) {
                    deltaStablePos = alreadyComputedLocations.get(flaggedPred) + delta;
                }
            }
        }
        return deltaStablePos;

    }

    private Optional<U> getLastGraphPredecessorIfEquals(Set<U> semanticPredecessors, Set<U> graphicalPredecessors) {
        Optional<U> result;
        if (semanticPredecessors.size() != graphicalPredecessors.size()) {
            result = Optional.empty();
        } else {
            Iterator<U> graphicalPredecessorsIterator = graphicalPredecessors.iterator();
            Iterator<U> semanticPredecessorsIterator = semanticPredecessors.iterator();
            U lastGraphPredecessor = null;
            while (graphicalPredecessorsIterator.hasNext() && semanticPredecessorsIterator.hasNext()) {
                U graphPred = graphicalPredecessorsIterator.next();
                U semPred = semanticPredecessorsIterator.next();
                if (!graphPred.equals(semPred)) {
                    return Optional.empty();
                }
                lastGraphPredecessor = graphPred;
            }
            result = Optional.ofNullable(lastGraphPredecessor);
        }
        return result;
    }

    /**
     * A function to retrieve the old positions.
     * 
     * @return the function.
     */
    protected abstract Function<U, Integer> getOldPosition();

    /**
     * A function to retrieve the old flagged positions.
     * 
     * @return the function.
     */
    protected abstract Function<U, Integer> getOldFlaggedPosition();
}
