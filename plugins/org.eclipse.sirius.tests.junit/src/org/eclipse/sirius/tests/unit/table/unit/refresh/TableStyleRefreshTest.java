/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.refresh;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableEditionEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeItemLabelFontFormatQuery;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Test that semantic changes which impact conditional styles are correctly
 * taken into account and propagated all the way to the UI level.
 * 
 * @bugzilla #468006
 */
public class TableStyleRefreshTest extends TableTestCase {
    private static final String FIXTURE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/Bug_468006";

    private static final String SEMANTIC_MODEL_PATH = FIXTURE_PATH + "/data.ecore";

    private static final String MODELER_PATH = FIXTURE_PATH + "/fixture.odesign";

    private static final String REPRESENTATION_PATH = FIXTURE_PATH + "/data.aird";

    private DTableEditionEditor tableEditor;

    private static DTable table;

    private static EPackage testPackage;

    @Override
    protected void init() throws Exception {
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_PATH);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        table = (DTable) getRepresentations("Bug_468006_Table").toArray()[0];
        testPackage = (EPackage) table.getTarget();
        tableEditor = (DTableEditionEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }
    
    public void testConditionalStyleChange() {
        TreeItem[] items = tableEditor.getTableViewer().getTreeViewer().getTree().getItems();
        assertEquals(3, items.length);
        checkItem(items[0], "Aaaaaa", true);
        checkItem(items[1], "Bbbbbb", false);
        checkItem(items[2], "Cccccc", false);

        final EClass b = (EClass) testPackage.getEClassifiers().get(1);
        assertFalse(b.isAbstract());
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                b.setAbstract(true);
            }
        });
        TestsUtil.synchronizationWithUIThread();
        items = tableEditor.getTableViewer().getTreeViewer().getTree().getItems();
        assertEquals(3, items.length);
        checkItem(items[0], "Aaaaaa", true);
        checkItem(items[1], "Bbbbbb", true); // This label should have switched to italic
        checkItem(items[2], "Cccccc", false);
    }
    
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
        tableEditor = null;
        table = null;
        testPackage = null;
        super.tearDown();
    }
    
    private void checkItem(TreeItem item, String text, boolean italic) {
        assertEquals(text, item.getText());

        TreeItemLabelFontFormatQuery treeItemBackgroundColorQuery = new TreeItemLabelFontFormatQuery(item, 1);
        Display.getDefault().syncExec(treeItemBackgroundColorQuery);
        List<FontFormat> formats = treeItemBackgroundColorQuery.getResult();

        if (italic) {
            assertTrue("Line " + text + " should be in italic", formats.contains(FontFormat.ITALIC_LITERAL));
        } else {
            assertFalse("Line " + text + " should NOT be in italic", formats.contains(FontFormat.ITALIC_LITERAL));
        }
    }
}
