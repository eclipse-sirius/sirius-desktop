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
package org.eclipse.sirius.tests.unit.table.unit.tools;

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.AbstractEditorCreateMenuAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.CreateLineAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.EditorCreateLineMenuAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
 * Test Refresh action tool bar on table.
 * 
 * @author jdupont
 * 
 */
public class RefreshToolActionBarTest extends TableTestCase {

    String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/tools/tests.ecore";

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/tools/tests.odesign";

    String VIEWPOINT_NAME = "TestTableTools";

    String REPRESENTATION_DESC_NAME1 = "TestTableTools_Classes";

    String REPRESENTATION_DESC_NAME2 = "TestTableTools_Attributes";

    String EDITOR_CREATE_LINE_MENU_ID = "CreateLineMenu";

    private static Method getCreateLineActionsForTable;

    static {
        try {
            getCreateLineActionsForTable = AbstractEditorCreateMenuAction.class.getDeclaredMethod("getCreateActionsForTable");
            getCreateLineActionsForTable.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void init() throws Exception {
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
    }

    /**
     * Test refresh creation tools when working on several table
     * representations. VP-1154
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testRefreshToolBarCreateLine() {
        final DTable dTable1 = (DTable) createRepresentation(REPRESENTATION_DESC_NAME1, semanticModel);
        EClassImpl semanticClass = (EClassImpl) ((EPackageImpl) semanticModel).getEClassifier("class1");
        final DTable dTable2 = (DTable) createRepresentation(REPRESENTATION_DESC_NAME2, semanticClass);
        AbstractDTableEditor editor1 = (AbstractDTableEditor) DialectUIManager.INSTANCE.openEditor(session, dTable1, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        AbstractDTableEditor editor2 = null;

        assertNotNull("The editor has not been correctly opened", editor1);

        IToolBarManager toolBar = ((EditorActionBarContributor) editor1.getEditorSite().getActionBarContributor()).getActionBars().getToolBarManager();

        ActionContributionItem contributionItem = (ActionContributionItem) toolBar.find(EDITOR_CREATE_LINE_MENU_ID);

        EditorCreateLineMenuAction editorCreateLineMenuAction = ((EditorCreateLineMenuAction) contributionItem.getAction());

        try {
            List<CreateLineAction> creationsLineAction = (List<CreateLineAction>) getCreateLineActionsForTable.invoke(editorCreateLineMenuAction);

            assertEquals("TestTableTools_Classes_CreateLine", creationsLineAction.get(0).getText());

            editor2 = (AbstractDTableEditor) DialectUIManager.INSTANCE.openEditor(session, dTable2, new NullProgressMonitor());

            TestsUtil.synchronizationWithUIThread();

            assertNotNull("The editor has not been correctly opened", editor2);

            toolBar = ((EditorActionBarContributor) editor2.getEditorSite().getActionBarContributor()).getActionBars().getToolBarManager();

            contributionItem = (ActionContributionItem) toolBar.find(EDITOR_CREATE_LINE_MENU_ID);

            editorCreateLineMenuAction = ((EditorCreateLineMenuAction) contributionItem.getAction());

            creationsLineAction = (List<CreateLineAction>) getCreateLineActionsForTable.invoke(editorCreateLineMenuAction);

            assertEquals("TestTableTools_Attributes_CreateLine", creationsLineAction.get(0).getText());

            IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

            workbenchPage.activate(editor1);

            toolBar = ((EditorActionBarContributor) editor1.getEditorSite().getActionBarContributor()).getActionBars().getToolBarManager();

            contributionItem = (ActionContributionItem) toolBar.find(EDITOR_CREATE_LINE_MENU_ID);

            editorCreateLineMenuAction = ((EditorCreateLineMenuAction) contributionItem.getAction());

            creationsLineAction = (List<CreateLineAction>) getCreateLineActionsForTable.invoke(editorCreateLineMenuAction);

            assertEquals("TestTableTools_Classes_CreateLine", creationsLineAction.get(0).getText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DialectUIManager.INSTANCE.closeEditor(editor1, false);
            TestsUtil.synchronizationWithUIThread();
            DialectUIManager.INSTANCE.closeEditor(editor2, false);
            TestsUtil.synchronizationWithUIThread();
        }

    }
}
