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
package org.eclipse.sirius.tests.suite.diagram.sequence;

import org.eclipse.sirius.tests.unit.common.AllContentsTests;
import org.eclipse.sirius.tests.unit.common.ContentsTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.actions.ReorderingJavaActionTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.util.RangeTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.vsm.color.SequenceColorTest;
import org.eclipse.sirius.tests.unit.diagram.sequence.vsm.interpreted.expression.variables.SequenceVariablesTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Test suite to run all the non plug-in unit tests relative to sequence
 * diagrams.
 * 
 * @author pcdavid
 */
public class AllSequenceDiagramsStandaloneTests extends TestCase {

    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(AllSequenceDiagramsStandaloneTests.suite());
    }

    /**
     * @return the suite of stand-alone JUnit tests for sequence diagrams.
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Sequence Diagrams Standalone Tests");
        suite.addTestSuite(RangeTests.class);
        suite.addTestSuite(ContentsTests.class);
        suite.addTestSuite(AllContentsTests.class);
        suite.addTestSuite(ReorderingJavaActionTests.class);
        suite.addTestSuite(SequenceVariablesTest.class);
        suite.addTestSuite(SequenceColorTest.class);
        return suite;
    }
}
