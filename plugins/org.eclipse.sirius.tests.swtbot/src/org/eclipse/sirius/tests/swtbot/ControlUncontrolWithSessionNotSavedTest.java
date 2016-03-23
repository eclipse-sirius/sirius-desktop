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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * All tests for control/uncontrol : the session is not saved
 * 
 * @author dlecan
 */
public class ControlUncontrolWithSessionNotSavedTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String CONTROL_SHELL_NAME = "Control";

    private static final String SAVE_SHELL_NAME = "Save Resource";

    private static final String MODEL = "tc1993.ecore";

    private static final String SESSION_FILE = "tc1993.aird";

    private static final String DATA_UNIT_DIR = "data/unit/control/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UIResource modelUIResource;

    private UILocalSession localSession;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        modelUIResource = new UIResource(designerProject, FILE_DIR, MODEL);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSaveSessionCheckDiagramAndTableSelectionWhenControl() throws Exception {
        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(modelUIResource);
        SWTBotTreeItem packageNodeToControl = semanticResourceNode.expandNode("tc1993").expandNode("sp1").select();
        localSession.newDiagramRepresentation("blank diagram", "").on(packageNodeToControl).withDefaultName().ok();
        localSession.selectView();
        final SWTBotTreeItem semanticResourceNode2 = localSession.getSemanticResourceNode(modelUIResource);
        SWTBotTreeItem packageNodeToControl2 = semanticResourceNode2.expandNode("tc1993").expandNode("sp1").select();
        SWTBotUtils.clickContextMenu(packageNodeToControl2, "Control...");

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(SAVE_SHELL_NAME));
        final SWTBotShell saveShell = bot.shell(SAVE_SHELL_NAME);
        saveShell.activate();

        final SWTBotButton yes = bot.button("Yes");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(yes));
        yes.click();

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(CONTROL_SHELL_NAME));
        final SWTBotShell controlShell = bot.shell(CONTROL_SHELL_NAME);
        controlShell.activate();

        final SWTBotButton ok = bot.button("OK");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(ok));
        ok.click();

        bot.waitUntilWidgetAppears(Conditions.shellIsActive("Wizard of representations selection"));
        final SWTBotShell wizardShell = bot.shell("Wizard of representations selection");
        wizardShell.activate();

        final SWTBotTree representationTree = bot.tree();
        final SWTBotTreeItem[] items = representationTree.getAllItems()[0].expand().getItems()[0].expand().getItems()[0].expand().getItems();

        // 2 diagram2 + 1 table
        assertEquals("Wrong number of detected representations", 3, items.length);

        assertTrue("1st representation should be checked", items[0].isChecked());
        assertTrue("2nd representation should be checked", items[1].isChecked());
        assertTrue("3rd representation should be checked", items[2].isChecked());

        final SWTBotButton finish = bot.button("Finish");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(finish));
        finish.click();

        assertFileExists(TEMP_PROJECT_NAME + "/tc1993_sp1.ecore");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoSavingSessionBeforeControl() throws Exception {
        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(modelUIResource);
        final SWTBotTreeItem packageNodeToControl = semanticResourceNode.expandNode("tc1993").expandNode("sp1").select();
        localSession.newDiagramRepresentation("blank diagram", "").on(packageNodeToControl).withDefaultName().ok();
        localSession.selectView();
        final SWTBotTreeItem semanticResourceNode2 = localSession.getSemanticResourceNode(modelUIResource);
        SWTBotTreeItem packageNodeToControl2 = semanticResourceNode2.expandNode("tc1993").expandNode("sp1").select();
        SWTBotUtils.clickContextMenu(packageNodeToControl2, "Control...");

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(SAVE_SHELL_NAME));
        final SWTBotShell saveShell = bot.shell(SAVE_SHELL_NAME);
        saveShell.activate();

        final SWTBotButton no = bot.button("No");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(no));
        no.click();

        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc1993_sp1.ecore");
    }

    private void assertFileDoesNotExists(String wksPath) {
        assertFalse(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath)).exists());
    }

    private void assertFileExists(String wksPath) {
        assertTrue(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath)).exists());
    }
}
