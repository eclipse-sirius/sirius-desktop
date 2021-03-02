/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.propertypage;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemSelected;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.internal.preference.SiriusPreferencesPropertyPage;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * Tests to check the Sirius Preferences property page.
 * 
 * @author lfasani
 */
public class SiriusPreferencesPropertyPageTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/propertypage/";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private Session session;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    /**
     * Tests the content of {@link SiriusPreferencesPropertyPage}
     */
    public void testSiriusPreferencesPage() {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        // Check the initial Settings value via the Session
        checkSessionSettings(false, false, true);

        // The page is called from its contribution instead of the contextual menu via
        // SWTBot because most of the time SWTBot wrongly focuses on the properties view instead.
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(designerProject.getName());
        IFile file = project.getFile(SESSION_FILE);
        IPreferenceNode[] propertiesContributors = PreferencesUtil.propertiesContributorsFor(file);
        IPreferenceNode siriusSessionDetailsPropertyPage = null;
        for (IPreferenceNode iPreferenceNode : propertiesContributors) {
            if (iPreferenceNode.getId().equals("org.eclipse.sirius.ui.SiriusPreferencesPropertyPage")) {
                siriusSessionDetailsPropertyPage = iPreferenceNode;
            }
        }
        // Check that the page is available
        assertNotNull("The Sirius Session Details property page has not been found", siriusSessionDetailsPropertyPage);
        PreferenceManager preferenceManager = new PreferenceManager();
        preferenceManager.addToRoot(siriusSessionDetailsPropertyPage);

        // --------------------
        // Property page test
        // Open the page in a Properties Dialog
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                final Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                PreferenceDialog propertiesDialog = new PreferenceDialog(activeShell, preferenceManager);
                propertiesDialog.create();
                propertiesDialog.open();
            }
        });
        bot.waitUntil(Conditions.shellIsActive("Preferences"));

        // Check the state of the Settings
        SWTBot shellBot = bot.shell("Preferences").bot();
        SWTBotTreeItem treeItem = shellBot.tree().getTreeItem("Sirius Preferences");
        treeItem.select();
        shellBot.waitUntil(new TreeItemSelected(treeItem));

        SWTBotCheckBox enableSpecificSettingsCheckBox = checkButton(shellBot, Messages.SiriusPreferencesPropertyPage_enableProjectSpecificSettings, true, false);
        SWTBotCheckBox refreshAtOpeningCheckBox = checkButton(shellBot, org.eclipse.sirius.viewpoint.provider.Messages.SiriusPreferencePage_refreshOnRepresentationOpening, false, false);
        SWTBotCheckBox autoRefreshCheckBox = checkButton(shellBot, org.eclipse.sirius.viewpoint.provider.Messages.SiriusPreferencePage_autoRefresh, false, true);

        // Check the Settings change
        enableSpecificSettingsCheckBox.click();
        refreshAtOpeningCheckBox.click();
        checkButton(shellBot, org.eclipse.sirius.viewpoint.provider.Messages.SiriusPreferencePage_refreshOnRepresentationOpening, true, true);
        autoRefreshCheckBox.click();
        checkButton(shellBot, org.eclipse.sirius.viewpoint.provider.Messages.SiriusPreferencePage_autoRefresh, true, false);

        shellBot.button("Apply and Close").click();

        // Check the Settings value via the Session
        checkSessionSettings(true, true, false);
    }

    /**
     * Check the value of the Preferences from SiriusPreferences API;
     */
    private void checkSessionSettings(boolean hasSpecificSetting, boolean isRefreshOnRepresentationOpening, boolean isAutoRefresh) {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        session = localSession.getOpenedSession();

        assertEquals(hasSpecificSetting, session.getSiriusPreferences().hasSpecificSettingAutoRefresh());
        assertEquals("Bad Session preference value for " + org.eclipse.sirius.viewpoint.provider.Messages.SiriusPreferencePage_refreshOnRepresentationOpening, isRefreshOnRepresentationOpening,
                session.getSiriusPreferences().isRefreshOnRepresentationOpening());
        assertEquals(hasSpecificSetting, session.getSiriusPreferences().hasSpecificSettingRefreshOnRepresentationOpening());
        assertEquals("Bad Session preference value for " + org.eclipse.sirius.viewpoint.provider.Messages.SiriusPreferencePage_autoRefresh, isAutoRefresh,
                session.getSiriusPreferences().isAutoRefresh());

        session.close(new NullProgressMonitor());
    }

    private SWTBotCheckBox checkButton(SWTBot shellBot, String buttonText, boolean enabled, boolean checked) {
        SWTBotCheckBox checkBox = shellBot.checkBox(buttonText);
        assertEquals("Bad enabled state for check button " + buttonText, enabled, checkBox.isEnabled());
        assertEquals("Bad checked state for check button " + buttonText, checked, checkBox.isChecked());
        return checkBox;
    }
}
