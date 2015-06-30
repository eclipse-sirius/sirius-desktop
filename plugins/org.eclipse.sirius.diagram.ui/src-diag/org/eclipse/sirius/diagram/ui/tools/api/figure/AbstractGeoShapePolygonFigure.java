/******************************************************************************
 * Copyright (c) 2003, 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Mariot Chauvin <mariot.chauvin@obeo.fr> - Checkstylized
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IPolygonAnchorableFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;

/**
 * Base class for polygons in the Geometric shapes palette.
 * 
 * author jschofie
 * 
 * @author mchauvin
 */
public abstract class AbstractGeoShapePolygonFigure extends DefaultSizeNodeFigure implements IPolygonAnchorableFigure {

    /**
     * Constructor.
     * 
     * @param width
     *            figure width
     * @param height
     *            figure height
     * @param spacing
     *            figure spacing
     */
    public AbstractGeoShapePolygonFigure(final int width, final int height, final int spacing) {
        super(width, height);
        setOpaque(true);
    }

    /**
     * This method is used to compute the shapes polygon points. This is
     * currently based on the shapes bounding box.
     * 
     * Subclasses must return their list of points based on the object size.
     * 
     * @param rect
     *            the rectangle that the shape will fit in
     * @return the point list
     */
    protected abstract PointList calculatePoints(Rectangle rect);

    /**
     * Get the content pane.
     * 
     * @return the content pane
     */
    public IFigure getContentPane() {
        return (IFigure) getChildren().get(0);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(final Graphics g) {
        g.setBackgroundColor(getBackgroundColor());
        g.setForegroundColor(getForegroundColor());
        g.setLineWidth(getLineWidth());
        g.setLineStyle(getLineStyle());

        final PointList points = calculatePoints(new Rectangle(getBounds()));
        g.fillPolygon(points);
        g.drawPolygon(points);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IPolygonAnchorableFigure#getPolygonPoints()
     */
    public PointList getPolygonPoints() {
        return calculatePoints(new Rectangle(getBounds()));
    }
}
