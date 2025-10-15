/**
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.matcher.geometry;

import org.eclipse.draw2d.geometry.Point;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Matcher to check if a specified point is "around" another expected point, that is to say, specified point is located
 * in a circle centered on expected point and whom radius is equal to provided distance, edge of the circle included.
 * 
 * @author dlecan
 */
public class PointAround extends BaseMatcher<Point> {

    private final int distance;

    private final Point expected;

    /**
     * Constructor.
     * 
     * @param expected
     *            Expected point.
     * @param distance
     *            Distance to use.
     */
    protected PointAround(Point expected, int distance) {
        this.expected = expected;
        this.distance = distance;
    }

    @Override
    public boolean matches(Object item) {
        if (item instanceof Point other) {
            return isAround(expected, other, distance);
        }
        return false;
    }

    /**
     * Check if a point is around another.
     * 
     * @param expected
     *            Expected point.
     * @param actual
     *            Actual point
     * @param distance
     *            Distance to check.
     * @return <code>true</code> if actual point is around expected point.
     */
    protected static boolean isAround(Point expected, Point actual, int distance) {
        return actual.getDistance(expected) <= distance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("A point around ");
        description.appendValue(expected);
        description.appendText("and whom distance from this point is less or equal to " + distance);
    }

    /**
     * Create matcher.
     * 
     * @param expected
     *            Expected point.
     * @param distance
     *            Distance between.
     * @return Matcher.
     */
    public static Matcher<Point> around(Point expected, int distance) {
        return new PointAround(expected, distance);
    }

}
