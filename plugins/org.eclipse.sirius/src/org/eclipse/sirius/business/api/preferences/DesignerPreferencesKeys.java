/*******************************************************************************
 * Copyright (c) 2008, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.preferences;

/**
 * Provides constants for preferences.
 * 
 * @author ymortier
 */
public enum DesignerPreferencesKeys {

    /**
     * Says if the refresh should automatically be done or not.
     */
    PREF_AUTO_REFRESH(boolean.class),

    /**
     * Says if diagram elements should be marked as pinned automatically when
     * created using a creation tool, when the user specifies explicitly the
     * location of the element.
     */
    PREF_AUTO_PIN_ON_CREATE(boolean.class),

    /**
     * Says if an empty aird fragment should be created on control even if there
     * is no selected representation.
     */
    PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL(boolean.class);

    /** The type of the preference. */
    private Class<?> type;

    /**
     * Creates a new DesignerPreferencesKeys with the given type.
     * 
     * @param type
     *            the type of the value of the preference.
     */
    private DesignerPreferencesKeys(final Class<?> type) {
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
