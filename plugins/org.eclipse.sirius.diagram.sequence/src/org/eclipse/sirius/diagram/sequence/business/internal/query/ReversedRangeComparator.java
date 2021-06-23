/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;

/**
 * {@link Comparator} used to order Set of {@link ISequenceElement} according to their {@link Range#getUpperBound()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ReversedRangeComparator implements Comparator<ISequenceElement>, Serializable {

    /**
     * Generated SUID.
     */
    private static final long serialVersionUID = -962057468507177598L;

    /**
     * Overridden to compare {@link ISequenceElement} order according to their range from upper to lower.
     * 
     * {@inheritDoc}
     */
    @Override
    public int compare(ISequenceElement sequenceElement1, ISequenceElement sequenceElement2) {
        Range sequenceElement1Range = RangeHelper.verticalRange(sequenceElement1.getProperLogicalBounds());
        Range sequenceElement2Range = RangeHelper.verticalRange(sequenceElement2.getProperLogicalBounds());
        int comparison = sequenceElement1Range.getUpperBound() - sequenceElement2Range.getUpperBound();
        if (comparison == 0) {
            if (sequenceElement1 instanceof ISequenceEvent && sequenceElement2 instanceof ISequenceEvent) {
                ISequenceEvent sequenceEvent1 = (ISequenceEvent) sequenceElement1;
                ISequenceEvent sequenceEvent2 = (ISequenceEvent) sequenceElement2;

                SequenceDiagram sequenceDiagram = sequenceElement1.getDiagram();

                List<EventEnd> sequenceEvent1EventEnds = sequenceDiagram.findEnds(sequenceEvent1);
                List<EventEnd> sequenceEvent2EventEnds = sequenceDiagram.findEnds(sequenceEvent2);

                if (!sequenceEvent1EventEnds.isEmpty() && !sequenceEvent2EventEnds.isEmpty()) {
                    EventEnd lastEventEndOfSequenceEvent1 = sequenceEvent1EventEnds.get(sequenceEvent1EventEnds.size() - 1);
                    EventEnd lastEventEndOfSequenceEvent2 = sequenceEvent2EventEnds.get(sequenceEvent2EventEnds.size() - 1);
                    List<EventEnd> eventEnds = sequenceDiagram.getSequenceDDiagram().getGraphicalOrdering().getEventEnds();
                    comparison = eventEnds.indexOf(lastEventEndOfSequenceEvent2) - eventEnds.indexOf(lastEventEndOfSequenceEvent1);
                }
            }

        }
        return comparison;
    }

}
