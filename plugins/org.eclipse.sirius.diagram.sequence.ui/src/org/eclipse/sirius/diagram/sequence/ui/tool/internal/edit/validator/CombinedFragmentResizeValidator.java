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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.Collection;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;

import com.google.common.base.Preconditions;

/**
 * This class is responsible to check whether a resize request on a Combined Fragment should be accepted (i.e. it would
 * produce a well-formed diagram). While doing the validation, it also stores all the relevant information required to
 * actually perform the resize properly.
 * 
 * @author smonnier
 */
public class CombinedFragmentResizeValidator extends AbstractInteractionFrameValidator {

    /**
     * The impacted Operand on Combined Fragment resize.
     */
    private Operand impactedOperand;

    /**
     * The validator of the impacted Operand.
     */
    private OperandResizeValidator impactedOperandResizeValidator;

    /**
     * Constructor.
     * 
     * @param combinedFragment
     *            the CombinedFragment which will be resized.
     * @param requestQuery
     *            a query on the resize request targeting the CombinedFragment.
     */
    protected CombinedFragmentResizeValidator(CombinedFragment combinedFragment, RequestQuery requestQuery) {
        super(combinedFragment, requestQuery);
        Preconditions.checkArgument(requestQuery.isResizeFromTop() || requestQuery.isResizeFromBottom());
        defaultFrameHeight = LayoutConstants.DEFAULT_COMBINED_FRAGMENT_HEIGHT;
        if (requestQuery.isResizeFromTop()) {
            impactedOperand = combinedFragment.getFirstOperand();
        } else if (requestQuery.isResizeFromBottom()) {
            impactedOperand = combinedFragment.getLastOperand();
        }
        // A combined fragment always have at least one operand
        Preconditions.checkArgument(impactedOperand != null);
        impactedOperandResizeValidator = new OperandResizeValidator(impactedOperand, requestQuery);
    }

    @Override
    protected Collection<ISequenceEvent> getFinalParents() {
        return frame.computeParentEvents();
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to trigger the impacted operand resize validation.
     */
    @Override
    protected void validate() {
        super.validate();
        if (!(getRequestQuery().isResizeFromBottom() && getRequestQuery().getLogicalDelta().height > 0)) {
            impactedOperandResizeValidator.validate();
            valid = valid && impactedOperandResizeValidator.isValid();
        }
    }

    /**
     * Resize do not autorize auto expand.
     * 
     * @return false;
     */
    @Override
    protected boolean canExpand() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Range computeExpansionZone() {
        Range expansionZone = Range.emptyRange();
        if (getRequestQuery().isResizeFromBottom() && getRequestQuery().getLogicalDelta().height > 0) {
            expansionZone = new Range(initialRange.getUpperBound(), finalRange.getUpperBound());
        }
        return expansionZone;
    }
}
