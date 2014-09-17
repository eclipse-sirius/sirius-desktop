/*******************************************************************************
 * Copyright (c) 2014 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Predicate;

/**
 * Filter keeping resources instances for which the URI has content.
 * 
 * 
 * @author cbrun
 * 
 */
public class URIExists implements Predicate<Resource> {

    private Map<?, ?> options;

    /**
     * Create a new diagnose for the given resource.
     * 
     * @param options
     *            the save options to use to compute the new serialization.
     */
    public URIExists(final Map<?, ?> options) {
        this.options = options;
    }

    @Override
    public boolean apply(Resource resourcetoSave) {
        final URIConverter uriConverter = resourcetoSave.getResourceSet() == null ? new ResourceSetImpl().getURIConverter() : resourcetoSave.getResourceSet().getURIConverter();
        return uriConverter.exists(resourcetoSave.getURI(), options);
    }

}
