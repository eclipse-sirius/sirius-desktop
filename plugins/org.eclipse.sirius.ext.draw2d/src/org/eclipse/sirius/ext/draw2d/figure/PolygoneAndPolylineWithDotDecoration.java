/*****************************************************************************
 * Copyright (c) 2023 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A rotatable, polygon shaped with arrow decoration and dot.
 * 
 * @author Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr>
 */
public class PolygoneAndPolylineWithDotDecoration extends PolygoneAndPolylineDecoraction {

    /**
     * the radius of the dot
     */
    private static final int DOT_DIAMETER = 6;

    /**
     * the dot
     */
    private Ellipse dot;

    /**
     * Constructs a PolygoneAndPolylineDecoraction.
     */
    public PolygoneAndPolylineWithDotDecoration() {
        super();
        dot = new Ellipse();
        dot.setSize(DOT_DIAMETER, DOT_DIAMETER);
        dot.setFill(true);
        add(dot);
        dot.setLocation(new Point());

        PointList arrow = TRIANGLE_TIP.getCopy();
        arrow.translate(1, 0);
        setPolylineTemplate(arrow);
    }

    /**
     * {@inheritDoc}
     * 
     * 
     * @see org.eclipse.draw2d.Figure#paintChildren(org.eclipse.draw2d.Graphics.)
     */
    @Override
    protected void paintChildren(Graphics graphics) {
        // the only way I found to get the dot paint of the expected color (when the link is not in black!)
        this.dot.setBackgroundColor(graphics.getForegroundColor());
        super.paintChildren(graphics);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Polyline#getBounds()
     */
    @Override
    public Rectangle getBounds() {
        if (dot != null) {
            Rectangle b = super.getBounds();
            b.union(dot.getBounds());
            return b;
        } else {
            return super.getBounds();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.PolygonDecoration#setLocation(org.eclipse.draw2d.geometry.Point)
     * 
     */
    @Override
    public void setLocation(Point p) {
        super.setLocation(p);
        Point ellipseLocation = new Point(p);
        // initialize with an horizontal location
        dot.setLocation(ellipseLocation.translate(new Point(-DOT_DIAMETER / 2, -DOT_DIAMETER / 2)));

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.PolygonDecoration#setRotation(double)
     * 
     */
    @Override
    public void setRotation(double angle) {
        int translatationX = (int) (DOT_DIAMETER * Math.cos(angle));
        int translationY = (int) (DOT_DIAMETER * Math.sin(angle));

        dot.setLocation(new Point(dot.getLocation().x - translatationX / 2, dot.getLocation().y - translationY / 2));
        super.setRotation(angle);
    }
}
