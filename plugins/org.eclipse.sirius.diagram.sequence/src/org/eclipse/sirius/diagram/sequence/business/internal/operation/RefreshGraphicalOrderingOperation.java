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
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.VerticalPositionFunction;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.RefreshOrderingHelper;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * An operation to re-compute the graphical ordering in which events appear on a diagram.
 * 
 * <pre>
 * GMF View Model ------> GraphicalMessageOrdering
 * </pre>
 * 
 * @author pcdavid, smonnier
 */
public class RefreshGraphicalOrderingOperation extends AbstractModelChangeOperation<Boolean> {
    private final SequenceDDiagram sequenceDiagram;

    /**
     * Creates a command which updates the graphical ordering of events store in the model using the latest available
     * graphical informations.
     * 
     * @param sequenceDiagram
     *            the diagram whose graphical ordering should be refreshed.
     */
    public RefreshGraphicalOrderingOperation(SequenceDiagram sequenceDiagram) {
        super(Messages.RefreshGraphicalOrderingOperation_operationName);
        this.sequenceDiagram = sequenceDiagram.getSequenceDDiagram();
    }

    @Override
    public Boolean execute() {
        EventEndsOrdering graphicalOrdering = sequenceDiagram.getGraphicalOrdering();
        if (graphicalOrdering != null) {
            return refreshGlobalOrdering(graphicalOrdering);
        }
        return false;
    }

    private boolean refreshGlobalOrdering(EventEndsOrdering graphicalOrdering) {
        if (graphicalOrdering.eContainer() instanceof SequenceDDiagram) {
            return refreshGlobalOrdering(graphicalOrdering, new CustomVerticalPositionFunction(sequenceDiagram));
        }
        return false;
    }

    /**
     * Recomputes the graphical ordering of events.
     * 
     * @param sequenceDiagram
     *            the diagram.
     * 
     * @param verticalPosition
     *            the function to use to obtain the vertical position of the event ends.
     */
    private boolean refreshGlobalOrdering(EventEndsOrdering graphicalOrdering, VerticalPositionFunction verticalPosition) {
        final LoadingCache<EventEnd, Integer> positions = CacheBuilder.newBuilder().build(CacheLoader.from(verticalPosition));
        Predicate<EventEnd> isValidEnd = new Predicate<EventEnd>() {
            @Override
            public boolean apply(EventEnd input) {
                try {
                    Integer pos = positions.get(input);
                    return pos != VerticalPositionFunction.INVALID_POSITION && pos != -VerticalPositionFunction.INVALID_POSITION;
                } catch (ExecutionException e) {
                    return false;
                }
            }
        };
        List<EventEnd> allEnds = Lists.newArrayList(Iterables.filter(getAllEventEnds(), isValidEnd));
        Collections.sort(allEnds, Ordering.natural().onResultOf(new Function<EventEnd, Integer>() {
            @Override
            public Integer apply(EventEnd input) {
                try {
                    return positions.get(input);
                } catch (ExecutionException e) {
                    return VerticalPositionFunction.INVALID_POSITION;
                }
            }
        }));
        return RefreshOrderingHelper.updateIfNeeded(graphicalOrdering.getEventEnds(), allEnds);
    }

    /**
     * Returns all the event ends of the current Sequence diagram.
     * 
     * The default implementation does the computation on each call, subclasses may override this method to change this
     * behavior.
     * 
     * @return an Iterable with all event ends.
     */
    protected Iterable<? extends EventEnd> getAllEventEnds() {
        return RefreshOrderingHelper.getAllEventEnds(sequenceDiagram);
    }

    /**
     * Custom vertical function which do not return the real location of an event end but allow to correctly order event
     * end from logically instantaneous ISequenceEvent.
     * 
     * @author mPorhel
     */
    private static class CustomVerticalPositionFunction extends VerticalPositionFunction {

        /**
         * Constructor.
         */
        CustomVerticalPositionFunction(SequenceDDiagram diagram) {
            super(diagram);
        }

        @Override
        public Integer apply(EventEnd end) {
            Integer customPos = super.apply(end);
            if (customPos != INVALID_POSITION || customPos != -INVALID_POSITION) {
                /*
                 * Simulates a 10x zoom so that we can adjust the SingleEventEnds position to get the proper ordering.
                 * This manipulation gives us the right ordering, but the actual Integer values returned by the function
                 * should not be used for anything else than comparing relative positions.
                 */
                customPos *= 10;

                if (end instanceof SingleEventEnd) {
                    SingleEventEnd see = (SingleEventEnd) end;
                    if (see.isStart()) {
                        customPos -= 1;
                    } else {
                        customPos += 1;
                    }

                }
            }
            return customPos;
        }
    }
}
