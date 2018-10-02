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

/**
 * Base class for equality tests.
 * 
 * @author dlecan
 */
public abstract class EqualsHashCodeTestCase extends EqualsTestCase {

    /**
     * Tests the <code>hashCode</code> contract.
     */
    public final void testHashCodeContract() {
        Assert.assertEquals("1st vs. 2nd", eq1.hashCode(), eq2.hashCode());
        Assert.assertEquals("1st vs. 3rd", eq1.hashCode(), eq3.hashCode());
        Assert.assertEquals("2nd vs. 3rd", eq2.hashCode(), eq3.hashCode());
    }

    /**
     * Tests the consistency of <code>hashCode</code>.
     */
    public final void testHashCodeIsConsistentAcrossInvocations() {
        final int eq1Hash = eq1.hashCode();
        final int eq2Hash = eq2.hashCode();
        final int eq3Hash = eq3.hashCode();
        final int neqHash = neq.hashCode();

        for (int i = 0; i < EqualsTestCase.NUM_ITERATIONS; ++i) {
            Assert.assertEquals("1st equal instance", eq1Hash, eq1.hashCode());
            Assert.assertEquals("2nd equal instance", eq2Hash, eq2.hashCode());
            Assert.assertEquals("3rd equal instance", eq3Hash, eq3.hashCode());
            Assert.assertEquals("not-equal instance", neqHash, neq.hashCode());
        }
    }
}
