/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Queries on {@link List} of {@link EStructuralFeature}.
 * 
 * @author laurent.redor@obeo.fr
 */
public class EStructuralFeaturesQuery {

    private final List<EStructuralFeature> eStructuralFeatures;

    /**
     * Constructor.
     * 
     * @param eStructuralFeatures
     *            the list of EStructuralFeature to query.
     */
    public EStructuralFeaturesQuery(List<EStructuralFeature> eStructuralFeatures) {
        this.eStructuralFeatures = eStructuralFeatures;
    }

    /**
     * Get the common {@link EClass} for all
     * {@link EStructuralFeature#getEType()}, None {@link Option} if no common
     * type exists.
     * 
     * @return the common {@link EClass} for all
     *         {@link EStructuralFeature#getEType()}, None {@link Option} if no
     *         common type exists
     */
    public Option<EClass> getCommonType() {
        EClass commonType = null;
        Iterator<EStructuralFeature> it = eStructuralFeatures.iterator();
        if (it.hasNext()) {
            EStructuralFeature eStructuralFeature = it.next();
            if (eStructuralFeature.getEType() instanceof EClass) {
                commonType = (EClass) eStructuralFeature.getEType();
            }
            while (it.hasNext()) {
                EStructuralFeature next = it.next();
                if (next.getEType() instanceof EClass) {
                    EClass eType = (EClass) next.getEType();
                    if (!eType.equals(commonType) && !eType.getEAllSuperTypes().contains(commonType)) {
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
        if (commonType == null) {
            return Options.newNone();
        } else {
            return Options.newSome(commonType);
        }
    }
}
