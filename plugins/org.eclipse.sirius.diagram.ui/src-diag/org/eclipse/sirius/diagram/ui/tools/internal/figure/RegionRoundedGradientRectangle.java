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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;

/**
 * Specific {@link GradientRoundedRectangle} able to use a different dimension
 * for chosen corners.
 * 
 * This is currently used to render region, and will always be configured to
 * have outline == false.
 * 
 * fillShape(Graphics) draws the chosen corners with the additional dimension
 * and the other ones with the main corner dimension.
 * 
 * outlineShape(Graphics) currently calls super.outlineShape() and will draw the
 * outline corresponding to a standard RoundedCorner with the same corner
 * dimension.
 * 
 * outlineShape(Graphics) will never be call as setOutline will do nothing and
 * the outline is set to false
 * 
 * @author mporhel
 */
public class RegionRoundedGradientRectangle extends GradientRoundedRectangle {

    private final Dimension secondaryCorner = new Dimension(0, 0);

    private int position = PositionConstants.NONE;

    /**
     * Create a new {@link RegionRoundedGradientRectangle}.
     * 
     * @param dimension
     *            dimension of the corner (with radius, height radius)
     * @param backgroundStyle
     *            style of the wanted gradient
     * 
     */
    public RegionRoundedGradientRectangle(final Dimension dimension, final BackgroundStyle backgroundStyle) {
        super(dimension, backgroundStyle);
        setOutline(false);
    }

    /**
     * The outline of the shape is disabled for regions which have a specific
     * border figure.
     */
    @Override
    public void setOutline(boolean b) {
        super.setOutline(false);
    }

    /**
     * Sets the dimensions of each corner. This will form the radii of the arcs
     * which form the additional corners.
     * 
     * @param chosenCorners
     *            a bit-wise OR of the chosen corners. see
     *            {@link PositionConstants}
     * @param d
     *            the dimensions of the corner, it should be smaller than the
     *            main corner dimension to be visible.
     */
    public void setAdditionalCornerDimensions(int chosenCorners, Dimension d) {
        position = chosenCorners;

        if (d == null) {
            secondaryCorner.width = 0;
            secondaryCorner.height = 0;
        }
        secondaryCorner.width = d.width;
        secondaryCorner.height = d.height;
    }

    @Override
    protected void fillShape(Graphics graphics) {
        super.fillShape(graphics);

        if (PositionConstants.NONE == position) {
            return;
        }

        if (correspondsTo(PositionConstants.NORTH_WEST)) {
            Rectangle corner = new Rectangle(bounds.getCenter(), bounds.getTopLeft());
            graphics.fillRoundRectangle(corner, secondaryCorner.width, secondaryCorner.height);
        }

        if (correspondsTo(PositionConstants.NORTH_EAST)) {
            Rectangle corner = new Rectangle(bounds.getCenter(), bounds.getTopRight());
            graphics.fillRoundRectangle(corner, secondaryCorner.width, secondaryCorner.height);
        }

        if (correspondsTo(PositionConstants.SOUTH_EAST)) {
            Rectangle corner = new Rectangle(bounds.getCenter(), bounds.getBottomRight());
            graphics.fillRoundRectangle(corner, secondaryCorner.width, secondaryCorner.height);
        }

        if (correspondsTo(PositionConstants.SOUTH_WEST)) {
            Rectangle corner = new Rectangle(bounds.getCenter(), bounds.getBottomLeft());
            graphics.fillRoundRectangle(corner, secondaryCorner.width, secondaryCorner.height);
        }
    }

    private boolean correspondsTo(int corner) {
        return (corner & position) == corner;
    }
}
