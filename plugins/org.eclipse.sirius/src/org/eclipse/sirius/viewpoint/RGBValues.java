/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * RGBValues are descriptions of colors in terms of the primary additive color
 * model (red, green and blue).
 * 
 * A color may be described in terms of the relative intensities of these three
 * primary colors. The brightness of each color is specified by a value in the
 * range 0 to 255, where 0 indicates no color (blackness) and 255 indicates
 * maximum intensity.
 * 
 * RGBValues instances are interned.
 * 
 * @author cbrun
 *
 */
public final class RGBValues {

    private static final Map<String, RGBValues> INTERN = Maps.newHashMap();

    // CHECKSTYLE:OFF
    // Disable checkstyle to be able to intern the DEFAULT public constant with
    // a declaration after the private INTERN map.
    /**
     * A default color.
     */
    public static final RGBValues DEFAULT_GRAY = new RGBValues(209, 209, 209);

    // CHECKSTYLE:ON

    private static final String SEPARATOR = ","; //$NON-NLS-1$

    private int red;

    private int green;

    private int blue;

    /**
     * Create a new {@link RGBValues}.
     * 
     * @param red
     *            red channel.
     * @param green
     *            green channel.
     * @param blue
     *            blue channel.
     */
    private RGBValues(int red, int green, int blue) {
        super();
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    // CHECKSTYLE:OFF

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + blue;
        result = prime * result + green;
        result = prime * result + red;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RGBValues other = (RGBValues) obj;
        if (blue != other.blue)
            return false;
        if (green != other.green)
            return false;
        if (red != other.red)
            return false;
        return true;
    }

    // CHECKSTYLE:ON

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(red);
        result.append(SEPARATOR);
        result.append(green);
        result.append(SEPARATOR);
        result.append(blue);
        return result.toString();
    }

    /**
     * Return a RGBValues corresponding to the given red, green and blue
     * attributes. The RGBValues elements are interned, successive calls with
     * the same color attributes will return the same object.
     * 
     * @param r
     *            the red attribute.
     * @param g
     *            the green attribute.
     * @param b
     *            the blue attribute.
     * @return the corresponding RGBValues object.
     */
    public static RGBValues create(int r, int g, int b) {
        String key = computeKey(r, g, b);
        RGBValues found = INTERN.get(key);
        if (found == null) {
            found = new RGBValues(r, g, b);
            INTERN.put(key, found);
        }
        return found;
    }

    private static String computeKey(int r, int g, int b) {
        return r + SEPARATOR + g + SEPARATOR + b;
    }

    /**
     * Converts from an Integer to an {@link RGBValues} representation. The
     * Integer encoding is the one used by gmf notation.
     * 
     * @param color
     *            an integer value encoding
     * @return An {@link RGBValues} instance matching the integer values.
     */
    public static RGBValues integerToRGBValues(int color) {
        return RGBValues.create(color & 0x000000FF, (color & 0x0000FF00) >> 8, (color & 0x00FF0000) >> 16);
    }

}
