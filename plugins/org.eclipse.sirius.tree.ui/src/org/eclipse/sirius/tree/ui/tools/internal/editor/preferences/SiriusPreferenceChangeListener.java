/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.preferences;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;

import org.eclipse.sirius.business.api.preferences.DesignerPreferencesKeys;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;

/**
 * Listener of the
 * {@link org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor}
 * preferences.
 * 
 * @author mporhel
 * 
 */
public class SiriusPreferenceChangeListener implements IPreferenceChangeListener {

    ITreeCommandFactory factory;

    /**
     * Build a new listener.
     * 
     * @param factory
     *            editor's command factory.
     */
    public SiriusPreferenceChangeListener(final ITreeCommandFactory factory) {
        this.factory = factory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Preferences.IPreferenceChangeListener#propertyChange(org.eclipse.core.runtime.Preferences.PreferenceChangeEvent)
     */
    public void preferenceChange(PreferenceChangeEvent event) {
        if (DesignerPreferencesKeys.PREF_AUTO_REFRESH.name().equals(event.getKey()) && event.getNewValue() instanceof String && !event.getNewValue().equals(event.getOldValue())) {
            factory.setAutoRefreshDTree(Boolean.parseBoolean((String) event.getNewValue()));
        }
    }
}
