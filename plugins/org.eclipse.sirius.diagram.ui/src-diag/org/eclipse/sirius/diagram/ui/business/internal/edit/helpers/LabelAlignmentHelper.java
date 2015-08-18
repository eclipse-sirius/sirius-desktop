/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.edit.helpers;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.sirius.viewpoint.LabelAlignment;

/**
 * Utility class to retrieve diagram elements' {@link LabelAlignment} and
 * convert it into the appropriate Draw2D constants to implement the alignment.
 * 
 * @author pcdavid
 */
public final class LabelAlignmentHelper {
    private LabelAlignmentHelper() {
        // Prevent instantiation.
    }

    /**
     * Converts a {@link LabelAlignment} style value into the equivalent Draw2D
     * {@link PositionConstants} value, which can be used in
     * {@link org.eclipse.sirius.common.ui.tools.api.draw2d.ui.figures.SiriusWrapLabel}
     * s.
     * 
     * @param alignment
     *            the {@link LabelAlignment} to convert
     * @return the equivalent {@link PositionConstants} value
     */
    public static int getAsPositionConstant(final LabelAlignment alignment) {
        final int alignmentValue;
        switch (alignment) {
        case CENTER:
            alignmentValue = PositionConstants.CENTER;
            break;
        case LEFT:
            alignmentValue = PositionConstants.LEFT;
            break;
        case RIGHT:
            alignmentValue = PositionConstants.RIGHT;
            break;
        default:
            alignmentValue = PositionConstants.CENTER;
            break;
        }
        return alignmentValue;
    }

    /**
     * Converts a {@link LabelAlignment} style value into the equivalent
     * alignment constant to use in a
     * {@link org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout}
     * .
     * 
     * @param alignment
     *            the {@link LabelAlignment} to convert
     * @return the equivalent
     *         {@link org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout}
     *         minor alignment value
     */
    public static int getAsCTLMinorAlignment(final LabelAlignment alignment) {
        final int alignmentValue;
        switch (alignment) {
        case CENTER:
            alignmentValue = ToolbarLayout.ALIGN_CENTER;
            break;
        case LEFT:
            alignmentValue = ToolbarLayout.ALIGN_TOPLEFT;
            break;
        case RIGHT:
            alignmentValue = ToolbarLayout.ALIGN_BOTTOMRIGHT;
            break;
        default:
            alignmentValue = ToolbarLayout.ALIGN_CENTER;
            break;
        }
        return alignmentValue;
    }
}
