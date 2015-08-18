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
    private static final RGBValues RED = makeColor(255, 0, 0);

    private static final RGBValues GREEN = makeColor(0, 255, 0);

    /**
     * Predicate that validate a ColorSet.
     */
    private static final Predicate<ColorStep> WELL_DEFINED_STEP = new Predicate<ColorStep>() {
        public boolean apply(ColorStep input) {
            return input.getAssociatedColor() != null && input.getAssociatedValue() != null && !"".equals(input.getAssociatedValue()); //$NON-NLS-1$
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
        if (object != null) {
            return RGBValues.create(object.getRed(), object.getGreen(), object.getBlue());
        }
        return ViewpointFactory.eINSTANCE.createRGBValues();
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
        return makeColor(red, green, blue);
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
        if (object.getColorSteps().isEmpty() || !Iterables.any(object.getColorSteps(), WELL_DEFINED_STEP)) {
            return getDefaultRGBValues(object, evaluationContext, interpreter);
        } else {
            return getInterpolatedRGBValues(object, evaluationContext, interpreter);
        }
    }

    private RGBValues getInterpolatedRGBValues(final InterpolatedColor object, final EObject evaluationContext, final IInterpreter interpreter) {
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
        for (ColorStep colorStep : Iterables.filter(object.getColorSteps(), WELL_DEFINED_STEP)) {
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
        return makeColor(valRed, valGreen, valBlue);
    }

    private RGBValues getDefaultRGBValues(final InterpolatedColor object, final EObject evaluationContext, final IInterpreter interpreter) {
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
        final int valRed = (int) (GREEN.getRed() + ((RED.getRed() - GREEN.getRed()) * scale));
        final int valGreen = (int) (GREEN.getGreen() + ((RED.getGreen() - GREEN.getGreen()) * scale));
        final int valBlue = (int) (GREEN.getBlue() + ((RED.getBlue() - GREEN.getBlue()) * scale));
        return makeColor(valRed, valGreen, valBlue);
    }

    private static RGBValues makeColor(int red, int green, int blue) {
        return RGBValues.create(EnvironmentSystemColorFactory.clamp(red, 0, 255), EnvironmentSystemColorFactory.clamp(green, 0, 255), EnvironmentSystemColorFactory.clamp(blue, 0, 255));
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
