/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

import org.eclipse.sirius.common.tools.api.util.AllContents;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.viewpoint.DDiagram;

/**
 * Represents a sequence diagram. This is the root of all sequence elements.
 * 
 * @author mporhel, pcdavid
 */
public class SequenceDiagram extends AbstractSequenceElement {

    private static final String INTERNAL_ERROR = "Internal error.";

    /**
     * Predicate to check whether a GMF View represents a sequence diagram.
     */
    private static enum NotationPredicate implements Predicate<View> {
        INSTANCE;

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
     * Predicate to check whether a Sirius DDiagram represents a sequence
     * diagram.
     */
    private static enum SiriusElementPredicate implements Predicate<DDiagram> {
        INSTANCE;

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
     * Constructor.
     * 
     * @param diagram
     *            the GMF Diagram representing this sequence diagram.
     */
    SequenceDiagram(Diagram diagram) {
        super(diagram);
        Preconditions.checkArgument(SequenceDiagram.notationPredicate().apply(diagram), "The diagram does not represent a sequence diagram.");
    }

    /**
     * Returns a predicate to check whether a GMF View represents a sequence
     * diagram.
     * 
     * @return a predicate to check whether a GMF View represents a sequence
     *         diagram.
     */
    public static Predicate<View> notationPredicate() {
        return NotationPredicate.INSTANCE;
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagram represents a
     * sequence diagram.
     * 
     * @return a predicate to check whether a Sirius DDiagram represents a
     *         sequence diagram.
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
     * Finds all the lifelines in this diagram which are at least partially
     * covered by the specified rectangular area.
     * 
     * @param area
     *            the rectangular area to check for lifelines (in logical
     *            coordinates).
     * @return all the lifelines in this diagram which are at least partially
     *         covered by the area.
     */
    public Set<Lifeline> getGraphicallyCoveredLifelines(final Rectangle area) {
        List<Lifeline> result = Lists.newArrayList();
        Iterables.addAll(result, Iterables.filter(getAllLifelines(), new Predicate<Lifeline>() {
            public boolean apply(Lifeline input) {
                return input.getProperLogicalBounds().intersects(area) && input.getVerticalRange().includes(area.getTop().y);
            }
        }));
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * .
     * 
     * @return .
     */
    public List<InstanceRole> getSortedInstanceRole() {
        Function<InstanceRole, Integer> xLocation = new Function<InstanceRole, Integer>() {
            public Integer apply(InstanceRole from) {
                Rectangle bounds = from.getBounds();
                return bounds.x;
            }
        };

        List<InstanceRole> allInstanceRoles = Lists.newArrayList(getAllInstanceRoles());
        Collections.sort(allInstanceRoles, Ordering.natural().onResultOf(xLocation));
        return allInstanceRoles;
    }

    /**
     * .
     * 
     * @return .
     */
    public Collection<InstanceRole> getAllInstanceRoles() {
        Collection<InstanceRole> result = Lists.newArrayList();
        for (View child : Iterables.filter(getNotationView().getChildren(), View.class)) {
            if (InstanceRole.notationPredicate().apply(child)) {
                Option<InstanceRole> instanceRole = ISequenceElementAccessor.getInstanceRole(child);
                if (instanceRole.some()) {
                    result.add(instanceRole.get());
                }
            }
        }
        return result;
    }

    /**
     * .
     * 
     * @return .
     */
    public List<Lifeline> getAllLifelines() {
        Collection<InstanceRole> allInstanceRoles = getAllInstanceRoles();
        Function<ISequenceNode, Lifeline> lifelineFunction = new Function<ISequenceNode, Lifeline>() {
            public Lifeline apply(ISequenceNode from) {
                return from.getLifeline().get();
            }
        };
        List<Lifeline> result = Lists.newArrayList(Iterables.transform(allInstanceRoles, lifelineFunction));
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return result;
    }

    /**
     * Returns all the {@link Node}s in the specified diagram which represent an
     * ObservationPoint.
     * 
     * @return the Nodes inside this diagram which represent sequence
     *         ObservationPoint. An empty iterator is returned if the diagram is
     *         not a sequence diagram.
     */
    public Collection<ObservationPoint> getAllObservationPoints() {
        Collection<ObservationPoint> result = Lists.newArrayList();
        for (View child : Iterables.filter(getNotationView().getChildren(), View.class)) {
            if (ObservationPoint.notationPredicate().apply(child)) {
                Option<ObservationPoint> obsPoint = ISequenceElementAccessor.getObservationPoint(child);
                if (obsPoint.some()) {
                    result.add(obsPoint.get());
                }
            }
        }
        return result;
    }

    /**
     * Returns all the {@link Node}s in the specified diagram which represent a
     * lost sequence message end.
     * 
     * @return the Nodes inside this diagram which represent lost sequence
     *         messages end. An empty iterator is returned if the diagram is not
     *         a sequence diagram.
     */
    public Collection<LostMessageEnd> getAllLostMessageEnds() {
        Collection<LostMessageEnd> result = Lists.newArrayList();
        for (View child : Iterables.filter(getNotationView().getChildren(), View.class)) {
            if (LostMessageEnd.notationPredicate().apply(child)) {
                Option<LostMessageEnd> lostMessageEnd = ISequenceElementAccessor.getLostMessageEnd(child);
                if (lostMessageEnd.some()) {
                    result.add(lostMessageEnd.get());
                }
            }
        }
        return result;
    }

    /**
     * Returns all the {@link Edge}s in the specified diagram which represent a
     * sequence message of any kind.
     * 
     * @return the Edges inside this diagram which represent sequence messages.
     *         An empty iterator is returned if the diagram is not a sequence
     *         diagram.
     */
    public Set<Message> getAllMessages() {
        List<Message> result = Lists.newArrayList();
        for (Edge edge : Iterables.filter(Iterables.filter(getNotationDiagram().getEdges(), Edge.class), Message.notationPredicate())) {
            Option<Message> message = ISequenceElementAccessor.getMessage(edge);
            assert message.some() : INTERNAL_ERROR;
            result.add(message.get());
        }
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all AbstractNodeEvent in the given diagram.
     * 
     * @return all AbstractNodeEvent on the given diagram.
     */
    public Set<AbstractNodeEvent> getAllAbstractNodeEvents() {
        List<AbstractNodeEvent> result = Lists.newArrayList();
        for (Node node : Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), Node.class), AbstractNodeEvent.notationPredicate())) {
            Option<AbstractNodeEvent> exec = ISequenceElementAccessor.getAbstractNodeEvent(node);
            assert exec.some() : INTERNAL_ERROR;
            result.add(exec.get());
        }
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all executions in the given diagram.
     * 
     * @return all executions on the given diagram.
     */
    public Set<Execution> getAllExecutions() {
        List<Execution> result = Lists.newArrayList();
        for (Node node : Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), Node.class), Execution.notationPredicate())) {
            Option<Execution> exec = ISequenceElementAccessor.getExecution(node);
            assert exec.some() : INTERNAL_ERROR;
            result.add(exec.get());
        }
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all executions in the given diagram.
     * 
     * @return all executions on the given diagram.
     */
    public Set<State> getAllStates() {
        List<State> result = Lists.newArrayList();
        for (Node node : Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), Node.class), State.notationPredicate())) {
            Option<State> exec = ISequenceElementAccessor.getState(node);
            assert exec.some() : INTERNAL_ERROR;
            result.add(exec.get());
        }
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all frames in the given diagram.
     * 
     * @return all frames on the given diagram.
     */
    public Collection<AbstractFrame> getAllFrames() {
        List<AbstractFrame> result = Lists.newArrayList();
        for (Node node : Iterables.filter(Iterables.filter(getNotationDiagram().getChildren(), Node.class), AbstractFrame.notationPredicate())) {
            Option<ISequenceEvent> exec = ISequenceElementAccessor.getISequenceEvent(node);
            assert exec.some() : INTERNAL_ERROR;
            if (exec.get() instanceof AbstractFrame) {
                result.add((AbstractFrame) exec.get());
            }
        }
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all interaction uses in the given diagram.
     * 
     * @return all interaction uses on the given diagram.
     */
    public Set<InteractionUse> getAllInteractionUses() {
        List<InteractionUse> result = Lists.newArrayList();
        for (Node node : Iterables.filter(Iterables.filter(getNotationDiagram().getChildren(), Node.class), InteractionUse.notationPredicate())) {
            Option<InteractionUse> exec = ISequenceElementAccessor.getInteractionUse(node);
            assert exec.some() : INTERNAL_ERROR;
            result.add(exec.get());
        }
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all combined fragments in the given diagram.
     * 
     * @return all combined fragments on the given diagram.
     */
    public Set<CombinedFragment> getAllCombinedFragments() {
        List<CombinedFragment> result = Lists.newArrayList();
        for (Node node : Iterables.filter(Iterables.filter(getNotationDiagram().getChildren(), Node.class), CombinedFragment.notationPredicate())) {
            Option<CombinedFragment> exec = ISequenceElementAccessor.getCombinedFragment(node);
            assert exec.some() : INTERNAL_ERROR;
            result.add(exec.get());
        }
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all operands in the given diagram.
     * 
     * @return all operands on the given diagram.
     */
    public Set<Operand> getAllOperands() {
        List<Operand> result = Lists.newArrayList();
        for (Node node : Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), Node.class), Operand.notationPredicate())) {
            Option<Operand> exec = ISequenceElementAccessor.getOperand(node);
            assert exec.some() : INTERNAL_ERROR;
            result.add(exec.get());
        }
        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all endOfLifes in the given diagram.
     * 
     * @return all endOfLifes on the given diagram.
     */
    public Set<EndOfLife> getAllEndOfLifes() {
        Set<EndOfLife> allEndOfLifes = new HashSet<EndOfLife>();
        for (Lifeline lifeline : getAllLifelines()) {
            if (lifeline.getEndOfLife().some()) {
                allEndOfLifes.add(lifeline.getEndOfLife().get());
            }
        }
        return allEndOfLifes;
    }

    /**
     * Returns all sequence events in the given diagram. The result is ordered
     * regarding the lower bound ordering.
     * 
     * @return all sequence events on the given diagram.
     */
    public Set<ISequenceEvent> getAllOrderedDelimitedSequenceEvents() {
        List<ISequenceEvent> result = Lists.newArrayList();
        Iterables.addAll(result, getAllDelimitedSequenceEvents());

        Collections.sort(result, Range.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
        return Sets.newLinkedHashSet(result);
    }

    /**
     * Returns all sequence events in the given diagram. The result is not
     * ordered and will be computed on iteration.
     * 
     * @return all sequence events on the given diagram.
     */
    public Iterable<? extends ISequenceEvent> getAllDelimitedSequenceEvents() {
        Function<View, ? extends ISequenceEvent> getISE = new Function<View, ISequenceEvent>() {
            public ISequenceEvent apply(View from) {
                Option<ISequenceEvent> ise = ISequenceElementAccessor.getISequenceEvent(from);
                assert ise.some() : INTERNAL_ERROR;
                return ise.get();
            }
        };
        return Iterables.transform(Iterables.filter(Iterables.filter(AllContents.of(getNotationDiagram()), View.class), ISequenceEvent.ISEQUENCEEVENT_NOTATION_PREDICATE), getISE);
    }

    /**
     * Finds and returns the EventEnds corresponding to the given part.
     * 
     * @param event
     *            current event
     * @return the EventEnds corresponding to the given part
     */
    public List<EventEnd> findEnds(ISequenceEvent event) {
        List<EventEnd> ends = Lists.newArrayList();
        EObject seqDiag = getNotationDiagram().getElement();
        Option<EObject> semanticEvent = event.getSemanticTargetElement();
        if (seqDiag instanceof SequenceDDiagram && semanticEvent.some()) {
            for (EventEnd ee : ((SequenceDDiagram) seqDiag).getGraphicalOrdering().getEventEnds()) {
                if (EventEndHelper.getSemanticEvents(ee).contains(semanticEvent.get())) {
                    ends.add(ee);
                }
            }
        }
        return ends;
    }

    /**
     * Diagram are not associated to a particular lifeline.
     * <p>
     * {@inheritDoc}
     */
    public Option<Lifeline> getLifeline() {
        return Options.newNone();
    }

    /**
     * The diagram itself has no significant bounds.
     * 
     * @return the bounds of the diagram.
     */
    public Rectangle getProperLogicalBounds() {
        return new Rectangle(0, 0, 0, 0);
    }

}
