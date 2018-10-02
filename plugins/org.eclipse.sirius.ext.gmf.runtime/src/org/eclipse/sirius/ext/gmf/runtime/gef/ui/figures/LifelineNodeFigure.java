/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

/**
 * This specific figure overrides {@link SequenceNodeFigure} to draw a dash line
 * over.
 * 
 * @author smonnier
 * 
 */
public class LifelineNodeFigure extends SequenceNodeFigure {
    
    /** Defines the visual appearance of lifelines. */
    public static final int[] LIFELINE_DASH_STYLE = new int[] { 10, 10 };

    /**
     * Create a new {@link LifelineNodeFigure}.
     * 
     * @param width
     *            the width.
     * @param height
     *            the height.
     * @param anchorProvider
     *            the anchor provider.
     */
    public LifelineNodeFigure(int width, int height, AnchorProvider anchorProvider) {
        super(width, height, anchorProvider);

        OneLineMarginBorder oneLineBorder = new OneLineMarginBorder(PositionConstants.CENTER);
        oneLineBorder.setStyle(Graphics.LINE_CUSTOM);
        oneLineBorder.setLineDash(LIFELINE_DASH_STYLE);
        setBorder(oneLineBorder);
    }

    /**
     * This method is overridden to avoid drawing the border.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void paintClientArea(Graphics graphics) {
        // Do nothing. This way the style edit part of the node will not be
        // drawn. The lifeline is displayed using the OneLineDashborder, with
        // middle position.
    }
}
