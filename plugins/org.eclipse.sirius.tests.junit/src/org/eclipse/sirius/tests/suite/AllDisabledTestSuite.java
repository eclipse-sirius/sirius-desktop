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
package org.eclipse.sirius.tests.suite;

import org.eclipse.sirius.tests.unit.api.tools.DisabledDragAndDropForEdgesonEdgesFromDiagramTest;
import org.eclipse.sirius.tests.unit.common.EMFTransansactionTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation.EntitiesDiagramDropTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation.EntitiesDiagramTooltipsRefreshTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation.EntitiesDiagramTooltipsTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.CombinedFragmentsTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.CreateMessageTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.InstanceRoleResizableEditPolicyTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionUseTests;
import org.eclipse.sirius.tests.unit.diagram.session.SessionOpeningWithAirdNoDiagramTest;
import org.eclipse.sirius.tests.unit.diagram.tools.PaneBasedSelectionWizardTests;

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
public class AllDisabledTestSuite extends TestCase {
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
        TestSuite suite = new TestSuite("Disabled Sirius Tests (known to currently fail)");

        suite.addTestSuite(EntitiesDiagramDropTests.class);
        suite.addTestSuite(EntitiesDiagramTooltipsRefreshTests.class);
        suite.addTestSuite(EntitiesDiagramTooltipsTests.class);
        suite.addTestSuite(SessionOpeningWithAirdNoDiagramTest.class);
        suite.addTestSuite(PaneBasedSelectionWizardTests.class);
        suite.addTestSuite(EMFTransansactionTests.class);
        suite.addTestSuite(DisabledDragAndDropForEdgesonEdgesFromDiagramTest.class);

        // Sequence
        suite.addTestSuite(InteractionUseTests.class);
        suite.addTestSuite(CombinedFragmentsTests.class);
        suite.addTestSuite(InstanceRoleResizableEditPolicyTests.class);
        suite.addTestSuite(CreateMessageTests.class);

        /*
         * uncommenting the next test leads to a deadlock
         */
        // suite.addTestSuite(GMFTests.class);
        return suite;
    }

}
