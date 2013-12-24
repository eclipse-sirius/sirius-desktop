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
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.sirius.business.api.query.ViewpointURIQuery;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Preconditions;
/**
 * The converter used to normalize Viewpoint URIs into the URI of the concrete
 * resource their are currently provided by.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public final class ViewpointURIConverter extends ExtensibleURIConverterImpl {
    private final ViewpointRegistry registry;

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry used to translate logical viewpoint URIs into
     *            physical URIs.
     */
    public ViewpointURIConverter(ViewpointRegistry registry) {
        this.registry = Preconditions.checkNotNull(registry);
    }

    /**
     * Normalizes logical <code>viewpoint:/pluginId/ViewpointName</code> URIs
     * into the physical (platform) URI of the resource which currently provides
     * this Sirius in the registry. Non viewpoint URIs and viewpoint URIs
     * unknown to the registry are handled using the default behavior.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public URI normalize(URI uri) {
        if (ViewpointURIQuery.isValidViewpointURI(uri)) {
            ViewpointURIQuery q = new ViewpointURIQuery(uri);
            Option<Entry> entry = registry.getEntry(q.getBaseURI());
            if (entry.some()) {
                return entry.get().getResource().getURI();
            }
        }
        return super.normalize(uri);
    }
}
