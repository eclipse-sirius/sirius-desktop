/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

/**
 * This operation is called to shift the given messages. It adjusts the GMF bendpoints of the messages to/from an
 * execution (or any of its sub-executions).
 * 
 * @author mporhel
 */
public class ShiftMessagesOperation extends AbstractModelChangeOperation<Void> {
    /**
     * Indicates if messages to shift did really move.
     */
    protected final boolean move;

    /**
     * Message to shift.
     */
    protected final Collection<Message> messagesToShift = new ArrayList<>();

    /**
     * Moved elements.
     */
    protected final Collection<ISequenceEvent> movedElements = new HashSet<>();

    /**
     * Vertical move to handle.
     */
    protected final int deltaY;

    private final boolean revert;

    /**
     * Constructor.
     * 
     * @param name
     *            name of the current Operation.
     * @param deltaY
     *            the vertical amount the execution was moved.
     * @param revert
     *            if true, revert the adjustments from source/target vectors
     * @param move
     *            if true, the messages of any of its sub-executions will be shifted. If false, the parent part was
     *            resized and only direct sub messages will be shifted
     */
    protected ShiftMessagesOperation(String name, int deltaY, boolean revert, boolean move) {
        super(name);
        this.deltaY = deltaY;
        this.revert = revert;
        this.move = move;
    }

    /**
     * Constructor.
     * 
     * @param messagesToShift
     *            name of the current Operation.
     * @param movedElements
     *            name of the current Operation.
     * @param deltaY
     *            the vertical amount the execution was moved.
     * @param revert
     *            if true, revert the adjustments from source/target vectors
     * @param move
     *            if true, the messages of any of its sub-executions will be shifted. If false, the parent part was
     *            resized and only direct sub messages will be shifted
     */
    public ShiftMessagesOperation(Collection<Message> messagesToShift, Collection<ISequenceEvent> movedElements, int deltaY, boolean revert, boolean move) {
        this(Messages.ShiftMessagesOperation_operationName, deltaY, revert, move);
        this.messagesToShift.addAll(Objects.requireNonNull(messagesToShift));
        this.movedElements.addAll(Objects.requireNonNull(movedElements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        for (Message smep : messagesToShift) {
            shiftBendpoints(smep);
        }

        return null;
    }

    private void shiftBendpoints(Message message) {
        boolean needShiftFromSrc = needShift(message, true);
        boolean needShiftFromTgt = needShift(message, false);

        Edge edge = (Edge) message.getNotationView();
        int currentSourceDeltaY = 0;
        int currentTargetDeltaY = 0;

        /*
         * If the message starts from the execution being moved (or any of its sub-executions), its sourceX/sourceY
         * vectors are still valid as they are relative to the source anchor which moved along with the execution.
         * However, the target execution/lifeline on the other side did not move, so we need to adjust the target
         * vector.
         */

        /*
         * The current message is moved by its target, so we need to adjust the source vector.
         */
        int srcShift = getDeltaY(edge, false);
        if (needShiftFromSrc) {
            currentSourceDeltaY = revert ? 0 : srcShift;
            currentTargetDeltaY = revert ? srcShift : 0;
        }
        /*
         * The current message is moved by its source, so we need to adjust the target vector.
         */
        int tgtShift = getDeltaY(edge, true);
        if (needShiftFromTgt) {
            currentTargetDeltaY = revert ? currentTargetDeltaY : tgtShift;
            currentSourceDeltaY = revert ? tgtShift : currentSourceDeltaY;
        }

        RelativeBendpoints bp = (RelativeBendpoints) edge.getBendpoints();
        List<?> oldPoints = bp.getPoints();
        List<RelativeBendpoint> newPoints = new ArrayList<>();
        for (int i = 0; i < oldPoints.size(); i++) {
            RelativeBendpoint old = (RelativeBendpoint) oldPoints.get(i);
            newPoints.add(new RelativeBendpoint(old.getSourceX(), old.getSourceY() + currentSourceDeltaY, old.getTargetX(), old.getTargetY() + currentTargetDeltaY));
        }
        bp.setPoints(newPoints);
    }

    /**
     * Check the direction of the given Message regarding the current context.
     * 
     * @param message
     *            the message to check.
     * @return true if the given message source lifeline is the actual context lifeline.
     */
    private boolean needShift(Message message, boolean source) {
        boolean movedBySrc = movedElements.contains(message.getSourceElement());
        movedBySrc = movedBySrc || message.getSourceElement() instanceof Gate g && movedElements.contains(g.getHierarchicalParent());
        boolean movedByTgt = movedElements.contains(message.getTargetElement());
        movedByTgt = movedByTgt || message.getTargetElement() instanceof Gate g && movedElements.contains(g.getHierarchicalParent());
        boolean moved = movedBySrc || movedByTgt;

        if (move && !revert) {
            moved = moved || movedElements.contains(message);
        }

        boolean shiftedEnd = source ? !movedBySrc : !movedByTgt;
        boolean reverted = revert && movedBySrc && movedByTgt;

        return moved && (shiftedEnd || reverted);
    }

    /**
     * Get deltaY for the request end of the current edge.
     * 
     * @param edge
     *            the given edge.
     * @param source
     *            the requested end.
     * @return the deltaY.
     */
    protected int getDeltaY(Edge edge, boolean source) {
        return deltaY;
    }
}
