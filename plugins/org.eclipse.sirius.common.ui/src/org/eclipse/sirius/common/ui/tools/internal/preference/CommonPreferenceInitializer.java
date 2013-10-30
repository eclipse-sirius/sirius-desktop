/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;

/**
 * Initializes preferences.
 * 
 * @author ymortier
 */
public class CommonPreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {

        final IPreferenceStore preferenceStore = getPreferenceStore();
        preferenceStore.setDefault(CommonPreferencesConstants.PREF_TRACE_ON, false);
        preferenceStore.setDefault(CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, true);
        preferenceStore.setDefault(CommonPreferencesConstants.PREF_GROUP_ENABLE, true);
        preferenceStore.setDefault(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE, false);
        preferenceStore.setDefault(CommonPreferencesConstants.PREF_GROUP_TRIGGER, 10000);
        preferenceStore.setDefault(CommonPreferencesConstants.PREF_GROUP_SIZE, 100);
    }

    /**
     * returns the preference store.
     * 
     * @return the preference store.
     */
    protected IPreferenceStore getPreferenceStore() {
        return SiriusTransPlugin.getPlugin().getPreferenceStore();
    }

}
