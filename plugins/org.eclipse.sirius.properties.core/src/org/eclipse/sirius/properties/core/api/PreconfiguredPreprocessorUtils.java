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
        List<EStructuralFeature> featuresToFilter = new ArrayList<>();
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
        List<EStructuralFeature> featuresToCopy = new ArrayList<>();
        eClass.getEAllStructuralFeatures().stream().filter(EReference.class::isInstance).map(EReference.class::cast).forEach(eReference -> {
            EClass featureType = eReference.getEReferenceType();
            if (shouldCopy(featureType)) {
                featuresToCopy.add(eReference);
            }
        });
        return featuresToCopy;
    }

    /**
     * Checks if an eClass should be copied by the preprocessor.
     * 
     * @param featureType
     * @return True if the EClass should be copied
     */
    private static boolean shouldCopy(EClass eClass) {
        boolean shouldCopy = false;

        shouldCopy = shouldCopy || ToolPackage.Literals.INITIAL_OPERATION.equals(eClass);

        shouldCopy = shouldCopy || PropertiesPackage.Literals.WIDGET_STYLE.isSuperTypeOf(eClass);
        shouldCopy = shouldCopy || PropertiesPackage.Literals.GROUP_STYLE.isSuperTypeOf(eClass);
        shouldCopy = shouldCopy || PropertiesPackage.Literals.GROUP_CONDITIONAL_STYLE.isSuperTypeOf(eClass);
        shouldCopy = shouldCopy || PropertiesPackage.Literals.WIDGET_CONDITIONAL_STYLE.isSuperTypeOf(eClass);

        return shouldCopy;
    }
}
