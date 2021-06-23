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
package org.eclipse.sirius.diagram.sequence.business.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * A function which computes the vertical position (in absolute, normalized coordinates) of an {@link EventEnd}.
 * 
 * @author pcdavid
 */
public class VerticalPositionFunction implements Function<EventEnd, Integer> {
    /**
     * The value returned by the function to indicate an invalid input from which a position can not be determined.
     */
    public static final int INVALID_POSITION = Integer.MAX_VALUE;

    private final SequenceDDiagram diagram;

    /**
     * Constructor.
     * 
     * @param diagram
     *            the diagram on which the compute the position of the ends.
     */
    public VerticalPositionFunction(SequenceDDiagram diagram) {
        this.diagram = diagram;
    }

    /**
     * Returns the vertical position of the specified end as it appears on the diagram associated to this function, or
     * <code>INVALID_POSITION</code> if the end is invalid or is not part of the diagram.
     * 
     * @param end
     *            the end for which to compute the position.
     * @return the vertical position of the end, or <code>INVALID_POSITION</code>.
     */
    @Override
    public Integer apply(EventEnd end) {
        Integer result;
        SingleEventEnd see = null;
        EObject semanticEvent = null;
        if (end instanceof SingleEventEnd) {
            see = (SingleEventEnd) end;
        } else if (end instanceof CompoundEventEnd) {
            see = ((CompoundEventEnd) end).getEventEnds().iterator().next();
        }

        if (see != null) {
            semanticEvent = see.getSemanticEvent();
        }

        Iterable<View> eventViews = ISequenceElementAccessor.getViewsForSemanticElement(diagram, semanticEvent);

        if (Iterables.isEmpty(eventViews) && end instanceof CompoundEventEnd) {
            for (SingleEventEnd see2 : ((CompoundEventEnd) end).getEventEnds()) {
                if (see != null) {
                    semanticEvent = see2.getSemanticEvent();
                }
                eventViews = ISequenceElementAccessor.getViewsForSemanticElement(diagram, semanticEvent);
                if (!Iterables.isEmpty(eventViews)) {
                    break;
                }
            }
        }

        if (Iterables.isEmpty(eventViews)) {
            result = INVALID_POSITION;
        } else {
            result = INVALID_POSITION;
            Range range = Range.emptyRange();

            for (View potentialView : eventViews) {
                range = VerticalRangeFunction.INSTANCE.apply(potentialView);
                if (!range.isEmpty()) {
                    break;
                }
            }

            if (EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(end)) {
                result = (int) range.middleValue();
            } else if (EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(end)) {
                result = (int) range.middleValue();
            } else {
                result = (int) (see.isStart() ? range.getLowerBound() : range.getUpperBound());
            }
        }
        return result;
    }
}
