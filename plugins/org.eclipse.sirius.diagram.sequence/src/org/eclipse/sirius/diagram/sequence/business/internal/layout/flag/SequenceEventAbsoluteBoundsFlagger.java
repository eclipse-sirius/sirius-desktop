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

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;

import com.google.common.collect.Lists;

/**
 * Helper to compute and attach absolute bounds flag for sequence events.
 * 
 * @author mporhel
 */
public class SequenceEventAbsoluteBoundsFlagger extends AbstractSequenceAbsoluteBoundsFlagger {

    private ISequenceEvent ise;

    /**
     * Constructor.
     * 
     * @param ise
     *            the sequence event to flag.
     */
    public SequenceEventAbsoluteBoundsFlagger(ISequenceEvent ise) {
        this.ise = ise;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<ISequenceElement> getEventsToFlag() {
        List<ISequenceElement> eventsToFlag = Lists.newArrayList();
        if (ise != null) {
            eventsToFlag.add(ise);
        }
        return eventsToFlag;
    }
}
