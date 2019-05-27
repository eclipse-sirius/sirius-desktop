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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
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
 * Test create, open, close and delete tree representations.
 * 
 * @author jdupont
 */
public class OpenCloseCreateDeleteTreeRepresentationTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /**
     * 
     */
    private static final String OK = "OK";

    /**
     * 
     */
    private static final String EDIT = "Edit";

    /**
     * 
     */
    private static final String NEW_E_CLASS_1 = "new EClass 1";

    /**
     * 
     */
    private static final String NEW_ATTRIBUTE = "new Attribute";

    /**
     * 
     */
    private static final String P1 = "p1";

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
     * Editor name.
     */
    private String editor = "myTree";

    /**
     * Session.
     */
    private UIResource sessionAirdResource;

    /**
     * Local Session.
     */
    private UILocalSession localSession;

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
     * Test create, open, close and delete tree representation.
     * 
     * @throws Exception
     *             when exception thrown
     */
    @Test
    public void testCreateDeletTreeRepresentation() throws Exception {
        SWTBotTreeItem treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).select();

        // Test create tree representation
        createTree(treeItem);

        // Test open tree representation
        openTree(treeItem);

        // Test close tree representation
        closeTree(treeItem);

        // Test delete tree representation
        deleteTree(treeItem);

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
        wizardBot.text(0).setText(editor);
        wizardBot.button(OK).click();
        // waiting for editor opening
        SWTBotSiriusHelper.getTreeDialectEditorBots(editor);
        treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).expandNode(editor).select();

        assertTrue(treeItem != null);
    }

    /**
     * Test open tree representation on double click on DTree.
     * 
     * @param treeItem
     *            swtbotTreeItem
     */
    private void openTree(SWTBotTreeItem treeItem) {
        treeItem.doubleClick();

        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(editor);
        odesignEditor.setFocus();

        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotTreeItem defaultItem = tree.expandNode(NEW_E_CLASS_1).expandNode(NEW_ATTRIBUTE).select();
        assertTrue(defaultItem != null);
    }

    /**
     * Test close tree representation.
     * 
     * @param treeItem
     *            swtbotTreeItem
     */
    private void closeTree(SWTBotTreeItem treeItem) {
        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(editor);
        odesignEditor.setFocus();
        odesignEditor.close();
        try {
            odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(editor);
            assertFalse("The DTree editor must be close", true);
        } catch (Exception e) {
            String message = e.getMessage();
            assertThat(message, containsString("Could not find editor"));
        }

    }

    /**
     * Test create tree representation on right click on DTree.
     * 
     * @param treeItem
     *            swtbotTreeItem
     */
    private void deleteTree(SWTBotTreeItem treeItem) {
        treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).expandNode(editor).select();
        SWTBotUtils.clickContextMenu(treeItem, "Delete");
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Delete representation");
        wizardBot.button(OK).click();
        try {
            treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode(P1).expandNode(editor).select();
            assertFalse("The DTree editor must be delete", true);
        } catch (Exception e) {
            String message = e.getMessage();
            assertThat(message, equalTo("Could not find node with text: myTree"));
        }
    }

}
