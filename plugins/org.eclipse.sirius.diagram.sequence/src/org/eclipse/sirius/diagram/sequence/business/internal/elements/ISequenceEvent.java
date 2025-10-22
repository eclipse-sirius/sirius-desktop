/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.Collection;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Common interface for all the elements of a sequence diagram which represent an event associated to a (logical) time
 * interval and thus a range of vertical coordinates. This includes lifelines (considered as a special case of
 * executions), executions and messages.
 * 
 * @author mporhel
 */
public interface ISequenceEvent extends ISequenceElement {

    /**
     * Predicate to test all notation predicate of existing sequence events.
     */
    @SuppressWarnings("unchecked")
    Predicate<View> ISEQUENCEEVENT_NOTATION_PREDICATE = Predicates.or(AbstractNodeEvent.notationPredicate(), Message.notationPredicate(), InteractionUse.notationPredicate(),
            CombinedFragment.notationPredicate(), Operand.notationPredicate());

    /**
     * A function to compute the vertical range a sequence event.
     */
    Function<ISequenceEvent, Range> VERTICAL_RANGE = new Function<ISequenceEvent, Range>() {
        @Override
        public Range apply(ISequenceEvent from) {
            return from.getVerticalRange();
        }
    };

    /**
     * Tests whether this event should be considered logically to be instantaneous. Depending on its graphical
     * representation, it may still cover a significant vertical space.
     * 
     * @return <code>true</code> if this event should be considered instantaneous.
     */
    boolean isLogicallyInstantaneous();

    /**
     * Returns the vertical range of coordinates this event covers. The coordinates are normalized y coordinates
     * (relative to the origin of the logical plane, whatever the scroll state is, and independent of the zoom level).
     * 
     * @return the vertical range of coordinates this event covers.
     */
    Range getVerticalRange();

    /**
     * Set the vertical range of this sequence event.
     * 
     * @param range
     *            the new vertical range.
     * @throws IllegalStateException
     *             if range is not valid.
     */
    void setVerticalRange(Range range) throws IllegalStateException;

    /**
     * Returns the parent event of this event (from a business point of view), if any. Returns <code>null</code> for
     * top-level events, i.e. lifelines.
     * 
     * @return the parent event of this event, if any.
     */
    ISequenceEvent getParentEvent();

    /**
     * Returns the hierarchical parent event of this event (from a Notation point of view), if any. Returns
     * <code>null</code> for top-level events i.e. lifelines / frames / messages.
     * 
     * @return the hierarchical parent event of this event, if any.
     */
    ISequenceEvent getHierarchicalParentEvent();

    /**
     * Returns the list of direct sub-events of this event, in chronological (and thus also graphical) order. This
     * includes both events which are directly owned by this event (e.g. the messages sent by an execution) and events
     * not owned but connected to this event (e.g. the messages received by an execution).
     * 
     * @return the list of direct sub-events of this event, in chronological order.
     */
    List<ISequenceEvent> getSubEvents();

    /**
     * Returns the vertical range of coordinates inside which direct sub-events of this event can be. The coordinates
     * are normalized y coordinates. The range returned is guaranteed to be a sub-range of {@link #getVerticalRange()}
     * or the empty range for events which can not have children.
     * 
     * @return the vertical range in
     */
    Range getValidSubEventsRange();

    /**
     * Tests whether a given child can be placed anywhere in the specified vertical range.
     * 
     * @param child
     *            the child.
     * @param range
     *            the vertical range to test.
     * @return <code>true</code> if the child can be placed anywhere inside the specified vertical range (including
     *         occupying the whole range).
     */
    boolean canChildOccupy(ISequenceEvent child, Range range);

    /**
     * Tests whether a given child can be placed anywhere in the specified vertical range.
     * 
     * @param child
     *            the child.
     * @param range
     *            the vertical range to test.
     * @param eventsToIgnore
     *            the list of events to ignore while computing canChildOccupy.
     * @param lifelines
     *            lifelines to inspect.
     * @return <code>true</code> if the child can be placed anywhere inside the specified vertical range (including
     *         occupying the whole range).
     */
    boolean canChildOccupy(ISequenceEvent child, Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines);

    /**
     * Calculate the maximal occupied range of this event.
     * 
     * @return the maximal range occupied by children of this event, from the beginning of the first sub-event to the
     *         end of the last one.
     */
    Range getOccupiedRange();

    /**
     * Finds the deepest Operand container if existing.
     * 
     * @return the deepest Operand container if existing.
     */
    Option<Operand> getParentOperand();

    /**
     * Elements whcih should move with current events.
     * 
     * @return a collection of elements moved with the current event.
     */
    Collection<ISequenceEvent> getEventsToMoveWith();
}
