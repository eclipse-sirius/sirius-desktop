/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.tests.suite.common.AllCommonStandaloneTests;
import org.eclipse.sirius.tests.suite.diagram.AllDiagramStandaloneTests;
import org.eclipse.sirius.tests.suite.table.AllTableStandaloneTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * A test suite which contains only the standalone JUnit tests which do not
 * depend on Eclipse. These can be run as simple "JUnit Test" instead of
 * "JUnit Plug-in Test", which is generally much faster.
 * 
 * @author pcdavid
 */
public class AllSiriusStandaloneTestSuite extends TestCase {
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
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the
     * standalone JUnit tests.
     * 
     * @return The test suite containing all the pure JUnit tests.
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("Sirius Standalone Tests");
        suite.addTest(AllCommonStandaloneTests.suite());
        suite.addTest(AllTableStandaloneTests.suite());
        suite.addTest(AllDiagramStandaloneTests.suite());
        return suite;
    }
}
