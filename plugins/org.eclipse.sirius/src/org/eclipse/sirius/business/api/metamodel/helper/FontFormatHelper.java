/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.business.api.metamodel.helper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.viewpoint.FontFormat;

import com.google.common.collect.Lists;

/**
 * This class helps to update label format multi valued features. Do not use
 * directly add or addAll on {@see BasicLabelStyle#getLabelFormat()}.
 * 
 * @author mbats
 */
public final class FontFormatHelper {
    private FontFormatHelper() {
    }

    /**
     * Set the font format. Pay attention to update the minimum of values and to
     * keep the list ordered.
     * 
     * @param labelFormat
     *            Font format list to update
     * @param newValue
     *            New values
     */
    public static void setFontFormat(List<FontFormat> labelFormat, final Collection<? extends FontFormat> newValue) {
        // Get elements to add
        List<FontFormat> toAdd = Lists.newArrayList(newValue);
        toAdd.removeAll(labelFormat);
        Collections.sort(toAdd);

        // Get elements to remove
        List<FontFormat> toRemove = Lists.newArrayList(labelFormat);
        toRemove.removeAll(newValue);

        // Update the list
        for (FontFormat fontFormat : toRemove) {
            labelFormat.remove(fontFormat);
        }
        for (FontFormat fontFormat : toAdd) {
            labelFormat.add(getIndex(labelFormat, fontFormat), fontFormat);
        }
    }

    /**
     * Set the font format. Pay attention to keep the list ordered.
     * 
     * @param labelFormat
     *            Font format list to update
     * @param newValue
     *            New value
     */
    public static void setFontFormat(List<FontFormat> labelFormat, FontFormat newValue) {
        if (labelFormat.contains(newValue)) {
            return;
        }

        labelFormat.add(getIndex(labelFormat, newValue), newValue);
    }

    /**
     * Get the index where to add the new label format value.
     * 
     * @param labelFormat
     *            Label format
     * @param newValue
     *            New value
     * @return Index where new value must be added
     */
    private static int getIndex(List<FontFormat> labelFormat, FontFormat newValue) {
        int index = 0;
        for (FontFormat fontFormat : labelFormat) {
            if (fontFormat.getValue() > newValue.getValue()) {
                return index;
            }
            index++;
        }
        return index;
    }
}
