/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.suite;

import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.tests.swtbot.tree.ConditionalTreeItemStyleDescriptionTest;
import org.eclipse.sirius.tests.swtbot.tree.ContextMenuTreeTest;
import org.eclipse.sirius.tests.swtbot.tree.CopyTreeRepresentationTest;
import org.eclipse.sirius.tests.swtbot.tree.CreatedDTreeItemsSelectionTests;
import org.eclipse.sirius.tests.swtbot.tree.DeleteSeveralElementOnTree;
import org.eclipse.sirius.tests.swtbot.tree.DirectEditTreeItemTest;
import org.eclipse.sirius.tests.swtbot.tree.DisplayLabelOnNodeTest;
import org.eclipse.sirius.tests.swtbot.tree.NavigateInTreeRepresentationTest;
import org.eclipse.sirius.tests.swtbot.tree.OpenCloseCreateDeleteTreeRepresentationTest;
import org.eclipse.sirius.tests.swtbot.tree.RefreshWithPropertiesViewTest;
import org.eclipse.sirius.tests.swtbot.tree.RenameTreeRepresentationTest;
import org.eclipse.sirius.tests.swtbot.tree.TreeItemMappingTest;
import org.eclipse.sirius.tests.swtbot.tree.TreeItemPopupMenusTest;
import org.eclipse.sirius.tests.swtbot.tree.TreeItemPopupMenusWithJavaActionTest;
import org.eclipse.sirius.tests.swtbot.tree.TreeItemStyleDescriptionTest;
import org.eclipse.sirius.tests.swtbot.tree.TreeRefreshWithF5ShortcutTests;
import org.eclipse.sirius.tests.swtbot.tree.TreeUIPermissionAuthorityTests;
import org.eclipse.sirius.tests.swtbot.tree.TreeUIRefreshTests;
import org.osgi.framework.Version;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Test suite.
 * 
 * @author nlepine
 */
public class TreeSwtbotTestSuite extends TestCase {
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
        final TestSuite suite = new TestSuite("Tree SWTBOT test suite");
        suite.addTestSuite(CopyTreeRepresentationTest.class);
        // Test to be executed only from Eclipse Mars because dependent of fix
        // from Bug 460206
        if (Platform.getBundle("org.eclipse.emf.transaction").getVersion().compareTo(Version.parseVersion("1.9.0")) >= 0) {
            suite.addTestSuite(CreatedDTreeItemsSelectionTests.class);
        }
        suite.addTestSuite(OpenCloseCreateDeleteTreeRepresentationTest.class);
        suite.addTestSuite(RenameTreeRepresentationTest.class);
        suite.addTestSuite(NavigateInTreeRepresentationTest.class);
        suite.addTestSuite(DirectEditTreeItemTest.class);
        suite.addTestSuite(ConditionalTreeItemStyleDescriptionTest.class);
        suite.addTestSuite(TreeItemStyleDescriptionTest.class);
        suite.addTestSuite(TreeItemMappingTest.class);
        suite.addTestSuite(TreeItemPopupMenusTest.class);
        suite.addTestSuite(RefreshWithPropertiesViewTest.class);
        suite.addTestSuite(DeleteSeveralElementOnTree.class);
        suite.addTestSuite(ContextMenuTreeTest.class);
        suite.addTestSuite(TreeUIPermissionAuthorityTests.class);
        suite.addTestSuite(TreeUIRefreshTests.class);
        suite.addTestSuite(TreeRefreshWithF5ShortcutTests.class);
        suite.addTestSuite(DisplayLabelOnNodeTest.class);
        return suite;
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the disabled test.
     * 
     * @return The test suite containing all the disabled tests.
     */
    public static Test disabledSuite() {
        final TestSuite suite = new TestSuite("Tree Disabled SwtBot tests");
        // These tests use external java action defined in tree.tests plugin
        // enable these tests when hudson build considers junit tree tests
        // plugin.
        // add the action in swtbot test plugin causes wrong behavior with the
        // others swtbot tests (like full screen for example)
        suite.addTestSuite(TreeItemPopupMenusWithJavaActionTest.class);
        return suite;
    }
}
