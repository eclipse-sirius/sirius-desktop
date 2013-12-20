/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation;

import java.util.Collection;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;

/**
 * This operation is called to shift the given messages. It adjusts the GMF
 * bendpoints of the messages to/from an execution (or any of its
 * sub-executions).
 * 
 * @author mporhel
 */
public class ResizeMessagesOperation extends ShiftMessagesOperation {

    private int resizeDelta;

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
     *            if true, the messages of any of its sub-executions will be
     *            shifted. If false, the parent part was resized and only direct
     *            sub messages will be shifted
     * @param resizeDelta
     *            the resize delta
     */
    public ResizeMessagesOperation(Collection<Message> messagesToShift, Collection<ISequenceEvent> movedElements, int deltaY, boolean revert, boolean move, int resizeDelta) {
        super(messagesToShift, movedElements, deltaY, revert, move);
        // this.resizeDelta = resizeDelta;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ShiftMessagesOperation#getDeltaY(org.eclipse.gmf.runtime.notation.Edge,
     *      boolean)
     */
    @Override
    protected int getDeltaY(Edge edge, boolean source) {
        return super.getDeltaY(edge, source) + resizeDelta;
    }

}
