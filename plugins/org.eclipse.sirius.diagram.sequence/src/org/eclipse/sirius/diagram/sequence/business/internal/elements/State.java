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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;

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
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
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
        Preconditions.checkArgument(State.notationPredicate().apply(node), Messages.State_nonStaveNode);
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
     * Returns a predicate to check whether a Sirius DDiagramElement represents a state.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents a state.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    @Override
    public boolean canChildOccupy(ISequenceEvent child, Range range) {
        return false;
    }

    @Override
    public boolean canChildOccupy(ISequenceEvent child, Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines) {
        return false;
    }

    @Override
    public boolean isLogicallyInstantaneous() {
        List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(this);
        return ends.size() == 1 && EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(ends.iterator().next());
    }

    @Override
    public ISequenceEvent getHierarchicalParentEvent() {
        return getHierarchicalParentEvent(Messages.State_invalidStateContext);
    }

    @Override
    public List<ISequenceEvent> getSubEvents() {
        return Collections.emptyList();
    }

    @Override
    public Collection<ISequenceEvent> getEventsToMoveWith() {
        return Collections.emptyList();
    }

    @Override
    public Range getOccupiedRange() {
        return Range.emptyRange();
    }

    @Override
    public Range getValidSubEventsRange() {
        return Range.emptyRange();
    }

    @Override
    public List<Message> getLinkedMessages() {
        return Collections.emptyList();
    }
}
