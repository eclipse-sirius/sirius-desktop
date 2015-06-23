/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gmf.runtime.draw2d.ui.figures.OneLineBorder;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;

/**
 * Specific {@link OneLineBorder} with dash capabilities, two more supported
 * positions ({@link PositionConstants#MIDDLE} and
 * {@link PositionConstants#CENTER}) and margin capabilities.
 * 
 * @author mporhel
 */
public class OneLineMarginBorder extends OneLineBorder {

    private int[] dash;

    private Insets margin = new Insets();

    /**
     * Constructor.
     * 
     * @param position
     *            The position to set.
     */
    public OneLineMarginBorder(int position) {
        super();
        setPosition(position);
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
        graphics.setLineDash(dash);

        super.paint(figure, graphics, insets);

        int one = MapModeUtil.getMapMode(figure).DPtoLP(1);
        int widthInDP = getWidth() / one;
        int halfWidthInLP = MapModeUtil.getMapMode(figure).DPtoLP(widthInDP / 2);

        switch (getPosition()) {
        case PositionConstants.MIDDLE:
            // See PositionConstants.TOP_MIDDLE_BOTTOM = TOP | MIDDLE | BOTTOM;
            tempRect.y += halfWidthInLP;
            tempRect.height -= getWidth();
            graphics.drawLine(tempRect.getLeft(), tempRect.getRight());
            break;
        case PositionConstants.CENTER:
            // See PositionConstants.LEFT_CENTER_RIGHT = LEFT | CENTER | RIGHT;
            tempRect.x += halfWidthInLP;
            tempRect.width -= getWidth();
            graphics.drawLine(tempRect.getTop(), tempRect.getBottom());
            break;
        default:
            break;
        }
    }

    @Override
    public Insets getInsets(IFigure figure) {
        Insets borderInsets;

        switch (getPosition()) {
        case PositionConstants.TOP:
            borderInsets = new Insets(getWidth(), 0, 0, 0);
            break;
        case PositionConstants.LEFT:
            borderInsets = new Insets(0, getWidth(), 0, 0);
            break;
        case PositionConstants.BOTTOM:
            borderInsets = new Insets(0, 0, getWidth(), 0);
            break;
        case PositionConstants.RIGHT:
            borderInsets = new Insets(0, 0, 0, getWidth());
            break;
        case PositionConstants.MIDDLE:
            borderInsets = new Insets(getWidth() / 2, 0, getWidth() / 2, 0);
            break;
        case PositionConstants.CENTER:
            borderInsets = new Insets(0, getWidth() / 2, 0, getWidth() / 2);
            break;
        default:
            borderInsets = IFigure.NO_INSETS;
            break;
        }

        Insets globalInsets = new Insets();
        globalInsets.add(borderInsets);
        globalInsets.add(margin);

        return globalInsets;
    }

    /**
     * Sets the dash pattern when the custom line style ( is in use.
     * 
     * @param dashPattern
     *            the pixel pattern
     */
    public void setLineDash(int[] dashPattern) {
        int[] copy = null;
        if (dashPattern != null) {
            copy = new int[dashPattern.length];
            System.arraycopy(dashPattern, 0, copy, 0, dashPattern.length);
        }
        this.dash = copy;
    }

    /**
     * Set the extra padding to add to the border width.
     * 
     * @param insets
     *            The blank padding Insets for the border
     */
    public void setMargin(Insets insets) {
        if (margin != null) {
            margin = insets;
        } else {
            margin = new Insets();
        }
    }

    /**
     * Set the extra padding to add to the border width.
     * 
     * @param t
     *            Top padding
     * @param l
     *            Left padding
     * @param b
     *            Bottom padding
     * @param r
     *            Right padding
     */
    public void setMargin(int t, int l, int b, int r) {
        setMargin(new Insets(t, l, b, r));
    }

    /**
     * Set the extra padding to add to the border width.
     * 
     * @param allsides
     *            Padding size for all sides of the border.
     * @since 0.9.0
     */
    public void setMargin(int allsides) {
        setMargin(new Insets(allsides));
    }
}
