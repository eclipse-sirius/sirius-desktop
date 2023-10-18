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
package org.eclipse.sirius.diagram.tools.api.preferences;

/**
 * Sirius preferences keys dedicated to diagrams.
 * 
 * @author jdupont
 * 
 */
public enum SiriusDiagramPreferencesKeys {

    /**
     * Says if notes unlinked should be moved during layout of arrange all.
     */
    PREF_MOVE_NOTES_DURING_LATOUT(boolean.class),

    /**
     * Indicates that clipboard support should be disabled except for note.
     */
    PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE(boolean.class),

    /**
     * Says if diagram elements should be marked as pinned automatically when created using a creation tool, when the
     * user specifies explicitly the location of the element.
     */
    PREF_AUTO_PIN_ON_CREATE(boolean.class),

    /**
     * Says if the header section should be display in the diagram or not.
     */
    PREF_DISPLAY_HEADER_SECTION(boolean.class),

    /**
     * Says if the generic edge creation tool should be displayed in the palette.
     */
    PREF_DISPLAY_GENERIC_EDGE_CREATION_TOOL(boolean.class),

    /**
     * Says if the layout commands move pinned elements.
     */
    PREF_MOVE_PINNED_ELEMENTS(boolean.class);

    /** The type of the preference. */
    private Class<?> type;

    /**
     * Creates a new DesignerPreferencesKeys with the given type.
     * 
     * @since 0.9.0
     * @param type
     *            the type of the value of the preference.
     */
    SiriusDiagramPreferencesKeys(final Class<?> type) {
        this.type = type;
    }

    /**
     * Returns the type of the value of the preference of this key.
     * 
     * @return the type of the value of the preference of this key.
     */
    public Class<?> getType() {
        return type;
    }

}
