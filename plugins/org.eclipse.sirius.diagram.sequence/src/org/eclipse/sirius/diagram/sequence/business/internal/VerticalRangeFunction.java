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

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Function;

/**
 * Computes the absolute vertical range occupied by an element of a sequence diagram, from its GMF View.
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
    @Override
    public Range apply(View view) {
        Range result = Range.emptyRange();
        Option<ISequenceEvent> iSequenceEvent = ISequenceElementAccessor.getISequenceEvent(view);
        if (iSequenceEvent.some()) {
            result = iSequenceEvent.get().getVerticalRange();
        }
        return result;
    }
}
