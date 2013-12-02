/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.listener;

/**
 * A class to provide custom notifications.
 * 
 * @author mchauvin
 */
public interface Notification {

    /**
     * The kind of notification.
     * 
     * @author mchauvin
     */
    public interface Kind {
        /** start kind. */
        int START = 0;

        /** stop kind. */
        int STOP = 1;
    }

    /**
     * Activate or deactivate the visibility propagator.
     * 
     * @since 0.9.0
     */
    int VISIBILITY = 2;

    /**
     * Activate or deactivate the visibility updater.
     * 
     * @since 0.9.0
     */
    int VISIBILITY_UPDATE = 5;

    /**
     * Activate or deactivate the visibility propagator for refresh on all
     * diagram.
     * 
     * @since 0.9.0
     */
    int REFRESH_VISIBILITY_ON_DIAGRAM = 3;
}
