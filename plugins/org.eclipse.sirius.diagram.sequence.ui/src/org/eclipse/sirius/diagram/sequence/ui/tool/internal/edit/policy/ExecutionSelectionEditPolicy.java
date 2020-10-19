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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.IdentityCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SetMessageRangeOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SetVerticalRangeOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.ShiftDirectSubExecutionsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.VerticalSpaceExpansionOrReduction;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ShiftDescendantMessagesOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.AbstractNodeEventResizeSelectionValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.ISEComplexMoveValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.RangeGuide;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SpecificBorderItemSelectionEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;
import org.eclipse.sirius.ext.draw2d.figure.HorizontalGuide;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swt.graphics.Color;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Specialization of the default policy for executions, in order to validate and execute the specific resize and move
 * behaviors needed for sequence diagrams.
 * 
 * @author pcdavid
 */
public class ExecutionSelectionEditPolicy extends SpecificBorderItemSelectionEditPolicy {

    /**
     * The color to use for the horizontal feedback rules shown when moving/resizing an execution.
     */
    protected static final Color EXECUTION_FEEDBACK_COLOR = ColorConstants.lightGray;

    /**
     * Additional figures for feedback.
     */
    protected Collection<Figure> guides = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        cancelHorizontalDelta(request);
        ICommand solution = IdentityCommand.INSTANCE;
        ExecutionEditPart hostPart = (ExecutionEditPart) getHost();

        RequestQuery requestQuery = new RequestQuery(request);
        if (hostPart.getSelected() == EditPart.SELECTED_PRIMARY && requestQuery.isMove()) {
            ISEComplexMoveValidator validator = ISEComplexMoveValidator.getOrCreateValidator(request, requestQuery, hostPart.getISequenceEvent());
            if (validator != null && validator.isValid()) {
                solution = buildMoveCommand(hostPart, request, validator);
            } else {
                solution = org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
            }
        }
        return new ICommandProxy(solution);
    }

    private ICommand buildMoveCommand(ExecutionEditPart hostPart, ChangeBoundsRequest request, ISEComplexMoveValidator validator) {
        TransactionalEditingDomain editingDomain = hostPart.getEditingDomain();
        RequestQuery requestQuery = new RequestQuery(request);
        ISEComplexMoveCommandBuilder builder = new ISEComplexMoveCommandBuilder(editingDomain, Messages.ExecutionSelectionEditPolicy_moveCompositeCommand, requestQuery, validator);
        return postProcessCommand(builder.buildCommand(), hostPart, requestQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        Command result = UnexecutableCommand.INSTANCE;
        ExecutionEditPart hostPart = (ExecutionEditPart) getHost();
        AbstractNodeEvent host = (AbstractNodeEvent) hostPart.getISequenceEvent();

        // The resize in top direction is not handled as the resize in bottom direction. A vertical space expansion can
        // be needed for resize to bottom, and there is always free space in bottom. All this aspect is handled by the
        // AbstractNodeEventResizeSelectionValidator. For resize to top, we only need to resize recursively the parent
        // as long as it is possible.
        Command resizeParentToTopCmd = null;
        RequestQuery requestQuery = new RequestQuery(request);
        if (requestQuery.isResizeFromTop() && request.getSizeDelta().height() > 0) {
            ISequenceEvent parentEvent = host.getParentEvent();
            EditPart parentEventEditPart = (EditPart) hostPart.getViewer().getEditPartRegistry().get(parentEvent.getNotationView());
            if (hostPart != null && parentEventEditPart != null) {
                // Get resize command in top direction from for its container
                Rectangle executionFinalBounds = requestQuery.getFinalBounds(hostPart);
                Range executionFinalRange = RangeHelper.verticalRange(executionFinalBounds);
                if (parentEvent.getValidSubEventsRange().getLowerBound() >= executionFinalRange.getLowerBound()) {
                    ChangeBoundsRequest parentRequest = new ChangeBoundsRequest(request.getType());
                    int yDelta = parentEvent.getValidSubEventsRange().getLowerBound() - executionFinalRange.getLowerBound();
                    parentRequest.setMoveDelta(new PrecisionPoint(request.getMoveDelta().x(), -yDelta));
                    parentRequest.setSizeDelta(new Dimension(request.getSizeDelta().width(), yDelta));
                    parentRequest.setEditParts(parentEventEditPart);
                    parentRequest.setResizeDirection(request.getResizeDirection());
                    parentRequest.setLocation(request.getLocation());

                    resizeParentToTopCmd = parentEventEditPart.getCommand(parentRequest);
                }
            }
        }

        AbstractNodeEventResizeSelectionValidator validator = AbstractNodeEventResizeSelectionValidator.getOrCreateValidator(request, host);

        if (validator.isValid()) {
            ICommand solution = buildResizeCommand(request, hostPart, validator);
            if (solution == null) {
                solution = IdentityCommand.INSTANCE;
            }
            if (resizeParentToTopCmd == null) {
                result = new ICommandProxy(solution);
            } else if (resizeParentToTopCmd.canExecute()) {
                CompoundCommand cc = new CompoundCommand(solution.getLabel());
                cc.add(resizeParentToTopCmd);
                cc.add(new ICommandProxy(solution));
                result = cc;
            }
        }
        return result;
    }

    private ICommand buildResizeCommand(ChangeBoundsRequest request, ExecutionEditPart hostPart, AbstractNodeEventResizeSelectionValidator validator) {
        TransactionalEditingDomain editingDomain = hostPart.getEditingDomain();
        ICommand solution = null;

        AbstractNodeEvent self = (AbstractNodeEvent) hostPart.getISequenceEvent();
        RequestQuery requestQuery = new RequestQuery(request);
        if (requestQuery.isMultiSelectionOperation()) {
            validator.setExpansionZone(null);
        }

        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, Messages.ExecutionSelectionEditPolicy_resizeCompositeCommand);
        if (needVerticalSpaceExpansion(validator, request)) {
            SequenceDiagramEditPart diagram = EditPartsHelper.getSequenceDiagramPart(hostPart);
            Collection<ISequenceEvent> eventToIgnore = Collections.singletonList((ISequenceEvent) self);
            ctc.compose(CommandFactory.createICommand(editingDomain, new VerticalSpaceExpansionOrReduction(diagram.getSequenceDiagram(), validator.getExpansionZone(), 0, eventToIgnore)));
        }
        if (validator.getFinalHierarchicalParent().equals(self.getHierarchicalParentEvent())) {
            Command cmd = DiagramBorderNodeEditPartOperation.getResizeBorderItemCommand((ExecutionEditPart) getHost(), request, false);
            ctc.add(new CommandProxy(cmd));
            ctc.setLabel(cmd.getLabel());

            if (self instanceof Execution) {
                addChildrenAdjustmentCommands((Execution) self, ctc, request, validator);
            }
            solution = postProcessCommand(ctc, hostPart, requestQuery);
        }
        return solution;
    }

    /**
     * Add refresh ordering commands and reorder commands.
     * 
     * @param ctc
     *            the current composite command to complete.
     * @param selfEditPart
     *            the current edit part
     * @param requestQuery
     *            query on the request
     * @return the completed command
     */
    protected ICommand postProcessCommand(CompositeTransactionalCommand ctc, ExecutionEditPart selfEditPart, RequestQuery requestQuery) {
        AbstractNodeEvent self = (AbstractNodeEvent) selfEditPart.getISequenceEvent();
        SequenceEditPartsOperations.addRefreshGraphicalOrderingCommand(ctc, selfEditPart);
        SequenceEditPartsOperations.addRefreshSemanticOrderingCommand(ctc, selfEditPart);

        List<Message> linkedMessages = self.getLinkedMessages();
        if (!linkedMessages.isEmpty() && !linkedMessages.get(0).isLogicallyInstantaneous()) {
            SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(ctc, linkedMessages.get(0));
        }

        if (requestQuery.isMultiSelectionOperation()) {
            SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(ctc, self, requestQuery.getISequenceEvents());
        } else {
            SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(ctc, self);
        }
        if (linkedMessages.size() >= 2 && !linkedMessages.get(1).isLogicallyInstantaneous()) {
            SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(ctc, linkedMessages.get(1));
        }
        return ctc;
    }

    private boolean needVerticalSpaceExpansion(AbstractNodeEventResizeSelectionValidator validator, ChangeBoundsRequest request) {
        return validator.getExpansionZone() != null && !new RequestQuery(request).isExecutionMovedIndirectly();
    }

    private Collection<ISequenceEvent> getSequenceEventsUpperToInsertionTime(SequenceDiagram sequenceDiagram, int lowerBound) {
        Collection<ISequenceEvent> result = new ArrayList<ISequenceEvent>();
        Set<AbstractNodeEvent> allDelimitedSequenceEvents = sequenceDiagram.getAllAbstractNodeEvents();
        for (ISequenceEvent sequenceEvent : allDelimitedSequenceEvents) {
            if (sequenceEvent.getVerticalRange().getLowerBound() > lowerBound) {
                result.add(sequenceEvent);
            }
        }
        return result;
    }

    /**
     * Compute the current vertical move.
     * 
     * @param cbr
     *            the current request.
     * @return the vertical move.
     */
    protected Integer getVMove(ChangeBoundsRequest cbr) {
        Rectangle logicalDelta = new RequestQuery(cbr).getLogicalDelta();
        return logicalDelta.y;
    }

    private void addChildrenAdjustmentCommands(Execution exec, CompositeTransactionalCommand cc, final ChangeBoundsRequest cbr, AbstractNodeEventResizeSelectionValidator validator) {
        RequestQuery rq = new RequestQuery(cbr);
        Rectangle logicalDelta = rq.getLogicalDelta();
        int moveDelta = logicalDelta.y;
        int sizeDelta = logicalDelta.height;
        if (rq.isResizeFromTop()) {
            cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectSubExecutionsOperation(exec, sizeDelta)));
            cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDescendantMessagesOperation(exec, moveDelta, true, false, true)));

            addCompoundEventsMoveCommands(exec, cc, true, moveDelta, cbr, validator);
            addCompoundEventsMoveCommands(exec, cc, false, 0, cbr, validator);
        } else if (rq.isResizeFromBottom()) {
            cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDescendantMessagesOperation(exec, sizeDelta, true, false, false)));

            addCompoundEventsMoveCommands(exec, cc, true, 0, cbr, validator);
            addCompoundEventsMoveCommands(exec, cc, false, sizeDelta, cbr, validator);
        }
    }

    private void addCompoundEventsMoveCommands(Execution self, CompositeTransactionalCommand cc, final boolean top, int height, ChangeBoundsRequest request,
            AbstractNodeEventResizeSelectionValidator validator) {
        List<EventEnd> findEnds = EventEndHelper.findEndsFromSemanticOrdering(self);
        RequestQuery rq = new RequestQuery(request);

        final Range oldRange = self.getVerticalRange();
        final int movedBound = top ? oldRange.getLowerBound() : oldRange.getUpperBound();
        final EObject sem = self.getSemanticTargetElement().get();
        final Predicate<SingleEventEnd> toMove = new Predicate<SingleEventEnd>() {
            @Override
            public boolean apply(SingleEventEnd input) {
                return !input.getSemanticEvent().equals(sem);
            }
        };
        final Predicate<EventEnd> moved = new Predicate<EventEnd>() {
            @Override
            public boolean apply(EventEnd input) {
                return EventEndHelper.getSingleEventEnd(input, sem).isStart() == top;
            }
        };

        SequenceDiagram sequenceDiagram = self.getDiagram();
        for (CompoundEventEnd cee : Iterables.filter(Iterables.filter(findEnds, moved), CompoundEventEnd.class)) {
            for (SingleEventEnd see : Iterables.filter(Lists.newArrayList(cee.getEventEnds()), toMove)) {
                final ISequenceEvent ise = EventEndHelper.findISequenceEvent(see, sequenceDiagram);
                if (ise == null) {
                    continue;
                }

                final Range seeRange = ise.getVerticalRange();
                int lDelta = 0;
                int uDelta = 0;
                if (seeRange.getLowerBound() == movedBound && doNotMoveSourceOfReturnMessageOfReflexiveSyncCall(self, ise, rq)) {
                    lDelta = height;
                    if (new ISequenceEventQuery(ise).isReflectiveMessage() && getSelection(ise) == EditPart.SELECTED_NONE) {
                        // A reflexive message does not have the same lower and
                        // upper bound but both bound have to be moved like
                        // "normal" messages
                        uDelta = height;
                    }
                }
                if (seeRange.getUpperBound() == movedBound && doNotMoveTargetOfStartMessageOfReflexiveSyncCall(self, ise, rq)) {
                    uDelta = height;
                    if (new ISequenceEventQuery(ise).isReflectiveMessage() && getSelection(ise) == EditPart.SELECTED_NONE) {
                        // A reflexive message does not have the same lower and
                        // upper bound but both bound have to be moved like
                        // "normal" messages
                        lDelta = height;
                    }
                }

                if ((seeRange.getLowerBound() + lDelta) <= (seeRange.getUpperBound() + uDelta)) {
                    final Range newRange = new Range(seeRange.getLowerBound() + lDelta, seeRange.getUpperBound() + uDelta);
                    if (ise instanceof Message && !hasBothEndMoving((Message) ise)) {
                        Message msg = (Message) ise;
                        addMessageReconnectionCommand(self, cc, msg, newRange, request, validator, sequenceDiagram);
                    } else {
                        cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new SetVerticalRangeOperation(ise, newRange)));
                    }
                } else {
                    cc.compose(org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE);
                }
            }
        }
    }

    private int getSelection(ISequenceElement ise) {
        Map<?, ?> editPartRegistry = getHost().getViewer().getEditPartRegistry();
        Object object = editPartRegistry.get(ise.getNotationView());
        if (object instanceof EditPart) {
            return ((EditPart) object).getSelected();
        }
        return Integer.MIN_VALUE;
    }

    private void addMessageReconnectionCommand(Execution self, CompositeTransactionalCommand cc, Message message, Range newRange, ChangeBoundsRequest request,
            AbstractNodeEventResizeSelectionValidator validator, SequenceDiagram sequenceDiagram) {

        Set<Execution> executionsInMove = new RequestQuery(request).getExecutions();
        boolean invalidCommand = false;

        Predicate<EventEnd> filterCompoundEventEnd = new Predicate<EventEnd>() {
            @Override
            public boolean apply(EventEnd input) {
                return input instanceof CompoundEventEnd;
            }
        };

        SetMessageRangeOperation smrc = new SetMessageRangeOperation((Edge) message.getNotationView(), newRange);

        Lifeline selfLifeline = self.getLifeline().get();
        Rectangle logicalDelta = new RequestQuery(request).getLogicalDelta();
        Rectangle bounds = self.getProperLogicalBounds().getCopy();
        bounds.translate(logicalDelta.getLocation());
        bounds.resize(logicalDelta.getSize());
        Range thisFinalRange = RangeHelper.verticalRange(bounds);

        List<ISequenceEvent> toIgnore = new ArrayList<>();
        boolean isReplyMessage = message.getKind() == Message.Kind.REPLY;
        boolean isReflective = message.isReflective();
        ISequenceNode sourceElement = message.getSourceElement();
        ISequenceNode targetElement = message.getTargetElement();
        if (!isReplyMessage && isReflective && Iterables.any(EventEndHelper.findEndsFromSemanticOrdering(message), filterCompoundEventEnd) && targetElement == self) {
            // Avoid target of the return message of a reflexive sync call to
            // reconnect on its execution
            toIgnore.add(self);
        }
        // if a verticalSpaceExpansion will occurs, ignore ISequenceEvent under
        // the insertionPoint
        if (needVerticalSpaceExpansion(validator, request)) {
            Collection<ISequenceEvent> sequenceEventsUpperToInsertionTime = getSequenceEventsUpperToInsertionTime(sequenceDiagram, validator.getExpansionZone().getLowerBound());
            sequenceEventsUpperToInsertionTime.removeAll(executionsInMove);
            toIgnore.addAll(sequenceEventsUpperToInsertionTime);
        }

        Option<Lifeline> srcLifeline = message.getSourceLifeline();
        if (srcLifeline.some()) {
            EventFinder srcFinder = new EventFinder(srcLifeline.get());
            srcFinder.setReconnection(true);
            srcFinder.setEventsToIgnore(Predicates.in(toIgnore));
            srcFinder.setExpansionZone(validator.getExpansionZone());
            ISequenceEvent finalSrc = (srcLifeline.get() == selfLifeline && sourceElement == self) ? self : srcFinder.findMostSpecificEvent(newRange);
            Range finalSrcRange = (srcLifeline.get() == selfLifeline && sourceElement == self) ? thisFinalRange : finalSrc.getVerticalRange();
            smrc.setSource(finalSrc.getNotationView(), new Rectangle(0, finalSrcRange.getLowerBound(), 0, finalSrcRange.width()));
        } else {
            Range finalSrcRange = RangeHelper.verticalRange(sourceElement.getProperLogicalBounds());
            smrc.setSource(sourceElement.getNotationView(), new Rectangle(0, finalSrcRange.getLowerBound(), 0, finalSrcRange.width()));
        }

        toIgnore.clear();
        if (isReplyMessage && isReflective && Iterables.any(EventEndHelper.findEndsFromSemanticOrdering(message), filterCompoundEventEnd) && sourceElement == self) {
            // Avoid target of the return message of a reflexive sync call to
            // reconnect on its execution
            toIgnore.add(self);
        }
        // if a verticalSpaceExpansion will occurs, ignore ISequenceEvent under
        // the insertionPoint
        if (needVerticalSpaceExpansion(validator, request)) {
            Collection<ISequenceEvent> sequenceEventsUpperToInsertionTime = getSequenceEventsUpperToInsertionTime(sequenceDiagram, validator.getExpansionZone().getLowerBound());
            sequenceEventsUpperToInsertionTime.removeAll(executionsInMove);
            toIgnore.addAll(sequenceEventsUpperToInsertionTime);
        }

        Option<Lifeline> tgtLifeline = message.getTargetLifeline();
        if (tgtLifeline.some()) {
            EventFinder tgtFinder = new EventFinder(tgtLifeline.get());
            tgtFinder.setReconnection(true);
            tgtFinder.setEventsToIgnore(Predicates.in(toIgnore));
            tgtFinder.setExpansionZone(validator.getExpansionZone());
            ISequenceEvent finalTgt = (tgtLifeline.get() == selfLifeline && targetElement == self) ? self : tgtFinder.findMostSpecificEvent(newRange);
            if (finalTgt == null) {
                invalidCommand = true;
            } else {
                Range finalTgtRange = (tgtLifeline.get() == selfLifeline && targetElement == self) ? thisFinalRange : finalTgt.getVerticalRange();
                smrc.setTarget(finalTgt.getNotationView(), new Rectangle(0, finalTgtRange.getLowerBound(), 0, finalTgtRange.width()));
            }
        } else {
            Range finalTgtRange = RangeHelper.verticalRange(targetElement.getProperLogicalBounds());
            smrc.setTarget(targetElement.getNotationView(), new Rectangle(0, finalTgtRange.getLowerBound(), 0, finalTgtRange.width()));
        }

        if (invalidCommand) {
            cc.compose(org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE);
        } else {
            cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), smrc));
        }

    }

    private boolean hasBothEndMoving(Message smep) {
        Set<AbstractNodeEvent> movingExecutionEditPart = getMovingExecutions();
        return movingExecutionEditPart.contains(smep.getSourceElement()) && movingExecutionEditPart.contains(smep.getTargetElement());
    }

    private Set<AbstractNodeEvent> getMovingExecutions() {
        EditPartViewer viewer = getHost().getViewer();
        Set<AbstractNodeEvent> movingExecutions = new HashSet<>();
        for (ExecutionEditPart eep : Iterables.filter(viewer.getSelectedEditParts(), ExecutionEditPart.class)) {
            AbstractNodeEvent exec = (AbstractNodeEvent) eep.getISequenceEvent();
            movingExecutions.add(exec);
            if (exec instanceof Execution) {
                movingExecutions.addAll(((Execution) exec).findLinkedExecutions(true));
            }
        }

        ArrayList<Execution> subExecutions = new ArrayList<>();
        for (Execution eep : Iterables.filter(movingExecutions, Execution.class)) {
            subExecutions.addAll(new ISequenceEventQuery(eep).getAllExecutions());
        }
        movingExecutions.addAll(subExecutions);
        return movingExecutions;
    }

    /**
     * Avoid moving the source of the return message of a reflexive synchronous message when resizing a parent
     * execution.
     * 
     * @param ise
     *            the sequence event to validate if it is a reflexive message we do not want to move
     * @return the validation result of the message move.
     */
    private boolean doNotMoveSourceOfReturnMessageOfReflexiveSyncCall(Execution self, ISequenceEvent ise, RequestQuery rq) {
        return !(isMovedReflexiveMessage(ise, rq) && self.equals(((Message) ise).getSourceElement()) && getSelection(((Message) ise).getSourceElement()) == EditPart.SELECTED_NONE
                && getSelection(ise) == EditPart.SELECTED_NONE);
    }

    /**
     * Avoid moving the target of the invocation message of a reflexive synchronous message when resizing a parent
     * execution.
     * 
     * @param ise
     *            the sequence event to validate if it is a reflexive message we do not want to move
     * @return the validation result of the message move.
     */
    private boolean doNotMoveTargetOfStartMessageOfReflexiveSyncCall(Execution self, ISequenceEvent ise, RequestQuery rq) {
        return !(isMovedReflexiveMessage(ise, rq) && self.equals(((Message) ise).getTargetElement()) && getSelection(((Message) ise).getTargetElement()) == EditPart.SELECTED_NONE
                && getSelection(ise) == EditPart.SELECTED_NONE);
    }

    private boolean isMovedReflexiveMessage(ISequenceEvent ise, RequestQuery rq) {
        return rq.isResize() && new ISequenceEventQuery(ise).isReflectiveMessage();
    }

    /*
     * Feedback
     */
    /**
     * Show/update the horizontal feedback lines aligned on the top and bottom of the execution.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        eraseChangeBoundsFeedback(request);
        super.showChangeBoundsFeedback(request);

        ExecutionEditPart hostPart = (ExecutionEditPart) getHost();
        AbstractNodeEvent host = (AbstractNodeEvent) hostPart.getISequenceEvent();
        RequestQuery requestQuery = new RequestQuery(request);

        if (hostPart.getSelected() == EditPart.SELECTED_PRIMARY && requestQuery.isMove()) {
            ISEComplexMoveValidator validator = ISEComplexMoveValidator.getOrCreateValidator(request, requestQuery, host);
            if (validator != null) {
                SequenceInteractionFeedBackBuilder feedBackBuilder = new SequenceInteractionFeedBackBuilder(validator, getFeedbackLayer(), hostPart);
                for (Figure fig : feedBackBuilder.buildFeedBack()) {
                    addFeedback(fig);
                    guides.add(fig);
                }
            }
        } else if (requestQuery.isResize()) {
            AbstractNodeEventResizeSelectionValidator validator = AbstractNodeEventResizeSelectionValidator.getOrCreateValidator(request, host);
            validator.validate();
            showResizeFeedBack(request);
            feedBack(validator);
        }
    }

    /**
     * Show feedback for computed conflicts during validation.
     * 
     * @param validator
     *            the current resize validator.
     */
    protected void feedBack(AbstractNodeEventResizeSelectionValidator validator) {
        IFigure feedbackLayer = getFeedbackLayer();
        for (Integer conflict : validator.getInvalidPositions()) {
            Point conflictingPosition = new Point(0, conflict);
            conflictingPosition.performScale(GraphicalHelper.getZoom(getHost()));

            Rectangle bounds = feedbackLayer.getBounds().getCopy();
            bounds.y = conflictingPosition.y;
            bounds.height = 1;

            HorizontalGuide conflictGuide = new HorizontalGuide(ColorConstants.red, conflictingPosition.y);
            conflictGuide.setBounds(bounds);
            addFeedback(conflictGuide);
            guides.add(conflictGuide);
        }

        Range expansionZone = validator.getExpansionZone();
        if (expansionZone != null && !expansionZone.isEmpty() && expansionZone.width() != 0) {
            Rectangle screenRange = new Rectangle(0, expansionZone.getLowerBound(), 0, expansionZone.width());
            screenRange.performScale(GraphicalHelper.getZoom(getHost()));
            Range expand = RangeHelper.verticalRange(screenRange);

            Rectangle bounds = feedbackLayer.getBounds().getCopy();
            bounds.height = expand.width();
            bounds.y = expand.getLowerBound();

            RangeGuide expansion = new RangeGuide(validator.isValid() ? ColorConstants.blue : ColorConstants.red, expand, true);
            expansion.setBounds(bounds);
            addFeedback(expansion);
            guides.add(expansion);
        }
    }

    /**
     * Show feedback for resize.
     * 
     * @param request
     *            the current resize request.
     */
    protected void showResizeFeedBack(ChangeBoundsRequest request) {
        // TODO Refactor this and share the code with
        // SequenceMessageEditPolicy
        // and put in a graphical edit policy to inherit instead of copy the
        // feedback utility methods.

        Rectangle oldBounds = getHostAbsoluteBounds().getCopy();
        Rectangle newBounds = request.getTransformedRectangle(oldBounds).getCopy();
        Rectangle execBounds = newBounds.getCopy();

        FreeformViewport viewport = FigureUtilities.getFreeformViewport(getHostFigure());
        if (viewport != null) {
            oldBounds.translate(viewport.getViewLocation());
            newBounds.translate(viewport.getViewLocation());
        }

        if (getHost() instanceof ExecutionEditPart && newBounds.height > 0) {
            ISequenceEvent iSequenceEvent = ((ISequenceEventEditPart) getHost()).getISequenceEvent();
            GraphicalHelper.screen2logical(newBounds, (IGraphicalEditPart) getHost());
            GraphicalHelper.screen2logical(oldBounds, (IGraphicalEditPart) getHost());
            Range oldRange = RangeHelper.verticalRange(oldBounds);
            Range execRange = RangeHelper.verticalRange(newBounds);
            Range fullFinalRange = RangeHelper.verticalRange(newBounds);
            List<ISequenceEvent> delimitingMessages = EventEndHelper.getCompoundEvents(iSequenceEvent);
            if (delimitingMessages.size() > 0) {
                ISequenceEvent callMessage = delimitingMessages.get(0);
                Range callMsgRange = callMessage.getVerticalRange();
                if (request.isConstrainedMove()) {
                    fullFinalRange = new Range(oldRange.getLowerBound() - callMsgRange.width(), fullFinalRange.getUpperBound());
                } else {
                    fullFinalRange = new Range(fullFinalRange.getLowerBound() - callMsgRange.width(), fullFinalRange.getUpperBound());
                }
            }
            if (delimitingMessages.size() > 1) {
                ISequenceEvent returnMessage = delimitingMessages.get(1);
                Range returnMsgRange = returnMessage.getVerticalRange();
                if (request.isConstrainedResize()) {
                    fullFinalRange = new Range(fullFinalRange.getLowerBound(), oldRange.getUpperBound() + returnMsgRange.width());
                } else {
                    fullFinalRange = new Range(fullFinalRange.getLowerBound(), fullFinalRange.getUpperBound() + returnMsgRange.width());
                }
            }
            newBounds = new Rectangle(0, fullFinalRange.getLowerBound(), 0, fullFinalRange.width());
            execBounds = new Rectangle(0, execRange.getLowerBound(), 0, execRange.width());

            if (iSequenceEvent.isLogicallyInstantaneous() && delimitingMessages.isEmpty()) {
                execBounds.y = execBounds.getCenter().y;
                execBounds.height = 1;

                newBounds = execBounds.getCopy();
            }

            GraphicalHelper.logical2screen(newBounds, (IGraphicalEditPart) getHost());
            GraphicalHelper.logical2screen(execBounds, (IGraphicalEditPart) getHost());
        }

        Point topLocation = new Point(1, newBounds.getTop().y);
        Point bottomLocation = new Point(1, newBounds.getBottom().y);

        Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
        execBounds.height = Math.max(execBounds.height, 0);

        Figure execGuide = new RangeGuide(EXECUTION_FEEDBACK_COLOR, RangeHelper.verticalRange(execBounds), false);
        bounds.height = execBounds.height + 1;
        bounds.y = execBounds.y;
        execGuide.setBounds(bounds);
        addFeedback(execGuide);
        guides.add(execGuide);

        if (execBounds.y != topLocation.y) {
            Figure topGuide = new HorizontalGuide(EXECUTION_FEEDBACK_COLOR, topLocation.y);
            bounds.height = 1;
            bounds.y = topLocation.y;
            topGuide.setBounds(bounds);
            addFeedback(topGuide);
            guides.add(topGuide);
        }

        if (execBounds.bottom() != bottomLocation.y) {
            bounds = getFeedbackLayer().getBounds().getCopy();
            Figure bottomGuide = new HorizontalGuide(EXECUTION_FEEDBACK_COLOR, bottomLocation.y);
            bounds.height = 1;
            bounds.y = bottomLocation.y;
            bottomGuide.setBounds(bounds);
            addFeedback(bottomGuide);
            guides.add(bottomGuide);
        }
    }

    private Rectangle getHostAbsoluteBounds() {
        Rectangle bounds = getHostFigure().getBounds().getCopy();
        getHostFigure().getParent().translateToAbsolute(bounds);
        return bounds;
    }

    private void removeFeedBackOnGuides() {
        if (guides != null && !guides.isEmpty()) {
            for (Figure hGuide : guides) {
                removeFeedback(hGuide);
            }
            guides.clear();
        }
    }

    /**
     * Remove the horizontal feedback lines.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
        removeFeedBackOnGuides();
        super.eraseChangeBoundsFeedback(request);
    }

    /**
     * Cancel horizontal changes of the given request.
     * 
     * @param request
     *            a request.
     */
    protected void cancelHorizontalDelta(ChangeBoundsRequest request) {
        if (request == null) {
            return;
        }

        Point moveDelta = request.getMoveDelta();
        if (moveDelta != null) {
            request.setMoveDelta(new Point(0, moveDelta.y));
        }

        Dimension sizeDelta = request.getSizeDelta();
        if (sizeDelta != null) {
            request.setSizeDelta(new Dimension(0, sizeDelta.height));
        }
    }
}
