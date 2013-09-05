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
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;

import com.google.common.base.Preconditions;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.query.SiriusURIQuery;

/**
 * The converter used to normalize Sirius URIs into the URI of the concrete
 * resource their are currently provided by.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public final class SiriusURIConverter extends URIConverterImpl {
    private final SiriusRegistry registry;

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry used to translate logical Sirius URIs into
     *            physical URIs.
     */
    public SiriusURIConverter(SiriusRegistry registry) {
        this.registry = Preconditions.checkNotNull(registry);
    }

    /**
     * Normalizes logical <code>viewpoint:/pluginId/SiriusName</code> URIs
     * into the physical (platform) URI of the resource which currently provides
     * this Sirius in the registry. Non Sirius URIs and Sirius URIs
     * unknown to the registry are handled using the default behavior.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public URI normalize(URI uri) {
        if (SiriusURIQuery.isValidSiriusURI(uri)) {
            SiriusURIQuery q = new SiriusURIQuery(uri);
            Option<Entry> entry = registry.getEntry(q.getBaseURI());
            if (entry.some()) {
                return entry.get().getResource().getURI();
            }
        }
        return super.normalize(uri);
    }
}
