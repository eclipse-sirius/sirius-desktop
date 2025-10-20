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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.ConnectionRouter;
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
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.OnConnectionLocator;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
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
import org.eclipse.sirius.diagram.sequence.ui.Messages;
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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceMessagesRouter;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusBendpointMoveHandle;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.draw2d.figure.HorizontalGuide;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swt.graphics.Color;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Specialized edit policy for sequence diagrams messages: tracks graphical reordering and invokes user-specified
 * reordering tool to reflect the changes in the semantic model.
 * <p>
 * This edit policy should only be installed on SequenceMessageEditParts.
 * 
 * @author pcdavid
 */
public class SequenceMessageEditPolicy extends ConnectionBendpointEditPolicy {

    /**
     * Key constant use for request from a BendpointRequest on Message.
     */
    public static final String REQUEST_FROM_SEQUENCE_MESSAGE_EDIT_POLICY = "org.eclipse.sirius.sequence.resize.execution.from.bendpoint.request"; //$NON-NLS-1$

    /**
     * Key constant use for request from a BendpointRequest on Message.
     */
    public static final String REQUEST_FROM_SEQUENCE_MESSAGE_EDIT_POLICY_OBLIQUE_MOVE_TYPE = "org.eclipse.sirius.sequence.resize.execution.from.bendpoint.request.oblique.move.type"; //$NON-NLS-1$

    /**
     * The value used to identify a move of the target of an oblique message.
     */
    public static final int OBLIQUE_MESSAGE_MOVE_TARGET = 1;

    /**
     * The value used to identify a move of the source of an oblique message.
     */
    public static final int OBLIQUE_MESSAGE_MOVE_SOURCE = -1;

    /**
     * The value used to identify the move of an oblique message.
     */
    public static final int OBLIQUE_MESSAGE_MOVE_MESSAGE = 0;

    /**
     * The color top use for the horizontal feedback rules shown when moving a message.
     */
    private static final Color MESSAGE_FEEDBACK_COLOR = SequenceInteractionFeedBackBuilder.ISE_FEEDBACK_COLOR;

    private final Collection<Figure> guides = new ArrayList<>();

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

    @Override
    protected List createManualHandles() {
        List list = super.createManualHandles();
        SequenceMessageEditPart message = getMessage();
        if (isObliqueMessage(message)) {
            list.add(new SiriusBendpointMoveHandle(message, 0, new OnConnectionLocator(getConnection(), 25)));
            list.add(new SiriusBendpointMoveHandle(message, 0, new OnConnectionLocator(getConnection(), 75)));
        }
        return list;
    }

    @Override
    protected void addInvisibleCreationHandle(List list, ConnectionEditPart connEP, int i) {
        /*
         * Do nothing: the handles created by default use a raw GEF drag tracker which we do not control, and which can
         * lead to disconnections of branches on reflective messages.
         */
    }

    /**
     * Update the location of the horizontal feedback line.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void showSourceFeedback(Request request) {
        removeFeedBackOnGuides();
        if (request instanceof BendpointRequest) {
            showSourceFeedback((BendpointRequest) request);
        }
    }

    @SuppressWarnings("restriction")
    private void showSourceFeedback(BendpointRequest br) {
        SequenceMessageEditPart thisEvent = (SequenceMessageEditPart) getHost();
        ISequenceEvent iSequenceEvent = thisEvent.getISequenceEvent();
        List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(iSequenceEvent);

        MoveType moveType = getMoveType(thisEvent, br, ends);
        boolean isOblique = isObliqueMessage(getMessage());
        if (isOblique) {
            ConnectionRouter connectionRouter = thisEvent.getPrimaryShape().getConnectionRouter();
            if (connectionRouter instanceof SequenceMessagesRouter smr) {
                connectionRouter.setConstraint(getConnection(), br);
            }
        }
        super.showSourceFeedback(br);

        if (moveType.needsCompoundMove()) {
            showCompoundEndFeedback(br, thisEvent, ends, moveType.isFromTop());
        } else {
            showMessageFeedback(br, thisEvent, iSequenceEvent, isOblique);
        }
    }

    private void showMessageFeedback(BendpointRequest br, SequenceMessageEditPart thisEvent, ISequenceEvent iSequenceEvent, boolean isOblique) {
        Point reqLoc = br.getLocation().getCopy();
        GraphicalHelper.screen2logical(reqLoc, thisEvent);
        Optional<Range> finalRange = computeFinalRange(br, thisEvent, reqLoc);
        Point location = new Point(1, thisEvent.getConnectionFigure().getPoints().getFirstPoint().y);
        location.performScale(GraphicalHelper.getZoom(thisEvent));

        if (isOblique && finalRange.isPresent()) {
            Point startLocation = new Point(1, finalRange.get().getLowerBound());
            startLocation.performScale(GraphicalHelper.getZoom(thisEvent));
            location.y = startLocation.y;
        }

        Figure guide = new HorizontalGuide(MESSAGE_FEEDBACK_COLOR, location.y);
        Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
        bounds.setHeight(1);
        bounds.setY(location.y);
        guide.setBounds(bounds);
        addFeedback(guide);
        guides.add(guide);

        if (isReflectiveMessage(getMessage())) {
            Point endLocation = new Point(1, thisEvent.getConnectionFigure().getPoints().getLastPoint().y);
            endLocation.performScale(GraphicalHelper.getZoom(thisEvent));
            Figure messageToSelfBottomGuide = new HorizontalGuide(MESSAGE_FEEDBACK_COLOR, endLocation.y);
            bounds = getFeedbackLayer().getBounds().getCopy();
            bounds.setHeight(1);
            bounds.setY(endLocation.y);
            messageToSelfBottomGuide.setBounds(bounds);
            addFeedback(messageToSelfBottomGuide);
            guides.add(messageToSelfBottomGuide);
        } else if (isOblique && finalRange.isPresent()) {
            int upperBound = finalRange.get().getUpperBound();
            Point endLocation = new Point(1, upperBound);
            endLocation.performScale(GraphicalHelper.getZoom(thisEvent));

            Figure obliqueMessageBottomGuide = new HorizontalGuide(MESSAGE_FEEDBACK_COLOR, endLocation.y);
            bounds = getFeedbackLayer().getBounds().getCopy();
            bounds.setHeight(1);
            bounds.setY(endLocation.y);
            obliqueMessageBottomGuide.setBounds(bounds);
            addFeedback(obliqueMessageBottomGuide);
            guides.add(obliqueMessageBottomGuide);
        }

        if (thisEvent.getTarget() instanceof InstanceRoleEditPart) {
            showInstanceRoleFeedback(br);
        } else if (thisEvent.getTarget() instanceof EndOfLifeEditPart) {
            showEndOfLifeFeedback(br);
        }

        if (finalRange.isPresent()) {
            Collection<Integer> invalidPositions = checkGlobalPositions(iSequenceEvent, finalRange);
            for (Integer conflict : invalidPositions) {
                bounds = getFeedbackLayer().getBounds().getCopy();

                Point conflictingPosition = new Point(0, conflict);
                conflictingPosition.performScale(GraphicalHelper.getZoom(thisEvent));

                HorizontalGuide conflictGuide = new HorizontalGuide(SequenceInteractionFeedBackBuilder.CONFLICT_FEEDBACK_COLOR, conflictingPosition.y);
                bounds.setY(conflictingPosition.y);
                bounds.setHeight(1);
                conflictGuide.setBounds(bounds);
                addFeedback(conflictGuide);
                guides.add(conflictGuide);
            }
        }
    }

    private static boolean isReflectiveMessage(SequenceMessageEditPart messageEP) {
        return new ISequenceEventQuery(messageEP.getISequenceEvent()).isReflectiveMessage();
    }

    private static boolean isObliqueMessage(SequenceMessageEditPart messageEP) {
        return new ISequenceEventQuery(messageEP.getISequenceEvent()).isObliqueMessage();
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
            @Override
            public boolean apply(SingleEventEnd input) {
                return !input.getSemanticEvent().equals(thisSemanticEvent);
            }
        };

        Dimension resizeDelta = getResizeDelta(request, location.getCopy(), thisEvent, thisRange, fromTop);
        int obliqueMoveType = resizeDelta.width;
        boolean obliqueMessage = isObliqueMessage(thisEvent);
        resizeDelta.width = 0;
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
                    if (obliqueMessage) {
                        cbr.getExtendedData().put(REQUEST_FROM_SEQUENCE_MESSAGE_EDIT_POLICY_OBLIQUE_MOVE_TYPE, obliqueMoveType);
                    }
                }
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
            @Override
            public boolean apply(SingleEventEnd input) {
                return !input.getSemanticEvent().equals(thisSemanticEvent);
            }
        };

        Dimension resizeDelta = getResizeDelta(request, location.getCopy(), thisEvent, thisRange, fromTop);
        for (CompoundEventEnd cee : Iterables.filter(ends, CompoundEventEnd.class)) {
            for (SingleEventEnd see : Iterables.filter(Lists.newArrayList(cee.getEventEnds()), toMove)) {
                ISequenceEventEditPart ise = EditPartsHelper.findISequenceEvent(see, sdep);
                ChangeBoundsRequest cbr = buildChangeBoundRequest(location.getCopy(), ise, see, resizeDelta);
                ise.eraseSourceFeedback(cbr);
            }
        }
    }

    /**
     * Moving a create message up and down will also show the feedback of the targeted instance role.
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

        cbr.getMoveDelta().setY(feedbackRangeLimit - (target.getFigure().getBounds().y + target.getFigure().getBounds().height / 2) + scrollSize.y); // br.getLocation().y
        target.showSourceFeedback(cbr);
    }

    /**
     * Moving a destroy message up and down will also show the feedback of the targeted end of life.
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
            if (thisEvent.getTarget() instanceof InstanceRoleEditPart target) {
                ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);
                cbr.getMoveDelta().setY(br.getLocation().y - (target.getFigure().getBounds().y + target.getFigure().getBounds().height / 2)); // br.getLocation().y
                target.eraseSourceFeedback(cbr);
            } else if (thisEvent.getTarget() instanceof EndOfLifeEditPart eolEditPart) {
                EndOfLifeOperations.eraseEndOfLifeFeedback(eolEditPart.getLifelineEditPart(), br);
            } else {
                MoveType moveType = getMoveType(thisEvent, br, ends);
                if (moveType.needsCompoundMove()) {
                    eraseCompoundEndFeedback(br, thisEvent, ends, moveType.isFromTop());
                }
            }
        }
    }

    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_SET_ALL_BENDPOINT.equals(request.getType())) {
            return getHost();
        } else {
            return super.getTargetEditPart(request);
        }
    }

    /**
     * Change the place of the message in the semantic model if the new graphical positions requires it.
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

        Optional<Range> finalRange = computeFinalRange(request, thisEvent, location);
        if (finalRange.isPresent()) {
            smrc = createReconnectionCommandOnBendpointMove(request, thisEvent, location, finalRange.get());
        }

        List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(thisEvent.getISequenceEvent());
        invalidCommand = invalidCommand || !baseCommand.canExecute() || !finalRange.isPresent();
        invalidCommand = invalidCommand || org.eclipse.gef.RequestConstants.REQ_MOVE_BENDPOINT.equals(request.getType());
        invalidCommand = invalidCommand || !validateMessageParentOperand(finalRange);
        invalidCommand = invalidCommand || !checkGlobalPositions(thisEvent.getISequenceEvent(), finalRange).isEmpty();

        if (invalidCommand) {
            thisEvent.setCursor(org.eclipse.draw2d.Cursors.NO);
            result = UnexecutableCommand.INSTANCE;
        } else {
            String label = baseCommand.getLabel();
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(thisEvent.getEditingDomain(),
                    MessageFormat.format(Messages.SequenceMessageEditPolicy_synchronizeOrderingCompositeCommand, label != null ? label : "<null>")); //$NON-NLS-1$
            SequenceEditPartsOperations.appendFullRefresh(thisEvent, ctc);

            MoveType move = getMoveType(thisEvent, request, ends);
            if (move.needsCompoundMove()) {
                addCompoundEventCommands(ctc, thisEvent, ends, request, move.isFromTop());
                thisEvent.setCursor(Cursors.SIZENS);
            } else {
                /*
                 * Overridden to handle lifeline move/resize when moving the selected create/destroy message.
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

    private Collection<Integer> checkGlobalPositions(final ISequenceEvent thisEvent, final Optional<Range> finalRange) {
        Function<ISequenceEvent, Range> futureRangeFunction = new Function<ISequenceEvent, Range>() {
            @Override
            public Range apply(ISequenceEvent from) {
                Range verticalRange = from.getVerticalRange();
                if (thisEvent.equals(from) && finalRange.isPresent()) {
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

        setOperations(true, message, finalRange, smrc);
        setOperations(false, message, finalRange, smrc);

        return CommandFactory.createICommand(thisEvent.getEditingDomain(), smrc);
    }

    private void setOperations(boolean source, Message message, Range finalRange, SetMessageRangeOperation smrc) {
        Range messageEndRange = source ? new Range(finalRange.getLowerBound(), finalRange.getLowerBound()) : new Range(finalRange.getUpperBound(), finalRange.getUpperBound());
        ISequenceNode currentEnd = source ? message.getSourceElement() : message.getTargetElement();
        boolean reflectiveMessage = message.isReflective();
        boolean obliqueMessage = message.isOblique();
        Option<Lifeline> endLifeline = currentEnd.getLifeline();
        if (endLifeline.some() && currentEnd instanceof ISequenceEvent && !(currentEnd instanceof Gate)) {
            ISequenceEvent finalEnd;
            Range finalEndRange;

            EventFinder endFinder = new EventFinder(endLifeline.get());
            endFinder.setReconnection(true);

            if (!(reflectiveMessage || obliqueMessage)) {
                finalEnd = endFinder.findMostSpecificEvent(finalRange);
            } else {
                finalEnd = (ISequenceEvent) currentEnd;

                ISequenceEvent potentialFinalEnd = endFinder.findMostSpecificEvent(messageEndRange);
                boolean compoundSrc = finalEnd instanceof Execution && message.equals(source ? ((Execution) finalEnd).getEndMessage().get() : ((Execution) finalEnd).getStartMessage().get());
                if (!compoundSrc && !finalEnd.equals(potentialFinalEnd)) {
                    // It is not allowed to reconnect a reflexive message by
                    // moving bendpoints
                    invalidCommand = reflectiveMessage;
                }

                if (obliqueMessage && !compoundSrc) {
                    finalEnd = potentialFinalEnd;

                }
                // look for event source
                endFinder.setReconnection(false);
                ISequenceEvent potentialSource = endFinder.findMostSpecificEvent(messageEndRange);
                if (potentialSource instanceof CombinedFragment) {
                    invalidCommand = true;
                }
            }

            // finalSrc can be null while moving a message under its lifeline.
            // finalTgt cannot be null : restrain the capability to move down out of
            // lifeline for destruction messages.
            if (source && finalEnd == null && message.getSourceElement().getProperLogicalBounds().y < finalRange.getLowerBound()) {
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

            boolean noValidation = reflectiveMessage || obliqueMessage || realEnd instanceof Lifeline;
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
        } else if (currentEnd instanceof LostMessageEnd || currentEnd instanceof EndOfLife || currentEnd instanceof InstanceRole || currentEnd instanceof Gate) {
            Rectangle finalEndBounds = currentEnd.getProperLogicalBounds().getCopy();
            if (source) {
                smrc.setSource(currentEnd.getNotationView(), finalEndBounds);
            } else {
                smrc.setTarget(currentEnd.getNotationView(), finalEndBounds);
            }
        }

    }

    private Optional<Range> computeFinalRange(BendpointRequest request, SequenceMessageEditPart smep, Point location) {
        Range finalRange = null;
        if (isReflectiveMessage(smep)) {
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
        } else if (isObliqueMessage(smep)) {
            int deltaY = 0;
            int moveType = 0;
            Object initialClick = request.getExtendedData().get(SequenceMessageEditPart.MSG_OBLIQUE_CBR_INITAL_CLICK);
            if (initialClick instanceof Point obliqueMsgInitialClick) {
                deltaY = location.y - obliqueMsgInitialClick.y;
                moveType = obliqueMsgInitialClick.x;
            }
            Range verticalRange = smep.getISequenceEvent().getVerticalRange();
            if (moveType == SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_SOURCE) {
                finalRange = new Range(Math.min(verticalRange.getUpperBound() - LayoutConstants.DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT, verticalRange.getLowerBound() + deltaY),
                        verticalRange.getUpperBound());
            } else if (moveType == SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_MESSAGE) {
                finalRange = verticalRange.shifted(deltaY);
            } else if (moveType == SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_TARGET) {
                finalRange = new Range(verticalRange.getLowerBound(),
                        Math.max(verticalRange.getLowerBound() + LayoutConstants.DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT, verticalRange.getUpperBound() + deltaY));
            }
        } else {
            finalRange = new Range(location.y, location.y);
        }
        return Optional.ofNullable(finalRange);
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
        boolean msgToSelfMove = isReflectiveMessage(event);
        boolean obliqueMove = isObliqueMessage(event);
        boolean needsCompoundEventCommands = compoundMove && !msgToSelfMove || compoundMove && !obliqueMove;
        boolean fromTop = true;
        if (compoundMove && msgToSelfMove && ends.size() == 2) {
            if (request.getExtendedData().containsKey(SequenceMessageEditPart.MSG_TO_SELF_TOP_MOVE)) {
                fromTop = (Boolean) request.getExtendedData().get(SequenceMessageEditPart.MSG_TO_SELF_TOP_MOVE);
                needsCompoundEventCommands = fromTop ? ends.get(0) instanceof CompoundEventEnd : ends.get(1) instanceof CompoundEventEnd;
            } else {
                needsCompoundEventCommands = false;
            }
        }

        if (compoundMove && obliqueMove && ends.size() == 2) {
            Object initialClick = request.getExtendedData().get(SequenceMessageEditPart.MSG_OBLIQUE_CBR_INITAL_CLICK);
            if (initialClick instanceof Point obliqueMsgInitialClick) {
                // Handle only ObliqueAsyncCall case with move target or move edge
                needsCompoundEventCommands = obliqueMsgInitialClick.x == OBLIQUE_MESSAGE_MOVE_TARGET || obliqueMsgInitialClick.x == OBLIQUE_MESSAGE_MOVE_MESSAGE;
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
            @Override
            public boolean apply(SingleEventEnd input) {
                return !input.getSemanticEvent().equals(thisSemanticEvent);
            }
        };

        Dimension resizeDelta = getResizeDelta(request, location.getCopy(), thisEvent, thisRange, fromTop);
        int obliqueMoveType = resizeDelta.width;
        boolean obliqueMessage = isObliqueMessage(thisEvent);
        resizeDelta.width = 0;
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
                    if (obliqueMessage) {
                        cbr.getExtendedData().put(REQUEST_FROM_SEQUENCE_MESSAGE_EDIT_POLICY_OBLIQUE_MOVE_TYPE, obliqueMoveType);
                    }
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

    private Dimension getResizeDelta(BendpointRequest request, Point location, ISequenceEventEditPart ise, Range range, boolean fromTop) {
        GraphicalHelper.screen2logical(location, ise);
        int deltaY;
        int moveType = 0;
        Object initialClick = request.getExtendedData().get(SequenceMessageEditPart.MSG_OBLIQUE_CBR_INITAL_CLICK);
        if (initialClick instanceof Point obliqueMsgInitialClick) {
            deltaY = location.y - obliqueMsgInitialClick.y;
            moveType = obliqueMsgInitialClick.x;
            if (range.getUpperBound() + deltaY < range.getLowerBound() + LayoutConstants.DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT && moveType == OBLIQUE_MESSAGE_MOVE_TARGET) {
                deltaY = -range.width() + LayoutConstants.DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT;
            }
        } else {
            deltaY = location.y - (fromTop ? range.getLowerBound() : range.getUpperBound());
        }
        deltaY = (int) (deltaY * GraphicalHelper.getZoom(ise));
        return new Dimension(moveType, deltaY);
    }

    /**
     * Overridden to resize the targeted RoteExecitionEditPart when moving the selected destroy message.
     * 
     * @param baseCommand
     * @param smep
     * 
     * @param request
     *            the current request
     * @return a compound command if the destroy message is moved, the super command otherwise
     */
    private AbstractEMFOperation getMoveDestroyMessageCommand(BendpointRequest br, SequenceMessageEditPart smep, Command baseCommand) {
        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(smep.getEditingDomain(), Messages.SequenceMessageEditPolicy_moveCreateMessageCommand);
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
     * Overridden to move the targeted InstanceRoleEditPart when moving the selected create message.
     * 
     * @param request
     *            the current request
     * @param baseCommand
     * @return a compound command if the create message is moved, the super command otherwise
     */
    private AbstractEMFOperation getMoveCreateMessageCommand(BendpointRequest request, SequenceMessageEditPart smep, Command baseCommand) {
        Point normalizedLocation = request.getLocation().getCopy();
        GraphicalHelper.screen2logical(normalizedLocation, smep);

        InstanceRoleEditPart instanceRoleEditPart = (InstanceRoleEditPart) smep.getTarget();
        LifelineEditPart lifelineEditPart = Iterables.getOnlyElement(EditPartsHelper.getAllLifelines(instanceRoleEditPart));

        CompositeTransactionalCommand cc = new CompositeTransactionalCommand(smep.getEditingDomain(), Messages.SequenceMessageEditPolicy_moveCreateMessageCommand);

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
        cbr.getMoveDelta().setY(sourceRangeLimit - (instanceRoleEditPart.getFigure().getBounds().y + instanceRoleEditPart.getFigure().getBounds().height / 2));
        cbr.setConstrainedMove(true);
        cbr.setEditParts(instanceRoleEditPart);
        cc.compose(new CommandProxy(instanceRoleEditPart.getCommand(cbr)));
        cc.compose(new CommandProxy(baseCommand));

        /*
         * These additional commands adjust the positions of the executions and messages on the lifeline so that
         * visually they do not move. They are dual to the commands we add when moving a normal execution, as in that
         * case we want all the executions and messages it contains to move along.
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
             * Update target reference point of the SetConnectionBendpoint base command to take into account he move ot
             * the instance role.
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

    private boolean validateMessageParentOperand(Optional<Range> finalRange) {
        boolean valid = true;

        SequenceMessageEditPart thisEvent = (SequenceMessageEditPart) getHost();
        Message message = (Message) thisEvent.getISequenceEvent();
        Option<Lifeline> sourceLifeline = message.getSourceLifeline();
        Option<Lifeline> targetLifeline = message.getTargetLifeline();

        if (finalRange.isPresent()) {
            if (sourceLifeline.some() && targetLifeline.some()) {
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
            } else {
                if (message.getSourceElement() instanceof Gate g) {
                    ISequenceElement hierarchicalParent = g.getHierarchicalParent();
                    if (hierarchicalParent instanceof ISequenceEvent ise) {
                        valid = valid && ise.getVerticalRange().includes(finalRange.get().getLowerBound());
                    }
                } 
                
                if (message.getTargetElement() instanceof Gate g) {
                    ISequenceElement hierarchicalParent = g.getHierarchicalParent();
                    if (hierarchicalParent instanceof ISequenceEvent ise) {
                        valid = valid && ise.getVerticalRange().includes(finalRange.get().getUpperBound());
                    }
                }
            }
        }

        return valid;

    }

    private static class MoveType {
        private boolean fromTop;

        private boolean needsCompoundMove;

        MoveType(boolean needsCompoundMove, boolean fromTop) {
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
            return "[fromTop:" + fromTop + ", compound:" + needsCompoundMove + "]"; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        }
    }
}
