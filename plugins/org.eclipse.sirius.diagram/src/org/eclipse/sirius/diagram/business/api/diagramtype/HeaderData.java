/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.api.diagramtype;

import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * A specific class grouping all data of a header :
 * <UL>
 * <LI>The name of the header to display,</LI>
 * <LI>The x location in pixel and with logical coordinate (from origin in zoom
 * level 100%),</LI>
 * <LI>The width in pixel and with logical coordinate (in zoom level 100%),</LI>
 * <LI>The background color,</LI>
 * <LI>The label color.</LI>
 * </UL>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class HeaderData {
    /**
     * The default background color for header when <code>backgroundColor</code>
     * is null.
     */
    public static final RGBValues DEFAULT_BACKGROUND_COLOR = RGBValues.create(200, 200, 200);

    /**
     * The default label color for header when <code>labelColor</code> is null.
     */
    public static final RGBValues DEFAULT_LABEL_COLOR = RGBValues.create(0, 0, 0);

    /**
     * The default background color for space between each header.
     */
    public static final RGBValues DEFAULT_SEPARATOR_BACKGROUND_COLOR = RGBValues.create(255, 255, 255);

    String name;

    int xLocation;

    int width;

    RGBValues backgroundColor;

    RGBValues labelColor;

    /**
     * Default constructor.
     * 
     * @param name
     *            The name of this header
     * @param xLocation
     *            The x coordinate of this header
     * @param width
     *            The width of this header
     * @param backgroundColor
     *            The background color of this header
     * @param labelColor
     *            The label color of this header
     */
    public HeaderData(String name, int xLocation, int width, RGBValues backgroundColor, RGBValues labelColor) {
        this.name = name;
        this.xLocation = xLocation;
        this.width = width;
        this.backgroundColor = backgroundColor;
        this.labelColor = labelColor;
    }

    /**
     * Get the name of this header.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the x coordinate of this header.
     * 
     * @return the xLocation
     */
    public int getXLocation() {
        return xLocation;
    }

    /**
     * Get the width of this header.
     * 
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the background color of this header.
     * 
     * @return the background color
     */
    public RGBValues getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Get the label color of this header.
     * 
     * @return the label color
     */
    public RGBValues getLabelColor() {
        return labelColor;
    }
}
