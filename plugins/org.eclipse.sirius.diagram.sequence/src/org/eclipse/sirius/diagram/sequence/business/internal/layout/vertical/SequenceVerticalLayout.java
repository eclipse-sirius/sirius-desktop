/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.layout.vertical;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionContainer;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.AbstractSequenceLayout;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.AbstractSequenceOrderingLayout;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.EventEndToPositionFunction;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceElementQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceMessageViewQuery;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;

/**
 * Computes the appropriate graphical locations of sequence events and lifelines on a sequence diagram to reflect the
 * semantic order.
 * 
 * @author pcdavid, mporhel
 */
public class SequenceVerticalLayout extends AbstractSequenceOrderingLayout<ISequenceElement, Range, EventEnd> {

    /**
     * A map to link an {@link EventEnd} to the attached {@link ISequenceEvent}.
     */
    protected final Map<EventEnd, Message> creators;

    /**
     * A map to link an {@link EventEnd} to the attached {@link ISequenceEvent}.
     */
    protected final Map<EventEnd, Message> destructors;

    /**
     * A map to link an {@link EventEnd} to an attached {@link LostMessageEnd}.
     */
    protected final Map<EventEnd, LostMessageEnd> losts;

    /**
     * Unconnected lostMessageEnds.
     */
    protected final List<LostMessageEnd> unconnectedLostEnds;

    /**
     * Semantic flagged event ends at creation.
     */
    protected final List<EventEnd> toolCreatedEnds = new ArrayList<>();

    /**
     * A map to link an {@link EventEnd} to the attached {@link ISequenceEvent}.
     */
    protected final Multimap<EventEnd, ISequenceEvent> endToISequencEvents;

    /**
     * A map to link an {@link ISequenceEvent} to its starting and ending {@link EventEnd}.
     */
    protected final Multimap<ISequenceEvent, EventEnd> iSequenceEventsToEventEnds;

    /**
     * A function to compute the sequence events corresponding to an event end.
     */
    protected final Function<EventEnd, Collection<ISequenceEvent>> eventEndToSequenceEvents = new Function<EventEnd, Collection<ISequenceEvent>>() {
        @Override
        public Collection<ISequenceEvent> apply(EventEnd from) {
            return endToISequencEvents.get(from);
        }
    };

    /**
     * The global time range of the diagram. Can be udpated during layout computation.
     */
    protected Range timeRange;

    /**
     * A function to get the instance role height of a lifeline.
     */
    private final Function<Lifeline, Integer> instanceRoleHeight = new Function<Lifeline, Integer>() {
        @Override
        public Integer apply(Lifeline from) {
            InstanceRole irep = from.getInstanceRole();
            if (irep != null) {
                return ((Size) irep.getNotationNode().getLayoutConstraint()).getHeight();
            }
            return 0;
        }
    };

    /**
     * An ordering to sort {@link Lifeline} regarding the height of their {@link InstanceRole}.
     */
    private final Ordering<Lifeline> heightOrdering = Ordering.natural().onResultOf(instanceRoleHeight);

    private final Function<ISequenceEvent, Option<Range>> oldRangeFunction = new Function<ISequenceEvent, Option<Range>>() {
        @Override
        public Option<Range> apply(ISequenceEvent from) {
            Range range = oldLayoutData.get(from);
            if (range == null) {
                range = Range.emptyRange();
            }
            return Options.newSome(range);
        }
    };

    private final Function<ISequenceEvent, Option<Range>> oldFlaggedRange = new Function<ISequenceEvent, Option<Range>>() {
        @Override
        public Option<Range> apply(ISequenceEvent from) {
            Rectangle rect = oldFlaggedLayoutData.get(from);
            Range result = null;
            if (rect != null) {
                result = RangeHelper.verticalRange(rect);
            }
            return Options.newSome(result);
        }
    };

    private final Function<EventEnd, Integer> eventEndOldPosition = new EventEndToPositionFunction(eventEndToSequenceEvents, oldRangeFunction) {

        @Override
        protected Integer getOldPositionFromRange(SingleEventEnd see, ISequenceEvent ise) {
            Integer oldPos = super.getOldPositionFromRange(see, ise);

            if (ise instanceof Message && !ise.isLogicallyInstantaneous() && see != null) {
                // Real position (diagram initialization, message creation)
                Message smep = (Message) ise;
                Edge notationView = smep.getNotationEdge();
                SequenceMessageViewQuery query = new SequenceMessageViewQuery(notationView);
                oldPos = see.isStart() ? query.getFirstPointVerticalPosition(true) : query.getLastPointVerticalPosition(true);
            }

            return oldPos;
        }

    };

    private final Function<EventEnd, Integer> eventEndOldFlaggedPosition = new EventEndToPositionFunction(eventEndToSequenceEvents, oldFlaggedRange);

    /**
     * Constructor.
     * 
     * @param sequenceDiagram
     *            the sequence diagram for which to compute the messages locations.
     */
    public SequenceVerticalLayout(SequenceDiagram sequenceDiagram) {
        super(sequenceDiagram);

        this.iSequenceEventsToEventEnds = LinkedHashMultimap.create();
        this.endToISequencEvents = HashMultimap.create();

        this.creators = new HashMap<>();
        this.destructors = new HashMap<>();
        this.losts = new HashMap<>();
        this.unconnectedLostEnds = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init(boolean pack) {
        initSortedEventEnds(pack);
        initLifelinesOldLayoutData();
        initTimeBounds(pack);
        registerEventEnds();

        lookForUnconnectedLostEnd();

        checkOrderingSync();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Range getOldLayoutData(ISequenceElement ise) {
        Range verticalRange = Range.emptyRange();

        if (ise instanceof ISequenceEvent) {
            verticalRange = ((ISequenceEvent) ise).getVerticalRange();

            if (ise instanceof Message) {
                Message msg = (Message) ise;
                ISequenceElementQuery query = null;
                ISequenceNode sourceElement = msg.getSourceElement();
                ISequenceNode targetElement = msg.getTargetElement();
                if (sourceElement instanceof LostMessageEnd && AbstractSequenceLayout.createdFromTool((LostMessageEnd) sourceElement)) {
                    query = new ISequenceElementQuery(sourceElement);
                } else if (targetElement instanceof LostMessageEnd && AbstractSequenceLayout.createdFromTool((LostMessageEnd) targetElement)) {
                    query = new ISequenceElementQuery(targetElement);
                }

                if (query != null && query.hasAbsoluteBoundsFlag()) {
                    Rectangle flag = query.getFlaggedAbsoluteBounds();
                    verticalRange = new Range(flag.y, flag.y);
                }
            }
        }

        return verticalRange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean applyComputedLayout(Map<? extends ISequenceElement, Range> finalRanges, boolean pack) {
        boolean applied = false;
        Iterable<ISequenceEvent> keySet = Iterables.filter(finalRanges.keySet(), ISequenceEvent.class);

        // Begin with lifelines and executions (anchor positions move)
        for (ISequenceEvent ise : Iterables.filter(keySet, Predicates.not(Predicates.instanceOf(Message.class)))) {
            final Range newRange = finalRanges.get(ise);
            ise.setVerticalRange(newRange);
            applied = true;
        }

        // Then apply computed vertical range on messages
        for (Message smep : Iterables.filter(keySet, Message.class)) {
            final Range newRange = finalRanges.get(smep);
            smep.setVerticalRange(newRange);
            applied = true;
        }

        applied = layoutUnconnectedLostMessageEnd() || applied;
        applied = layoutInteractionContainer() || applied;

        return applied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<? extends ISequenceElement, Range> computeLayout(boolean pack) {
        LinkedHashMap<ISequenceEvent, Range> sequenceEventRanges = new LinkedHashMap<ISequenceEvent, Range>();

        // Compute the position of each event end.
        Map<EventEnd, Integer> endLocations = computeEndBounds(pack);

        // Compute ISequenceEvent vertical ranges from event end locations.
        Map<ISequenceEvent, Range> basicRanges = computeBasicRanges(endLocations);

        // Compute punctual States vertical range
        Map<ISequenceEvent, Range> punctualEventRanges = computePunctualEventsGraphicalRanges(endLocations, pack);

        // Update lifeline size.
        Map<ISequenceEvent, Range> lifelinesRanges = computeLifelineRanges(endLocations);

        sequenceEventRanges.putAll(lifelinesRanges);
        sequenceEventRanges.putAll(basicRanges);
        sequenceEventRanges.putAll(punctualEventRanges);

        return sequenceEventRanges;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispose() {
        creators.clear();
        destructors.clear();
        losts.clear();
        unconnectedLostEnds.clear();
        toolCreatedEnds.clear();

        endToISequencEvents.clear();
        iSequenceEventsToEventEnds.clear();

        super.dispose();
    }

    private Map<ISequenceEvent, Range> computePunctualEventsGraphicalRanges(Map<EventEnd, Integer> endLocations, boolean pack) {
        final Map<ISequenceEvent, Range> sequenceEventsToRange = new LinkedHashMap<ISequenceEvent, Range>();
        if (pack) {
            for (EventEnd cee : Iterables.filter(semanticOrdering, EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END)) {
                if (endLocations.containsKey(cee) && endToISequencEvents.containsKey(cee)) {
                    int loc = endLocations.get(cee);
                    Collection<ISequenceEvent> ises = endToISequencEvents.get(cee);

                    if (Iterables.any(ises, Predicates.instanceOf(State.class)) && ises.size() == 1) {
                        State ise = (State) ises.iterator().next();
                        int midSize = getAbstractNodeEventVerticalSize(cee, ise, ises, pack) / 2;
                        sequenceEventsToRange.put(ise, new Range(loc - midSize, loc + midSize));
                    }
                }
            }
        }
        return sequenceEventsToRange;
    }

    /**
     * Computes the absolute vertical (Y) location for all the messages in the sequence diagram.
     * 
     * @return a map associating each message edit part to the new absolute vertical location it should have.
     */
    private Map<EventEnd, Integer> computeEndBounds(boolean pack) {
        final Map<EventEnd, Integer> result = new LinkedHashMap<>();

        if (semanticOrdering == null || semanticOrdering.isEmpty()) {
            return result;
        }

        // current y location
        int currentY = this.timeRange.getLowerBound();

        EventEnd endBefore = null;
        for (EventEnd end : semanticOrdering) {
            currentY = computeLocation(currentY, end, endBefore, pack, result);
            result.put(end, currentY);
            endBefore = end;
        }
        return result;
    }

    private int getGapFromCommonSequenceEvent(EventEnd end, Collection<ISequenceEvent> commonIses, boolean pack, int genericGap) {
        int beforeGap = genericGap;

        if (commonIses.isEmpty()) {
            return beforeGap;
        }

        ISequenceEvent commonIse = commonIses.iterator().next();
        if (commonIse instanceof Message && ((Message) commonIse).isReflective()) {
            beforeGap = LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP;
        } else if (commonIse instanceof AbstractNodeEvent) {
            beforeGap = Math.max(genericGap, getAbstractNodeEventVerticalSize(end, (AbstractNodeEvent) commonIse, commonIses, pack));
        } else if (commonIse instanceof InteractionUse) {
            beforeGap = LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT;
        } else if (commonIse instanceof Operand) {
            beforeGap = LayoutConstants.DEFAULT_OPERAND_HEIGHT;
        }
        return beforeGap;
    }

    private int getAbstractNodeEventVerticalSize(EventEnd end, AbstractNodeEvent ise, Collection<ISequenceEvent> commonIses, boolean pack) {
        int vSize = 0;
        if (pack) {
            DNode execution = (DNode) ise.getNotationView().getElement();
            int specifiedVSize = getSpecifiedVSize(execution);
            if (specifiedVSize != 0) {
                vSize = specifiedVSize;
            }
        } else if (isFlagguedByRefreshExtension(end, commonIses)) {
            Rectangle rect = oldFlaggedLayoutData.get(ise);
            vSize = rect != null ? rect.height : LayoutConstants.DEFAULT_EXECUTION_HEIGHT;
        } else {
            Range range = oldLayoutData.get(ise);
            vSize = range != null ? range.width() : LayoutConstants.DEFAULT_EXECUTION_HEIGHT;
        }

        return vSize;
    }

    private Map<ISequenceEvent, Range> computeBasicRanges(Map<EventEnd, Integer> endLocations) {
        final Map<ISequenceEvent, Range> sequenceEventsToRange = new LinkedHashMap<ISequenceEvent, Range>();
        Predicate<ISequenceEvent> notMoved = Predicates.not(Predicates.in(sequenceEventsToRange.keySet()));

        // CombinedFragments
        for (EventEnd sortedEnd : semanticOrdering) {
            Predicate<ISequenceEvent> frames = Predicates.and(notMoved, Predicates.or(Predicates.instanceOf(CombinedFragment.class), Predicates.instanceOf(InteractionUse.class)));
            for (ISequenceEvent ise : Iterables.filter(endToISequencEvents.get(sortedEnd), frames)) {
                computeFinalRange(endLocations, sequenceEventsToRange, ise);
            }
        }

        // Operands
        for (EventEnd sortedEnd : semanticOrdering) {
            Predicate<ISequenceEvent> operands = Predicates.and(notMoved, Predicates.instanceOf(Operand.class));
            for (ISequenceEvent ise : Iterables.filter(endToISequencEvents.get(sortedEnd), operands)) {
                computeFinalRange(endLocations, sequenceEventsToRange, ise);
            }
        }

        // Other sequence events
        for (EventEnd sortedEnd : semanticOrdering) {
            for (ISequenceEvent ise : Iterables.filter(endToISequencEvents.get(sortedEnd), notMoved)) {
                computeFinalRange(endLocations, sequenceEventsToRange, ise);
            }
        }
        return sequenceEventsToRange;
    }

    private void computeFinalRange(Map<EventEnd, Integer> endLocations, final Map<ISequenceEvent, Range> sequenceEventsToRange, ISequenceEvent ise) {
        Collection<EventEnd> ends = iSequenceEventsToEventEnds.get(ise);
        if (ends.size() == 2) {
            Iterator<EventEnd> it = ends.iterator();
            EventEnd start = it.next();
            EventEnd finish = it.next();

            Range newRange = getNewRange(ise, start, finish, endLocations);
            sequenceEventsToRange.put(ise, newRange);
        } else if (ends.size() == 1 && ise.isLogicallyInstantaneous() && (ise instanceof Message || EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(ends.iterator().next()))) {
            Iterator<EventEnd> it = ends.iterator();
            EventEnd middle = it.next();

            Range newRange = getNewRange(ise, middle, middle, endLocations);
            sequenceEventsToRange.put(ise, newRange);
        }
    }

    private Range getNewRange(final ISequenceEvent event, final EventEnd start, final EventEnd end, final Map<EventEnd, Integer> endLocations) {
        Range oldRange = oldLayoutData.containsKey(event) ? oldLayoutData.get(event) : Range.emptyRange();
        int lowerBound = endLocations.containsKey(start) ? endLocations.get(start) : oldRange.getLowerBound();
        int upperBound = endLocations.containsKey(end) ? endLocations.get(end) : oldRange.getUpperBound();

        if (event.isLogicallyInstantaneous() && start == end) {
            lowerBound = lowerBound - oldRange.width() / 2;
            upperBound = lowerBound + oldRange.width();
        }

        updateTimerange(upperBound);
        return new Range(lowerBound, upperBound);
    }

    private Map<ISequenceEvent, Range> computeLifelineRanges(Map<EventEnd, Integer> endLocations) {
        final Map<ISequenceEvent, Range> sequenceEventsToRange = new LinkedHashMap<ISequenceEvent, Range>();
        int endOfLife = timeRange.getUpperBound() + LayoutConstants.TIME_STOP_OFFSET;

        layoutLifelinesWithoutCreation(sequenceEventsToRange);
        layoutCreatedLifelines(endLocations, sequenceEventsToRange);
        layoutDestructedLifelines(endLocations, sequenceEventsToRange);
        layoutNonDestructedLifelines(sequenceEventsToRange, Math.max(endOfLife, LayoutConstants.LIFELINES_MIN_Y));

        return sequenceEventsToRange;
    }

    private void layoutLifelinesWithoutCreation(final Map<ISequenceEvent, Range> sequenceEventsToRange) {
        for (ISequenceEvent event : getLifeLinesWithoutCreation()) {
            Range oldRange = oldLayoutData.get(event);
            Option<Lifeline> parentLifeline = event.getLifeline();
            if (parentLifeline.some()) {
                InstanceRole instanceRole = parentLifeline.get().getInstanceRole();
                if (instanceRole != null) {
                    int newLBound = getLifelineMinLowerBound(instanceRole);
                    if (newLBound != oldRange.getLowerBound()) {
                        sequenceEventsToRange.put(event, new Range(newLBound, Math.max(newLBound, oldRange.getUpperBound())));
                    }
                }
            }
        }
    }

    private void layoutCreatedLifelines(Map<EventEnd, Integer> endLocations, final Map<ISequenceEvent, Range> sequenceEventsToRange) {
        for (Message smep : creators.values()) {
            Collection<EventEnd> ends = iSequenceEventsToEventEnds.get(smep);
            if (!ends.isEmpty()) {
                Iterator<EventEnd> it = ends.iterator();
                EventEnd first = it.next();
                if (endLocations.containsKey(first)) {
                    int endMove = endLocations.get(first);
                    int vGap = getTargetFigureMidHeight(smep);
                    Lifeline lep = smep.getTargetElement().getLifeline().get();
                    Range oldRange = sequenceEventsToRange.containsKey(lep) ? sequenceEventsToRange.get(lep) : oldLayoutData.get(lep);
                    sequenceEventsToRange.put(lep, new Range(endMove + vGap, endMove + vGap + oldRange.width()));
                }
            }
        }
    }

    private void layoutDestructedLifelines(Map<EventEnd, Integer> endLocations, final Map<ISequenceEvent, Range> sequenceEventsToRange) {
        for (Message smep : destructors.values()) {
            Collection<EventEnd> ends = iSequenceEventsToEventEnds.get(smep);
            if (!ends.isEmpty()) {
                Iterator<EventEnd> it = ends.iterator();
                EventEnd first = it.next();
                int endMove = endLocations.get(first);
                int vGap = getTargetFigureMidHeight(smep);
                int newY = endMove - vGap;
                Lifeline lep = ((EndOfLife) smep.getTargetElement()).getLifeline().get();
                Range oldRange = sequenceEventsToRange.containsKey(lep) ? sequenceEventsToRange.get(lep) : oldLayoutData.get(lep);
                sequenceEventsToRange.put(lep, new Range(oldRange.getLowerBound(), newY));
            }
        }
    }

    private void layoutNonDestructedLifelines(final Map<ISequenceEvent, Range> sequenceEventsToRange, int endOfLife) {
        // update lifeline ranges
        for (ISequenceEvent event : getLifeLinesWithoutDestruction()) {
            Range currentRange = sequenceEventsToRange.containsKey(event) ? sequenceEventsToRange.get(event) : oldLayoutData.get(event);
            if (currentRange.getUpperBound() != endOfLife) {
                sequenceEventsToRange.put(event, new Range(currentRange.getLowerBound(), endOfLife));
            }
        }
    }

    private int computeLocation(final int currentY, final EventEnd end, final EventEnd endBefore, final boolean pack, Map<EventEnd, Integer> alreadyComputedLocations) {
        int location = currentY;
        Collection<ISequenceEvent> commonIses = getCommonISequenceEvent(endBefore, end);

        if (shouldMove(commonIses)) {
            int newMinY = getMinY(endBefore, end, commonIses, pack, location, alreadyComputedLocations);
            if (pack) {
                location = newMinY;
            } else {
                // try to save position
                int oldPosition = getOldStablePosition(currentY, end);

                // don't minimize previous range
                int rangeStableY = getRangeStablePosition(currentY, end, alreadyComputedLocations);

                // don't reduce previous delta with known/flagged predecessor
                int deltaStableY = getDeltaStablePosition(currentY, end, alreadyComputedLocations);

                location = Math.max(newMinY, Math.max(oldPosition, Math.max(deltaStableY, rangeStableY)));
            }
        }
        return location;
    }

    private int getOldStablePosition(final int currentY, final EventEnd end) {
        int oldPosition = currentY;

        // Should we trust GMF positions ?
        if (flaggedEnds.contains(end) || toolCreatedEnds.contains(end)) {
            oldPosition = eventEndOldPosition.apply(end);
        }

        if (isFlagguedByRefreshExtension(end, endToISequencEvents.get(end))) {
            oldPosition = eventEndOldFlaggedPosition.apply(end);
        }

        return oldPosition;
    }

    private int getRangeStablePosition(final int currentY, final EventEnd end, Map<EventEnd, Integer> alreadyComputedLocations) {
        int rangeStabilityPos = currentY;
        Collection<ISequenceEvent> ises = endToISequencEvents.get(end);
        for (ISequenceEvent ise : ises) {
            if (!ise.isLogicallyInstantaneous()) {
                SingleEventEnd see = EventEndHelper.getSingleEventEnd(end, ise.getSemanticTargetElement().get());
                if (!see.isStart() && !(ise instanceof Message && !Iterables.isEmpty(Iterables.filter(iSequenceEventsToEventEnds.get(ise), CompoundEventEnd.class)))) {
                    int startLocation = getStartLocation(ise, alreadyComputedLocations);
                    Option<Range> oldRange = oldRangeFunction.apply(ise);

                    if (isFlagguedByRefreshExtension(end, Collections.singleton(ise))) {
                        oldRange = oldFlaggedRange.apply(ise);
                    }

                    int width = oldRange.some() ? oldRange.get().width() : 0;
                    rangeStabilityPos = Math.max(rangeStabilityPos, startLocation + width);
                }
            }
        }
        return rangeStabilityPos;
    }

    private boolean isFlagguedByRefreshExtension(EventEnd end, Collection<ISequenceEvent> ises) {
        if (flaggedEnds.contains(end)) {
            for (ISequenceEvent ise : ises) {
                Rectangle flaggedAbsoluteBounds = new ISequenceElementQuery(ise).getFlaggedAbsoluteBounds();
                if (flaggedAbsoluteBounds.x == LayoutConstants.EXTERNAL_CHANGE_FLAG.x) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shouldMove(Collection<ISequenceEvent> commonIses) {
        boolean shouldMove = true;
        if (!commonIses.isEmpty()) {
            ISequenceEvent commonIse = commonIses.iterator().next();
            shouldMove = !commonIse.isLogicallyInstantaneous();
        }

        return shouldMove;
    }

    private int getMinY(EventEnd endBefore, EventEnd end, Collection<ISequenceEvent> commonIses, boolean pack, int currentLocation, Map<EventEnd, Integer> alreadyComputedLocations) {
        int genericGap = getGenericGap(endBefore, end, pack);
        int minGap = genericGap;

        if (!commonIses.isEmpty()) {
            int commonIseGap = getGapFromCommonSequenceEvent(end, commonIses, pack, genericGap);
            minGap = commonIseGap;
        } else {
            boolean operands = Iterables.any(eventEndToSequenceEvents.apply(end), Predicates.instanceOf(Operand.class));
            if (operands) {
                minGap = getGapBeforeOperandEnd(endBefore, end, currentLocation, genericGap, alreadyComputedLocations);
            }
        }

        return currentLocation + minGap;
    }

    private int getGenericGap(EventEnd endBefore, EventEnd end, boolean pack) {
        int beforeGap = 0;

        if (endBefore != null) {
            Collection<ISequenceEvent> endBeforeEvents = eventEndToSequenceEvents.apply(endBefore);
            beforeGap = pack ? LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP : LayoutConstants.EXECUTION_CHILDREN_MARGIN;

            // Predecessor : Logically instantaneouse States
            Iterable<State> predStates = Iterables.filter(endBeforeEvents, State.class);
            if (EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(endBefore) && endBeforeEvents.size() == 1 && Iterables.size(predStates) == 1) {
                State predState = Iterables.getOnlyElement(predStates);
                if (predState.isLogicallyInstantaneous()) {
                    beforeGap += getAbstractNodeEventVerticalSize(endBefore, predState, endBeforeEvents, pack) / 2;
                }
            }

            if (Iterables.any(endBeforeEvents, Predicates.instanceOf(InteractionUse.class)) && endBefore instanceof SingleEventEnd && ((SingleEventEnd) endBefore).isStart()) {
                beforeGap = LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT / 2;
            }

            if (Iterables.any(eventEndToSequenceEvents.apply(end), Predicates.instanceOf(InteractionUse.class)) && end instanceof SingleEventEnd && !((SingleEventEnd) end).isStart()) {
                beforeGap = LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT / 2;
            }

            if (creators.keySet().contains(endBefore)) {
                if (pack) {
                    beforeGap += getTargetFigureMidHeight(creators.get(endBefore)) + LayoutConstants.TIME_START_OFFSET - LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
                } else {
                    beforeGap += getTargetFigureMidHeight(creators.get(endBefore));
                }
            } else if (losts.containsKey(endBefore)) {
                beforeGap += losts.get(endBefore).getBounds().height / 2;
            }
        } else {
            beforeGap = pack ? LayoutConstants.TIME_START_OFFSET : LayoutConstants.TIME_START_MIN_OFFSET;
        }

        if (destructors.keySet().contains(end)) {
            beforeGap += getTargetFigureMidHeight(destructors.get(end));
        } else if (losts.containsKey(end)) {
            beforeGap += losts.get(end).getBounds().height / 2;
        }

        // current event : Logically instantaneouse States
        Collection<ISequenceEvent> endEvents = eventEndToSequenceEvents.apply(end);
        Iterable<State> states = Iterables.filter(endEvents, State.class);
        if (EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(end) && endEvents.size() == 1 && Iterables.size(states) == 1) {
            State state = Iterables.getOnlyElement(states);
            if (state.isLogicallyInstantaneous()) {
                beforeGap += getAbstractNodeEventVerticalSize(endBefore, state, endEvents, pack) / 2;
            }
        }
        return beforeGap;
    }

    private int getGapBeforeOperandEnd(EventEnd endBefore, EventEnd end, int currentLocation, int genericGap, Map<EventEnd, Integer> alreadyComputedLocations) {
        int beforeGap = genericGap;
        Iterable<Operand> operands = Iterables.filter(eventEndToSequenceEvents.apply(end), Operand.class);
        if (!Iterables.isEmpty(operands) && endBefore instanceof SingleEventEnd) {
            if (Iterables.any(eventEndToSequenceEvents.apply(endBefore), Predicates.instanceOf(CombinedFragment.class)) && ((SingleEventEnd) endBefore).isStart()) {
                beforeGap = LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT;
            } else {
                Operand op = selectEndedOperand(end, operands);
                if (op != null) {
                    int startLoc = getStartLocation(op, alreadyComputedLocations);
                    int minEndLoc = startLoc + LayoutConstants.DEFAULT_OPERAND_HEIGHT;
                    beforeGap = Math.max(minEndLoc - currentLocation, genericGap);
                }
            }
        }
        return beforeGap;
    }

    private Operand selectEndedOperand(EventEnd end, Iterable<Operand> operands) {
        Operand op = null;
        if (end instanceof CompoundEventEnd) {
            for (SingleEventEnd see : ((CompoundEventEnd) end).getEventEnds()) {
                if (!see.isStart()) {
                    EObject semanticEvent = see.getSemanticEvent();
                    for (Operand opp : operands) {
                        EObject eObject = opp.getSemanticTargetElement().get();
                        if (semanticEvent != null && semanticEvent.equals(eObject)) {
                            op = opp;
                        }
                    }
                }
            }
        }
        return op;
    }

    private int getStartLocation(ISequenceEvent ise, Map<EventEnd, Integer> alreadyComputedLocations) {
        Collection<EventEnd> ends = iSequenceEventsToEventEnds.get(ise);
        for (EventEnd end : ends) {
            SingleEventEnd see = EventEndHelper.getSingleEventEnd(end, ise.getSemanticTargetElement().get());
            if (see.isStart() && alreadyComputedLocations.containsKey(end)) {
                return alreadyComputedLocations.get(end);
            }
        }
        return 0;
    }

    private boolean layoutUnconnectedLostMessageEnd() {
        boolean applied = false;
        for (LostMessageEnd lme : unconnectedLostEnds) {
            if (createdFromTool(lme)) {
                ISequenceElementQuery query = new ISequenceElementQuery(lme);
                int y = query.getFlaggedAbsoluteBounds().y;
                if (y != -1) {
                    LayoutConstraint layoutConstraint = lme.getNotationNode().getLayoutConstraint();
                    if (layoutConstraint instanceof Location) {
                        Rectangle bounds = lme.getProperLogicalBounds();
                        ((Location) layoutConstraint).setY(y - bounds.height / 2);
                        applied = true;
                    }
                }
            }
        }
        return applied;
    }

    private boolean layoutInteractionContainer() {
        boolean applied = false;
        Optional<InteractionContainer> optionalInteractionContainer = this.sequenceDiagram.getInteractionContainer();
        if (optionalInteractionContainer.isPresent()) {
            // Reset height of the interaction container
            Rectangle rectangle = new Rectangle(0, 0, InteractionContainer.DEFAULT_WIDTH, InteractionContainer.DEFAULT_HEIGHT);
            for (InstanceRole instanceRole : this.sequenceDiagram.getAllInstanceRoles()) {
                // Gather instance role bounds
                final Node instanceRoleNode = instanceRole.getNotationNode();
                LayoutConstraint layoutConstraint = instanceRoleNode.getLayoutConstraint();
                // Check that the lifeline is within bounds of the interaction container, resize it otherwise
                if (layoutConstraint instanceof Bounds instanceRoleBounds && instanceRole.getLifeline().some()) {
                    Lifeline lifeline = instanceRole.getLifeline().get();
                    Option<EndOfLife> endOfLife = lifeline.getEndOfLife();
                    Node lifelineNode = lifeline.getNotationNode();

                    int top = instanceRoleBounds.getY();
                    if (top - InteractionContainer.MARGIN < rectangle.y()) {
                        rectangle.setY(top - InteractionContainer.MARGIN);
                    }

                    int bottom = lifeline.getVerticalRange().getUpperBound();
                    if (endOfLife.some()) {
                        bottom = bottom + endOfLife.get().getProperLogicalBounds().height;
                    }
                    // lifeline position is relative to the parent instance role position
                    if (bottom + InteractionContainer.MARGIN > rectangle.bottom()) {
                        rectangle.setBottom(bottom + InteractionContainer.MARGIN);
                    }
                }
            }

            InteractionContainer interactionContainer = optionalInteractionContainer.get();
            Node node = interactionContainer.getNotationNode();
            LayoutConstraint interactionContainerLayoutConstraint = node.getLayoutConstraint();
            if (interactionContainerLayoutConstraint instanceof Bounds interactionContainerBounds) {
                interactionContainerBounds.setY(rectangle.y);
                interactionContainerBounds.setHeight(rectangle.height);
                applied = true;
            }
        }
        return applied;

    }

    private void initSortedEventEnds(boolean pack) {
        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagram.getNotationDiagram().getElement();
        graphicalOrdering.addAll(sequenceDDiagram.getGraphicalOrdering().getEventEnds());
        semanticOrdering.addAll(sequenceDDiagram.getSemanticOrdering().getEventEnds());
    }

    private void initLifelinesOldLayoutData() {
        Collection<Lifeline> lifelines = new ArrayList<Lifeline>();
        lifelines.addAll(sequenceDiagram.getAllLifelines());
        for (ISequenceEvent ise : lifelines) {
            oldLayoutData.put(ise, getOldLayoutData(ise));
        }
    }

    private void lookForUnconnectedLostEnd() {
        Collection<LostMessageEnd> allLostMessageEnds = new ArrayList<>(sequenceDiagram.getAllLostMessageEnds());
        Collection<LostMessageEnd> discoveredLostEnds = new ArrayList<>();
        for (Message knownMsgs : Iterables.filter(iSequenceEventsToEventEnds.keySet(), Message.class)) {
            ISequenceNode sourceElement = knownMsgs.getSourceElement();
            if (sourceElement instanceof LostMessageEnd) {
                discoveredLostEnds.add((LostMessageEnd) sourceElement);
            }

            ISequenceNode targetElement = knownMsgs.getTargetElement();
            if (targetElement instanceof LostMessageEnd) {
                discoveredLostEnds.add((LostMessageEnd) targetElement);
            }
        }

        Iterables.removeAll(allLostMessageEnds, discoveredLostEnds);

        unconnectedLostEnds.addAll(allLostMessageEnds);
    }

    /**
     * Determines the range of absolute Y locations in which the messages can be laid out.
     * 
     * @param pack
     *            packing layout if true.
     * @return
     */
    protected void initTimeBounds(boolean pack) {
        int minTimeBounds = getMinTimeBounds();
        int startTime = minTimeBounds;
        int endTime = getMaxTimeBounds(pack, minTimeBounds) - LayoutConstants.TIME_STOP_OFFSET;
        this.timeRange = new Range(startTime, Math.max(startTime, endTime));
    }

    private int getMaxTimeBounds(boolean pack, int minTimeBounds) {
        int max = getSpecifiedMaxTimeBounds(minTimeBounds);

        if (!pack) {
            Iterable<Lifeline> lifelinesWithoutDestruction = getLifeLinesWithoutDestruction();

            // Avoid to handle lifelines to move up for max computation.
            Predicate<Lifeline> isMaxRangeCandidate = new Predicate<Lifeline>() {
                @Override
                public boolean apply(Lifeline input) {
                    InstanceRole irep = input.getInstanceRole();
                    if (irep != null) {
                        return irep.getBounds().getLocation().y <= LayoutConstants.LIFELINES_START_Y;
                    }
                    return false;
                }
            };

            Collection<Lifeline> lifelinesToConsider = Lists.newArrayList(Iterables.filter(lifelinesWithoutDestruction, isMaxRangeCandidate));
            Ordering<ISequenceEvent> maxOrdering = Ordering.natural().onResultOf(Functions.compose(RangeHelper.upperBoundFunction(), ISequenceEvent.VERTICAL_RANGE));
            if (!lifelinesToConsider.isEmpty()) {
                Lifeline lep = maxOrdering.max(lifelinesToConsider);
                max = lep.getVerticalRange().getUpperBound();
            }
        }

        return max;
    }

    private int getSpecifiedMaxTimeBounds(int minTimeBounds) {
        List<Lifeline> allLifelines = sequenceDiagram.getAllLifelines();
        int timeBounds = LayoutConstants.LIFELINES_MIN_Y;

        for (Lifeline lep : allLifelines) {
            DDiagramElement dde = (DDiagramElement) lep.getNotationNode().getElement();
            if (dde instanceof DNode && Lifeline.viewpointElementPredicate().apply(dde)) {
                DNode node = (DNode) dde;
                int specifiedVSize = getSpecifiedVSize(node);
                int endOfLifeVsize = getSpecifiedEndOfLifeVSize(node);
                timeBounds = Math.max(LayoutConstants.LIFELINES_MIN_Y, minTimeBounds + specifiedVSize - endOfLifeVsize / 2);
            }
        }

        return timeBounds;
    }

    private int getSpecifiedEndOfLifeVSize(DNode node) {
        int endOfLifeVsize = 0;

        List<DNode> endOfLifes = Lists.newArrayList(Iterables.filter(node.getOwnedBorderedNodes(), new Predicate<DNode>() {
            @Override
            public boolean apply(DNode input) {
                return input.isVisible() && EndOfLife.viewpointElementPredicate().apply(input);
            }
        }));

        if (!endOfLifes.isEmpty()) {
            endOfLifeVsize = getSpecifiedVSize(endOfLifes.iterator().next());
        }
        return endOfLifeVsize;
    }

    /**
     * Return the specified size of the given node.
     * 
     * @param node
     *            the given node.
     * @return the specified height of a {@link DNode}.
     */
    protected int getSpecifiedVSize(DNode node) {
        return new DNodeQuery(node).getDefaultDimension().height;
    }

    private int getMinTimeBounds() {
        int min = 2 * LayoutConstants.LIFELINES_START_Y;
        Iterable<Lifeline> lifelinesWithoutCreation = getLifeLinesWithoutCreation();

        // Avoid to handle lifelines to move up for min computation.
        Predicate<Lifeline> isMinRangeCandidate = new Predicate<Lifeline>() {
            @Override
            public boolean apply(Lifeline input) {
                InstanceRole irep = input.getInstanceRole();
                if (irep != null) {
                    return irep.getBounds().getLocation().y <= LayoutConstants.LIFELINES_START_Y;
                }
                return false;
            }
        };

        Collection<Lifeline> lifelinesToConsider = Lists.newArrayList(Iterables.filter(lifelinesWithoutCreation, isMinRangeCandidate));
        if (!lifelinesToConsider.isEmpty()) {
            Lifeline lep = heightOrdering.max(lifelinesToConsider);
            min = LayoutConstants.LIFELINES_START_Y + instanceRoleHeight.apply(lep);
        }
        return min;
    }

    /**
     * Get the middle height of the given message's targeted figure.
     * 
     * @param mover
     *            the given message.
     * @return the middle height.
     */
    protected int getTargetFigureMidHeight(Message mover) {
        int midHeight = 0;
        if (mover != null && mover.getTargetElement() != null) {
            midHeight = mover.getTargetElement().getBounds().height / 2;
        }
        return midHeight;
    }

    /**
     * Increase the time range to the given upperBound. If the current upper bounds is bigger than the parameter, it
     * does nothing.
     * 
     * @param upperBound
     *            the new minimum upperBound.
     */
    protected void updateTimerange(int upperBound) {
        if (upperBound > timeRange.getUpperBound()) {
            timeRange = new Range(timeRange.getLowerBound(), upperBound);
        }
    }

    /**
     * Return the minimum valid lower start time on the {@link Lifeline} of the given {@link InstanceRole}.
     * 
     * @param irep
     *            the current {@link InstanceRole}.
     * @return the lifeline minimum valid lower bound.
     */
    protected int getLifelineMinLowerBound(final InstanceRole irep) {
        int vGap = LayoutConstants.LIFELINES_START_Y;
        vGap += irep.getBounds().height;
        return vGap;
    }

    /**
     * Register event old and init context (ends, old layout data, previous bounds flag, creators, destructors, ...).
     */
    protected void registerEventEnds() {
        for (EventEnd end : new ArrayList<EventEnd>(semanticOrdering)) {
            registerEventEnd(end);
        }
        Collections.sort(flaggedEnds, Ordering.natural().onResultOf(eventEndOldFlaggedPosition));
    }

    private void registerEventEnd(EventEnd end) {
        Collection<EObject> semanticEvents = EventEndHelper.getSemanticEvents(end);
        Collection<ISequenceEvent> eventParts = new LinkedHashSet<>();
        for (EObject semanticEvent : semanticEvents) {
            eventParts.addAll(ISequenceElementAccessor.getEventsForSemanticElement(sequenceDiagram, semanticEvent));
        }

        // ISequenceEvent has not been created
        if (eventParts.isEmpty()) {
            Collection<DDiagramElement> ddes = new LinkedHashSet<>();
            for (EObject semanticEvent : semanticEvents) {
                ddes.addAll(ISequenceElementAccessor.getDiagramElementsForSemanticElement(sequenceDiagram, semanticEvent));
            }

            // No ISequenceEvent has been created but DDiagramElement exists,
            // gmf refresh did not occurs, abort
            // current layout.
            if (!ddes.isEmpty()) {
                semanticOrdering.clear();
                graphicalOrdering.clear();
            }
        }

        boolean flagged = false;
        boolean toolCreated = false;
        boolean toolSemanticCreated = false;
        boolean lost = false;
        for (ISequenceEvent ise : eventParts) {
            Range oldData = getOldLayoutData(ise);
            oldLayoutData.put(ise, oldData);
            endToISequencEvents.put(end, ise);
            iSequenceEventsToEventEnds.put(ise, end);

            ISequenceElementQuery query = new ISequenceElementQuery(ise);
            if (query.hasAbsoluteBoundsFlag()) {
                Rectangle flaggedAbsoluteBounds = query.getFlaggedAbsoluteBounds();
                if (LayoutConstants.TOOL_CREATION_FLAG.equals(flaggedAbsoluteBounds)) {
                    toolCreated = true;
                } else if (LayoutConstants.TOOL_CREATION_FLAG_FROM_SEMANTIC.equals(flaggedAbsoluteBounds)) {
                    toolSemanticCreated = true;
                } else {
                    if (flaggedAbsoluteBounds.height == -1) {
                        // Correct auto-size
                        flaggedAbsoluteBounds.setHeight(0);
                    }
                    oldFlaggedLayoutData.put(ise, flaggedAbsoluteBounds);
                    flagged = true;
                }
            }

            if (ise instanceof Message) {
                Message smep = (Message) ise;
                ISequenceNode targetElement = smep.getTargetElement();
                if (targetElement instanceof InstanceRole) {
                    creators.put(end, smep);
                } else if (targetElement instanceof EndOfLife) {
                    destructors.put(end, smep);
                } else if (targetElement instanceof LostMessageEnd) {
                    lost = true;
                    losts.put(end, (LostMessageEnd) targetElement);
                }

                ISequenceNode sourceElement = smep.getSourceElement();
                if (sourceElement instanceof LostMessageEnd) {
                    lost = true;
                    losts.put(end, (LostMessageEnd) sourceElement);
                }
            }
        }

        if (flagged && !toolCreated) {
            flaggedEnds.add(end);
        } else if (isSafeToolCreation(end)) {
            if (toolCreated) {
                toolCreatedEnds.add(end);
            } else if (toolSemanticCreated && ((end instanceof SingleEventEnd && ((SingleEventEnd) end).isStart()) || lost)) {
                toolCreatedEnds.add(end);
            }
        }
    }

    private boolean isSafeToolCreation(EventEnd end) {
        boolean safe = !(end instanceof CompoundEventEnd);
        safe = safe || EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(end);
        for (Message msg : Iterables.filter(endToISequencEvents.get(end), Message.class)) {
            safe = safe || msg.getSourceElement() instanceof LostMessageEnd || msg.getTargetElement() instanceof LostMessageEnd;
        }

        return safe;
    }

    /**
     * Get the common {@link ISequenceEvent} between the given ends.
     * 
     * @param end1
     *            a first {@link EventEnd}
     * @param end2
     *            a second {@link EventEnd}
     * @return the common events between given ends
     */
    protected Collection<ISequenceEvent> getCommonISequenceEvent(EventEnd end1, EventEnd end2) {
        if (end1 == null || end2 == null) {
            return Collections.<ISequenceEvent> emptyList();
        }
        Collection<ISequenceEvent> ises1 = endToISequencEvents.get(end1);
        Collection<ISequenceEvent> ises2 = endToISequencEvents.get(end2);
        Collection<ISequenceEvent> commonIses = new ArrayList<ISequenceEvent>(ises2);
        Iterables.retainAll(commonIses, ises1);
        return commonIses;
    }

    @Override
    protected Function<EventEnd, Integer> getOldPosition() {
        return eventEndOldPosition;
    }

    @Override
    protected Function<EventEnd, Integer> getOldFlaggedPosition() {
        return eventEndOldFlaggedPosition;
    }
}
