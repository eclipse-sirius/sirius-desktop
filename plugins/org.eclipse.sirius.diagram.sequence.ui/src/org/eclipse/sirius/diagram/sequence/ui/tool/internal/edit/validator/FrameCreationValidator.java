/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.query.RangeComparator;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ReversedRangeComparator;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceDiagramQuery;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.CreateRequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * Validates that is request for InteractionUse or CombinedFragment is allowed.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class FrameCreationValidator extends AbstractSequenceInteractionValidator {

    /**
     * The key used in request's extended data map to identify the original
     * target edit part of a request which was redirected to us.
     */
    public static final String ORIGINAL_TARGET = "original.target"; //$NON-NLS-1$

    private static final String VALIDATOR = "org.eclipse.sirius.sequence.frame.creation.validator"; //$NON-NLS-1$

    private SequenceDiagram sequenceDiagram;

    private SequenceDiagramQuery sequenceDiagramQuery;

    private Collection<Lifeline> coverage;

    private EventEnd startingEndPredecessor;

    private EventEnd finishingEndPredecessor;

    private Multimap<Lifeline, ISequenceEvent> sequenceEventsInCreationRange;

    // Store for each lifeline, the first detected event in conflict ->
    // expansion zone potential start
    private Set<ISequenceEvent> eventsToShift;

    // Store for each lifeline, the event which should carry the frame to create
    private Set<ISequenceEvent> localParents;

    private final ContainerCreationDescription ccdTool;

    private Rectangle creationBounds;

    /**
     * Default constructor to validate InteractionUse or CombinedFragment
     * creation.
     * 
     * @param sequenceDiagram
     *            the {@link SequenceDiagram} on which do the creation
     * @param ccdTool
     *            {@link ContainerCreationDescription}
     * @param createRequestQuery
     *            {@link CreateRequestQuery}
     */
    public FrameCreationValidator(SequenceDiagram sequenceDiagram, ContainerCreationDescription ccdTool, CreateRequestQuery createRequestQuery) {
        super(createRequestQuery);
        this.creationBounds = createRequestQuery.getLogicalDelta();
        this.sequenceDiagram = sequenceDiagram;
        this.ccdTool = ccdTool;
        this.sequenceDiagramQuery = new SequenceDiagramQuery(sequenceDiagram);
        this.sequenceEventsInCreationRange = HashMultimap.create();
        this.eventsToShift = Sets.newTreeSet(new RangeComparator());
        this.localParents = Sets.newHashSet();
    }

    @Override
    public SequenceDiagram getDiagram() {
        return sequenceDiagram;
    }

    @Override
    public Function<ISequenceEvent, Range> getRangeFunction() {
        return ISequenceEvent.VERTICAL_RANGE;
    }

    /**
     * Validate the creation of a InteractionUse or CombinedFragment creation.
     */
    protected void doValidation() {
        int firstClickY = creationBounds.y;
        int secondClickY = creationBounds.bottom();
        Range creationRange = new Range(firstClickY, secondClickY);

        computeCoverage();

        // Potential creation ?
        if (!coverage.isEmpty() || creationBounds.width > 0 || creationBounds.height > 0) {
            // Remove tests to always display horizontal guides for two click
            // creation, (retarget show target feedback in
            // ExecutionAwareNodeCreationPolicy).
            createdElements.add(creationRange);
        }

        valid = !coverage.isEmpty();

        if (valid) {
            valid = categorizeOverlappedEvents(creationRange);
            if (valid) {
                computeExpanzionZone(creationRange);
            }
            computeSemanticPredecessors(firstClickY);
        }
    }

    private void computeExpanzionZone(Range creationRange) {
        if (ccdTool instanceof InteractionUseCreationTool) {
            for (ISequenceEvent parent : localParents) {
                Range newExpansionZone = new Range(parent.getVerticalRange().getUpperBound() - 1, creationRange.getUpperBound());
                expansionZone = expansionZone.union(newExpansionZone);
            }

            SortedSet<ISequenceEvent> overlapped = Sets.newTreeSet(new RangeComparator());
            overlapped.addAll(sequenceEventsInCreationRange.values());
            for (ISequenceEvent ise : Iterables.filter(overlapped, Predicates.not(Predicates.in(localParents)))) {
                int lowerBound = ise.getVerticalRange().getLowerBound();
                if (lowerBound >= creationRange.getLowerBound()) {
                    Range newExpansionZone = new Range(lowerBound - 1, creationRange.getUpperBound());
                    expansionZone = expansionZone.union(newExpansionZone);
                    break;
                }
            }
        } else if (ccdTool instanceof CombinedFragmentCreationTool) {
            Collection<ISequenceEvent> partialOverlaps = Lists.newArrayList(Iterables.concat(localParents, eventsToShift));
            for (ISequenceEvent parent : localParents) {
                checkOtherLifelines(parent, partialOverlaps);
                int expansionCut = Math.max(creationRange.getLowerBound(), parent.getVerticalRange().getUpperBound() - 1);
                Range newExpansionZone = new Range(expansionCut, creationRange.getUpperBound());
                expansionZone = expansionZone.union(newExpansionZone);
            }

            for (ISequenceEvent eventToShift : eventsToShift) {
                checkOtherLifelines(eventToShift, partialOverlaps);
                int expansionCut = Math.max(creationRange.getLowerBound(), eventToShift.getVerticalRange().getLowerBound() - 1);
                Range newExpansionZone = new Range(expansionCut, creationRange.getUpperBound());
                expansionZone = expansionZone.union(newExpansionZone);
            }
        }
    }

    private void computeSemanticPredecessors(int firstClickY) {
        // Look for correct finishing end predecessor
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        startingEndPredecessor = SequenceGraphicalHelper.getEndBefore(sequenceDDiagram, firstClickY - 1);
        if (expansionZone != null && !expansionZone.isEmpty()) {
            finishingEndPredecessor = SequenceGraphicalHelper.getEndBefore(sequenceDDiagram, expansionZone.getLowerBound());
        } else {
            finishingEndPredecessor = SequenceGraphicalHelper.getEndBefore(sequenceDDiagram, creationBounds.getBottom().y);
        }
    }

    private void checkOtherLifelines(ISequenceEvent eventToCheck, Collection<ISequenceEvent> eventsToIgnore) {
        Range rangeToCheck = eventToCheck.getVerticalRange();
        for (Lifeline lifeline : sequenceEventsInCreationRange.keySet()) {
            Collection<ISequenceEvent> overlap = sequenceEventsInCreationRange.get(lifeline);
            // Dot check event to shift lifeline
            if (!overlap.contains(eventToCheck)) {
                for (ISequenceEvent otherLifelineEvent : Iterables.filter(overlap, Predicates.not(Predicates.in(eventsToIgnore)))) {
                    Range otherRange = otherLifelineEvent.getVerticalRange();
                    Range intersection = otherRange.intersection(rangeToCheck);
                    if (!intersection.equals(rangeToCheck) && !intersection.equals(otherRange) && !intersection.isEmpty()) {
                        valid = false;
                    }
                }
            }
        }
    }

    private void computeCoverage() {
        coverage = Sets.newLinkedHashSet();

        Set<Lifeline> graphicallyCoveredLifelines = sequenceDiagram.getGraphicallyCoveredLifelines(creationBounds);
        Object originalTarget = request.getExtendedData().get(ORIGINAL_TARGET);
        if (originalTarget instanceof ISequenceEventEditPart) {
            ISequenceEventEditPart sequenceEventEditPart = (ISequenceEventEditPart) originalTarget;
            Lifeline lifeline = sequenceEventEditPart.getISequenceEvent().getLifeline().get();
            request.getExtendedData().remove(ORIGINAL_TARGET);
            coverage.add(lifeline);
        } else {
            for (Lifeline coveredLifeline : computeGraphicalCoverageFromSelectionArea(graphicallyCoveredLifelines)) {
                coverage.add(coveredLifeline);
            }

            // Remove covered interaction uses
            for (InteractionUse iu : sequenceDiagram.getAllInteractionUses()) {
                if (iu.getVerticalRange().includes(creationBounds.y)) {
                    Collection<Lifeline> iuLifelines = iu.computeCoveredLifelines();
                    coverage.removeAll(iuLifelines);
                    // Do not add to events in error, sometimes lifelines cannot
                    // be clearly layouted and ordered
                }
            }
        }
    }

    private boolean categorizeOverlappedEvents(Range creationRange) {
        Set<ISequenceEvent> checkedSequenceEvents = Sets.newHashSet();
        for (Lifeline lifeline : coverage) {
            categorizeOverlappedEvents(lifeline, creationRange, checkedSequenceEvents);
        }
        return eventInError.isEmpty();
    }

    /**
     * Checks if the specified inclusion range overlap partially
     * {@link ISequenceEvent} of the specified {@link Lifeline} vertically (for
     * Execution, ...) or horizontally (for {@link Message} or
     * {@link AbstractFrame}).
     * 
     * @param lifeline
     *            the {@link Lifeline} to check
     * @param creationRange
     *            the request future range of the AbstractFrame to create
     */
    private void categorizeOverlappedEvents(Lifeline lifeline, Range creationRange, Collection<ISequenceEvent> alreadyChecked) {

        SortedSet<ISequenceEvent> overlappedEvents = sequenceDiagramQuery.getAllSequenceEventsOnLifelineOnRange(lifeline, creationRange);

        if (!overlappedEvents.isEmpty()) {
            this.sequenceEventsInCreationRange.putAll(lifeline, overlappedEvents);

            // Check vertical overlap : Check only the first event 's lower
            // bound and the last event 's upper bound : other are between those
            // elements and are completely graphically included

            // Look for potential parent to expand (event which will carry the
            // created event on the current lifeline)
            ISequenceEvent lFirstIseInRange = overlappedEvents.first();
            for (ISequenceEvent ise : overlappedEvents) {
                if (ise.getVerticalRange().getLowerBound() < creationRange.getLowerBound()) {
                    lFirstIseInRange = ise;
                } // else complete overlap
            }

            // Look for potential following sibling to shift.
            SortedSet<ISequenceEvent> upperBoundSorted = Sets.newTreeSet(new ReversedRangeComparator());
            upperBoundSorted.addAll(overlappedEvents);
            ISequenceEvent lLastIseInRange = upperBoundSorted.last();

            // If we have the last sequenceEvent only partially covered we mark
            // it as to be shifted
            if (!creationRange.includes(lFirstIseInRange.getVerticalRange().getLowerBound())) {
                localParents.add(lFirstIseInRange);

                if (ccdTool instanceof InteractionUseCreationTool && lFirstIseInRange instanceof InteractionUse) {
                    eventInError.add(lFirstIseInRange);
                }
            }
            if (!creationRange.includes(lLastIseInRange.getVerticalRange().getUpperBound())) {
                eventsToShift.add(lLastIseInRange);
            }

            if (ccdTool instanceof CombinedFragmentCreationTool) {

                categorizeCombinedFragmentOverlappedEvents(creationRange, alreadyChecked, overlappedEvents, lFirstIseInRange);
            }
        } else {
            // Check no conflict with interaction use
        }
    }

    private void categorizeCombinedFragmentOverlappedEvents(Range creationRange, Collection<ISequenceEvent> alreadyChecked, SortedSet<ISequenceEvent> overlappedEvents, ISequenceEvent lFirstIseInRange) {
        // Check horizontal overlap.
        for (Message message : Iterables.filter(overlappedEvents, Message.class)) {
            if (!alreadyChecked.contains(message)) {
                alreadyChecked.add(message);
                Option<Lifeline> srcLifeline = message.getSourceLifeline();
                if (srcLifeline.some() && !coverage.contains(srcLifeline.get())) {
                    // As we have coverage incompatibility we do
                    // insertion then we shift all events in the
                    // creationRange

                    ISequenceEvent parentEvent = null;
                    ISequenceNode targetElement = message.getTargetElement();

                    if (targetElement instanceof ISequenceEvent) {
                        parentEvent = getHigherAncestorWithLowerBoundInCreationRange((ISequenceEvent) targetElement, creationRange);
                    }

                    if (parentEvent == null) {
                        eventsToShift.add(lFirstIseInRange);
                    } else if (!creationRange.includes(parentEvent.getVerticalRange())) {
                        eventsToShift.add(message);
                    } else {
                        eventsToShift.add(parentEvent);
                    }

                    // eventInError.add(message) : will cause error and
                    // red feedback, expansion is better
                }

                Option<Lifeline> tgtLifeline = message.getTargetLifeline();
                if (tgtLifeline.some() && !coverage.contains(tgtLifeline.get())) {
                    // As we have coverage incompatibility we do
                    // insertion then we shift all events in the
                    // creationRange
                    ISequenceEvent parentEvent = null;
                    ISequenceNode sourceElement = message.getSourceElement();

                    if (sourceElement instanceof ISequenceEvent) {
                        parentEvent = getHigherAncestorWithLowerBoundInCreationRange((ISequenceEvent) sourceElement, creationRange);
                    }

                    if (parentEvent == null) {
                        eventsToShift.add(lFirstIseInRange);
                    } else if (!creationRange.includes(parentEvent.getVerticalRange())) {
                        eventsToShift.add(message);
                    } else {
                        eventsToShift.add(parentEvent);
                    }

                    // eventInError.add(message) : will cause error and
                    // red feedback, expansion is better
                }
            }
        }

        for (AbstractFrame frame : Iterables.filter(overlappedEvents, AbstractFrame.class)) {
            if (!alreadyChecked.contains(frame)) {
                alreadyChecked.add(frame);
                Collection<Lifeline> coveredLifelines = frame.computeCoveredLifelines();
                if (!coverage.containsAll(coveredLifelines) && !eventsToShift.contains(frame) && creationRange.includes(frame.getVerticalRange().getLowerBound())) {
                    eventsToShift.add(frame);
                    // eventInError.add(message) : will cause error and
                    // red feedback, expansion is better
                }
            }
        }
    }

    /**
     * 
     * @param event
     * @param creationRange
     * @return
     */
    private ISequenceEvent getHigherAncestorWithLowerBoundInCreationRange(ISequenceEvent event, Range creationRange) {
        ISequenceEvent parentEvent = event == null ? null : event.getParentEvent();
        if (parentEvent != null && creationRange.includes(parentEvent.getVerticalRange().getLowerBound())) {
            return getHigherAncestorWithLowerBoundInCreationRange(parentEvent, creationRange);
        } else {
            return event;
        }
    }

    private Set<Lifeline> computeGraphicalCoverageFromSelectionArea(Set<Lifeline> graphicallyCoveredLifelines) {
        Set<Lifeline> validCoverage = new LinkedHashSet<Lifeline>();

        for (Lifeline lifeline : graphicallyCoveredLifelines) {
            if (coveredByDeepestOperand(lifeline, graphicallyCoveredLifelines)) {
                validCoverage.add(lifeline);
            }
        }
        return validCoverage;
    }

    private boolean coveredByDeepestOperand(Lifeline lifeline, Set<Lifeline> graphicallyCoveredLifelines) {
        boolean coveredByDeepestOperand = true;
        Range inclusionRange = RangeHelper.verticalRange(creationBounds);
        Option<Operand> findDeepestCoveringOperand = findDeepestCoveringOperand(graphicallyCoveredLifelines);
        if (findDeepestCoveringOperand.some()) {
            Option<Operand> parentOperand = lifeline.getParentOperand(inclusionRange);
            coveredByDeepestOperand = parentOperand.some() && findDeepestCoveringOperand.get().equals(parentOperand.get());
        }
        return coveredByDeepestOperand;
    }

    /**
     * Finds the deepest operand covering at least one of the lifelines on range
     * of rect.
     * 
     * @param graphicallyCoveredLifelines
     *            the covered lifelines
     * @return the deepest operand covering at least one of the lifelines on
     *         range of rect.
     */
    private Option<Operand> findDeepestCoveringOperand(Set<Lifeline> graphicallyCoveredLifelines) {
        Option<Operand> deepestOperandOption = Options.newNone();
        for (Lifeline lifeline : graphicallyCoveredLifelines) {
            // get the operand option for the current lifeline
            Option<Operand> currentOperandOption = lifeline.getParentOperand(RangeHelper.verticalRange(creationBounds));
            if (deepestOperandOption.some() && currentOperandOption.some()) {
                // save the deepest operand
                Operand deepestOperand = deepestOperandOption.get();
                Operand currentOperand = currentOperandOption.get();

                if (deepestOperand.getVerticalRange().includes(currentOperand.getVerticalRange())) {
                    deepestOperand = currentOperand;
                }
            } else if (!deepestOperandOption.some() && currentOperandOption.some()) {
                // initialize the deepest operand;
                deepestOperandOption = currentOperandOption;
            }
        }
        return deepestOperandOption;
    }

    public List<EObject> getCoverage() {
        return Lists.newArrayList(Iterables.transform(coverage, ISequenceElement.SEMANTIC_TARGET));
    }

    public Rectangle getCreationBounds() {
        return creationBounds;
    }

    public EventEnd getStartingEndPredecessor() {
        return startingEndPredecessor;
    }

    public EventEnd getFinishingEndPredecessor() {
        return finishingEndPredecessor;
    }

    /**
     * Get the validator from the request extended data or a new one.
     * 
     * 
     * @param sequenceDiagram
     *            the {@link SequenceDiagram}
     * @param containerCreationDescription
     *            {@link ContainerCreationDescription}
     * 
     * @param createRequestQuery
     *            a query on the current request.
     * 
     * @return a validator {@link FrameCreationValidator}
     */
    @SuppressWarnings("unchecked")
    public static FrameCreationValidator getOrCreateValidator(SequenceDiagram sequenceDiagram, ContainerCreationDescription containerCreationDescription, CreateRequestQuery createRequestQuery) {
        FrameCreationValidator validator = null;
        Object object = createRequestQuery.getExtendedData().get(VALIDATOR);

        if (object instanceof FrameCreationValidator) {
            validator = (FrameCreationValidator) object;
            if (validator.request == null || !validator.getCreationBounds().equals(createRequestQuery.getLogicalDelta())) {
                validator = null;
            }
        }

        if (validator == null || createRequestQuery.getExtendedData().get(ORIGINAL_TARGET) != null) {
            validator = new FrameCreationValidator(sequenceDiagram, containerCreationDescription, createRequestQuery);
            createRequestQuery.getExtendedData().put(VALIDATOR, validator);
        }
        return validator;
    }

}
