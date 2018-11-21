/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ISequenceEventsTreeIterator;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * This operation is called when an execution is moved or resized vertically. It
 * adjusts the GMF bendpoints of the messages to/from an execution (or any of
 * its sub-executions) so that the messages are moved along with the execution
 * of the same amount.
 * 
 * @author pcdavid, mporhel, smonnier
 */
public class ShiftDescendantMessagesOperation extends ShiftMessagesOperation {
    private ISequenceEvent parent;

    private final boolean fromTop;

    /**
     * Used for execution reparent.
     */
    private boolean ignoreContainedReflexiveMessage;

    private Range oldParentRange;

    private Range newParentRange;

    private ISequenceEvent finalGrandParent;

    /**
     * Constructor to shift all sub messages after move.
     * 
     * @param parent
     *            the execution whose descendant messages must be adjusted.
     * @param deltaY
     *            the vertical amount the execution was moved.
     */
    public ShiftDescendantMessagesOperation(ISequenceEvent parent, int deltaY) {
        this(parent, deltaY, false, true, true);
    }

    /**
     * Constructor.
     * 
     * @param parent
     *            the execution whose descendant messages must be adjusted.
     * @param deltaY
     *            the vertical amount the execution was moved.
     * @param revert
     *            if true, revert the adjustments from source/target vectors
     * @param move
     *            if true, the messages of any of its sub-executions will be
     *            shifted. If false, the parent part was resized and only direct
     *            sub messages will be shifted
     * @param fromTop
     *            Used if move = false (resize) to know from where the
     *            parentPart is resized.
     */
    public ShiftDescendantMessagesOperation(ISequenceEvent parent, int deltaY, boolean revert, boolean move, boolean fromTop) {
        super(MessageFormat.format(Messages.ShifDescendantMessagesOperation_operationName, deltaY), deltaY, revert, move);
        this.parent = parent;
        this.fromTop = fromTop;
        this.oldParentRange = parent.getVerticalRange();
        this.newParentRange = getNewParentRange();
        this.finalGrandParent = null;
    }

    /**
     * Constructor.
     * 
     * @param parent
     *            the execution whose descendant messages must be adjusted.
     * @param finalGrandParent
     *            the actual grandparent of the "executionEditPart" replacement
     *            (after a refresh) at the time of command execution.
     * @param deltaY
     *            the vertical amount the execution was moved.
     * @param ignoreContainedReflexiveMessage
     *            the parameter to decide if we need to ignore the contained
     *            reflexive messages.
     */
    public ShiftDescendantMessagesOperation(ISequenceEvent parent, ISequenceEvent finalGrandParent, int deltaY, boolean ignoreContainedReflexiveMessage) {
        this(parent, deltaY);
        this.finalGrandParent = finalGrandParent;
        this.ignoreContainedReflexiveMessage = ignoreContainedReflexiveMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        final Set<ISequenceEvent> descendants = new HashSet<>();
        populateMessageToShift(descendants);

        // Handle messages.
        super.execute();

        // Handle notes.
        Set<Edge> allConnections = new HashSet<>();

        Iterator<ISequenceEvent> iter = new ISequenceEventsTreeIterator(parent, true);
        while (iter.hasNext()) {
            ISequenceEvent iSequenceEvent = iter.next();
            populateConnections(allConnections, iSequenceEvent.getNotationView());
        }

        for (Edge conn : allConnections) {
            if (!descendants.contains(conn) && (isNoteAttachment(conn) || isNonSequenceEdgeAttachment(conn))) {
                shiftAnchor(conn);
            }
        }
        return null;
    }

    private void populateMessageToShift(Set<ISequenceEvent> descendants) {
        if (finalGrandParent != null) {
            final View model = parent.getNotationView();
            for (final ISequenceEvent child : finalGrandParent.getSubEvents()) {
                if (child.getNotationView() == model) {
                    parent = child;
                    break;
                }
            }
        }

        populate(descendants);

        final Predicate<Message> filterReflexiveMessage = new Predicate<Message>() {
            @Override
            public boolean apply(final Message input) {
                return !input.isReflective();
            }
        };

        if (ignoreContainedReflexiveMessage) {
            Iterables.addAll(messagesToShift, Iterables.filter(Iterables.filter(descendants, Message.class), filterReflexiveMessage));
        } else {
            Iterables.addAll(messagesToShift, Iterables.filter(descendants, Message.class));
        }
    }

    private void populateConnections(Set<Edge> allConnections, View part) {
        Iterables.addAll(allConnections, Iterables.filter(part.getSourceEdges(), Edge.class));
        Iterables.addAll(allConnections, Iterables.filter(part.getTargetEdges(), Edge.class));
    }

    private boolean isNoteAttachment(Edge conn) {
        return conn != null && GMFNotationHelper.isNoteAttachment(conn);
    }
    
    private boolean isNonSequenceEdgeAttachment(Edge conn) {
        return conn != null && conn.getElement() instanceof DEdge && !ISequenceElementAccessor.getMessage(conn).some();
    }

    private void shiftAnchor(Edge edge) {
        boolean isOutgoing = Iterables.contains(Iterables.transform(movedElements, ISequenceElement.NOTATION_VIEW), edge.getSource());

        if (isOutgoing) {
            Anchor sourceAnchor = edge.getSourceAnchor();
            if (sourceAnchor instanceof IdentityAnchor) {
                IdentityAnchor srcIdentityAnchor = (IdentityAnchor) sourceAnchor;
                int sourceAnchorLocation = SequenceGraphicalHelper.getAnchorAbsolutePosition(srcIdentityAnchor, oldParentRange);
                PrecisionPoint position = BaseSlidableAnchor.parseTerminalString(srcIdentityAnchor.getId());
                position.setPreciseY(newParentRange.getProportionalLocation(sourceAnchorLocation));
                String terminal = new SlidableAnchor(null, position).getTerminal();
                srcIdentityAnchor.setId(terminal);
            }
        } else {
            Anchor targetAnchor = edge.getTargetAnchor();
            if (targetAnchor instanceof IdentityAnchor) {
                IdentityAnchor tgtIdentityAnchor = (IdentityAnchor) targetAnchor;
                int targetAnchorLocation = SequenceGraphicalHelper.getAnchorAbsolutePosition(tgtIdentityAnchor, oldParentRange);
                PrecisionPoint position = BaseSlidableAnchor.parseTerminalString(tgtIdentityAnchor.getId());
                position.setPreciseY(newParentRange.getProportionalLocation(targetAnchorLocation));
                String terminal = new SlidableAnchor(null, position).getTerminal();
                tgtIdentityAnchor.setId(terminal);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getDeltaY(Edge edge, boolean source) {
        if (!move) {
            IdentityAnchor anchor;
            if (source) {
                anchor = (IdentityAnchor) edge.getSourceAnchor();
            } else {
                anchor = (IdentityAnchor) edge.getTargetAnchor();
            }
            return getDeltaY(anchor);
        } else {
            return super.getDeltaY(edge, source);
        }
    }

    private int getDeltaY(IdentityAnchor anchor) {
        double oldAnchorLocation = SequenceGraphicalHelper.getAnchorAbsolutePosition(anchor, oldParentRange);
        double newAnchorLocation = SequenceGraphicalHelper.getAnchorAbsolutePosition(anchor, newParentRange);
        return (int) (oldAnchorLocation - newAnchorLocation);
    }

    private void populate(Set<ISequenceEvent> descendants) {
        if (move) {
            descendants.addAll(new ISequenceEventQuery(parent).getAllDescendants(true));
            Iterables.addAll(movedElements, Iterables.filter(descendants, AbstractNodeEvent.class));
        } else {
            // descendants.addAll(parent.getSubEvents());
            descendants.addAll(new ISequenceEventQuery(parent).getAllDescendants(true));
            movedElements.add(parent);
        }
        // Finds compounds events of each ExecutionEditPart found in descendants
        ArrayList<ISequenceEvent> compoundEvents = new ArrayList<>();
        for (AbstractNodeEvent eep : Iterables.filter(descendants, AbstractNodeEvent.class)) {
            compoundEvents.addAll(EventEndHelper.getCompoundEvents(eep));
        }
        Iterables.addAll(descendants, compoundEvents);
        if (parent instanceof Lifeline) {
            movedElements.add(parent);
        }
    }

    private Range getNewParentRange() {
        int newLowerBound = oldParentRange.getLowerBound();
        int newUppeRBound = oldParentRange.getUpperBound();
        if (move) {
            newLowerBound += deltaY;
            newUppeRBound += deltaY;
        } else if (fromTop) {
            newLowerBound += deltaY;
        } else {
            newUppeRBound += deltaY;
        }
        return new Range(newLowerBound, newUppeRBound);
    }
}
