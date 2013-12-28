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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * A rotatable, polygon shaped with arrow decoration.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class PolygoneAndPolylineDecoraction extends PolygonDecoration {

    /** A triangle template. */
    public static final PointList TRIANGLE_TIP = new PointList();

    static {
        TRIANGLE_TIP.addPoint(-3, 1);
        TRIANGLE_TIP.addPoint(-2, 0);
        TRIANGLE_TIP.addPoint(-3, -1);
    }

    PolylineDecoration polylineDecoration = new PolylineDecoration();

    /**
     * Constructs a PolygoneAndPolylineDecoraction.
     */
    public PolygoneAndPolylineDecoraction() {
        super();
        polylineDecoration.setTemplate(TRIANGLE_TIP);
    }

    /**
     * Sets the PolylineDecoration's point template. This template is an outline
     * of the PolylineDecoration's region. (The default value is TRIANGLE_TIP
     * which is a triangle whose tip is pointing to the right).
     * 
     * @param pl
     *            the template
     */
    public void setPolylineTemplate(PointList pl) {
        polylineDecoration.setTemplate(pl);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Polygon#outlineShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void outlineShape(Graphics g) {
        super.outlineShape(g);
        g.drawPolyline(polylineDecoration.getPoints());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Polyline#getBounds()
     */
    @Override
    public Rectangle getBounds() {
        if (bounds == null) {
            if (polylineDecoration != null) {
                bounds = getPoints().getBounds().getUnion(polylineDecoration.getPoints().getBounds()).getExpanded(getLineWidth() / 2, getLineWidth() / 2);
            } else {
                // In case of constructor the first geBounds is called before
                // the polylineDecoration creation.
                bounds = super.getBounds();
            }
        }
        return bounds;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.PolygonDecoration#setRotation(double)
     */
    @Override
    public void setRotation(double angle) {
        super.setRotation(angle);
        polylineDecoration.setRotation(angle);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.PolygonDecoration#setScale(double, double)
     */
    @Override
    public void setScale(double x, double y) {
        super.setScale(x, y);
        if (polylineDecoration != null) {
            // The polylineDecoration can be null (at least during the
            // constructor)
            polylineDecoration.setScale(x, y);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setBackgroundColor(org.eclipse.swt.graphics.Color)
     */
    @Override
    public void setBackgroundColor(Color bg) {
        super.setBackgroundColor(bg);
        polylineDecoration.setBackgroundColor(bg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setForegroundColor(org.eclipse.swt.graphics.Color)
     */
    @Override
    public void setForegroundColor(Color fg) {
        super.setForegroundColor(fg);
        polylineDecoration.setForegroundColor(fg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Polyline#setLineWidth(int)
     */
    @Override
    public void setLineWidth(int w) {
        super.setLineWidth(w);
        polylineDecoration.setLineWidth(w);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.PolygonDecoration#setLocation(org.eclipse.draw2d.geometry.Point)
     */
    @Override
    public void setLocation(Point p) {
        super.setLocation(p);
        polylineDecoration.setLocation(p);
    }
}
