/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.sequence.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;

import junit.framework.TestCase;

/**
 * Tests for the {@link Range} utility class.
 * 
 * @author pcdavid
 * 
 */
public class RangeTests extends TestCase {
    /**
     * Tests that it is possible to create an empty range.
     */
    public void testEmptyRangeCanBeCreated() {
        Range empty = Range.emptyRange();
        assertNotNull(empty);
        assertTrue(empty.isEmpty());
    }

    /**
     * Tests that an empty range produced with {@link Range#emptyRange()} does not include any element.
     */
    public void testEmptyRangeContainsNoValue() {
        Range empty = Range.emptyRange();
        assertFalse(empty.includes(0));
        assertFalse(empty.includes(-1));
        assertFalse(empty.includes(1));
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            assertFalse(empty.includes(rand.nextInt()));
        }
    }

    /**
     * Tests the both bounds of a range produced with {@link Range#emptyRange()} are <code>null</code>.
     */
    public void testEmptyRangeHasNaNBounds() {
        Range invalid = Range.emptyRange();
        assertTrue(Integer.MIN_VALUE == invalid.getLowerBound());
        assertTrue(Integer.MIN_VALUE == invalid.getUpperBound());
        assertTrue(0 == invalid.width());
    }

    /**
     * Tests that it is possible to create a range with a single element, i.e. start == end.
     */
    public void testAtomicRangeCanBeCreated() {
        Range atomic = new Range(1, 1);
        assertNotNull(atomic);
        assertFalse(atomic.isEmpty());
    }

    /**
     * Tests that an atomic range includes its value (i.e. the bounds are inclusive).
     */
    public void testAtomicRangeIncludesTheValue() {
        int theValue = 1;
        Range atomic = new Range(theValue, theValue);
        assertTrue(atomic.includes(theValue));
    }

    /**
     * Tests that an atomic range does not include the values immediately below or above its value.
     */
    public void testAtomicValueDoesNotContainsNeighboringValues() {
        int theValue = 1;
        Range atomic = new Range(theValue, theValue);
        assertFalse(atomic.includes(theValue - 1));
        assertFalse(atomic.includes(theValue + 1));
    }

    /**
     * Tests that is is possible to access the bounds of a range after its creation.
     */
    public void testRangeBoundsAreAccessible() {
        Range range = new Range(1, 3);
        assertEquals(1, range.getLowerBound());
        assertEquals(3, range.getUpperBound());
    }

    /**
     * Tests that trying to create a range with an upper bound below the lower bound is correctly detected as an error.
     */
    public void testCreatingRangeWithInvalidBoundsThrowsException() {
        try {
            new Range(1, -1);
            fail("Should throw an IllegalArgumentException");
        } catch (IllegalArgumentException iae) {
            // Expected.
        }
    }

    /**
     * Tests that values between the lower and upper bounds of the range are correctly detected as included in the
     * range.
     */
    public void testMiddleValuesAreIncludedInRange() {
        Range range = new Range(2, 10);
        for (int i = 2; i <= 10; i++) {
            assertTrue(range.includes(i));
        }
    }

    /**
     * Tests the clamp method.
     */
    public void testClampLowValue() {
        Range range = new Range(1, 5);
        assertEquals(1, range.clamp(0));
    }

    /**
     * Tests the clamp method.
     */
    public void testClampHighValue() {
        Range range = new Range(1, 5);
        assertEquals(5, range.clamp(7));
    }

    /**
     * Tests the clamp method.
     */
    public void testClampLowerBound() {
        Range range = new Range(1, 5);
        assertEquals(1, range.clamp(1));
    }

    /**
     * Tests the clamp method.
     */
    public void testClampUpperBound() {
        Range range = new Range(1, 5);
        assertEquals(5, range.clamp(5));
    }

    /**
     * Tests the clamp method.
     */
    public void testClampMiddleValue() {
        Range range = new Range(1, 5);
        assertEquals(3, range.clamp(3));
    }

    /**
     * Tests the intersection between two empty ranges.
     */
    public void testEmptyRangesIntersectionIsEmpty() {
        assertTrue(Range.emptyRange().intersection(Range.emptyRange()).isEmpty());
    }

    /**
     * Test the intersection of the empty range with a non-empty range.
     */
    public void testEmptyRangeIntersectionWithNonEmptyIsEmpty() {
        assertTrue(Range.emptyRange().intersection(new Range(0, 1)).isEmpty());
    }

    /**
     * Test the intersection of a non-empty range with the empty range.
     */
    public void testNonEmptyIntersectionWithEmptyRangeIsEmpty() {
        assertTrue(Range.emptyRange().intersection(new Range(0, 1)).isEmpty());
    }

    /**
     * Tests the intersection of an atomic range with itself.
     */
    public void testAtomicRangeIntersectionWithItself() {
        Range atomic = new Range(1, 1);
        assertEquals(atomic, atomic.intersection(atomic));
    }

    /**
     * Tests the intersection of a non-atomic range with itself.
     */
    public void testNonAtomicRangeIntersectionWithItself() {
        Range r = new Range(1, 2);
        assertEquals(r, r.intersection(r));
    }

    /**
     * Test that the intersection of non-overlapping ranges is empty.
     */
    public void testIntersectionOfNonOverlappingRanges() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(2, 3);
        assertTrue(r1.intersection(r2).isEmpty());
    }

    /**
     * Tests the intersection of two actually partially overlapping ranges.
     */
    public void testIntersectionOfPartiallyOverlappingRanges() {
        Range r1 = new Range(0, 2);
        Range r2 = new Range(1, 3);
        assertEquals(new Range(1, 2), r1.intersection(r2));
        assertEquals(new Range(1, 2), r2.intersection(r1));
    }

    public void testUnionEmptyWithEmpty() {
        Range result = Range.emptyRange().union(Range.emptyRange());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    public void testUnionEmptyWithNonEmpty() {
        Range r = new Range(0, 1);
        Range result = Range.emptyRange().union(r);
        assertNotNull(result);
        assertEquals(r, result);
    }

    public void testUnionNonEmptyWithEmpty() {
        Range r = new Range(0, 1);
        Range result = r.union(Range.emptyRange());
        assertNotNull(result);
        assertEquals(r, result);
    }

    public void testUnionRangeWithItSelf() {
        Range r = new Range(0, 1);
        Range result = r.union(r);
        assertNotNull(result);
        assertEquals(r, result);
    }

    public void testUnionWithIncludedRange() {
        Range r1 = new Range(0, 3);
        Range r2 = new Range(1, 2);
        Range result = r1.union(r2);
        assertNotNull(result);
        assertEquals(r1, result);
    }

    public void testUnionWithIncludingRange() {
        Range r1 = new Range(0, 3);
        Range r2 = new Range(1, 2);
        Range result = r2.union(r1);
        assertNotNull(result);
        assertEquals(r1, result);
    }

    public void testUnionBetweenAdjacentRanges() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(1, 2);
        Range result = r1.union(r2);
        assertNotNull(result);
        assertEquals(new Range(0, 2), result);
    }

    public void testUnionBetweenDisjointRanges() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(2, 3);
        Range result = r1.union(r2);
        assertNotNull(result);
        assertEquals(new Range(0, 3), result);
    }

    public void testShiftEmptyRange() {
        Range r = Range.emptyRange();
        Range result = r.shifted(10);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    public void testShiftRangeByZero() {
        Range r = new Range(1, 2);
        Range result = r.shifted(0);
        assertNotNull(result);
        assertEquals(r, result);
    }

    public void testShiftRangeByPositiveDistance() {
        Range r = new Range(1, 2);
        Range result = r.shifted(1);
        assertNotNull(result);
        assertEquals(new Range(2, 3), result);
    }

    public void testShiftRangeByNegativeDistance() {
        Range r = new Range(1, 2);
        Range result = r.shifted(-1);
        assertNotNull(result);
        assertEquals(new Range(0, 1), result);
    }

    public void testRangeOrderingByLowerBound() {
        Range r1 = new Range(1, 2);
        Range r2 = new Range(2, 3);
        List<Range> ranges = Arrays.asList(r2, r1);
        Collections.sort(ranges, RangeHelper.lowerBoundOrdering());
        assertSame(ranges.get(0), r1);
        assertSame(ranges.get(1), r2);
    }

    public void testEmptyRangeSortsBelowOthersOnLowerBoundsOrdering() {
        Range r1 = Range.emptyRange();
        Range r2 = new Range(-10000, -5000);
        List<Range> ranges = Arrays.asList(r2, r1);
        Collections.sort(ranges, RangeHelper.lowerBoundOrdering());
        assertSame(ranges.get(0), r1);
        assertSame(ranges.get(1), r2);
    }

    public void testRangeOrderingByUpperBound() {
        Range r1 = new Range(1, 5);
        Range r2 = new Range(2, 3);
        List<Range> ranges = Arrays.asList(r1, r2);
        Collections.sort(ranges, RangeHelper.upperBoundOrdering());
        assertSame(ranges.get(0), r2);
        assertSame(ranges.get(1), r1);
    }

    public void testEmptyRangeSortsBelowOthersOnUpperBoundsOrdering() {
        Range r1 = Range.emptyRange();
        Range r2 = new Range(-10000, -5000);
        List<Range> ranges = Arrays.asList(r2, r1);
        Collections.sort(ranges, RangeHelper.upperBoundOrdering());
        assertSame(ranges.get(0), r1);
        assertSame(ranges.get(1), r2);
    }

    public void testShrinkEmptyRange() {
        Range r = Range.emptyRange();
        Range result = r.shrinked(10);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    public void testShrinkRangeByZero() {
        Range r = new Range(1, 2);
        Range result = r.shrinked(0);
        assertNotNull(result);
        assertEquals(r, result);
    }

    public void testShrinkRangeByPositiveDistance() {
        Range r = new Range(0, 2);
        Range result = r.shrinked(1);
        assertNotNull(result);
        assertEquals(new Range(1, 1), result);
    }

    public void testShrinkRangeByIllegalDistance() {
        Range r = new Range(1, 2);

        try {
            r.shrinked(1);
            fail("An illegal argument exception is expected");
        } catch (IllegalArgumentException e) {
            // ok
        }
    }

    public void testShrinkRangeByNegativeDistance() {
        Range r = new Range(1, 2);

        try {
            r.shrinked(-1);
            fail("An illegal argument exception is expected");
        } catch (IllegalArgumentException e) {
            // ok
        }

    }

    public void testGrowEmptyRange() {
        Range r = Range.emptyRange();
        Range result = r.grown(10);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    public void testGrowRangeByZero() {
        Range r = new Range(1, 2);
        Range result = r.grown(0);
        assertNotNull(result);
        assertEquals(r, result);
    }

    public void testGrowRangeByPositiveDistance() {
        Range r = new Range(1, 2);
        Range result = r.grown(1);
        assertNotNull(result);
        assertEquals(new Range(0, 3), result);
    }

    public void testGrowRangeByNegativeDistance() {
        Range r = new Range(1, 2);
        try {
            r.grown(-1);
            fail("An illegal argument exception is expected");
        } catch (IllegalArgumentException e) {
            // ok
        }
    }
}
