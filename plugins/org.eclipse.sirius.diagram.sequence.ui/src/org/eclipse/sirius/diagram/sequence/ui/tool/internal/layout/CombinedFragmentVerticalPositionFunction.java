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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Function;

/**
 * A function which computes the vertical position (in absolute, normalized
 * coordinates) of an
 * {@link org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart}
 * .
 * 
 * @author smonnier
 */
public class CombinedFragmentVerticalPositionFunction implements Function<IGraphicalEditPart, Integer> {
    /**
     * The value returned by the function to indicate an invalid input from
     * which a position can not be determined.
     */
    public static final int INVALID_POSITION = 0;

    /**
     * Constructor.
     * 
     */
    public CombinedFragmentVerticalPositionFunction() {
    }

    /**
     * Returns the vertical position of the specified InteractionUseEditPart as
     * it appears on the diagram associated to this function.
     * 
     * @param graphicalEditPart
     *            the CombinedFragmentEditPart for which to compute the
     *            position.
     * @return the vertical position of the end, or
     *         <code>INVALID_POSITION</code>.
     */
    public Integer apply(IGraphicalEditPart graphicalEditPart) {
        Option<CombinedFragment> combinedFragment = ISequenceElementAccessor.getCombinedFragment(graphicalEditPart.getNotationView());
        if (combinedFragment.some()) {
            return combinedFragment.get().getVerticalRange().getLowerBound();
        } else {
            return INVALID_POSITION;
        }
    }
}
