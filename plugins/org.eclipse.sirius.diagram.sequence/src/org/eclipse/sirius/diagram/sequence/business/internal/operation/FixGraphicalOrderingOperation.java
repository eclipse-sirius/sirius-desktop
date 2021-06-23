/*******************************************************************************
 * Copyright (c) 2013, 2021 THALES GLOBAL SERVICES and others.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

/**
 * An operation to remove the invalid ends from the graphical ordering of a sequence diagram.
 * 
 * @author pcdavid
 */
public final class FixGraphicalOrderingOperation extends AbstractModelChangeOperation<Void> {
    private final SequenceDDiagram diagram;

    /**
     * Constructor.
     * 
     * @param diagram
     *            the diagram whos graphical ordering to fix.
     */
    public FixGraphicalOrderingOperation(SequenceDDiagram diagram) {
        super(Messages.FixGraphicalOrderingOperation_operationName);
        this.diagram = diagram;
    }

    @Override
    public Void execute() {
        EventEndsOrdering graphical = diagram.getGraphicalOrdering();
        List<EventEnd> invalids = findInvalidEnds(graphical);
        removeEnds(graphical, invalids);
        return null;
    }

    private List<EventEnd> findInvalidEnds(EventEndsOrdering graphical) {
        List<EventEnd> invalids = new ArrayList<>();
        for (EventEnd end : graphical.getEventEnds()) {
            if (!isValidEnd(end)) {
                invalids.add(end);
            }
        }
        return invalids;
    }

    private boolean isValidEnd(EventEnd end) {
        return end.getSemanticEnd() != null && end.getSemanticEnd().eContainer() != null;
    }

    private void removeEnds(EventEndsOrdering graphical, List<EventEnd> invalids) {
        for (EventEnd invalidEnd : invalids) {
            graphical.getEventEnds().remove(invalidEnd);
        }
    }

}
