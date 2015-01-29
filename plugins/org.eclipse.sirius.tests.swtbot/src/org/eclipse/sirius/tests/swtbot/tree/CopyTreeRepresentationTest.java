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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;

/**
 * 
 * Test copy tree representations. JIRA number ticket : VP-923
 * 
 * @author jdupont
 */
public class CopyTreeRepresentationTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /**
     * Session.
     */
    private UIResource sessionAirdResource;

    /**
     * Local Session.
     */
    private UILocalSession localSession;

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
     * Test copy tree representation on right click on DTree.
     * 
     * @throws Exception
     *             when exception thrown
     */
    @Test
    public void testCopyTreeRepresentation() throws Exception {
        SWTBotTreeItem treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode("p1").select();
        // Create tree
        createTree(treeItem);

        treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode("p1").expandNode("myTree").select();
        // Copy tree
        SWTBotUtils.clickContextMenu(treeItem, "Copy");

        bot.activeShell().bot().text(0).setText("myTreeCopy");
        bot.button("OK").click();

        treeItem = localSession.getLocalSessionBrowser().perSemantic().expandNode("p1").expandNode("myTreeCopy").select();

        assertTrue(treeItem != null);

    }

    /**
     * Test create tree representation on right click on ECore package.
     * 
     * @param treeItem
     *            swtbotTreeItem
     */
    private void createTree(SWTBotTreeItem treeItem) {
        SWTBotUtils.clickContextMenu(treeItem, "new Tree");

        bot.activeShell().bot().text(0).setText("myTree");
        bot.button("OK").click();

        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName("myTree");
        odesignEditor.setFocus();

        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotTreeItem defaultItem = tree.expandNode("new EClass 1").expandNode("new Attribute").select();
        assertTrue(defaultItem != null);
    }
}
