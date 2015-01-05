/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug.statusline;

import org.eclipse.draw2d.geometry.Point;

/**
 * Control manager.
 * 
 * @author dlecan
 */
public interface MouseLocationManager {

    /**
     * Absolute prefix.
     */
    String LOGICAL_PREFIX = "Logical: ";

    /**
     * Relative prefix.
     */
    String SCREEN_PREFIX = "Screen: ";

    /**
     * Separator.
     */
    String SEP = " | ";

    /**
     * Instance.
     */
    MouseLocationManager INSTANCE = new MouseLocationManagerImpl();

    /**
     * Update mouse location.
     * 
     * @param logicalMouseLocation
     *            New absolute mouse location. May be <code>null</code>.
     * @param screenMouseLocation
     *            New relative mouse location. May be <code>null</code>.
     */
    void updateMouseLocation(Point logicalMouseLocation, Point screenMouseLocation);

}
