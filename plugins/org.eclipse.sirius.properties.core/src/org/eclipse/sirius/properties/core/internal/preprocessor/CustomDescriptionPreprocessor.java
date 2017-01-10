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
package org.eclipse.sirius.properties.core.internal.preprocessor;

import java.util.Collection;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionPreprocessorWithFiltering;
import org.eclipse.sirius.properties.core.api.PreconfiguredPreprocessorUtils;

/**
 * Preprocessor for {@link CustomDescription}.
 * 
 * @author mbats
 */
public class CustomDescriptionPreprocessor extends DefaultDescriptionPreprocessorWithFiltering<CustomDescription> {
    /**
     * The constructor.
     */
    public CustomDescriptionPreprocessor() {
        super(CustomDescription.class, PreconfiguredPreprocessorUtils.getFeaturesToFilter(PropertiesPackage.Literals.CUSTOM_DESCRIPTION), getFeaturesToCopy());
    }

    /**
     * Get the features to copy, the custom expressions and the custom
     * operations.
     * 
     * @return The features to copy
     */
    private static Collection<EStructuralFeature> getFeaturesToCopy() {
        Collection<EStructuralFeature> featuresToCopy = PreconfiguredPreprocessorUtils.getFeaturesToCopy(PropertiesPackage.Literals.CUSTOM_DESCRIPTION);
        featuresToCopy.add(PropertiesPackage.Literals.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS);
        featuresToCopy.add(PropertiesPackage.Literals.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS);
        return featuresToCopy;
    }
}
