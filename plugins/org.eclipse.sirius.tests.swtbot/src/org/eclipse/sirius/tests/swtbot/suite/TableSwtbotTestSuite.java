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
package org.eclipse.sirius.tests.swtbot.suite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.eclipse.sirius.tests.swtbot.table.CellEditionTest;
import org.eclipse.sirius.tests.swtbot.table.ContextMenuTableTest;
import org.eclipse.sirius.tests.swtbot.table.DeleteHideSeveralLineOnTable;
import org.eclipse.sirius.tests.swtbot.table.HideRevealTableColumnsTest;
import org.eclipse.sirius.tests.swtbot.table.HideRevealTableLinesTest;
import org.eclipse.sirius.tests.swtbot.table.NavigationDescriptionFromTableAndTreeTest;
import org.eclipse.sirius.tests.swtbot.table.ReadOnlyColumnTest;
import org.eclipse.sirius.tests.swtbot.table.RenameTableRepresentationTest;
import org.eclipse.sirius.tests.swtbot.table.TableUIPermissionAuthorityTests;
import org.eclipse.sirius.tests.swtbot.table.TableUIRefreshTests;

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
        suite.addTestSuite(HideRevealTableColumnsTest.class);
        suite.addTestSuite(HideRevealTableLinesTest.class);
        suite.addTestSuite(RenameTableRepresentationTest.class);
        suite.addTestSuite(DeleteHideSeveralLineOnTable.class);
        suite.addTestSuite(ContextMenuTableTest.class);
        suite.addTestSuite(TableUIPermissionAuthorityTests.class);
        suite.addTestSuite(TableUIRefreshTests.class);
        suite.addTestSuite(NavigationDescriptionFromTableAndTreeTest.class);
        suite.addTestSuite(CellEditionTest.class);
        suite.addTestSuite(ReadOnlyColumnTest.class);
        return suite;
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
