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
package org.eclipse.sirius.diagram.ui.tools.internal.routers;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;

/**
 * This class override the default BendpointConnectionRouter to avoid the
 * duplication of the start and the end point of the figure. <BR>
 * It seems that the default BendpointConnectionRouter waiting a constraint
 * without the start and the end point. But the GMF
 * ConnectionEditPart.refreshBendpoint set a constraint with the start and the
 * end point.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusBendpointConnectionRouter extends BendpointConnectionRouter {
    private static final PrecisionPoint A_POINT = new PrecisionPoint();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.BendpointConnectionRouter#route(org.eclipse.draw2d.Connection)
     */
    @Override
    public void route(Connection conn) {
        PointList points = conn.getPoints();
        points.removeAllPoints();

        List bendpoints = (List) getConstraint(conn);
        if (bendpoints == null) {
            bendpoints = Collections.EMPTY_LIST;
        }

        Point ref1;
        Point ref2;

        if (bendpoints.isEmpty()) {
            ref1 = conn.getTargetAnchor().getReferencePoint();
            ref2 = conn.getSourceAnchor().getReferencePoint();
        } else if (bendpoints.size() >= 3) {
            // Take the point just after the start point
            ref1 = new Point(((Bendpoint) bendpoints.get(1)).getLocation());
            conn.translateToAbsolute(ref1);
            // Take the point just before the end point
            ref2 = new Point(((Bendpoint) bendpoints.get(bendpoints.size() - 2)).getLocation());
            conn.translateToAbsolute(ref2);
        } else {
            ref1 = new Point(((Bendpoint) bendpoints.get(0)).getLocation());
            conn.translateToAbsolute(ref1);
            ref2 = new Point(((Bendpoint) bendpoints.get(bendpoints.size() - 1)).getLocation());
            conn.translateToAbsolute(ref2);
        }

        A_POINT.setLocation(conn.getSourceAnchor().getLocation(ref1));
        conn.translateToRelative(A_POINT);
        points.addPoint(A_POINT);

        if (bendpoints.size() > 2) {
            for (int i = 1; i < bendpoints.size() - 1; i++) {
                Bendpoint bp = (Bendpoint) bendpoints.get(i);
                points.addPoint(bp.getLocation());
            }
        }

        A_POINT.setLocation(conn.getTargetAnchor().getLocation(ref2));
        conn.translateToRelative(A_POINT);
        points.addPoint(A_POINT);
        conn.setPoints(points);
    }
}
