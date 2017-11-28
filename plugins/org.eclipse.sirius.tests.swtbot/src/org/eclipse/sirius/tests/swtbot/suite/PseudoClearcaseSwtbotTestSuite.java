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

import org.eclipse.sirius.tests.swtbot.pseudoclearcase.DiagramPseudoClearCaseTest;
import org.eclipse.sirius.tests.swtbot.pseudoclearcase.TablePseudoClearCaseTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * 
 * @author lredor
 */
public class PseudoClearcaseSwtbotTestSuite extends TestCase {
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
        final TestSuite suite = new TestSuite("Pseudo Clearcase SWTBOT test suite");
        // TODO DLE : theses tests seem to freeze CI
        // suite.addTestSuite(DiagramPseudoClearCaseTest.class);
        // TODO DLE : need to investigate why table representation doesn't open
        // before timeout
        // suite.addTestSuite(TablePseudoClearCaseTest.class);
        return suite;
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the
     * disabled test.
     * 
     * @return The test suite containing all the disabled tests.
     */
    public static Test disabledSuite() {
        final TestSuite suite = new TestSuite("Pseudo Clearcase Disabled SwtBot tests");

        // TODO DLE : theses tests seem to freeze CI
        suite.addTestSuite(DiagramPseudoClearCaseTest.class);

        // TODO DLE : need to investigate why table representation doesn't open
        // before timeout
        suite.addTestSuite(TablePseudoClearCaseTest.class);

        return suite;
    }
}
