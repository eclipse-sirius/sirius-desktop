/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * An operation to shift all the atomic events on a sequence diagram below a certain point to make room. The space is
 * added or removed from a point to the bottom direction.
 * 
 * @author pcdavid, smonnier
 */
public class VerticalSpaceExpansionOrReduction extends AbstractModelChangeOperation<Void> {

    private final SequenceDiagram sequenceDiagram;

    private int insertionPoint;

    private int expansionSize;

    private Set<ISequenceEvent> eventsToIgnore;

    private Set<ISequenceNode> eventsToResize;

    private Set<ISequenceNode> eventsToShift;

    private Set<Message> messagesToResize;

    private Set<Message> messagesToShift;

    private Map<Message, Range> finalMessagesRanges;

    private Integer move;

    /**
     * Constructor.
     * 
     * @param diagram
     *            the sequence diagram in which to make the change.
     * @param shift
     *            the zone to expand or reduce.
     * @param move
     *            how much the main execution which triggered this change is vertically moved.
     * @param eventsToIgnore
     *            the events which should be ignored, as they will be moved into the new space.
     */
    public VerticalSpaceExpansionOrReduction(SequenceDiagram diagram, Range shift, Integer move, Collection<ISequenceEvent> eventsToIgnore) {
        super(shift.getLowerBound() > shift.getUpperBound() ? MessageFormat.format(Messages.VerticalSpaceReduction_operationName, shift)
                : MessageFormat.format(Messages.VerticalSpaceExpansion_operationName, shift));
        this.sequenceDiagram = diagram;
        this.move = move;
        this.insertionPoint = shift.getLowerBound();
        this.expansionSize = shift.width();
        // Complete the specified eventsToIgnore with all their descendants.
        this.eventsToIgnore = new HashSet<>();
        for (ISequenceEvent evt : eventsToIgnore) {
            this.eventsToIgnore.add(evt);
            this.eventsToIgnore.addAll(new ISequenceEventQuery(evt).getAllDescendants());
            this.eventsToIgnore.addAll(new ISequenceEventQuery(evt).getAllMessages());
        }
    }

    @Override
    public Void execute() {
        categorizeSequenceNodes(findAllSequenceNodesToConsider());
        categorizeMessages(findAllMessagesToConsider());
        computeFinalMessageRanges();

        expandLifelines();
        shiftSequenceNodes();
        resizeSequenceNodes();
        setFinalMessagesRanges();

        return null;
    }

    private void setFinalMessagesRanges() {
        for (Entry<Message, Range> smep : finalMessagesRanges.entrySet()) {
            smep.getKey().setVerticalRange(smep.getValue());
        }
    }

    private void computeFinalMessageRanges() {
        finalMessagesRanges = new HashMap<>();
        Set<Message> messages = sequenceDiagram.getAllMessages();
        for (Message msg : messages) {
            if (messagesToShift.contains(msg)) {
                if (!isConnectedToAMovedExecutionByASingleEnd(msg) && !isContainedReflexiveMessage(msg)) {
                    finalMessagesRanges.put(msg, msg.getVerticalRange().shifted(expansionSize));
                } else {
                    finalMessagesRanges.put(msg, msg.getVerticalRange().shifted(move));
                }
            } else if (messagesToResize.contains(msg)) {
                final Range rangeBefore = msg.getVerticalRange();
                finalMessagesRanges.put(msg, new Range(rangeBefore.getLowerBound(), rangeBefore.getUpperBound() + expansionSize));
            } else {
                finalMessagesRanges.put(msg, msg.getVerticalRange());
            }
        }
    }

    private boolean isConnectedToAMovedExecutionByASingleEnd(Message msg) {
        boolean onlySourceIsInMovedExecutions = eventsToIgnore.contains(msg.getSourceElement()) && !eventsToIgnore.contains(msg.getTargetElement());
        boolean onlyTargetIsInMovedExecutions = !eventsToIgnore.contains(msg.getSourceElement()) && eventsToIgnore.contains(msg.getTargetElement());
        return onlySourceIsInMovedExecutions || onlyTargetIsInMovedExecutions;
    }

    /**
     * Expand all the lifelines which do not have a destroy message, but keep the messages they contain stable.
     */
    private void expandLifelines() {
        List<Lifeline> lifelines = sequenceDiagram.getAllLifelines();
        lifelines.removeAll(eventsToIgnore);
        for (Lifeline lifeline : lifelines) {
            Option<Message> cm = lifeline.getCreationMessage();
            if (cm.some() && isStrictlyBelowInsertionPoint(cm.get())) {
                /*
                 * The whole lifeline is below the insertion point.
                 */
                InstanceRole irep = lifeline.getInstanceRole();
                Node node = (Node) irep.getNotationView();
                LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                if (layoutConstraint instanceof Location) {
                    Location location = (Location) layoutConstraint;
                    location.setY(location.getY() + expansionSize);
                }
            } else {
                /*
                 * Only the end of the lifeline is below the insertion point.
                 */
                Option<Message> dm = lifeline.getDestructionMessage();
                if (!dm.some() || isStrictlyBelowInsertionPoint(dm.get())) {
                    expandDown(lifeline, expansionSize);
                }
            }
        }
    }

    /**
     * Find all the executions in the diagram which may be affected by the operation. This is <em>all</em> the
     * executions except the ones we are explicitly told to ignore (and their descendants).
     */
    private Set<ISequenceNode> findAllSequenceNodesToConsider() {
        Set<ISequenceNode> sequenceNodes = new LinkedHashSet<>();
        sequenceNodes.addAll(sequenceDiagram.getAllAbstractNodeEvents());
        sequenceNodes.addAll(sequenceDiagram.getAllInteractionUses());
        sequenceNodes.addAll(sequenceDiagram.getAllCombinedFragments());
        sequenceNodes.addAll(sequenceDiagram.getAllOperands());
        sequenceNodes.removeAll(eventsToIgnore);
        return sequenceNodes;
    }

    private Set<Message> findAllMessagesToConsider() {
        Set<Message> messages = new HashSet<>();
        for (Message msg : sequenceDiagram.getAllMessages()) {
            if (!isBetweenTwoMovedEvents(msg) || isContainedReflexiveMessage(msg)) {
                messages.add(msg);
            }
        }
        return messages;
    }

    private boolean isBetweenTwoMovedEvents(Message msg) {
        return eventsToIgnore.contains(msg.getSourceElement()) && eventsToIgnore.contains(msg.getTargetElement());
    }

    /**
     * Validate if the message is a reflexive message between two ignored executions
     * 
     * @param msg
     *            a Message
     * @return if the message "msg" is a reflexive message between two ignored executions
     */
    private boolean isContainedReflexiveMessage(Message msg) {
        return eventsToIgnore.contains(msg.getSourceElement()) && eventsToIgnore.contains(msg.getTargetElement()) && msg.isReflective();
    }

    private void categorizeMessages(Set<Message> messages) {
        messagesToResize = new HashSet<>();
        messagesToShift = new HashSet<>();
        for (Message ise : Iterables.filter(messages, Predicates.not(Predicates.in(eventsToIgnore)))) {
            if (containsInsertionPoint(ise)) {
                messagesToResize.add(ise);
            } else if (isStrictlyBelowInsertionPoint(ise) || isConnectedToAMovedExecutionByASingleEnd(ise)) {
                messagesToShift.add(ise);
            }
        }
    }

    /**
     * Decide what needs to be done for each of the specified ISequenceNode:
     * <ul>
     * <li>nothing if it is above the expansion zone.</li>
     * <li>a resize if it intersects the insertion point, i.e. its top is above the point but its bottom is below.</li>
     * <li>a complete shift if it is completely below the insertion point.</li>
     * </ul>
     * <p>
     * After completion of this method, <code>toResize</code> contains all the executions which need to be resized and
     * <code>toShift</code> all the executions which need to be shifted.
     */
    private void categorizeSequenceNodes(Set<? extends ISequenceNode> sequenceNodes) {
        eventsToResize = new HashSet<>();
        eventsToShift = new HashSet<>();
        for (ISequenceNode isn : sequenceNodes) {
            if (isn instanceof ISequenceEvent) {
                ISequenceEvent ise = (ISequenceEvent) isn;
                if (containsInsertionPoint(ise)) {
                    eventsToResize.add(isn);
                } else if (isStrictlyBelowInsertionPoint(ise) && !(isn instanceof Operand && eventsToShift.contains(((Operand) isn).getCombinedFragment()))) {
                    eventsToShift.add(isn);
                }
            }
        }
    }

    private void shiftSequenceNodes() {

        for (ISequenceNode nodes : Iterables.filter(eventsToShift, Predicates.not(Predicates.instanceOf(AbstractNodeEvent.class)))) {
            shift(nodes, expansionSize);
        }

        for (AbstractNodeEvent execution : Iterables.filter(eventsToShift, AbstractNodeEvent.class)) {
            Lifeline lep = execution.getLifeline().get();
            Option<Message> cm = lep.getCreationMessage();
            if (cm.some() && isStrictlyBelowInsertionPoint(cm.get())) {
                continue;
            }
            /*
             * Only actually shift the "top-level" executions. The rest will be moved along with their shifted ancestor,
             * as execution position is relative to its parent.
             */

            if (!containsAncestors(eventsToShift, execution)) {
                shift(execution, expansionSize);
            }
        }

        for (AbstractNodeEvent execution : Iterables.filter(eventsToIgnore, AbstractNodeEvent.class)) {
            /*
             * Unshift events to ignore...
             */
            if (eventsToShift.contains(execution.getHierarchicalParentEvent())) {
                shift(execution, -expansionSize);
            }
        }
    }

    private void resizeSequenceNodes() {
        for (ISequenceNode ise : eventsToResize) {
            expandDown(ise, expansionSize);
        }
    }

    private boolean containsAncestors(Set<ISequenceNode> events, AbstractNodeEvent ise) {
        ISequenceEvent parent = ise.getHierarchicalParentEvent();
        if (parent == null || !(parent instanceof AbstractNodeEvent)) {
            return false;
        } else {
            return Iterables.contains(events, parent) || containsAncestors(events, (AbstractNodeEvent) parent);
        }
    }

    private boolean containsInsertionPoint(ISequenceEvent event) {
        return event != null && event.getVerticalRange().includes(insertionPoint, true, false);
    }

    private boolean isStrictlyBelowInsertionPoint(ISequenceEvent event) {
        return event != null && event.getVerticalRange().getLowerBound() >= insertionPoint;
    }

    private void expandDown(Lifeline lifeline, int height) {
        Range range = lifeline.getVerticalRange();
        lifeline.setVerticalRange(new Range(range.getLowerBound(), range.getUpperBound() + height));
    }

    private void expandDown(ISequenceNode isn, int height) {
        Node node = isn.getNotationNode();
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Size) {
            Size s = (Size) layoutConstraint;
            s.setHeight(s.getHeight() + height);
        }
    }

    private void shift(ISequenceNode isn, int height) {
        Node node = (Node) isn.getNotationView();
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Location && height != 0) {
            Location location = (Location) layoutConstraint;
            location.setY(location.getY() + height);
        }
    }
}
