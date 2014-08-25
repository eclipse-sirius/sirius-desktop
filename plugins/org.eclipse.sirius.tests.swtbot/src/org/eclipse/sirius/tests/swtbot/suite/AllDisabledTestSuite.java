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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * All Disabled SWTBot tests.
 * 
 * @author mporhel
 */
public class AllDisabledTestSuite extends TestCase {

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
        final TestSuite suite = new TestSuite("Disabled SwtBot tests");

        suite.addTest(AllTestSuite.disabledSuite());
        suite.addTest(PseudoClearcaseSwtbotTestSuite.disabledSuite());
        suite.addTest(SequenceSwtBotTestSuite.disabledSuite());
        suite.addTest(STDSwtbotTestSuite.disabledSuite());
        suite.addTest(TableSwtbotTestSuite.disabledSuite());
        suite.addTest(TreeSwtbotTestSuite.disabledSuite());
        suite.addTest(UMLSwtbotTestSuite.disabledSuite());

        return suite;
    }

}
