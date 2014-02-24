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
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.TransparentFigureGraphicsModifier;

/**
 * Specific Figure to handle lozenge style.
 * 
 * @author mporhel
 * 
 */
public class LozengeFigure extends Shape implements StyledFigure, ITransparentFigure {

    private int viewpointAlpha = DEFAULT_ALPHA;

    private boolean transparent;

    /**
     * Create a new lozenge figure with default values.
     */
    public LozengeFigure() {
    }

    /**
     * Outlines the lozenge.
     * 
     * @param graphics
     *            <code>Graphics</code> object that allows to fill the surface
     */
    @Override
    protected void outlineShape(final Graphics graphics) {
        final PointList pointList = getPointList();
        graphics.drawPolygon(pointList);
    }

    /**
     * Fills the Lozenge.
     * 
     * @see org.eclipse.draw2d.Shape#fillShape(org.eclipse.draw2d.Graphics)
     * @param graphics
     *            <code>Graphics</code> object that allows to draw to the
     *            surface
     */
    @Override
    protected void fillShape(final Graphics graphics) {
        TransparentFigureGraphicsModifier modifier = new TransparentFigureGraphicsModifier(this, graphics);
        modifier.pushState();
        graphics.fillPolygon(getPointList());
        modifier.popState();
    }

    /**
     * Computes the polygon points of the lozenge.
     * 
     * @return PointList list of the points
     */
    protected PointList getPointList() {
        final Rectangle r = new Rectangle();
        final PointList pointList = new PointList();

        r.x = bounds.x + getLineWidth() / 2;
        r.y = bounds.y + getLineWidth() / 2;
        r.width = bounds.width - getLineWidth();
        r.height = bounds.height - getLineWidth();
        pointList.removeAllPoints();
        pointList.addPoint(r.x + r.width / 2, r.y);
        pointList.addPoint(r.x + r.width, r.y + r.height / 2);
        pointList.addPoint(r.x + r.width / 2, r.y + r.height);
        pointList.addPoint(r.x, r.y + r.height / 2);

        return pointList;
    }

    /**
     * {@inheritDoc}
     */
    public int getSiriusAlpha() {
        return viewpointAlpha;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTransparent() {
        return transparent;
    }

    /**
     * {@inheritDoc}
     */
    public void setSiriusAlpha(int alpha) {
        this.viewpointAlpha = alpha;

    }

    /**
     * {@inheritDoc}
     */
    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }
}
