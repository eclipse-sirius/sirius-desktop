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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;

/**
 * This class is responsible to check whether a resize request on an interaction use should be accepted (i.e. it would
 * produce a well-formed diagram). While doing the validation, it also stores all the relevant information required to
 * actually perform the resize properly.
 * 
 * @author mporhel
 */
public class InteractionUseMoveValidator extends AbstractInteractionFrameValidator {

    /**
     * Constructor.
     * 
     * @param interactionUse
     *            the interactionUse which will be moved.
     * @param requestQuery
     *            a query on the move request targeting the execution.
     */
    public InteractionUseMoveValidator(InteractionUse interactionUse, RequestQuery requestQuery) {
        super(interactionUse, requestQuery);
        Preconditions.checkArgument(requestQuery.isMove());
        defaultFrameHeight = LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<ISequenceEvent> getFinalParents() {
        // Possibility to handle "reparent" and insertion"
        Collection<ISequenceEvent> finalParents = new LinkedHashSet<>();
        Range insertionPoint = new Range(finalRange.getLowerBound(), finalRange.getLowerBound());
        Collection<Lifeline> coveredLifelines = frame.computeCoveredLifelines();
        for (Lifeline lifeline : coveredLifelines) {
            EventFinder finder = new EventFinder(lifeline);
            finder.setEventsToIgnore(Predicates.in(Collections.<ISequenceEvent> singletonList(frame)));
            ISequenceEvent localParent = finder.findMostSpecificEvent(insertionPoint);
            if (localParent != null) {
                finalParents.add(localParent);
            }
        }
        return finalParents;
        // return frame.computeParentEvents();
    }

    /**
     * {@inheritDoc}
     * 
     * expand to handle insertion.
     */
    @Override
    protected boolean canExpand() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Range computeExpansionZone() {
        Range expansionZone = Range.emptyRange();
        RequestQuery requestQuery = getRequestQuery();
        if (requestQuery.isMove()) { // requestQuery.getLogicalDelta().y > 0) {
            expansionZone = finalRange;
        }
        return expansionZone;
    }
}
