/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.style;

import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;

import junit.framework.TestCase;

/**
 * Class to test the ColorManager and its cache.
 * 
 * @author mporhel
 */
public class ColorManagerTest extends TestCase {

    /**
     * Check the get lighter color method effect.
     */
    public void testColorManager() {
        RGBValues color = getTestColor();

        Color lighterColor = ColorManager.getDefault().getLighterColor(color);
        assertTrue("The lighter fonction should be checked.", lighterColor.getBlue() >= color.getBlue());
        assertTrue("The lighter fonction should be checked.", lighterColor.getGreen() >= color.getGreen());
        assertTrue("The lighter fonction should be checked.", lighterColor.getRed() >= color.getRed());
    }

    /**
     * Check that the cache will return the same org.eclipse.swt.graphics.Color
     * for two RGBValues elements with the same color properties.
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

    private RGBValues getTestColor() {
        RGBValues rgbValues = RGBValues.create(33, 33, 33);
        return rgbValues;
    }
}
