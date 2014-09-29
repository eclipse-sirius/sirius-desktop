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
package org.eclipse.sirius.tests.unit.common;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Tests for the preferences synchronization. Some preferences are declared in
 * {@link SiriusPreferencesKeys} but initialized with an ui preference page in
 * both {@link SiriusPlugin}'s preferences and {@link SiriusEditPlugin}'s
 * preference store.
 * 
 * @author mporhel
 */
public class PreferencesTests extends SiriusTestCase {

    // allows to do all the calls to preferences services with same default
    // value if a preference is not initialized/
    private final boolean commonDefValue = false;

    /**
     * Tests that a null EObject produces a non-null but empty iterable.
     */
    public void testDefaultPreferenceValuesSynchronization() {
        checkPreferenceValues(true);
    }

    /**
     * Tests that a null EObject produces a non-null but empty iterable.
     */
    public void testPreferenceValuesSynchronization() {
        checkPreferenceValues(false);
    }

    /**
     * Tests that a null EObject produces a non-null but empty iterable.
     */
    public void testPreferenceValuesSynchronizationOnChangefromSirius() {
        checkPreferenceValues(false);

        String autoRefreshPrefName = SiriusPreferencesKeys.PREF_AUTO_REFRESH.name();
        boolean oldAutoRefreshValue = getPlatformPreferenceServiceValue(SiriusPlugin.ID, autoRefreshPrefName, commonDefValue);
        changeSiriusPreference(autoRefreshPrefName, !oldAutoRefreshValue);
        assertEquals("Test cannot change the preference value.", !oldAutoRefreshValue, getPlatformPreferenceServiceValue(SiriusPlugin.ID, autoRefreshPrefName, commonDefValue));

        checkPreferenceValues(false);
    }

    /**
     * Tests that a null EObject produces a non-null but empty iterable.
     */
    public void testPreferenceValuesSynchronizationOnChangeFromSiriusUI() {
        checkPreferenceValues(false);

        String autoRefreshPrefName = SiriusPreferencesKeys.PREF_AUTO_REFRESH.name();
        boolean oldAutoRefreshValue = getPlatformPreferenceServiceValue(SiriusEditPlugin.ID, autoRefreshPrefName, false);
        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();

        /*
         * CHANGE AUTO-REFRESH FROM UI Do not call the api methods here : it
         * will failsWe check here the synchro as called when the user change a
         * pref from the property view.
         */
        viewpointUIPrefs.setValue(autoRefreshPrefName, !oldAutoRefreshValue);
        assertEquals("Test cannot change the preference value.", !oldAutoRefreshValue, getPlatformPreferenceServiceValue(SiriusEditPlugin.ID, autoRefreshPrefName, commonDefValue));
        assertEquals("Test cannot change the preference value : simple synchronization seems to fail.", !oldAutoRefreshValue,
                getPlatformPreferenceServiceValue(SiriusPlugin.ID, autoRefreshPrefName, commonDefValue));

        checkPreferenceValues(false);

        /*
         * RESET AUTO-REFRESH FROM UI Do not call the api methods here : it will
         * failsWe check here the synchro as called when the user change a pref
         * from the property view.
         */
        viewpointUIPrefs.setValue(autoRefreshPrefName, oldAutoRefreshValue);
        assertEquals("Test cannot reset the preference value.", oldAutoRefreshValue, getPlatformPreferenceServiceValue(SiriusEditPlugin.ID, autoRefreshPrefName, commonDefValue));
        assertEquals("Test cannot rest the preference value : simple synchronization seems to fail.", oldAutoRefreshValue,
                getPlatformPreferenceServiceValue(SiriusPlugin.ID, autoRefreshPrefName, commonDefValue));
    }

    @SuppressWarnings("deprecation")
    private void checkPreferenceValues(boolean defaultValues) {
        final IPreferenceStore uiPreferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        final IEclipsePreferences uiPreferences;
        final IEclipsePreferences corePreferences;
        final Preferences deprecatedCorePreferences = SiriusPlugin.getDefault().getPluginPreferences();

        if (defaultValues) {
            uiPreferences = new DefaultScope().getNode(SiriusEditPlugin.ID);
            corePreferences = new DefaultScope().getNode(SiriusPlugin.ID);
        } else {
            uiPreferences = InstanceScope.INSTANCE.getNode(SiriusEditPlugin.ID);
            corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        }

        for (SiriusPreferencesKeys key : SiriusPreferencesKeys.values()) {
            final String prefName = key.name();

            // Check that the preference has been initialized for call to
            // platform preferences service.
            // Call the service with two different value for the def
            // attribute -> used by the platform if no pref value is found.
            assertEquals(getPlatformPreferenceServiceValue(SiriusPlugin.ID, prefName, !commonDefValue), getPlatformPreferenceServiceValue(SiriusPlugin.ID, prefName, commonDefValue));
            assertEquals(getPlatformPreferenceServiceValue(SiriusEditPlugin.ID, prefName, !commonDefValue), getPlatformPreferenceServiceValue(SiriusEditPlugin.ID, prefName, commonDefValue));

            // Get initialization status of the current preference.
            // Call the service with two different value for the def
            // attribute -> used by the platform if no pref value is found.
            boolean uiPrefInitialized = uiPreferences.getBoolean(prefName, !commonDefValue) == uiPreferences.getBoolean(prefName, commonDefValue);
            boolean corePrefInitialized = corePreferences.getBoolean(prefName, !commonDefValue) == corePreferences.getBoolean(prefName, commonDefValue);

            // Then get all preference values
            final boolean uiStoreValue = defaultValues ? uiPreferenceStore.getDefaultBoolean(prefName) : uiPreferenceStore.getBoolean(prefName);
            final boolean deprCoreValue = defaultValues ? deprecatedCorePreferences.getDefaultBoolean(prefName) : deprecatedCorePreferences.getBoolean(prefName);
            final boolean uiScopeValue = uiPreferences.getBoolean(prefName, commonDefValue);
            final boolean coreScopeValue = corePreferences.getBoolean(prefName, commonDefValue);

            if (defaultValues) {
                // For default scope, it must be initialized
                String message = "Initialization of DesignerPreferenceKeys." + prefName + " default values should be checked for the scope named ";
                assertEquals(message + uiPreferences.name(), true, uiPrefInitialized);
                assertEquals(message + corePreferences.name(), true, corePrefInitialized);

                // Check synchronized value for all preference store types.
                message = prefName + "should have same value for all default preferences access through old, new, core and ui methods.";
                assertEquals(message, uiStoreValue, deprCoreValue);
                assertEquals(message, uiStoreValue, uiScopeValue);
                assertEquals(message, uiStoreValue, coreScopeValue);
            } else {
                final boolean corePrefServiceValue = getPlatformPreferenceServiceValue(SiriusPlugin.ID, prefName, commonDefValue);
                final boolean uiPrefServiceValue = getPlatformPreferenceServiceValue(SiriusEditPlugin.ID, prefName, commonDefValue);

                // Check synchronized value for all preference store types.
                assertTrue(corePrefInitialized);
                String message = prefName + "should have same value for core preferences access through old and new methods.";
                assertEquals(message, corePrefServiceValue, coreScopeValue);
                assertEquals(message, corePrefServiceValue, deprCoreValue);

                if (uiPrefInitialized) {
                    message = prefName + "should have same value for all ui preferences access.";
                    assertEquals(message, uiPrefServiceValue, uiScopeValue);
                    assertEquals(message, uiPrefServiceValue, uiStoreValue);
                    message = prefName + "should have same value for core preferences access and ui preferences access.";
                    assertEquals(message, uiPrefServiceValue, corePrefServiceValue);
                }
            }
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
