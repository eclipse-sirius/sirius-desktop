/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.query;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.ext.base.Option;

/**
 * Queries on {@link List} of {@link EClass}.
 * 
 * @author laurent.redor@obeo.fr
 */
public class EClassesQuery {

    private final List<EClass> eClasses;

    /**
     * Constructor.
     * 
     * @param eClasses
     *            the list of EClass to query.
     */
    public EClassesQuery(List<EClass> eClasses) {
        this.eClasses = eClasses;
    }

    /**
     * Get the common {@link EClass} for all {@link EReference#getEType()}, None
     * {@link Option} if no common type exists. The {@link EReference} are
     * searched in <code>eClasses</code> according to <code>referenceName</code>
     * .
     * 
     * @param referenceName
     *            the name of the {@link EReference}
     * @return the common {@link EClass} for all
     *         {@link EStructuralFeature#getEType()}, None {@link Option} if no
     *         common type exists
     */
    public Option<EClass> getCommonTypeForReference(String referenceName) {
        Set<EStructuralFeature> eReferences = new LinkedHashSet<EStructuralFeature>();
        for (EClass eClazz : eClasses) {
            EStructuralFeature eStructuralFeature = eClazz.getEStructuralFeature(referenceName);
            if (eStructuralFeature instanceof EReference) {
                eReferences.add(eStructuralFeature);
            }
        }
        return new EStructuralFeaturesQuery(new ArrayList<EStructuralFeature>(eReferences)).getCommonType();
    }
}
