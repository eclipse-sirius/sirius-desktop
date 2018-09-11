/*******************************************************************************
 * Copyright (c) 2009, 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Initializes the preferences.
 * 
 * @author mchauvin
 */
public class GenericPreferencesInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore uiPreferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        final IEclipsePreferences defaultCorePreferences = DefaultScope.INSTANCE.getNode(SiriusPlugin.ID);
        // Initialize the session editor plugin if it is present in runtime to be able to launch session editor when
        // expanding a modeling project.
        // TODO replace it by usage of an extension point or put the session editor in org.eclipse.sirius.ui plugin to
        // avoid this trick.
        DefaultScope.INSTANCE.getNode("org.eclipse.sirius.ui.editor"); //$NON-NLS-1$

        uiPreferenceStore.setDefault(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name(), 10);
        uiPreferenceStore.setDefault(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), getValue("_Pref_RefreshOnRepresentationOpening")); //$NON-NLS-1$
        uiPreferenceStore.setDefault(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), getValue("_Pref_ReloadOnLastEditorClose")); //$NON-NLS-1$
        uiPreferenceStore.setDefault(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), getValue("_Pref_SaveWhenNoEditor")); //$NON-NLS-1$
        uiPreferenceStore.setDefault(SiriusUIPreferencesKeys.PREF_REACT_TO_PERMISSION_ISSUES_BY_GRAPHICAL_DISPLAY.name(), getValue("_Pref_ReactToPermissionIssuesByDisplayingPopup")); //$NON-NLS-1$
        uiPreferenceStore.setDefault(SiriusUIPreferencesKeys.PREF_DISPLAY_PERMISSION_ISSUES_THROUGH_DIALOG.name(), getValue("_Pref_DisplayPermissionIssuesThroughDialog")); //$NON-NLS-1$
        uiPreferenceStore.setDefault(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name(), getValue("_Pref_DisplayUserVsmColorInPalette")); //$NON-NLS-1$

        final boolean autoRefreshDefValue = getValue("_Pref_AutoRefresh"); //$NON-NLS-1$
        defaultCorePreferences.putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), autoRefreshDefValue);
        uiPreferenceStore.setDefault(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), autoRefreshDefValue);

        final boolean emptyAirdFragOnControl = getValue("_Pref_EmptyAirdFragmentOnControl"); //$NON-NLS-1$
        defaultCorePreferences.putBoolean(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), emptyAirdFragOnControl);
        uiPreferenceStore.setDefault(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), emptyAirdFragOnControl);

    }

    private boolean getValue(final String key) {
        final String value = SiriusEditPlugin.INSTANCE.getString(key);
        return Boolean.valueOf(value);
    }
}
