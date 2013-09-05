/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.refresh;

/**
 * Constants for the refresh API.
 * 
 * @author ymortier
 */
public final class RefreshConstants {

    /**
     * The lowest priority.
     */
    public static final int LOWEST_PRIORITY = 20;

    /**
     * A low priority.
     */
    public static final int LOW_PRIORITY = 10;

    /**
     * A normal priority (the EcoreIntrinsicExtender use this one.
     */
    public static final int NORMAL_PRIORITY = 5;

    /**
     * A high priority.
     */
    public static final int HIGH_PRIORITY = 1;

    /**
     * The highest priority.
     */
    public static final int HIGHEST_PRIORITY = 0;

    /**
     * Avoid instanciation
     */
    private RefreshConstants() {

    }

}
