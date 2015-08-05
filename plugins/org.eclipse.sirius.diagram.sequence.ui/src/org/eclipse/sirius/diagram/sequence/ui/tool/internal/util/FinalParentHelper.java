/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class FinalParentHelper {
    private final AbstractNodeEvent self;

    private final RequestQuery request;

    private Range expansionZone;

    private ISequenceEvent globalFinalParent;

    /**
     * Constructor.
     * 
     * @param self
     *            the execution which will be resized.
     * @param resizeRequest
     *            the resize query.
     */
    public FinalParentHelper(AbstractNodeEvent self, RequestQuery resizeRequest) {
        this.self = Preconditions.checkNotNull(self);
        this.request = Preconditions.checkNotNull(resizeRequest);
        Preconditions.checkArgument(resizeRequest.isResize());
    }

    /**
     * Determines what event will be the direct parent of this execution after
     * the execution of the specified request. Returns <code>null</code> if the
     * request is invalid. Calling this method also sets the
     * <code>expansionZone</code> field: it is set to <code>null</code> if the
     * returned parent can accept this execution directly, or to the vertical
     * range which needs to be expanded on the future parent if there is not yet
     * enough room to contain this execution.
     */
    public void computeFinalParent() {
        Range fullFinalRange = getFullFinalRange();
        if (fullFinalRange != null) {
            if (isInvalidInteractionInsideOperand(fullFinalRange)) {
                globalFinalParent = null;
            } else if (request.isResize()) {
                globalFinalParent = getFinalParentOnResize(fullFinalRange);
            }
        }
    }

    /**
     * Validates if the execution resize is valid if located in an operand.
     * 
     * @param fullFinalRange
     *            the new range of the resized {@link ISequenceEvent}
     * @return if the execution resize is valid if located in an operand.
     */
    private boolean isInvalidInteractionInsideOperand(Range fullFinalRange) {
        boolean result = false;
        Option<Operand> parentOperand = self.getParentOperand();
        if (request.isResize()) {
            result = parentOperand.some() && !parentOperand.get().getVerticalRange().includes(fullFinalRange);
        }
        return result;
    }

    /**
     * Returns the computed final parent of the execution after the resize.
     * 
     * @return the final parent of the execution after the resize.
     */
    public ISequenceEvent getFinalParent() {
        return globalFinalParent;
    }

    /**
     * Returns the auto-expansion required before the resize.
     * 
     * @return the auto-expansion required before the resize.
     */
    public Range getRequiredExpansion() {
        return expansionZone;
    }

    /**
     * Computes the full final range which would be occupied by this execution
     * and any of the elements resized along with it if we accept the specified
     * request.
     * 
     * @param request
     *            the request for changing the size/location of this execution
     * @return the final vertical range of the execution and its linked messages
     *         (if any) if we accept the request.
     */
    private Range getFullFinalRange() {
        Rectangle newBounds = request.getLogicalTransformedRectangle(self.getProperLogicalBounds());
        if (newBounds.width < 0 || newBounds.height < 0) {
            return null;
        }
        Range fullFinalRange = RangeHelper.verticalRange(newBounds);

        if (self instanceof Execution) {
            Execution execution = (Execution) self;

            Option<Message> startMessage = execution.getStartMessage();
            if (startMessage.some() && !startMessage.get().surroundsEventOnSameLifeline()) {
                int startY = fullFinalRange.getLowerBound() - startMessage.get().getVerticalRange().width();
                fullFinalRange = new Range(startY, fullFinalRange.getUpperBound());
            }
            Option<Message> endMessage = execution.getEndMessage();
            if (endMessage.some() && !endMessage.get().surroundsEventOnSameLifeline()) {
                int finishY = fullFinalRange.getUpperBound() + endMessage.get().getVerticalRange().width();
                fullFinalRange = new Range(fullFinalRange.getLowerBound(), finishY);
            }
        }

        return fullFinalRange;
    }

    private ISequenceEvent getFinalParentOnResize(final Range fullFinalRange) {
        Preconditions.checkArgument(request.isResize());
        final Collection<ISequenceEvent> remoteErrors = Sets.newHashSet();

        /*
         * A simple resizing can not change the parent of an execution.
         */
        ISequenceEvent finalParent = self.getParentEvent();
        /*
         * We must still check that the resizing is valid, in that it will not
         * cause an overlap/conflict with sibling events.
         */
        final Collection<ISequenceEvent> linkedSiblings = FinalParentHelper.computeLinkedSiblings(request);
        Iterable<ISequenceEvent> finalSiblings = EventEndHelper.getIndependantEvents(self, finalParent.getSubEvents());

        final Option<Lifeline> selfLifeline = self.getLifeline();
        Predicate<ISequenceEvent> sameLifeline = new Predicate<ISequenceEvent>() {
            public boolean apply(ISequenceEvent input) {
                Option<Lifeline> inputLifeline = input.getLifeline();
                boolean same = !inputLifeline.some() || (selfLifeline.some() && inputLifeline.get() == selfLifeline.get());

                if (input instanceof Message) {
                    Option<Lifeline> sourceLifeline = ((Message) input).getSourceLifeline();
                    same = same || !sourceLifeline.some() || (selfLifeline.some() && sourceLifeline.get() == selfLifeline.get());
                    Option<Lifeline> tgtLifeline = ((Message) input).getTargetLifeline();
                    same = same || !tgtLifeline.some() || (selfLifeline.some() && tgtLifeline.get() == selfLifeline.get());
                }

                return same;
            }
        };

        Predicate<ISequenceEvent> intersectsFinalBounds = new Predicate<ISequenceEvent>() {
            public boolean apply(ISequenceEvent input) {
                Range inputRange = input.getVerticalRange();
                boolean intersection = inputRange.intersects(fullFinalRange) && !linkedSiblings.contains(input);
                // Some event could not be parents : states for examples.
                boolean includedInput = !self.getValidSubEventsRange().isEmpty() && fullFinalRange.includes(inputRange.grown(1));

                if (input instanceof Message) {
                    Message msg = (Message) input;

                    if (msg.isReflective() && !includedInput) {
                        intersection = inputRange.intersects(fullFinalRange) && !inputRange.includes(fullFinalRange);
                        includedInput = inputRange.includes(fullFinalRange);
                    }

                    if (intersection && msg.isCompoundMessage()) {
                        Iterable<Execution> compoundEvents = Iterables.filter(EventEndHelper.getCompoundEvents(input), Execution.class);
                        if (!Iterables.isEmpty(compoundEvents)) {
                            Execution remoteExec = compoundEvents.iterator().next();
                            if (remoteExec != null && remoteExec.getEndMessage().some() && !fullFinalRange.includes(remoteExec.getExtendedVerticalRange())) {
                                includedInput = false;
                                remoteErrors.add(remoteExec);
                            }
                        }
                    }
                }
                return intersection && !includedInput;
            }
        };

        /*
         * Removes parent combined fragment to be able to resize an execution in
         * a combined fragment
         */
        Predicate<ISequenceEvent> notParentCombinedFragment = new Predicate<ISequenceEvent>() {
            public boolean apply(ISequenceEvent input) {
                if (input instanceof CombinedFragment && self.getLifeline().some()) {
                    CombinedFragment combinedFragment = (CombinedFragment) input;
                    return !(combinedFragment.computeCoveredLifelines().contains(self.getLifeline().get()) && combinedFragment.getVerticalRange().includes(fullFinalRange));
                }
                return true;
            }
        };

        Iterable<ISequenceEvent> invalids = Iterables.filter(finalSiblings, Predicates.and(sameLifeline, notParentCombinedFragment, intersectsFinalBounds));
        if (!Iterables.isEmpty(invalids)) {
            finalParent = null;
            for (ISequenceEvent ise : Iterables.concat(invalids, remoteErrors)) {
                Range verticalRange = ise.getVerticalRange();

                if (ise instanceof Message && ((Message) ise).isReflective() && verticalRange.includes(self.getVerticalRange())) {
                    verticalRange = new Range(verticalRange.getUpperBound(), verticalRange.getUpperBound());
                }

                if (request.isResizeFromBottom()) {
                    Range errorRange = new Range(verticalRange.getLowerBound() - 1, Math.max(verticalRange.getLowerBound(), fullFinalRange.getUpperBound()));
                    expansionZone = expansionZone == null ? errorRange : errorRange.union(expansionZone);
                }
            }
        }
        return finalParent;
    }

    /**
     * Get list of {@link ISequenceEvent} from the request.
     * 
     * @param request
     *            request of resize
     * 
     * @return list of {@link ISequenceEvent}
     */
    public static ArrayList<ISequenceEvent> computeLinkedSiblings(RequestQuery request) {
        final ArrayList<ISequenceEvent> linkedSiblings = Lists.newArrayList();
        Set<Execution> parts = request.getExecutions();
        for (Execution execution : parts) {
            linkedSiblings.add(execution);
            linkedSiblings.addAll(execution.findLinkedExecutions(true));
            linkedSiblings.addAll(new ISequenceEventQuery(execution).getAllDescendants());
        }
        return linkedSiblings;
    }

    /**
     * Get the {@link ISequenceEvent} for ...
     * 
     * @param self
     *            {@link AbstractNodeEvent} to consider.
     * @param smep
     *            the {@link Message} to consider.
     * @param deltaY
     *            the deltaY to use
     * @param deltaHeight
     *            the deltaHeight to use
     * 
     * @return the {@link ISequenceEvent} for ...
     */
    public static ISequenceEvent getFinalRemoteParent(AbstractNodeEvent self, Message smep, int deltaY, int deltaHeight) {
        Range finalMessageRange = smep.getVerticalRange().shifted(deltaY);
        if (deltaHeight != 0) {
            finalMessageRange = new Range(finalMessageRange.getLowerBound(), finalMessageRange.getUpperBound() + deltaHeight);
        }
        Set<ISequenceEvent> allMovedElements = new ISequenceEventQuery(self).getAllDescendants();
        allMovedElements.add(self);

        ISequenceEvent sourceParent = (smep.getSourceElement() instanceof ISequenceEvent) ? (ISequenceEvent) smep.getSourceElement() : null;

        /*
         * The target will not be an ISequenceEvent for creation and destruction
         * messages as instance roles and EOL are not ISequenceEvents.
         */
        ISequenceEvent targetParent = (smep.getTargetElement() instanceof ISequenceEvent) ? (ISequenceEvent) smep.getTargetElement() : null;
        ISequenceEvent remoteParent = allMovedElements.contains(sourceParent) ? targetParent : sourceParent;

        ISequenceEvent finalRemoteParent = null;
        if (remoteParent != null) {
            Option<Lifeline> remoteLifeline = remoteParent.getLifeline();
            if (remoteLifeline.some()) {
                EventFinder remoteFinder = new EventFinder(remoteLifeline.get());
                remoteFinder.setEventsToIgnore(Predicates.in(Lists.newArrayList(allMovedElements)));
                finalRemoteParent = remoteFinder.findMostSpecificEvent(finalMessageRange);
            }
        }
        return finalRemoteParent;
    }
}
