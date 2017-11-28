/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.suite;

import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.suite.common.AllCommonPluginTests;
import org.eclipse.sirius.tests.suite.common.AllCommonStandaloneTests;
import org.eclipse.sirius.tests.suite.diagram.AllDiagramPluginsTests;
import org.eclipse.sirius.tests.suite.diagram.AllDiagramStandaloneTests;
import org.eclipse.sirius.tests.suite.table.AllTablePluginTests;
import org.eclipse.sirius.tests.suite.table.AllTableStandaloneTests;
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
        suite.addTest(AllTableStandaloneTests.suite());
        suite.addTest(AllDiagramStandaloneTests.suite());
        AllCommonPluginTests.addGerritPart(suite);
        suite.addTest(AllTablePluginTests.suite());
        AllDiagramPluginsTests.addGerritPart(suite);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Sirius tests");
        addGerritPart(suite);
        AllCommonPluginTests.addNonGerritPart(suite);
        AllDiagramPluginsTests.addNonGerritPart(suite);
        return suite;
    }

}
