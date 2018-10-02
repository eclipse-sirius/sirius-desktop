/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.sirius.ext.draw2d.figure.ICollapseMode;

/**
 * Constants for border item offsets.
 * 
 * @author mchauvin
 */
public interface IBorderItemOffsets {
    /**
     * Default offset: represents how much of a bordered node can be "inside"
     * its parent (the container it is on the border of).
     */
    Dimension DEFAULT_OFFSET = new Dimension(8, 8);

    /**
     * Offset to use when collapse filter is used.
     */
    Dimension COLLAPSE_FILTER_OFFSET = ICollapseMode.DEFAULT ? ICollapseMode.COLLAPSE_DEFAULT_OFFSET : ICollapseMode.COLLAPSE_MINIMIZED_OFFSET;

    /**
     * Offset to use to remove item.
     */
    Dimension NO_OFFSET = new Dimension(-1, -1);
}
