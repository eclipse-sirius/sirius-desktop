/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.preferences;

import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;

/**
 * Sirius preferences keys dedicated to diagrams.
 * 
 * @author jdupont
 * 
 */
public enum SiriusDiagramUiPreferencesKeys {

    /**
     * Says if the old UI should be used.
     */
    PREF_OLD_UI(boolean.class),

    /**
     * Indicates if diagram editors should display messages using pop-up or notification figures. See
     * {@link org.eclipse.sirius.diagram.tools.internal.editor.DiagramDialectEditorDialogFactory} for more details.
     * 
     * @since 0.9.0
     */
    PREF_DIAGRAM_SHOULD_DISPLAY_MESSAGES_USING_POP_UPS(boolean.class),

    /**
     * Says whether icons of the labels on diagram shapes should be hidden or not.
     */
    PREF_HIDE_LABEL_ICONS_ON_SHAPES(boolean.class),

    /**
     * Says whether icons of the labels on diagram connectors should be hidden or not.
     */
    PREF_HIDE_LABEL_ICONS_ON_CONNECTORS(boolean.class),

    /**
     * Says whether decorators (validation, lock statuses...) should be refreshed:
     * <ul>
     * <li>Synchronously: whenever a modification is performed, all decorators must be refreshed before the user can
     * make any other modification ( <b>Warning:</b> this can have severe impacts on performances)</li>
     * <li>Asynchronously: whenever a modification is performed, all decorators are refreshed without blocking the
     * end-user (he can make other modifications even if there are still non-updated decorators).</li>
     * </ul>
     */
    PREF_REFRESH_DECORATORS_SYNCHRONOUSLY(boolean.class),

    /**
     * Says whether a link between edge and its labels must be show on edge or label selection.
     */
    PREF_SHOW_LINK_EDGE_LABEL_ON_SELECTION(boolean.class),
    /**
     * 
     * Says if the overlap is authorized between decoration group.
     */
    PREF_AUTHORIZE_DECORATION_OVERLAP(boolean.class),
    /**
     * 
     * Says if the decorations are printed.
     */
    PREF_PRINT_DECORATION(boolean.class),

    /**
     * Says if the synchronize status decorator must be shown.
     */
    PREF_SHOW_SYNCHRONIZE_STATUS_DECORATOR(boolean.class),

    /**
     * Indicates if by default exported diagrams are automatically scaled (instead of being export at 100% zoom level).
     * 
     * @deprecated Currently only the scale level is visible in the UI. This boolean value is kept for compatibility
     *             reason and will be removed on the next major version.
     *             SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT must be used instead.
     */
    PREF_SCALE_DIAGRAMS_ON_EXPORT(boolean.class),

    /**
     * Maximum size (in pixels) of the image buffer used to render the diagrams.
     */
    PREF_MAXIMUM_EXPORT_BUFFER_SIZE(int.class),

    /**
     * Maximum size (in pixels) of the image buffer used to render the diagrams under Windows (which safely supports
     * larger buffers).
     */
    PREF_MAXIMUM_EXPORT_BUFFER_SIZE_WINDOWS(int.class),

    /**
     * A boolean preference to say if the paste mode must be requested at each "Paste format" or "Paste layout" action.
     */
    PREF_PROMPT_PASTE_MODE(boolean.class),

    /**
     * A boolean preference for the paste mode: true for "Absolute" mode, false for "Bounding box" mode:
     * <UL>
     * <LI>If the {@link #PREF_PROMPT_PASTE_MODE} is true, this preference is used to pre-select the choice in the
     * dialog.</LI>
     * <LI>If the {@link #PREF_PROMPT_PASTE_MODE} is false, this preference is used as the Paste mode to apply.</LI>
     * </UL>
     * 
     * This preference is changed as soon as the user changes the selection in the dialog asking the mode. Thereby, at
     * the next dialog, the last choice is pre-selected.
     */
    PREF_PASTE_MODE_ABSOLUTE(boolean.class),

    /**
     * Used to define the arrangement of elements created on a diagram when they are not directly positioned by the
     * user.
     * <ul>
     * <li>Using {@link SiriusLayoutDataManager#DIAGONAL_ARRANGEMENT}, elements created one after the other will by
     * default be arranged diagonally one after the other on the diagram.</li>
     * <li>Using {@link SiriusLayoutDataManager#VERTICAL_ARRANGEMENT}, elements created one after the other will by
     * default be arranged vertically one after the other on the diagram.</li>
     * <li>Using {@link SiriusLayoutDataManager#HORIZONTAL_ARRANGEMENT}, elements created one after the other will by
     * default be arranged horizontally one after the other on the diagram.</li> By default, the value used is
     * {@link SiriusLayoutDataManager#DIAGONAL_ARRANGEMENT}.
     */
    PREF_NEWLY_CREATED_ELEMENTS_LAYOUT(int.class);

    /** The type of the preference. */
    private Class<?> type;

    /**
     * Creates a new DesignerPreferencesKeys with the given type.
     * 
     * @since 0.9.0
     * @param type
     *            the type of the value of the preference.
     */
    SiriusDiagramUiPreferencesKeys(final Class<?> type) {
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
