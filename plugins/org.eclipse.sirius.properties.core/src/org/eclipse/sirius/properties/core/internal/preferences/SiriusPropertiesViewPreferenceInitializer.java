/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

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
        IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode(SiriusPropertiesCorePlugin.PLUGIN_ID);
        preferences.put(SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB.name(),
                SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB.getDefaultValue());
        preferences.put(SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB.name(),
                SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB.getDefaultValue());
        preferences.put(SiriusPropertiesCorePreferencesKeys.PREF_MAX_LENGTH_TAB_NAME.name(), SiriusPropertiesCorePreferencesKeys.PREF_MAX_LENGTH_TAB_NAME.getDefaultValue());
    }
}
