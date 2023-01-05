/*****************************************************************************
 * Copyright (c) 2009, 2023 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Fadoi LAKHAL (CEA LIST) <Fadoi.Lakhal@cea.fr> - Initial API and implementation
 *  Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Bug 581287
 *****************************************************************************/
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;

/**
 * Use to create a circle plus decoration 
 * Adapted from Papyrus ContainmentDecoration class.
 * 
 * @author flakhal
 */
public class CirclePlusDecoration extends Ellipse implements RotatableDecoration {

    /** The default radius of the circle of the oval decoration */
    private static final int RADIUS = 10;

    private Point location;

    private Transform transform;

    private Point ovalCenter;

    private Dimension ovalSize;

    private double xScale = 1;

    private double yScale = 1;

    /**
     * Constructor.
     */
    public CirclePlusDecoration() {
        location = new Point();
        transform = new Transform();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#getBounds()
     */
    @Override
    public Rectangle getBounds() {
        if (bounds == null) {
            updatePoints();

            bounds = new Rectangle(location, new Dimension(0, 0));

            Point p = Point.SINGLETON;
            p.setLocation(ovalCenter);
            p.x += ovalSize.width / 2;
            p.y += ovalSize.height / 2;
            bounds.union(p);
            p.x -= ovalSize.width + 1; // +1 to avoid a truncated circle on the top of the circle
            p.y -= ovalSize.height + 1; // +1 to avoid a truncated circle on the top of the circle
            bounds.union(p);

            bounds.expand(getLineWidth() / 2, getLineWidth() / 2);
        }
        return bounds;
    }

    /**
     * Set the scale of the Oval. By default, the oval has a size of 10 per 10 : that is a circle !
     *
     * @param x
     *            the x-scale value
     * @param y
     *            the y-scale value
     */
    public void setScale(double x, double y) {
        ovalCenter = null;
        ovalSize = null;
        bounds = null;
        transform.setScale(x, y);
        xScale = x;
        yScale = y;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setLocation(org.eclipse.draw2d.geometry.Point)
     */
    @Override
    public void setLocation(Point p) {
        ovalCenter = null;
        ovalSize = null;
        bounds = null;
        location.setLocation(p);
        transform.setTranslation(p.x, p.y);
        super.setLocation(p);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.RotatableDecoration#setReferencePoint(org.eclipse.draw2d.geometry.Point).
     */
    @Override
    public void setReferencePoint(Point p) {
        ovalCenter = null;
        ovalSize = null;
        bounds = null;
        Point pt = Point.SINGLETON;
        pt.setLocation(p);
        pt.negate().translate(location);
        setRotation(Math.atan2(pt.y, pt.x));
    }

    /**
     * Set the rotation parameter.
     *
     * @param angle
     *            set the angle of the oval
     */
    public void setRotation(double angle) {
        ovalCenter = null;
        ovalSize = null;
        bounds = null;
        transform.setRotation(angle);
    }

    /**
     * Update the points of the Circle.
     */
    protected void updatePoints() {
        if (ovalSize == null) {
            ovalSize = new Dimension((int) xScale * RADIUS * 2, (int) yScale * RADIUS * 2);
        }
        if (ovalCenter == null) {
            ovalCenter = new Point(transform.getTransformed(new Point((int) -xScale * RADIUS, 0)));
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paintFigure(Graphics graphics) {
        updatePoints();

        graphics.pushState();
        int oldLineWidth = graphics.getLineWidth();
        graphics.setLineWidth(getLineWidth());
        graphics.fillOval(ovalCenter.x - ovalSize.width / 2, ovalCenter.y - ovalSize.height / 2, ovalSize.width, ovalSize.height);
        graphics.drawLine(ovalCenter.x - ovalSize.width / 2, ovalCenter.y, ovalCenter.x + ovalSize.width / 2, ovalCenter.y);
        graphics.drawLine(ovalCenter.x, ovalCenter.y - ovalSize.width / 2, ovalCenter.x, ovalCenter.y + ovalSize.width / 2);
        graphics.setLineWidth(oldLineWidth);

        graphics.popState();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#paintBorder(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintBorder(Graphics graphics) {
        updatePoints();

        graphics.pushState();
        int oldLineWidth = graphics.getLineWidth();
        graphics.setLineWidth(getLineWidth());

        graphics.drawOval(ovalCenter.x - ovalSize.width / 2, ovalCenter.y - ovalSize.height / 2, ovalSize.width, ovalSize.height);
        graphics.setLineWidth(oldLineWidth);
        graphics.popState();
    }
}
