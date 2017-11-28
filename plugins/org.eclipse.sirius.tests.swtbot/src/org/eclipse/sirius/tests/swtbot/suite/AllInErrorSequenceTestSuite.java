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
package org.eclipse.sirius.tests.swtbot.suite;

import org.eclipse.sirius.tests.swtbot.sequence.ComplexInOutCombinedFragmentTest;
import org.eclipse.sirius.tests.swtbot.sequence.SyncCall2Test;
import org.eclipse.sirius.tests.swtbot.sequence.SyncCallTest;

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
public class AllInErrorSequenceTestSuite extends TestCase {

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
     * @return The test suite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("SwtBot tests on sequence diagram");

        final TestSuite unreliable = new TestSuite("Unreliable");
        final TestSuite inError = new TestSuite("In error");
        final TestSuite inDeadLock = new TestSuite("In dead lock");
        TestSuite current = null;

        /* Unreliable */
        current = unreliable;

        // current.addTestSuite(CombinedFragmentsTests.class);
        // current.addTestSuite(InteractionUseTwoClickCreationTests.class);
        // current.addTestSuite(InteractionUseTests.class);
        // current.addTestSuite(ExecutionMoveTests.class);
        // current.addTestSuite(InteractionUseResizeTests.class);
        // current.addTestSuite(CombinedFragmentsTwoClickCreationTests.class);
        // current.addTestSuite(StateBasicTests.class);
        // current.addTestSuite(InteractionUseMoveDownTests.class);

        /* In Error */
        current = inError;
        current.addTestSuite(SyncCallTest.class);
        current.addTestSuite(SyncCall2Test.class);
        current.addTestSuite(ComplexInOutCombinedFragmentTest.class);

        /* IN DEAD LOCK */
        current = inDeadLock;

        // if(!"true".equals(System.getProperty("org.eclipse.sirius.tests.skipUnreliableTests"))){
        suite.addTest(unreliable);
        // }

        // if(!"true".equals(System.getProperty("org.eclipse.sirius.tests.skipErrorTests"))){
        suite.addTest(inError);
        // }

        // if(!"true".equals(System.getProperty("org.eclipse.sirius.tests.skipDeadlockTests"))){
        suite.addTest(inDeadLock);
        // }
        return suite;
    }

}
