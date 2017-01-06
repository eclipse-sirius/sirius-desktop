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

/**
 * The properties view preference keys.
 * 
 * @author mbats
 */
public enum SiriusPropertiesCorePreferencesKeys {

    /**
     * Says if the semantic tab should be automatically filtered or not.
     */
    PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB(Boolean.TRUE.toString()),

    /**
     * Says if the default tab should be automatically filtered or not.
     */
    PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB(Boolean.TRUE.toString()),

    /**
     * Says which is the max length of a tab name.
     */
    PREF_MAX_LENGTH_TAB_NAME(Integer.valueOf(20).toString());

    /**
     * The default value.
     */
    private String defaultValue;

    /**
     * The constructor.
     * 
     * @param defaultValue
     *            The default value
     */
    private SiriusPropertiesCorePreferencesKeys(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the default value.
     * 
     * @return The default value
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }

}
