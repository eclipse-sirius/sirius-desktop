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
package org.eclipse.sirius.business.api.color;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;

/**
 * Base classe for colors updating.
 * 
 * @author cbrun
 * 
 */
public class AbstractColorUpdater {

    /** Default red value. */
    public static final int DEFAULT_RED_VALUE = RGBValues.DEFAULT_GRAY.getRed();

    /** Default green value. */
    public static final int DEFAULT_GREEN_VALUE = RGBValues.DEFAULT_GRAY.getGreen();

    /** Default blue value. */
    public static final int DEFAULT_BLUE_VALUE = RGBValues.DEFAULT_GRAY.getBlue();

    /**
     * Return the computed rgbvalues from an interpolated color description.
     * 
     * @param context
     *            current EObject usde to evaluate expressions.
     * @param descValue
     *            interpolated color definition.
     * @return the computed rgbvalues from an interpolated color description.
     */
    protected RGBValues getRGBValueForInterpolatedColor(final EObject context, final InterpolatedColor descValue) {
        if (context != null) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(context);
            if (interpreter != null) {
                return new RGBValuesProvider().getRGBValues(descValue, context, interpreter);
            }
        }
        return ViewpointFactory.eINSTANCE.createRGBValues();
    }

    /**
     * Return the computed rgbvalues from an computed color description.
     * 
     * @param context
     *            current EObject usde to evaluate expressions.
     * @param descValue
     *            interpolated color definition.
     * @return the computed rgbvalues from an computed color description.
     */
    protected RGBValues getRGBValueForComputedColor(final EObject context, final ComputedColor descValue) {
        if (context != null) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(context);
            if (interpreter != null) {
                return new RGBValuesProvider().getRGBValues(descValue, context, interpreter);
            }
        }
        return ViewpointFactory.eINSTANCE.createRGBValues();
    }

    /**
     * Return the {@link RGBValues} associated with a fixed color description.
     * 
     * @param descValue
     *            the color defintion.
     * @return the rgb values.
     */
    protected RGBValues getRGBValueForFixedColor(final FixedColor descValue) {
        RGBValues newValues;
        newValues = new RGBValuesProvider().getRGBValues(descValue);
        return newValues;
    }

    /**
     * Return a default rgbvalue.
     * 
     * @return a default rgbvalue.
     */
    protected RGBValues createDefaultRGBValue() {
        return RGBValues.DEFAULT_GRAY;
    }

    /**
     * Return the rgbvalues corresponding to the current color description.
     * 
     * @param context
     *            current EObject.
     * @param descValue
     *            color description.
     * @return the rgbvalues corresponding to the current color description.
     */
    public RGBValues getRGBValuesFromColorDescription(final EObject context, final ColorDescription descValue) {
        RGBValues newValues;
        if (descValue instanceof FixedColor) {
            newValues = getRGBValueForFixedColor((FixedColor) descValue);
        } else if (descValue instanceof InterpolatedColor) {
            newValues = getRGBValueForInterpolatedColor(context, (InterpolatedColor) descValue);
        } else if (descValue instanceof ComputedColor) {
            newValues = getRGBValueForComputedColor(context, (ComputedColor) descValue);
        } else {
            newValues = createDefaultRGBValue();
        }
        return newValues;
    }

    /**
     * Return true if both values are equals.
     * 
     * @param originalValue
     *            the first value.
     * @param newValues
     *            the second value.
     * @return true if both values are equals
     */
    public static boolean areEquals(final RGBValues originalValue, final RGBValues newValues) {
        if (originalValue != null && newValues != null) {
            return originalValue.getBlue() == newValues.getBlue() && originalValue.getRed() == newValues.getRed() && originalValue.getGreen() == newValues.getGreen();
        }
        return false;
    }

}
