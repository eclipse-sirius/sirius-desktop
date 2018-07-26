/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * A rectangle across the whole diagram, useful as feedback to insert blank space in diagram.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class InsertBlankSpaceGuide extends Figure {

    private boolean horizontal = true;

    /**
     * Creates a feedback figure with two horizontal guide placed at the given range.
     * 
     * @param color
     *            the color of the line.
     * @param horizontal
     *            true if this guide is horizontal, false otherwise.
     */
    public InsertBlankSpaceGuide(Color color, boolean horizontal) {
        setForegroundColor(color);
        setBackgroundColor(color);
        this.horizontal = horizontal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintFigure(Graphics graphics) {
        Rectangle rect = getBounds().getCopy();
        // Draw the starting line
        if (horizontal) {
            graphics.drawLine(getBounds().getTopLeft(), getBounds().getTopRight());
        } else {
            graphics.drawLine(getBounds().getTopLeft(), getBounds().getBottomLeft());
        }

        // If the size is null, no need to fill it or to draw the bottom line.
        // we have to reduce the height of the drawn rectangle to stay in the
        // bounds of the figure.
        if (horizontal && rect.height > 1 || !horizontal && rect.width > 1) {
            if (horizontal) {
                graphics.drawLine(rect.getBottomLeft(), rect.getBottomRight());
            } else {
                graphics.drawLine(getBounds().getTopRight(), getBounds().getBottomRight());
            }

            // Fill rectangle with transparency
            int alpha = graphics.getAlpha();
            graphics.setAlpha(25);
            graphics.fillRectangle(rect);
            graphics.setAlpha(alpha);
        }
    }
}
