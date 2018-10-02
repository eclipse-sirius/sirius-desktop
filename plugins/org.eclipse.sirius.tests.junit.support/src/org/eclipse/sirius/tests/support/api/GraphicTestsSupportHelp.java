/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import org.eclipse.draw2d.geometry.Point;
import org.junit.Assert;

/**
 * Graphical helpers for tests.
 * 
 * 
 * @author dlecan
 */
public class GraphicTestsSupportHelp {

    /**
     * Constructor.
     */
    protected GraphicTestsSupportHelp() {
        // Nothing
    }

    /**
     * Asserts that two points are equal. If they are not an
     * AssertionFailedError is thrown with the given message.
     * 
     * @param message
     *            Message
     * @param expected
     *            Expected point
     * @param actual
     *            Actual point
     * @param delta
     *            X and Y delta
     */
    public static void assertEquals(final String message, final Point expected, final Point actual, final int delta) {
        GraphicTestsSupportHelp.assertEquals(message, expected, actual, delta, delta);
    }

    /**
     * Asserts that two points are equal.
     * 
     * @param expected
     *            Expected point
     * @param actual
     *            Actual point
     * @param delta
     *            X and Y delta
     */
    public static void assertEquals(final Point expected, final Point actual, final int delta) {
        GraphicTestsSupportHelp.assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two points are equal. If they are not an
     * AssertionFailedError is thrown with the given message.
     * 
     * @param message
     *            Message
     * @param expected
     *            Expected point
     * @param actual
     *            Actual point
     * @param xDelta
     *            X delta
     * @param yDelta
     *            Y delta
     */
    public static void assertEquals(final String message, final Point expected, final Point actual, final int xDelta, final int yDelta) {
        String xfailMessage = "";
        String yfailMessage = "";
        if (message != null) {
            xfailMessage += message;
            yfailMessage += message;
        }
        xfailMessage += " for x coordinate";
        yfailMessage += " for y coordinate";
        Assert.assertEquals(xfailMessage, expected.x, actual.x, xDelta);
        Assert.assertEquals(yfailMessage, expected.y, actual.y, yDelta);
    }

    /**
     * Asserts that two points are equal.
     * 
     * @param expected
     *            Expected point
     * @param actual
     *            Actual point
     * @param xDelta
     *            X delta
     * @param yDelta
     *            Y delta
     */
    public static void assertEquals(final Point expected, final Point actual, final int xDelta, final int yDelta) {
        GraphicTestsSupportHelp.assertEquals(null, expected, actual, xDelta, yDelta);
    }

}
