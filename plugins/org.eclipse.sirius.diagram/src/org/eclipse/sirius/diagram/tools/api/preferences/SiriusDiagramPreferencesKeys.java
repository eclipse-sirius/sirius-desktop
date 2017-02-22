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
     * Says if diagram elements should be marked as pinned automatically when
     * created using a creation tool, when the user specifies explicitly the
     * location of the element.
     */
    PREF_AUTO_PIN_ON_CREATE(boolean.class),

    /**
     * Says if the header section should be display in the diagram or not.
     */
    PREF_DISPLAY_HEADER_SECTION(boolean.class);

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
     * Returns the type of the value of the preference of thsi key.
     * 
     * @return the type of the value of the preference of thsi key.
     */
    public Class<?> getType() {
        return type;
    }

}
