/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;

import com.google.common.base.Function;

/**
 * Abstract validator providing common services for user interactions.
 * 
 * @author mporhel
 */
public abstract class AbstractSequenceInteractionValidator {

    /** Last validator. */
    protected static ISEComplexMoveValidator lastValidator;

    /** Last request. */
    protected static ChangeBoundsRequest lastRequest;

    /** Keep the value of the validation. */
    protected boolean valid = true;

    /** {@link RequestQuery} for the current Request. */
    protected RequestQuery request;

    /** The expansionZine. */
    protected Range expansionZone = Range.emptyRange();

    /** {@link ISequenceEvent} in errors. */
    protected Set<ISequenceEvent> eventInError = new HashSet<ISequenceEvent>();

    /** invalid positions. */
    protected Set<Integer> invalidPositions = new HashSet<Integer>();

    /** invalid ranges. */
    protected Set<Range> invalidRanges = new HashSet<Range>();

    /** {@link ISequenceEvent}s moved. */
    protected final Collection<ISequenceEvent> movedElements = new ArrayList<ISequenceEvent>();

    /** {@link ISequenceEvent}s moved. */
    protected Collection<Range> createdElements = new ArrayList<Range>();

    /** startReflexiveMessageToResize. */
    protected final Collection<Message> startReflexiveMessageToResize = new HashSet<Message>();

    /** endReflexiveMessageToResize. */
    protected final Collection<Message> endReflexiveMessageToResize = new HashSet<Message>();

    /** validation done ? */
    protected boolean validationDone;

    /** initialized ? */
    protected boolean initialized;

    /**
     * Constructor.
     * 
     * @param request
     *            a request query
     */
    public AbstractSequenceInteractionValidator(RequestQuery request) {
        this.request = request;
    }

    /**
     * Get the {@link SequenceDiagram}.
     * 
     * @return the {@link SequenceDiagram}
     */
    public abstract SequenceDiagram getDiagram();

    /**
     * Get the {@link Function} which give the {@link Range} of a {@link ISequenceEvent}.
     * 
     * @return the {@link Range} of a {@link ISequenceEvent}
     */
    public abstract Function<ISequenceEvent, Range> getRangeFunction();

    /**
     * Do the validation of the request.
     */
    protected abstract void doValidation();

    /**
     * Return the validation status. Validate the request result in the first call only.
     * 
     * @return the validation status.
     */
    public final boolean isValid() {
        validate();
        return valid;
    }

    /**
     * Performs all the computations required to validate the resizing, and stores any important information which will
     * be useful to actually execute the move if it is valid, like for example avoid contact with siblings or handle
     * reconnection.
     */
    public final void validate() {
        if (!validationDone) {
            doValidation();
            validationDone = true;
        }
    }

    public Collection<ISequenceEvent> getMovedElements() {
        return movedElements;
    }

    public Collection<Range> getCreatedElements() {
        return createdElements;
    }

    public Collection<Message> getResizedStartMessages() {
        return startReflexiveMessageToResize;
    }

    public Collection<Message> getResizedEndMessages() {
        return endReflexiveMessageToResize;
    }

    public Range getExpansionZone() {
        return expansionZone;
    }

    public Collection<ISequenceEvent> getEventsInError() {
        return eventInError;
    }

    public Collection<Integer> getInvalidPostions() {
        return invalidPositions;
    }

    public Collection<Range> getInvalidRanges() {
        return invalidRanges;
    }
}
