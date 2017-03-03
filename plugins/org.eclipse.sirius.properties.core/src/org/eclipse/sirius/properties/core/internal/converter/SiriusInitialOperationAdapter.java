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
package org.eclipse.sirius.properties.core.internal.converter;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;

/**
 * Adapter used to hold the URI of the original Sirius initial operation prior to the preprocessing.
 * 
 * @author sbegaudeau
 */
public class SiriusInitialOperationAdapter extends AdapterImpl {
    /**
     * The URI of the initial operation.
     */
    private URI initialOperationURI;

    /**
     * The URI of the initial operation.
     * 
     * @param initialOperationURI
     *            The URI of the initial operation
     */
    public SiriusInitialOperationAdapter(URI initialOperationURI) {
        this.initialOperationURI = initialOperationURI;
    }

    /**
     * Returns the URI of the initial operation.
     * 
     * @return The URI of the initial operation
     */
    public URI getInitialOperationURI() {
        return this.initialOperationURI;
    }

    @Override
    public boolean isAdapterForType(Object type) {
        if (SiriusInitialOperationAdapter.class.equals(type)) {
            return true;
        }
        return super.isAdapterForType(type);
    }
}
