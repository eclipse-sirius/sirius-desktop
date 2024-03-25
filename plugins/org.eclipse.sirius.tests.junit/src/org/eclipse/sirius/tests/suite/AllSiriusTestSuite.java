/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.suite.common.AllCommonPluginTests;
import org.eclipse.sirius.tests.suite.common.AllCommonStandaloneTests;
import org.eclipse.sirius.tests.suite.diagram.AllDiagramPluginsTests;
import org.eclipse.sirius.tests.unit.common.EnvironmentReportTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllSiriusTestSuite extends TestCase {

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
            super(SiriusTestsPlugin.getDefault().getBundle(), "JUnit");
        }
    }

    /**
     * Add the gerrit part of the Junit tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addGerritPart(TestSuite suite) {
        suite.addTest(new JUnit4TestAdapter(JUnitBundlesReport.class));
        suite.addTest(AllCommonStandaloneTests.suite());
        // suite.addTest(AllTableStandaloneTests.suite());
        // suite.addTest(AllDiagramStandaloneTests.suite());
        // AllCommonPluginTests.addGerritPart(suite);
        // suite.addTest(AllTablePluginTests.suite());
        // AllDiagramPluginsTests.addGerritPart(suite);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.<BR>
     * This suite can also launch a specific test by setting the environment variable TEST_CLASS_NAME to the qualified
     * name of the expected class to launch.
     * 
     * @return The testsuite containing all the tests
     */
    @SuppressWarnings("unchecked")
    public static Test suite() {
        final TestSuite suite = new TestSuite("Sirius tests");
        String testClassQualifiedName = System.getenv("TEST_CLASS_NAME"); //$NON-NLS-1$
        if (testClassQualifiedName != null && testClassQualifiedName.length() > 0) {
            try {
                Class<?> testToLaunch = SiriusTestsPlugin.getDefault().getBundle().loadClass(testClassQualifiedName);
                if (TestCase.class.isAssignableFrom(testToLaunch)) {
                    suite.addTestSuite((Class<? extends TestCase>) testToLaunch);
                }
            } catch (ClassNotFoundException e) {
                fail("The class " + testClassQualifiedName + ", to launch for test specific case, has not been found.");
            }
        } else {
            addGerritPart(suite);
            AllCommonPluginTests.addNonGerritPart(suite);
            AllDiagramPluginsTests.addNonGerritPart(suite);
        }
        return suite;
    }

}
