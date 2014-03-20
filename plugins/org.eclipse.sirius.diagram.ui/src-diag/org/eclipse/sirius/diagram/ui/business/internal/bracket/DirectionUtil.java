/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.bracket;

import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;

/**
 * Helper to get a {@link Direction}.
 * 
 * @author <a href="mailto:mariot.chauvin@obeo.fr">Mariot Chauvin</a>
 */
public final class DirectionUtil {

    /**
     * To not instantiate.
     */
    private DirectionUtil() {
    }

    /**
     * Get the direction from a reference point and the pointer location.
     * 
     * @param location
     *            the pointer location
     * @param reference
     *            the reference point
     * @return the direction
     */
    public static Direction getDirection(final Point location, final Point reference) {
        Direction direction = Direction.RIGHT;
        final int diffX = location.x - reference.x;
        final int diffY = location.y - reference.y;
        if (diffX == 0) {
            if (diffY < 0) {
                direction = Direction.TOP;
            } else if (diffY > 0) {
                direction = Direction.BOTTOM;
            }
        } else if (diffY == 0) {
            if (diffX > 0) {
                direction = Direction.RIGHT;
            } else if (diffX < 0) {
                direction = Direction.LEFT;
            }
        } else if (diffX > 0) {
            if (diffY > 0) {
                if (diffX >= diffY) {
                    direction = Direction.RIGHT;
                } else {
                    direction = Direction.BOTTOM;
                }
            } else {
                if (diffX >= -diffY) {
                    direction = Direction.RIGHT;
                } else {
                    direction = Direction.TOP;
                }
            }
        } else if (diffX < 0) {
            if (diffY > 0) {
                if (-diffX <= diffY) {
                    direction = Direction.BOTTOM;
                } else {
                    direction = Direction.LEFT;
                }
            } else {
                if (-diffX >= -diffY) {
                    direction = Direction.LEFT;
                } else {
                    direction = Direction.TOP;
                }
            }
        }
        return direction;
    }

    /**
     * Get the {@link Direction} for the specified dimension {@link Connection}.
     * 
     * @param connection
     *            the specified dimension {@link Connection}
     * @return the {@link Direction} for the specified dimension
     *         {@link Connection}
     */
    public static Direction getDirection(Connection connection) {
        Direction direction = Direction.RIGHT;
        final Object routingConstraint = connection.getRoutingConstraint();
        if (routingConstraint instanceof List<?>) {
            final List<?> list = (List<?>) routingConstraint;
            if (list.size() == 1 && list.get(0) instanceof BracketRelativeBendpoint) {
                final BracketRelativeBendpoint dimensionRelativeBendpoint = (BracketRelativeBendpoint) list.get(0);
                direction = Direction.values()[dimensionRelativeBendpoint.getDirection()];
            }
        }
        return direction;
    }

}
