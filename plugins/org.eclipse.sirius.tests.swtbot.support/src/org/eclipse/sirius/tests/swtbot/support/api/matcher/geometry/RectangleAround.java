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

import org.eclipse.draw2d.geometry.Rectangle;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Matcher to check if a specified rectangle is "around" another expected rectangle, that is to say, topleft point of the
 * specified rectangle is located in a circle centered on topleft point of expected rectangle and whom radius is equal
 * to provided distance, edge of the circle included. More over, both rectangles must have same heigh and width.
 * 
 * @author dlecan
 */
public class RectangleAround extends BaseMatcher<Rectangle> {

    private final int distance;

    private final Rectangle expected;

    /**
     * Constructor.
     * 
     * @param expected
     *            Expected rectangle.
     * @param distance
     *            Distance to use.
     */
    protected RectangleAround(Rectangle expected, int distance) {
        this.expected = expected;
        this.distance = distance;
    }

    @Override
    public boolean matches(Object item) {
        boolean result = false;
        if (item instanceof Rectangle other) {
            result = other.height == expected.height;
            result = result && other.width == expected.width;
            result = result && PointAround.isAround(expected.getTopLeft(), other.getTopLeft(), distance);
        }
        return result;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("A rectangle around ");
        description.appendValue(expected);
        description.appendText("and whom distance from this rectangle is less or equal to " + distance);
    }

    /**
     * Create matcher.
     * 
     * @param expected
     *            Expected rectangle.
     * @param distance
     *            Distance between.
     * @return Matcher.
     */
    public static Matcher<Rectangle> around(Rectangle expected, int distance) {
        return new RectangleAround(expected, distance);
    }

}
