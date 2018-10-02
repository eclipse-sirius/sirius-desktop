/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class for direct edit on tree item.
 * 
 * @author jmallet
 */
public class DirectEditTreeItemTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/directEdit/";

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "487828.odesign";

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "representations.aird";

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "My.ecore";

    private SWTBotEditor treeEditorBot;

    private UITreeRepresentation treeRepresentation;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_FILE, SESSION_RESOURCE_FILE,
                SEMANTIC_RESOURCE_FILE);

        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();

        treeRepresentation = openEditor(localSession, "487428", "tree", "new tree");

        treeEditorBot = treeRepresentation.getEditor();
    }

    /**
     * Test that direct edition on label of the tree element modifies the
     * label with F2 key.
     */
    public void testDirectEditLabelWithF2Key() {
        SWTBotTreeItem treeItem = treeEditorBot.bot().tree().getTreeItem("C1").select();
        treeItem.click();
        treeItem.pressShortcut(Keystrokes.F2);
        treeEditorBot.bot().activeShell();
        treeEditorBot.bot().text().typeText("newLabel\n");
        treeItem.expand();
        treeEditorBot.bot().waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return (treeItem.getText()).contains("newLabel");
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "The treeItem must display \"newLabel\" after direct edit with F2";
            }
        });
    }

    /**
     * Test that direct edition on label of the tree element modifies the
     * label with return key.
     */
    public void testDirectEditLabelWithReturnKey() {
        SWTBotTreeItem treeItem = treeEditorBot.bot().tree().getTreeItem("C1").select();
        treeItem.click();
        treeItem.pressShortcut(SWT.CR, SWT.LF);
        treeEditorBot.bot().activeShell();
        treeEditorBot.bot().text().typeText("newLabel\n");
        treeItem.expand();
        treeEditorBot.bot().waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return (treeItem.getText()).contains("newLabel");
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "The treeItem must display \"newLabel\" after direct edit with F2";
            }
        });
    }

    @Override
    protected void tearDown() throws Exception {
        treeEditorBot.close();
        treeEditorBot = null;
        treeRepresentation = null;
        super.tearDown();
    }

}
