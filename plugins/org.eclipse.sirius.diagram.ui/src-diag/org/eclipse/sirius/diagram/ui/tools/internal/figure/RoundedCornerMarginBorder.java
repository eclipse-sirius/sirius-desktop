/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.sirius.diagram.ui.tools.api.figure.OneLineMarginBorder;

/**
 * Specific {@link OneLineMarginBorder} with rounded corner capabilities.
 * 
 * @author mporhel
 */
public class RoundedCornerMarginBorder extends OneLineMarginBorder {

    private final Dimension corner = new Dimension(0, 0);

    /**
     * Constructor.
     * 
     * @param position
     *            The position to set.
     */
    public RoundedCornerMarginBorder(int position) {
        super(position);
    }

    /**
     * Paints the border based on the inputs given.
     * 
     * @param figure
     *            <code>IFigure</code> for which this is the border.
     * @param graphics
     *            <code>Graphics</code> handle for drawing the border.
     * @param insets
     *            Space to be taken up by this border.
     */
    @Override
    public void paint(IFigure figure, Graphics graphics, Insets insets) {
        int hRadius = corner.width / 2;
        int vRadius = corner.height / 2;
        Insets cornerAwareInsets = insets;
        switch (getPosition()) {
        case PositionConstants.TOP:
        case PositionConstants.BOTTOM:
            cornerAwareInsets = insets.getAdded(new Insets(0, hRadius, 0, hRadius));
            break;
        case PositionConstants.LEFT:
        case PositionConstants.RIGHT:
            cornerAwareInsets = insets.getAdded(new Insets(vRadius, 0, vRadius, 0));
            break;
        default:
            break;
        }
        super.paint(figure, graphics, cornerAwareInsets);

        int one = MapModeUtil.getMapMode(figure).DPtoLP(1);
        int widthInDP = getWidth() / one;
        int halfWidthInLP = MapModeUtil.getMapMode(figure).DPtoLP(widthInDP / 2);

        if (!corner.isEmpty()) {
            switch (getPosition()) {
            case PositionConstants.TOP:
                tempRect.x += halfWidthInLP;
                tempRect.width -= getWidth();
                graphics.drawArc(tempRect.getTopRight().x - hRadius, tempRect.getTopRight().y, corner.width, corner.height, 5, 85);
                graphics.drawArc(tempRect.getTopLeft().x - hRadius, tempRect.getTopLeft().y, corner.width, corner.height, 90, 85);
                break;
            case PositionConstants.BOTTOM:
                tempRect.x += halfWidthInLP;
                tempRect.width -= getWidth();
                graphics.drawArc(tempRect.getBottomLeft().x - hRadius, tempRect.getBottomLeft().y - corner.height, corner.width, corner.height, 185, 85);
                graphics.drawArc(tempRect.getBottomRight().x - hRadius, tempRect.getBottomRight().y - corner.height, corner.width, corner.height, 270, 85);
                break;
            case PositionConstants.LEFT:
                tempRect.y += halfWidthInLP;
                tempRect.height -= getWidth();
                graphics.drawArc(tempRect.getTopLeft().x, tempRect.getTopLeft().y - vRadius, corner.width, corner.height, 95, 85);
                graphics.drawArc(tempRect.getBottomLeft().x, tempRect.getBottomLeft().y - vRadius, corner.width, corner.height, 180, 85);
                break;
            case PositionConstants.RIGHT:
                tempRect.y += halfWidthInLP;
                tempRect.height -= getWidth();
                graphics.drawArc(tempRect.getTopRight().x - corner.width, tempRect.getTopRight().y - vRadius, corner.width, corner.height, 0, 85);
                graphics.drawArc(tempRect.getBottomRight().x - corner.width, tempRect.getBottomRight().y - vRadius, corner.width, corner.height, 270, 85);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Sets the dimensions of each corner. This will form the radii of the arcs
     * which form the corners.
     * 
     * @param d
     *            the dimensions of the corner
     */
    public void setCornerDimensions(Dimension d) {
        if (d == null) {
            corner.width = 0;
            corner.height = 0;
        }
        corner.width = d.width;
        corner.height = d.height;
    }
}
