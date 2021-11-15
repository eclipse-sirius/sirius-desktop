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

import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;

/**
 * This class is responsible to check whether a resize request on a Combined Fragment should be accepted (i.e. it would
 * produce a well-formed diagram). While doing the validation, it also stores all the relevant information required to
 * actually perform the resize properly.
 * 
 * @author smonnier
 */
public class CombinedFragmentMoveValidator extends AbstractInteractionFrameValidator {

    /**
     * Constructor.
     * 
     * @param combinedFragment
     *            the CombinedFragment which will be moved.
     * @param requestQuery
     *            a query on the move request targeting the CombinedFragment.
     */
    public CombinedFragmentMoveValidator(CombinedFragment combinedFragment, RequestQuery requestQuery) {
        super(combinedFragment, requestQuery);
        Preconditions.checkArgument(requestQuery.isMove());
        defaultFrameHeight = LayoutConstants.DEFAULT_COMBINED_FRAGMENT_HEIGHT;
    }

    /**
     * Overridden to get final parents.
     * 
     * {@inheritDoc}
     */
    @Override
    protected Collection<ISequenceEvent> getFinalParents(Collection<Lifeline> coveredLifelines) {
        // Possibility to handle "reparent" and insertion"
        Collection<ISequenceEvent> finalParents = new LinkedHashSet<>();
        Range insertionPoint = new Range(finalRange.getLowerBound(), finalRange.getLowerBound());
        for (Lifeline lifeline : coveredLifelines) {
            EventFinder finder = new EventFinder(lifeline);
            finder.setEventsToIgnore(Predicates.in(Collections.<ISequenceEvent> singletonList(frame)));
            ISequenceEvent localParent = finder.findMostSpecificEvent(insertionPoint);
            if (localParent != null) {
                finalParents.add(localParent);
            }
        }
        return finalParents;
    }

    /**
     * Auto-expand is allowed for moves.
     * <p>
     * {@inheritDoc}
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
        if (getRequestQuery().isMove()) { // getLogicalDelta().y > 0) {
            expansionZone = finalRange;
        }
        return expansionZone;
    }
}
