/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.export;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.tools.api.export.TableExportHelper;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusAssert;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.ui.IEditorPart;

/**
 * Tests the export of edition/cross tables as CSV asserting a proper content
 * export and the creation of the CSV file
 * 
 * @author lchituc
 */

public class ExportToCsvTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/table/unit/refresh/";

    private static final String SEMANTIC_MODEL_FILENAME = "tables.uml";

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME;

    private static final String MODELER_MODEL_FILENAME = "tables.odesign";

    private static final String SEMANTIC_MODELER_PATH = TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_FILENAME;

    private static final String VIEWPOINT_NAME = "UML2 tables for tests";

    private static final String REPRESENTATION_DESC_NAME_TABLE = "Colored Classes Table";

    private static final String REPRESENTATION_DESC_NAME_CROSS_TABLE = "Model Generalization Cross Table";

    private static final String CSV_FILE_NAME = REPRESENTATION_DESC_NAME_TABLE + ".csv";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, MODELER_MODEL_FILENAME, SEMANTIC_MODEL_FILENAME);

        genericSetUp(SEMANTIC_MODEL_PATH, SEMANTIC_MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
    }

    /**
     * Tests that the export of a table as CSV file created the CSV file
     * 
     * @throws Exception
     */
    public void testEditionTableCsvExport() {

        final DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC_NAME_TABLE, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        IPath absoluteImagePath = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getLocation().append(CSV_FILE_NAME);

        // Asserts that the editor is a DTableEditor
        if (editor instanceof DTableEditor) {
            try {
                DialectUIManager.INSTANCE.export(dTable, session, absoluteImagePath, new ExportFormat(ExportDocumentFormat.CSV, null), new NullProgressMonitor(), false);
            } catch (CoreException e) {
                fail(e.getMessage());
            }
        } else {
            fail("The editor is not an instance of DTable Editor");
        }

        // Asserts that the CSV file has been created
        SiriusAssert.assertFileExists("/" + TEMPORARY_PROJECT_NAME + "/" + CSV_FILE_NAME);

        // Close the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that the export of a cross table as CSV file created the CSV file
     * 
     * @throws Exception
     */
    public void testCrossTableCsvExport() {

        final DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC_NAME_TABLE, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        IPath absoluteImagePath = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getLocation().append(CSV_FILE_NAME);

        // Asserts that the editor is a DTableEditor
        if (editor instanceof DTableEditor) {
            try {
                DialectUIManager.INSTANCE.export(dTable, session, absoluteImagePath, new ExportFormat(ExportDocumentFormat.CSV, null), new NullProgressMonitor(), false);
            } catch (CoreException e) {
                fail(e.getMessage());
            }
        } else {
            fail("The editor is not an instance of DTable Editor");
        }

        // Asserts that the CSV file has been created
        SiriusAssert.assertFileExists("/" + TEMPORARY_PROJECT_NAME + "/" + CSV_FILE_NAME);

        // Close the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that the content of the table has been properly exported
     * 
     * @throws Exception
     * 
     */
    public void testEditionTableCsvTransformation() throws Exception {
        final DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC_NAME_TABLE, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        String content = TableExportHelper.INSTANCE.exportToCsv(dTable);

        assertEquals(
                ";;name;isAbstract ?;Active ?;\nClass : Class1;;Class1;true;false;\n;Property;P1;;;\nClass : Class2;;Class2;false;false;\n;Property;P2;;;\nClass : Class3;;Class3;false;false;\n;Property;P3;;;\nClass : AbstractClass1;;AbstractClass1;true;false;\nClass : AbstractClass2;;AbstractClass2;true;false;\nClass : Class4;;Class4;true;false;\n",
                content);
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that the content of the table has been properly exported when
     * column and lines are hidden.
     * 
     * @throws Exception
     * 
     */
    public void testEditionTableCsvTransformationWithHiddenElements() throws Exception {
        final DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC_NAME_TABLE, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Hidden first column and first line
        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                dTable.getColumns().get(0).setVisible(false);
                dTable.getLines().get(0).setVisible(false);
            };
        });

        String content = TableExportHelper.INSTANCE.exportToCsv(dTable);

        assertEquals(
                ";;isAbstract ?;Active ?;\nClass : Class2;;false;false;\n;Property;;;\nClass : Class3;;false;false;\n;Property;;;\nClass : AbstractClass1;;true;false;\nClass : AbstractClass2;;true;false;\nClass : Class4;;true;false;\n",
                content);
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that the content of the cross table has been properly exported
     * 
     * @throws Exception
     * 
     */
    public void testCrossTableCsvTransformation() throws Exception {
        final DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC_NAME_CROSS_TABLE, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        String content = TableExportHelper.INSTANCE.exportToCsv(dTable);

        assertEquals(";AbstractClass1;AbstractClass2;Class1;Class2;Class3;Class4;\nAbstractClass1;;;;;;;\nAbstractClass2;;;;;;;\nClass1;X;;;;;;\nClass2;;X;;;;;\nClass3;;;;;;;\nClass4;;;;X;;;\n",
                content);
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

}
