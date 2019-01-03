/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.table.unit.refresh;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.ui.business.api.helper.TableUIHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

/**
 * Test synchronization between modeler and editor. Tets VP-597 VP-598 & VP-1680
 * 
 * @author jdupont
 * 
 */
public class DTableSynchronizerVSMWithEditorTest extends TableTestCase {

    private static final String TEST_DIR = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/";

    private static final String SEMANTIC_MODEL_PATH = TEST_DIR + "tc598.ecore";

    private static final String SESSION_NAME = "tc598.aird";

    private static final String SESSION_PATH = TEST_DIR + SESSION_NAME;

    private static final String MODELER_PATH = TEST_DIR + "tc598.odesign";

    private static final String TABLE_DESCRIPTION_ID = "TestColumnTable";

    private AbstractDTableEditor tableEditor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        ModelUtils.load(URI.createPlatformPluginURI("/org.eclipse.sirius.tests.junit/data/table/unit/refresh/tables.odesign", true), session.getTransactionalEditingDomain().getResourceSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    /**
     * Test the table synchronization after deletion of a column in the VSM.
     */
    public void testRefreshDeleteOneColumn() {
        if ("gtk".equals(SWT.getPlatform())) {
            // We know the test fails under Linux/Gtk (although the feature
            // works),
            // because of a difference in the behavior of Gtk, so it is
            // disabled.
            return;
        }
        final TableDescription tableDescription = find(TABLE_DESCRIPTION_ID);
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);

        assertEquals(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, tableDescription.getAllLineMappings().size());

        // Test representation corresponding to attempt.
        Assert.assertNotNull(getRepresentations(TABLE_DESCRIPTION_ID));
        DTable newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        String currentHtml = TableUIHelper.toContentHTMl(tree);
        String expectedHtml = getExpectedDefaultHtml();

        assertTrue("The editor has not the good number element", expectedHtml.length() == currentHtml.length());
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();

        EList<FeatureColumnMapping> columns = ((EditionTableDescription) tableDescription).getAllColumnMappings();
        assertEquals("The number of columns mapping is not correct", 4, columns.size());

        // Remove 1 column
        final ColumnMapping columnToDelete = columns.get(2);
        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {

            protected void doExecute() {
                ((EditionTableDescription) tableDescription).getOwnedColumnMappings().remove(columnToDelete);
            };

        });

        columns = ((EditionTableDescription) tableDescription).getAllColumnMappings();
        assertEquals("The number of columns mapping is not correct", 3, columns.size());

        openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        tableEditor = (AbstractDTableEditor) openedEditor;
        tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        currentHtml = TableUIHelper.toContentHTMl(tree);
        expectedHtml = getExpectedHtmlWithDeletedColumn();

        assertTrue("The editor has not the good number element", expectedHtml.length() == currentHtml.length());
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test the table synchronization after deletion of all the columns in the VSM.
     */
    public void testRefreshAfterDeleteAllColumns() {
        if ("gtk".equals(SWT.getPlatform())) {
            // We know the test fails under Linux/Gtk (although the feature
            // works),
            // because of a difference in the behavior of Gtk, so it is
            // disabled.
            return;
        }
        final TableDescription tableDescription = find(TABLE_DESCRIPTION_ID);
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);

        assertEquals(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, tableDescription.getAllLineMappings().size());

        // Test representation corresponding to attempt.
        Assert.assertNotNull(getRepresentations(TABLE_DESCRIPTION_ID));
        DTable newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        String currentHtml = TableUIHelper.toContentHTMl(tree);
        String expectedHtml = getExpectedDefaultHtml();

        assertTrue("The editor has not the good number element", expectedHtml.length() == currentHtml.length());
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();

        EList<FeatureColumnMapping> columns = ((EditionTableDescription) tableDescription).getAllColumnMappings();
        assertEquals("The number of columns mapping is not correct", 4, columns.size());

        // Remove all columns.
        final EList<FeatureColumnMapping> columnsToRemove = columns;
        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {

            protected void doExecute() {
                ((EditionTableDescription) tableDescription).getOwnedColumnMappings().removeAll(columnsToRemove);
            };

        });

        columns = ((EditionTableDescription) tableDescription).getAllColumnMappings();
        assertEquals("The number of columns mapping is not correct", 0, columns.size());

        newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        tableEditor = (AbstractDTableEditor) openedEditor;
        tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        currentHtml = TableUIHelper.toContentHTMl(tree);

        expectedHtml = getExpectedHtmlWithDeleteAllColumns();

        assertTrue("The editor has not the good number element", expectedHtml.length() == currentHtml.length());
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test the modification of feature column in the VSM.
     */
    public void testRefreshFeatureColumn() {
        if ("gtk".equals(SWT.getPlatform())) {
            // We know the test fails under Linux/Gtk (although the feature
            // works),
            // because of a difference in the behavior of Gtk, so it is
            // disabled.
            return;
        }
        final TableDescription tableDescription = find(TABLE_DESCRIPTION_ID);
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);

        assertEquals(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, tableDescription.getAllLineMappings().size());

        // Test representation corresponding to attempt.
        Assert.assertNotNull(getRepresentations(TABLE_DESCRIPTION_ID));
        DTable newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        String currentHtml = TableUIHelper.toContentHTMl(tree);
        String expectedHtml = getExpectedDefaultHtml();

        assertTrue("The editor has not the good number element", expectedHtml.length() == currentHtml.length());
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();

        EList<FeatureColumnMapping> columns = ((EditionTableDescription) tableDescription).getAllColumnMappings();
        assertEquals("The number of columns mapping is not correct", 4, columns.size());

        // Add 1 column.
        final FeatureColumnMapping columnToAdd = (FeatureColumnMapping) SiriusCopierHelper.copyWithNoUidDuplication(columns.get(3));

        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {

            protected void doExecute() {
                columnToAdd.setName("Name5");
                columnToAdd.setHeaderLabelExpression("Name5");
                ((EditionTableDescription) tableDescription).getOwnedColumnMappings().add(columnToAdd);
            };
        });

        columns = ((EditionTableDescription) tableDescription).getAllColumnMappings();
        assertEquals("The number of columns mapping is not correct", 5, columns.size());

        openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        tableEditor = (AbstractDTableEditor) openedEditor;
        tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        currentHtml = TableUIHelper.toContentHTMl(tree);
        expectedHtml = getExpectedHtmlWithOneAdditionalColumn();

        assertTrue("The editor has not the good number element", expectedHtml.length() == currentHtml.length());
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testRefreshFeatureColumnFirstLevelSubLineMappingWithVSModificationSurroundByCloseAndOpen() {
        testRefreshFeatureColumnFirstLevelSubLineMapping(true);
    }

    /**
     * Test the modification of line mapping in the VSM :
     * <UL>
     * <LI>Open the editor and check the result,
     * <LI>Add one sub line under the first line and check the result</LI>
     * <LI>Remove this sub line and check the result</LI>
     * <LI>Remove all lines and check the result</LI>
     * <LI>Close the editor</LI>
     * </UL>
     * 
     * @param surroundVSModificationByCloseAndOpen
     *            true if the table must be close before VSM modification and open after, false otherwise.
     */
    protected void testRefreshFeatureColumnFirstLevelSubLineMapping(boolean surroundVSModificationByCloseAndOpen) {
        final TableDescription tableDescription = find(TABLE_DESCRIPTION_ID);
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);
        assertEquals(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, tableDescription.getAllLineMappings().size());

        // Test representation corresponding to attempt.
        DTable newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        String currentHtml = TableUIHelper.toContentHTMl(tree);
        String expectedHtml = getExpectedDefaultHtml();

        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        EList<LineMapping> lines = ((EditionTableDescription) tableDescription).getAllLineMappings();
        assertEquals("The number of line mapping is not correct", 1, lines.size());
        assertEquals("The number of sub line mapping is not correct", 2, lines.get(0).getAllSubLines().size());

        // Close editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
            TestsUtil.synchronizationWithUIThread();
            openedEditor = null;
            tableEditor = null;
            tree = null;
        }

        // Add 1 sub line mapping.
        final LineMapping lineToAdd = (LineMapping) SiriusCopierHelper.copyWithNoUidDuplication(lines.get(0).getAllSubLines().get(1));
        assertEquals("Bad line to copy", "Attribut2", lineToAdd.getName());

        String commandName = "Add a new sub line mapping";
        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain(), commandName) {
            protected void doExecute() {
                lineToAdd.setName("Attribut3");
                lineToAdd.setHeaderLabelExpression("aql:'3-' + self.name");
                ((EditionTableDescription) tableDescription).getAllLineMappings().get(0).getOwnedSubLines().add(lineToAdd);
            };
        });

        lines = ((EditionTableDescription) tableDescription).getAllLineMappings();
        assertEquals("The number of line mapping is not correct", 1, lines.size());
        assertEquals("The number of sub line mapping is not correct", 3, lines.get(0).getAllSubLines().size());

        // Open editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            tableEditor = (AbstractDTableEditor) openedEditor;
            tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        }

        TestsUtil.synchronizationWithUIThread();

        // Check attempted
        currentHtml = TableUIHelper.toContentHTMl(tree);
        expectedHtml = getExpectedHtmlWithOneAdditionalFirstLevelSubLine();
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        // Close editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
            TestsUtil.synchronizationWithUIThread();
            openedEditor = null;
            tableEditor = null;
            tree = null;
        }

        // Remove 1 sub line mapping (Remove the previously added sub line
        // mapping)
        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain(), commandName) {
            protected void doExecute() {
                ((EditionTableDescription) tableDescription).getAllLineMappings().get(0).getOwnedSubLines().remove(lineToAdd);
            };
        });

        lines = ((EditionTableDescription) tableDescription).getAllLineMappings();
        assertEquals("The number of line mapping is not correct", 1, lines.size());
        assertEquals("The number of sub line mapping is not correct", 2, lines.get(0).getAllSubLines().size());

        // Open editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            tableEditor = (AbstractDTableEditor) openedEditor;
            tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        }

        // Check attempted
        currentHtml = TableUIHelper.toContentHTMl(tree);
        expectedHtml = getExpectedDefaultHtml();
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        // Close editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
            TestsUtil.synchronizationWithUIThread();
            openedEditor = null;
            tableEditor = null;
            tree = null;
        }

        // Remove all lines.
        final EList<LineMapping> linesToRemove = lines;
        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {

            protected void doExecute() {
                ((EditionTableDescription) tableDescription).getOwnedLineMappings().removeAll(linesToRemove);
            };

        });

        lines = ((EditionTableDescription) tableDescription).getAllLineMappings();
        assertEquals("The number of columns mapping is not correct", 0, lines.size());

        // Open editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            tableEditor = (AbstractDTableEditor) openedEditor;
            tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        }

        // Check attempted
        currentHtml = TableUIHelper.toContentHTMl(tree);
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Name", "Name2", "Name3", "Name4" });
        expectedHtml = TableUIHelper.toHTML(expected);
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        // Close editor
        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test the modification of sub line mapping in the VSM.
     */
    public void testRefreshFeatureColumnOtherLevelSubLineMappingWithVSModificationSurroundByCloseAndOpen() {
        testRefreshFeatureColumnOtherLevelSubLineMapping(true);
    }

    /**
     * Test the modification of sub line mapping in the VSM.
     * 
     * @param surroundVSModificationByCloseAndOpen
     *            true if the table must be close before VSM modification and open after, false otherwise.
     */
    protected void testRefreshFeatureColumnOtherLevelSubLineMapping(boolean surroundVSModificationByCloseAndOpen) {
        final TableDescription tableDescription = find(TABLE_DESCRIPTION_ID);
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);
        assertEquals(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, tableDescription.getAllLineMappings().size());

        // Test representation corresponding to attempt.
        DTable newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        String currentHtml = TableUIHelper.toContentHTMl(tree);
        String expectedHtml = getExpectedDefaultHtml();

        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        // Close editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
            TestsUtil.synchronizationWithUIThread();
            openedEditor = null;
            tableEditor = null;
            tree = null;
        }

        EList<LineMapping> lines = ((EditionTableDescription) tableDescription).getAllLineMappings();
        assertEquals("The number of line mapping is not correct", 1, lines.size());
        assertEquals("The number of sub line mapping is not correct", 2, lines.get(0).getAllSubLines().size());
        assertEquals("The number of sub sub line mapping is not correct", 3, lines.get(0).getAllSubLines().get(0).getAllSubLines().size());

        // Add 1 sub line to the first sub line of the first line.
        final LineMapping lineToAdd = (LineMapping) SiriusCopierHelper.copyWithNoUidDuplication(lines.get(0).getAllSubLines().get(0).getAllSubLines().get(0));

        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {

            protected void doExecute() {
                lineToAdd.setName("SousAttribut4");
                lineToAdd.setHeaderLabelExpression("aql:'S4-' + self.name");
                ((EditionTableDescription) tableDescription).getOwnedLineMappings().get(0).getOwnedSubLines().get(0).getOwnedSubLines().add(lineToAdd);
            };

        });

        lines = ((EditionTableDescription) tableDescription).getAllLineMappings();
        assertEquals("The number of line mapping is not correct", 1, lines.size());
        assertEquals("The number of sub line mapping is not correct", 2, lines.get(0).getAllSubLines().size());
        assertEquals("The number of sub sub line mapping is not correct", 4, lines.get(0).getAllSubLines().get(0).getAllSubLines().size());

        // Open editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            tableEditor = (AbstractDTableEditor) openedEditor;
            tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        }

        TestsUtil.synchronizationWithUIThread();

        // Check attempted
        currentHtml = TableUIHelper.toContentHTMl(tree);
        expectedHtml = getExpectedHtmlWithOneAdditionalOtherLevelSubLine();
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        // Close editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
            TestsUtil.synchronizationWithUIThread();
            openedEditor = null;
            tableEditor = null;
            tree = null;
        }

        // Remove 1 sub line mapping (Remove the previously added sub line
        // mapping)
        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {
            protected void doExecute() {
                ((EditionTableDescription) tableDescription).getOwnedLineMappings().get(0).getOwnedSubLines().get(0).getOwnedSubLines().remove(lineToAdd);
            };
        });

        lines = ((EditionTableDescription) tableDescription).getAllLineMappings();
        assertEquals("The number of line mapping is not correct", 1, lines.size());
        assertEquals("The number of sub line mapping is not correct", 2, lines.get(0).getAllSubLines().size());
        assertEquals("The number of sub sub line mapping is not correct", 3, lines.get(0).getAllSubLines().get(0).getAllSubLines().size());

        // Open editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            tableEditor = (AbstractDTableEditor) openedEditor;
            tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        }

        // Check attempted
        currentHtml = TableUIHelper.toContentHTMl(tree);
        expectedHtml = getExpectedDefaultHtml();
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        // Close editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
            TestsUtil.synchronizationWithUIThread();
            openedEditor = null;
            tableEditor = null;
            tree = null;
        }

        // Remove all sub sub lines.
        EList<LineMapping> subSubLines = lines.get(0).getAllSubLines().get(0).getAllSubLines();
        final EList<LineMapping> linesToRemove = subSubLines;
        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {

            protected void doExecute() {
                ((EditionTableDescription) tableDescription).getAllLineMappings().get(0).getOwnedSubLines().get(0).getOwnedSubLines().removeAll(linesToRemove);
            };

        });

        lines = ((EditionTableDescription) tableDescription).getAllLineMappings();
        assertEquals("The number of sub lines mapping is not correct", 2, lines.get(0).getAllSubLines().size());
        assertEquals("The number of sub sub lines mapping is not correct", 0, lines.get(0).getAllSubLines().get(0).getAllSubLines().size());

        // Open editor if needed
        if (surroundVSModificationByCloseAndOpen) {
            openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            tableEditor = (AbstractDTableEditor) openedEditor;
            tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        }

        // Check attempted
        currentHtml = TableUIHelper.toContentHTMl(tree);
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Name", "Name2", "Name3", "Name4" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1" });
        TableUIHelper.addLineToTable(expected, new String[] { "1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2" });
        expectedHtml = TableUIHelper.toHTML(expected);

        assertTrue("The editor has not the good number element", expectedHtml.length() == currentHtml.length());
        assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);

        // Close editor
        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    private String getExpectedDefaultHtml() {
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Name", "Name2", "Name3", "Name4" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1" });
        TableUIHelper.addLineToTable(expected, new String[] { "1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S3-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2" });
        return TableUIHelper.toHTML(expected);
    }

    private String getExpectedHtmlWithOneAdditionalFirstLevelSubLine() {
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Name", "Name2", "Name3", "Name4" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1" });
        TableUIHelper.addLineToTable(expected, new String[] { "1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S3-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "3-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2" });
        return TableUIHelper.toHTML(expected);
    }

    private String getExpectedHtmlWithOneAdditionalOtherLevelSubLine() {
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Name", "Name2", "Name3", "Name4" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1" });
        TableUIHelper.addLineToTable(expected, new String[] { "1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S3-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S4-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2" });
        return TableUIHelper.toHTML(expected);
    }

    private String getExpectedHtmlWithOneAdditionalColumn() {
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Name", "Name2", "Name3", "Name4", "Name5" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1" });
        TableUIHelper.addLineToTable(expected, new String[] { "1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S1-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S3-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "2-Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2" });
        return TableUIHelper.toHTML(expected);
    }

    private String getExpectedHtmlWithDeletedColumn() {
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Name", "Name2", "Name4" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 1", "new EClass 1", "new EClass 1", "new EClass 1" });
        TableUIHelper.addLineToTable(expected, new String[] { "1-Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S1-Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S2-Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S3-Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "2-Attribut1", "Attribut1", "Attribut1", "Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 2", "new EClass 2", "new EClass 2", "new EClass 2" });
        return TableUIHelper.toHTML(expected);
    }

    private String getExpectedHtmlWithDeleteAllColumns() {
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 1" });
        TableUIHelper.addLineToTable(expected, new String[] { "1-Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S1-Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S2-Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "S3-Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "2-Attribut1" });
        TableUIHelper.addLineToTable(expected, new String[] { "new EClass 2" });
        return TableUIHelper.toHTML(expected);
    }
}
