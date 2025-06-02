/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.layout.flag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;

import com.google.common.collect.Iterables;

/**
 * Helper to compute and attach absolute bounds flag for sequence events.
 * 
 * @author mporhel
 */
public class SequenceDiagramAbsoluteBoundsFlagger extends AbstractSequenceAbsoluteBoundsFlagger {

    private SequenceDiagram diagram;

    /**
     * Constructor.
     * 
     * @param sequenceDiagram
     *            the sequence diagram to flag.
     */
    public SequenceDiagramAbsoluteBoundsFlagger(SequenceDiagram sequenceDiagram) {
        this.diagram = sequenceDiagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<ISequenceElement> getEventsToFlag() {
        List<ISequenceElement> eventsToFlag = new ArrayList<>();
        if (diagram != null) {
            Iterables.addAll(eventsToFlag, diagram.getAllDelimitedSequenceEvents());
            Iterables.addAll(eventsToFlag, diagram.getAllLostMessageEnds());
            Iterables.addAll(eventsToFlag, diagram.getAllInstanceRoles());
            Iterables.addAll(eventsToFlag, diagram.getAllGates());
        }
        return eventsToFlag;
    }
}
