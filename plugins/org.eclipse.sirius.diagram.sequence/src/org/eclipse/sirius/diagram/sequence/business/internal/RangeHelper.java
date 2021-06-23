/*******************************************************************************
 * Copyright (c) 2014, 2021 Obeo.
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
package org.eclipse.sirius.diagram.sequence.business.internal;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

/**
 * Helper functions and orderings for the Range type.
 * 
 * @author pcdavid
 */
public final class RangeHelper {
    private RangeHelper() {
        // Prevent instanciation
    }

    /**
     * A function to obtain the lower bound of a range.
     * 
     * @return a function to obtain the lower bound of a range.
     */
    public static Function<Range, Integer> lowerBoundFunction() {
        return LowerBoundFunction.INSTANCE;
    }

    /**
     * A function to obtain the upper bound of a range.
     * 
     * @return a function to obtain the upper bound of a range.
     */
    public static Function<Range, Integer> upperBoundFunction() {
        return UpperBoundFunction.INSTANCE;
    }

    /**
     * A function to obtain the width of a range.
     * 
     * @return a function to obtain the width of a range.
     */
    public static Function<Range, Integer> widthFunction() {
        return WidthFunction.INSTANCE;
    }

    /**
     * Returns an ordering suitable to sort ranges by their lower bound.
     * 
     * @return an ordering suitable to sort ranges by their lower bound.
     */
    public static Ordering<Range> lowerBoundOrdering() {
        return Ordering.natural().onResultOf(RangeHelper.lowerBoundFunction());
    }

    /**
     * Returns an ordering suitable to sort ranges by their upper bound.
     * 
     * @return an ordering suitable to sort ranges by their upper bound.
     */
    public static Ordering<Range> upperBoundOrdering() {
        return Ordering.natural().onResultOf(RangeHelper.upperBoundFunction());
    }

    /**
     * Returns a range representing the vertical range occupied by a rectangle.
     * 
     * @param rect
     *            the rectangle.
     * @return the vertical range of the rectangle.
     */
    public static Range verticalRange(Rectangle rect) {
        return new Range(rect.y, rect.y + rect.height);
    }

    /**
     * Returns a range representing the horizontal range occupied by a rectangle.
     * 
     * @param rect
     *            the rectangle.
     * @return the horizontal range of the rectangle.
     */
    public static Range horizontalRange(Rectangle rect) {
        return new Range(rect.x, rect.x + rect.width);
    }

    /**
     * The "lowerBound" function.
     */
    private enum LowerBoundFunction implements Function<Range, Integer> {
        INSTANCE;

        @Override
        public Integer apply(Range from) {
            return from.getLowerBound();
        }

        @Override
        public String toString() {
            return "lowerBound"; //$NON-NLS-1$
        }
    }

    /**
     * The "upperBound" function.
     */
    private enum UpperBoundFunction implements Function<Range, Integer> {
        INSTANCE;

        @Override
        public Integer apply(Range from) {
            return from.getUpperBound();
        }

        @Override
        public String toString() {
            return "upperBound"; //$NON-NLS-1$
        }
    }

    /**
     * The "widthBound" function.
     */
    private enum WidthFunction implements Function<Range, Integer> {
        INSTANCE;

        @Override
        public Integer apply(Range from) {
            return from.width();
        }

        @Override
        public String toString() {
            return "width"; //$NON-NLS-1$
        }
    }
}
