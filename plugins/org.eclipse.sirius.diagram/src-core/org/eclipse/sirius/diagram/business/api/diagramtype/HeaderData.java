/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.api.diagramtype;

import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;

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
    public static final RGBValues DEFAULT_BACKGROUND_COLOR = ViewpointFactory.eINSTANCE.createRGBValues();

    /**
     * The default label color for header when <code>labelColor</code> is null.
     */
    public static final RGBValues DEFAULT_LABEL_COLOR = ViewpointFactory.eINSTANCE.createRGBValues();

    /**
     * The default background color for space between each header.
     */
    public static final RGBValues DEFAULT_SEPARATOR_BACKGROUND_COLOR = ViewpointFactory.eINSTANCE.createRGBValues();

    static {
        DEFAULT_BACKGROUND_COLOR.setRed(200);
        DEFAULT_BACKGROUND_COLOR.setBlue(200);
        DEFAULT_BACKGROUND_COLOR.setGreen(200);

        DEFAULT_LABEL_COLOR.setRed(0);
        DEFAULT_LABEL_COLOR.setBlue(0);
        DEFAULT_LABEL_COLOR.setGreen(0);

        DEFAULT_SEPARATOR_BACKGROUND_COLOR.setRed(255);
        DEFAULT_SEPARATOR_BACKGROUND_COLOR.setBlue(255);
        DEFAULT_SEPARATOR_BACKGROUND_COLOR.setGreen(255);
    }

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
