/*******************************************************************************
 * Copyright (c) 2017, 2022 Obeo.
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
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class for direct edit on tree item.
 * 
 * @author jmallet
 */
public class DirectEditTreeItemTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/directEdit/"; //$NON-NLS-1$

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "487828.odesign"; //$NON-NLS-1$

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "representations.aird"; //$NON-NLS-1$

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "My.ecore"; //$NON-NLS-1$

    private SWTBotEditor treeEditorBot;

    private UITreeRepresentation treeRepresentation;

    private final String c1TreeItemName = "C1"; //$NON-NLS-1$

    private final String suffixName = "newLabel"; //$NON-NLS-1$

    private final String suffixNameAndCarriageReturn = suffixName + "\n"; //$NON-NLS-1$

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_FILE, SESSION_RESOURCE_FILE, SEMANTIC_RESOURCE_FILE);

        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILE); //$NON-NLS-1$
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
    }

    private void endSetup() {
        treeRepresentation = openEditor(localSession, "487428", "tree", "new tree"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        treeEditorBot = treeRepresentation.getEditor();
    }

    @Override
    protected void tearDown() throws Exception {
        treeEditorBot.close();
        treeEditorBot = null;
        treeRepresentation = null;
        super.tearDown();
    }

    /**
     * Test that direct edition on label of the tree element modifies the label with F2 key.
     */
    public void testDirectEditLabelWithF2Key() {
        endSetup();
        SWTBotTreeItem treeItem = treeEditorBot.bot().tree().getTreeItem(c1TreeItemName).select();
        treeItem.click();
        treeItem.pressShortcut(Keystrokes.F2);
        treeEditorBot.bot().activeShell();
        treeEditorBot.bot().text().typeText(suffixNameAndCarriageReturn);
        treeItem.expand();
        treeEditorBot.bot().waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return (treeItem.getText()).contains(suffixName);
            }

            @Override
            public String getFailureMessage() {
                return "The treeItem must display \"newLabel\" after direct edit with F2"; //$NON-NLS-1$
            }
        });
    }

    /**
     * Test that direct edition on label of the tree element modifies the label with return key.
     */
    public void testDirectEditLabelWithReturnKey() {
        endSetup();
        SWTBotTreeItem treeItem = treeEditorBot.bot().tree().getTreeItem(c1TreeItemName).select();
        treeItem.click();
        treeItem.pressShortcut(SWT.CR, SWT.LF);
        treeEditorBot.bot().activeShell();
        treeEditorBot.bot().text().typeText(suffixNameAndCarriageReturn);
        treeItem.expand();
        treeEditorBot.bot().waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return (treeItem.getText()).contains(suffixName);
            }

            @Override
            public String getFailureMessage() {
                return "The treeItem must display \"newLabel\" after direct edit with F2"; //$NON-NLS-1$
            }
        });
    }

    /**
     * Test that direct edition on label of the tree element modifies the label with "any alphanumeric" key if the
     * system property "org.eclipse.sirius.ui.restoreBehaviorEnablingDirectEditOnAlphanumericKey" is set to true; ie to
     * restore previous behavior.
     */
    public void testDirectEditLabelWithAnyAlphanumericKeyWithSpecificSystemProperty() {
        System.setProperty("org.eclipse.sirius.ui.restoreBehaviorEnablingDirectEditOnAlphanumericKey", "true"); //$NON-NLS-1$ //$NON-NLS-2$
        try {
            endSetup();
            SWTBotTreeItem treeItem = treeEditorBot.bot().tree().getTreeItem(c1TreeItemName).select();
            treeItem.click();
            treeItem.pressShortcut(Keystrokes.create('P'));
            treeEditorBot.bot().activeShell();
            treeEditorBot.bot().text().typeText(suffixNameAndCarriageReturn);
            treeItem.expand();
            treeEditorBot.bot().waitUntil(new DefaultCondition() {

                @Override
                public boolean test() throws Exception {
                    return (treeItem.getText()).contains(suffixName);
                }

                @Override
                public String getFailureMessage() {
                    return "The treeItem must display \"newLabel\" after direct edit with F2"; //$NON-NLS-1$
                }
            });
        } finally {
            System.setProperty("org.eclipse.sirius.ui.restoreBehaviorEnablingDirectEditOnAlphanumericKey", "false"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Test that the direct edition on label of the tree element is not possible when pressing an alphanumeric key.
     * Instead, the next item, starting with the key pressed, is selected.
     */
    public void testDirectEditLabelWithAnyAlphanumericKeyDoesNothing() {
        endSetup();
        SWTBotTreeItem treeItem = treeEditorBot.bot().tree().getTreeItem(c1TreeItemName).select();
        treeItem.click();
        treeItem.pressShortcut(Keystrokes.create('P'));
        treeEditorBot.bot().activeShell();

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            // Reduce the timeout as the fail is expected
            SWTBotPreferences.TIMEOUT = 2000;
            treeEditorBot.bot().text().typeText(suffixNameAndCarriageReturn);
            fail("The direct edit should not be triggered on an alphanumeric key."); //$NON-NLS-1$
        } catch (WidgetNotFoundException e) {
            // This is the expected behavior because the direct edit should not be triggered
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
        treeEditorBot.bot().waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return ("P2".equals(treeEditorBot.bot().tree().selection().get(0, 0))); //$NON-NLS-1$
            }

            @Override
            public String getFailureMessage() {
                return "After pressing \"P\" key on \"C1\", the selected item should be the package \"P2\"."; //$NON-NLS-1$
            }
        });
    }
}
