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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

/**
 * Interface for transparent figures.
 * 
 * @author mporhel
 */
public interface ITransparentFigure {

    /**
     * Default alpha value.
     */
    int DEFAULT_ALPHA = 100;

    /**
     * Return true if figure is in transparent mode.
     * 
     * @return true if figure is in transparent mode.
     */
    boolean isTransparent();

    /**
     * Enable/disable the tranparent mode of the figure.
     * 
     * @param transparent
     *            the wanted mode.
     */
    void setTransparent(boolean transparent);

    /**
     * Get the alpha value.
     * 
     * @return the alpha value.
     */
    int getSiriusAlpha();

    /**
     * Set the alpha to the given value. Values may range from 0 to 255. A value
     * of 0 is completely transparent.
     * 
     * @param alpha
     *            an alpha value (0-255)
     */
    void setSiriusAlpha(int alpha);
}
