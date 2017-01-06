/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api.preferences;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;
import org.eclipse.sirius.properties.core.internal.preferences.SiriusPropertiesCorePreferencesKeys;

/**
 * Utility class used to retrieve and edit the preferences of the Sirius
 * Properties Core bundle.
 * 
 * @author sbegaudeau
 */
public final class SiriusPropertiesCorePreferences {

    /**
     * The sole instance used to configure the preferences.
     */
    public static final SiriusPropertiesCorePreferences INSTANCE = new SiriusPropertiesCorePreferences();

    /**
     * The constructor.
     */
    private SiriusPropertiesCorePreferences() {
        // prevent instantiation
    }

    /**
     * Returns the max length of the name of a tab.
     * 
     * @return The max length of the name of a tab
     */
    public int getMaxLengthTabName() {
        IEclipsePreferences[] lookupOrder = this.getLookupOrder();
        String maxLengthTabName = Platform.getPreferencesService().get(SiriusPropertiesCorePreferencesKeys.PREF_MAX_LENGTH_TAB_NAME.name(),
                SiriusPropertiesCorePreferencesKeys.PREF_MAX_LENGTH_TAB_NAME.getDefaultValue(), lookupOrder);
        return Integer.parseInt(maxLengthTabName);
    }

    /**
     * Returns the default value for the max length of the name of a tab.
     * 
     * @return The default value for the max length of the name of a tab
     */
    public int getMaxLengthTabNameDefaultValue() {
        return Integer.parseInt(SiriusPropertiesCorePreferencesKeys.PREF_MAX_LENGTH_TAB_NAME.getDefaultValue());
    }

    /**
     * Sets the new value of the max length of the name of a tab.
     * 
     * @param maxLength
     *            The max length authorized
     */
    public void setMaxLengthTabName(int maxLength) {
        IEclipsePreferences instanceScope = InstanceScope.INSTANCE.getNode(SiriusPropertiesCorePlugin.PLUGIN_ID);
        instanceScope.put(SiriusPropertiesCorePreferencesKeys.PREF_MAX_LENGTH_TAB_NAME.name(), Integer.toString(maxLength));
    }

    /**
     * Indicates if the default tab should be filtered.
     * 
     * @return <code>true</code> if the tab should be filtered,
     *         <code>false</code> otherwise
     */
    public boolean isDefaultTabFiltered() {
        IEclipsePreferences[] lookupOrder = this.getLookupOrder();
        String isDefaultTabFiltered = Platform.getPreferencesService().get(SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB.name(),
                SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB.getDefaultValue(), lookupOrder);
        return Boolean.parseBoolean(isDefaultTabFiltered);
    }

    /**
     * Returns the default value indicating if the default tab should be
     * filtered.
     * 
     * @return <code>true</code> if it should be filtered by default,
     *         <code>false</code> otherwise
     */
    public boolean isDefaultTabFilteredDefaultValue() {
        return Boolean.parseBoolean(SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB.getDefaultValue());
    }

    /**
     * Sets if the default tab should be filtered.
     * 
     * @param shouldFilter
     *            <code>true</code> if the default tab should be filtered,
     *            <code>false</code> otherwise
     */
    public void setFilterDefaultTab(boolean shouldFilter) {
        IEclipsePreferences instanceScope = InstanceScope.INSTANCE.getNode(SiriusPropertiesCorePlugin.PLUGIN_ID);
        instanceScope.put(SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB.name(), Boolean.toString(shouldFilter));
    }

    /**
     * Indicates if the semantic tab should be filtered.
     * 
     * @return <code>true</code> if the tab should be filtered,
     *         <code>false</code> otherwise
     */
    public boolean isSemanticTabFiltered() {
        IEclipsePreferences[] lookupOrder = this.getLookupOrder();
        String isSemanticTabFiltered = Platform.getPreferencesService().get(SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB.name(),
                SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB.getDefaultValue(), lookupOrder);
        return Boolean.parseBoolean(isSemanticTabFiltered);
    }

    /**
     * Returns the default value indicating if the semantic tab should be
     * filtered.
     * 
     * @return <code>true</code> if it should be filtered by default,
     *         <code>false</code> otherwise
     */
    public boolean isSemanticTabFilteredDefaultValue() {
        return Boolean.parseBoolean(SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB.getDefaultValue());
    }

    /**
     * Sets if the semantic tab should be filtered.
     * 
     * @param shouldFilter
     *            <code>true</code> if the semantic tab should be filtered,
     *            <code>false</code> otherwise
     */
    public void setFilterSemanticTab(boolean shouldFilter) {
        IEclipsePreferences instanceScope = InstanceScope.INSTANCE.getNode(SiriusPropertiesCorePlugin.PLUGIN_ID);
        instanceScope.put(SiriusPropertiesCorePreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB.name(), Boolean.toString(shouldFilter));
    }

    /**
     * Returns the eclipse preferences scope to use for the lookup.
     * 
     * @return The Eclipse preferences scope to use for the lookup
     */
    private IEclipsePreferences[] getLookupOrder() {
        IEclipsePreferences defaultScope = DefaultScope.INSTANCE.getNode(SiriusPropertiesCorePlugin.PLUGIN_ID);
        IEclipsePreferences instanceScope = InstanceScope.INSTANCE.getNode(SiriusPropertiesCorePlugin.PLUGIN_ID);
        return new IEclipsePreferences[] { instanceScope, defaultScope, };
    }
}
