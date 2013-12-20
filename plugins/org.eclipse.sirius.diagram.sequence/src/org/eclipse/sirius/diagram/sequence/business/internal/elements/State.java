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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceNodeQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.business.internal.util.RangeSetter;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.util.NotationPredicate;
import org.eclipse.sirius.diagram.sequence.util.Range;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Represents a state on a lifeline or an execution.
 * 
 * @author smonnier
 */
public class State extends AbstractNodeEvent {

    /**
     * The visual ID. Same as a normal bordered node.
     * 
     * @see org.eclipse.sirius.diagram.internal.edit.parts.DNode2EditPart.VISUAL_ID
     */
    public static final int VISUAL_ID = 3001;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents a state.
     */
    private static enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getStateMapping())
                    && !InstanceRole.viewpointElementPredicate().apply((DDiagramElement) input.eContainer());
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing this state.
     */
    State(Node node) {
        super(node);
        Preconditions.checkArgument(State.notationPredicate().apply(node), "The node does not represent an state.");
    }

    /**
     * Returns a predicate to check whether a GMF View represents a state.
     * 
     * @return a predicate to check whether a GMF View represents a state.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, State.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents
     * a state.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents
     *         a state.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canChildOccupy(ISequenceEvent child, Range range) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canChildOccupy(ISequenceEvent child, Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<ISequenceEvent> getEventsToMoveWith() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISequenceEvent getHierarchicalParentEvent() {
        EObject viewContainer = this.view.eContainer();
        if (viewContainer instanceof View) {
            View parentView = (View) viewContainer;
            Option<ISequenceEvent> parentElement = ISequenceElementAccessor.getISequenceEvent(parentView);
            if (parentElement.some()) {
                return parentElement.get();
            }
        }
        throw new RuntimeException("Invalid context for state " + this);
    }

    /**
     * {@inheritDoc}
     */
    public Range getOccupiedRange() {
        return Range.emptyRange();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISequenceEvent getParentEvent() {
        ISequenceEvent parent = getHierarchicalParentEvent();

        List<ISequenceEvent> potentialSiblings = parent.getSubEvents();
        if (!potentialSiblings.contains(this)) {
            // look for parentOperand
            parent = getParentOperand().get();
        }
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Operand> getParentOperand() {
        return new ParentOperandFinder(this).getParentOperand();
    }

    /**
     * {@inheritDoc}
     */
    public List<ISequenceEvent> getSubEvents() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    public Range getValidSubEventsRange() {
        return Range.emptyRange();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Range getVerticalRange() {
        return new SequenceNodeQuery(getNotationNode()).getVerticalRange();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogicallyInstantaneous() {
        List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(this);
        return ends.size() == 1 && EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(ends.iterator().next());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVerticalRange(Range range) throws IllegalStateException {
        RangeSetter.setVerticalRange(this, range);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Lifeline> getLifeline() {
        return getParentLifeline();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Message> getLinkedMessages() {
        return Collections.emptyList();
    }
}
