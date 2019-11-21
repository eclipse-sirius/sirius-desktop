/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.suite.diagram.sequence;

import org.eclipse.sirius.tests.unit.diagram.sequence.CollapseFilterSequenceMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.sequence.ExecutionTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.HeaderHeightTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.LifelineTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.LifelinesSouthCenteredBorderItemLocatorTest;
import org.eclipse.sirius.tests.unit.diagram.sequence.NewChildMenusExtensionTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.OperandFigureAndEdgesTest;
import org.eclipse.sirius.tests.unit.diagram.sequence.SequenceMessageFlagResetMigrationTest;
import org.eclipse.sirius.tests.unit.diagram.sequence.SiriusSequenceDiagramTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.structure.SequenceDiagramElementsIdentificationTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.structure.SequenceDiagramElementsNavigationTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.structure.SequenceDiagramElementsNavigationWithCFTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.structure.SequenceDiagramElementsParentEventsTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.structure.SequenceDiagramElementsSubEventsTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.template.TemplateToDiagramDescriptionTest;
import org.eclipse.sirius.tests.unit.diagram.sequence.vsm.edit.SequenceAdapterFactoryRegistryTest;
import org.eclipse.sirius.tests.unit.diagram.sequence.vsm.interpreted.expression.variables.AbstractToolDescription_Precondition_AbstractVariable_Access_Tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Test suite to run all the plug-in tests relative to sequence diagrams.
 * 
 * @author pcdavid
 */
public class AllSequenceDiagramsPluginTests extends TestCase {

    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(AllSequenceDiagramsPluginTests.suite());
    }

    /**
     * @return the suite of JUnit Plug-in tests for sequence diagrams.
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Sequence Diagrams Plug-in Tests");
        suite.addTestSuite(SiriusSequenceDiagramTests.class);
        suite.addTestSuite(SequenceAdapterFactoryRegistryTest.class);
        suite.addTestSuite(TemplateToDiagramDescriptionTest.class);
        suite.addTestSuite(NewChildMenusExtensionTests.class);
        suite.addTestSuite(SequenceDiagramElementsIdentificationTests.class);
        suite.addTestSuite(SequenceDiagramElementsNavigationTests.class);
        suite.addTestSuite(SequenceDiagramElementsNavigationWithCFTests.class);
        suite.addTestSuite(SequenceDiagramElementsSubEventsTests.class);
        suite.addTestSuite(SequenceDiagramElementsParentEventsTests.class);
        suite.addTestSuite(LifelinesSouthCenteredBorderItemLocatorTest.class);
        suite.addTestSuite(LifelineTests.class);
        suite.addTestSuite(ExecutionTests.class);
        suite.addTestSuite(HeaderHeightTests.class);
        suite.addTestSuite(AbstractToolDescription_Precondition_AbstractVariable_Access_Tests.class);
        suite.addTestSuite(CollapseFilterSequenceMigrationTest.class);
        suite.addTestSuite(SequenceMessageFlagResetMigrationTest.class);
        suite.addTestSuite(OperandFigureAndEdgesTest.class);
        return suite;
    }
}
