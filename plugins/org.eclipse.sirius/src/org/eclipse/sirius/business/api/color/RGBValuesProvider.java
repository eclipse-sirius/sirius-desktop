/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Class responsible to provide RGBValues from the color definitions.
 * 
 * @author cbrun, smonnier
 * 
 */
public class RGBValuesProvider {

    /**
     * Predicate that validate a LevelColor.
     */
    private Predicate<ColorStep> definedLevelColors = new Predicate<ColorStep>() {

        public boolean apply(ColorStep input) {
            return input.getAssociatedColor() != null && input.getAssociatedValue() != null && !"".equals(input.getAssociatedValue());
        }

    };

    /**
     * return the rgb values from the color definition.
     * 
     * @param object
     *            the color definition.
     * @return the rgb values from the color definition.
     */
    public RGBValues getRGBValues(final FixedColor object) {
        final RGBValues rgb = ViewpointFactory.eINSTANCE.createRGBValues();
        if (object != null) {
            rgb.setBlue(object.getBlue());
            rgb.setGreen(object.getGreen());
            rgb.setRed(object.getRed());
        }
        return rgb;
    }

    /**
     * return the rgb values from the color definition.
     * 
     * @param object
     *            the color definition.
     * @param evaluationContext
     *            the current EObject used to evaluate the expressions.
     * @param interpreter
     *            the interpreter to evaluate the expressions.
     * @return the rgb values from the color definition.
     */
    public RGBValues getRGBValues(final ComputedColor object, final EObject evaluationContext, final IInterpreter interpreter) {
        Integer red = RGBValuesProvider.getIntFromAcceleoExpression(interpreter, evaluationContext, object, DescriptionPackage.eINSTANCE.getComputedColor_Red());
        if (red == null) {
            red = 0;
        }
        Integer green = RGBValuesProvider.getIntFromAcceleoExpression(interpreter, evaluationContext, object, DescriptionPackage.eINSTANCE.getComputedColor_Green());
        if (green == null) {
            green = 0;
        }
        Integer blue = RGBValuesProvider.getIntFromAcceleoExpression(interpreter, evaluationContext, object, DescriptionPackage.eINSTANCE.getComputedColor_Blue());
        if (blue == null) {
            blue = 0;
        }

        final RGBValues rgb = ViewpointFactory.eINSTANCE.createRGBValues();
        rgb.setBlue(blue);
        rgb.setGreen(green);
        rgb.setRed(red);
        return rgb;
    }

    /**
     * return the rgb values from the color definition.
     * 
     * @param object
     *            the color definition.
     * @param evaluationContext
     *            the current EObject used to evaluate the expressions.
     * @param interpreter
     *            the interpreter to evaluate the expressions.
     * @return the rgb values from the color definition.
     */
    public RGBValues getRGBValues(final InterpolatedColor object, final EObject evaluationContext, final IInterpreter interpreter) {
        if (object.getColorSteps().isEmpty() || !Iterables.any(object.getColorSteps(), definedLevelColors)) {
            return getDefaultRGBValues(object, evaluationContext, interpreter);
        } else {
            return getInterpolatedRGBValues(object, evaluationContext, interpreter);
        }
    }

    private RGBValues getInterpolatedRGBValues(final InterpolatedColor object, final EObject evaluationContext, final IInterpreter interpreter) {
        final RGBValues rgb = ViewpointFactory.eINSTANCE.createRGBValues();
        rgb.setBlue(0);
        rgb.setGreen(0);
        rgb.setRed(0);

        Integer closestLowerBound = Integer.MIN_VALUE;
        FixedColor closestLowerFixedColor = null;
        Integer minBound = Integer.MAX_VALUE;
        FixedColor minFixedColor = null;
        Integer closestUpperBound = Integer.MAX_VALUE;
        FixedColor closestUpperFixedColor = null;
        Integer maxBound = Integer.MIN_VALUE;
        FixedColor maxFixedColor = null;

        Integer value = RGBValuesProvider.getIntFromAcceleoExpression(interpreter, evaluationContext, object, DescriptionPackage.eINSTANCE.getInterpolatedColor_ColorValueComputationExpression());

        if (value == null) {
            return getDefaultRGBValues(object, evaluationContext, interpreter);
        }

        // Investigate colors to find the closest lower and upper LevelColor
        // Also find the min and max color if the value is out of LevelColors
        // range
        for (ColorStep colorStep : Iterables.filter(object.getColorSteps(), definedLevelColors)) {
            Integer associatedValue = RGBValuesProvider.getIntFromAcceleoExpression(interpreter, evaluationContext, colorStep, DescriptionPackage.eINSTANCE.getColorStep_AssociatedValue());
            if (associatedValue != null && associatedValue > closestLowerBound && associatedValue <= value) {
                closestLowerBound = associatedValue;
                closestLowerFixedColor = colorStep.getAssociatedColor();
            }
            if (associatedValue != null && associatedValue < closestUpperBound && associatedValue >= value) {
                closestUpperBound = associatedValue;
                closestUpperFixedColor = colorStep.getAssociatedColor();
            }
            if (associatedValue != null && associatedValue < minBound) {
                minBound = associatedValue;
                minFixedColor = colorStep.getAssociatedColor();
            }
            if (associatedValue != null && associatedValue > maxBound) {
                maxBound = associatedValue;
                maxFixedColor = colorStep.getAssociatedColor();
            }
        }

        if (closestLowerFixedColor == null) {
            // No closest min color found because value is out of range. The min
            // color is set.
            closestLowerBound = minBound;
            closestLowerFixedColor = minFixedColor;
        }
        if (closestUpperFixedColor == null) {
            // No closest max color found because value is out of range. The max
            // color is set.
            closestUpperBound = maxBound;
            closestUpperFixedColor = maxFixedColor;
        }

        closestLowerBound = closestLowerBound == null ? Integer.valueOf(0) : closestLowerBound;
        closestUpperBound = closestUpperBound == null ? Integer.valueOf(1) : closestUpperBound;

        if (closestUpperBound.intValue() <= closestLowerBound.intValue()) {
            closestUpperBound = Integer.valueOf(closestLowerBound.intValue() + 1);
        }
        if (value.intValue() < closestLowerBound.intValue()) {
            value = Integer.valueOf(closestLowerBound.intValue());
        }
        if (value.intValue() > closestUpperBound.intValue()) {
            value = Integer.valueOf(closestUpperBound.intValue());
        }

        if (closestUpperBound <= closestLowerBound) {
            closestUpperBound = closestLowerBound + 1;
        }
        if (value > closestUpperBound) {
            value = closestUpperBound;
        }
        if (value < closestLowerBound) {
            value = closestLowerBound;
        }

        final float scale = ((float) value - closestLowerBound) / (closestUpperBound - closestLowerBound);
        final int valRed = (int) (closestLowerFixedColor.getRed() + ((closestUpperFixedColor.getRed() - closestLowerFixedColor.getRed()) * scale));
        final int valGreen = (int) (closestLowerFixedColor.getGreen() + ((closestUpperFixedColor.getGreen() - closestLowerFixedColor.getGreen()) * scale));
        final int valBlue = (int) (closestLowerFixedColor.getBlue() + ((closestUpperFixedColor.getBlue() - closestLowerFixedColor.getBlue()) * scale));
        rgb.setRed(EnvironmentSystemColorFactory.clamp(valRed, 0, 255));
        rgb.setGreen(EnvironmentSystemColorFactory.clamp(valGreen, 0, 255));
        rgb.setBlue(EnvironmentSystemColorFactory.clamp(valBlue, 0, 255));
        return rgb;

    }

    private RGBValues getDefaultRGBValues(final InterpolatedColor object, final EObject evaluationContext, final IInterpreter interpreter) {
        final RGBValues rgb = ViewpointFactory.eINSTANCE.createRGBValues();
        rgb.setBlue(0);
        rgb.setGreen(0);
        rgb.setRed(0);

        Integer min = RGBValuesProvider.getIntFromAcceleoExpression(interpreter, evaluationContext, object, DescriptionPackage.eINSTANCE.getInterpolatedColor_MinValueComputationExpression());
        Integer max = RGBValuesProvider.getIntFromAcceleoExpression(interpreter, evaluationContext, object, DescriptionPackage.eINSTANCE.getInterpolatedColor_MaxValueComputationExpression());
        Integer value = RGBValuesProvider.getIntFromAcceleoExpression(interpreter, evaluationContext, object, DescriptionPackage.eINSTANCE.getInterpolatedColor_ColorValueComputationExpression());

        min = min == null ? Integer.valueOf(0) : min;
        max = max == null ? Integer.valueOf(1) : max;
        value = value == null ? min : value;

        if (max.intValue() <= min.intValue()) {
            max = Integer.valueOf(min.intValue() + 1);
        }
        if (value.intValue() < min.intValue()) {
            value = Integer.valueOf(min.intValue());
        }
        if (value.intValue() > max.intValue()) {
            value = Integer.valueOf(max.intValue());
        }

        final RGBValues red = red();
        final RGBValues green = green();

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
        rgb.setRed(EnvironmentSystemColorFactory.clamp(valRed, 0, 255));
        rgb.setGreen(EnvironmentSystemColorFactory.clamp(valGreen, 0, 255));
        rgb.setBlue(EnvironmentSystemColorFactory.clamp(valBlue, 0, 255));
        return rgb;

    }

    private RGBValues red() {
        final RGBValues values = ViewpointFactory.eINSTANCE.createRGBValues();
        values.setRed(255);
        values.setBlue(0);
        values.setGreen(0);
        return values;
    }

    private RGBValues green() {
        final RGBValues values = ViewpointFactory.eINSTANCE.createRGBValues();
        values.setRed(0);
        values.setBlue(0);
        values.setGreen(255);
        return values;
    }

    private static Integer getIntFromAcceleoExpression(final IInterpreter interpreter, final EObject context, final EObject descriptionObject, final EStructuralFeature eFeature) {
        final String computationExpression = (String) descriptionObject.eGet(eFeature);
        try {
            return interpreter.evaluateInteger(context, computationExpression);
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, eFeature, e);
        }
        return Integer.valueOf(0);
    }

}
