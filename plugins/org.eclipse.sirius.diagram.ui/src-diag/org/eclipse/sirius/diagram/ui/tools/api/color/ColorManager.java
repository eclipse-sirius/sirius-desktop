/*******************************************************************************
 * Copyright (c) 2011, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * Manage the color in complement to {@link VisualBindingManager} with use of draw2d API to compute some colors.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class ColorManager {

    private static final List<SystemColors> GRAYED_COLORS = Arrays.asList(SystemColors.BLACK_LITERAL, SystemColors.DARK_GRAY_LITERAL, SystemColors.GRAY_LITERAL, SystemColors.LIGHT_GRAY_LITERAL,
            SystemColors.WHITE_LITERAL);

    private static final List<SystemColors> RAINBOW_COLORS = Arrays.asList(SystemColors.DARK_RED_LITERAL, SystemColors.RED_LITERAL, SystemColors.LIGHT_RED_LITERAL, SystemColors.DARK_CHOCOLATE_LITERAL,
            SystemColors.CHOCOLATE_LITERAL, SystemColors.LIGHT_CHOCOLATE_LITERAL, SystemColors.DARK_ORANGE_LITERAL, SystemColors.ORANGE_LITERAL, SystemColors.LIGHT_ORANGE_LITERAL,
            SystemColors.DARK_YELLOW_LITERAL, SystemColors.YELLOW_LITERAL, SystemColors.LIGHT_YELLOW_LITERAL, SystemColors.DARK_GREEN_LITERAL, SystemColors.GREEN_LITERAL,
            SystemColors.LIGHT_GREEN_LITERAL, SystemColors.DARK_BLUE_LITERAL, SystemColors.BLUE_LITERAL, SystemColors.LIGHT_BLUE_LITERAL, SystemColors.DARK_PURPLE_LITERAL, SystemColors.PURPLE_LITERAL,
            SystemColors.LIGHT_PURPLE_LITERAL);

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

    /**
     * Collect the {@link FixedUserColor}s defined in all the selected VSM of the given session and concatenate them to
     * the {@link SystemColor}s.
     * 
     * @param session
     *            the session in which to find FixedUserColor
     * @return A map which key is the color name and the value is the RGB.
     */
    public Map<String, RGB> collectVsmAndDefaultColors(Session session) {
        // Get user fixed colors
        // @formatter:off
        // Get all the UserColorsPalette from every VSM resource of selected viewpoints
        List<UserColorsPalette> palettes = session.getSelectedViewpoints(true).stream()
                .map(EObject::eContainer)
                .map(org.eclipse.sirius.viewpoint.description.Group.class::cast)
                .flatMap(group -> group.getUserColorsPalettes().stream()).collect(Collectors.toList());

        Map<String, RGB> vsmColors = palettes.stream()
                .flatMap(palette -> palette.getEntries().stream())
                .filter(UserFixedColor.class::isInstance)
                .map(UserFixedColor.class::cast)
                .collect(Collectors.toMap(c -> c.getName(), c -> new RGB(c.getRed(), c.getGreen(), c.getBlue()), (rgb1, rgb2) -> {
                    // in a case of collision of key we keep the first value
                    return rgb1;
                }, () -> new LinkedHashMap<String, RGB>()));
        // @formatter:on

        // get system colors
        Map<String, RGB> systemColors = VisualBindingManager.getDefault().getSystemPalette();

        // organize the order of colors
        Map<String, RGB> colors = new LinkedHashMap<>();
        for (SystemColors color : GRAYED_COLORS) {
            colors.put(color.getName(), systemColors.get(color.getName()));
        }
        for (String colorName : vsmColors.keySet()) {
            colors.put(colorName, vsmColors.get(colorName));
        }
        for (SystemColors color : RAINBOW_COLORS) {
            colors.put(color.getName(), systemColors.get(color.getName()));
        }

        return colors;
    }
}
