/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.xtext.suite;

import org.eclipse.sirius.tests.unit.common.EnvironmentReportTest;
import org.eclipse.sirius.tests.xtext.SiriusXtextTestsPlugin;
import org.eclipse.sirius.tests.xtext.unit.XTextModelSynchronizationTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllSiriusXtextTestSuite extends TestCase {

    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(suite());
    }

    public static class JUnitBundlesReport extends EnvironmentReportTest {
        public JUnitBundlesReport() {
            super(SiriusXtextTestsPlugin.getDefault().getBundle(), "JUnit");
        }
    }
    
    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the tests.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Sirius xtext tests");

        suite.addTestSuite(XTextModelSynchronizationTests.class);
        return suite;
    }

}
