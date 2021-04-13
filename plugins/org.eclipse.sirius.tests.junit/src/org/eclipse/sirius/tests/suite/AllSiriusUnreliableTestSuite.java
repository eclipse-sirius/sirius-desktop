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
package org.eclipse.sirius.tests.suite;

import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.editors.EntitiesSpecificEditorTests;
import org.eclipse.sirius.tests.unit.api.modelingproject.SaveWhenNoEditorsTests;
import org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEdgeConditionalStyleTest;
import org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEndUserOverrideTest;
import org.eclipse.sirius.tests.unit.api.session.SessionManagerListener2Tests;
import org.eclipse.sirius.tests.unit.api.session.TablesAndEntitiesDirtyTest;
import org.eclipse.sirius.tests.unit.api.tools.SiriusControlAndDeleteRepresentationTest;
import org.eclipse.sirius.tests.unit.api.tools.SiriusControlTest;
import org.eclipse.sirius.tests.unit.common.EnvironmentReportTest;
import org.eclipse.sirius.tests.unit.common.RestoreSessionFromEditorInputTests;
import org.eclipse.sirius.tests.unit.common.WorkspaceResourceSyncTestCase;
import org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.AcceleoMTInterpreterOnPackageImportTests;
import org.eclipse.sirius.tests.unit.diagram.control.HierarchicalControlTest;
import org.eclipse.sirius.tests.unit.diagram.migration.RunRepairTest;
import org.eclipse.sirius.tests.unit.diagram.modelers.dynamicinstance.DynamicInstanceTests;
import org.eclipse.sirius.tests.unit.diagram.tools.palette.ModifySessionOutsideEclipseTest;
import org.eclipse.sirius.tests.unit.perf.common.Session1MillionTests;

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
            super(SiriusTestsPlugin.getDefault().getBundle(), "JUnit unreliable");
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
        final TestSuite suite = new TestSuite("Sirius unreliable tests");
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
                suite.addTestSuite(AcceleoMTInterpreterOnPackageImportTests.class);
                suite.addTestSuite(DynamicInstanceTests.class);
                suite.addTestSuite(EdgeRoutingStyleEdgeConditionalStyleTest.class);
                suite.addTestSuite(EdgeRoutingStyleEndUserOverrideTest.class);
                suite.addTestSuite(EntitiesSpecificEditorTests.class);
                suite.addTestSuite(HierarchicalControlTest.class);
                suite.addTestSuite(ModifySessionOutsideEclipseTest.class);
                suite.addTestSuite(RestoreSessionFromEditorInputTests.class);
                suite.addTestSuite(RunRepairTest.class);
                suite.addTestSuite(SaveWhenNoEditorsTests.class);
                suite.addTestSuite(Session1MillionTests.class);
                suite.addTestSuite(SessionManagerListener2Tests.class);
                suite.addTestSuite(SiriusControlAndDeleteRepresentationTest.class);
                suite.addTestSuite(SiriusControlTest.class);
                suite.addTestSuite(TablesAndEntitiesDirtyTest.class);
                suite.addTestSuite(WorkspaceResourceSyncTestCase.class);
            }
        }
        return suite;
    }

}
