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
        }
        return eventsToFlag;
    }
}
