/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal;

import org.eclipse.gmf.runtime.notation.View;

import com.google.common.base.Function;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.util.Range;

/**
 * Computes the absolute vertical range occupied by an element of a sequence
 * diagram, from its GMF View.
 * 
 * @author pcdavid
 */
public enum VerticalRangeFunction implements Function<View, Range> {
    /**
     * The default instance.
     */
    INSTANCE;

    /**
     * {@inheritDoc}
     */
    public Range apply(View view) {
        Range result = Range.emptyRange();
        Option<ISequenceEvent> iSequenceEvent = ISequenceElementAccessor.getISequenceEvent(view);
        if (iSequenceEvent.some()) {
            result = iSequenceEvent.get().getVerticalRange();
        }
        return result;
    }
}
