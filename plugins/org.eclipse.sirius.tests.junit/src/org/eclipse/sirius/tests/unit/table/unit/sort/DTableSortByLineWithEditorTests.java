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
package org.eclipse.sirius.tests.unit.table.unit.sort;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.business.api.helper.TableUIHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.SortColumnsByLineAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;

/**
 * Test the sort by line.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableSortByLineWithEditorTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/tables.uml";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/tables.odesign";

    private static final String VIEWPOINT_NAME = "UML2 tables for tests";

    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
    }

    public void testNormalSort() throws Exception {
        final DTable dTable = (DTable) createRepresentation("Model Association Cross Table", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("The editor has not the correct type", openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        assertEquals("We have 6 classes so we should get 6 lines", 6, dTable.getLines().size());
        assertEquals("We have 6 classes so we should get 6 columns", 6, dTable.getColumns().size());

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "_", "class 1 to class 2", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "_", "class 2 to class 3", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });
        assertEquals("The original sort is KO for the DTable Model", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(dTable, false));

        assertEquals("The original sort is KO for the editor", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testAscendingSortOnFirstLine() throws Exception {
        final DTable dTable = (DTable) createRepresentation("Model Association Cross Table", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("The editor has not the correct type", openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        assertEquals("We have 6 classes so we should get 6 lines", 6, dTable.getLines().size());
        assertEquals("We have 6 classes so we should get 6 columns", 6, dTable.getColumns().size());

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final SortColumnsByLineAction sortColumnsByLineAction = new SortColumnsByLineAction(domain);
        sortColumnsByLineAction.setLine(dTable.getLines().get(0));
        sortColumnsByLineAction.run();

        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class3", "AbstractClass1", "AbstractClass2", "Class4", "Class2" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "_", "_", "_", "_", "_", "class 1 to class 2" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "class 2 to class 3", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });
        assertEquals("The ascending sort on the first line is KO for the DTable Model", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(dTable, false));

        assertEquals("The ascending sort on name column is KO for the editor", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testDescendingSortOnFirstLine() throws Exception {
        final DTable dTable = (DTable) createRepresentation("Model Association Cross Table", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("The editor has not the correct type", openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        assertEquals("We have 6 classes so we should get 6 lines", 6, dTable.getLines().size());
        assertEquals("We have 6 classes so we should get 6 columns", 6, dTable.getColumns().size());

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        SortColumnsByLineAction sortColumnsByLineAction = new SortColumnsByLineAction(domain);
        sortColumnsByLineAction.setLine(dTable.getLines().get(0));
        sortColumnsByLineAction.run();

        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();

        sortColumnsByLineAction = new SortColumnsByLineAction(domain);
        sortColumnsByLineAction.setLine(dTable.getLines().get(0));
        sortColumnsByLineAction.run();

        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class2", "Class1", "Class3", "AbstractClass1", "AbstractClass2", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "class 1 to class 2", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "_", "class 2 to class 3", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });
        assertEquals("The descending sort on the first line is KO for the DTable Model", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(dTable, false));

        assertEquals("The descending sort on name column is KO for the editor", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

}
