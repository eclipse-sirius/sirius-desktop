/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemTextCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;

/**
 * 
 * Test rename tree representations. JIRA number ticket : VP-922
 * 
 * @author jdupont
 */
public class RenameTreeRepresentationTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /**
     * Odesign.
     */
    private static final String MODEL = "ecore.odesign";

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/tree/";

    /**
     * Session file.
     */
    private static final String SESSION_FILE = "tree.aird";

    /**
     * UML File.
     */
    private static final String ECORE_FILE = "tree.ecore";

    /**
     * File directory.
     */
    private static final String FILE_DIR = "/";

    /**
     * Constant for variable p1.
     */
    private static final String P1 = "p1";

    /**
     * Tree representation name.
     */
    private static final String TREE_NAME = "myTree";

    /**
     * Tree representation name after rename.
     */
    private static final String TREE_RENAME = "myTreeRename";

    /**
     * Local Session.
     */
    private UILocalSession localSession;

    /**
     * Session.
     */
    private UIResource sessionAirdResource;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ECORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

    }

    /**
     * Test rename tree representation on right click on DTree.
     * 
     * @throws Exception
     *             when exception thrown
     */
    @Test
    public void testRenameTreeRepresentation() throws Exception {
        SWTBotTreeItem treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).select();
        // Create tree
        createTree(treeItem);

        treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).expandNode(TREE_NAME).select();

        // Rename tree
        SWTBotUtils.clickContextMenu(treeItem, "Rename");

        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Rename representation");

        wizardBot.text(0).setText(TREE_RENAME);
        wizardBot.button("OK").click();

        bot.waitUntil(new TreeItemTextCondition(treeItem, TREE_RENAME));

        treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).expandNode(TREE_RENAME).select();
        assertTrue("The name of tree representation must be equal to the new name", TREE_RENAME.equals(treeItem.select().getText()));
        assertTrue("the editor name must be same that tree representation", treeItem.select().getText().equals(bot.activeEditor().getTitle()));

        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Test create tree representation on right click on ECore package.
     * 
     * @param treeItem
     *            swtbotTreeItem
     */
    private void createTree(SWTBotTreeItem treeItem) {
        SWTBotUtils.clickContextMenu(treeItem, "new Tree");

        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("New Tree");
        wizardBot.text(0).setText(TREE_NAME);
        wizardBot.button("OK").click();

        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(TREE_NAME);
        odesignEditor.setFocus();

        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotTreeItem defaultItem = tree.expandNode("new EClass 1").expandNode("new Attribute").select();
        assertTrue(defaultItem != null);
    }
}
