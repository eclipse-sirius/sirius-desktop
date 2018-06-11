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
package org.eclipse.sirius.services.diagram.internal.converter;

import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * Utility class used to transform RGB values.
 *
 * @author sbegaudeau
 */
public final class SiriusDiagramColorConverter {
    /**
     * The default color.
     */
    public static final SiriusDiagramRGBColor DEFAULT_COLOR = new SiriusDiagramRGBColor(0, 0, 0);

    /**
     * The constructor.
     */
    private SiriusDiagramColorConverter() {
        // To prevent instantiation
    }

    /**
     * Converts the given RGB values object.
     *
     * @param rgbValues
     *            The RGB values
     * @return The converted color
     */
    public static SiriusDiagramRGBColor convert(RGBValues rgbValues) {
        return new SiriusDiagramRGBColor(rgbValues.getRed(), rgbValues.getGreen(), rgbValues.getBlue());
    }
}
