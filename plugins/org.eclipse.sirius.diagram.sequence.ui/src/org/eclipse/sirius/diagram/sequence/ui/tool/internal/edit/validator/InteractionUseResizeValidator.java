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
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;

/**
 * This class is responsible to check whether a resize request on an interaction use should be accepted (i.e. it would
 * produce a well-formed diagram). While doing the validation, it also stores all the relevant information required to
 * actually perform the resize properly.
 * 
 * @author mporhel
 */
public class InteractionUseResizeValidator extends AbstractInteractionFrameValidator {
    /**
     * Constructor.
     * 
     * @param interactionUse
     *            the interactionUse which will be resized.
     * @param requestQuery
     *            a query on the resize request targeting the execution.
     */
    protected InteractionUseResizeValidator(InteractionUse interactionUse, RequestQuery requestQuery) {
        super(interactionUse, requestQuery);
        defaultFrameHeight = LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validate() {
        if (!(getRequestQuery().isResizeFromTop() || getRequestQuery().isResizeFromBottom())) {
            valid = false;
        } else {
            super.validate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<ISequenceEvent> getFinalParents() {
        return frame.computeParentEvents();
    }

    /**
     * Resize do not authorize auto expand.
     * 
     * @return true;
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
