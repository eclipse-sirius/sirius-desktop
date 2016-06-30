/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.suite;

import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.tests.swtbot.table.CellEditionTest;
import org.eclipse.sirius.tests.swtbot.table.ContextMenuTableTest;
import org.eclipse.sirius.tests.swtbot.table.CreatedDLinesSelectionTests;
import org.eclipse.sirius.tests.swtbot.table.DeleteHideSeveralLineOnTable;
import org.eclipse.sirius.tests.swtbot.table.DeleteLineWithDELShortcutTest;
import org.eclipse.sirius.tests.swtbot.table.HideRevealTableColumnsTest;
import org.eclipse.sirius.tests.swtbot.table.HideRevealTableLinesTest;
import org.eclipse.sirius.tests.swtbot.table.NavigationDescriptionFromTableAndTreeTest;
import org.eclipse.sirius.tests.swtbot.table.ReadOnlyColumnTest;
import org.eclipse.sirius.tests.swtbot.table.RenameTableRepresentationTest;
import org.eclipse.sirius.tests.swtbot.table.TableRefreshWithF5ShortcutTests;
import org.eclipse.sirius.tests.swtbot.table.TableUIPermissionAuthorityTests;
import org.eclipse.sirius.tests.swtbot.table.TableUIRefreshTests;
import org.osgi.framework.Version;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Test suite.
 * 
 * @author dlecan
 */
public class TableSwtbotTestSuite extends TestCase {
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
        final TestSuite suite = new TestSuite("Table SWTBOT test suite");
        addPart1(suite);
        addPart2(suite);
        return suite;
    }
    /**
     * Add the first part of the SWTbot tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addPart1(TestSuite suite) {
        suite.addTestSuite(HideRevealTableColumnsTest.class);
        suite.addTestSuite(HideRevealTableLinesTest.class);
        suite.addTestSuite(RenameTableRepresentationTest.class);
        suite.addTestSuite(DeleteHideSeveralLineOnTable.class);
        // Test to be executed only from Eclipse Mars because dependent of fix
        // from Bug 460206
        if (Platform.getBundle("org.eclipse.emf.transaction").getVersion().compareTo(Version.parseVersion("1.9.0")) >= 0) {
            suite.addTestSuite(CreatedDLinesSelectionTests.class);
        }
        suite.addTestSuite(ContextMenuTableTest.class);
        suite.addTestSuite(TableUIPermissionAuthorityTests.class);
        suite.addTestSuite(DeleteLineWithDELShortcutTest.class);
        suite.addTestSuite(TableRefreshWithF5ShortcutTests.class);
        suite.addTestSuite(CellEditionTest.class);
        suite.addTestSuite(ReadOnlyColumnTest.class);
    }
    /**
     * Add the second part of the SWTbot tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addPart2(TestSuite suite) {
        suite.addTestSuite(NavigationDescriptionFromTableAndTreeTest.class);
        suite.addTestSuite(TableUIRefreshTests.class);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the
     * disabled test.
     * 
     * @return The test suite containing all the disabled tests.
     */
    public static Test disabledSuite() {
        final TestSuite suite = new TestSuite("Table Disabled SwtBot tests");

        return suite;
    }
}
