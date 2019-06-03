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
package org.eclipse.sirius.tests.swtbot;

import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.internal.preference.SiriusPreferencePage;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * All tests for control/uncontrol for session with no description
 * 
 * @author nlepine, smonnier
 */
public class ControlUncontrolWithNoRepresentationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String CONTROL_SHELL_NAME = "Control";

    private static final String UNCONTROL_SHELL_NAME = "Uncontrol representations?";

    private static final String SAVE_SHELL_NAME = "Save Resource";

    private static final String MODEL = "tc2066.ecore";

    private static final String SESSION_FILE = "tc2066.aird";

    private static final String DATA_UNIT_DIR = "data/unit/control/tc2066/";

    private static final String MODEL_2203 = "tc2203.ecore";

    private static final String SESSION_FILE_2203 = "tc2203.aird";

    private static final String DATA_UNIT_DIR_2203 = "data/unit/control/tc2203/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UIResource modelUIResource;

    private UILocalSession localSession;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileDefineToTestProject(MODEL, SESSION_FILE);
    }

    private void copyFileDefineToTestProject(final String... fileNames) {
        for (final String fileName : fileNames) {
            if (fileName.startsWith("tc2203")) {
                EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_2203 + fileName, getProjectName() + "/" + fileName);
            } else {
                EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + fileName, getProjectName() + "/" + fileName);
            }
        }
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
    public void testControlWithoutRepresentation() throws Exception {
        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(modelUIResource);
        final SWTBotTreeItem packageNodeToControl = semanticResourceNode.expandNode("tc1993").expandNode("sp1").select();

        SWTBotUtils.clickContextMenu(packageNodeToControl, "Control...");

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(CONTROL_SHELL_NAME));
        final SWTBotShell controlShell = bot.shell(CONTROL_SHELL_NAME);
        controlShell.activate();

        final SWTBotButton ok = bot.button("OK");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(ok));
        ok.click();

        assertFileExists(TEMP_PROJECT_NAME + "/tc2066_sp1.ecore");
        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2066_sp1.aird");

        final SWTBotTreeItem semanticResourceNode2 = localSession.getSemanticResourceNode(modelUIResource);
        final SWTBotTreeItem packageNodeToControl2 = semanticResourceNode2.expandNode("tc1993").expandNode("sp1").select();
        SWTBotUtils.clickContextMenu(packageNodeToControl2, "Uncontrol");

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(UNCONTROL_SHELL_NAME));
        final SWTBotShell uncontrolShell = bot.shell(UNCONTROL_SHELL_NAME);
        uncontrolShell.activate();

        final SWTBotButton yes = bot.button("Yes");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(yes));
        yes.click();

        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2066_sp1.ecore");
        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2066_sp1.aird");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testControlWithoutRepresentation_EmptyAirdAllowed() throws Exception {

        // Activate programmatically the viewpoint preference : Creates an empty
        // aird fragment on control even if there is no selected representation
        changeSiriusPreference(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);

        // Control sp1
        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(modelUIResource);
        final SWTBotTreeItem packageNodeToControl = semanticResourceNode.expandNode("tc1993").expandNode("sp1").select();

        SWTBotUtils.clickContextMenu(packageNodeToControl, "Control...");

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(CONTROL_SHELL_NAME));
        final SWTBotShell controlShell = bot.shell(CONTROL_SHELL_NAME);
        controlShell.activate();

        final SWTBotButton ok = bot.button("OK");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(ok));
        ok.click();

        assertFileExists(TEMP_PROJECT_NAME + "/tc2066_sp1.ecore");
        assertFileExists(TEMP_PROJECT_NAME + "/tc2066_sp1.aird");

        // Check viewpoints are activated in the new aird fragment even if there
        // is no representation
        // Close the parent session
        localSession.getRootSessionTreeItem().select();
        SWTBotUtils.clickContextMenu(localSession.getRootSessionTreeItem(), "Close");

        // Open a session on the new aird fragment
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, "tc2066_sp1.aird");
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Check Representations per category contains only the Design viewpoint
        // with no representation
        SWTBotTreeItem expandNode = localSession.getRootSessionTreeItem().expand().expandNode("Representations per category");
        List<String> nodes = expandNode.getNodes();
        assertEquals("The number of selected viewpoint is not as expected", 1, nodes.size());
        assertEquals("The selected viewpoint should be the Design viewpoint", "Design", nodes.get(0));
        SWTBotTreeItem designNode = expandNode.expandNode("Design");
        assertEquals("The Design viewpoint should not have any representation", 0, designNode.getNodes().size());

        // Close the session on the fragment aird
        localSession.getRootSessionTreeItem().select();
        SWTBotUtils.clickContextMenu(localSession.getRootSessionTreeItem(), "Close");

        // Open back the parent session
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Uncontrol sp1
        final SWTBotTreeItem semanticResourceNode2 = localSession.getSemanticResourceNode(modelUIResource);
        final SWTBotTreeItem packageNodeToControl2 = semanticResourceNode2.expandNode("tc1993").expandNode("sp1").select();
        SWTBotUtils.clickContextMenu(packageNodeToControl2, "Uncontrol");

        // Save
        bot.waitUntilWidgetAppears(Conditions.shellIsActive(SAVE_SHELL_NAME));
        final SWTBotShell saveShell = bot.shell(SAVE_SHELL_NAME);
        saveShell.activate();
        SWTBotButton yes = bot.button("Yes");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(yes));
        yes.click();

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(UNCONTROL_SHELL_NAME));
        final SWTBotShell uncontrolShell = bot.shell(UNCONTROL_SHELL_NAME);
        uncontrolShell.activate();

        yes = bot.button("Yes");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(yes));
        yes.click();

        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2066_sp1.ecore");
        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2066_sp1.aird");

        // Deactivate manually the viewpoint preference : Creates an empty aird
        // fragment on control even if there is no selected representation
        SWTBotSiriusHelper.menu(bot, "Window").menu("Preferences").click();
        bot.tree().select("Sirius");
        bot.checkBox(SiriusPreferencePage.EMPTY_AIRD_ON_CONTROL_CHECKBOX_LABEL).click();
        bot.button("OK").click();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testControlWithoutRepresentation_WithManyActiveSiriuss_EmptyAirdAllowed() throws Exception {
        copyFileDefineToTestProject(MODEL_2203, SESSION_FILE_2203);

        modelUIResource = new UIResource(designerProject, FILE_DIR, MODEL_2203);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_2203);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Activate programmatically the viewpoint preference : Creates an empty
        // aird fragment on control even if there is no selected representation
        changeSiriusPreference(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);

        // Control package 0
        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(modelUIResource);
        final SWTBotTreeItem packageNodeToControl = semanticResourceNode.expandNode("aaaa").expandNode("0").select();

        SWTBotUtils.clickContextMenu(packageNodeToControl, "Control...");

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(CONTROL_SHELL_NAME));
        final SWTBotShell controlShell = bot.shell(CONTROL_SHELL_NAME);
        controlShell.activate();

        final SWTBotButton ok = bot.button("OK");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(ok));
        ok.click();

        final SWTBotButton finish = bot.button("Finish");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(finish));
        finish.click();

        assertFileExists(TEMP_PROJECT_NAME + "/tc2203_0.ecore");
        assertFileExists(TEMP_PROJECT_NAME + "/tc2203_0.aird");

        // Check viewpoints are activated in the new aird fragment even if there
        // is no representation
        // Close the parent session
        localSession.getRootSessionTreeItem().select();
        SWTBotUtils.clickContextMenu(localSession.getRootSessionTreeItem(), "Close");

        // Open a session on the new aird fragment
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, "tc2203_0.aird");
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Check Representations per category contains only the Design viewpoint
        // with no representation
        SWTBotTreeItem expandNode = localSession.getRootSessionTreeItem().expand().expandNode("Representations per category");
        List<String> nodes = expandNode.getNodes();
        assertEquals("The number of selected viewpoint is not as expected", 3, nodes.size());

        assertEquals("The selected viewpoint should be the Archetype viewpoint", "Archetype", nodes.get(0));
        SWTBotTreeItem archetypeNode = expandNode.expandNode("Archetype");
        assertEquals("The Archetype viewpoint should not have any representation", 0, archetypeNode.getNodes().size());
        assertEquals("The selected viewpoint should be the Design viewpoint", "Design", nodes.get(1));
        SWTBotTreeItem designNode = expandNode.expandNode("Design");
        assertEquals("The Design viewpoint should not have any representation", 0, designNode.getNodes().size());
        assertEquals("The selected viewpoint should be the Design viewpoint", "Quality", nodes.get(2));
        SWTBotTreeItem qualityNode = expandNode.expandNode("Quality");
        assertEquals("The Quality viewpoint should not have any representation", 0, qualityNode.getNodes().size());

        // Close the session on the fragment aird
        localSession.getRootSessionTreeItem().select();
        SWTBotUtils.clickContextMenu(localSession.getRootSessionTreeItem(), "Close");

        // Open back the parent session
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_2203);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Uncontrol 0
        final SWTBotTreeItem semanticResourceNode2 = localSession.getSemanticResourceNode(modelUIResource);
        final SWTBotTreeItem packageNodeToControl2 = semanticResourceNode2.expandNode("aaaa").expandNode("0").select();
        SWTBotUtils.clickContextMenu(packageNodeToControl2, "Uncontrol");

        // Save
        bot.waitUntilWidgetAppears(Conditions.shellIsActive(SAVE_SHELL_NAME));
        final SWTBotShell saveShell = bot.shell(SAVE_SHELL_NAME);
        saveShell.activate();
        SWTBotButton yes = bot.button("Yes");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(yes));
        yes.click();

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(UNCONTROL_SHELL_NAME));
        final SWTBotShell uncontrolShell = bot.shell(UNCONTROL_SHELL_NAME);
        uncontrolShell.activate();

        yes = bot.button("Yes");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(yes));
        yes.click();

        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2203_0.ecore");
        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2203_0.aird");

        // Deactivate manually the viewpoint preference : Creates an empty aird
        // fragment on control even if there is no selected representation
        SWTBotSiriusHelper.menu(bot, "Window").menu("Preferences").click();
        bot.tree().select("Sirius");
        bot.checkBox(SiriusPreferencePage.EMPTY_AIRD_ON_CONTROL_CHECKBOX_LABEL).click();
        bot.button("OK").click();

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckCancelControl() throws Exception {
        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(modelUIResource);
        final SWTBotTreeItem packageNodeToControl = semanticResourceNode.expandNode("tc1993").expandNode("sp1").select();

        SWTBotUtils.clickContextMenu(packageNodeToControl, "Control...");

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(CONTROL_SHELL_NAME));
        final SWTBotShell controlShell = bot.shell(CONTROL_SHELL_NAME);
        controlShell.activate();

        final SWTBotButton cancel = bot.button("Cancel");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(cancel));
        cancel.click();

        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2066" + "_sp1.ecore");
        assertFileDoesNotExists(TEMP_PROJECT_NAME + "/tc2066_sp1.aird");
    }

    private void assertFileDoesNotExists(String wksPath) {
        assertFalse(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath)).exists());
    }

    private void assertFileExists(String wksPath) {
        assertTrue(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath)).exists());
    }
}
