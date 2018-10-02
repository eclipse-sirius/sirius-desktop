/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo
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

/**
 * Image figure which has the ability to retrieve alpha information from its
 * internal representation.
 * 
 * @author cedric
 * 
 */
public interface ImageFigureWithAlpha {

    /**
     * return the image (buffer or internal representation) height.
     * 
     * @return the image (buffer or internal representation) height.
     */
    int getImageHeight();

    /**
     * return the image (buffer or internal representation) width.
     * 
     * @return the image (buffer or internal representation) width.
     */
    int getImageWidth();

    /**
     * Return the alpha level at the given x and y position in the image
     * coordinate system.
     * 
     * @param x
     *            x value in the image buffer.
     * @param y
     *            y value in the image buffer.
     * @return the alpha value from 0 to 255. In case the figure is not able to
     *         retrieve the alpha information 255 should be returned.
     */
    int getImageAlphaValue(int x, int y);

}
