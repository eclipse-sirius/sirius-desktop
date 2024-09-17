/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.api.color;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

/**
 * Interface representing a color palette popup used for color selection in a graphical user interface.
 * Implementations of this interface provide customizable behavior for initializing and displaying the palette
 * and selecting colors.
 *
 * <p>Classes implementing this interface should define the logic for palette initialization, user color selection,
 * and palette display.</p>
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public interface IColorPalettePopup {

    /**
     * Initializes the layout of the popup.
     */
    void init();

    /**
     * Opens the popup, waits for an item to be selected and then closes popup.
     * 
     * @param location
     *            the initial location of the popup.
     * 
     * @return the selected color or null if none selected
     */
    RGB open(Point location);

    /**
     * Gets the color the user selected. Could be null as the user may have cancelled the gesture or they may have
     * selected the default color button.
     * 
     * @return selectedColor
     */
    RGB getSelectedColor();

    /**
     * Sets the previous color.
     * 
     * @param previousColor
     *            the previous color.
     */
    void setPreviousColor(int previousColor);

    /**
     * Returns the previous color.
     * 
     * @return previousColor
     */
    int getPreviousColor();
}
