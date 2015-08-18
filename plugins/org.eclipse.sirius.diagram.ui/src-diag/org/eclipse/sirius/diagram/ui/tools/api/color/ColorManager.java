/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.color;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;

/**
 * Manage the color in complement to {@link VisualBindingManager} with use of
 * draw2d API to compute some colors.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class ColorManager {

    private static ColorManager defaultInstance;

    private Map<String, Color> lighterColorCache;

    /**
     * Constructor.
     */
    protected ColorManager() {
        lighterColorCache = new HashMap<String, Color>();
    }

    /**
     * Return the singleton instance.
     * 
     * @return a default instance
     */
    public static ColorManager getDefault() {
        if (defaultInstance == null) {
            defaultInstance = new ColorManager();
        }
        return defaultInstance;
    }

    /**
     * Get a lighter color from the colorToLight.
     * 
     * @param colorToLight
     *            the color to light
     * @return A color get from the cache
     */
    public Color getLighterColor(final RGBValues colorToLight) {
        final String key = getKey(colorToLight);
        if (!lighterColorCache.containsKey(key)) {
            Color newLighterColor = FigureUtilities.mixColors(VisualBindingManager.getDefault().getColorFromRGBValues(colorToLight), ColorConstants.white, 0.4);
            lighterColorCache.put(key, newLighterColor);
        }
        return lighterColorCache.get(key);
    }

    private String getKey(RGBValues color) {
        StringBuilder sb = new StringBuilder("Color"); //$NON-NLS-1$
        if (color != null) {
            sb.append("_r:"); //$NON-NLS-1$
            sb.append(color.getRed());
            sb.append("_g:"); //$NON-NLS-1$
            sb.append(color.getGreen());
            sb.append("_b:"); //$NON-NLS-1$
            sb.append(color.getBlue());
        }
        return sb.toString();
    }
}
