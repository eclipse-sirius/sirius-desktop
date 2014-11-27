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

/**
 * RGBValues are descriptions of colors in terms of the primary additive color
 * model (red, green and blue).
 * 
 * A color may be described in terms of the relative intensities of these three
 * primary colors. The brightness of each color is specified by a value in the
 * range 0 to 255, where 0 indicates no color (blackness) and 255 indicates
 * maximum intensity.
 */
public class RGBValues {

    private int red;

    private int green;

    private int blue;

    /**
     * create a new {@link RGBValues}.
     * 
     * @param red
     *            red channel.
     * @param green
     *            green channel.
     * @param blue
     *            blue channel.
     */
    public RGBValues(int red, int green, int blue) {
        super();
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Default constructor.
     */
    public RGBValues() {
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
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
        result.append(",");
        result.append(green);
        result.append(",");
        result.append(blue);
        return result.toString();
    }

}
