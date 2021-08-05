/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Tests for the preferences synchronization. Some preferences are declared in {@link SiriusPreferencesKeys} but
 * initialized with an ui preference page in both {@link SiriusPlugin}'s preferences and {@link SiriusEditPlugin}'s
 * preference store.
 * 
 * @author mporhel
 */
public class PreferencesTests extends SiriusTestCase {

    // allows to do all the calls to preferences services with same default
    // value if a preference is not initialized/
    private final boolean commonDefValue = false;

    /**
     * Tests SiriusPreferencesKeys default scope preferences.
     */
    public void testDefaultPreferenceValuesSynchronization() {
        checkPreferenceValues(true, false);
    }

    /**
     * Tests SiriusPreferencesKeys instance scope preferences.
     */
    public void testPreferenceValuesSynchronization() {
        checkPreferenceValues(false, false);

        String autoRefreshPrefName = SiriusPreferencesKeys.PREF_AUTO_REFRESH.name();
        boolean oldAutoRefreshValue = getPlatformPreferenceServiceValue(SiriusPlugin.ID, autoRefreshPrefName, commonDefValue);
        changeSiriusPreference(autoRefreshPrefName, !oldAutoRefreshValue);
        assertEquals("Test cannot change the preference value.", !oldAutoRefreshValue, getPlatformPreferenceServiceValue(SiriusPlugin.ID, autoRefreshPrefName, commonDefValue));

        String emptyAirdFragOnControlPrefName = SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name();
        boolean oldemptyAirdFragOnControlValue = getPlatformPreferenceServiceValue(SiriusPlugin.ID, emptyAirdFragOnControlPrefName, commonDefValue);
        changeSiriusPreference(emptyAirdFragOnControlPrefName, !oldemptyAirdFragOnControlValue);
        assertEquals("Test cannot change the preference value.", !oldemptyAirdFragOnControlValue, getPlatformPreferenceServiceValue(SiriusPlugin.ID, emptyAirdFragOnControlPrefName, commonDefValue));

        checkPreferenceValues(false, true);
    }

    @SuppressWarnings("deprecation")
    private void checkPreferenceValues(boolean testDefaultScope, boolean instanceScopeHasBeenInitialized) {
        final IEclipsePreferences corePreferences;
        final Preferences deprecatedCorePreferences = SiriusPlugin.getDefault().getPluginPreferences();

        if (testDefaultScope) {
            corePreferences = new DefaultScope().getNode(SiriusPlugin.ID);
        } else {
            corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        }

        for (SiriusPreferencesKeys key : SiriusPreferencesKeys.values()) {
            final String prefName = key.name();

            // Check that the preference has been initialized for call to
            // platform preferences service.
            // Call the service with two different value for the def
            // attribute -> used by the platform if no pref value is found.
            assertEquals(getPlatformPreferenceServiceValue(SiriusPlugin.ID, prefName, !commonDefValue), getPlatformPreferenceServiceValue(SiriusPlugin.ID, prefName, commonDefValue));

            // Get initialization status of the current preference.
            // Call the service with two different value for the def
            // attribute -> used by the platform if no pref value is found.
            boolean corePrefInitialized = corePreferences.getBoolean(prefName, !commonDefValue) == corePreferences.getBoolean(prefName, commonDefValue);

            // Then get all preference values
            final boolean deprCoreValue = testDefaultScope ? deprecatedCorePreferences.getDefaultBoolean(prefName) : deprecatedCorePreferences.getBoolean(prefName);
            final boolean coreScopeValue = corePreferences.getBoolean(prefName, commonDefValue);

            if (testDefaultScope) {
                // For default scope, it must be initialized
                String message = "Initialization of SiriusPreferencesKeys." + prefName + " value should be checked for the default scope ";
                assertTrue(message + corePreferences.name(), corePrefInitialized);
            } else {
                final boolean corePrefServiceValue = getPlatformPreferenceServiceValue(SiriusPlugin.ID, prefName, commonDefValue);
                String message = prefName + " should have same value for core preferences access through old and new methods.";
                assertEquals(message, corePrefServiceValue, deprCoreValue);
                if (instanceScopeHasBeenInitialized) {
                    assertEquals(message, corePrefServiceValue, coreScopeValue);
                    message = "Initialization of SiriusPreferencesKeys." + prefName + " value should be checked for the instance scope ";
                    assertTrue(message + corePreferences.name(), corePrefInitialized);
                }
            }
        }
    }

    /**
     * Check that SiriusPreferencesKeys are not managed in SiriusEditPlugin preference store.
     */
    public void testNoCorePreferenceInUIPrefStore() {
        try {
            String message = "No SiriusPreferencesKeys should be in the SiriusEditPlugin preference store";
            assertFalse(message, InstanceScope.INSTANCE.getNode(SiriusEditPlugin.ID).nodeExists(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name()));
            assertFalse(message, InstanceScope.INSTANCE.getNode(SiriusEditPlugin.ID).nodeExists(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name()));
        } catch (BackingStoreException e) {
        }
    }

    private boolean getPlatformPreferenceServiceValue(String pluginId, String name, boolean defValueIdNotInitialized) {
        return Platform.getPreferencesService().getBoolean(pluginId, name, false, null);
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
