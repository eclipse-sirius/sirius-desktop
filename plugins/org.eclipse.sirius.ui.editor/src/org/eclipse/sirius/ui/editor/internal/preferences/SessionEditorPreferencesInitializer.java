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
package org.eclipse.sirius.ui.editor.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.ui.tools.internal.preference.SessionEditorUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * This class is in charge of setting the default value for preferences
 * regarding the session's editor.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SessionEditorPreferencesInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore uiPreferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        uiPreferenceStore.setDefault(SessionEditorUIPreferencesKeys.PREF_OPEN_SESSION_EDITOR_AT_MODELING_PROJECT_EXPANSION.name(), false);
    }

}
