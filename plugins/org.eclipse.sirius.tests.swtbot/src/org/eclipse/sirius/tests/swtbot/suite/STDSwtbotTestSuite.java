/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.suite;

import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.std.STD001;
import org.eclipse.sirius.tests.swtbot.std.STD002;
import org.eclipse.sirius.tests.swtbot.std.STD004;
import org.eclipse.sirius.tests.swtbot.std.STD005;
import org.eclipse.sirius.tests.swtbot.std.STD006;
import org.eclipse.sirius.tests.swtbot.std.STD007;
import org.eclipse.sirius.tests.swtbot.std.STD008;
import org.eclipse.sirius.tests.swtbot.std.STD009;
import org.eclipse.sirius.tests.swtbot.std.STD010;
import org.eclipse.sirius.tests.swtbot.std.STD011;
import org.eclipse.sirius.tests.swtbot.std.STD013;
import org.eclipse.sirius.tests.swtbot.std.STD017;
import org.eclipse.sirius.tests.swtbot.std.STD018;
import org.eclipse.sirius.tests.swtbot.std.STD019;
import org.eclipse.sirius.tests.swtbot.std.STD022;
import org.eclipse.sirius.tests.swtbot.std.STD025;
import org.eclipse.sirius.tests.swtbot.std.STD028;
import org.eclipse.sirius.tests.swtbot.std.STD030;
import org.eclipse.sirius.tests.swtbot.std.STD043;
import org.eclipse.sirius.tests.swtbot.std.STD044;
import org.eclipse.sirius.tests.swtbot.std.STD048;
import org.eclipse.sirius.tests.swtbot.std.STD049;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * 
 * @author lredor
 */
public class STDSwtbotTestSuite extends TestCase {
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
        final TestSuite suite = new TestSuite("STD SWTBOT test suite");
        addPart1(suite);
        addPart2(suite);
        return suite;
    }
    
    /**
     * Add the first part of the SWTbot tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */    
    public static void addPart1(TestSuite suite) {
        suite.addTestSuite(STD001.class);
        suite.addTestSuite(STD002.class);
        suite.addTestSuite(STD004.class);
        suite.addTestSuite(STD005.class);
        suite.addTestSuite(STD007.class);
        suite.addTestSuite(STD009.class);
        suite.addTestSuite(STD011.class);
        // suite.addTestSuite(STD013.class);
        // suite.addTestSuite(STD017.class);
		if (!(System.getProperty("os.name").contains("Linux") && TestsUtil.is202003Platform())) {
			suite.addTestSuite(STD018.class);
		}
        // // suite.addTestSuite(STD019.class); NOT YET COMMMITED
        // // suite.addTestSuite(STD022.class); NOT YET COMMMITED
        // suite.addTestSuite(STD025.class);
        // suite.addTestSuite(STD028.class);
        // // suite.addTestSuite(STD030.class); NOT YET COMMMITED
        // suite.addTestSuite(STD043.class);
        // suite.addTestSuite(STD044.class);
        // suite.addTestSuite(STD047.class);/// broken?=>pe pb du UILocalSession
        // suite.addTestSuite(STD048.class);
        // suite.addTestSuite(STD049.class);
    }
    
    /**
     * Add the second part of the SWTbot tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */    
    public static void addPart2(TestSuite suite) {
        suite.addTestSuite(STD006.class);
        suite.addTestSuite(STD008.class);
        suite.addTestSuite(STD010.class);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the
     * disabled test.
     * 
     * @return The test suite containing all the disabled tests.
     */
    public static Test disabledSuite() {
        final TestSuite suite = new TestSuite("STD Disabled SwtBot tests");

        suite.addTestSuite(STD013.class);
        suite.addTestSuite(STD017.class);
        suite.addTestSuite(STD019.class);
        suite.addTestSuite(STD022.class);
        suite.addTestSuite(STD025.class);
        suite.addTestSuite(STD028.class);
        suite.addTestSuite(STD030.class);
        suite.addTestSuite(STD043.class);
        suite.addTestSuite(STD044.class);
        suite.addTestSuite(STD048.class);
        suite.addTestSuite(STD049.class);

        return suite;
    }
}
