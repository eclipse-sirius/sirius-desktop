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
package org.eclipse.sirius.business.internal.movida.registry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;

import com.google.common.base.Preconditions;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.query.ViewpointURIQuery;
import org.eclipse.sirius.business.internal.movida.Movida;

/**
 * This class overrides URIHandlerImpl.PlatformSchemeAware to normalize baseURI
 * before processing super.deresolve(uri).
 * 
 * @author smonnier, pcdavid
 */
public class SiriusURIHandler extends URIHandlerImpl.PlatformSchemeAware {
    private final ResourceSet resourceSet;

    /**
     * Constructor.
     * 
     * @param resourceSet
     *            the resource set to use for the conversion of physical URIs to
     *            logical <code>viewpoint:/</code> URIs on save..
     */
    public SiriusURIHandler(ResourceSet resourceSet) {
        this.resourceSet = Preconditions.checkNotNull(resourceSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI deresolve(URI uri) {
        if (!baseURI.isPlatform() && "viewpoint".equals(baseURI.scheme())) {
            baseURI = resourceSet.getURIConverter().normalize(baseURI);
        }
        if (Movida.isEnabled()) {
            Option<URI> viewpointURI = ViewpointURIQuery.asViewpointURI(uri, resourceSet);
            if (viewpointURI.some()) {
                return viewpointURI.get();
            }
        }
        return super.deresolve(uri);
    }
}
