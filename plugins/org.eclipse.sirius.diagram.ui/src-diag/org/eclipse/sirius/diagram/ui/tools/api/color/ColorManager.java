/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
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

    /**
     * The separator between each integer value of RGB color.
     */
    private static final String RGB_VALUES_SEPARATOR = ","; //$NON-NLS-1$

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
     * Collect the {@link UserFixedColor}s defined in the all the selected VSM of the given session and concat them to
     * the {@link SystemColor}s.
     * 
     * @param session
     *            the session in which to find UserFixedColor
     * @return A map which key is the color name and the value is the RGB.
     */
    public Map<String, RGB> collectVsmAndDefaultColors(Session session) {
        // Get user fixed colors
        // Get all the UserColorsPalette from every VSM resource of selected viewpoints
        Map<String, RGB> vsmColors = new LinkedHashMap<>();
        final IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        if (preferenceStore != null && preferenceStore.getBoolean(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name())) {
            List<UserColorsPalette> palettes = session.getSelectedViewpoints(true).stream().map(EObject::eContainer).map(org.eclipse.sirius.viewpoint.description.Group.class::cast)
                    .flatMap(group -> group.getUserColorsPalettes().stream()).collect(Collectors.toList());

            vsmColors = palettes.stream().flatMap(palette -> palette.getEntries().stream()).filter(UserFixedColor.class::isInstance).map(UserFixedColor.class::cast)
                    .collect(Collectors.toMap(c -> c.getName(), c -> new RGB(c.getRed(), c.getGreen(), c.getBlue()), (rgb1, rgb2) -> {
                        // in a case of collision of key we keep the first value
                        return rgb1;
                    }, () -> new LinkedHashMap<String, RGB>()));
        }

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

    /**
     * Collect colors defined in the VSM of the specified session.
     * 
     * @param session
     *            the session from which the VSMs defining colors can be retrieved.
     * @return the map of RGB colors and their name defined in VSM
     */
    public Map<String, RGB> collectVsmColors(Session session) {
        Map<String, RGB> vsmColors = new LinkedHashMap<>();
        final IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        if (preferenceStore != null) {
            vsmColors = session.getSelectedViewpoints(true).stream() //
                    .map(EObject::eContainer) //
                    .map(org.eclipse.sirius.viewpoint.description.Group.class::cast) //
                    .flatMap(group -> group.getUserColorsPalettes().stream()) //
                    .flatMap(palette -> palette.getEntries().stream())//
                    .filter(UserFixedColor.class::isInstance) //
                    .map(UserFixedColor.class::cast) //
                    .collect(Collectors.toMap(//
                            c -> c.getName(), //
                            c -> new RGB(c.getRed(), c.getGreen(), c.getBlue()), //
                            (rgb1, rgb2) -> { //
                                // in a case of collision of key we keep the first value
                                return rgb1;
                            }, //
                            () -> new LinkedHashMap<String, RGB>()));
        }
        return vsmColors;
    }

    /**
     * Converts an RGB color to a string.
     * 
     * @param rgb
     *            the color to convert.
     * @return the converted color as string.
     */
    public String rgbToString(RGB rgb) {
        String result = new String();
        if (rgb != null) {
            result = String.format("{%d, %d, %d}", rgb.red, rgb.green, rgb.blue); //$NON-NLS-1$
        }
        return result;
    }

    /**
     * Converts a string color to an RGB color. Accepted type strings: "{255,255,255}" or "{255, 255, 255}".
     * 
     * @param stringToConvert
     *            the color to convert
     * @return the converted color as RGB.
     */
    public RGB stringToRGB(String stringToConvert) {
        RGB color = null;
        if (stringToConvert != null && stringToConvert.matches("\\{(\\d{1,3})\\s?,\\s?(\\d{1,3})\\s?,\\s?(\\d{1,3})\\}")) { //$NON-NLS-1$
            String colorString = stringToConvert.replaceAll(" ", StringUtil.EMPTY_STRING); //$NON-NLS-1$
            String[] stringToParseArray = colorString.replaceAll("[{}]", StringUtil.EMPTY_STRING).split(RGB_VALUES_SEPARATOR); //$NON-NLS-1$
            try {
                color = new RGB(Integer.parseInt(stringToParseArray[0]), Integer.parseInt(stringToParseArray[1]), Integer.parseInt(stringToParseArray[2]));
            } catch (IllegalArgumentException e) {
                DiagramUIPlugin.getPlugin().log(String.format(Messages.ColorManager_logErrorParsingRGB, stringToConvert));
            }
        }
        return color;
    }

    /**
     * Returns a list of colors sorted by HSB (Hue, Saturation, Brightness). The specified collection isn't modified.
     * 
     * @param colorsToSort
     *            the collection of colors to sort.
     * @return returns a list of colors sorted by HSB.
     */
    public List<RGB> sortColors(final Collection<RGB> colorsToSort) {
        List<RGB> sortedList = Collections.EMPTY_LIST;
        if (colorsToSort != null) {
            sortedList = new ArrayList<>(colorsToSort);
            Collections.sort(sortedList, new Comparator<RGB>() {
                @Override
                public int compare(RGB o1, RGB o2) {
                    float[] hsb1 = o1.getHSB();
                    float[] hsb2 = o2.getHSB();
                    int hueComparison = Float.compare(hsb1[0], hsb2[0]);
                    int saturationComparison = Float.compare(hsb1[1], hsb2[1]);
                    int brightnessComparison = Float.compare(hsb1[2], hsb2[2]);
                    int result;
                    if (hueComparison != 0) {
                        result = hueComparison;
                    } else {
                        if (saturationComparison != 0) {
                            result = saturationComparison;
                        } else {
                            result = brightnessComparison;
                        }
                    }
                    return result;
                }
            });
        }
        return sortedList;
    }
}
