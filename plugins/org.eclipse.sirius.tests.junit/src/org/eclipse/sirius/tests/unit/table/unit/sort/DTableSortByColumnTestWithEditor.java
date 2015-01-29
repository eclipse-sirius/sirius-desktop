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
import org.eclipse.sirius.table.ui.tools.internal.editor.action.SortLinesByColumnAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * Test the sort by column.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DTableSortByColumnTestWithEditor extends SiriusDiagramTestCase {

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
        final DTable dTable = (DTable) createRepresentation("Colored Classes Table", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("The editor has not the correct type", openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        assertEquals("We have 6 classes so we should get 6 lines", 6, dTable.getLines().size());

        assertEquals("We have 3 features columns so we should get 3 columns", 3, dTable.getColumns().size());

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "name", "isAbstract ?", "Active ?" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class1", "Class1", "true", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P1", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class2", "Class2", "false", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P2", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class3", "Class3", "false", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P3", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : AbstractClass1", "AbstractClass1", "true", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : AbstractClass2", "AbstractClass2", "true", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class4", "Class4", "true", "false" });

        assertEquals("The original sort is KO for the DTable Model", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(dTable, false));

        assertEquals("The original sort is KO for the editor", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testAscendingSortOnNameColumn() throws Exception {
        final DTable dTable = (DTable) createRepresentation("Colored Classes Table", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("The editor has not the correct type", openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        assertEquals("We have 6 classes so we should get 6 lines", 6, dTable.getLines().size());

        assertEquals("We have 3 features columns so we should get 3 columns", 3, dTable.getColumns().size());

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final SortLinesByColumnAction sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(dTable.getColumns().get(0));
        sortLinesByColumnAction.run();

        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();

        final List<List<String>> expectedAscendingSortOnName = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "", "name", "isAbstract ?", "Active ?" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : AbstractClass1", "AbstractClass1", "true", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : AbstractClass2", "AbstractClass2", "true", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : Class1", "Class1", "true", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Property", "P1", "", "" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : Class2", "Class2", "false", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Property", "P2", "", "" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : Class3", "Class3", "false", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Property", "P3", "", "" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : Class4", "Class4", "true", "false" });

        assertEquals("The ascending sort on name column is KO for the DTable Model", TableUIHelper.toHTML(expectedAscendingSortOnName), TableUIHelper.toContentHTMl(dTable, false));

        assertEquals("The ascending sort on name column is KO for the editor", TableUIHelper.toHTML(expectedAscendingSortOnName), TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testDescendingSortOnNameColumn() throws Exception {
        final DTable dTable = (DTable) createRepresentation("Colored Classes Table", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("The editor has not the correct type", openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        assertEquals("We have 6 classes so we should get 6 lines", 6, dTable.getLines().size());

        assertEquals("We have 3 features columns so we should get 3 columns", 3, dTable.getColumns().size());

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        SortLinesByColumnAction sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(dTable.getColumns().get(0));
        sortLinesByColumnAction.run();

        // Wait the viewer.update run in asyncExec mode
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
        }

        sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(dTable.getColumns().get(0));
        sortLinesByColumnAction.run();

        // Wait the viewer.update run in asyncExec mode
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
        }

        final List<List<String>> expectedDescendingSortOnName = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "", "name", "isAbstract ?", "Active ?" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : Class4", "Class4", "true", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : Class3", "Class3", "false", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Property", "P3", "", "" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : Class2", "Class2", "false", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Property", "P2", "", "" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : Class1", "Class1", "true", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Property", "P1", "", "" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : AbstractClass2", "AbstractClass2", "true", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : AbstractClass1", "AbstractClass1", "true", "false" });

        assertEquals("The descending sort on name column is KO for the DTable Model", TableUIHelper.toHTML(expectedDescendingSortOnName), TableUIHelper.toContentHTMl(dTable, false));

        assertEquals("The descending sort on name column is KO for the editor", TableUIHelper.toHTML(expectedDescendingSortOnName), TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
