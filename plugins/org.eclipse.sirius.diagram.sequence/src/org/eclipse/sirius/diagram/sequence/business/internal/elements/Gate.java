/*******************************************************************************
 * Copyright (c) 2025 CEA and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Represents a Gate on a Combine fragment, Interaction Use or Interaction Container.
 * 
 * @author smonnier
 */
public class Gate extends AbstractMultiLifelineNodeEvent {
    /**
     * Predicate to filter Frames and Operand from possible new parents of an gate reparent.
     */
    public static final Predicate<ISequenceEvent> NO_REPARENTABLE_EVENTS = new Predicate<ISequenceEvent>() {
        @Override
        public boolean apply(ISequenceEvent input) {
            return input instanceof AbstractFrame || input instanceof Operand || input instanceof Message;
        }
    };

    /**
     * The visual ID. Same as a normal bordered node.
     */
    public static final int VISUAL_ID = 3012;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an gate.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getGateMapping())
                    && !InstanceRole.viewpointElementPredicate().apply((DDiagramElement) input.eContainer());
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing this gate.
     */
    Gate(Node node) {
        super(node);
        Preconditions.checkArgument(Gate.notationPredicate().apply(node), Messages.Gate_nonGateNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an gate.
     * 
     * @return a predicate to check whether a GMF View represents an gate.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, Gate.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an gate.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an gate.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

//    @Override
//    public boolean canChildOccupy(ISequenceEvent child, Range range) {
//        return new SubEventsHelper(this).canChildOccupy(child, range);
//    }
//
//    @Override
//    public boolean canChildOccupy(ISequenceEvent child, Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines) {
//        return new SubEventsHelper(this).canChildOccupy(child, range, eventsToIgnore, lifelines);
//    }
//
//    @Override
//    public boolean isLogicallyInstantaneous() {
//        return false;
//    }
//
//    @Override
//    public ISequenceEvent getHierarchicalParentEvent() {
//        return getHierarchicalParentEvent(Messages.Gate_invalidGateContext);
//    }
//
//    @Override
//    public Rectangle getProperLogicalBounds() {
//        EObject viewContainer = this.view.eContainer();
//        if (viewContainer instanceof View) {
//            View parentView = (View) viewContainer;
// Option<Gate> gate = ISequenceElementAccessor.getGate(parentView);
// if (gate.some()) {
// return gate.get().getProperLogicalBounds();
//            }
//        }
//        return super.getProperLogicalBounds();
//    }
//
//    @Override
//    public List<ISequenceEvent> getSubEvents() {
//        return Collections.emptyList();
//    }
//
//    @Override
//    public Collection<ISequenceEvent> getEventsToMoveWith() {
//        Set<ISequenceEvent> toMove = new LinkedHashSet<>();
//        List<ISequenceEvent> subEvents = getSubEvents();
//        // toMove.addAll(findLinkedExecutions(subEvents));
//        toMove.addAll(getLinkedMessages());
//        // toMove.addAll(findCoveredExecutions(subEvents));
//        toMove.addAll(subEvents);
//        return toMove;
//    }
//
//    @Override
//    public Range getOccupiedRange() {
//        return new ISequenceEventQuery(this).getOccupiedRange();
//    }
//
//    /**
//     * Sub-events can occur anywhere on a normal execution as long as it is strictly inside.
//     * <p>
//     * {@inheritDoc}
//     */
//    @Override
//    public Range getValidSubEventsRange() {
//        Range range = getVerticalRange();
//        if (range.width() > 2 * LayoutConstants.EXECUTION_CHILDREN_MARGIN) {
//            return range.shrinked(LayoutConstants.EXECUTION_CHILDREN_MARGIN);
//        } else {
//            return range;
//        }
//    }

    @Override
    public List<Message> getLinkedMessages() {
        List<Message> linkedMessages = new ArrayList<>();

        Option<Message> startMessage = getStartMessage();
        if (startMessage.some()) {
            linkedMessages.add(startMessage.get());
        }

        Option<Message> targetMessage = getEndMessage();
        if (targetMessage.some()) {
            linkedMessages.add(targetMessage.get());
        }

        return linkedMessages;
    }

    /**
     * Returns the message linked to the start (i.e. top side) of this gate, if any.
     * 
     * @return the message linked to the start of this execution, if any.
     */
    public Option<Message> getStartMessage() {
        return getCompoundMessage(true);
    }

    /**
     * Returns the message linked to the end (i.e. bottom side) of this gate, if any.
     * 
     * @return the message linked to the end of this execution, if any.
     */
    public Option<Message> getEndMessage() {
        return getCompoundMessage(false);
    }

    private Option<Message> getCompoundMessage(boolean start) {
        Message result = null;
        Option<Message> resultOption = Options.newNone();
        if (CacheHelper.isStructuralCacheEnabled()) {
            if (start) {
                result = CacheHelper.getStartCompoundMessageCache().get(this);
            } else {
                result = CacheHelper.getEndCompoundMessageCache().get(this);
            }
            if (result != null) {
                resultOption = Options.newSome(result);
            }
        }

        if (!resultOption.some()) {
            Node node = getNotationNode();
            Set<Edge> edges = new HashSet<>();
            Iterables.addAll(edges, Iterables.filter(node.getSourceEdges(), Edge.class));
            Iterables.addAll(edges, Iterables.filter(node.getTargetEdges(), Edge.class));

            List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(this);
            for (Edge edge : edges) {
                Option<Message> message = ISequenceElementAccessor.getMessage(edge);
                if (message.some() && start && this.equals(message.get().getSourceElement())) {
                    resultOption = message;
                    break;
                } else if (message.some() && !start && this.equals(message.get().getTargetElement())) {
                    resultOption = message;
                    break;
                }
            }
        }
        return resultOption;
    }

//    private void putMessageInCache(boolean start, Message message) {
//        if (CacheHelper.isStructuralCacheEnabled()) {
//            if (start) {
//                CacheHelper.getStartCompoundMessageCache().put(this, message);
//            } else {
//                CacheHelper.getEndCompoundMessageCache().put(this, message);
//            }
//        }
//    }

    /**
     * Tests whether this gate starts with a reflective message.
     * 
     * @return <code>true</code> if this gate has a reflective message linked to its start.
     */
    public boolean startsWithReflectiveMessage() {
        Option<Message> startMessage = getStartMessage();
        if (startMessage.some()) {
            return startMessage.get().isReflective();
        } else {
            return false;
        }
    }

    /**
     * Tests whether this gate ends with a reflective message.
     * 
     * @return <code>true</code> if this gate has a reflective message linked to its end.
     */
    public boolean endsWithReflectiveMessage() {
        Option<Message> finishMessage = getEndMessage();
        if (finishMessage.some()) {
            return finishMessage.get().isReflective();
        } else {
            return false;
        }
    }

    @Override
    protected Option<Lifeline> getParentLifeline() {
        // TODO return a lifeline covered by parent or empty list?
        return Options.newSome(this.getDiagram().getAllLifelines().get(0));
    }

    @Override
    public Rectangle getProperLogicalBounds() {
        if (getNotationNode().getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) getNotationNode().getLayoutConstraint();
            return new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        } else {
            throw new RuntimeException();
        }
    }
}
