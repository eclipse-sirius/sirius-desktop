/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.ISequenceNodeMoveOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.ReparentExecutionOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SetMessageRangeOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.VerticalSpaceExpansionOrReduction;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ShiftMessagesOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.ISEComplexMoveValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A builder for complex sequence move commands.
 * 
 * @author mporhel
 */
public class ISEComplexMoveCommandBuilder {

    private final TransactionalEditingDomain editingDomain;

    private final String label;

    private final RequestQuery requestQuery;

    private final ISEComplexMoveValidator validator;

    /**
     * Constructor.
     * 
     * @param editingDomain
     *            tue editing domain
     * @param label
     *            the label of the command to build
     * @param requestQuery
     *            a query corresponding to the current request
     * @param validator
     *            the move validator.
     */
    public ISEComplexMoveCommandBuilder(TransactionalEditingDomain editingDomain, String label, RequestQuery requestQuery, ISEComplexMoveValidator validator) {
        this.editingDomain = editingDomain;
        this.label = label;
        this.requestQuery = requestQuery;
        this.validator = validator;
    }

    /**
     * Build the command.
     * 
     * @return a composite transactional command.
     */
    public CompositeTransactionalCommand buildCommand() {
        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, label);

        Integer vMove = requestQuery.getLogicalDelta().y;
        expandDiagram(ctc, vMove);
        handleNodes(ctc, vMove);
        handleMessages(ctc, vMove);

        return ctc;
    }

    private void handleMessages(CompositeTransactionalCommand ctc, Integer vMove) {

        shiftMessages(ctc, vMove);
        resizeMessages(ctc, vMove);
        reconnectMessages(ctc, vMove);

    }

    private void handleNodes(CompositeTransactionalCommand ctc, Integer vMove) {
        Collection<ISequenceNode> seqNodesToMove = new ArrayList<ISequenceNode>(validator.getSequenceNodeToMove());
        Map<AbstractNodeEvent, ISequenceEvent> reparents = new HashMap<>();

        computeReparents(seqNodesToMove, reparents);

        moveNodes(ctc, vMove, seqNodesToMove);
        reparentNodes(ctc, vMove, reparents);
    }

    private void reconnectMessages(CompositeTransactionalCommand ctc, Integer vMove) {
        for (Reconnection reconnection : computeReconnections()) {
            Message message = reconnection.getMessage();
            ISequenceNode source = reconnection.getSource();
            ISequenceNode target = reconnection.getTarget();

            Rectangle srcBounds = source.getProperLogicalBounds();
            Rectangle tgtBounds = target.getProperLogicalBounds();
            Collection<ISequenceEvent> movedElements = validator.getMovedElements();
            if (movedElements.contains(source)) {
                srcBounds = requestQuery.getLogicalTransformedRectangle(srcBounds);
            }
            if (movedElements.contains(target)) {
                tgtBounds = requestQuery.getLogicalTransformedRectangle(tgtBounds);
            }

            Range verticalRange = message.getVerticalRange();
            Range newRange = movedElements.contains(message) ? verticalRange.shifted(vMove) : verticalRange;
            SetMessageRangeOperation smrc = new SetMessageRangeOperation((Edge) message.getNotationView(), newRange);
            smrc.setSource(source.getNotationNode(), srcBounds);
            smrc.setTarget(target.getNotationNode(), tgtBounds);
            ctc.compose(CommandFactory.createICommand(editingDomain, smrc));
        }
    }

    private void reparentNodes(CompositeTransactionalCommand ctc, Integer vMove, Map<AbstractNodeEvent, ISequenceEvent> reparents) {
        for (Map.Entry<AbstractNodeEvent, ISequenceEvent> entry : reparents.entrySet()) {
            ISequenceEvent newParent = entry.getValue();
            ctc.compose(CommandFactory.createICommand(editingDomain, new ReparentExecutionOperation(entry.getKey(), newParent)));

            // Compute the absolute bounds implied by the requested move.
            Rectangle realLocation = entry.getKey().getProperLogicalBounds();
            if (validator.getMovedElements().contains(entry.getKey())) {
                realLocation = requestQuery.getLogicalTransformedRectangle(realLocation);
            }

            // Adjust x coordinate depending on the nature of the final parent.
            Rectangle parentBounds = newParent.getProperLogicalBounds();
            if (validator.getMovedElements().contains(newParent)) {
                parentBounds = requestQuery.getLogicalTransformedRectangle(parentBounds);
            }

            Range futureRange = validator.getRangeFunction().apply(entry.getKey());
            realLocation.setY(futureRange.getLowerBound());

            // Make the coordinates relative to the final parent's figure.
            final Point parentOrigin = parentBounds.getLocation();
            final Dimension d = realLocation.getTopLeft().getDifference(parentOrigin);
            Point locationOnFinalParent = new Point(realLocation.x, d.height);

            // Create the command to apply the change.
            final ICommand moveCommand = new SetBoundsCommand(ctc.getEditingDomain(), DiagramUIMessages.Commands_MoveElement, new EObjectAdapter(entry.getKey().getNotationNode()),
                    locationOnFinalParent);
            ctc.compose(moveCommand);

        }
    }

    private void shiftMessages(CompositeTransactionalCommand ctc, Integer vMove) {
        ICommand messageMoveCommand = CommandFactory.createICommand(editingDomain, new ShiftMessagesOperation(validator.getMessageToMove(), validator.getMovedElements(), vMove, false, true));
        ctc.add(messageMoveCommand);
    }

    private void resizeMessages(CompositeTransactionalCommand ctc, Integer vMove) {
        for (Message msg : Iterables.concat(validator.getResizedStartMessages(), validator.getResizedEndMessages())) {
            SetMessageRangeOperation smrc = new SetMessageRangeOperation(msg.getNotationEdge(), validator.getRangeFunction().apply(msg));

            ISequenceEvent src = (ISequenceEvent) msg.getSourceElement();
            Range srcRange = validator.getRangeFunction().apply(src);
            smrc.setSource(src.getNotationView(), new Rectangle(0, srcRange.getLowerBound(), 0, srcRange.getUpperBound()));

            ISequenceEvent tgt = (ISequenceEvent) msg.getTargetElement();
            Range tgtRange = validator.getRangeFunction().apply(tgt);
            smrc.setTarget(tgt.getNotationView(), new Rectangle(0, tgtRange.getLowerBound(), 0, tgtRange.getUpperBound()));

            ICommand resizeStartMessages = CommandFactory.createICommand(editingDomain, smrc);
            ctc.add(resizeStartMessages);
        }
    }

    private void moveNodes(CompositeTransactionalCommand ctc, Integer vMove, Collection<ISequenceNode> seqNodesToMove) {
        ICommand moveExecCmd = CommandFactory.createICommand(editingDomain, new ISequenceNodeMoveOperation(seqNodesToMove, vMove));
        ctc.compose(moveExecCmd);
        ctc.setLabel(moveExecCmd.getLabel());
    }

    private void expandDiagram(CompositeTransactionalCommand ctc, Integer vMove) {
        if (validator.getExpansionZone() != null && !validator.getExpansionZone().isEmpty()) {
            ctc.compose(CommandFactory.createICommand(editingDomain, new VerticalSpaceExpansionOrReduction(validator.getDiagram(), validator.getExpansionZone(), vMove, validator.getMovedElements())));
        }
    }

    private void computeReparents(Collection<ISequenceNode> sequenceNodesToMove, Map<AbstractNodeEvent, ISequenceEvent> reparents) {
        // reparent directly moved execution
        Collection<AbstractNodeEvent> movedExecutions = Lists.newArrayList(Iterables.filter(sequenceNodesToMove, AbstractNodeEvent.class));

        // reparent unmoved executions
        // filter unmoved executions to keep only ones with an intersection with initial or final range
        Range validatorInitialRange = validator.getInitialRange();
        Range validatorFinalRange = validator.getMovedRange();
        Predicate<AbstractNodeEvent> filterRange = new Predicate<AbstractNodeEvent>() {

            @Override
            public boolean apply(AbstractNodeEvent nodeEvent) {
                Range initialRange = nodeEvent.getVerticalRange();
                Range futureRange = validator.getRangeFunction().apply(nodeEvent);
                return validatorInitialRange.intersects(initialRange) || validatorFinalRange.intersects(futureRange);
            }
        };
        Iterable<AbstractNodeEvent> filterUnmovedExecutions = Iterables.filter(validator.getDiagram().getAllAbstractNodeEvents(),
                Predicates.and(Predicates.not(Predicates.in(validator.getMovedElements())), filterRange));
        Collection<AbstractNodeEvent> unmovedExecutions = Lists.newArrayList(filterUnmovedExecutions);

        for (AbstractNodeEvent execToReparent : Iterables.concat(movedExecutions, unmovedExecutions)) {
            ISequenceEvent potentialParent = getNewParent(execToReparent, reparents);
            if (potentialParent instanceof ISequenceNode && !potentialParent.equals(execToReparent.getHierarchicalParentEvent())) {
                reparents.put(execToReparent, potentialParent);
                sequenceNodesToMove.remove(execToReparent);
            }
        }
    }

    private Collection<Reconnection> computeReconnections() {
        Collection<Reconnection> reconnections = new ArrayList<>();
        // Reconnect moved and unmoved messages

        Set<Message> allMessages = validator.getDiagram().getAllMessages();

        Range validatorInitialRange = validator.getInitialRange();
        Range validatorFinalRange = validator.getMovedRange();
        Predicate<Message> filterRange = new Predicate<Message>() {

            @Override
            public boolean apply(Message msg) {
                Range initialRange = msg.getVerticalRange();
                Range futureRange = validator.getRangeFunction().apply(msg);

                if (initialRange.width() == 0) {
                    return validatorInitialRange.includes(initialRange.getLowerBound()) || validatorFinalRange.includes(futureRange.getLowerBound());
                }
                return validatorInitialRange.intersects(initialRange) || validatorFinalRange.intersects(futureRange);
            }
        };

        for (Message message : Iterables.filter(allMessages, filterRange)) {

            // check source change
            ISequenceNode sourceElement = message.getSourceElement();
            ISequenceNode newSource = getNewReconnectionEnd(message, sourceElement);

            // check target change
            ISequenceNode targetElement = message.getTargetElement();
            ISequenceNode newTarget = targetElement;
            if (targetElement instanceof ISequenceEvent) {
                newTarget = getNewReconnectionEnd(message, targetElement);
            }

            if (!sourceElement.equals(newSource) || !targetElement.equals(newTarget)) {
                Reconnection rec = new Reconnection(message, newSource, newTarget);
                reconnections.add(rec);
            }
        }
        return reconnections;
    }

    private ISequenceEvent getNewParent(AbstractNodeEvent movedExec, Map<AbstractNodeEvent, ISequenceEvent> reparents) {
        EventFinder newParentFinder = new EventFinder(movedExec.getLifeline().get());
        newParentFinder.setReparent(true);
        newParentFinder.setVerticalRangefunction(validator.getRangeFunction());
        newParentFinder.setEventsToIgnore(Predicates.equalTo((ISequenceEvent) movedExec));
        newParentFinder.setReparented(reparents);
        Range futureRange = validator.getRangeFunction().apply(movedExec);
        Range lookedRange = new Range(futureRange.getLowerBound(), futureRange.getLowerBound());

        if (movedExec instanceof State && movedExec.isLogicallyInstantaneous()) {
            int mid = futureRange.middleValue();
            lookedRange = new Range(mid, mid);
        }

        ISequenceEvent potentialParent = newParentFinder.findMostSpecificEvent(lookedRange);
        return potentialParent;
    }

    private ISequenceNode getNewReconnectionEnd(Message message, ISequenceNode actualEnd) {
        boolean compoundEnds = false;
        if (message.isReflective() && actualEnd instanceof Execution) {
            Execution exec = (Execution) actualEnd;
            compoundEnds = exec.getLinkedMessages().contains(message);
        }

        boolean bothMoved = false;
        bothMoved = validator.getMovedElements().contains(message) && validator.getMovedElements().contains(actualEnd);

        if (!compoundEnds && !bothMoved && actualEnd instanceof ISequenceEvent) {
            EventFinder newEndFinder = new EventFinder(actualEnd.getLifeline().get());
            newEndFinder.setReconnection(true);

            Range lookedRange = validator.getRangeFunction().apply(message);
            newEndFinder.setVerticalRangefunction(validator.getRangeFunction());

            ISequenceEvent potentialEnd = newEndFinder.findMostSpecificEvent(lookedRange);
            if (potentialEnd instanceof ISequenceNode) {
                return (ISequenceNode) potentialEnd;
            }
        }
        return actualEnd;
    }

    /**
     * Wrapper to handle reconnection.
     * 
     * @author mporhel
     * 
     */
    public static class Reconnection {
        final Message message;

        final ISequenceNode source;

        final ISequenceNode target;

        /**
         * Constructor.
         * 
         * @param message
         *            message to reconnect.
         * @param source
         *            source after reconnection.
         * @param target
         *            target after reconnection.
         */
        public Reconnection(Message message, ISequenceNode source, ISequenceNode target) {
            this.message = message;
            this.source = source;
            this.target = target;
        }

        public Message getMessage() {
            return message;
        }

        public ISequenceNode getSource() {
            return source;
        }

        public ISequenceNode getTarget() {
            return target;
        }
    }
}
