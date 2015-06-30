/*******************************************************************************
 * Copyright (c) 2010,2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.suite.tree;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.sirius.tests.api.tools.RefreshWhenSeveralEditorsOpenTest;
import org.eclipse.sirius.tests.api.tools.TreeItemCreationToolsTest;
import org.eclipse.sirius.tests.api.tools.TreeItemDeletionToolsTest;
import org.eclipse.sirius.tests.api.tools.TreeItemDragAndDropToolTest;
import org.eclipse.sirius.tests.api.tools.TreeItemDragAndDropToolUsingRecordingCommandCompositionTest;
import org.eclipse.sirius.tests.api.tools.TreeItemEditionToolTest;
import org.eclipse.sirius.tests.api.tools.TreeItemRefreshWithToolsTest;
import org.eclipse.sirius.tests.unit.migration.InitializeElementsToSelectExpressionForTreeMigrationTest;
import org.eclipse.sirius.tests.unit.tree.MappingHierarchyTableTest;
import org.eclipse.sirius.tests.unit.tree.TreeItemOrderTests;
import org.eclipse.sirius.tests.unit.tree.TreeRefreshTests;
import org.eclipse.sirius.tests.unit.tree.TreeVariablesTest;
import org.eclipse.sirius.tests.unit.tree.tools.SelectionInTreeAfterToolExecutionTest;

/**
 * The designer main test suite for the tree component.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class AllTreePluginTests extends TestCase {

    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(AllTreePluginTests.suite());
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the tests
     * of the table component.
     * 
     * @return The testsuite containing all the tests of the tree component
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Tree Plugin Tests");
        suite.addTest(AllTreeStandaloneTests.suite());

        suite.addTestSuite(TreeItemDeletionToolsTest.class);
        suite.addTestSuite(TreeItemCreationToolsTest.class);
        // Put in disable suite for moment
        // suite.addTestSuite(TreeItemRefreshTest.class);
        suite.addTestSuite(MappingHierarchyTableTest.class);
        suite.addTestSuite(TreeItemEditionToolTest.class);
        suite.addTestSuite(TreeRefreshTests.class);
        suite.addTestSuite(TreeVariablesTest.class);
        suite.addTestSuite(TreeItemDragAndDropToolTest.class);
        suite.addTestSuite(TreeItemDragAndDropToolUsingRecordingCommandCompositionTest.class);
        suite.addTestSuite(RefreshWhenSeveralEditorsOpenTest.class);
        suite.addTestSuite(TreeItemOrderTests.class);
        suite.addTestSuite(TreeItemRefreshWithToolsTest.class);
        suite.addTestSuite(SelectionInTreeAfterToolExecutionTest.class);
        suite.addTestSuite(InitializeElementsToSelectExpressionForTreeMigrationTest.class);
        return suite;
    }

}
