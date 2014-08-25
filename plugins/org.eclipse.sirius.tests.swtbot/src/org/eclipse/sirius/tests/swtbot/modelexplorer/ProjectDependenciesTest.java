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
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIProject;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ProjectDependenciesItemDisplayed;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that we cannot select a representation file when adding a semantic
 * resource on project dependencies.
 * 
 * @author fbarbin
 */
public class ProjectDependenciesTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL_FILE = "rep1.ecore";

    private static final String MODEL_FILE_2 = "rep2.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String DATA_UNIT_DIR = "data/unit/projectDependencies/";

    SWTBot modelExplorerViewBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, MODEL_FILE_2, SESSION_FILE);
    }

    /**
     * {@inheritDoc}
     */
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        modelExplorerView.setFocus();
        modelExplorerViewBot = modelExplorerView.bot();
        final UIProject uiProject = new UIProject(TEMP_PROJECT_NAME);
        uiProject.convertToModelingProject();

        modelExplorerViewBot.waitUntil(new ProjectDependenciesItemDisplayed(modelExplorerViewBot, TEMP_PROJECT_NAME));
    }

    /**
     * This test verifies that aird resource is not available in workspace
     * resource selection wizard.
     */
    public void testCannotSelectAird() {
        SWTBotUtils.waitAllUiEvents();
        SWTBotTree tree = modelExplorerViewBot.tree();
        tree.setFocus();
        SWTBotTreeItem swtBotTreeItem = tree.expandNode(TEMP_PROJECT_NAME);
        SWTBotTreeItem projectDependencies = swtBotTreeItem.getNode(0);
        SWTBotUtils.clickContextMenu(projectDependencies.select(), "Add Model");
        bot.waitUntilWidgetAppears(Conditions.shellIsActive("Select resources to add"));
        SWTBotShell shell = bot.activeShell();
        shell.setFocus();
        SWTBot shellBot = shell.bot();
        shellBot.button("Browse &Workspace...").click();

        shell = bot.activeShell();
        shellBot = shell.bot();
        tree = shellBot.tree();
        swtBotTreeItem = tree.expandNode(TEMP_PROJECT_NAME);
        for (SWTBotTreeItem currentItem : swtBotTreeItem.getItems()) {
            assertFalse("The aird resource should not be available for selection.", "representations.aird".equals(currentItem.getText()));
        }

        shell = bot.activeShell();
        shellBot = shell.bot();
        shellBot.button("Cancel").click();

        SWTBotUtils.waitAllUiEvents();

        shell = bot.activeShell();
        shellBot = shell.bot();
        shellBot.button("Cancel").click();
    }
}
