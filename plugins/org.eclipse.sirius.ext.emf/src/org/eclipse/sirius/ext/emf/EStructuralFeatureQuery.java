/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ext.emf;

import java.util.Objects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Queries on Ecore {@link EStructuralFeature}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class EStructuralFeatureQuery {
    private final EStructuralFeature feature;

    /**
     * Constructor.
     * 
     * @param feature
     *            the feature to query.
     */
    public EStructuralFeatureQuery(EStructuralFeature feature) {
        this.feature = Objects.requireNonNull(feature);
    }

    /**
     * Tests whether the specified object has this feature.
     * 
     * @param obj
     *            an object.
     * @return <code>true</code> if the specified object has this feature.
     */
    public boolean existsIn(EObject obj) {
        return obj != null && obj.eClass().getEAllStructuralFeatures().contains(feature);
    }

    /**
     * Tests whether this feature is a containment feature.
     * 
     * @return <code>true</code> if this feature is a containment feature.
     */
    public boolean isContainment() {
        return feature.getEContainingClass().getEAllContainments().contains(feature);
    }

    /**
     * Checks whether the values for the specified feature are valid (in terms
     * of typing) for this feature.
     * 
     * @param source
     *            the feature to test compatibility with.
     * @return <code>true</code> if the values from the source feature are
     *         type-compatible with the queried feature.
     */
    public boolean isAssignableFrom(EStructuralFeature source) {
        boolean result = false;
        if (feature instanceof EAttribute && source instanceof EAttribute) {
            EDataType thisType = ((EAttribute) feature).getEAttributeType();
            EDataType sourceType = ((EAttribute) source).getEAttributeType();
            result = thisType.getInstanceClass().isAssignableFrom(sourceType.getInstanceClass());
        } else if (feature instanceof EReference && source instanceof EReference) {
            EClass hisType = ((EReference) feature).getEReferenceType();
            EClass sourceType = ((EReference) source).getEReferenceType();
            result = hisType.isSuperTypeOf(sourceType);
        }
        return result;
    }
}
