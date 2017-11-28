/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.suite.tree;

import org.eclipse.sirius.tests.api.tools.TreeItemRefreshTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This special test suite contains all the tests which are known to currently
 * fail.
 * 
 * @author pcdavid
 */
public class AllTreeDisabledTestSuite extends TestCase {
    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(AllTreeDisabledTestSuite.suite());
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("Disabled Sirius Tests (known to currently fail)");

        suite.addTestSuite(TreeItemRefreshTest.class);
        return suite;
    }

}
