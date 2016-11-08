/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.ui.properties.api.preferences.SiriusPropertiesViewPreferencesKeys;
import org.eclipse.sirius.ui.properties.internal.SiriusUIPropertiesPlugin;

/**
 * The properties preference initializer.
 * 
 * @author mbats
 */
public class SiriusPropertiesViewPreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore preferenceStore = SiriusUIPropertiesPlugin.getPlugin().getPreferenceStore();
        // By default the Semantic and Default tabs are visible
        preferenceStore.setDefault(SiriusPropertiesViewPreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB.name(), true);
        preferenceStore.setDefault(SiriusPropertiesViewPreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB.name(), true);
        // By default the max tab name length is 20
        preferenceStore.setDefault(SiriusPropertiesViewPreferencesKeys.PREF_MAX_LENGTH_TAB_NAME.name(), 20);
    }
}
