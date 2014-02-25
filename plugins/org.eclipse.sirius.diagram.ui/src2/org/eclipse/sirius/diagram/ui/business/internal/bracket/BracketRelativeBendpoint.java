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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * A specific {@link RelativeBendpoint} to store direction and offset for
 * dimension figure.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketRelativeBendpoint extends RelativeBendpoint {

    /** Side of the source figure from which begin the bracket point list. */
    private int sourceDirection;

    /** Direction of the next (from source) or previous (from target) point. */
    private int direction;

    /** offset relative to the source or the target. */
    private int offset;

    /**
     * Default constructor.
     * 
     * @param connection
     *            the {@link Connection} of the Dimension
     * 
     * @param sourceDirection
     *            the side of the source figure from which begin the bracket
     *            point list
     * @param direction
     *            Direction of the next (from source) or previous (from target)
     *            point
     * @param offset
     *            offset relative to the source or the target
     */
    public BracketRelativeBendpoint(Connection connection, int sourceDirection, int direction, int offset) {
        super(connection);
        this.sourceDirection = sourceDirection;
        this.direction = direction;
        this.offset = offset;
        setRelativeDimensions(Dimension.SINGLETON, Dimension.SINGLETON);
        setWeight(0);
    }

    /**
     * Get the integer representing the {@link Direction} (i.e. the side of the
     * source figure) from which start the bracket point list.
     * 
     * @return the integer representing the {@link Direction}
     */
    public int getSourceDirection() {
        return sourceDirection;
    }

    /**
     * Get the integer representing the {@link Direction}.
     * 
     * @return the integer representing the {@link Direction}
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Get the offset.
     * 
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

}
