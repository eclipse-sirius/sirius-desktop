/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.color;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Class responsible to reflectively update a style color using the given
 * mapping between style features and style description features.
 * 
 * @author cbrun
 * 
 */
public class DiagramStyleColorUpdater extends AbstractColorUpdater {

    /**
     * A mapping between style description color features and style color
     * features.
     */
    private BiMap<EReference, EAttribute> descToStyleForColorFeatures;

    /**
     * Create a new {@link DiagramStyleColorUpdater} .
     * 
     */
    public DiagramStyleColorUpdater() {
        descToStyleForColorFeatures = HashBiMap.create();
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getBorderedStyleDescription_BorderColor(), DiagramPackage.eINSTANCE.getBorderedStyle_BorderColor());
        descToStyleForColorFeatures.put(org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelColor(),
                ViewpointPackage.eINSTANCE.getBasicLabelStyle_LabelColor());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getBundledImageDescription_Color(), DiagramPackage.eINSTANCE.getBundledImage_Color());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getDotDescription_BackgroundColor(), DiagramPackage.eINSTANCE.getDot_BackgroundColor());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getEdgeStyleDescription_StrokeColor(), DiagramPackage.eINSTANCE.getEdgeStyle_StrokeColor());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getEllipseNodeDescription_Color(), DiagramPackage.eINSTANCE.getEllipse_Color());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getFlatContainerStyleDescription_BackgroundColor(), DiagramPackage.eINSTANCE.getFlatContainerStyle_BackgroundColor());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getFlatContainerStyleDescription_ForegroundColor(), DiagramPackage.eINSTANCE.getFlatContainerStyle_ForegroundColor());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getGaugeSectionDescription_BackgroundColor(), DiagramPackage.eINSTANCE.getGaugeSection_BackgroundColor());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getGaugeSectionDescription_ForegroundColor(), DiagramPackage.eINSTANCE.getGaugeSection_ForegroundColor());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getLozengeNodeDescription_Color(), DiagramPackage.eINSTANCE.getLozenge_Color());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getNoteDescription_Color(), DiagramPackage.eINSTANCE.getNote_Color());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getSquareDescription_Color(), DiagramPackage.eINSTANCE.getSquare_Color());
        descToStyleForColorFeatures.put(StylePackage.eINSTANCE.getShapeContainerStyleDescription_BackgroundColor(), DiagramPackage.eINSTANCE.getShapeContainerStyle_BackgroundColor());
    }

    /**
     * Update the color features of the given style (if necessary) using the
     * style description.
     * 
     * @param context
     *            the current EObject, might be used if it's necessary to
     *            compute expressions.
     * @param style
     *            the style to update.
     * @param description
     *            the style description.
     * @param previousStyle
     *            the previous style (if existing) to keep compatible
     *            customization.
     */
    public void updateColors(final EObject context, final Customizable style, final EObject description, Option<? extends Customizable> previousStyle) {
        updateColorReflectively(context, style, description, previousStyle);
    }

    private void updateColorReflectively(final EObject context, final Customizable style, final EObject description, Option<? extends Customizable> previousStyle) {
        List<EAttribute> eAllAttributes = getAllStyleAttributes(style);

        for (final EAttribute feature : eAllAttributes) {
            if (descToStyleForColorFeatures.containsValue(feature) && description != null) {
                if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(feature.getName())) {
                    EStructuralFeature eStructuralFeature = previousStyle.get().eClass().getEStructuralFeature(feature.getName());
                    if (eStructuralFeature == null) {
                        // If we don't found a matching feature, we search in
                        // specific matching cases
                        if ("color".equals(feature.getName())) { //$NON-NLS-1$
                            eStructuralFeature = previousStyle.get().eClass().getEStructuralFeature("backgroundColor"); //$NON-NLS-1$
                        } else if ("backgroundColor".equals(feature.getName())) { //$NON-NLS-1$
                            eStructuralFeature = previousStyle.get().eClass().getEStructuralFeature("color"); //$NON-NLS-1$
                        }
                    }
                    // If we don't found a matching feature, we ignore the
                    // customization (probably a not compatible
                    // customization).
                    if (eStructuralFeature != null && eStructuralFeature.getEType() == feature.getEType()) {
                        Object customValue = previousStyle.get().eGet(eStructuralFeature);
                        style.eSet(feature, customValue);
                        style.getCustomFeatures().add(feature.getName());
                    }
                }
                if (!style.getCustomFeatures().contains(feature.getName())) {
                    updateNotCustomFeatureReflectively(context, style, description, feature);
                }
            }
        }
    }

    private void updateNotCustomFeatureReflectively(final EObject context, final Customizable style, final EObject description, final EAttribute feature) {
        // The feature is a color.
        final Object descValue = description.eGet(descToStyleForColorFeatures.inverse().get(feature));
        if (descValue instanceof ColorDescription) {
            final RGBValues newValues = getRGBValuesFromColorDescription(context, (ColorDescription) descValue);
            if (style.eGet(feature) != newValues) {
                style.eSet(feature, newValues);
            }
        }
    }

    /**
     * @param style
     * @param eAllReferences
     */
    private List<EAttribute> getAllStyleAttributes(final EObject style) {
        List<EAttribute> eAllAttributes = new ArrayList<>();
        EList<EAttribute> styleReferences = style.eClass().getEAllAttributes();
        eAllAttributes.addAll(styleReferences);
        return eAllAttributes;
    }

    /**
     * Update the colors of a style instance using the specified description.
     * 
     * @param style
     *            the style instance to update.
     * @param description
     *            the style description to use.
     * @param previousStyle
     *            the previous style (if existing) to keep compatible
     *            customization.
     */
    public void updateColors(final Style style, final StyleDescription description, Option<? extends Customizable> previousStyle) {
        updateColorReflectively(null, style, description, previousStyle);
    }

    /**
     * Update the colors of a style instance using the specified description.
     * 
     * @param style
     *            the style instance to update.
     * @param description
     *            the style description to use.
     * @param previousStyle
     *            the previous style (if existing) to keep compatible
     *            customization.
     */
    public void updateColors(final BasicLabelStyle style, final BasicLabelStyleDescription description, Option<? extends Customizable> previousStyle) {
        updateColorReflectively(null, style, description, previousStyle);
    }
}
