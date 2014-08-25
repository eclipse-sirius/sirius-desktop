/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * SWTBot condition to test if a edge has moved.
 * 
 * @author edugueperoux
 */
public class ConnectionEditPartChangedCondition extends DefaultCondition {

    private AbstractConnectionEditPart dEdgeEditPart;

    private PointList pointList;

    /**
     * Default constructor.
     * 
     * @param abstractConnectionEditPart
     *            the edge editPart to test the connection figure move
     */
    public ConnectionEditPartChangedCondition(AbstractConnectionEditPart abstractConnectionEditPart) {
        this.dEdgeEditPart = abstractConnectionEditPart;
        Connection connection = abstractConnectionEditPart.getConnectionFigure();
        this.pointList = connection.getPoints();
    }

    /**
     * {@inheritDoc}
     */
    public boolean test() throws Exception {
        return changedPoints();
    }

    /**
     * @return
     */
    private boolean changedPoints() {
        PointList newPointList = dEdgeEditPart.getConnectionFigure().getPoints();
        if (newPointList.size() == pointList.size()) {
            for (int i = 0; i < newPointList.size(); i++) {
                Point newPoint = newPointList.getPoint(i);
                Point point = pointList.getPoint(i);
                if (!newPoint.equals(point)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public String getFailureMessage() {
        return "Edge not moved";
    }

}
