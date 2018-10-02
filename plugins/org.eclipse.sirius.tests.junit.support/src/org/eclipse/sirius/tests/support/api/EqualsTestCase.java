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

import org.junit.Assert;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * Base class for equality tests.
 * 
 * @author dlecan
 */
public abstract class EqualsTestCase extends TestCase {
    /**
     * Number of iterations.
     */
    protected static final int NUM_ITERATIONS = 20;

    /**
     * Object.
     */
    protected Object eq1;

    /**
     * Object.
     */
    protected Object eq2;

    /**
     * Object.
     */
    protected Object eq3;

    /**
     * Object.
     */
    protected Object neq;

    /**
     * Creates and returns an instance of the class under test.
     * 
     * @return a new instance of the class under test; each object returned from
     *         this method should compare equal to each other.
     * @throws Exception
     *             Creation error.
     */
    protected abstract Object createInstance() throws Exception;

    /**
     * Creates and returns an instance of the class under test.
     * 
     * @return a new instance of the class under test; each object returned from
     *         this method should compare equal to each other, but not to the
     *         objects returned from {@link #createInstance() createInstance}.
     * @throws Exception
     *             Creation error.
     */
    protected abstract Object createNotEqualInstance() throws Exception;

    /**
     * Sets up the test fixture.
     * 
     * @throws Exception
     *             Setup error.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        eq1 = createInstance();
        eq2 = createInstance();
        eq3 = createInstance();
        neq = createNotEqualInstance();

        // We want these assertions to yield errors, not failures.
        try {
            Assert.assertNotNull("createInstance() returned null", eq1);
            Assert.assertNotNull("2nd createInstance() returned null", eq2);
            Assert.assertNotNull("3rd createInstance() returned null", eq3);
            Assert.assertNotNull("createNotEqualInstance() returned null", neq);

            Assert.assertNotSame(eq1, eq2);
            Assert.assertNotSame(eq1, eq3);
            Assert.assertNotSame(eq1, neq);
            Assert.assertNotSame(eq2, eq3);
            Assert.assertNotSame(eq2, neq);
            Assert.assertNotSame(eq3, neq);

            Assert.assertEquals("1st and 2nd equal instances of different classes", eq1.getClass(), eq2.getClass());
            Assert.assertEquals("1st and 3rd equal instances of different classes", eq1.getClass(), eq3.getClass());
            Assert.assertEquals("1st equal instance and not-equal instance of different classes", eq1.getClass(), neq.getClass());
        } catch (final AssertionFailedError ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    /**
     * Tests whether <code>equals</code> holds up against a new
     * <code>Object</code> (should always be <code>false</code>).
     */
    public final void testEqualsAgainstNewObject() {
        final Object o = new Object();

        SiriusAssert.assertNotEquals(eq1, o);
        SiriusAssert.assertNotEquals(eq2, o);
        SiriusAssert.assertNotEquals(eq3, o);
        SiriusAssert.assertNotEquals(neq, o);
    }

    /**
     * Tests whether <code>equals</code> holds up against <code>null</code>.
     */
    public final void testEqualsAgainstNull() {
        SiriusAssert.assertNotEquals("1st vs. null", eq1, null);
        SiriusAssert.assertNotEquals("2nd vs. null", eq2, null);
        SiriusAssert.assertNotEquals("3rd vs. null", eq3, null);
        SiriusAssert.assertNotEquals("not-equal vs. null", neq, null);
    }

    /**
     * Tests whether <code>equals</code> holds up against objects that should
     * not compare equal.
     */
    public final void testEqualsAgainstUnequalObjects() {
        SiriusAssert.assertNotEquals("1st vs. not-equal", eq1, neq);
        SiriusAssert.assertNotEquals("2nd vs. not-equal", eq2, neq);
        SiriusAssert.assertNotEquals("3rd vs. not-equal", eq3, neq);

        SiriusAssert.assertNotEquals("not-equal vs. 1st", neq, eq1);
        SiriusAssert.assertNotEquals("not-equal vs. 2nd", neq, eq2);
        SiriusAssert.assertNotEquals("not-equal vs. 3rd", neq, eq3);
    }

    /**
     * Tests whether <code>equals</code> is <em>consistent</em>.
     */
    public final void testEqualsIsConsistentAcrossInvocations() {
        for (int i = 0; i < EqualsTestCase.NUM_ITERATIONS; ++i) {
            testEqualsAgainstNewObject();
            testEqualsAgainstNull();
            testEqualsAgainstUnequalObjects();
            testEqualsIsReflexive();
            testEqualsIsSymmetricAndTransitive();
        }
    }

    /**
     * Tests whether <code>equals</code> is <em>reflexive</em>.
     */
    public final void testEqualsIsReflexive() {
        Assert.assertEquals("1st equal instance", eq1, eq1);
        Assert.assertEquals("2nd equal instance", eq2, eq2);
        Assert.assertEquals("3rd equal instance", eq3, eq3);
        Assert.assertEquals("not-equal instance", neq, neq);
    }

    /**
     * Tests whether <code>equals</code> is <em>symmetric</em> and
     * <em>transitive</em>.
     */
    public final void testEqualsIsSymmetricAndTransitive() {
        Assert.assertEquals("1st vs. 2nd", eq1, eq2);
        Assert.assertEquals("2nd vs. 1st", eq2, eq1);

        Assert.assertEquals("1st vs. 3rd", eq1, eq3);
        Assert.assertEquals("3rd vs. 1st", eq3, eq1);

        Assert.assertEquals("2nd vs. 3rd", eq2, eq3);
        Assert.assertEquals("3rd vs. 2nd", eq3, eq2);
    }
}
