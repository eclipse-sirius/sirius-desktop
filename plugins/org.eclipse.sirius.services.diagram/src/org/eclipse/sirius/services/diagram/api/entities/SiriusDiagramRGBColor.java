/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.api.entities;

/**
 * The values of the RGB color.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramRGBColor {
    /**
     * The red part of the color.
     */
    private int red;

    /**
     * The green part of the color.
     */
    private int green;

    /**
     * The blue part of the color.
     */
    private int blue;

    /**
     * The constructor.
     * 
     * @param red
     *            The red
     * @param green
     *            The green
     * @param blue
     *            The blue
     */
    public SiriusDiagramRGBColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Return the red.
     *
     * @return the red
     */
    public int getRed() {
        return this.red;
    }

    /**
     * Return the green.
     *
     * @return the green
     */
    public int getGreen() {
        return this.green;
    }

    /**
     * Return the blue.
     *
     * @return the blue
     */
    public int getBlue() {
        return this.blue;
    }
}
