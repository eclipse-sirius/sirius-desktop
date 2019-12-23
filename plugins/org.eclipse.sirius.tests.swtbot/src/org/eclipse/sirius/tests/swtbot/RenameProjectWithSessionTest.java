/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Rename project with opened session
 * 
 * @author nlepine
 */
public class RenameProjectWithSessionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String RENAME_RESOURCE = "Rename Resource";

    private static final String RENAME = "Rename...";

    private static final String RENAMED_PROJECT = "RenamedProject";

    private static final String MODEL = "tc-1850.ecore";

    private static final String SESSION_FILE_1 = "tc-1850-1.aird";

    private static final String DATA_UNIT_DIR = "data/unit/session/sessionCreation/tc-1850/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE_1);
    }

    /**
     * Rename the project with opened session : this closes the session
     */
    public void testRenameProjectWithOpenSession() {
        openSessionFromExistingAird("tc-1850-1.aird");

        // rename the project
        renameProject(designerProject.getName(), RENAMED_PROJECT);
        assertTrue(getProjectNamed(RENAMED_PROJECT));

        // Rename the new rename project with default project name
        renameProject(RENAMED_PROJECT, getProjectName());
        assertTrue(getProjectNamed(designerProject.getName()));
    }

    /**
     * Rename the project with close session
     */
    public void testRenameProjectWithCloseSession() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        // rename the project
        renameProject(designerProject.getName(), RENAMED_PROJECT);
        assertTrue(getProjectNamed(RENAMED_PROJECT));

        // Rename the new rename project with default project name
        renameProject(RENAMED_PROJECT, getProjectName());
        assertTrue(getProjectNamed(designerProject.getName()));
    }

    /**
     * Rename the project
     * 
     * @param closeSession
     * 
     */
    private void renameProject(String projectName, String name) {
        SWTBotTreeItem treeItem = getProjectTreeItem(projectName);
        treeItem.select();
        treeItem.contextMenu(RENAME).click();
        UIThreadRunnable.syncExec(bot.getDisplay(), () -> {
            treeItem.widget.setText(name);
        });
        treeItem.pressShortcut(Keystrokes.CR);
    }

    /**
     * Return true if the name passed to parameter correspond to project.
     * 
     * @param projectName
     *            the name of project
     * @return true if the name correspond to project
     */
    private boolean getProjectNamed(String projectName) {
        boolean nameIsOk = false;
        try {
            getProjectTreeItem(projectName);
            nameIsOk = true;
        } catch (WidgetNotFoundException wnfe) {
            fail("The project is not named " + projectName);
        }
        return nameIsOk;
    }

    private void openSessionFromExistingAird(final String airdName) {
        final UIResource sessionAirdResource = new UIResource(designerProject, "/", airdName);
        designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    private SWTBotTreeItem getProjectTreeItem(String projectName) {
        final SWTBot projectExplorerBot = bot.viewByTitle("Model Explorer").bot();

        final SWTBotTree projectExplorerTree = projectExplorerBot.tree();
        projectExplorerTree.expandNode(projectName);
        SWTBotTreeItem treeItem = projectExplorerTree.getTreeItem(projectName);
        treeItem.expand();

        treeItem = projectExplorerTree.getTreeItem(projectName);
        return treeItem;
    }

}
