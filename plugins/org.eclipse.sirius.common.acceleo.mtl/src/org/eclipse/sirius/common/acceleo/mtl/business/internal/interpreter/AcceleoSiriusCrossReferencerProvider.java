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
package org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.acceleo.common.utils.IAcceleoCrossReferenceProvider;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

/**
 * Implements the {@link IAcceleoCrossReferenceProvider} interface in order to
 * use the viewpoint cross referencer for "eInverse()" calls.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class AcceleoSiriusCrossReferencerProvider implements IAcceleoCrossReferenceProvider {
    /** The delegate cross referencer we'll be using. */
    private final ECrossReferenceAdapter crossReferencer;

    /**
     * Instantiate our cross referencer provider given its delegate.
     * 
     * @param crossReferencer
     *            The delegate cross referencer.
     */
    public AcceleoSiriusCrossReferencerProvider(ECrossReferenceAdapter crossReferencer) {
        this.crossReferencer = crossReferencer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.common.utils.IAcceleoCrossReferenceProvider#getInverseReferences(org.eclipse.emf.ecore.EObject)
     */
    public Set<EObject> getInverseReferences(EObject eObject) {
        final Set<EObject> result = new LinkedHashSet<>();
        for (EStructuralFeature.Setting setting : crossReferencer.getInverseReferences(eObject)) {
            result.add(setting.getEObject());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.common.utils.IAcceleoCrossReferenceProvider#getInverseReferences(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EClassifier)
     */
    public Set<EObject> getInverseReferences(EObject eObject, EClassifier filter) {
        final Set<EObject> result = new LinkedHashSet<>();
        for (EStructuralFeature.Setting setting : crossReferencer.getInverseReferences(eObject)) {
            final EObject eObj = setting.getEObject();
            if (filter == null || filter.isInstance(eObj)) {
                result.add(eObj);
            }
        }
        return result;
    }
}
