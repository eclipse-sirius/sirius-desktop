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
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.tools.api.export.TableExportHelper;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusAssert;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.ui.IEditorPart;

/**
 * Tests the export of edition/cross tables as CSV asserting a proper content
 * export and the creation of the CSV file
 * 
 * @author mporhel
 */

public class MultiLineExportToCsvTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/table/unit/exportMultiLine/";

    private static final String SEMANTIC_MODEL_FILENAME = "tables.ecore";

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME;

    private static final String MODELER_MODEL_FILENAME = "tables.odesign";

    private static final String SEMANTIC_MODELER_PATH = TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_FILENAME;

    private static final String VIEWPOINT_NAME = "Table for tests";

    private static final String REPRESENTATION_DESC_NAME_TABLE = "Test Table";

    private static final String CSV_FILE_NAME = REPRESENTATION_DESC_NAME_TABLE + ".csv";

    private static final String EOL = "\r\n";

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
     * Tests that the content of the table has been properly exported
     * 
     * @throws Exception
     */
    public void testEditionTableCsvTransformationWithCroppedMultiLineLabel() throws Exception {
        final DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC_NAME_TABLE, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        Option<DCell> cell = TableHelper.getCell(dTable.getLines().get(1), dTable.getColumns().get(0));
        assertTrue("Review the test data.", cell.some());
        assertTrue("Review the test data.", cell.get().getTableElementMapping() instanceof FeatureColumnMapping);
        assertTrue("Review the test data.", StringUtil.isEmpty(((FeatureColumnMapping) cell.get().getTableElementMapping()).getLabelComputationExpression()));
        assertEquals("The table should contain a multi line label.", "multi " + EOL + "line", cell.get().getLabel());

        String content = TableExportHelper.INSTANCE.exportToCsv(dTable);
        assertEquals(";name;\np1;single Line;\np2;'multi \nline';\n", content);

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
