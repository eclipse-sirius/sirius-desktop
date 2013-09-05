/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * Constants for collapsed nodes.
 * 
 * @author mporhel
 */
public interface ICollapseMode {

    /**
     * Indicates the current collapse mode.
     * 
     * - true : border nodes are collapsed (height, weight = 0, no available
     * selection)
     * 
     * - false : border nodes are minimized and transparents (selection and move
     * are enabled)
     * 
     * This flag is only there to ease the switch between the two behavior, as
     * the client was not sure which mode was best.
     */
    boolean DEFAULT = false;

    /**
     * Offset for default collapsed nodes (in default mode).
     */
    Dimension COLLAPSE_DEFAULT_OFFSET = new Dimension(0, 0);

    /**
     * Offset for collapsed nodes (in minimized mode).
     */
    Dimension COLLAPSE_MINIMIZED_OFFSET = new Dimension(2, 2);

    /**
     * dimension for collapsed nodes (in default mode).
     */
    Dimension COLLAPSED_DIMENSION = new Dimension(0, 0);

    /**
     * dimension for collapsed nodes (in minimized mode).
     */
    // we decrease the minimized_dimension of collapsed nodes in minimized mode
    Dimension MINIMIZED_DIMENSION = new Dimension(1, 1);
}
