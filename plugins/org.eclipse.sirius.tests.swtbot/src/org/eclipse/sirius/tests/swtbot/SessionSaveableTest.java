/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionSavedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.api.command.AbstractSWTCallback;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;

/**
 *
 * Test pop ups appears when closing editors with session dirty. - Test personal UiCallback for pop up message on close
 * dirty representations correspond to message defines in personal UICallback. - Test pop up appears when many editors
 * dirty closed. Test VP-2458 VP-2454. Test VP-2457.
 *
 * @author jdupont
 */
public class SessionSaveableTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String SESSION_FILE = "sessionSaveable.aird";

    private static final String MODEL = "sessionSaveable.ecore";

    private static final String TEXT_FILE = "text.txt";

    private static final String DATA_UNIT_DIR = "data/unit/session/sessionSaveable/";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_INSTANCE_NAME = "p1 package entities";

    private static final String SECOND_REPRESENTATION_INSTANCE_NAME = "p1 package entities 2";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String NODE_CREATION_TOOL_NAME = "Class";

    private static final String TEST_UI_CALLBACK = "for test project ";

    private static final String CANCEL = "Cancel";

    private static final String NEXT = "Next >";

    private static final String BACK = "< Back";

    private static final String FINISH = "Finish";

    private static final String OK = "OK";

    private UICallBack uiCallBack;

    private UILocalSession localSession;

    private SWTBotSiriusDiagramEditor editor;

    private boolean oldValuePrefPromptWhenSaveableStillOpen;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, TEXT_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        // Change the UICallBack to have the Sirius one plus specific things
        uiCallBack = SiriusEditPlugin.getPlugin().getUiCallback();
        SiriusEditPlugin.getPlugin().setUiCallback(new SessionCallBack());
    }

    @Override
    protected void tearDown() throws Exception {
        // Restore original Call back
        SiriusEditPlugin.getPlugin().setUiCallback(uiCallBack);
        super.tearDown();
    }

    /**
     * Test that when closing dirty representation it's a good message display in pop up. The message is define in own
     * UICallBack test.
     */
    public void testSessionProvideOwnSaveable() {
        // Restore the default preference values of Sirius (not a customer
        // specific one)

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);

        oldValuePrefPromptWhenSaveableStillOpen = PlatformUI.getPreferenceStore().getBoolean(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN);
        try {
            PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, true);
            // Open session
            localSession = openSessionFromExistingAird(SESSION_FILE);
            // Open representation
            editor = openDiagram(REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);
            // Modify representation
            createNode(200, 150);
            // Close representation
            if (TestsUtil.is202006Platform()) {
                bot.activeEditor().bot().menu("Close Editor").click();
            } else {
                bot.activeEditor().bot().menu("Close").click();
            }
            // Check pop up close.
            checkSaveDialog();
        } finally {
            // Restore original preferences
            PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, oldValuePrefPromptWhenSaveableStillOpen);
        }

    }

    /**
     * When closing a dirty representation, a dialog appears to choice what to do. This test checks that when the user
     * clicks on the escape key, the dialog is closed and nothing happens (session still dirty and editor still opened).
     */
    public void testEscapeKeyEffectOnSaveDialog() {
        // Restore the default preference values of Sirius (not a customer
        // specific one)
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);
        oldValuePrefPromptWhenSaveableStillOpen = PlatformUI.getPreferenceStore().getBoolean(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN);
        try {
            PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, true);
            // Open session
            localSession = openSessionFromExistingAird(SESSION_FILE);
            // Open representation
            editor = openDiagram(REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);
            // Modify representation
            createNode(200, 150);
            // Close representation
            if (TestsUtil.is202006Platform()) {
                bot.activeEditor().bot().menu("Close Editor").click();
            } else {
                bot.activeEditor().bot().menu("Close").click();
            }
            // Press escape on save dialog.
            escapeOnSaveDialog();
            // Check that the session is not saved and the editor is still
            // opened
            assertTrue("The session must not be saved", localSession.isDirty());
            assertEquals("The editor must be still opened.", 1, editor.getReference().getPage().getEditorReferences().length);
        } finally {
            // Restore original preferences
            PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, oldValuePrefPromptWhenSaveableStillOpen);
        }

    }

    /**
     * When closing a dirty representation, a dialog appears to choice what to do. This test checks that when the user
     * closes this dialog, nothing happens (session still dirty and editor still opened).
     */
    public void testCloseEffectOnSaveDialog() {
        // Restore the default preference values of Sirius (not a customer
        // specific one)
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);
        oldValuePrefPromptWhenSaveableStillOpen = PlatformUI.getPreferenceStore().getBoolean(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN);
        try {
            PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, true);
            // Open session
            localSession = openSessionFromExistingAird(SESSION_FILE);
            // Open representation
            editor = openDiagram(REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);
            // Modify representation
            createNode(200, 150);
            // Close representation
            if (TestsUtil.is202006Platform()) {
                bot.activeEditor().bot().menu("Close Editor").click();
            } else {
                bot.activeEditor().bot().menu("Close").click();
            }
            // Close the save dialog.
            closeSaveDialog();
            // Check that the session is not saved and the editor is still
            // opened
            assertTrue("The session must not be saved", localSession.isDirty());
            assertEquals("The editor must be still opened.", 1, editor.getReference().getPage().getEditorReferences().length);
        } finally {
            // Restore original preferences
            PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, oldValuePrefPromptWhenSaveableStillOpen);
        }

    }

    /**
     * When closing many editors, a dialog appears to choice what to do. This test checks that when the user closes this
     * dialog by pressing "No" button, nothing happens (sessions must not be saved and editor are closed).
     */
    public void testCloseAllEditorsWithoutSaving() {
        // Open session
        localSession = openSessionFromExistingAird(SESSION_FILE);
        // Open representation
        editor = openDiagram(REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);
        // Modify representation
        createNode(200, 150);
        // Open second representation
        editor = openDiagram(REPRESENTATION_NAME, SECOND_REPRESENTATION_INSTANCE_NAME);
        // Modify representation
        createNode(200, 150);
        // Close all representations
        if (TestsUtil.is202006Platform()) {
            bot.activeEditor().bot().menu("Close All Editors").click();
        } else {
            bot.activeEditor().bot().menu("Close All").click();
        }
        SWTBot saveBot = SWTBotSiriusHelper.getShellBot("Save");
        // Close the first save dialog.
        saveBot.button("No").click();
        // Close the second save dialog.
        saveBot = SWTBotSiriusHelper.getShellBot("Save");
        saveBot.button("No").click();
        // Check that the sessions are not saved and editors are closed
        assertEquals("The editors must be closed.", 0, editor.getReference().getPage().getEditorReferences().length);
        editor = openDiagram(REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);
        assertEquals("The session must not be saved", 1, editor.getDiagramEditPart().getChildren().size());
        editor = openDiagram(REPRESENTATION_NAME, SECOND_REPRESENTATION_INSTANCE_NAME);
        assertEquals("The session must not be saved", 1, editor.getDiagramEditPart().getChildren().size());
    }

    /**
     * Test when many editors opened that a pop up with many check appears. Test with _Pref_ReloadOnLastEditorClose and
     * _Pref_SaveWhenNoEditor to false value. The preference 'Prompt when saveable still open' is set to true.
     */
    public void testCloseAllEditorWithDesignPrefTrueAndElipsePrefFalse() {
        closeAllEditorOpened(true, false);
        // Check pop up appears
        checkSaveResourcesDialog();
        // Check that the session is save
        assertEquals("The session must be save", false, localSession.isDirty());
    }

    /**
     * Test when many editors opened that a pop up with many check appears. _Pref_ReloadOnLastEditorClose and
     * _Pref_SaveWhenNoEditor are set to true value and preference 'Prompt when saveable still open' is set to true.
     */
    public void testCloseAllEditorWithDesignPrefTrueAndElipsePrefTrue() {
        closeAllEditorOpened(true, true);
        // Check pop up appears
        checkSaveResourcesDialogWithPrefTrue();
        // Check that the session is save
        assertEquals("The session must be save", false, localSession.isDirty());
    }

    /**
     * Test when many editors opened that a pop up with many check appears. It's necessary to this test to passed
     * _Pref_ReloadOnLastEditorClose and _Pref_SaveWhenNoEditor to false value.Preference 'Prompt when saveable stil
     * open' is set to false.
     */
    public void testCloseAllEditorWithDesignPrefFalseAndElipsePrefFalse() {
        closeAllEditorOpened(false, false);
        // Check pop up appears
        checkSaveResourcesDialog();
        // Check that the session is save
        assertEquals("The session must be save", false, localSession.isDirty());
    }

    /**
     * Test when many editors opened that a pop up with many check appears. _Pref_ReloadOnLastEditorClose and
     * _Pref_SaveWhenNoEditor are set to true value. Preference 'Prompt when saveable stil open' is set to false.
     */
    public void testCloseAllEditorWithDesignPrefFalseAndElipsePrefTrue() {
        closeAllEditorOpened(false, true);
        // Check pop up appears
        checkSaveResourcesDialogWithPrefTrue();
        // Check that the session is save
        assertEquals("The session must be save", false, localSession.isDirty());
    }

    private void closeAllEditorOpened(boolean eclipsePref, boolean designerPref) {
        // Set preferences for test
        changePlatformUIPreference(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, eclipsePref);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), designerPref);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), designerPref);
        // Check that the preferences are correctly set
        assertEquals("The preferences 'prompt when saveable still open' must be value to " + String.valueOf(eclipsePref), eclipsePref,
                PlatformUI.getPreferenceStore().getBoolean(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN));
        assertEquals("The preferences 'Reload On Last Editor Close' must be value to " + String.valueOf(designerPref), designerPref,
                SiriusEditPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name()));
        assertEquals("The preferences 'Save When No Editor' must be value to  " + String.valueOf(designerPref), designerPref,
                SiriusEditPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name()));
        // Open session
        localSession = openSessionFromExistingAird(SESSION_FILE);
        // Create new representation
        createRepresentation(REPRESENTATION_NAME, "p1");
        // Open text file
        SWTBotCommonHelper.openEditor(TEMP_PROJECT_NAME, TEXT_FILE);
        // Modify text file
        SWTBotEditor textEditor = bot.activeEditor();
        textEditor.toTextEditor().setText("Modification Text file");
        // Open the second representations
        editor = openDiagram(REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);
        // Modify the representation
        createNode(200, 150);
        // Close all representations
        if (TestsUtil.is202006Platform()) {
            bot.activeEditor().bot().menu("Close All Editors").click();
        } else {
            bot.activeEditor().bot().menu("Close All").click();
        }
    }

    private UILocalSession openSessionFromExistingAird(final String airdName) {
        final UIResource sessionAirdResource = new UIResource(designerProject, "/", airdName);
        return designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    private SWTBotSiriusDiagramEditor openDiagram(String representationName, String representationInstanceName) {
        return (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationName, representationInstanceName, DDiagram.class);
    }

    private void checkSaveDialog() {
        SWTBot saveBot = SWTBotSiriusHelper.getShellBot("Save");
        // Check the message (must start with the session name, instead of
        // editor name).
        final SWTBotButton buttonNo = saveBot.button("No");
        final SWTBotLabel labelOfPopup = TestsUtil.is202303Platform() ? saveBot.label(0) : saveBot.label(1);
        final String msgOfPopup = labelOfPopup.getText();
        assertTrue("The message (\"" + msgOfPopup + "\") should be contains:  (" + TEST_UI_CALLBACK + ").", msgOfPopup.contains(TEST_UI_CALLBACK));
        buttonNo.click();
    }

    private void escapeOnSaveDialog() throws WidgetNotFoundException {
        bot.waitUntil(Conditions.shellIsActive("Save"));
        SWTBotShell saveShell = bot.shell("Save");
        try {
            saveShell.activate();
            saveShell.setFocus();
            saveShell.pressShortcut(KeyStroke.getInstance(IKeyLookup.ESC_NAME));
        } catch (ParseException e) {
            fail("Problem to press escape key");
        }
        bot.waitUntil(Conditions.shellCloses(saveShell));
    }

    private void closeSaveDialog() throws WidgetNotFoundException {
        bot.waitUntil(Conditions.shellIsActive("Save"));
        SWTBotShell saveShell = bot.shell("Save");
        saveShell.close();
        bot.waitUntil(Conditions.shellCloses(saveShell));
    }

    private void checkSaveResourcesDialog() {
        SWTBot saveBot = SWTBotSiriusHelper.getShellBot("Save Resources");
        final SWTBotButton buttonOk;
        if (TestsUtil.is202303Platform()) {
            // on platform 2023-03, the text of button change to "Save X of Y"
            buttonOk = saveBot.button("Save 2 of 2");
        } else {
            buttonOk = saveBot.button(TestsUtil.isPhotonPlatformOrLater() ? "Save Selected" : "OK");
        }
        assertTrue("The popup should be contains 2 elements", saveBot.table().rowCount() == 2);
        buttonOk.click();
        SessionSavedCondition sessionCondition = new SessionSavedCondition(localSession.getOpenedSession());
        bot.waitUntil(sessionCondition);
    }

    private void checkSaveResourcesDialogWithPrefTrue() {
        SWTBot saveBot = SWTBotSiriusHelper.getShellBot("Save");
        SWTBotButton buttonYes = saveBot.button("Yes");
        buttonYes.click();
        saveBot = SWTBotSiriusHelper.getShellBot("Save Resource");
        buttonYes = saveBot.button(TestsUtil.isOxygenPlatform() ? "Save" : "Yes");
        buttonYes.click();
        SessionSavedCondition sessionCondition = new SessionSavedCondition(localSession.getOpenedSession());
        bot.waitUntil(sessionCondition);
    }

    /**
     * Create a new node using the defined Node Creation tool, at the given position.
     *
     * @param xOfNodeToCreate
     *            position of the note to create
     * @param yOfNodeToCreate
     *            position of the note to create
     */
    private void createNode(int xOfNodeToCreate, int yOfNodeToCreate) {
        // Select the Class tool and
        editor.activateTool(NODE_CREATION_TOOL_NAME);
        // Click in the editor (the coordinates use to
        // click is with the zoom level) -> Node is created
        editor.click(xOfNodeToCreate, yOfNodeToCreate);
    }

    /**
     * Create a representation from the session wizard.
     *
     * @param representation
     * @param semanticElement
     * @return the opened representation
     */
    private SWTBotEditor createRepresentation(String representation, String semanticElement) {
        SWTBot secondWizardBot = secondWizard(representation);

        SWTBotUtils.waitAllUiEvents();
        secondWizardBot.tree().expandNode("platform:/resource/" + TEMP_PROJECT_NAME + "/" + MODEL).expandNode(semanticElement).select();
        SWTBotUtils.waitAllUiEvents();
        secondWizardBot.button(FINISH).click();

        SWTBotUtils.waitAllUiEvents();

        // choose the representation name
        SWTBotShell shell = bot.shell("New Entities");
        shell.activate();
        shell.bot().button(OK).click();
        SWTBotUtils.waitAllUiEvents();
        SWTBotUtils.waitProgressMonitorClose("Representation creation");

        return bot.activeEditor();
    }

    /**
     * Open the second wizard.
     *
     * @param representation
     * @return
     */
    private SWTBot secondWizard(String representation) {
        // create representation
        createOnContextMenu();

        // select representation to create
        SWTBotShell shell = bot.shell("Create Representation Wizard");
        shell.activate();
        SWTBot wizardBot = shell.bot();
        wizardBot.tree().expandNode(VIEWPOINT_NAME).expandNode(representation).select();
        wizardBot.button(NEXT).click();

        // select semantic element of the new representation
        shell = bot.shell("Create Representation");
        shell.activate();
        return shell.bot();
    }

    /**
     * Use context menu and open create representation wizard.
     */
    private void createOnContextMenu() {
        SWTBotTreeItem sessionTreeItem = localSession.getLocalSessionBrowser().getTreeItem();

        try {
            sessionTreeItem.contextMenu("Create Representation").click();
        } catch (WidgetNotFoundException e) {
            SWTBotUtils.clickContextMenu(sessionTreeItem, "Create Representation");
        }
    }

    private class SessionCallBack extends AbstractSWTCallback {

        /**
         * {@inheritDoc}
         */
        @Override
        protected String getVariableNameForRepresentation() {
            return "";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSessionNameToDisplayWhileSaving(Session session) {
            String name = "";
            String projectName = session.getSessionResource().getURI().segment(1);
            name = "Models and Representations";
            name += " " + TEST_UI_CALLBACK + " " + projectName;
            return name;
        }

    }
}
