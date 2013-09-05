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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * A utility class to centralize all the dimensions ("magic number") used during
 * the layout of sequence diagrams. Unless otherwise stated, all the dimensions
 * and locations are in pixels.
 * 
 * @author mporhel
 */
public final class LayoutEditPartConstants {

    /**
     * How much an EndOfLife overlaps its parent lifeline.
     */
    public static final Dimension EOL_BORDER_ITEM_OFFSET = new Dimension(0, 0);

    /**
     * Which side of its parent should an EndOfLife appear on.
     */
    public static final int EOL_SIDE = PositionConstants.SOUTH;

    /**
     * How much an execution overlaps its parent execution.
     */
    public static final Dimension EXECUTION_BORDER_ITEM_OFFSET = new Dimension(5, 0);

    /**
     * Which side of its parent should an execution appear on.
     */
    public static final int EXECUTION_SIDE = PositionConstants.EAST;
    
    /**
     * How much a root execution overlaps its parent instance role.
     */
    public static final Dimension ROOT_EXECUTION_BORDER_ITEM_OFFSET = new Dimension(0, 0);

    private LayoutEditPartConstants() {
        // Prevents instantiation.
    }
}
