/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.business.api.preferences;

import org.eclipse.sirius.business.api.session.SiriusPreferences;

/**
 * This class bundles all the preferences keys for viewpoint.
 * 
 * @author ymortier
 */
public enum SiriusUIPreferencesKeys {

    /**
     * Says if the refresh should automatically be done on representation opening.<br/>
     * 
     * @deprecated This preference should not be used directly by client with Eclipse preference API. Use
     *             {@link SiriusPreferences}.isRefreshAtRepresentationOpening instead.
     */
    PREF_REFRESH_ON_REPRESENTATION_OPENING,

    /**
     * Says if session should return to the last {@link org.eclipse.sirius.business.api.session.SessionStatus.SYNC}
     * state when closing its last opened editor.
     * 
     * For a coherent save behavior, the preference should have the same value than PREF_SAVE_WHEN_NO_EDITOR.
     */
    PREF_RELOAD_ON_LAST_EDITOR_CLOSE,

    /**
     * Says if session should be saved on a resource change of there is no opened dialect editors.
     * 
     * For a coherent save behavior, the preference should have the same value than PREF_RELOAD_ON_LAST_EDITOR_CLOSE.
     */
    PREF_SAVE_WHEN_NO_EDITOR,

    /**
     * Specifies whether any permission issue should be displayed graphically to end-user. If false, then it will simply
     * be logged in the Error Log.
     */
    PREF_REACT_TO_PERMISSION_ISSUES_BY_GRAPHICAL_DISPLAY,

    /**
     * Indicates level of scaling when exported diagrams are scaled. Level to 10 means full autoScale whereas 0 means no
     * autoScale. This preference will be moved in SiriusDiagramUiPreferencesKeys in same time as corresponding "Export
     * as image" dialog (see bugzilla 527846).
     */
    PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT,

    /**
     * <b>Not considered if {@link DesignerUIPreferencesKeys#PREF_REACT_TO_PERMISSION_ISSUES_BY_GRAPHICAL_DISPLAY} is
     * false</b>. When a a permission issue should be displayed graphically, specifies wether it should be displayed
     * through the Dialect (e.g. a NotificationFigure inside a DDiagram) or through a standard pop-up.
     */
    PREF_DISPLAY_PERMISSION_ISSUES_THROUGH_DIALOG,

    /**
     * Indicates if the UserFixedColors, defined in the VSM, are displayed in the color palette.</br>
     * The color palette is used in appearance tab of properties view.
     */
    PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE;

}
