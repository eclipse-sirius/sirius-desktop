/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.emf.AllContents;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.collect.Streams;

/**
 * Represents a sequence diagram. This is the root of all sequence elements.
 * 
 * @author mporhel, pcdavid
 */
public class SequenceDiagram extends AbstractSequenceElement {

    /**
     * Predicate to check whether a GMF View represents a sequence diagram.
     */
    private enum NotationPredicate implements Predicate<View> {
        INSTANCE;

        @Override
        public boolean apply(View input) {
            if (input instanceof Diagram) {
                EObject element = input.getElement();
                return element instanceof DDiagram && SequenceDiagram.viewpointElementPredicate().apply((DDiagram) element);
            } else {
                return false;
            }
        }

    }

    /**
     * Predicate to check whether a Sirius DDiagram represents a sequence diagram.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagram> {
        INSTANCE;

        @Override
        public boolean apply(DDiagram input) {
            if (input == null) {
                return false;
            } else {
                EClass sdDescClass = DescriptionPackage.eINSTANCE.getSequenceDiagramDescription();
                return input instanceof SequenceDDiagram && sdDescClass.isInstance(input.getDescription());
            }
        }
    }

    /**
     * Indicate if this class should use cache or not. Use {@link #useCache(boolean))} to enable/disable this mode and {
     * {@link #clearAllCaches()} to clear caches.
     */
    private boolean useCache;

    private List<AbstractNodeEvent> allAbstractNodeEventsCache;

    private List<CombinedFragment> allCombinedFragmentsCache;

    private Set<EndOfLife> allEndOfLifesCache;

    private List<Execution> allExecutionsCache;

    private List<AbstractFrame> allFramesCache;

    private Collection<InstanceRole> allInstanceRolesCache;

    private List<InteractionUse> allInteractionUsesCache;

    private List<Lifeline> allLifelinesCache;

    private Collection<LostMessageEnd> allLostMessageEndCache;

    private Collection<Gate> allGatesCache;

    private List<Message> allMessagesCache;

    private Collection<ObservationPoint> allObservationPointsCache;

    private List<Operand> allOperandsCache;

    private List<State> allStatesCache;

    private LinkedHashSet<AbstractNodeEvent> allOrderedAbstractNodeEventsCache;

    private LinkedHashSet<CombinedFragment> allOrderedCombinedFragmentsCache;

    private Set<ISequenceEvent> allOrderedDelimitedSequenceEventsCache;

    private LinkedHashSet<Execution> allOrderedExecutionsCache;

    private LinkedHashSet<AbstractFrame> allOrderedFramesCache;

    private LinkedHashSet<InteractionUse> allOrderedInteractionUsesCache;

    private List<Lifeline> allOrderedLifelinesCache;

    private LinkedHashSet<Message> allOrderedMessagesCache;

    private LinkedHashSet<Operand> allOrderedOperandsCache;

    private LinkedHashSet<State> allOrderedStatesCache;

    private List<ISequenceEvent> allDelimitedEventsCache;

    private Optional<InteractionContainer> interactionContainerCache;

    /**
     * Constructor.
     * 
     * @param diagram
     *            the GMF Diagram representing this sequence diagram.
     */
    SequenceDiagram(Diagram diagram) {
        super(diagram);
        Preconditions.checkArgument(SequenceDiagram.notationPredicate().apply(diagram), Messages.SequenceDiagram_nonSequenceDiagramDiagram);
    }

    /**
     * Returns a predicate to check whether a GMF View represents a sequence diagram.
     * 
     * @return a predicate to check whether a GMF View represents a sequence diagram.
     */
    public static Predicate<View> notationPredicate() {
        return NotationPredicate.INSTANCE;
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagram represents a sequence diagram.
     * 
     * @return a predicate to check whether a Sirius DDiagram represents a sequence diagram.
     */
    public static Predicate<DDiagram> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    public Diagram getNotationDiagram() {
        return (Diagram) view;
    }

    public SequenceDDiagram getSequenceDDiagram() {
        return (SequenceDDiagram) view.getElement();
    }

    /**
     * Finds all the lifelines in this diagram which are at least partially covered by the specified rectangular area.
     * 
     * @param area
     *            the rectangular area to check for lifelines (in logical coordinates).
     * @return all the lifelines in this diagram which are at least partially covered by the area.
     */
    public Set<Lifeline> getGraphicallyCoveredLifelines(final Rectangle area) {
        List<Lifeline> result = new ArrayList<>();
        Iterables.addAll(result, Iterables.filter(getAllLifelines(), new Predicate<Lifeline>() {
            @Override
            public boolean apply(Lifeline input) {
                return input.getProperLogicalBounds().intersects(area) && input.getVerticalRange().includes(area.getTop().y);
            }
        }));
        Collections.sort(result, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * .
     * 
     * @return .
     */
    public List<InstanceRole> getSortedInstanceRole() {
        Function<InstanceRole, Integer> xLocation = new Function<InstanceRole, Integer>() {
            @Override
            public Integer apply(InstanceRole from) {
                Rectangle bounds = from.getBounds();
                return bounds.x;
            }
        };

        List<InstanceRole> allInstanceRoles = new ArrayList<InstanceRole>(getAllInstanceRoles());
        Collections.sort(allInstanceRoles, Ordering.natural().onResultOf(xLocation));
        return allInstanceRoles;
    }

    /**
     * .
     * 
     * @return .
     */
    public Collection<InstanceRole> getAllInstanceRoles() {
        Collection<InstanceRole> allInstanceRoles = null;
        if (useCache) {
            // Initialize from cache
            allInstanceRoles = allInstanceRolesCache;
        }
        if (allInstanceRoles == null) {
            allInstanceRoles = new ArrayList<>();
            for (View child : Iterables.filter(getNotationView().getChildren(), View.class)) {
                if (InstanceRole.notationPredicate().apply(child)) {
                    Option<InstanceRole> instanceRole = ISequenceElementAccessor.getInstanceRole(child);
                    if (instanceRole.some()) {
                        allInstanceRoles.add(instanceRole.get());
                    }
                }
            }
            if (useCache) {
                // Store the result
                allInstanceRolesCache = allInstanceRoles;
            }
        }
        return allInstanceRoles;
    }

    /**
     * .
     * 
     * @return .
     */
    public List<Lifeline> getAllLifelines() {
        List<Lifeline> allLifelines = null;
        if (useCache) {
            // Initialize from cache
            if (allLifelinesCache != null) {
                allLifelines = new ArrayList<Lifeline>(allLifelinesCache);
            }
        }
        if (allOrderedLifelinesCache == null) {
            if (allLifelines == null) {
                allLifelines = new ArrayList<>();
                Collection<InstanceRole> allInstanceRoles = getAllInstanceRoles();
                Function<ISequenceNode, Lifeline> lifelineFunction = new Function<ISequenceNode, Lifeline>() {
                    @Override
                    public Lifeline apply(ISequenceNode from) {
                        return from.getLifeline().get();
                    }
                };
                allLifelines = new ArrayList<Lifeline>();
                Iterables.addAll(allLifelines, Iterables.transform(allInstanceRoles, lifelineFunction));
                if (useCache) {
                    // Store the result
                    allLifelinesCache = allLifelines;
                }
            }
            Collections.sort(allLifelines, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedLifelinesCache = new ArrayList<Lifeline>(allLifelines);
        }
        return allOrderedLifelinesCache;
    }

    /**
     * Returns all the {@link Node}s in the specified diagram which represent an ObservationPoint.
     * 
     * @return the Nodes inside this diagram which represent sequence ObservationPoint. An empty iterator is returned if
     *         the diagram is not a sequence diagram.
     */
    public Collection<ObservationPoint> getAllObservationPoints() {
        Collection<ObservationPoint> allObservationPoints = null;
        if (useCache) {
            // Initialize from cache
            allObservationPoints = allObservationPointsCache;
        }
        if (allObservationPoints == null) {
            allObservationPoints = new ArrayList<>();
            for (View child : Iterables.filter(getNotationView().getChildren(), View.class)) {
                if (ObservationPoint.notationPredicate().apply(child)) {
                    Option<ObservationPoint> obsPoint = ISequenceElementAccessor.getObservationPoint(child);
                    if (obsPoint.some()) {
                        allObservationPoints.add(obsPoint.get());
                    }
                }
            }
            if (useCache) {
                // Store the result
                allObservationPointsCache = allObservationPoints;
            }
        }
        return allObservationPoints;
    }

    /**
     * Returns all the {@link Node}s in the specified diagram which represent a lost sequence message end.
     * 
     * @return the Nodes inside this diagram which represent lost sequence messages end. An empty iterator is returned
     *         if the diagram is not a sequence diagram.
     */
    public Collection<LostMessageEnd> getAllLostMessageEnds() {
        Collection<LostMessageEnd> allLostMessageEnd = null;
        if (useCache) {
            // Initialize from cache
            allLostMessageEnd = allLostMessageEndCache;
        }
        if (allLostMessageEnd == null) {
            allLostMessageEnd = new ArrayList<>();
            for (View child : Iterables.filter(getNotationView().getChildren(), View.class)) {
                if (LostMessageEnd.notationPredicate().apply(child)) {
                    Option<LostMessageEnd> lostMessageEnd = ISequenceElementAccessor.getLostMessageEnd(child);
                    if (lostMessageEnd.some()) {
                        allLostMessageEnd.add(lostMessageEnd.get());
                    }
                }
            }
            if (useCache) {
                // Store the result
                allLostMessageEndCache = allLostMessageEnd;
            }
        }
        return allLostMessageEnd;
    }

    /**
     * Returns all the {@link Node}s in the specified diagram which represent a lost sequence message end.
     * 
     * @return the Nodes inside this diagram which represent lost sequence messages end. An empty iterator is returned
     *         if the diagram is not a sequence diagram.
     */
    public Collection<Gate> getAllGates() {
        Collection<Gate> allGates = null;
        if (useCache) {
            // Initialize from cache
            allGates = allGatesCache;
        }
        if (allGates == null) {
            Collection<Gate> gates = new ArrayList<>();

            Stream<? extends AbstractFrame> gateParents = Streams.concat(getAllCombinedFragments().stream(), getAllInteractionUses().stream());
            gateParents.forEach(parent -> {
                gates.addAll(parent.getGates());
            });
            Optional<InteractionContainer> interactionContainer = getInteractionContainer();
            if (interactionContainer.isPresent()) {
                gates.addAll(interactionContainer.get().getGates());
            }

            allGates = gates;
        }
        if (useCache) {
            // Store the result
            allGatesCache = allGates;
        }
        return allGates;

    }

    /**
     * Returns all the {@link Edge}s in the specified diagram which represent a sequence message of any kind.
     * 
     * @return the Edges inside this diagram which represent sequence messages. An empty iterator is returned if the
     *         diagram is not a sequence diagram.
     */
    public Set<Message> getAllMessages() {
        List<Message> allMessages = null;
        LinkedHashSet<Message> allOrderedMessages = null;
        if (useCache) {
            // Initialize from cache
            if (allMessagesCache != null) {
                allMessages = new ArrayList<Message>(allMessagesCache);
            }
            allOrderedMessages = allOrderedMessagesCache;
        }
        if (allOrderedMessages == null) {
            if (allMessages == null) {
                allMessages = new ArrayList<>();
                for (Edge edge : Iterables.filter(Iterables.filter(getNotationDiagram().getEdges(), Edge.class), Message.notationPredicate())) {
                    Option<Message> message = ISequenceElementAccessor.getMessage(edge);
                    assert message.some() : Messages.SequenceDiagram_InternalError;
                    allMessages.add(message.get());
                }
                if (useCache) {
                    // Store the result
                    allMessagesCache = allMessages;
                }
            }
            Collections.sort(allMessages, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedMessages = Sets.newLinkedHashSet(allMessages);
            if (useCache) {
                // Store the result
                allOrderedMessagesCache = allOrderedMessages;
            }
        }
        return allOrderedMessages;
    }

    /**
     * Returns all AbstractNodeEvent in the given diagram.
     * 
     * @return all AbstractNodeEvent on the given diagram.
     */
    public Set<AbstractNodeEvent> getAllAbstractNodeEvents() {
        List<AbstractNodeEvent> allAbstractNodeEvents = null;
        LinkedHashSet<AbstractNodeEvent> allOrderedAbstractNodeEvents = null;
        if (useCache) {
            // Initialize from cache
            if (allAbstractNodeEventsCache != null) {
                allAbstractNodeEvents = new ArrayList<AbstractNodeEvent>(allAbstractNodeEventsCache);
            }
            allOrderedAbstractNodeEvents = allOrderedAbstractNodeEventsCache;
        }
        if (allOrderedAbstractNodeEvents == null) {
            if (allAbstractNodeEvents == null) {
                allAbstractNodeEvents = new ArrayList<>();
                for (Node node : Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), Node.class), AbstractNodeEvent.notationPredicate())) {
                    Option<AbstractNodeEvent> exec = ISequenceElementAccessor.getAbstractNodeEvent(node);
                    assert exec.some() : Messages.SequenceDiagram_InternalError;
                    allAbstractNodeEvents.add(exec.get());
                }
                if (useCache) {
                    // Store the result
                    allAbstractNodeEventsCache = allAbstractNodeEvents;
                }
            }
            Collections.sort(allAbstractNodeEvents, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedAbstractNodeEvents = Sets.newLinkedHashSet(allAbstractNodeEvents);
            if (useCache) {
                // Store the result
                allOrderedAbstractNodeEventsCache = allOrderedAbstractNodeEvents;
            }
        }
        return allOrderedAbstractNodeEvents;
    }

    /**
     * Returns all executions in the given diagram.
     * 
     * @return all executions on the given diagram.
     */
    public Set<Execution> getAllExecutions() {
        List<Execution> allExecutions = null;
        LinkedHashSet<Execution> allOrderedExecutions = null;
        if (useCache) {
            // Initialize from cache
            if (allExecutionsCache != null) {
                allExecutions = new ArrayList<Execution>(allExecutionsCache);
            }
            allOrderedExecutions = allOrderedExecutionsCache;
        }
        if (allOrderedExecutions == null) {
            if (allExecutions == null) {
                allExecutions = new ArrayList<>();
                for (Node node : Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), Node.class), Execution.notationPredicate())) {
                    Option<Execution> exec = ISequenceElementAccessor.getExecution(node);
                    assert exec.some() : Messages.SequenceDiagram_InternalError;
                    allExecutions.add(exec.get());
                }
                if (useCache) {
                    // Store the result
                    allExecutionsCache = allExecutions;
                }
            }
            Collections.sort(allExecutions, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedExecutions = Sets.newLinkedHashSet(allExecutions);
            if (useCache) {
                // Store the result
                allOrderedExecutionsCache = allOrderedExecutions;
            }
        }
        return allOrderedExecutions;
    }

    /**
     * Returns all executions in the given diagram.
     * 
     * @return all executions on the given diagram.
     */
    public Set<State> getAllStates() {
        List<State> allStates = null;
        LinkedHashSet<State> allOrderedStates = null;
        if (useCache) {
            // Initialize from cache
            if (allStatesCache != null) {
                allStates = new ArrayList<State>(allStatesCache);
            }
            allOrderedStates = allOrderedStatesCache;
        }
        if (allOrderedStates == null) {
            if (allStates == null) {
                allStates = new ArrayList<>();
                for (Node node : Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), Node.class), State.notationPredicate())) {
                    Option<State> exec = ISequenceElementAccessor.getState(node);
                    assert exec.some() : Messages.SequenceDiagram_InternalError;
                    allStates.add(exec.get());
                }
                if (useCache) {
                    // Store the result
                    allStatesCache = allStates;
                }
            }
            Collections.sort(allStates, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedStates = Sets.newLinkedHashSet(allStates);
            if (useCache) {
                // Store the result
                allOrderedStatesCache = allOrderedStates;
            }
        }
        return allOrderedStates;
    }

    /**
     * Returns all frames in the given diagram.
     * 
     * @return all frames on the given diagram.
     */
    public Collection<AbstractFrame> getAllFrames() {
        List<AbstractFrame> allFrames = null;
        LinkedHashSet<AbstractFrame> allOrderedFrames = null;
        if (useCache) {
            // Initialize from cache
            if (allFramesCache != null) {
                allFrames = new ArrayList<AbstractFrame>(allFramesCache);
            }
            allOrderedFrames = allOrderedFramesCache;
        }
        if (allOrderedFrames == null) {
            if (allFrames == null) {
                allFrames = new ArrayList<>();
                for (Node node : Iterables.filter(Iterables.filter(getNotationDiagram().getChildren(), Node.class), AbstractFrame.notationPredicate())) {
                    Option<ISequenceEvent> exec = ISequenceElementAccessor.getISequenceEvent(node);
                    assert exec.some() : Messages.SequenceDiagram_InternalError;
                    if (exec.get() instanceof AbstractFrame) {
                        allFrames.add((AbstractFrame) exec.get());
                    }
                }
                if (useCache) {
                    // Store the result
                    allFramesCache = allFrames;
                }
            }
            Collections.sort(allFrames, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedFrames = Sets.newLinkedHashSet(allFrames);
            if (useCache) {
                // Store the result
                allOrderedFramesCache = allOrderedFrames;
            }
        }
        return allOrderedFrames;
    }

    /**
     * Returns all interaction uses in the given diagram.
     * 
     * @return all interaction uses on the given diagram.
     */
    public Set<InteractionUse> getAllInteractionUses() {
        List<InteractionUse> allInteractionUses = null;
        LinkedHashSet<InteractionUse> allOrderedInteractionUses = null;
        if (useCache) {
            // Initialize from cache
            if (allInteractionUsesCache != null) {
                allInteractionUses = new ArrayList<InteractionUse>(allInteractionUsesCache);
            }
            allOrderedInteractionUses = allOrderedInteractionUsesCache;
        }
        if (allOrderedInteractionUses == null) {
            if (allInteractionUses == null) {
                allInteractionUses = new ArrayList<>();
                for (Node node : Iterables.filter(Iterables.filter(getNotationDiagram().getChildren(), Node.class), InteractionUse.notationPredicate())) {
                    Option<InteractionUse> exec = ISequenceElementAccessor.getInteractionUse(node);
                    assert exec.some() : Messages.SequenceDiagram_InternalError;
                    allInteractionUses.add(exec.get());
                }
                if (useCache) {
                    // Store the result
                    allInteractionUsesCache = allInteractionUses;
                }
            }
            Collections.sort(allInteractionUses, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedInteractionUses = Sets.newLinkedHashSet(allInteractionUses);
            if (useCache) {
                // Store the result
                allOrderedInteractionUsesCache = allOrderedInteractionUses;
            }
        }
        return allOrderedInteractionUses;
    }

    /**
     * Returns all combined fragments in the given diagram.
     * 
     * @return all combined fragments on the given diagram.
     */
    public Set<CombinedFragment> getAllCombinedFragments() {
        List<CombinedFragment> allCombinedFragments = null;
        LinkedHashSet<CombinedFragment> allOrderedCombinedFragments = null;
        if (useCache) {
            // Initialize from cache
            if (allCombinedFragmentsCache != null) {
                allCombinedFragments = new ArrayList<CombinedFragment>(allCombinedFragmentsCache);
            }
            allOrderedCombinedFragments = allOrderedCombinedFragmentsCache;
        }
        if (allOrderedCombinedFragments == null) {
            if (allCombinedFragments == null) {
                allCombinedFragments = new ArrayList<>();
                for (Node node : Iterables.filter(Iterables.filter(getNotationDiagram().getChildren(), Node.class), CombinedFragment.notationPredicate())) {
                    Option<CombinedFragment> exec = ISequenceElementAccessor.getCombinedFragment(node);
                    assert exec.some() : Messages.SequenceDiagram_InternalError;
                    allCombinedFragments.add(exec.get());
                }
                if (useCache) {
                    // Store the result
                    allCombinedFragmentsCache = allCombinedFragments;
                }
            }
            Collections.sort(allCombinedFragments, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedCombinedFragments = Sets.newLinkedHashSet(allCombinedFragments);
            if (useCache) {
                // Store the result
                allOrderedCombinedFragmentsCache = allOrderedCombinedFragments;
            }
        }
        return allOrderedCombinedFragments;
    }

    /**
     * Returns all operands in the given diagram.
     * 
     * @return all operands on the given diagram.
     */
    public Set<Operand> getAllOperands() {
        List<Operand> allOperands = null;
        LinkedHashSet<Operand> allOrderedOperands = null;
        if (useCache) {
            // Initialize from cache
            if (allOperandsCache != null) {
                allOperands = new ArrayList<Operand>(allOperandsCache);
            }
            allOrderedOperands = allOrderedOperandsCache;
        }
        if (allOrderedOperands == null) {
            if (allOperands == null) {
                allOperands = new ArrayList<>();
                for (Node node : Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), Node.class), Operand.notationPredicate())) {
                    Option<Operand> exec = ISequenceElementAccessor.getOperand(node);
                    assert exec.some() : Messages.SequenceDiagram_InternalError;
                    allOperands.add(exec.get());
                }
                if (useCache) {
                    // Store the result
                    allOperandsCache = allOperands;
                }
            }
            Collections.sort(allOperands, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedOperands = Sets.newLinkedHashSet(allOperands);
            if (useCache) {
                // Store the result
                allOrderedOperandsCache = allOrderedOperands;
            }
        }
        return allOrderedOperands;
    }

    /**
     * Returns all endOfLifes in the given diagram.
     * 
     * @return all endOfLifes on the given diagram.
     */
    public Set<EndOfLife> getAllEndOfLifes() {
        Set<EndOfLife> allEndOfLifes = null;
        if (useCache) {
            // Initialize from cache
            allEndOfLifes = allEndOfLifesCache;
        }
        if (allEndOfLifes == null) {
            allEndOfLifes = new HashSet<EndOfLife>();
            for (Lifeline lifeline : getAllLifelines()) {
                if (lifeline.getEndOfLife().some()) {
                    allEndOfLifes.add(lifeline.getEndOfLife().get());
                }
            }
            if (useCache) {
                // Store the result
                allEndOfLifesCache = allEndOfLifes;
            }
        }
        return allEndOfLifes;
    }

    /**
     * Returns all sequence events in the given diagram. The result is ordered regarding the lower bound ordering.
     * 
     * @return all sequence events on the given diagram.
     */
    public Set<ISequenceEvent> getAllOrderedDelimitedSequenceEvents() {
        Set<ISequenceEvent> allOrderedDelimitedSequenceEvents = null;
        if (useCache) {
            // Initialize from cache
            allOrderedDelimitedSequenceEvents = allOrderedDelimitedSequenceEventsCache;
        }
        if (allOrderedDelimitedSequenceEvents == null) {
            List<ISequenceEvent> result = new ArrayList<>();
            Iterables.addAll(result, getAllDelimitedSequenceEvents());

            Collections.sort(result, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            allOrderedDelimitedSequenceEvents = Sets.newLinkedHashSet(result);
            if (useCache) {
                // Store the result
                allOrderedDelimitedSequenceEventsCache = allOrderedDelimitedSequenceEvents;
            }
        }
        return allOrderedDelimitedSequenceEvents;
    }

    /**
     * Returns all sequence events in the given diagram. The result is not ordered and will be computed on iteration.
     * 
     * @return all sequence events on the given diagram.
     */
    public Iterable<? extends ISequenceEvent> getAllDelimitedSequenceEvents() {
        Iterable<? extends ISequenceEvent> allDelimitedEvents = null;
        if (useCache) {
            // Initialize from cache
            allDelimitedEvents = allDelimitedEventsCache;
        }

        if (allDelimitedEvents == null) {
            Function<View, ? extends ISequenceEvent> getISE = new Function<View, ISequenceEvent>() {
                @Override
                public ISequenceEvent apply(View from) {
                    Option<ISequenceEvent> ise = ISequenceElementAccessor.getISequenceEvent(from);
                    assert ise.some() : Messages.SequenceDiagram_InternalError;
                    return ise.get();
                }
            };
            allDelimitedEvents = Iterables.transform(Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), View.class), ISequenceEvent.ISEQUENCEEVENT_NOTATION_PREDICATE), getISE);
            if (useCache) {
                // Store the result
                List<ISequenceEvent> result = new ArrayList<>();
                Iterables.addAll(result, allDelimitedEvents);

                allDelimitedEventsCache = result;
                allDelimitedEvents = allDelimitedEventsCache;
            }
        }
        return allDelimitedEvents;
    }

    /**
     * Returns all Interaction containers in the given diagram.
     * 
     * @return all Interaction containers on the given diagram.
     */
    public Optional<InteractionContainer> getInteractionContainer() {
        Optional<InteractionContainer> optionalInteractionContainer = null;
        if (useCache) {
            // Initialize from cache
            optionalInteractionContainer = interactionContainerCache;
        }
        if (interactionContainerCache == null) {
            Iterator<Node> interactionContainerIterator = Iterables.filter(Iterables.filter(getNotationDiagram().getChildren(), Node.class), InteractionContainer.notationPredicate()).iterator();
            if (interactionContainerIterator.hasNext()) {
                Option<InteractionContainer> ic = ISequenceElementAccessor.getInteractionContainer(interactionContainerIterator.next());
                assert !interactionContainerIterator.hasNext() : Messages.InteractionContainer_multipleElementDetected;
                optionalInteractionContainer = Optional.of(ic.get());
            } else {
                optionalInteractionContainer = Optional.empty();
            }
            interactionContainerCache = optionalInteractionContainer;
        }

        return optionalInteractionContainer;
    }

    /**
     * Finds and returns the EventEnds corresponding to the given part.
     * 
     * @param event
     *            current event
     * @return the EventEnds corresponding to the given part
     */
    public List<EventEnd> findEnds(ISequenceEvent event) {
        return EventEndHelper.findEndsFromSemanticOrdering(event, getSequenceDDiagram());
    }

    /**
     * Diagram are not associated to a particular lifeline.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Option<Lifeline> getLifeline() {
        return Options.newNone();
    }

    /**
     * The diagram itself has no significant bounds.
     * 
     * @return the bounds of the diagram.
     */
    @Override
    public Rectangle getProperLogicalBounds() {
        return new Rectangle(0, 0, 0, 0);
    }

    /**
     * Enable/Disable the cache mode.
     * 
     * @param newStatus
     *            the new status for the cache mode
     */
    public void useCache(boolean newStatus) {
        this.useCache = newStatus;
    }

    /**
     * Clear all the caches.
     */
    public void clearAllCaches() {
        this.allAbstractNodeEventsCache = null;
        this.allCombinedFragmentsCache = null;
        this.allEndOfLifesCache = null;
        this.allExecutionsCache = null;
        this.allFramesCache = null;
        this.allInstanceRolesCache = null;
        this.allInteractionUsesCache = null;
        this.allLifelinesCache = null;
        this.allLostMessageEndCache = null;
        this.allMessagesCache = null;
        this.allObservationPointsCache = null;
        this.allOperandsCache = null;
        this.allStatesCache = null;
        this.allDelimitedEventsCache = null;
        this.interactionContainerCache = null;
        this.allGatesCache = null;
        clearOrderedCaches();
    }

    /**
     * Clear all the ordered caches. The order has been changed and it must be computed again.
     */
    public void clearOrderedCaches() {
        this.allOrderedAbstractNodeEventsCache = null;
        this.allOrderedCombinedFragmentsCache = null;
        this.allOrderedDelimitedSequenceEventsCache = null;
        this.allOrderedExecutionsCache = null;
        this.allOrderedFramesCache = null;
        this.allOrderedInteractionUsesCache = null;
        this.allOrderedLifelinesCache = null;
        this.allOrderedMessagesCache = null;
        this.allOrderedOperandsCache = null;
        this.allOrderedStatesCache = null;
    }

}
