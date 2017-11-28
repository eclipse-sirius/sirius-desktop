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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.ext.base.collect.SetIntersection;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

public class SetIntersectionTest extends TestCase {

    protected SetIntersection<String> biSet;

    private static final String E = "e";

    private static final String D = "d";

    private static final String C = "c";

    private static final String B = "b";

    private static final String A = "a";

    /**
     * test.
     */

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        biSet = new SetIntersection<String>();
    }

    public void testAddInBiset() {
        final String[] originalSet = { A, A, A, B, C };
        String[] newSet = { B, C, E };

        for (int i = 0; i < originalSet.length; i++) {
            biSet.addInOld(originalSet[i]);
        }
        for (int i = 0; i < newSet.length; i++) {
            biSet.addInNew(newSet[i]);
        }

        Iterable<String> kept = biSet.getKeptElements();
        Collection<String> newE = biSet.getNewElements();
        Iterable<String> removedE = biSet.getRemovedElements();

        String[] expectedKept = { B, C };
        String[] expectedNew = { E };
        String[] expectedRemoved = { A, A, A };

        assertDeepEquals("kept set", expectedKept, kept);
        assertDeepEquals("new set", expectedNew, newE);
        assertDeepEquals("removed set", expectedRemoved, removedE);

    }

    public void testKeepOrderWithNew() {
        final String[] originalSet = {};
        final String[] newSet = { A, B, C, D, E, };

        for (int i = 0; i < originalSet.length; i++) {
            biSet.addInOld(originalSet[i]);
        }
        for (int i = 0; i < newSet.length; i++) {
            biSet.addInNew(newSet[i]);
        }

        Collection<String> all = Lists.newArrayList(biSet.getAllElements());

        String[] expectedAll = { A, B, C, D, E };

        assertDeepEqualsWithOrder("all set", expectedAll, all);
    }

    public void testKeepOrderWithOld() {
        final String[] originalSet = { B, C, A, E, D };
        final String[] newSet = { A, B, C, D, E, };

        for (int i = 0; i < originalSet.length; i++) {
            biSet.addInOld(originalSet[i]);
        }
        for (int i = 0; i < newSet.length; i++) {
            biSet.addInNew(newSet[i]);
        }

        Collection<String> all = Lists.newArrayList(biSet.getAllElements());

        String[] expectedAll = { A, B, C, D, E };

        assertDeepEqualsWithOrder("all set", expectedAll, all);
    }

    public void testKeptElementsReference() {
        final String[] originalSet = { A };
        final String[] newSet = { new String("a") };

        for (int i = 0; i < originalSet.length; i++) {
            biSet.addInOld(originalSet[i]);
        }
        for (int i = 0; i < newSet.length; i++) {
            biSet.addInNew(newSet[i]);
        }

        Iterable<String> kept = biSet.getKeptElements();

        assertTrue("there is at least one element", kept.iterator().hasNext());
        assertTrue("the kept element reference should be the old one", kept.iterator().next() == A);
    }

    /**
     * test.
     */
    public void testAddInBisetOtherWayAround() {
        final String[] originalSet = { A, A, A, B, C };
        String[] newSet = { B, C, E };

        for (int i = 0; i < newSet.length; i++) {
            biSet.addInNew(newSet[i]);
        }
        for (int i = 0; i < originalSet.length; i++) {
            biSet.addInOld(originalSet[i]);
        }

        Iterable<String> kept = biSet.getKeptElements();
        Collection<String> newE = biSet.getNewElements();
        Iterable<String> removedE = biSet.getRemovedElements();

        String[] expectedKept = { B, C };
        String[] expectedNew = { E };
        String[] expectedRemoved = { A, A, A };

        assertDeepEquals("kept set", expectedKept, kept);
        assertDeepEquals("new set", expectedNew, newE);
        assertDeepEquals("removed set", expectedRemoved, removedE);

    }

    /**
     * test.
     */
    public void testKeepRequestedOrder() {
        final String[] originalSet = { A, B, C };
        String[] newSet = { A, B, E, C };
        for (int i = 0; i < originalSet.length; i++) {
            biSet.addInOld(originalSet[i]);
        }

        for (int i = 0; i < newSet.length; i++) {
            biSet.addInNew(newSet[i]);
        }
        assertDeepEqualsWithOrder("the order should be the same as the new set", newSet, Lists.newArrayList(biSet.getAllElements()));

    }

    /**
     * assert deep equals.
     * 
     * @param message
     *            message.
     * @param expected
     *            expected result.
     * @param result
     *            result.
     */
    protected void assertDeepEqualsWithOrder(String message, String[] expected, List<String> result) {
        assertEquals(message, expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            String expectedStr = expected[i];
            assertTrue(message, expectedStr.equals(result.get(i)));
        }
    }

    /**
     * assert deep equals.
     * 
     * @param message
     *            message.
     * @param expected
     *            expected result.
     * @param result
     *            result.
     */
    protected void assertDeepEquals(String message, String[] expected, Collection<String> result) {
        assertEquals(message, expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            String expectedStr = expected[i];
            assertTrue(message, result.contains(expectedStr));
        }
    }

    /**
     * assert deep equals.
     * 
     * @param message
     *            message.
     * @param expected
     *            expected result.
     * @param result
     *            result.
     */
    protected void assertDeepEquals(String message, String[] expected, Iterable<String> result) {
        assertDeepEquals(message, expected, Lists.newArrayList(result));
    }

    /**
     * assert deep equals.
     * 
     * @param message
     *            message.
     * @param expected
     *            expected result.
     * @param result
     *            result.
     */
    protected void assertDeepEqualsWithOrder(String message, String[] expected, Collection<String> result) {
        assertEquals(message, expected.length, result.size());
        int i = 0;
        for (String sResult : result) {
            String expectedStr = expected[i];
            assertTrue(message, sResult.equals(expectedStr));
            i++;
        }
    }
}
