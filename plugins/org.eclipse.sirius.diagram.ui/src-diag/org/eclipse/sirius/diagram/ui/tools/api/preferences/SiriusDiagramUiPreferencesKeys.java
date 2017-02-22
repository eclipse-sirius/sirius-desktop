/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.preferences;

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
     * Indicates if diagram editors should display messages using pop-up or
     * notification figures. See
     * {@link org.eclipse.sirius.diagram.tools.internal.editor.DiagramDialectEditorDialogFactory}
     * for more details.
     * 
     * @since 0.9.0
     */
    PREF_DIAGRAM_SHOULD_DISPLAY_MESSAGES_USING_POP_UPS(boolean.class),

    /**
     * Says whether icons of the labels on diagram shapes should be hidden or
     * not.
     */
    PREF_HIDE_LABEL_ICONS_ON_SHAPES(boolean.class),

    /**
     * Says whether icons of the labels on diagram connectors should be hidden
     * or not.
     */
    PREF_HIDE_LABEL_ICONS_ON_CONNECTORS(boolean.class),

    /**
     * Says whether decorators (validation, lock statuses...) should be
     * refreshed:
     * <ul>
     * <li>Synchronously: whenever a modification is performed, all decorators
     * must be refreshed before the user can make any other modification (
     * <b>Warning:</b> this can have severe impacts on performances)</li>
     * <li>Asynchronously: whenever a modification is performed, all decorators
     * are refreshed without blocking the end-user (he can make other
     * modifications even if there are still non-updated decorators).</li>
     * </ul>
     */
    PREF_REFRESH_DECORATORS_SYNCHRONOUSLY(boolean.class),

    /**
     * Says whether a link between edge and its labels must be show on edge or
     * label selection.
     */
    PREF_SHOW_LINK_EDGE_LABEL_ON_SELECTION(boolean.class);

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
