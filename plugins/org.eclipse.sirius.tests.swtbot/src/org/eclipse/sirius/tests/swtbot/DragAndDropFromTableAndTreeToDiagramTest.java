/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotSplitEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.swtbot.support.utils.dnd.DndUtil;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * 
 * @author fbarbin
 */
public class DragAndDropFromTableAndTreeToDiagramTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL = "test.ecore";

    private static final String SESSION_FILE = "test.aird";

    private static final String VSM_FILE = "dndTableTreeToDiag.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/dragAndDrop/tree_table_to_diagram/";

    private static final String FILE_DIR = "/";

    private UITableRepresentation table;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private SWTBotSiriusDiagramEditor editor;

    private UITreeRepresentation tree;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFilesToTestProject(MODEL, SESSION_FILE, VSM_FILE);
    }

    private void copyFilesToTestProject(final String... fileNames) {
        for (final String fileName : fileNames) {
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + fileName, getProjectName() + "/" + fileName);
        }
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        bot.viewById("org.eclipse.ui.views.ContentOutline").close();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
    }

    /**
     * @throws Exception
     */
    public void testDragAndDropFromTableToDiagram() throws Exception {
        // Not available in 4.x specific. Split Editor issues.
        if (TestsUtil.isEclipse4xPlatform()) {
            return;
        }
        // open table
        table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint("dnd").selectRepresentation("dndTable").selectRepresentationInstance("new dndTable", UITableRepresentation.class)
                .open();
        // tableEditor = table.getEditor();

        // open diagram

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "dndDiagram", "new dndDiagram", DDiagram.class);

        // Read the initial state.
        Set<SWTBotGefEditPart> allEditPartsBefore = Sets.newHashSet(editor.mainEditPart().children());
        SWTBotUtils.waitAllUiEvents();
        SWTBotSplitEditor.splitEditorArea();
        Thread.sleep(500);

        // Perform the DnD
        DndUtil util = new DndUtil(bot.getDisplay());
        SWTBotTreeItem ecoreTreeItem = table.getTable().getTreeItem("new EClass 2");
        util.dragAndDrop(ecoreTreeItem, editor.getCanvas());
        SWTBotUtils.waitAllUiEvents();
        Thread.sleep(500);

        // Force a refresh of the diagram. This happens automatically when
        // running the test manually, but for some reason in the context of the
        // SWTbot test it must be done explicitly.
        DView dView = localSession.getOpenedSession().getSelectedViews().iterator().next();
        final DDiagram diag = (DDiagram) new DViewQuery(dView).getLoadedRepresentations().iterator().next();
        TransactionalEditingDomain ted = localSession.getOpenedSession().getTransactionalEditingDomain();
        ted.getCommandStack().execute(new RecordingCommand(ted) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.refresh(diag, new NullProgressMonitor());
            }
        });
        SWTBotUtils.waitAllUiEvents();
        Thread.sleep(500);

        // Check the final state: we should have exactly one new edit part on
        // the diagram.
        Set<SWTBotGefEditPart> allEditPartsAfter = Sets.newHashSet(editor.mainEditPart().children());
        SetView<SWTBotGefEditPart> newParts = Sets.difference(allEditPartsAfter, allEditPartsBefore);
        assertEquals("Expected exactly one new element on the diagram.", 1, newParts.size());
    }

    /**
     * @throws Exception
     */
    public void testDragAndDropFromTreeToDiagram() throws Exception {
        // Not available in 4.x : split editor issue.
        if (TestsUtil.isEclipse4xPlatform()) {
            return;
        }
        // open table
        tree = localSession.getLocalSessionBrowser().perCategory().selectViewpoint("dnd").selectRepresentation("dndTree").selectRepresentationInstance("new dndTree", UITreeRepresentation.class)
                .open();
        // treeEditor = tree.getEditor();

        // open diagram

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "dndDiagram", "new dndDiagram", DDiagram.class);

        // Read the initial state.
        Set<SWTBotGefEditPart> allEditPartsBefore = Sets.newHashSet(editor.mainEditPart().children());
        SWTBotUtils.waitAllUiEvents();
        SWTBotSplitEditor.splitEditorArea();
        Thread.sleep(500);

        // Perform the DnD
        // tree.getTable().setFocus();
        DndUtil util = new DndUtil(bot.getDisplay());
        SWTBotTreeItem ecoreTreeItem = tree.getTree().getTreeItem("new EClass 2");
        util.dragAndDrop(ecoreTreeItem, editor.getCanvas());
        SWTBotUtils.waitAllUiEvents();
        Thread.sleep(500);

        // Force a refresh of the diagram. This happens automatically when
        // running the test manually, but for some reason in the context of the
        // SWTbot test it must be done explicitly.
        DView dView = localSession.getOpenedSession().getSelectedViews().iterator().next();
        final DDiagram diag = (DDiagram) new DViewQuery(dView).getLoadedRepresentations().iterator().next();
        TransactionalEditingDomain ted = localSession.getOpenedSession().getTransactionalEditingDomain();
        ted.getCommandStack().execute(new RecordingCommand(ted) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.refresh(diag, new NullProgressMonitor());
            }
        });
        SWTBotUtils.waitAllUiEvents();
        Thread.sleep(500);

        // Check the final state: we should have exactly one new edit part on
        // the diagram.
        Set<SWTBotGefEditPart> allEditPartsAfter = Sets.newHashSet(editor.mainEditPart().children());
        SetView<SWTBotGefEditPart> newParts = Sets.difference(allEditPartsAfter, allEditPartsBefore);
        assertEquals("Expected exactly one new element on the diagram.", 1, newParts.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Reopen outline
        designerViews.openOutlineView();

        super.tearDown();
    }
}
