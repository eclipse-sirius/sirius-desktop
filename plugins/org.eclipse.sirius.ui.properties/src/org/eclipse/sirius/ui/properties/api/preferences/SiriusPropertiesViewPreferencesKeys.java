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
package org.eclipse.sirius.ui.properties.api.preferences;

/**
 * The properties view preference keys.
 * 
 * @author mbats
 */
public enum SiriusPropertiesViewPreferencesKeys {

    /**
     * Says if the semantic tab should be automatically filtered or not.
     */
    PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB(boolean.class),

    /**
     * Says if the default tab should be automatically filtered or not.
     */
    PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB(boolean.class),

    /**
     * Says which is the max length of a tab name.
     */
    PREF_MAX_LENGTH_TAB_NAME(int.class);

    /** The type of the preference. */
    private final Class<?> type;

    /**
     * Creates a new DesignerPreferencesKeys with the given type.
     * 
     * @param type
     *            the type of the value of the preference.
     */
    private SiriusPropertiesViewPreferencesKeys(final Class<?> type) {
        this.type = type;
    }

    /**
     * Returns the type of the value of the preference of this key.
     * 
     * @return the type of the value of the preference of this key.
     */
    public Class<?> getType() {
        return type;
    }

}
