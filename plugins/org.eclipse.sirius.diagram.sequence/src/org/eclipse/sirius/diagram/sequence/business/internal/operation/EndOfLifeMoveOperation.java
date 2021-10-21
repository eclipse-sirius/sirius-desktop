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

import java.util.Objects;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

/**
 * Modifies the vertical range of a lifeline edit part to move the end of life edit part.
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
        super(Messages.EndOfLifeMoveOperation_operationName);
        this.lifeline = Objects.requireNonNull(lifeline);
        this.rangeDeltaWidth = rangeDeltaWidth;
    }

    @Override
    public Void execute() {
        Range movedRange = RangeHelper.verticalRange(lifeline.getProperLogicalBounds());
        lifeline.setVerticalRange(new Range(movedRange.getLowerBound(), movedRange.getUpperBound() + rangeDeltaWidth));
        return null;
    }
}
