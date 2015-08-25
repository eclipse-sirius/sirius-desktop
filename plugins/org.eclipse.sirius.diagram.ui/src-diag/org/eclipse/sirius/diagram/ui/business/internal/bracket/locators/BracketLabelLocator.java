/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.bracket.locators;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.LabelLocator;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;

/**
 * Bracket specific {@link LabelLocator} to have the center label the middle of
 * the bracket segment as reference point.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketLabelLocator extends LabelLocator {
    /**
     * Constructor to create a an instance of <code>LabelLocator</code> which
     * locates an IFigure offset relative to a calculated reference point.
     *
     * @param parent
     *            the parent figure
     * @param offSet
     *            the relative location of the label
     * @param alignment
     *            the alignment hint in the case the parent is a
     *            <code>Connection</code>
     */
    public BracketLabelLocator(IFigure parent, Point offSet, int alignment) {
        super(parent, offSet, alignment);
    }

    /**
     * Return the middle of the bracket segment as reference point.
     *
     * @param parent
     *            The parent figure of the label.
     * @return the middle of the bracket segment as reference point.
     */
    public static final Point getReferencePoint(IFigure parent) {
        Point referencePoint = null;
        if (parent instanceof Connection) {
            PointList pointList = ((Connection) parent).getPoints();
            referencePoint = getReferencePoint(pointList);
        } else {
            referencePoint = parent.getBounds().getLocation();
        }
        return referencePoint;
    }

    /**
     * Return the middle of the bracket segment as reference point.
     *
     * @param pointList
     *            The points list of the connection.
     * @return the middle of the bracket segment as reference point.
     */
    public static final Point getReferencePoint(PointList pointList) {
        Point referencePoint = null;
        if (pointList.size() == 6) {
            Point originPoint = pointList.getPoint(BracketConnectionQuery.ORIGIN_POINT_INDEX);
            Point targetPoint = pointList.getPoint(BracketConnectionQuery.TARGET_POINT_INDEX);
            referencePoint = new Rectangle(originPoint, targetPoint).getCenter();
        } else {
            referencePoint = pointList.getMidpoint();
        }
        return referencePoint;
    }

    /**
     * Overridden to return the middle of the bracket segment as reference
     * point.
     *
     * {@inheritDoc}
     */
    @Override
    protected Point getReferencePoint() {
        return BracketLabelLocator.getReferencePoint(parent);
    }
}
