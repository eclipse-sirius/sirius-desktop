/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.suite.common;

import org.eclipse.sirius.tests.unit.common.EditingDomainFactoryServiceTests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllCommonStandaloneTests extends TestCase {

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
        final TestSuite suite = new TestSuite("Common Standalone Tests");
        suite.addTest(new JUnit4TestAdapter(EditingDomainFactoryServiceTests.class));
        // suite.addTest(new JUnit4TestAdapter(LabelProviderProviderServiceTests.class));
        // suite.addTest(new JUnit4TestAdapter(EditingDomainServicesTest.class));
        // suite.addTestSuite(SetIntersectionTest.class);
        // suite.addTestSuite(GSetIntersectionTest.class);
        // suite.addTestSuite(SiriusVariablesTest.class);
        // suite.addTestSuite(DiagramVariablesTest.class);
        // suite.addTestSuite(SiriusColorTest.class);
        // suite.addTestSuite(CartesianProductTestCase.class);
        // suite.addTestSuite(VisualBindingManagerTestCase.class);
        // suite.addTest(new JUnit4TestAdapter(FileUtilTest.class));
        // suite.addTestSuite(ContentHelperTests.class);
        // // suite.addTest(new JUnit4TestAdapter(AllContributionTests.class));
        // suite.addTestSuite(DragAndDropNodeTest.class);
        // suite.addTestSuite(TypeAssistantTests.class);
        // suite.addTestSuite(TypeContentProposalProviderTests.class);
        // suite.addTestSuite(SessionLabelTest.class);
        // suite.addTestSuite(InitInterpreterFromParsedVariableTaskTest.class);
        // suite.addTest(new JUnit4TestAdapter(EcoreIntrinsicExtenderTest.class));
        // suite.addTest(new JUnit4TestAdapter(MarkerRuntimeLoggerTest.class));
        // suite.addTestSuite(RefreshFromViewpointActivationTests.class);
        return suite;
    }
}
