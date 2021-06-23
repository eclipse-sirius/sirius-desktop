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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * This class is responsible to check whether a request on an operand should be accepted (i.e. it would produce a
 * well-formed diagram). While doing the validation, it also stores all the relevant information required to actually
 * perform the interaction properly.
 * 
 * @author smonnier
 */
public abstract class AbstractOperandValidator {

    /**
     * Allow to query the request.
     */
    protected final RequestQuery requestQuery;

    /**
     * Current Operand.
     */
    protected final Operand currentOperand;

    /**
     * Sibling Operand that will have the opposite resize.
     */
    protected Option<Operand> siblingOperandOption = Options.newNone();

    private Range initialOperandRange;

    private Range finalOperandRange;

    private Range initialSiblingOperandRange;

    private Range finalSiblingOperandRange;

    private boolean valid = true;

    private boolean initialized;

    /**
     * Constructor.
     * 
     * @param operand
     *            the Operand which will be resized.
     * @param requestQuery
     *            a query on the resize request targeting the execution.
     */
    public AbstractOperandValidator(Operand operand, RequestQuery requestQuery) {
        this.currentOperand = operand;
        this.requestQuery = requestQuery;
        this.valid = false;

        if (requestQuery.isResizeFromTop()) {
            siblingOperandOption = operand.getPreviousOperand();
        } else if (requestQuery.isResizeFromBottom()) {
            siblingOperandOption = operand.getFollowingOperand();
        }
    }

    /**
     * Return the validation status. Validate the request result in the first call only.
     * 
     * @return the validation status.
     */
    public final boolean isValid() {
        if (!initialized) {
            validate();
            initialized = true;
        }
        return valid;
    }

    public Range getFinalRange() {
        return finalOperandRange;
    }

    /**
     * Performs all the computations required to validate the resizing, and stores any important information which will
     * be useful to actually execute the resize if it is valid, like for example avoid contact with siblings.
     */
    protected void validate() {
        valid = checkAndComputeRanges();

        valid = valid && checkContainedISequenceEvent();
    }

    /**
     * Computes, checks and stores the initial and final range of the operand if the resize is performed.
     */
    private boolean checkAndComputeRanges() {
        boolean result = true;
        // Proper range
        initialOperandRange = currentOperand.getVerticalRange();
        Rectangle newBounds = getResizedBounds(new Rectangle(0, initialOperandRange.getLowerBound(), 0, initialOperandRange.width()));

        if (newBounds.height < LayoutConstants.DEFAULT_OPERAND_HEIGHT) {
            result = false;
            finalOperandRange = RangeHelper.verticalRange(newBounds);
        } else {
            // The current operand new range is valid, we can check the sibling
            // operand
            if (siblingOperandOption.some()) {
                Operand siblingOperand = siblingOperandOption.get();
                initialSiblingOperandRange = siblingOperand.getVerticalRange();
                Rectangle siblingOperandNewBounds = getInverseResizedBounds(new Rectangle(0, initialSiblingOperandRange.getLowerBound(), 0, initialSiblingOperandRange.width()));
                if (siblingOperandNewBounds.height < LayoutConstants.DEFAULT_OPERAND_HEIGHT) {
                    result = false;
                } else {
                    // The minimum heights are validated, we can save the final
                    // ranges
                    finalSiblingOperandRange = RangeHelper.verticalRange(siblingOperandNewBounds);
                }
            }
            finalOperandRange = RangeHelper.verticalRange(newBounds);
        }
        return result;
    }

    private boolean checkContainedISequenceEvent() {
        boolean result = finalOperandRange.includes(currentOperand.getOccupiedRange().grown(LayoutConstants.EXECUTION_CHILDREN_MARGIN));
        if (siblingOperandOption.some()) {
            result = result && finalSiblingOperandRange.includes(siblingOperandOption.get().getOccupiedRange().grown(LayoutConstants.EXECUTION_CHILDREN_MARGIN));
        }
        return result;
    }

    private Rectangle getResizedBounds(Rectangle bounds) {
        return requestQuery.getLogicalTransformedRectangle(bounds);
    }

    private Rectangle getInverseResizedBounds(Rectangle bounds) {
        Rectangle logicalDelta = requestQuery.getLogicalDelta();
        Point moveDelta = new Point();
        Dimension sizeDelta = logicalDelta.getSize().getCopy().getNegated();
        if (requestQuery.isResizeFromBottom()) {
            Dimension size = logicalDelta.getSize().getCopy();
            moveDelta = new Point(size.width, size.height);
        }
        return bounds.getCopy().translate(moveDelta).resize(sizeDelta);
    }

    public RequestQuery getRequestQuery() {
        return requestQuery;
    }
}
