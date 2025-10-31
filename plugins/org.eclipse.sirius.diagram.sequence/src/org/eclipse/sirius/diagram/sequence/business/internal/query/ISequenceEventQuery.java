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
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * General queries on {@link ISequenceEvent}s.
 * 
 * @author pcdavid
 */
public class ISequenceEventQuery {
    /**
     * The event to query.
     */
    protected final ISequenceEvent event;

    /**
     * Constructor.
     * 
     * @param event
     *            the event to query.
     */
    public ISequenceEventQuery(ISequenceEvent event) {
        this.event = Objects.requireNonNull(event);
    }

    /**
     * Tests whether this event is an ancestor of the specified child event.
     * 
     * @param child
     *            the potential descendant.
     * @return <code>true</code> if <em>this</em> event is identical to the child, the parent of the child or an
     *         indirect ancestor of the child.
     */
    public boolean isAncestorOrSelf(ISequenceEvent child) {
        ISequenceEvent iSequenceEvent = event;
        final boolean result;
        if (iSequenceEvent == null || child == null) {
            result = false;
        } else if (iSequenceEvent.equals(child)) {
            result = true;
        } else {
            ISequenceEvent parentEvent = child.getParentEvent();
            if (iSequenceEvent.equals(parentEvent)) {
                result = true;
            } else {
                result = parentEvent != null && isAncestorOrSelf(parentEvent);
            }
        }
        return result;
    }

    /**
     * Tests whether this event is a reflective message.
     * 
     * @return <code>true</code> if this event is a reflective message.
     */
    public boolean isReflectiveMessage() {
        return event instanceof Message message && message.isReflective();
    }

    public boolean isObliqueMessage() {
        return event instanceof Message message && message.isOblique();
    }

    /**
     * Computes all the descendants of the specified execution, i.e. the recursive transitive closure on getSubEvents().
     * 
     * The current ise is not included.
     * 
     * @return all the proper descendant events of the given sequence event.
     */
    public Set<ISequenceEvent> getAllDescendants() {
        return getAllDescendants(false, ISequenceEvent.ISEQUENCEEVENT_NOTATION_PREDICATE);
    }

    /**
     * Computes all the descendants of the specified execution, i.e. the recursive transitive closure on getSubEvents().
     * 
     * @param includeSelf
     *            whether or not to consider "self" as a descendant.
     * @return all the proper descendant events of the given sequence event.
     */
    public Set<ISequenceEvent> getAllDescendants(boolean includeSelf) {
        return getAllDescendants(includeSelf, ISequenceEvent.ISEQUENCEEVENT_NOTATION_PREDICATE);
    }

    /**
     * Computes all the descendants of the specified execution, i.e. the recursive transitive closure on getSubEvents().
     * 
     * @param includeSelf
     *            whether or not to consider "self" as a descendant.
     * @param predicate
     *            the predicate to select which descendants to include in the collection.
     * @return all the proper descendant events of the given execution.
     */
    public Set<ISequenceEvent> getAllDescendants(boolean includeSelf, Predicate<? super View> predicate) {
        Set<ISequenceEvent> result = new HashSet<>();
        addAllDescendants(predicate, result);
        if (!includeSelf) {
            result.remove(event);
        }
        return result;
    }

    /**
     * Adds all the descendants of the specified edit part which verify the predicate into a collection. Only children
     * edit parts are considered, not source and target connections.
     * 
     * @param predicate
     *            the predicate to select which descendants to include in the collection.
     * @param parts
     *            the collection in which to add all the descendants of <code>element</code> which verify the predicate.
     */
    private void addAllDescendants(Predicate<? super View> predicate, Collection<ISequenceEvent> parts) {
        addAllDescendants(event, predicate, parts);
    }

    private void addAllDescendants(ISequenceEvent ise, Predicate<? super View> predicate, Collection<ISequenceEvent> parts) {
        View element = ise.getNotationView();
        if (predicate.apply(element)) {
            Option<ISequenceEvent> iSequenceEvent = ISequenceElementAccessor.getISequenceEvent(element);
            if (iSequenceEvent.some()) {
                parts.add(iSequenceEvent.get());
            }
        }
        for (ISequenceEvent childEvent : ise.getSubEvents()) {
            addAllDescendants(childEvent, predicate, parts);
        }
    }

    /**
     * Finds all the executions without duplicates.
     * 
     * @return the found executions found.
     */
    public Collection<Execution> getAllExecutions() {
        return Lists.newArrayList(Iterables.filter(getAllDescendants(true, Execution.notationPredicate()), Execution.class));
    }

    /**
     * Finds all the sequence messages whose source or target is the specified element or any of its descendant edit
     * parts, without duplicates.
     * 
     * @return the messages found without duplicates.
     */
    public Set<Message> getAllMessages() {
        Set<Message> allMessages = new HashSet<>();
        allMessages.addAll(getAllMessagesFrom());
        allMessages.addAll(getAllMessagesTo());
        return allMessages;
    }

    /**
     * Finds all the sequence messages whose source is the specified element or any of its descendant edit parts.
     * 
     * @return the messages found.
     */
    public List<Message> getAllMessagesFrom() {
        List<Message> messagesParts = new ArrayList<>();
        addAllMessagesFrom(event.getNotationView(), messagesParts);
        return messagesParts;
    }

    /**
     * Finds all the sequence messages whose target is the specified element or any of its descendant edit parts.
     * 
     * @return the messages found.
     */
    public List<Message> getAllMessagesTo() {
        List<Message> messagesParts = new ArrayList<>();
        addAllMessagesTo(event.getNotationView(), messagesParts);
        return messagesParts;
    }

    /**
     * Finds all the sequence messages whose target is the specified element or any of its descendant edit parts and add
     * them to a collection.
     * 
     * @param element
     *            the element from which to start the search for messages.
     * @param messages
     *            the collection in which to add the messages.
     */
    private void addAllMessagesTo(View element, Collection<Message> messages) {
        for (Edge connectionPart : Iterables.filter(Iterables.filter(element.getTargetEdges(), Edge.class), Message.notationPredicate())) {
            Option<Message> message = ISequenceElementAccessor.getMessage(connectionPart);
            if (message.some()) {
                messages.add(message.get());
            }
        }
        if (element instanceof Message) {
            messages.add((Message) element);
        }
        for (View child : Iterables.filter(element.getChildren(), Node.class)) {
            addAllMessagesFrom(child, messages);
        }
    }

    /**
     * Finds all the sequence messages whose source is the specified element or any of its descendant edit parts and add
     * them to a collection.
     * 
     * @param element
     *            the element from which to start the search for messages.
     * @param messages
     *            the collection in which to add the messages.
     */
    private void addAllMessagesFrom(View element, Collection<Message> messages) {
        for (Edge connectionPart : Iterables.filter(Iterables.filter(element.getSourceEdges(), Edge.class), Message.notationPredicate())) {
            Option<Message> message = ISequenceElementAccessor.getMessage(connectionPart);
            if (message.some()) {
                messages.add(message.get());
            }
        }
        if (element instanceof Message) {
            messages.add((Message) element);
        }
        for (View child : Iterables.filter(element.getChildren(), Node.class)) {
            addAllMessagesFrom(child, messages);
        }
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#getOccupiedRange()}.
     * 
     * @return the maximal range occupied by children of the event.
     */
    public Range getOccupiedRange() {
        Range range = Range.emptyRange();
        for (ISequenceEvent child : event.getSubEvents()) {
            Range childRange = child.getVerticalRange();
            if (child instanceof Message msg && msg.isOblique() && !msg.isReflective()) {
                // Oblique Message represent asynchronous concept, do not include "the other end".
                // This currently forbids to remove this child by resizing an execution, to keep consistency with non-oblique message)
                if (msg.getSourceElement() == this.event) {
                    childRange = new Range(childRange.getLowerBound(), childRange.getLowerBound());
                } else if (msg.getTargetElement() == this.event) {
                    childRange = new Range(childRange.getUpperBound(), childRange.getUpperBound());
                }
            }

            range = range.union(childRange);
        }
        return range;
    }

    /**
     * Return all moved elements from the context.
     * 
     * @return the all moved elements.
     */
    public Set<ISequenceEvent> getAllSequenceEventToMoveWith() {
        Set<ISequenceEvent> entryPoints = new LinkedHashSet<>();
        return getAllSequenceEventToMoveWith(entryPoints);
    }

    /**
     * Return all moved elements from the context.
     * 
     * @param additionnalEntryPoints
     *            additionnal entry points.
     * @return the all moved elements.
     */
    public Set<ISequenceEvent> getAllSequenceEventToMoveWith(Collection<ISequenceEvent> additionnalEntryPoints) {
        Set<ISequenceEvent> entryPoints = new LinkedHashSet<>();
        entryPoints.add(event);
        entryPoints.addAll(additionnalEntryPoints);

        Set<ISequenceEvent> moved = new LinkedHashSet<>();
        for (ISequenceEvent ise : entryPoints) {
            populateMovedElements(ise, moved);
        }
        return moved;
    }

    private void populateMovedElements(ISequenceEvent inspectedElement, Collection<ISequenceEvent> moved) {
        moved.add(inspectedElement);
        for (ISequenceEvent subEvent : Iterables.filter(inspectedElement.getEventsToMoveWith(), Predicates.not(Predicates.in(moved)))) {
            populateMovedElements(subEvent, moved);
        }

    }
}
