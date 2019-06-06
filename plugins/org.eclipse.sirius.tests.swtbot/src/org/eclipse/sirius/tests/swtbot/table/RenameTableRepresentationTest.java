/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.table;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemTextCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;

/**
 * 
 * Test rename table representations.
 * 
 * @author jdupont
 */
public class RenameTableRepresentationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/tree/";

    private static final String MODELER_MODEL_FILENAME = "ecore.odesign";

    private static final String SESSION_MODEL_FILENAME = "tree.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "tree.ecore";

    private static final String FILE_DIR = "/";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String P1 = "p1";

    private static final String TABLE_NAME = "myTable";

    private static final String TABLE_RENAME = "myTableRename";

    private static final String TITLE_RENAME_DIALOG = "Rename representation";

    private UILocalSession localSession;

    private UIResource sessionAirdResource;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + "/" + MODELER_MODEL_FILENAME, "/" + getProjectName() + "/" + MODELER_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + getProjectName() + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME, "/" + getProjectName() + "/" + SESSION_MODEL_FILENAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_MODEL_FILENAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        if ("".equals(localSession.getOpenedSession().getSelectedViewpoints(false).iterator().next().getName().trim())) {
            Set<String> set = new HashSet<String>();
            set.add(VIEWPOINT_NAME);
            Set<String> setEmpty = new HashSet<String>();
            localSession.changeViewpointSelection(set, setEmpty);
        }
    }

    /**
     * Test rename table representation on right click on DTable.
     * 
     * @throws Exception
     *             when exception thrown
     */
    @Test
    public void testRenameTableRepresentation() throws Exception {
        SWTBotTreeItem treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).select();
        // Create table
        createTable(treeItem);

        treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).expandNode(TABLE_NAME).select();

        // Rename table
        SWTBotUtils.clickContextMenu(treeItem, "Rename");
        SWTBot dialogBot = SWTBotSiriusHelper.getShellBot(TITLE_RENAME_DIALOG);
        dialogBot.text(0).setText(TABLE_RENAME);
        dialogBot.button("OK").click();

        bot.waitUntil(new TreeItemTextCondition(treeItem, TABLE_RENAME));

        treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).expandNode(TABLE_RENAME).select();
        assertTrue("The name of table representation must be equal to the new name", TABLE_RENAME.equals(treeItem.select().getText()));
        assertTrue("the editor name must be same that table representation", treeItem.select().getText().equals(bot.activeEditor().getTitle()));
    }

    /**
     * Test create table representation on right click on ECore package.
     * 
     * @param treeItem
     *            swtbotTreeItem
     */
    private void createTable(SWTBotTreeItem treeItem) {
        SWTBotUtils.clickContextMenu(treeItem, "new Classes");

        SWTBot dialogBot = SWTBotSiriusHelper.getShellBot("New Classes");
        dialogBot.text(0).setText(TABLE_NAME);
        dialogBot.button("OK").click();

        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(TABLE_NAME);
        odesignEditor.setFocus();

        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotTreeItem defaultItem = tree.expandNode("new EClass 1").expandNode("new Attribute").select();
        assertTrue(defaultItem != null);
    }

}
