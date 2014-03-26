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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;

/**
 * @was-generated
 */
public class DiagramUIPreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * @was-generated
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore store = getPreferenceStore();
        DiagramPrintingPreferencePage.initDefaults(store);
        DiagramGeneralPreferencePage.initDefaults(store);
        DiagramAppearancePreferencePage.initDefaults(store);
        DiagramConnectionsPreferencePage.initDefaults(store);
        DiagramRulersAndGridPreferencePage.initDefaults(store);
    }

    /**
     * @was-generated
     */
    protected IPreferenceStore getPreferenceStore() {
        return DiagramUIPlugin.getPlugin().getPreferenceStore();
    }
}
