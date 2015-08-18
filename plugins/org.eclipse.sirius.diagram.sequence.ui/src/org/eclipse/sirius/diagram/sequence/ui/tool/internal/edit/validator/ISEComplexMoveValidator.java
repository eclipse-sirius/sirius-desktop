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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ISequenceElementSwitch;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Abstract class to validate Execution move & resize request and get from it a
 * command.
 * 
 * @author mporhel
 * 
 */
public class ISEComplexMoveValidator extends AbstractSequenceInteractionValidator {

    private static final String VALIDATOR = "org.eclipse.sirius.sequence.move.validator"; //$NON-NLS-1$

    /** List of top levels events of the current move. */
    protected final Set<ISequenceEvent> topLevelElements = new HashSet<ISequenceEvent>();

    /** List of entry points events of the current move (selection). */
    protected final Set<ISequenceEvent> otherEntryPoints = new HashSet<ISequenceEvent>();

    /** The global moved range. */
    protected Range globalMovedRange = Range.emptyRange();

    /** The primary selected {@link ISequenceEvent}. */
    protected ISequenceEvent primarySelected;

    private final int vMove;

    private LinkedHashSet<ISequenceNode> sequenceNodesToMove = Sets.newLinkedHashSet();

    private Collection<Message> messagesToMove = Sets.newLinkedHashSet();

    private Function<ISequenceEvent, Range> rangeFunction = new Function<ISequenceEvent, Range>() {

        public Range apply(ISequenceEvent from) {
            Range range = from.getVerticalRange();
            if (movedElements.contains(from)) {
                range = range.shifted(vMove);
            } else if (startReflexiveMessageToResize.contains(from)) {
                int minExecStart = Math.max(range.getLowerBound(), range.getUpperBound() + vMove);
                range = new Range(range.getLowerBound(), minExecStart);
            } else if (endReflexiveMessageToResize.contains(from)) {
                int maxExecEnd = Math.min(range.getUpperBound(), range.getLowerBound() + vMove);
                range = new Range(maxExecEnd, range.getUpperBound());
                if (expansionZone != null && !expansionZone.isEmpty() && range.includes(expansionZone.getUpperBound())) {
                    range = new Range(range.getLowerBound(), range.getUpperBound() + expansionZone.width());
                }
            } else if (expansionZone != null && !expansionZone.isEmpty()) {
                if (range.includes(expansionZone.getLowerBound())) {
                    range = new Range(range.getLowerBound(), range.getUpperBound() + expansionZone.width());
                } else if (range.getLowerBound() >= expansionZone.getLowerBound()) {
                    range = range.shifted(expansionZone.width());
                }
            }

            return range;
        }
    };

    /**
     * Constructor.
     * 
     * @param host
     *            the main execution to resize/move
     * @param request
     *            the resize request targeting the execution.
     */
    protected ISEComplexMoveValidator(ISequenceEvent host, RequestQuery request) {
        super(request);
        this.primarySelected = host;
        Preconditions.checkArgument(request.isMove());
        this.vMove = request.getLogicalDelta().y;
    }

    public Function<ISequenceEvent, Range> getRangeFunction() {
        return rangeFunction;
    }

    public SequenceDiagram getDiagram() {
        return primarySelected.getDiagram();
    }

    public Range getMovedRange() {
        return globalMovedRange;
    }

    /**
     * Return the nodes to move.
     * 
     * @return a linked hash set of nodes to move.
     */
    public LinkedHashSet<ISequenceNode> getSequenceNodeToMove() {
        return sequenceNodesToMove;
    }

    public Set<ISequenceEvent> getTopLevelElements() {
        return topLevelElements;
    }

    /**
     * Get the message to move.
     * 
     * @return the message to move.
     */
    public Collection<Message> getMessageToMove() {
        return messagesToMove;
    }

    /**
     * {@inheritDoc}
     */
    public void addAdditionalEntryPoints(Collection<ISequenceEvent> sequenceElements) {
        if (sequenceElements != null && !sequenceElements.isEmpty()) {
            this.otherEntryPoints.addAll(sequenceElements);
        }
    }

    /**
     * Overridden to do validation.
     * 
     * {@inheritDoc}
     */
    protected void doValidation() {
        populateMoves();
        populateMessageToResize();
        categorizeMoves();

        checkMoves();

        // Avoid expansion with non complete moves
        if (!startReflexiveMessageToResize.isEmpty() || !endReflexiveMessageToResize.isEmpty()) {
            valid = valid && (expansionZone == null || expansionZone.isEmpty());
        }
    }

    private void populateMoves() {
        Set<ISequenceEvent> movedEvents = new ISequenceEventQuery(primarySelected).getAllSequenceEventToMoveWith(otherEntryPoints);
        if (movedEvents != null && !movedEvents.isEmpty()) {
            movedElements.addAll(movedEvents);
        }
    }

    private void populateMessageToResize() {
        for (Execution movedExec : Iterables.filter(movedElements, Execution.class)) {

            Option<Message> startMessage = movedExec.getStartMessage();
            if (startMessage.some() && startMessage.get().surroundsEventOnSameLifeline()) {
                startReflexiveMessageToResize.add(startMessage.get());
            }

            Option<Message> endMessage = movedExec.getEndMessage();
            if (endMessage.some() && endMessage.get().surroundsEventOnSameLifeline()) {
                endReflexiveMessageToResize.add(endMessage.get());
            }
        }
    }

    private void categorizeMoves() {
        MoveSwitch moveAnalyzer = new MoveSwitch();
        for (ISequenceEvent movedEvent : Lists.newArrayList(movedElements)) {
            Range extendedRange = moveAnalyzer.doSwitch(movedEvent);
            globalMovedRange = globalMovedRange.union(extendedRange);
        }

        Set<ISequenceEvent> move = Sets.newHashSet();
        move.add(primarySelected);
        move.addAll(otherEntryPoints);
        Iterables.retainAll(move, sequenceNodesToMove);
        Iterables.addAll(topLevelElements, move);

        Rectangle movedRange = new Rectangle(0, globalMovedRange.getLowerBound(), 0, globalMovedRange.width());
        globalMovedRange = RangeHelper.verticalRange(request.getLogicalTransformedRectangle(movedRange));
    }

    private void checkMoves() {
        for (ISequenceEvent ise : topLevelElements) {
            if (!moveIsValid(ise, true)) {
                valid = false;
                eventInError.add(ise);
                // break;
            }
        }

        if (valid) {
            Iterable<ISequenceEvent> otherMovedElements = Iterables.filter(movedElements, Predicates.not(Predicates.in(topLevelElements)));
            for (ISequenceEvent ise : otherMovedElements) {
                if (!(ise instanceof Operand) && !moveIsValid(ise, false)) {
                    valid = false;
                    eventInError.add(ise);
                }
            }
        }

        for (Message resizedMsg : Iterables.concat(startReflexiveMessageToResize, endReflexiveMessageToResize)) {
            boolean currentResizeIsValid = rangeFunction.apply(resizedMsg).width() >= LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP;
            valid = valid && currentResizeIsValid;
            if (!currentResizeIsValid) {
                eventInError.add(resizedMsg);
            }
        }

        valid = valid && checkConflictesInFinalPositions() && checkTitleZonesInFinalPositions();
    }

    private boolean checkConflictesInFinalPositions() {
        List<Integer> conflicts = Lists.newArrayList();
        conflicts.addAll(new PositionsChecker(getDiagram(), rangeFunction).getInvalidPositions());

        if (!conflicts.isEmpty()) {
            // try with global moved range...
            if (!expansionZone.isEmpty() && globalMovedRange != expansionZone) {
                expansionZone = globalMovedRange;
                conflicts = Lists.newArrayList();
                conflicts.addAll(new PositionsChecker(getDiagram(), rangeFunction).getInvalidPositions());
            }
        }

        invalidPositions.addAll(conflicts);
        return conflicts.isEmpty();
    }

    private boolean checkTitleZonesInFinalPositions() {
        SequenceDiagram diagram = getDiagram();
        Collection<Range> conflicts = Lists.newArrayList();
        Collection<Range> titleZones = getTitleZoneRanges(diagram);

        for (ISequenceEvent movedElement : movedElements) {
            Range movedRange = rangeFunction.apply(movedElement);
            if (movedElement instanceof State && movedElement.isLogicallyInstantaneous()) {
                movedRange = new Range(movedRange.middleValue(), movedRange.middleValue());
            }
            for (Range title : titleZones) {
                if (title.includes(movedRange.getLowerBound()) || title.includes(movedRange.getUpperBound())) {
                    conflicts.add(title);
                }
            }
        }

        invalidRanges.addAll(conflicts);
        return conflicts.isEmpty();
    }

    private Collection<Range> getTitleZoneRanges(SequenceDiagram diagram) {
        Collection<Range> titleZones = Lists.newArrayList();
        for (CombinedFragment unmovedCF : Iterables.filter(diagram.getAllCombinedFragments(), Predicates.not(Predicates.in(movedElements)))) {
            int titleZoneLowerBound = rangeFunction.apply(unmovedCF).getLowerBound();
            int titleZoneUpperBound = rangeFunction.apply(unmovedCF.getFirstOperand()).getLowerBound();

            titleZones.add(new Range(titleZoneLowerBound, titleZoneUpperBound));
        }
        return titleZones;
    }

    private boolean moveIsValid(ISequenceEvent ise, boolean topLevel) {
        final Range futureExtRange = getFutureExtendedRange(ise);
        Option<Lifeline> lifeline = ise.getLifeline();
        boolean result = true;

        if (lifeline.some()) {
            EventFinder futureParentFinder = new EventFinder(lifeline.get());
            futureParentFinder.setEventsToIgnore(Predicates.equalTo(ise));
            futureParentFinder.setVerticalRangefunction(rangeFunction);
            Range insertionPoint = getInsertionPoint(ise, futureExtRange);
            ISequenceEvent insertionParent = futureParentFinder.findMostSpecificEvent(insertionPoint);

            if (insertionParent == null) {
                if (lifeline.get().isExplicitlyCreated() || lifeline.get().isExplicitlyDestroyed()) {
                    return false;
                } else {
                    insertionParent = lifeline.get();
                }
            }

            // check Operand;
            boolean stableOperand = checkOperandStability(ise, topLevel, insertionParent);

            // check expansion need
            boolean validExpansion = stableOperand && checkExpansionNeed(ise, topLevel, insertionParent, futureExtRange, insertionPoint, Collections.singletonList(lifeline.get()));

            // Test remote expansion
            boolean validRemoteExpansion = validExpansion && checkRemoteExpansion(ise, topLevel, futureExtRange, insertionPoint);

            result = stableOperand && validExpansion && validRemoteExpansion;
        } else if (ise instanceof InteractionUse) {
            InteractionUseMoveValidator subValidator = new InteractionUseMoveValidator((InteractionUse) ise, request);
            subValidator.setMovedElements(movedElements);
            result = subValidator.isValid();
            expansionZone = expansionZone.union(subValidator.getExpansionZone());
        } else if (ise instanceof CombinedFragment) {
            CombinedFragmentMoveValidator subValidator = new CombinedFragmentMoveValidator((CombinedFragment) ise, request);
            subValidator.setMovedElements(movedElements);
            result = subValidator.isValid();
            expansionZone = expansionZone.union(subValidator.getExpansionZone());
        } else if (ise instanceof Message) {
            result = messageMoveIsValid((Message) ise);
        }
        return result;
    }

    private Range getInsertionPoint(ISequenceEvent ise, Range futureExtRange) {
        int insertionY = futureExtRange.getLowerBound();

        if (ise instanceof State && ise.isLogicallyInstantaneous()) {
            insertionY = futureExtRange.middleValue();
        }

        return new Range(insertionY, insertionY);
    }

    private boolean messageMoveIsValid(final Message message) {
        boolean result = true;
        Option<Operand> parentOperand = message.getParentOperand();

        // If parent operand is moved too, no check for source/target validity :
        // it will not change.
        if (!(parentOperand.some() && movedElements.contains(parentOperand.get()))) {

            ISequenceEvent potentialSource = getMessageEnd(message, message.getSourceLifeline(), parentOperand);
            ISequenceEvent potentialTarget = getMessageEnd(message, message.getTargetLifeline(), parentOperand);

            // Validate that the source and target will not overlap a frame

            if (potentialSource instanceof AbstractFrame || potentialSource instanceof State || potentialTarget instanceof AbstractFrame || potentialTarget instanceof State) {
                result = false;
            } else if (potentialSource != null && potentialTarget != null) {
                // We filter found or lost message because for them move is
                // always allowed

                Range finalRange = rangeFunction.apply(message);
                int lBound = finalRange.getLowerBound();
                int uBound = finalRange.getUpperBound();

                Operand srcOperand = null;
                if (potentialSource instanceof Operand) {
                    srcOperand = (Operand) potentialSource;
                } else if (potentialSource instanceof Execution) {
                    srcOperand = new ParentOperandFinder(potentialSource, rangeFunction).getParentOperand(new Range(lBound, lBound)).get();
                }

                Operand tgtOperand = null;
                if (potentialTarget instanceof Operand) {
                    tgtOperand = (Operand) potentialTarget;
                } else if (potentialTarget instanceof Execution) {
                    tgtOperand = new ParentOperandFinder(potentialTarget, rangeFunction).getParentOperand(new Range(uBound, uBound)).get();
                }
                result = srcOperand == tgtOperand;
            }
        }
        return result;
    }

    private ISequenceEvent getMessageEnd(final Message message, Option<Lifeline> lifeline, Option<Operand> parentOperand) {
        if (lifeline.some()) {
            EventFinder newEndFinder = new EventFinder(lifeline.get());
            newEndFinder.setReconnection(false);

            Range lookedRange = rangeFunction.apply(message);
            newEndFinder.setVerticalRangefunction(rangeFunction);

            return newEndFinder.findMostSpecificEvent(lookedRange);
        }
        return null;
    }

    private boolean checkOperandStability(ISequenceEvent ise, boolean topLevel, ISequenceEvent insertionParent) {
        Option<Operand> parentOperand = ise.getParentOperand();
        Operand futureOperand = null;
        if (insertionParent instanceof Operand) {
            futureOperand = (Operand) insertionParent;
        } else {
            futureOperand = insertionParent.getParentOperand().get();
        }

        return true; // futureOperand == parentOperand.get();
    }

    private boolean checkExpansionNeed(ISequenceEvent ise, boolean topLevel, ISequenceEvent insertionParent, Range futureExtRange, Range insertionPoint, Collection<Lifeline> lifelines) {
        List<ISequenceEvent> toIgnore = Lists.newArrayList(movedElements);
        toIgnore.addAll(startReflexiveMessageToResize);
        toIgnore.addAll(endReflexiveMessageToResize);

        boolean canChildOccupy = movedElements.contains(insertionParent) || insertionParent.canChildOccupy(ise, futureExtRange, toIgnore, lifelines);
        if (!canChildOccupy) {
            if (topLevel && !expansionZone.isEmpty()) {
                expansionZone = globalMovedRange;
                canChildOccupy = true;
            } else if (topLevel || expansionZone.isEmpty()) {
                if (insertionParent.canChildOccupy(ise, insertionPoint, toIgnore, lifelines)) {
                    if (!(ise instanceof State && ise.isLogicallyInstantaneous())) {
                        expansionZone = expansionZone.union(futureExtRange);
                    }
                    canChildOccupy = true;
                }
            } else if (expansionZone.includes(futureExtRange)) {
                canChildOccupy = true;
            }
        }
        return canChildOccupy;
    }

    private boolean checkRemoteExpansion(ISequenceEvent ise, boolean topLevel, Range futureExtRange, Range insertionPoint) {
        if (ise instanceof Execution) {
            Execution exec = (Execution) ise;
            Option<Message> startMessage = exec.getStartMessage();
            Option<Message> endMessage = exec.getEndMessage();

            if (startMessage.some() && endMessage.some() && !startReflexiveMessageToResize.contains(startMessage.get()) && !endReflexiveMessageToResize.contains(endMessage.get())) {
                Option<Lifeline> startLifeline = startMessage.get().getSourceLifeline();
                // Should be the same...
                Option<Lifeline> targetLifeline = endMessage.get().getTargetLifeline();

                Range srcInsertionRange = rangeFunction.apply(startMessage.get());
                ISequenceEvent remoteSource = getRemoteEnd(startMessage, startLifeline, srcInsertionRange);

                // Expansion ?
                Range tgtInsertionRange = rangeFunction.apply(endMessage.get());
                ISequenceEvent remoteTarget = getRemoteEnd(endMessage, targetLifeline, tgtInsertionRange);

                boolean remoteCanChildOccupy = startLifeline.some() && remoteSource != null && remoteTarget == remoteSource || !startLifeline.some();
                boolean tryExpand = topLevel || expansionZone.isEmpty();
                boolean canExpandedChildOccupy = remoteSource != null && remoteSource.canChildOccupy(ise, srcInsertionRange);
                if (!remoteCanChildOccupy && tryExpand && canExpandedChildOccupy) {
                    expansionZone = expansionZone.union(futureExtRange);
                    remoteCanChildOccupy = true;
                }
                return remoteCanChildOccupy;
            }
        }
        return true;
    }

    private ISequenceEvent getRemoteEnd(Option<Message> message, Option<Lifeline> lifeline, Range insertionRange) {
        ISequenceEvent remoteEnd = null;
        if (lifeline.some()) {
            EventFinder remoteSrcFinder = new EventFinder(lifeline.get());
            remoteSrcFinder.setEventsToIgnore(Predicates.equalTo((ISequenceEvent) message.get()));
            remoteSrcFinder.setVerticalRangefunction(rangeFunction);
            remoteSrcFinder.setExpansionZone(expansionZone);
            remoteSrcFinder.setReconnection(true);

            remoteEnd = remoteSrcFinder.findMostSpecificEvent(insertionRange);
        }
        return remoteEnd;
    }

    private Range getFutureExtendedRange(ISequenceEvent ise) {
        Range futureExtRange = rangeFunction.apply(ise);
        if (ise instanceof Execution) {
            Execution exec = (Execution) ise;
            Option<Message> startMessage = exec.getStartMessage();
            if (startMessage.some() && !startReflexiveMessageToResize.contains(startMessage.get())) {
                futureExtRange = futureExtRange.union(rangeFunction.apply(startMessage.get()));
            }
            Option<Message> endMessage = exec.getEndMessage();
            if (endMessage.some() && !endReflexiveMessageToResize.contains(endMessage.get())) {
                futureExtRange = futureExtRange.union(rangeFunction.apply(endMessage.get()));
            }
        }
        return futureExtRange;
    }

    /**
     * Specific switch returning allowing to categorize and event.
     * 
     * The doSwitch will return the extended range occupied by the event
     * (including the linked messages for reflexives executions).
     * 
     * @author mporhel
     * 
     */
    public class MoveSwitch extends ISequenceElementSwitch<Range> {

        /**
         * {@inheritDoc}
         */
        @Override
        public Range caseMessage(Message movedEvent) {
            Predicate<Message> toMove = new Predicate<Message>() {
                public boolean apply(Message input) {
                    boolean movedBySrc = movedElements.contains(input.getSourceElement());
                    boolean movedByTgt = movedElements.contains(input.getTargetElement());

                    return !(movedBySrc && movedByTgt);
                }
            };
            if (toMove.apply(movedEvent)) {
                if (startReflexiveMessageToResize.contains(movedEvent) || endReflexiveMessageToResize.contains(movedEvent)) {
                    movedElements.remove(movedEvent);
                    return Range.emptyRange();
                }
                messagesToMove.add(movedEvent);
            } else {
                startReflexiveMessageToResize.remove(movedEvent);
                endReflexiveMessageToResize.remove(movedEvent);
            }
            return movedEvent.getVerticalRange();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Range caseExecution(Execution movedEvent) {
            ISequenceEvent hierarchicalParentEvent = movedEvent.getHierarchicalParentEvent();
            if (hierarchicalParentEvent instanceof ISequenceNode && !movedElements.contains(hierarchicalParentEvent)) {
                sequenceNodesToMove.add(movedEvent);
            }

            Range extendedVerticalRange = movedEvent.getExtendedVerticalRange();
            Option<Message> startMessage = movedEvent.getStartMessage();
            if (startMessage.some() && startReflexiveMessageToResize.contains(startMessage.get())) {
                extendedVerticalRange = new Range(startMessage.get().getVerticalRange().getUpperBound(), extendedVerticalRange.getUpperBound());
            }

            Option<Message> endMessage = movedEvent.getEndMessage();
            if (endMessage.some() && endReflexiveMessageToResize.contains(endMessage.get())) {
                extendedVerticalRange = new Range(extendedVerticalRange.getLowerBound(), endMessage.get().getVerticalRange().getLowerBound());
            }

            return extendedVerticalRange;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Range caseState(State movedEvent) {
            ISequenceEvent hierarchicalParentEvent = movedEvent.getHierarchicalParentEvent();
            if (hierarchicalParentEvent instanceof ISequenceNode && !movedElements.contains(hierarchicalParentEvent)) {
                sequenceNodesToMove.add(movedEvent);
            }
            return movedEvent.getVerticalRange();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Range caseFrame(AbstractFrame movedEvent) {
            sequenceNodesToMove.add(movedEvent);
            return movedEvent.getVerticalRange();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Range caseOperand(Operand movedEvent) {
            // Do nothing, operand is silently moved by its parent combined
            // fragment.
            return movedEvent.getVerticalRange();
        }
    }

    /**
     * Get the validator from the request extended data or a new one.
     * 
     * @param cbr
     *            the current request
     * @param requestQuery
     *            a query on the current request.
     * @param host
     *            the host
     * @return a validator.
     */
    @SuppressWarnings("unchecked")
    public static ISEComplexMoveValidator getOrCreateValidator(ChangeBoundsRequest cbr, RequestQuery requestQuery, ISequenceEvent host) {
        ISEComplexMoveValidator validator = null;
        Object object = cbr.getExtendedData().get(VALIDATOR);
        if (object instanceof ISEComplexMoveValidator) {
            validator = (ISEComplexMoveValidator) object;
            if (validator.request == null || !validator.request.getLogicalDelta().equals(requestQuery.getLogicalDelta())) {
                validator = null;
            }
        }

        if (validator == null && requestQuery.isMove()) {
            Collection<ISequenceEvent> selectedIses = new RequestQuery(cbr).getISequenceEvents();
            validator = new ISEComplexMoveValidator(host, requestQuery);
            validator.addAdditionalEntryPoints(selectedIses);
            cbr.getExtendedData().put(VALIDATOR, validator);
        }
        return validator;
    }

}
