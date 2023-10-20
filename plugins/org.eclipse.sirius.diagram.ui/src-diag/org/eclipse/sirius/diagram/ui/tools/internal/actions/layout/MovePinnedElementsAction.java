/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.common.ui.action.INonRetargetable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;

/**
 * This action provides a choose to layout as if all element are unpin.
 * 
 * @author Séraphin Costa
 */
public class MovePinnedElementsAction extends Action implements IDisposableAction, INonRetargetable {

    private static final String PREF_KEY = SiriusDiagramPreferencesKeys.PREF_MOVE_PINNED_ELEMENTS.name();

    private IPropertyChangeListener syncToPreference;

    private IPreferenceChangeListener syncFromPreference;

    /**
     * Default constructor.
     */
    public MovePinnedElementsAction() {
        super(Messages.MovePinnedElementsAction_text, Action.AS_CHECK_BOX);

        setId(ActionIds.MOVE_PINNED_ELEMENTS);
        setToolTipText(Messages.MovePinnedElementsAction_toolTipText);
        setChecked(getValue());

        // auto sync action <-> preference
        syncToPreference = event -> {
            // filter on the "checked" property
            if (IAction.CHECKED.equals(event.getProperty())) {
                InstanceScope.INSTANCE.getNode(DiagramPlugin.ID).putBoolean(PREF_KEY, isChecked());
            }
        };
        syncFromPreference = event -> {
            // filter on the appropriate preference
            if (PREF_KEY.equals(event.getKey())) {
                setChecked(getValue());
            }
        };
    }

    /**
     * Return if the action move pinned elements are checked or not, according preference.
     * 
     * @return <code>true</code> if checked, <code>false</code> if unchecked.
     */
    public static boolean getValue() {
        return Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, PREF_KEY, false, null);
    }

    @Override
    public void init() {
        // Note: There's no infinite loop here (ping pong with two following listener),
        // because the values reach a stable state. Example:
        // The action changes from "uncheck" to "check"
        // -> the action listener synchronize the preference from false to true
        // ... -> the preference listener synchronize changes set action to "check" state
        // ....... -> the action is already in check state, its listener is not called
        addPropertyChangeListener(syncToPreference);
        InstanceScope.INSTANCE.getNode(DiagramPlugin.ID).addPreferenceChangeListener(syncFromPreference);
    }

    @Override
    public void dispose() {
        InstanceScope.INSTANCE.getNode(DiagramPlugin.ID).removePreferenceChangeListener(syncFromPreference);
        // not really require but it wouldn't hurt
        removePropertyChangeListener(syncToPreference);
    }

    @Override
    public boolean isDisposed() {
        return syncToPreference == null && syncFromPreference == null;
    }
}
