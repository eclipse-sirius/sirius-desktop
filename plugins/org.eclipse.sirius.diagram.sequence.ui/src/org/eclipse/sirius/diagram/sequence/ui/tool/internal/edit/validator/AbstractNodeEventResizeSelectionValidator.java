/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.FinalParentHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Abstract class to validate Execution move & resize request and get from it a
 * command.
 * 
 * @author edugueperoux
 * 
 */
public class AbstractNodeEventResizeSelectionValidator {

    /**
     * Key constant use for request on multiple selection.
     */
    public static final String GROUP_REQUEST_ALREADY_ANSWERED = "Already answered";

    private static final String EXECUTION_RESIZE_VALIDATOR = "org.eclipse.sirius.sequence.resize.execution.validator"; //$NON-NLS-1$

    /**
     * The expansionZine.
     */
    protected Range expansionZone;

    /**
     * The finalParent.
     */
    protected ISequenceEvent finalParent;

    /**
     * Common map of future location for executions in move/resize.
     */
    protected Map<AbstractNodeEvent, Location> moveDeltas = new HashMap<AbstractNodeEvent, Location>();

    private boolean valid;

    private AbstractNodeEvent host;

    private ChangeBoundsRequest request;

    private boolean initialized;

    private Collection<Integer> invalidPositions = Lists.newArrayList();

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
     * Return the validation status. Validate the request result in the first
     * call only.
     * 
     * @return the validation status.
     */
    public final boolean isValid() {
        validate();
        return valid;
    }

    /**
     * Performs all the computations required to validate the resizing, and
     * stores any important information which will be useful to actually execute
     * the move if it is valid, like for example avoid contact with siblings or
     * handle reconnection.
     */
    public final void validate() {
        if (!initialized) {
            doValidation();
            initialized = true;
        }
    }

    /**
     * Performs all the computations required to validate the resizing, and
     * stores any important information which will be useful to actually execute
     * the resize if it is valid, like for example avoid contact with siblings.
     */
    private void doValidation() {
        Preconditions.checkNotNull(host, "validator must know on which executions check the request validation");

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
     * Verifies whether the new position/size of the execution would still be
     * valid if we accept the request. In particular, ensures sibling events can
     * not overlap.
     * 
     * @return true if the request is validated to true
     */
    protected boolean validateNewBoundsForAllTargets() {
        return Iterables.all(Iterables.filter(request.getEditParts(), ExecutionEditPart.class), new Predicate<ExecutionEditPart>() {
            public boolean apply(ExecutionEditPart input) {
                return validateNewBounds(input);
            }
        });
    }

    /**
     * Checks whether the new bounds implied by the requested change are valid
     * for this execution. Uses Draw2D information to get the current bounds.
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
             * If this is a MOVE, children will move along with us, so it is OK
             * for them. Otherwise this is a RESIZE, where children do not move,
             * so the resize is constrained by the range currently occupied by
             * them.
             */
            boolean okForChildren = isMove || RangeHelper.verticalRange(newBounds).includes(self.getISequenceEvent().getOccupiedRange());
            // linkedMessages are moved during execution resize too
            // boolean okForLinkedMessages = isMove ||
            // validateMessageToSelfMinRanges(self, request,
            // GraphicalHelper.verticalRange(bounds),
            // GraphicalHelper.verticalRange(newBounds));
            boolean okForParent = ((AbstractNodeEvent) self.getISequenceEvent()).getLifeline().get().getValidSubEventsRange().includes(RangeHelper.verticalRange(newBounds));
            /*
             * Do not allow resize to expand beyond the range of the parent.
             */
            if (requestQuery.isResize()) {
                if (!parent.getVerticalRange().includes(RangeHelper.verticalRange(newBounds))) {
                    okForParent = false;
                }
            }
            /*
             * Also check that the messages which will move with us will not
             * become inconsistent.
             */
            boolean okForMessageEnds = (expansionZone != null) || validateMessageEndsConsistency(self, bounds, newBounds);
            result = okForChildren && okForParent && okForMessageEnds;
        }
        return result;
    }

    /**
     * If this execution is delimited by a start and finish message, make sure
     * they always point to the same remote execution/lifeline.
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
                    Message msg = delimitingMessages.get(0);
                    if (delimitedSee.isStart() && requestQuery.isResizeFromTop() && msg.isReflective()) {
                        delimitedDeltaH = delimitedDeltaY;
                        delimitedDeltaY = 0;
                    }

                    if (!delimitedSee.isStart() && requestQuery.isResizeFromBottom() && msg.isReflective()) {
                        delimitedDeltaH = -delimitedDeltaY;
                    }
                }
            }

            ISequenceEvent prevMessageRemote = FinalParentHelper.getFinalRemoteParent(abstractNodeEvent, delimitingMessages.get(0), 0, 0);
            ISequenceEvent delimitingMessageRemote = FinalParentHelper.getFinalRemoteParent(abstractNodeEvent, delimitingMessages.get(0), delimitedDeltaY, delimitedDeltaH);
            result = prevMessageRemote == delimitingMessageRemote;

            // prevMessageRemote == delimitingMessageRemote
            Message callMessage = delimitingMessages.get(0);
            result = result && respectLowRangeMarginOfParent(prevMessageRemote, callMessage, newRange);

        }
        return result;
    }

    /**
     * Check upper range margin between parentSequenceEventEditPart and
     * returnMessageEditPart respect LayoutConstants.EXECUTION_CHILDREN_MARGIN
     * margin
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
     * Check if lower range margin between parentSequenceEventEditPart and
     * callMessageEditPart respect LayoutConstants.EXECUTION_CHILDREN_MARGIN
     * margin
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
    @SuppressWarnings("unchecked")
    public static AbstractNodeEventResizeSelectionValidator getOrCreateValidator(ChangeBoundsRequest cbr, AbstractNodeEvent host) {
        RequestQuery requestQuery = new RequestQuery(cbr);
        AbstractNodeEventResizeSelectionValidator validator = null;
        Object object = cbr.getExtendedData().get(EXECUTION_RESIZE_VALIDATOR);
        if (object instanceof AbstractNodeEventResizeSelectionValidator) {
            validator = (AbstractNodeEventResizeSelectionValidator) object;
            if (!validator.getRequestQuery().getLogicalDelta().equals(requestQuery.getLogicalDelta())) {
                validator = null;
            }
        }

        if (validator == null && requestQuery.isResize()) {
            validator = new AbstractNodeEventResizeSelectionValidator(host, cbr);
            cbr.getExtendedData().put(EXECUTION_RESIZE_VALIDATOR, validator);
        }
        return validator;
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

                public Range apply(ISequenceEvent from) {
                    Range verticalRange = from.getVerticalRange();
                    if (host.equals(from)) {
                        verticalRange = finalRange;
                    } else if (startMessage.some() && startMessage.get().equals(from)) {
                        if (!startMessage.get().isReflective()) {
                            verticalRange = new Range(finalRange.getLowerBound(), finalRange.getLowerBound());
                        } else if (requestQuery.isResizeFromTop()) {
                            if (requestQuery.isDirectedByMessage()) {
                                verticalRange = new Range(verticalRange.getLowerBound(), verticalRange.getUpperBound() + requestQuery.getLogicalDelta().y);
                            } else {
                                // reflexive and resized -> moved message
                                verticalRange = verticalRange.shifted(requestQuery.getLogicalDelta().y);
                            }
                        }
                    } else if (endMessage.some() && endMessage.get().equals(from)) {
                        if (!endMessage.get().isReflective()) {
                            verticalRange = new Range(finalRange.getUpperBound(), finalRange.getUpperBound());
                        } else if (requestQuery.isResizeFromBottom()) {
                            if (requestQuery.isDirectedByMessage()) {
                                verticalRange = new Range(verticalRange.getLowerBound() + requestQuery.getLogicalDelta().height, verticalRange.getUpperBound());
                            } else { // reflexive and resized -> moved message
                                verticalRange = verticalRange.shifted(requestQuery.getLogicalDelta().height);
                            }
                        }
                    }

                    return verticalRange;
                }
            };
            invalidPositions.addAll(new PositionsChecker(host.getDiagram(), futureRangeFunction).getInvalidPositions());
            safeMove = invalidPositions.isEmpty();
        }
        return safeMove;
    }
}
