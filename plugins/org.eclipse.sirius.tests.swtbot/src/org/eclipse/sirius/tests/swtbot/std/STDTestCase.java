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
package org.eclipse.sirius.tests.swtbot.std;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * 
 * @author amartin
 */
public class STDTestCase extends TestSuite {

    /*
     * 
     * STD-CASE UNDOABLE sue to technical issue with SwtBot 050 023 =>
     * impossible to manipulate a runtime Eclipse 024 => automaticaly tested by
     * Junit test : ExtensionAccessTest.java 026 => out of Scope 027 => out of
     * Scope 029 => Out of Scope
     */

    // Still in development.
    /**
     * @return the test.
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        // suite.addTestSuite(STDTestCase001.class);
        // suite.addTestSuite(STDTestCase002.class);
        // suite.addTestSuite(STD013.class);
        // suite.addTestSuite(STD017.class);
        suite.addTestSuite(STD018.class);
        // suite.addTestSuite(STD019.class);
        // suite.addTestSuite(STD022.class);
        // suite.addTestSuite(STD025.class);
        // suite.addTestSuite(STDTestCase047.class); broken?=>pe pb du
        // UILocalSession
        // suite.addTestSuite(STDTestCase043.class);
        // suite.addTestSuite(STDTestCase044.class);
        // suite.addTestSuite(STDTestCase048.class);
        // suite.addTestSuite(STDTestCase049.class);
        return suite;
    }

    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(String args) {
        TestRunner.run(suite());
    }
}
