/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.preferences;

import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.preferences.RulerGridPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;

/**
 * @was-generated
 */
public class DiagramRulersAndGridPreferencePage extends RulerGridPreferencePage {

    /**
     * Initializes the default preference values for the preferences (firstly,
     * the GMF default values and then the Sirius ones to override GMF).
     * 
     * @param IPreferenceStore
     *            preferences
     */
    public static void initDefaults(IPreferenceStore preferenceStore) {
        RulerGridPreferencePage.initDefaults(preferenceStore);
        preferenceStore.setDefault(IPreferenceConstants.PREF_SNAP_TO_GEOMETRY, true);
    }

    /**
     * @was-generated
     */
    public DiagramRulersAndGridPreferencePage() {
        setPreferenceStore(DiagramUIPlugin.getPlugin().getPreferenceStore());
    }

}
