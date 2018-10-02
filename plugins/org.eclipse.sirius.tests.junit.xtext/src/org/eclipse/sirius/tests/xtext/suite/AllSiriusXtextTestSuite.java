/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.tests.xtext.suite;

import org.eclipse.sirius.tests.unit.common.EnvironmentReportTest;
import org.eclipse.sirius.tests.xtext.SiriusXtextTestsPlugin;
import org.eclipse.sirius.tests.xtext.unit.XTextModelSynchronizationTests;

import junit.framework.JUnit4TestAdapter;
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

    public static class XTextJUnitBundlesReport extends EnvironmentReportTest {
        public XTextJUnitBundlesReport() {
            super(SiriusXtextTestsPlugin.getDefault().getBundle(), "XText-JUnit");
        }
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the
     * tests.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Sirius xtext tests");

        suite.addTest(new JUnit4TestAdapter(XTextJUnitBundlesReport.class));
        suite.addTestSuite(XTextModelSynchronizationTests.class);
        return suite;
    }

}
