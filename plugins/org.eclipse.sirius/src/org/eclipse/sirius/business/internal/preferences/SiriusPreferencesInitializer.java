/*******************************************************************************
 * Copyright (c) 2020, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Initializes the Sirius core (non UI) preferences.
 * 
 * @author lfasani
 */
public class SiriusPreferencesInitializer extends AbstractPreferenceInitializer {

    /**
     * Default constructor.
     */
    public SiriusPreferencesInitializer() {
    }

    @Override
    public void initializeDefaultPreferences() {
        final IEclipsePreferences defaultCorePreferences = DefaultScope.INSTANCE.getNode(SiriusPlugin.ID);
        final boolean autoRefreshDefValue = getValue("_Pref_AutoRefresh"); //$NON-NLS-1$
        defaultCorePreferences.putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), autoRefreshDefValue);

        final boolean emptyAirdFragOnControl = getValue("_Pref_EmptyAirdFragmentOnControl"); //$NON-NLS-1$
        defaultCorePreferences.putBoolean(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), emptyAirdFragOnControl);

    }

    private boolean getValue(final String key) {
        final String value = SiriusPlugin.INSTANCE.getString(key);
        return Boolean.valueOf(value);
    }
}
