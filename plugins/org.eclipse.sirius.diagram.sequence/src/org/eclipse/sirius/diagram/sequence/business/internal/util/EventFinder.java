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
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Helper to locate sequence events inside a given context event.
 * 
 * @author pcdavid
 */
public class EventFinder {
    private final ISequenceEvent context;

    private final Lifeline lifeline;

    private Range expansionZone;

    private Predicate<ISequenceEvent> eventsToIgnore;

    private boolean reconnect;

    private boolean reparent;

    private Function<ISequenceEvent, Range> verticalRangeFunction = new Function<ISequenceEvent, Range>() {
        @Override
        public Range apply(ISequenceEvent from) {
            return from.getVerticalRange();
        }
    };

    private Map<AbstractNodeEvent, ISequenceEvent> reparented = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param context
     *            the event in which to look for. Only sub-events of the context are considered in the search.
     */
    public EventFinder(Lifeline context) {
        this.context = context;
        this.lifeline = context;
    }

    /**
     * Constructor.
     * 
     * @param context
     *            the event in which to look for. Only sub-events of the context are considered in the search.
     */
    public EventFinder(AbstractNodeEvent context) {
        this.context = context;
        Preconditions.checkArgument(context.getLifeline().some());
        this.lifeline = context.getLifeline().get();
    }

    /**
     * Constructor.
     * 
     * @param context
     *            the event in which to look for. Only sub-events of the context are considered in the search.
     * 
     * @param lifeline
     *            the lifeline on which to look for an event.
     */
    public EventFinder(ISequenceEvent context, Lifeline lifeline) {
        this.context = context;
        this.lifeline = lifeline;
    }

    /**
     * Allow to use another way to compute the range of the context (iG range after move?..).
     * 
     * @param rangeFunction
     *            the range function to use to compute the context range.
     */
    public void setVerticalRangefunction(Function<ISequenceEvent, Range> rangeFunction) {
        if (verticalRangeFunction != null) {
            this.verticalRangeFunction = rangeFunction;
        }
    }

    public void setReconnection(boolean mode) {
        this.reconnect = mode;
    }

    public boolean isReconnection() {
        return reconnect;
    }

    public void setReparent(boolean mode) {
        this.reparent = mode;
    }

    public boolean isReparent() {
        return reparent;
    }

    public void setExpansionZone(Range expansionZone) {
        this.expansionZone = expansionZone;
    }

    public void setEventsToIgnore(Predicate<ISequenceEvent> eventsToIgnore) {
        this.eventsToIgnore = eventsToIgnore;
    }

    /**
     * Returns the deepest event in the hierarchy of sub-event starting from this finder's context which includes
     * completely the specified range.
     * 
     * @param range
     *            the range to look for.
     * @return the deepest event, starting from this finder's context, which includes the specified range, or
     *         <code>null</code>.
     */
    public ISequenceEvent findMostSpecificEvent(Range range) {
        if (context instanceof Message) {
            return null;
        }
        ISequenceEvent result = null;
        boolean contextIncludesRange = contextIncludesRange(range);
        if (contextIncludesRange) {
            if (context != null && !shouldIgnore().apply(context)) {
                boolean okForReconnection = !isReconnection() || !Message.NO_RECONNECTABLE_EVENTS.apply(context);
                boolean okForReparent = !isReparent() || !AbstractNodeEvent.NO_REPARENTABLE_EVENTS.apply(context);
                if (okForReconnection && okForReparent) {
                    result = context;
                }
            }
        }
        if (contextIncludesRange || isReparent()) {
            Predicate<ISequenceEvent> sameLifeline = new SameLifelinePredicate(lifeline);
            Predicate<ISequenceEvent> includeRange = new Predicate<ISequenceEvent>() {

                @Override
                public boolean apply(ISequenceEvent ise) {
                    return includeRange(ise, range);
                }

            };

            List<ISequenceEvent> eventsToInspect = new ArrayList<>();
            if ((reconnect || reparent) && (context instanceof AbstractNodeEvent || context instanceof Lifeline)) {
                for (View view : Iterables.filter(context.getNotationView().getChildren(), View.class)) {
                    Option<ISequenceEvent> ise = ISequenceElementAccessor.getISequenceEvent(view);
                    if (ise != null && ise.some() && !reparented.containsKey(ise.get())) {
                        eventsToInspect.add(ise.get());
                    }
                }

                // handle reparented events
                // look for new child
                for (Entry<AbstractNodeEvent, ISequenceEvent> entry : reparented.entrySet()) {
                    if (entry.getValue() == context) {
                        eventsToInspect.add(entry.getKey());
                    }
                }
            } else {
                eventsToInspect.addAll(context.getSubEvents());
            }

            for (ISequenceEvent child : Iterables.filter(eventsToInspect, Predicates.and(includeRange, Predicates.not(shouldIgnore()), sameLifeline))) {
                EventFinder childFinder = new EventFinder(child, lifeline);
                childFinder.setReconnection(isReconnection());
                childFinder.setReparent(isReparent());
                childFinder.setEventsToIgnore(eventsToIgnore);
                childFinder.setExpansionZone(expansionZone);
                childFinder.setVerticalRangefunction(verticalRangeFunction);
                childFinder.setReparented(reparented);
                ISequenceEvent moreSpecific = childFinder.findMostSpecificEvent(range);
                if (moreSpecific != null) {
                    result = moreSpecific;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Tests whether this finder's context element includes the specified range, considering the optional expansion
     * step.
     */
    private boolean contextIncludesRange(Range range) {
        return includeRange(context, range);
    }

    /**
     * Tests whether this finder's ise element includes the specified range, considering the optional expansion step.
     */
    private boolean includeRange(ISequenceEvent ise, Range range) {
        Range currentContextRange = verticalRangeFunction.apply(ise);
        return getRangeAfterExpansion(currentContextRange).includes(range);
    }

    /**
     * Returns a predicate which tests whether an element should be ignored in the search for descendants.
     */
    private Predicate<ISequenceEvent> shouldIgnore() {
        if (eventsToIgnore == null) {
            return Predicates.alwaysFalse();
        } else {
            return eventsToIgnore;
        }
    }

    private Range getRangeAfterExpansion(Range range) {
        if (expansionZone != null && range.includesAtLeastOneBound(expansionZone)) {
            return range.union(expansionZone);
        } else {
            return range;
        }
    }

    /**
     * Handle already reparented event.
     * 
     * @param newParents
     *            key is the reparented exec, value is the new parent.
     */
    public void setReparented(Map<AbstractNodeEvent, ISequenceEvent> newParents) {
        this.reparented.putAll(newParents);
    }
}
