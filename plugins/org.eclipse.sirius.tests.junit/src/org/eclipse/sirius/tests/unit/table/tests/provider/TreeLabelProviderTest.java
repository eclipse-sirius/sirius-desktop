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
package org.eclipse.sirius.tests.unit.table.tests.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.business.api.helper.TableUIHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableEditionEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTableViewerManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;

/**
 * Checks that the labels visible by the users in the tree is displayed
 * properly.
 * 
 * @author smonnier
 * 
 */
public class TreeLabelProviderTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/tables.uml";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/tables.odesign";

    private static final String VIEWPOINT_NAME = "UML2 tables for tests";

    private static final String REPRESENTATION_DESC_NAME_TABLE = "Colored Classes Table";

    private static final String REPRESENTATION_DESC_NAME_CROSS_TABLE = "Model Generalization Cross Table";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
    }

    /**
     * Checks that the Labels of a table are displayed properly.
     * 
     * @throws Exception
     */
    public void testTableLabelProvider() throws Exception {
        DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC_NAME_TABLE, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<List<String>> expected = new ArrayList<List<String>>();
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

        if (openedEditor instanceof DTableEditionEditor) {
            DTableEditionEditor dtee = (DTableEditionEditor) openedEditor;
            AbstractDTableViewerManager tableViewer = dtee.getTableViewer();
            TreeViewer treeViewer = tableViewer.getTreeViewer();
            Tree tree = treeViewer.getTree();

            assertEquals("the table have changed even if nothing has been modified in the semantic model", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(tree));
        }
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Checks that the Labels of a table are displayed properly.
     * 
     * @throws Exception
     */
    public void testCrossTableLabelProvider() throws Exception {
        DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC_NAME_CROSS_TABLE, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "X", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "X", "_", "_" });

        if (openedEditor instanceof DTableEditionEditor) {
            DTableEditionEditor dtee = (DTableEditionEditor) openedEditor;
            AbstractDTableViewerManager tableViewer = dtee.getTableViewer();
            TreeViewer treeViewer = tableViewer.getTreeViewer();
            Tree tree = treeViewer.getTree();

            assertEquals("the table have changed even if nothing has been modified in the semantic model", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(tree));
        }
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
