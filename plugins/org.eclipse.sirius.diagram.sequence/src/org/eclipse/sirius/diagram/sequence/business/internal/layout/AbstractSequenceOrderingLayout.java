/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.layout;

import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Computes the appropriate graphical locations of sequence events and lifelines
 * on a sequence diagram to reflect the semantic order.
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

        this.semanticOrdering = Lists.newArrayList();
        this.graphicalOrdering = Lists.newArrayList();
        this.flaggedEnds = Lists.newArrayList();
        this.oldFlaggedLayoutData = Maps.newHashMap();
    }

    /**
     * Dispose the layout context after layout application.
     */
    protected void dispose() {
        semanticOrdering.clear();
        graphicalOrdering.clear();
        flaggedEnds.clear();
        oldFlaggedLayoutData.clear();
        super.dispose();
    }

    /**
     * 
     * Look in the semantic, graphical and flaggedEnds orderings to retrieve the
     * safest predecessor and try to keep a stable delta regarding it.
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
            List<U> semanticPredecessors = Lists.newArrayList(semanticOrdering.subList(0, semanticIndex));
            List<U> graphicalPredecessors = Lists.newArrayList(graphicalOrdering.subList(0, graphicalIndex));
            List<U> flaggedPredecessors = Lists.newArrayList(flaggedEnds.subList(0, flaggedIndex));

            // Intersection
            semanticPredecessors.retainAll(flaggedEnds);
            graphicalPredecessors.retainAll(flaggedEnds);
            flaggedPredecessors.retainAll(semanticPredecessors);

            // Which is the safer position ?
            Function<U, Integer> oldPosition = getOldPosition();
            U flaggedPred = null;

            if (Iterables.elementsEqual(semanticPredecessors, graphicalPredecessors) && !graphicalPredecessors.isEmpty()) {
                flaggedPred = graphicalPredecessors.get(graphicalPredecessors.size() - 1);
            } else {
                // Desynchronisation -> flagged position
                oldPosition = getOldFlaggedPosition();

                // Look for the last semantic predecessor with same index in
                // semantic and flagged lists.
                U potentialSafePred = null;
                for (int i = 0; i < flaggedPredecessors.size(); i++) {
                    U semPot = semanticPredecessors.get(i);
                    U flaggedPot = flaggedPredecessors.get(i);
                    if (semPot != null && semPot.equals(flaggedPot)) {
                        potentialSafePred = semPot;
                    }
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
