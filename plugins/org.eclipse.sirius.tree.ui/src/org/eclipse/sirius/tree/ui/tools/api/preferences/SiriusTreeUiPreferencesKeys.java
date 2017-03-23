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
package org.eclipse.sirius.tree.ui.tools.api.preferences;

/**
 * Sirius preferences keys dedicated to trees.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public enum SiriusTreeUiPreferencesKeys {

    /**
     * Says whether the font size of the Sirius tree editors' items must always
     * be the one defined by the runtime (if true) or if only the user ones
     * should be used (if false).
     */
    PREF_ALWAYS_USE_STANDARD_FONT_SIZE(boolean.class);

    /** The type of the preference. */
    private Class<?> type;

    /**
     * Creates a new DesignerPreferencesKeys with the given type.
     * 
     * @param type
     *            the type of the value of the preference.
     */
    SiriusTreeUiPreferencesKeys(final Class<?> type) {
        this.type = type;
    }

    /**
     * Returns the type of the value of the preference of thsi key.
     * 
     * @return the type of the value of the preference of thsi key.
     */
    public Class<?> getType() {
        return type;
    }

}
