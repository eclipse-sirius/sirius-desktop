/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Utilities for preconfigured preprocessor.
 * 
 * @author mbats
 */
public final class PreconfiguredPreprocessorUtils {

    /**
     * Avoid instantiation.
     */
    private PreconfiguredPreprocessorUtils() {
        // empty
    }

    /**
     * Get the preconfigured features to filter for a given EClass.
     * 
     * @param eClass
     *            The EClass
     * @return The preconfigured features to filter:
     *         <ul>
     *         <li>the extends reference,</li>
     *         <li>all the filters of the extension mechanism.</li>
     *         </ul>
     */
    public static List<EStructuralFeature> getFeaturesToFilter(EClass eClass) {
        List<EStructuralFeature> featuresToFilter = new ArrayList<EStructuralFeature>();
        eClass.getEAllStructuralFeatures().stream().filter(feature -> feature.getName().equals("extends")).forEach(featuresToFilter::add); //$NON-NLS-1$
        return featuresToFilter;
    }

    /**
     * Get the preconfigured features to copy for a given EClass.
     * 
     * @param eClass
     *            The EClass
     * @return The preconfigured features to copy:
     *         <ul>
     *         <li>the actions</li>
     *         <li>the initial operation,</li>
     *         <li>the style,</li>
     *         <li>the conditional styles.</li>
     *         </ul>
     */
    public static List<EStructuralFeature> getFeaturesToCopy(EClass eClass) {
        List<EStructuralFeature> featuresToCopy = new ArrayList<EStructuralFeature>();
        eClass.getEAllStructuralFeatures().stream().filter(EReference.class::isInstance).map(EReference.class::cast).forEach(eReference -> {
            EClass featureType = eReference.getEReferenceType();
            if (isWidgetAction(featureType) || isStyle(featureType) || isInitialOperation(featureType)) {
                featuresToCopy.add(eReference);
            }
        });
        return featuresToCopy;
    }

    /**
     * Checks if an EClass is an
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitialOperation}.
     * 
     * @param eClass
     *            The eClass to test
     * @return True if the EClass is a InitialOperation otherwise false
     */
    private static boolean isInitialOperation(EClass featureType) {
        return ToolPackage.Literals.INITIAL_OPERATION.equals(featureType);
    }

    /**
     * Checks if an EClass is a
     * {@link org.eclipse.sirius.properties.WidgetAction}.
     * 
     * @param eClass
     *            The eClass to test
     * @return True if the EClass is a WidgetAction otherwise false
     */
    private static boolean isWidgetAction(EClass eClass) {
        return PropertiesPackage.Literals.WIDGET_ACTION.equals(eClass);
    }

    /**
     * Checks if an EClass is a style.
     * 
     * @param eClass
     *            The eClass to test
     * @return True if the EClass is a style otherwise false
     */
    private static boolean isStyle(EClass eClass) {
        return PropertiesPackage.Literals.WIDGET_STYLE.isSuperTypeOf(eClass) || PropertiesPackage.Literals.GROUP_STYLE.isSuperTypeOf(eClass)
                || PropertiesPackage.Literals.GROUP_CONDITIONAL_STYLE.isSuperTypeOf(eClass) || PropertiesPackage.Literals.WIDGET_CONDITIONAL_STYLE.isSuperTypeOf(eClass);
    }
}
