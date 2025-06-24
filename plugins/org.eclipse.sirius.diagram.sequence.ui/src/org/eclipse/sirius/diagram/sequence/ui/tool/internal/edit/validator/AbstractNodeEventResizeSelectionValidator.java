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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceMessageEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.FinalParentHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Abstract class to validate Execution move & resize request and get from it a command.
 * 
 * @author edugueperoux
 * 
 */
public class AbstractNodeEventResizeSelectionValidator {

    /** Last validator. */
    protected static AbstractNodeEventResizeSelectionValidator lastValidator;

    /** Last request. */
    protected static ChangeBoundsRequest lastRequest;

    /**
     * The expansionZine.
     */
    protected Range expansionZone;

    /**
     * The finalParent.
     */
    protected ISequenceEvent finalParent;

    /** validation done ? */
    protected boolean validationDone;

    private boolean valid;

    private AbstractNodeEvent host;

    private ChangeBoundsRequest request;

    private boolean initialized;

    private Collection<Integer> invalidPositions = new ArrayList<>();

    private RequestQuery requestQuery;

    /**
     * Constructor.
     * 
     * @param host
     *            the main execution to resize/move
     * @param request
     *            the resize request targeting the execution.
     */
    protected AbstractNodeEventResizeSelectionValidator(AbstractNodeEvent host, ChangeBoundsRequest request) {
        this.host = host;
        this.requestQuery = new RequestQuery(request);
        Preconditions.checkArgument(requestQuery.isResize());
        this.request = request;
    }

    /**
     * Return the validation status. Validate the request result in the first call only.
     * 
     * @return the validation status.
     */
    public final boolean isValid() {
        validate();
        return valid;
    }

    /**
     * Performs all the computations required to validate the resizing, and stores any important information which will
     * be useful to actually execute the move if it is valid, like for example avoid contact with siblings or handle
     * reconnection.
     */
    public final void validate() {
        if (!validationDone) {
            doValidation();
            validationDone = true;
        }
    }

    /**
     * Performs all the computations required to validate the resizing, and stores any important information which will
     * be useful to actually execute the resize if it is valid, like for example avoid contact with siblings.
     */
    private void doValidation() {
        Objects.requireNonNull(host, Messages.AbstractNodeEventResizeSelectionValidator_nullExecution);
        if (!initialized) {
            // Nothing to do
            initialized = true;
        }

        FinalParentHelper finalParentHelper = new FinalParentHelper(host, requestQuery);
        finalParentHelper.computeFinalParent();
        expansionZone = finalParentHelper.getRequiredExpansion();
        finalParent = finalParentHelper.getFinalParent();

        if (finalParent == null && requestQuery.isResizeFromBottom() && expansionZone != null && expansionZone.width() != 0) {
            finalParent = host.getParentEvent();
        }
        valid = validateNewBoundsForAllTargets() && finalParent != null;
        valid = valid && checkGlobalPositions();
    }

    /**
     * Verifies whether the new position/size of the execution would still be valid if we accept the request. In
     * particular, ensures sibling events can not overlap.
     * 
     * @return true if the request is validated to true
     */
    protected boolean validateNewBoundsForAllTargets() {
        return Iterables.all(Iterables.filter(request.getEditParts(), ExecutionEditPart.class), new Predicate<ExecutionEditPart>() {
            @Override
            public boolean apply(ExecutionEditPart input) {
                return validateNewBounds(input);
            }
        });
    }

    /**
     * Checks whether the new bounds implied by the requested change are valid for this execution. Uses Draw2D
     * information to get the current bounds.
     */
    private boolean validateNewBounds(ExecutionEditPart self) {
        final boolean result;
        Rectangle bounds = self.getFigure().getBounds().getCopy();
        Rectangle newBounds = requestQuery.getFinalBounds(self);
        ISequenceEvent selfEvent = self.getISequenceEvent();
        ISequenceEvent parent = selfEvent.getParentEvent();
        boolean newBoundsFails = (newBounds.width <= 0 && bounds.width != 0) || newBounds.height <= 0;
        if (newBoundsFails || parent == null) {
            result = false;
        } else {
            boolean isMove = requestQuery.isMove();
            /*
             * If this is a MOVE, children will move along with us, so it is OK for them. Otherwise this is a RESIZE,
             * where children do not move, so the resize is constrained by the range currently occupied by them.
             */
            boolean okForChildren = isMove || RangeHelper.verticalRange(newBounds).includes(self.getISequenceEvent().getOccupiedRange());
            // linkedMessages are moved during execution resize too
            // boolean okForLinkedMessages = isMove ||
            // validateMessageToSelfMinRanges(self, request,
            // GraphicalHelper.verticalRange(bounds),
            // GraphicalHelper.verticalRange(newBounds));
            boolean okForParent = ((AbstractNodeEvent) self.getISequenceEvent()).getLifeline().get().getValidSubEventsRange().includes(RangeHelper.verticalRange(newBounds));
            /*
             * Expansion of the operand is valid and triggers a zone expansion.
             */
            if (requestQuery.isResize()) {
                if (parent instanceof Operand) {
                    okForParent = validateNewBounds(self, newBounds, (Operand) parent);
                } else if (!parent.getVerticalRange().includes(RangeHelper.verticalRange(newBounds))) {
                    okForParent = false;
                }
            }
            /*
             * Also check that the messages which will move with us will not become inconsistent.
             */
            boolean okForMessageEnds = (expansionZone != null) || validateMessageEndsConsistency(self, bounds, newBounds);
            result = okForChildren && okForParent && okForMessageEnds;
        }
        return result;
    }

    private boolean validateNewBounds(ExecutionEditPart self, Rectangle newBounds, Operand parent) {
        boolean okForParent = false;
        if (requestQuery.isResizeFromBottom()) {
            if (parent.getValidSubEventsRange().getLowerBound() <= RangeHelper.verticalRange(newBounds).getLowerBound()) {
                okForParent = true;
                if (parent.getValidSubEventsRange().getUpperBound() < RangeHelper.verticalRange(newBounds).getUpperBound()) {
                    expansionZone = new Range(parent.getValidSubEventsRange().getUpperBound(), RangeHelper.verticalRange(newBounds).getUpperBound());
                }
            }
        } else if (requestQuery.isResizeFromTop()) {
            // We consider that parent is resize before if needed. There is just an exception when the execution is just
            // on the lower bound limit of the operand.
            if (parent.getVerticalRange().getLowerBound() != RangeHelper.verticalRange(newBounds).getLowerBound()) {
                okForParent = true;
            }
        } else if (parent.getValidSubEventsRange().includes(RangeHelper.verticalRange(newBounds))) {
            okForParent = true;
        }
        return okForParent;
    }

    /**
     * If this execution is delimited by a start and finish message, make sure they always point to the same remote
     * execution/lifeline.
     * 
     * @param move
     *            indicates the current action : move or resize.
     */
    private boolean validateMessageEndsConsistency(ExecutionEditPart self, Rectangle bounds, Rectangle newBounds) {
        boolean result = true;
        AbstractNodeEvent abstractNodeEvent = (AbstractNodeEvent) self.getISequenceEvent();
        List<Message> delimitingMessages = Lists.newArrayList(Iterables.filter(EventEndHelper.getCompoundEvents(self.getISequenceEvent()), Message.class));
        if (delimitingMessages.size() == 2) {
            Message callMessage = delimitingMessages.get(0);
            Message returnMessage = delimitingMessages.get(1);

            Range oldRange = RangeHelper.verticalRange(bounds);
            Range newRange = RangeHelper.verticalRange(newBounds);
            int deltaYStart = newRange.getLowerBound() - oldRange.getLowerBound();
            int deltaYFinish = newRange.getUpperBound() - oldRange.getUpperBound();
            int deltaHStart = 0;
            int deltaHFinish = 0;

            if (requestQuery.isDirectedByMessage()) {
                if (requestQuery.isResizeFromTop() && callMessage.isReflective()) {
                    deltaHStart = deltaYStart;
                    deltaYStart = 0;
                }

                if (requestQuery.isResizeFromBottom() && returnMessage.isReflective()) {
                    deltaHFinish = -deltaYFinish;
                }
            }

            ISequenceEvent startMessageRemote = FinalParentHelper.getFinalRemoteParent(abstractNodeEvent, delimitingMessages.get(0), deltaYStart, deltaHStart);
            ISequenceEvent finishMessageRemote = FinalParentHelper.getFinalRemoteParent(abstractNodeEvent, delimitingMessages.get(1), deltaYFinish, deltaHFinish);
            result = startMessageRemote == finishMessageRemote;

            if (!result && requestQuery.isResizeFromBottom() && finishMessageRemote != null) {
                Range verticalRange = finishMessageRemote.getVerticalRange();
                if (newRange.getLowerBound() < verticalRange.getLowerBound()) {
                    Range errorRange = new Range(verticalRange.getLowerBound() - 1, newRange.getUpperBound());
                    expansionZone = expansionZone == null ? errorRange : errorRange.union(expansionZone);
                    result = true;
                }
            }

            result = result && respectLowRangeMarginOfParent(startMessageRemote, callMessage, newRange) && respectUpperRangeMarginOfParent(startMessageRemote, returnMessage, newRange);

        } else if (delimitingMessages.size() == 1) {
            Message msg = delimitingMessages.get(0);

            List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(self.getISequenceEvent());
            SingleEventEnd delimitedSee = getDelimitedSingleEventEnd(self, ends);

            Range oldRange = RangeHelper.verticalRange(bounds);
            Range newRange = RangeHelper.verticalRange(newBounds);
            int deltaYStart = newRange.getLowerBound() - oldRange.getLowerBound();
            int deltaYFinish = newRange.getUpperBound() - oldRange.getUpperBound();

            int delimitedDeltaY = 0;
            int delimitedDeltaH = 0;
            if (delimitedSee != null) {
                delimitedDeltaY = delimitedSee.isStart() ? deltaYStart : deltaYFinish;

                if (requestQuery.isDirectedByMessage()) {
                    if (delimitedSee.isStart() && requestQuery.isResizeFromTop() && msg.isReflective()) {
                        delimitedDeltaH = delimitedDeltaY;
                        delimitedDeltaY = 0;
                    }

                    if (!delimitedSee.isStart() && requestQuery.isResizeFromBottom() && msg.isReflective()) {
                        delimitedDeltaH = -delimitedDeltaY;
                    }
                }
            }

            ISequenceEvent prevMessageRemote = FinalParentHelper.getFinalRemoteParent(abstractNodeEvent, msg, 0, 0);
            ISequenceEvent delimitingMessageRemote = FinalParentHelper.getFinalRemoteParent(abstractNodeEvent, msg, delimitedDeltaY, delimitedDeltaH);
            result = delimitingMessageRemote != null;
            result = result && respectLowRangeMarginOfParent(prevMessageRemote, msg, newRange);
            result = result && prevMessageRemote != delimitingMessageRemote ? respectLowRangeMarginOfParent(delimitingMessageRemote, msg, newRange) : true;
        }
        return result;
    }

    /**
     * Check upper range margin between parentSequenceEventEditPart and returnMessageEditPart respect
     * LayoutConstants.EXECUTION_CHILDREN_MARGIN margin
     * 
     * @param parentSequenceEventEditPart
     * @param returnMessageEditPart
     * @param newRange
     * 
     * @return
     */
    private boolean respectUpperRangeMarginOfParent(ISequenceEvent parentSequenceEvent, Message returnMessage, Range newRange) {
        boolean upperRangeMarginOK = true;
        if (parentSequenceEvent != null) {
            Range remoteParentRange = parentSequenceEvent.getVerticalRange();
            Range finishMessageRange = returnMessage.getVerticalRange();
            if (requestQuery.isDirectedByMessage() && requestQuery.isResizeFromBottom() && returnMessage.isReflective()) {
                finishMessageRange = new Range(finishMessageRange.getLowerBound() + requestQuery.getLogicalDelta().height, finishMessageRange.getUpperBound());
            }
            int upperRangeMargin = Math.abs(remoteParentRange.getUpperBound() - (newRange.getUpperBound() + finishMessageRange.width()));
            upperRangeMarginOK = upperRangeMargin >= LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        }
        return upperRangeMarginOK;
    }

    /**
     * Check if lower range margin between parentSequenceEventEditPart and callMessageEditPart respect
     * LayoutConstants.EXECUTION_CHILDREN_MARGIN margin
     * 
     * @param parentSequenceEventEditPart
     * @param callMessageEditPart
     * @param newRange
     * @return
     */
    private boolean respectLowRangeMarginOfParent(ISequenceEvent parentSequenceEvent, Message callMessage, Range newRange) {
        boolean lowerRangeMarginOK = true;
        if (parentSequenceEvent != null) {
            Range remoteParentRange = parentSequenceEvent.getVerticalRange();
            Range startMessageRange = callMessage.getVerticalRange();

            int lowerRangeMargin = Math.abs((newRange.getLowerBound() - startMessageRange.width()) - remoteParentRange.getLowerBound());
            lowerRangeMarginOK = lowerRangeMargin >= LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        }
        return lowerRangeMarginOK;
    }

    private SingleEventEnd getDelimitedSingleEventEnd(ExecutionEditPart self, List<EventEnd> ends) {
        Iterable<CompoundEventEnd> thisDelimitedEnds = Iterables.filter(ends, CompoundEventEnd.class);
        if (!Iterables.isEmpty(thisDelimitedEnds)) {
            return EventEndHelper.getSingleEventEnd(thisDelimitedEnds.iterator().next(), self.resolveTargetSemanticElement());
        }
        return null;
    }

    public Range getExpansionZone() {
        return expansionZone;
    }

    public void setExpansionZone(Range expansionZone) {
        this.expansionZone = expansionZone;
    }

    /**
     * Return the final hierarchical parent.
     * 
     * @return the final hierarchical parent.
     */
    public ISequenceEvent getFinalHierarchicalParent() {
        return getReconnectionFinalParent(finalParent);
    }

    private ISequenceEvent getReconnectionFinalParent(ISequenceEvent ise) {
        if (ise instanceof Operand) {
            return getReconnectionFinalParent(((Operand) ise).getCombinedFragment());
        }

        ISequenceEvent reconnectionParent = ise;
        if (ise instanceof AbstractFrame) {
            AbstractFrame frame = (AbstractFrame) ise;
            Collection<ISequenceEvent> computeParentEvents = frame.computeParentEvents(Collections.singletonList(host.getLifeline().get()));
            ISequenceEvent iSequenceEvent = Iterables.get(computeParentEvents, 0);

            if (iSequenceEvent instanceof Lifeline || iSequenceEvent instanceof AbstractNodeEvent) {
                reconnectionParent = iSequenceEvent;
            } else {
                reconnectionParent = getReconnectionFinalParent(iSequenceEvent);
            }
        }
        return reconnectionParent;
    }

    public void setFinalParent(ISequenceEvent finalParent) {
        this.finalParent = finalParent;
    }

    /**
     * Get the validator from the request extended data or a new one.
     * 
     * @param cbr
     *            the current request.
     * @param host
     *            the host execution
     * @return a validator.
     */
    public static AbstractNodeEventResizeSelectionValidator getOrCreateValidator(ChangeBoundsRequest cbr, AbstractNodeEvent host) {
        AbstractNodeEventResizeSelectionValidator validator = null;
        if (lastRequest != cbr) {
            lastValidator = null;
            lastRequest = null;
        } else {
            validator = lastValidator;
        }
        RequestQuery requestQuery = new RequestQuery(cbr);
        if (validator != null && validator.getRequestQuery().getLogicalDelta() != new RequestQuery(lastRequest).getLogicalDelta() && validateSameSelection(validator, cbr, requestQuery, host)) {
            validator.reInit(requestQuery);
        }

        if (validator == null && requestQuery.isResize()) {
            validator = new AbstractNodeEventResizeSelectionValidator(host, cbr);
            lastValidator = validator;
            lastRequest = cbr;
        }
        return validator;
    }

    private void reInit(RequestQuery rq) {
        validationDone = false;
        requestQuery = rq;
        valid = false;
        invalidPositions = new ArrayList<>();
    }

    private static boolean validateSameSelection(AbstractNodeEventResizeSelectionValidator validator, ChangeBoundsRequest cbr, RequestQuery requestQuery, ISequenceEvent host) {
        return !requestQuery.getISequenceEvents().isEmpty() && requestQuery.getISequenceEvents().iterator().next().equals(validator.host);
    }

    private RequestQuery getRequestQuery() {
        return requestQuery;
    }

    public Collection<Integer> getInvalidPositions() {
        return invalidPositions;
    }

    private boolean checkGlobalPositions() {
        boolean safeMove = true;
        final Option<Message> startMessage;
        final Option<Message> endMessage;

        if (host instanceof Execution) {
            Execution exec = (Execution) host;
            startMessage = exec.getStartMessage();
            endMessage = exec.getEndMessage();
        } else {
            startMessage = Options.newNone();
            endMessage = Options.newNone();
        }

        Range actualRange = host.getVerticalRange();
        Rectangle newBounds = requestQuery.getLogicalTransformedRectangle(new Rectangle(0, actualRange.getLowerBound(), 0, actualRange.width()));
        if (newBounds.height < 0) {
            safeMove = false;
        } else {
            final Range finalRange = RangeHelper.verticalRange(newBounds);
            Function<ISequenceEvent, Range> futureRangeFunction = new Function<ISequenceEvent, Range>() {
                @Override
                public Range apply(ISequenceEvent from) {
                    return getFutureRange(startMessage, endMessage, finalRange, from);
                }
            };
            invalidPositions.addAll(new PositionsChecker(host.getDiagram(), futureRangeFunction).getInvalidPositions());
            safeMove = invalidPositions.isEmpty();
        }
        return safeMove;
    }

    private Range getFutureRange(final Option<Message> startMessage, final Option<Message> endMessage, final Range finalRange, ISequenceEvent from) {
        Range verticalRange = from.getVerticalRange();
        if (host.equals(from)) {
            verticalRange = finalRange;
        } else if (startMessage.some() && startMessage.get().equals(from)) {
            boolean reflexiveStart = startMessage.get().isReflective();
            boolean obliqueStart = startMessage.get().isOblique();
            if (!reflexiveStart && !obliqueStart) {
                verticalRange = new Range(finalRange.getLowerBound(), finalRange.getLowerBound());
            } else if (requestQuery.isResizeFromTop()) {
                boolean isReflexiveMoveDirectedByMessage = reflexiveStart && requestQuery.isDirectedByMessage();
                boolean isObliqueMoveRirectedByTargetSegment = obliqueStart && requestQuery.isDirectedByMessage() && requestQuery.getObliqueMoveType().isPresent()
                        && requestQuery.getObliqueMoveType().get() == SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_TARGET;
                if (isReflexiveMoveDirectedByMessage || isObliqueMoveRirectedByTargetSegment) {
                    int validAdditionalDelta = verticalRange.getLowerBound() > verticalRange.getUpperBound() + requestQuery.getLogicalDelta().y ? 0 : requestQuery.getLogicalDelta().y; 
                    verticalRange = new Range(verticalRange.getLowerBound(), verticalRange.getUpperBound() + validAdditionalDelta);
                } else { // reflexive or oblique, and resized -> moved message
                    verticalRange = verticalRange.shifted(requestQuery.getLogicalDelta().y);
                }
            }
        } else if (endMessage.some() && endMessage.get().equals(from)) {
            boolean reflexiveEnd = endMessage.get().isReflective();
            boolean obliqueEnd = endMessage.get().isOblique();
            if (!reflexiveEnd && !obliqueEnd) {
                verticalRange = new Range(finalRange.getUpperBound(), finalRange.getUpperBound());
            } else if (requestQuery.isResizeFromBottom()) {
                if (reflexiveEnd && requestQuery.isDirectedByMessage()) {
                    int validAdditionalDelta = verticalRange.getLowerBound() + requestQuery.getLogicalDelta().height > verticalRange.getUpperBound() ? 0 : requestQuery.getLogicalDelta().height;
                    verticalRange = new Range(verticalRange.getLowerBound() + validAdditionalDelta, verticalRange.getUpperBound());
                } else { // reflexive and resized -> moved message
                    verticalRange = verticalRange.shifted(requestQuery.getLogicalDelta().height);
                }
            }
        } else if (from instanceof Operand && from.equals(host.getParentEvent())) {
            // The parent Operand will be resized.
            verticalRange = finalRange.grown(LayoutConstants.EXECUTION_CHILDREN_MARGIN).union(verticalRange);
        } else if (from instanceof CombinedFragment && host.getParentEvent() != null && from.equals(host.getParentEvent().getParentEvent())) {
            // The grand parent CombinedFragment will be resized.
            verticalRange = finalRange.shifted(-LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT - LayoutConstants.EXECUTION_CHILDREN_MARGIN).union(verticalRange);
        }
        return verticalRange;
    }
}
