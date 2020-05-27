/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.internal.session.GenericSWTCallBack;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.IWorkbenchPreferenceConstants;

/**
 * Tests for the saving of an editors
 * 
 * @author lredor
 */
public class EditorSavingTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/blankEcore/";

    private static final String MODEL = "blank.ecore";

    private UILocalSession localSession;

    private SWTBotTreeItem rootEPackage;

    private UIResource semanticModel;

    private UICallBack originalUICallBack;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        SiriusEditPlugin.getPlugin().setUiCallback(originalUICallBack);
        super.tearDown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // User will be notifed on the first close with mutli reps opened
        // editors from the same session, if he chooses to disable the pref, it
        // should not be notified anymore.
        changePlatformUIPreference(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, false);
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        semanticModel = new UIResource(designerProject, MODEL);
        localSession = designerPerspective.openSessionCreationWizardFromSemanticResource(semanticModel).fromAlreadySelectedSemanticResource().withDefaultSessionName().finish()
                .selectViewpoints("Design");
        localSession.getLocalSessionBrowser().perCategory();
        localSession.getLocalSessionBrowser().perCategory().selectViewpoint("Design");

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(semanticModel);
        rootEPackage = semanticResourceNode.expandNode("root").click();

        // Restore the default preference values of Sirius
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);
        // Change the UICallBack to have the Sirius one
        originalUICallBack = SiriusEditPlugin.getPlugin().getUiCallback();
        SiriusEditPlugin.getPlugin().setUiCallback(new GenericSWTCallBack());
    }

    /**
     * Test that when a session is dirty, a confirmation is asked for the closing of the last diagram editor. The
     * message must start with the session name and not with the editor name.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSaveOnCloseDiagram() throws Exception {
        final UIDiagramRepresentation firstDiagramRepresentation = localSession.newDiagramRepresentation("root package entities", "Entities").on(rootEPackage).withDefaultName().ok();
        firstDiagramRepresentation.open();

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(semanticModel);
        rootEPackage = semanticResourceNode.expandNode("root").click();
        final UIDiagramRepresentation secondDiagramRepresentation = localSession.newDiagramRepresentation("root package entities", "Entities").on(rootEPackage).withDefaultName().ok();
        secondDiagramRepresentation.open();

        commonFinalChecksIterativeClose();
    }

    /**
     * Test that when a session is dirty, a confirmation is asked for the closing of the last table editor. The message
     * must start with the session name and not with the editor name.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSaveOnCloseTable() throws Exception {
        final UITableRepresentation firstTableRepresentation = localSession.newTableRepresentation("Classes in root package", "Classes").on(rootEPackage).withDefaultName().ok();
        firstTableRepresentation.open();

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(semanticModel);
        rootEPackage = semanticResourceNode.expandNode("root").click();
        final UITableRepresentation secondTableRepresentation = localSession.newTableRepresentation("Classes in root package", "Classes").on(rootEPackage).withDefaultName().ok();
        secondTableRepresentation.open();

        commonFinalChecksIterativeClose();
    }

    /**
     * Test that when a session is dirty, a confirmation is asked for the closing of all editors. The message must start
     * with the session name and not with the editor name.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSaveOnCloseAll() throws Exception {
        final UIDiagramRepresentation firstDiagramRepresentation = localSession.newDiagramRepresentation("root package entities", "Entities").on(rootEPackage).withDefaultName().ok();
        firstDiagramRepresentation.open();

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(semanticModel);
        rootEPackage = semanticResourceNode.expandNode("root").click();
        final UIDiagramRepresentation secondDiagramRepresentation = localSession.newDiagramRepresentation("root package entities", "Entities").on(rootEPackage).withDefaultName().ok();
        secondDiagramRepresentation.open();

        rootEPackage = semanticResourceNode.expandNode("root").click();
        final UITableRepresentation firstTableRepresentation = localSession.newTableRepresentation("Classes in root package", "Classes").on(rootEPackage).withDefaultName().ok();
        firstTableRepresentation.open();

        rootEPackage = semanticResourceNode.expandNode("root").click();
        final UITableRepresentation secondTableRepresentation = localSession.newTableRepresentation("Classes in root package", "Classes").on(rootEPackage).withDefaultName().ok();
        secondTableRepresentation.open();

        commonFinalChecksCloseAll();
    }

    /**
     * Check that when a session is dirty, a confirmation is asked for the closing of the last editor. The message must
     * start with the session name and not with the editor name.
     * 
     * @throws Exception
     *             Test error.
     */
    private void commonFinalChecksIterativeClose() throws Exception {
        // Close the first editor (normally nothing is asked to the user)
        SWTBotCommonHelper.closeCurrentEditor();
        checkNoSaveDialog();

        // Close the second editor
        SWTBotCommonHelper.closeCurrentEditor();
        // That's the last editor for this session,
        checkSaveDialog();
    }

    /**
     * Check that when a session is dirty, a confirmation is asked when cloase all occurs. The message must start with
     * the session name and not with the editor name.
     * 
     * @throws Exception
     *             Test error.
     */
    private void commonFinalChecksCloseAll() throws Exception {
        // Close all, so it should have popup of
        // confirmation for saving.
        if (TestsUtil.is202006Platform()) {
            bot.activeEditor().bot().menu("Close All Editors").click();
        } else {
            bot.activeEditor().bot().menu("Close All").click();
        }
        checkSaveDialog();
    }

    private void checkNoSaveDialog() {
        // Reduce the timeout to 1 sec
        final long defaultTimeOut = SWTBotPreferences.TIMEOUT;
        SWTBotPreferences.TIMEOUT = 1000;

        try {
            bot.waitUntil(Conditions.shellIsActive("Save Resource"));
            fail("That is not the last editor for this session, so it shouldn't have popup of confirmation for saving.");
        } catch (TimeoutException e) {
            // Do nothing : It's the waited behavior.
        } finally {
            // Restore the default timeout
            SWTBotPreferences.TIMEOUT = defaultTimeOut;
        }
    }

    private void checkSaveDialog() {

        bot.waitUntil(Conditions.shellIsActive("Save"));
        // Check the message (must start with the session name, instead of
        // editor name).
        final SWTBotButton buttonNo = bot.button("No");
        final SWTBotLabel labelOfPopup = bot.label(1);
        final String msgOfPopup = labelOfPopup.getText();
        final String sessionName = localSession.getSessionResource().getName();
        assertFalse("The message (\"" + msgOfPopup + "\") should not mention the session (" + sessionName + ").", msgOfPopup.contains(sessionName));
        buttonNo.click();
    }

}
