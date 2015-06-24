/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Condition to wait that a specific point of a {@link PolylineConnection} is on
 * the expected axis.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class BendpointLocationCondition extends DefaultCondition {
    /** Edge figure containing the point to check. */
    private PolylineConnection edgeFigure;

    /** Index of the point to check */
    private int index;

    /** True if the x axis is tested, false for y axis */
    private boolean isHorizontalAxis;

    /** The expected location on the given axis */
    private int expectedLocation;

    /** Failure message when a test fails */
    private String message = "";

    /**
     * A delta of one pixel can be tolerated, in case of zoom different from
     * 100% for example.
     */
    private boolean addOnePixelDelta;

    private int actual;

    /**
     * Default constructor.
     * 
     * @param edgeFigure
     *            Edge figure containing the point to check
     * @param index
     *            Index of the point to check
     * @param isHorizontalAxis
     *            True if the x axis is tested, false for y axis
     * @param expectedLocation
     *            The expected location on the given axis
     * @param message
     *            Failure message when a test fails
     * @param addOnePixelDelta
     *            A delta of one pixel can be tolerated, in case of zoom
     *            different from 100% for example
     */
    public BendpointLocationCondition(PolylineConnection edgeFigure, int index, boolean isHorizontalAxis, int expectedLocation, String message, boolean addOnePixelDelta) {
        this.edgeFigure = edgeFigure;
        this.index = index;
        this.isHorizontalAxis = isHorizontalAxis;
        this.expectedLocation = expectedLocation;
        this.message = message;
        this.addOnePixelDelta = addOnePixelDelta;
    }

    @Override
    public boolean test() throws Exception {
        if (isHorizontalAxis) {
            actual = edgeFigure.getPoints().getPoint(index).x;
        } else {
            actual = edgeFigure.getPoints().getPoint(index).y;
        }
        if (addOnePixelDelta) {
            // A delta of one pixel is tolerated in case of zoom
            // different from 100%
            return (expectedLocation - 1) <= actual && actual <= (expectedLocation + 1);
        } else {
            return expectedLocation == actual;
        }
    }

    @Override
    public String getFailureMessage() {
        return message + ", expected:<" + expectedLocation + "> but was:<" + actual + ">";
    }
}
