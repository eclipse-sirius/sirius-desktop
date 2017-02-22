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
package org.eclipse.sirius.table.ui.tools.internal.editor.print;

/**
 * The page dimension.
 * 
 * @author mchauvin
 */
public enum Dimension {
    /**
     * Custom dimensions.
     */
    CUSTOM(-1, -1),
    /**
     * A3 dimensions.
     */
    A3(297, 420),
    /**
     * A4 dimensions.
     */
    A4(210, 297);

    private int width;

    private int height;

    Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get the width.
     * 
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height.
     * 
     * @return the height
     */
    public int getHeight() {
        return height;
    }

}
