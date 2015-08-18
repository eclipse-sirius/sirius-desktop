/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida;

/**
 * Configuration class used to control if Movida-specific behaviors are enabled
 * or not in the rest of Sirius.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public final class Movida {
    private static final boolean ENABLED = Boolean.valueOf(System.getProperty("movida")); //$NON-NLS-1$

    private Movida() {
        // Prevents instantiation.
    }

    /**
     * If true, enable the Movida-specific behaviors.
     * 
     * @return <code>true</code> if we should use the new behaviors.
     */
    public static boolean isEnabled() {
        return ENABLED;
    }
}
