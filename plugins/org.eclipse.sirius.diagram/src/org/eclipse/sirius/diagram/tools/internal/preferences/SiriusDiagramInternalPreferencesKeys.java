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
package org.eclipse.sirius.diagram.tools.internal.preferences;

/**
 * Sirius preferences keys dedicated to diagrams.
 * 
 * @author cbrun
 * 
 */
public enum SiriusDiagramInternalPreferencesKeys {

    /**
     * Says if diagram should be marked as synchronized on creation.
     */
    PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION(boolean.class);

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
