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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;

import com.google.common.base.Preconditions;

/**
 * This class is responsible to check whether a resize request on an operand
 * should be accepted (i.e. it would produce a well-formed diagram). While doing
 * the validation, it also stores all the relevant information required to
 * actually perform the resize properly.
 * 
 * @author smonnier
 */
public class OperandResizeValidator extends AbstractOperandValidator {

    /**
     * Constructor.
     * 
     * @param operand
     *            the operand which will be resized.
     * @param requestQuery
     *            a query on the resize request targeting the execution.
     */
    public OperandResizeValidator(Operand operand, RequestQuery requestQuery) {
        super(operand, requestQuery);
        Preconditions.checkArgument(requestQuery.isResizeFromTop() || requestQuery.isResizeFromBottom());
    }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // protected Collection<? extends ISequenceEvent> getFinalParents() {
    // return operand.computeParentEvents();
    // }

}
