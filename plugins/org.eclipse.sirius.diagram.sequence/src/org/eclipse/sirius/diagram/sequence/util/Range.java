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
package org.eclipse.sirius.diagram.sequence.util;

import com.google.common.base.Preconditions;

/**
 * Represents a closed interval between two numbers. The starting and ending
 * points of the range are considered inside the interval.
 * 
 * @author pcdavid
 */
public class Range {
    /**
     * A sentinel object to represent the empty range. It is technically not
     * empty but this instance is treated specially.
     */
    private static final Range EMPTY_RANGE = new Range(0, 0);

    private final int lower;

    private final int upper;

    /**
     * Constructor.
     * 
     * @param lower
     *            the lower bound of the range.
     * @param upper
     *            the upper bound of the range.
     */
    public Range(int lower, int upper) {
        Preconditions.checkArgument(lower <= upper);
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * Returns the single Range instance which represents an empty range (
     * <code>null</code> object pattern).
     * 
     * @return an empty range, which contains no element.
     */
    public static Range emptyRange() {
        return EMPTY_RANGE;
    }

    /**
     * Tests whether the range is empty or not.
     * 
     * @return <code>true</code> if this range is empty.
     */
    public boolean isEmpty() {
        return this == EMPTY_RANGE;
    }

    /**
     * Two ranges are equal if they are both empty or have the same bounds.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        boolean result;
        if (this == obj) {
            // The empty range is a singleton, so this case covers empty ==
            // empty.
            result = true;
        } else if (obj instanceof Range) {
            Range that = (Range) obj;
            if (this.isEmpty() || that.isEmpty()) {
                result = false;
            } else {
                result = this.lower == that.lower && this.upper == that.upper;
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = lower;
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = upper;
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Returns the lower bound of this range, or <code>NaN</code> if the range
     * is empty.
     * 
     * @return the lower bound of this range, or <code>NaN</code> if the range
     *         is empty.
     */
    public int getLowerBound() {
        return isEmpty() ? Integer.MIN_VALUE : lower;
    }

    /**
     * Returns the upper bound of this range, or <code>NaN</code> if the range
     * is empty.
     * 
     * @return the upper bound of this range, or <code>NaN</code> if the range
     *         is empty.
     */
    public int getUpperBound() {
        return isEmpty() ? Integer.MIN_VALUE : upper;
    }

    /**
     * Constrain a value inside this range. If the specified value if less than
     * this range's lower bound (resp. greater than this range's upper bound),
     * returns the lower bound (resp. the upper bound). Otherwise return the
     * value itself.
     * 
     * @param value
     *            the value to constrain.
     * @return the value closest to the specified input value which is inside
     *         the range.
     */
    public int clamp(int value) {
        final int result;
        if (isEmpty()) {
            result = Integer.MIN_VALUE;
        } else if (value < lower) {
            result = lower;
        } else if (upper < value) {
            result = upper;
        } else {
            result = value;
        }
        return result;
    }

    /**
     * Tests whether on element is included or not in the range (inclusively).
     * 
     * @param value
     *            the element to test.
     * @return <code>true</code> if the element is included in the range.
     */
    public boolean includes(int value) {
        if (!isEmpty()) {
            return lower <= value && value <= upper;
        } else {
            return false;
        }
    }

    /**
     * Returns the width of this range.
     * 
     * @return the width of this range.
     */
    public int width() {
        if (isEmpty()) {
            return 0;
        } else {
            return upper - lower;
        }
    }

    /**
     * Returns the value in the middle of this range.
     * 
     * @return the value in the middle of this range.
     */
    public int middleValue() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return lower + width() / 2;
        }
    }

    /**
     * Returns a new range which is the union of this range and the other. The
     * union range is the smallest range which includes all the elements of both
     * initial ranges.
     * 
     * @param other
     *            the other range.
     * @return the union of this range and <code>other</code>.
     */
    public Range union(Range other) {
        final Range result;
        if (this.isEmpty()) {
            result = other;
        } else if (other.isEmpty()) {
            result = this;
        } else {
            result = new Range(Math.min(this.lower, other.lower), Math.max(this.upper, other.upper));
        }
        return result;
    }

    /**
     * Returns the intersection of this range and another one.
     * 
     * @param other
     *            the other range.
     * @return the intersection of this range and <code>other</code>.
     */
    public Range intersection(Range other) {
        Range result;
        if (this.isEmpty() || other.isEmpty()) {
            result = Range.emptyRange();
        } else {
            int l = Math.max(this.lower, other.lower);
            int u = Math.min(this.upper, other.upper);
            if (l <= u) {
                result = new Range(l, u);
            } else {
                result = Range.emptyRange();
            }
        }
        return result;
    }

    /**
     * Tests whether or not this range intersects with another one.
     * 
     * @param other
     *            the other range.
     * @return <code>true</code> if this range intersects with
     *         <code>other</code>.
     */
    public boolean intersects(Range other) {
        return !intersection(other).isEmpty();
    }

    /**
     * Tests whether the specified range is included inside this one.
     * 
     * @param other
     *            the other range to test for inclusion.
     * @return <code>true</code> if <code>other</code> is included in this
     *         range.
     */
    public boolean includes(Range other) {
        return this.intersection(other).equals(other);
    }

    /**
     * Tests whether only one bound of the specified range is included inside
     * this one.
     * 
     * @param other
     *            the other range to test for inclusion.
     * @return <code>true</code> if <code>other</code> is included in this
     *         range.
     */
    public boolean includesOneBoundOnly(Range other) {
        return (this.includes(other.getLowerBound()) && !this.includes(other.getUpperBound())) || (!this.includes(other.getLowerBound()) && this.includes(other.getUpperBound()));
    }

    /**
     * Tests whether only one bound of the specified range is included inside
     * this one.
     * 
     * @param other
     *            the other range to test for inclusion.
     * @return <code>true</code> if <code>other</code> is included in this
     *         range.
     */
    public boolean includesAtLeastOneBound(Range other) {
        return includes(other) || includesOneBoundOnly(other);
    }

    /**
     * Returns a new range corresponding to this range shifted by the specified
     * distance (which can be positive or negative). Shifting an empty range
     * produces an empty range. Otherwise the resulting range has the same width
     * as the original.
     * 
     * @param distance
     *            the distance to shift this range.
     * @return the new shifted range.
     */
    public Range shifted(int distance) {
        if (isEmpty()) {
            return Range.EMPTY_RANGE;
        } else {
            return new Range(lower + distance, upper + distance);
        }
    }

    /**
     * Returns a new range corresponding to this range grown by the specified
     * distance (which must be positive). Growing an empty range produces an
     * empty range. Otherwise the resulting range has a bigger width than
     * original (+ 2 * distance). The middle value of this range is kept
     * constant.
     * 
     * @param distance
     *            the positive distance to grow this range.
     * @return the new grown range.
     */
    public Range grown(int distance) {
        Preconditions.checkArgument(distance >= 0);
        if (isEmpty()) {
            return Range.EMPTY_RANGE;
        } else {
            return new Range(lower - distance, upper + distance);
        }
    }

    /**
     * Returns a new range corresponding to this range shrinked by the specified
     * distance (which must be positive). Shrinking an empty range produces an
     * empty range. Otherwise the resulting range has a smaller width than
     * original (- 2 * distance). The middle value of this range is kept
     * constant.
     * 
     * @param distance
     *            the positive distance to shrink this range.
     * @return the new grown range.
     */
    public Range shrinked(int distance) {
        Preconditions.checkArgument(distance >= 0);
        if (isEmpty()) {
            return Range.EMPTY_RANGE;
        } else {
            Preconditions.checkArgument(width() - 2 * distance >= 0);
            return new Range(lower + distance, upper - distance);
        }
    }

    /**
     * Returns a percentage of how much the given absolute number is inside this
     * range.
     * 
     * @param n
     *            an integer.
     * @return a number between 0.0 and 1.0 representing how much <code>n</code>
     *         is inside this range (0.0 meaning n is the range's lower bound or
     *         below, 1.0 meaning n is the range's upper bound or above), or
     *         Double.NaN if this range is empty.
     */
    public double getProportionalLocation(int n) {
        double result = Double.NaN;
        if (!this.isEmpty()) {
            double offset = this.clamp(n) - this.getLowerBound();
            double width = this.width();
            if (width != 0.0) {
                result = offset / width;
            } else {
                result = 0.0;
            }
        }
        return result;
    }

    /**
     * Validates that this range bounds does not match any bounds of the other
     * range.
     * 
     * @param other
     *            the other range.
     * @return if this range bounds does not match any bounds of the other
     *         range.
     */
    public boolean validatesBoundsAreDifferent(Range other) {
        return getLowerBound() != other.getLowerBound() && getLowerBound() != other.getUpperBound() && getUpperBound() != other.getLowerBound() && getUpperBound() != other.getUpperBound();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (!isEmpty()) {
            return "[" + lower + ", " + upper + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else {
            return "[<empty range>]";
        }
    }
}
