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
package org.eclipse.sirius.tests.suite.performance;

import org.eclipse.sirius.tests.unit.perf.scenario.modeler.ecore.EntitiesDiagramDeletePerfsTests;
import org.eclipse.sirius.tests.unit.perf.scenario.modeler.ecore.EntitiesDiagramLayersPerfsTests;
import org.eclipse.sirius.tests.unit.perf.scenario.modeler.ecore.EntitiesDiagramOpenPerfsTests;
import org.eclipse.sirius.tests.unit.perf.scenario.modeler.ecore.EntitiesDiagramRefreshPerfTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class EcoreModelerPerfTestSuite extends TestCase {

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
     * Creates the {@link junit.framework.TestSuite TestSuite} for all ecore
     * modeler test.
     * 
     * @return The test suite containing all ecore modeler tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Ecore modeler test suite");
        suite.addTestSuite(EntitiesDiagramDeletePerfsTests.class);
        suite.addTestSuite(EntitiesDiagramLayersPerfsTests.class);
        suite.addTestSuite(EntitiesDiagramRefreshPerfTests.class);
        suite.addTestSuite(EntitiesDiagramOpenPerfsTests.class);
        return suite;
    }

}
