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
package org.eclipse.sirius.tests.suite;

import org.eclipse.sirius.tests.unit.diagram.control.ControlTest;
import org.eclipse.sirius.tests.unit.diagram.filter.CompositeFilterTest;
import org.eclipse.sirius.tests.unit.diagram.synchronization.UnsynchronizedMappingAndDeleteFromOutsideEditorTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This special test suite contains all the tests which are known to currently
 * fail. We use it to enhance the debug process on Jenkins.
 * 
 * @author hmarchadour
 */
public class AllInErrorTestSuite extends TestCase {
    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(suite());
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {

        TestSuite suite = new TestSuite("Junit tests");
        final TestSuite unreliable = new TestSuite("Unreliable");
        final TestSuite inError = new TestSuite("In error");
        final TestSuite inDeadLock = new TestSuite("In dead lock");
        TestSuite current = null;

        /* Unreliable */
        current = unreliable;
        current.addTest(org.eclipse.sirius.tests.suite.AllSiriusUnreliableTestSuite.suite());
        // The below tests do not use method "TestsUtil.shouldSkipUnreliableTests()" and have not been modified for a
        // long time so I let them here.
        current.addTestSuite(ControlTest.class);
        current.addTestSuite(CompositeFilterTest.class);
        current.addTestSuite(UnsynchronizedMappingAndDeleteFromOutsideEditorTests.class);

        /* In Error */
        current = inError;

        /* IN DEAD LOCK */
        current = inDeadLock;

        // if(!"true".equals(System.getProperty("org.eclipse.sirius.tests.skipUnreliableTests"))){
        suite.addTest(unreliable);
        // }

        // if(!"true".equals(System.getProperty("org.eclipse.sirius.tests.skipErrorTest"))){
        suite.addTest(inError);
        // }

        // if(!"true".equals(System.getProperty("org.eclipse.sirius.tests.skipDeadlockTests"))){
        suite.addTest(inDeadLock);
        // }
        return suite;
    }

}
