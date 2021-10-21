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
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

import com.google.common.base.Preconditions;

/**
 * Modifies the vertical range of an event on a sequence diagram.
 * 
 * @author pcdavid, smonnier
 */
public class SetVerticalRangeOperation extends AbstractModelChangeOperation<Void> {
    private final ISequenceEvent ise;

    private final Range newRange;

    /**
     * Constructor.
     * 
     * @param ise
     *            the event whose range to modify.
     * @param newRange
     *            the new vertical range for the event.
     */
    public SetVerticalRangeOperation(ISequenceEvent ise, Range newRange) {
        super(Messages.SetVerticalRangeOperation_operationName);
        this.ise = Objects.requireNonNull(ise);
        this.newRange = Objects.requireNonNull(newRange);
        Preconditions.checkArgument(!newRange.isEmpty());
    }

    @Override
    public Void execute() {
        if (ise.getSemanticTargetElement().some()) {
            ise.setVerticalRange(newRange);
        }
        return null;
    }
}
