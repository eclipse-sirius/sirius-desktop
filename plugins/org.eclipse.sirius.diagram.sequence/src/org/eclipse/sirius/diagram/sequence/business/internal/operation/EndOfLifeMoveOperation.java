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
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

import com.google.common.base.Preconditions;

/**
 * Modifies the vertical range of a lifeline edit part to move the end of life
 * edit part.
 * 
 * @author mporhel, smonnier
 */
public class EndOfLifeMoveOperation extends AbstractModelChangeOperation<Void> {
    private final Lifeline lifeline;

    private final int rangeDeltaWidth;

    /**
     * Constructor.
     * 
     * @param lifeline
     *            the lifeline whose range to modify.
     * @param rangeDeltaWidth
     *            the rangeDeltaWidth.
     */
    public EndOfLifeMoveOperation(Lifeline lifeline, int rangeDeltaWidth) {
        super("Move EOL");
        this.lifeline = Preconditions.checkNotNull(lifeline);
        this.rangeDeltaWidth = rangeDeltaWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        Range movedRange = RangeHelper.verticalRange(lifeline.getProperLogicalBounds());
        lifeline.setVerticalRange(new Range(movedRange.getLowerBound(), movedRange.getUpperBound() + rangeDeltaWidth));
        return null;
    }
}
