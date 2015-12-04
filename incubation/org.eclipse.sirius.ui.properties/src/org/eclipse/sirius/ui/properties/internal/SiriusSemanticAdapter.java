/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * An adapter factory to convert an element selected in Sirius into the
 * underlying semantic element.
 */
public class SiriusSemanticAdapter implements IAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
        if (adapterType != null) {
            return SemanticElementFinder.getAssociatedSemanticElement(adaptableObject);
        } else {
            return null;
        }
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class<?>[] { EObject.class };
    }

}
