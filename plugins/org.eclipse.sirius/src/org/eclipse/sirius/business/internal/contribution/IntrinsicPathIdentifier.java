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
package org.eclipse.sirius.business.internal.contribution;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import com.google.common.base.Function;

/**
 * A function to obtain the path of an EObject, relative to its top-most parent.
 * The function produces the same results regardless of whether the element is
 * in a resource or not.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class IntrinsicPathIdentifier implements Function<EObject, String> {
    /**
     * {@inheritDoc}
     */
    public String apply(EObject from) {
        /*
         * Code taken from inside EcoreUtil.getURI(), but EcoreUtil.getURI()
         * does not use it if we are inside a resource or if the elements have
         * intrinsic IDs, and we want a result independent of these.
         */
        InternalEObject internalEObject = (InternalEObject) from;
        List<String> uriFragmentPath = new ArrayList<String>();
        for (InternalEObject container = internalEObject.eInternalContainer(); container != null; container = internalEObject.eInternalContainer()) {
            uriFragmentPath.add(container.eURIFragmentSegment(internalEObject.eContainingFeature(), internalEObject));
            internalEObject = container;
        }

        StringBuilder result = new StringBuilder("#//"); //$NON-NLS-1$
        for (int i = uriFragmentPath.size() - 1; i >= 0; --i) {
            result.append('/');
            result.append(uriFragmentPath.get(i));
        }
        return result.toString();
    }
}
