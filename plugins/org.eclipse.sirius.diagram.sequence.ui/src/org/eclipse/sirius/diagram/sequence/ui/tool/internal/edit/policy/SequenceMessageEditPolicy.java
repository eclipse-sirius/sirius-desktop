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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.EndOfLifeMoveOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SetMessageRangeOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.ShiftDirectSubExecutionsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceMessageViewQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.EndOfLifeOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ShiftDescendantMessagesOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.PositionsChecker;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.HorizontalGuide;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swt.graphics.Color;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Specialized edit policy for sequence diagrams messages: tracks graphical
 * reordering and invokes user-specified reordering tool to reflect the changes
 * in the semantic model.
 * <p>
 * This edit policy should only be installed on SequenceMessageEditParts.
 * 
 * @author pcdavid
 */
public class SequenceMessageEditPolicy extends ConnectionBendpointEditPolicy {

    /**
     * Key constant use for request from a BendpointRequest on Message.
     */
    public static final String REQUEST_FROM_SEQUENCE_MESSAGE_EDIT_POLICY = "Request a Execution resize from a BendpointRequest";

    /**
     * The color top use for the horizontal feedback rules shown when moving a
     * message.
     */
    private static final Color MESSAGE_FEEDBACK_COLOR = ColorConstants.lightGray;

    private final Collection<Figure> guides = Lists.newArrayList();

    /**
     * Saves the validation status of the command on bendpoint move.
     */
    private boolean invalidCommand;

    /**
     * Overridden to check that we are only installed on sequence messages.
     */
    @Override
    @SuppressWarnings("restriction")
    public void activate() {
        Preconditions.checkState(getHost() instanceof SequenceMessageEditPart);
        super.activate();
    }

    protected SequenceMessageEditPart getMessage() {
        return (SequenceMessageEditPart) getHost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addInvisibleCreationHandle(@SuppressWarnings("rawtypes") List list, ConnectionEditPart connEP, int i) {
        /*
         * Do nothing: the handles created by default use a raw GEF drag tracker
         * which we do not control, and which can lead to disconnections of
         * branches on reflective messages.
         */
    }

    /**
     * Update the location of the horizontal feedback line.
     * <p>
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("restriction")
    public void showSourceFeedback(Request request) {
        removeFeedBackOnGuides();

        if (request instanceof BendpointRequest) {
            BendpointRequest br = (BendpointRequest) request;
            SequenceMessageEditPart thisEvent = (SequenceMessageEditPart) getHost();
            ISequenceEvent iSequenceEvent = thisEvent.getISequenceEvent();
            List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(iSequenceEvent);
            super.showSourceFeedback(request);

            MoveType moveType = getMoveType(thisEvent, br, ends);
            if (moveType.needsCompoundMove()) {
                showCompoundEndFeedback(br, thisEvent, ends, moveType.isFromTop());
            } else {
                Point location = new Point(1, thisEvent.getConnectionFigure().getPoints().getFirstPoint().y);
                location.performScale(GraphicalHelper.getZoom(getHost()));

                Figure guide = new HorizontalGuide(MESSAGE_FEEDBACK_COLOR, location.y);
                Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
                bounds.height = 1;
                bounds.y = location.y;
                guide.setBounds(bounds);
                addFeedback(guide);
                guides.add(guide);

                if (new ISequenceEventQuery(getMessage().getISequenceEvent()).isReflectiveMessage()) {
                    Point endLocation = new Point(1, thisEvent.getConnectionFigure().getPoints().getLastPoint().y);
                    endLocation.performScale(GraphicalHelper.getZoom(getHost()));
                    Figure messageToSelfBottomGuide = new HorizontalGuide(MESSAGE_FEEDBACK_COLOR, endLocation.y);
                    bounds = getFeedbackLayer().getBounds().getCopy();
                    bounds.height = 1;
                    bounds.y = endLocation.y;
                    messageToSelfBottomGuide.setBounds(bounds);
                    addFeedback(messageToSelfBottomGuide);
                    guides.add(messageToSelfBottomGuide);
                }

                if (thisEvent.getTarget() instanceof InstanceRoleEditPart) {
                    showInstanceRoleFeedback(br);
                } else if (thisEvent.getTarget() instanceof EndOfLifeEditPart) {
                    showEndOfLifeFeedback(br);
                }

                Point reqLoc = br.getLocation().getCopy();
                GraphicalHelper.screen2logical(reqLoc, (IGraphicalEditPart) getHost());
                Option<Range> finalRange = computeFinalRange(br, thisEvent, reqLoc);
                if (finalRange.some()) {
                    Collection<Integer> invalidPositions = checkGlobalPositions(iSequenceEvent, finalRange);
                    for (Integer conflict : invalidPositions) {
                        bounds = getFeedbackLayer().getBounds().getCopy();

                        Point conflictingPosition = new Point(0, conflict);
                        conflictingPosition.performScale(GraphicalHelper.getZoom(getHost()));

                        HorizontalGuide conflictGuide = new HorizontalGuide(ColorConstants.red, conflictingPosition.y);
                        bounds.y = conflictingPosition.y;
                        bounds.height = 1;
                        conflictGuide.setBounds(bounds);
                        addFeedback(conflictGuide);
                        guides.add(conflictGuide);
                    }
                }
            }
        }
    }

    private void removeFeedBackOnGuides() {
        for (Figure fig : guides) {
            removeFeedback(fig);
        }
        guides.clear();

    }

    private void showCompoundEndFeedback(BendpointRequest request, SequenceMessageEditPart thisEvent, List<EventEnd> ends, boolean fromTop) {
        final EObject thisSemanticEvent = thisEvent.resolveTargetSemanticElement();
        final SequenceDiagramEditPart sdep = EditPartsHelper.getSequenceDiagramPart(thisEvent);
        final Range thisRange = thisEvent.getISequenceEvent().getVerticalRange();
        final Point location = request.getLocation().getCopy();

        final Predicate<SingleEventEnd> toMove = new Predicate<SingleEventEnd>() {
            public boolean apply(SingleEventEnd input) {
                return !input.getSemanticEvent().equals(thisSemanticEvent);
            }
        };

        Dimension resizeDelta = getResizeDelta(location.getCopy(), thisEvent, thisRange, fromTop);
        for (CompoundEventEnd cee : Iterables.filter(ends, CompoundEventEnd.class)) {
            for (SingleEventEnd see : Iterables.filter(Lists.newArrayList(cee.getEventEnds()), toMove)) {
                ISequenceEventEditPart ise = EditPartsHelper.findISequenceEvent(see, sdep);
                ChangeBoundsRequest cbr = buildChangeBoundRequest(location.getCopy(), thisEvent, see, resizeDelta);
                ise.showSourceFeedback(cbr);
            }
        }
    }

    private void eraseCompoundEndFeedback(BendpointRequest request, SequenceMessageEditPart thisEvent, List<EventEnd> ends, boolean fromTop) {
        final EObject thisSemanticEvent = thisEvent.resolveTargetSemanticElement();
        final SequenceDiagramEditPart sdep = EditPartsHelper.getSequenceDiagramPart(thisEvent);
        final Range thisRange = thisEvent.getISequenceEvent().getVerticalRange();
        final Point location = request.getLocation().getCopy();

        final Predicate<SingleEventEnd> toMove = new Predicate<SingleEventEnd>() {
            public boolean apply(SingleEventEnd input) {
                return !input.getSemanticEvent().equals(thisSemanticEvent);
            }
        };

        Dimension resizeDelta = getResizeDelta(location.getCopy(), thisEvent, thisRange, fromTop);
        for (CompoundEventEnd cee : Iterables.filter(ends, CompoundEventEnd.class)) {
            for (SingleEventEnd see : Iterables.filter(Lists.newArrayList(cee.getEventEnds()), toMove)) {
                ISequenceEventEditPart ise = EditPartsHelper.findISequenceEvent(see, sdep);
                ChangeBoundsRequest cbr = buildChangeBoundRequest(location.getCopy(), thisEvent, see, resizeDelta);
                ise.eraseSourceFeedback(cbr);
            }
        }
    }

    /**
     * Moving a create message up and down will also show the feedback of the
     * targeted instance role.
     * 
     * @param request
     *            the "create message" move request
     */
    private void showInstanceRoleFeedback(BendpointRequest request) {
        InstanceRoleEditPart target = (InstanceRoleEditPart) ((SequenceMessageEditPart) getHost()).getTarget();
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);
        Point scrollSize = GraphicalHelper.getScrollSize(target);
        IGraphicalEditPart source = (IGraphicalEditPart) ((SequenceMessageEditPart) getHost()).getSource();
        int feedbackRangeLimit = request.getLocation().y;
        Rectangle sourceBbounds = source.getFigure().getBounds();

        // limit the vertical move to the first sequence event of the targeted
        // instance role
        int firstMessageInTargetInstanceRole = Integer.MAX_VALUE;

        LifelineEditPart lifelineEditPart = Iterables.getOnlyElement(EditPartsHelper.getAllLifelines(target));
        Range occupiedRange = lifelineEditPart.getISequenceEvent().getOccupiedRange();
        if (!occupiedRange.isEmpty()) {
            // limite the move to the first sequence event of the target
            // lifeline
            firstMessageInTargetInstanceRole = occupiedRange.getLowerBound() - LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        }

        if (feedbackRangeLimit < sourceBbounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN) {
            feedbackRangeLimit = sourceBbounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        } else if (firstMessageInTargetInstanceRole < feedbackRangeLimit + LayoutConstants.EXECUTION_CHILDREN_MARGIN) {
            feedbackRangeLimit = firstMessageInTargetInstanceRole - LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        } else if (feedbackRangeLimit + GraphicalHelper.getScrollSize(target).y > sourceBbounds.y + sourceBbounds.height - LayoutConstants.EXECUTION_CHILDREN_MARGIN) {
            feedbackRangeLimit = sourceBbounds.y + sourceBbounds.height - GraphicalHelper.getScrollSize(target).y - LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        }

        cbr.getMoveDelta().y = feedbackRangeLimit - (target.getFigure().getBounds().y + target.getFigure().getBounds().height / 2) + scrollSize.y; // br.getLocation().y
        target.showSourceFeedback(cbr);
    }

    /**
     * Moving a destroy message up and down will also show the feedback of the
     * targeted end of life.
     * 
     * @param request
     *            the "create message" move request
     */
    private void showEndOfLifeFeedback(Request request) {
        EndOfLifeEditPart endOfLifeEditPart = (EndOfLifeEditPart) ((SequenceMessageEditPart) getHost()).getTarget();
        EndOfLifeOperations.showEndOfLifeFeedback(request, endOfLifeEditPart, (IGraphicalEditPart) ((SequenceMessageEditPart) getHost()).getSource());
    }

    /**
     * Remove the the horizontal feedback line.
     * <p>
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("restriction")
    public void eraseSourceFeedback(Request request) {
        removeFeedBackOnGuides();

        super.eraseSourceFeedback(request);

        if (request instanceof BendpointRequest) {
            SequenceMessageEditPart thisEvent = (SequenceMessageEditPart) getHost();
            List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(((ISequenceEventEditPart) getHost()).getISequenceEvent());
            BendpointRequest br = (BendpointRequest) request;
            // thisEvent.setCursor(org.eclipse.gmf.runtime.gef.ui.internal.l10n.Cursors.CURSOR_SEG_MOVE);
            if (thisEvent.getTarget() instanceof InstanceRoleEditPart) {
                InstanceRoleEditPart target = (InstanceRoleEditPart) thisEvent.getTarget();
                ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);
                cbr.getMoveDelta().y = br.getLocation().y - (target.getFigure().getBounds().y + target.getFigure().getBounds().height / 2); // br.getLocation().y
                target.eraseSourceFeedback(cbr);
            } else if (thisEvent.getTarget() instanceof EndOfLifeEditPart) {
                EndOfLifeOperations.eraseEndOfLifeFeedback((LifelineEditPart) thisEvent.getTarget().getParent(), br);
            } else {
                MoveType moveType = getMoveType(thisEvent, br, ends);
                if (moveType.needsCompoundMove()) {
                    eraseCompoundEndFeedback(br, thisEvent, ends, moveType.isFromTop());
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_SET_ALL_BENDPOINT.equals(request.getType())) {
            return getHost();
        } else {
            return super.getTargetEditPart(request);
        }
    }

    /**
     * Change the place of the message in the semantic model if the new
     * graphical positions requires it.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected Command getBendpointsChangedCommand(BendpointRequest request) {
        invalidCommand = false;

        SequenceMessageEditPart thisEvent = (SequenceMessageEditPart) getHost();
        Command result;
        Command baseCommand = super.getBendpointsChangedCommand(request);
        ICommand smrc = null;

        Point location = request.getLocation().getCopy();
        GraphicalHelper.screen2logical(location, (IGraphicalEditPart) getHost());

        Option<Range> finalRange = computeFinalRange(request, thisEvent, location);

        if (finalRange.some()) {
            smrc = createReconnectionCommandOnBendpointMove(request, thisEvent, location, finalRange.get());
        }

        List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(thisEvent.getISequenceEvent());
        invalidCommand = invalidCommand || !baseCommand.canExecute() || !finalRange.some();
        invalidCommand = invalidCommand || org.eclipse.gef.RequestConstants.REQ_MOVE_BENDPOINT.equals(request.getType());
        invalidCommand = invalidCommand || !validateMessageParentOperand(finalRange);
        invalidCommand = invalidCommand || !checkGlobalPositions(thisEvent.getISequenceEvent(), finalRange).isEmpty();

        if (invalidCommand) {
            thisEvent.setCursor(org.eclipse.draw2d.Cursors.NO);
            result = UnexecutableCommand.INSTANCE;
        } else {
            String label = baseCommand.getLabel();
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(thisEvent.getEditingDomain(), (label != null ? label : "<null>") + " and synchronize ordering"); //$NON-NLS-1$
            SequenceEditPartsOperations.appendFullRefresh(thisEvent, ctc);

            MoveType move = getMoveType(thisEvent, request, ends);
            if (move.needsCompoundMove()) {
                addCompoundEventCommands(ctc, thisEvent, ends, request, move.isFromTop());
                thisEvent.setCursor(Cursors.SIZENS);
            } else {
                /*
                 * Overridden to handle lifeline move/resize when moving the
                 * selected create/destroy message.
                 */
                if (thisEvent.getTarget() instanceof InstanceRoleEditPart) {
                    ctc.compose(getMoveCreateMessageCommand(request, thisEvent, baseCommand));
                } else if (thisEvent.getTarget() instanceof EndOfLifeEditPart) {
                    ctc.compose(getMoveDestroyMessageCommand(request, thisEvent, baseCommand));
                }
                ctc.compose(smrc);

                SequenceEditPartsOperations.addRefreshGraphicalOrderingCommand(ctc, thisEvent);
                SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(ctc, thisEvent.getISequenceEvent());
                thisEvent.setCursor(org.eclipse.gmf.runtime.gef.ui.internal.l10n.Cursors.CURSOR_SEG_MOVE);
            }

            SequenceEditPartsOperations.appendFullRefresh(thisEvent, ctc);
            // if (!(thisEvent.getTarget() instanceof EndOfLifeEditPart ||
            // thisEvent.getTarget() instanceof InstanceRoleEditPart)) {
            // addRequestLayout(cc, thisEvent);
            // }

            if (!ctc.canExecute()) {
                thisEvent.setCursor(org.eclipse.draw2d.Cursors.NO);
                result = UnexecutableCommand.INSTANCE;
            } else {
                result = new ICommandProxy(ctc);
            }
        }
        return result;
    }

    private Collection<Integer> checkGlobalPositions(final ISequenceEvent thisEvent, final Option<Range> finalRange) {
        Function<ISequenceEvent, Range> futureRangeFunction = new Function<ISequenceEvent, Range>() {
            public Range apply(ISequenceEvent from) {
                Range verticalRange = from.getVerticalRange();
                if (thisEvent.equals(from) && finalRange.some()) {
                    verticalRange = finalRange.get();
                }
                return verticalRange;
            }
        };
        return new PositionsChecker(thisEvent.getDiagram(), futureRangeFunction).getInvalidPositions();
    }

    private ICommand createReconnectionCommandOnBendpointMove(BendpointRequest request, SequenceMessageEditPart thisEvent, Point location, Range finalRange) {
        SetMessageRangeOperation smrc = new SetMessageRangeOperation((Edge) thisEvent.getNotationView(), finalRange);
        Message message = (Message) thisEvent.getISequenceEvent();
        boolean reflectiveMessage = message.isReflective();

        setOperations(true, message, finalRange, smrc, reflectiveMessage);
        setOperations(false, message, finalRange, smrc, reflectiveMessage);

        return CommandFactory.createICommand(thisEvent.getEditingDomain(), smrc);
    }

    private void setOperations(boolean source, Message message, Range finalRange, SetMessageRangeOperation smrc, boolean reflectiveMessage) {
        Range messageEndRange = source ? new Range(finalRange.getLowerBound(), finalRange.getLowerBound()) : new Range(finalRange.getUpperBound(), finalRange.getUpperBound());
        ISequenceNode currentEnd = source ? message.getSourceElement() : message.getTargetElement();
        Option<Lifeline> endLifeline = currentEnd.getLifeline();
        if (endLifeline.some() && currentEnd instanceof ISequenceEvent) {
            ISequenceEvent finalEnd;
            Range finalEndRange;

            EventFinder endFinder = new EventFinder(endLifeline.get());
            endFinder.setReconnection(true);

            if (!reflectiveMessage) {
                finalEnd = endFinder.findMostSpecificEvent(finalRange);
            } else {
                finalEnd = (ISequenceEvent) currentEnd;

                boolean compoundSrc = finalEnd instanceof Execution && message.equals(source ? ((Execution) finalEnd).getEndMessage().get() : ((Execution) finalEnd).getStartMessage().get());
                if (!compoundSrc && !finalEnd.equals(endFinder.findMostSpecificEvent(messageEndRange))) {
                    // It is not allowed to reconnect a reflexive message by
                    // moving bendpoints
                    invalidCommand = true;
                }

                // look for event source
                endFinder.setReconnection(false);
                ISequenceEvent potentialSource = endFinder.findMostSpecificEvent(messageEndRange);
                if (potentialSource instanceof CombinedFragment) {
                    invalidCommand = true;
                }
            }

            // finalSrc can be null while moving a message under its lifeline.
            // finalTgt cannot be null : restrain the capability to move out of
            // lifeline for destruction messages.
            if (source && finalEnd == null) {
                finalEnd = (ISequenceEvent) currentEnd;
            }

            // look for event end (source/target)
            endFinder.setReconnection(false);
            ISequenceEvent realEnd = endFinder.findMostSpecificEvent(finalRange);
            if (realEnd == null) {
                // realEnd can be null while moving a message under its
                // lifeline
                realEnd = (ISequenceEvent) currentEnd;
            }

            boolean noValidation = reflectiveMessage || realEnd instanceof Lifeline;
            if (!invalidCommand) {
                if (finalEnd != null && (noValidation || realEnd != null && realEnd.canChildOccupy(message, finalRange))) {
                    finalEndRange = finalEnd.getVerticalRange();
                    Rectangle endBounds = new Rectangle(0, finalEndRange.getLowerBound(), 0, finalEndRange.width());
                    if (source) {
                        smrc.setSource(finalEnd.getNotationView(), endBounds);
                    } else {
                        smrc.setTarget(finalEnd.getNotationView(), endBounds);
                    }
                } else {
                    // The message is moved beyond the lifeline range
                    invalidCommand = true;
                }
            }
        } else if (currentEnd instanceof LostMessageEnd || currentEnd instanceof EndOfLife || currentEnd instanceof InstanceRole) {
            Rectangle finalEndBounds = currentEnd.getProperLogicalBounds().getCopy();
            if (source) {
                smrc.setSource(currentEnd.getNotationView(), finalEndBounds);
            } else {
                smrc.setTarget(currentEnd.getNotationView(), finalEndBounds);
            }
        }

    }

    private Option<Range> computeFinalRange(BendpointRequest request, SequenceMessageEditPart smep, Point location) {
        Range finalRange = null;
        if (!new ISequenceEventQuery(smep.getISequenceEvent()).isReflectiveMessage()) {
            finalRange = new Range(location.y, location.y);
        } else {
            Edge edge = (Edge) smep.getNotationView();
            SequenceMessageViewQuery query = new SequenceMessageViewQuery(edge);

            int firstPointVerticalPosition = query.getFirstPointVerticalPosition(true);
            int lastPointVerticalPosition = query.getLastPointVerticalPosition(true);

            switch (request.getIndex()) {
            case 0:
                finalRange = safeComputeMessageToSelfFinalRangeFromTop(location, lastPointVerticalPosition);
                break;
            case 2:
                finalRange = safeComputeMessageToSelfFinalRangeFromBottom(firstPointVerticalPosition, location);
                break;
            case 1:
            default:
                finalRange = new Range(firstPointVerticalPosition, lastPointVerticalPosition);
                break;
            }
        }
        return Options.newSome(finalRange);
    }

    private Range safeComputeMessageToSelfFinalRangeFromBottom(int firstPointVerticalPosition, Point newBottomLocation) {
        if (newBottomLocation.y >= firstPointVerticalPosition + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP) {
            return new Range(firstPointVerticalPosition, newBottomLocation.y);
        }
        invalidCommand = true;
        return null;
    }

    private Range safeComputeMessageToSelfFinalRangeFromTop(Point newTopLocation, int lastPointVerticalPosition) {
        if (newTopLocation.y <= lastPointVerticalPosition - LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP) {
            return new Range(newTopLocation.y, lastPointVerticalPosition);
        }
        invalidCommand = true;
        return null;
    }

    private MoveType getMoveType(SequenceMessageEditPart event, BendpointRequest request, List<EventEnd> ends) {
        boolean compoundMove = !Iterables.isEmpty(Iterables.filter(ends, CompoundEventEnd.class));
        boolean msgToSelfMove = new ISequenceEventQuery(event.getISequenceEvent()).isReflectiveMessage();
        boolean needsCompoundEventCommands = compoundMove && !msgToSelfMove;
        boolean fromTop = true;
        if (compoundMove && msgToSelfMove && ends.size() == 2) {
            Point location = request.getLocation().getCopy();
            GraphicalHelper.screen2logical(location, event);
            if (request.getExtendedData().containsKey(SequenceMessageEditPart.MSG_TO_SELF_TOP_MOVE)) {
                fromTop = (Boolean) request.getExtendedData().get(SequenceMessageEditPart.MSG_TO_SELF_TOP_MOVE);
                needsCompoundEventCommands = fromTop ? ends.get(0) instanceof CompoundEventEnd : ends.get(1) instanceof CompoundEventEnd;
            } else {
                needsCompoundEventCommands = false;
            }
        }
        return new MoveType(needsCompoundEventCommands, fromTop);
    }

    private void addCompoundEventCommands(CompositeTransactionalCommand ctc, final SequenceMessageEditPart thisEvent, List<EventEnd> ends, BendpointRequest request, boolean fromTop) {
        final EObject thisSemanticEvent = thisEvent.resolveTargetSemanticElement();
        final SequenceDiagramEditPart sdep = EditPartsHelper.getSequenceDiagramPart(thisEvent);
        final Range thisRange = thisEvent.getISequenceEvent().getVerticalRange();
        final Point location = request.getLocation().getCopy();

        final Predicate<SingleEventEnd> toMove = new Predicate<SingleEventEnd>() {
            public boolean apply(SingleEventEnd input) {
                return !input.getSemanticEvent().equals(thisSemanticEvent);
            }
        };

        Dimension resizeDelta = getResizeDelta(location.getCopy(), thisEvent, thisRange, fromTop);
        for (CompoundEventEnd cee : Iterables.filter(ends, CompoundEventEnd.class)) {
            for (SingleEventEnd see : Iterables.filter(Lists.newArrayList(cee.getEventEnds()), toMove)) {
                ISequenceEventEditPart ise = EditPartsHelper.findISequenceEvent(see, sdep);
                ISequenceEvent sequenceEvent = ise.getISequenceEvent();
                ChangeBoundsRequest cbr = buildChangeBoundRequest(location.getCopy(), ise, see, resizeDelta);
                if (sequenceEvent instanceof AbstractNodeEvent) {
                    // if sequenveEvent is Execution, we must indicates to the
                    // ExecutionSelectionValidator that we want resize its
                    // Execution
                    cbr.getExtendedData().put(REQUEST_FROM_SEQUENCE_MESSAGE_EDIT_POLICY, true);
                }
                ctc.compose(new CommandProxy(ise.getCommand(cbr)));
            }
        }
    }

    private ChangeBoundsRequest buildChangeBoundRequest(Point point, ISequenceEventEditPart ise, SingleEventEnd see, Dimension resizeDelta) {
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_RESIZE);
        cbr.setLocation(point);
        cbr.setEditParts(ise);
        cbr.setConstrainedResize(true);
        if (see.isStart()) {
            cbr.setResizeDirection(PositionConstants.NORTH);
            cbr.setMoveDelta(new Point(0, resizeDelta.height));
            cbr.setSizeDelta(resizeDelta.getNegated());
            cbr.setConstrainedMove(true);
        } else {
            cbr.setResizeDirection(PositionConstants.SOUTH);
            cbr.setSizeDelta(resizeDelta);
            cbr.setConstrainedResize(true);
        }
        return cbr;
    }

    private Dimension getResizeDelta(Point location, ISequenceEventEditPart ise, Range range, boolean fromTop) {
        GraphicalHelper.screen2logical(location, ise);
        int deltaY = location.y - (fromTop ? range.getLowerBound() : range.getUpperBound());
        deltaY = (int) (deltaY * GraphicalHelper.getZoom(ise));
        return new Dimension(0, deltaY);
    }

    /**
     * Overridden to resize the targeted RoteExecitionEditPart when moving the
     * selected destroy message.
     * 
     * @param baseCommand
     * @param smep
     * 
     * @param request
     *            the current request
     * @return a compound command if the destroy message is moved, the super
     *         command otherwise
     */
    private AbstractEMFOperation getMoveDestroyMessageCommand(BendpointRequest br, SequenceMessageEditPart smep, Command baseCommand) {
        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(smep.getEditingDomain(), "Move create message");
        if (smep.getSource() instanceof ISequenceEventEditPart) {
            ISequenceEventEditPart source = (ISequenceEventEditPart) smep.getSource();
            Range sourceRange = source.getISequenceEvent().getVerticalRange();
            Range lowerLimit = new Range(sourceRange.getLowerBound() - LayoutConstants.EXECUTION_CHILDREN_MARGIN, sourceRange.getLowerBound() + LayoutConstants.EXECUTION_CHILDREN_MARGIN);
            Range upperLimit = new Range(sourceRange.getUpperBound() - LayoutConstants.EXECUTION_CHILDREN_MARGIN, sourceRange.getUpperBound() + LayoutConstants.EXECUTION_CHILDREN_MARGIN);

            Point wantedLocation = br.getLocation().getCopy();
            GraphicalHelper.screen2logical(wantedLocation, smep);
            if (lowerLimit.includes(wantedLocation.y) || upperLimit.includes(wantedLocation.y)) {
                ctc.compose(org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE);
                return ctc;
            }
        }
        EndOfLifeEditPart endOfLifeEditPart = (EndOfLifeEditPart) smep.getTarget();
        final LifelineEditPart lifelineEditPart = (LifelineEditPart) endOfLifeEditPart.getParent();

        ChangeBoundsRequest cbr = getEndOfLifeMoveRequest(endOfLifeEditPart, br.getLocation().getCopy());
        ctc.compose(new CommandProxy(endOfLifeEditPart.getCommand(cbr)));

        if (ctc.canExecute()) {
            ctc.compose(new CommandProxy(baseCommand));
            updateMovingTargetReferencePoint(baseCommand, cbr);
            TransactionalEditingDomain editingDomain = smep.getEditingDomain();
            ctc.compose(CommandFactory.createICommand(editingDomain, new ShiftDescendantMessagesOperation(lifelineEditPart.getISequenceEvent(), cbr.getSizeDelta().height, true, false, false)));
        }
        return ctc;
    }

    private static ChangeBoundsRequest getEndOfLifeMoveRequest(EndOfLifeEditPart endOfLifeEditPart, Point location) {
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);
        cbr.setEditParts(endOfLifeEditPart);
        cbr.setLocation(location.getCopy());
        cbr.setConstrainedMove(true);

        double zoom = GraphicalHelper.getZoom(endOfLifeEditPart);
        GraphicalHelper.screen2logical(location, endOfLifeEditPart);
        Rectangle bounds = endOfLifeEditPart.getFigure().getBounds();
        int delta = location.y - (bounds.getCenter().y);
        cbr.setMoveDelta(new PrecisionPoint(0, delta * zoom));
        return cbr;
    }

    /**
     * Overridden to move the targeted InstanceRoleEditPart when moving the
     * selected create message.
     * 
     * @param request
     *            the current request
     * @param baseCommand
     * @return a compound command if the create message is moved, the super
     *         command otherwise
     */
    private AbstractEMFOperation getMoveCreateMessageCommand(BendpointRequest request, SequenceMessageEditPart smep, Command baseCommand) {
        Point normalizedLocation = request.getLocation().getCopy();
        GraphicalHelper.screen2logical(normalizedLocation, smep);

        InstanceRoleEditPart instanceRoleEditPart = (InstanceRoleEditPart) smep.getTarget();
        LifelineEditPart lifelineEditPart = Iterables.getOnlyElement(EditPartsHelper.getAllLifelines(instanceRoleEditPart));

        CompositeTransactionalCommand cc = new CompositeTransactionalCommand(smep.getEditingDomain(), "Move create message");

        // limite the move to the first sequence event of the target
        int firstMessageInTargetInstanceRole = lifelineEditPart.getISequenceEvent().getVerticalRange().getUpperBound() - LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Range occupiedRange = lifelineEditPart.getISequenceEvent().getOccupiedRange();
        if (!occupiedRange.isEmpty()) {
            // limite the move to the first sequence event of the target
            firstMessageInTargetInstanceRole = occupiedRange.getLowerBound() - LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        }

        int sourceRangeLimit = computeSourceRangeLimit(smep, normalizedLocation, firstMessageInTargetInstanceRole);
        if (normalizedLocation.y > sourceRangeLimit) {
            cc.compose(org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE);
            return cc;
        }

        final ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);
        cbr.getMoveDelta().y = sourceRangeLimit - (instanceRoleEditPart.getFigure().getBounds().y + instanceRoleEditPart.getFigure().getBounds().height / 2);
        cbr.setConstrainedMove(true);
        cbr.setEditParts(instanceRoleEditPart);
        cc.compose(new CommandProxy(instanceRoleEditPart.getCommand(cbr)));
        cc.compose(new CommandProxy(baseCommand));

        /*
         * These additional commands adjust the positions of the executions and
         * messages on the lifeline so that visually they do not move. They are
         * dual to the commands we add when moving a normal execution, as in
         * that case we want all the executions and messages it contains to move
         * along.
         */
        final LifelineEditPart lep = EditPartsHelper.getAllLifelines(instanceRoleEditPart).get(0);

        // Avoid EndOfLife Move
        TransactionalEditingDomain editingDomain = smep.getEditingDomain();
        cc.compose(CommandFactory.createICommand(editingDomain, new EndOfLifeMoveOperation((Lifeline) lep.getISequenceEvent(), -cbr.getMoveDelta().y)));
        cc.compose(CommandFactory.createICommand(editingDomain, new ShiftDirectSubExecutionsOperation(lep.getISequenceEvent(), -cbr.getMoveDelta().y)));
        cc.compose(CommandFactory.createICommand(editingDomain, new ShiftDescendantMessagesOperation(lep.getISequenceEvent(), cbr.getMoveDelta().y, true, false, true)));

        return cc;
    }

    private void updateMovingTargetReferencePoint(Command baseCommand, final ChangeBoundsRequest cbr) {
        if (baseCommand instanceof ICommandProxy && ((ICommandProxy) baseCommand).getICommand() instanceof SetConnectionBendpointsCommand) {
            /*
             * Update target reference point of the SetConnectionBendpoint base
             * command to take into account he move ot the instance role.
             */
            SetConnectionBendpointsCommand scbc = (SetConnectionBendpointsCommand) ((ICommandProxy) baseCommand).getICommand();
            scbc.setNewPointList(scbc.getNewPointList(), scbc.getSourceRefPoint(), scbc.getTargetRefPoint().getCopy().getTranslated(cbr.getMoveDelta()));
        }
    }

    private int computeSourceRangeLimit(SequenceMessageEditPart smep, Point normalizedLocation, int firstMessageInTargetInstanceRole) {
        int sourceRangeLimit = normalizedLocation.y;
        Range sourceRange = smep.getISequenceEvent().getVerticalRange();
        Range lowerLimit = new Range(sourceRange.getLowerBound(), sourceRange.getLowerBound() + LayoutConstants.EXECUTION_CHILDREN_MARGIN);
        Range upperLimit = new Range(sourceRange.getUpperBound() - LayoutConstants.EXECUTION_CHILDREN_MARGIN, sourceRange.getUpperBound());

        if (firstMessageInTargetInstanceRole < sourceRangeLimit + LayoutConstants.EXECUTION_CHILDREN_MARGIN) {
            sourceRangeLimit = firstMessageInTargetInstanceRole - LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        } else if (lowerLimit.includes(sourceRangeLimit)) {
            sourceRangeLimit = lowerLimit.getUpperBound();
        } else if (upperLimit.includes(sourceRangeLimit)) {
            sourceRangeLimit = upperLimit.getLowerBound();
        }
        return sourceRangeLimit;
    }

    private boolean validateMessageParentOperand(Option<Range> finalRange) {
        boolean valid = true;

        SequenceMessageEditPart thisEvent = (SequenceMessageEditPart) getHost();
        Message message = (Message) thisEvent.getISequenceEvent();
        Option<Lifeline> sourceLifeline = message.getSourceLifeline();
        Option<Lifeline> targetLifeline = message.getTargetLifeline();

        if (finalRange.some() && sourceLifeline.some() && targetLifeline.some()) {
            Option<Operand> sourceFinalOperand = Options.newNone();
            Option<Operand> targetFinalOperand = Options.newNone();

            if (sourceLifeline.get().equals(targetLifeline.get())) {
                int lBound = finalRange.get().getLowerBound();
                int uBound = finalRange.get().getUpperBound();

                sourceFinalOperand = sourceLifeline.get().getParentOperand(new Range(lBound, lBound));
                targetFinalOperand = targetLifeline.get().getParentOperand(new Range(uBound, uBound));
            } else {
                sourceFinalOperand = sourceLifeline.get().getParentOperand(finalRange.get());
                targetFinalOperand = targetLifeline.get().getParentOperand(finalRange.get());
            }

            valid = sourceFinalOperand.get() == targetFinalOperand.get();
        }

        return valid;

    }

    private static class MoveType {
        private boolean fromTop;

        private boolean needsCompoundMove;

        public MoveType(boolean needsCompoundMove, boolean fromTop) {
            this.fromTop = fromTop;
            this.needsCompoundMove = needsCompoundMove;
        }

        public boolean isFromTop() {
            return fromTop;
        }

        public boolean needsCompoundMove() {
            return needsCompoundMove;
        }

        @Override
        public String toString() {
            return "[fromTop:" + fromTop + ", compound:" + needsCompoundMove + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
    }
}
