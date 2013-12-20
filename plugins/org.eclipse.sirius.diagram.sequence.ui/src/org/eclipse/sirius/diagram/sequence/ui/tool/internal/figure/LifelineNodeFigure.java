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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.ui.tools.api.figure.OneLineMarginBorder;
import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.AnchorProvider;

/**
 * This specific figure overrides {@link SequenceNodeFigure} to draw a dash line
 * over.
 * 
 * @author smonnier
 * 
 */
public class LifelineNodeFigure extends SequenceNodeFigure {

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
        oneLineBorder.setLineDash(LayoutConstants.LIFELINE_DASH_STYLE);
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
