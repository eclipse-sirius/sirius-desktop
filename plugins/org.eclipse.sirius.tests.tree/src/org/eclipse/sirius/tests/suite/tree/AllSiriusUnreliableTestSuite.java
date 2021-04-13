/*******************************************************************************
 * Copyright (c) 2021 Obeo.
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
package org.eclipse.sirius.tests.suite.tree;

import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.common.EnvironmentReportTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * All JUnit unreliable tests.
 * 
 * @author lredor
 */
public class AllSiriusUnreliableTestSuite extends TestCase {

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
            super(SiriusTestsPlugin.getDefault().getBundle(), "JUnit unreliable tree");
        }
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test classes that have at least one test
     * using {@link TestsUtil#shouldSkipUnreliableTests()}.<BR>
     * This suite can also launch a specific test by setting the environment variable TEST_CLASS_NAME to the qualified
     * name of the expected class to launch.
     * 
     * @return The {@link junit.framework.TestSuite} containing all the tests
     */
    @SuppressWarnings("unchecked")
    public static Test suite() {
        final TestSuite suite = new TestSuite("Sirius unreliable tree tests");
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
            suite.addTest(new JUnit4TestAdapter(JUnitBundlesReport.class));
            if (TestsUtil.shouldRunUnreliableTests()) {
                // Currently no long test. This class avoids to have an error of kind
                // "Failed to execute goal org.eclipse.tycho:tycho-surefire-plugin:2.0.0:test (default-test) on project
                // org.eclipse.sirius.tests.tree: No tests found."
            }
        }
        return suite;
    }

}
