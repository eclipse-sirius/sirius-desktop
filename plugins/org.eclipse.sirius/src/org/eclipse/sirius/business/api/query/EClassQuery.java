/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * Queries on {@link EClass}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class EClassQuery {
    /**
     * The predicate used to identify containment features.
     * 
     * @author pierre-charles.david@obeo.fr
     */
    private static final class IsContaintmentFeature implements Predicate<EStructuralFeature> {
        public boolean apply(EStructuralFeature input) {
            return new EStructuralFeatureQuery(input).isContainment();
        }
    }

    private final EClass eClass;

    /**
     * Constructor.
     * 
     * @param eClass
     *            the EClass to query.
     */
    public EClassQuery(EClass eClass) {
        this.eClass = eClass;
    }

    /**
     * Returns all the containment features of the queried {@link EClass}.
     * 
     * @return all the containment features of the {@link EClass}.
     */
    public Collection<EStructuralFeature> getAllContainmentFeatures() {
        return ImmutableList.copyOf(Iterables.filter(eClass.getEAllStructuralFeatures(), new IsContaintmentFeature()));
    }

    /**
     * Returns all the non-containment features of the queried {@link EClass}.
     * 
     * @return all the non-containment features of the {@link EClass}.
     */
    public Collection<EStructuralFeature> getAllNonContainmentFeatures() {
        return ImmutableList.copyOf(Iterables.filter(eClass.getEAllStructuralFeatures(), Predicates.not(new IsContaintmentFeature())));
    }

    /**
     * Get from a list of {@link EObject} having all a {@link EReference} named
     * <code>referenceName</code>, the same or different ones, the common type
     * {@link EClass} of the {@link EReference#getEType()}, null if no common
     * type exists.
     * 
     * @param eObjects
     *            list of {@link EObject} for which get common type for the
     *            specified referenceName
     * @param referenceName
     *            the name of the {@link EReference}
     * @return a list of {@link EObject} having all a {@link EReference} named
     *         <code>referenceName</code>, the same or different ones, the
     *         common type {@link EClass} of the {@link EReference#getEType()},
     *         null if no common type exists
     */
    public EClass getCommonType(List<EObject> eObjects, String referenceName) {
        EClass commonType = null;
        Set<EReference> eReferences = new LinkedHashSet<EReference>();
        for (EObject eObject : eObjects) {
            EStructuralFeature eStructuralFeature = eObject.eClass().getEStructuralFeature(referenceName);
            if (eStructuralFeature instanceof EReference) {
                eReferences.add((EReference) eStructuralFeature);
            }
        }
        Iterator<EReference> eReferencesIterator = eReferences.iterator();
        if (eReferencesIterator.hasNext()) {
            EReference eReference = eReferencesIterator.next();
            if (eReference.getEType() instanceof EClass) {
                commonType = (EClass) eReference.getEType();
            }
            while (eReferencesIterator.hasNext()) {
                EReference next = eReferencesIterator.next();
                if (next.getEType() instanceof EClass) {
                    EClass eType = (EClass) next.getEType();
                    if (!eType.getEAllSuperTypes().contains(commonType)) {
                        if (commonType.getEAllSuperTypes().contains(eType)) {
                            commonType = eType;
                        } else {
                            commonType = null;
                            break;
                        }
                    }
                }
            }
        }
        return commonType;
    }
}
