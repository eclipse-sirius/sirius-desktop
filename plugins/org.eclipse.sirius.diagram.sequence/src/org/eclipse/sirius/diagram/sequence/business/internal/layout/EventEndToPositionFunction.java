/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.layout;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceElementQuery;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Function to compute position of and EventEnd.
 * 
 * @author pcmporhel
 */
public class EventEndToPositionFunction implements Function<EventEnd, Integer> {

    private final Function<EventEnd, Collection<ISequenceEvent>> eventEndToSequenceEvents;

    private final Function<ISequenceEvent, Option<Range>> ranges;

    /**
     * Constructor.
     * 
     * @param eventEndToSequenceEvents
     *            function to get sequence event linked to the given event end.
     * @param ranges
     *            ranges of sequence events.
     */
    public EventEndToPositionFunction(Function<EventEnd, Collection<ISequenceEvent>> eventEndToSequenceEvents, Function<ISequenceEvent, Option<Range>> ranges) {
        this.eventEndToSequenceEvents = eventEndToSequenceEvents;
        this.ranges = ranges;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer apply(EventEnd from) {
        return getOldPosition(from, eventEndToSequenceEvents.apply(from));
    }

    private Integer getOldPosition(EventEnd end, Collection<ISequenceEvent> ises) {
        SingleEventEnd see = null;
        ISequenceEvent ise = null;
        if (end instanceof SingleEventEnd && !ises.isEmpty()) {
            see = (SingleEventEnd) end;
            ise = ises.iterator().next();
        } else if (end instanceof CompoundEventEnd && !ises.isEmpty()) {
            CompoundEventEnd cee = (CompoundEventEnd) end;
            if (EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(cee)) {
                ise = getSafeEvent(ises);
            } else {
                ise = getSafeEvent(ises);
                see = ise == null ? null : EventEndHelper.getSingleEventEnd(end, ((DDiagramElement) ise.getNotationView().getElement()).getTarget());
            }
        }
        return getOldPositionFromRange(see, ise);
    }

    private ISequenceEvent getSafeEvent(Collection<ISequenceEvent> ises) {
        ISequenceEvent ise = null;
        Predicate<Object> safe = Predicates.or(Predicates.instanceOf(AbstractNodeEvent.class), Predicates.instanceOf(AbstractFrame.class));
        Iterable<? extends ISequenceEvent> safeEvents = Iterables.filter(ises, safe);

        if (!Iterables.isEmpty(safeEvents)) {
            ise = safeEvents.iterator().next();
        } else if (Iterables.size(Iterables.filter(ises, Operand.class)) == 2) {
            ise = getSafeOperandEnd(ises);
        } else {
            ise = ises.iterator().next();
        }
        return ise;
    }

    private ISequenceEvent getSafeOperandEnd(Collection<ISequenceEvent> ises) {
        ISequenceEvent ise = null;

        Iterator<ISequenceEvent> iterator = ises.iterator();
        ISequenceEvent pot1 = iterator.next();
        ISequenceEvent pot2 = iterator.next();

        if (new ISequenceElementQuery(pot1).hasAbsoluteBoundsFlag()) {
            ise = pot1;
        } else if (new ISequenceElementQuery(pot2).hasAbsoluteBoundsFlag()) {
            ise = pot2;
        }
        return ise;
    }

    /**
     * Get the old position of the corresponding event end, regarding the given event old range. event.
     * 
     * @param see
     *            event end
     * @param ise
     *            corresponding event
     * @return old position
     */
    protected Integer getOldPositionFromRange(SingleEventEnd see, ISequenceEvent ise) {
        Integer oldPosition = 0;
        Option<Range> oldRange = ranges.apply(ise);

        if (ise != null && oldRange.some()) {
            if (see != null) {
                oldPosition = see.isStart() ? oldRange.get().getLowerBound() : oldRange.get().getUpperBound();
            } else if (see == null && ise.isLogicallyInstantaneous()) {
                oldPosition = oldRange.get().middleValue();
            }
        }
        return oldPosition;
    }
}
