/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.style;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * Class to test the ColorManager and its cache.
 * 
 * @author mporhel
 */
public class ColorManagerTest extends SiriusDiagramTestCase {

    private static final RGB[] SORTED_RAINBOW = { new RGB(246, 139, 139), new RGB(239, 41, 41), new RGB(156, 12, 12), new RGB(224, 133, 3), new RGB(252, 175, 62), new RGB(253, 206, 137),
            new RGB(233, 185, 110), new RGB(154, 103, 23), new RGB(238, 201, 142), new RGB(255, 245, 181), new RGB(214, 197, 66), new RGB(252, 233, 79), new RGB(204, 242, 166), new RGB(138, 226, 52),
            new RGB(77, 137, 20), new RGB(194, 239, 255), new RGB(39, 76, 114), new RGB(114, 159, 207), new RGB(217, 196, 215), new RGB(114, 73, 110), new RGB(173, 127, 168) };

    private static final RGB[] RAINBOW = { new RGB(156, 12, 12), new RGB(239, 41, 41), new RGB(246, 139, 139), new RGB(154, 103, 23), new RGB(233, 185, 110), new RGB(238, 201, 142),
            new RGB(224, 133, 3), new RGB(252, 175, 62), new RGB(253, 206, 137), new RGB(214, 197, 66), new RGB(252, 233, 79), new RGB(255, 245, 181), new RGB(77, 137, 20), new RGB(138, 226, 52),
            new RGB(204, 242, 166), new RGB(39, 76, 114), new RGB(114, 159, 207), new RGB(194, 239, 255), new RGB(114, 73, 110), new RGB(173, 127, 168), new RGB(217, 196, 215) };

    private static final RGB[] VSM_COLORS = { new RGB(252, 82, 82), new RGB(47, 198, 255), new RGB(49, 217, 0), new RGB(96, 96, 96), new RGB(180, 180, 180), new RGB(255, 134, 13),
            new RGB(145, 56, 241), new RGB(60, 255, 250), new RGB(255, 66, 255), new RGB(255, 255, 0), new RGB(128, 64, 0), new RGB(0, 0, 0), new RGB(255, 255, 255), new RGB(247, 51, 85),
            new RGB(0, 202, 101) };

    private static final String PATH = "/data/unit/colors/github-152/";

    private static final String MODELER_RESOURCE_NAME = "github-152.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "github-152.ecore";

    private static final String SESSION_RESOURCE_NAME = "github-152.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, MODELER_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME),
                TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

    }

    /**
     * Check the get lighter color method effect.
     */
    public void testGetLighterColor() {
        RGBValues color = getTestColor();

        Color lighterColor = ColorManager.getDefault().getLighterColor(color);
        assertTrue("The lighter fonction should be checked.", lighterColor.getBlue() >= color.getBlue());
        assertTrue("The lighter fonction should be checked.", lighterColor.getGreen() >= color.getGreen());
        assertTrue("The lighter fonction should be checked.", lighterColor.getRed() >= color.getRed());
    }

    /**
     * Check that the cache will return the same org.eclipse.swt.graphics.Color for two RGBValues elements with the same
     * color properties.
     */
    public void testColorManagerCacheForTwoCallOnSameRGBValues() {
        RGBValues testColor1 = getTestColor();
        RGBValues testColor2 = getTestColor();

        Color lighterColor1 = ColorManager.getDefault().getLighterColor(testColor1);
        Color lighterColor1_bis = ColorManager.getDefault().getLighterColor(testColor1);
        Color lighterColor2 = ColorManager.getDefault().getLighterColor(testColor2);

        assertEquals("The lighter colors should have the same properties : the initial color was the same object.", lighterColor1, lighterColor1_bis);
        assertEquals("The lighter colors should have the same properties : the initial colors have the same r, g, b values.", lighterColor1, lighterColor2);

        // We must get the same instance and not just two equal objects. Do not
        // use asserEquals here, but assertTrue and ==.
        assertTrue("The color manager should return the same swt Color object, check the cache.", lighterColor1 == lighterColor1_bis);
        assertTrue("The color manager should return the same swt Color object, check the cache.", lighterColor1 == lighterColor2);
    }

    public void testRgbToString() {
        RGB red = new RGB(255, 0, 0);
        RGB green = new RGB(0, 255, 0);
        RGB blue = new RGB(0, 0, 255);
        assertEquals("{255, 0, 0}", ColorManager.getDefault().rgbToString(red));
        assertEquals("{0, 255, 0}", ColorManager.getDefault().rgbToString(green));
        assertEquals("{0, 0, 255}", ColorManager.getDefault().rgbToString(blue));
        assertEquals("", ColorManager.getDefault().rgbToString(null));
    }

    public void testStringToRGB() {
        RGB red = new RGB(255, 0, 0);
        RGB green = new RGB(0, 255, 0);
        RGB blue = new RGB(0, 0, 255);
        RGB white = new RGB(255, 255, 255);
        RGB black = new RGB(0, 0, 0);
        RGB gray = new RGB(50, 50, 50);
        assertEquals(red, ColorManager.getDefault().stringToRGB("{255, 0, 0}"));
        assertEquals(red, ColorManager.getDefault().stringToRGB("{255,0,0}"));
        assertEquals(green, ColorManager.getDefault().stringToRGB("{0, 255, 0}"));
        assertEquals(green, ColorManager.getDefault().stringToRGB("{0,255,0}"));
        assertEquals(blue, ColorManager.getDefault().stringToRGB("{0, 0, 255}"));
        assertEquals(blue, ColorManager.getDefault().stringToRGB("{0,0,255}"));
        assertEquals(white, ColorManager.getDefault().stringToRGB("{255, 255, 255}"));
        assertEquals(white, ColorManager.getDefault().stringToRGB("{255,255,255}"));
        assertEquals(black, ColorManager.getDefault().stringToRGB("{0, 0, 0}"));
        assertEquals(black, ColorManager.getDefault().stringToRGB("{0,0,0}"));
        assertEquals(gray, ColorManager.getDefault().stringToRGB("{50, 50, 50}"));
        assertEquals(gray, ColorManager.getDefault().stringToRGB("{50,50,50}"));

        assertNull(ColorManager.getDefault().stringToRGB(null));
        assertNull(ColorManager.getDefault().stringToRGB("0,0,0"));
        assertNull(ColorManager.getDefault().stringToRGB("{0,0,0,0}"));
        assertNull(ColorManager.getDefault().stringToRGB("{0,0}"));
        assertNull(ColorManager.getDefault().stringToRGB("{0}"));
        assertNull(ColorManager.getDefault().stringToRGB("{0,0,256}"));
        assertNull(ColorManager.getDefault().stringToRGB("{0,256,0}"));
        assertNull(ColorManager.getDefault().stringToRGB("{256,0,0}"));
        assertNull(ColorManager.getDefault().stringToRGB("{-1,0,0}"));
        assertNull(ColorManager.getDefault().stringToRGB("{0,-1,0}"));
        assertNull(ColorManager.getDefault().stringToRGB("{0,0,-1}"));
    }

    public void testSortColors() {
        assertTrue(ColorManager.getDefault().sortColors(null).isEmpty());
        assertTrue(ColorManager.getDefault().sortColors(new ArrayList<>()).isEmpty());
        RGB black = new RGB(0, 0, 0);
        List<RGB> sortedColor = ColorManager.getDefault().sortColors(List.of(black));
        assertEquals(1, sortedColor.size());

        List<RGB> rainbowColors = Arrays.asList(RAINBOW);
        assertArrayEquals(SORTED_RAINBOW, ColorManager.getDefault().sortColors(rainbowColors).toArray());
    }

    public void testCollectVsmColors() {
        Map<String, RGB> collectVsmColors = ColorManager.getDefault().collectVsmColors(session);
        assertArrayEquals(VSM_COLORS, collectVsmColors.values().toArray());
    }

    private RGBValues getTestColor() {
        RGBValues rgbValues = RGBValues.create(33, 33, 33);
        return rgbValues;
    }
}
