/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * .
 * 
 * @author mporhel
 * 
 */
public final class SubEventsHelper {

    private static Collection<Class<?>> types = new ArrayList<>();
    {
        types.add(Execution.class);
        types.add(Lifeline.class);
        types.add(Operand.class);
    }

    private ISequenceEvent parentEvent;

    private Range parentRange;

    private final Multimap<ISequenceEvent, Lifeline> coverage = LinkedHashMultimap.create();

    /**
     * Default constructor.
     * 
     * @param event
     *            a supported {@link ISequenceEvent} : {@linkLifeline}, {@link AbstractNodeEvent}, {@link Operand}.
     */
    public SubEventsHelper(ISequenceEvent event) {
        Preconditions.checkArgument(types.contains(event.getClass()));
        Preconditions.checkNotNull(event);
        this.parentEvent = event;
        this.parentRange = event.getVerticalRange();
    }

    /**
     * Common implementation of {@link ISequenceEvent#getSubEvents()}.
     * 
     * @return the sub-events of the (root) execution, ordered by their starting position (graphically) from top to
     *         bottom.
     */
    public List<ISequenceEvent> getSubEvents() {
        Collection<ISequenceEvent> subEvents = CacheHelper.getSubEventsCache().get(parentEvent);
        if (subEvents != null) {
            return new ArrayList<ISequenceEvent>(subEvents);
        }

        List<ISequenceEvent> result = getValidSubEvents();
        Collections.sort(result, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));

        if (CacheHelper.isStructuralCacheEnabled()) {
            CacheHelper.getSubEventsCache().put(parentEvent, new ArrayList<>(result));
        }
        return result;
    }

    /**
     * Compute childs of Execution/Operand/Lifeline.
     * 
     * @return the sub events.
     */
    private List<ISequenceEvent> getValidSubEvents() {
        List<ISequenceEvent> childrenEvents = new ArrayList<>();

        Set<ISequenceEvent> localParents = new LinkedHashSet<>();
        Set<Lifeline> coveredLifelines = new LinkedHashSet<>();
        if (parentEvent instanceof AbstractNodeEvent || parentEvent instanceof Lifeline) {
            localParents.add(parentEvent);
            coveredLifelines.add(parentEvent.getLifeline().get());
        } else if (parentEvent instanceof Operand) {
            Operand op = (Operand) parentEvent;
            CombinedFragment parentCombinedFragment = op.getCombinedFragment();
            coveredLifelines.addAll(getCoverage(parentCombinedFragment));
            localParents.addAll(getCarryingParents(parentCombinedFragment, coveredLifelines));
        }

        Set<ISequenceEvent> hierarchicalChildren = getNotationDirectChildrenInParentRange(localParents);
        Set<ISequenceEvent> frameChildren = getFrameChildrenInParentRange(coveredLifelines);

        childrenEvents.addAll(getTopLevelEvents(hierarchicalChildren, frameChildren, coveredLifelines));
        childrenEvents.addAll(getTopLevelEvents(frameChildren, childrenEvents, coveredLifelines));

        return childrenEvents;
    }

    private Collection<Lifeline> getCoverage(ISequenceEvent ise) {
        if (coverage.containsKey(ise)) {
            return coverage.get(ise);
        }

        Option<Lifeline> lifeline = ise.getLifeline();
        Collection<Lifeline> coveredLifelines = new LinkedHashSet<>();
        if (lifeline.some()) {
            coveredLifelines.add(lifeline.get());
        } else if (ise instanceof Operand) {
            coveredLifelines.addAll(getCoverage(((Operand) ise).getCombinedFragment()));
        } else if (ise instanceof AbstractFrame) {
            coveredLifelines.addAll(((AbstractFrame) ise).computeCoveredLifelines());
        } else if (ise instanceof Message) {
            Message msg = (Message) ise;
            Option<Lifeline> sourceLifeline = msg.getSourceLifeline();
            if (sourceLifeline.some()) {
                coveredLifelines.add(sourceLifeline.get());
            }
            Option<Lifeline> targetLifeline = msg.getTargetLifeline();
            if (targetLifeline.some()) {
                coveredLifelines.add(targetLifeline.get());
            }
        }
        coverage.putAll(ise, coveredLifelines);
        return coveredLifelines;
    }

    private Collection<ISequenceEvent> getCarryingParents(AbstractFrame frame, Set<Lifeline> coveredLifelines) {
        Set<ISequenceEvent> coveredEvents = new LinkedHashSet<>();
        for (Lifeline lifeline : coveredLifelines) {
            EventFinder localParentFinder = new EventFinder(lifeline);
            localParentFinder.setReparent(true);
            localParentFinder.setEventsToIgnore(Predicates.equalTo((ISequenceEvent) frame));
            ISequenceEvent localParent = localParentFinder.findMostSpecificEvent(frame.getVerticalRange());
            if (localParent != null) {
                coveredEvents.add(localParent);
            }
        }
        return coveredEvents;
    }

    /**
     * Look for execution and lifeline
     * 
     * @param localParents
     * @return
     */
    private Set<ISequenceEvent> getNotationDirectChildrenInParentRange(Collection<ISequenceEvent> localParents) {
        Collection<View> childViews = new LinkedHashSet<>();
        Collection<View> parentConnections = new HashSet<>();
        Set<ISequenceEvent> childrenEvents = new LinkedHashSet<>();

        for (ISequenceEvent ise : localParents) {
            View notationView = ise.getNotationView();
            childViews.addAll(Lists.newArrayList(Iterables.filter(notationView.getChildren(), View.class)));

            Collection<View> sourceEdges = Lists.newArrayList(Iterables.filter(notationView.getSourceEdges(), View.class));
            Collection<View> targetEdges = Lists.newArrayList(Iterables.filter(notationView.getTargetEdges(), View.class));

            childViews.addAll(sourceEdges);
            childViews.addAll(targetEdges);

            // In some resize cases, edges could appears to be out of their
            // source/target ranges
            if (ise == parentEvent) {
                parentConnections.addAll(sourceEdges);
                parentConnections.addAll(targetEdges);
            }
        }

        for (View view : childViews) {
            Option<ISequenceEvent> iSequenceEvent = ISequenceElementAccessor.getISequenceEvent(view);
            if (iSequenceEvent.some()) {
                ISequenceEvent ise = iSequenceEvent.get();
                if (parentConnections.contains(view) || parentRange.includes(ise.getVerticalRange())) {
                    childrenEvents.add(iSequenceEvent.get());
                }
            }
        }

        return Sets.newLinkedHashSet(EventEndHelper.getIndependantEvents(parentEvent, childrenEvents));
    }

    private Set<ISequenceEvent> getFrameChildrenInParentRange(Set<Lifeline> coveredLifelines) {
        Set<ISequenceEvent> childrenEvents = new HashSet<>();
        SequenceDiagram diagram = parentEvent.getDiagram();
        Set<AbstractFrame> frames = new HashSet<>();
        frames.addAll(diagram.getAllFrames());

        for (AbstractFrame frame : frames) {
            Range frameRange = frame.getVerticalRange();
            if (parentRange.includes(frameRange) && validCoverage(frame, coveredLifelines)) {
                childrenEvents.add(frame);
            }
        }
        return getTopLevelEvents(childrenEvents, childrenEvents, coveredLifelines);
    }

    private Set<ISequenceEvent> getTopLevelEvents(Set<ISequenceEvent> events, Collection<ISequenceEvent> potentialParents, Set<Lifeline> coveredLifelines) {
        HashSet<ISequenceEvent> topLevel = new LinkedHashSet<>();
        boolean parentFrames = Iterables.size(Iterables.filter(potentialParents, AbstractFrame.class)) != 0;

        for (ISequenceEvent event : events) {
            final Range verticalRange = event.getVerticalRange();
            final ISequenceEvent potentialChild = event;

            Predicate<ISequenceEvent> isParentOfCurrent = new Predicate<ISequenceEvent>() {
                @Override
                public boolean apply(ISequenceEvent input) {
                    Range inputRange = input.getVerticalRange();
                    boolean isParent = inputRange.includes(verticalRange);
                    return isParent && input != potentialChild;
                }
            };

            Iterable<ISequenceEvent> parents = Iterables.filter(potentialParents, isParentOfCurrent);
            if (Iterables.isEmpty(parents)) {
                topLevel.add(potentialChild);
            } else if (potentialChild instanceof AbstractFrame && !parentFrames) {
                Collection<ISequenceEvent> carriers = new ArrayList<ISequenceEvent>(getCarryingParents((AbstractFrame) potentialChild, coveredLifelines));
                List<ISequenceEvent> parentsList = Lists.newArrayList(parents);
                Iterables.removeAll(carriers, parentsList);
                if (!carriers.isEmpty()) {
                    topLevel.add(potentialChild);
                }
            }
        }
        return topLevel;
    }

    private boolean validCoverage(AbstractFrame frame, Set<Lifeline> parentCoveredLifelines) {
        boolean result = false;
        Collection<Lifeline> coveredLifelines = getCoverage(frame);
        if (parentEvent instanceof Operand) {
            result = parentCoveredLifelines.containsAll(coveredLifelines);
        } else {
            result = coveredLifelines.contains(parentEvent.getLifeline().get());
        }

        return result;
    }

    /**
     * Implementation of {@link ISequenceEvent#canChildOccupy(ISequenceEvent, Range)} .
     * 
     * @param child
     *            the child.
     * @param range
     *            the vertical range to test.
     * @return <code>true</code> if the child can be placed anywhere inside the specified vertical range (including
     *         occupying the whole range).
     */
    public boolean canChildOccupy(ISequenceEvent child, Range range) {
        return canChildOccupy(child, range, null, child == null ? getCoverage(parentEvent) : getCoverage(child));
    }

    /**
     * Implementation of {@link ISequenceEvent#canChildOccupy(ISequenceEvent, Range)} .
     * 
     * @param child
     *            the child, if child is null it means that it is a insertion point request from a CreationTool.
     * @param range
     *            the vertical range to test.
     * @param eventsToIgnore
     *            the list of events to ignore while compute canChildOccupy.
     * @param lifelines
     *            lifelines to inspect
     * @return <code>true</code> if the child can be placed anywhere inside the specified vertical range (including
     *         occupying the whole range).
     */
    public boolean canChildOccupy(ISequenceEvent child, final Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines) {
        boolean result = true;
        if (!parentEvent.getValidSubEventsRange().includes(range)) {
            result = false;
        } else {
            for (ISequenceEvent event : getSequenceEventsToFilter(parentEvent, child, range, lifelines)) {
                Range eventRange = event.getVerticalRange();
                if (eventsToIgnore != null && eventsToIgnore.contains(event)) {
                    // do nothing
                } else if (event instanceof Message) {
                    Message msg = (Message) event;
                    if (!checkOverlapWithSiblingMessage(child, range, msg, eventRange)) {
                        result = false;
                        break;
                    }
                } else if (eventRange.intersects(range) || !range.validatesBoundsAreDifferent(eventRange)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private boolean checkOverlapWithSiblingMessage(ISequenceEvent child, Range childRange, Message msg, Range msgRange) {
        boolean result = true;
        // if event is a SequenceMessageEditPart reconnect it to the
        // moved child
        // only if event is a simple message or if full range of its
        // associated Execution
        // with the associated messages (call and return) is included in
        // the future range of the child being dropped
        if (child instanceof State) {
            // A state can not have an overlapping message
            result = !childRange.includesAtLeastOneBound(msgRange);
        } else if (msg.isReflective()) {
            if (msgRange.intersects(childRange) && (childRange.getLowerBound() <= msgRange.getLowerBound() || childRange.getUpperBound() >= msgRange.getUpperBound())) {
                result = false;
            }
        } else {
            ISequenceNode sourceEvent = msg.getSourceElement();
            ISequenceNode targetEvent = msg.getTargetElement();
            Execution parentExecEvent = null;
            if (sourceEvent instanceof Execution) {
                Execution sourceExec = (Execution) sourceEvent;
                if (msg.equals(sourceExec.getEndMessage().get())) {
                    parentExecEvent = sourceExec;
                }
            }

            if (targetEvent instanceof Execution) {
                Execution targetExec = (Execution) targetEvent;
                if (msg.equals(targetExec.getStartMessage().get())) {
                    parentExecEvent = targetExec;
                }
            }

            if (parentExecEvent != null) {
                Range verticalRange = parentExecEvent.getVerticalRange();
                if (child != null && (!childRange.validatesBoundsAreDifferent(verticalRange) || !childRange.includes(verticalRange) && !verticalRange.includes(childRange))) {
                    result = false;
                }
            }
        }
        return result;
    }

    private Iterable<ISequenceEvent> getSequenceEventsToFilter(ISequenceEvent self, ISequenceEvent child, final Range range, final Collection<Lifeline> lifelines) {
        Set<ISequenceEvent> result = new HashSet<ISequenceEvent>(self.getSubEvents());
        Predicate<ISequenceEvent> inRangePredicate = new Predicate<ISequenceEvent>() {

            @Override
            public boolean apply(ISequenceEvent input) {
                Range inputRange = input.getVerticalRange();
                return range.includesAtLeastOneBound(inputRange) || new ISequenceEventQuery(input).isReflectiveMessage() && inputRange.includesAtLeastOneBound(range);
            }

        };
        Predicate<ISequenceEvent> inCoverage = new Predicate<ISequenceEvent>() {

            @Override
            public boolean apply(ISequenceEvent input) {
                Collection<Lifeline> inputCoverage = new ArrayList<Lifeline>(getCoverage(input));
                return Iterables.removeAll(inputCoverage, lifelines);
            }

        };

        @SuppressWarnings("unchecked")
        Predicate<ISequenceEvent> predicateFilter = Predicates.and(Predicates.not(Predicates.equalTo(child)), inRangePredicate, inCoverage);
        return Iterables.filter(result, predicateFilter);
    }
}
