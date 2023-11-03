/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.suite.table;

import org.eclipse.sirius.tests.unit.table.tests.provider.TreeLabelProviderTest;
import org.eclipse.sirius.tests.unit.table.tests.srs.ExploitArrays_Test;
import org.eclipse.sirius.tests.unit.table.tests.srs.SpecifyArrays_Test;
import org.eclipse.sirius.tests.unit.table.unit.TableContentTest;
import org.eclipse.sirius.tests.unit.table.unit.TableWithHeaderColumnWidthTest;
import org.eclipse.sirius.tests.unit.table.unit.TableWithMultivaluedAttributeTest;
import org.eclipse.sirius.tests.unit.table.unit.dialect.DialectManagerTest;
import org.eclipse.sirius.tests.unit.table.unit.export.ExportToCsvTest;
import org.eclipse.sirius.tests.unit.table.unit.export.MultiLineExportToCsvTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.CrossReferencedDLineDeleteTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.DCellDeleteTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.DCrossTableNoDomainSynchronizerVSMWithEditorTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.DCrossTableSynchronizerVSMWithEditorTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.DTableCellStylesTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.DTableSynchronizerTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.DTableSynchronizerVSMWithEditorTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.DTableSynchronizerWithEditorTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.InvalidParentExpressionTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.TableManuelRefreshTests;
import org.eclipse.sirius.tests.unit.table.unit.refresh.TableNotDirtyOnOpeningTest;
import org.eclipse.sirius.tests.unit.table.unit.refresh.TableStyleRefreshTest;
import org.eclipse.sirius.tests.unit.table.unit.sort.DTableSortByColumnTest;
import org.eclipse.sirius.tests.unit.table.unit.sort.DTableSortByColumnTestWithEditor;
import org.eclipse.sirius.tests.unit.table.unit.sort.DTableSortByLineTest;
import org.eclipse.sirius.tests.unit.table.unit.sort.DTableSortByLineWithEditorTests;
import org.eclipse.sirius.tests.unit.table.unit.tools.CreateTableWithToolWithPrecondtionTest;
import org.eclipse.sirius.tests.unit.table.unit.tools.NoVariableDuplicationTest;
import org.eclipse.sirius.tests.unit.table.unit.tools.RefreshToolActionBarTest;
import org.eclipse.sirius.tests.unit.table.unit.tools.TableToolPreconditionCompletionTest;
import org.eclipse.sirius.tests.unit.table.unit.variables.TableVariableTests;
import org.eclipse.sirius.tests.unit.table.unit.vsm.edit.TableAdapterFactoryRegistryTest;
import org.eclipse.sirius.tests.unit.table.unit.vsm.editor.DefaultVariablesOnToolsTest;
import org.eclipse.sirius.tests.unit.table.unit.vsm.editor.PopupMenuTest;
import org.eclipse.sirius.tests.unit.table.unit.vsm.interpreted.expression.variables.VariableOnTableCreationToolsTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * The designer main test suite for the table component.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class AllTablePluginTests extends TestCase {

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
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the tests
     * of the table component.
     * 
     * @return The testsuite containing all the tests of the table component
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Table Plugin Tests");
        suite.addTestSuite(DTableSynchronizerTest.class);
        suite.addTestSuite(DTableSynchronizerWithEditorTest.class);
        suite.addTestSuite(DTableCellStylesTest.class);
        suite.addTestSuite(DTableSortByColumnTest.class);
        suite.addTestSuite(DTableSortByColumnTestWithEditor.class);
        suite.addTestSuite(DTableSortByLineTest.class);
        suite.addTestSuite(DTableSortByLineWithEditorTests.class);
        suite.addTestSuite(DialectManagerTest.class);
        suite.addTestSuite(ExportToCsvTest.class);
        suite.addTestSuite(MultiLineExportToCsvTest.class);
        suite.addTestSuite(TreeLabelProviderTest.class);
        suite.addTestSuite(PopupMenuTest.class);
        suite.addTestSuite(DefaultVariablesOnToolsTest.class);
        suite.addTestSuite(TableAdapterFactoryRegistryTest.class);
        suite.addTestSuite(SpecifyArrays_Test.class);
        suite.addTestSuite(CreateTableWithToolWithPrecondtionTest.class);
        suite.addTestSuite(RefreshToolActionBarTest.class);
        suite.addTestSuite(TableToolPreconditionCompletionTest.class);
        suite.addTestSuite(DTableSynchronizerVSMWithEditorTest.class);
        suite.addTestSuite(DCrossTableSynchronizerVSMWithEditorTest.class);
        suite.addTestSuite(DCrossTableNoDomainSynchronizerVSMWithEditorTest.class);
        suite.addTestSuite(NoVariableDuplicationTest.class);
        suite.addTestSuite(InvalidParentExpressionTest.class);
        suite.addTestSuite(TableManuelRefreshTests.class);
        suite.addTestSuite(TableNotDirtyOnOpeningTest.class);
        suite.addTest(ExploitArrays_Test.suite());
        suite.addTestSuite(DCellDeleteTest.class);
        suite.addTestSuite(CrossReferencedDLineDeleteTest.class);
        suite.addTestSuite(TableContentTest.class);
        suite.addTestSuite(VariableOnTableCreationToolsTest.class);
        suite.addTestSuite(TableStyleRefreshTest.class);
        suite.addTestSuite(TableWithMultivaluedAttributeTest.class);
        suite.addTestSuite(TableWithHeaderColumnWidthTest.class);
        suite.addTestSuite(TableVariableTests.class);
        return suite;
    }

}
