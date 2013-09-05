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
 * Configuration of the page for printing.
 * 
 * @author mchauvin
 */
public class PageSetup {

    private Orientation orientation;

    private Dimension dimension;

    /**
     * Creates a new page setup with the given orientation and dimension.
     * 
     * @param orientation
     *            the page orientation
     * @param dimension
     *            the page dimension
     */
    public PageSetup(final Orientation orientation, final Dimension dimension) {
        this.orientation = orientation;
        this.dimension = dimension;
    }

    /**
     * Get the orientation.
     * 
     * @return the orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Get the dimension.
     * 
     * @return the dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Convert a dimension in millimeter to inch.
     * 
     * @param dimension
     *            to convert
     * @return the same dimension in inches
     */
    public static double inches(final int dimension) {
        return dimension / 25.4;
    }

    /**
     * Compute a size in pixel.
     * 
     * @param dimension
     *            page dimension in inches
     * @param dpi
     *            pixel per inches
     * @return the size in pixel
     */
    public static int size(final double dimension, final int dpi) {
        return (int) Math.round(dimension * dpi);
    }

}
