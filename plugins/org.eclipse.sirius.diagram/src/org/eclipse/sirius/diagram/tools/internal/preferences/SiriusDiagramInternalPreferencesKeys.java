/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.preferences;

/**
 * Sirius preferences keys dedicated to diagrams.
 * 
 * @author cbrun
 * 
 */
public enum SiriusDiagramInternalPreferencesKeys {

    /**
     * Says whether the container should be automatically auto-sized during the
     * arrange all or not.
     */
    PREF_AUTOSIZE_ON_ARRANGE(boolean.class),

    /**
     * Says if diagram elements should be marked as pinned automatically when
     * moved by the user.
     */
    PREF_AUTO_PIN_ON_MOVE(boolean.class),

    /**
     * Says if diagram should be marked as synchronized on creation.
     */
    PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION(boolean.class),

    /**
     * Says if Note must be hide or remove when hide or remove associated
     * element.
     */
    PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE(boolean.class);

    /** The type of the preference. */
    private Class<?> type;

    /**
     * Creates a new DesignerPreferencesKeys with the given type.
     * 
     * @param type
     *            the type of the value of the preference.
     */
    private SiriusDiagramInternalPreferencesKeys(final Class<?> type) {
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
