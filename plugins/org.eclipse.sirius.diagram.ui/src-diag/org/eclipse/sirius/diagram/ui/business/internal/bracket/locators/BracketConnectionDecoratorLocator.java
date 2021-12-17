/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.bracket.locators;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.Direction;

/**
 * A {@link ConnectionLocator} to locate decorator.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketConnectionDecoratorLocator extends ConnectionLocator {

    /** The bracket origin point of the connection. */
    public static final int DORIGIN = 5;

    /** The bracket target point of the connection. */
    public static final int DTARGET = 6;

    private BracketConnectionQuery bracketConnectionQuery;

    private Rectangle bracketSegmentBounds;

    private Direction generalDirection;

    /**
     * Default constructor.
     * 
     * @param connection
     *            {@link Connection}
     * @param align
     *            align
     * @param bracketConnectionQuery
     *            the {@link BracketConnectionQuery} of the {@link Connection}
     */
    public BracketConnectionDecoratorLocator(Connection connection, int align, BracketConnectionQuery bracketConnectionQuery) {
        super(connection, align);
        this.bracketConnectionQuery = bracketConnectionQuery;
    }

    /**
     * Relocates the passed in figure (which must be a
     * {@link RotatableDecoration}) at either the start or end of the
     * connection.
     * 
     * @param target
     *            The RotatableDecoration to relocate
     */
    public void relocate(IFigure target) {
        final PointList points = getConnection().getPoints();
        final RotatableDecoration arrow = (RotatableDecoration) target;
        Point location = getLocation(points);
        arrow.setLocation(location);

        Point inversedLocation = getInversedLocation(points, location, arrow);
        arrow.setReferencePoint(inversedLocation);
        Point center = arrow.getBounds().getCenter();
        if (bracketSegmentTooSmallForDecoration(arrow) && (bracketSegmentBounds.contains(center) || bracketSegmentBounds.isEmpty())) {
            inversedLocation = location.getTranslated(location.getDifference(inversedLocation).getExpanded(getExpansion()));
            arrow.setReferencePoint(inversedLocation);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Point getLocation(PointList points) {
        Point location = null;
        switch (getAlignment()) {
        case DORIGIN:
            if (points.size() > 2) {
                location = points.getPoint(BracketConnectionQuery.ORIGIN_POINT_INDEX);
            } else {
                location = points.getFirstPoint();
            }
            break;
        case DTARGET:
            if (points.size() > 2) {
                location = points.getPoint(BracketConnectionQuery.TARGET_POINT_INDEX);
            } else {
                location = points.getLastPoint();
            }
            break;
        default:
            location = super.getLocation(points);
            break;
        }
        return location.getCopy();
    }

    /**
     * Get a reversed choice to getLocation().
     * 
     * @param points
     *            {@link PointList} of the {@link Connection}
     * @param location
     *            the location
     * @param arrow
     *            the decoration on source or target
     * @return a reversed choice to getLocation()
     */
    protected Point getInversedLocation(PointList points, Point location, RotatableDecoration arrow) {
        Point inversedLocation = null;
        switch (getAlignment()) {
        case DORIGIN:
            if (points.size() > 2) {
                inversedLocation = points.getPoint(BracketConnectionQuery.TARGET_POINT_INDEX);
            } else {
                inversedLocation = points.getLastPoint();
            }
            break;
        case DTARGET:
            if (points.size() > 2) {
                inversedLocation = points.getPoint(BracketConnectionQuery.ORIGIN_POINT_INDEX);
            } else {
                inversedLocation = points.getFirstPoint();
            }
            break;
        default:
            inversedLocation = super.getLocation(points);
            break;
        }
        return inversedLocation.getCopy();
    }

    private boolean bracketSegmentTooSmallForDecoration(RotatableDecoration rotatableDecoration) {
        boolean bracketSegmentTooSmallForDecoration = false;
        Rectangle decorationBounds = rotatableDecoration.getBounds();
        bracketSegmentBounds = bracketConnectionQuery.getBracketSegmentBounds();
        generalDirection = bracketConnectionQuery.getGeneralDirection();
        int bracketSegmentLength = Math.max(bracketSegmentBounds.width, bracketSegmentBounds.height) - 1;
        switch (generalDirection) {
        case LEFT:
        case RIGHT:
            bracketSegmentTooSmallForDecoration = 3 * decorationBounds.height > bracketSegmentLength;
            bracketSegmentBounds.shrink(new Insets(1, 0, 1, 0));
            break;
        case TOP:
        case BOTTOM:
            bracketSegmentTooSmallForDecoration = 3 * decorationBounds.width > bracketSegmentLength;
            bracketSegmentBounds.shrink(new Insets(0, 1, 0, 1));
            break;
        default:
        }
        return bracketSegmentTooSmallForDecoration;
    }

    /**
     * Get the expansion used to have a referencedPoint in case of null bracket
     * segment length.
     * 
     * @return the expansion used to have a referencedPoint
     */
    private Dimension getExpansion() {
        Dimension expansion = new Dimension(0, 0);
        int expansionValue = 2;
        if (getAlignment() == DTARGET) {
            expansionValue *= -1;
        }

        switch (generalDirection) {
        case LEFT:
        case RIGHT:
            expansion.setHeight(expansionValue);
            break;
        case TOP:
        case BOTTOM:
            expansion.setWidth(expansionValue);
            break;
        default:
            break;
        }
        return expansion;
    }

}
