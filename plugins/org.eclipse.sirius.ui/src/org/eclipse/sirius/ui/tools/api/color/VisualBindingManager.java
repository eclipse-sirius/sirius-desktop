/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.color;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.ext.swt.SWTResourceLRUCache;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Objects;

/**
 * Take care of computing colors, font or size from integer values. Avoid memory
 * leaks by properly caching and disposing the corresponding SWT resources.
 * 
 * @author cbrun, pcdavid
 */
public class VisualBindingManager {
    private static final String DEFAULT_FONT_NAME = "ARIAL"; //$NON-NLS-1$

    private static VisualBindingManager defaultInstance;

    private final Map<String, RGB> systemPalette;

    private Map<String, Color> colorCache;

    private Map<FontStyleDescriptor, Font> fontCache;

    private Map<Integer, Font> intToFontCache;

    private Map<PatternDescriptor, Pattern> patternCache;

    /**
     * Create a new {@link VisualBindingManager}.
     */
    public VisualBindingManager() {
        final HashMap<String, RGB> palette = new HashMap<String, RGB>(SystemColors.values().length);
        addStandardPaletteColors(palette);
        systemPalette = palette;
    }

    /**
     * return the system palette.
     * 
     * @return the system palette.
     * @since 0.9.0
     */
    public Map<String, RGB> getSystemPalette() {
        return systemPalette;
    }

    /**
     * Return the singleton instance.
     * 
     * @return a default instance
     */
    public static VisualBindingManager getDefault() {
        if (defaultInstance == null) {
            defaultInstance = new VisualBindingManager();
        }
        return defaultInstance;
    }

    /**
     * Initialize the Visual binding manager with the give size.
     * 
     * @param colorCacheSize
     *            the size for the color cache
     * @param fontCacheSize
     *            the size for the font cache
     */
    public void init(final int colorCacheSize, final int fontCacheSize) {
        colorCache = new SWTResourceLRUCache<String, Color>(colorCacheSize, colorCacheSize);
        fontCache = new SWTResourceLRUCache<FontStyleDescriptor, Font>(fontCacheSize, fontCacheSize);
        intToFontCache = new SWTResourceLRUCache<Integer, Font>(fontCacheSize, fontCacheSize);
        patternCache = new SWTResourceLRUCache<PatternDescriptor, Pattern>(colorCacheSize, colorCacheSize);
    }

    /**
     * Dispose all the caches.
     */
    public void dispose() {
        systemPalette.clear();
        VisualBindingManager.disposeResources(colorCache.values());
        colorCache.clear();
        VisualBindingManager.disposeResources(fontCache.values());
        fontCache.clear();
        VisualBindingManager.disposeResources(intToFontCache.values());
        intToFontCache.clear();
        VisualBindingManager.disposeResources(patternCache.values());
        patternCache.clear();
    }

    private static <T extends Resource> void disposeResources(final Collection<T> resources) {
        final Iterator<T> it = resources.iterator();
        while (it.hasNext()) {
            final Resource resource = it.next();
            resource.dispose();
        }
    }

    private void addStandardPaletteColors(final Map<String, RGB> palette) {
        // base colors
        palette.put(SystemColors.WHITE_LITERAL.getName(), new RGB(255, 255, 255));
        palette.put(SystemColors.BLACK_LITERAL.getName(), new RGB(0, 0, 0));
        palette.put(SystemColors.BLUE_LITERAL.getName(), new RGB(114, 159, 207));
        palette.put(SystemColors.CHOCOLATE_LITERAL.getName(), new RGB(233, 185, 110));
        palette.put(SystemColors.GRAY_LITERAL.getName(), new RGB(136, 136, 136));
        palette.put(SystemColors.GREEN_LITERAL.getName(), new RGB(138, 226, 52));
        palette.put(SystemColors.ORANGE_LITERAL.getName(), new RGB(252, 175, 62));
        palette.put(SystemColors.PURPLE_LITERAL.getName(), new RGB(173, 127, 168));
        palette.put(SystemColors.RED_LITERAL.getName(), new RGB(239, 41, 41));
        palette.put(SystemColors.YELLOW_LITERAL.getName(), new RGB(252, 233, 79));
        // light variants of the base colors (except black & white)
        palette.put(SystemColors.LIGHT_BLUE_LITERAL.getName(), new RGB(194, 239, 255));
        palette.put(SystemColors.LIGHT_CHOCOLATE_LITERAL.getName(), new RGB(238, 201, 142));
        palette.put(SystemColors.LIGHT_GRAY_LITERAL.getName(), new RGB(209, 209, 209));
        palette.put(SystemColors.LIGHT_GREEN_LITERAL.getName(), new RGB(204, 242, 166));
        palette.put(SystemColors.LIGHT_ORANGE_LITERAL.getName(), new RGB(253, 206, 137));
        palette.put(SystemColors.LIGHT_PURPLE_LITERAL.getName(), new RGB(217, 196, 215));
        palette.put(SystemColors.LIGHT_RED_LITERAL.getName(), new RGB(246, 139, 139));
        palette.put(SystemColors.LIGHT_YELLOW_LITERAL.getName(), new RGB(255, 245, 181));
        // dark variants of the base colors (except black & white)
        palette.put(SystemColors.DARK_BLUE_LITERAL.getName(), new RGB(39, 76, 114));
        palette.put(SystemColors.DARK_CHOCOLATE_LITERAL.getName(), new RGB(154, 103, 23));
        palette.put(SystemColors.DARK_GRAY_LITERAL.getName(), new RGB(69, 69, 69));
        palette.put(SystemColors.DARK_GREEN_LITERAL.getName(), new RGB(77, 137, 20));
        palette.put(SystemColors.DARK_ORANGE_LITERAL.getName(), new RGB(224, 133, 3));
        palette.put(SystemColors.DARK_PURPLE_LITERAL.getName(), new RGB(114, 73, 110));
        palette.put(SystemColors.DARK_RED_LITERAL.getName(), new RGB(156, 12, 12));
        palette.put(SystemColors.DARK_YELLOW_LITERAL.getName(), new RGB(214, 197, 66));
    }

    private RGB getDescriptorFromName(final String name) {
        if (systemPalette.containsKey(name)) {
            return systemPalette.get(name);
        }
        return systemPalette.get(DescriptionPackage.eINSTANCE.getSystemColors().getDefaultValue());
    }

    /**
     * Retrieves the RGBValues for a given color from the system palette.
     * 
     * @param defaultColor
     *            the default color
     * @return the RGB values for that color.
     */
    public RGBValues getRGBValuesFor(final SystemColors defaultColor) {
        final RGB rgb = systemPalette.get(defaultColor.getName());
        return createRGBvalues(rgb);
    }

    /**
     * This method return a Color object from a value and from a max value. The
     * color starts green for value 0 and finish Red for value max.
     * 
     * @param max
     *            maximum value.
     * @param value
     *            number corresponding to the color
     * @return the corresponding color instance.
     */
    public Color getColorFromValue(final int value, final int max) {
        return getColorFromCache(getColorCodeValue(value, max, 0));
    }

    /**
     * Use the name, if the color is known then it is returned, otherwise black
     * color is returned.
     * 
     * @param name
     *            : name of a color, in English
     * @return : the asked color or black if it is not found.
     */
    public Color getColorFromName(final String name) {
        return getColorFromCache(getDescriptorFromName(name));
    }

    /**
     * Retrieve a proper SWT {@link Color} from an {@link RGB} value, using the
     * cache as appropriate.
     * 
     * @param rgb
     *            the RGB values of the color to retrieve
     * @return a proper SWT {@link Color} with the specified RGB values.
     * @since 0.9.0
     */
    public Color getColorFromRGB(final RGB rgb) {
        return getColorFromCache(rgb);
    }

    /**
     * Retrieve a proper SWT {@link Color} from an {@link RGBValues} element,
     * using the cache as appropriate.
     * 
     * @param values
     *            the RGBValues of the color to retrieve
     * @return a proper SWT {@link Color} with the specified RGB values.
     * @since 0.9.0
     */
    public Color getColorFromRGBValues(final RGBValues values) {
        if (values != null) {
            return getColorFromRGBValue(values.getRed(), values.getGreen(), values.getBlue());
        } else {
            // Ideally get the default color from the preferences page
            // (IPreferenceConstants.PREF_LINE_COLOR), but no editor (so
            // preferences) from this plugin
            return getColorFromName(SystemColors.GRAY_LITERAL.getName());
        }
    }

    /**
     * Retrieve a proper SWT {@link Color} from an {@link RGBValues} element,
     * using the cache as appropriate.
     * 
     * @param values
     *            the RGBValues of the color to retrieve
     * @return a proper SWT {@link Color} with the specified RGB values.
     */
    public Color getLabelColorFromRGBValues(final RGBValues values) {
        if (values != null) {
            return getColorFromRGBValue(values.getRed(), values.getGreen(), values.getBlue());
        } else {
            // Ideally get the default color from the preferences page
            // (IPreferenceConstants.PREF_FONT_COLOR), but no editor (so
            // preferences) from this plugin
            return getColorFromName("black"); //$NON-NLS-1$
        }
    }

    private Color getColorFromCache(final RGB rgb) {
        final String key = rgb.toString();
        if (!colorCache.containsKey(key)) {
            colorCache.put(key, createColor(rgb));
        }
        return colorCache.get(key);
    }

    private Color createColor(final RGB rgb) {
        return new Color(Display.getDefault(), rgb);
    }

    /**
     * Creates an RGBValues element from an SWT RGB color.
     * 
     * @param rgb
     *            the RGB color from SWT.
     * @return the equivalent RGBValues model element.
     */
    public RGBValues createRGBvalues(RGB rgb) {
        final RGBValues result = RGBValues.create(rgb.red, rgb.green, rgb.blue);
        return result;
    }

    /**
     * conversion from RGBColor to DefaultColor.
     * 
     * @param color
     *            The original color
     * @return The retrieved default color
     */
    public SystemColors getDColorFromRGBColor(final FixedColor color) {
        return findClosestSystemColor(color);
    }

    /**
     * Return a color from RGB values.
     * 
     * @param red
     *            current red value.
     * @param green
     *            current green value.
     * @param blue
     *            current blue value.
     * @return a cached {@link Color} instance of the wanted color.
     * @since 0.9.0
     */
    public Color getColorFromRGBValue(final int red, final int green, final int blue) {
        return getColorFromCache(new RGB(VisualBindingManager.clamp(red, 0, 255), VisualBindingManager.clamp(green, 0, 255), VisualBindingManager.clamp(blue, 0, 255)));
    }

    /**
     * Clamps a value into a specified interval.
     * 
     * @param value
     *            the value
     * @param min
     *            the minimum possible value (inclusive).
     * @param max
     *            the maximum possible value (inclusive). Must be >= min.
     * @return the integer closes to <code>value</code> which is inside the
     *         inclusive interval <code>[min, max]</code>.
     * @since 0.9.0
     */
    public static int clamp(final int value, final int min, final int max) {
        return EnvironmentSystemColorFactory.clamp(value, min, max);
    }

    /**
     * This method return a Color object from a value. The color starts green
     * for value 0 and finish Red for 100
     * 
     * @param value
     *            number corresponding to the color
     * @return the corresponding color instance.
     */
    public Color getColorFromValue(final int value) {
        return getColorFromValue(value, 100);
    }

    /**
     * This method return an int value from a value and a max one.
     * 
     * @param value
     *            value.
     * @param max
     *            max value.
     * @return the line width.
     */
    public int getLineWidthFromValue(final int value, final int max) {
        return (int) ((double) value * 10 / max);
    }

    /**
     * This method return an int value from a given value compared with 10.
     * 
     * @param value
     *            the value.
     * @return the line width.
     */
    public int getLineWidthFromValue(final int value) {
        return getLineWidthFromValue(value, 10);
    }

    /**
     * Return a color from a value on a scale.
     * 
     * @param pvalue
     *            current value on the scale.
     * @param pmax
     *            maximum value on the scale.
     * @param pmin
     *            minimum value on the scale.
     * @return a cached {@link Color} instance from green to red using the
     *         value.
     */
    public RGB getColorCodeValue(final int pvalue, final int pmax, final int pmin) {
        final int min = pmin;
        int max = pmax;
        int value = pvalue;
        final Color red = this.getColorFromName("red"); //$NON-NLS-1$
        final Color green = this.getColorFromName("green"); //$NON-NLS-1$

        if (max <= min) {
            max = min + 1;
        }
        if (value > max) {
            value = max;
        }
        if (value < min) {
            value = min;
        }

        final float scale = ((float) value - min) / (max - min);
        final int valRed = (int) (green.getRed() + ((red.getRed() - green.getRed()) * scale));
        final int valGreen = (int) (green.getGreen() + ((red.getGreen() - green.getGreen()) * scale));
        final int valBlue = (int) (green.getBlue() + ((red.getBlue() - green.getBlue()) * scale));
        return new RGB(VisualBindingManager.clamp(valRed, 0, 255), VisualBindingManager.clamp(valGreen, 0, 255), VisualBindingManager.clamp(valBlue, 0, 255));
    }

    /**
     * Return a color from a value on a scale.
     * 
     * @param value
     *            current value on the scale.
     * @param max
     *            maximum value on the scale.
     * @param min
     *            minimum value on the scale.
     * @return a cached {@link Color} instance from green to red using the
     *         value.
     */
    public Color getColorFromValue(final int value, final int max, final int min) {
        return getColorFromCache(getColorCodeValue(value, max, min));
    }

    /**
     * Finds the closest color default color (from the standard palette) from an
     * arbitrary RGB color.
     * 
     * @param color
     *            the color to match
     * @return the entry in the default palette which is closest (in RGB
     *         color-space) to the specified color.
     * @since 0.9.0
     */
    public SystemColors findClosestSystemColor(final FixedColor color) {
        int bestDistanceSoFar = Integer.MAX_VALUE;
        String bestColorNameSoFar = null;
        for (Entry<String, RGB> defaultColorName : systemPalette.entrySet()) {
            final int dist = squareDistance(defaultColorName.getValue(), color);
            if (dist < bestDistanceSoFar) {
                bestColorNameSoFar = defaultColorName.getKey();
                bestDistanceSoFar = dist;
                if (dist == 0) {
                    break;
                }
            }
        }
        return SystemColors.getByName(bestColorNameSoFar);
    }

    /**
     * Finds the closest color default color (from the standard palette) from an
     * arbitrary RGB color.
     * 
     * @param values
     *            the color to match
     * @return the entry in the default palette which is closest (in RGB
     *         color-space) to the specified color.
     * @since 0.9.0
     */
    public SystemColors findClosestStandardColor(final RGBValues values) {
        final SystemColor color = DescriptionFactory.eINSTANCE.createSystemColor();
        color.setName("<anonymous>"); //$NON-NLS-1$
        color.setRed(values.getRed());
        color.setGreen(values.getGreen());
        color.setBlue(values.getBlue());
        return findClosestSystemColor(color);
    }

    /**
     * Computes the square of the distance between two colors in the RGB
     * color-space.
     */
    private int squareDistance(final RGB rgb, final FixedColor color) {
        return squareDistance(rgb.red, rgb.green, rgb.blue, color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Computes the square of the Euclidean distance between two 3D points.
     */
    private int squareDistance(final int x1, final int y1, final int z1, final int x2, final int y2, final int z2) {
        final int dx = x2 - x1;
        final int dy = y2 - y1;
        final int dz = z2 - z1;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Silly class...In C I would have use a Struct :P
     * 
     * @author mchauvin, pcdavid
     */
    private static final class PatternDescriptor {
        final int x;

        final int y;

        final int w;

        final int h;

        final Color backgroundColor;

        final Color foregroundColor;

        /**
         * Constructor.
         * 
         * @param x
         *            the x coordinate
         * @param y
         *            the x coordinate
         * @param w
         *            the width
         * @param h
         *            the height
         * @param backgroundColor
         *            the background color
         * @param foregrounColor
         *            the forground color
         */
        public PatternDescriptor(final int x, final int y, final int w, final int h, final Color backgroundColor, final Color foregrounColor) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.backgroundColor = backgroundColor;
            this.foregroundColor = foregrounColor;
        }

        @Override
        public boolean equals(final Object obj) {
            final boolean result;
            if (this == obj) {
                result = true;
            } else if (obj instanceof PatternDescriptor) {
                final PatternDescriptor that = (PatternDescriptor) obj;
                final boolean sameCoordinates = this.x == that.x && this.y == that.y && this.h == that.h && this.w == that.w;
                result = sameCoordinates && Objects.equal(this.backgroundColor, that.backgroundColor) && Objects.equal(this.foregroundColor, that.foregroundColor);
            } else {
                result = false;
            }
            return result;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.x, this.y, this.w, this.h, this.foregroundColor, this.backgroundColor);
        }

        public Pattern createPattern() {
            return new Pattern(null, this.x, this.y, this.w, this.h, this.backgroundColor, this.foregroundColor);
        }
    }

    private Pattern getPatternFromCache(final PatternDescriptor desc) {
        if (!patternCache.containsKey(desc)) {
            patternCache.put(desc, desc.createPattern());
        }
        return patternCache.get(desc);
    }

    /**
     * Get a pattern from value.
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the x coordinate
     * @param w
     *            the width
     * @param h
     *            the height
     * @param backgroundColor
     *            the background color
     * @param foregrounColor
     *            the foreground color
     * @return the created or cached pattern, which does not need to be
     *         disposed.
     */
    public Pattern getPatternFromValue(final int x, final int y, final int w, final int h, final Color backgroundColor, final Color foregrounColor) {
        final PatternDescriptor desc = new PatternDescriptor(x, y, w, h, backgroundColor, foregrounColor);
        return getPatternFromCache(desc);
    }

    /**
     * This method helps avoiding memory leaks by keeping track of the already
     * built fonts.
     * 
     * @param size
     *            : size of the font
     * @return the default font with the given size.
     */
    public Font getFontFromValue(final int size) {
        final int rangedSize = Math.max(size, 1);
        if (!intToFontCache.containsKey(Integer.valueOf(rangedSize))) {
            intToFontCache.put(Integer.valueOf(rangedSize), new Font(Display.getDefault(), DEFAULT_FONT_NAME, rangedSize, SWT.NORMAL));
        }
        return intToFontCache.get(Integer.valueOf(rangedSize));
    }

    /**
     * Return a font from a label style.
     * 
     * @param style
     *            current {@link BasicLabelStyle}.
     * @return a Font from a {@link BasicLabelStyle}.
     */
    public Font getFontFromLabelStyle(final BasicLabelStyle style) {
        return getFontFromLabelFormatAndSize(style.getLabelFormat(), style.getLabelSize(), DEFAULT_FONT_NAME);
    }

    /**
     * Return a font from a label style and a font name..
     * 
     * @param style
     *            current {@link BasicLabelStyle}.
     * @param fontName
     *            the name of font.
     * @return a Font from a {@link BasicLabelStyle}.
     */
    public Font getFontFromLabelStyle(final BasicLabelStyle style, final String fontName) {
        if (fontName == null) {
            return getFontFromLabelStyle(style);
        }
        return getFontFromLabelFormatAndSize(style.getLabelFormat(), style.getLabelSize(), fontName);
    }

    /**
     * Retrieves if a label is underlined from a label style.
     * 
     * @param style
     *            current {@link BasicLabelStyle}.
     * @return true if label is underlined otherwise false.
     */
    public boolean isUnderlineFromLabelStyle(final BasicLabelStyle style) {
        List<FontFormat> labelFormat = style.getLabelFormat();
        for (FontFormat fontFormat : labelFormat) {
            if (FontFormat.UNDERLINE_LITERAL == fontFormat) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves if a label is strike through from a label style.
     * 
     * @param style
     *            current {@link BasicLabelStyle}.
     * @return true if label is strike through otherwise false.
     */
    public boolean isStrikeThroughFromLabelStyle(final BasicLabelStyle style) {
        List<FontFormat> labelFormat = style.getLabelFormat();
        for (FontFormat fontFormat : labelFormat) {
            if (FontFormat.STRIKE_THROUGH_LITERAL == fontFormat) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return a font from a label format and a label size.
     * 
     * @param labelFormat
     *            current {@link FontFormat}.
     * @param labelSize
     *            current size
     * @return a Font from a {@link FontFormat} and a size.
     */
    public Font getFontFromLabelFormatAndSize(final List<FontFormat> labelFormat, final int labelSize) {
        return getFontFromLabelFormatAndSize(labelFormat, labelSize, DEFAULT_FONT_NAME);
    }

    /**
     * Return a font from a label format and a label size.
     * 
     * @param labelFormat
     *            current {@link FontFormat}.
     * @param labelSize
     *            current size
     * @param fontName
     *            the name of the font.
     * @return a Font from a {@link FontFormat} and a size.
     */
    public Font getFontFromLabelFormatAndSize(final List<FontFormat> labelFormat, final int labelSize, final String fontName) {
        final int rangedSize = Math.max(labelSize, 1);
        final FontStyleDescriptor desc = new FontStyleDescriptor(labelFormat, rangedSize, fontName);
        if (!fontCache.containsKey(desc)) {
            int format = SWT.NORMAL;

            for (FontFormat fontFormat : labelFormat) {
                if (FontFormat.BOLD_LITERAL.equals(fontFormat)) {
                    format = format | SWT.BOLD;
                }
                if (FontFormat.ITALIC_LITERAL.equals(fontFormat)) {
                    format = format | SWT.ITALIC;
                }
            }

            fontCache.put(desc, new Font(Display.getDefault(), fontName, rangedSize, format));
        }
        return fontCache.get(desc);
    }

    /**
     * A descriptor for font: all the informations identifying a font without
     * allocating an SWT resource.
     * 
     * @author cbrun, pcdavid
     */
    private static final class FontStyleDescriptor {
        final String name;

        final Set<FontFormat> format;

        final int size;

        public FontStyleDescriptor(final List<FontFormat> format, final int size, final String name) {
            this.format = format.isEmpty() ? null : EnumSet.copyOf(format);
            this.size = size;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, format, size);
        }

        @Override
        public boolean equals(final Object obj) {
            boolean result;
            if (this == obj) {
                result = true;
            } else if (obj instanceof FontStyleDescriptor) {
                final FontStyleDescriptor that = (FontStyleDescriptor) obj;
                result = Objects.equal(this.name, that.name) && Objects.equal(this.format, that.format) && this.size == that.size;
            } else {
                result = false;
            }
            return result;
        }
    }

    /**
     * return the color description corresponding to the color name.
     * 
     * @param name
     *            the name of the color.
     * @return return the color description corresponding to the color name.
     */
    public SystemColor getSystemColorDescription(final String name) {
        final String uri = SiriusUtil.VIEWPOINT_ENVIRONMENT_RESOURCE_URI + "#/0/@systemColors/@entries[name='" + name + "']"; //$NON-NLS-1$ //$NON-NLS-2$
        final EObject color = EcoreUtil.create(DescriptionPackage.eINSTANCE.getSystemColor());
        final URI colorURI = URI.createURI(uri);
        ((InternalEObject) color).eSetProxyURI(colorURI);
        return (SystemColor) color;
    }

}
