/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES and others.
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
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.sequence.Messages;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceMessageViewQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.RangeSetter;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * Common interface for all the elements of a sequence diagram.
 * 
 * @author mporhel
 */
public class Message extends AbstractSequenceElement implements ISequenceEvent {
    /**
     * Predicate to filter States, Frames and Operand from possible new source or target of a message reconnection.
     */
    public static final Predicate<ISequenceEvent> NO_RECONNECTABLE_EVENTS = new Predicate<ISequenceEvent>() {
        @Override
        public boolean apply(ISequenceEvent input) {
            return input instanceof AbstractFrame || input instanceof Operand || input instanceof State;
        }
    };

    /**
     * Function to get the Sirius DDiagramElement message kind.
     */
    public static final Function<DEdge, Kind> VIEWPOINT_MESSAGE_KIND = new Function<DEdge, Kind>() {
        @Override
        public Kind apply(DEdge from) {
            Kind result = null;
            if (AbstractSequenceElement.isSequenceDiagramElement(from, DescriptionPackage.eINSTANCE.getBasicMessageMapping())) {
                result = Kind.BASIC;
            } else if (AbstractSequenceElement.isSequenceDiagramElement(from, DescriptionPackage.eINSTANCE.getReturnMessageMapping())) {
                result = Kind.REPLY;
            } else if (AbstractSequenceElement.isSequenceDiagramElement(from, DescriptionPackage.eINSTANCE.getCreationMessageMapping())) {
                result = Kind.CREATION;
            } else if (AbstractSequenceElement.isSequenceDiagramElement(from, DescriptionPackage.eINSTANCE.getDestructionMessageMapping())) {
                result = Kind.DESTRUCTION;
            }
            assert result != null : Messages.Message_unsupportedMessageKind;
            return result;
        }
    };

    /**
     * The visual ID.
     * 
     * see org.eclipse.sirius.diagram.internal.edit.parts.DEdgeEditPart. VISUAL_ID
     */
    public static final int VISUAL_ID = 4001;

    /**
     * The different (exclusive) kinds of sequence messages.
     */
    public enum Kind {
        /**
         * Normal, basic message.
         */
        BASIC,
        /**
         * Reply message, associated to the basic message to which it replies.
         */
        REPLY,
        /**
         * Creation message.
         */
        CREATION,
        /**
         * Destruction message.
         */
        DESTRUCTION;
    }

    /**
     * Predicate to check whether a Sirius DDiagramElement represents a message.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getMessageMapping());
        }
    }

    /**
     * .
     * 
     * @param edge
     *            .
     */
    public Message(Edge edge) {
        super(edge);
        Preconditions.checkArgument(Message.notationPredicate().apply(edge), Messages.Message_nonSequenceMessageEdge);
    }

    /**
     * Returns a predicate to check whether a GMF View represents a message.
     * 
     * @return a predicate to check whether a GMF View represents a message.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getEdge(), VISUAL_ID, Message.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents a message.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents a message.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    public Edge getNotationEdge() {
        return (Edge) view;
    }

    /**
     * Returns the precise kind of this message, if this element is valid.
     * 
     * @return the precise kind of this message, if this element is valid.
     */
    public Kind getKind() {
        EObject element = view.getElement();
        if (element instanceof DEdge) {
            return VIEWPOINT_MESSAGE_KIND.apply((DEdge) element);
        } else {
            // Assume basic message
            return Kind.BASIC;
        }
    }

    /**
     * {@inheritDoc}
     */
    public ISequenceNode getSourceElement() {
        return ISequenceElementAccessor.getISequenceNode(getNotationEdge().getSource()).get();
    }

    /**
     * {@inheritDoc}
     */
    public ISequenceNode getTargetElement() {
        return ISequenceElementAccessor.getISequenceNode(getNotationEdge().getTarget()).get();
    }

    @Override
    public Range getVerticalRange() {
        return new SequenceMessageViewQuery(getNotationEdge()).getVerticalRange();
    }

    @Override
    public boolean isLogicallyInstantaneous() {
        return !isReflective();
    }

    @Override
    public void setVerticalRange(Range range) throws IllegalStateException {
        RangeSetter.setVerticalRange(this, range);
    }

    @Override
    public Option<Lifeline> getLifeline() {
        if (isReflective()) {
            return getSourceLifeline();
        }
        return Options.newNone();
    }

    /**
     * Tests whether this a reflective message, i.e. both its source and target are in the context of the same lifeline.
     * 
     * @return <code>true</code> if this message is reflective.
     */
    public boolean isReflective() {
        Option<Lifeline> sourceLifeline = getSourceLifeline();
        Option<Lifeline> targetLifeline = getTargetLifeline();
        return sourceLifeline.some() && targetLifeline.some() && sourceLifeline.get() == targetLifeline.get();
    }

    /**
     * Returns the lifeline in the context of which this message is sent.
     * 
     * @return the lifeline in the context of which this message is sent.
     */
    public Option<Lifeline> getSourceLifeline() {
        ISequenceNode sourceElement = getSourceElement();
        if (sourceElement != null) {
            return sourceElement.getLifeline();
        }
        return Options.newNone();
    }

    /**
     * Returns the lifeline in the context of which this message is received.
     * 
     * @return the lifeline in the context of which this message is received.
     */
    public Option<Lifeline> getTargetLifeline() {
        ISequenceNode targetElement = getTargetElement();
        if (targetElement != null) {
            return targetElement.getLifeline();
        }
        return Options.newNone();
    }

    /**
     * Returns the lifeline on "the other side" of the message, with respect to the specified lifeline. For reflective
     * messages, this is the same as the local lifeline. The specified local lifeline <em>must</em> be either the source
     * of target lifeline of this message. Otherwise the result is unspecified.
     * 
     * @param local
     *            the lifeline to consider as "local", either the source or target lifeline of the message.
     * @return the lifeline on "the other side" of the message, i.e. the the target lifeline is <code>local</code> it
     *         the source lifeline, and the source lifeline otherwise.
     */
    public Option<Lifeline> getRemoteLifeline(Lifeline local) {
        Option<Lifeline> sourceLifeline = getSourceLifeline();
        if (local == sourceLifeline.get()) {
            return getTargetLifeline();
        } else {
            return sourceLifeline;
        }
    }

    public boolean isCompoundMessage() {
        return !Iterables.isEmpty(Iterables.filter(EventEndHelper.findEndsFromSemanticOrdering(this), CompoundEventEnd.class));
    }

    @Override
    public Rectangle getProperLogicalBounds() {
        Range range = getVerticalRange();

        ISequenceNode srcElement = getSourceElement();
        ISequenceNode tgtElement = getTargetElement();

        Rectangle srcLogicalBounds = srcElement.getProperLogicalBounds().getCopy();
        Rectangle tgtLogicalBounds = tgtElement.getProperLogicalBounds().getCopy();

        int srcCenterX = srcLogicalBounds.getCenter().x;
        int tgtCenterX = tgtLogicalBounds.getCenter().x;

        int srcX = 0;
        int tgtX = 0;
        if (isReflective()) {
            srcX = srcLogicalBounds.getRight().x;
            tgtX = tgtLogicalBounds.getRight().x;
        } else if (srcCenterX <= tgtCenterX) {
            srcX = srcLogicalBounds.getRight().x;
            tgtX = tgtLogicalBounds.getLeft().x;
        } else {
            srcX = srcLogicalBounds.getLeft().x;
            tgtX = tgtLogicalBounds.getRight().x;
        }

        if (srcElement instanceof Lifeline) {
            srcX = srcLogicalBounds.getCenter().x;
        }

        if (tgtElement instanceof Lifeline) {
            tgtX = tgtLogicalBounds.getCenter().x;
        }

        return new Rectangle(srcX, range.getLowerBound(), tgtX - srcX, range.width());
    }

    /**
     * Messages have no sub-events.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public List<ISequenceEvent> getSubEvents() {
        return Collections.emptyList();
    }

    @Override
    public Collection<ISequenceEvent> getEventsToMoveWith() {
        return getSubEvents();
    }

    @Override
    public ISequenceEvent getParentEvent() {
        ISequenceNode sourceElement = getSourceElement();
        if (sourceElement instanceof ISequenceEvent) {
            return (ISequenceEvent) sourceElement;
        }
        return null;
    }

    @Override
    public ISequenceEvent getHierarchicalParentEvent() {
        return null;
    }

    @Override
    public Range getOccupiedRange() {
        return new ISequenceEventQuery(this).getOccupiedRange();
    }

    /**
     * Messages have no sub-events.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Range getValidSubEventsRange() {
        return Range.emptyRange();
    }

    /**
     * Messages have no sub-events.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean canChildOccupy(ISequenceEvent child, Range range) {
        return false;
    }

    /**
     * Messages have no sub-events.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean canChildOccupy(ISequenceEvent child, Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines) {
        return false;
    }

    @Override
    public Option<Operand> getParentOperand() {
        Option<Lifeline> sourceLifeline = getSourceLifeline();
        Option<Operand> sourceParentOperand = Options.newNone();
        Range verticalRange = getVerticalRange();
        if (sourceLifeline.some()) {
            sourceParentOperand = sourceLifeline.get().getParentOperand(verticalRange);
        }

        Option<Lifeline> targetLifeline = getTargetLifeline();
        Option<Operand> targetParentOperand = Options.newNone();
        if (targetLifeline.some()) {
            targetParentOperand = targetLifeline.get().getParentOperand(verticalRange);
        }

        boolean noOperand = !sourceParentOperand.some() && !targetParentOperand.some();
        boolean lostEnd = sourceLifeline.some() && !targetLifeline.some() || !sourceLifeline.some() && targetLifeline.some();
        boolean sameOperand = lostEnd || noOperand || sourceParentOperand.get().equals(targetParentOperand.get());
        Preconditions.checkArgument(noOperand || sameOperand, Messages.Message_invalidOperand);

        Option<Operand> parentOperand = sourceParentOperand;
        if (!parentOperand.some()) {
            parentOperand = targetParentOperand;
        }

        return parentOperand;
    }

    /**
     * Check if the current message is reflexive and surrounds other events on the same lifeline.
     * 
     * @return true if the current message is reflexive and surrounds other events on the same lifeline.
     */
    public boolean surroundsEventOnSameLifeline() {
        return !getSurroundedSameLifelineEvents().isEmpty();
    }

    /**
     * Get the surrounded reflexives message depth.
     * 
     * @return the surrounded reflexives message depth.
     */
    public int getReflexiveMessageWidth() {
        Collection<ISequenceEvent> events = getSurroundedSameLifelineEvents();

        int width = LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP;
        if (!events.isEmpty()) {

            final Range range = getVerticalRange();
            Predicate<ISequenceEvent> toConsider = new Predicate<ISequenceEvent>() {
                @Override
                public boolean apply(ISequenceEvent input) {
                    boolean toConsider = range.includes(input.getVerticalRange());
                    if (input instanceof Message) {
                        toConsider = toConsider && ((Message) input).isReflective();
                    }
                    return toConsider;
                }
            };

            List<ISequenceEvent> impactingEvents = Lists.newArrayList(Iterables.filter(events, toConsider));
            Collections.sort(impactingEvents, Ordering.natural().onResultOf(Functions.compose(RangeHelper.lowerBoundFunction(), ISequenceEvent.VERTICAL_RANGE)));
            int subMessagesMaxRight = 0;
            for (Message msg : Iterables.filter(impactingEvents, Message.class)) {
                int reflexiveMessageWidth = msg.getReflexiveMessageWidth();
                int origin = msg.getSourceElement().getProperLogicalBounds().right();
                origin = Math.max(origin, msg.getTargetElement().getProperLogicalBounds().right());
                subMessagesMaxRight = Math.max(subMessagesMaxRight, origin + reflexiveMessageWidth);
            }

            int maxRight = 0;
            for (AbstractNodeEvent node : Iterables.filter(impactingEvents, AbstractNodeEvent.class)) {
                maxRight = Math.max(maxRight, node.getProperLogicalBounds().right());
            }

            int origin = getSourceElement().getProperLogicalBounds().right();
            origin = Math.max(origin, getTargetElement().getProperLogicalBounds().right());

            width = Math.max(width, maxRight - origin + LayoutConstants.MESSAGE_TO_SELF_HORIZONTAL_GAP);
            width = Math.max(width, subMessagesMaxRight - origin + LayoutConstants.MESSAGE_TO_SELF_HORIZONTAL_GAP);
        }
        return width;
    }

    /**
     * Get the surrounded events on the same lifeline.
     * 
     * @return the surrounded events on the same lifeline.
     */
    public Collection<ISequenceEvent> getSurroundedSameLifelineEvents() {
        Set<ISequenceEvent> englobedEvents = new LinkedHashSet<ISequenceEvent>();
        if (isReflective()) {
            Lifeline lifeline = getLifeline().get();
            SequenceDiagram diagram = getDiagram();
            EventEndsOrdering semanticOrdering = diagram.getSequenceDDiagram().getSemanticOrdering();
            List<EventEnd> msgEnds = EventEndHelper.findEndsFromSemanticOrdering(this);
            if (msgEnds.size() == 2) {
                int start = semanticOrdering.getEventEnds().indexOf(msgEnds.get(0));
                int end = semanticOrdering.getEventEnds().indexOf(msgEnds.get(1));
                if (Math.abs(start - end) > 1) {
                    Collection<SingleEventEnd> sees = new HashSet<>();
                    Collection<ISequenceEvent> interEvents = new HashSet<>();

                    for (int i = start + 1; i < end; i++) {
                        EventEnd eventEnd = semanticOrdering.getEventEnds().get(i);
                        if (eventEnd instanceof SingleEventEnd) {
                            sees.add((SingleEventEnd) eventEnd);
                        } else if (eventEnd instanceof CompoundEventEnd) {
                            sees.addAll(((CompoundEventEnd) eventEnd).getEventEnds());
                        }
                    }

                    for (SingleEventEnd see : sees) {
                        ISequenceEvent perturbing = EventEndHelper.findISequenceEvent(see, diagram);
                        if (perturbing != null) {
                            interEvents.add(perturbing);
                        }
                    }

                    englobedEvents.addAll(getValidInterEvents(interEvents, lifeline));
                }
            }
        }
        return englobedEvents;
    }

    private Collection<? extends ISequenceEvent> getValidInterEvents(Collection<ISequenceEvent> interEvents, Lifeline lifeline) {
        Set<ISequenceEvent> englobedEvents = new LinkedHashSet<ISequenceEvent>();
        for (ISequenceEvent pertub : interEvents) {
            if (pertub instanceof Message) {
                Message msg = (Message) pertub;
                Option<Lifeline> sourceLifeline = msg.getSourceLifeline();
                Option<Lifeline> targetLifeline = msg.getTargetLifeline();
                if (targetLifeline.some() && targetLifeline.get().equals(lifeline)) {
                    englobedEvents.add(pertub);
                } else if (sourceLifeline.some() && sourceLifeline.get().equals(lifeline)) {
                    englobedEvents.add(pertub);
                }
            } else if (pertub instanceof CombinedFragment) {
                CombinedFragment cf = (CombinedFragment) pertub;
                Collection<Lifeline> coverage = cf.computeCoveredLifelines();
                if (coverage.contains(lifeline)) {
                    englobedEvents.add(pertub);
                }
            } else {
                Option<Lifeline> pLif = pertub.getLifeline();
                if (pLif.some() && pLif.get().equals(lifeline)) {
                    englobedEvents.add(pertub);
                }
            }
        }
        return englobedEvents;
    }

}
