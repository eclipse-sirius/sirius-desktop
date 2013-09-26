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
package org.eclipse.sirius.table.business.internal.color;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * Table implementation to update the cell style colors.
 * 
 * @author cbrun
 * 
 */
public class TableStyleColorUpdater extends AbstractColorUpdater {

    /**
     * Update the background color, if needed, of a cell/line/column style.
     * 
     * @param style
     *            style to update.
     * @param colorDescription
     *            the color description.
     * @param defaultStyle
     *            true if the coloDescription comes from the default style
     *            description, false if it is comes from a conditional style
     *            description.
     * @param target
     *            the current semantic element.
     */
    public void updateBackgroundColor(final DTableElementStyle style, final ColorDescription colorDescription, final boolean defaultStyle, final EObject target) {
        final RGBValues values = getRGBValuesFromColorDescription(target, colorDescription);
        if (!AbstractColorUpdater.areEquals(style.getBackgroundColor(), values)) {
            style.setDefaultBackgroundStyle(defaultStyle);
            style.setBackgroundColor(values);
        }

    }

    /**
     * Update the foreground color, if needed, of a cell/line/column style.
     * 
     * @param style
     *            style to update.
     * @param colorDescription
     *            the color description.
     * @param defaultStyle
     *            true if the coloDescription comes from the default style
     *            description, false if it is comes from a conditional style
     *            description.
     * @param target
     *            the current semantic element.
     */
    public void updateForegroundColor(final DTableElementStyle style, final ColorDescription colorDescription, final boolean defaultStyle, final EObject target) {
        final RGBValues values = getRGBValuesFromColorDescription(target, colorDescription);
        if (!AbstractColorUpdater.areEquals(style.getForegroundColor(), values)) {
            style.setDefaultForegroundStyle(defaultStyle);
            style.setForegroundColor(values);
        }
    }
}
