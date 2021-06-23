/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;

/**
 * Helper to check position of events on the diagram.
 * 
 * @author mporhel
 * 
 */
public class PositionsChecker {

    private SequenceDiagram diagram;

    private Function<ISequenceEvent, Range> rangeFunction = ISequenceEvent.VERTICAL_RANGE;

    /**
     * Constructor. Position will be computed using the events vertical range.
     * 
     * @param diagram
     *            the diagram to check.
     */
    public PositionsChecker(SequenceDiagram diagram) {
        this.diagram = diagram;
    }

    /**
     * Constructor. Position will be computed using the provided function.
     * 
     * @param diagram
     *            the diagram to check.
     * @param rangeFunction
     *            function to compute range (future range for example).
     * 
     */
    public PositionsChecker(SequenceDiagram diagram, Function<ISequenceEvent, Range> rangeFunction) {
        this.diagram = diagram;

        if (rangeFunction != null) {
            this.rangeFunction = rangeFunction;
        }
    }

    /**
     * Inspect all sequence events and check that there is no conflicts.
     * 
     * @return a collection of invalid positions.
     */
    public Collection<Integer> getInvalidPositions() {
        final Multiset<Integer> positions = HashMultiset.create();
        // Check conflicts
        for (ISequenceEvent ise : diagram.getAllOrderedDelimitedSequenceEvents()) {
            Range futureRange = rangeFunction.apply(ise);
            int futureLowerBound = futureRange.getLowerBound();
            int futureUpperBound = futureRange.getUpperBound();

            if (ise instanceof Execution) {
                Execution exec = (Execution) ise;
                if (!exec.getStartMessage().some()) {
                    positions.add(futureLowerBound);
                }
                if (!exec.getEndMessage().some()) {
                    positions.add(futureUpperBound);
                }
            } else if (ise instanceof Operand) {
                positions.add(futureLowerBound);
            } else if (ise instanceof Message) {
                positions.add(futureLowerBound);
                if (((Message) ise).isReflective()) {
                    positions.add(futureUpperBound);
                }
            } else if (ise instanceof State && ise.isLogicallyInstantaneous()) {
                positions.add(futureRange.middleValue());
            } else {
                positions.add(futureLowerBound);
                positions.add(futureUpperBound);
            }
        }

        Set<Integer> invalidPositions = new HashSet<>();
        Iterables.addAll(invalidPositions, Iterables.filter(positions, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                int count = positions.count(input);
                return count != 1;
            }
        }));

        return invalidPositions;
    }
}
