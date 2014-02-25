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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;

/**
 * A Bracket specific {@link ResizableShapeLabelEditPolicy} to customize tether
 * feedback.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketResizableShapeLabelEditPolicy extends ResizableShapeLabelEditPolicy {

    /**
     * Overridden to customize the tether feedback.
     * 
     * {@inheritDoc}
     */
    @Override
    protected Point getReferencePoint() {
        Point referencePoint = null;
        if (getHost().getParent() instanceof AbstractConnectionEditPart) {
            PointList pointList = ((AbstractConnectionEditPart) getHost().getParent()).getConnectionFigure().getPoints();
            if (pointList.size() == 6) {
                Point originPoint = pointList.getPoint(BracketConnectionQuery.ORIGIN_POINT_INDEX);
                Point targetPoint = pointList.getPoint(BracketConnectionQuery.TARGET_POINT_INDEX);
                referencePoint = new Rectangle(originPoint, targetPoint).getCenter();
            } else {
                referencePoint = pointList.getMidpoint();
            }
        } else {
            referencePoint = ((GraphicalEditPart) getHost().getParent()).getFigure().getBounds().getLocation();
        }
        return referencePoint;
    }

}
