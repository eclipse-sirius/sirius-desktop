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
package org.eclipse.sirius.tree.ui.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.api.preferences.SiriusTreeUiPreferencesKeys;

/**
 * Initializes the tree preferences.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class TreeUIPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = getPreferenceStore();
        store.setDefault(SiriusTreeUiPreferencesKeys.PREF_ALWAYS_USE_STANDARD_FONT_SIZE.name(), false);
    }

    /**
     * Returns the preference store to use.
     * 
     * @return the preference store to use.
     */
    protected IPreferenceStore getPreferenceStore() {
        return TreeUIPlugin.getPlugin().getPreferenceStore();
    }
}
