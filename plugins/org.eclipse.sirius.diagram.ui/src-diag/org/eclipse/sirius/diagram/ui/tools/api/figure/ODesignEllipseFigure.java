/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.TransparentFigureGraphicsModifier;

/**
 * Specific Figure to handle ellipse style.
 * 
 * @author mporhel
 * 
 */
public class ODesignEllipseFigure extends AbstractTransparentEllipse {

    /**
     * Outlines the Ellipse.
     * 
     * @Override
     * @see org.eclipse.draw2d.Ellipse#outlineShape(org.eclipse.draw2d.Graphics)
     * @param graphics
     *            <code>Graphics</code> object that allows to draw to the
     *            surface
     */
    @Override
    protected void outlineShape(final Graphics graphics) {
        final Rectangle r = Rectangle.SINGLETON;
        r.setBounds(getDrawBounds());
        r.width--;
        r.height--;
        r.shrink((getLineWidth() - 1) / 2, (getLineWidth() - 1) / 2);
        graphics.drawOval(r);
    }

    /**
     * Fills the Ellipse.
     * 
     * @Override
     * @see org.eclipse.draw2d.Ellipse#fillShape(org.eclipse.draw2d.Graphics)
     * @param graphics
     *            <code>Graphics</code> object that allows to draw to the
     *            surface
     */
    @Override
    protected void fillShape(final Graphics graphics) {
        TransparentFigureGraphicsModifier modifier = new TransparentFigureGraphicsModifier(this, graphics);
        modifier.pushState();
        graphics.fillOval(getDrawBounds());
        modifier.popState();
    }

    /**
     * Computes the rectangle containing the Ellipse.
     * 
     * @Override
     * @see org.eclipse.draw2d.Ellipse#fillShape(org.eclipse.draw2d.Graphics)
     * @return a rectangle which can contains the ellipse and its border
     */
    protected Rectangle getDrawBounds() {
        final Rectangle r = new Rectangle(super.getBounds());
        r.x = bounds.x + getLineWidth() / 2;
        r.y = bounds.y + getLineWidth() / 2;
        r.width = bounds.width - getLineWidth();
        r.height = bounds.height - getLineWidth();
        return r;
    }

}
