/*******************************************************************************
 * Copyright (c) 2014, 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;

/**
 * Initializes the Diagram core preferences.
 * 
 * @author mporhel
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {
    @Override
    public void initializeDefaultPreferences() {
        final IEclipsePreferences diagramCoreDefaultPreferences = DefaultScope.INSTANCE.getNode(DiagramPlugin.ID);

        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), true);

        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), false);
        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE.name(), false);
        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name(), true);
        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramPreferencesKeys.PREF_AUTO_PIN_ON_CREATE.name(), getValue("_Pref_AutoPinOnCreate")); //$NON-NLS-1$

        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE_DEFAULT_VALUE);
        diagramCoreDefaultPreferences.putInt(SiriusDiagramCorePreferences.PREF_LINE_STYLE, SiriusDiagramCorePreferences.PREF_LINE_STYLE_DEFAULT_VALUE);
        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE, SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE_DEFAULT_VALUE);
        diagramCoreDefaultPreferences.putInt(SiriusDiagramCorePreferences.PREF_JUMP_LINK_STATUS, SiriusDiagramCorePreferences.PREF_JUMP_LINK_STATUS_DEFAULT_VALUE);
        diagramCoreDefaultPreferences.putInt(SiriusDiagramCorePreferences.PREF_JUMP_LINK_TYPE, SiriusDiagramCorePreferences.PREF_JUMP_LINK_TYPE_DEFAULT_VALUE);
        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramCorePreferences.PREF_REVERSE_JUMP_LINK, SiriusDiagramCorePreferences.PREF_REVERSE_JUMP_LINK_DEFAULT_VALUE);
        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramPreferencesKeys.PREF_DISPLAY_GENERIC_EDGE_CREATION_TOOL.name(), true);
        diagramCoreDefaultPreferences.putBoolean(SiriusDiagramPreferencesKeys.PREF_MOVE_PINNED_ELEMENTS.name(), false);
    }

    private boolean getValue(final String key) {
        final String value = DiagramPlugin.getPlugin().getString(key);
        return Boolean.valueOf(value);
    }
}
