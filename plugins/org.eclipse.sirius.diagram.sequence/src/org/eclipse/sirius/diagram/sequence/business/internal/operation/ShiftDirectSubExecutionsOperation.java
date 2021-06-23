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

import java.text.MessageFormat;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

/**
 * This command moves all the direct sub-executions of a given ExecutionEdipart vertically. It is used when an execution
 * is resized from the top to ensure the sub-executions stay at the same absolute position instead of moving along (as
 * they are relative to the top of the parent).
 * 
 * @author pcdavid, smonnier
 */
public class ShiftDirectSubExecutionsOperation extends AbstractModelChangeOperation<Void> {
    private final int deltaY;

    private final ISequenceEvent parent;

    /**
     * Constructor.
     * 
     * @param parent
     *            the execution or lifeline whose direct sub-executions must be shifted.
     * @param deltaY
     *            the vertical amount to shift the sub-executions (in logical space).
     */
    public ShiftDirectSubExecutionsOperation(ISequenceEvent parent, int deltaY) {
        super(MessageFormat.format(Messages.ShiftDirectSubExecutionsOperation_operationName, deltaY));
        this.parent = Preconditions.checkNotNull(parent);
        this.deltaY = deltaY;
    }

    @Override
    public Void execute() {

        for (View view : Iterables.filter(Iterables.filter(parent.getNotationView().getChildren(), View.class), AbstractNodeEvent.notationPredicate())) {
            Option<AbstractNodeEvent> execution = ISequenceElementAccessor.getAbstractNodeEvent(view);
            if (execution.some()) {
                AbstractNodeEvent ise = execution.get();
                Range rg = ise.getVerticalRange();
                Range nrg = new Range(rg.getLowerBound() + deltaY, rg.getUpperBound() + deltaY);
                ise.setVerticalRange(nrg);
            }
        }
        return null;
    }

}
