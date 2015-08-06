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

import org.eclipse.sirius.tests.swtbot.BorderedNodeCreationNearCollapsedTest;
import org.eclipse.sirius.tests.swtbot.BorderedNodeCreationNearCollapsedWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.BorderedNodeCreationTest;
import org.eclipse.sirius.tests.swtbot.BorderedNodeCreationWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.CollapsedBorderedNodeCreationNearCollapsedTest;
import org.eclipse.sirius.tests.swtbot.CollapsedBorderedNodeCreationNearCollapsedWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.CollapsedBorderedNodeCreationTest;
import org.eclipse.sirius.tests.swtbot.CollapsedBorderedNodeCreationWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.ContainerCreationTest;
import org.eclipse.sirius.tests.swtbot.ContainerCreationWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.DNodeListCreationTest;
import org.eclipse.sirius.tests.swtbot.DNodeListCreationWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.EdgeCreationPositionTest;
import org.eclipse.sirius.tests.swtbot.EdgeCreationPositionWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.EdgeWithBorderNodeCreationPositionWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.NodeCreationPositionTest;
import org.eclipse.sirius.tests.swtbot.NodeCreationTest;
import org.eclipse.sirius.tests.swtbot.NodeCreationWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.NoteCreationTest;
import org.eclipse.sirius.tests.swtbot.NoteCreationWithSnapToGridTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * All SWTBot tests.
 * 
 * @author lredor
 */
public class AllCreationTestSuite extends TestCase {

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
        final TestSuite suite = new TestSuite("Sirius SwtBot tests");

        suite.addTestSuite(NoteCreationTest.class);
        suite.addTestSuite(NodeCreationPositionTest.class);
        suite.addTestSuite(NodeCreationTest.class);
        suite.addTestSuite(ContainerCreationTest.class);
        suite.addTestSuite(DNodeListCreationTest.class);
        suite.addTestSuite(BorderedNodeCreationTest.class);
        suite.addTestSuite(BorderedNodeCreationNearCollapsedTest.class);
        suite.addTestSuite(CollapsedBorderedNodeCreationTest.class);
        suite.addTestSuite(CollapsedBorderedNodeCreationNearCollapsedTest.class);

        suite.addTestSuite(NoteCreationWithSnapToGridTest.class);
        // suite.addTestSuite(NodeCreationPositionWithSnapToGridTest.class);
        suite.addTestSuite(NodeCreationWithSnapToGridTest.class);
        suite.addTestSuite(ContainerCreationWithSnapToGridTest.class);
        suite.addTestSuite(DNodeListCreationWithSnapToGridTest.class);
        suite.addTestSuite(BorderedNodeCreationWithSnapToGridTest.class);
        suite.addTestSuite(BorderedNodeCreationNearCollapsedWithSnapToGridTest.class);
        suite.addTestSuite(CollapsedBorderedNodeCreationWithSnapToGridTest.class);
        suite.addTestSuite(CollapsedBorderedNodeCreationNearCollapsedWithSnapToGridTest.class);
        suite.addTestSuite(EdgeCreationPositionTest.class);
        suite.addTestSuite(EdgeCreationPositionWithSnapToGridTest.class);
        suite.addTestSuite(EdgeWithBorderNodeCreationPositionWithSnapToGridTest.class);

        return suite;
    }
}
