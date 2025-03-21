/*******************************************************************************
 * Copyright (c) 2025 Obeo.
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
package org.eclipse.sirius.diagram.elk;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkShape;

/**
 * Utility methods for ELK layout-related things.
 *
 * @author lredor
 */
public class SiriusElkUtil {

    /**
     * Prevents instanciation. 
     */
    private SiriusElkUtil() {
    }

    /**
     * The ELK coordinates use floating point, but GMF uses Integer coordinates. The accumulation of rounding could have
     * side effects on absolute location (switch of one pixel for example). To avoid the problem, we try to find the
     * more appropriated rounding, above or below, according to parents coordinates. The return value is a
     * PrecisionPoint but the coordinates are Integer. PrecisionPoint is used for convenience in the following usages.
     * 
     * @param elkShape
     *            The shape for which we want to get the rounded coordinates.
     * @return The rounded coordinates of <code>elkShape</code>
     */
    public static PrecisionPoint getRoundedCoordinatesAccordingToParents(ElkShape elkShape) {
        PrecisionPoint defaultRoundedCoordinates = new PrecisionPoint(Math.toIntExact(Math.round(elkShape.getX())), Math.toIntExact(Math.round(elkShape.getY())));
        if (elkShape.eContainer() instanceof ElkShape parent) {
            KVector absoluteELKCoordinates = ElkUtil.absolutePosition(elkShape);
            PrecisionPoint parentRoundedAbsoluteCoordinates = getRoundedAbsoluteCoordinates(parent);
            PrecisionPoint roundedAbsoluteCoordinates = (PrecisionPoint) defaultRoundedCoordinates.getTranslated(parentRoundedAbsoluteCoordinates);
            Dimension delta = new Dimension((int) (absoluteELKCoordinates.x - roundedAbsoluteCoordinates.preciseX()), (int) (absoluteELKCoordinates.y - roundedAbsoluteCoordinates.preciseY()));
            defaultRoundedCoordinates.translate(delta);
        }
        return defaultRoundedCoordinates;
    }

    private static PrecisionPoint getRoundedAbsoluteCoordinates(ElkShape elkShape) {
        PrecisionPoint roundedCoordinates = getRoundedCoordinatesAccordingToParents(elkShape);
        if (elkShape.eContainer() instanceof ElkShape parent) {
            roundedCoordinates.translate(getRoundedAbsoluteCoordinates(parent));
        }
        return roundedCoordinates;
    }
}
