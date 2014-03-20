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
package org.eclipse.sirius.diagram.ui.tools.api.figure.anchor;

import org.eclipse.gef.editparts.ZoomManager;

/**
 * Anchor should implement if they need to know the zoom level for location
 * computation.
 * 
 * @author mchauvin
 */
public interface ZoomDependantAnchor {
    /**
     * Set the zoom manager.
     * 
     * @param manager
     *            the zoom manager.
     */
    void setZoomManager(ZoomManager manager);
}
