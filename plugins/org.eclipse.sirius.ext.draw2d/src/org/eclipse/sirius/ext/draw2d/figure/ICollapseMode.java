/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ext.draw2d.figure;

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
