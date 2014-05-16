/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.gmf.runtime.draw2d.ui.geometry;

import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;

/**
 * A class aggregating for queries on {@link LineSeg} elements.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class LineSegQuery {

    private LineSeg lineseg;

    /**
     * Create a new query.
     * 
     * @param lineseg
     *            the {@link LineSeg} used by the query.
     */
    public LineSegQuery(LineSeg lineseg) {
        super();
        this.lineseg = lineseg;
    }

    /**
     * Determines if this is a horizontal segment (or at least more horizontal
     * than vertical).
     * 
     * @return <code>boolean</code> <code>true</code> if horizontal,
     *         <code>false</code> otherwise.
     */
    public boolean isHorizontal() {
        boolean isHorizontal = lineseg.isHorizontal();
        boolean isVertical = lineseg.isVertical();
        if (!isHorizontal && !isVertical) {
            // We are in case where the bendpoints does not represent
            // exactly a rectilinear edge
            int xDifference = lineseg.getOrigin().x - lineseg.getTerminus().x;
            int yDifference = lineseg.getOrigin().y - lineseg.getTerminus().y;
            if (xDifference < 0 && yDifference < 0) {
                isHorizontal = xDifference < yDifference;
            } else {
                isHorizontal = xDifference > yDifference;
            }
        }
        return isHorizontal;
    }
}
