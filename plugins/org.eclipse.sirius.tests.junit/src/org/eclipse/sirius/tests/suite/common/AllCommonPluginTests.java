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
package org.eclipse.sirius.tests.suite.common;

import org.eclipse.sirius.tests.unit.api.session.SessionManagerListener2Tests;
import org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.AcceleoMTInterpreterOnPackageImportTests;
import org.eclipse.sirius.tests.unit.diagram.migration.RepairWithActivatedFiltersTest;
import org.eclipse.sirius.tests.unit.multipageeditor.SiriusMultiPageEditorTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllCommonPluginTests extends TestCase {

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
     * Add the gerrit part of the Junit tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addGerritPart(TestSuite suite) {
        suite.addTestSuite(AcceleoMTInterpreterOnPackageImportTests.class);
    }

    /**
     * Add the tests which for one reason or another are not part of the suite
     * launched on each Gerrit verification.
     * 
     * @param suite
     *            the suite to add the tests into.
     */
    public static void addNonGerritPart(TestSuite suite) {
        // This one takes too long (12 minutes) to be part of the Gerrit suite.
        suite.addTestSuite(AcceleoMTInterpreterOnPackageImportTests.class);
        // The ones below are "blacklisted" for now because they caused at least
        // one false-negative Gerrit Verification job
        suite.addTestSuite(SessionManagerListener2Tests.class);
        suite.addTestSuite(RepairWithActivatedFiltersTest.class);
        // We don't officially handle Sirius editors inside MultiPageEditorPart
        // so it is not necessary to run it in Gerrit.
        suite.addTestSuite(SiriusMultiPageEditorTest.class);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Common Plugin Tests");
        addGerritPart(suite);
        return suite;
    }
}
