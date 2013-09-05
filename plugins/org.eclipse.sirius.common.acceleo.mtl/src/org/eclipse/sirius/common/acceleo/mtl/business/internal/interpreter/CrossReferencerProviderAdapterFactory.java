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
package org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter;

import org.eclipse.acceleo.common.utils.IAcceleoCrossReferenceProvider;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

/**
 * This will be registered against the platform in order to adapt EObjects into
 * {@link AcceleoSiriusCrossReferencerProvider}s.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class CrossReferencerProviderAdapterFactory implements IAdapterFactory {
    /** The delegate cross referencer we'll be using. */
    private final ECrossReferenceAdapter crossReferencer;

    /**
     * Instantiates our factory given the cross referencer we are to use.
     * 
     * @param crossReferencer
     *            The cross referencer we'll be using.
     */
    public CrossReferencerProviderAdapterFactory(ECrossReferenceAdapter crossReferencer) {
        this.crossReferencer = crossReferencer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object,
     *      java.lang.Class)
     */
    public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
        if (adaptableObject instanceof EObject && adapterType.equals(IAcceleoCrossReferenceProvider.class)) {
            Resource res = ((EObject) adaptableObject).eResource();
            if (res != null && res.eAdapters().contains(crossReferencer)) {
                return new AcceleoSiriusCrossReferencerProvider(crossReferencer);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
     */
    @SuppressWarnings("rawtypes")
    public Class[] getAdapterList() {
        return new Class[] { IAcceleoCrossReferenceProvider.class, };
    }
}
