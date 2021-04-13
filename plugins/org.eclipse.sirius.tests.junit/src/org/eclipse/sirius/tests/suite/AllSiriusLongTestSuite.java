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

import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.refresh.RefreshWithCustomizedStyleTests;
import org.eclipse.sirius.tests.unit.api.tools.SiriusControlTest;
import org.eclipse.sirius.tests.unit.common.EnvironmentReportTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCallCheckSequenceTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCallCheckTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCreateTargetDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerExistingTargetDiagramDiffSessionTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.MappingBasedSiriusFormatDataManagerExistingTargetSequenceDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * All JUnit long tests.
 * 
 * @author lredor
 */
public class AllSiriusLongTestSuite extends TestCase {

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
            super(SiriusTestsPlugin.getDefault().getBundle(), "JUnit long");
        }
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test classes that have at least one test
     * using {@link TestsUtil#shouldSkipLongTests()}.<BR>
     * This suite can also launch a specific test by setting the environment variable TEST_CLASS_NAME to the qualified
     * name of the expected class to launch.
     * 
     * @return The {@link junit.framework.TestSuite} containing all the tests
     */
    @SuppressWarnings("unchecked")
    public static Test suite() {
        final TestSuite suite = new TestSuite("Sirius long tests");
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
            if (TestsUtil.shouldRunLongTests()) {
                suite.addTestSuite(SiriusControlTest.class);
                suite.addTestSuite(RefreshWithCustomizedStyleTests.class);
                suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCallCheckSequenceTest.class));
                suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCallCheckTest.class));
                suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCreateTargetDiagramTest.class));
                suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramTest.class));
                suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerExistingTargetDiagramDiffSessionTest.class));
                suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest.class));
                suite.addTest(new JUnit4TestAdapter(MappingBasedSiriusFormatDataManagerExistingTargetSequenceDiagramTest.class));
                String platformVersion = Platform.getBundle("org.eclipse.core.runtime").getHeaders().get("Bundle-Version");
                if (!platformVersion.startsWith("3.5")) {
                    // This one is long (~9 minutes), so it is ignored when running
                    suite.addTest(new JUnit4TestAdapter(SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest.class));
                }
            }
        }
        return suite;
    }

}
