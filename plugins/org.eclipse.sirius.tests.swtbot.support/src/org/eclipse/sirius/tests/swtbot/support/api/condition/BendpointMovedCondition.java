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
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * This {@link org.eclipse.swtbot.swt.finder.waits.ICondition} waits until a
 * specific bendpoint has been moved.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 * 
 */
public final class BendpointMovedCondition extends DefaultCondition {

    private final ConnectionEditPart connectionEditPart;

    private final Point initialLocation;

    private boolean checkFirstBendpoint;

    private boolean checkLastBendpoint;

    private int bendpointPosition;

    /**
     * Default constructor.
     * 
     * @param connectionEditPart
     *            {@link ConnectionEditPart} to check if a bendpoint has moved
     * @param initialLocation
     *            initial location of the bendpoint that should move
     */
    public BendpointMovedCondition(ConnectionEditPart connectionEditPart, Point initialLocation) {
        this.connectionEditPart = connectionEditPart;
        this.initialLocation = initialLocation;
    }

    /**
     * Call this method to check the first bendpoint of the connection.
     * 
     * @return the current condition
     */
    public BendpointMovedCondition checkFirstBendpoint() {
        this.checkFirstBendpoint = true;
        this.checkLastBendpoint = false;
        this.bendpointPosition = -1;
        return this;
    }

    /**
     * Call this method to check the last bendpoint of the connection.
     * 
     * @return the current condition
     */
    public BendpointMovedCondition checkLastBendpoint() {
        this.checkFirstBendpoint = false;
        this.checkLastBendpoint = true;
        this.bendpointPosition = -1;
        return this;
    }

    /**
     * Call this method to check the bendpoint at the given position of the
     * connection.
     * 
     * @param bendpointPos
     *            the position of the bendpoint to test
     * @return the current condition
     */
    public BendpointMovedCondition checkBendpointAtPosition(int bendpointPos) {
        this.checkFirstBendpoint = false;
        this.checkLastBendpoint = false;
        this.bendpointPosition = bendpointPos;
        return this;
    }

    @Override
    public boolean test() throws Exception {
        boolean location = false;
        PointList points = ((PolylineConnectionEx) connectionEditPart.getFigure()).getPoints();
        if (checkFirstBendpoint) {
            location = !initialLocation.equals(points.getFirstPoint());
        } else if (checkLastBendpoint) {
            location = !initialLocation.equals(points.getLastPoint());
        } else if (bendpointPosition >= 0 || bendpointPosition < points.size()) {
            location = !initialLocation.equals(points.getPoint(bendpointPosition));
        }
        return location;
    }

    @Override
    public String getFailureMessage() {
        return "The bendpoint at location " + initialLocation + " has not been moved";
    }
}
